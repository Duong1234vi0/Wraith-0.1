/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.packets;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.api.packets.ConvertablePacket;
import mods.voicechat.api.packets.Packet;

public interface SoundPacket
extends ConvertablePacket,
Packet {
    public UUID getChannelId();

    public UUID getSender();

    public byte[] getOpusEncodedData();

    public long getSequenceNumber();

    @Nullable
    public String getCategory();

    public static interface Builder<T extends Builder<T, P>, P extends SoundPacket> {
        public T channelId(UUID var1);

        public T opusEncodedData(byte[] var1);

        public T category(@Nullable String var1);

        public P build();
    }
}

