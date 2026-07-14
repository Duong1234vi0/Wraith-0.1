/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;

public class CrossedArmsItemLayer<T extends r_4811_B, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    public CrossedArmsItemLayer(j_4203_m<T, M> p_i226037_1_) {
        super(p_i226037_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, (double)0.4f, (double)-0.4f);
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(180.0f));
        Z_1993_T itemstack = ((r_4811_B)entitylivingbaseIn).J_1907_R(e_1174_E.n_1700_B);
        MinecraftClient.A_4115_X().A_1038_p().n_1700_B((r_4811_B)entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.J_1907_R();
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



