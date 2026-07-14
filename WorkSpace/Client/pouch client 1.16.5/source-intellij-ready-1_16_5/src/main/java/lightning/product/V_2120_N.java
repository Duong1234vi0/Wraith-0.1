/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.SuspiciousStewItem;
import lightning.product.ItemTags;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.FlowerBlock;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.RecipeSerializer;
import lightning.product.Items;
import lightning.product.v_1669_V;

public class V_2120_N
extends CustomRecipe {
    public V_2120_N(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            if (itemstack.J_1907_R() == a_3742_W.JsonUtils.u_1723_Y() && !flag2) {
                flag2 = true;
                continue;
            }
            if (itemstack.J_1907_R() == a_3742_W.RealmsPersistence.u_1723_Y() && !flag1) {
                flag1 = true;
                continue;
            }
            if (itemstack.J_1907_R().n_1700_B(ItemTags.d_2427_y) && !flag) {
                flag = true;
                continue;
            }
            if (itemstack.J_1907_R() != Items.S_4088_D || flag3) {
                return false;
            }
            flag3 = true;
        }
        return flag && flag2 && flag1 && flag3;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.n_1700_B() || !itemstack1.J_1907_R().n_1700_B(ItemTags.d_2427_y)) continue;
            itemstack = itemstack1;
            break;
        }
        Z_1993_T itemstack2 = new Z_1993_T(Items.Q_2342_H, 1);
        if (itemstack.J_1907_R() instanceof v_1669_V && ((v_1669_V)itemstack.J_1907_R()).v_4262_N() instanceof FlowerBlock) {
            FlowerBlock flowerblock = (FlowerBlock)((v_1669_V)itemstack.J_1907_R()).v_4262_N();
            g_422_i effect = flowerblock.J_1907_R();
            SuspiciousStewItem.n_1700_B(itemstack2, effect, flowerblock.t_148_a());
        }
        return itemstack2;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.h_1847_R;
    }
}


