/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.W_3491_f;
import lightning.product.a_2900_S;
import lightning.product.g_4614_N;
import lightning.product.RecipeType;
import lightning.product.n_1680_G;
import lightning.product.BlockEntityType;
import lightning.product.x_282_a;

public class FurnaceBlockEntity
extends n_1680_G {
    public FurnaceBlockEntity() {
        super(BlockEntityType.n_1700_B, RecipeType.J_1907_R);
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.furnace");
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new g_4614_N(id, player, this, this.J_1907_R);
    }
}


