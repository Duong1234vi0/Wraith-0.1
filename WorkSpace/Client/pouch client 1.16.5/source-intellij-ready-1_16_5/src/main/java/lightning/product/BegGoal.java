/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.TargetingConditions;
import lightning.product.Goal;
import lightning.product.q_2335_j;
import lightning.product.Items;
import lightning.product.x_1688_C;

public class BegGoal
extends Goal {
    private final q_2335_j n_1700_B;
    private a_3913_L J_1907_R;
    private final b_4507_u R_4764_Y;
    private final float G_564_y;
    private int P_1922_E;
    private final TargetingConditions u_1723_Y;

    public BegGoal(q_2335_j wolf, float minDistance) {
        this.n_1700_B = wolf;
        this.R_4764_Y = wolf.O_508_d;
        this.G_564_y = minDistance;
        this.u_1723_Y = new TargetingConditions().n_1700_B(minDistance).n_1700_B().J_1907_R().G_564_y();
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        this.J_1907_R = this.R_4764_Y.n_1700_B(this.u_1723_Y, this.n_1700_B);
        return this.J_1907_R == null ? false : this.n_1700_B(this.J_1907_R);
    }

    @Override
    public boolean J_1907_R() {
        if (!this.J_1907_R.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) > (double)(this.G_564_y * this.G_564_y)) {
            return false;
        }
        return this.P_1922_E > 0 && this.n_1700_B(this.J_1907_R);
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.w_1457_N(true);
        this.P_1922_E = 40 + this.n_1700_B.M_3508_C().nextInt(40);
    }

    @Override
    public void G_564_y() {
        this.n_1700_B.w_1457_N(false);
        this.J_1907_R = null;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.c_3005_b().n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2048_Y(), this.J_1907_R.l_2647_k(), 10.0f, this.n_1700_B.Z_976_R());
        --this.P_1922_E;
    }

    private boolean n_1700_B(a_3913_L player) {
        for (x_1688_C hand : x_1688_C.values()) {
            Z_1993_T itemstack = player.R_4764_Y(hand);
            if (this.n_1700_B.U_3758_B() && itemstack.J_1907_R() == Items.DamageSourcePredicate) {
                return true;
            }
            if (!this.n_1700_B.u_2550_I(itemstack)) continue;
            return true;
        }
        return false;
    }
}


