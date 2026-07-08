/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.event.events;

import code.alphadlc.utils.client.managers.event.events.Event;

public abstract class EventStoppable
implements Event {
    private boolean stopped;

    protected EventStoppable() {
    }

    public void stop() {
        this.stopped = true;
    }

    public boolean isStopped() {
        return this.stopped;
    }
}
