/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import lightning.product.I_3887_a;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.Items;

public final class n_4974_X
extends Enum<n_4974_X> {
    public static final /* enum */ n_4974_X n_1700_B = new n_4974_X(new Z_1993_T(Items.X_1303_p));
    public static final /* enum */ n_4974_X J_1907_R = new n_4974_X(new Z_1993_T(a_3742_W.d_4007_L));
    public static final /* enum */ n_4974_X R_4764_Y = new n_4974_X(new Z_1993_T(Items.v_570_f));
    public static final /* enum */ n_4974_X G_564_y = new n_4974_X(new Z_1993_T(Items.E_390_U), new Z_1993_T(Items.n_2412_y));
    public static final /* enum */ n_4974_X P_1922_E = new n_4974_X(new Z_1993_T(Items.u_1934_K), new Z_1993_T(Items.E_738_L));
    public static final /* enum */ n_4974_X u_1723_Y = new n_4974_X(new Z_1993_T(Items.X_1303_p));
    public static final /* enum */ n_4974_X v_4262_N = new n_4974_X(new Z_1993_T(Items.j_1654_T));
    public static final /* enum */ n_4974_X w_1484_f = new n_4974_X(new Z_1993_T(a_3742_W.J_1907_R));
    public static final /* enum */ n_4974_X t_148_a = new n_4974_X(new Z_1993_T(Items.u_1934_K), new Z_1993_T(Items.Y_2905_A));
    public static final /* enum */ n_4974_X s_956_w = new n_4974_X(new Z_1993_T(Items.X_1303_p));
    public static final /* enum */ n_4974_X u_2550_I = new n_4974_X(new Z_1993_T(a_3742_W.o_1800_r));
    public static final /* enum */ n_4974_X M_588_G = new n_4974_X(new Z_1993_T(Items.Z_1243_X), new Z_1993_T(Items.k_4946_A));
    public static final /* enum */ n_4974_X P_4830_p = new n_4974_X(new Z_1993_T(Items.X_1303_p));
    public static final /* enum */ n_4974_X h_1847_R = new n_4974_X(new Z_1993_T(Items.j_1654_T));
    public static final /* enum */ n_4974_X Q_4569_t = new n_4974_X(new Z_1993_T(Items.q_839_y));
    public static final /* enum */ n_4974_X M_182_A = new n_4974_X(new Z_1993_T(Items.O_1043_U));
    public static final /* enum */ n_4974_X t_1786_h = new n_4974_X(new Z_1993_T(Items.j_1654_T));
    public static final /* enum */ n_4974_X multiplayerClientSuggestionProvider = new n_4974_X(new Z_1993_T(Items.AimAssist));
    public static final List<n_4974_X> w_1457_N;
    public static final List<n_4974_X> Y_601_j;
    public static final List<n_4974_X> Y_259_p;
    public static final List<n_4974_X> Q_2552_b;
    public static final Map<n_4974_X, List<n_4974_X>> C_2741_M;
    private final List<Z_1993_T> k_2293_S;
    private static final /* synthetic */ n_4974_X[] q_2307_F;

    public static n_4974_X[] values() {
        return (n_4974_X[])q_2307_F.clone();
    }

    public static n_4974_X valueOf(String name) {
        return Enum.valueOf(n_4974_X.class, name);
    }

    private n_4974_X(Z_1993_T ... p_i48836_3_) {
        this.k_2293_S = ImmutableList.copyOf((Object[])p_i48836_3_);
    }

    public static List<n_4974_X> n_1700_B(I_3887_a p_243236_0_) {
        switch (p_243236_0_) {
            case n_1700_B: {
                return Q_2552_b;
            }
            case J_1907_R: {
                return Y_259_p;
            }
            case R_4764_Y: {
                return Y_601_j;
            }
            case G_564_y: {
                return w_1457_N;
            }
        }
        return ImmutableList.of();
    }

    public List<Z_1993_T> n_1700_B() {
        return this.k_2293_S;
    }

    private static /* synthetic */ n_4974_X[] J_1907_R() {
        return new n_4974_X[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, multiplayerClientSuggestionProvider};
    }

    static {
        q_2307_F = n_4974_X.J_1907_R();
        w_1457_N = ImmutableList.of((Object)((Object)P_4830_p), (Object)((Object)h_1847_R));
        Y_601_j = ImmutableList.of((Object)((Object)s_956_w), (Object)((Object)u_2550_I), (Object)((Object)M_588_G));
        Y_259_p = ImmutableList.of((Object)((Object)u_1723_Y), (Object)((Object)v_4262_N), (Object)((Object)w_1484_f), (Object)((Object)t_148_a));
        Q_2552_b = ImmutableList.of((Object)((Object)n_1700_B), (Object)((Object)G_564_y), (Object)((Object)J_1907_R), (Object)((Object)P_1922_E), (Object)((Object)R_4764_Y));
        C_2741_M = ImmutableMap.of((Object)((Object)n_1700_B), (Object)ImmutableList.of((Object)((Object)G_564_y), (Object)((Object)J_1907_R), (Object)((Object)P_1922_E), (Object)((Object)R_4764_Y)), (Object)((Object)u_1723_Y), (Object)ImmutableList.of((Object)((Object)v_4262_N), (Object)((Object)w_1484_f), (Object)((Object)t_148_a)), (Object)((Object)s_956_w), (Object)ImmutableList.of((Object)((Object)u_2550_I), (Object)((Object)M_588_G)), (Object)((Object)P_4830_p), (Object)ImmutableList.of((Object)((Object)h_1847_R)));
    }
}



