/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import lightning.product.K_1310_v;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;

public class EnchantmentUtils {
    private static final Map<String, K_1310_v> MAP_ENCHANTMENTS = new HashMap<String, K_1310_v>();

    public static K_1310_v getEnchantment(String name) {
        K_1310_v enchantment = MAP_ENCHANTMENTS.get(name);
        if (enchantment == null) {
            g_2336_b resourcelocation = new g_2336_b(name);
            if (V_3137_a.z_4693_k.R_4764_Y(resourcelocation)) {
                enchantment = V_3137_a.z_4693_k.n_1700_B(resourcelocation);
            }
            MAP_ENCHANTMENTS.put(name, enchantment);
        }
        return enchantment;
    }

    public static K_1310_v getEnchantment(g_2336_b loc) {
        return !V_3137_a.z_4693_k.R_4764_Y(loc) ? null : V_3137_a.z_4693_k.n_1700_B(loc);
    }
}

