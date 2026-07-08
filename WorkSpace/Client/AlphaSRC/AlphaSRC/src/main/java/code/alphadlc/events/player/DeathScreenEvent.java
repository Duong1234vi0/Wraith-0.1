/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;

public class DeathScreenEvent
implements Event {
    private int ticksSinceDeath;

    public int getTicksSinceDeath() {
        return this.ticksSinceDeath;
    }

    public DeathScreenEvent(int ticksSinceDeath) {
        this.ticksSinceDeath = ticksSinceDeath;
    }
}
