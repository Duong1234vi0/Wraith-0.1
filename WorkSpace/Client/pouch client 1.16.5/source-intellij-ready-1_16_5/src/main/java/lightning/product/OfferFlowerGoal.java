/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.D_2364_U;
import lightning.product.L_2225_p;
import lightning.product.TargetingConditions;
import lightning.product.Goal;

public class OfferFlowerGoal
extends Goal {
    private static final TargetingConditions n_1700_B = new TargetingConditions().n_1700_B(6.0).J_1907_R().n_1700_B();
    private final D_2364_U J_1907_R;
    private L_2225_p R_4764_Y;
    private int G_564_y;

    public OfferFlowerGoal(D_2364_U ironGolemIn) {
        this.J_1907_R = ironGolemIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        if (!this.J_1907_R.O_508_d.q_4610_l()) {
            return false;
        }
        if (this.J_1907_R.M_3508_C().nextInt(8000) != 0) {
            return false;
        }
        this.R_4764_Y = this.J_1907_R.O_508_d.n_1700_B(L_2225_p.class, n_1700_B, this.J_1907_R, this.J_1907_R.O_3598_v(), this.J_1907_R.X_2960_b(), this.J_1907_R.l_2647_k(), this.J_1907_R.i_601_W().grow(6.0, 2.0, 6.0));
        return this.R_4764_Y != null;
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y > 0;
    }

    @Override
    public void R_4764_Y() {
        this.G_564_y = 400;
        this.J_1907_R.w_1457_N(true);
    }

    @Override
    public void G_564_y() {
        this.J_1907_R.w_1457_N(false);
        this.R_4764_Y = null;
    }

    @Override
    public void P_1922_E() {
        this.J_1907_R.c_3005_b().n_1700_B(this.R_4764_Y, 30.0f, 30.0f);
        --this.G_564_y;
    }
}


