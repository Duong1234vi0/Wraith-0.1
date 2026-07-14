/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.RenderLayer;
import lightning.product.PlayerModel;
import lightning.product.N_4263_v;
import lightning.product.Emotions;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.o_3091_w;
import lightning.product.o_4479_Q;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public abstract class W_1980_j<T extends r_4811_B, M extends PlayerModel<T>>
extends RenderLayer<T, M> {
    public W_1980_j(o_4479_Q<T, M> p_i226041_1_) {
        super(p_i226041_1_);
    }

    protected abstract int n_1700_B(T var1);

    protected abstract void n_1700_B(g_221_o var1, o_3091_w var2, int var3, N_4263_v var4, float var5, float var6, float var7, float var8);

    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Emotions emotionsPreview = Emotions.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            return;
        }
        int i = this.n_1700_B(entitylivingbaseIn);
        Random random = new Random(((N_4263_v)entitylivingbaseIn).j_276_v());
        if (i > 0) {
            for (int j = 0; j < i; ++j) {
                matrixStackIn.n_1700_B();
                e_4189_z modelrenderer = ((PlayerModel)this.getEntityModel()).n_1700_B(random);
                e_4189_z.n_1700_B modelrenderer$modelbox = modelrenderer.n_1700_B(random);
                modelrenderer.n_1700_B(matrixStackIn);
                float f = random.nextFloat();
                float f1 = random.nextFloat();
                float f2 = random.nextFloat();
                float f3 = u_530_F.v_4262_N(f, modelrenderer$modelbox.n_1700_B, modelrenderer$modelbox.G_564_y) / 16.0f;
                float f4 = u_530_F.v_4262_N(f1, modelrenderer$modelbox.J_1907_R, modelrenderer$modelbox.P_1922_E) / 16.0f;
                float f5 = u_530_F.v_4262_N(f2, modelrenderer$modelbox.R_4764_Y, modelrenderer$modelbox.u_1723_Y) / 16.0f;
                matrixStackIn.n_1700_B((double)f3, (double)f4, (double)f5);
                f = -1.0f * (f * 2.0f - 1.0f);
                f1 = -1.0f * (f1 * 2.0f - 1.0f);
                f2 = -1.0f * (f2 * 2.0f - 1.0f);
                this.n_1700_B(matrixStackIn, bufferIn, packedLightIn, (N_4263_v)entitylivingbaseIn, f, f1, f2, partialTicks);
                matrixStackIn.J_1907_R();
            }
        }
    }

    @Override
    public /* synthetic */ void render(g_221_o g_221_o2, o_3091_w o_3091_w2, int n, N_4263_v n_4263_v, float f, float f2, float f3, float f4, float f5, float f6) {
        this.n_1700_B(g_221_o2, o_3091_w2, n, (r_4811_B)n_4263_v, f, f2, f3, f4, f5, f6);
    }
}



