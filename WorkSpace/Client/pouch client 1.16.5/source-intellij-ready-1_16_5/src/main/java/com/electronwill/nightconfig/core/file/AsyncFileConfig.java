/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.concurrent.StampedConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.ConfigLoadFilter;
import com.electronwill.nightconfig.core.file.DebouncedRunnable;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.core.io.IoUtils;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingException;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.core.utils.ConcurrentCommentedConfigWrapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

final class AsyncFileConfig
extends ConcurrentCommentedConfigWrapper<StampedConfig>
implements CommentedFileConfig {
    static final Duration DEFAULT_WRITE_DEBOUNCE_TIME = Duration.ofSeconds(1L);
    private final boolean asyncLoad;
    private volatile boolean closed;
    private final Path nioPath;
    private final DebouncedRunnable saveTask;
    private final ConfigWriter configWriter;
    private final WritingMode writingMode;
    private final ConfigParser<?> configParser;
    private final ParsingMode parsingMode;
    private final FileNotFoundAction notFoundAction;
    private final Charset charset;
    private final ConfigLoadFilter reloadFilter;
    private final Runnable saveListener;
    private final Runnable loadListener;

    AsyncFileConfig(StampedConfig config, Path nioPath, Charset charset, ConfigWriter writer, WritingMode writingMode, ConfigParser<?> parser, ParsingMode parsingMode, FileNotFoundAction notFoundAction, boolean asyncLoad, ConfigLoadFilter reloadFilter, Runnable saveListener, Runnable loadListener, Duration debounceTime) {
        super(config);
        this.asyncLoad = asyncLoad;
        this.nioPath = nioPath;
        this.writingMode = writingMode;
        this.configWriter = writer;
        this.saveTask = new DebouncedRunnable(this::saveNow, debounceTime);
        this.configParser = parser;
        this.parsingMode = parsingMode;
        this.notFoundAction = notFoundAction;
        this.charset = charset;
        this.reloadFilter = reloadFilter;
        this.saveListener = saveListener;
        this.loadListener = loadListener;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void saveNow() {
        StampedConfig.Accumulator copy = ((StampedConfig)this.config).newAccumulatorCopy();
        AsyncFileConfig asyncFileConfig = this;
        synchronized (asyncFileConfig) {
            BufferedWriter fileWriter;
            if (this.writingMode == WritingMode.REPLACE_ATOMIC) {
                Path tmp = this.nioPath.resolveSibling(IoUtils.tempConfigFileName(this.nioPath));
                try (BufferedWriter writer = Files.newBufferedWriter(tmp, this.charset, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);){
                    this.configWriter.write((UnmodifiableConfig)copy, writer);
                }
                catch (IOException e) {
                    String msg = String.format("Failed to write (%s) the config to: %s", this.writingMode.toString(), tmp.toString());
                    throw new WritingException(msg, e);
                }
                try {
                    IoUtils.retryIfAccessDenied("move", () -> Files.move(tmp, this.nioPath, StandardCopyOption.ATOMIC_MOVE));
                }
                catch (AtomicMoveNotSupportedException e) {
                    String msg = String.format("Failed to atomically move the config from '%s' to '%s': WritingMode.REPLACE_ATOMIC is not supported for this path, use WritingMode.REPLACE instead.\n%s", tmp.toString(), this.nioPath.toString(), "Note: you may see *.new.tmp files after this error, they contain the \"new version\" of your configurations and can be safely removed.If you want, you can manually copy their content into your regular configuration files (replacing the old config).");
                    throw new WritingException(msg, e);
                }
                catch (IOException e) {
                    String msg = String.format("Failed to atomically write (%s) the config to: %s", this.writingMode.toString(), tmp.toString());
                    throw new WritingException(msg, e);
                }
            }
            StandardOpenOption lastOption = this.writingMode == WritingMode.APPEND ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;
            try {
                fileWriter = Files.newBufferedWriter(this.nioPath, this.charset, StandardOpenOption.WRITE, StandardOpenOption.CREATE, lastOption);
            }
            catch (IOException e) {
                throw new WritingException("Failed to open a BufferedWriter on: " + String.valueOf(this.nioPath), e);
            }
            this.configWriter.write((UnmodifiableConfig)copy, fileWriter);
            try {
                if (this.closed) {
                    fileWriter.close();
                } else {
                    fileWriter.flush();
                }
            }
            catch (IOException e) {
                String op = this.closed ? "close" : "flush";
                String msg = String.format("Buffer %s failed while saving asynchronous FileConfig.", op);
                throw new WritingException(msg, e);
            }
        }
        this.saveListener.run();
    }

    private void loadNow() {
        Object newConfig = this.configParser.parse(this.nioPath, this.notFoundAction, this.charset);
        CommentedConfig newCC = CommentedConfig.fake(newConfig);
        if (this.reloadFilter != null && !this.reloadFilter.acceptNewVersion(newCC)) {
            return;
        }
        switch (this.parsingMode) {
            case REPLACE: {
                StampedConfig newSafeContent = ((StampedConfig)this.config).createSubConfig();
                newSafeContent.putAll(newCC);
                newSafeContent.putAllComments(newCC);
                ((StampedConfig)this.config).replaceContentBy(newSafeContent);
                break;
            }
            default: {
                AsyncFileConfig.putWithParsingMode(this.parsingMode, newCC, (ConcurrentCommentedConfig)this.config);
            }
        }
        this.loadListener.run();
    }

    static void putWithParsingMode(ParsingMode parsingMode, CommentedConfig newCC, ConcurrentCommentedConfig config) {
        config.bulkCommentedUpdate((? super CommentedConfig view) -> {
            for (CommentedConfig.Entry entry : newCC.entrySet()) {
                List<String> key = Collections.singletonList(entry.getKey());
                Object value = entry.getRawValue();
                if (value instanceof UnmodifiableConfig && value.getClass() != config.getClass()) {
                    ConcurrentCommentedConfig newSafeContent = config.createSubConfig();
                    newSafeContent.putAll((UnmodifiableConfig)value);
                    if (value instanceof UnmodifiableCommentedConfig) {
                        newSafeContent.putAllComments((UnmodifiableCommentedConfig)value);
                    }
                    value = newSafeContent;
                }
                parsingMode.put((Config)view, key, value);
            }
        });
    }

    @Override
    public File getFile() {
        return this.nioPath.toFile();
    }

    @Override
    public Path getNioPath() {
        return this.nioPath;
    }

    @Override
    public void save() {
        if (this.closed) {
            throw new IllegalStateException("This FileConfig is closed, cannot save().");
        }
        this.saveTask.run(LazyExecutorHolder.sharedExecutor);
    }

    public void asyncLoad() {
        LazyExecutorHolder.sharedExecutor.execute(() -> this.loadNow());
    }

    @Override
    public void load() {
        if (this.closed) {
            throw new IllegalStateException("This FileConfig is closed, cannot load().");
        }
        if (this.asyncLoad) {
            this.asyncLoad();
        } else {
            this.loadNow();
        }
    }

    @Override
    public void close() {
        this.closed = true;
    }

    private static final class LazyExecutorHolder {
        private static final ScheduledExecutorService sharedExecutor;

        private LazyExecutorHolder() {
        }

        static {
            int poolSize = Runtime.getRuntime().availableProcessors();
            ThreadFactory defaultFactory = Executors.defaultThreadFactory();
            ThreadFactory factory = r -> {
                Thread t = defaultFactory.newThread(r);
                t.setDaemon(true);
                return t;
            };
            sharedExecutor = Executors.newScheduledThreadPool(poolSize, factory);
        }
    }
}

