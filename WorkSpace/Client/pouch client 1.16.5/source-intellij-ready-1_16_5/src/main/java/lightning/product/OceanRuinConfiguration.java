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
import lightning.product.d_2489_R;
import lightning.product.FeatureConfiguration;

public class OceanRuinConfiguration
implements FeatureConfiguration {
    public static final Codec<OceanRuinConfiguration> n_1700_B = RecordCodecBuilder.create(p_236563_0_ -> p_236563_0_.group((App)d_2489_R.J_1907_R.R_4764_Y.fieldOf("biome_temp").forGetter(p_236565_0_ -> p_236565_0_.J_1907_R), (App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("large_probability").forGetter(p_236564_0_ -> Float.valueOf(p_236564_0_.R_4764_Y)), (App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("cluster_probability").forGetter(p_236562_0_ -> Float.valueOf(p_236562_0_.G_564_y))).apply((Applicative)p_236563_0_, OceanRuinConfiguration::new));
    public final d_2489_R.J_1907_R J_1907_R;
    public final float R_4764_Y;
    public final float G_564_y;

    public OceanRuinConfiguration(d_2489_R.J_1907_R p_i48866_1_, float largeProbability, float clusterProbability) {
        this.J_1907_R = p_i48866_1_;
        this.R_4764_Y = largeProbability;
        this.G_564_y = clusterProbability;
    }
}


