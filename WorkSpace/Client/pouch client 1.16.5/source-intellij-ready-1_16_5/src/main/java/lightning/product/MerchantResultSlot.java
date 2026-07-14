/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2000_A;
import lightning.product.Stats;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.Merchant;
import lightning.product.MerchantOffer;

public class MerchantResultSlot
extends Slot {
    private final H_2000_A n_1700_B;
    private final a_3913_L J_1907_R;
    private int v_4262_N;
    private final Merchant w_1484_f;

    public MerchantResultSlot(a_3913_L player, Merchant merchant, H_2000_A merchantInventory, int slotIndex, int xPosition, int yPosition) {
        super(merchantInventory, slotIndex, xPosition, yPosition);
        this.J_1907_R = player;
        this.w_1484_f = merchant;
        this.n_1700_B = merchantInventory;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(int amount) {
        if (this.J_1907_R()) {
            this.v_4262_N += Math.min(amount, this.n_1700_B().t_4043_B());
        }
        return super.n_1700_B(amount);
    }

    @Override
    protected void n_1700_B(Z_1993_T stack, int amount) {
        this.v_4262_N += amount;
        this.b_(stack);
    }

    @Override
    protected void b_(Z_1993_T stack) {
        stack.n_1700_B(this.J_1907_R.O_508_d, this.J_1907_R, this.v_4262_N);
        this.v_4262_N = 0;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
        this.b_(stack);
        MerchantOffer merchantoffer = this.n_1700_B.G_564_y();
        if (merchantoffer != null) {
            Z_1993_T itemstack1;
            Z_1993_T itemstack = this.n_1700_B.s_956_w(0);
            if (merchantoffer.J_1907_R(itemstack, itemstack1 = this.n_1700_B.s_956_w(1)) || merchantoffer.J_1907_R(itemstack1, itemstack)) {
                this.w_1484_f.n_1700_B(merchantoffer);
                thePlayer.J_1907_R(Stats.B_1668_F);
                this.n_1700_B.J_1907_R(0, itemstack);
                this.n_1700_B.J_1907_R(1, itemstack1);
            }
            this.w_1484_f.n_1700_B(this.w_1484_f.G_564_y() + merchantoffer.Q_4569_t());
        }
        return stack;
    }
}


