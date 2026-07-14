/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.t_3546_P;
import lightning.product.w_748_f;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;
import lightning.product.Hopper;

public class c_1788_D
extends BaseEntityBlock {
    public static final DirectionProperty P_4830_p = BlockStateProperties.T_2506_i;
    public static final U_1266_O h_1847_R = BlockStateProperties.u_1723_Y;
    private static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 10.0, 0.0, 16.0, 16.0, 16.0);
    private static final s_1395_c M_182_A = T_2915_h.n_1700_B(4.0, 4.0, 4.0, 12.0, 10.0, 12.0);
    private static final s_1395_c t_1786_h = x_268_Y.n_1700_B(M_182_A, Q_4569_t);
    private static final s_1395_c multiplayerClientSuggestionProvider = x_268_Y.n_1700_B(t_1786_h, Hopper.n_1700_B, BooleanOp.P_1922_E);
    private static final s_1395_c w_1457_N = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 4.0, 10.0));
    private static final s_1395_c Y_601_j = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, T_2915_h.n_1700_B(12.0, 4.0, 6.0, 16.0, 8.0, 10.0));
    private static final s_1395_c Y_259_p = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, T_2915_h.n_1700_B(6.0, 4.0, 0.0, 10.0, 8.0, 4.0));
    private static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, T_2915_h.n_1700_B(6.0, 4.0, 12.0, 10.0, 8.0, 16.0));
    private static final s_1395_c C_2741_M = x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, T_2915_h.n_1700_B(0.0, 4.0, 6.0, 4.0, 8.0, 10.0));
    private static final s_1395_c k_2293_S = Hopper.n_1700_B;
    private static final s_1395_c q_2307_F = x_268_Y.n_1700_B(Hopper.n_1700_B, T_2915_h.n_1700_B(12.0, 8.0, 6.0, 16.0, 10.0, 10.0));
    private static final s_1395_c Z_875_P = x_268_Y.n_1700_B(Hopper.n_1700_B, T_2915_h.n_1700_B(6.0, 8.0, 0.0, 10.0, 10.0, 4.0));
    private static final s_1395_c c_3005_b = x_268_Y.n_1700_B(Hopper.n_1700_B, T_2915_h.n_1700_B(6.0, 8.0, 12.0, 10.0, 10.0, 16.0));
    private static final s_1395_c H_2857_Y = x_268_Y.n_1700_B(Hopper.n_1700_B, T_2915_h.n_1700_B(0.0, 8.0, 6.0, 4.0, 10.0, 10.0));

    public c_1788_D(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.n_1700_B)).n_1700_B(h_1847_R, true));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            case n_1700_B: {
                return w_1457_N;
            }
            case R_4764_Y: {
                return Y_259_p;
            }
            case G_564_y: {
                return Q_2552_b;
            }
            case P_1922_E: {
                return C_2741_M;
            }
            case u_1723_Y: {
                return Y_601_j;
            }
        }
        return multiplayerClientSuggestionProvider;
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        switch (state.R_4764_Y(P_4830_p)) {
            case n_1700_B: {
                return k_2293_S;
            }
            case R_4764_Y: {
                return Z_875_P;
            }
            case G_564_y: {
                return c_3005_b;
            }
            case P_1922_E: {
                return H_2857_Y;
            }
            case u_1723_Y: {
                return q_2307_F;
            }
        }
        return Hopper.n_1700_B;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getFace().u_1723_Y();
        return (K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? b_257_Y.n_1700_B : direction)).n_1700_B(h_1847_R, true);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new w_748_f();
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof w_748_f) {
            ((w_748_f)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof w_748_f) {
            player.n_1700_B((w_748_f)tileentity);
            player.J_1907_R(Stats.s_2632_s);
        }
        return m_3054_I.J_1907_R;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        this.n_1700_B(worldIn, pos, state);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        boolean flag;
        boolean bl = flag = !worldIn.Y_601_j(pos);
        if (flag != state.R_4764_Y(h_1847_R)) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, flag), 4);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof w_748_f) {
                K_3065_y.n_1700_B(worldIn, pos, (Container)((w_748_f)tileentity));
                worldIn.R_4764_Y(pos, this);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return a_2900_S.n_1700_B(worldIn.getTileEntity(pos));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof w_748_f) {
            ((w_748_f)tileentity).n_1700_B(entityIn);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


