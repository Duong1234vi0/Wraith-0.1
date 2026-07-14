/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events.type;

import mods.baritone.api.api.java.baritone.api.event.events.type.ICancellable;

public class Cancellable
implements ICancellable {
    private boolean cancelled;

    @Override
    public final void cancel() {
        this.cancelled = true;
    }

    @Override
    public final boolean isCancelled() {
        return this.cancelled;
    }
}

