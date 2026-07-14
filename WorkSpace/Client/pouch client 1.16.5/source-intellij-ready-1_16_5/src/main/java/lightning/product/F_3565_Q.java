/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import javax.annotation.Nullable;
import lightning.product.B_3871_I;
import lightning.product.B_4830_U;
import lightning.product.AnimationFrame;
import lightning.product.L_3848_p;
import lightning.product.T_1114_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.l_52_h;

public final class F_3565_Q
extends B_3871_I {
    private static final g_2336_b M_182_A = new g_2336_b("missingno");
    @Nullable
    private static T_1114_L t_1786_h;
    private static final l_52_h<i_2518_W> multiplayerClientSuggestionProvider;
    private static final B_3871_I.n_1700_B w_1457_N;

    public F_3565_Q(L_3848_p p_i242120_1_, B_3871_I.n_1700_B p_i242120_2_, int p_i242120_3_, int p_i242120_4_, int p_i242120_5_, int p_i242120_6_, int p_i242120_7_) {
        super(p_i242120_1_, p_i242120_2_, p_i242120_3_, p_i242120_4_, p_i242120_5_, p_i242120_6_, p_i242120_7_, F_3565_Q.n_1700_B(p_i242120_2_.J_1907_R(), p_i242120_2_.R_4764_Y()));
    }

    private F_3565_Q(L_3848_p atlasTextureIn, int mipmapLevelIn, int atlasWidthIn, int atlasHeightIn, int xIn, int yIn) {
        super(atlasTextureIn, w_1457_N, mipmapLevelIn, atlasWidthIn, atlasHeightIn, xIn, yIn, multiplayerClientSuggestionProvider.n_1700_B());
    }

    public static F_3565_Q n_1700_B(L_3848_p atlasTextureIn, int mipmapLevelIn, int atlasWidthIn, int atlasHeightIn, int xIn, int yIn) {
        return new F_3565_Q(atlasTextureIn, mipmapLevelIn, atlasWidthIn, atlasHeightIn, xIn, yIn);
    }

    public static g_2336_b n_1700_B() {
        return M_182_A;
    }

    public static B_3871_I.n_1700_B J_1907_R() {
        return w_1457_N;
    }

    @Override
    public void close() {
        super.close();
    }

    public static T_1114_L R_4764_Y() {
        if (t_1786_h == null) {
            t_1786_h = new T_1114_L(multiplayerClientSuggestionProvider.n_1700_B());
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(M_182_A, (c_4477_a)t_1786_h);
        }
        return t_1786_h;
    }

    private static i_2518_W n_1700_B(int p_makeMissingImage_0_, int p_makeMissingImage_1_) {
        int i = p_makeMissingImage_0_ / 2;
        int j = p_makeMissingImage_1_ / 2;
        i_2518_W nativeimage = new i_2518_W(p_makeMissingImage_0_, p_makeMissingImage_1_, false);
        int k = -16777216;
        int l = -524040;
        for (int i1 = 0; i1 < p_makeMissingImage_1_; ++i1) {
            for (int j1 = 0; j1 < p_makeMissingImage_0_; ++j1) {
                if (i1 < j ^ j1 < i) {
                    nativeimage.n_1700_B(j1, i1, l);
                    continue;
                }
                nativeimage.n_1700_B(j1, i1, k);
            }
        }
        return nativeimage;
    }

    static {
        multiplayerClientSuggestionProvider = new l_52_h<i_2518_W>(() -> {
            i_2518_W nativeimage = new i_2518_W(16, 16, false);
            int i = -16777216;
            int j = -524040;
            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < 16; ++l) {
                    if (k < 8 ^ l < 8) {
                        nativeimage.n_1700_B(l, k, -524040);
                        continue;
                    }
                    nativeimage.n_1700_B(l, k, -16777216);
                }
            }
            nativeimage.v_4262_N();
            return nativeimage;
        });
        w_1457_N = new B_3871_I.n_1700_B(M_182_A, 16, 16, new B_4830_U(Lists.newArrayList((Object[])new AnimationFrame[]{new AnimationFrame(0, -1)}), 16, 16, 1, false));
    }
}



