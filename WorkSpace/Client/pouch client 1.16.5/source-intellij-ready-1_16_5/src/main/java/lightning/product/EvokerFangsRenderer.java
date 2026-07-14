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
import lightning.product.t_950_g;
import lightning.product.w_2040_b;
import lightning.product.EvokerFangsModel;

public class EvokerFangsRenderer
extends Z_2049_e<t_950_g> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/illager/evoker_fangs.png");
    private final EvokerFangsModel<t_950_g> v_4262_N = new EvokerFangsModel();

    public EvokerFangsRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(t_950_g entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        float f = entityIn.n_1700_B(partialTicks);
        if (f != 0.0f) {
            float f1 = 2.0f;
            if (f > 0.9f) {
                f1 = (float)((double)f1 * ((1.0 - (double)f) / (double)0.1f));
            }
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f - entityIn.p_178_J));
            matrixStackIn.n_1700_B(-f1, -f1, f1);
            float f2 = 0.03125f;
            matrixStackIn.n_1700_B(0.0, (double)-0.626f, 0.0);
            matrixStackIn.n_1700_B(0.5f, 0.5f, 0.5f);
            this.v_4262_N.n_1700_B(entityIn, f, 0.0f, 0.0f, entityIn.p_178_J, entityIn.f_4016_n);
            D_4792_h ivertexbuilder = bufferIn.getBuffer(this.v_4262_N.getRenderType(n_1700_B));
            this.v_4262_N.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
            matrixStackIn.J_1907_R();
            super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public g_2336_b n_1700_B(t_950_g entity) {
        return n_1700_B;
    }
}


