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
import lightning.product.HugeMushroomFeatureConfiguration;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.BlockTags;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public abstract class AbstractHugeMushroomFeature
extends Feature<HugeMushroomFeatureConfiguration> {
    public AbstractHugeMushroomFeature(Codec<HugeMushroomFeatureConfiguration> p_i231923_1_) {
        super(p_i231923_1_);
    }

    protected void n_1700_B(LevelAccessor p_227210_1_, Random p_227210_2_, c_1514_x p_227210_3_, HugeMushroomFeatureConfiguration p_227210_4_, int p_227210_5_, c_1514_x.n_1700_B p_227210_6_) {
        for (int i = 0; i < p_227210_5_; ++i) {
            p_227210_6_.n_1700_B(p_227210_3_).n_1700_B(b_257_Y.J_1907_R, i);
            if (p_227210_1_.getBlockState(p_227210_6_).t_148_a(p_227210_1_, p_227210_6_)) continue;
            this.n_1700_B(p_227210_1_, p_227210_6_, p_227210_4_.R_4764_Y.n_1700_B(p_227210_2_, p_227210_3_));
        }
    }

    protected int n_1700_B(Random p_227211_1_) {
        int i = p_227211_1_.nextInt(3) + 4;
        if (p_227211_1_.nextInt(12) == 0) {
            i *= 2;
        }
        return i;
    }

    protected boolean n_1700_B(LevelAccessor p_227209_1_, c_1514_x p_227209_2_, int p_227209_3_, c_1514_x.n_1700_B p_227209_4_, HugeMushroomFeatureConfiguration p_227209_5_) {
        int i = p_227209_2_.getY();
        if (i >= 1 && i + p_227209_3_ + 1 < 256) {
            T_2915_h block = p_227209_1_.getBlockState(p_227209_2_.down()).J_1907_R();
            if (!AbstractHugeMushroomFeature.J_1907_R(block) && !block.n_1700_B(BlockTags.j_1564_a)) {
                return false;
            }
            for (int j = 0; j <= p_227209_3_; ++j) {
                int k = this.n_1700_B(-1, -1, p_227209_5_.G_564_y, j);
                for (int l = -k; l <= k; ++l) {
                    for (int i1 = -k; i1 <= k; ++i1) {
                        K_4074_S blockstate = p_227209_1_.getBlockState(p_227209_4_.n_1700_B(p_227209_2_, l, j, i1));
                        if (blockstate.v_4262_N() || blockstate.n_1700_B(BlockTags.d_2427_y)) continue;
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, HugeMushroomFeatureConfiguration p_241855_5_) {
        c_1514_x.n_1700_B blockpos$mutable;
        int i = this.n_1700_B(p_241855_3_);
        if (!this.n_1700_B((LevelAccessor)p_241855_1_, p_241855_4_, i, blockpos$mutable = new c_1514_x.n_1700_B(), p_241855_5_)) {
            return false;
        }
        this.n_1700_B((LevelAccessor)p_241855_1_, p_241855_3_, p_241855_4_, i, blockpos$mutable, p_241855_5_);
        this.n_1700_B((LevelAccessor)p_241855_1_, p_241855_3_, p_241855_4_, p_241855_5_, i, blockpos$mutable);
        return true;
    }

    protected abstract int n_1700_B(int var1, int var2, int var3, int var4);

    protected abstract void n_1700_B(LevelAccessor var1, Random var2, c_1514_x var3, int var4, c_1514_x.n_1700_B var5, HugeMushroomFeatureConfiguration var6);
}


