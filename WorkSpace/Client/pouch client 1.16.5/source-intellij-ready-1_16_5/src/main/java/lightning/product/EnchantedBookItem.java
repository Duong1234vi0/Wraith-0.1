/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.K_1310_v;
import lightning.product.NonNullList;
import lightning.product.S_1134_u;
import lightning.product.T_4041_i;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.x_282_a;

public class EnchantedBookItem
extends q_1613_l {
    public EnchantedBookItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return true;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return false;
    }

    public static q_2896_o G_564_y(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        return compoundnbt != null ? compoundnbt.G_564_y("StoredEnchantments", 10) : new q_2896_o();
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        super.n_1700_B(stack, worldIn, tooltip, flagIn);
        Z_1993_T.n_1700_B(tooltip, EnchantedBookItem.G_564_y(stack));
    }

    public static void n_1700_B(Z_1993_T p_92115_0_, T_4041_i stack) {
        q_2896_o listnbt = EnchantedBookItem.G_564_y(p_92115_0_);
        boolean flag = true;
        g_2336_b resourcelocation = V_3137_a.z_4693_k.J_1907_R(stack.n_1700_B);
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            g_2336_b resourcelocation1 = g_2336_b.J_1907_R(compoundnbt.M_588_G("id"));
            if (resourcelocation1 == null || !resourcelocation1.equals(resourcelocation)) continue;
            if (compoundnbt.w_1484_f("lvl") < stack.J_1907_R) {
                compoundnbt.n_1700_B("lvl", (short)stack.J_1907_R);
            }
            flag = false;
            break;
        }
        if (flag) {
            U_2912_j compoundnbt1 = new U_2912_j();
            compoundnbt1.n_1700_B("id", String.valueOf(resourcelocation));
            compoundnbt1.n_1700_B("lvl", (short)stack.J_1907_R);
            listnbt.add(compoundnbt1);
        }
        p_92115_0_.M_182_A().n_1700_B("StoredEnchantments", listnbt);
    }

    public static Z_1993_T n_1700_B(T_4041_i enchantData) {
        Z_1993_T itemstack = new Z_1993_T(Items.M_4472_P);
        EnchantedBookItem.n_1700_B(itemstack, enchantData);
        return itemstack;
    }

    @Override
    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        block4: {
            block3: {
                if (group != S_1134_u.v_4262_N) break block3;
                for (K_1310_v enchantment : V_3137_a.z_4693_k) {
                    if (enchantment.J_1907_R == null) continue;
                    for (int i = enchantment.P_1922_E(); i <= enchantment.n_1700_B(); ++i) {
                        items.add(EnchantedBookItem.n_1700_B(new T_4041_i(enchantment, i)));
                    }
                }
                break block4;
            }
            if (group.h_1847_R().length == 0) break block4;
            for (K_1310_v enchantment1 : V_3137_a.z_4693_k) {
                if (!group.n_1700_B(enchantment1.J_1907_R)) continue;
                items.add(EnchantedBookItem.n_1700_B(new T_4041_i(enchantment1, enchantment1.n_1700_B())));
            }
        }
    }
}


