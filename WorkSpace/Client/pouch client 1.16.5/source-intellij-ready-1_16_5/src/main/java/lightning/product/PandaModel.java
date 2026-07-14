/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AgableMob;
import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;
import lightning.product.j_3013_R;
import lightning.product.u_530_F;
import lightning.product.ModelUtils;

public class PandaModel<T extends j_3013_R>
extends QuadrupedModel<T> {
    private float v_4262_N;
    private float w_1484_f;
    private float t_148_a;

    public PandaModel(int p_i51063_1_, float p_i51063_2_) {
        super(p_i51063_1_, p_i51063_2_, true, 23.0f, 4.8f, 2.7f, 3.0f, 49);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 6);
        this.n_1700_B.n_1700_B(-6.5f, -5.0f, -4.0f, 13.0f, 10.0f, 9.0f);
        this.n_1700_B.n_1700_B(0.0f, 11.5f, -17.0f);
        this.n_1700_B.n_1700_B(45, 16).n_1700_B(-3.5f, 0.0f, -6.0f, 7.0f, 5.0f, 2.0f);
        this.n_1700_B.n_1700_B(52, 25).n_1700_B(-8.5f, -8.0f, -1.0f, 5.0f, 4.0f, 1.0f);
        this.n_1700_B.n_1700_B(52, 25).n_1700_B(3.5f, -8.0f, -1.0f, 5.0f, 4.0f, 1.0f);
        this.J_1907_R = new e_4189_z(this, 0, 25);
        this.J_1907_R.n_1700_B(-9.5f, -13.0f, -6.5f, 19.0f, 26.0f, 13.0f);
        this.J_1907_R.n_1700_B(0.0f, 10.0f, 0.0f);
        int i = 9;
        int j = 6;
        this.R_4764_Y = new e_4189_z(this, 40, 0);
        this.R_4764_Y.n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f);
        this.R_4764_Y.n_1700_B(-5.5f, 15.0f, 9.0f);
        this.G_564_y = new e_4189_z(this, 40, 0);
        this.G_564_y.n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f);
        this.G_564_y.n_1700_B(5.5f, 15.0f, 9.0f);
        this.P_1922_E = new e_4189_z(this, 40, 0);
        this.P_1922_E.n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f);
        this.P_1922_E.n_1700_B(-5.5f, 15.0f, -9.0f);
        this.u_1723_Y = new e_4189_z(this, 40, 0);
        this.u_1723_Y.n_1700_B(-3.0f, 0.0f, -3.0f, 6.0f, 9.0f, 6.0f);
        this.u_1723_Y.n_1700_B(5.5f, 15.0f, -9.0f);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.v_4262_N = ((j_3013_R)entityIn).c_3005_b(partialTick);
        this.w_1484_f = ((j_3013_R)entityIn).H_2857_Y(partialTick);
        this.t_148_a = ((AgableMob)entityIn).d_() ? 0.0f : ((j_3013_R)entityIn).A_4115_X(partialTick);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        boolean flag = ((j_3013_R)entityIn).y_4642_Y() > 0;
        boolean flag1 = ((j_3013_R)entityIn).h_1640_b();
        int i = ((j_3013_R)entityIn).V_537_k();
        boolean flag2 = ((j_3013_R)entityIn).J_3635_s();
        boolean flag3 = ((j_3013_R)entityIn).Module();
        if (flag) {
            this.n_1700_B.v_4262_N = 0.35f * u_530_F.n_1700_B(0.6f * ageInTicks);
            this.n_1700_B.w_1484_f = 0.35f * u_530_F.n_1700_B(0.6f * ageInTicks);
            this.P_1922_E.u_1723_Y = -0.75f * u_530_F.n_1700_B(0.3f * ageInTicks);
            this.u_1723_Y.u_1723_Y = 0.75f * u_530_F.n_1700_B(0.3f * ageInTicks);
        } else {
            this.n_1700_B.w_1484_f = 0.0f;
        }
        if (flag1) {
            if (i < 15) {
                this.n_1700_B.u_1723_Y = -0.7853982f * (float)i / 14.0f;
            } else if (i < 20) {
                float f = (i - 15) / 5;
                this.n_1700_B.u_1723_Y = -0.7853982f + 0.7853982f * f;
            }
        }
        if (this.v_4262_N > 0.0f) {
            this.J_1907_R.u_1723_Y = ModelUtils.n_1700_B(this.J_1907_R.u_1723_Y, 1.7407963f, this.v_4262_N);
            this.n_1700_B.u_1723_Y = ModelUtils.n_1700_B(this.n_1700_B.u_1723_Y, 1.5707964f, this.v_4262_N);
            this.P_1922_E.w_1484_f = -0.27079642f;
            this.u_1723_Y.w_1484_f = 0.27079642f;
            this.R_4764_Y.w_1484_f = 0.5707964f;
            this.G_564_y.w_1484_f = -0.5707964f;
            if (flag2) {
                this.n_1700_B.u_1723_Y = 1.5707964f + 0.2f * u_530_F.n_1700_B(ageInTicks * 0.6f);
                this.P_1922_E.u_1723_Y = -0.4f - 0.2f * u_530_F.n_1700_B(ageInTicks * 0.6f);
                this.u_1723_Y.u_1723_Y = -0.4f - 0.2f * u_530_F.n_1700_B(ageInTicks * 0.6f);
            }
            if (flag3) {
                this.n_1700_B.u_1723_Y = 2.1707964f;
                this.P_1922_E.u_1723_Y = -0.9f;
                this.u_1723_Y.u_1723_Y = -0.9f;
            }
        } else {
            this.R_4764_Y.w_1484_f = 0.0f;
            this.G_564_y.w_1484_f = 0.0f;
            this.P_1922_E.w_1484_f = 0.0f;
            this.u_1723_Y.w_1484_f = 0.0f;
        }
        if (this.w_1484_f > 0.0f) {
            this.R_4764_Y.u_1723_Y = -0.6f * u_530_F.n_1700_B(ageInTicks * 0.15f);
            this.G_564_y.u_1723_Y = 0.6f * u_530_F.n_1700_B(ageInTicks * 0.15f);
            this.P_1922_E.u_1723_Y = 0.3f * u_530_F.n_1700_B(ageInTicks * 0.25f);
            this.u_1723_Y.u_1723_Y = -0.3f * u_530_F.n_1700_B(ageInTicks * 0.25f);
            this.n_1700_B.u_1723_Y = ModelUtils.n_1700_B(this.n_1700_B.u_1723_Y, 1.5707964f, this.w_1484_f);
        }
        if (this.t_148_a > 0.0f) {
            this.n_1700_B.u_1723_Y = ModelUtils.n_1700_B(this.n_1700_B.u_1723_Y, 2.0561945f, this.t_148_a);
            this.R_4764_Y.u_1723_Y = -0.5f * u_530_F.n_1700_B(ageInTicks * 0.5f);
            this.G_564_y.u_1723_Y = 0.5f * u_530_F.n_1700_B(ageInTicks * 0.5f);
            this.P_1922_E.u_1723_Y = 0.5f * u_530_F.n_1700_B(ageInTicks * 0.5f);
            this.u_1723_Y.u_1723_Y = -0.5f * u_530_F.n_1700_B(ageInTicks * 0.5f);
        }
    }
}



