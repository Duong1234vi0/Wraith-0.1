/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import lightning.product.biomeBiomes;
import lightning.product.BuiltinRegistries;
import lightning.product.f_2392_k;
import lightning.product.j_154_J;
import lightning.product.k_594_Q;
import lightning.product.SurfaceBuilders;

public abstract class Biomes {
    private static final Int2ObjectMap<f_2392_k<k_594_Q>> R_4764_Y = new Int2ObjectArrayMap();
    public static final k_594_Q n_1700_B = Biomes.n_1700_B(1, biomeBiomes.J_1907_R, j_154_J.n_1700_B(false));
    public static final k_594_Q J_1907_R = Biomes.n_1700_B(127, biomeBiomes.g_2268_R, j_154_J.multiplayerClientSuggestionProvider());

    private static k_594_Q n_1700_B(int id, f_2392_k<k_594_Q> key, k_594_Q biome) {
        R_4764_Y.put(id, key);
        return BuiltinRegistries.n_1700_B(BuiltinRegistries.t_148_a, id, key, biome);
    }

    public static f_2392_k<k_594_Q> n_1700_B(int id) {
        return (f_2392_k)R_4764_Y.get(id);
    }

    static {
        Biomes.n_1700_B(0, biomeBiomes.n_1700_B, j_154_J.R_4764_Y(false));
        Biomes.n_1700_B(2, biomeBiomes.R_4764_Y, j_154_J.n_1700_B(0.125f, 0.05f, true, true, true));
        Biomes.n_1700_B(3, biomeBiomes.G_564_y, j_154_J.n_1700_B(1.0f, 0.5f, SurfaceBuilders.P_4830_p, false));
        Biomes.n_1700_B(4, biomeBiomes.P_1922_E, j_154_J.R_4764_Y(0.1f, 0.2f));
        Biomes.n_1700_B(5, biomeBiomes.u_1723_Y, j_154_J.n_1700_B(0.2f, 0.2f, false, false, true, false));
        Biomes.n_1700_B(6, biomeBiomes.v_4262_N, j_154_J.G_564_y(-0.2f, 0.1f, false));
        Biomes.n_1700_B(7, biomeBiomes.w_1484_f, j_154_J.n_1700_B(-0.5f, 0.0f, 0.5f, 4159204, false));
        Biomes.n_1700_B(8, biomeBiomes.t_148_a, j_154_J.w_1457_N());
        Biomes.n_1700_B(9, biomeBiomes.s_956_w, j_154_J.t_148_a());
        Biomes.n_1700_B(10, biomeBiomes.u_2550_I, j_154_J.P_1922_E(false));
        Biomes.n_1700_B(11, biomeBiomes.M_588_G, j_154_J.n_1700_B(-0.5f, 0.0f, 0.0f, 3750089, true));
        Biomes.n_1700_B(12, biomeBiomes.P_4830_p, j_154_J.n_1700_B(0.125f, 0.05f, false, false));
        Biomes.n_1700_B(13, biomeBiomes.h_1847_R, j_154_J.n_1700_B(0.45f, 0.3f, false, true));
        Biomes.n_1700_B(14, biomeBiomes.Q_4569_t, j_154_J.n_1700_B(0.2f, 0.3f));
        Biomes.n_1700_B(15, biomeBiomes.M_182_A, j_154_J.n_1700_B(0.0f, 0.025f));
        Biomes.n_1700_B(16, biomeBiomes.t_1786_h, j_154_J.n_1700_B(0.0f, 0.025f, 0.8f, 0.4f, 4159204, false, false));
        Biomes.n_1700_B(17, biomeBiomes.multiplayerClientSuggestionProvider, j_154_J.n_1700_B(0.45f, 0.3f, false, true, false));
        Biomes.n_1700_B(18, biomeBiomes.w_1457_N, j_154_J.R_4764_Y(0.45f, 0.3f));
        Biomes.n_1700_B(19, biomeBiomes.Y_601_j, j_154_J.n_1700_B(0.45f, 0.3f, false, false, false, false));
        Biomes.n_1700_B(20, biomeBiomes.Y_259_p, j_154_J.n_1700_B(0.8f, 0.3f, SurfaceBuilders.s_956_w, true));
        Biomes.n_1700_B(21, biomeBiomes.Q_2552_b, j_154_J.n_1700_B());
        Biomes.n_1700_B(22, biomeBiomes.C_2741_M, j_154_J.P_1922_E());
        Biomes.n_1700_B(23, biomeBiomes.k_2293_S, j_154_J.J_1907_R());
        Biomes.n_1700_B(24, biomeBiomes.q_2307_F, j_154_J.R_4764_Y(true));
        Biomes.n_1700_B(25, biomeBiomes.Z_875_P, j_154_J.n_1700_B(0.1f, 0.8f, 0.2f, 0.3f, 4159204, false, true));
        Biomes.n_1700_B(26, biomeBiomes.c_3005_b, j_154_J.n_1700_B(0.0f, 0.025f, 0.05f, 0.3f, 4020182, true, false));
        Biomes.n_1700_B(27, biomeBiomes.H_2857_Y, j_154_J.n_1700_B(0.1f, 0.2f, false));
        Biomes.n_1700_B(28, biomeBiomes.A_4115_X, j_154_J.n_1700_B(0.45f, 0.3f, false));
        Biomes.n_1700_B(29, biomeBiomes.Y_1740_V, j_154_J.R_4764_Y(0.1f, 0.2f, false));
        Biomes.n_1700_B(30, biomeBiomes.t_4043_B, j_154_J.n_1700_B(0.2f, 0.2f, true, false, false, true));
        Biomes.n_1700_B(31, biomeBiomes.x_607_J, j_154_J.n_1700_B(0.45f, 0.3f, true, false, false, false));
        Biomes.n_1700_B(32, biomeBiomes.e_4240_b, j_154_J.n_1700_B(0.2f, 0.2f, 0.3f, false));
        Biomes.n_1700_B(33, biomeBiomes.n_3318_d, j_154_J.n_1700_B(0.45f, 0.3f, 0.3f, false));
        Biomes.n_1700_B(34, biomeBiomes.d_2427_y, j_154_J.n_1700_B(1.0f, 0.5f, SurfaceBuilders.s_956_w, true));
        Biomes.n_1700_B(35, biomeBiomes.z_1737_N, j_154_J.n_1700_B(0.125f, 0.05f, 1.2f, false, false));
        Biomes.n_1700_B(36, biomeBiomes.v_4276_D, j_154_J.P_4830_p());
        Biomes.n_1700_B(37, biomeBiomes.d_2461_k, j_154_J.J_1907_R(0.1f, 0.2f, false));
        Biomes.n_1700_B(38, biomeBiomes.G_624_v, j_154_J.J_1907_R(1.5f, 0.025f));
        Biomes.n_1700_B(39, biomeBiomes.T_2506_i, j_154_J.J_1907_R(1.5f, 0.025f, true));
        Biomes.n_1700_B(40, biomeBiomes.q_4610_l, j_154_J.M_588_G());
        Biomes.n_1700_B(41, biomeBiomes.z_4693_k, j_154_J.s_956_w());
        Biomes.n_1700_B(42, biomeBiomes.g_221_o, j_154_J.u_2550_I());
        Biomes.n_1700_B(43, biomeBiomes.e_2887_G, j_154_J.w_1484_f());
        Biomes.n_1700_B(44, biomeBiomes.B_1668_F, j_154_J.Q_4569_t());
        Biomes.n_1700_B(45, biomeBiomes.g_164_R, j_154_J.G_564_y(false));
        Biomes.n_1700_B(46, biomeBiomes.X_933_l, j_154_J.J_1907_R(false));
        Biomes.n_1700_B(47, biomeBiomes.Z_976_R, j_154_J.M_182_A());
        Biomes.n_1700_B(48, biomeBiomes.H_1990_U, j_154_J.G_564_y(true));
        Biomes.n_1700_B(49, biomeBiomes.N_2525_X, j_154_J.J_1907_R(true));
        Biomes.n_1700_B(50, biomeBiomes.c_4037_x, j_154_J.P_1922_E(true));
        Biomes.n_1700_B(129, biomeBiomes.T_3594_S, j_154_J.n_1700_B(true));
        Biomes.n_1700_B(130, biomeBiomes.D_4792_h, j_154_J.n_1700_B(0.225f, 0.25f, false, false, false));
        Biomes.n_1700_B(131, biomeBiomes.s_2632_s, j_154_J.n_1700_B(1.0f, 0.5f, SurfaceBuilders.u_2550_I, false));
        Biomes.n_1700_B(132, biomeBiomes.l_1233_K, j_154_J.t_1786_h());
        Biomes.n_1700_B(133, biomeBiomes.z_1333_t, j_154_J.n_1700_B(0.3f, 0.4f, false, true, false, false));
        Biomes.n_1700_B(134, biomeBiomes.O_508_d, j_154_J.G_564_y(-0.1f, 0.3f, true));
        Biomes.n_1700_B(140, biomeBiomes.r_715_M, j_154_J.n_1700_B(0.425f, 0.45000002f, true, false));
        Biomes.n_1700_B(149, biomeBiomes.A_1038_p, j_154_J.G_564_y());
        Biomes.n_1700_B(151, biomeBiomes.i_1637_u, j_154_J.R_4764_Y());
        Biomes.n_1700_B(155, biomeBiomes.Ping, j_154_J.n_1700_B(0.2f, 0.4f, true));
        Biomes.n_1700_B(156, biomeBiomes.p_178_J, j_154_J.n_1700_B(0.55f, 0.5f, true));
        Biomes.n_1700_B(157, biomeBiomes.RealmsClientConfig, j_154_J.R_4764_Y(0.2f, 0.4f, true));
        Biomes.n_1700_B(158, biomeBiomes.f_4016_n, j_154_J.n_1700_B(0.3f, 0.4f, true, true, false, false));
        Biomes.n_1700_B(160, biomeBiomes.j_276_v, j_154_J.n_1700_B(0.2f, 0.2f, 0.25f, true));
        Biomes.n_1700_B(161, biomeBiomes.UploadStatus, j_154_J.n_1700_B(0.2f, 0.2f, 0.25f, true));
        Biomes.n_1700_B(162, biomeBiomes.e_1992_r, j_154_J.n_1700_B(1.0f, 0.5f, SurfaceBuilders.u_2550_I, false));
        Biomes.n_1700_B(163, biomeBiomes.D_60_a, j_154_J.n_1700_B(0.3625f, 1.225f, 1.1f, true, true));
        Biomes.n_1700_B(164, biomeBiomes.k_3961_g, j_154_J.n_1700_B(1.05f, 1.2125001f, 1.0f, true, true));
        Biomes.n_1700_B(165, biomeBiomes.Ops, j_154_J.h_1847_R());
        Biomes.n_1700_B(166, biomeBiomes.h_4320_q, j_154_J.J_1907_R(0.45f, 0.3f));
        Biomes.n_1700_B(167, biomeBiomes.t_4219_U, j_154_J.J_1907_R(0.45f, 0.3f, true));
        Biomes.n_1700_B(168, biomeBiomes.V_1446_Y, j_154_J.u_1723_Y());
        Biomes.n_1700_B(169, biomeBiomes.PlayerInfo, j_154_J.v_4262_N());
        Biomes.n_1700_B(170, biomeBiomes.V_1225_t, j_154_J.Y_601_j());
        Biomes.n_1700_B(171, biomeBiomes.U_1241_n, j_154_J.Q_2552_b());
        Biomes.n_1700_B(172, biomeBiomes.q_1982_R, j_154_J.C_2741_M());
        Biomes.n_1700_B(173, biomeBiomes.dtoRealmsServerAddress, j_154_J.Y_259_p());
    }
}


