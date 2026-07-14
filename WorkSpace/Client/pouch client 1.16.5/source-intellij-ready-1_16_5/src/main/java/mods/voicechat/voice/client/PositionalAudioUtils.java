/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import javax.annotation.Nullable;
import lightning.product.P_3504_Q;
import lightning.product.MinecraftClient;
import lightning.product.e_2866_D;
import lightning.product.h_3572_K;
import mods.voicechat.VoicechatClient;
import mods.voicechat.voice.client.speaker.AudioType;
import mods.voicechat.voice.common.Utils;

public class PositionalAudioUtils {
    private static final MinecraftClient mc = MinecraftClient.A_4115_X();

    private static float[] getStereoVolume(e_2866_D cameraPos, float yRot, e_2866_D soundPos) {
        float rot;
        e_2866_D d = soundPos.G_564_y(cameraPos).G_564_y();
        P_3504_Q diff = new P_3504_Q((float)d.J_1907_R, (float)d.G_564_y);
        float diffAngle = Utils.angle(diff, new P_3504_Q(-1.0f, 0.0f));
        float angle = Utils.normalizeAngle(diffAngle - yRot % 360.0f);
        float dif = (float)(Math.abs(cameraPos.R_4764_Y - soundPos.R_4764_Y) / 32.0);
        float perc = rot = angle / 180.0f;
        if (rot < -0.5f) {
            perc = -(0.5f + (rot + 0.5f));
        } else if (rot > 0.5f) {
            perc = 0.5f - (rot - 0.5f);
        }
        float minVolume = 0.3f;
        float left = perc < 0.0f ? Math.abs((perc *= 1.0f - dif) * 1.4f) + minVolume : minVolume;
        float right = perc >= 0.0f ? perc * 1.4f + minVolume : minVolume;
        float fill = 1.0f - Math.max(left, right);
        return new float[]{left += fill, right += fill};
    }

    private static float[] getStereoVolume(e_2866_D soundPos) {
        h_3572_K mainCamera = PositionalAudioUtils.mc.s_956_w.M_588_G();
        return PositionalAudioUtils.getStereoVolume(mainCamera.J_1907_R(), mainCamera.P_1922_E(), soundPos);
    }

    public static float getDistanceVolume(float maxDistance, e_2866_D pos) {
        return PositionalAudioUtils.getDistanceVolume(maxDistance, PositionalAudioUtils.mc.s_956_w.M_588_G().J_1907_R(), pos);
    }

    public static float getDistanceVolume(float maxDistance, e_2866_D listenerPos, e_2866_D pos) {
        float distance = (float)pos.u_1723_Y(listenerPos);
        distance = Math.min(distance, maxDistance);
        return 1.0f - distance / maxDistance;
    }

    public static short[] convertToStereo(short[] audio, @Nullable e_2866_D soundPos) {
        if (soundPos == null) {
            return PositionalAudioUtils.convertToStereo(audio);
        }
        return PositionalAudioUtils.convertToStereo(audio, PositionalAudioUtils.getStereoVolume(soundPos));
    }

    public static short[] convertToStereo(short[] audio, e_2866_D cameraPos, float yRot, @Nullable e_2866_D soundPos) {
        if (soundPos == null) {
            return PositionalAudioUtils.convertToStereo(audio);
        }
        return PositionalAudioUtils.convertToStereo(audio, PositionalAudioUtils.getStereoVolume(cameraPos, yRot, soundPos));
    }

    public static short[] convertToStereo(short[] audio) {
        short[] stereo = new short[audio.length * 2];
        for (int i = 0; i < audio.length; ++i) {
            stereo[i * 2] = audio[i];
            stereo[i * 2 + 1] = audio[i];
        }
        return stereo;
    }

    private static short[] convertToStereo(short[] audio, float volumeLeft, float volumeRight) {
        short[] stereo = new short[audio.length * 2];
        for (int i = 0; i < audio.length; ++i) {
            short left = (short)((float)audio[i] * volumeLeft);
            short right = (short)((float)audio[i] * volumeRight);
            stereo[i * 2] = left;
            stereo[i * 2 + 1] = right;
        }
        return stereo;
    }

    private static short[] convertToStereo(short[] audio, float[] volumes) {
        return PositionalAudioUtils.convertToStereo(audio, volumes[0], volumes[1]);
    }

    public static short[] convertToStereo(short[] audio, float volume) {
        return PositionalAudioUtils.convertToStereo(audio, volume, volume);
    }

    public static short[] convertToStereoForRecording(float maxDistance, e_2866_D pos, short[] monoData) {
        return PositionalAudioUtils.convertToStereoForRecording(maxDistance, PositionalAudioUtils.mc.s_956_w.M_588_G().J_1907_R(), PositionalAudioUtils.mc.s_956_w.M_588_G().P_1922_E(), pos, monoData);
    }

    public static short[] convertToStereoForRecording(float maxDistance, e_2866_D pos, short[] monoData, float volume) {
        return PositionalAudioUtils.convertToStereoForRecording(maxDistance, PositionalAudioUtils.mc.s_956_w.M_588_G().J_1907_R(), PositionalAudioUtils.mc.s_956_w.M_588_G().P_1922_E(), pos, monoData, volume);
    }

    public static short[] convertToStereoForRecording(float maxDistance, e_2866_D cameraPos, float yRot, e_2866_D pos, short[] monoData) {
        return PositionalAudioUtils.convertToStereoForRecording(maxDistance, cameraPos, yRot, pos, monoData, 1.0f);
    }

    public static short[] convertToStereoForRecording(float maxDistance, e_2866_D cameraPos, float yRot, e_2866_D pos, short[] monoData, float volume) {
        float distanceVolume = PositionalAudioUtils.getDistanceVolume(maxDistance, cameraPos, pos) * volume;
        if (!((AudioType)((Object)VoicechatClient.CLIENT_CONFIG.audioType.get())).equals((Object)AudioType.OFF)) {
            float[] stereoVolume = PositionalAudioUtils.getStereoVolume(cameraPos, yRot, pos);
            return PositionalAudioUtils.convertToStereo(monoData, distanceVolume * stereoVolume[0], distanceVolume * stereoVolume[1]);
        }
        return PositionalAudioUtils.convertToStereo(monoData, distanceVolume, distanceVolume);
    }
}


