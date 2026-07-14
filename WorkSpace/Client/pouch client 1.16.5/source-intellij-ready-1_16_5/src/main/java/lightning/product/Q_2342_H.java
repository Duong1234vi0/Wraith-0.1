/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.Fluids;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.TallSeagrass;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.g_3212_H;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;

public class Q_2342_H
extends BushBlock
implements BonemealableBlock,
LiquidBlockContainer {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);

    protected Q_2342_H(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.G_564_y(worldIn, pos, b_257_Y.J_1907_R) && !state.n_1700_B(a_3742_W.LevitationControl);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return fluidstate.n_1700_B(FluidTags.J_1907_R) && fluidstate.P_1922_E() == 8 ? super.n_1700_B(context) : null;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        K_4074_S blockstate = super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        if (!blockstate.v_4262_N()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return blockstate;
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return true;
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return Fluids.R_4764_Y.n_1700_B(false);
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        K_4074_S blockstate = a_3742_W.LongRunningTask.multiplayerClientSuggestionProvider();
        K_4074_S blockstate1 = (K_4074_S)blockstate.n_1700_B(TallSeagrass.h_1847_R, g_3212_H.n_1700_B);
        c_1514_x blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b)) {
            worldIn.n_1700_B(pos, blockstate, 2);
            worldIn.n_1700_B(blockpos, blockstate1, 2);
        }
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, Fluid fluidIn) {
        return false;
    }

    @Override
    public boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, FluidState fluidStateIn) {
        return false;
    }
}



