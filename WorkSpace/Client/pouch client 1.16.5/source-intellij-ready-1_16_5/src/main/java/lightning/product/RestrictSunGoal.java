/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.GoalUtils;
import lightning.product.e_1174_E;
import lightning.product.i_2099_H;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class RestrictSunGoal
extends Goal {
    private final PathfinderMob n_1700_B;

    public RestrictSunGoal(PathfinderMob creature) {
        this.n_1700_B = creature;
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.O_508_d.q_4610_l() && this.n_1700_B.J_1907_R(e_1174_E.u_1723_Y).n_1700_B() && GoalUtils.n_1700_B(this.n_1700_B);
    }

    @Override
    public void R_4764_Y() {
        ((i_2099_H)this.n_1700_B.e_4240_b()).J_1907_R(true);
    }

    @Override
    public void G_564_y() {
        if (GoalUtils.n_1700_B(this.n_1700_B)) {
            ((i_2099_H)this.n_1700_B.e_4240_b()).J_1907_R(false);
        }
    }
}


