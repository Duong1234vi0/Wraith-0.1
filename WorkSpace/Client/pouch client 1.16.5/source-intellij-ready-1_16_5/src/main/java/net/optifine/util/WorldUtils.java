/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.b_4507_u;
import lightning.product.f_2392_k;

public class WorldUtils {
    public static int getDimensionId(b_4507_u world) {
        return world == null ? 0 : WorldUtils.getDimensionId(world.g_2268_R());
    }

    public static int getDimensionId(f_2392_k<b_4507_u> dimension) {
        if (dimension == b_4507_u.v_4262_N) {
            return -1;
        }
        if (dimension == b_4507_u.u_1723_Y) {
            return 0;
        }
        return dimension == b_4507_u.w_1484_f ? 1 : 0;
    }

    public static boolean isNether(b_4507_u world) {
        return world.g_2268_R() == b_4507_u.v_4262_N;
    }

    public static boolean isOverworld(b_4507_u world) {
        f_2392_k<b_4507_u> registrykey = world.g_2268_R();
        return WorldUtils.getDimensionId(registrykey) == 0;
    }

    public static boolean isEnd(b_4507_u world) {
        return world.g_2268_R() == b_4507_u.w_1484_f;
    }
}

