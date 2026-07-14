/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;

public final class PlayerUpdateEvent {
    private final EventState state;

    public PlayerUpdateEvent(EventState state) {
        this.state = state;
    }

    public final EventState getState() {
        return this.state;
    }
}

