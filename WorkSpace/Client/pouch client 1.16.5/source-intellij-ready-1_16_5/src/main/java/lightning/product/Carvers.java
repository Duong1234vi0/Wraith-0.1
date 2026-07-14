/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.K_4573_Z;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.BuiltinRegistries;
import lightning.product.i_4544_r;

public class Carvers {
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> n_1700_B = Carvers.n_1700_B("cave", i_4544_r.n_1700_B.n_1700_B(new ProbabilityFeatureConfiguration(0.14285715f)));
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> J_1907_R = Carvers.n_1700_B("canyon", i_4544_r.R_4764_Y.n_1700_B(new ProbabilityFeatureConfiguration(0.02f)));
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> R_4764_Y = Carvers.n_1700_B("ocean_cave", i_4544_r.n_1700_B.n_1700_B(new ProbabilityFeatureConfiguration(0.06666667f)));
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> G_564_y = Carvers.n_1700_B("underwater_canyon", i_4544_r.G_564_y.n_1700_B(new ProbabilityFeatureConfiguration(0.02f)));
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> P_1922_E = Carvers.n_1700_B("underwater_cave", i_4544_r.P_1922_E.n_1700_B(new ProbabilityFeatureConfiguration(0.06666667f)));
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> u_1723_Y = Carvers.n_1700_B("nether_cave", i_4544_r.J_1907_R.n_1700_B(new ProbabilityFeatureConfiguration(0.2f)));

    private static <WC extends K_4573_Z> ConfiguredWorldCarver<WC> n_1700_B(String p_243773_0_, ConfiguredWorldCarver<WC> p_243773_1_) {
        return BuiltinRegistries.n_1700_B(BuiltinRegistries.G_564_y, p_243773_0_, p_243773_1_);
    }
}


