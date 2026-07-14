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
import lightning.product.V_1395_p;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class KelpFeature
extends Feature<o_2105_O> {
    public KelpFeature(Codec<o_2105_O> p_i231967_1_) {
        super(p_i231967_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        int i = 0;
        int j = p_241855_1_.n_1700_B(z_2963_s.n_1700_B.G_564_y, p_241855_4_.getX(), p_241855_4_.getZ());
        c_1514_x blockpos = new c_1514_x(p_241855_4_.getX(), j, p_241855_4_.getZ());
        if (p_241855_1_.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b)) {
            K_4074_S blockstate = a_3742_W.R_1796_s.multiplayerClientSuggestionProvider();
            K_4074_S blockstate1 = a_3742_W.g_24_p.multiplayerClientSuggestionProvider();
            int k = 1 + p_241855_3_.nextInt(10);
            for (int l = 0; l <= k; ++l) {
                if (p_241855_1_.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b) && p_241855_1_.getBlockState(blockpos.up()).n_1700_B(a_3742_W.c_3005_b) && blockstate1.n_1700_B(p_241855_1_, blockpos)) {
                    if (l == k) {
                        p_241855_1_.n_1700_B(blockpos, (K_4074_S)blockstate.n_1700_B(V_1395_p.M_182_A, p_241855_3_.nextInt(4) + 20), 2);
                        ++i;
                    } else {
                        p_241855_1_.n_1700_B(blockpos, blockstate1, 2);
                    }
                } else if (l > 0) {
                    c_1514_x blockpos1 = blockpos.down();
                    if (!blockstate.n_1700_B(p_241855_1_, blockpos1) || p_241855_1_.getBlockState(blockpos1.down()).n_1700_B(a_3742_W.R_1796_s)) break;
                    p_241855_1_.n_1700_B(blockpos1, (K_4074_S)blockstate.n_1700_B(V_1395_p.M_182_A, p_241855_3_.nextInt(4) + 20), 2);
                    ++i;
                    break;
                }
                blockpos = blockpos.up();
            }
        }
        return i > 0;
    }
}


