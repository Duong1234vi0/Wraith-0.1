/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.Items;
import lightning.product.x_2414_j;

public class ShieldDecorationRecipe
extends CustomRecipe {
    public ShieldDecorationRecipe(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Z_1993_T itemstack1 = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack2 = inv.s_956_w(i);
            if (itemstack2.n_1700_B()) continue;
            if (itemstack2.J_1907_R() instanceof x_2414_j) {
                if (!itemstack1.n_1700_B()) {
                    return false;
                }
                itemstack1 = itemstack2;
                continue;
            }
            if (itemstack2.J_1907_R() != Items.NoteBlock) {
                return false;
            }
            if (!itemstack.n_1700_B()) {
                return false;
            }
            if (itemstack2.J_1907_R("BlockEntityTag") != null) {
                return false;
            }
            itemstack = itemstack2;
        }
        return !itemstack.n_1700_B() && !itemstack1.n_1700_B();
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Z_1993_T itemstack1 = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack2 = inv.s_956_w(i);
            if (itemstack2.n_1700_B()) continue;
            if (itemstack2.J_1907_R() instanceof x_2414_j) {
                itemstack = itemstack2;
                continue;
            }
            if (itemstack2.J_1907_R() != Items.NoteBlock) continue;
            itemstack1 = itemstack2.t_148_a();
        }
        if (itemstack1.n_1700_B()) {
            return itemstack1;
        }
        U_2912_j compoundnbt = itemstack.J_1907_R("BlockEntityTag");
        U_2912_j compoundnbt1 = compoundnbt == null ? new U_2912_j() : compoundnbt.v_4262_N();
        compoundnbt1.J_1907_R("Base", ((x_2414_j)itemstack.J_1907_R()).R_4764_Y().J_1907_R());
        itemstack1.n_1700_B("BlockEntityTag", compoundnbt1);
        return itemstack1;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.M_588_G;
    }
}


