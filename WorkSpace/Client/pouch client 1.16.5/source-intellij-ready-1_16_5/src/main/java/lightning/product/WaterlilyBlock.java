/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.g_1462_f;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.Material;

public class WaterlilyBlock
extends BushBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 1.5, 15.0);

    protected WaterlilyBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        super.n_1700_B(state, worldIn, pos, entityIn);
        if (worldIn instanceof e_3591_l && entityIn instanceof g_1462_f) {
            worldIn.n_1700_B(new c_1514_x(pos), true, entityIn);
        }
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        FluidState fluidstate = worldIn.getFluidState(pos);
        FluidState fluidstate1 = worldIn.getFluidState(pos.up());
        return (fluidstate.n_1700_B() == Fluids.R_4764_Y || state.R_4764_Y() == Material.e_4240_b) && fluidstate1.n_1700_B() == Fluids.n_1700_B;
    }
}


