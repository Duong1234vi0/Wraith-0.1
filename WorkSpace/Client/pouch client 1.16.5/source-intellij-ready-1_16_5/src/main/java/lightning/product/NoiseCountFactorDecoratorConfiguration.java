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

public class NoiseCountFactorDecoratorConfiguration
implements P_1781_m {
    public static final Codec<NoiseCountFactorDecoratorConfiguration> n_1700_B = RecordCodecBuilder.create(p_236980_0_ -> p_236980_0_.group((App)Codec.INT.fieldOf("noise_to_count_ratio").forGetter(p_236984_0_ -> p_236984_0_.J_1907_R), (App)Codec.DOUBLE.fieldOf("noise_factor").forGetter(p_236983_0_ -> p_236983_0_.R_4764_Y), (App)Codec.DOUBLE.fieldOf("noise_offset").orElse((Object)0.0).forGetter(p_236982_0_ -> p_236982_0_.G_564_y)).apply((Applicative)p_236980_0_, NoiseCountFactorDecoratorConfiguration::new));
    public final int J_1907_R;
    public final double R_4764_Y;
    public final double G_564_y;

    public NoiseCountFactorDecoratorConfiguration(int p_i242029_1_, double p_i242029_2_, double p_i242029_4_) {
        this.J_1907_R = p_i242029_1_;
        this.R_4764_Y = p_i242029_2_;
        this.G_564_y = p_i242029_4_;
    }
}


