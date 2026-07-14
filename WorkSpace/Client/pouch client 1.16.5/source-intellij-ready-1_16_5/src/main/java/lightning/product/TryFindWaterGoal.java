/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.c_1514_x;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class TryFindWaterGoal
extends Goal {
    private final PathfinderMob n_1700_B;

    public TryFindWaterGoal(PathfinderMob creature) {
        this.n_1700_B = creature;
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.M_1641_O() && !this.n_1700_B.O_508_d.getFluidState(this.n_1700_B.b_2312_j()).n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    public void R_4764_Y() {
        z_3539_x blockpos = null;
        for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() - 2.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b() - 2.0), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() - 2.0), u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() + 2.0), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b()), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() + 2.0))) {
            if (!this.n_1700_B.O_508_d.getFluidState(blockpos1).n_1700_B(FluidTags.J_1907_R)) continue;
            blockpos = blockpos1;
            break;
        }
        if (blockpos != null) {
            this.n_1700_B.A_4115_X().n_1700_B(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 1.0);
        }
    }
}


