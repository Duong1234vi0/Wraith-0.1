/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SurfaceBuilder;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.BuiltinRegistries;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredSurfaceBuilder;

public class SurfaceBuilders {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> n_1700_B = SurfaceBuilders.n_1700_B("badlands", SurfaceBuilder.H_2857_Y.n_1700_B(SurfaceBuilder.h_1847_R));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> J_1907_R = SurfaceBuilders.n_1700_B("basalt_deltas", SurfaceBuilder.d_2427_y.n_1700_B(SurfaceBuilder.Y_259_p));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> R_4764_Y = SurfaceBuilders.n_1700_B("crimson_forest", SurfaceBuilder.e_4240_b.n_1700_B(SurfaceBuilder.w_1457_N));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> G_564_y = SurfaceBuilders.n_1700_B("desert", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.u_2550_I));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> P_1922_E = SurfaceBuilders.n_1700_B("end", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.multiplayerClientSuggestionProvider));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> u_1723_Y = SurfaceBuilders.n_1700_B("eroded_badlands", SurfaceBuilder.Y_1740_V.n_1700_B(SurfaceBuilder.h_1847_R));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> v_4262_N = SurfaceBuilders.n_1700_B("frozen_ocean", SurfaceBuilder.t_4043_B.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> w_1484_f = SurfaceBuilders.n_1700_B("full_sand", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.P_4830_p));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> t_148_a = SurfaceBuilders.n_1700_B("giant_tree_taiga", SurfaceBuilder.Z_875_P.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> s_956_w = SurfaceBuilders.n_1700_B("grass", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> u_2550_I = SurfaceBuilders.n_1700_B("gravelly_mountain", SurfaceBuilder.q_2307_F.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> M_588_G = SurfaceBuilders.n_1700_B("ice_spikes", SurfaceBuilder.Q_2552_b.n_1700_B(new SurfaceBuilderBaseConfiguration(a_3742_W.l_697_B.multiplayerClientSuggestionProvider(), a_3742_W.s_956_w.multiplayerClientSuggestionProvider(), a_3742_W.t_4043_B.multiplayerClientSuggestionProvider())));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> P_4830_p = SurfaceBuilders.n_1700_B("mountain", SurfaceBuilder.C_2741_M.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> h_1847_R = SurfaceBuilders.n_1700_B("mycelium", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.Q_4569_t));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> Q_4569_t = SurfaceBuilders.n_1700_B("nether", SurfaceBuilder.x_607_J.n_1700_B(SurfaceBuilder.M_182_A));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> M_182_A = SurfaceBuilders.n_1700_B("nope", SurfaceBuilder.z_1737_N.n_1700_B(SurfaceBuilder.t_148_a));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> t_1786_h = SurfaceBuilders.n_1700_B("ocean_sand", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.M_588_G));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> multiplayerClientSuggestionProvider = SurfaceBuilders.n_1700_B("shattered_savanna", SurfaceBuilder.k_2293_S.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> w_1457_N = SurfaceBuilders.n_1700_B("soul_sand_valley", SurfaceBuilder.n_3318_d.n_1700_B(SurfaceBuilder.t_1786_h));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> Y_601_j = SurfaceBuilders.n_1700_B("stone", SurfaceBuilder.Q_2552_b.n_1700_B(SurfaceBuilder.t_148_a));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> Y_259_p = SurfaceBuilders.n_1700_B("swamp", SurfaceBuilder.c_3005_b.n_1700_B(SurfaceBuilder.w_1484_f));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> Q_2552_b = SurfaceBuilders.n_1700_B("warped_forest", SurfaceBuilder.e_4240_b.n_1700_B(SurfaceBuilder.Y_601_j));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> C_2741_M = SurfaceBuilders.n_1700_B("wooded_badlands", SurfaceBuilder.A_4115_X.n_1700_B(SurfaceBuilder.h_1847_R));

    private static <SC extends Z_927_M> ConfiguredSurfaceBuilder<SC> n_1700_B(String p_244192_0_, ConfiguredSurfaceBuilder<SC> p_244192_1_) {
        return BuiltinRegistries.n_1700_B(BuiltinRegistries.R_4764_Y, p_244192_0_, p_244192_1_);
    }
}


