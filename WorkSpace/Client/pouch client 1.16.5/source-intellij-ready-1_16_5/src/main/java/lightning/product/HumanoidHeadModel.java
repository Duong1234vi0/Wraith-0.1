/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.SkullModel;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;

public class HumanoidHeadModel
extends SkullModel {
    private final e_4189_z J_1907_R = new e_4189_z(this, 32, 0);

    public HumanoidHeadModel() {
        super(0, 0, 64, 64);
        this.J_1907_R.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.25f);
        this.J_1907_R.n_1700_B(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void n_1700_B(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
        super.n_1700_B(p_225603_1_, p_225603_2_, p_225603_3_);
        this.J_1907_R.v_4262_N = this.n_1700_B.v_4262_N;
        this.J_1907_R.u_1723_Y = this.n_1700_B.u_1723_Y;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}


