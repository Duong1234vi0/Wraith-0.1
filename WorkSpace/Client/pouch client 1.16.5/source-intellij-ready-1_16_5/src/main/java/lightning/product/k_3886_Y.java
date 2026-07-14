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
import lightning.product.c_1514_x;
import lightning.product.DoublePlantBlock;
import lightning.product.LevelAccessor;
import lightning.product.BlockPlacer;

public class k_3886_Y
extends BlockPlacer {
    public static final Codec<k_3886_Y> J_1907_R;
    public static final k_3886_Y R_4764_Y;

    @Override
    protected BlockPlacerType<?> n_1700_B() {
        return BlockPlacerType.J_1907_R;
    }

    @Override
    public void n_1700_B(LevelAccessor world, c_1514_x pos, K_4074_S state, Random random) {
        ((DoublePlantBlock)state.J_1907_R()).n_1700_B(world, pos, 2);
    }

    static {
        R_4764_Y = new k_3886_Y();
        J_1907_R = Codec.unit(() -> R_4764_Y);
    }
}


