/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.ConfiguredFeature;
import lightning.product.FeatureConfiguration;

public class SimpleRandomFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<SimpleRandomFeatureConfiguration> n_1700_B = ConfiguredFeature.R_4764_Y.fieldOf("features").xmap(SimpleRandomFeatureConfiguration::new, p_236643_0_ -> p_236643_0_.J_1907_R).codec();
    public final List<Supplier<ConfiguredFeature<?, ?>>> J_1907_R;

    public SimpleRandomFeatureConfiguration(List<Supplier<ConfiguredFeature<?, ?>>> features) {
        this.J_1907_R = features;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> M_() {
        return this.J_1907_R.stream().flatMap(p_242826_0_ -> ((ConfiguredFeature)p_242826_0_.get()).G_564_y());
    }
}


