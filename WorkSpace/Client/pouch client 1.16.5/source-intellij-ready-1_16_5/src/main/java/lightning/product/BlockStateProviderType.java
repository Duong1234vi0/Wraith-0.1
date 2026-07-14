/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.V_1684_a;
import lightning.product.V_3137_a;
import lightning.product.WeightedStateProvider;
import lightning.product.ForestFlowerProvider;
import lightning.product.SimpleStateProvider;
import lightning.product.RotatedBlockProvider;
import lightning.product.BlockStateProvider;

public class BlockStateProviderType<P extends BlockStateProvider> {
    public static final BlockStateProviderType<SimpleStateProvider> n_1700_B = BlockStateProviderType.n_1700_B("simple_state_provider", SimpleStateProvider.n_1700_B);
    public static final BlockStateProviderType<WeightedStateProvider> J_1907_R = BlockStateProviderType.n_1700_B("weighted_state_provider", WeightedStateProvider.n_1700_B);
    public static final BlockStateProviderType<V_1684_a> R_4764_Y = BlockStateProviderType.n_1700_B("plain_flower_provider", V_1684_a.n_1700_B);
    public static final BlockStateProviderType<ForestFlowerProvider> G_564_y = BlockStateProviderType.n_1700_B("forest_flower_provider", ForestFlowerProvider.n_1700_B);
    public static final BlockStateProviderType<RotatedBlockProvider> P_1922_E = BlockStateProviderType.n_1700_B("rotated_block_provider", RotatedBlockProvider.n_1700_B);
    private final Codec<P> u_1723_Y;

    private static <P extends BlockStateProvider> BlockStateProviderType<P> n_1700_B(String name, Codec<P> codec) {
        return V_3137_a.n_1700_B(V_3137_a.r_3651_U, name, new BlockStateProviderType<P>(codec));
    }

    private BlockStateProviderType(Codec<P> codec) {
        this.u_1723_Y = codec;
    }

    public Codec<P> n_1700_B() {
        return this.u_1723_Y;
    }
}


