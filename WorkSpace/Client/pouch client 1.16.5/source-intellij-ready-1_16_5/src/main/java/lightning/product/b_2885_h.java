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
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.TallSeagrass;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.g_3212_H;
import lightning.product.Feature;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class b_2885_h
extends Feature<ProbabilityFeatureConfiguration> {
    public b_2885_h(Codec<ProbabilityFeatureConfiguration> p_i231988_1_) {
        super(p_i231988_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, ProbabilityFeatureConfiguration p_241855_5_) {
        boolean flag = false;
        int i = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
        int j = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
        int k = p_241855_1_.n_1700_B(z_2963_s.n_1700_B.G_564_y, p_241855_4_.getX() + i, p_241855_4_.getZ() + j);
        c_1514_x blockpos = new c_1514_x(p_241855_4_.getX() + i, k, p_241855_4_.getZ() + j);
        if (p_241855_1_.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b)) {
            K_4074_S blockstate;
            boolean flag1 = p_241855_3_.nextDouble() < (double)p_241855_5_.J_1907_R;
            K_4074_S k_4074_S = blockstate = flag1 ? a_3742_W.LongRunningTask.multiplayerClientSuggestionProvider() : a_3742_W.RowButton.multiplayerClientSuggestionProvider();
            if (blockstate.n_1700_B(p_241855_1_, blockpos)) {
                if (flag1) {
                    K_4074_S blockstate1 = (K_4074_S)blockstate.n_1700_B(TallSeagrass.h_1847_R, g_3212_H.n_1700_B);
                    c_1514_x blockpos1 = blockpos.up();
                    if (p_241855_1_.getBlockState(blockpos1).n_1700_B(a_3742_W.c_3005_b)) {
                        p_241855_1_.n_1700_B(blockpos, blockstate, 2);
                        p_241855_1_.n_1700_B(blockpos1, blockstate1, 2);
                    }
                } else {
                    p_241855_1_.n_1700_B(blockpos, blockstate, 2);
                }
                flag = true;
            }
        }
        return flag;
    }
}


