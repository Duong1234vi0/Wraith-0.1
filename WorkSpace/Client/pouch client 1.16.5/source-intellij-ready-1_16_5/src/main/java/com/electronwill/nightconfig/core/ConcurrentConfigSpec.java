/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigSpec;

@Deprecated
public class ConcurrentConfigSpec
extends ConfigSpec {
    public ConcurrentConfigSpec() {
        super(Config.inMemoryUniversalConcurrent());
    }
}

