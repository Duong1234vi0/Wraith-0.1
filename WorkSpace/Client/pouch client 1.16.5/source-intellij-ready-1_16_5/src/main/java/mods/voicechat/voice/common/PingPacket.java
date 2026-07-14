/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import java.util.UUID;
import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;

public class PingPacket
implements Packet<PingPacket> {
    private UUID id;
    private long timestamp;

    public PingPacket(UUID id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public PingPacket() {
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public PingPacket fromBytes(b_2585_i buf) {
        PingPacket soundPacket = new PingPacket();
        soundPacket.id = buf.w_1484_f();
        soundPacket.timestamp = buf.readLong();
        return soundPacket;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.id);
        buf.writeLong(this.timestamp);
    }
}

