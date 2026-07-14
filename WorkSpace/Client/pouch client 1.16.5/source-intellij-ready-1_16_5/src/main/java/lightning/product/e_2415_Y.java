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
import lightning.product.ConfiguredFeature;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.SimpleRandomFeatureConfiguration;
import lightning.product.z_1753_f;

public class e_2415_Y
extends Feature<SimpleRandomFeatureConfiguration> {
    public e_2415_Y(Codec<SimpleRandomFeatureConfiguration> p_i231992_1_) {
        super(p_i231992_1_);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, SimpleRandomFeatureConfiguration p_241855_5_) {
        int i = p_241855_3_.nextInt(p_241855_5_.J_1907_R.size());
        ConfiguredFeature<?, ?> configuredfeature = p_241855_5_.J_1907_R.get(i).get();
        return configuredfeature.n_1700_B(p_241855_1_, p_241855_2_, p_241855_3_, p_241855_4_);
    }
}


