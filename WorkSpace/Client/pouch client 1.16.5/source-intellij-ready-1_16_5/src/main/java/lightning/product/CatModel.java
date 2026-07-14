/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.OcelotModel;
import lightning.product.C_3622_I;
import lightning.product.K_550_M;
import lightning.product.ModelUtils;

public class CatModel<T extends K_550_M>
extends OcelotModel<T> {
    private float s_956_w;
    private float u_2550_I;
    private float M_588_G;

    public CatModel(float p_i51069_1_) {
        super(p_i51069_1_);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.s_956_w = ((K_550_M)entityIn).c_3005_b(partialTick);
        this.u_2550_I = ((K_550_M)entityIn).H_2857_Y(partialTick);
        this.M_588_G = ((K_550_M)entityIn).A_4115_X(partialTick);
        if (this.s_956_w <= 0.0f) {
            this.v_4262_N.u_1723_Y = 0.0f;
            this.v_4262_N.w_1484_f = 0.0f;
            this.R_4764_Y.u_1723_Y = 0.0f;
            this.R_4764_Y.w_1484_f = 0.0f;
            this.G_564_y.u_1723_Y = 0.0f;
            this.G_564_y.w_1484_f = 0.0f;
            this.G_564_y.R_4764_Y = -1.2f;
            this.n_1700_B.u_1723_Y = 0.0f;
            this.J_1907_R.u_1723_Y = 0.0f;
            this.J_1907_R.w_1484_f = 0.0f;
            this.J_1907_R.R_4764_Y = -1.1f;
            this.J_1907_R.G_564_y = 18.0f;
        }
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        if (((C_3622_I)entityIn).z_2372_L()) {
            this.w_1484_f.u_1723_Y = 0.7853982f;
            this.w_1484_f.G_564_y += -4.0f;
            this.w_1484_f.P_1922_E += 5.0f;
            this.v_4262_N.G_564_y += -3.3f;
            this.v_4262_N.P_1922_E += 1.0f;
            this.P_1922_E.G_564_y += 8.0f;
            this.P_1922_E.P_1922_E += -2.0f;
            this.u_1723_Y.G_564_y += 2.0f;
            this.u_1723_Y.P_1922_E += -0.8f;
            this.P_1922_E.u_1723_Y = 1.7278761f;
            this.u_1723_Y.u_1723_Y = 2.670354f;
            this.R_4764_Y.u_1723_Y = -0.15707964f;
            this.R_4764_Y.G_564_y = 16.1f;
            this.R_4764_Y.P_1922_E = -7.0f;
            this.G_564_y.u_1723_Y = -0.15707964f;
            this.G_564_y.G_564_y = 16.1f;
            this.G_564_y.P_1922_E = -7.0f;
            this.n_1700_B.u_1723_Y = -1.5707964f;
            this.n_1700_B.G_564_y = 21.0f;
            this.n_1700_B.P_1922_E = 1.0f;
            this.J_1907_R.u_1723_Y = -1.5707964f;
            this.J_1907_R.G_564_y = 21.0f;
            this.J_1907_R.P_1922_E = 1.0f;
            this.t_148_a = 3;
        }
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (this.s_956_w > 0.0f) {
            this.v_4262_N.w_1484_f = ModelUtils.n_1700_B(this.v_4262_N.w_1484_f, -1.2707963f, this.s_956_w);
            this.v_4262_N.v_4262_N = ModelUtils.n_1700_B(this.v_4262_N.v_4262_N, 1.2707963f, this.s_956_w);
            this.R_4764_Y.u_1723_Y = -1.2707963f;
            this.G_564_y.u_1723_Y = -0.47079635f;
            this.G_564_y.w_1484_f = -0.2f;
            this.G_564_y.R_4764_Y = -0.2f;
            this.n_1700_B.u_1723_Y = -0.4f;
            this.J_1907_R.u_1723_Y = 0.5f;
            this.J_1907_R.w_1484_f = -0.5f;
            this.J_1907_R.R_4764_Y = -0.3f;
            this.J_1907_R.G_564_y = 20.0f;
            this.P_1922_E.u_1723_Y = ModelUtils.n_1700_B(this.P_1922_E.u_1723_Y, 0.8f, this.u_2550_I);
            this.u_1723_Y.u_1723_Y = ModelUtils.n_1700_B(this.u_1723_Y.u_1723_Y, -0.4f, this.u_2550_I);
        }
        if (this.M_588_G > 0.0f) {
            this.v_4262_N.u_1723_Y = ModelUtils.n_1700_B(this.v_4262_N.u_1723_Y, -0.58177644f, this.M_588_G);
        }
    }
}


