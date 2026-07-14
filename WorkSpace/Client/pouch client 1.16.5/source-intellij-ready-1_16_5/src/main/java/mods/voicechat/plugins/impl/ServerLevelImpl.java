/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import java.util.Objects;
import lightning.product.e_3591_l;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public class ServerLevelImpl
implements ServerLevel {
    private final e_3591_l serverLevel;

    public ServerLevelImpl(e_3591_l serverLevel) {
        this.serverLevel = serverLevel;
    }

    @Override
    public Object getServerLevel() {
        return CommonCompatibilityManager.INSTANCE.createRawApiLevel(this.serverLevel);
    }

    public e_3591_l getRawServerLevel() {
        return this.serverLevel;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ServerLevelImpl that = (ServerLevelImpl)object;
        return Objects.equals(this.serverLevel, that.serverLevel);
    }

    public int hashCode() {
        return this.serverLevel != null ? this.serverLevel.hashCode() : 0;
    }
}

