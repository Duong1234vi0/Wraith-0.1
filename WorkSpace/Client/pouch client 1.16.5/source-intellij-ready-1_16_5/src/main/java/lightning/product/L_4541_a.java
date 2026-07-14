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
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.Items;

public class L_4541_a
extends CustomRecipe {
    private static final b_3278_X n_1700_B = b_3278_X.n_1700_B(Items.FenceGateBlock);

    public L_4541_a(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        boolean flag = false;
        boolean flag1 = false;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            if (itemstack.J_1907_R() instanceof DyeItem) {
                flag = true;
                continue;
            }
            if (!n_1700_B.n_1700_B(itemstack)) {
                return false;
            }
            if (flag1) {
                return false;
            }
            flag1 = true;
        }
        return flag1 && flag;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        ArrayList list = Lists.newArrayList();
        Z_1993_T itemstack = null;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            q_1613_l item = itemstack1.J_1907_R();
            if (item instanceof DyeItem) {
                list.add(((DyeItem)item).R_4764_Y().u_1723_Y());
                continue;
            }
            if (!n_1700_B.n_1700_B(itemstack1)) continue;
            itemstack = itemstack1.t_148_a();
            itemstack.P_1922_E(1);
        }
        if (itemstack != null && !list.isEmpty()) {
            itemstack.n_1700_B("Explosion").n_1700_B("FadeColors", list);
            return itemstack;
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.t_148_a;
    }
}


