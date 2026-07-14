/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.events;

import java.util.UUID;
import mods.voicechat.api.events.ServerEvent;

public interface PlayerDisconnectedEvent
extends ServerEvent {
    public UUID getPlayerUuid();
}

