/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.E_4925_L;
import lightning.product.H_3330_w;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.TridentModel;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class ThrownTridentRenderer
extends Z_2049_e<E_4925_L> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/trident.png");
    private final TridentModel v_4262_N = new TridentModel();

    public ThrownTridentRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(E_4925_L entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(partialTicks, entityIn.j_276_v, entityIn.p_178_J) - 90.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.v_4262_N(partialTicks, entityIn.UploadStatus, entityIn.f_4016_n) + 90.0f));
        D_4792_h ivertexbuilder = H_3330_w.R_4764_Y(bufferIn, this.v_4262_N.getRenderType(this.n_1700_B(entityIn)), false, entityIn.w_1457_N());
        this.v_4262_N.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(E_4925_L entity) {
        return n_1700_B;
    }
}


