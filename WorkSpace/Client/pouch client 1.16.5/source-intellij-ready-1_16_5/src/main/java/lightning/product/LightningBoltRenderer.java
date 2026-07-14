/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.L_3848_p;
import lightning.product.Z_2049_e;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.LightningBolt;
import lightning.product.w_2040_b;

public class LightningBoltRenderer
extends Z_2049_e<LightningBolt> {
    public LightningBoltRenderer(w_2040_b renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void n_1700_B(LightningBolt entityIn, float entityYaw, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn, int packedLightIn) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0f;
        float f1 = 0.0f;
        Random random = new Random(entityIn.n_1700_B);
        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float)(random.nextInt(11) - 5);
            f1 += (float)(random.nextInt(11) - 5);
        }
        D_4792_h ivertexbuilder = bufferIn.getBuffer(o_2576_A.Y_259_p());
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        for (int j = 0; j < 4; ++j) {
            Random random1 = new Random(entityIn.n_1700_B);
            for (int k = 0; k < 3; ++k) {
                int l = 7;
                int i1 = 0;
                if (k > 0) {
                    l = 7 - k;
                }
                if (k > 0) {
                    i1 = l - 2;
                }
                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;
                for (int j1 = l; j1 >= i1; --j1) {
                    float f4 = f2;
                    float f5 = f3;
                    if (k == 0) {
                        f2 += (float)(random1.nextInt(11) - 5);
                        f3 += (float)(random1.nextInt(11) - 5);
                    } else {
                        f2 += (float)(random1.nextInt(31) - 15);
                        f3 += (float)(random1.nextInt(31) - 15);
                    }
                    float f6 = 0.5f;
                    float f7 = 0.45f;
                    float f8 = 0.45f;
                    float f9 = 0.5f;
                    float f10 = 0.1f + (float)j * 0.2f;
                    if (k == 0) {
                        f10 = (float)((double)f10 * ((double)j1 * 0.1 + 1.0));
                    }
                    float f11 = 0.1f + (float)j * 0.2f;
                    if (k == 0) {
                        f11 *= (float)(j1 - 1) * 0.1f + 1.0f;
                    }
                    LightningBoltRenderer.n_1700_B(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, 0.45f, 0.45f, 0.5f, f10, f11, false, false, true, false);
                    LightningBoltRenderer.n_1700_B(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, 0.45f, 0.45f, 0.5f, f10, f11, true, false, true, true);
                    LightningBoltRenderer.n_1700_B(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, 0.45f, 0.45f, 0.5f, f10, f11, true, true, false, true);
                    LightningBoltRenderer.n_1700_B(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, 0.45f, 0.45f, 0.5f, f10, f11, false, true, false, false);
                }
            }
        }
    }

    private static void n_1700_B(D_1098_v p_229116_0_, D_4792_h p_229116_1_, float p_229116_2_, float p_229116_3_, int p_229116_4_, float p_229116_5_, float p_229116_6_, float p_229116_7_, float p_229116_8_, float p_229116_9_, float p_229116_10_, float p_229116_11_, boolean p_229116_12_, boolean p_229116_13_, boolean p_229116_14_, boolean p_229116_15_) {
        p_229116_1_.n_1700_B(p_229116_0_, p_229116_2_ + (p_229116_12_ ? p_229116_11_ : -p_229116_11_), (float)(p_229116_4_ * 16), p_229116_3_ + (p_229116_13_ ? p_229116_11_ : -p_229116_11_)).n_1700_B(p_229116_7_, p_229116_8_, p_229116_9_, 0.3f).endVertex();
        p_229116_1_.n_1700_B(p_229116_0_, p_229116_5_ + (p_229116_12_ ? p_229116_10_ : -p_229116_10_), (float)((p_229116_4_ + 1) * 16), p_229116_6_ + (p_229116_13_ ? p_229116_10_ : -p_229116_10_)).n_1700_B(p_229116_7_, p_229116_8_, p_229116_9_, 0.3f).endVertex();
        p_229116_1_.n_1700_B(p_229116_0_, p_229116_5_ + (p_229116_14_ ? p_229116_10_ : -p_229116_10_), (float)((p_229116_4_ + 1) * 16), p_229116_6_ + (p_229116_15_ ? p_229116_10_ : -p_229116_10_)).n_1700_B(p_229116_7_, p_229116_8_, p_229116_9_, 0.3f).endVertex();
        p_229116_1_.n_1700_B(p_229116_0_, p_229116_2_ + (p_229116_14_ ? p_229116_11_ : -p_229116_11_), (float)(p_229116_4_ * 16), p_229116_3_ + (p_229116_15_ ? p_229116_11_ : -p_229116_11_)).n_1700_B(p_229116_7_, p_229116_8_, p_229116_9_, 0.3f).endVertex();
    }

    @Override
    public g_2336_b n_1700_B(LightningBolt entity) {
        return L_3848_p.n_1700_B;
    }
}


