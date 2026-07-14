/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.DiskConfiguration;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;

public class GlowstoneFeature
extends Feature<DiskConfiguration> {
    public GlowstoneFeature(Codec<DiskConfiguration> p_i241977_1_) {
        super(p_i241977_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, DiskConfiguration p_241855_5_) {
        boolean flag = false;
        int i = p_241855_5_.R_4764_Y.n_1700_B(p_241855_3_);
        for (int j = p_241855_4_.getX() - i; j <= p_241855_4_.getX() + i; ++j) {
            for (int k = p_241855_4_.getZ() - i; k <= p_241855_4_.getZ() + i; ++k) {
                int i1;
                int l = j - p_241855_4_.getX();
                if (l * l + (i1 = k - p_241855_4_.getZ()) * i1 > i * i) continue;
                block2: for (int j1 = p_241855_4_.getY() - p_241855_5_.G_564_y; j1 <= p_241855_4_.getY() + p_241855_5_.G_564_y; ++j1) {
                    c_1514_x blockpos = new c_1514_x(j, j1, k);
                    T_2915_h block = p_241855_1_.getBlockState(blockpos).J_1907_R();
                    for (K_4074_S blockstate : p_241855_5_.P_1922_E) {
                        if (!blockstate.n_1700_B(block)) continue;
                        p_241855_1_.n_1700_B(blockpos, p_241855_5_.J_1907_R, 2);
                        flag = true;
                        continue block2;
                    }
                }
            }
        }
        return flag;
    }
}


