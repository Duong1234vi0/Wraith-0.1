/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.LocationalSoundPacketEvent;
import mods.voicechat.api.packets.LocationalSoundPacket;
import mods.voicechat.plugins.impl.events.SoundPacketEventImpl;

public class LocationalSoundPacketEventImpl
extends SoundPacketEventImpl<LocationalSoundPacket>
implements LocationalSoundPacketEvent {
    public LocationalSoundPacketEventImpl(LocationalSoundPacket packet, @Nullable VoicechatConnection senderConnection, VoicechatConnection receiverConnection, String source) {
        super(packet, senderConnection, receiverConnection, source);
    }
}

