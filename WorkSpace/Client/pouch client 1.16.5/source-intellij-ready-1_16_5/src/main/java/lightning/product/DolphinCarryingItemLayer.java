/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.DolphinModel;
import lightning.product.N_4263_v;
import lightning.product.Y_559_r;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.k_4231_L;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class DolphinCarryingItemLayer
extends RenderLayer<Y_559_r, DolphinModel<Y_559_r>> {
    public DolphinCarryingItemLayer(j_4203_m<Y_559_r, DolphinModel<Y_559_r>> p_i50944_1_) {
        super(p_i50944_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, Y_559_r entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.d_2169_p() == k_4231_L.J_1907_R;
        matrixStackIn.n_1700_B();
        float f = 1.0f;
        float f1 = -1.0f;
        float f2 = u_530_F.P_1922_E(entitylivingbaseIn.f_4016_n) / 60.0f;
        if (entitylivingbaseIn.f_4016_n < 0.0f) {
            matrixStackIn.n_1700_B(0.0, (double)(1.0f - f2 * 0.5f), (double)(-1.0f + f2 * 0.5f));
        } else {
            matrixStackIn.n_1700_B(0.0, (double)(1.0f + f2 * 0.8f), (double)(-1.0f + f2 * 0.2f));
        }
        Z_1993_T itemstack = flag ? entitylivingbaseIn.A_2714_y() : entitylivingbaseIn.S_4035_N();
        MinecraftClient.A_4115_X().A_1038_p().n_1700_B(entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.J_1907_R();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (Y_559_r)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



