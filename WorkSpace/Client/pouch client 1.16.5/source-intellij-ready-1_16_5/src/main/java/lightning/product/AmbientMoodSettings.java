/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;

public class AmbientMoodSettings {
    public static final Codec<AmbientMoodSettings> n_1700_B = RecordCodecBuilder.create(moodSoundCodecInstance -> moodSoundCodecInstance.group((App)SoundEvent.n_1700_B.fieldOf("sound").forGetter(moodSound -> moodSound.R_4764_Y), (App)Codec.INT.fieldOf("tick_delay").forGetter(moodSound -> moodSound.G_564_y), (App)Codec.INT.fieldOf("block_search_extent").forGetter(moodSound -> moodSound.P_1922_E), (App)Codec.DOUBLE.fieldOf("offset").forGetter(moodSound -> moodSound.u_1723_Y)).apply((Applicative)moodSoundCodecInstance, AmbientMoodSettings::new));
    public static final AmbientMoodSettings J_1907_R = new AmbientMoodSettings(SoundEvents.n_1700_B, 6000, 8, 2.0);
    private SoundEvent R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private double u_1723_Y;

    public AmbientMoodSettings(SoundEvent sound, int tickDelay, int searchRadius, double offset) {
        this.R_4764_Y = sound;
        this.G_564_y = tickDelay;
        this.P_1922_E = searchRadius;
        this.u_1723_Y = offset;
    }

    public SoundEvent n_1700_B() {
        return this.R_4764_Y;
    }

    public int J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return this.P_1922_E;
    }

    public double G_564_y() {
        return this.u_1723_Y;
    }
}


