/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 */
package lightning.product;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.Random;
import lightning.product.K_2588_D;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.BlockStateProvider;
import lightning.product.BlockStateProviderType;

public class WeightedStateProvider
extends BlockStateProvider {
    public static final Codec<WeightedStateProvider> n_1700_B = K_2588_D.n_1700_B(K_4074_S.J_1907_R).comapFlatMap(WeightedStateProvider::n_1700_B, provider -> provider.R_4764_Y).fieldOf("entries").codec();
    private final K_2588_D<K_4074_S> R_4764_Y;

    private static DataResult<WeightedStateProvider> n_1700_B(K_2588_D<K_4074_S> weightedStates) {
        return weightedStates.J_1907_R() ? DataResult.error((String)"WeightedStateProvider with no states") : DataResult.success((Object)new WeightedStateProvider(weightedStates));
    }

    private WeightedStateProvider(K_2588_D<K_4074_S> weightedStates) {
        this.R_4764_Y = weightedStates;
    }

    @Override
    protected BlockStateProviderType<?> n_1700_B() {
        return BlockStateProviderType.J_1907_R;
    }

    public WeightedStateProvider() {
        this(new K_2588_D<K_4074_S>());
    }

    public WeightedStateProvider n_1700_B(K_4074_S blockStateIn, int weightIn) {
        this.R_4764_Y.n_1700_B(blockStateIn, weightIn);
        return this;
    }

    @Override
    public K_4074_S n_1700_B(Random randomIn, c_1514_x blockPosIn) {
        return this.R_4764_Y.J_1907_R(randomIn);
    }
}


