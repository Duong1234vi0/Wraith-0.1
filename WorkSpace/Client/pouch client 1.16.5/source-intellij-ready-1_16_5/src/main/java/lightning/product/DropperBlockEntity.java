/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.l_3848_Y;
import lightning.product.BlockEntityType;
import lightning.product.x_282_a;

public class DropperBlockEntity
extends l_3848_Y {
    public DropperBlockEntity() {
        super(BlockEntityType.v_4262_N);
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.dropper");
    }
}


