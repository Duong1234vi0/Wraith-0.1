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
import lightning.product.SoundEvent;

public class AmbientAdditionsSettings {
    public static final Codec<AmbientAdditionsSettings> n_1700_B = RecordCodecBuilder.create(soundAdditionsCodecInstance -> soundAdditionsCodecInstance.group((App)SoundEvent.n_1700_B.fieldOf("sound").forGetter(soundAdditions -> soundAdditions.J_1907_R), (App)Codec.DOUBLE.fieldOf("tick_chance").forGetter(soundAdditions -> soundAdditions.R_4764_Y)).apply((Applicative)soundAdditionsCodecInstance, AmbientAdditionsSettings::new));
    private SoundEvent J_1907_R;
    private double R_4764_Y;

    public AmbientAdditionsSettings(SoundEvent sound, double tickChance) {
        this.J_1907_R = sound;
        this.R_4764_Y = tickChance;
    }

    public SoundEvent n_1700_B() {
        return this.J_1907_R;
    }

    public double J_1907_R() {
        return this.R_4764_Y;
    }
}


