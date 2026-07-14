/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.FeatureSize;
import lightning.product.ThreeLayersFeatureSize;
import lightning.product.V_3137_a;
import lightning.product.TwoLayersFeatureSize;

public class FeatureSizeType<P extends FeatureSize> {
    public static final FeatureSizeType<TwoLayersFeatureSize> n_1700_B = FeatureSizeType.n_1700_B("two_layers_feature_size", TwoLayersFeatureSize.R_4764_Y);
    public static final FeatureSizeType<ThreeLayersFeatureSize> J_1907_R = FeatureSizeType.n_1700_B("three_layers_feature_size", ThreeLayersFeatureSize.R_4764_Y);
    private final Codec<P> R_4764_Y;

    private static <P extends FeatureSize> FeatureSizeType<P> n_1700_B(String name, Codec<P> codec) {
        return V_3137_a.n_1700_B(V_3137_a.R_3077_Z, name, new FeatureSizeType<P>(codec));
    }

    private FeatureSizeType(Codec<P> codec) {
        this.R_4764_Y = codec;
    }

    public Codec<P> n_1700_B() {
        return this.R_4764_Y;
    }
}


