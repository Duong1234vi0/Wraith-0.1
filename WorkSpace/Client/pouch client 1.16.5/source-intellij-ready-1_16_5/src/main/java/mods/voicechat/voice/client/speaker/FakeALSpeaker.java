/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.openal.AL11
 */
package mods.voicechat.voice.client.speaker;

import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.e_2866_D;
import mods.voicechat.voice.client.PositionalAudioUtils;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.speaker.ALSpeakerBase;
import org.lwjgl.openal.AL11;

public class FakeALSpeaker
extends ALSpeakerBase {
    public FakeALSpeaker(SoundManager soundManager, int sampleRate, int bufferSize, @Nullable UUID audioChannelId) {
        super(soundManager, sampleRate, bufferSize, audioChannelId);
        this.bufferSize *= 2;
    }

    @Override
    protected void openSync() {
        super.openSync();
        AL11.alDistanceModel((int)0);
        SoundManager.checkAlError();
    }

    @Override
    protected short[] convert(short[] data, @Nullable e_2866_D position) {
        return PositionalAudioUtils.convertToStereo(data, position);
    }

    @Override
    protected int getFormat() {
        return 4355;
    }

    @Override
    protected void setPositionSync(@Nullable e_2866_D soundPos, float maxDistance) {
    }

    @Override
    protected float getVolume(float volume, @Nullable e_2866_D position, float maxDistance) {
        if (position == null) {
            return super.getVolume(volume, position, maxDistance);
        }
        return super.getVolume(volume, position, maxDistance) * PositionalAudioUtils.getDistanceVolume(maxDistance, position);
    }

    @Override
    protected int getBufferSize() {
        return Math.min(super.getBufferSize(), 3);
    }

    @Override
    protected void linearAttenuation(float maxDistance) {
    }
}

