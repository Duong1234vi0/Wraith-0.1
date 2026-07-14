/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;

public class ShieldModel
extends v_3569_v {
    private final e_4189_z n_1700_B;
    private final e_4189_z J_1907_R;

    public ShieldModel() {
        super(o_2576_A::J_1907_R);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-6.0f, -11.0f, -2.0f, 12.0f, 22.0f, 1.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 26, 0);
        this.J_1907_R.n_1700_B(-1.0f, -3.0f, -1.0f, 2.0f, 6.0f, 6.0f, 0.0f);
    }

    public e_4189_z n_1700_B() {
        return this.n_1700_B;
    }

    public e_4189_z J_1907_R() {
        return this.J_1907_R;
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.n_1700_B.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}


