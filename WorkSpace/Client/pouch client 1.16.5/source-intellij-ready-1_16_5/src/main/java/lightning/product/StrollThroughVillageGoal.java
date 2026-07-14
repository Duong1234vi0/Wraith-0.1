/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.W_3371_U;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.z_2963_s;

public class StrollThroughVillageGoal
extends Goal {
    private final PathfinderMob n_1700_B;
    private final int J_1907_R;
    @Nullable
    private c_1514_x R_4764_Y;

    public StrollThroughVillageGoal(PathfinderMob entity, int p_i50321_2_) {
        this.n_1700_B = entity;
        this.J_1907_R = p_i50321_2_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.H_1883_T()) {
            return false;
        }
        if (this.n_1700_B.O_508_d.q_4610_l()) {
            return false;
        }
        if (this.n_1700_B.M_3508_C().nextInt(this.J_1907_R) != 0) {
            return false;
        }
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        if (!serverworld.R_4764_Y(blockpos, 6)) {
            return false;
        }
        e_2866_D vector3d = W_3371_U.n_1700_B(this.n_1700_B, 15, 7, p_220755_1_ -> -serverworld.J_1907_R(SectionPos.n_1700_B(p_220755_1_)));
        this.R_4764_Y = vector3d == null ? null : new c_1514_x(vector3d);
        return this.R_4764_Y != null;
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y != null && !this.n_1700_B.e_4240_b().M_588_G() && this.n_1700_B.e_4240_b().v_4262_N().equals(this.R_4764_Y);
    }

    @Override
    public void P_1922_E() {
        PathNavigation pathnavigator;
        if (this.R_4764_Y != null && (pathnavigator = this.n_1700_B.e_4240_b()).M_588_G() && !this.R_4764_Y.withinDistance(this.n_1700_B.s_4990_V(), 10.0)) {
            e_2866_D vector3d = e_2866_D.R_4764_Y(this.R_4764_Y);
            e_2866_D vector3d1 = this.n_1700_B.s_4990_V();
            e_2866_D vector3d2 = vector3d1.G_564_y(vector3d);
            vector3d = vector3d2.n_1700_B(0.4).P_1922_E(vector3d);
            e_2866_D vector3d3 = vector3d.G_564_y(vector3d1).G_564_y().n_1700_B(10.0).P_1922_E(vector3d1);
            c_1514_x blockpos = new c_1514_x(vector3d3);
            if (!pathnavigator.n_1700_B((double)(blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, blockpos)).getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 1.0)) {
                this.v_4262_N();
            }
        }
    }

    private void v_4262_N() {
        Random random = this.n_1700_B.M_3508_C();
        c_1514_x blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, this.n_1700_B.b_2312_j().add(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));
        this.n_1700_B.e_4240_b().n_1700_B((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 1.0);
    }
}


