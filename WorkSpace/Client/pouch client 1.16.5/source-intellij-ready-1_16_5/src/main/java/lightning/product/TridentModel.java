/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.v_3569_v;

public class TridentModel
extends v_3569_v {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/trident.png");
    private final e_4189_z J_1907_R = new e_4189_z(32, 32, 0, 6);

    public TridentModel() {
        super(o_2576_A::J_1907_R);
        this.J_1907_R.n_1700_B(-0.5f, 2.0f, -0.5f, 1.0f, 25.0f, 1.0f, 0.0f);
        e_4189_z modelrenderer = new e_4189_z(32, 32, 4, 0);
        modelrenderer.n_1700_B(-1.5f, 0.0f, -0.5f, 3.0f, 2.0f, 1.0f);
        this.J_1907_R.J_1907_R(modelrenderer);
        e_4189_z modelrenderer1 = new e_4189_z(32, 32, 4, 3);
        modelrenderer1.n_1700_B(-2.5f, -3.0f, -0.5f, 1.0f, 4.0f, 1.0f);
        this.J_1907_R.J_1907_R(modelrenderer1);
        e_4189_z modelrenderer2 = new e_4189_z(32, 32, 0, 0);
        modelrenderer2.n_1700_B(-0.5f, -4.0f, -0.5f, 1.0f, 4.0f, 1.0f, 0.0f);
        this.J_1907_R.J_1907_R(modelrenderer2);
        e_4189_z modelrenderer3 = new e_4189_z(32, 32, 4, 3);
        modelrenderer3.t_148_a = true;
        modelrenderer3.n_1700_B(1.5f, -3.0f, -0.5f, 1.0f, 4.0f, 1.0f);
        this.J_1907_R.J_1907_R(modelrenderer3);
    }

    @Override
    public void render(g_221_o matrixStackIn, D_4792_h bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.J_1907_R.n_1700_B(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}


