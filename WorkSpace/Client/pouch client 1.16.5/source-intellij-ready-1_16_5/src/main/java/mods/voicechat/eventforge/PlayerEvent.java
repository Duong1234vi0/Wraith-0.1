/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.a_3913_L;
import mods.voicechat.eventforge.LivingEvent;

public class PlayerEvent
extends LivingEvent {
    private final a_3913_L entityPlayer;

    public PlayerEvent(a_3913_L player) {
        super(player);
        this.entityPlayer = player;
    }

    public a_3913_L getPlayer() {
        return this.entityPlayer;
    }

    public static class PlayerLoggedOutEvent
    extends PlayerEvent {
        public PlayerLoggedOutEvent(a_3913_L player) {
            super(player);
        }
    }

    public static class PlayerLoggedInEvent
    extends PlayerEvent {
        public PlayerLoggedInEvent(a_3913_L player) {
            super(player);
        }
    }
}

