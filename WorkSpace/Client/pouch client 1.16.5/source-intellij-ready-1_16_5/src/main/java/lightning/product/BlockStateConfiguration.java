/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.K_4074_S;
import lightning.product.FeatureConfiguration;

public class BlockStateConfiguration
implements FeatureConfiguration {
    public static final Codec<BlockStateConfiguration> n_1700_B = K_4074_S.J_1907_R.fieldOf("state").xmap(BlockStateConfiguration::new, p_236456_0_ -> p_236456_0_.J_1907_R).codec();
    public final K_4074_S J_1907_R;

    public BlockStateConfiguration(K_4074_S p_i225831_1_) {
        this.J_1907_R = p_i225831_1_;
    }
}


