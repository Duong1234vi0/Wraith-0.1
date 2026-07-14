/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MinecraftClient;

public class S_4088_D {
    public static String n_1700_B() {
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc.z_1737_N() != null) {
            return mc.z_1737_N().R_4764_Y();
        }
        return "Player";
    }

    public static long J_1907_R() {
        return System.currentTimeMillis() % 1000000L;
    }

    public static String R_4764_Y() {
        return "1.16.5";
    }
}


