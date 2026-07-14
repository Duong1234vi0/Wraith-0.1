/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_512_t;
import lightning.product.F_2904_S;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.RecipeType;
import lightning.product.n_1680_G;
import lightning.product.BlockEntityType;
import lightning.product.x_282_a;

public class SmokerBlockEntity
extends n_1680_G {
    public SmokerBlockEntity() {
        super(BlockEntityType.c_3005_b, RecipeType.G_564_y);
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.smoker");
    }

    @Override
    protected int n_1700_B(Z_1993_T fuel) {
        return super.n_1700_B(fuel) / 2;
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new B_512_t(id, player, this, this.J_1907_R);
    }
}


