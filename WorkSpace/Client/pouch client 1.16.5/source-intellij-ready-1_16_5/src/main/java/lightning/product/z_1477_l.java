/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;

public class z_1477_l
extends a_2900_S {
    private final Container n_1700_B;

    public z_1477_l(int id, W_3491_f playerInventory) {
        this(id, playerInventory, new N_1216_z(5));
    }

    public z_1477_l(int id, W_3491_f playerInventory, Container inventory) {
        super(MenuType.M_182_A, id);
        this.n_1700_B = inventory;
        z_1477_l.n_1700_B(inventory, 5);
        inventory.b_(playerInventory.P_1922_E);
        int i = 51;
        for (int j = 0; j < 5; ++j) {
            this.J_1907_R(new Slot(inventory, j, 44 + j * 18, 20));
        }
        for (int l = 0; l < 3; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.J_1907_R(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.J_1907_R(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
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


