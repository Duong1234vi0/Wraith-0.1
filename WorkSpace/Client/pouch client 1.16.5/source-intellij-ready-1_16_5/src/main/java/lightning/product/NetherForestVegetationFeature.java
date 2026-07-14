/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.BlockPileConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.BlockTags;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class NetherForestVegetationFeature
extends Feature<BlockPileConfiguration> {
    public NetherForestVegetationFeature(Codec<BlockPileConfiguration> p_i231971_1_) {
        super(p_i231971_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, BlockPileConfiguration p_241855_5_) {
        return NetherForestVegetationFeature.n_1700_B(p_241855_1_, p_241855_3_, p_241855_4_, p_241855_5_, 8, 4);
    }

    public static boolean n_1700_B(LevelAccessor p_236325_0_, Random p_236325_1_, c_1514_x p_236325_2_, BlockPileConfiguration p_236325_3_, int p_236325_4_, int p_236325_5_) {
        T_2915_h block = p_236325_0_.getBlockState(p_236325_2_.down()).J_1907_R();
        if (!block.n_1700_B(BlockTags.UploadStatus)) {
            return false;
        }
        int i = p_236325_2_.getY();
        if (i >= 1 && i + 1 < 256) {
            int j = 0;
            for (int k = 0; k < p_236325_4_ * p_236325_4_; ++k) {
                c_1514_x blockpos = p_236325_2_.add(p_236325_1_.nextInt(p_236325_4_) - p_236325_1_.nextInt(p_236325_4_), p_236325_1_.nextInt(p_236325_5_) - p_236325_1_.nextInt(p_236325_5_), p_236325_1_.nextInt(p_236325_4_) - p_236325_1_.nextInt(p_236325_4_));
                K_4074_S blockstate = p_236325_3_.J_1907_R.n_1700_B(p_236325_1_, blockpos);
                if (!p_236325_0_.u_1723_Y(blockpos) || blockpos.getY() <= 0 || !blockstate.n_1700_B(p_236325_0_, blockpos)) continue;
                p_236325_0_.n_1700_B(blockpos, blockstate, 2);
                ++j;
            }
            return j > 0;
        }
        return false;
    }
}


