/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.common;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.b_2585_i;
import lightning.product.e_2866_D;
import mods.voicechat.voice.common.SoundPacket;

public class LocationSoundPacket
extends SoundPacket<LocationSoundPacket> {
    protected e_2866_D location;
    protected float distance;

    public LocationSoundPacket(UUID channelId, UUID sender, e_2866_D location, byte[] data, long sequenceNumber, float distance, @Nullable String category) {
        super(channelId, sender, data, sequenceNumber, category);
        this.location = location;
        this.distance = distance;
    }

    public LocationSoundPacket(UUID channelId, UUID sender, short[] data, e_2866_D location, float distance, @Nullable String category) {
        super(channelId, sender, data, category);
        this.location = location;
        this.distance = distance;
    }

    public LocationSoundPacket() {
    }

    public e_2866_D getLocation() {
        return this.location;
    }

    public float getDistance() {
        return this.distance;
    }

    @Override
    public LocationSoundPacket fromBytes(b_2585_i buf) {
        LocationSoundPacket soundPacket = new LocationSoundPacket();
        soundPacket.channelId = buf.w_1484_f();
        soundPacket.sender = buf.w_1484_f();
        soundPacket.location = new e_2866_D(buf.readDouble(), buf.readDouble(), buf.readDouble());
        soundPacket.data = buf.n_1700_B();
        soundPacket.sequenceNumber = buf.readLong();
        soundPacket.distance = buf.readFloat();
        byte data = buf.readByte();
        if (this.hasFlag(data, (byte)2)) {
            soundPacket.category = buf.P_1922_E(16);
        }
        return soundPacket;
    }

    @Override
    public void toBytes(b_2585_i buf) {
        buf.n_1700_B(this.channelId);
        buf.n_1700_B(this.sender);
        buf.writeDouble(this.location.J_1907_R);
        buf.writeDouble(this.location.R_4764_Y);
        buf.writeDouble(this.location.G_564_y);
        buf.n_1700_B(this.data);
        buf.writeLong(this.sequenceNumber);
        buf.writeFloat(this.distance);
        byte data = 0;
        if (this.category != null) {
            data = this.setFlag(data, (byte)2);
        }
        buf.writeByte(data);
        if (this.category != null) {
            buf.n_1700_B(this.category, 16);
        }
    }
}

