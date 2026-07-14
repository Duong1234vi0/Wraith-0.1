package ua.babka.event;

import net.minecraft.network.packet.Packet;
import ua.babka.event.Event;

public class EventPacket
extends Event {
    private final Packet<?> packet;
    private Type type;

    private boolean isSent() {
        return this.type == Type.SEND;
    }

    private boolean isReceive() {
        return this.type == Type.RECEIVE;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public Type getType() {
        return this.type;
    }

    public EventPacket(Packet<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    public static enum Type {
        SEND,
        RECEIVE;

    }
}

