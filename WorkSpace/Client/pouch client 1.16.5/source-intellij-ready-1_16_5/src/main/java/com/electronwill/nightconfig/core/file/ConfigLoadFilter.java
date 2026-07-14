/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.CommentedConfig;

@FunctionalInterface
public interface ConfigLoadFilter {
    public boolean acceptNewVersion(CommentedConfig var1);
}

