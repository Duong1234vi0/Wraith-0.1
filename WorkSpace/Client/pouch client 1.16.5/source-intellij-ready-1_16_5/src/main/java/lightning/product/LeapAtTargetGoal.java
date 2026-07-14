/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.e_2866_D;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class LeapAtTargetGoal
extends Goal {
    private final Z_530_i n_1700_B;
    private r_4811_B J_1907_R;
    private final float R_4764_Y;

    public LeapAtTargetGoal(Z_530_i leapingEntity, float leapMotionYIn) {
        this.n_1700_B = leapingEntity;
        this.R_4764_Y = leapMotionYIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.H_1883_T()) {
            return false;
        }
        this.J_1907_R = this.n_1700_B.t_148_a();
        if (this.J_1907_R == null) {
            return false;
        }
        double d0 = this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R);
        if (!(d0 < 4.0) && !(d0 > 16.0)) {
            if (!this.n_1700_B.M_1641_O()) {
                return false;
            }
            return this.n_1700_B.M_3508_C().nextInt(5) == 0;
        }
        return false;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.M_1641_O();
    }

    @Override
    public void R_4764_Y() {
        e_2866_D vector3d = this.n_1700_B.I_4348_c();
        e_2866_D vector3d1 = new e_2866_D(this.J_1907_R.O_3598_v() - this.n_1700_B.O_3598_v(), 0.0, this.J_1907_R.l_2647_k() - this.n_1700_B.l_2647_k());
        if (vector3d1.v_4262_N() > 1.0E-7) {
            vector3d1 = vector3d1.G_564_y().n_1700_B(0.4).P_1922_E(vector3d.n_1700_B(0.2));
        }
        this.n_1700_B.h_1847_R(vector3d1.J_1907_R, this.R_4764_Y, vector3d1.G_564_y);
    }
}


