/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.H_2000_A;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.Merchant;
import lightning.product.MerchantResultSlot;
import lightning.product.MerchantOffers;
import lightning.product.ClientSideMerchant;
import lightning.product.MerchantOffer;

public class K_3710_b
extends a_2900_S {
    private final Merchant n_1700_B;
    private final H_2000_A J_1907_R;
    private int R_4764_Y;
    private boolean G_564_y;
    private boolean v_4262_N;

    public K_3710_b(int id, W_3491_f playerInventoryIn) {
        this(id, playerInventoryIn, new ClientSideMerchant(playerInventoryIn.P_1922_E));
    }

    public K_3710_b(int id, W_3491_f playerInventoryIn, Merchant merchantIn) {
        super(MenuType.w_1457_N, id);
        this.n_1700_B = merchantIn;
        this.J_1907_R = new H_2000_A(merchantIn);
        this.J_1907_R(new Slot(this.J_1907_R, 0, 136, 37));
        this.J_1907_R(new Slot(this.J_1907_R, 1, 162, 37));
        this.J_1907_R(new MerchantResultSlot(playerInventoryIn.P_1922_E, merchantIn, this.J_1907_R, 2, 220, 37));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(playerInventoryIn, j + i * 9 + 9, 108 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(playerInventoryIn, k, 108 + k * 18, 142));
        }
    }

    public void n_1700_B(boolean p_217045_1_) {
        this.G_564_y = p_217045_1_;
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        this.J_1907_R.R_4764_Y();
        super.n_1700_B(inventoryIn);
    }

    public void G_564_y(int currentRecipeIndex) {
        this.J_1907_R.n_1700_B(currentRecipeIndex);
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.n_1700_B.n_1700_B() == playerIn;
    }

    public int n_1700_B() {
        return this.n_1700_B.G_564_y();
    }

    public int J_1907_R() {
        return this.J_1907_R.P_1922_E();
    }

    public void P_1922_E(int xp) {
        this.n_1700_B.n_1700_B(xp);
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public void u_1723_Y(int level) {
        this.R_4764_Y = level;
    }

    public void J_1907_R(boolean p_223431_1_) {
        this.v_4262_N = p_223431_1_;
    }

    public boolean G_564_y() {
        return this.v_4262_N;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == 2) {
                if (!this.n_1700_B(itemstack1, 3, 39, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
                this.v_4262_N();
            } else if (index != 0 && index != 1 ? (index >= 3 && index < 30 ? !this.n_1700_B(itemstack1, 30, 39, false) : index >= 30 && index < 39 && !this.n_1700_B(itemstack1, 3, 30, false)) : !this.n_1700_B(itemstack1, 3, 39, false)) {
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

    private void v_4262_N() {
        if (!this.n_1700_B.R_4764_Y().Y_259_p) {
            N_4263_v entity = (N_4263_v)((Object)this.n_1700_B);
            this.n_1700_B.R_4764_Y().n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), this.n_1700_B.u_1723_Y(), D_38_f.v_4262_N, 1.0f, 1.0f, false);
        }
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.n_1700_B.n_1700_B((a_3913_L)null);
        if (!this.n_1700_B.R_4764_Y().Y_259_p) {
            if (!playerIn.RealmsLongRunningMcoTaskScreen() || playerIn instanceof B_4088_l && ((B_4088_l)playerIn).C_2741_M()) {
                Z_1993_T itemstack = this.J_1907_R.u_2550_I(0);
                if (!itemstack.n_1700_B()) {
                    playerIn.n_1700_B(itemstack, false);
                }
                if (!(itemstack = this.J_1907_R.u_2550_I(1)).n_1700_B()) {
                    playerIn.n_1700_B(itemstack, false);
                }
            } else {
                playerIn.l_1268_F.n_1700_B(playerIn.O_508_d, this.J_1907_R.u_2550_I(0));
                playerIn.l_1268_F.n_1700_B(playerIn.O_508_d, this.J_1907_R.u_2550_I(1));
            }
        }
    }

    public void v_4262_N(int p_217046_1_) {
        if (this.P_1922_E().size() > p_217046_1_) {
            Z_1993_T itemstack1;
            Z_1993_T itemstack = this.J_1907_R.s_956_w(0);
            if (!itemstack.n_1700_B()) {
                if (!this.n_1700_B(itemstack, 3, 39, true)) {
                    return;
                }
                this.J_1907_R.J_1907_R(0, itemstack);
            }
            if (!(itemstack1 = this.J_1907_R.s_956_w(1)).n_1700_B()) {
                if (!this.n_1700_B(itemstack1, 3, 39, true)) {
                    return;
                }
                this.J_1907_R.J_1907_R(1, itemstack1);
            }
            if (this.J_1907_R.s_956_w(0).n_1700_B() && this.J_1907_R.s_956_w(1).n_1700_B()) {
                Z_1993_T itemstack2 = ((MerchantOffer)this.P_1922_E().get(p_217046_1_)).J_1907_R();
                this.J_1907_R(0, itemstack2);
                Z_1993_T itemstack3 = ((MerchantOffer)this.P_1922_E().get(p_217046_1_)).R_4764_Y();
                this.J_1907_R(1, itemstack3);
            }
        }
    }

    private void J_1907_R(int p_217053_1_, Z_1993_T p_217053_2_) {
        if (!p_217053_2_.n_1700_B()) {
            for (int i = 3; i < 39; ++i) {
                Z_1993_T itemstack = ((Slot)this.P_1922_E.get(i)).n_1700_B();
                if (itemstack.n_1700_B() || !this.J_1907_R(p_217053_2_, itemstack)) continue;
                Z_1993_T itemstack1 = this.J_1907_R.s_956_w(p_217053_1_);
                int j = itemstack1.n_1700_B() ? 0 : itemstack1.t_4043_B();
                int k = Math.min(p_217053_2_.R_4764_Y() - j, itemstack.t_4043_B());
                Z_1993_T itemstack2 = itemstack.t_148_a();
                int l = j + k;
                itemstack.v_4262_N(k);
                itemstack2.P_1922_E(l);
                this.J_1907_R.J_1907_R(p_217053_1_, itemstack2);
                if (l >= p_217053_2_.R_4764_Y()) break;
            }
        }
    }

    private boolean J_1907_R(Z_1993_T stack1, Z_1993_T stack2) {
        return stack1.J_1907_R() == stack2.J_1907_R() && Z_1993_T.n_1700_B(stack1, stack2);
    }

    public void n_1700_B(MerchantOffers offers) {
        this.n_1700_B.n_1700_B(offers);
    }

    public MerchantOffers P_1922_E() {
        return this.n_1700_B.J_1907_R();
    }

    public boolean u_1723_Y() {
        return this.G_564_y;
    }
}


