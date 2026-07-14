/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.Fluids;
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
import lightning.product.FluidState;
import lightning.product.e_563_h;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.m_2244_y;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;

public class x_2838_H
extends HorizontalDirectionalBlock
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.Y_259_p;
    public static final e_563_h<m_2244_y> h_1847_R = BlockStateProperties.D_4792_h;
    public static final U_1266_O Q_4569_t = BlockStateProperties.C_2741_M;
    public static final U_1266_O M_182_A = BlockStateProperties.A_4115_X;
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c w_1457_N = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final s_1395_c Y_601_j = T_2915_h.n_1700_B(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c Y_259_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
    protected static final s_1395_c Q_2552_b = T_2915_h.n_1700_B(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);

    protected x_2838_H(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(w_612_n, b_257_Y.R_4764_Y)).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, m_2244_y.J_1907_R)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        if (!state.R_4764_Y(P_4830_p).booleanValue()) {
            return state.R_4764_Y(h_1847_R) == m_2244_y.n_1700_B ? Q_2552_b : Y_259_p;
        }
        switch (state.R_4764_Y(w_612_n)) {
            default: {
                return Y_601_j;
            }
            case G_564_y: {
                return w_1457_N;
            }
            case P_1922_E: {
                return multiplayerClientSuggestionProvider;
            }
            case u_1723_Y: 
        }
        return t_1786_h;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        switch (type) {
            case n_1700_B: {
                return state.R_4764_Y(P_4830_p);
            }
            case J_1907_R: {
                return state.R_4764_Y(M_182_A);
            }
            case R_4764_Y: {
                return state.R_4764_Y(P_4830_p);
            }
        }
        return false;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (this.J_1907_R == Material.z_1737_N) {
            return m_3054_I.R_4764_Y;
        }
        state = (K_4074_S)state.n_1700_B(P_4830_p);
        worldIn.n_1700_B(pos, state, 2);
        if (state.R_4764_Y(M_182_A).booleanValue()) {
            worldIn.M_588_G().n_1700_B(pos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        this.n_1700_B(player, worldIn, pos, state.R_4764_Y(P_4830_p));
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    protected void n_1700_B(@Nullable a_3913_L player, b_4507_u worldIn, c_1514_x pos, boolean isOpened) {
        if (isOpened) {
            int i = this.J_1907_R == Material.z_1737_N ? 1037 : 1007;
            worldIn.n_1700_B(player, i, pos, 0);
        } else {
            int j = this.J_1907_R == Material.z_1737_N ? 1036 : 1013;
            worldIn.n_1700_B(player, j, pos, 0);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag;
        if (!worldIn.Y_259_p && (flag = worldIn.Y_601_j(pos)) != state.R_4764_Y(Q_4569_t)) {
            if (state.R_4764_Y(P_4830_p) != flag) {
                state = (K_4074_S)state.n_1700_B(P_4830_p, flag);
                this.n_1700_B((a_3913_L)null, worldIn, pos, flag);
            }
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(Q_4569_t, flag), 2);
            if (state.R_4764_Y(M_182_A).booleanValue()) {
                worldIn.M_588_G().n_1700_B(pos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate = this.multiplayerClientSuggestionProvider();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        b_257_Y direction = context.getFace();
        blockstate = !context.J_1907_R() && direction.h_1847_R().G_564_y() ? (K_4074_S)((K_4074_S)blockstate.n_1700_B(w_612_n, direction)).n_1700_B(h_1847_R, context.getHitVec().R_4764_Y - (double)context.getPos().getY() > 0.5 ? m_2244_y.n_1700_B : m_2244_y.J_1907_R) : (K_4074_S)((K_4074_S)blockstate.n_1700_B(w_612_n, context.getPlacementHorizontalFacing().u_1723_Y())).n_1700_B(h_1847_R, direction == b_257_Y.J_1907_R ? m_2244_y.J_1907_R : m_2244_y.n_1700_B);
        if (context.getWorld().Y_601_j(context.getPos())) {
            blockstate = (K_4074_S)((K_4074_S)blockstate.n_1700_B(P_4830_p, true)).n_1700_B(Q_4569_t, true);
        }
        return (K_4074_S)blockstate.n_1700_B(M_182_A, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(w_612_n, P_4830_p, h_1847_R, Q_4569_t, M_182_A);
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(M_182_A) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(M_182_A).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}


