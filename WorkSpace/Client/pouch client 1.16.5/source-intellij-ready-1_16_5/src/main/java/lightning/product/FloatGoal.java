/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.FluidTags;
import lightning.product.Z_530_i;
import lightning.product.Goal;

public class FloatGoal
extends Goal {
    private final Z_530_i n_1700_B;

    public FloatGoal(Z_530_i entityIn) {
        this.n_1700_B = entityIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y));
        entityIn.e_4240_b().R_4764_Y(true);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.RowButton() && this.n_1700_B.J_1907_R(FluidTags.J_1907_R) > this.n_1700_B.i_3196_G() || this.n_1700_B.W_3464_O();
    }

    @Override
    public void P_1922_E() {
        if (this.n_1700_B.M_3508_C().nextFloat() < 0.8f) {
            this.n_1700_B.t_4043_B().n_1700_B();
        }
    }
}


