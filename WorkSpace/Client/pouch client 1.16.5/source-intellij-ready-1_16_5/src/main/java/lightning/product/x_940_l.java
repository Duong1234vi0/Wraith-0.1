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

public class x_940_l
extends a_2900_S {
    private final Container n_1700_B;

    public x_940_l(int p_i50087_1_, W_3491_f p_i50087_2_) {
        this(p_i50087_1_, p_i50087_2_, new N_1216_z(9));
    }

    public x_940_l(int p_i50088_1_, W_3491_f p_i50088_2_, Container p_i50088_3_) {
        super(MenuType.v_4262_N, p_i50088_1_);
        x_940_l.n_1700_B(p_i50088_3_, 9);
        this.n_1700_B = p_i50088_3_;
        p_i50088_3_.b_(p_i50088_2_.P_1922_E);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.J_1907_R(new Slot(p_i50088_3_, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.J_1907_R(new Slot(p_i50088_2_, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.J_1907_R(new Slot(p_i50088_2_, l, 8 + l * 18, 142));
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
            if (index < 9 ? !this.n_1700_B(itemstack1, 9, 45, true) : !this.n_1700_B(itemstack1, 0, 9, false)) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            } else {
                slot.R_4764_Y();
            }
            if (itemstack1.t_4043_B() == itemstack.t_4043_B()) {
                return Z_1993_T.J_1907_R;
            }
            slot.n_1700_B(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.n_1700_B.J_1907_R(playerIn);
    }
}


