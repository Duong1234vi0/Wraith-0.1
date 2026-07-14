/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.GrowingPlantHeadBlock;
import lightning.product.h_4327_W;
import lightning.product.q_4293_E;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.x_268_Y;

public class KelpPlantBlock
extends h_4327_W
implements LiquidBlockContainer {
    protected KelpPlantBlock(q_4293_E.P_1922_E properties) {
        super(properties, b_257_Y.J_1907_R, x_268_Y.J_1907_R(), true);
    }

    @Override
    protected GrowingPlantHeadBlock t_148_a() {
        return (GrowingPlantHeadBlock)a_3742_W.R_1796_s;
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


