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
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;

public class V_1395_p
extends GrowingPlantHeadBlock
implements LiquidBlockContainer {
    protected static final s_1395_c t_1786_h = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);

    protected V_1395_p(q_4293_E.P_1922_E builder) {
        super(builder, b_257_Y.J_1907_R, t_1786_h, true, 0.14);
    }

    @Override
    protected boolean w_1484_f(K_4074_S state) {
        return state.n_1700_B(a_3742_W.c_3005_b);
    }

    @Override
    protected T_2915_h J_1907_R() {
        return a_3742_W.g_24_p;
    }

    @Override
    protected boolean n_1700_B(T_2915_h block) {
        return block != a_3742_W.LevitationControl;
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, Fluid fluidIn) {
        return false;
    }

    @Override
    public boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, FluidState fluidStateIn) {
        return false;
    }

    @Override
    protected int n_1700_B(Random rand) {
        return 1;
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return fluidstate.n_1700_B(FluidTags.J_1907_R) && fluidstate.P_1922_E() == 8 ? super.n_1700_B(context) : null;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return Fluids.R_4764_Y.n_1700_B(false);
    }
}



