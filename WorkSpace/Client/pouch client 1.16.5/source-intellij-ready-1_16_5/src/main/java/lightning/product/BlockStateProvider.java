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
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.BlockStateProviderType;

public abstract class BlockStateProvider {
    public static final Codec<BlockStateProvider> J_1907_R = V_3137_a.r_3651_U.dispatch(BlockStateProvider::n_1700_B, BlockStateProviderType::n_1700_B);

    protected abstract BlockStateProviderType<?> n_1700_B();

    public abstract K_4074_S n_1700_B(Random var1, c_1514_x var2);
}


