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
import java.util.Collections;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.CoralFeature;

public class CoralClawFeature
extends CoralFeature {
    public CoralClawFeature(Codec<o_2105_O> p_i231939_1_) {
        super(p_i231939_1_);
    }

    @Override
    protected boolean n_1700_B(LevelAccessor p_204623_1_, Random p_204623_2_, c_1514_x p_204623_3_, K_4074_S p_204623_4_) {
        if (!this.J_1907_R(p_204623_1_, p_204623_2_, p_204623_3_, p_204623_4_)) {
            return false;
        }
        b_257_Y direction = b_257_Y.R_4764_Y.n_1700_B.n_1700_B(p_204623_2_);
        int i = p_204623_2_.nextInt(2) + 2;
        ArrayList list = Lists.newArrayList((Object[])new b_257_Y[]{direction, direction.v_4262_N(), direction.w_1484_f()});
        Collections.shuffle(list, p_204623_2_);
        block0: for (b_257_Y direction1 : list.subList(0, i)) {
            int k;
            b_257_Y direction2;
            c_1514_x.n_1700_B blockpos$mutable = p_204623_3_.toMutable();
            int j = p_204623_2_.nextInt(2) + 1;
            blockpos$mutable.n_1700_B(direction1);
            if (direction1 == direction) {
                direction2 = direction;
                k = p_204623_2_.nextInt(3) + 2;
            } else {
                blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
                b_257_Y[] adirection = new b_257_Y[]{direction1, b_257_Y.J_1907_R};
                direction2 = j_3341_s.n_1700_B(adirection, p_204623_2_);
                k = p_204623_2_.nextInt(3) + 3;
            }
            for (int l = 0; l < j && this.J_1907_R(p_204623_1_, p_204623_2_, blockpos$mutable, p_204623_4_); ++l) {
                blockpos$mutable.n_1700_B(direction2);
            }
            blockpos$mutable.n_1700_B(direction2.u_1723_Y());
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
            for (int i1 = 0; i1 < k; ++i1) {
                blockpos$mutable.n_1700_B(direction);
                if (!this.J_1907_R(p_204623_1_, p_204623_2_, blockpos$mutable, p_204623_4_)) continue block0;
                if (!(p_204623_2_.nextFloat() < 0.25f)) continue;
                blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
            }
        }
        return true;
    }
}


