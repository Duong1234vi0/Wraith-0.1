/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.voice.client;

import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_772_m;
import lightning.product.MinecraftClient;
import lightning.product.c_973_a;
import lightning.product.j_3341_s;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public class ChatUtils {
    public static void sendPlayerError(String translationKey, @Nullable Exception e) {
        MutableComponent error = ChatUtils.createModMessage(new F_2904_S(translationKey).n_1700_B(D_4024_W.P_4830_p)).n_1700_B(style -> {
            if (e != null) {
                return style.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b(e.getMessage()).n_1700_B(D_4024_W.P_4830_p)));
            }
            return style;
        });
        ChatUtils.sendPlayerMessage(error);
    }

    public static void sendModMessage(x_282_a message) {
        ChatUtils.sendPlayerMessage(ChatUtils.createModMessage(message));
    }

    public static MutableComponent createModMessage(x_282_a message) {
        return new U_2871_b("").n_1700_B(ComponentUtils.n_1700_B(new U_2871_b(CommonCompatibilityManager.INSTANCE.getModName())).n_1700_B(D_4024_W.u_2550_I)).n_1700_B(" ").n_1700_B(message);
    }

    public static void sendPlayerMessage(x_282_a component) {
        V_772_m player = MinecraftClient.A_4115_X().Y_259_p;
        if (player == null) {
            return;
        }
        player.n_1700_B(component, j_3341_s.J_1907_R);
    }
}



