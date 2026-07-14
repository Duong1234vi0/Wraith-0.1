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
import lightning.product.b_1722_e;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.z_2963_s;
import lightning.product.AbstractDragonPhaseInstance;

public class d_218_Y
extends AbstractDragonPhaseInstance {
    private boolean J_1907_R;
    private b_1722_e R_4764_Y;
    private e_2866_D G_564_y;

    public d_218_Y(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void J_1907_R() {
        if (!this.J_1907_R && this.R_4764_Y != null) {
            c_1514_x blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B);
            if (!blockpos.withinDistance(this.n_1700_B.s_4990_V(), 10.0)) {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
            }
        } else {
            this.J_1907_R = false;
            this.s_956_w();
        }
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = true;
        this.R_4764_Y = null;
        this.G_564_y = null;
    }

    private void s_956_w() {
        int i = this.n_1700_B.w_1484_f();
        e_2866_D vector3d = this.n_1700_B.G_564_y(1.0f);
        int j = this.n_1700_B.M_182_A(-vector3d.J_1907_R * 40.0, 105.0, -vector3d.G_564_y * 40.0);
        if (this.n_1700_B.h_1640_b() != null && this.n_1700_B.h_1640_b().R_4764_Y() > 0) {
            if ((j %= 12) < 0) {
                j += 12;
            }
        } else {
            j -= 12;
            j &= 7;
            j += 12;
        }
        this.R_4764_Y = this.n_1700_B.n_1700_B(i, j, (D_1436_R)null);
        this.u_2550_I();
    }

    private void u_2550_I() {
        if (this.R_4764_Y != null) {
            this.R_4764_Y.n_1700_B();
            if (!this.R_4764_Y.R_4764_Y()) {
                double d0;
                c_1514_x vector3i = this.R_4764_Y.v_4262_N();
                this.R_4764_Y.n_1700_B();
                while ((d0 = (double)((float)vector3i.getY() + this.n_1700_B.M_3508_C().nextFloat() * 20.0f)) < (double)vector3i.getY()) {
                }
                this.G_564_y = new e_2866_D(vector3i.getX(), d0, vector3i.getZ());
            }
        }
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.G_564_y;
    }

    public Z_1164_j<d_218_Y> G_564_y() {
        return Z_1164_j.P_1922_E;
    }
}


