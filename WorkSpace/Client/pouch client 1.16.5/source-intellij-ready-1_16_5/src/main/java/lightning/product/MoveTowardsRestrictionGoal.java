/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.W_3371_U;
import lightning.product.e_2866_D;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class MoveTowardsRestrictionGoal
extends Goal {
    private final PathfinderMob n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private double G_564_y;
    private final double P_1922_E;

    public MoveTowardsRestrictionGoal(PathfinderMob creatureIn, double speedIn) {
        this.n_1700_B = creatureIn;
        this.P_1922_E = speedIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.l_1233_K()) {
            return false;
        }
        e_2866_D vector3d = W_3371_U.J_1907_R(this.n_1700_B, 16, 7, e_2866_D.R_4764_Y(this.n_1700_B.z_1333_t()));
        if (vector3d == null) {
            return false;
        }
        this.J_1907_R = vector3d.J_1907_R;
        this.R_4764_Y = vector3d.R_4764_Y;
        this.G_564_y = vector3d.G_564_y;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B.e_4240_b().M_588_G();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
    }
}


