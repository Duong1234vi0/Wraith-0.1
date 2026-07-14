/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.events;

import javax.annotation.Nullable;
import mods.voicechat.api.VoicechatConnection;
import mods.voicechat.api.events.PacketEvent;
import mods.voicechat.api.packets.Packet;
import mods.voicechat.plugins.impl.events.ServerEventImpl;

public class PacketEventImpl<T extends Packet>
extends ServerEventImpl
implements PacketEvent<T> {
    private final T packet;
    @Nullable
    private final VoicechatConnection receiverConnection;
    @Nullable
    private final VoicechatConnection senderConnection;

    public PacketEventImpl(T packet, @Nullable VoicechatConnection senderConnection, @Nullable VoicechatConnection receiverConnection) {
        this.packet = packet;
        this.senderConnection = senderConnection;
        this.receiverConnection = receiverConnection;
    }

    @Override
    public T getPacket() {
        return this.packet;
    }

    @Override
    @Nullable
    public VoicechatConnection getReceiverConnection() {
        return this.receiverConnection;
    }

    @Override
    @Nullable
    public VoicechatConnection getSenderConnection() {
        return this.senderConnection;
    }
}

