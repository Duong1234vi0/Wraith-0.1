/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client.speaker;

import java.util.UUID;
import javax.annotation.Nullable;
import mods.voicechat.VoicechatClient;
import mods.voicechat.voice.client.SoundManager;
import mods.voicechat.voice.client.speaker.ALSpeaker;
import mods.voicechat.voice.client.speaker.ALSpeakerBase;
import mods.voicechat.voice.client.speaker.AudioType;
import mods.voicechat.voice.client.speaker.FakeALSpeaker;
import mods.voicechat.voice.client.speaker.MonoALSpeaker;
import mods.voicechat.voice.client.speaker.Speaker;
import mods.voicechat.voice.client.speaker.SpeakerException;

public class SpeakerManager {
    public static Speaker createSpeaker(SoundManager soundManager, @Nullable UUID audioChannel) throws SpeakerException {
        ALSpeakerBase speaker = switch ((AudioType)((Object)VoicechatClient.CLIENT_CONFIG.audioType.get())) {
            default -> new ALSpeaker(soundManager, 48000, 960, audioChannel);
            case AudioType.REDUCED -> new FakeALSpeaker(soundManager, 48000, 960, audioChannel);
            case AudioType.OFF -> new MonoALSpeaker(soundManager, 48000, 960, audioChannel);
        };
        speaker.open();
        return speaker;
    }
}

