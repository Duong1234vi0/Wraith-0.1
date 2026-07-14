/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.AgableMob;
import lightning.product.D_4792_h;
import lightning.product.EntityModel;
import lightning.product.W_3443_Y;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.u_530_F;

public class LlamaModel<T extends W_3443_Y>
extends EntityModel<T> {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;
    private final e_4189_z G_564_y;
    private final e_4189_z P_1922_E;
    private final e_4189_z u_1723_Y;
    private final e_4189_z v_4262_N;
    private final e_4189_z w_1484_f;

    public LlamaModel(float p_i47226_1_) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-2.0f, -14.0f, -10.0f, 4.0f, 4.0f, 9.0f, p_i47226_1_);
        this.n_1700_B.n_1700_B(0.0f, 7.0f, -6.0f);
        this.n_1700_B.n_1700_B(0, 14).n_1700_B(-4.0f, -16.0f, -6.0f, 8.0f, 18.0f, 6.0f, p_i47226_1_);
        this.n_1700_B.n_1700_B(17, 0).n_1700_B(-4.0f, -19.0f, -4.0f, 3.0f, 3.0f, 2.0f, p_i47226_1_);
        this.n_1700_B.n_1700_B(17, 0).n_1700_B(1.0f, -19.0f, -4.0f, 3.0f, 3.0f, 2.0f, p_i47226_1_);
        this.J_1907_R = new e_4189_z(this, 29, 0);
        this.J_1907_R.n_1700_B(-6.0f, -10.0f, -7.0f, 12.0f, 18.0f, 10.0f, p_i47226_1_);
        this.J_1907_R.n_1700_B(0.0f, 5.0f, 2.0f);
        this.v_4262_N = new e_4189_z(this, 45, 28);
        this.v_4262_N.n_1700_B(-3.0f, 0.0f, 0.0f, 8.0f, 8.0f, 3.0f, p_i47226_1_);
        this.v_4262_N.n_1700_B(-8.5f, 3.0f, 3.0f);
        this.v_4262_N.v_4262_N = 1.5707964f;
        this.w_1484_f = new e_4189_z(this, 45, 41);
        this.w_1484_f.n_1700_B(-3.0f, 0.0f, 0.0f, 8.0f, 8.0f, 3.0f, p_i47226_1_);
        this.w_1484_f.n_1700_B(5.5f, 3.0f, 3.0f);
        this.w_1484_f.v_4262_N = 1.5707964f;
        int i = 4;
        int j = 14;
        this.R_4764_Y = new e_4189_z(this, 29, 29);
        this.R_4764_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 14.0f, 4.0f, p_i47226_1_);
        this.R_4764_Y.n_1700_B(-2.5f, 10.0f, 6.0f);
        this.G_564_y = new e_4189_z(this, 29, 29);
        this.G_564_y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 14.0f, 4.0f, p_i47226_1_);
        this.G_564_y.n_1700_B(2.5f, 10.0f, 6.0f);
        this.P_1922_E = new e_4189_z(this, 29, 29);
        this.P_1922_E.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 14.0f, 4.0f, p_i47226_1_);
        this.P_1922_E.n_1700_B(-2.5f, 10.0f, -4.0f);
        this.u_1723_Y = new e_4189_z(this, 29, 29);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 14.0f, 4.0f, p_i47226_1_);
        this.u_1723_Y.n_1700_B(2.5f, 10.0f, -4.0f);
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
        boolean flag;
        this.n_1700_B.u_1723_Y = headPitch * ((float)Math.PI / 180);
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.J_1907_R.u_1723_Y = 1.5707964f;
        this.R_4764_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.v_4262_N.s_956_w = flag = !((AgableMob)entityIn).d_() && ((W_3443_Y)entityIn).V_1176_p();
        this.w_1484_f.s_956_w = flag;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.M_182_A) {
            float f = 2.0f;
            matrixStackIn.n_1700_B();
            float f1 = 0.7f;
            matrixStackIn.n_1700_B(0.71428573f, 0.64935064f, 0.7936508f);
            matrixStackIn.n_1700_B(0.0, 1.3125, (double)0.22f);
            this.n_1700_B.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            float f2 = 1.1f;
            matrixStackIn.n_1700_B(0.625f, 0.45454544f, 0.45454544f);
            matrixStackIn.n_1700_B(0.0, 2.0625, 0.0);
            this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            matrixStackIn.J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.45454544f, 0.41322312f, 0.45454544f);
            matrixStackIn.n_1700_B(0.0, 2.0625, 0.0);
            ImmutableList.of((Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f).forEach(p_228280_8_ -> p_228280_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.J_1907_R();
        } else {
            ImmutableList.of((Object)this.n_1700_B, (Object)this.J_1907_R, (Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.w_1484_f).forEach(p_228279_8_ -> p_228279_8_.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
        }
    }
}


