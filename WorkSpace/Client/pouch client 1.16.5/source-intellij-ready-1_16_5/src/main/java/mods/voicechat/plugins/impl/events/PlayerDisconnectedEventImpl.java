/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import java.util.UUID;
import mods.voicechat.api.events.PlayerDisconnectedEvent;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class PlayerDisconnectedEventImpl
extends ServerEventImpl
implements PlayerDisconnectedEvent {
    protected UUID player;

    public PlayerDisconnectedEventImpl(UUID player) {
        this.player = player;
    }

    @Override
    public UUID getPlayerUuid() {
        return this.player;
    }
}

