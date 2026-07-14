/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.L_2125_Q;
import lightning.product.Container;
import lightning.product.NonNullList;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.RecipeType;

public class ResultSlot
extends Slot {
    private final CraftingContainer n_1700_B;
    private final a_3913_L J_1907_R;
    private int v_4262_N;

    public ResultSlot(a_3913_L player, CraftingContainer craftingInventory, Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.J_1907_R = player;
        this.n_1700_B = craftingInventory;
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
    protected void J_1907_R(int numItemsCrafted) {
        this.v_4262_N += numItemsCrafted;
    }

    @Override
    protected void b_(Z_1993_T stack) {
        if (this.v_4262_N > 0) {
            stack.n_1700_B(this.J_1907_R.O_508_d, this.J_1907_R, this.v_4262_N);
        }
        if (this.R_4764_Y instanceof L_2125_Q) {
            ((L_2125_Q)((Object)this.R_4764_Y)).n_1700_B(this.J_1907_R);
        }
        this.v_4262_N = 0;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
        this.b_(stack);
        NonNullList<Z_1993_T> nonnulllist = thePlayer.O_508_d.s_956_w().R_4764_Y(RecipeType.n_1700_B, this.n_1700_B, thePlayer.O_508_d);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            Z_1993_T itemstack = this.n_1700_B.s_956_w(i);
            Z_1993_T itemstack1 = nonnulllist.get(i);
            if (!itemstack.n_1700_B()) {
                this.n_1700_B.n_1700_B(i, 1);
                itemstack = this.n_1700_B.s_956_w(i);
            }
            if (itemstack1.n_1700_B()) continue;
            if (itemstack.n_1700_B()) {
                this.n_1700_B.J_1907_R(i, itemstack1);
                continue;
            }
            if (Z_1993_T.R_4764_Y(itemstack, itemstack1) && Z_1993_T.n_1700_B(itemstack, itemstack1)) {
                itemstack1.u_1723_Y(itemstack.t_4043_B());
                this.n_1700_B.J_1907_R(i, itemstack1);
                continue;
            }
            if (this.J_1907_R.l_1268_F.P_1922_E(itemstack1)) continue;
            this.J_1907_R.n_1700_B(itemstack1, false);
        }
        return stack;
    }
}


