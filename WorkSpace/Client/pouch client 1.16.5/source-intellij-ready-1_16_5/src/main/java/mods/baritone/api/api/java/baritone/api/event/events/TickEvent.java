/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import java.util.function.BiFunction;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;

public final class TickEvent {
    private static int overallTickCount;
    private final EventState state;
    private final Type type;
    private final int count;

    public TickEvent(EventState state, Type type, int count) {
        this.state = state;
        this.type = type;
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public Type getType() {
        return this.type;
    }

    public EventState getState() {
        return this.state;
    }

    public static synchronized BiFunction<EventState, Type, TickEvent> createNextProvider() {
        int count = overallTickCount++;
        return (state, type) -> new TickEvent((EventState)((Object)state), (Type)((Object)type), count);
    }

    public static enum Type {
        IN,
        OUT;

    }
}

