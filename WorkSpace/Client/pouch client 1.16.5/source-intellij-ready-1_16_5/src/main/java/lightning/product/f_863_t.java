/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.e_1174_E;
import lightning.product.g_221_o;
import lightning.product.j_3013_R;
import lightning.product.j_4203_m;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.PandaModel;

public class f_863_t
extends RenderLayer<j_3013_R, PandaModel<j_3013_R>> {
    public f_863_t(j_4203_m<j_3013_R, PandaModel<j_3013_R>> p_i50930_1_) {
        super(p_i50930_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, j_3013_R entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack = entitylivingbaseIn.J_1907_R(e_1174_E.n_1700_B);
        if (entitylivingbaseIn.V_1176_p() && !entitylivingbaseIn.Module()) {
            float f = -0.6f;
            float f1 = 1.4f;
            if (entitylivingbaseIn.J_3635_s()) {
                f -= 0.2f * u_530_F.n_1700_B(ageInTicks * 0.6f) + 0.2f;
                f1 -= 0.09f * u_530_F.n_1700_B(ageInTicks * 0.6f);
            }
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B((double)0.1f, (double)f1, (double)f);
            MinecraftClient.A_4115_X().A_1038_p().n_1700_B(entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.w_1484_f, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (j_3013_R)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



