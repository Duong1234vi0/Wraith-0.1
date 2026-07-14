/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl;

import lightning.product.a_3913_L;
import mods.voicechat.api.Player;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;
import mods.voicechat.plugins.impl.EntityImpl;

public class PlayerImpl
extends EntityImpl
implements Player {
    public PlayerImpl(a_3913_L entity) {
        super(entity);
    }

    @Override
    public Object getPlayer() {
        return CommonCompatibilityManager.INSTANCE.createRawApiPlayer(this.getRealPlayer());
    }

    public a_3913_L getRealPlayer() {
        return (a_3913_L)this.entity;
    }
}

