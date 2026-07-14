/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.gui;

import java.util.UUID;
import lightning.product.A_2226_Q;
import lightning.product.W_2853_p;
import lightning.product.MinecraftClient;
import lightning.product.g_2336_b;
import lightning.product.s_2614_w;

public class GameProfileUtils {
    private static final MinecraftClient mc = MinecraftClient.A_4115_X();

    public static g_2336_b getSkin(UUID uuid) {
        W_2853_p connection = mc.k_2293_S();
        if (connection == null) {
            return s_2614_w.n_1700_B(uuid);
        }
        A_2226_Q playerInfo = connection.n_1700_B(uuid);
        if (playerInfo == null) {
            return s_2614_w.n_1700_B(uuid);
        }
        return playerInfo.u_1723_Y();
    }
}


