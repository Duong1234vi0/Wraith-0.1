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
import lightning.product.K_4573_Z;
import lightning.product.FeatureConfiguration;

public class ProbabilityFeatureConfiguration
implements K_4573_Z,
FeatureConfiguration {
    public static final Codec<ProbabilityFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_236578_0_ -> p_236578_0_.group((App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("probability").forGetter(p_236577_0_ -> Float.valueOf(p_236577_0_.J_1907_R))).apply((Applicative)p_236578_0_, ProbabilityFeatureConfiguration::new));
    public final float J_1907_R;

    public ProbabilityFeatureConfiguration(float probability) {
        this.J_1907_R = probability;
    }
}


