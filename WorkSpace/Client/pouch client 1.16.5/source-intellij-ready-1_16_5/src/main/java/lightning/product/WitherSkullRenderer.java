/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.SkullModel;
import lightning.product.WitherSkull;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class WitherSkullRenderer
extends Z_2049_e<WitherSkull> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/wither/wither_invulnerable.png");
    private static final g_2336_b v_4262_N = new g_2336_b("textures/entity/wither/wither.png");
    private final SkullModel w_1484_f = new SkullModel();

    public WitherSkullRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    protected int n_1700_B(WitherSkull entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public void n_1700_B(WitherSkull entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        float f = u_530_F.t_148_a(entityIn.j_276_v, entityIn.p_178_J, partialTicks);
        float f1 = u_530_F.v_4262_N(partialTicks, entityIn.UploadStatus, entityIn.f_4016_n);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.w_1484_f.getRenderType(this.n_1700_B(entityIn)));
        this.w_1484_f.n_1700_B(0.0f, f, f1);
        this.w_1484_f.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(WitherSkull entity) {
        return entity.P_1922_E() ? n_1700_B : v_4262_N;
    }
}


