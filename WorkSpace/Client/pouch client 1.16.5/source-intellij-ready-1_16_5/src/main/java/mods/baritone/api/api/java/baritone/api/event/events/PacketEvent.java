/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.event.events;

import lightning.product.c_1633_k;
import lightning.product.Packet;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;

public final class PacketEvent {
    private final c_1633_k networkManager;
    private final EventState state;
    private final Packet<?> packet;

    public PacketEvent(c_1633_k networkManager, EventState state, Packet<?> packet) {
        this.networkManager = networkManager;
        this.state = state;
        this.packet = packet;
    }

    public final c_1633_k getNetworkManager() {
        return this.networkManager;
    }

    public final EventState getState() {
        return this.state;
    }

    public final Packet<?> getPacket() {
        return this.packet;
    }

    public final <T extends Packet<?>> T cast() {
        return (T)this.packet;
    }
}


