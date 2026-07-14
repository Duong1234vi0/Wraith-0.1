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

public class E_514_j
extends J_133_e {
    private final C_3622_I n_1700_B;
    private r_4811_B J_1907_R;
    private int R_4764_Y;

    public E_514_j(C_3622_I theDefendingTameableIn) {
        super(theDefendingTameableIn, false);
        this.n_1700_B = theDefendingTameableIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.U_3758_B() && !this.n_1700_B.D_3612_q()) {
            r_4811_B livingentity = this.n_1700_B.A_1306_N();
            if (livingentity == null) {
                return false;
            }
            this.J_1907_R = livingentity.q_817_e();
            int i = livingentity.r_260_T();
            return i != this.R_4764_Y && this.n_1700_B(this.J_1907_R, TargetingConditions.n_1700_B) && this.n_1700_B.n_1700_B(this.J_1907_R, livingentity);
        }
        return false;
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E.R_4764_Y(this.J_1907_R);
        r_4811_B livingentity = this.n_1700_B.A_1306_N();
        if (livingentity != null) {
            this.R_4764_Y = livingentity.r_260_T();
        }
        super.R_4764_Y();
    }
}


