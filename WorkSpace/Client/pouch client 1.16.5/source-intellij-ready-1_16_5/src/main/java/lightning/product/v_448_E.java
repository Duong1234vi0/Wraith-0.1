/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
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

public class v_448_E
extends g_2711_h {
    public static final e_563_h<w_801_N> Q_4569_t = BlockStateProperties.l_1233_K;
    public static final U_1266_O M_182_A = BlockStateProperties.C_2741_M;

    protected v_448_E(q_4293_E.P_1922_E builder) {
        super(true, builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(Q_4569_t, w_801_N.n_1700_B)).n_1700_B(M_182_A, false));
    }

    protected boolean n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, boolean searchForward, int recursionCount) {
        if (recursionCount >= 8) {
            return false;
        }
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        boolean flag = true;
        w_801_N railshape = state.R_4764_Y(Q_4569_t);
        switch (railshape) {
            case n_1700_B: {
                if (searchForward) {
                    ++k;
                    break;
                }
                --k;
                break;
            }
            case J_1907_R: {
                if (searchForward) {
                    --i;
                    break;
                }
                ++i;
                break;
            }
            case R_4764_Y: {
                if (searchForward) {
                    --i;
                } else {
                    ++i;
                    ++j;
                    flag = false;
                }
                railshape = w_801_N.J_1907_R;
                break;
            }
            case G_564_y: {
                if (searchForward) {
                    --i;
                    ++j;
                    flag = false;
                } else {
                    ++i;
                }
                railshape = w_801_N.J_1907_R;
                break;
            }
            case P_1922_E: {
                if (searchForward) {
                    ++k;
                } else {
                    --k;
                    ++j;
                    flag = false;
                }
                railshape = w_801_N.n_1700_B;
                break;
            }
            case u_1723_Y: {
                if (searchForward) {
                    ++k;
                    ++j;
                    flag = false;
                } else {
                    --k;
                }
                railshape = w_801_N.n_1700_B;
            }
        }
        if (this.n_1700_B(worldIn, new c_1514_x(i, j, k), searchForward, recursionCount, railshape)) {
            return true;
        }
        return flag && this.n_1700_B(worldIn, new c_1514_x(i, j - 1, k), searchForward, recursionCount, railshape);
    }

    protected boolean n_1700_B(b_4507_u world, c_1514_x state, boolean searchForward, int recursionCount, w_801_N shape) {
        K_4074_S blockstate = world.getBlockState(state);
        if (!blockstate.n_1700_B(this)) {
            return false;
        }
        w_801_N railshape = blockstate.R_4764_Y(Q_4569_t);
        if (shape != w_801_N.J_1907_R || railshape != w_801_N.n_1700_B && railshape != w_801_N.P_1922_E && railshape != w_801_N.u_1723_Y) {
            if (shape != w_801_N.n_1700_B || railshape != w_801_N.J_1907_R && railshape != w_801_N.R_4764_Y && railshape != w_801_N.G_564_y) {
                if (blockstate.R_4764_Y(M_182_A).booleanValue()) {
                    return world.Y_601_j(state) ? true : this.n_1700_B(world, state, blockstate, searchForward, recursionCount + 1);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    protected void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn) {
        boolean flag1;
        boolean flag = state.R_4764_Y(M_182_A);
        boolean bl = flag1 = worldIn.Y_601_j(pos) || this.n_1700_B(worldIn, pos, state, true, 0) || this.n_1700_B(worldIn, pos, state, false, 0);
        if (flag1 != flag) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(M_182_A, flag1), 3);
            worldIn.J_1907_R(pos.down(), this);
            if (state.R_4764_Y(Q_4569_t).J_1907_R()) {
                worldIn.J_1907_R(pos.up(), this);
            }
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
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.J_1907_R);
                    }
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.n_1700_B);
                    }
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
                }
            }
            case J_1907_R: {
                switch (state.R_4764_Y(Q_4569_t)) {
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.J_1907_R);
                    }
                    case J_1907_R: {
                        return (K_4074_S)state.n_1700_B(Q_4569_t, w_801_N.n_1700_B);
                    }
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
        builder.n_1700_B(Q_4569_t, M_182_A);
    }
}


