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
import lightning.product.g_1198_o;
import lightning.product.FeatureConfiguration;

public class ColumnFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<ColumnFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_242793_0_ -> p_242793_0_.group((App)g_1198_o.n_1700_B(0, 2, 1).fieldOf("reach").forGetter(p_242796_0_ -> p_242796_0_.J_1907_R), (App)g_1198_o.n_1700_B(1, 5, 5).fieldOf("height").forGetter(p_242792_0_ -> p_242792_0_.R_4764_Y)).apply((Applicative)p_242793_0_, ColumnFeatureConfiguration::new));
    private final g_1198_o J_1907_R;
    private final g_1198_o R_4764_Y;

    public ColumnFeatureConfiguration(g_1198_o p_i241981_1_, g_1198_o p_i241981_2_) {
        this.J_1907_R = p_i241981_1_;
        this.R_4764_Y = p_i241981_2_;
    }

    public g_1198_o n_1700_B() {
        return this.J_1907_R;
    }

    public g_1198_o J_1907_R() {
        return this.R_4764_Y;
    }
}


