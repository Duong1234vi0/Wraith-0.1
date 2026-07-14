/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.n_1680_G;

public class FurnaceResultSlot
extends Slot {
    private final a_3913_L n_1700_B;
    private int J_1907_R;

    public FurnaceResultSlot(a_3913_L player, Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.n_1700_B = player;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(int amount) {
        if (this.J_1907_R()) {
            this.J_1907_R += Math.min(amount, this.n_1700_B().t_4043_B());
        }
        return super.n_1700_B(amount);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
        this.b_(stack);
        super.n_1700_B(thePlayer, stack);
        return stack;
    }

    @Override
    protected void n_1700_B(Z_1993_T stack, int amount) {
        this.J_1907_R += amount;
        this.b_(stack);
    }

    @Override
    protected void b_(Z_1993_T stack) {
        stack.n_1700_B(this.n_1700_B.O_508_d, this.n_1700_B, this.J_1907_R);
        if (!this.n_1700_B.O_508_d.Y_259_p && this.R_4764_Y instanceof n_1680_G) {
            ((n_1680_G)this.R_4764_Y).G_564_y(this.n_1700_B);
        }
        this.J_1907_R = 0;
    }
}


