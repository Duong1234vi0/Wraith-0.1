/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import lightning.product.e_2866_D;
import mods.voicechat.api.Position;
import mods.voicechat.api.audiochannel.ClientLocationalAudioChannel;
import mods.voicechat.plugins.impl.audiochannel.ClientAudioChannelImpl;
import mods.voicechat.voice.client.ClientUtils;
import mods.voicechat.voice.common.LocationSoundPacket;
import mods.voicechat.voice.common.SoundPacket;

public class ClientLocationalAudioChannelImpl
extends ClientAudioChannelImpl
implements ClientLocationalAudioChannel {
    private Position position;
    private float distance;

    public ClientLocationalAudioChannelImpl(UUID id, Position position) {
        super(id);
        this.position = position;
        this.distance = ClientUtils.getDefaultDistanceClient();
    }

    @Override
    protected SoundPacket<?> createSoundPacket(short[] rawAudio) {
        return new LocationSoundPacket(this.id, this.id, rawAudio, new e_2866_D(this.position.getX(), this.position.getY(), this.position.getZ()), this.distance, this.category);
    }

    @Override
    public void setLocation(Position position) {
        this.position = position;
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
}

