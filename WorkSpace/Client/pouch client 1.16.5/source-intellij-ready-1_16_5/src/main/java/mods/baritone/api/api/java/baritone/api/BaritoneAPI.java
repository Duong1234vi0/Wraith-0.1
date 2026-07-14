/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api;

import mods.baritone.api.api.java.baritone.api.IBaritoneProvider;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;

public final class BaritoneAPI {
    private static final IBaritoneProvider provider;
    private static final Settings settings;

    public static IBaritoneProvider getProvider() {
        return provider;
    }

    public static Settings getSettings() {
        return settings;
    }

    static {
        settings = new Settings();
        SettingsUtil.readAndApply(settings, "settings.txt");
        try {
            provider = (IBaritoneProvider)Class.forName("mods.baritone.BaritoneProvider").newInstance();
        }
        catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
}

