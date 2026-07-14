/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import mods.baritone.api.api.java.baritone.api.event.events.type.Cancellable;

public final class TabCompleteEvent
extends Cancellable {
    public final String prefix;
    public String[] completions;

    public TabCompleteEvent(String prefix) {
        this.prefix = prefix;
        this.completions = null;
    }
}

