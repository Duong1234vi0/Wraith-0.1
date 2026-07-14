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
import lightning.product.RotatedPillarBlock;
import lightning.product.T_2915_h;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.BlockStateProvider;
import lightning.product.BlockStateProviderType;
import lightning.product.q_4293_E;

public class RotatedBlockProvider
extends BlockStateProvider {
    public static final Codec<RotatedBlockProvider> n_1700_B = K_4074_S.J_1907_R.fieldOf("state").xmap(q_4293_E.n_1700_B::J_1907_R, T_2915_h::multiplayerClientSuggestionProvider).xmap(RotatedBlockProvider::new, provider -> provider.R_4764_Y).codec();
    private final T_2915_h R_4764_Y;

    public RotatedBlockProvider(T_2915_h block) {
        this.R_4764_Y = block;
    }

    @Override
    protected BlockStateProviderType<?> n_1700_B() {
        return BlockStateProviderType.P_1922_E;
    }

    @Override
    public K_4074_S n_1700_B(Random randomIn, c_1514_x blockPosIn) {
        b_257_Y.n_1700_B direction$axis = b_257_Y.n_1700_B.n_1700_B(randomIn);
        return (K_4074_S)this.R_4764_Y.multiplayerClientSuggestionProvider().n_1700_B(RotatedPillarBlock.t_1786_h, direction$axis);
    }
}


