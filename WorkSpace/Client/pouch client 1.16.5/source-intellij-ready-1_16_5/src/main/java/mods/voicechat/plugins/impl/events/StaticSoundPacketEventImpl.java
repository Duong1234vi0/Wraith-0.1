/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.StaticSoundPacketEvent;
import mods.voicechat.api.packets.StaticSoundPacket;
import mods.voicechat.plugins.impl.events.SoundPacketEventImpl;

public class StaticSoundPacketEventImpl
extends SoundPacketEventImpl<StaticSoundPacket>
implements StaticSoundPacketEvent {
    public StaticSoundPacketEventImpl(StaticSoundPacket packet, @Nullable VoicechatConnection senderConnection, VoicechatConnection receiverConnection, String source) {
        super(packet, senderConnection, receiverConnection, source);
    }
}

