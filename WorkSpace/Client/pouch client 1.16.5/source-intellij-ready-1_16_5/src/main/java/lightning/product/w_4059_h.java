/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2334_m;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.g_2711_h;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;
import lightning.product.w_801_N;

public class w_4059_h
extends g_2711_h {
    public static final e_563_h<w_801_N> Q_4569_t = BlockStateProperties.s_2632_s;

    protected w_4059_h(q_4293_E.P_1922_E builder) {
        super(false, builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(Q_4569_t, w_801_N.n_1700_B));
    }

    @Override
    protected void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn) {
        if (blockIn.multiplayerClientSuggestionProvider().t_148_a() && new U_2334_m(worldIn, pos, state).J_1907_R() == 3) {
            this.n_1700_B(worldIn, pos, state, false);
        }
    }

    @Override
    public v_3760_Q<w_801_N> t_148_a() {
        return Q_4569_t;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                switch (state.R_4764_Y(Q_4569_t)) {
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.G_564_y);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.R_4764_Y);
                    }
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.u_1723_Y);
                    }
                    case u_1723_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.P_1922_E);
                    }
                    case v_4262_N: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.t_148_a);
                    }
                    case w_1484_f: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.s_956_w);
                    }
                    case t_148_a: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.v_4262_N);
                    }
                    case s_956_w: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.w_1484_f);
                    }
                }
            }
            case G_564_y: {
                switch (state.R_4764_Y(Q_4569_t)) {
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.P_1922_E);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.u_1723_Y);
                    }
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.G_564_y);
                    }
                    case u_1723_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.R_4764_Y);
                    }
                    case v_4262_N: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.s_956_w);
                    }
                    case w_1484_f: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.v_4262_N);
                    }
                    case t_148_a: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.w_1484_f);
                    }
                    case s_956_w: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.t_148_a);
                    }
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.J_1907_R);
                    }
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.n_1700_B);
                    }
                }
            }
            case J_1907_R: {
                switch (state.R_4764_Y(Q_4569_t)) {
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.u_1723_Y);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.P_1922_E);
                    }
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.R_4764_Y);
                    }
                    case u_1723_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.G_564_y);
                    }
                    case v_4262_N: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.w_1484_f);
                    }
                    case w_1484_f: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.t_148_a);
                    }
                    case t_148_a: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.s_956_w);
                    }
                    case s_956_w: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.v_4262_N);
                    }
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.J_1907_R);
                    }
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.n_1700_B);
                    }
                }
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        w_801_N railshape = state.R_4764_Y(Q_4569_t);
        block0 : switch (mirrorIn) {
            case J_1907_R: {
                switch (railshape) {
                    case P_1922_E: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.u_1723_Y);
                    }
                    case u_1723_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.P_1922_E);
                    }
                    case v_4262_N: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.s_956_w);
                    }
                    case w_1484_f: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.t_148_a);
                    }
                    case t_148_a: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.w_1484_f);
                    }
                    case s_956_w: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.v_4262_N);
                    }
                }
                return super.n_1700_B(state, mirrorIn);
            }
            case R_4764_Y: {
                switch (railshape) {
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.G_564_y);
                    }
                    case G_564_y: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.R_4764_Y);
                    }
                    default: {
                        break block0;
                    }
                    case v_4262_N: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.w_1484_f);
                    }
                    case w_1484_f: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.v_4262_N);
                    }
                    case t_148_a: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.s_956_w);
                    }
                    case s_956_w: 
                }
                return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.t_148_a);
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{Q_4569_t});
    }
}


