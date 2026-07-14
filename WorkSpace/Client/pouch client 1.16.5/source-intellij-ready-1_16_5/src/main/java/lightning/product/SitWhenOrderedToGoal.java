/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.C_3622_I;
import lightning.product.N_4263_v;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class SitWhenOrderedToGoal
extends Goal {
    private final C_3622_I n_1700_B;

    public SitWhenOrderedToGoal(C_3622_I entityIn) {
        this.n_1700_B = entityIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B.D_3612_q();
    }

    @Override
    public boolean n_1700_B() {
        if (!this.n_1700_B.U_3758_B()) {
            return false;
        }
        if (this.n_1700_B.S_980_j()) {
            return false;
        }
        if (!this.n_1700_B.M_1641_O()) {
            return false;
        }
        r_4811_B livingentity = this.n_1700_B.A_1306_N();
        if (livingentity == null) {
            return true;
        }
        return this.n_1700_B.G_564_y((N_4263_v)livingentity) < 144.0 && livingentity.q_817_e() != null ? false : this.n_1700_B.D_3612_q();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().h_1847_R();
        this.n_1700_B.C_2741_M(true);
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.C_2741_M(false);
    }
}


