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
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.o_2105_O;
import lightning.product.LevelAccessor;
import lightning.product.CoralFeature;

public class s_3543_h
extends CoralFeature {
    public s_3543_h(Codec<o_2105_O> p_i231941_1_) {
        super(p_i231941_1_);
    }

    @Override
    protected boolean n_1700_B(LevelAccessor p_204623_1_, Random p_204623_2_, c_1514_x p_204623_3_, K_4074_S p_204623_4_) {
        int i = p_204623_2_.nextInt(3) + 3;
        int j = p_204623_2_.nextInt(3) + 3;
        int k = p_204623_2_.nextInt(3) + 3;
        int l = p_204623_2_.nextInt(3) + 1;
        c_1514_x.n_1700_B blockpos$mutable = p_204623_3_.toMutable();
        for (int i1 = 0; i1 <= j; ++i1) {
            for (int j1 = 0; j1 <= i; ++j1) {
                for (int k1 = 0; k1 <= k; ++k1) {
                    blockpos$mutable.n_1700_B(i1 + p_204623_3_.getX(), j1 + p_204623_3_.getY(), k1 + p_204623_3_.getZ());
                    blockpos$mutable.n_1700_B(b_257_Y.n_1700_B, l);
                    if ((i1 != 0 && i1 != j || j1 != 0 && j1 != i) && (k1 != 0 && k1 != k || j1 != 0 && j1 != i) && (i1 != 0 && i1 != j || k1 != 0 && k1 != k) && (i1 == 0 || i1 == j || j1 == 0 || j1 == i || k1 == 0 || k1 == k) && !(p_204623_2_.nextFloat() < 0.1f) && this.J_1907_R(p_204623_1_, p_204623_2_, blockpos$mutable, p_204623_4_)) continue;
                }
            }
        }
        return true;
    }
}


