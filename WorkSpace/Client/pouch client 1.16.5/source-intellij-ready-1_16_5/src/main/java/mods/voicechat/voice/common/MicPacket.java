/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import lightning.product.b_2585_i;
import mods.voicechat.voice.common.Packet;

public class MicPacket
implements Packet<MicPacket> {
    private byte[] data;
    private boolean whispering;
    private long sequenceNumber;

    public MicPacket(byte[] data, boolean whispering, long sequenceNumber) {
        this.data = data;
        this.whispering = whispering;
        this.sequenceNumber = sequenceNumber;
    }

    public MicPacket() {
    }

    @Override
    public long getTTL() {
        return 500L;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public boolean isWhispering() {
        return this.whispering;
    }

    @Override
    public MicPacket fromBytes(b_2585_i buf) {
        MicPacket soundPacket = new MicPacket();
        soundPacket.data = buf.n_1700_B();
        soundPacket.sequenceNumber = buf.readLong();
        soundPacket.whispering = buf.readBoolean();
        return soundPacket;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.data);
        buf.writeLong(this.sequenceNumber);
        buf.writeBoolean(this.whispering);
    }
}

