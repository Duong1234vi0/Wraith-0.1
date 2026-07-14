/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.BoatModel;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.g_1462_f;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3270_j;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import lightning.product.w_3785_E;

public class m_4813_h
extends Z_2049_e<g_1462_f> {
    private static final g_2336_b[] v_4262_N = new g_2336_b[]{new g_2336_b("textures/entity/boat/oak.png"), new g_2336_b("textures/entity/boat/spruce.png"), new g_2336_b("textures/entity/boat/birch.png"), new g_2336_b("textures/entity/boat/jungle.png"), new g_2336_b("textures/entity/boat/acacia.png"), new g_2336_b("textures/entity/boat/dark_oak.png")};
    protected final BoatModel n_1700_B = new BoatModel();

    public m_4813_h(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.8f;
    }

    @Override
    public void n_1700_B(g_1462_f entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        float f2;
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.Z_875_P);
        A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.0, 0.375, 0.0);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - entityYaw));
        float f = (float)entityIn.u_2550_I() - partialTicks;
        float f1 = entityIn.t_148_a() - partialTicks;
        if (f1 < 0.0f) {
            f1 = 0.0f;
        }
        if (f > 0.0f) {
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.n_1700_B(f) * f * f1 / 10.0f * (float)entityIn.h_1847_R()));
        }
        if (!u_530_F.n_1700_B(f2 = entityIn.G_564_y(partialTicks), 0.0f)) {
            matrixStackIn.n_1700_B(new w_3785_E(new M_1336_P(1.0f, 0.0f, 1.0f), entityIn.G_564_y(partialTicks), true));
        }
        matrixStackIn.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(90.0f));
        this.n_1700_B.n_1700_B(entityIn, partialTicks, 0.0f, -0.1f, 0.0f, 0.0f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(this.n_1700_B.getRenderType(this.n_1700_B(entityIn)));
        this.n_1700_B.render(matrixStackIn, ivertexbuilder, packedLightIn, Z_3224_L.n_1700_B, 1.0f, 1.0f, 1.0f, 1.0f);
        if (!entityIn.z_1737_N()) {
            D_4792_h ivertexbuilder1 = bufferIn.getBuffer(o_2576_A.P_4830_p());
            this.n_1700_B.R_4764_Y().n_1700_B(matrixStackIn, ivertexbuilder1, packedLightIn, Z_3224_L.n_1700_B);
        }
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public g_2336_b n_1700_B(g_1462_f entity) {
        return v_4262_N[entity.Q_4569_t().ordinal()];
    }
}


