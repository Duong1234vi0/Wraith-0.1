/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.FeatureConfiguration;

public class o_2105_O
implements FeatureConfiguration {
    public static final Codec<o_2105_O> n_1700_B;
    public static final o_2105_O J_1907_R;

    static {
        J_1907_R = new o_2105_O();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


