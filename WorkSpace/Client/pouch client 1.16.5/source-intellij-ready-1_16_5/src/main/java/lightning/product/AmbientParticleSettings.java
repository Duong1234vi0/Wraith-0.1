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
import java.util.Random;
import lightning.product.ParticleOptions;
import lightning.product.ParticleTypes;

public class AmbientParticleSettings {
    public static final Codec<AmbientParticleSettings> n_1700_B = RecordCodecBuilder.create(particleAmbienceCodecInstance -> particleAmbienceCodecInstance.group((App)ParticleTypes.t_4219_U.fieldOf("options").forGetter(particleAmbience -> particleAmbience.J_1907_R), (App)Codec.FLOAT.fieldOf("probability").forGetter(particleAmbience -> Float.valueOf(particleAmbience.R_4764_Y))).apply((Applicative)particleAmbienceCodecInstance, AmbientParticleSettings::new));
    private final ParticleOptions J_1907_R;
    private final float R_4764_Y;

    public AmbientParticleSettings(ParticleOptions particleOptions, float probability) {
        this.J_1907_R = particleOptions;
        this.R_4764_Y = probability;
    }

    public ParticleOptions n_1700_B() {
        return this.J_1907_R;
    }

    public boolean n_1700_B(Random rand) {
        return rand.nextFloat() <= this.R_4764_Y;
    }
}


