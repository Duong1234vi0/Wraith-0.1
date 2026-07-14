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
import lightning.product.Fluids;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.DirectionProperty;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;

public class C_1985_D
extends T_2915_h
implements SimpleWaterloggedBlock {
    public static final DirectionProperty P_4830_p = HorizontalDirectionalBlock.w_612_n;
    public static final U_1266_O h_1847_R = BlockStateProperties.A_4115_X;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final s_1395_c multiplayerClientSuggestionProvider = T_2915_h.n_1700_B(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);

    protected C_1985_D(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            case R_4764_Y: {
                return multiplayerClientSuggestionProvider;
            }
            case G_564_y: {
                return t_1786_h;
            }
            case P_1922_E: {
                return M_182_A;
            }
        }
        return Q_4569_t;
    }

    private boolean n_1700_B(BlockGetter blockReader, c_1514_x pos, b_257_Y direction) {
        K_4074_S blockstate = blockReader.getBlockState(pos);
        return blockstate.G_564_y(blockReader, pos, direction);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        b_257_Y direction = state.R_4764_Y(P_4830_p);
        return this.n_1700_B((BlockGetter)worldIn, pos.offset(direction.u_1723_Y()), direction);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (facing.u_1723_Y() == stateIn.R_4764_Y(P_4830_p) && !stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
        }
        if (stateIn.R_4764_Y(h_1847_R).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        K_4074_S blockstate;
        if (!context.J_1907_R() && (blockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().u_1723_Y()))).n_1700_B(this) && blockstate.R_4764_Y(P_4830_p) == context.getFace()) {
            return null;
        }
        K_4074_S blockstate1 = this.multiplayerClientSuggestionProvider();
        b_4507_u iworldreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for (b_257_Y direction : context.G_564_y()) {
            if (!direction.h_1847_R().G_564_y() || !(blockstate1 = (K_4074_S)blockstate1.n_1700_B(P_4830_p, direction.u_1723_Y())).n_1700_B((T_1316_M)iworldreader, blockpos)) continue;
            return (K_4074_S)blockstate1.n_1700_B(h_1847_R, fluidstate.n_1700_B() == Fluids.R_4764_Y);
        }
        return null;
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
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(h_1847_R) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }
}


