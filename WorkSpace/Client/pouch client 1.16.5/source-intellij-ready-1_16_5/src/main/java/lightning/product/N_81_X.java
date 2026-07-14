/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.h_4152_b;
import lightning.product.DirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_268_Y;
import lightning.product.y_1539_W;

public class N_81_X
extends DirectionalBlock {
    public static final e_563_h<y_1539_W> h_1847_R = BlockStateProperties.R_3908_n;
    public static final U_1266_O Q_4569_t = BlockStateProperties.k_2293_S;
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(12.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 4.0, 16.0, 16.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 12.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 4.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 12.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    protected static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(6.0, -4.0, 6.0, 10.0, 12.0, 10.0);
    protected static final s_1395_c C_2741_M = T_2915_h.n_1700_B(6.0, 4.0, 6.0, 10.0, 20.0, 10.0);
    protected static final s_1395_c k_2293_S = T_2915_h.n_1700_B(6.0, 6.0, -4.0, 10.0, 10.0, 12.0);
    protected static final s_1395_c q_2307_F = T_2915_h.n_1700_B(6.0, 6.0, 4.0, 10.0, 10.0, 20.0);
    protected static final s_1395_c Z_875_P = T_2915_h.n_1700_B(-4.0, 6.0, 6.0, 12.0, 10.0, 10.0);
    protected static final s_1395_c c_3005_b = T_2915_h.n_1700_B(4.0, 6.0, 6.0, 20.0, 10.0, 10.0);
    protected static final s_1395_c H_2857_Y = T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 12.0, 10.0);
    protected static final s_1395_c A_4115_X = T_2915_h.n_1700_B(6.0, 4.0, 6.0, 10.0, 16.0, 10.0);
    protected static final s_1395_c e_4240_b = T_2915_h.n_1700_B(6.0, 6.0, 0.0, 10.0, 10.0, 12.0);
    protected static final s_1395_c n_3318_d = T_2915_h.n_1700_B(6.0, 6.0, 4.0, 10.0, 10.0, 16.0);
    protected static final s_1395_c d_2427_y = T_2915_h.n_1700_B(0.0, 6.0, 6.0, 12.0, 10.0, 10.0);
    protected static final s_1395_c z_1737_N = T_2915_h.n_1700_B(4.0, 6.0, 6.0, 16.0, 10.0, 10.0);
    private static final s_1395_c[] v_4276_D = N_81_X.n_1700_B(true);
    private static final s_1395_c[] d_2461_k = N_81_X.n_1700_B(false);

    private static s_1395_c[] n_1700_B(boolean extended) {
        return (s_1395_c[])Arrays.stream(b_257_Y.values()).map(direction -> N_81_X.n_1700_B(direction, extended)).toArray(s_1395_c[]::new);
    }

    private static s_1395_c n_1700_B(b_257_Y direction, boolean shortArm) {
        switch (direction) {
            default: {
                return x_268_Y.n_1700_B(Y_259_p, shortArm ? A_4115_X : C_2741_M);
            }
            case J_1907_R: {
                return x_268_Y.n_1700_B(Y_601_j, shortArm ? H_2857_Y : Q_2552_b);
            }
            case R_4764_Y: {
                return x_268_Y.n_1700_B(w_1457_N, shortArm ? n_3318_d : q_2307_F);
            }
            case G_564_y: {
                return x_268_Y.n_1700_B(multiplayerClientSuggestionProvider, shortArm ? e_4240_b : k_2293_S);
            }
            case P_1922_E: {
                return x_268_Y.n_1700_B(t_1786_h, shortArm ? z_1737_N : c_3005_b);
            }
            case u_1723_Y: 
        }
        return x_268_Y.n_1700_B(M_182_A, shortArm ? d_2427_y : Z_875_P);
    }

    public N_81_X(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, y_1539_W.n_1700_B)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return (state.R_4764_Y(Q_4569_t) != false ? v_4276_D : d_2461_k)[state.R_4764_Y(P_4830_p).ordinal()];
    }

    private boolean n_1700_B(K_4074_S baseState, K_4074_S extendedState) {
        T_2915_h block = baseState.R_4764_Y(h_1847_R) == y_1539_W.n_1700_B ? a_3742_W.j_2266_I : a_3742_W.RealmsDefaultUncaughtExceptionHandler;
        return extendedState.n_1700_B(block) && extendedState.R_4764_Y(h_4152_b.h_1847_R) != false && extendedState.R_4764_Y(P_4830_p) == baseState.R_4764_Y(P_4830_p);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        c_1514_x blockpos;
        if (!worldIn.Y_259_p && player.C_415_h.G_564_y && this.n_1700_B(state, worldIn.getBlockState(blockpos = pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y())))) {
            worldIn.J_1907_R(blockpos, false);
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
            c_1514_x blockpos = pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y());
            if (this.n_1700_B(state, worldIn.getBlockState(blockpos))) {
                worldIn.J_1907_R(blockpos, true);
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing.u_1723_Y() == stateIn.R_4764_Y(P_4830_p) && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y()));
        return this.n_1700_B(state, blockstate) || blockstate.n_1700_B(a_3742_W.O_2151_c) && blockstate.R_4764_Y(P_4830_p) == state.R_4764_Y(P_4830_p);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (state.n_1700_B((T_1316_M)worldIn, pos)) {
            c_1514_x blockpos = pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y());
            worldIn.getBlockState(blockpos).n_1700_B(worldIn, blockpos, blockIn, fromPos, false);
        }
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(state.R_4764_Y(h_1847_R) == y_1539_W.J_1907_R ? a_3742_W.RealmsDefaultUncaughtExceptionHandler : a_3742_W.j_2266_I);
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
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


