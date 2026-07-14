/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.c_1514_x;
import lightning.product.BlockStateProvider;
import lightning.product.BlockStateProviderType;

public class SimpleStateProvider
extends BlockStateProvider {
    public static final Codec<SimpleStateProvider> n_1700_B = K_4074_S.J_1907_R.fieldOf("state").xmap(SimpleStateProvider::new, provider -> provider.R_4764_Y).codec();
    private final K_4074_S R_4764_Y;

    public SimpleStateProvider(K_4074_S state) {
        this.R_4764_Y = state;
    }

    @Override
    protected BlockStateProviderType<?> n_1700_B() {
        return BlockStateProviderType.n_1700_B;
    }

    @Override
    public K_4074_S n_1700_B(Random randomIn, c_1514_x blockPosIn) {
        return this.R_4764_Y;
    }
}


