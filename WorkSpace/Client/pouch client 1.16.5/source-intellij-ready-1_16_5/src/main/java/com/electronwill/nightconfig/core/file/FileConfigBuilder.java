/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.file.GenericBuilder;
import java.nio.file.Path;

public class FileConfigBuilder
extends GenericBuilder<Config, FileConfig> {
    FileConfigBuilder(Path file, ConfigFormat<? extends Config> format) {
        super(file, format);
    }
}

