/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class LookControl {
    protected final Z_530_i n_1700_B;
    protected float J_1907_R;
    protected float R_4764_Y;
    protected boolean G_564_y;
    protected double P_1922_E;
    protected double u_1723_Y;
    protected double v_4262_N;

    public LookControl(Z_530_i mob) {
        this.n_1700_B = mob;
    }

    public void n_1700_B(e_2866_D lookVector) {
        this.n_1700_B(lookVector.J_1907_R, lookVector.R_4764_Y, lookVector.G_564_y);
    }

    public void n_1700_B(N_4263_v entityIn, float deltaYaw, float deltaPitch) {
        this.n_1700_B(entityIn.O_3598_v(), LookControl.n_1700_B(entityIn), entityIn.l_2647_k(), deltaYaw, deltaPitch);
    }

    public void n_1700_B(double x, double y, double z) {
        this.n_1700_B(x, y, z, this.n_1700_B.N_2525_X(), this.n_1700_B.Z_976_R());
    }

    public void n_1700_B(double x, double y, double z, float deltaYaw, float deltaPitch) {
        this.P_1922_E = x;
        this.u_1723_Y = y;
        this.v_4262_N = z;
        this.J_1907_R = deltaYaw;
        this.R_4764_Y = deltaPitch;
        this.G_564_y = true;
    }

    public void n_1700_B() {
        if (this.J_1907_R()) {
            this.n_1700_B.f_4016_n = 0.0f;
        }
        if (this.G_564_y) {
            this.G_564_y = false;
            this.n_1700_B.f_3449_S = this.n_1700_B(this.n_1700_B.f_3449_S, this.w_1484_f(), this.J_1907_R);
            this.n_1700_B.f_4016_n = this.n_1700_B(this.n_1700_B.f_4016_n, this.v_4262_N(), this.R_4764_Y);
        } else {
            this.n_1700_B.f_3449_S = this.n_1700_B(this.n_1700_B.f_3449_S, this.n_1700_B.C_1162_e, 10.0f);
        }
        if (!this.n_1700_B.e_4240_b().M_588_G()) {
            this.n_1700_B.f_3449_S = u_530_F.J_1907_R(this.n_1700_B.f_3449_S, this.n_1700_B.C_1162_e, (float)this.n_1700_B.H_1990_U());
        }
    }

    protected boolean J_1907_R() {
        return true;
    }

    public boolean R_4764_Y() {
        return this.G_564_y;
    }

    public double G_564_y() {
        return this.P_1922_E;
    }

    public double P_1922_E() {
        return this.u_1723_Y;
    }

    public double u_1723_Y() {
        return this.v_4262_N;
    }

    protected float v_4262_N() {
        double d0 = this.P_1922_E - this.n_1700_B.O_3598_v();
        double d1 = this.u_1723_Y - this.n_1700_B.X_2048_Y();
        double d2 = this.v_4262_N - this.n_1700_B.l_2647_k();
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        return (float)(-(u_530_F.G_564_y(d1, d3) * 57.2957763671875));
    }

    protected float w_1484_f() {
        double d0 = this.P_1922_E - this.n_1700_B.O_3598_v();
        double d1 = this.v_4262_N - this.n_1700_B.l_2647_k();
        return (float)(u_530_F.G_564_y(d1, d0) * 57.2957763671875) - 90.0f;
    }

    protected float n_1700_B(float from, float to, float maxDelta) {
        float f = u_530_F.R_4764_Y(from, to);
        float f1 = u_530_F.n_1700_B(f, -maxDelta, maxDelta);
        return from + f1;
    }

    private static double n_1700_B(N_4263_v entity) {
        return entity instanceof r_4811_B ? entity.X_2048_Y() : (entity.i_601_W().minY + entity.i_601_W().maxY) / 2.0;
    }
}


