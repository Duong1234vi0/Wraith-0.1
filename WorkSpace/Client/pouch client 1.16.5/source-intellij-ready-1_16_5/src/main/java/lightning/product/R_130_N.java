/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import lightning.product.CraftingContainer;
import lightning.product.Z_1993_T;
import lightning.product.DyeItem;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.DyeableLeatherItem;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;

public class R_130_N
extends CustomRecipe {
    public R_130_N(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.n_1700_B()) continue;
            if (itemstack1.J_1907_R() instanceof DyeableLeatherItem) {
                if (!itemstack.n_1700_B()) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (!(itemstack1.J_1907_R() instanceof DyeItem)) {
                return false;
            }
            list.add(itemstack1);
        }
        return !itemstack.n_1700_B() && !list.isEmpty();
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        ArrayList list = Lists.newArrayList();
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.n_1700_B()) continue;
            q_1613_l item = itemstack1.J_1907_R();
            if (item instanceof DyeableLeatherItem) {
                if (!itemstack.n_1700_B()) {
                    return Z_1993_T.J_1907_R;
                }
                itemstack = itemstack1.t_148_a();
                continue;
            }
            if (!(item instanceof DyeItem)) {
                return Z_1993_T.J_1907_R;
            }
            list.add((DyeItem)item);
        }
        return !itemstack.n_1700_B() && !list.isEmpty() ? DyeableLeatherItem.n_1700_B(itemstack, list) : Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.R_4764_Y;
    }
}


