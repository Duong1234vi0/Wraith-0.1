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

public class DeltaFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<DeltaFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_242803_0_ -> p_242803_0_.group((App)K_4074_S.J_1907_R.fieldOf("contents").forGetter(p_236506_0_ -> p_236506_0_.J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("rim").forGetter(p_236505_0_ -> p_236505_0_.R_4764_Y), (App)g_1198_o.n_1700_B(0, 8, 8).fieldOf("size").forGetter(p_242805_0_ -> p_242805_0_.G_564_y), (App)g_1198_o.n_1700_B(0, 8, 8).fieldOf("rim_size").forGetter(p_242802_0_ -> p_242802_0_.P_1922_E)).apply((Applicative)p_242803_0_, DeltaFeatureConfiguration::new));
    private final K_4074_S J_1907_R;
    private final K_4074_S R_4764_Y;
    private final g_1198_o G_564_y;
    private final g_1198_o P_1922_E;

    public DeltaFeatureConfiguration(K_4074_S p_i241985_1_, K_4074_S p_i241985_2_, g_1198_o p_i241985_3_, g_1198_o p_i241985_4_) {
        this.J_1907_R = p_i241985_1_;
        this.R_4764_Y = p_i241985_2_;
        this.G_564_y = p_i241985_3_;
        this.P_1922_E = p_i241985_4_;
    }

    public K_4074_S n_1700_B() {
        return this.J_1907_R;
    }

    public K_4074_S J_1907_R() {
        return this.R_4764_Y;
    }

    public g_1198_o R_4764_Y() {
        return this.G_564_y;
    }

    public g_1198_o G_564_y() {
        return this.P_1922_E;
    }
}


