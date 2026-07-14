/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import lightning.product.B_4088_l;
import lightning.product.e_3591_l;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.ServerPlayer;
import mods.voicechat.plugins.impl.PlayerImpl;
import mods.voicechat.plugins.impl.ServerLevelImpl;

public class ServerPlayerImpl
extends PlayerImpl
implements ServerPlayer {
    public ServerPlayerImpl(B_4088_l entity) {
        super(entity);
    }

    public B_4088_l getRealServerPlayer() {
        return (B_4088_l)this.entity;
    }

    @Override
    public ServerLevel getServerLevel() {
        return new ServerLevelImpl((e_3591_l)this.entity.O_508_d);
    }
}

