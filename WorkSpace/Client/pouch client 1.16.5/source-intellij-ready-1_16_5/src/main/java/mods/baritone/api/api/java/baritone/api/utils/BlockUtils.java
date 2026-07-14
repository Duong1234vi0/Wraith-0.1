/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import java.util.HashMap;
import java.util.Map;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;

public class BlockUtils {
    private static transient Map<String, T_2915_h> resourceCache = new HashMap<String, T_2915_h>();

    public static String blockToString(T_2915_h block) {
        g_2336_b loc = V_3137_a.q_4610_l.J_1907_R(block);
        String name = loc.J_1907_R();
        if (!loc.R_4764_Y().equals("minecraft")) {
            name = loc.toString();
        }
        return name;
    }

    public static T_2915_h stringToBlockRequired(String name) {
        T_2915_h block = BlockUtils.stringToBlockNullable(name);
        if (block == null) {
            throw new IllegalArgumentException(String.format("Invalid block name %s", name));
        }
        return block;
    }

    public static T_2915_h stringToBlockNullable(String name) {
        T_2915_h block = resourceCache.get(name);
        if (block != null) {
            return block;
        }
        if (resourceCache.containsKey(name)) {
            return null;
        }
        block = V_3137_a.q_4610_l.J_1907_R(g_2336_b.J_1907_R((String)(name.contains(":") ? name : "minecraft:" + name))).orElse(null);
        HashMap<String, T_2915_h> copy = new HashMap<String, T_2915_h>(resourceCache);
        copy.put(name, block);
        resourceCache = copy;
        return block;
    }

    private BlockUtils() {
    }
}

