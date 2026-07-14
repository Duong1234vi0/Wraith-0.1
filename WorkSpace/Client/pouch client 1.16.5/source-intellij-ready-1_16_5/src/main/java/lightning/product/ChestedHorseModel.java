/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.W_3443_Y;
import lightning.product.HorseModel;
import lightning.product.e_4189_z;

public class ChestedHorseModel<T extends W_3443_Y>
extends HorseModel<T> {
    private final e_4189_z R_4764_Y = new e_4189_z(this, 26, 21);
    private final e_4189_z G_564_y;

    public ChestedHorseModel(float p_i51068_1_) {
        super(p_i51068_1_);
        this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 8.0f, 3.0f);
        this.G_564_y = new e_4189_z(this, 26, 21);
        this.G_564_y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 8.0f, 3.0f);
        this.R_4764_Y.v_4262_N = -1.5707964f;
        this.G_564_y.v_4262_N = 1.5707964f;
        this.R_4764_Y.n_1700_B(6.0f, -8.0f, 0.0f);
        this.G_564_y.n_1700_B(-6.0f, -8.0f, 0.0f);
        this.n_1700_B.J_1907_R(this.R_4764_Y);
        this.n_1700_B.J_1907_R(this.G_564_y);
    }

    @Override
    protected void n_1700_B(e_4189_z p_199047_1_) {
        e_4189_z modelrenderer = new e_4189_z(this, 0, 12);
        modelrenderer.n_1700_B(-1.0f, -7.0f, 0.0f, 2.0f, 7.0f, 1.0f);
        modelrenderer.n_1700_B(1.25f, -10.0f, 4.0f);
        e_4189_z modelrenderer1 = new e_4189_z(this, 0, 12);
        modelrenderer1.n_1700_B(-1.0f, -7.0f, 0.0f, 2.0f, 7.0f, 1.0f);
        modelrenderer1.n_1700_B(-1.25f, -10.0f, 4.0f);
        modelrenderer.u_1723_Y = 0.2617994f;
        modelrenderer.w_1484_f = 0.2617994f;
        modelrenderer1.u_1723_Y = 0.2617994f;
        modelrenderer1.w_1484_f = -0.2617994f;
        p_199047_1_.J_1907_R(modelrenderer);
        p_199047_1_.J_1907_R(modelrenderer1);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (((W_3443_Y)entityIn).V_1176_p()) {
            this.R_4764_Y.s_956_w = true;
            this.G_564_y.s_956_w = true;
        } else {
            this.R_4764_Y.s_956_w = false;
            this.G_564_y.s_956_w = false;
        }
    }
}


