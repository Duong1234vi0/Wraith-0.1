/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.U_2534_D;
import lightning.product.W_3443_Y;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.Items;

public class R_3940_n
extends a_2900_S {
    private final Container n_1700_B;
    private final U_2534_D J_1907_R;

    public R_3940_n(int id, W_3491_f playerInventory, Container horseInventory, final U_2534_D horse) {
        super(null, id);
        this.n_1700_B = horseInventory;
        this.J_1907_R = horse;
        int i = 3;
        horseInventory.b_(playerInventory.P_1922_E);
        int j = -18;
        this.J_1907_R(new Slot(this, horseInventory, 0, 8, 18){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() == Items.Z_361_l && !this.J_1907_R() && horse.n_1700_B();
            }

            @Override
            public boolean u_1723_Y() {
                return horse.n_1700_B();
            }
        });
        this.J_1907_R(new Slot(this, horseInventory, 1, 8, 36){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return horse.M_588_G(stack);
            }

            @Override
            public boolean u_1723_Y() {
                return horse.N_4006_T();
            }

            @Override
            public int G_564_y() {
                return 1;
            }
        });
        if (horse instanceof W_3443_Y && ((W_3443_Y)horse).V_1176_p()) {
            for (int k = 0; k < 3; ++k) {
                for (int l = 0; l < ((W_3443_Y)horse).V_537_k(); ++l) {
                    this.J_1907_R(new Slot(horseInventory, 2 + l + k * ((W_3443_Y)horse).V_537_k(), 80 + l * 18, 18 + k * 18));
                }
            }
        }
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.J_1907_R(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.J_1907_R(new Slot(playerInventory, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.n_1700_B.R_4764_Y(playerIn) && this.J_1907_R.RealmsLongRunningMcoTaskScreen() && this.J_1907_R.R_4764_Y((N_4263_v)playerIn) < 8.0f;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            int i = this.n_1700_B.Y_259_p();
            if (index < i) {
                if (!this.n_1700_B(itemstack1, i, this.P_1922_E.size(), true)) {
                    return Z_1993_T.J_1907_R;
                }
            } else if (this.n_1700_B(1).n_1700_B(itemstack1) && !this.n_1700_B(1).J_1907_R()) {
                if (!this.n_1700_B(itemstack1, 1, 2, false)) {
                    return Z_1993_T.J_1907_R;
                }
            } else if (this.n_1700_B(0).n_1700_B(itemstack1)) {
                if (!this.n_1700_B(itemstack1, 0, 1, false)) {
                    return Z_1993_T.J_1907_R;
                }
            } else if (i <= 2 || !this.n_1700_B(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k ? !this.n_1700_B(itemstack1, i, j, false) : (index >= i && index < j ? !this.n_1700_B(itemstack1, j, k, false) : !this.n_1700_B(itemstack1, j, j, false))) {
                    return Z_1993_T.J_1907_R;
                }
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


