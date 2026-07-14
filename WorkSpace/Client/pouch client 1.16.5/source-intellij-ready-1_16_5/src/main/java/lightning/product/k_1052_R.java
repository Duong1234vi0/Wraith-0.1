/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 */
package lightning.product;

import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lightning.product.Attributes;
import lightning.product.MobEffects;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.L_1875_m;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.k_2610_C;
import lightning.product.q_2896_o;
import lightning.product.Items;

public class k_1052_R {
    private static boolean D_4792_h(Z_1993_T stack) {
        if (!stack.h_1847_R()) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        return lore.size() > 0;
    }

    private static String s_2632_s(Z_1993_T stack) {
        if (!stack.h_1847_R()) {
            return "";
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return "";
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return "";
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        return lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining(" "));
    }

    private static boolean n_1700_B(Z_1993_T stack, String ... keywords) {
        String loreText = k_1052_R.s_2632_s(stack);
        if (loreText.isEmpty()) {
            return false;
        }
        for (String keyword : keywords) {
            if (loreText.contains(keyword.toLowerCase())) continue;
            return false;
        }
        return true;
    }

    private static boolean J_1907_R(Z_1993_T stack, String ... keywords) {
        String loreText = k_1052_R.s_2632_s(stack);
        if (loreText.isEmpty()) {
            return false;
        }
        for (String keyword : keywords) {
            if (!loreText.contains(keyword.toLowerCase())) continue;
            return true;
        }
        return false;
    }

