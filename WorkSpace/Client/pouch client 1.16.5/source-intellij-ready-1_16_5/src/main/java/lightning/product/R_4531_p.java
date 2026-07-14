/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.Z_3224_L;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_1802_R;
import lightning.product.o_1290_k;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;
import lightning.product.x_3974_Q;

public class R_4531_p
extends l_1802_R<x_3974_Q> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/entity/beacon_beam.png");

    public R_4531_p(f_2689_h rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void n_1700_B(x_3974_Q tileEntityIn, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int combinedLightIn, int combinedOverlayIn) {
        long i = tileEntityIn.c_3005_b().X_933_l();
        List<x_3974_Q.n_1700_B> list = tileEntityIn.v_4262_N();
        int j = 0;
        for (int k = 0; k < list.size(); ++k) {
            x_3974_Q.n_1700_B beacontileentity$beamsegment = list.get(k);
            R_4531_p.n_1700_B(matrixStackIn, bufferIn, partialTicks, i, j, k == list.size() - 1 ? 1024 : beacontileentity$beamsegment.R_4764_Y(), beacontileentity$beamsegment.J_1907_R());
            j += beacontileentity$beamsegment.R_4764_Y();
        }
    }

    private static void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, float partialTicks, long totalWorldTime, int yOffset, int height, float[] colors) {
        R_4531_p.n_1700_B(matrixStackIn, bufferIn, n_1700_B, partialTicks, 1.0f, totalWorldTime, yOffset, height, colors, 0.2f, 0.25f);
    }

    public static void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, g_2336_b textureLocation, float partialTicks, float textureScale, long totalWorldTime, int yOffset, int height, float[] colors, float beamRadius, float glowRadius) {
        int i = yOffset + height;
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(0.5, 0.0, 0.5);
        float f = (float)Math.floorMod(totalWorldTime, 40L) + partialTicks;
        float f1 = height < 0 ? f : -f;
        float f2 = u_530_F.w_1484_f(f1 * 0.2f - (float)u_530_F.G_564_y(f1 * 0.1f));
        float f3 = colors[0];
        float f4 = colors[1];
        float f5 = colors[2];
        matrixStackIn.n_1700_B();
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f * 2.25f - 45.0f));
        float f6 = 0.0f;
        float f8 = 0.0f;
        float f9 = -beamRadius;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = -beamRadius;
        float f13 = 0.0f;
        float f14 = 1.0f;
        float f15 = -1.0f + f2;
        float f16 = (float)height * textureScale * (0.5f / beamRadius) + f15;
        R_4531_p.n_1700_B(matrixStackIn, bufferIn.getBuffer(o_2576_A.G_564_y(textureLocation, false)), f3, f4, f5, 1.0f, yOffset, i, 0.0f, beamRadius, beamRadius, 0.0f, f9, 0.0f, 0.0f, f12, 0.0f, 1.0f, f16, f15);
        matrixStackIn.J_1907_R();
        f6 = -glowRadius;
        float f7 = -glowRadius;
        f8 = -glowRadius;
        f9 = -glowRadius;
        f13 = 0.0f;
        f14 = 1.0f;
        f15 = -1.0f + f2;
        f16 = (float)height * textureScale + f15;
        R_4531_p.n_1700_B(matrixStackIn, bufferIn.getBuffer(o_2576_A.G_564_y(textureLocation, true)), f3, f4, f5, 0.125f, yOffset, i, f6, f7, glowRadius, f8, f9, glowRadius, glowRadius, glowRadius, 0.0f, 1.0f, f16, f15);
        matrixStackIn.J_1907_R();
    }

    private static void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float p_228840_8_, float p_228840_9_, float p_228840_10_, float p_228840_11_, float p_228840_12_, float p_228840_13_, float p_228840_14_, float p_228840_15_, float u1, float u2, float v1, float v2) {
        g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
        D_1098_v matrix4f = matrixstack$entry.n_1700_B();
        o_1290_k matrix3f = matrixstack$entry.J_1907_R();
        R_4531_p.n_1700_B(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_8_, p_228840_9_, p_228840_10_, p_228840_11_, u1, u2, v1, v2);
        R_4531_p.n_1700_B(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_14_, p_228840_15_, p_228840_12_, p_228840_13_, u1, u2, v1, v2);
        R_4531_p.n_1700_B(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_10_, p_228840_11_, p_228840_14_, p_228840_15_, u1, u2, v1, v2);
        R_4531_p.n_1700_B(matrix4f, matrix3f, bufferIn, red, green, blue, alpha, yMin, yMax, p_228840_12_, p_228840_13_, p_228840_8_, p_228840_9_, u1, u2, v1, v2);
    }

    private static void n_1700_B(D_1098_v matrixPos, o_1290_k matrixNormal, D_4792_h bufferIn, float red, float green, float blue, float alpha, int yMin, int yMax, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        R_4531_p.n_1700_B(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x1, z1, u2, v1);
        R_4531_p.n_1700_B(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x1, z1, u2, v2);
        R_4531_p.n_1700_B(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMin, x2, z2, u1, v2);
        R_4531_p.n_1700_B(matrixPos, matrixNormal, bufferIn, red, green, blue, alpha, yMax, x2, z2, u1, v1);
    }

    private static void n_1700_B(D_1098_v matrixPos, o_1290_k matrixNormal, D_4792_h bufferIn, float red, float green, float blue, float alpha, int y, float x, float z, float texU, float texV) {
        bufferIn.n_1700_B(matrixPos, x, (float)y, z).n_1700_B(red, green, blue, alpha).tex(texU, texV).R_4764_Y(Z_3224_L.n_1700_B).J_1907_R(0xF000F0).n_1700_B(matrixNormal, 0.0f, 1.0f, 0.0f).endVertex();
    }

    @Override
    public boolean n_1700_B(x_3974_Q te) {
        return true;
    }
}

