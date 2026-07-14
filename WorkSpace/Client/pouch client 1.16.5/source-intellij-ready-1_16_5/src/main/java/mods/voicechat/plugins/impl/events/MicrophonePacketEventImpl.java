/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.events;

import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.MicrophonePacketEvent;
import mods.voicechat.api.packets.MicrophonePacket;
import mods.voicechat.plugins.impl.events.PacketEventImpl;

public class MicrophonePacketEventImpl
extends PacketEventImpl<MicrophonePacket>
implements MicrophonePacketEvent {
    public MicrophonePacketEventImpl(MicrophonePacket packet, VoicechatConnection connection) {
        super(packet, connection, null);
    }
}

