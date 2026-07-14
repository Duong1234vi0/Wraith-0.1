/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.events;

import java.util.UUID;
import lightning.product.x_607_J;

public class ServerVoiceChatDisconnectedEvent
implements x_607_J {
    private final UUID playerID;

    public ServerVoiceChatDisconnectedEvent(UUID playerID) {
        this.playerID = playerID;
    }

    public UUID getPlayerID() {
        return this.playerID;
    }
}

