/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.Config
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.config;

import de.maxhenkel.configbuilder.Config;
import javax.annotation.Nullable;
import mods.voicechat.api.config.ConfigAccessor;

public class ConfigAccessorImpl
implements ConfigAccessor {
    private Config config;

    public ConfigAccessorImpl(Config config) {
        this.config = config;
    }

    @Override
    public boolean hasKey(String key) {
        return this.config.getEntries().containsKey(key);
    }

    @Override
    @Nullable
    public String getValue(String key) {
        return (String)this.config.getEntries().get(key);
    }
}

