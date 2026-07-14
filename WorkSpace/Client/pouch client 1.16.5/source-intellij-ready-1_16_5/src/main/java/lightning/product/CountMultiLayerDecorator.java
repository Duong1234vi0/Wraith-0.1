/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;
import lightning.product.K_4074_S;
import lightning.product.DecorationContext;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.CountConfiguration;
import lightning.product.y_2419_Z;
import lightning.product.z_2963_s;

public class CountMultiLayerDecorator
extends y_2419_Z<CountConfiguration> {
    public CountMultiLayerDecorator(Codec<CountConfiguration> p_i242034_1_) {
        super(p_i242034_1_);
    }

    @Override
    public Stream<c_1514_x> n_1700_B(DecorationContext p_241857_1_, Random p_241857_2_, CountConfiguration p_241857_3_, c_1514_x p_241857_4_) {
        boolean flag;
        ArrayList list = Lists.newArrayList();
        int i = 0;
        do {
            flag = false;
            for (int j = 0; j < p_241857_3_.J_1907_R().n_1700_B(p_241857_2_); ++j) {
                int l;
                int i1;
                int k = p_241857_2_.nextInt(16) + p_241857_4_.getX();
                int j1 = CountMultiLayerDecorator.n_1700_B(p_241857_1_, k, i1 = p_241857_1_.n_1700_B(z_2963_s.n_1700_B.P_1922_E, k, l = p_241857_2_.nextInt(16) + p_241857_4_.getZ()), l, i);
                if (j1 == Integer.MAX_VALUE) continue;
                list.add(new c_1514_x(k, j1, l));
                flag = true;
            }
            ++i;
        } while (flag);
        return list.stream();
    }

    private static int n_1700_B(DecorationContext p_242915_0_, int p_242915_1_, int p_242915_2_, int p_242915_3_, int p_242915_4_) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(p_242915_1_, p_242915_2_, p_242915_3_);
        int i = 0;
        K_4074_S blockstate = p_242915_0_.n_1700_B(blockpos$mutable);
        for (int j = p_242915_2_; j >= 1; --j) {
            blockpos$mutable.setY(j - 1);
            K_4074_S blockstate1 = p_242915_0_.n_1700_B(blockpos$mutable);
            if (!CountMultiLayerDecorator.n_1700_B(blockstate1) && CountMultiLayerDecorator.n_1700_B(blockstate) && !blockstate1.n_1700_B(a_3742_W.Z_875_P)) {
                if (i == p_242915_4_) {
                    return blockpos$mutable.getY() + 1;
                }
                ++i;
            }
            blockstate = blockstate1;
        }
        return Integer.MAX_VALUE;
    }

    private static boolean n_1700_B(K_4074_S p_242914_0_) {
        return p_242914_0_.v_4262_N() || p_242914_0_.n_1700_B(a_3742_W.c_3005_b) || p_242914_0_.n_1700_B(a_3742_W.H_2857_Y);
    }
}


