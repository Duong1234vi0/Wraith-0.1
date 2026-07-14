/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.BlockPlacerType;
import lightning.product.K_4074_S;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.LevelAccessor;

public abstract class BlockPlacer {
    public static final Codec<BlockPlacer> n_1700_B = V_3137_a.RowButton.dispatch(BlockPlacer::n_1700_B, BlockPlacerType::n_1700_B);

    public abstract void n_1700_B(LevelAccessor var1, c_1514_x var2, K_4074_S var3, Random var4);

    protected abstract BlockPlacerType<?> n_1700_B();
}


