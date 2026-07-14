/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.b_3485_j;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class f_4072_M
extends Goal {
    private final b_3485_j n_1700_B;
    private r_4811_B J_1907_R;

    public f_4072_M(b_3485_j entitycreeperIn) {
        this.n_1700_B = entitycreeperIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        return this.n_1700_B.y_4642_Y() > 0 || livingentity != null && this.n_1700_B.G_564_y((N_4263_v)livingentity) < 9.0;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().h_1847_R();
        this.J_1907_R = this.n_1700_B.t_148_a();
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
    }

    @Override
    public void P_1922_E() {
        if (this.J_1907_R == null) {
            this.n_1700_B.n_1700_B(-1);
        } else if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) > 49.0) {
            this.n_1700_B.n_1700_B(-1);
        } else if (!this.n_1700_B.n_3318_d().n_1700_B(this.J_1907_R)) {
            this.n_1700_B.n_1700_B(-1);
        } else {
            this.n_1700_B.n_1700_B(1);
        }
    }
}


