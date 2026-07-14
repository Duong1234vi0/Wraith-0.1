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
import lightning.product.j_4336_h;
import lightning.product.FeatureConfiguration;

public class MineshaftConfiguration
implements FeatureConfiguration {
    public static final Codec<MineshaftConfiguration> n_1700_B = RecordCodecBuilder.create(p_236543_0_ -> p_236543_0_.group((App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("probability").forGetter(p_236544_0_ -> Float.valueOf(p_236544_0_.J_1907_R)), (App)j_4336_h.J_1907_R.R_4764_Y.fieldOf("type").forGetter(p_236542_0_ -> p_236542_0_.R_4764_Y)).apply((Applicative)p_236543_0_, MineshaftConfiguration::new));
    public final float J_1907_R;
    public final j_4336_h.J_1907_R R_4764_Y;

    public MineshaftConfiguration(float p_i241988_1_, j_4336_h.J_1907_R p_i241988_2_) {
        this.J_1907_R = p_i241988_1_;
        this.R_4764_Y = p_i241988_2_;
    }
}


