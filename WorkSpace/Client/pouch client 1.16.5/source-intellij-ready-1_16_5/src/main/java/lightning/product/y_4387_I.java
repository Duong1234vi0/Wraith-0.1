/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.i_2518_W;
import lightning.product.j_3341_s;
import net.optifine.Mipmaps;
import net.optifine.texture.IColorBlender;

public class y_4387_I {
    private static final float[] n_1700_B = j_3341_s.n_1700_B(new float[256], p_lambda$static$0_0_ -> {
        for (int i = 0; i < ((float[])p_lambda$static$0_0_).length; ++i) {
            p_lambda$static$0_0_[i] = (float)Math.pow((float)i / 255.0f, 2.2);
        }
    });

    public static i_2518_W[] n_1700_B(i_2518_W imageIn, int mipmapLevelsIn) {
        return y_4387_I.n_1700_B(imageIn, mipmapLevelsIn, null);
    }

    public static i_2518_W[] n_1700_B(i_2518_W p_generateMipmaps_0_, int p_generateMipmaps_1_, IColorBlender p_generateMipmaps_2_) {
        i_2518_W[] anativeimage = new i_2518_W[p_generateMipmaps_1_ + 1];
        anativeimage[0] = p_generateMipmaps_0_;
        if (p_generateMipmaps_1_ > 0) {
            boolean flag = false;
            for (int i = 1; i <= p_generateMipmaps_1_; ++i) {
                i_2518_W nativeimage = anativeimage[i - 1];
                i_2518_W nativeimage1 = new i_2518_W(nativeimage.n_1700_B() >> 1, nativeimage.J_1907_R() >> 1, false);
                int j = nativeimage1.n_1700_B();
                int k = nativeimage1.J_1907_R();
                for (int l = 0; l < j; ++l) {
                    for (int i1 = 0; i1 < k; ++i1) {
                        if (p_generateMipmaps_2_ != null) {
                            nativeimage1.n_1700_B(l, i1, p_generateMipmaps_2_.blend(nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 1), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 1)));
                            continue;
                        }
                        nativeimage1.n_1700_B(l, i1, y_4387_I.n_1700_B(nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 0), nativeimage.n_1700_B(l * 2 + 0, i1 * 2 + 1), nativeimage.n_1700_B(l * 2 + 1, i1 * 2 + 1), flag));
                    }
                }
                anativeimage[i] = nativeimage1;
            }
        }
        return anativeimage;
    }

    private static int n_1700_B(int col1, int col2, int col3, int col4, boolean transparent) {
        return Mipmaps.alphaBlend(col1, col2, col3, col4);
    }

    private static int n_1700_B(int col1, int col2, int col3, int col4, int bitOffset) {
        float f = y_4387_I.n_1700_B(col1 >> bitOffset);
        float f1 = y_4387_I.n_1700_B(col2 >> bitOffset);
        float f2 = y_4387_I.n_1700_B(col3 >> bitOffset);
        float f3 = y_4387_I.n_1700_B(col4 >> bitOffset);
        float f4 = (float)((double)((float)Math.pow((double)(f + f1 + f2 + f3) * 0.25, 0.45454545454545453)));
        return (int)((double)f4 * 255.0);
    }

    private static float n_1700_B(int valIn) {
        return n_1700_B[valIn & 0xFF];
    }
}

