/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.WorldGenLevel;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.OreConfiguration;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class NoSurfaceOreFeature
extends Feature<OreConfiguration> {
    NoSurfaceOreFeature(Codec<OreConfiguration> p_i231974_1_) {
        super(p_i231974_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, OreConfiguration p_241855_5_) {
        int i = p_241855_3_.nextInt(p_241855_5_.R_4764_Y + 1);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int j = 0; j < i; ++j) {
            this.n_1700_B(blockpos$mutable, p_241855_3_, p_241855_4_, Math.min(j, 7));
            if (!p_241855_5_.J_1907_R.n_1700_B(p_241855_1_.getBlockState(blockpos$mutable), p_241855_3_) || this.n_1700_B(p_241855_1_, blockpos$mutable)) continue;
            p_241855_1_.n_1700_B((c_1514_x)blockpos$mutable, p_241855_5_.G_564_y, 2);
        }
        return true;
    }

    private void n_1700_B(c_1514_x.n_1700_B p_236327_1_, Random p_236327_2_, c_1514_x p_236327_3_, int p_236327_4_) {
        int i = this.n_1700_B(p_236327_2_, p_236327_4_);
        int j = this.n_1700_B(p_236327_2_, p_236327_4_);
        int k = this.n_1700_B(p_236327_2_, p_236327_4_);
        p_236327_1_.n_1700_B(p_236327_3_, i, j, k);
    }

    private int n_1700_B(Random p_236328_1_, int p_236328_2_) {
        return Math.round((p_236328_1_.nextFloat() - p_236328_1_.nextFloat()) * (float)p_236328_2_);
    }

    private boolean n_1700_B(LevelAccessor p_236326_1_, c_1514_x p_236326_2_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (b_257_Y direction : b_257_Y.values()) {
            blockpos$mutable.n_1700_B(p_236326_2_, direction);
            if (!p_236326_1_.getBlockState(blockpos$mutable).v_4262_N()) continue;
            return true;
        }
        return false;
    }
}


