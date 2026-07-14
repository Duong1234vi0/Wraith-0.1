/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.B_4088_l;
import lightning.product.CraftingContainer;
import lightning.product.I_3887_a;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.Q_4863_g;
import lightning.product.W_3491_f;
import lightning.product.ResultContainer;
import lightning.product.ResultSlot;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_4764_N;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.r_4432_i;
import lightning.product.ContainerLevelAccess;
import lightning.product.RecipeBookMenu;

public class CraftingMenu
extends RecipeBookMenu<CraftingContainer> {
    private final CraftingContainer n_1700_B = new CraftingContainer(this, 3, 3);
    private final ResultContainer J_1907_R = new ResultContainer();
    private final ContainerLevelAccess R_4764_Y;
    private final a_3913_L G_564_y;

    public CraftingMenu(int id, W_3491_f playerInventory) {
        this(id, playerInventory, ContainerLevelAccess.n_1700_B);
    }

    public CraftingMenu(int id, W_3491_f playerInventory, ContainerLevelAccess p_i50090_3_) {
        super(MenuType.M_588_G, id);
        this.R_4764_Y = p_i50090_3_;
        this.G_564_y = playerInventory.P_1922_E;
        this.J_1907_R(new ResultSlot(playerInventory.P_1922_E, this.n_1700_B, this.J_1907_R, 0, 124, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.J_1907_R(new Slot(this.n_1700_B, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.J_1907_R(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.J_1907_R(new Slot(playerInventory, l, 8 + l * 18, 142));
        }
    }

    protected static void n_1700_B(int id, b_4507_u world, a_3913_L player, CraftingContainer inventory, ResultContainer inventoryResult) {
        if (!world.Y_259_p) {
            Q_4863_g icraftingrecipe;
            B_4088_l serverplayerentity = (B_4088_l)player;
            Z_1993_T itemstack = Z_1993_T.J_1907_R;
            Optional<Q_4863_g> optional = world.T_2506_i().ValueObject().n_1700_B(RecipeType.n_1700_B, inventory, world);
            if (optional.isPresent() && inventoryResult.n_1700_B(world, serverplayerentity, icraftingrecipe = optional.get())) {
                itemstack = icraftingrecipe.n_1700_B(inventory);
            }
            inventoryResult.J_1907_R(0, itemstack);
            serverplayerentity.n_1700_B.n_1700_B(new a_4764_N(id, 0, itemstack));
        }
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        this.R_4764_Y.n_1700_B((b_4507_u p_217069_1_, c_1514_x p_217069_2_) -> CraftingMenu.n_1700_B(this.u_1723_Y, p_217069_1_, this.G_564_y, this.n_1700_B, this.J_1907_R));
    }

    @Override
    public void n_1700_B(r_4432_i itemHelperIn) {
        this.n_1700_B.n_1700_B(itemHelperIn);
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B.C_2741_M();
        this.J_1907_R.C_2741_M();
    }

    @Override
    public boolean n_1700_B(Recipe<? super CraftingContainer> recipeIn) {
        return recipeIn.n_1700_B(this.n_1700_B, this.G_564_y.O_508_d);
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.R_4764_Y.n_1700_B((b_4507_u p_217068_2_, c_1514_x p_217068_3_) -> this.n_1700_B(playerIn, (b_4507_u)p_217068_2_, this.n_1700_B));
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return CraftingMenu.n_1700_B(this.R_4764_Y, playerIn, a_3742_W.O_2934_T);
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            if (index == 0) {
                this.R_4764_Y.n_1700_B((b_4507_u p_217067_2_, c_1514_x p_217067_3_) -> itemstack1.J_1907_R().J_1907_R(itemstack1, (b_4507_u)p_217067_2_, playerIn));
                if (!this.n_1700_B(itemstack1, 10, 46, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index >= 10 && index < 46 ? !this.n_1700_B(itemstack1, 1, 10, false) && (index < 37 ? !this.n_1700_B(itemstack1, 37, 46, false) : !this.n_1700_B(itemstack1, 10, 37, false)) : !this.n_1700_B(itemstack1, 10, 46, false)) {
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
            Z_1993_T itemstack2 = slot.n_1700_B(playerIn, itemstack1);
            if (index == 0) {
                playerIn.n_1700_B(itemstack2, false);
            }
        }
        return itemstack;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return slotIn.R_4764_Y != this.J_1907_R && super.n_1700_B(stack, slotIn);
    }

    @Override
    public int J_1907_R() {
        return 0;
    }

    @Override
    public int R_4764_Y() {
        return this.n_1700_B.G_564_y();
    }

    @Override
    public int G_564_y() {
        return this.n_1700_B.R_4764_Y();
    }

    @Override
    public int P_1922_E() {
        return 10;
    }

    @Override
    public I_3887_a t_148_a() {
        return I_3887_a.n_1700_B;
    }
}


