/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Attributes;
import lightning.product.MoveControl;
import lightning.product.Z_530_i;
import lightning.product.u_530_F;

public class FlyingMoveControl
extends MoveControl {
    private final int t_148_a;
    private final boolean s_956_w;

    public FlyingMoveControl(Z_530_i p_i225710_1_, int p_i225710_2_, boolean p_i225710_3_) {
        super(p_i225710_1_);
        this.t_148_a = p_i225710_2_;
        this.s_956_w = p_i225710_3_;
    }

    @Override
    public void n_1700_B() {
        if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R) {
            this.w_1484_f = MoveControl.n_1700_B.n_1700_B;
            this.n_1700_B.w_1484_f(true);
            double d0 = this.J_1907_R - this.n_1700_B.O_3598_v();
            double d1 = this.R_4764_Y - this.n_1700_B.X_2960_b();
            double d2 = this.G_564_y - this.n_1700_B.l_2647_k();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < 2.500000277905201E-7) {
                this.n_1700_B.k_2293_S(0.0f);
                this.n_1700_B.C_2741_M(0.0f);
                return;
            }
            float f = (float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f;
            this.n_1700_B.p_178_J = this.n_1700_B(this.n_1700_B.p_178_J, f, 90.0f);
            float f1 = this.n_1700_B.M_1641_O() ? (float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.G_564_y)) : (float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.P_1922_E));
            this.n_1700_B.w_1457_N(f1);
            double d4 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
            float f2 = (float)(-(u_530_F.G_564_y(d1, d4) * 57.2957763671875));
            this.n_1700_B.f_4016_n = this.n_1700_B(this.n_1700_B.f_4016_n, f2, this.t_148_a);
            this.n_1700_B.k_2293_S(d1 > 0.0 ? f1 : -f1);
        } else {
            if (!this.s_956_w) {
                this.n_1700_B.w_1484_f(false);
            }
            this.n_1700_B.k_2293_S(0.0f);
            this.n_1700_B.C_2741_M(0.0f);
        }
    }
}


