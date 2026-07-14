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
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.ConfiguredFeature;
import lightning.product.FeatureConfiguration;

public class RandomBooleanFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<RandomBooleanFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_236581_0_ -> p_236581_0_.group((App)ConfiguredFeature.J_1907_R.fieldOf("feature_true").forGetter(p_236582_0_ -> p_236582_0_.J_1907_R), (App)ConfiguredFeature.J_1907_R.fieldOf("feature_false").forGetter(p_236580_0_ -> p_236580_0_.R_4764_Y)).apply((Applicative)p_236581_0_, RandomBooleanFeatureConfiguration::new));
    public final Supplier<ConfiguredFeature<?, ?>> J_1907_R;
    public final Supplier<ConfiguredFeature<?, ?>> R_4764_Y;

    public RandomBooleanFeatureConfiguration(Supplier<ConfiguredFeature<?, ?>> p_i241990_1_, Supplier<ConfiguredFeature<?, ?>> p_i241990_2_) {
        this.J_1907_R = p_i241990_1_;
        this.R_4764_Y = p_i241990_2_;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> M_() {
        return Stream.concat(this.J_1907_R.get().G_564_y(), this.R_4764_Y.get().G_564_y());
    }
}


