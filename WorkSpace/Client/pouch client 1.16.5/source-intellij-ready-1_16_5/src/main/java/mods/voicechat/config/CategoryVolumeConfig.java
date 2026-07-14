/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.config;

import java.nio.file.Path;
import mods.voicechat.config.VolumeConfigBase;

public class CategoryVolumeConfig
extends VolumeConfigBase<String> {
    public static final String OTHER_CATEGORY = "other";

    public CategoryVolumeConfig(Path path) {
        super(path);
    }

    @Override
    protected String getConfigName() {
        return "category";
    }

    @Override
    protected String mapKey(String key) {
        return key;
    }

    @Override
    protected String serializeKey(String key) {
        return key;
    }
}

