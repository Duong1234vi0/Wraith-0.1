/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.plugins.impl.audiochannel;

import java.util.UUID;
import mods.voicechat.api.audiochannel.ClientEntityAudioChannel;
import mods.voicechat.plugins.impl.audiochannel.ClientAudioChannelImpl;
import mods.voicechat.voice.client.ClientUtils;
import mods.voicechat.voice.common.PlayerSoundPacket;
import mods.voicechat.voice.common.SoundPacket;

public class ClientEntityAudioChannelImpl
extends ClientAudioChannelImpl
implements ClientEntityAudioChannel {
    private boolean whispering = false;
    private float distance = ClientUtils.getDefaultDistanceClient();

    public ClientEntityAudioChannelImpl(UUID id) {
        super(id);
    }

    @Override
    protected SoundPacket<?> createSoundPacket(short[] rawAudio) {
        return new PlayerSoundPacket(this.id, this.id, rawAudio, this.whispering, this.distance, this.category);
    }

    @Override
    public void setWhispering(boolean whispering) {
        this.whispering = whispering;
    }

    @Override
    public boolean isWhispering() {
        return this.whispering;
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

