/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.booleans.BooleanArrayList
 *  it.unimi.dsi.fastutil.booleans.BooleanList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.J_2170_O;
import lightning.product.O_4606_n;
import lightning.product.b_257_Y;
import lightning.product.j_3341_s;
import lightning.product.o_1290_k;

public final class r_4970_d
extends Enum<r_4970_d>
implements E_4700_p {
    public static final /* enum */ r_4970_d n_1700_B = new r_4970_d("identity", J_2170_O.n_1700_B, false, false, false);
    public static final /* enum */ r_4970_d J_1907_R = new r_4970_d("rot_180_face_xy", J_2170_O.n_1700_B, true, true, false);
    public static final /* enum */ r_4970_d R_4764_Y = new r_4970_d("rot_180_face_xz", J_2170_O.n_1700_B, true, false, true);
    public static final /* enum */ r_4970_d G_564_y = new r_4970_d("rot_180_face_yz", J_2170_O.n_1700_B, false, true, true);
    public static final /* enum */ r_4970_d P_1922_E = new r_4970_d("rot_120_nnn", J_2170_O.G_564_y, false, false, false);
    public static final /* enum */ r_4970_d u_1723_Y = new r_4970_d("rot_120_nnp", J_2170_O.P_1922_E, true, false, true);
    public static final /* enum */ r_4970_d v_4262_N = new r_4970_d("rot_120_npn", J_2170_O.P_1922_E, false, true, true);
    public static final /* enum */ r_4970_d w_1484_f = new r_4970_d("rot_120_npp", J_2170_O.G_564_y, true, false, true);
    public static final /* enum */ r_4970_d t_148_a = new r_4970_d("rot_120_pnn", J_2170_O.P_1922_E, true, true, false);
    public static final /* enum */ r_4970_d s_956_w = new r_4970_d("rot_120_pnp", J_2170_O.G_564_y, true, true, false);
    public static final /* enum */ r_4970_d u_2550_I = new r_4970_d("rot_120_ppn", J_2170_O.G_564_y, false, true, true);
    public static final /* enum */ r_4970_d M_588_G = new r_4970_d("rot_120_ppp", J_2170_O.P_1922_E, false, false, false);
    public static final /* enum */ r_4970_d P_4830_p = new r_4970_d("rot_180_edge_xy_neg", J_2170_O.J_1907_R, true, true, true);
    public static final /* enum */ r_4970_d h_1847_R = new r_4970_d("rot_180_edge_xy_pos", J_2170_O.J_1907_R, false, false, true);
    public static final /* enum */ r_4970_d Q_4569_t = new r_4970_d("rot_180_edge_xz_neg", J_2170_O.u_1723_Y, true, true, true);
    public static final /* enum */ r_4970_d M_182_A = new r_4970_d("rot_180_edge_xz_pos", J_2170_O.u_1723_Y, false, true, false);
    public static final /* enum */ r_4970_d t_1786_h = new r_4970_d("rot_180_edge_yz_neg", J_2170_O.R_4764_Y, true, true, true);
    public static final /* enum */ r_4970_d multiplayerClientSuggestionProvider = new r_4970_d("rot_180_edge_yz_pos", J_2170_O.R_4764_Y, true, false, false);
    public static final /* enum */ r_4970_d w_1457_N = new r_4970_d("rot_90_x_neg", J_2170_O.R_4764_Y, false, false, true);
    public static final /* enum */ r_4970_d Y_601_j = new r_4970_d("rot_90_x_pos", J_2170_O.R_4764_Y, false, true, false);
    public static final /* enum */ r_4970_d Y_259_p = new r_4970_d("rot_90_y_neg", J_2170_O.u_1723_Y, true, false, false);
    public static final /* enum */ r_4970_d Q_2552_b = new r_4970_d("rot_90_y_pos", J_2170_O.u_1723_Y, false, false, true);
    public static final /* enum */ r_4970_d C_2741_M = new r_4970_d("rot_90_z_neg", J_2170_O.J_1907_R, false, true, false);
    public static final /* enum */ r_4970_d k_2293_S = new r_4970_d("rot_90_z_pos", J_2170_O.J_1907_R, true, false, false);
    public static final /* enum */ r_4970_d q_2307_F = new r_4970_d("inversion", J_2170_O.n_1700_B, true, true, true);
    public static final /* enum */ r_4970_d Z_875_P = new r_4970_d("invert_x", J_2170_O.n_1700_B, true, false, false);
    public static final /* enum */ r_4970_d c_3005_b = new r_4970_d("invert_y", J_2170_O.n_1700_B, false, true, false);
    public static final /* enum */ r_4970_d H_2857_Y = new r_4970_d("invert_z", J_2170_O.n_1700_B, false, false, true);
    public static final /* enum */ r_4970_d A_4115_X = new r_4970_d("rot_60_ref_nnn", J_2170_O.P_1922_E, true, true, true);
    public static final /* enum */ r_4970_d Y_1740_V = new r_4970_d("rot_60_ref_nnp", J_2170_O.G_564_y, true, false, false);
    public static final /* enum */ r_4970_d t_4043_B = new r_4970_d("rot_60_ref_npn", J_2170_O.G_564_y, false, false, true);
    public static final /* enum */ r_4970_d x_607_J = new r_4970_d("rot_60_ref_npp", J_2170_O.P_1922_E, false, false, true);
    public static final /* enum */ r_4970_d e_4240_b = new r_4970_d("rot_60_ref_pnn", J_2170_O.G_564_y, false, true, false);
    public static final /* enum */ r_4970_d n_3318_d = new r_4970_d("rot_60_ref_pnp", J_2170_O.P_1922_E, true, false, false);
    public static final /* enum */ r_4970_d d_2427_y = new r_4970_d("rot_60_ref_ppn", J_2170_O.P_1922_E, false, true, false);
    public static final /* enum */ r_4970_d z_1737_N = new r_4970_d("rot_60_ref_ppp", J_2170_O.G_564_y, true, true, true);
    public static final /* enum */ r_4970_d v_4276_D = new r_4970_d("swap_xy", J_2170_O.J_1907_R, false, false, false);
    public static final /* enum */ r_4970_d d_2461_k = new r_4970_d("swap_yz", J_2170_O.R_4764_Y, false, false, false);
    public static final /* enum */ r_4970_d G_624_v = new r_4970_d("swap_xz", J_2170_O.u_1723_Y, false, false, false);
    public static final /* enum */ r_4970_d T_2506_i = new r_4970_d("swap_neg_xy", J_2170_O.J_1907_R, true, true, false);
    public static final /* enum */ r_4970_d q_4610_l = new r_4970_d("swap_neg_yz", J_2170_O.R_4764_Y, false, true, true);
    public static final /* enum */ r_4970_d z_4693_k = new r_4970_d("swap_neg_xz", J_2170_O.u_1723_Y, true, false, true);
    public static final /* enum */ r_4970_d g_221_o = new r_4970_d("rot_90_ref_x_neg", J_2170_O.R_4764_Y, true, false, true);
    public static final /* enum */ r_4970_d e_2887_G = new r_4970_d("rot_90_ref_x_pos", J_2170_O.R_4764_Y, true, true, false);
    public static final /* enum */ r_4970_d B_1668_F = new r_4970_d("rot_90_ref_y_neg", J_2170_O.u_1723_Y, true, true, false);
    public static final /* enum */ r_4970_d g_164_R = new r_4970_d("rot_90_ref_y_pos", J_2170_O.u_1723_Y, false, true, true);
    public static final /* enum */ r_4970_d X_933_l = new r_4970_d("rot_90_ref_z_neg", J_2170_O.J_1907_R, false, true, true);
    public static final /* enum */ r_4970_d Z_976_R = new r_4970_d("rot_90_ref_z_pos", J_2170_O.J_1907_R, true, false, true);
    private final o_1290_k H_1990_U;
    private final String N_2525_X;
    @Nullable
    private Map<b_257_Y, b_257_Y> c_4037_x;
    private final boolean g_2268_R;
    private final boolean T_3594_S;
    private final boolean D_4792_h;
    private final J_2170_O s_2632_s;
    private static final r_4970_d[][] l_1233_K;
    private static final r_4970_d[] z_1333_t;
    private static final /* synthetic */ r_4970_d[] O_508_d;

    public static r_4970_d[] values() {
        return (r_4970_d[])O_508_d.clone();
    }

    public static r_4970_d valueOf(String name) {
        return Enum.valueOf(r_4970_d.class, name);
    }

    private r_4970_d(String p_i231784_3_, J_2170_O p_i231784_4_, boolean p_i231784_5_, boolean p_i231784_6_, boolean p_i231784_7_) {
        this.N_2525_X = p_i231784_3_;
        this.g_2268_R = p_i231784_5_;
        this.T_3594_S = p_i231784_6_;
        this.D_4792_h = p_i231784_7_;
        this.s_2632_s = p_i231784_4_;
        this.H_1990_U = new o_1290_k();
        this.H_1990_U.n_1700_B = p_i231784_5_ ? -1.0f : 1.0f;
        this.H_1990_U.P_1922_E = p_i231784_6_ ? -1.0f : 1.0f;
        this.H_1990_U.t_148_a = p_i231784_7_ ? -1.0f : 1.0f;
        this.H_1990_U.J_1907_R(p_i231784_4_.n_1700_B());
    }

    private BooleanList J_1907_R() {
        return new BooleanArrayList(new boolean[]{this.g_2268_R, this.T_3594_S, this.D_4792_h});
    }

    public r_4970_d n_1700_B(r_4970_d p_235527_1_) {
        return l_1233_K[this.ordinal()][p_235527_1_.ordinal()];
    }

    public String toString() {
        return this.N_2525_X;
    }

    @Override
    public String n_1700_B() {
        return this.N_2525_X;
    }

    public b_257_Y n_1700_B(b_257_Y p_235530_1_) {
        if (this.c_4037_x == null) {
            this.c_4037_x = Maps.newEnumMap(b_257_Y.class);
            for (b_257_Y direction : b_257_Y.values()) {
                b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
                b_257_Y.J_1907_R direction$axisdirection = direction.P_1922_E();
                b_257_Y.n_1700_B direction$axis1 = b_257_Y.n_1700_B.values()[this.s_2632_s.n_1700_B(direction$axis.ordinal())];
                b_257_Y.J_1907_R direction$axisdirection1 = this.n_1700_B(direction$axis1) ? direction$axisdirection.J_1907_R() : direction$axisdirection;
                b_257_Y direction1 = b_257_Y.n_1700_B(direction$axis1, direction$axisdirection1);
                this.c_4037_x.put(direction, direction1);
            }
        }
        return this.c_4037_x.get(p_235530_1_);
    }

    public boolean n_1700_B(b_257_Y.n_1700_B axis) {
        switch (axis) {
            case n_1700_B: {
                return this.g_2268_R;
            }
            case J_1907_R: {
                return this.T_3594_S;
            }
        }
        return this.D_4792_h;
    }

    public O_4606_n n_1700_B(O_4606_n p_235531_1_) {
        return O_4606_n.n_1700_B(this.n_1700_B(p_235531_1_.J_1907_R()), this.n_1700_B(p_235531_1_.R_4764_Y()));
    }

    private static /* synthetic */ r_4970_d[] R_4764_Y() {
        return new r_4970_d[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider, w_1457_N, Y_601_j, Y_259_p, Q_2552_b, C_2741_M, k_2293_S, q_2307_F, Z_875_P, c_3005_b, H_2857_Y, A_4115_X, Y_1740_V, t_4043_B, x_607_J, e_4240_b, n_3318_d, d_2427_y, z_1737_N, v_4276_D, d_2461_k, G_624_v, T_2506_i, q_4610_l, z_4693_k, g_221_o, e_2887_G, B_1668_F, g_164_R, X_933_l, Z_976_R};
    }

    static {
        O_508_d = r_4970_d.R_4764_Y();
        l_1233_K = j_3341_s.n_1700_B(new r_4970_d[r_4970_d.values().length][r_4970_d.values().length], (T p_235532_0_) -> {
            Map<Pair, r_4970_d> map = Arrays.stream(r_4970_d.values()).collect(Collectors.toMap(p_235536_0_ -> Pair.of((Object)((Object)p_235536_0_.s_2632_s), (Object)p_235536_0_.J_1907_R()), p_235535_0_ -> p_235535_0_));
            for (r_4970_d orientation : r_4970_d.values()) {
                for (r_4970_d orientation1 : r_4970_d.values()) {
                    BooleanList booleanlist = orientation.J_1907_R();
                    BooleanList booleanlist1 = orientation1.J_1907_R();
                    J_2170_O triplepermutation = orientation1.s_2632_s.n_1700_B(orientation.s_2632_s);
                    BooleanArrayList booleanarraylist = new BooleanArrayList(3);
                    for (int i = 0; i < 3; ++i) {
                        booleanarraylist.add(booleanlist.getBoolean(i) ^ booleanlist1.getBoolean(orientation.s_2632_s.n_1700_B(i)));
                    }
                    p_235532_0_[orientation.ordinal()][orientation1.ordinal()] = map.get(Pair.of((Object)((Object)triplepermutation), (Object)booleanarraylist));
                }
            }
        });
        z_1333_t = (r_4970_d[])Arrays.stream(r_4970_d.values()).map(p_235534_0_ -> Arrays.stream(r_4970_d.values()).filter(p_235528_1_ -> p_235534_0_.n_1700_B((r_4970_d)p_235528_1_) == n_1700_B).findAny().get()).toArray(r_4970_d[]::new);
    }
}


