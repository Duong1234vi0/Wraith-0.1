/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Q_3816_H;
import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;

public class PolarBearModel<T extends Q_3816_H>
extends QuadrupedModel<T> {
    public PolarBearModel() {
        super(12, 0.0f, true, 16.0f, 4.0f, 2.25f, 2.0f, 24);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-3.5f, -3.0f, -3.0f, 7.0f, 7.0f, 7.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 10.0f, -16.0f);
        this.n_1700_B.n_1700_B(0, 44).n_1700_B(-2.5f, 1.0f, -6.0f, 5.0f, 3.0f, 3.0f, 0.0f);
        this.n_1700_B.n_1700_B(26, 0).n_1700_B(-4.5f, -4.0f, -1.0f, 2.0f, 2.0f, 1.0f, 0.0f);
        e_4189_z modelrenderer = this.n_1700_B.n_1700_B(26, 0);
        modelrenderer.t_148_a = true;
        modelrenderer.n_1700_B(2.5f, -4.0f, -1.0f, 2.0f, 2.0f, 1.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this);
        this.J_1907_R.n_1700_B(0, 19).n_1700_B(-5.0f, -13.0f, -7.0f, 14.0f, 14.0f, 11.0f, 0.0f);
        this.J_1907_R.n_1700_B(39, 0).n_1700_B(-4.0f, -25.0f, -7.0f, 12.0f, 12.0f, 10.0f, 0.0f);
        this.J_1907_R.n_1700_B(-2.0f, 9.0f, 12.0f);
        int i = 10;
        this.R_4764_Y = new e_4189_z(this, 50, 22);
        this.R_4764_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 8.0f, 0.0f);
        this.R_4764_Y.n_1700_B(-3.5f, 14.0f, 6.0f);
        this.G_564_y = new e_4189_z(this, 50, 22);
        this.G_564_y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 8.0f, 0.0f);
        this.G_564_y.n_1700_B(3.5f, 14.0f, 6.0f);
        this.P_1922_E = new e_4189_z(this, 50, 40);
        this.P_1922_E.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 6.0f, 0.0f);
        this.P_1922_E.n_1700_B(-2.5f, 14.0f, -7.0f);
        this.u_1723_Y = new e_4189_z(this, 50, 40);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 6.0f, 0.0f);
        this.u_1723_Y.n_1700_B(2.5f, 14.0f, -7.0f);
        this.R_4764_Y.R_4764_Y -= 1.0f;
        this.G_564_y.R_4764_Y += 1.0f;
        this.R_4764_Y.P_1922_E += 0.0f;
        this.G_564_y.P_1922_E += 0.0f;
        this.P_1922_E.R_4764_Y -= 1.0f;
        this.u_1723_Y.R_4764_Y += 1.0f;
        this.P_1922_E.P_1922_E -= 1.0f;
        this.u_1723_Y.P_1922_E -= 1.0f;
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float f = ageInTicks - (float)((Q_3816_H)entityIn).RealmsWorldResetDto;
        float f1 = ((Q_3816_H)entityIn).c_3005_b(f);
        f1 *= f1;
        float f2 = 1.0f - f1;
        this.J_1907_R.u_1723_Y = 1.5707964f - f1 * (float)Math.PI * 0.35f;
        this.J_1907_R.G_564_y = 9.0f * f2 + 11.0f * f1;
        this.P_1922_E.G_564_y = 14.0f * f2 - 6.0f * f1;
        this.P_1922_E.P_1922_E = -8.0f * f2 - 4.0f * f1;
        this.P_1922_E.u_1723_Y -= f1 * (float)Math.PI * 0.45f;
        this.u_1723_Y.G_564_y = this.P_1922_E.G_564_y;
        this.u_1723_Y.P_1922_E = this.P_1922_E.P_1922_E;
        this.u_1723_Y.u_1723_Y -= f1 * (float)Math.PI * 0.45f;
        if (this.M_182_A) {
            this.n_1700_B.G_564_y = 10.0f * f2 - 9.0f * f1;
            this.n_1700_B.P_1922_E = -16.0f * f2 - 7.0f * f1;
        } else {
            this.n_1700_B.G_564_y = 10.0f * f2 - 14.0f * f1;
            this.n_1700_B.P_1922_E = -16.0f * f2 - 3.0f * f1;
        }
        this.n_1700_B.u_1723_Y += f1 * (float)Math.PI * 0.15f;
    }
}


