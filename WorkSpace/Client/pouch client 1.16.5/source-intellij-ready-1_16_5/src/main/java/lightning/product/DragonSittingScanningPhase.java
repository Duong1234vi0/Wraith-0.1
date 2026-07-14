/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1164_j;
import lightning.product.AbstractDragonSittingPhase;
import lightning.product.a_3913_L;
import lightning.product.b_2971_b;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.u_530_F;

public class DragonSittingScanningPhase
extends AbstractDragonSittingPhase {
    private static final TargetingConditions J_1907_R = new TargetingConditions().n_1700_B(150.0);
    private final TargetingConditions R_4764_Y = new TargetingConditions().n_1700_B(20.0).n_1700_B(p_221114_1_ -> Math.abs(p_221114_1_.X_2960_b() - dragonIn.X_2960_b()) <= 10.0);
    private int G_564_y;

    public DragonSittingScanningPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void J_1907_R() {
        ++this.G_564_y;
        a_3913_L livingentity = this.n_1700_B.O_508_d.n_1700_B(this.R_4764_Y, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
        if (livingentity != null) {
            if (this.G_564_y > 25) {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.w_1484_f);
            } else {
                e_2866_D vector3d = new e_2866_D(livingentity.O_3598_v() - this.n_1700_B.O_3598_v(), 0.0, livingentity.l_2647_k() - this.n_1700_B.l_2647_k()).G_564_y();
                e_2866_D vector3d1 = new e_2866_D(u_530_F.n_1700_B(this.n_1700_B.p_178_J * ((float)Math.PI / 180)), 0.0, -u_530_F.J_1907_R(this.n_1700_B.p_178_J * ((float)Math.PI / 180))).G_564_y();
                float f = (float)vector3d1.J_1907_R(vector3d);
                float f1 = (float)(Math.acos(f) * 57.2957763671875) + 0.5f;
                if (f1 < 0.0f || f1 > 10.0f) {
                    float f2;
                    double d0 = livingentity.O_3598_v() - this.n_1700_B.h_1847_R.O_3598_v();
                    double d1 = livingentity.l_2647_k() - this.n_1700_B.h_1847_R.l_2647_k();
                    double d2 = u_530_F.n_1700_B(u_530_F.u_1723_Y(180.0 - u_530_F.G_564_y(d0, d1) * 57.2957763671875 - (double)this.n_1700_B.p_178_J), -100.0, 100.0);
                    this.n_1700_B.w_1457_N *= 0.8f;
                    float f3 = f2 = u_530_F.n_1700_B(d0 * d0 + d1 * d1) + 1.0f;
                    if (f2 > 40.0f) {
                        f2 = 40.0f;
                    }
                    this.n_1700_B.w_1457_N = (float)((double)this.n_1700_B.w_1457_N + d2 * (double)(0.7f / f2 / f3));
                    this.n_1700_B.p_178_J += this.n_1700_B.w_1457_N;
                }
            }
        } else if (this.G_564_y >= 100) {
            livingentity = this.n_1700_B.O_508_d.n_1700_B(J_1907_R, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.P_1922_E);
            if (livingentity != null) {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.t_148_a);
                this.n_1700_B.y_4642_Y().J_1907_R(Z_1164_j.t_148_a).n_1700_B(new e_2866_D(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k()));
            }
        }
    }

    @Override
    public void R_4764_Y() {
        this.G_564_y = 0;
    }

    public Z_1164_j<DragonSittingScanningPhase> G_564_y() {
        return Z_1164_j.v_4262_N;
    }
}


