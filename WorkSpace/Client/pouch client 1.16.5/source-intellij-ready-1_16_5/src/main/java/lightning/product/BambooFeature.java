/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.E_872_n;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.d_2484_X;
import lightning.product.Feature;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class BambooFeature
extends Feature<ProbabilityFeatureConfiguration> {
    private static final K_4074_S n_1700_B = (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.t_1509_b.multiplayerClientSuggestionProvider().n_1700_B(E_872_n.M_182_A, 1)).n_1700_B(E_872_n.t_1786_h, d_2484_X.n_1700_B)).n_1700_B(E_872_n.multiplayerClientSuggestionProvider, 0);
    private static final K_4074_S D_4792_h = (K_4074_S)((K_4074_S)n_1700_B.n_1700_B(E_872_n.t_1786_h, d_2484_X.R_4764_Y)).n_1700_B(E_872_n.multiplayerClientSuggestionProvider, 1);
    private static final K_4074_S s_2632_s = (K_4074_S)n_1700_B.n_1700_B(E_872_n.t_1786_h, d_2484_X.R_4764_Y);
    private static final K_4074_S l_1233_K = (K_4074_S)n_1700_B.n_1700_B(E_872_n.t_1786_h, d_2484_X.J_1907_R);

    public BambooFeature(Codec<ProbabilityFeatureConfiguration> p_i231924_1_) {
        super(p_i231924_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, ProbabilityFeatureConfiguration p_241855_5_) {
        int i = 0;
        c_1514_x.n_1700_B blockpos$mutable = p_241855_4_.toMutable();
        c_1514_x.n_1700_B blockpos$mutable1 = p_241855_4_.toMutable();
        if (p_241855_1_.u_1723_Y(blockpos$mutable)) {
            if (a_3742_W.t_1509_b.multiplayerClientSuggestionProvider().n_1700_B(p_241855_1_, (c_1514_x)blockpos$mutable)) {
                int j = p_241855_3_.nextInt(12) + 5;
                if (p_241855_3_.nextFloat() < p_241855_5_.J_1907_R) {
                    int k = p_241855_3_.nextInt(4) + 1;
                    for (int l = p_241855_4_.getX() - k; l <= p_241855_4_.getX() + k; ++l) {
                        for (int i1 = p_241855_4_.getZ() - k; i1 <= p_241855_4_.getZ() + k; ++i1) {
                            int k1;
                            int j1 = l - p_241855_4_.getX();
                            if (j1 * j1 + (k1 = i1 - p_241855_4_.getZ()) * k1 > k * k) continue;
                            blockpos$mutable1.n_1700_B(l, p_241855_1_.n_1700_B(z_2963_s.n_1700_B.J_1907_R, l, i1) - 1, i1);
                            if (!BambooFeature.J_1907_R(p_241855_1_.getBlockState(blockpos$mutable1).J_1907_R())) continue;
                            p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable1, a_3742_W.M_588_G.multiplayerClientSuggestionProvider(), 2);
                        }
                    }
                }
                for (int l1 = 0; l1 < j && p_241855_1_.u_1723_Y(blockpos$mutable); ++l1) {
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, n_1700_B, 2);
                    blockpos$mutable.n_1700_B(b_257_Y.J_1907_R, 1);
                }
                if (blockpos$mutable.getY() - p_241855_4_.getY() >= 3) {
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, D_4792_h, 2);
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable.n_1700_B(b_257_Y.n_1700_B, 1), s_2632_s, 2);
                    p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable.n_1700_B(b_257_Y.n_1700_B, 1), l_1233_K, 2);
                }
            }
            ++i;
        }
        return i > 0;
    }
}


