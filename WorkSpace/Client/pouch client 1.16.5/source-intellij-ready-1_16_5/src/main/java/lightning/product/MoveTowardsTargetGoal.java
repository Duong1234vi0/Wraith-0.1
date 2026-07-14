/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class MoveTowardsTargetGoal
extends Goal {
    private final PathfinderMob n_1700_B;
    private r_4811_B J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private double P_1922_E;
    private final double u_1723_Y;
    private final float v_4262_N;

    public MoveTowardsTargetGoal(PathfinderMob creature, double speedIn, float targetMaxDistance) {
        this.n_1700_B = creature;
        this.u_1723_Y = speedIn;
        this.v_4262_N = targetMaxDistance;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        this.J_1907_R = this.n_1700_B.t_148_a();
        if (this.J_1907_R == null) {
            return false;
        }
        if (this.J_1907_R.G_564_y((N_4263_v)this.n_1700_B) > (double)(this.v_4262_N * this.v_4262_N)) {
            return false;
        }
        e_2866_D vector3d = W_3371_U.J_1907_R(this.n_1700_B, 16, 7, this.J_1907_R.s_4990_V());
        if (vector3d == null) {
            return false;
        }
        this.R_4764_Y = vector3d.J_1907_R;
        this.G_564_y = vector3d.R_4764_Y;
        this.P_1922_E = vector3d.G_564_y;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.e_4240_b().M_588_G() && this.J_1907_R.RealmsLongRunningMcoTaskScreen() && this.J_1907_R.G_564_y((N_4263_v)this.n_1700_B) < (double)(this.v_4262_N * this.v_4262_N);
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
    }
}


