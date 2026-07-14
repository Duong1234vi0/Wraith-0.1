/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ShulkerBoxSlot;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;

public class x_353_w
extends a_2900_S {
    private final Container n_1700_B;

    public x_353_w(int id, W_3491_f playerInventory) {
        this(id, playerInventory, new N_1216_z(27));
    }

    public x_353_w(int id, W_3491_f playerInventory, Container inventory) {
        super(MenuType.Y_601_j, id);
        x_353_w.n_1700_B(inventory, 27);
        this.n_1700_B = inventory;
        inventory.b_(playerInventory.P_1922_E);
        int i = 3;
        int j = 9;
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.J_1907_R(new ShulkerBoxSlot(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.J_1907_R(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.J_1907_R(new Slot(playerInventory, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.n_1700_B.R_4764_Y(playerIn);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index < this.n_1700_B.Y_259_p() ? !this.n_1700_B(itemstack1, this.n_1700_B.Y_259_p(), this.P_1922_E.size(), true) : !this.n_1700_B(itemstack1, 0, this.n_1700_B.Y_259_p(), false)) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            } else {
                slot.R_4764_Y();
            }
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.n_1700_B.J_1907_R(playerIn);
    }
}


