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
import lightning.product.T_2915_h;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.BlockStateConfiguration;
import lightning.product.z_1753_f;

public class S_2843_Z
extends Feature<BlockStateConfiguration> {
    public S_2843_Z(Codec<BlockStateConfiguration> p_i231931_1_) {
        super(p_i231931_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, BlockStateConfiguration p_241855_5_) {
        while (true) {
            T_2915_h block;
            if (p_241855_4_.getY() <= 3 || !p_241855_1_.u_1723_Y(p_241855_4_.down()) && (S_2843_Z.J_1907_R(block = p_241855_1_.getBlockState(p_241855_4_.down()).J_1907_R()) || S_2843_Z.n_1700_B(block))) {
                if (p_241855_4_.getY() <= 3) {
                    return false;
                }
                for (int l = 0; l < 3; ++l) {
                    int i = p_241855_3_.nextInt(2);
                    int j = p_241855_3_.nextInt(2);
                    int k = p_241855_3_.nextInt(2);
                    float f = (float)(i + j + k) * 0.333f + 0.5f;
                    for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(p_241855_4_.add(-i, -j, -k), p_241855_4_.add(i, j, k))) {
                        if (!(blockpos.distanceSq(p_241855_4_) <= (double)(f * f))) continue;
                        p_241855_1_.n_1700_B(blockpos, p_241855_5_.J_1907_R, 4);
                    }
                    p_241855_4_ = p_241855_4_.add(-1 + p_241855_3_.nextInt(2), -p_241855_3_.nextInt(2), -1 + p_241855_3_.nextInt(2));
                }
                return true;
            }
            p_241855_4_ = p_241855_4_.down();
        }
    }
}


