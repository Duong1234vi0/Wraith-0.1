/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.Decoratable;
import lightning.product.WorldGenLevel;
import lightning.product.V_3137_a;
import lightning.product.DecoratedFeatureConfiguration;
import lightning.product.c_1514_x;
import lightning.product.Feature;
import lightning.product.ConfiguredDecorator;
import lightning.product.WeightedConfiguredFeature;
import lightning.product.n_4684_C;
import lightning.product.FeatureConfiguration;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfiguredFeature<FC extends FeatureConfiguration, F extends Feature<FC>>
implements Decoratable<ConfiguredFeature<?, ?>> {
    public static final Codec<ConfiguredFeature<?, ?>> n_1700_B = V_3137_a.RealmsServerPing.dispatch(p_236266_0_ -> p_236266_0_.P_1922_E, Feature::n_1700_B);
    public static final Codec<Supplier<ConfiguredFeature<?, ?>>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.Ops, n_1700_B);
    public static final Codec<List<Supplier<ConfiguredFeature<?, ?>>>> R_4764_Y = n_4684_C.J_1907_R(V_3137_a.Ops, n_1700_B);
    public static final Logger G_564_y = LogManager.getLogger();
    public final F P_1922_E;
    public final FC u_1723_Y;

    public ConfiguredFeature(F featureIn, FC configIn) {
        this.P_1922_E = featureIn;
        this.u_1723_Y = configIn;
    }

    public F J_1907_R() {
        return this.P_1922_E;
    }

    public FC R_4764_Y() {
        return this.u_1723_Y;
    }

    public ConfiguredFeature<?, ?> J_1907_R(ConfiguredDecorator<?> p_227228_1_) {
        return Feature.T_3594_S.J_1907_R(new DecoratedFeatureConfiguration(() -> this, p_227228_1_));
    }

    public WeightedConfiguredFeature n_1700_B(float p_227227_1_) {
        return new WeightedConfiguredFeature(this, p_227227_1_);
    }

    public boolean n_1700_B(WorldGenLevel p_242765_1_, z_1753_f p_242765_2_, Random p_242765_3_, c_1514_x p_242765_4_) {
        return ((Feature)this.P_1922_E).n_1700_B(p_242765_1_, p_242765_2_, p_242765_3_, p_242765_4_, this.u_1723_Y);
    }

    public Stream<ConfiguredFeature<?, ?>> G_564_y() {
        return Stream.concat(Stream.of(this), this.u_1723_Y.M_());
    }

    @Override
    public /* synthetic */ Object n_1700_B(ConfiguredDecorator i_3648_a2) {
        return this.J_1907_R(i_3648_a2);
    }
}


