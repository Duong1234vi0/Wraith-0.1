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

public class ChestMenu
extends a_2900_S {
    private final Container n_1700_B;
    private final int J_1907_R;

    private ChestMenu(MenuType<?> type, int id, W_3491_f player, int rows) {
        this(type, id, player, new N_1216_z(9 * rows), rows);
    }

    public static ChestMenu n_1700_B(int id, W_3491_f player) {
        return new ChestMenu(MenuType.n_1700_B, id, player, 1);
    }

    public static ChestMenu J_1907_R(int id, W_3491_f player) {
        return new ChestMenu(MenuType.J_1907_R, id, player, 2);
    }

    public static ChestMenu R_4764_Y(int id, W_3491_f player) {
        return new ChestMenu(MenuType.R_4764_Y, id, player, 3);
    }

    public static ChestMenu G_564_y(int id, W_3491_f player) {
        return new ChestMenu(MenuType.G_564_y, id, player, 4);
    }

    public static ChestMenu P_1922_E(int id, W_3491_f player) {
        return new ChestMenu(MenuType.P_1922_E, id, player, 5);
    }

    public static ChestMenu u_1723_Y(int id, W_3491_f player) {
        return new ChestMenu(MenuType.u_1723_Y, id, player, 6);
    }

    public static ChestMenu n_1700_B(int id, W_3491_f player, Container blockEntity) {
        return new ChestMenu(MenuType.R_4764_Y, id, player, blockEntity, 3);
    }

    public static ChestMenu J_1907_R(int id, W_3491_f player, Container blockEntity) {
        return new ChestMenu(MenuType.u_1723_Y, id, player, blockEntity, 6);
    }

    public ChestMenu(MenuType<?> type, int id, W_3491_f playerInventoryIn, Container p_i50092_4_, int rows) {
        super(type, id);
        ChestMenu.n_1700_B(p_i50092_4_, rows * 9);
        this.n_1700_B = p_i50092_4_;
        this.J_1907_R = rows;
        p_i50092_4_.b_(playerInventoryIn.P_1922_E);
        int i = (this.J_1907_R - 4) * 18;
        for (int j = 0; j < this.J_1907_R; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.J_1907_R(new Slot(p_i50092_4_, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.J_1907_R(new Slot(playerInventoryIn, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.J_1907_R(new Slot(playerInventoryIn, i1, 8 + i1 * 18, 161 + i));
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
            if (index < this.J_1907_R * 9 ? !this.n_1700_B(itemstack1, this.J_1907_R * 9, this.P_1922_E.size(), true) : !this.n_1700_B(itemstack1, 0, this.J_1907_R * 9, false)) {
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

    public Container n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }
}


