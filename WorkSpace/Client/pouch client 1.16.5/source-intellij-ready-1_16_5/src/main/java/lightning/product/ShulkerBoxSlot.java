/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.T_2915_h;
import lightning.product.Slot;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;

public class ShulkerBoxSlot
extends Slot {
    public ShulkerBoxSlot(Container inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
        super(inventoryIn, slotIndexIn, xPosition, yPosition);
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return !(T_2915_h.n_1700_B(stack.J_1907_R()) instanceof Y_3462_U);
    }
}


