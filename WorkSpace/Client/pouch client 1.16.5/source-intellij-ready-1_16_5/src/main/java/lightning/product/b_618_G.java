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
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.CoralFeature;

public class b_618_G
extends CoralFeature {
    public b_618_G(Codec<o_2105_O> p_i231942_1_) {
        super(p_i231942_1_);
    }

    @Override
    protected boolean n_1700_B(LevelAccessor p_204623_1_, Random p_204623_2_, c_1514_x p_204623_3_, K_4074_S p_204623_4_) {
        c_1514_x.n_1700_B blockpos$mutable = p_204623_3_.toMutable();
        int i = p_204623_2_.nextInt(3) + 1;
        for (int j = 0; j < i; ++j) {
            if (!this.J_1907_R(p_204623_1_, p_204623_2_, blockpos$mutable, p_204623_4_)) {
                return true;
            }
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
        }
        c_1514_x blockpos = blockpos$mutable.toImmutable();
        int k = p_204623_2_.nextInt(3) + 2;
        ArrayList list = Lists.newArrayList((Iterable)b_257_Y.R_4764_Y.n_1700_B);
        Collections.shuffle(list, p_204623_2_);
        for (b_257_Y direction : list.subList(0, k)) {
            blockpos$mutable.n_1700_B(blockpos);
            blockpos$mutable.n_1700_B(direction);
            int l = p_204623_2_.nextInt(5) + 2;
            int i1 = 0;
            for (int j1 = 0; j1 < l && this.J_1907_R(p_204623_1_, p_204623_2_, blockpos$mutable, p_204623_4_); ++j1) {
                blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
                if (j1 != 0 && (++i1 < 2 || !(p_204623_2_.nextFloat() < 0.25f))) continue;
                blockpos$mutable.n_1700_B(direction);
                i1 = 0;
            }
        }
        return true;
    }
}


