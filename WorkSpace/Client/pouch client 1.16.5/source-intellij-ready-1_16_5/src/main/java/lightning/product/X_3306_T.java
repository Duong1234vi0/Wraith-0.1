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
import lightning.product.LevelAccessor;
import lightning.product.BlockPlacer;

public class X_3306_T
extends BlockPlacer {
    public static final Codec<X_3306_T> J_1907_R;
    public static final X_3306_T R_4764_Y;

    @Override
    protected BlockPlacerType<?> n_1700_B() {
        return BlockPlacerType.n_1700_B;
    }

    @Override
    public void n_1700_B(LevelAccessor world, c_1514_x pos, K_4074_S state, Random random) {
        world.n_1700_B(pos, state, 2);
    }

    static {
        R_4764_Y = new X_3306_T();
        J_1907_R = Codec.unit(() -> R_4764_Y);
    }
}


