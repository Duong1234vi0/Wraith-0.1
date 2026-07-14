/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.e_933_M;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.r_4889_F;
import lightning.product.x_2414_j;

public class q_1576_y
extends CustomRecipe {
    public q_1576_y(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        e_933_M dyecolor = null;
        Z_1993_T itemstack = null;
        Z_1993_T itemstack1 = null;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack2 = inv.s_956_w(i);
            q_1613_l item = itemstack2.J_1907_R();
            if (!(item instanceof x_2414_j)) continue;
            x_2414_j banneritem = (x_2414_j)item;
            if (dyecolor == null) {
                dyecolor = banneritem.R_4764_Y();
            } else if (dyecolor != banneritem.R_4764_Y()) {
                return false;
            }
            int j = r_4889_F.J_1907_R(itemstack2);
            if (j > 6) {
                return false;
            }
            if (j > 0) {
                if (itemstack != null) {
                    return false;
                }
                itemstack = itemstack2;
                continue;
            }
            if (itemstack1 != null) {
                return false;
            }
            itemstack1 = itemstack2;
        }
        return itemstack != null && itemstack1 != null;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            int j;
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B() || (j = r_4889_F.J_1907_R(itemstack)) <= 0 || j > 6) continue;
            Z_1993_T itemstack1 = itemstack.t_148_a();
            itemstack1.P_1922_E(1);
            return itemstack1;
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public NonNullList<Z_1993_T> J_1907_R(CraftingContainer inv) {
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B(inv.Y_259_p(), Z_1993_T.J_1907_R);
        for (int i = 0; i < nonnulllist.size(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            if (itemstack.J_1907_R().multiplayerClientSuggestionProvider()) {
                nonnulllist.set(i, new Z_1993_T(itemstack.J_1907_R().t_1786_h()));
                continue;
            }
            if (!itemstack.h_1847_R() || r_4889_F.J_1907_R(itemstack) <= 0) continue;
            Z_1993_T itemstack1 = itemstack.t_148_a();
            itemstack1.P_1922_E(1);
            nonnulllist.set(i, itemstack1);
        }
        return nonnulllist;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.u_2550_I;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }
}


