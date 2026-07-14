/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;

public final class R_3043_n {
    public static final R_3043_n n_1700_B = R_3043_n.n_1700_B("desert");
    public static final R_3043_n J_1907_R = R_3043_n.n_1700_B("jungle");
    public static final R_3043_n R_4764_Y = R_3043_n.n_1700_B("plains");
    public static final R_3043_n G_564_y = R_3043_n.n_1700_B("savanna");
    public static final R_3043_n P_1922_E = R_3043_n.n_1700_B("snow");
    public static final R_3043_n u_1723_Y = R_3043_n.n_1700_B("swamp");
    public static final R_3043_n v_4262_N = R_3043_n.n_1700_B("taiga");
    private final String w_1484_f;
    private static final Map<f_2392_k<k_594_Q>, R_3043_n> t_148_a = j_3341_s.n_1700_B(Maps.newHashMap(), p_221172_0_ -> {
        p_221172_0_.put(biomeBiomes.d_2461_k, n_1700_B);
        p_221172_0_.put(biomeBiomes.T_2506_i, n_1700_B);
        p_221172_0_.put(biomeBiomes.R_4764_Y, n_1700_B);
        p_221172_0_.put(biomeBiomes.multiplayerClientSuggestionProvider, n_1700_B);
        p_221172_0_.put(biomeBiomes.D_4792_h, n_1700_B);
        p_221172_0_.put(biomeBiomes.Ops, n_1700_B);
        p_221172_0_.put(biomeBiomes.t_4219_U, n_1700_B);
        p_221172_0_.put(biomeBiomes.h_4320_q, n_1700_B);
        p_221172_0_.put(biomeBiomes.G_624_v, n_1700_B);
        p_221172_0_.put(biomeBiomes.V_1446_Y, J_1907_R);
        p_221172_0_.put(biomeBiomes.PlayerInfo, J_1907_R);
        p_221172_0_.put(biomeBiomes.Q_2552_b, J_1907_R);
        p_221172_0_.put(biomeBiomes.k_2293_S, J_1907_R);
        p_221172_0_.put(biomeBiomes.C_2741_M, J_1907_R);
        p_221172_0_.put(biomeBiomes.A_1038_p, J_1907_R);
        p_221172_0_.put(biomeBiomes.i_1637_u, J_1907_R);
        p_221172_0_.put(biomeBiomes.v_4276_D, G_564_y);
        p_221172_0_.put(biomeBiomes.z_1737_N, G_564_y);
        p_221172_0_.put(biomeBiomes.D_60_a, G_564_y);
        p_221172_0_.put(biomeBiomes.k_3961_g, G_564_y);
        p_221172_0_.put(biomeBiomes.c_4037_x, P_1922_E);
        p_221172_0_.put(biomeBiomes.u_2550_I, P_1922_E);
        p_221172_0_.put(biomeBiomes.M_588_G, P_1922_E);
        p_221172_0_.put(biomeBiomes.r_715_M, P_1922_E);
        p_221172_0_.put(biomeBiomes.c_3005_b, P_1922_E);
        p_221172_0_.put(biomeBiomes.h_1847_R, P_1922_E);
        p_221172_0_.put(biomeBiomes.t_4043_B, P_1922_E);
        p_221172_0_.put(biomeBiomes.x_607_J, P_1922_E);
        p_221172_0_.put(biomeBiomes.f_4016_n, P_1922_E);
        p_221172_0_.put(biomeBiomes.P_4830_p, P_1922_E);
        p_221172_0_.put(biomeBiomes.v_4262_N, u_1723_Y);
        p_221172_0_.put(biomeBiomes.O_508_d, u_1723_Y);
        p_221172_0_.put(biomeBiomes.j_276_v, v_4262_N);
        p_221172_0_.put(biomeBiomes.UploadStatus, v_4262_N);
        p_221172_0_.put(biomeBiomes.e_4240_b, v_4262_N);
        p_221172_0_.put(biomeBiomes.n_3318_d, v_4262_N);
        p_221172_0_.put(biomeBiomes.s_2632_s, v_4262_N);
        p_221172_0_.put(biomeBiomes.e_1992_r, v_4262_N);
        p_221172_0_.put(biomeBiomes.Y_259_p, v_4262_N);
        p_221172_0_.put(biomeBiomes.G_564_y, v_4262_N);
        p_221172_0_.put(biomeBiomes.u_1723_Y, v_4262_N);
        p_221172_0_.put(biomeBiomes.Y_601_j, v_4262_N);
        p_221172_0_.put(biomeBiomes.z_1333_t, v_4262_N);
        p_221172_0_.put(biomeBiomes.d_2427_y, v_4262_N);
    });

    private R_3043_n(String p_i241919_1_) {
        this.w_1484_f = p_i241919_1_;
    }

    public String toString() {
        return this.w_1484_f;
    }

    private static R_3043_n n_1700_B(String key) {
        return V_3137_a.n_1700_B(V_3137_a.O_508_d, new g_2336_b(key), new R_3043_n(key));
    }

    public static R_3043_n n_1700_B(Optional<f_2392_k<k_594_Q>> p_242371_0_) {
        return p_242371_0_.flatMap(p_242372_0_ -> Optional.ofNullable(t_148_a.get(p_242372_0_))).orElse(R_4764_Y);
    }
}


