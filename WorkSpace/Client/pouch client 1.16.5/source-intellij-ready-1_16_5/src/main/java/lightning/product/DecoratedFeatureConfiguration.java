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
import lightning.product.V_3137_a;
import lightning.product.ConfiguredFeature;
import lightning.product.Feature;
import lightning.product.ConfiguredDecorator;
import lightning.product.FeatureConfiguration;

public class DecoratedFeatureConfiguration
implements FeatureConfiguration {
    public static final Codec<DecoratedFeatureConfiguration> n_1700_B = RecordCodecBuilder.create(p_236493_0_ -> p_236493_0_.group((App)ConfiguredFeature.J_1907_R.fieldOf("feature").forGetter(p_236494_0_ -> p_236494_0_.J_1907_R), (App)ConfiguredDecorator.n_1700_B.fieldOf("decorator").forGetter(p_236492_0_ -> p_236492_0_.R_4764_Y)).apply((Applicative)p_236493_0_, DecoratedFeatureConfiguration::new));
    public final Supplier<ConfiguredFeature<?, ?>> J_1907_R;
    public final ConfiguredDecorator<?> R_4764_Y;

    public DecoratedFeatureConfiguration(Supplier<ConfiguredFeature<?, ?>> p_i241984_1_, ConfiguredDecorator<?> p_i241984_2_) {
        this.J_1907_R = p_i241984_1_;
        this.R_4764_Y = p_i241984_2_;
    }

    public String toString() {
        return String.format("< %s [%s | %s] >", this.getClass().getSimpleName(), V_3137_a.RealmsServerPing.J_1907_R((Feature<?>)this.J_1907_R.get().J_1907_R()), this.R_4764_Y);
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> M_() {
        return this.J_1907_R.get().G_564_y();
    }
}


