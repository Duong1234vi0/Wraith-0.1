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
import lightning.product.P_1781_m;

public class NoiseDependantDecoratorConfiguration
implements P_1781_m {
    public static final Codec<NoiseDependantDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_236552_0_ -> p_236552_0_.group((App)Codec.DOUBLE.fieldOf("noise_level").forGetter(p_236554_0_ -> p_236554_0_.J_1907_R), (App)Codec.INT.fieldOf("below_noise").forGetter(p_236553_0_ -> p_236553_0_.R_4764_Y), (App)Codec.INT.fieldOf("above_noise").forGetter(p_236551_0_ -> p_236551_0_.G_564_y)).apply((Applicative)p_236552_0_, NoiseDependantDecoratorConfiguration::new));
    public final double J_1907_R;
    public final int R_4764_Y;
    public final int G_564_y;

    public NoiseDependantDecoratorConfiguration(double noiseLevel, int belowNoise, int aboveNoise) {
        this.J_1907_R = noiseLevel;
        this.R_4764_Y = belowNoise;
        this.G_564_y = aboveNoise;
    }
}


