/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.ReplaceSphereConfiguration;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public class ReplaceBlobsFeature
extends Feature<ReplaceSphereConfiguration> {
    public ReplaceBlobsFeature(Codec<ReplaceSphereConfiguration> p_i231982_1_) {
        super(p_i231982_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, ReplaceSphereConfiguration p_241855_5_) {
        T_2915_h block = p_241855_5_.J_1907_R.J_1907_R();
        c_1514_x blockpos = ReplaceBlobsFeature.n_1700_B(p_241855_1_, p_241855_4_.toMutable().n_1700_B(b_257_Y.n_1700_B.J_1907_R, 1, p_241855_1_.c_3005_b() - 1), block);
        if (blockpos == null) {
            return false;
        }
        int i = p_241855_5_.n_1700_B().n_1700_B(p_241855_3_);
        boolean flag = false;
        for (c_1514_x blockpos1 : c_1514_x.getProximitySortedBoxPositionsIterator(blockpos, i, i, i)) {
            if (blockpos1.manhattanDistance(blockpos) > i) break;
            K_4074_S blockstate = p_241855_1_.getBlockState(blockpos1);
            if (!blockstate.n_1700_B(block)) continue;
            this.n_1700_B(p_241855_1_, blockpos1, p_241855_5_.R_4764_Y);
            flag = true;
        }
        return flag;
    }

    @Nullable
    private static c_1514_x n_1700_B(LevelAccessor p_236329_0_, c_1514_x.n_1700_B p_236329_1_, T_2915_h p_236329_2_) {
        while (p_236329_1_.getY() > 1) {
            K_4074_S blockstate = p_236329_0_.getBlockState(p_236329_1_);
            if (blockstate.n_1700_B(p_236329_2_)) {
                return p_236329_1_;
            }
            p_236329_1_.n_1700_B(b_257_Y.n_1700_B);
        }
        return null;
    }
}


