/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.E_414_E;
import lightning.product.SimpleContainerData;
import lightning.product.L_1875_m;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.U_3554_Q;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.ContainerData;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.y_528_b;

public class BrewingStandMenu
extends a_2900_S {
    private final Container n_1700_B;
    private final ContainerData J_1907_R;
    private final Slot R_4764_Y;

    public BrewingStandMenu(int id, W_3491_f playerInventory) {
        this(id, playerInventory, new N_1216_z(5), new SimpleContainerData(2));
    }

    public BrewingStandMenu(int id, W_3491_f playerInventory, Container inventory, ContainerData p_i50096_4_) {
        super(MenuType.u_2550_I, id);
        BrewingStandMenu.n_1700_B(inventory, 5);
        BrewingStandMenu.n_1700_B(p_i50096_4_, 2);
        this.n_1700_B = inventory;
        this.J_1907_R = p_i50096_4_;
        this.J_1907_R(new R_4764_Y(inventory, 0, 56, 51));
        this.J_1907_R(new R_4764_Y(inventory, 1, 79, 58));
        this.J_1907_R(new R_4764_Y(inventory, 2, 102, 51));
        this.R_4764_Y = this.J_1907_R(new J_1907_R(inventory, 3, 79, 17));
        this.J_1907_R(new n_1700_B(inventory, 4, 17, 17));
        this.n_1700_B(p_i50096_4_);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(playerInventory, k, 8 + k * 18, 142));
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
            if ((index < 0 || index > 2) && index != 3 && index != 4) {
                if (lightning.product.BrewingStandMenu$n_1700_B.G_564_y(itemstack) ? this.n_1700_B(itemstack1, 4, 5, false) || this.R_4764_Y.n_1700_B(itemstack1) && !this.n_1700_B(itemstack1, 3, 4, false) : (this.R_4764_Y.n_1700_B(itemstack1) ? !this.n_1700_B(itemstack1, 3, 4, false) : (lightning.product.BrewingStandMenu$R_4764_Y.G_564_y(itemstack) && itemstack.t_4043_B() == 1 ? !this.n_1700_B(itemstack1, 0, 3, false) : (index >= 5 && index < 32 ? !this.n_1700_B(itemstack1, 32, 41, false) : (index >= 32 && index < 41 ? !this.n_1700_B(itemstack1, 5, 32, false) : !this.n_1700_B(itemstack1, 5, 41, false)))))) {
                    return Z_1993_T.J_1907_R;
                }
            } else {
                if (!this.n_1700_B(itemstack1, 5, 41, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
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

    public int n_1700_B() {
        return this.J_1907_R.n_1700_B(1);
    }

    public int J_1907_R() {
        return this.J_1907_R.n_1700_B(0);
    }

    static class R_4764_Y
    extends Slot {
        public R_4764_Y(Container p_i47598_1_, int p_i47598_2_, int p_i47598_3_, int p_i47598_4_) {
            super(p_i47598_1_, p_i47598_2_, p_i47598_3_, p_i47598_4_);
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack) {
            return lightning.product.BrewingStandMenu$R_4764_Y.G_564_y(stack);
        }

        @Override
        public int G_564_y() {
            return 1;
        }

        @Override
        public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
            y_528_b potion = L_1875_m.G_564_y(stack);
            if (thePlayer instanceof B_4088_l) {
                U_3554_Q.u_2550_I.n_1700_B((B_4088_l)thePlayer, potion);
            }
            super.n_1700_B(thePlayer, stack);
            return stack;
        }

        public static boolean G_564_y(Z_1993_T stack) {
            q_1613_l item = stack.J_1907_R();
            return item == Items.j_2461_G || item == Items.g_2492_v || item == Items.NetherrackBlock || item == Items.Y_3588_g;
        }
    }

    static class J_1907_R
    extends Slot {
        public J_1907_R(Container iInventoryIn, int index, int xPosition, int yPosition) {
            super(iInventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack) {
            return E_414_E.n_1700_B(stack);
        }

        @Override
        public int G_564_y() {
            return 64;
        }
    }

    static class n_1700_B
    extends Slot {
        public n_1700_B(Container iInventoryIn, int index, int xPosition, int yPosition) {
            super(iInventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack) {
            return lightning.product.BrewingStandMenu$n_1700_B.G_564_y(stack);
        }

        public static boolean G_564_y(Z_1993_T itemStackIn) {
            return itemStackIn.J_1907_R() == Items.C_3528_u;
        }

        @Override
        public int G_564_y() {
            return 64;
        }
    }
}


