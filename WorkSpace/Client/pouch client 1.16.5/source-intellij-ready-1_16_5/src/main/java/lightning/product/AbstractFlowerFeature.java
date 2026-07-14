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
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.FeatureConfiguration;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;

public abstract class AbstractFlowerFeature<U extends FeatureConfiguration>
extends Feature<U> {
    public AbstractFlowerFeature(Codec<U> p_i231922_1_) {
        super(p_i231922_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, U p_241855_5_) {
        K_4074_S blockstate = this.n_1700_B(p_241855_3_, p_241855_4_, p_241855_5_);
        int i = 0;
        for (int j = 0; j < this.n_1700_B(p_241855_5_); ++j) {
            c_1514_x blockpos = this.J_1907_R(p_241855_3_, p_241855_4_, p_241855_5_);
            if (!p_241855_1_.u_1723_Y(blockpos) || blockpos.getY() >= 255 || !blockstate.n_1700_B(p_241855_1_, blockpos) || !this.n_1700_B(p_241855_1_, blockpos, p_241855_5_)) continue;
            p_241855_1_.n_1700_B(blockpos, blockstate, 2);
            ++i;
        }
        return i > 0;
    }

    public abstract boolean n_1700_B(LevelAccessor var1, c_1514_x var2, U var3);

    public abstract int n_1700_B(U var1);

    public abstract c_1514_x J_1907_R(Random var1, c_1514_x var2, U var3);

    public abstract K_4074_S n_1700_B(Random var1, c_1514_x var2, U var3);
}


