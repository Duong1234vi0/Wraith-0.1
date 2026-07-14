/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2099_H;

public class WallClimberNavigation
extends i_2099_H {
    private c_1514_x n_1700_B;

    public WallClimberNavigation(Z_530_i entityLivingIn, b_4507_u worldIn) {
        super(entityLivingIn, worldIn);
    }

    @Override
    public b_1722_e n_1700_B(c_1514_x pos, int p_179680_2_) {
        this.n_1700_B = pos;
        return super.n_1700_B(pos, p_179680_2_);
    }

    @Override
    public b_1722_e n_1700_B(N_4263_v entityIn, int p_75494_2_) {
        this.n_1700_B = entityIn.b_2312_j();
        return super.n_1700_B(entityIn, p_75494_2_);
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn, double speedIn) {
        b_1722_e path = this.n_1700_B(entityIn, 0);
        if (path != null) {
            return this.n_1700_B(path, speedIn);
        }
        this.n_1700_B = entityIn.b_2312_j();
        this.P_1922_E = speedIn;
        return true;
    }

    @Override
    public void n_1700_B() {
        if (!this.M_588_G()) {
            super.n_1700_B();
        } else if (this.n_1700_B != null) {
            if (!(this.n_1700_B.withinDistance(this.J_1907_R.s_4990_V(), (double)this.J_1907_R.C_415_h()) || this.J_1907_R.X_2960_b() > (double)this.n_1700_B.getY() && new c_1514_x((double)this.n_1700_B.getX(), this.J_1907_R.X_2960_b(), (double)this.n_1700_B.getZ()).withinDistance(this.J_1907_R.s_4990_V(), (double)this.J_1907_R.C_415_h()))) {
                this.J_1907_R.A_4115_X().n_1700_B(this.n_1700_B.getX(), this.n_1700_B.getY(), this.n_1700_B.getZ(), this.P_1922_E);
            } else {
                this.n_1700_B = null;
            }
        }
    }
}