    public static boolean n_1700_B(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.u_488_m) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.containsKey(Enchantments.v_4262_N) && enchants.getOrDefault(Enchantments.G_564_y, 0) >= 5 && enchants.getOrDefault(Enchantments.J_1907_R, 0) >= 5 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_1922_E, 0) >= 5 && enchants.getOrDefault(Enchantments.n_1700_B, 0) >= 5 && enchants.getOrDefault(Enchantments.u_1723_Y, 0) >= 3 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.O_1043_U) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.getOrDefault(Enchantments.G_564_y, 0) >= 5 && enchants.getOrDefault(Enchantments.J_1907_R, 0) >= 5 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_1922_E, 0) >= 5 && enchants.getOrDefault(Enchantments.n_1700_B, 0) >= 5 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean R_4764_Y(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.v_1900_v) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.getOrDefault(Enchantments.G_564_y, 0) >= 5 && enchants.getOrDefault(Enchantments.J_1907_R, 0) >= 5 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_1922_E, 0) >= 5 && enchants.getOrDefault(Enchantments.n_1700_B, 0) >= 5 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean G_564_y(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.j_2129_E) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.getOrDefault(Enchantments.G_564_y, 0) >= 5 && enchants.getOrDefault(Enchantments.t_148_a, 0) >= 3 && enchants.getOrDefault(Enchantments.R_4764_Y, 0) >= 4 && enchants.getOrDefault(Enchantments.J_1907_R, 0) >= 5 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_1922_E, 0) >= 5 && enchants.getOrDefault(Enchantments.n_1700_B, 0) >= 5 && enchants.getOrDefault(Enchantments.M_588_G, 0) >= 3 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean P_1922_E(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.K_1200_E) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.getOrDefault(Enchantments.Q_4569_t, 0) >= 7 && enchants.getOrDefault(Enchantments.t_1786_h, 0) >= 2 && enchants.getOrDefault(Enchantments.multiplayerClientSuggestionProvider, 0) >= 5 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_4830_p, 0) >= 7 && enchants.getOrDefault(Enchantments.h_1847_R, 0) >= 7 && enchants.getOrDefault(Enchantments.w_1457_N, 0) >= 3 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean u_1723_Y(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.P_2605_j) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.containsKey(Enchantments.e_4240_b) && enchants.getOrDefault(Enchantments.t_1786_h, 0) >= 2 && enchants.getOrDefault(Enchantments.t_4043_B, 0) >= 5 && enchants.getOrDefault(Enchantments.Y_1740_V, 0) >= 3 && enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.P_4830_p, 0) >= 7 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5;
    }

    public static boolean v_4262_N(Z_1993_T stack) {
        String loreText;
        if (stack.J_1907_R() != Items.K_1964_I) {
            return false;
        }
        int count = 0;
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        if (enchants.getOrDefault(Enchantments.Y_601_j, 0) >= 10) {
            ++count;
        }
        if (enchants.getOrDefault(Enchantments.C_2741_M, 0) >= 5) {
            ++count;
        }
        if (enchants.containsKey(Enchantments.v_4276_D)) {
            ++count;
        }
        if (enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 5) {
            ++count;
        }
        if ((loreText = k_1052_R.s_2632_s(stack)).contains("\u043d\u0435\u0441\u0442\u0430\u0431\u0438\u043b\u044c\u043d\u044b\u0439")) {
            return false;
        }
        if (loreText.contains("\u0431\u0443\u043b\u044c\u0434\u043e\u0437\u0435\u0440 ii") || loreText.contains("bulldozer ii")) {
            ++count;
        }
        if (loreText.contains("\u043c\u0430\u0433\u043d\u0438\u0442")) {
            ++count;
        }
        if (loreText.contains("\u043e\u043f\u044b\u0442\u043d\u044b\u0439 iii")) {
            ++count;
        }
        if (loreText.contains("\u043f\u0430\u0443\u0442\u0438\u043d\u0430")) {
            ++count;
        }
        if (loreText.contains("\u043f\u0438\u043d\u0433\u0435\u0440")) {
            ++count;
        }
        if (loreText.contains("\u0430\u0432\u0442\u043e-\u043f\u043b\u0430\u0432\u043a\u0430")) {
            ++count;
        }
        return count == 10;
    }

    public static boolean w_1484_f(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.V_2454_J) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.containsKey(Enchantments.v_4276_D) && enchants.getOrDefault(Enchantments.z_1737_N, 0) >= 5 && enchants.getOrDefault(Enchantments.d_2427_y, 0) >= 3 && enchants.getOrDefault(Enchantments.Q_2552_b, 0) >= 3 && enchants.containsKey(Enchantments.n_3318_d);
    }

    public static boolean t_148_a(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasSlow = false;
        boolean hasSpeed = false;
        boolean hasGlowing = false;
        boolean hasBlindness = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.J_1907_R && effect.R_4764_Y() >= 9 && effect.J_1907_R() >= 200) {
                hasSlow = true;
            }
            if (effect.n_1700_B() == MobEffects.n_1700_B && effect.R_4764_Y() >= 5 && effect.J_1907_R() >= 400) {
                hasSpeed = true;
            }
            if (effect.n_1700_B() == MobEffects.Q_4569_t && effect.R_4764_Y() >= 1 && effect.J_1907_R() >= 3600) {
                hasBlindness = true;
            }
            if (effect.n_1700_B() != MobEffects.k_2293_S || effect.R_4764_Y() < 9 || effect.J_1907_R() < 100) continue;
            hasGlowing = true;
        }
        return hasSlow && hasSpeed && hasGlowing && hasBlindness;
    }

    public static boolean s_956_w(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasRegen = false;
        boolean hasInvis = false;
        boolean hasInstHealth = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.s_956_w && effect.R_4764_Y() >= 3 && effect.J_1907_R() >= 1200) {
                hasRegen = true;
            }
            if (effect.n_1700_B() == MobEffects.h_1847_R && effect.R_4764_Y() >= 2 && effect.J_1907_R() >= 12000) {
                hasInvis = true;
            }
            if (effect.n_1700_B() != MobEffects.u_1723_Y || effect.R_4764_Y() < 2 || effect.J_1907_R() < 0) continue;
            hasInstHealth = true;
        }
        return hasRegen && hasInvis && hasInstHealth;
    }

    public static boolean u_2550_I(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasStreght = false;
        boolean hasSlowness = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.P_1922_E && effect.J_1907_R() >= 5 && effect.J_1907_R() > 600) {
                hasStreght = true;
            }
            if (effect.n_1700_B() != MobEffects.J_1907_R || effect.R_4764_Y() < 4 || effect.J_1907_R() < 600) continue;
            hasSlowness = true;
        }
        return hasStreght && hasSlowness;
    }

    public static boolean M_588_G(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasHealthBoost = false;
        boolean hasFire = false;
        boolean hasInvis = false;
        boolean hasResistance = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.u_2550_I && effect.J_1907_R() >= 12000) {
                hasResistance = true;
            }
            if (effect.n_1700_B() == MobEffects.M_588_G && effect.R_4764_Y() >= 12000) {
                hasFire = true;
            }
            if (effect.n_1700_B() == MobEffects.Y_259_p && effect.R_4764_Y() >= 3 && effect.J_1907_R() >= 1200) {
                hasHealthBoost = true;
            }
            if (effect.n_1700_B() != MobEffects.h_1847_R || effect.R_4764_Y() < 3 || effect.J_1907_R() < 18000) continue;
            hasInvis = true;
        }
        return hasHealthBoost && hasFire && hasInvis && hasResistance;
    }

    public static boolean P_4830_p(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasStrength = false;
        boolean hasSpeed = false;
        boolean hasHaste = false;
        boolean hasDamage = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.P_1922_E && effect.R_4764_Y() == 4 && effect.J_1907_R() >= 1200) {
                hasStrength = true;
            }
            if (effect.n_1700_B() == MobEffects.n_1700_B && effect.R_4764_Y() == 3 && effect.J_1907_R() >= 6000) {
                hasSpeed = true;
            }
            if (effect.n_1700_B() == MobEffects.R_4764_Y && effect.R_4764_Y() == 3 && effect.J_1907_R() >= 1200) {
                hasHaste = true;
            }
            if (effect.n_1700_B() != MobEffects.v_4262_N || effect.R_4764_Y() != 2 || effect.J_1907_R() < 0) continue;
            hasDamage = true;
        }
        return hasStrength && hasSpeed && hasDamage && hasHaste;
    }

    public static boolean h_1847_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasPoison = false;
        boolean hasWither = false;
        boolean hasSlow = false;
        boolean hasHunger = false;
        boolean hasGlow = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.w_1457_N && effect.R_4764_Y() >= 2 && effect.J_1907_R() >= 400) {
                hasPoison = true;
            }
            if (effect.n_1700_B() == MobEffects.Y_601_j && effect.R_4764_Y() >= 2 && effect.J_1907_R() >= 400) {
                hasWither = true;
            }
            if (effect.n_1700_B() == MobEffects.J_1907_R && effect.R_4764_Y() >= 3 && effect.J_1907_R() >= 400) {
                hasSlow = true;
            }
            if (effect.n_1700_B() == MobEffects.t_1786_h && effect.R_4764_Y() >= 5 && effect.J_1907_R() >= 400) {
                hasHunger = true;
            }
            if (effect.n_1700_B() != MobEffects.k_2293_S || effect.J_1907_R() < 0) continue;
            hasGlow = true;
        }
        return hasPoison && hasWither && hasSlow && hasHunger && hasGlow;
    }

    public static boolean Q_4569_t(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.g_2492_v) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        boolean hasWeak = false;
        boolean hasWither = false;
        boolean hasMining = false;
        boolean hasBlid = false;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.multiplayerClientSuggestionProvider && effect.R_4764_Y() >= 2 && effect.J_1907_R() >= 1800) {
                hasWeak = true;
            }
            if (effect.n_1700_B() == MobEffects.G_564_y && effect.R_4764_Y() >= 2 && effect.J_1907_R() >= 200) {
                hasMining = true;
            }
            if (effect.n_1700_B() == MobEffects.Y_601_j && effect.R_4764_Y() >= 3 && effect.J_1907_R() >= 1800) {
                hasWither = true;
            }
            if (effect.n_1700_B() != MobEffects.Q_4569_t || effect.R_4764_Y() < 1 || effect.J_1907_R() < 200) continue;
            hasBlid = true;
        }
        return hasWeak && hasWither && hasMining && hasBlid;
    }

    public static boolean M_182_A(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsSpeedRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        boolean meetsArmorRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.w_1484_f && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsAttackSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.t_148_a && modifier.G_564_y() == -3.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 4.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsSpeedRequirement && meetsAttackDamageRequirement && meetsAttackSpeedRequirement && meetsArmorRequirement;
    }

    public static boolean t_1786_h(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        if (attributes.size() != 2) {
            return false;
        }
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsArmorRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.t_148_a && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 2.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsAttackDamageRequirement && meetsArmorRequirement;
    }

    public static boolean multiplayerClientSuggestionProvider(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 5.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsAttackDamageRequirement;
    }

    public static boolean w_1457_N(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsSpeedRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.15 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.w_1484_f || modifier.G_564_y() != -4.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackSpeedRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsAttackSpeedRequirement && meetsSpeedRequirement;
    }

    public static boolean Y_601_j(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsArmorRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 1.5 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.t_148_a || modifier.G_564_y() != 1.5 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsArmorRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsArmorRequirement;
    }

    public static boolean Y_259_p(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsAttackSpeedRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.w_1484_f && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsAttackSpeedRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 2.5 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsAttackSpeedRequirement && meetsAttackDamageRequirement;
    }

    public static boolean Q_2552_b(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsArmorToughnessRequirement = false;
        boolean meetsArmorRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.u_1723_Y && modifier.G_564_y() == 3.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsAttackDamageRequirement = true;
                continue;
            }
            if (attribute == Attributes.s_956_w && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorToughnessRequirement = true;
                continue;
            }
            if (attribute != Attributes.t_148_a || modifier.G_564_y() != 2.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsArmorRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsAttackDamageRequirement && meetsArmorToughnessRequirement && meetsArmorRequirement;
    }

    public static boolean C_2741_M(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsSpeedRequirement = false;
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 7.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsSpeedRequirement && meetsMaxHealthRequirement && meetsAttackDamageRequirement;
    }

    public static boolean k_2293_S(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsSpeedRequirement = false;
        boolean meetsArmorRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.07 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.w_1484_f && modifier.G_564_y() == 0.13 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsAttackSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.t_148_a && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 3.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsSpeedRequirement && meetsArmorRequirement && meetsAttackDamageRequirement && meetsMaxHealthRequirement && meetsAttackSpeedRequirement;
    }

    public static boolean q_2307_F(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsSpeedRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.u_1723_Y && modifier.G_564_y() == 3.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsAttackDamageRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.15 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute != Attributes.w_1484_f || modifier.G_564_y() != 0.15 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackSpeedRequirement = true;
        }
        return meetsSpeedRequirement && meetsAttackDamageRequirement && meetsMaxHealthRequirement && meetsAttackSpeedRequirement;
    }

    public static boolean Z_875_P(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsAttackDamageRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.w_1484_f && modifier.G_564_y() == 0.15 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsAttackSpeedRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 2.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsAttackDamageRequirement && meetsAttackSpeedRequirement;
    }

    public static boolean c_3005_b(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsArmorRequirement = false;
        boolean meetsArmorToughnessRequirement = false;
        boolean meetsSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.t_148_a && modifier.G_564_y() == 3.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorRequirement = true;
                continue;
            }
            if (attribute == Attributes.s_956_w && modifier.G_564_y() == 3.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorToughnessRequirement = true;
                continue;
            }
            if (attribute != Attributes.G_564_y || modifier.G_564_y() != -0.15 || modifier.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R) continue;
            meetsSpeedRequirement = true;
        }
        return meetsArmorRequirement && meetsArmorToughnessRequirement && meetsSpeedRequirement;
    }

    public static boolean H_2857_Y(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsHealthRequirement = false;
        boolean meetsSpeedRequirement = false;
        boolean meetsArmorRequirement = false;
        boolean meetsAttackSpeedRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.G_564_y && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsSpeedRequirement = true;
                continue;
            }
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.w_1484_f && modifier.G_564_y() == 0.1 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsAttackSpeedRequirement = true;
                continue;
            }
            if (attribute != Attributes.t_148_a || modifier.G_564_y() != 0.1 || modifier.R_4764_Y() != U_1880_G.n_1700_B.J_1907_R) continue;
            meetsArmorRequirement = true;
        }
        return meetsHealthRequirement && meetsSpeedRequirement && meetsAttackSpeedRequirement && meetsArmorRequirement;
    }

    public static boolean A_4115_X(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsMaxHealthRequirement = false;
        boolean meetsAttackDamageRequirement = false;
        boolean meetsArmorDamageRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == -2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute == Attributes.t_148_a && modifier.G_564_y() == -2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsArmorDamageRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 6.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsMaxHealthRequirement && meetsAttackDamageRequirement && meetsArmorDamageRequirement;
    }

    public static boolean Y_1740_V(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsArmorRequirement = false;
        boolean meetsHealthRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 4.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.J_1907_R) {
                meetsHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.t_148_a || modifier.G_564_y() != 2.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsArmorRequirement = true;
        }
        return meetsArmorRequirement && meetsHealthRequirement;
    }

    public static boolean t_4043_B(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsAttackDamageRequirement = false;
        boolean meetsMaxHealthRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_1723_Y || modifier.G_564_y() != 2.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsAttackDamageRequirement = true;
        }
        return meetsAttackDamageRequirement && meetsMaxHealthRequirement;
    }

    public static boolean x_607_J(Z_1993_T itemStack) {
        Multimap<Attribute, U_1880_G> attributes = itemStack.n_1700_B(e_1174_E.J_1907_R);
        boolean meetsLuckRequirement = false;
        boolean meetsMaxHealthRequirement = false;
        for (Map.Entry entry : attributes.entries()) {
            Attribute attribute = (Attribute)entry.getKey();
            U_1880_G modifier = (U_1880_G)entry.getValue();
            if (attribute == Attributes.n_1700_B && modifier.G_564_y() == 2.0 && modifier.R_4764_Y() == U_1880_G.n_1700_B.n_1700_B) {
                meetsMaxHealthRequirement = true;
                continue;
            }
            if (attribute != Attributes.u_2550_I || modifier.G_564_y() != 1.0 || modifier.R_4764_Y() != U_1880_G.n_1700_B.n_1700_B) continue;
            meetsLuckRequirement = true;
        }
        return meetsLuckRequirement && meetsMaxHealthRequirement;
    }

    public static boolean e_4240_b(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.f_1186_l) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.contains("\u0421\u0435\u0440\u0435\u0431\u0440\u043e")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0440\u0435\u0434\u043a\u043e\u0441\u0442\u044c", "\u043c\u0430\u0442\u0435\u0440\u0438\u0430\u043b", "\u0434\u043e\u043d\u0430\u0442", "\u0432\u0430\u043b\u044e\u0442\u0430", "\u043e\u0431\u043c\u0435\u043d");
    }

    public static boolean n_3318_d(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.V_1824_v) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.contains("\u0414\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0441\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c", "\u044d\u0444\u0444\u0435\u043a\u0442", "\u0438\u0441\u043f\u043e\u043b\u044c\u0437", "\u0430\u043a\u0442\u0438\u0432\u0430\u0446", "\u0443\u0440\u043e\u043d");
    }

    public static boolean d_2427_y(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.m_396_H) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.contains("\u0422\u0440\u0430\u043f\u043a\u0430")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u043b\u043e\u0432\u0443\u0448\u043a\u0430", "trap", "\u0443\u0441\u0442\u0430\u043d\u043e\u0432", "\u0430\u043a\u0442\u0438\u0432\u0430\u0446", "\u0443\u0440\u043e\u043d");
    }

    public static boolean z_1737_N(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.MinMaxBounds) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.contains("\u041f\u043b\u0430\u0441\u0442")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u043c\u0430\u0442\u0435\u0440\u0438\u0430\u043b", "\u043a\u0440\u0430\u0444\u0442", "\u0440\u0435\u0434\u043a\u043e\u0441\u0442\u044c", "\u0438\u0441\u043f\u043e\u043b\u044c\u0437");
    }

    public static boolean v_4276_D(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.RotatedPillarBlock) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.contains("\u0411\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0430\u0443\u0440\u0430", "\u044d\u0444\u0444\u0435\u043a\u0442", "\u0440\u0430\u0434\u0438\u0443\u0441", "\u0441\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c", "\u0437\u0430\u0449\u0438\u0442");
    }

    public static boolean d_2461_k(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.l_3370_o) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u043d\u0435\u0438\u0437\u0431\u0435\u0436\u043d\u044b\u0439");
    }

    public static boolean G_624_v(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.l_3370_o) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u0434\u0440\u0430\u043a\u043e\u043d\u0438\u0439");
    }

    public static boolean T_2506_i(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.k_578_l) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u0441 \u0441\u0444\u0435\u0440\u0430\u043c\u0438") && !stack.multiplayerClientSuggestionProvider().getString().toLowerCase().contains("\u0441 \u0441\u0444\u0435\u0440\u0430\u043c\u0438");
    }

    public static boolean q_4610_l(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.M_4472_P) {
            return false;
        }
        Map<K_1310_v, Integer> enchants = K_4096_w.n_1700_B(stack);
        return enchants.containsKey(Enchantments.v_4276_D);
    }

    public static boolean z_4693_k(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.NetherWartBlock) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        int count = 0;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.Q_4569_t) {
                ++count;
            }
            if (effect.n_1700_B() == MobEffects.t_148_a) {
                ++count;
            }
            if (effect.n_1700_B() == MobEffects.multiplayerClientSuggestionProvider) {
                ++count;
            }
            if (effect.n_1700_B() != MobEffects.J_1907_R) continue;
            ++count;
        }
        return count == 4;
    }

    public static boolean g_221_o(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.NetherWartBlock) {
            return false;
        }
        List<k_2610_C> effects = L_1875_m.n_1700_B(stack);
        int count = 0;
        for (k_2610_C effect : effects) {
            if (effect.n_1700_B() == MobEffects.multiplayerClientSuggestionProvider) {
                ++count;
            }
            if (effect.n_1700_B() != MobEffects.J_1907_R) continue;
            ++count;
        }
        return count == 2;
    }

    public static boolean e_2887_G(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.V_118_c) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!(itemName.contains("[\u2605]") && itemName.contains("TIER") && itemName.contains("WHITE"))) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0432\u0437\u0440\u044b\u0432", "\u0443\u0440\u043e\u043d", "\u0440\u0430\u0434\u0438\u0443\u0441", "tier", "\u043c\u043e\u0449\u043d\u043e\u0441\u0442\u044c");
    }

    public static boolean B_1668_F(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.V_118_c) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!(itemName.contains("[\u2605]") && itemName.contains("TIER") && itemName.contains("BLACK"))) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0432\u0437\u0440\u044b\u0432", "\u0443\u0440\u043e\u043d", "\u0440\u0430\u0434\u0438\u0443\u0441", "tier", "\u043c\u043e\u0449\u043d\u043e\u0441\u0442\u044c");
    }

    public static boolean g_164_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.R_4912_F) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!itemName.contains("[\u2605]") || !itemName.toLowerCase().contains("\u0434\u0430\u043c\u0430\u0433\u0435\u0440")) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u0443\u0440\u043e\u043d", "\u0430\u0442\u0430\u043a\u0430", "damage", "\u0441\u043f\u043e\u0441\u043e\u0431\u043d\u043e\u0441\u0442\u044c");
    }

    public static boolean X_933_l(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.A_2204_Z) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!(itemName.contains("[\u2605]") && itemName.contains("\u0447\u0430\u043d\u043a\u043e\u0432") && itemName.contains("1x1"))) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u043f\u0440\u043e\u0433\u0440\u0443\u0437", "\u0447\u0430\u043d\u043a", "\u0437\u0430\u0433\u0440\u0443\u0437", "chunk");
    }

    public static boolean Z_976_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.A_2204_Z) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!(itemName.contains("[\u2605]") && itemName.contains("\u0447\u0430\u043d\u043a\u043e\u0432") && itemName.contains("3x3"))) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u043f\u0440\u043e\u0433\u0440\u0443\u0437", "\u0447\u0430\u043d\u043a", "\u0437\u0430\u0433\u0440\u0443\u0437", "chunk");
    }

    public static boolean H_1990_U(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.A_2204_Z) {
            return false;
        }
        String itemName = stack.multiplayerClientSuggestionProvider().getString();
        if (!(itemName.contains("[\u2605]") && itemName.contains("\u0447\u0430\u043d\u043a\u043e\u0432") && itemName.contains("5x5"))) {
            return false;
        }
        return k_1052_R.D_4792_h(stack) && k_1052_R.J_1907_R(stack, "\u043f\u0440\u043e\u0433\u0440\u0443\u0437", "\u0447\u0430\u043d\u043a", "\u0437\u0430\u0433\u0440\u0443\u0437", "chunk");
    }

    public static boolean N_2525_X(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.z_2909_G) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u043e\u0431\u044b\u0447\u043d\u044b\u0439") && loreText.contains("\u043b\u0443\u0442\u0430");
    }

    public static boolean c_4037_x(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.z_2909_G) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u0431\u043e\u0433\u0430\u0442\u044b\u0439") && loreText.contains("\u043b\u0443\u0442\u0430");
    }

    public static boolean g_2268_R(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.z_2909_G) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u044d\u043b\u0438\u0442\u043d\u044b\u0439") && loreText.contains("\u043b\u0443\u0442\u0430");
    }

    public static boolean T_3594_S(Z_1993_T stack) {
        if (stack.J_1907_R() != Items.z_2909_G) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        String loreText = lore.stream().map(nbt -> nbt.toString().toLowerCase()).collect(Collectors.joining());
        return loreText.contains("\u043b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u044b\u0439") && loreText.contains("\u043b\u0443\u0442\u0430");
    }

    public static boolean n_1700_B(Z_1993_T stack, String itemName) {
        if (stack == null || stack.n_1700_B()) {
            return false;
        }
        String lowerName = itemName.toLowerCase();
        if (lowerName.contains("\u0442\u043e\u0442\u0435\u043c \u0431\u0435\u0441\u0441\u043c\u0435\u0440\u0442\u0438\u044f") && stack.J_1907_R() == Items.N_81_X && !stack.k_2293_S()) {
            return true;
        }
        if (lowerName.contains("\u044d\u043b\u0438\u0442\u0440\u044b") && stack.J_1907_R() == Items.NyliumBlock) {
            return true;
        }
        if (lowerName.contains("\u0447\u0430\u0440\u043a\u0430") && stack.J_1907_R() == Items.E_4612_l) {
            return true;
        }
        if (lowerName.contains("\u0430\u043b\u043c\u0430\u0437") && stack.J_1907_R() == Items.k_2273_q) {
            return true;
        }
        if (lowerName.contains("\u043f\u0443\u0437\u044b\u0440\u0435\u043a \u043e\u043f\u044b\u0442\u0430") && stack.J_1907_R() == Items.s_3084_y) {
            return true;
        }
        if (lowerName.contains("\u0448\u0430\u043b\u043a\u0435\u0440") && stack.J_1907_R() == Items.n_3932_q) {
            return true;
        }
        if (lowerName.contains("\u043c\u0430\u044f\u043a") && stack.J_1907_R() == Items.t_2932_z) {
            return true;
        }
        if (lowerName.contains("\u043e\u0431\u0441\u0438\u0434\u0438\u0430\u043d") && stack.J_1907_R() == Items.d_2545_n) {
            return true;
        }
        if (lowerName.contains("\u043d\u0435\u0437\u0435\u0440\u0438\u0442\u043e\u0432\u044b\u0439 \u0441\u043b\u0438\u0442\u043e\u043a") && stack.J_1907_R() == Items.q_4124_m) {
            return true;
        }
        if (lowerName.contains("\u0448\u043b\u0435\u043c \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.n_1700_B(stack);
        }
        if (lowerName.contains("\u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.J_1907_R(stack);
        }
        if (lowerName.contains("\u043f\u043e\u043d\u043e\u0436\u0438 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.R_4764_Y(stack);
        }
        if (lowerName.contains("\u0431\u043e\u0442\u0438\u043d\u043a\u0438 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.G_564_y(stack);
        }
        if (lowerName.contains("\u043c\u0435\u0447 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.P_1922_E(stack);
        }
        if (lowerName.contains("\u0442\u0440\u0435\u0437\u0443\u0431\u0435\u0446 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.u_1723_Y(stack);
        }
        if (lowerName.contains("\u043a\u0438\u0440\u043a\u0430 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.v_4262_N(stack);
        }
        if (lowerName.contains("\u0430\u0440\u0431\u0430\u043b\u0435\u0442 \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.w_1484_f(stack);
        }
        if (lowerName.contains("\u0445\u043b\u043e\u043f\u0443\u0448\u043a\u0430")) {
            return k_1052_R.t_148_a(stack);
        }
        if (lowerName.contains("\u0441\u0432\u044f\u0442\u0430\u044f \u0432\u043e\u0434\u0430")) {
            return k_1052_R.s_956_w(stack);
        }
        if (lowerName.contains("\u0437\u0435\u043b\u044c\u0435 \u0433\u043d\u0435\u0432\u0430")) {
            return k_1052_R.u_2550_I(stack);
        }
        if (lowerName.contains("\u0437\u0435\u043b\u044c\u0435 \u043f\u0430\u043b\u043b\u0430\u0434\u0438\u043d\u0430")) {
            return k_1052_R.M_588_G(stack);
        }
        if (lowerName.contains("\u0437\u0435\u043b\u044c\u0435 \u0430\u0441\u0441\u0430\u0441\u0438\u043d\u0430")) {
            return k_1052_R.P_4830_p(stack);
        }
        if (lowerName.contains("\u0437\u0435\u043b\u044c\u0435 \u0440\u0430\u0434\u0438\u0430\u0446\u0438\u0438")) {
            return k_1052_R.h_1847_R(stack);
        }
        if (lowerName.contains("\u0441\u043d\u043e\u0442\u0432\u043e\u0440\u043d\u043e\u0435")) {
            return k_1052_R.Q_4569_t(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0440\u0430\u0437\u0434\u043e\u0440\u0430")) {
            return k_1052_R.M_182_A(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0442\u0438\u0440\u0430\u043d\u0430")) {
            return k_1052_R.t_1786_h(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u044f\u0440\u043e\u0441\u0442\u0438")) {
            return k_1052_R.multiplayerClientSuggestionProvider(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0432\u0438\u0445\u0440\u044f")) {
            return k_1052_R.w_1457_N(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u043c\u0440\u0430\u043a\u0430")) {
            return k_1052_R.Y_601_j(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0434\u0435\u043c\u043e\u043d\u0430")) {
            return k_1052_R.Y_259_p(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u043a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044f")) {
            return k_1052_R.Q_2552_b(stack);
        }
        if (lowerName.contains("\u0442\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u043a\u0430\u0440\u0430\u0442\u0435\u043b\u044f")) {
            return k_1052_R.C_2741_M(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0445\u0430\u043e\u0441\u0430")) {
            return k_1052_R.k_2293_S(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0430\u0444\u0438\u043d\u044b")) {
            return k_1052_R.q_2307_F(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0441\u0430\u0442\u0438\u0440\u0430")) {
            return k_1052_R.Z_875_P(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0442\u0438\u0442\u0430\u043d\u0430")) {
            return k_1052_R.c_3005_b(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0438\u043a\u0430\u0440\u0430")) {
            return k_1052_R.t_4043_B(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0433\u0438\u0434\u0440\u044b")) {
            return k_1052_R.Y_1740_V(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0431\u0435\u0441\u0442\u0438\u0438")) {
            return k_1052_R.H_2857_Y(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u0430\u0440\u0435\u0441\u0430")) {
            return k_1052_R.A_4115_X(stack);
        }
        if (lowerName.contains("\u0441\u0444\u0435\u0440\u0430 \u044d\u0440\u0438\u0434\u0430")) {
            return k_1052_R.x_607_J(stack);
        }
        if (lowerName.contains("\u0441\u0435\u0440\u0435\u0431\u0440\u043e")) {
            return k_1052_R.e_4240_b(stack);
        }
        if (lowerName.contains("\u0434\u0435\u0437\u043e\u0440\u0438\u0435\u043d\u0442\u0430\u0446\u0438\u044f")) {
            return k_1052_R.n_3318_d(stack);
        }
        if (lowerName.contains("\u0442\u0440\u0430\u043f\u043a\u0430")) {
            return k_1052_R.d_2427_y(stack);
        }
        if (lowerName.contains("\u043f\u043b\u0430\u0441\u0442")) {
            return k_1052_R.z_1737_N(stack);
        }
        if (lowerName.contains("\u0431\u043e\u0436\u044c\u044f \u0430\u0443\u0440\u0430")) {
            return k_1052_R.v_4276_D(stack);
        }
        if (lowerName.contains("\u043d\u0435\u0438\u0437\u0431\u0435\u0436\u043d\u044b\u0439 \u0441\u043a\u0438\u043d")) {
            return k_1052_R.d_2461_k(stack);
        }
        if (lowerName.contains("\u0434\u0440\u0430\u043a\u043e\u043d\u0438\u0439 \u0441\u043a\u0438\u043d")) {
            return k_1052_R.G_624_v(stack);
        }
        if (lowerName.contains("\u043e\u0442\u043c\u044b\u0447\u043a\u0430 \u043a \u0441\u0444\u0435\u0440\u0430\u043c")) {
            return k_1052_R.T_2506_i(stack);
        }
        if (lowerName.contains("\u043a\u043d\u0438\u0433\u0430 \u043f\u043e\u0447\u0438\u043d\u043a\u0430") || lowerName.contains("\u043f\u043e\u0447\u0438\u043d\u043a\u0430")) {
            return k_1052_R.q_4610_l(stack);
        }
        if (lowerName.contains("\u0441\u0442\u0440\u0435\u043b\u0430 \u043f\u0430\u0440\u0430\u043d\u043e\u0438") || lowerName.contains("\u0441\u0442\u0440\u0435\u043b\u0430 \u043f\u0430\u0440\u0430\u043d\u043e\u0439\u0438")) {
            return k_1052_R.z_4693_k(stack);
        }
        if (lowerName.contains("\u043b\u0435\u0434\u044f\u043d\u0430\u044f \u0441\u0442\u0440\u0435\u043b\u0430")) {
            return k_1052_R.g_221_o(stack);
        }
        if (lowerName.contains("\u0442\u0430\u0435\u0440 \u0432\u0430\u0439\u0442") || lowerName.contains("tier white")) {
            return k_1052_R.e_2887_G(stack);
        }
        if (lowerName.contains("\u0442\u0430\u0439\u0440 \u0431\u043b\u044d\u043a") || lowerName.contains("tier black")) {
            return k_1052_R.B_1668_F(stack);
        }
        if (lowerName.contains("\u0434\u0430\u043c\u0430\u0433\u0435\u0440")) {
            return k_1052_R.g_164_R(stack);
        }
        if (lowerName.contains("\u043f\u0440\u043e\u0433\u0440\u0443\u0437\u0447\u0438\u043a \u0447\u0430\u043d\u043a\u043e\u0432 1x1")) {
            return k_1052_R.X_933_l(stack);
        }
        if (lowerName.contains("\u043f\u0440\u043e\u0433\u0440\u0443\u0437\u0447\u0438\u043a \u0447\u0430\u043d\u043a\u043e\u0432 3x3")) {
            return k_1052_R.Z_976_R(stack);
        }
        if (lowerName.contains("\u043f\u0440\u043e\u0433\u0440\u0443\u0437\u0447\u0438\u043a \u0447\u0430\u043d\u043a\u043e\u0432 5x5")) {
            return k_1052_R.H_1990_U(stack);
        }
        if (lowerName.contains("\u043e\u0431\u044b\u0447\u043d\u044b\u0439") && lowerName.contains("\u043c\u0438\u0441\u0442")) {
            return k_1052_R.N_2525_X(stack);
        }
        if (lowerName.contains("\u0431\u043e\u0433\u0430\u0442\u044b\u0439") && lowerName.contains("\u043c\u0438\u0441\u0442")) {
            return k_1052_R.c_4037_x(stack);
        }
        if (lowerName.contains("\u044d\u043b\u0438\u0442\u043d\u044b\u0439") && lowerName.contains("\u043c\u0438\u0441\u0442")) {
            return k_1052_R.g_2268_R(stack);
        }
        if (lowerName.contains("\u043b\u0435\u0433\u0435\u043d\u0434\u0430\u0440\u043d\u044b\u0439") && lowerName.contains("\u043c\u0438\u0441\u0442")) {
            return k_1052_R.T_3594_S(stack);
        }
        return false;
    }
}


