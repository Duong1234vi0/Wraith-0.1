/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.eventforge;

import lightning.product.x_607_J;

public class TickEvent
implements x_607_J {
    public final Type type;
    public final Phase phase;

    public TickEvent(Type type, Phase phase) {
        this.type = type;
        this.phase = phase;
    }

    public static enum Type {
        WORLD,
        PLAYER,
        CLIENT,
        SERVER,
        RENDER;

    }

    public static enum Phase {
        START,
        END;

    }

    public static class ClientTickEvent
    extends TickEvent {
        public ClientTickEvent(Phase phase) {
            super(Type.CLIENT, phase);
        }
    }

    public static class ServerTickEvent
    extends TickEvent {
        public ServerTickEvent(Phase phase) {
            super(Type.SERVER, phase);
        }
    }
}

