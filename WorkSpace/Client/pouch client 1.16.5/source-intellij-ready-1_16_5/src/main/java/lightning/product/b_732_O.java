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
import lightning.product.P_11_z;
import lightning.product.V_3354_l;
import lightning.product.Z_1164_j;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.u_530_F;
import lightning.product.z_2963_s;
import lightning.product.AbstractDragonPhaseInstance;

public class b_732_O
extends AbstractDragonPhaseInstance {
    private static final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(64.0);
    private b_1722_e R_4764_Y;
    private e_2866_D G_564_y;
    private boolean P_1922_E;

    public b_732_O(b_2971_b dragonIn) {
        super(dragonIn);
    }

    public Z_1164_j<b_732_O> G_564_y() {
        return Z_1164_j.n_1700_B;
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
    public void R_4764_Y() {
        this.R_4764_Y = null;
        this.G_564_y = null;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.G_564_y;
    }

    private void s_956_w() {
        if (this.R_4764_Y != null && this.R_4764_Y.R_4764_Y()) {
            int i;
            c_1514_x blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, new c_1514_x(EndPodiumFeature.n_1700_B));
            int n = i = this.n_1700_B.h_1640_b() == null ? 0 : this.n_1700_B.h_1640_b().R_4764_Y();
            if (this.n_1700_B.M_3508_C().nextInt(i + 3) == 0) {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.R_4764_Y);
                return;
            }
            double d0 = 64.0;
            a_3913_L playerentity = this.n_1700_B.O_508_d.n_1700_B(J_1907_R, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            if (playerentity != null) {
                d0 = blockpos.distanceSq(playerentity.s_4990_V(), true) / 512.0;
            }
            if (!(playerentity == null || playerentity.C_415_h.n_1700_B || this.n_1700_B.M_3508_C().nextInt(u_530_F.n_1700_B((int)d0) + 2) != 0 && this.n_1700_B.M_3508_C().nextInt(i + 2) != 0)) {
                this.n_1700_B(playerentity);
                return;
            }
        }
        if (this.R_4764_Y == null || this.R_4764_Y.R_4764_Y()) {
            int j;
            int k = j = this.n_1700_B.w_1484_f();
            if (this.n_1700_B.M_3508_C().nextInt(8) == 0) {
                this.P_1922_E = !this.P_1922_E;
                k = j + 6;
            }
            k = this.P_1922_E ? ++k : --k;
            if (this.n_1700_B.h_1640_b() != null && this.n_1700_B.h_1640_b().R_4764_Y() >= 0) {
                if ((k %= 12) < 0) {
                    k += 12;
                }
            } else {
                k -= 12;
                k &= 7;
                k += 12;
            }
            this.R_4764_Y = this.n_1700_B.n_1700_B(j, k, (D_1436_R)null);
            if (this.R_4764_Y != null) {
                this.R_4764_Y.n_1700_B();
            }
        }
        this.u_2550_I();
    }

    private void n_1700_B(a_3913_L player) {
        this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.J_1907_R);
        this.n_1700_B.y_4642_Y().J_1907_R(Z_1164_j.J_1907_R).n_1700_B(player);
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

    @Override
    public void n_1700_B(V_3354_l crystal, c_1514_x pos, P_11_z dmgSrc, @Nullable a_3913_L plyr) {
        if (plyr != null && !plyr.C_415_h.n_1700_B) {
            this.n_1700_B(plyr);
        }
    }
}


