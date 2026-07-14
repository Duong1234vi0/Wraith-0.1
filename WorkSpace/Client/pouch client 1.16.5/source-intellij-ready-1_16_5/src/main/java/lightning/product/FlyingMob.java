/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.Z_530_i;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public abstract class FlyingMob
extends Z_530_i {
    protected FlyingMob(t_5_h<? extends FlyingMob> type, b_4507_u worldIn) {
        super((t_5_h<? extends Z_530_i>)type, worldIn);
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.RowButton()) {
            this.n_1700_B(0.02f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B((double)0.8f));
        } else if (this.W_3464_O()) {
            this.n_1700_B(0.02f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.5));
        } else {
            float f = 0.91f;
            if (this.e_1992_r) {
                f = this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2960_b() - 1.0, this.l_2647_k())).J_1907_R().h_1847_R() * 0.91f;
            }
            float f1 = 0.16277137f / (f * f * f);
            f = 0.91f;
            if (this.e_1992_r) {
                f = this.O_508_d.getBlockState(new c_1514_x(this.O_3598_v(), this.X_2960_b() - 1.0, this.l_2647_k())).J_1907_R().h_1847_R() * 0.91f;
            }
            this.n_1700_B(this.e_1992_r ? 0.1f * f1 : 0.02f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B((double)f));
        }
        this.n_1700_B((r_4811_B)this, false);
    }

    @Override
    public boolean e_() {
        return false;
    }
}


