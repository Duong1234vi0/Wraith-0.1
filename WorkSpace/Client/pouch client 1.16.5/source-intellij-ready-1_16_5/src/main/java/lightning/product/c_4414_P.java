/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Z_2049_e;
import lightning.product.Z_3224_L;
import lightning.product.c_1514_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.n_4637_L;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.w_2040_b;
import net.optifine.Config;
import net.optifine.CustomColors;

public class c_4414_P
extends Z_2049_e<n_4637_L> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/experience_orb.png");
    private static final o_2576_A v_4262_N = o_2576_A.u_1723_Y(n_1700_B);

    public c_4414_P(w_2040_b renderManagerIn) {
        super(renderManagerIn);
        this.R_4764_Y = 0.15f;
        this.G_564_y = 0.75f;
    }

    @Override
    protected int n_1700_B(n_4637_L entityIn, c_1514_x partialTicks) {
        return u_530_F.n_1700_B(super.n_1700_B(entityIn, partialTicks) + 7, 0, 15);
    }

    @Override
    public void n_1700_B(n_4637_L entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        int l1;
        matrixStackIn.n_1700_B();
        int i = entityIn.u_1723_Y();
        float f = (float)(i % 4 * 16 + 0) / 64.0f;
        float f1 = (float)(i % 4 * 16 + 16) / 64.0f;
        float f2 = (float)(i / 4 * 16 + 0) / 64.0f;
        float f3 = (float)(i / 4 * 16 + 16) / 64.0f;
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        float f7 = 255.0f;
        float f8 = ((float)entityIn.n_1700_B + partialTicks) / 2.0f;
        if (Config.isCustomColors()) {
            f8 = CustomColors.getXpOrbTimer(f8);
        }
        int j = (int)((u_530_F.n_1700_B(f8 + 0.0f) + 1.0f) * 0.5f * 255.0f);
        int k = 255;
        int l = (int)((u_530_F.n_1700_B(f8 + 4.1887903f) + 1.0f) * 0.1f * 255.0f);
        matrixStackIn.n_1700_B(0.0, (double)0.1f, 0.0);
        matrixStackIn.n_1700_B(this.J_1907_R.R_4764_Y());
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f));
        float f9 = 0.3f;
        matrixStackIn.n_1700_B(0.3f, 0.3f, 0.3f);
        D_4792_h ivertexbuilder = bufferIn.getBuffer(v_4262_N);
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        int i1 = j;
        int j1 = 255;
        int k1 = l;
        if (Config.isCustomColors() && (l1 = CustomColors.getXpOrbColor(f8)) >= 0) {
            i1 = l1 >> 16 & 0xFF;
            j1 = l1 >> 8 & 0xFF;
            k1 = l1 >> 0 & 0xFF;
        }
        c_4414_P.n_1700_B(ivertexbuilder, matrix4f, matrix3f, -0.5f, -0.25f, i1, j1, k1, f, f3, packedLightIn);
        c_4414_P.n_1700_B(ivertexbuilder, matrix4f, matrix3f, 0.5f, -0.25f, i1, j1, k1, f1, f3, packedLightIn);
        c_4414_P.n_1700_B(ivertexbuilder, matrix4f, matrix3f, 0.5f, 0.75f, i1, j1, k1, f1, f2, packedLightIn);
        c_4414_P.n_1700_B(ivertexbuilder, matrix4f, matrix3f, -0.5f, 0.75f, i1, j1, k1, f, f2, packedLightIn);
        matrixStackIn.J_1907_R();
        super.n_1700_B(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void n_1700_B(D_4792_h bufferIn, D_1098_v matrixIn, o_1290_k matrixNormalIn, float x, float y, int red, int green, int blue, float texU, float texV, int packedLight) {
        bufferIn.n_1700_B(matrixIn, x, y, 0.0f).color(red, green, blue, 128).tex(texU, texV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(packedLight).n_1700_B(matrixNormalIn, 0.0f, 1.0f, 0.0f).endVertex();
    }

    @Override
    public g_2336_b n_1700_B(n_4637_L entity) {
        return n_1700_B;
    }
}

