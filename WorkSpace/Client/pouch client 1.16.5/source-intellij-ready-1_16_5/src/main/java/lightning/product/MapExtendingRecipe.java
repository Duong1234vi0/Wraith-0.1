/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3620_e;
import lightning.product.G_3165_y;
import lightning.product.CraftingContainer;
import lightning.product.J_2020_G;
import lightning.product.M_996_h;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.Items;

public class MapExtendingRecipe
extends M_996_h {
    public MapExtendingRecipe(g_2336_b id) {
        super(id, "", 3, 3, NonNullList.n_1700_B(b_3278_X.n_1700_B, b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.K_4518_s), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o), b_3278_X.n_1700_B(Items.l_3370_o)), new Z_1993_T(Items.S_1431_H));
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        if (!super.n_1700_B(inv, worldIn)) {
            return false;
        }
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p() && itemstack.n_1700_B(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.J_1907_R() != Items.K_4518_s) continue;
            itemstack = itemstack1;
        }
        if (itemstack.n_1700_B()) {
            return false;
        }
        F_3620_e mapdata = G_3165_y.J_1907_R(itemstack, worldIn);
        if (mapdata == null) {
            return false;
        }
        if (this.n_1700_B(mapdata)) {
            return false;
        }
        return mapdata.u_1723_Y < 4;
    }

    private boolean n_1700_B(F_3620_e data) {
        if (data.s_956_w != null) {
            for (J_2020_G mapdecoration : data.s_956_w.values()) {
                if (mapdecoration.J_1907_R() != J_2020_G.n_1700_B.t_148_a && mapdecoration.J_1907_R() != J_2020_G.n_1700_B.s_956_w) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        for (int i = 0; i < inv.Y_259_p() && itemstack.n_1700_B(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.J_1907_R() != Items.K_4518_s) continue;
            itemstack = itemstack1;
        }
        itemstack = itemstack.t_148_a();
        itemstack.P_1922_E(1);
        itemstack.M_182_A().J_1907_R("map_scale_direction", 1);
        return itemstack;
    }

    @Override
    public boolean t_148_a() {
        return true;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.u_1723_Y;
    }
}


