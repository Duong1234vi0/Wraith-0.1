/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemTransforms;
import lightning.product.H_3330_w;
import lightning.product.J_3992_v;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.w_2040_b;

public class FireworkEntityRenderer
extends Z_2049_e<J_3992_v> {
    private final H_3330_w n_1700_B;

    public FireworkEntityRenderer(w_2040_b renderManagerIn, H_3330_w itemRendererIn) {
        super(renderManagerIn);
        this.n_1700_B = itemRendererIn;
    }

    @Override
    public void n_1700_B(J_3992_v entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        if (entityIn.P_1922_E()) {
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
        }
        this.n_1700_B.n_1700_B(entityIn.n_1700_B(), ItemTransforms.J_1907_R.w_1484_f, packedLightIn, Z_3224_L.n_1700_B, matrixStackIn, bufferIn);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(J_3992_v entity) {
        return L_3848_p.n_1700_B;
    }
}


