/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableFloat
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.K_1310_v;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_4041_i;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.SweepingEdgeEnchantment;
import lightning.product.WeighedRandom;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.g_2336_b;
import lightning.product.MobType;
import lightning.product.j_3341_s;
import lightning.product.EnchantedBookItem;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;

public class K_4096_w {
    public static int n_1700_B(K_1310_v enchID, Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return 0;
        }
        g_2336_b resourcelocation = V_3137_a.z_4693_k.J_1907_R(enchID);
        q_2896_o listnbt = stack.t_1786_h();
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            g_2336_b resourcelocation1 = g_2336_b.J_1907_R(compoundnbt.M_588_G("id"));
            if (resourcelocation1 == null || !resourcelocation1.equals(resourcelocation)) continue;
            return u_530_F.n_1700_B(compoundnbt.w_1484_f("lvl"), 0, 255);
        }
        return 0;
    }

    public static Map<K_1310_v, Integer> n_1700_B(Z_1993_T stack) {
        q_2896_o listnbt = stack.J_1907_R() == Items.M_4472_P ? EnchantedBookItem.G_564_y(stack) : stack.t_1786_h();
        return K_4096_w.n_1700_B(listnbt);
    }

    public static Map<K_1310_v, Integer> n_1700_B(q_2896_o serialized) {
        LinkedHashMap map = Maps.newLinkedHashMap();
        for (int i = 0; i < serialized.size(); ++i) {
            U_2912_j compoundnbt = serialized.n_1700_B(i);
            V_3137_a.z_4693_k.J_1907_R(g_2336_b.J_1907_R(compoundnbt.M_588_G("id"))).ifPresent(enchantment -> {
                Integer integer = map.put(enchantment, compoundnbt.w_1484_f("lvl"));
            });
        }
        return map;
    }

    public static void n_1700_B(Map<K_1310_v, Integer> enchMap, Z_1993_T stack) {
        q_2896_o listnbt = new q_2896_o();
        for (Map.Entry<K_1310_v, Integer> entry : enchMap.entrySet()) {
            K_1310_v enchantment = entry.getKey();
            if (enchantment == null) continue;
            int i = entry.getValue();
            U_2912_j compoundnbt = new U_2912_j();
            compoundnbt.n_1700_B("id", String.valueOf(V_3137_a.z_4693_k.J_1907_R(enchantment)));
            compoundnbt.n_1700_B("lvl", (short)i);
            listnbt.add(compoundnbt);
            if (stack.J_1907_R() != Items.M_4472_P) continue;
            EnchantedBookItem.n_1700_B(stack, new T_4041_i(enchantment, i));
        }
        if (listnbt.isEmpty()) {
            stack.R_4764_Y("Enchantments");
        } else if (stack.J_1907_R() != Items.M_4472_P) {
            stack.n_1700_B("Enchantments", listnbt);
        }
    }

    private static void n_1700_B(n_1700_B modifier, Z_1993_T stack) {
        if (!stack.n_1700_B()) {
            q_2896_o listnbt = stack.t_1786_h();
            for (int i = 0; i < listnbt.size(); ++i) {
                String s = listnbt.n_1700_B(i).M_588_G("id");
                int j = listnbt.n_1700_B(i).w_1484_f("lvl");
                V_3137_a.z_4693_k.J_1907_R(g_2336_b.J_1907_R(s)).ifPresent(enchantment -> modifier.accept((K_1310_v)enchantment, j));
            }
        }
    }

    private static void n_1700_B(n_1700_B modifier, Iterable<Z_1993_T> stacks) {
        for (Z_1993_T itemstack : stacks) {
            K_4096_w.n_1700_B(modifier, itemstack);
        }
    }

    public static int n_1700_B(Iterable<Z_1993_T> stacks, P_11_z source) {
        MutableInt mutableint = new MutableInt();
        K_4096_w.n_1700_B((K_1310_v enchantment, int level) -> mutableint.add(enchantment.n_1700_B(level, source)), stacks);
        return mutableint.intValue();
    }

    public static float n_1700_B(Z_1993_T stack, MobType creatureAttribute) {
        MutableFloat mutablefloat = new MutableFloat();
        K_4096_w.n_1700_B((K_1310_v enchantment, int level) -> mutablefloat.add(enchantment.n_1700_B(level, creatureAttribute)), stack);
        return mutablefloat.floatValue();
    }

    public static float n_1700_B(r_4811_B entityIn) {
        int i = K_4096_w.n_1700_B(Enchantments.w_1457_N, entityIn);
        return i > 0 ? SweepingEdgeEnchantment.P_1922_E(i) : 0.0f;
    }

    public static void n_1700_B(r_4811_B user, N_4263_v attacker) {
        n_1700_B enchantmenthelper$ienchantmentvisitor = (enchantment, level) -> enchantment.J_1907_R(user, attacker, level);
        if (user != null) {
            K_4096_w.n_1700_B(enchantmenthelper$ienchantmentvisitor, user.JsonUtils());
        }
        if (attacker instanceof a_3913_L) {
            K_4096_w.n_1700_B(enchantmenthelper$ienchantmentvisitor, user.A_2714_y());
        }
    }

    public static void J_1907_R(r_4811_B user, N_4263_v target) {
        n_1700_B enchantmenthelper$ienchantmentvisitor = (enchantment, level) -> enchantment.n_1700_B(user, target, level);
        if (user != null) {
            K_4096_w.n_1700_B(enchantmenthelper$ienchantmentvisitor, user.JsonUtils());
        }
        if (user instanceof a_3913_L) {
            K_4096_w.n_1700_B(enchantmenthelper$ienchantmentvisitor, user.A_2714_y());
        }
    }

    public static int n_1700_B(K_1310_v enchantmentIn, r_4811_B entityIn) {
        Collection<Z_1993_T> iterable = enchantmentIn.n_1700_B(entityIn).values();
        if (iterable == null) {
            return 0;
        }
        int i = 0;
        for (Z_1993_T itemstack : iterable) {
            int j = K_4096_w.n_1700_B(enchantmentIn, itemstack);
            if (j <= i) continue;
            i = j;
        }
        return i;
    }

    public static int J_1907_R(r_4811_B player) {
        return K_4096_w.n_1700_B(Enchantments.M_182_A, player);
    }

    public static int R_4764_Y(r_4811_B player) {
        return K_4096_w.n_1700_B(Enchantments.t_1786_h, player);
    }

    public static int G_564_y(r_4811_B entityIn) {
        return K_4096_w.n_1700_B(Enchantments.u_1723_Y, entityIn);
    }

    public static int P_1922_E(r_4811_B entityIn) {
        return K_4096_w.n_1700_B(Enchantments.t_148_a, entityIn);
    }

    public static int u_1723_Y(r_4811_B entityIn) {
        return K_4096_w.n_1700_B(Enchantments.Y_601_j, entityIn);
    }

    public static int J_1907_R(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.H_2857_Y, stack);
    }

    public static int R_4764_Y(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.A_4115_X, stack);
    }

    public static int v_4262_N(r_4811_B entityIn) {
        return K_4096_w.n_1700_B(Enchantments.multiplayerClientSuggestionProvider, entityIn);
    }

    public static boolean w_1484_f(r_4811_B entityIn) {
        return K_4096_w.n_1700_B(Enchantments.v_4262_N, entityIn) > 0;
    }

    public static boolean t_148_a(r_4811_B player) {
        return K_4096_w.n_1700_B(Enchantments.s_956_w, player) > 0;
    }

    public static boolean s_956_w(r_4811_B entity) {
        return K_4096_w.n_1700_B(Enchantments.M_588_G, entity) > 0;
    }

    public static boolean G_564_y(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.u_2550_I, stack) > 0;
    }

    public static boolean P_1922_E(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.d_2461_k, stack) > 0;
    }

    public static int u_1723_Y(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.Y_1740_V, stack);
    }

    public static int v_4262_N(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.x_607_J, stack);
    }

    public static boolean w_1484_f(Z_1993_T stack) {
        return K_4096_w.n_1700_B(Enchantments.e_4240_b, stack) > 0;
    }

    @Nullable
    public static Map.Entry<e_1174_E, Z_1993_T> J_1907_R(K_1310_v targetEnchantment, r_4811_B entityIn) {
        return K_4096_w.n_1700_B(targetEnchantment, entityIn, (Z_1993_T stack) -> true);
    }

    @Nullable
    public static Map.Entry<e_1174_E, Z_1993_T> n_1700_B(K_1310_v enchantment, r_4811_B livingEntity, Predicate<Z_1993_T> stackCondition) {
        Map<e_1174_E, Z_1993_T> map = enchantment.n_1700_B(livingEntity);
        if (map.isEmpty()) {
            return null;
        }
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<e_1174_E, Z_1993_T> entry : map.entrySet()) {
            Z_1993_T itemstack = entry.getValue();
            if (itemstack.n_1700_B() || K_4096_w.n_1700_B(enchantment, itemstack) <= 0 || !stackCondition.test(itemstack)) continue;
            list.add(entry);
        }
        return list.isEmpty() ? null : (Map.Entry)list.get(livingEntity.M_3508_C().nextInt(list.size()));
    }

    public static int n_1700_B(Random rand, int enchantNum, int power, Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        int i = item.G_564_y();
        if (i <= 0) {
            return 0;
        }
        if (power > 15) {
            power = 15;
        }
        int j = rand.nextInt(8) + 1 + (power >> 1) + rand.nextInt(power + 1);
        if (enchantNum == 0) {
            return Math.max(j / 3, 1);
        }
        return enchantNum == 1 ? j * 2 / 3 + 1 : Math.max(j, power * 2);
    }

    public static Z_1993_T n_1700_B(Random random, Z_1993_T stack, int level, boolean allowTreasure) {
        boolean flag;
        List<T_4041_i> list = K_4096_w.J_1907_R(random, stack, level, allowTreasure);
        boolean bl = flag = stack.J_1907_R() == Items.K_4237_u;
        if (flag) {
            stack = new Z_1993_T(Items.M_4472_P);
        }
        for (T_4041_i enchantmentdata : list) {
            if (flag) {
                EnchantedBookItem.n_1700_B(stack, enchantmentdata);
                continue;
            }
            stack.n_1700_B(enchantmentdata.n_1700_B, enchantmentdata.J_1907_R);
        }
        return stack;
    }

    public static List<T_4041_i> J_1907_R(Random randomIn, Z_1993_T itemStackIn, int level, boolean allowTreasure) {
        ArrayList list = Lists.newArrayList();
        q_1613_l item = itemStackIn.J_1907_R();
        int i = item.G_564_y();
        if (i <= 0) {
            return list;
        }
        level = level + 1 + randomIn.nextInt(i / 4 + 1) + randomIn.nextInt(i / 4 + 1);
        float f = (randomIn.nextFloat() + randomIn.nextFloat() - 1.0f) * 0.15f;
        List<T_4041_i> list1 = K_4096_w.n_1700_B(level = u_530_F.n_1700_B(Math.round((float)level + (float)level * f), 1, Integer.MAX_VALUE), itemStackIn, allowTreasure);
        if (!list1.isEmpty()) {
            list.add(WeighedRandom.n_1700_B(randomIn, list1));
            while (randomIn.nextInt(50) <= level) {
                K_4096_w.n_1700_B(list1, (T_4041_i)j_3341_s.n_1700_B(list));
                if (list1.isEmpty()) break;
                list.add(WeighedRandom.n_1700_B(randomIn, list1));
                level /= 2;
            }
        }
        return list;
    }

    public static void n_1700_B(List<T_4041_i> dataList, T_4041_i data) {
        Iterator<T_4041_i> iterator = dataList.iterator();
        while (iterator.hasNext()) {
            if (data.n_1700_B.J_1907_R(iterator.next().n_1700_B)) continue;
            iterator.remove();
        }
    }

    public static boolean n_1700_B(Collection<K_1310_v> enchantmentsIn, K_1310_v enchantmentIn) {
        for (K_1310_v enchantment : enchantmentsIn) {
            if (enchantment.J_1907_R(enchantmentIn)) continue;
            return false;
        }
        return true;
    }

    public static List<T_4041_i> n_1700_B(int level, Z_1993_T stack, boolean allowTreasure) {
        ArrayList list = Lists.newArrayList();
        q_1613_l item = stack.J_1907_R();
        boolean flag = stack.J_1907_R() == Items.K_4237_u;
        block0: for (K_1310_v enchantment : V_3137_a.z_4693_k) {
            if (enchantment.J_1907_R() && !allowTreasure || !enchantment.t_148_a() || !enchantment.J_1907_R.n_1700_B(item) && !flag) continue;
            for (int i = enchantment.n_1700_B(); i > enchantment.P_1922_E() - 1; --i) {
                if (level < enchantment.n_1700_B(i) || level > enchantment.J_1907_R(i)) continue;
                list.add(new T_4041_i(enchantment, i));
                continue block0;
            }
        }
        return list;
    }

    @FunctionalInterface
    static interface n_1700_B {
        public void accept(K_1310_v var1, int var2);
    }
}


