/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Map;
import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.PipeBlock;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.l_311_L;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.v_4620_e;

public class E_1708_F
extends T_2915_h {
    public static final U_1266_O P_4830_p = BlockStateProperties.C_2741_M;
    public static final U_1266_O h_1847_R = BlockStateProperties.n_1700_B;
    public static final U_1266_O Q_4569_t = BlockStateProperties.G_564_y;
    public static final U_1266_O M_182_A = PipeBlock.P_4830_p;
    public static final U_1266_O t_1786_h = PipeBlock.h_1847_R;
    public static final U_1266_O multiplayerClientSuggestionProvider = PipeBlock.Q_4569_t;
    public static final U_1266_O w_1457_N = PipeBlock.M_182_A;
    private static final Map<b_257_Y, U_1266_O> Q_2552_b = v_4620_e.multiplayerClientSuggestionProvider;
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 1.0, 0.0, 16.0, 2.5, 16.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private final l_311_L C_2741_M;

    public E_1708_F(l_311_L hook, q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false)).n_1700_B(multiplayerClientSuggestionProvider, false)).n_1700_B(w_1457_N, false));
        this.C_2741_M = hook;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return state.R_4764_Y(h_1847_R) != false ? Y_601_j : Y_259_p;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(M_182_A, this.n_1700_B(iblockreader.getBlockState(blockpos.north()), b_257_Y.R_4764_Y))).n_1700_B(t_1786_h, this.n_1700_B(iblockreader.getBlockState(blockpos.east()), b_257_Y.u_1723_Y))).n_1700_B(multiplayerClientSuggestionProvider, this.n_1700_B(iblockreader.getBlockState(blockpos.south()), b_257_Y.G_564_y))).n_1700_B(w_1457_N, this.n_1700_B(iblockreader.getBlockState(blockpos.west()), b_257_Y.P_1922_E));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing.h_1847_R().G_564_y() ? (K_4074_S)stateIn.n_1700_B(Q_2552_b.get(facing), this.n_1700_B(facingState, facing)) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R())) {
            this.n_1700_B(worldIn, pos, state);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            this.n_1700_B(worldIn, pos, (K_4074_S)state.n_1700_B(P_4830_p, true));
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!worldIn.Y_259_p && !player.A_2714_y().n_1700_B() && player.A_2714_y().J_1907_R() == Items.LightPredicate) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, true), 4);
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        block0: for (b_257_Y direction : new b_257_Y[]{b_257_Y.G_564_y, b_257_Y.P_1922_E}) {
            for (int i = 1; i < 42; ++i) {
                c_1514_x blockpos = pos.offset(direction, i);
                K_4074_S blockstate = worldIn.getBlockState(blockpos);
                if (blockstate.n_1700_B(this.C_2741_M)) {
                    if (blockstate.R_4764_Y(l_311_L.P_4830_p) != direction.u_1723_Y()) continue block0;
                    this.C_2741_M.n_1700_B(worldIn, blockpos, blockstate, false, true, i, state);
                    continue block0;
                }
                if (!blockstate.n_1700_B(this)) continue block0;
            }
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!worldIn.Y_259_p && !state.R_4764_Y(P_4830_p).booleanValue()) {
            this.n_1700_B(worldIn, pos);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (worldIn.getBlockState(pos).R_4764_Y(P_4830_p).booleanValue()) {
            this.n_1700_B(worldIn, pos);
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos);
        boolean flag = blockstate.R_4764_Y(P_4830_p);
        boolean flag1 = false;
        List<N_4263_v> list = worldIn.n_1700_B((N_4263_v)null, blockstate.s_956_w(worldIn, pos).n_1700_B().offset(pos));
        if (!list.isEmpty()) {
            for (N_4263_v entity : list) {
                if (entity.P_2947_S()) continue;
                flag1 = true;
                break;
            }
        }
        if (flag1 != flag) {
            blockstate = (K_4074_S)blockstate.n_1700_B(P_4830_p, flag1);
            worldIn.n_1700_B(pos, blockstate, 3);
            this.n_1700_B(worldIn, pos, blockstate);
        }
        if (flag1) {
            worldIn.u_2550_I().n_1700_B(new c_1514_x(pos), this, 10);
        }
    }

    public boolean n_1700_B(K_4074_S state, b_257_Y direction) {
        T_2915_h block = state.J_1907_R();
        if (block == this.C_2741_M) {
            return state.R_4764_Y(l_311_L.P_4830_p) == direction.u_1723_Y();
        }
        return block == this;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, state.R_4764_Y(multiplayerClientSuggestionProvider))).n_1700_B(t_1786_h, state.R_4764_Y(w_1457_N))).n_1700_B(multiplayerClientSuggestionProvider, state.R_4764_Y(M_182_A))).n_1700_B(w_1457_N, state.R_4764_Y(t_1786_h));
            }
            case G_564_y: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, state.R_4764_Y(t_1786_h))).n_1700_B(t_1786_h, state.R_4764_Y(multiplayerClientSuggestionProvider))).n_1700_B(multiplayerClientSuggestionProvider, state.R_4764_Y(w_1457_N))).n_1700_B(w_1457_N, state.R_4764_Y(M_182_A));
            }
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, state.R_4764_Y(w_1457_N))).n_1700_B(t_1786_h, state.R_4764_Y(M_182_A))).n_1700_B(multiplayerClientSuggestionProvider, state.R_4764_Y(t_1786_h))).n_1700_B(w_1457_N, state.R_4764_Y(multiplayerClientSuggestionProvider));
            }
        }
        return state;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        switch (mirrorIn) {
            case J_1907_R: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(M_182_A, state.R_4764_Y(multiplayerClientSuggestionProvider))).n_1700_B(multiplayerClientSuggestionProvider, state.R_4764_Y(M_182_A));
            }
            case R_4764_Y: {
                return (K_4074_S)((K_4074_S)state.n_1700_B(t_1786_h, state.R_4764_Y(w_1457_N))).n_1700_B(w_1457_N, state.R_4764_Y(t_1786_h));
            }
        }
        return super.n_1700_B(state, mirrorIn);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h, w_1457_N, multiplayerClientSuggestionProvider);
    }
}


