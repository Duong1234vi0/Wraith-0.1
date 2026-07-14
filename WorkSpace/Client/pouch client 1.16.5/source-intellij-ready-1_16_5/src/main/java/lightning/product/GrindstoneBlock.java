/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2203_T;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_4082_i;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.FaceAttachedHorizontalDirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;

public class GrindstoneBlock
extends FaceAttachedHorizontalDirectionalBlock {
    public static final s_1395_c P_4830_p = T_2915_h.n_1700_B(2.0, 0.0, 6.0, 4.0, 7.0, 10.0);
    public static final s_1395_c h_1847_R = T_2915_h.n_1700_B(12.0, 0.0, 6.0, 14.0, 7.0, 10.0);
    public static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(2.0, 7.0, 5.0, 4.0, 13.0, 11.0);
    public static final s_1395_c M_182_A = T_2915_h.n_1700_B(12.0, 7.0, 5.0, 14.0, 13.0, 11.0);
    public static final s_1395_c t_1786_h = x_268_Y.n_1700_B(P_4830_p, Q_4569_t);
    public static final s_1395_c multiplayerClientSuggestionProvider = x_268_Y.n_1700_B(h_1847_R, M_182_A);
    public static final s_1395_c w_1457_N = x_268_Y.n_1700_B(t_1786_h, multiplayerClientSuggestionProvider);
    public static final s_1395_c Y_601_j = x_268_Y.n_1700_B(w_1457_N, T_2915_h.n_1700_B(4.0, 4.0, 2.0, 12.0, 16.0, 14.0));
    public static final s_1395_c Y_259_p = T_2915_h.n_1700_B(6.0, 0.0, 2.0, 10.0, 7.0, 4.0);
    public static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(6.0, 0.0, 12.0, 10.0, 7.0, 14.0);
    public static final s_1395_c C_2741_M = T_2915_h.n_1700_B(5.0, 7.0, 2.0, 11.0, 13.0, 4.0);
    public static final s_1395_c k_2293_S = T_2915_h.n_1700_B(5.0, 7.0, 12.0, 11.0, 13.0, 14.0);
    public static final s_1395_c q_2307_F = x_268_Y.n_1700_B(Y_259_p, C_2741_M);
    public static final s_1395_c Z_875_P = x_268_Y.n_1700_B(Q_2552_b, k_2293_S);
    public static final s_1395_c c_3005_b = x_268_Y.n_1700_B(q_2307_F, Z_875_P);
    public static final s_1395_c H_2857_Y = x_268_Y.n_1700_B(c_3005_b, T_2915_h.n_1700_B(2.0, 4.0, 4.0, 14.0, 16.0, 12.0));
    public static final s_1395_c A_4115_X = T_2915_h.n_1700_B(2.0, 6.0, 0.0, 4.0, 10.0, 7.0);
    public static final s_1395_c e_4240_b = T_2915_h.n_1700_B(12.0, 6.0, 0.0, 14.0, 10.0, 7.0);
    public static final s_1395_c n_3318_d = T_2915_h.n_1700_B(2.0, 5.0, 7.0, 4.0, 11.0, 13.0);
    public static final s_1395_c d_2427_y = T_2915_h.n_1700_B(12.0, 5.0, 7.0, 14.0, 11.0, 13.0);
    public static final s_1395_c z_1737_N = x_268_Y.n_1700_B(A_4115_X, n_3318_d);
    public static final s_1395_c v_4276_D = x_268_Y.n_1700_B(e_4240_b, d_2427_y);
    public static final s_1395_c d_2461_k = x_268_Y.n_1700_B(z_1737_N, v_4276_D);
    public static final s_1395_c G_624_v = x_268_Y.n_1700_B(d_2461_k, T_2915_h.n_1700_B(4.0, 2.0, 4.0, 12.0, 14.0, 16.0));
    public static final s_1395_c T_2506_i = T_2915_h.n_1700_B(2.0, 6.0, 7.0, 4.0, 10.0, 16.0);
    public static final s_1395_c q_4610_l = T_2915_h.n_1700_B(12.0, 6.0, 7.0, 14.0, 10.0, 16.0);
    public static final s_1395_c z_4693_k = T_2915_h.n_1700_B(2.0, 5.0, 3.0, 4.0, 11.0, 9.0);
    public static final s_1395_c g_221_o = T_2915_h.n_1700_B(12.0, 5.0, 3.0, 14.0, 11.0, 9.0);
    public static final s_1395_c e_2887_G = x_268_Y.n_1700_B(T_2506_i, z_4693_k);
    public static final s_1395_c B_1668_F = x_268_Y.n_1700_B(q_4610_l, g_221_o);
    public static final s_1395_c g_164_R = x_268_Y.n_1700_B(e_2887_G, B_1668_F);
    public static final s_1395_c X_933_l = x_268_Y.n_1700_B(g_164_R, T_2915_h.n_1700_B(4.0, 2.0, 0.0, 12.0, 14.0, 12.0));
    public static final s_1395_c Z_976_R = T_2915_h.n_1700_B(7.0, 6.0, 2.0, 16.0, 10.0, 4.0);
    public static final s_1395_c H_1990_U = T_2915_h.n_1700_B(7.0, 6.0, 12.0, 16.0, 10.0, 14.0);
    public static final s_1395_c N_2525_X = T_2915_h.n_1700_B(3.0, 5.0, 2.0, 9.0, 11.0, 4.0);
    public static final s_1395_c c_4037_x = T_2915_h.n_1700_B(3.0, 5.0, 12.0, 9.0, 11.0, 14.0);
    public static final s_1395_c g_2268_R = x_268_Y.n_1700_B(Z_976_R, N_2525_X);
    public static final s_1395_c T_3594_S = x_268_Y.n_1700_B(H_1990_U, c_4037_x);
    public static final s_1395_c D_4792_h = x_268_Y.n_1700_B(g_2268_R, T_3594_S);
    public static final s_1395_c s_2632_s = x_268_Y.n_1700_B(D_4792_h, T_2915_h.n_1700_B(0.0, 2.0, 4.0, 12.0, 14.0, 12.0));
    public static final s_1395_c l_1233_K = T_2915_h.n_1700_B(0.0, 6.0, 2.0, 9.0, 10.0, 4.0);
    public static final s_1395_c z_1333_t = T_2915_h.n_1700_B(0.0, 6.0, 12.0, 9.0, 10.0, 14.0);
    public static final s_1395_c O_508_d = T_2915_h.n_1700_B(7.0, 5.0, 2.0, 13.0, 11.0, 4.0);
    public static final s_1395_c r_715_M = T_2915_h.n_1700_B(7.0, 5.0, 12.0, 13.0, 11.0, 14.0);
    public static final s_1395_c A_1038_p = x_268_Y.n_1700_B(l_1233_K, O_508_d);
    public static final s_1395_c i_1637_u = x_268_Y.n_1700_B(z_1333_t, r_715_M);
    public static final s_1395_c Ping = x_268_Y.n_1700_B(A_1038_p, i_1637_u);
    public static final s_1395_c p_178_J = x_268_Y.n_1700_B(Ping, T_2915_h.n_1700_B(4.0, 2.0, 4.0, 16.0, 14.0, 12.0));
    public static final s_1395_c RealmsClientConfig = T_2915_h.n_1700_B(2.0, 9.0, 6.0, 4.0, 16.0, 10.0);
    public static final s_1395_c f_4016_n = T_2915_h.n_1700_B(12.0, 9.0, 6.0, 14.0, 16.0, 10.0);
    public static final s_1395_c j_276_v = T_2915_h.n_1700_B(2.0, 3.0, 5.0, 4.0, 9.0, 11.0);
    public static final s_1395_c UploadStatus = T_2915_h.n_1700_B(12.0, 3.0, 5.0, 14.0, 9.0, 11.0);
    public static final s_1395_c e_1992_r = x_268_Y.n_1700_B(RealmsClientConfig, j_276_v);
    public static final s_1395_c D_60_a = x_268_Y.n_1700_B(f_4016_n, UploadStatus);
    public static final s_1395_c k_3961_g = x_268_Y.n_1700_B(e_1992_r, D_60_a);
    public static final s_1395_c Ops = x_268_Y.n_1700_B(k_3961_g, T_2915_h.n_1700_B(4.0, 0.0, 2.0, 12.0, 12.0, 14.0));
    public static final s_1395_c h_4320_q = T_2915_h.n_1700_B(6.0, 9.0, 2.0, 10.0, 16.0, 4.0);
    public static final s_1395_c t_4219_U = T_2915_h.n_1700_B(6.0, 9.0, 12.0, 10.0, 16.0, 14.0);
    public static final s_1395_c V_1446_Y = T_2915_h.n_1700_B(5.0, 3.0, 2.0, 11.0, 9.0, 4.0);
    public static final s_1395_c PlayerInfo = T_2915_h.n_1700_B(5.0, 3.0, 12.0, 11.0, 9.0, 14.0);
    public static final s_1395_c V_1225_t = x_268_Y.n_1700_B(h_4320_q, V_1446_Y);
    public static final s_1395_c U_1241_n = x_268_Y.n_1700_B(t_4219_U, PlayerInfo);
    public static final s_1395_c q_1982_R = x_268_Y.n_1700_B(V_1225_t, U_1241_n);
    public static final s_1395_c dtoRealmsServerAddress = x_268_Y.n_1700_B(q_1982_R, T_2915_h.n_1700_B(2.0, 0.0, 4.0, 14.0, 12.0, 12.0));
    private static final x_282_a j_1564_a = new F_2904_S("container.grindstone_title");

    protected GrindstoneBlock(q_4293_E.P_1922_E propertiesIn) {
        super(propertiesIn);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(RealmsServerPing, F_2203_T.J_1907_R));
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    private s_1395_c t_148_a(K_4074_S state) {
        b_257_Y direction = state.R_4764_Y(w_612_n);
        switch ((F_2203_T)state.R_4764_Y(RealmsServerPing)) {
            case n_1700_B: {
                if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.G_564_y) {
                    return H_2857_Y;
                }
                return Y_601_j;
            }
            case J_1907_R: {
                if (direction == b_257_Y.R_4764_Y) {
                    return X_933_l;
                }
                if (direction == b_257_Y.G_564_y) {
                    return G_624_v;
                }
                if (direction == b_257_Y.u_1723_Y) {
                    return p_178_J;
                }
                return s_2632_s;
            }
            case R_4764_Y: {
                if (direction != b_257_Y.R_4764_Y && direction != b_257_Y.G_564_y) {
                    return dtoRealmsServerAddress;
                }
                return Ops;
            }
        }
        return H_2857_Y;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.t_148_a(state);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.t_148_a(state);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return true;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        player.J_1907_R(Stats.RealmsServerPing);
        return m_3054_I.J_1907_R;
    }

    @Override
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new F_4082_i(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), j_1564_a);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(w_612_n, rot.n_1700_B(state.R_4764_Y(w_612_n)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(w_612_n)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, RealmsServerPing);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


