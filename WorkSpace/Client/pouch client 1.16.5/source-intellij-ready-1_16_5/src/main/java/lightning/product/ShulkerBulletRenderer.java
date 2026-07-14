/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import lightning.product.L_2837_o;
import lightning.product.M_1336_P;
import lightning.product.X_2599_Q;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;

public class ShulkerBulletRenderer
extends Z_2049_e<L_2837_o> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/shulker/spark.png");
    private static final o_2576_A v_4262_N = o_2576_A.w_1484_f(n_1700_B);
    private final X_2599_Q<L_2837_o> w_1484_f = new X_2599_Q();

    public ShulkerBulletRenderer(w_2040_b manager) {
        super(manager);
    }

    @Override
    protected int n_1700_B(L_2837_o entityIn, c_1514_x partialTicks) {
        return 15;
    }

    @Override
    public void n_1700_B(L_2837_o entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        matrixStackIn.n_1700_B();
        float f = u_530_F.t_148_a(entityIn.j_276_v, entityIn.p_178_J, partialTicks);
        float f1 = u_530_F.v_4262_N(partialTicks, entityIn.UploadStatus, entityIn.f_4016_n);
        float f2 = (float)entityIn.RealmsWorldResetDto + partialTicks;
        matrixStackIn.n_1700_B(0.0, (double)0.15f, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.n_1700_B(f2 * 0.1f) * 180.0f));
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.J_1907_R(f2 * 0.1f) * 180.0f));
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.n_1700_B(f2 * 0.15f) * 360.0f));
        matrixStackIn.n_1700_B(-0.5f, -0.5f, 0.5f);
        this.w_1484_f.n_1700_B(entityIn, 0.0f, 0.0f, 0.0f, f, f1);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.w_1484_f.getRenderType(n_1700_B));
        this.w_1484_f.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStackIn.n_1700_B(1.5f, 1.5f, 1.5f);
        D_4792_h ivertexbuilder1 = bufferIn.getBuffer(v_4262_N);
        this.w_1484_f.render(matrixStackIn, ivertexbuilder1, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 0.15f);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(L_2837_o entity) {
        return n_1700_B;
    }
}


