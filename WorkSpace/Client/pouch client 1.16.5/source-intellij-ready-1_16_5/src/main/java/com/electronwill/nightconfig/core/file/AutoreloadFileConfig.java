/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileWatcher;
import com.electronwill.nightconfig.core.utils.ConcurrentCommentedConfigWrapper;
import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

final class AutoreloadFileConfig<C extends CommentedFileConfig>
extends ConcurrentCommentedConfigWrapper<C>
implements CommentedFileConfig {
    private final FileWatcher watcher;
    private final Runnable autoListener;

    AutoreloadFileConfig(C config, FileWatcher watcher, Runnable autoreloadListener) {
        super(config);
        this.watcher = watcher;
        this.autoListener = autoreloadListener;
        watcher.addWatch(config.getNioPath(), this::autoReload);
    }

    private void autoReload() {
        this.load();
        this.autoListener.run();
    }

    @Override
    public File getFile() {
        return ((CommentedFileConfig)this.config).getFile();
    }

    @Override
    public Path getNioPath() {
        return ((CommentedFileConfig)this.config).getNioPath();
    }

    @Override
    public void save() {
        ((CommentedFileConfig)this.config).save();
    }

    @Override
    public void load() {
        ((CommentedFileConfig)this.config).load();
    }

    @Override
    public void close() {
        try {
            this.watcher.removeWatchFuture(((CommentedFileConfig)this.config).getNioPath()).get(5L, TimeUnit.SECONDS);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        finally {
            ((CommentedFileConfig)this.config).close();
        }
    }
}

