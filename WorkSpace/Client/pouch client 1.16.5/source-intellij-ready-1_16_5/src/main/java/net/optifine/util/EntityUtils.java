/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lightning.product.N_4263_v;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.t_5_h;
import net.optifine.Config;

public class EntityUtils {
    private static final Map<t_5_h, Integer> mapIdByType = new HashMap<t_5_h, Integer>();
    private static final Map<String, Integer> mapIdByLocation = new HashMap<String, Integer>();
    private static final Map<String, Integer> mapIdByName = new HashMap<String, Integer>();

    public static int getEntityIdByClass(N_4263_v entity) {
        return entity == null ? -1 : EntityUtils.getEntityIdByType(entity.f_4016_n());
    }

    public static int getEntityIdByType(t_5_h type) {
        Integer integer = mapIdByType.get(type);
        return integer == null ? -1 : integer;
    }

    public static int getEntityIdByLocation(String locStr) {
        Integer integer = mapIdByLocation.get(locStr);
        return integer == null ? -1 : integer;
    }

    public static int getEntityIdByName(String name) {
        Integer integer = mapIdByName.get(name = name.toLowerCase(Locale.ROOT));
        return integer == null ? -1 : integer;
    }

    static {
        for (t_5_h t_5_h2 : V_3137_a.g_221_o) {
            int i = V_3137_a.g_221_o.n_1700_B(t_5_h2);
            g_2336_b resourcelocation = V_3137_a.g_221_o.J_1907_R(t_5_h2);
            String s = resourcelocation.toString();
            String s1 = resourcelocation.J_1907_R();
            if (mapIdByType.containsKey(t_5_h2)) {
                Config.warn("Duplicate entity type: " + String.valueOf(t_5_h2) + ", id1: " + String.valueOf(mapIdByType.get(t_5_h2)) + ", id2: " + i);
            }
            if (mapIdByLocation.containsKey(s)) {
                Config.warn("Duplicate entity location: " + s + ", id1: " + String.valueOf(mapIdByLocation.get(s)) + ", id2: " + i);
            }
            if (mapIdByName.containsKey(s)) {
                Config.warn("Duplicate entity name: " + s1 + ", id1: " + String.valueOf(mapIdByName.get(s1)) + ", id2: " + i);
            }
            mapIdByType.put(t_5_h2, i);
            mapIdByLocation.put(s, i);
            mapIdByName.put(s1, i);
        }
    }
}

