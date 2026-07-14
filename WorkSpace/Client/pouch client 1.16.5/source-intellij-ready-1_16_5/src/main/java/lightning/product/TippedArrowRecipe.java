/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.L_1875_m;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.Items;

public class TippedArrowRecipe
extends CustomRecipe {
    public TippedArrowRecipe(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        if (inv.G_564_y() == 3 && inv.R_4764_Y() == 3) {
            for (int i = 0; i < inv.G_564_y(); ++i) {
                for (int j = 0; j < inv.R_4764_Y(); ++j) {
                    Z_1993_T itemstack = inv.s_956_w(i + j * inv.G_564_y());
                    if (itemstack.n_1700_B()) {
                        return false;
                    }
                    q_1613_l item = itemstack.J_1907_R();
                    if (!(i == 1 && j == 1 ? item != Items.NetherrackBlock : item != Items.g_24_p)) continue;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = inv.s_956_w(1 + inv.G_564_y());
        if (itemstack.J_1907_R() != Items.NetherrackBlock) {
            return Z_1993_T.J_1907_R;
        }
        Z_1993_T itemstack1 = new Z_1993_T(Items.NetherWartBlock, 8);
        L_1875_m.n_1700_B(itemstack1, L_1875_m.G_564_y(itemstack));
        L_1875_m.n_1700_B(itemstack1, L_1875_m.J_1907_R(itemstack));
        return itemstack1;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.s_956_w;
    }
}


