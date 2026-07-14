/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_563_h;
import lightning.product.g_3212_H;
import lightning.product.DoublePlantBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;

public class TallSeagrass
extends DoublePlantBlock
implements LiquidBlockContainer {
    public static final e_563_h<g_3212_H> h_1847_R = DoublePlantBlock.P_4830_p;
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    public TallSeagrass(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.G_564_y(worldIn, pos, b_257_Y.J_1907_R) && !state.n_1700_B(a_3742_W.LevitationControl);
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(a_3742_W.RowButton);
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate;
        K_4074_S blockstate = super.n_1700_B(context);
        if (blockstate != null && (fluidstate = context.getWorld().getFluidState(context.getPos().up())).n_1700_B(FluidTags.J_1907_R) && fluidstate.P_1922_E() == 8) {
            return blockstate;
        }
        return null;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        if (state.R_4764_Y(h_1847_R) == g_3212_H.n_1700_B) {
            K_4074_S blockstate = worldIn.getBlockState(pos.down());
            return blockstate.n_1700_B(this) && blockstate.R_4764_Y(h_1847_R) == g_3212_H.J_1907_R;
        }
        FluidState fluidstate = worldIn.getFluidState(pos);
        return super.n_1700_B(state, worldIn, pos) && fluidstate.n_1700_B(FluidTags.J_1907_R) && fluidstate.P_1922_E() == 8;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return Fluids.R_4764_Y.n_1700_B(false);
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



