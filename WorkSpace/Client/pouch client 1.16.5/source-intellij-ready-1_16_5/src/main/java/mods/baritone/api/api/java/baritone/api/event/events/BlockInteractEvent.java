/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import lightning.product.c_1514_x;

public final class BlockInteractEvent {
    private final c_1514_x pos;
    private final Type type;

    public BlockInteractEvent(c_1514_x pos, Type type) {
        this.pos = pos;
        this.type = type;
    }

    public final c_1514_x getPos() {
        return this.pos;
    }

    public final Type getType() {
        return this.type;
    }

    public static enum Type {
        START_BREAK,
        USE;

    }
}

