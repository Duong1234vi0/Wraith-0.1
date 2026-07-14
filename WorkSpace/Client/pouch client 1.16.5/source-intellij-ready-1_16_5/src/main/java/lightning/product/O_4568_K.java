/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RenderLayer;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.M_914_T;
import lightning.product.N_4263_v;
import lightning.product.Z_3224_L;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.w_2498_n;

public class O_4568_K
extends RenderLayer<M_914_T, w_2498_n<M_914_T>> {
    public O_4568_K(j_4203_m<M_914_T, w_2498_n<M_914_T>> p_i50949_1_) {
        super(p_i50949_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, M_914_T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        K_4074_S blockstate = entitylivingbaseIn.J_3635_s();
        if (blockstate != null) {
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(0.0, 0.6875, -0.75);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(20.0f));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(45.0f));
            matrixStackIn.n_1700_B(0.25, 0.1875, 0.25);
            float f = 0.5f;
            matrixStackIn.n_1700_B(-0.5f, -0.5f, 0.5f);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f));
            MinecraftClient.A_4115_X().z_1333_t().n_1700_B(blockstate, matrixStackIn, bufferIn, packedLightIn, Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (M_914_T)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



