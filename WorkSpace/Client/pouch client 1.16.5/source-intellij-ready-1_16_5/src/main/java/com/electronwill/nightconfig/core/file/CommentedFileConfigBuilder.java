/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.GenericBuilder;
import java.nio.file.Path;

public final class CommentedFileConfigBuilder
extends GenericBuilder<CommentedConfig, CommentedFileConfig> {
    CommentedFileConfigBuilder(Path file, ConfigFormat<? extends CommentedConfig> format) {
        super(file, format);
    }
}

