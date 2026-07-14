/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.V_2473_P;
import lightning.product.SoundEvent;
import lightning.product.SoundInstance;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;

public class SimpleSoundInstance
extends V_2473_P {
    public SimpleSoundInstance(SoundEvent soundIn, D_38_f categoryIn, float volumeIn, float pitchIn, c_1514_x pos) {
        this(soundIn, categoryIn, volumeIn, pitchIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5);
    }

    public static SimpleSoundInstance n_1700_B(SoundEvent soundIn, float pitchIn) {
        return SimpleSoundInstance.n_1700_B(soundIn, pitchIn, 0.25f);
    }

    public static SimpleSoundInstance n_1700_B(SoundEvent soundIn, float pitchIn, float volumeIn) {
        return new SimpleSoundInstance(soundIn.n_1700_B(), D_38_f.n_1700_B, volumeIn, pitchIn, false, 0, SoundInstance.n_1700_B.n_1700_B, 0.0, 0.0, 0.0, true);
    }

    public static SimpleSoundInstance n_1700_B(SoundEvent soundIn) {
        return new SimpleSoundInstance(soundIn.n_1700_B(), D_38_f.J_1907_R, 1.0f, 1.0f, false, 0, SoundInstance.n_1700_B.n_1700_B, 0.0, 0.0, 0.0, true);
    }

    public static SimpleSoundInstance n_1700_B(SoundEvent soundIn, double xIn, double zIn, double zTrueIn) {
        return new SimpleSoundInstance(soundIn, D_38_f.R_4764_Y, 4.0f, 1.0f, false, 0, SoundInstance.n_1700_B.J_1907_R, xIn, zIn, zTrueIn);
    }

    public static SimpleSoundInstance J_1907_R(SoundEvent sound, float volume, float pitch) {
        return new SimpleSoundInstance(sound.n_1700_B(), D_38_f.t_148_a, pitch, volume, false, 0, SoundInstance.n_1700_B.n_1700_B, 0.0, 0.0, 0.0, true);
    }

    public static SimpleSoundInstance J_1907_R(SoundEvent sound) {
        return SimpleSoundInstance.J_1907_R(sound, 1.0f, 1.0f);
    }

    public static SimpleSoundInstance J_1907_R(SoundEvent sound, double x, double y, double z) {
        return new SimpleSoundInstance(sound, D_38_f.t_148_a, 1.0f, 1.0f, false, 0, SoundInstance.n_1700_B.J_1907_R, x, y, z);
    }

    public SimpleSoundInstance(SoundEvent sound, D_38_f category, float volume, float pitch, double x, double y, double z) {
        this(sound, category, volume, pitch, false, 0, SoundInstance.n_1700_B.J_1907_R, x, y, z);
    }

    private SimpleSoundInstance(SoundEvent sound, D_38_f category, float volume, float pitch, boolean repeat, int repeatDelay, SoundInstance.n_1700_B attenuationType, double x, double y, double z) {
        this(sound.n_1700_B(), category, volume, pitch, repeat, repeatDelay, attenuationType, x, y, z, false);
    }

    public SimpleSoundInstance(g_2336_b sound, D_38_f category, float volume, float pitch, boolean repeat, int repeatDelay, SoundInstance.n_1700_B attenuationType, double x, double y, double z, boolean global) {
        super(sound, category);
        this.P_1922_E = volume;
        this.u_1723_Y = pitch;
        this.v_4262_N = x;
        this.w_1484_f = y;
        this.t_148_a = z;
        this.s_956_w = repeat;
        this.u_2550_I = repeatDelay;
        this.M_588_G = attenuationType;
        this.h_1847_R = global;
    }
}


