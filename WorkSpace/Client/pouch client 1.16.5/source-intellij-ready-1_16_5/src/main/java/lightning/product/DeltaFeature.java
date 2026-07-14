/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.DeltaFeatureConfiguration;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class DeltaFeature
extends Feature<DeltaFeatureConfiguration> {
    private static final ImmutableList<T_2915_h> n_1700_B = ImmutableList.of((Object)a_3742_W.Z_875_P, (Object)a_3742_W.h_1015_G, (Object)a_3742_W.d_4500_Q, (Object)a_3742_W.O_2761_o, (Object)a_3742_W.W_3729_Q, (Object)a_3742_W.L_1362_X, (Object)a_3742_W.j_306_t);
    private static final b_257_Y[] D_4792_h = b_257_Y.values();

    public DeltaFeature(Codec<DeltaFeatureConfiguration> p_i231946_1_) {
        super(p_i231946_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, DeltaFeatureConfiguration p_241855_5_) {
        boolean flag = false;
        boolean flag1 = p_241855_3_.nextDouble() < 0.9;
        int i = flag1 ? p_241855_5_.G_564_y().n_1700_B(p_241855_3_) : 0;
        int j = flag1 ? p_241855_5_.G_564_y().n_1700_B(p_241855_3_) : 0;
        boolean flag2 = flag1 && i != 0 && j != 0;
        int k = p_241855_5_.R_4764_Y().n_1700_B(p_241855_3_);
        int l = p_241855_5_.R_4764_Y().n_1700_B(p_241855_3_);
        int i1 = Math.max(k, l);
        for (c_1514_x blockpos : c_1514_x.getProximitySortedBoxPositionsIterator(p_241855_4_, k, 0, l)) {
            c_1514_x blockpos1;
            if (blockpos.manhattanDistance(p_241855_4_) > i1) break;
            if (!DeltaFeature.n_1700_B(p_241855_1_, blockpos, p_241855_5_)) continue;
            if (flag2) {
                flag = true;
                this.n_1700_B(p_241855_1_, blockpos, p_241855_5_.J_1907_R());
            }
            if (!DeltaFeature.n_1700_B(p_241855_1_, blockpos1 = blockpos.add(i, 0, j), p_241855_5_)) continue;
            flag = true;
            this.n_1700_B(p_241855_1_, blockpos1, p_241855_5_.n_1700_B());
        }
        return flag;
    }

    private static boolean n_1700_B(LevelAccessor p_236277_0_, c_1514_x p_236277_1_, DeltaFeatureConfiguration p_236277_2_) {
        K_4074_S blockstate = p_236277_0_.getBlockState(p_236277_1_);
        if (blockstate.n_1700_B(p_236277_2_.n_1700_B().J_1907_R())) {
            return false;
        }
        if (n_1700_B.contains((Object)blockstate.J_1907_R())) {
            return false;
        }
        for (b_257_Y direction : D_4792_h) {
            boolean flag = p_236277_0_.getBlockState(p_236277_1_.offset(direction)).v_4262_N();
            if ((!flag || direction == b_257_Y.J_1907_R) && (flag || direction != b_257_Y.J_1907_R)) continue;
            return false;
        }
        return true;
    }
}


