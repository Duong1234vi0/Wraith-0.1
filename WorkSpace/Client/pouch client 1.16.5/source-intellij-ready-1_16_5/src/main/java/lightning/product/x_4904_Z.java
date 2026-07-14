/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.RenderLayer;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.EntityModel;
import lightning.product.T_4002_g;
import lightning.product.Z_1993_T;
import lightning.product.Emotions;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_4203_m;
import lightning.product.k_4231_L;
import lightning.product.o_3091_w;
import lightning.product.r_4811_B;

public class x_4904_Z<T extends r_4811_B, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    public x_4904_Z(j_4203_m<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Z_1993_T itemstack1;
        Emotions emotionsPreview = Emotions.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            return;
        }
        boolean flag = ((r_4811_B)entitylivingbaseIn).d_2169_p() == k_4231_L.J_1907_R;
        Z_1993_T itemstack = flag ? ((r_4811_B)entitylivingbaseIn).S_4035_N() : ((r_4811_B)entitylivingbaseIn).A_2714_y();
        Z_1993_T z_1993_T = itemstack1 = flag ? ((r_4811_B)entitylivingbaseIn).A_2714_y() : ((r_4811_B)entitylivingbaseIn).S_4035_N();
        if (!itemstack.n_1700_B() || !itemstack1.n_1700_B()) {
            matrixStackIn.n_1700_B();
            if (((EntityModel)this.getEntityModel()).M_182_A) {
                float f = 0.5f;
                matrixStackIn.n_1700_B(0.0, 0.75, 0.0);
                matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            }
            this.n_1700_B((r_4811_B)entitylivingbaseIn, itemstack1, ItemTransforms.J_1907_R.R_4764_Y, k_4231_L.J_1907_R, matrixStackIn, bufferIn, packedLightIn);
            this.n_1700_B((r_4811_B)entitylivingbaseIn, itemstack, ItemTransforms.J_1907_R.J_1907_R, k_4231_L.n_1700_B, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.J_1907_R();
        }
    }

    private void n_1700_B(r_4811_B p_229135_1_, Z_1993_T p_229135_2_, ItemTransforms.J_1907_R p_229135_3_, k_4231_L p_229135_4_, g_221_o p_229135_5_, o_3091_w p_229135_6_, int p_229135_7_) {
        if (!p_229135_2_.n_1700_B()) {
            p_229135_5_.n_1700_B();
            ((T_4002_g)this.getEntityModel()).n_1700_B(p_229135_4_, p_229135_5_);
            p_229135_5_.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-90.0f));
            p_229135_5_.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
            boolean flag = p_229135_4_ == k_4231_L.n_1700_B;
            p_229135_5_.n_1700_B((double)((float)(flag ? -1 : 1) / 16.0f), 0.125, -0.625);
            MinecraftClient.A_4115_X().A_1038_p().n_1700_B(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
            p_229135_5_.J_1907_R();
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



