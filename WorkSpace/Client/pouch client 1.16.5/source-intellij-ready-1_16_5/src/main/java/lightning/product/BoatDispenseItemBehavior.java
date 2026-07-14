/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.S_3458_C;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_1462_f;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.BlockSource;

public class BoatDispenseItemBehavior
extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior J_1907_R = new DefaultDispenseItemBehavior();
    private final g_1462_f.J_1907_R R_4764_Y;

    public BoatDispenseItemBehavior(g_1462_f.J_1907_R typeIn) {
        this.R_4764_Y = typeIn;
    }

    @Override
    public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
        double d3;
        b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
        e_3591_l world = source.v_4262_N();
        double d0 = source.n_1700_B() + (double)((float)direction.t_148_a() * 1.125f);
        double d1 = source.J_1907_R() + (double)((float)direction.s_956_w() * 1.125f);
        double d2 = source.R_4764_Y() + (double)((float)direction.u_2550_I() * 1.125f);
        c_1514_x blockpos = source.G_564_y().offset(direction);
        if (world.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R)) {
            d3 = 1.0;
        } else {
            if (!world.getBlockState(blockpos).v_4262_N() || !world.getFluidState(blockpos.down()).n_1700_B(FluidTags.J_1907_R)) {
                return this.J_1907_R.dispense(source, stack);
            }
            d3 = 0.0;
        }
        g_1462_f boatentity = new g_1462_f(world, d0, d1 + d3, d2);
        boatentity.n_1700_B(this.R_4764_Y);
        boatentity.p_178_J = direction.Q_4569_t();
        world.a_(boatentity);
        stack.v_4262_N(1);
        return stack;
    }

    @Override
    protected void n_1700_B(BlockSource source) {
        source.v_4262_N().R_4764_Y(1000, source.G_564_y(), 0);
    }
}


