/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.D_38_f;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.StonecutterRecipe;
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
import lightning.product.DataSlot;
import lightning.product.RecipeType;
import lightning.product.q_1613_l;
import lightning.product.ContainerLevelAccess;

public class R_4599_y
extends a_2900_S {
    private final ContainerLevelAccess G_564_y;
    private final DataSlot v_4262_N = DataSlot.n_1700_B();
    private final b_4507_u w_1484_f;
    private List<StonecutterRecipe> t_148_a = Lists.newArrayList();
    private Z_1993_T s_956_w = Z_1993_T.J_1907_R;
    private long u_2550_I;
    final Slot n_1700_B;
    final Slot J_1907_R;
    private Runnable M_588_G = () -> {};
    public final Container R_4764_Y = new N_1216_z(1){

        @Override
        public void J_1907_R() {
            super.J_1907_R();
            R_4599_y.this.n_1700_B(this);
            R_4599_y.this.M_588_G.run();
        }
    };
    private final ResultContainer P_4830_p = new ResultContainer();

    public R_4599_y(int windowIdIn, W_3491_f playerInventoryIn) {
        this(windowIdIn, playerInventoryIn, ContainerLevelAccess.n_1700_B);
    }

    public R_4599_y(int windowIdIn, W_3491_f playerInventoryIn, final ContainerLevelAccess worldPosCallableIn) {
        super(MenuType.k_2293_S, windowIdIn);
        this.G_564_y = worldPosCallableIn;
        this.w_1484_f = playerInventoryIn.P_1922_E.O_508_d;
        this.n_1700_B = this.J_1907_R(new Slot(this.R_4764_Y, 0, 20, 33));
        this.J_1907_R = this.J_1907_R(new Slot(this.P_4830_p, 1, 143, 33){

            @Override
            public boolean n_1700_B(Z_1993_T stack) {
                return false;
            }

            @Override
            public Z_1993_T n_1700_B(a_3913_L thePlayer, Z_1993_T stack) {
                stack.n_1700_B(thePlayer.O_508_d, thePlayer, stack.t_4043_B());
                R_4599_y.this.P_4830_p.n_1700_B(thePlayer);
                Z_1993_T itemstack = R_4599_y.this.n_1700_B.n_1700_B(1);
                if (!itemstack.n_1700_B()) {
                    R_4599_y.this.P_1922_E();
                }
                worldPosCallableIn.n_1700_B((b_4507_u p_216954_1_, c_1514_x p_216954_2_) -> {
                    long l = p_216954_1_.X_933_l();
                    if (R_4599_y.this.u_2550_I != l) {
                        p_216954_1_.n_1700_B((a_3913_L)null, (c_1514_x)p_216954_2_, SoundEvents.FaceAttachedHorizontalDirectionalBlock, D_38_f.P_1922_E, 1.0f, 1.0f);
                        R_4599_y.this.u_2550_I = l;
                    }
                });
                return super.n_1700_B(thePlayer, stack);
            }
        });
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }
        this.n_1700_B(this.v_4262_N);
    }

    public int n_1700_B() {
        return this.v_4262_N.J_1907_R();
    }

    public List<StonecutterRecipe> J_1907_R() {
        return this.t_148_a;
    }

    public int R_4764_Y() {
        return this.t_148_a.size();
    }

    public boolean G_564_y() {
        return this.n_1700_B.J_1907_R() && !this.t_148_a.isEmpty();
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return R_4599_y.n_1700_B(this.G_564_y, playerIn, a_3742_W.f_4705_f);
    }

    @Override
    public boolean J_1907_R(a_3913_L playerIn, int id) {
        if (this.G_564_y(id)) {
            this.v_4262_N.n_1700_B(id);
            this.P_1922_E();
        }
        return true;
    }

    private boolean G_564_y(int p_241818_1_) {
        return p_241818_1_ >= 0 && p_241818_1_ < this.t_148_a.size();
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        Z_1993_T itemstack = this.n_1700_B.n_1700_B();
        if (itemstack.J_1907_R() != this.s_956_w.J_1907_R()) {
            this.s_956_w = itemstack.t_148_a();
            this.n_1700_B(inventoryIn, itemstack);
        }
    }

    private void n_1700_B(Container inventoryIn, Z_1993_T stack) {
        this.t_148_a.clear();
        this.v_4262_N.n_1700_B(-1);
        this.J_1907_R.J_1907_R(Z_1993_T.J_1907_R);
        if (!stack.n_1700_B()) {
            this.t_148_a = this.w_1484_f.s_956_w().J_1907_R(RecipeType.u_1723_Y, inventoryIn, this.w_1484_f);
        }
    }

    private void P_1922_E() {
        if (!this.t_148_a.isEmpty() && this.G_564_y(this.v_4262_N.J_1907_R())) {
            StonecutterRecipe stonecuttingrecipe = this.t_148_a.get(this.v_4262_N.J_1907_R());
            this.P_4830_p.n_1700_B(stonecuttingrecipe);
            this.J_1907_R.J_1907_R(stonecuttingrecipe.n_1700_B(this.R_4764_Y));
        } else {
            this.J_1907_R.J_1907_R(Z_1993_T.J_1907_R);
        }
        this.M_588_G();
    }

    @Override
    public MenuType<?> s_956_w() {
        return MenuType.k_2293_S;
    }

    public void n_1700_B(Runnable listenerIn) {
        this.M_588_G = listenerIn;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return slotIn.R_4764_Y != this.P_4830_p && super.n_1700_B(stack, slotIn);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            q_1613_l item = itemstack1.J_1907_R();
            itemstack = itemstack1.t_148_a();
            if (index == 1) {
                item.J_1907_R(itemstack1, playerIn.O_508_d, playerIn);
                if (!this.n_1700_B(itemstack1, 2, 38, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index == 0 ? !this.n_1700_B(itemstack1, 2, 38, false) : (this.w_1484_f.s_956_w().n_1700_B(RecipeType.u_1723_Y, new N_1216_z(itemstack1), this.w_1484_f).isPresent() ? !this.n_1700_B(itemstack1, 0, 1, false) : (index >= 2 && index < 29 ? !this.n_1700_B(itemstack1, 29, 38, false) : index >= 29 && index < 38 && !this.n_1700_B(itemstack1, 2, 29, false)))) {
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
        this.P_4830_p.u_2550_I(1);
        this.G_564_y.n_1700_B((b_4507_u p_217079_2_, c_1514_x p_217079_3_) -> this.n_1700_B(playerIn, playerIn.O_508_d, this.R_4764_Y));
    }
}


