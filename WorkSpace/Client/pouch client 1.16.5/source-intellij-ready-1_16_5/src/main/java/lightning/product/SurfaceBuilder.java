/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.GravellyMountainSurfaceBuilder;
import lightning.product.NopeSurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.SoulSandValleySurfaceBuilder;
import lightning.product.SwampSurfaceBuilder;
import lightning.product.MountainSurfaceBuilder;
import lightning.product.V_3137_a;
import lightning.product.WoodedBadlandsSurfaceBuilder;
import lightning.product.Z_2711_B;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.b_4331_Y;
import lightning.product.FrozenOceanSurfaceBuilder;
import lightning.product.ChunkAccess;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.BadlandsSurfaceBuilder;
import lightning.product.k_594_Q;
import lightning.product.BasaltDeltasSurfaceBuilder;
import lightning.product.NetherSurfaceBuilder;
import lightning.product.ErodedBadlandsSurfaceBuilder;
import lightning.product.NetherForestSurfaceBuilder;
import lightning.product.DefaultSurfaceBuilder;

public abstract class SurfaceBuilder<C extends Z_927_M> {
    private static final K_4074_S n_1700_B = a_3742_W.s_956_w.multiplayerClientSuggestionProvider();
    private static final K_4074_S J_1907_R = a_3742_W.t_148_a.multiplayerClientSuggestionProvider();
    private static final K_4074_S R_4764_Y = a_3742_W.M_588_G.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_564_y = a_3742_W.t_4043_B.multiplayerClientSuggestionProvider();
    private static final K_4074_S P_1922_E = a_3742_W.J_1907_R.multiplayerClientSuggestionProvider();
    private static final K_4074_S v_4276_D = a_3742_W.u_2550_I.multiplayerClientSuggestionProvider();
    private static final K_4074_S d_2461_k = a_3742_W.A_4115_X.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_624_v = a_3742_W.Y_1740_V.multiplayerClientSuggestionProvider();
    private static final K_4074_S T_2506_i = a_3742_W.I_2209_R.multiplayerClientSuggestionProvider();
    private static final K_4074_S q_4610_l = a_3742_W.A_2714_y.multiplayerClientSuggestionProvider();
    private static final K_4074_S z_4693_k = a_3742_W.C_415_h.multiplayerClientSuggestionProvider();
    private static final K_4074_S g_221_o = a_3742_W.i_3196_G.multiplayerClientSuggestionProvider();
    private static final K_4074_S e_2887_G = a_3742_W.e_1231_S.multiplayerClientSuggestionProvider();
    private static final K_4074_S B_1668_F = a_3742_W.ServerFunctionManager.multiplayerClientSuggestionProvider();
    private static final K_4074_S g_164_R = a_3742_W.ServerAdvancementManager.multiplayerClientSuggestionProvider();
    private static final K_4074_S X_933_l = a_3742_W.LockSlot.multiplayerClientSuggestionProvider();
    private static final K_4074_S Z_976_R = a_3742_W.J_1008_m.multiplayerClientSuggestionProvider();
    private static final K_4074_S H_1990_U = a_3742_W.m_1964_F.multiplayerClientSuggestionProvider();
    private static final K_4074_S N_2525_X = a_3742_W.s_4990_V.multiplayerClientSuggestionProvider();
    private static final K_4074_S c_4037_x = a_3742_W.LevitationControl.multiplayerClientSuggestionProvider();
    public static final SurfaceBuilderBaseConfiguration u_1723_Y = new SurfaceBuilderBaseConfiguration(R_4764_Y, n_1700_B, G_564_y);
    public static final SurfaceBuilderBaseConfiguration v_4262_N = new SurfaceBuilderBaseConfiguration(G_564_y, G_564_y, G_564_y);
    public static final SurfaceBuilderBaseConfiguration w_1484_f = new SurfaceBuilderBaseConfiguration(J_1907_R, n_1700_B, G_564_y);
    public static final SurfaceBuilderBaseConfiguration t_148_a = new SurfaceBuilderBaseConfiguration(P_1922_E, P_1922_E, G_564_y);
    public static final SurfaceBuilderBaseConfiguration s_956_w = new SurfaceBuilderBaseConfiguration(v_4276_D, n_1700_B, G_564_y);
    public static final SurfaceBuilderBaseConfiguration u_2550_I = new SurfaceBuilderBaseConfiguration(d_2461_k, d_2461_k, G_564_y);
    public static final SurfaceBuilderBaseConfiguration M_588_G = new SurfaceBuilderBaseConfiguration(J_1907_R, n_1700_B, d_2461_k);
    public static final SurfaceBuilderBaseConfiguration P_4830_p = new SurfaceBuilderBaseConfiguration(d_2461_k, d_2461_k, d_2461_k);
    public static final SurfaceBuilderBaseConfiguration h_1847_R = new SurfaceBuilderBaseConfiguration(G_624_v, T_2506_i, G_564_y);
    public static final SurfaceBuilderBaseConfiguration Q_4569_t = new SurfaceBuilderBaseConfiguration(q_4610_l, n_1700_B, G_564_y);
    public static final SurfaceBuilderBaseConfiguration M_182_A = new SurfaceBuilderBaseConfiguration(g_221_o, g_221_o, g_221_o);
    public static final SurfaceBuilderBaseConfiguration t_1786_h = new SurfaceBuilderBaseConfiguration(z_4693_k, z_4693_k, z_4693_k);
    public static final SurfaceBuilderBaseConfiguration multiplayerClientSuggestionProvider = new SurfaceBuilderBaseConfiguration(e_2887_G, e_2887_G, e_2887_G);
    public static final SurfaceBuilderBaseConfiguration w_1457_N = new SurfaceBuilderBaseConfiguration(B_1668_F, g_221_o, X_933_l);
    public static final SurfaceBuilderBaseConfiguration Y_601_j = new SurfaceBuilderBaseConfiguration(g_164_R, g_221_o, Z_976_R);
    public static final SurfaceBuilderBaseConfiguration Y_259_p = new SurfaceBuilderBaseConfiguration(H_1990_U, N_2525_X, c_4037_x);
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> Q_2552_b = SurfaceBuilder.n_1700_B("default", new DefaultSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> C_2741_M = SurfaceBuilder.n_1700_B("mountain", new MountainSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> k_2293_S = SurfaceBuilder.n_1700_B("shattered_savanna", new b_4331_Y(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> q_2307_F = SurfaceBuilder.n_1700_B("gravelly_mountain", new GravellyMountainSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> Z_875_P = SurfaceBuilder.n_1700_B("giant_tree_taiga", new Z_2711_B(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> c_3005_b = SurfaceBuilder.n_1700_B("swamp", new SwampSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> H_2857_Y = SurfaceBuilder.n_1700_B("badlands", new BadlandsSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> A_4115_X = SurfaceBuilder.n_1700_B("wooded_badlands", new WoodedBadlandsSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> Y_1740_V = SurfaceBuilder.n_1700_B("eroded_badlands", new ErodedBadlandsSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> t_4043_B = SurfaceBuilder.n_1700_B("frozen_ocean", new FrozenOceanSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> x_607_J = SurfaceBuilder.n_1700_B("nether", new NetherSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> e_4240_b = SurfaceBuilder.n_1700_B("nether_forest", new NetherForestSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> n_3318_d = SurfaceBuilder.n_1700_B("soul_sand_valley", new SoulSandValleySurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> d_2427_y = SurfaceBuilder.n_1700_B("basalt_deltas", new BasaltDeltasSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> z_1737_N = SurfaceBuilder.n_1700_B("nope", new NopeSurfaceBuilder(SurfaceBuilderBaseConfiguration.n_1700_B));
    private final Codec<ConfiguredSurfaceBuilder<C>> g_2268_R;

    private static <C extends Z_927_M, F extends SurfaceBuilder<C>> F n_1700_B(String key, F builderIn) {
        return (F)V_3137_a.n_1700_B(V_3137_a.U_1241_n, key, builderIn);
    }

    public SurfaceBuilder(Codec<C> p_i232136_1_) {
        this.g_2268_R = p_i232136_1_.fieldOf("config").xmap(this::n_1700_B, ConfiguredSurfaceBuilder::n_1700_B).codec();
    }

    public Codec<ConfiguredSurfaceBuilder<C>> G_564_y() {
        return this.g_2268_R;
    }

    public ConfiguredSurfaceBuilder<C> n_1700_B(C p_242929_1_) {
        return new ConfiguredSurfaceBuilder<C>(this, p_242929_1_);
    }

    public abstract void n_1700_B(Random var1, ChunkAccess var2, k_594_Q var3, int var4, int var5, int var6, double var7, K_4074_S var9, K_4074_S var10, int var11, long var12, C var14);

    public void n_1700_B(long seed) {
    }
}



