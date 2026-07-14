/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;

public class SkullModel
extends v_3569_v {
    protected final e_4189_z n_1700_B;

    public SkullModel() {
        this(0, 35, 64, 64);
    }

    public SkullModel(int p_i51060_1_, int p_i51060_2_, int p_i51060_3_, int p_i51060_4_) {
        super(o_2576_A::w_1484_f);
        this.textureWidth = p_i51060_3_;
        this.textureHeight = p_i51060_4_;
        this.n_1700_B = new e_4189_z(this, p_i51060_1_, p_i51060_2_);
        this.n_1700_B.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 0.0f, 0.0f);
    }

    public void n_1700_B(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
        this.n_1700_B.v_4262_N = p_225603_2_ * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = p_225603_3_ * ((float)Math.PI / 180);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.n_1700_B.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}


