/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import mods.voicechat.api.audiochannel.StaticAudioChannel;
import mods.voicechat.api.packets.MicrophonePacket;
import mods.voicechat.plugins.impl.VoicechatConnectionImpl;
import mods.voicechat.plugins.impl.VoicechatServerApiImpl;
import mods.voicechat.plugins.impl.audiochannel.AudioChannelImpl;
import mods.voicechat.voice.common.GroupSoundPacket;
import mods.voicechat.voice.server.Server;

public class StaticAudioChannelImpl
extends AudioChannelImpl
implements StaticAudioChannel {
    protected VoicechatConnectionImpl connection;

    public StaticAudioChannelImpl(UUID channelId, Server server, VoicechatConnectionImpl connection) {
        super(channelId, server);
        this.connection = connection;
    }

    @Override
    public void send(byte[] opusData) {
        this.broadcast(new GroupSoundPacket(this.channelId, this.channelId, opusData, this.sequenceNumber.getAndIncrement(), this.category));
    }

    @Override
    public void send(MicrophonePacket packet) {
        this.send(packet.getOpusEncodedData());
    }

    @Override
    public void flush() {
        GroupSoundPacket packet = new GroupSoundPacket(this.channelId, this.channelId, new byte[0], this.sequenceNumber.getAndIncrement(), this.category);
        this.broadcast(packet);
    }

    private void broadcast(GroupSoundPacket packet) {
        VoicechatServerApiImpl.sendPacket(this.connection, packet);
    }
}

