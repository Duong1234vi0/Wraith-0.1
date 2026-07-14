/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.C_3622_I;
import lightning.product.J_133_e;
import lightning.product.TargetingConditions;
import lightning.product.Goal;
import lightning.product.r_4811_B;

/*
 * Renamed from lightning.product.L_311_L
 */
public class l_311_l_0
extends J_133_e {
    private final C_3622_I n_1700_B;
    private r_4811_B J_1907_R;
    private int R_4764_Y;

    public l_311_l_0(C_3622_I theEntityTameableIn) {
        super(theEntityTameableIn, false);
        this.n_1700_B = theEntityTameableIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.U_3758_B() && !this.n_1700_B.D_3612_q()) {
            r_4811_B livingentity = this.n_1700_B.A_1306_N();
            if (livingentity == null) {
                return false;
            }
            this.J_1907_R = livingentity.Q_2753_H();
            int i = livingentity.Y_2080_q();
            return i != this.R_4764_Y && this.n_1700_B(this.J_1907_R, TargetingConditions.n_1700_B) && this.n_1700_B.n_1700_B(this.J_1907_R, livingentity);
        }
        return false;
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E.R_4764_Y(this.J_1907_R);
        r_4811_B livingentity = this.n_1700_B.A_1306_N();
        if (livingentity != null) {
            this.R_4764_Y = livingentity.Y_2080_q();
        }
        super.R_4764_Y();
    }
}


