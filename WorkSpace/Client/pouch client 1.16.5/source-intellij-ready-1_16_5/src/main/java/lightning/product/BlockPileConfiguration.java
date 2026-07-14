/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.BlockStateProvider;
import lightning.product.FeatureConfiguration;

public class BlockPileConfiguration
implements FeatureConfiguration {
    public static final Codec<BlockPileConfiguration> n_1700_B = BlockStateProvider.J_1907_R.fieldOf("state_provider").xmap(BlockPileConfiguration::new, p_236454_0_ -> p_236454_0_.J_1907_R).codec();
    public final BlockStateProvider J_1907_R;

    public BlockPileConfiguration(BlockStateProvider p_i225830_1_) {
        this.J_1907_R = p_i225830_1_;
    }
}


