/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.e_4189_z;
import lightning.product.n_1658_l;
import lightning.product.r_4811_B;

public class w_2498_n<T extends r_4811_B>
extends n_1658_l<T> {
    public boolean M_588_G;
    public boolean P_4830_p;

    public w_2498_n(float scale) {
        super(0.0f, -14.0f, 64, 32);
        float f = -14.0f;
        this.J_1907_R = new e_4189_z(this, 0, 16);
        this.J_1907_R.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, scale - 0.5f);
        this.J_1907_R.n_1700_B(0.0f, -14.0f, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 32, 16);
        this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, scale);
        this.R_4764_Y.n_1700_B(0.0f, -14.0f, 0.0f);
        this.G_564_y = new e_4189_z(this, 56, 0);
        this.G_564_y.n_1700_B(-1.0f, -2.0f, -1.0f, 2.0f, 30.0f, 2.0f, scale);
        this.G_564_y.n_1700_B(-3.0f, -12.0f, 0.0f);
        this.P_1922_E = new e_4189_z(this, 56, 0);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(-1.0f, -2.0f, -1.0f, 2.0f, 30.0f, 2.0f, scale);
        this.P_1922_E.n_1700_B(5.0f, -12.0f, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 56, 0);
        this.u_1723_Y.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 30.0f, 2.0f, scale);
        this.u_1723_Y.n_1700_B(-2.0f, -2.0f, 0.0f);
        this.v_4262_N = new e_4189_z(this, 56, 0);
        this.v_4262_N.t_148_a = true;
        this.v_4262_N.n_1700_B(-1.0f, 0.0f, -1.0f, 2.0f, 30.0f, 2.0f, scale);
        this.v_4262_N.n_1700_B(2.0f, -2.0f, 0.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.n_1700_B.s_956_w = true;
        float f = -14.0f;
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.R_4764_Y.G_564_y = -14.0f;
        this.R_4764_Y.P_1922_E = -0.0f;
        this.u_1723_Y.u_1723_Y -= 0.0f;
        this.v_4262_N.u_1723_Y -= 0.0f;
        this.G_564_y.u_1723_Y = (float)((double)this.G_564_y.u_1723_Y * 0.5);
        this.P_1922_E.u_1723_Y = (float)((double)this.P_1922_E.u_1723_Y * 0.5);
        this.u_1723_Y.u_1723_Y = (float)((double)this.u_1723_Y.u_1723_Y * 0.5);
        this.v_4262_N.u_1723_Y = (float)((double)this.v_4262_N.u_1723_Y * 0.5);
        float f1 = 0.4f;
        if (this.G_564_y.u_1723_Y > 0.4f) {
            this.G_564_y.u_1723_Y = 0.4f;
        }
        if (this.P_1922_E.u_1723_Y > 0.4f) {
            this.P_1922_E.u_1723_Y = 0.4f;
        }
        if (this.G_564_y.u_1723_Y < -0.4f) {
            this.G_564_y.u_1723_Y = -0.4f;
        }
        if (this.P_1922_E.u_1723_Y < -0.4f) {
            this.P_1922_E.u_1723_Y = -0.4f;
        }
        if (this.u_1723_Y.u_1723_Y > 0.4f) {
            this.u_1723_Y.u_1723_Y = 0.4f;
        }
        if (this.v_4262_N.u_1723_Y > 0.4f) {
            this.v_4262_N.u_1723_Y = 0.4f;
        }
        if (this.u_1723_Y.u_1723_Y < -0.4f) {
            this.u_1723_Y.u_1723_Y = -0.4f;
        }
        if (this.v_4262_N.u_1723_Y < -0.4f) {
            this.v_4262_N.u_1723_Y = -0.4f;
        }
        if (this.M_588_G) {
            this.G_564_y.u_1723_Y = -0.5f;
            this.P_1922_E.u_1723_Y = -0.5f;
            this.G_564_y.w_1484_f = 0.05f;
            this.P_1922_E.w_1484_f = -0.05f;
        }
        this.G_564_y.P_1922_E = 0.0f;
        this.P_1922_E.P_1922_E = 0.0f;
        this.u_1723_Y.P_1922_E = 0.0f;
        this.v_4262_N.P_1922_E = 0.0f;
        this.u_1723_Y.G_564_y = -5.0f;
        this.v_4262_N.G_564_y = -5.0f;
        this.n_1700_B.P_1922_E = -0.0f;
        this.n_1700_B.G_564_y = -13.0f;
        this.J_1907_R.R_4764_Y = this.n_1700_B.R_4764_Y;
        this.J_1907_R.G_564_y = this.n_1700_B.G_564_y;
        this.J_1907_R.P_1922_E = this.n_1700_B.P_1922_E;
        this.J_1907_R.u_1723_Y = this.n_1700_B.u_1723_Y;
        this.J_1907_R.v_4262_N = this.n_1700_B.v_4262_N;
        this.J_1907_R.w_1484_f = this.n_1700_B.w_1484_f;
        if (this.P_4830_p) {
            float f2 = 1.0f;
            this.n_1700_B.G_564_y -= 5.0f;
        }
        float f3 = -14.0f;
        this.G_564_y.n_1700_B(-5.0f, -12.0f, 0.0f);
        this.P_1922_E.n_1700_B(5.0f, -12.0f, 0.0f);
    }
}

