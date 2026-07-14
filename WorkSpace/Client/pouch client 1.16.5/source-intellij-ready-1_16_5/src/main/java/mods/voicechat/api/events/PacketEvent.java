/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.api.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.ServerEvent;
import mods.voicechat.api.packets.Packet;

public interface PacketEvent<T extends Packet>
extends ServerEvent {
    public T getPacket();

    @Nullable
    public VoicechatConnection getReceiverConnection();

    @Nullable
    public VoicechatConnection getSenderConnection();
}

