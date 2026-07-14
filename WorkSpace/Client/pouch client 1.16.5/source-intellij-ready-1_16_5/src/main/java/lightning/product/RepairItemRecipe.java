/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lightning.product.CraftingContainer;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;

public class RepairItemRecipe
extends CustomRecipe {
    public RepairItemRecipe(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            list.add(itemstack);
            if (list.size() <= 1) continue;
            Z_1993_T itemstack1 = (Z_1993_T)list.get(0);
            if (itemstack.J_1907_R() == itemstack1.J_1907_R() && itemstack1.t_4043_B() == 1 && itemstack.t_4043_B() == 1 && itemstack1.J_1907_R().P_4830_p()) continue;
            return false;
        }
        return list.size() == 2;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            list.add(itemstack);
            if (list.size() <= 1) continue;
            Z_1993_T itemstack1 = (Z_1993_T)list.get(0);
            if (itemstack.J_1907_R() == itemstack1.J_1907_R() && itemstack1.t_4043_B() == 1 && itemstack.t_4043_B() == 1 && itemstack1.J_1907_R().P_4830_p()) continue;
            return Z_1993_T.J_1907_R;
        }
        if (list.size() == 2) {
            Z_1993_T itemstack3 = (Z_1993_T)list.get(0);
            Z_1993_T itemstack4 = (Z_1993_T)list.get(1);
            if (itemstack3.J_1907_R() == itemstack4.J_1907_R() && itemstack3.t_4043_B() == 1 && itemstack4.t_4043_B() == 1 && itemstack3.J_1907_R().P_4830_p()) {
                q_1613_l item = itemstack3.J_1907_R();
                int j = item.M_588_G() - itemstack3.v_4262_N();
                int k = item.M_588_G() - itemstack4.v_4262_N();
                int l = j + k + item.M_588_G() * 5 / 100;
                int i1 = item.M_588_G() - l;
                if (i1 < 0) {
                    i1 = 0;
                }
                Z_1993_T itemstack2 = new Z_1993_T(itemstack3.J_1907_R());
                itemstack2.J_1907_R(i1);
                HashMap map = Maps.newHashMap();
                Map<K_1310_v, Integer> map1 = K_4096_w.n_1700_B(itemstack3);
                Map<K_1310_v, Integer> map2 = K_4096_w.n_1700_B(itemstack4);
                V_3137_a.z_4693_k.u_1723_Y().filter(K_1310_v::R_4764_Y).forEach(curse -> {
                    int j1 = Math.max(map1.getOrDefault(curse, 0), map2.getOrDefault(curse, 0));
                    if (j1 > 0) {
                        map.put(curse, j1);
                    }
                });
                if (!map.isEmpty()) {
                    K_4096_w.n_1700_B(map, itemstack2);
                }
                return itemstack2;
            }
        }
        return Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.Q_4569_t;
    }
}


