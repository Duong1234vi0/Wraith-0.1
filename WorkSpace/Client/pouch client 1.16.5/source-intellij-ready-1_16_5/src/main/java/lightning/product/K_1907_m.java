/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.Items;

public class K_1907_m
extends CustomRecipe {
    public K_1907_m(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        int i = 0;
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack1 = inv.s_956_w(j);
            if (itemstack1.n_1700_B()) continue;
            if (itemstack1.J_1907_R() == Items.K_4518_s) {
                if (!itemstack.n_1700_B()) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.J_1907_R() != Items.S_1431_H) {
                return false;
            }
            ++i;
        }
        return !itemstack.n_1700_B() && i > 0;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        int i = 0;
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack1 = inv.s_956_w(j);
            if (itemstack1.n_1700_B()) continue;
            if (itemstack1.J_1907_R() == Items.K_4518_s) {
                if (!itemstack.n_1700_B()) {
                    return Z_1993_T.J_1907_R;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.J_1907_R() != Items.S_1431_H) {
                return Z_1993_T.J_1907_R;
            }
            ++i;
        }
        if (!itemstack.n_1700_B() && i >= 1) {
            Z_1993_T itemstack2 = itemstack.t_148_a();
            itemstack2.P_1922_E(i + 1);
            return itemstack2;
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.P_1922_E;
    }
}


