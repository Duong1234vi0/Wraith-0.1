/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.EntitySoundPacketEvent;
import mods.voicechat.api.packets.EntitySoundPacket;
import mods.voicechat.plugins.impl.events.SoundPacketEventImpl;

public class EntitySoundPacketEventImpl
extends SoundPacketEventImpl<EntitySoundPacket>
implements EntitySoundPacketEvent {
    public EntitySoundPacketEventImpl(EntitySoundPacket packet, @Nullable VoicechatConnection senderConnection, VoicechatConnection receiverConnection, String source) {
        super(packet, senderConnection, receiverConnection, source);
    }
}

