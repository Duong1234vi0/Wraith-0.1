/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;

public class FenceGateBlock
extends HorizontalDirectionalBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.Y_259_p;
    public static final U_1266_O h_1847_R = BlockStateProperties.C_2741_M;
    public static final U_1266_O Q_4569_t = BlockStateProperties.t_1786_h;
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 6.0, 16.0, 13.0, 10.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(6.0, 0.0, 0.0, 10.0, 13.0, 16.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);
    protected static final s_1395_c Q_2552_b = x_268_Y.n_1700_B(T_2915_h.n_1700_B(0.0, 5.0, 7.0, 2.0, 16.0, 9.0), T_2915_h.n_1700_B(14.0, 5.0, 7.0, 16.0, 16.0, 9.0));
    protected static final s_1395_c C_2741_M = x_268_Y.n_1700_B(T_2915_h.n_1700_B(7.0, 5.0, 0.0, 9.0, 16.0, 2.0), T_2915_h.n_1700_B(7.0, 5.0, 14.0, 9.0, 16.0, 16.0));
    protected static final s_1395_c k_2293_S = x_268_Y.n_1700_B(T_2915_h.n_1700_B(0.0, 2.0, 7.0, 2.0, 13.0, 9.0), T_2915_h.n_1700_B(14.0, 2.0, 7.0, 16.0, 13.0, 9.0));
    protected static final s_1395_c q_2307_F = x_268_Y.n_1700_B(T_2915_h.n_1700_B(7.0, 2.0, 0.0, 9.0, 13.0, 2.0), T_2915_h.n_1700_B(7.0, 2.0, 14.0, 9.0, 13.0, 16.0));

    public FenceGateBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (state.R_4764_Y(Q_4569_t).booleanValue()) {
            return state.R_4764_Y(w_612_n).h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? w_1457_N : multiplayerClientSuggestionProvider;
        }
        return state.R_4764_Y(w_612_n).h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? t_1786_h : M_182_A;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        b_257_Y.n_1700_B direction$axis = facing.h_1847_R();
        if (stateIn.R_4764_Y(w_612_n).v_4262_N().h_1847_R() != direction$axis) {
            return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        boolean flag = this.w_1484_f(facingState) || this.w_1484_f(worldIn.getBlockState(currentPos.offset(facing.u_1723_Y())));
        return (K_4074_S)stateIn.n_1700_B(Q_4569_t, flag);
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            return x_268_Y.n_1700_B();
        }
        return state.R_4764_Y(w_612_n).h_1847_R() == b_257_Y.n_1700_B.R_4764_Y ? Y_601_j : Y_259_p;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        if (state.R_4764_Y(Q_4569_t).booleanValue()) {
            return state.R_4764_Y(w_612_n).h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? q_2307_F : k_2293_S;
        }
        return state.R_4764_Y(w_612_n).h_1847_R() == b_257_Y.n_1700_B.n_1700_B ? C_2741_M : Q_2552_b;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return state.R_4764_Y(P_4830_p);
            }
            case J_1907_R: {
                return false;
            }
            case R_4764_Y: {
                return state.R_4764_Y(P_4830_p);
            }
        }
        return false;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u world = context.getWorld();
        c_1514_x blockpos = context.getPos();
        boolean flag = world.Y_601_j(blockpos);
        b_257_Y direction = context.getPlacementHorizontalFacing();
        b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
        boolean flag1 = direction$axis == b_257_Y.n_1700_B.R_4764_Y && (this.w_1484_f(world.getBlockState(blockpos.west())) || this.w_1484_f(world.getBlockState(blockpos.east()))) || direction$axis == b_257_Y.n_1700_B.n_1700_B && (this.w_1484_f(world.getBlockState(blockpos.north())) || this.w_1484_f(world.getBlockState(blockpos.south())));
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(w_612_n, direction)).n_1700_B(P_4830_p, flag)).n_1700_B(h_1847_R, flag)).n_1700_B(Q_4569_t, flag1);
    }

    private boolean w_1484_f(K_4074_S state) {
        return state.J_1907_R().n_1700_B(BlockTags.x_607_J);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            state = (K_4074_S)state.n_1700_B(P_4830_p, false);
            worldIn.n_1700_B(pos, state, 10);
        } else {
            b_257_Y direction = player.o_2767_H();
            if (state.R_4764_Y(w_612_n) == direction.u_1723_Y()) {
                state = (K_4074_S)state.n_1700_B(w_612_n, direction);
            }
            state = (K_4074_S)state.n_1700_B(P_4830_p, true);
            worldIn.n_1700_B(pos, state, 10);
        }
        worldIn.n_1700_B(player, state.R_4764_Y(P_4830_p) != false ? 1008 : 1014, pos, 0);
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (!worldIn.Y_259_p) {
            boolean flag = worldIn.Y_601_j(pos);
            if (state.R_4764_Y(h_1847_R) != flag) {
                worldIn.n_1700_B(pos, (K_4074_S)((K_4074_S)state.n_1700_B(h_1847_R, flag)).n_1700_B(P_4830_p, flag), 2);
                if (state.R_4764_Y(P_4830_p) != flag) {
                    worldIn.n_1700_B((a_3913_L)null, flag ? 1008 : 1014, pos, 0);
                }
            }
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p, h_1847_R, Q_4569_t);
    }

    public static boolean n_1700_B(K_4074_S state, b_257_Y direction) {
        return state.R_4764_Y(w_612_n).h_1847_R() == direction.v_4262_N().h_1847_R();
    }
}


