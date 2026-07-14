/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.MinecraftAccess;

public class Z_361_l
implements MinecraftAccess {
    public static String n_1700_B(int ticks) {
        int seconds = ticks / 20;
        int minutes = seconds / 60;
        return String.format("%d:%02d", minutes, seconds %= 60);
    }

    public static D_4024_W n_1700_B(String effectName) {
        if ((effectName = effectName.toLowerCase()).contains("strength")) {
            return D_4024_W.P_4830_p;
        }
        if (effectName.contains("fire_resistance")) {
            return D_4024_W.v_4262_N;
        }
        if (effectName.contains("speed")) {
            return D_4024_W.M_588_G;
        }
        if (effectName.contains("absorption")) {
            return D_4024_W.Q_4569_t;
        }
        if (effectName.contains("regeneration")) {
            return D_4024_W.h_1847_R;
        }
        if (effectName.contains("jump_boost")) {
            return D_4024_W.u_2550_I;
        }
        return D_4024_W.M_182_A;
    }
}


