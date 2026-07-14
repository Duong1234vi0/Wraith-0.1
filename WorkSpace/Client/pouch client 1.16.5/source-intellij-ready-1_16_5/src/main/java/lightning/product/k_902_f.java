/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.SimpleContainerData;
import lightning.product.I_3887_a;
import lightning.product.AbstractCookingRecipe;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.N_1216_z;
import lightning.product.FurnaceResultSlot;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.ServerPlaceSmeltingRecipe;
import lightning.product.ContainerData;
import lightning.product.n_1680_G;
import lightning.product.FurnaceFuelSlot;
import lightning.product.r_4432_i;
import lightning.product.y_452_M;
import lightning.product.RecipeBookMenu;

public abstract class k_902_f
extends RecipeBookMenu<Container> {
    private final Container J_1907_R;
    private final ContainerData R_4764_Y;
    protected final b_4507_u n_1700_B;
    private final RecipeType<? extends AbstractCookingRecipe> G_564_y;
    private final I_3887_a v_4262_N;

    protected k_902_f(MenuType<?> p_i241921_1_, RecipeType<? extends AbstractCookingRecipe> p_i241921_2_, I_3887_a p_i241921_3_, int p_i241921_4_, W_3491_f p_i241921_5_) {
        this(p_i241921_1_, p_i241921_2_, p_i241921_3_, p_i241921_4_, p_i241921_5_, new N_1216_z(3), new SimpleContainerData(4));
    }

    protected k_902_f(MenuType<?> p_i241922_1_, RecipeType<? extends AbstractCookingRecipe> p_i241922_2_, I_3887_a p_i241922_3_, int p_i241922_4_, W_3491_f p_i241922_5_, Container p_i241922_6_, ContainerData p_i241922_7_) {
        super(p_i241922_1_, p_i241922_4_);
        this.G_564_y = p_i241922_2_;
        this.v_4262_N = p_i241922_3_;
        k_902_f.n_1700_B(p_i241922_6_, 3);
        k_902_f.n_1700_B(p_i241922_7_, 4);
        this.J_1907_R = p_i241922_6_;
        this.R_4764_Y = p_i241922_7_;
        this.n_1700_B = p_i241922_5_.P_1922_E.O_508_d;
        this.J_1907_R(new Slot(p_i241922_6_, 0, 56, 17));
        this.J_1907_R(new FurnaceFuelSlot(this, p_i241922_6_, 1, 56, 53));
        this.J_1907_R(new FurnaceResultSlot(p_i241922_5_.P_1922_E, p_i241922_6_, 2, 116, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.J_1907_R(new Slot(p_i241922_5_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.J_1907_R(new Slot(p_i241922_5_, k, 8 + k * 18, 142));
        }
        this.n_1700_B(p_i241922_7_);
    }

    @Override
    public void n_1700_B(r_4432_i itemHelperIn) {
        if (this.J_1907_R instanceof y_452_M) {
            ((y_452_M)((Object)this.J_1907_R)).n_1700_B(itemHelperIn);
        }
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.C_2741_M();
    }

    @Override
    public void n_1700_B(boolean p_217056_1_, Recipe<?> p_217056_2_, B_4088_l player) {
        new ServerPlaceSmeltingRecipe<Container>(this).n_1700_B(player, p_217056_2_, p_217056_1_);
    }

    @Override
    public boolean n_1700_B(Recipe<? super Container> recipeIn) {
        return recipeIn.n_1700_B(this.J_1907_R, this.n_1700_B);
    }

    @Override
    public int J_1907_R() {
        return 2;
    }

    @Override
    public int R_4764_Y() {
        return 1;
    }

    @Override
    public int G_564_y() {
        return 1;
    }

    @Override
    public int P_1922_E() {
        return 3;
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return this.J_1907_R.R_4764_Y(playerIn);
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
            } else if (index != 1 && index != 0 ? (this.n_1700_B(itemstack1) ? !this.n_1700_B(itemstack1, 0, 1, false) : (this.J_1907_R(itemstack1) ? !this.n_1700_B(itemstack1, 1, 2, false) : (index >= 3 && index < 30 ? !this.n_1700_B(itemstack1, 30, 39, false) : index >= 30 && index < 39 && !this.n_1700_B(itemstack1, 3, 30, false)))) : !this.n_1700_B(itemstack1, 3, 39, false)) {
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

    protected boolean n_1700_B(Z_1993_T stack) {
        return this.n_1700_B.s_956_w().n_1700_B(this.G_564_y, new N_1216_z(stack), this.n_1700_B).isPresent();
    }

    protected boolean J_1907_R(Z_1993_T stack) {
        return n_1680_G.J_1907_R(stack);
    }

    public int u_1723_Y() {
        int i = this.R_4764_Y.n_1700_B(2);
        int j = this.R_4764_Y.n_1700_B(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public int v_4262_N() {
        int i = this.R_4764_Y.n_1700_B(1);
        if (i == 0) {
            i = 200;
        }
        return this.R_4764_Y.n_1700_B(0) * 13 / i;
    }

    public boolean w_1484_f() {
        return this.R_4764_Y.n_1700_B(0) > 0;
    }

    @Override
    public I_3887_a t_148_a() {
        return this.v_4262_N;
    }
}


