/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api;

import mods.voicechat.api.Player;
import mods.voicechat.api.ServerLevel;

public interface ServerPlayer
extends Player {
    public ServerLevel getServerLevel();
}

