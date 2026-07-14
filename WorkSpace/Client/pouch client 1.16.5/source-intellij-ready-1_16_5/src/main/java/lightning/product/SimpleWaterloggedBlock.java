/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.o_3946_o;
import lightning.product.LiquidBlockContainer;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;

public interface SimpleWaterloggedBlock
extends o_3946_o,
LiquidBlockContainer {
    @Override
    default public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, Fluid fluidIn) {
        return state.R_4764_Y(BlockStateProperties.A_4115_X) == false && fluidIn == Fluids.R_4764_Y;
    }

    @Override
    default public boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, FluidState fluidStateIn) {
        if (!state.R_4764_Y(BlockStateProperties.A_4115_X).booleanValue() && fluidStateIn.n_1700_B() == Fluids.R_4764_Y) {
            if (!worldIn.v_4276_D()) {
                worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(BlockStateProperties.A_4115_X, true), 3);
                worldIn.M_588_G().n_1700_B(pos, fluidStateIn.n_1700_B(), fluidStateIn.n_1700_B().n_1700_B(worldIn));
            }
            return true;
        }
        return false;
    }

    @Override
    default public Fluid J_1907_R(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        if (state.R_4764_Y(BlockStateProperties.A_4115_X).booleanValue()) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(BlockStateProperties.A_4115_X, false), 3);
            return Fluids.R_4764_Y;
        }
        return Fluids.n_1700_B;
    }
}


