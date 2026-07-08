/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.events.player;

import code.alphadlc.utils.client.managers.event.events.Event;

public class RotationUpdateEvent
implements Event {
    byte type;

    public byte getType() {
        return this.type;
    }

    public RotationUpdateEvent(byte type) {
        this.type = type;
    }
}
