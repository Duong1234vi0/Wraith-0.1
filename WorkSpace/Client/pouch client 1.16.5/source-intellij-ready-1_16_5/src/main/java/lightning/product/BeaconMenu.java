/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.SimpleContainerData;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.ItemTags;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.g_422_i;
import lightning.product.ContainerData;
import lightning.product.ContainerLevelAccess;

public class BeaconMenu
extends a_2900_S {
    private final Container n_1700_B = new N_1216_z(this, 1){

        @Override
        public boolean a_(int index, Z_1993_T stack) {
            return stack.J_1907_R().n_1700_B(ItemTags.T_3594_S);
        }

        @Override
        public int J_() {
            return 1;
        }
    };
    private final n_1700_B J_1907_R;
    private final ContainerLevelAccess R_4764_Y;
    private final ContainerData G_564_y;

    public BeaconMenu(int id, Container p_i50099_2_) {
        this(id, p_i50099_2_, new SimpleContainerData(3), ContainerLevelAccess.n_1700_B);
    }

    public BeaconMenu(int id, Container inventory, ContainerData p_i50100_3_, ContainerLevelAccess worldPosCallable) {
        super(MenuType.t_148_a, id);
        BeaconMenu.n_1700_B(p_i50100_3_, 3);
        this.G_564_y = p_i50100_3_;
        this.R_4764_Y = worldPosCallable;
        this.J_1907_R = new n_1700_B(this, this.n_1700_B, 0, 136, 110);
        this.J_1907_R(this.J_1907_R);
        this.n_1700_B(p_i50100_3_);
        int i = 36;
        int j = 137;
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.J_1907_R(new Slot(inventory, l + k * 9 + 9, 36 + l * 18, 137 + k * 18));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.J_1907_R(new Slot(inventory, i1, 36 + i1 * 18, 195));
        }
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        Z_1993_T itemstack;
        super.J_1907_R(playerIn);
        if (!playerIn.O_508_d.Y_259_p && !(itemstack = this.J_1907_R.n_1700_B(this.J_1907_R.G_564_y())).n_1700_B()) {
            playerIn.n_1700_B(itemstack, false);
        }
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return BeaconMenu.n_1700_B(this.R_4764_Y, playerIn, a_3742_W.k_578_l);
    }

    @Override
    public void n_1700_B(int id, int data) {
        super.n_1700_B(id, data);
        this.M_588_G();
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == 0) {
                if (!this.n_1700_B(itemstack1, 1, 37, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (!this.J_1907_R.J_1907_R() && this.J_1907_R.n_1700_B(itemstack1) && itemstack1.t_4043_B() == 1 ? !this.n_1700_B(itemstack1, 0, 1, false) : (index >= 1 && index < 28 ? !this.n_1700_B(itemstack1, 28, 37, false) : (index >= 28 && index < 37 ? !this.n_1700_B(itemstack1, 1, 28, false) : !this.n_1700_B(itemstack1, 1, 37, false)))) {
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

    public int n_1700_B() {
        return this.G_564_y.n_1700_B(0);
    }

    @Nullable
    public g_422_i J_1907_R() {
        return g_422_i.n_1700_B(this.G_564_y.n_1700_B(1));
    }

    @Nullable
    public g_422_i R_4764_Y() {
        return g_422_i.n_1700_B(this.G_564_y.n_1700_B(2));
    }

    public void J_1907_R(int p_216966_1_, int p_216966_2_) {
        if (this.J_1907_R.J_1907_R()) {
            this.G_564_y.n_1700_B(1, p_216966_1_);
            this.G_564_y.n_1700_B(2, p_216966_2_);
            this.J_1907_R.n_1700_B(1);
        }
    }

    public boolean G_564_y() {
        return !this.n_1700_B.s_956_w(0).n_1700_B();
    }

    class n_1700_B
    extends Slot {
        public n_1700_B(BeaconMenu this$0, Container inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        @Override
        public boolean n_1700_B(Z_1993_T stack) {
            return stack.J_1907_R().n_1700_B(ItemTags.T_3594_S);
        }

        @Override
        public int G_564_y() {
            return 1;
        }
    }
}


