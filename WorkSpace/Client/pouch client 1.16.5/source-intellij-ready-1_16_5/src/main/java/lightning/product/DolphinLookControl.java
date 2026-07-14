/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LookControl;
import lightning.product.Z_530_i;
import lightning.product.u_530_F;

public class DolphinLookControl
extends LookControl {
    private final int w_1484_f;

    public DolphinLookControl(Z_530_i p_i48942_1_, int p_i48942_2_) {
        super(p_i48942_1_);
        this.w_1484_f = p_i48942_2_;
    }

    @Override
    public void n_1700_B() {
        if (this.G_564_y) {
            this.G_564_y = false;
            this.n_1700_B.f_3449_S = this.n_1700_B(this.n_1700_B.f_3449_S, this.w_1484_f() + 20.0f, this.J_1907_R);
            this.n_1700_B.f_4016_n = this.n_1700_B(this.n_1700_B.f_4016_n, this.v_4262_N() + 10.0f, this.R_4764_Y);
        } else {
            if (this.n_1700_B.e_4240_b().M_588_G()) {
                this.n_1700_B.f_4016_n = this.n_1700_B(this.n_1700_B.f_4016_n, 0.0f, 5.0f);
            }
            this.n_1700_B.f_3449_S = this.n_1700_B(this.n_1700_B.f_3449_S, this.n_1700_B.C_1162_e, this.J_1907_R);
        }
        float f = u_530_F.v_4262_N(this.n_1700_B.f_3449_S - this.n_1700_B.C_1162_e);
        if (f < (float)(-this.w_1484_f)) {
            this.n_1700_B.C_1162_e -= 4.0f;
        } else if (f > (float)this.w_1484_f) {
            this.n_1700_B.C_1162_e += 4.0f;
        }
    }
}


