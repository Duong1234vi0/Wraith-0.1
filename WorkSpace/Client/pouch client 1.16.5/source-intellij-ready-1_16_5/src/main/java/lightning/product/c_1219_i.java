/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1436_R;
import lightning.product.K_4074_S;
import lightning.product.S_1431_H;
import lightning.product.GoalUtils;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.i_2099_H;
import lightning.product.Goal;

public abstract class c_1219_i
extends Goal {
    protected Z_530_i G_564_y;
    protected c_1514_x P_1922_E = c_1514_x.ZERO;
    protected boolean u_1723_Y;
    private boolean n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;

    public c_1219_i(Z_530_i entityIn) {
        this.G_564_y = entityIn;
        if (!GoalUtils.n_1700_B(entityIn)) {
            throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
        }
    }

    protected boolean v_4262_N() {
        if (!this.u_1723_Y) {
            return false;
        }
        K_4074_S blockstate = this.G_564_y.O_508_d.getBlockState(this.P_1922_E);
        if (!(blockstate.J_1907_R() instanceof S_1431_H)) {
            this.u_1723_Y = false;
            return false;
        }
        return blockstate.R_4764_Y(S_1431_H.h_1847_R);
    }

    protected void n_1700_B(boolean open) {
        K_4074_S blockstate;
        if (this.u_1723_Y && (blockstate = this.G_564_y.O_508_d.getBlockState(this.P_1922_E)).J_1907_R() instanceof S_1431_H) {
            ((S_1431_H)blockstate.J_1907_R()).n_1700_B(this.G_564_y.O_508_d, blockstate, this.P_1922_E, open);
        }
    }

    @Override
    public boolean n_1700_B() {
        if (!GoalUtils.n_1700_B(this.G_564_y)) {
            return false;
        }
        if (!this.G_564_y.D_60_a) {
            return false;
        }
        i_2099_H groundpathnavigator = (i_2099_H)this.G_564_y.e_4240_b();
        b_1722_e path = groundpathnavigator.s_956_w();
        if (path != null && !path.R_4764_Y() && groundpathnavigator.P_1922_E()) {
            for (int i = 0; i < Math.min(path.u_1723_Y() + 2, path.P_1922_E()); ++i) {
                D_1436_R pathpoint = path.n_1700_B(i);
                this.P_1922_E = new c_1514_x(pathpoint.n_1700_B, pathpoint.J_1907_R + 1, pathpoint.R_4764_Y);
                if (this.G_564_y.v_4262_N(this.P_1922_E.getX(), this.G_564_y.X_2960_b(), this.P_1922_E.getZ()) > 2.25) continue;
                this.u_1723_Y = S_1431_H.n_1700_B(this.G_564_y.O_508_d, this.P_1922_E);
                if (!this.u_1723_Y) continue;
                return true;
            }
            this.P_1922_E = this.G_564_y.b_2312_j().up();
            this.u_1723_Y = S_1431_H.n_1700_B(this.G_564_y.O_508_d, this.P_1922_E);
            return this.u_1723_Y;
        }
        return false;
    }

    @Override
    public boolean J_1907_R() {
        return !this.n_1700_B;
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B = false;
        this.J_1907_R = (float)((double)this.P_1922_E.getX() + 0.5 - this.G_564_y.O_3598_v());
        this.R_4764_Y = (float)((double)this.P_1922_E.getZ() + 0.5 - this.G_564_y.l_2647_k());
    }

    @Override
    public void P_1922_E() {
        float f1;
        float f = (float)((double)this.P_1922_E.getX() + 0.5 - this.G_564_y.O_3598_v());
        float f2 = this.J_1907_R * f + this.R_4764_Y * (f1 = (float)((double)this.P_1922_E.getZ() + 0.5 - this.G_564_y.l_2647_k()));
        if (f2 < 0.0f) {
            this.n_1700_B = true;
        }
    }
}


