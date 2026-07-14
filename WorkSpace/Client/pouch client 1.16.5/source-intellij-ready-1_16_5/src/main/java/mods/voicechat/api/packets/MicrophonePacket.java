/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.packets;

import mods.voicechat.api.packets.ConvertablePacket;
import mods.voicechat.api.packets.Packet;

public interface MicrophonePacket
extends ConvertablePacket,
Packet {
    public boolean isWhispering();

    public byte[] getOpusEncodedData();

    public void setOpusEncodedData(byte[] var1);
}

