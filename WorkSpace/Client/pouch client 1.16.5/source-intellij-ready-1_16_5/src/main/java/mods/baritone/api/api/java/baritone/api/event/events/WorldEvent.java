/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import lightning.product.k_4690_i;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;

public final class WorldEvent {
    private final k_4690_i world;
    private final EventState state;

    public WorldEvent(k_4690_i world, EventState state) {
        this.world = world;
        this.state = state;
    }

    public final k_4690_i getWorld() {
        return this.world;
    }

    public final EventState getState() {
        return this.state;
    }
}

