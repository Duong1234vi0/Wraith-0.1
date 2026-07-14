/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.concurrent.ConcurrentCommentedConfig;
import com.electronwill.nightconfig.core.concurrent.SynchronizedConfig;
import com.electronwill.nightconfig.core.file.AsyncFileConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.ConfigLoadFilter;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.electronwill.nightconfig.core.utils.ConcurrentCommentedConfigWrapper;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

final class SyncFileConfig
extends ConcurrentCommentedConfigWrapper<SynchronizedConfig>
implements CommentedFileConfig {
    private final Path nioPath;
    private final Charset charset;
    private volatile boolean closed;
    private final ConfigWriter writer;
    private final WritingMode writingMode;
    private final ConfigParser<?> parser;
    private final FileNotFoundAction nefAction;
    private final ParsingMode parsingMode;
    private final ConfigLoadFilter reloadFilter;
    private final Runnable saveListener;
    private final Runnable loadListener;

    SyncFileConfig(SynchronizedConfig config, Path nioPath, Charset charset, ConfigWriter writer, WritingMode writingMode, ConfigParser<?> parser, ParsingMode parsingMode, FileNotFoundAction nefAction, ConfigLoadFilter reloadFilter, Runnable saveListener, Runnable loadListener) {
        super(config);
        this.nioPath = nioPath;
        this.charset = charset;
        this.writer = writer;
        this.parser = parser;
        this.parsingMode = parsingMode;
        this.nefAction = nefAction;
        this.writingMode = writingMode;
        this.reloadFilter = reloadFilter;
        this.saveListener = saveListener;
        this.loadListener = loadListener;
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
        ((SynchronizedConfig)this.config).bulkCommentedRead((? super UnmodifiableCommentedConfig config) -> this.writer.write((UnmodifiableConfig)config, this.nioPath, this.writingMode, this.charset));
        this.saveListener.run();
    }

    @Override
    public void load() {
        if (this.closed) {
            throw new IllegalStateException("This FileConfig is closed, cannot load().");
        }
        if (this.reloadFilter == null) {
            ((SynchronizedConfig)this.config).bulkCommentedUpdate((? super CommentedConfig view) -> this.parser.parse(this.nioPath, (Config)view, this.parsingMode, this.nefAction, this.charset));
        } else {
            Object newConfig = this.parser.parse(this.nioPath, this.nefAction, this.charset);
            CommentedConfig newCC = CommentedConfig.fake(newConfig);
            if (!this.reloadFilter.acceptNewVersion(newCC)) {
                return;
            }
            switch (this.parsingMode) {
                case REPLACE: {
                    ((SynchronizedConfig)this.config).replaceContentBy(newCC);
                    break;
                }
                default: {
                    AsyncFileConfig.putWithParsingMode(this.parsingMode, newCC, (ConcurrentCommentedConfig)this.config);
                }
            }
        }
        this.loadListener.run();
    }

    @Override
    public void close() {
        this.closed = true;
    }
}

