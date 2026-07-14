/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.T_2915_h;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.DyeItem;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.Items;

public class ShulkerBoxColoring
extends CustomRecipe {
    public ShulkerBoxColoring(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < inv.Y_259_p(); ++k) {
            Z_1993_T itemstack = inv.s_956_w(k);
            if (itemstack.n_1700_B()) continue;
            if (T_2915_h.n_1700_B(itemstack.J_1907_R()) instanceof Y_3462_U) {
                ++i;
            } else {
                if (!(itemstack.J_1907_R() instanceof DyeItem)) {
                    return false;
                }
                ++j;
            }
            if (j <= 1 && i <= 1) continue;
            return false;
        }
        return i == 1 && j == 1;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        DyeItem dyeitem = (DyeItem)Items.ServerFunctionManager;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.n_1700_B()) continue;
            q_1613_l item = itemstack1.J_1907_R();
            if (T_2915_h.n_1700_B(item) instanceof Y_3462_U) {
                itemstack = itemstack1;
                continue;
            }
            if (!(item instanceof DyeItem)) continue;
            dyeitem = (DyeItem)item;
        }
        Z_1993_T itemstack2 = Y_3462_U.J_1907_R(dyeitem.R_4764_Y());
        if (itemstack.h_1847_R()) {
            itemstack2.R_4764_Y(itemstack.Q_4569_t().v_4262_N());
        }
        return itemstack2;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.P_4830_p;
    }
}


