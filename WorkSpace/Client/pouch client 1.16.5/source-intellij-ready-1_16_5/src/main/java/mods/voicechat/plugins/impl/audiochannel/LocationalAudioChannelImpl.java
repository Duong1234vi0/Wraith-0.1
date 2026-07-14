/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import lightning.product.B_4088_l;
import mods.voicechat.api.Position;
import mods.voicechat.api.ServerLevel;
import mods.voicechat.api.audiochannel.LocationalAudioChannel;
import mods.voicechat.api.packets.MicrophonePacket;
import mods.voicechat.plugins.impl.PositionImpl;
import mods.voicechat.plugins.impl.ServerLevelImpl;
import mods.voicechat.plugins.impl.ServerPlayerImpl;
import mods.voicechat.plugins.impl.audiochannel.AudioChannelImpl;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.Utils;
import mods.voicechat.voice.server.Server;
import mods.voicechat.voice.server.ServerWorldUtils;

public class LocationalAudioChannelImpl
extends AudioChannelImpl
implements LocationalAudioChannel {
    protected ServerLevel level;
    protected PositionImpl position;
    protected float distance;

    public LocationalAudioChannelImpl(UUID channelId, Server server, ServerLevel level, PositionImpl position) {
        super(channelId, server);
        this.level = level;
        this.position = position;
        this.distance = Utils.getDefaultDistanceServer();
    }

    @Override
    public void updateLocation(Position position) {
        if (!(position instanceof PositionImpl)) {
            throw new IllegalArgumentException("position is not an instance of PositionImpl");
        }
        this.position = (PositionImpl)position;
    }

    @Override
    public Position getLocation() {
        return this.position;
    }

    @Override
    public float getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public void send(byte[] opusData) {
        this.broadcast(new LocationSoundPacket(this.channelId, this.channelId, this.position.getPosition(), opusData, this.sequenceNumber.getAndIncrement(), this.distance, this.category));
    }

    @Override
    public void send(MicrophonePacket packet) {
        this.send(packet.getOpusEncodedData());
    }

    @Override
    public void flush() {
        this.broadcast(new LocationSoundPacket(this.channelId, this.channelId, this.position.getPosition(), new byte[0], this.sequenceNumber.getAndIncrement(), this.distance, this.category));
    }

    private void broadcast(LocationSoundPacket packet) {
        if (!(this.level instanceof ServerLevelImpl)) {
            throw new IllegalArgumentException("level is not an instance of ServerLevelImpl");
        }
        ServerLevelImpl serverLevel = (ServerLevelImpl)this.level;
        this.server.broadcast(ServerWorldUtils.getPlayersInRange(serverLevel.getRawServerLevel(), this.position.getPosition(), this.server.getBroadcastRange(this.distance), this.filter == null ? player -> true : player -> this.filter.test(new ServerPlayerImpl((B_4088_l)player))), packet, null, null, null, "plugin");
    }
}

