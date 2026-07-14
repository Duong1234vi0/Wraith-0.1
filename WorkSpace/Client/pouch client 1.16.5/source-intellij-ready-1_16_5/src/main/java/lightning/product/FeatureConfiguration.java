/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.stream.Stream;
import lightning.product.ConfiguredFeature;
import lightning.product.o_2105_O;

public interface FeatureConfiguration {
    public static final o_2105_O P_4830_p = o_2105_O.J_1907_R;

    default public Stream<ConfiguredFeature<?, ?>> M_() {
        return Stream.empty();
    }
}


