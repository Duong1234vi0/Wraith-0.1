/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.EndPodiumFeature;
import lightning.product.Z_1164_j;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.z_2963_s;
import lightning.product.AbstractDragonPhaseInstance;

public class DragonLandingApproachPhase
extends AbstractDragonPhaseInstance {
    private static final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(128.0);
    private b_1722_e R_4764_Y;
    private e_2866_D G_564_y;

    public DragonLandingApproachPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    public Z_1164_j<DragonLandingApproachPhase> G_564_y() {
        return Z_1164_j.R_4764_Y;
    }

    @Override
    public void R_4764_Y() {
        this.R_4764_Y = null;
        this.G_564_y = null;
    }

    @Override
    public void J_1907_R() {
        double d0;
        double d = d0 = this.G_564_y == null ? 0.0 : this.G_564_y.R_4764_Y(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
        if (d0 < 100.0 || d0 > 22500.0 || this.n_1700_B.D_60_a || this.n_1700_B.k_3961_g) {
            this.s_956_w();
        }
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.G_564_y;
    }

    private void s_956_w() {
        if (this.R_4764_Y == null || this.R_4764_Y.R_4764_Y()) {
            int j;
            int i = this.n_1700_B.w_1484_f();
            c_1514_x blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B);
            a_3913_L playerentity = this.n_1700_B.O_508_d.n_1700_B(J_1907_R, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            if (playerentity != null) {
                e_2866_D vector3d = new e_2866_D(playerentity.O_3598_v(), 0.0, playerentity.l_2647_k()).G_564_y();
                j = this.n_1700_B.M_182_A(-vector3d.J_1907_R * 40.0, 105.0, -vector3d.G_564_y * 40.0);
            } else {
                j = this.n_1700_B.M_182_A(40.0, blockpos.getY(), 0.0);
            }
            D_1436_R pathpoint = new D_1436_R(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.R_4764_Y = this.n_1700_B.n_1700_B(i, j, pathpoint);
            if (this.R_4764_Y != null) {
                this.R_4764_Y.n_1700_B();
            }
        }
        this.u_2550_I();
        if (this.R_4764_Y != null && this.R_4764_Y.R_4764_Y()) {
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.G_564_y);
        }
    }

    private void u_2550_I() {
        if (this.R_4764_Y != null && !this.R_4764_Y.R_4764_Y()) {
            double d2;
            c_1514_x vector3i = this.R_4764_Y.v_4262_N();
            this.R_4764_Y.n_1700_B();
            double d0 = vector3i.getX();
            double d1 = vector3i.getZ();
            while ((d2 = (double)((float)vector3i.getY() + this.n_1700_B.M_3508_C().nextFloat() * 20.0f)) < (double)vector3i.getY()) {
            }
            this.G_564_y = new e_2866_D(d0, d2, d1);
        }
    }
}


