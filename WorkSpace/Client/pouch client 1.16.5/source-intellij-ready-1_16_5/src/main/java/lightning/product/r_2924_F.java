/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_477_D;
import lightning.product.CraftingContainer;
import lightning.product.NonNullList;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.Items;

public class r_2924_F
extends CustomRecipe {
    public r_2924_F(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        int i = 0;
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack1 = inv.s_956_w(j);
            if (itemstack1.n_1700_B()) continue;
            if (itemstack1.J_1907_R() == Items.CryingObsidianBlock) {
                if (!itemstack.n_1700_B()) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.J_1907_R() != Items.CropBlock) {
                return false;
            }
            ++i;
        }
        return !itemstack.n_1700_B() && itemstack.h_1847_R() && i > 0;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        int i = 0;
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack1 = inv.s_956_w(j);
            if (itemstack1.n_1700_B()) continue;
            if (itemstack1.J_1907_R() == Items.CryingObsidianBlock) {
                if (!itemstack.n_1700_B()) {
                    return Z_1993_T.J_1907_R;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.J_1907_R() != Items.CropBlock) {
                return Z_1993_T.J_1907_R;
            }
            ++i;
        }
        if (!itemstack.n_1700_B() && itemstack.h_1847_R() && i >= 1 && B_477_D.G_564_y(itemstack) < 2) {
            Z_1993_T itemstack2 = new Z_1993_T(Items.CryingObsidianBlock, i);
            U_2912_j compoundnbt = itemstack.Q_4569_t().v_4262_N();
            compoundnbt.J_1907_R("generation", B_477_D.G_564_y(itemstack) + 1);
            itemstack2.R_4764_Y(compoundnbt);
            return itemstack2;
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public NonNullList<Z_1993_T> J_1907_R(CraftingContainer inv) {
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B(inv.Y_259_p(), Z_1993_T.J_1907_R);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.J_1907_R().multiplayerClientSuggestionProvider()) {
                nonnulllist.set(i, new Z_1993_T(itemstack.J_1907_R().t_1786_h()));
                continue;
            }
            if (!(itemstack.J_1907_R() instanceof B_477_D)) continue;
            Z_1993_T itemstack1 = itemstack.t_148_a();
            itemstack1.P_1922_E(1);
            nonnulllist.set(i, itemstack1);
            break;
        }
        return nonnulllist;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.G_564_y;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width >= 3 && height >= 3;
    }
}


