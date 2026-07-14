/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_530_i;
import lightning.product.u_530_F;

public class BodyRotationControl {
    private final Z_530_i n_1700_B;
    private int J_1907_R;
    private float R_4764_Y;

    public BodyRotationControl(Z_530_i mob) {
        this.n_1700_B = mob;
    }

    public void n_1700_B() {
        if (this.u_1723_Y()) {
            this.n_1700_B.C_1162_e = this.n_1700_B.p_178_J;
            this.R_4764_Y();
            this.R_4764_Y = this.n_1700_B.f_3449_S;
            this.J_1907_R = 0;
        } else if (this.P_1922_E()) {
            if (Math.abs(this.n_1700_B.f_3449_S - this.R_4764_Y) > 15.0f) {
                this.J_1907_R = 0;
                this.R_4764_Y = this.n_1700_B.f_3449_S;
                this.J_1907_R();
            } else {
                ++this.J_1907_R;
                if (this.J_1907_R > 10) {
                    this.G_564_y();
                }
            }
        }
    }

    private void J_1907_R() {
        this.n_1700_B.C_1162_e = u_530_F.J_1907_R(this.n_1700_B.C_1162_e, this.n_1700_B.f_3449_S, (float)this.n_1700_B.H_1990_U());
    }

    private void R_4764_Y() {
        this.n_1700_B.f_3449_S = u_530_F.J_1907_R(this.n_1700_B.f_3449_S, this.n_1700_B.C_1162_e, (float)this.n_1700_B.H_1990_U());
    }

    private void G_564_y() {
        int i = this.J_1907_R - 10;
        float f = u_530_F.n_1700_B((float)i / 10.0f, 0.0f, 1.0f);
        float f1 = (float)this.n_1700_B.H_1990_U() * (1.0f - f);
        this.n_1700_B.C_1162_e = u_530_F.J_1907_R(this.n_1700_B.C_1162_e, this.n_1700_B.f_3449_S, f1);
    }

    private boolean P_1922_E() {
        return this.n_1700_B.o_3599_Z().isEmpty() || !(this.n_1700_B.o_3599_Z().get(0) instanceof Z_530_i);
    }

    private boolean u_1723_Y() {
        double d1;
        double d0 = this.n_1700_B.O_3598_v() - this.n_1700_B.r_715_M;
        return d0 * d0 + (d1 = this.n_1700_B.l_2647_k() - this.n_1700_B.i_1637_u) * d1 > 2.500000277905201E-7;
    }
}


