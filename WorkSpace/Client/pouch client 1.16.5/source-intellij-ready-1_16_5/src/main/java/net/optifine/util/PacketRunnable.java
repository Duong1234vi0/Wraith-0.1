/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.Packet;

public class PacketRunnable
implements Runnable {
    private Packet packet;
    private Runnable runnable;

    public PacketRunnable(Packet packet, Runnable runnable) {
        this.packet = packet;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        this.runnable.run();
    }

    public Packet getPacket() {
        return this.packet;
    }

    public String toString() {
        return "PacketRunnable: " + String.valueOf(this.packet);
    }
}


