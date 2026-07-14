/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;
import lightning.product.v_4620_e;
import lightning.product.x_268_Y;

public class IronBarsBlock
extends v_4620_e {
    protected IronBarsBlock(q_4293_E.P_1922_E builder) {
        super(1.0f, 1.0f, 16.0f, 16.0f, 16.0f, builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false)).n_1700_B(h_1847_R, false)).n_1700_B(Q_4569_t, false)).n_1700_B(M_182_A, false)).n_1700_B(t_1786_h, false));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_4507_u iblockreader = context.getWorld();
        c_1514_x blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        c_1514_x blockpos1 = blockpos.north();
        c_1514_x blockpos2 = blockpos.south();
        c_1514_x blockpos3 = blockpos.west();
        c_1514_x blockpos4 = blockpos.east();
        K_4074_S blockstate = iblockreader.getBlockState(blockpos1);
        K_4074_S blockstate1 = iblockreader.getBlockState(blockpos2);
        K_4074_S blockstate2 = iblockreader.getBlockState(blockpos3);
        K_4074_S blockstate3 = iblockreader.getBlockState(blockpos4);
        return (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, this.n_1700_B(blockstate, blockstate.G_564_y((BlockGetter)iblockreader, blockpos1, b_257_Y.G_564_y)))).n_1700_B(Q_4569_t, this.n_1700_B(blockstate1, blockstate1.G_564_y((BlockGetter)iblockreader, blockpos2, b_257_Y.R_4764_Y)))).n_1700_B(M_182_A, this.n_1700_B(blockstate2, blockstate2.G_564_y((BlockGetter)iblockreader, blockpos3, b_257_Y.u_1723_Y)))).n_1700_B(h_1847_R, this.n_1700_B(blockstate3, blockstate3.G_564_y((BlockGetter)iblockreader, blockpos4, b_257_Y.P_1922_E)))).n_1700_B(t_1786_h, fluidstate.n_1700_B() == Fluids.R_4764_Y);
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(t_1786_h).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return facing.h_1847_R().G_564_y() ? (K_4074_S)stateIn.n_1700_B((v_3760_Q)multiplayerClientSuggestionProvider.get(facing), this.n_1700_B(facingState, facingState.G_564_y((BlockGetter)worldIn, facingPos, facing.u_1723_Y()))) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public s_1395_c R_4764_Y(K_4074_S state, BlockGetter reader, c_1514_x pos, CollisionContext context) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, K_4074_S adjacentBlockState, b_257_Y side) {
        if (adjacentBlockState.n_1700_B(this)) {
            if (!side.h_1847_R().G_564_y()) {
                return true;
            }
            if (((Boolean)state.R_4764_Y((v_3760_Q)multiplayerClientSuggestionProvider.get(side))).booleanValue() && ((Boolean)adjacentBlockState.R_4764_Y((v_3760_Q)multiplayerClientSuggestionProvider.get(side.u_1723_Y()))).booleanValue()) {
                return true;
            }
        }
        return super.n_1700_B(state, adjacentBlockState, side);
    }

    public final boolean n_1700_B(K_4074_S state, boolean solidSide) {
        T_2915_h block = state.J_1907_R();
        return !IronBarsBlock.R_4764_Y(block) && solidSide || block instanceof IronBarsBlock || block.n_1700_B(BlockTags.x_607_J);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, M_182_A, Q_4569_t, t_1786_h);
    }
}


