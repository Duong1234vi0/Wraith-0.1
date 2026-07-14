/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.client;

import java.util.Arrays;
import mods.voicechat.voice.common.Utils;

public class VolumeManager {
    public static final double MIN_GAIN = -40.0;
    public static final double MAX_GAIN = 24.0;
    private static final short MAX_AMPLIFICATION = 32766;
    private final double[] maxVolumes = new double[50];
    private int index;

    public VolumeManager() {
        Arrays.fill(this.maxVolumes, -1.0);
    }

    public void adjustVolume(short[] audio, double gainDb) {
        double maxMultiplier = gainDb <= -40.0 ? 0.0 : Utils.dbToLinear(gainDb);
        this.maxVolumes[this.index] = VolumeManager.getMaximumMultiplier(audio, maxMultiplier);
        this.index = (this.index + 1) % this.maxVolumes.length;
        double min = -1.0;
        for (double mul : this.maxVolumes) {
            if (mul < 0.0) continue;
            if (min < 0.0) {
                min = mul;
                continue;
            }
            if (!(mul < min)) continue;
            min = mul;
        }
        double maxVolume = Math.min(min, maxMultiplier);
        for (int i = 0; i < audio.length; ++i) {
            audio[i] = (short)((double)audio[i] * maxVolume);
        }
    }

    @Deprecated
    public short[] adjustVolumeMono(short[] audio, float volume) {
        this.adjustVolume(audio, Utils.linearToDb(volume));
        return audio;
    }

    private static double getMaximumMultiplier(short[] audio, double multiplier) {
        short max = 0;
        for (short value : audio) {
            short abs = value <= Short.MIN_VALUE ? (short)Math.abs(value + 1) : (short)Math.abs(value);
            if (abs <= max) continue;
            max = abs;
        }
        if (max == 0) {
            return 1.0;
        }
        return Math.min(multiplier, 32766.0 / (double)max);
    }
}

