/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.WorldGenLevel;
import lightning.product.RandomBooleanFeatureConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.z_1753_f;

public class featureIcePatchFeature
extends Feature<RandomBooleanFeatureConfiguration> {
    public featureIcePatchFeature(Codec<RandomBooleanFeatureConfiguration> p_i231978_1_) {
        super(p_i231978_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, RandomBooleanFeatureConfiguration p_241855_5_) {
        boolean flag = p_241855_3_.nextBoolean();
        return flag ? p_241855_5_.J_1907_R.get().n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_) : p_241855_5_.R_4764_Y.get().n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_);
    }
}


