/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.SoundPacketEvent;
import mods.voicechat.api.packets.Packet;
import mods.voicechat.plugins.impl.events.PacketEventImpl;

public class SoundPacketEventImpl<T extends Packet>
extends PacketEventImpl<T>
implements SoundPacketEvent<T> {
    private final String source;

    public SoundPacketEventImpl(T packet, @Nullable VoicechatConnection senderConnection, @Nullable VoicechatConnection receiverConnection, String source) {
        super(packet, senderConnection, receiverConnection);
        this.source = source;
    }

    @Override
    public String getSource() {
        return this.source;
    }
}

