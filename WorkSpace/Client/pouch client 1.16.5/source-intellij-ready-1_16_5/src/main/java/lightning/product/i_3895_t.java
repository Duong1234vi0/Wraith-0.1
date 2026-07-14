/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.SoundEvents;
import lightning.product.W_3491_f;
import lightning.product.ResultContainer;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.ContainerLevelAccess;

public class i_3895_t
extends a_2900_S {
    private final ContainerLevelAccess J_1907_R;
    private long R_4764_Y;
    public final Container n_1700_B = new N_1216_z(2){

        @Override
        public void J_1907_R() {
            i_3895_t.this.n_1700_B(this);
            super.J_1907_R();
        }
    };
    private final ResultContainer G_564_y = new ResultContainer(){

        @Override
        public void J_1907_R() {
            i_3895_t.this.n_1700_B(this);
            super.J_1907_R();
        }
    };

    public i_3895_t(int id, W_3491_f playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.n_1700_B);
    }

    public i_3895_t(int id, W_3491_f playerInventory, final ContainerLevelAccess worldPosCallable) {
        super(MenuType.C_2741_M, id);
        this.J_1907_R = worldPosCallable;
        this.J_1907_R(new Slot(this, this.n_1700_B, 0, 15, 15){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return stack.J_1907_R() == Items.K_4518_s;
            }
        });
        this.J_1907_R(new Slot(this, this.n_1700_B, 1, 15, 52){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                q_1613_l item = stack.J_1907_R();
                return item == Items.l_3370_o || item == Items.S_1431_H || item == Items.U_4087_m;
            }
        });
        this.J_1907_R(new Slot(this.G_564_y, 2, 145, 39){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return false;
            }

            @Override
            public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
                ((Slot)i_3895_t.this.P_1922_E.get(0)).n_1700_B(1);
                ((Slot)i_3895_t.this.P_1922_E.get(1)).n_1700_B(1);
                stack.J_1907_R().J_1907_R(stack, thePlayer.O_508_d, thePlayer);
                worldPosCallable.n_1700_B((b_4507_u p_242385_1_, c_1514_x p_242385_2_) -> {
                    long l = p_242385_1_.X_933_l();
                    if (i_3895_t.this.R_4764_Y != l) {
                        p_242385_1_.n_1700_B((a_3913_L)null, (c_1514_x)p_242385_2_, SoundEvents.HorizontalDirectionalBlock, D_38_f.P_1922_E, 1.0f, 1.0f);
                        i_3895_t.this.R_4764_Y = l;
                    }
                });
                return super.n_1700_B(thePlayer, stack);
            }
        });
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
        return i_3895_t.n_1700_B(this.J_1907_R, playerIn, a_3742_W.j_1376_w);
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        Z_1993_T itemstack = this.n_1700_B.s_956_w(0);
        Z_1993_T itemstack1 = this.n_1700_B.s_956_w(1);
        Z_1993_T itemstack2 = this.G_564_y.s_956_w(2);
        if (itemstack2.n_1700_B() || !itemstack.n_1700_B() && !itemstack1.n_1700_B()) {
            if (!itemstack.n_1700_B() && !itemstack1.n_1700_B()) {
                this.n_1700_B(itemstack, itemstack1, itemstack2);
            }
        } else {
            this.G_564_y.u_2550_I(2);
        }
    }

    private void n_1700_B(Z_1993_T stack, Z_1993_T p_216993_2_, Z_1993_T p_216993_3_) {
        this.J_1907_R.n_1700_B((b_4507_u p_216996_4_, c_1514_x p_216996_5_) -> {
            q_1613_l item = p_216993_2_.J_1907_R();
            F_3620_e mapdata = G_3165_y.n_1700_B(stack, p_216996_4_);
            if (mapdata != null) {
                Z_1993_T itemstack;
                if (item == Items.l_3370_o && !mapdata.w_1484_f && mapdata.u_1723_Y < 4) {
                    itemstack = stack.t_148_a();
                    itemstack.P_1922_E(1);
                    itemstack.M_182_A().J_1907_R("map_scale_direction", 1);
                    this.M_588_G();
                } else if (item == Items.U_4087_m && !mapdata.w_1484_f) {
                    itemstack = stack.t_148_a();
                    itemstack.P_1922_E(1);
                    itemstack.M_182_A().n_1700_B("map_to_lock", true);
                    this.M_588_G();
                } else {
                    if (item != Items.S_1431_H) {
                        this.G_564_y.u_2550_I(2);
                        this.M_588_G();
                        return;
                    }
                    itemstack = stack.t_148_a();
                    itemstack.P_1922_E(2);
                    this.M_588_G();
                }
                if (!Z_1993_T.J_1907_R(itemstack, p_216993_3_)) {
                    this.G_564_y.J_1907_R(2, itemstack);
                    this.M_588_G();
                }
            }
        });
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return slotIn.R_4764_Y != this.G_564_y && super.n_1700_B(stack, slotIn);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            q_1613_l item = itemstack1.J_1907_R();
            itemstack = itemstack1.t_148_a();
            if (index == 2) {
                item.J_1907_R(itemstack1, playerIn.O_508_d, playerIn);
                if (!this.n_1700_B(itemstack1, 3, 39, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index != 1 && index != 0 ? (item == Items.K_4518_s ? !this.n_1700_B(itemstack1, 0, 1, false) : (item != Items.l_3370_o && item != Items.S_1431_H && item != Items.U_4087_m ? (index >= 3 && index < 30 ? !this.n_1700_B(itemstack1, 30, 39, false) : index >= 30 && index < 39 && !this.n_1700_B(itemstack1, 3, 30, false)) : !this.n_1700_B(itemstack1, 1, 2, false))) : !this.n_1700_B(itemstack1, 3, 39, false)) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            }
            slot.R_4764_Y();
            if (itemstack1.t_4043_B() == itemstack.t_4043_B()) {
                return Z_1993_T.J_1907_R;
            }
            slot.n_1700_B(playerIn, itemstack1);
            this.M_588_G();
        }
        return itemstack;
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.G_564_y.u_2550_I(2);
        this.J_1907_R.n_1700_B((b_4507_u p_216995_2_, c_1514_x p_216995_3_) -> this.n_1700_B(playerIn, playerIn.O_508_d, this.n_1700_B));
    }
}


