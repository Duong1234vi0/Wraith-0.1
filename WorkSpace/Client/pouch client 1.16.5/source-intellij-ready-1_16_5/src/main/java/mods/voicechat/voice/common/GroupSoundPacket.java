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
import mods.voicechat.voice.common.SoundPacket;

public class GroupSoundPacket
extends SoundPacket<GroupSoundPacket> {
    public GroupSoundPacket(UUID channelId, UUID sender, byte[] data, long sequenceNumber, @Nullable String category) {
        super(channelId, sender, data, sequenceNumber, category);
    }

    public GroupSoundPacket(UUID channelId, UUID sender, short[] data, @Nullable String category) {
        super(channelId, sender, data, category);
    }

    public GroupSoundPacket() {
    }

    @Override
    public GroupSoundPacket fromBytes(b_2585_i buf) {
        GroupSoundPacket soundPacket = new GroupSoundPacket();
        soundPacket.channelId = buf.w_1484_f();
        soundPacket.sender = buf.w_1484_f();
        soundPacket.data = buf.n_1700_B();
        soundPacket.sequenceNumber = buf.readLong();
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
        buf.n_1700_B(this.data);
        buf.writeLong(this.sequenceNumber);
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

