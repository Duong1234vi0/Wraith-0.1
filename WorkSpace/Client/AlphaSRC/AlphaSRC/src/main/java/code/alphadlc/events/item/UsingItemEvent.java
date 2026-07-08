/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 */
package code.alphadlc.events.item;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_1268;

public class UsingItemEvent
extends EventCancellable {
    private final byte type;
    private final class_1268 hand;

    public UsingItemEvent(byte type) {
        this(type, null);
    }

    public UsingItemEvent(byte type, class_1268 hand) {
        this.type = type;
        this.hand = hand;
    }

    public byte getType() {
        return this.type;
    }

    public class_1268 getHand() {
        return this.hand;
    }
}
