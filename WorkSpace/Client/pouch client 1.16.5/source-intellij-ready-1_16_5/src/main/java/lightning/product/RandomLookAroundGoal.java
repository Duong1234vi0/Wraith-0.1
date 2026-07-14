/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.Z_530_i;
import lightning.product.Goal;

public class RandomLookAroundGoal
extends Goal {
    private final Z_530_i n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private int G_564_y;

    public RandomLookAroundGoal(Z_530_i entitylivingIn) {
        this.n_1700_B = entitylivingIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.M_3508_C().nextFloat() < 0.02f;
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y >= 0;
    }

    @Override
    public void R_4764_Y() {
        double d0 = Math.PI * 2 * this.n_1700_B.M_3508_C().nextDouble();
        this.J_1907_R = Math.cos(d0);
        this.R_4764_Y = Math.sin(d0);
        this.G_564_y = 20 + this.n_1700_B.M_3508_C().nextInt(20);
    }

    @Override
    public void P_1922_E() {
        --this.G_564_y;
        this.n_1700_B.c_3005_b().n_1700_B(this.n_1700_B.O_3598_v() + this.J_1907_R, this.n_1700_B.X_2048_Y(), this.n_1700_B.l_2647_k() + this.R_4764_Y);
    }
}


