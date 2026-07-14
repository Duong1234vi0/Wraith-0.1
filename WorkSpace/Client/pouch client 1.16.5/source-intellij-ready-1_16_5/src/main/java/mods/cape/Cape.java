/*
 * Decompiled with CFR 0.152.
 */
package mods.cape;

import mods.cape.Config;

public class Cape {
    public static Cape INSTANCE;
    public static Config config;

    public void init() {
        INSTANCE = this;
        config = new Config();
    }
}

