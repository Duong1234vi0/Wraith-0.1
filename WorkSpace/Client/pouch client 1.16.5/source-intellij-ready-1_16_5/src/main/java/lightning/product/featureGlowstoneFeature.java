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
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.o_2105_O;
import lightning.product.z_1753_f;

public class featureGlowstoneFeature
extends Feature<o_2105_O> {
    public featureGlowstoneFeature(Codec<o_2105_O> p_i231956_1_) {
        super(p_i231956_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, o_2105_O p_241855_5_) {
        if (!p_241855_1_.u_1723_Y(p_241855_4_)) {
            return false;
        }
        K_4074_S blockstate = p_241855_1_.getBlockState(p_241855_4_.up());
        if (!(blockstate.n_1700_B(a_3742_W.i_3196_G) || blockstate.n_1700_B(a_3742_W.s_4990_V) || blockstate.n_1700_B(a_3742_W.m_1964_F))) {
            return false;
        }
        p_241855_1_.n_1700_B(p_241855_4_, a_3742_W.X_2960_b.multiplayerClientSuggestionProvider(), 2);
        for (int i = 0; i < 1500; ++i) {
            c_1514_x blockpos = p_241855_4_.add(p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8), -p_241855_3_.nextInt(12), p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8));
            if (!p_241855_1_.getBlockState(blockpos).v_4262_N()) continue;
            int j = 0;
            for (b_257_Y direction : b_257_Y.values()) {
                if (p_241855_1_.getBlockState(blockpos.offset(direction)).n_1700_B(a_3742_W.X_2960_b)) {
                    ++j;
                }
                if (j > 1) break;
            }
            if (j != true) continue;
            p_241855_1_.n_1700_B(blockpos, a_3742_W.X_2960_b.multiplayerClientSuggestionProvider(), 2);
        }
        return true;
    }
}


