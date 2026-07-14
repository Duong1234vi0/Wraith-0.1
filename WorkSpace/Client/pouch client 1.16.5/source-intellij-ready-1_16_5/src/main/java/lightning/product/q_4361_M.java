/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.MinecraftAccess;
import lightning.product.r_4811_B;
import lightning.product.v_570_f;

public class q_4361_M
implements MinecraftAccess {
    public static N_4263_v n_1700_B(r_4811_B target, float yaw, float pitch, float attackDistance) {
        if (v_570_f.n_1700_B(yaw, pitch, attackDistance, target, true)) {
            return target;
        }
        return null;
    }
}


