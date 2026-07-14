/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.U_2334_m;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.a_2900_S;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.g_2711_h;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;
import lightning.product.w_801_N;
import lightning.product.y_4319_k;
import lightning.product.z_2326_J;

public class l_683_e
extends g_2711_h {
    public static final e_563_h<w_801_N> Q_4569_t = BlockStateProperties.l_1233_K;
    public static final U_1266_O M_182_A = BlockStateProperties.C_2741_M;

    public l_683_e(q_4293_E.P_1922_E properties) {
        super(true, properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(M_182_A, false)).n_1700_B(Q_4569_t, w_801_N.n_1700_B));
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!worldIn.Y_259_p && !state.R_4764_Y(M_182_A).booleanValue()) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(M_182_A).booleanValue()) {
            this.n_1700_B((b_4507_u)worldIn, pos, state);
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(M_182_A) != false ? 15 : 0;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        if (!blockState.R_4764_Y(M_182_A).booleanValue()) {
            return 0;
        }
        return side == b_257_Y.J_1907_R ? 15 : 0;
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        if (this.n_1700_B(state, (T_1316_M)worldIn, pos)) {
            boolean flag = state.R_4764_Y(M_182_A);
            boolean flag1 = false;
            List<y_4319_k> list = this.n_1700_B(worldIn, pos, y_4319_k.class, (Predicate<N_4263_v>)null);
            if (!list.isEmpty()) {
                flag1 = true;
            }
            if (flag1 && !flag) {
                K_4074_S blockstate = (K_4074_S)state.n_1700_B(M_182_A, true);
                worldIn.n_1700_B(pos, blockstate, 3);
                this.J_1907_R(worldIn, pos, blockstate, true);
                worldIn.J_1907_R(pos, this);
                worldIn.J_1907_R(pos.down(), this);
                worldIn.n_1700_B(pos, state, blockstate);
            }
            if (!flag1 && flag) {
                K_4074_S blockstate1 = (K_4074_S)state.n_1700_B(M_182_A, false);
                worldIn.n_1700_B(pos, blockstate1, 3);
                this.J_1907_R(worldIn, pos, blockstate1, false);
                worldIn.J_1907_R(pos, this);
                worldIn.J_1907_R(pos.down(), this);
                worldIn.n_1700_B(pos, state, blockstate1);
            }
            if (flag1) {
                worldIn.u_2550_I().n_1700_B(pos, this, 20);
            }
            worldIn.R_4764_Y(pos, this);
        }
    }

    protected void J_1907_R(b_4507_u worldIn, c_1514_x pos, K_4074_S state, boolean powered) {
        U_2334_m railstate = new U_2334_m(worldIn, pos, state);
        for (c_1514_x blockpos : railstate.n_1700_B()) {
            K_4074_S blockstate = worldIn.getBlockState(blockpos);
            blockstate.n_1700_B(worldIn, blockpos, blockstate.J_1907_R(), pos, false);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(worldIn, pos, this.n_1700_B(state, worldIn, pos, isMoving));
        }
    }

    @Override
    public v_3760_Q<w_801_N> t_148_a() {
        return Q_4569_t;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        if (blockState.R_4764_Y(M_182_A).booleanValue()) {
            List<z_2326_J> list = this.n_1700_B(worldIn, pos, z_2326_J.class, (Predicate<N_4263_v>)null);
            if (!list.isEmpty()) {
                return list.get(0).Y_259_p().u_1723_Y();
            }
            List<y_4319_k> list1 = this.n_1700_B(worldIn, pos, y_4319_k.class, I_408_V.G_564_y);
            if (!list1.isEmpty()) {
                return a_2900_S.J_1907_R((Container)((Object)list1.get(0)));
            }
        }
        return 0;
    }

    protected <T extends y_4319_k> List<T> n_1700_B(b_4507_u worldIn, c_1514_x pos, Class<T> cartType, @Nullable Predicate<N_4263_v> filter) {
        return worldIn.n_1700_B(cartType, this.n_1700_B(pos), filter);
    }

    private I_4817_s n_1700_B(c_1514_x pos) {
        double d0 = 0.2;
        return new I_4817_s((double)pos.getX() + 0.2, pos.getY(), (double)pos.getZ() + 0.2, (double)(pos.getX() + 1) - 0.2, (double)(pos.getY() + 1) - 0.2, (double)(pos.getZ() + 1) - 0.2);
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
        builder.n_1700_B(Q_4569_t, M_182_A);
    }
}


