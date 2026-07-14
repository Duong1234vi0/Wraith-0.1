/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2364_U;
import lightning.product.RenderLayer;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_3224_L;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.IronGolemModel;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;

public class q_2700_F
extends RenderLayer<D_2364_U, IronGolemModel<D_2364_U>> {
    public q_2700_F(j_4203_m<D_2364_U, IronGolemModel<D_2364_U>> p_i50935_1_) {
        super(p_i50935_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, D_2364_U entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.y_2447_C() != 0) {
            matrixStackIn.n_1700_B();
            e_4189_z modelrenderer = ((IronGolemModel)this.getEntityModel()).J_1907_R();
            modelrenderer.n_1700_B(matrixStackIn);
            matrixStackIn.n_1700_B(-1.1875, 1.0625, -0.9375);
            matrixStackIn.n_1700_B(0.5, 0.5, 0.5);
            float f = 0.5f;
            matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-90.0f));
            matrixStackIn.n_1700_B(-0.5, -0.5, -0.5);
            MinecraftClient.A_4115_X().z_1333_t().n_1700_B(a_3742_W.RealmsResetNormalWorldScreen.multiplayerClientSuggestionProvider(), matrixStackIn, bufferIn, packedLightIn, Z_3224_L.n_1700_B);
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (D_2364_U)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



