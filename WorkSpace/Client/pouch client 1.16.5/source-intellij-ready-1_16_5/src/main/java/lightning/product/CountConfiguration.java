/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.P_1781_m;
import lightning.product.g_1198_o;
import lightning.product.FeatureConfiguration;

public class CountConfiguration
implements P_1781_m,
FeatureConfiguration {
    public static final Codec<CountConfiguration> n_1700_B = g_1198_o.n_1700_B(-10, 128, 128).fieldOf("count").xmap(CountConfiguration::new, CountConfiguration::J_1907_R).codec();
    private final g_1198_o J_1907_R;

    public CountConfiguration(int p_i241982_1_) {
        this.J_1907_R = g_1198_o.n_1700_B(p_i241982_1_);
    }

    public CountConfiguration(g_1198_o p_i241983_1_) {
        this.J_1907_R = p_i241983_1_;
    }

    public g_1198_o J_1907_R() {
        return this.J_1907_R;
    }
}


