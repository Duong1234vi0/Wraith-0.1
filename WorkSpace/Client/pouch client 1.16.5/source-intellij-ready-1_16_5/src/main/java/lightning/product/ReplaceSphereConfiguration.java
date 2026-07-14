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
import lightning.product.K_4074_S;
import lightning.product.g_1198_o;
import lightning.product.FeatureConfiguration;

public class ReplaceSphereConfiguration
implements FeatureConfiguration {
    public static final Codec<ReplaceSphereConfiguration> n_1700_B = RecordCodecBuilder.create(p_242822_0_ -> p_242822_0_.group((App)K_4074_S.J_1907_R.fieldOf("target").forGetter(p_242825_0_ -> p_242825_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("state").forGetter(p_242824_0_ -> p_242824_0_.R_4764_Y), (App)g_1198_o.n_1700_B.fieldOf("radius").forGetter(p_242821_0_ -> p_242821_0_.G_564_y)).apply((Applicative)p_242822_0_, ReplaceSphereConfiguration::new));
    public final K_4074_S J_1907_R;
    public final K_4074_S R_4764_Y;
    private final g_1198_o G_564_y;

    public ReplaceSphereConfiguration(K_4074_S p_i241993_1_, K_4074_S p_i241993_2_, g_1198_o p_i241993_3_) {
        this.J_1907_R = p_i241993_1_;
        this.R_4764_Y = p_i241993_2_;
        this.G_564_y = p_i241993_3_;
    }

    public g_1198_o n_1700_B() {
        return this.G_564_y;
    }
}


