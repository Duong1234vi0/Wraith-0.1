/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.FeatureConfiguration;

public class ShipwreckConfiguration
implements FeatureConfiguration {
    public static final Codec<ShipwreckConfiguration> n_1700_B = Codec.BOOL.fieldOf("is_beached").orElse((Object)false).xmap(ShipwreckConfiguration::new, p_236635_0_ -> p_236635_0_.J_1907_R).codec();
    public final boolean J_1907_R;

    public ShipwreckConfiguration(boolean isBeachedIn) {
        this.J_1907_R = isBeachedIn;
    }
}


