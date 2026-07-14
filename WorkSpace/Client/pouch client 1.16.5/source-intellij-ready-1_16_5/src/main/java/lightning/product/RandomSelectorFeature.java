/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.RandomFeatureConfiguration;
import lightning.product.WorldGenLevel;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.WeightedConfiguredFeature;
import lightning.product.z_1753_f;

public class RandomSelectorFeature
extends Feature<RandomFeatureConfiguration> {
    public RandomSelectorFeature(Codec<RandomFeatureConfiguration> p_i231981_1_) {
        super(p_i231981_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, RandomFeatureConfiguration p_241855_5_) {
        for (WeightedConfiguredFeature configuredrandomfeaturelist : p_241855_5_.J_1907_R) {
            if (!(p_241855_3_.nextFloat() < configuredrandomfeaturelist.R_4764_Y)) continue;
            return configuredrandomfeaturelist.n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_);
        }
        return p_241855_5_.R_4764_Y.get().n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_);
    }
}


