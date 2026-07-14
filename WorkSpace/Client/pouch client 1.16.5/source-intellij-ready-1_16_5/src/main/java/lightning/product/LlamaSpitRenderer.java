/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.r_214_x;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.LlamaSpitModel;

public class LlamaSpitRenderer
extends Z_2049_e<r_214_x> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/llama/spit.png");
    private final LlamaSpitModel<r_214_x> v_4262_N = new LlamaSpitModel();

    public LlamaSpitRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(r_214_x entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, (double)0.15f, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(partialTicks, entityIn.j_276_v, entityIn.p_178_J) - 90.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.v_4262_N(partialTicks, entityIn.UploadStatus, entityIn.f_4016_n)));
        this.v_4262_N.n_1700_B(entityIn, partialTicks, 0.0f, -0.1f, 0.0f, 0.0f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.v_4262_N.getRenderType(n_1700_B));
        this.v_4262_N.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(r_214_x entity) {
        return n_1700_B;
    }
}


