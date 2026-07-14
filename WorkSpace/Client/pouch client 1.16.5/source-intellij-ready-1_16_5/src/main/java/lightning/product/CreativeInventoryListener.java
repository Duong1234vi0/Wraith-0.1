/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.ContainerListener;
import lightning.product.MinecraftClient;

public class CreativeInventoryListener
implements ContainerListener {
    private final MinecraftClient n_1700_B;

    public CreativeInventoryListener(MinecraftClient mc) {
        this.n_1700_B = mc;
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, NonNullList<Z_1993_T> itemsList) {
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
        this.n_1700_B.w_1457_N.sendSlotPacket(stack, slotInd);
    }

    @Override
    public void n_1700_B(a_2900_S containerIn, int varToUpdate, int newValue) {
    }
}



