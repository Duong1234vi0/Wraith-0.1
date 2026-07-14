/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.ConfiguredFeature;
import lightning.product.WeightedConfiguredFeature;
import lightning.product.FeatureConfiguration;

public class RandomFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<RandomFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_236585_0_ -> p_236585_0_.apply2(RandomFeatureConfiguration::new, (App)WeightedConfiguredFeature.n_1700_B.listOf().fieldOf("features").forGetter(p_236586_0_ -> p_236586_0_.J_1907_R), (App)ConfiguredFeature.J_1907_R.fieldOf("default").forGetter(p_236584_0_ -> p_236584_0_.R_4764_Y)));
    public final List<WeightedConfiguredFeature> J_1907_R;
    public final Supplier<ConfiguredFeature<?, ?>> R_4764_Y;

    public RandomFeatureConfiguration(List<WeightedConfiguredFeature> features, ConfiguredFeature<?, ?> defaultFeature) {
        this(features, () -> defaultFeature);
    }

    private RandomFeatureConfiguration(List<WeightedConfiguredFeature> p_i241991_1_, Supplier<ConfiguredFeature<?, ?>> p_i241991_2_) {
        this.J_1907_R = p_i241991_1_;
        this.R_4764_Y = p_i241991_2_;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> M_() {
        return Stream.concat(this.J_1907_R.stream().flatMap(p_242812_0_ -> p_242812_0_.J_1907_R.get().G_564_y()), this.R_4764_Y.get().G_564_y());
    }
}


