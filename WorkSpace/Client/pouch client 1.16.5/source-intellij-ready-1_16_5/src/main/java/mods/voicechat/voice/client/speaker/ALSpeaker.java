/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client.speaker;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.speaker.ALSpeakerBase;

public class ALSpeaker
extends ALSpeakerBase {
    public ALSpeaker(SoundManager soundManager, int sampleRate, int bufferSize, @Nullable UUID audioChannelId) {
        super(soundManager, sampleRate, bufferSize, audioChannelId);
    }

    @Override
    protected int getFormat() {
        return 4353;
    }
}

