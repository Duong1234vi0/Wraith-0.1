/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.SkullModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;

public class DragonHeadModel
extends SkullModel {
    private final e_4189_z J_1907_R;
    private final e_4189_z R_4764_Y;

    public DragonHeadModel(float p_i46588_1_) {
        this.textureWidth = 256;
        this.textureHeight = 256;
        float f = -16.0f;
        this.J_1907_R = new e_4189_z(this);
        this.J_1907_R.n_1700_B("upperlip", -6.0f, -1.0f, -24.0f, 12, 5, 16, p_i46588_1_, 176, 44);
        this.J_1907_R.n_1700_B("upperhead", -8.0f, -8.0f, -10.0f, 16, 16, 16, p_i46588_1_, 112, 30);
        this.J_1907_R.t_148_a = true;
        this.J_1907_R.n_1700_B("scale", -5.0f, -12.0f, -4.0f, 2, 4, 6, p_i46588_1_, 0, 0);
        this.J_1907_R.n_1700_B("nostril", -5.0f, -3.0f, -22.0f, 2, 2, 4, p_i46588_1_, 112, 0);
        this.J_1907_R.t_148_a = false;
        this.J_1907_R.n_1700_B("scale", 3.0f, -12.0f, -4.0f, 2, 4, 6, p_i46588_1_, 0, 0);
        this.J_1907_R.n_1700_B("nostril", 3.0f, -3.0f, -22.0f, 2, 2, 4, p_i46588_1_, 112, 0);
        this.R_4764_Y = new e_4189_z(this);
        this.R_4764_Y.n_1700_B(0.0f, 4.0f, -8.0f);
        this.R_4764_Y.n_1700_B("jaw", -6.0f, 0.0f, -16.0f, 12, 4, 16, p_i46588_1_, 176, 65);
        this.J_1907_R.J_1907_R(this.R_4764_Y);
    }

    @Override
    public void n_1700_B(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
        this.R_4764_Y.u_1723_Y = (float)(Math.sin(p_225603_1_ * (float)Math.PI * 0.2f) + 1.0) * 0.2f;
        this.J_1907_R.v_4262_N = p_225603_2_ * ((float)Math.PI / 180);
        this.J_1907_R.u_1723_Y = p_225603_3_ * ((float)Math.PI / 180);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, (double)-0.374375f, 0.0);
        matrixStackIn.n_1700_B(0.75f, 0.75f, 0.75f);
        this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        matrixStackIn.J_1907_R();
    }
}


