/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.g_4621_i;
import lightning.product.Goal;

public class TradeWithPlayerGoal
extends Goal {
    private final g_4621_i n_1700_B;

    public TradeWithPlayerGoal(g_4621_i villager) {
        this.n_1700_B = villager;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (!this.n_1700_B.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (this.n_1700_B.RowButton()) {
            return false;
        }
        if (!this.n_1700_B.M_1641_O()) {
            return false;
        }
        if (this.n_1700_B.Ops) {
            return false;
        }
        a_3913_L playerentity = this.n_1700_B.n_1700_B();
        if (playerentity == null) {
            return false;
        }
        if (this.n_1700_B.G_564_y((N_4263_v)playerentity) > 16.0) {
            return false;
        }
        return playerentity.H_1873_g != null;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().h_1847_R();
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.n_1700_B((a_3913_L)null);
    }
}


