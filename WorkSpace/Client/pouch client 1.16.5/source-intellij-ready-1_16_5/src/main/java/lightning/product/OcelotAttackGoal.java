/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.BlockGetter;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class OcelotAttackGoal
extends Goal {
    private final BlockGetter n_1700_B;
    private final Z_530_i J_1907_R;
    private r_4811_B R_4764_Y;
    private int G_564_y;

    public OcelotAttackGoal(Z_530_i theEntityIn) {
        this.J_1907_R = theEntityIn;
        this.n_1700_B = theEntityIn.O_508_d;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        r_4811_B livingentity = this.J_1907_R.t_148_a();
        if (livingentity == null) {
            return false;
        }
        this.R_4764_Y = livingentity;
        return true;
    }

    @Override
    public boolean J_1907_R() {
        if (!this.R_4764_Y.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (this.J_1907_R.G_564_y((N_4263_v)this.R_4764_Y) > 225.0) {
            return false;
        }
        return !this.J_1907_R.e_4240_b().M_588_G() || this.n_1700_B();
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y = null;
        this.J_1907_R.e_4240_b().h_1847_R();
    }

    @Override
    public void P_1922_E() {
        this.J_1907_R.c_3005_b().n_1700_B(this.R_4764_Y, 30.0f, 30.0f);
        double d0 = this.J_1907_R.C_415_h() * 2.0f * this.J_1907_R.C_415_h() * 2.0f;
        double d1 = this.J_1907_R.v_4262_N(this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k());
        double d2 = 0.8;
        if (d1 > d0 && d1 < 16.0) {
            d2 = 1.33;
        } else if (d1 < 225.0) {
            d2 = 0.6;
        }
        this.J_1907_R.e_4240_b().n_1700_B((N_4263_v)this.R_4764_Y, d2);
        this.G_564_y = Math.max(this.G_564_y - 1, 0);
        if (!(d1 > d0) && this.G_564_y <= 0) {
            this.G_564_y = 20;
            this.J_1907_R.q_2307_F(this.R_4764_Y);
        }
    }
}


