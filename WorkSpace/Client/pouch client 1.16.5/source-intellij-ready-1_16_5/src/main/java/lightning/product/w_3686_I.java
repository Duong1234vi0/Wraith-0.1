/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.ShoulderRidingEntity;
import lightning.product.Goal;

public class w_3686_I
extends Goal {
    private final ShoulderRidingEntity n_1700_B;
    private B_4088_l J_1907_R;
    private boolean R_4764_Y;

    public w_3686_I(ShoulderRidingEntity entityIn) {
        this.n_1700_B = entityIn;
    }

    @Override
    public boolean n_1700_B() {
        B_4088_l serverplayerentity = (B_4088_l)this.n_1700_B.A_1306_N();
        boolean flag = serverplayerentity != null && !serverplayerentity.d_2461_k() && !serverplayerentity.C_415_h.J_1907_R && !serverplayerentity.RowButton();
        return !this.n_1700_B.D_3612_q() && flag && this.n_1700_B.J_3635_s();
    }

    @Override
    public boolean r_() {
        return !this.R_4764_Y;
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = (B_4088_l)this.n_1700_B.A_1306_N();
        this.R_4764_Y = false;
    }

    @Override
    public void P_1922_E() {
        if (!this.R_4764_Y && !this.n_1700_B.z_2372_L() && !this.n_1700_B.n_4915_F() && this.n_1700_B.i_601_W().intersects(this.J_1907_R.i_601_W())) {
            this.R_4764_Y = this.n_1700_B.G_564_y(this.J_1907_R);
        }
    }
}


