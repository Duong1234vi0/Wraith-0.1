/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.BlockPileConfiguration;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class BlockPileFeature
extends Feature<BlockPileConfiguration> {
    public BlockPileFeature(Codec<BlockPileConfiguration> p_i231932_1_) {
        super(p_i231932_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, BlockPileConfiguration p_241855_5_) {
        if (p_241855_4_.getY() < 5) {
            return false;
        }
        int i = 2 + p_241855_3_.nextInt(2);
        int j = 2 + p_241855_3_.nextInt(2);
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_241855_4_.add(-i, 0, -j), p_241855_4_.add(i, 1, j))) {
            int l;
            int k = p_241855_4_.getX() - blockpos.getX();
            if ((float)(k * k + (l = p_241855_4_.getZ() - blockpos.getZ()) * l) <= p_241855_3_.nextFloat() * 10.0f - p_241855_3_.nextFloat() * 6.0f) {
                this.n_1700_B(p_241855_1_, blockpos, p_241855_3_, p_241855_5_);
                continue;
            }
            if (!((double)p_241855_3_.nextFloat() < 0.031)) continue;
            this.n_1700_B(p_241855_1_, blockpos, p_241855_3_, p_241855_5_);
        }
        return true;
    }

    private boolean n_1700_B(LevelAccessor worldIn, c_1514_x pos, Random random) {
        c_1514_x blockpos = pos.down();
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        return blockstate.n_1700_B(a_3742_W.InvManager) ? random.nextBoolean() : blockstate.G_564_y((BlockGetter)worldIn, blockpos, b_257_Y.J_1907_R);
    }

    private void n_1700_B(LevelAccessor p_227225_1_, c_1514_x p_227225_2_, Random p_227225_3_, BlockPileConfiguration p_227225_4_) {
        if (p_227225_1_.u_1723_Y(p_227225_2_) && this.n_1700_B(p_227225_1_, p_227225_2_, p_227225_3_)) {
            p_227225_1_.n_1700_B(p_227225_2_, p_227225_4_.J_1907_R.n_1700_B(p_227225_3_, p_227225_2_), 4);
        }
    }
}



