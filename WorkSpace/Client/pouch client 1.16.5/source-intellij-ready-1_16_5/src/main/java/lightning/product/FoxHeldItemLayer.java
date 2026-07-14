/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.e_1174_E;
import lightning.product.g_1253_u;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.FoxModel;

public class FoxHeldItemLayer
extends RenderLayer<g_1253_u, FoxModel<g_1253_u>> {
    public FoxHeldItemLayer(j_4203_m<g_1253_u, FoxModel<g_1253_u>> p_i50938_1_) {
        super(p_i50938_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, g_1253_u entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.z_2372_L();
        boolean flag1 = entitylivingbaseIn.d_();
        matrixStackIn.n_1700_B();
        if (flag1) {
            float f = 0.75f;
            matrixStackIn.n_1700_B(0.75f, 0.75f, 0.75f);
            matrixStackIn.n_1700_B(0.0, 0.5, (double)0.209375f);
        }
        matrixStackIn.n_1700_B((double)(((FoxModel)this.getEntityModel()).n_1700_B.R_4764_Y / 16.0f), (double)(((FoxModel)this.getEntityModel()).n_1700_B.G_564_y / 16.0f), (double)(((FoxModel)this.getEntityModel()).n_1700_B.P_1922_E / 16.0f));
        float f1 = entitylivingbaseIn.c_3005_b(partialTicks);
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.J_1907_R(f1));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(netHeadYaw));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(headPitch));
        if (entitylivingbaseIn.d_()) {
            if (flag) {
                matrixStackIn.n_1700_B((double)0.4f, (double)0.26f, (double)0.15f);
            } else {
                matrixStackIn.n_1700_B((double)0.06f, (double)0.26f, -0.5);
            }
        } else if (flag) {
            matrixStackIn.n_1700_B((double)0.46f, (double)0.26f, (double)0.22f);
        } else {
            matrixStackIn.n_1700_B((double)0.06f, (double)0.27f, -0.5);
        }
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
        if (flag) {
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
        }
        Z_1993_T itemstack = entitylivingbaseIn.J_1907_R(e_1174_E.n_1700_B);
        MinecraftClient.A_4115_X().A_1038_p().n_1700_B(entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.J_1907_R();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (g_1253_u)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



