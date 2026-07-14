/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DispenseItemBehavior;
import lightning.product.S_3458_C;
import lightning.product.Position;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.n_1494_c;
import lightning.product.BlockSource;

public class DefaultDispenseItemBehavior
implements DispenseItemBehavior {
    @Override
    public final Z_1993_T dispense(BlockSource p_dispense_1_, Z_1993_T p_dispense_2_) {
        Z_1993_T itemstack = this.n_1700_B(p_dispense_1_, p_dispense_2_);
        this.n_1700_B(p_dispense_1_);
        this.n_1700_B(p_dispense_1_, p_dispense_1_.P_1922_E().R_4764_Y(S_3458_C.P_4830_p));
        return itemstack;
    }

    protected Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
        b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
        Position iposition = S_3458_C.n_1700_B(source);
        Z_1993_T itemstack = stack.n_1700_B(1);
        DefaultDispenseItemBehavior.n_1700_B(source.v_4262_N(), itemstack, 6, direction, iposition);
        return stack;
    }

    public static void n_1700_B(b_4507_u worldIn, Z_1993_T stack, int speed, b_257_Y facing, Position position) {
        double d0 = position.n_1700_B();
        double d1 = position.J_1907_R();
        double d2 = position.R_4764_Y();
        d1 = facing.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? (d1 -= 0.125) : (d1 -= 0.15625);
        n_1494_c itementity = new n_1494_c(worldIn, d0, d1, d2, stack);
        double d3 = worldIn.w_1457_N.nextDouble() * 0.1 + 0.2;
        itementity.h_1847_R(worldIn.w_1457_N.nextGaussian() * (double)0.0075f * (double)speed + (double)facing.t_148_a() * d3, worldIn.w_1457_N.nextGaussian() * (double)0.0075f * (double)speed + (double)0.2f, worldIn.w_1457_N.nextGaussian() * (double)0.0075f * (double)speed + (double)facing.u_2550_I() * d3);
        worldIn.a_(itementity);
    }

    protected void n_1700_B(BlockSource source) {
        source.v_4262_N().R_4764_Y(1000, source.G_564_y(), 0);
    }

    protected void n_1700_B(BlockSource source, b_257_Y facingIn) {
        source.v_4262_N().R_4764_Y(2000, source.G_564_y(), facingIn.R_4764_Y());
    }
}


