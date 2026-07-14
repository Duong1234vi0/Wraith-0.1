/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.M_1336_P;
import lightning.product.N_1077_C;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.SnowGolemModel;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;

public class n_4684_O
extends RenderLayer<N_1077_C, SnowGolemModel<N_1077_C>> {
    public n_4684_O(j_4203_m<N_1077_C, SnowGolemModel<N_1077_C>> p_i50922_1_) {
        super(p_i50922_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, N_1077_C entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.F_3572_x() && entitylivingbaseIn.y_4642_Y()) {
            matrixStackIn.n_1700_B();
            ((SnowGolemModel)this.getEntityModel()).J_1907_R().n_1700_B(matrixStackIn);
            float f = 0.625f;
            matrixStackIn.n_1700_B(0.0, -0.34375, 0.0);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
            matrixStackIn.n_1700_B(0.625f, -0.625f, -0.625f);
            Z_1993_T itemstack = new Z_1993_T(a_3742_W.X_2048_Y);
            MinecraftClient.A_4115_X().r_715_M().n_1700_B(entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.u_1723_Y, false, matrixStackIn, bufferIn, entitylivingbaseIn.O_508_d, packedLightIn, o_4479_Q.R_4764_Y(entitylivingbaseIn, 0.0f));
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (N_1077_C)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



