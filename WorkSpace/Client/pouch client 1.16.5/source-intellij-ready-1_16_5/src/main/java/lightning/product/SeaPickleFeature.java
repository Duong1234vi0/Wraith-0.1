/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.H_4584_y;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.CountConfiguration;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class SeaPickleFeature
extends Feature<CountConfiguration> {
    public SeaPickleFeature(Codec<CountConfiguration> p_i231987_1_) {
        super(p_i231987_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, CountConfiguration p_241855_5_) {
        int i = 0;
        int j = p_241855_5_.J_1907_R().n_1700_B(p_241855_3_);
        for (int k = 0; k < j; ++k) {
            int l = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
            int i1 = p_241855_3_.nextInt(8) - p_241855_3_.nextInt(8);
            int j1 = p_241855_1_.n_1700_B(z_2963_s.n_1700_B.G_564_y, p_241855_4_.getX() + l, p_241855_4_.getZ() + i1);
            c_1514_x blockpos = new c_1514_x(p_241855_4_.getX() + l, j1, p_241855_4_.getZ() + i1);
            K_4074_S blockstate = (K_4074_S)a_3742_W.Easing.multiplayerClientSuggestionProvider().n_1700_B(H_4584_y.P_4830_p, p_241855_3_.nextInt(4) + 1);
            if (!p_241855_1_.getBlockState(blockpos).n_1700_B(a_3742_W.c_3005_b) || !blockstate.n_1700_B(p_241855_1_, blockpos)) continue;
            p_241855_1_.n_1700_B(blockpos, blockstate, 2);
            ++i;
        }
        return i > 0;
    }
}


