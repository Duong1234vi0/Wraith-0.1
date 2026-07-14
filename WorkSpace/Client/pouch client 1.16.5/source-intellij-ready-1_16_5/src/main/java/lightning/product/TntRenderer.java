/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.a_3742_W;
import lightning.product.c_4467_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.PrimedTnt;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class TntRenderer
extends Z_2049_e<PrimedTnt> {
    public TntRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.5f;
    }

    @Override
    public void n_1700_B(PrimedTnt entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, 0.5, 0.0);
        if ((float)entityIn.v_4262_N() - partialTicks + 1.0f < 10.0f) {
            float f = 1.0f - ((float)entityIn.v_4262_N() - partialTicks + 1.0f) / 10.0f;
            f = u_530_F.n_1700_B(f, 0.0f, 1.0f);
            f *= f;
            f *= f;
            float f1 = 1.0f + f * 0.3f;
            matrixStackIn.n_1700_B(f1, f1, f1);
        }
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-90.0f));
        matrixStackIn.n_1700_B(-0.5, -0.5, 0.5);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f));
        c_4467_L.n_1700_B(a_3742_W.TextRenderingUtils.multiplayerClientSuggestionProvider(), matrixStackIn, bufferIn, packedLightIn, entityIn.v_4262_N() / 5 % 2 == 0);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(PrimedTnt entity) {
        return L_3848_p.n_1700_B;
    }
}


