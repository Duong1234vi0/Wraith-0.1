/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.g_2336_b;

public class s_2614_w {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/entity/steve.png");
    private static final g_2336_b J_1907_R = new g_2336_b("textures/entity/alex.png");

    public static g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public static g_2336_b n_1700_B(UUID playerUUID) {
        return s_2614_w.R_4764_Y(playerUUID) ? J_1907_R : n_1700_B;
    }

    public static String J_1907_R(UUID playerUUID) {
        return s_2614_w.R_4764_Y(playerUUID) ? "slim" : "default";
    }

    private static boolean R_4764_Y(UUID playerUUID) {
        return (playerUUID.hashCode() & 1) == 1;
    }
}

