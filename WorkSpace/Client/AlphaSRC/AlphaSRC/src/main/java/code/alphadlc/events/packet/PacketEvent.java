/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 */
package code.alphadlc.events.packet;

import code.alphadlc.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_2596;

public class PacketEvent
extends EventCancellable {
    private class_2596<?> packet;
    private Type type;

    public boolean isSend() {
        return this.type.equals((Object)Type.SEND);
    }

    public class_2596<?> getPacket() {
        return this.packet;
    }

    public Type getType() {
        return this.type;
    }

    public void setPacket(class_2596<?> packet) {
        this.packet = packet;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public PacketEvent(class_2596<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    public static enum Type {
        SEND,
        RECEIVE;

    }
}
