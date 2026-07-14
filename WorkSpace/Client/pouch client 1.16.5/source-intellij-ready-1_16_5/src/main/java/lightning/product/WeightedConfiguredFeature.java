/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Random;
import java.util.function.Supplier;
import lightning.product.WorldGenLevel;
import lightning.product.ConfiguredFeature;
import lightning.product.c_1514_x;
import lightning.product.z_1753_f;

public class WeightedConfiguredFeature {
    public static final Codec<WeightedConfiguredFeature> n_1700_B = RecordCodecBuilder.create(p_236433_0_ -> p_236433_0_.group((App)ConfiguredFeature.J_1907_R.fieldOf("feature").forGetter(p_242789_0_ -> p_242789_0_.J_1907_R), (App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("chance").forGetter(p_236432_0_ -> Float.valueOf(p_236432_0_.R_4764_Y))).apply((Applicative)p_236433_0_, WeightedConfiguredFeature::new));
    public final Supplier<ConfiguredFeature<?, ?>> J_1907_R;
    public final float R_4764_Y;

    public WeightedConfiguredFeature(ConfiguredFeature<?, ?> p_i225822_1_, float p_i225822_2_) {
        this(() -> p_i225822_1_, p_i225822_2_);
    }

    private WeightedConfiguredFeature(Supplier<ConfiguredFeature<?, ?>> p_i241980_1_, float p_i241980_2_) {
        this.J_1907_R = p_i241980_1_;
        this.R_4764_Y = p_i241980_2_;
    }

    public boolean n_1700_B(WorldGenLevel p_242787_1_, z_1753_f p_242787_2_, Random p_242787_3_, c_1514_x p_242787_4_) {
        return this.J_1907_R.get().n_1700_B(p_242787_1_, p_242787_2_, p_242787_3_, p_242787_4_);
    }
}


