/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_1468_N;
import lightning.product.MobEffects;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public final class MobEffectUtil {
    public static String n_1700_B(k_2610_C effect, float durationFactor) {
        if (effect.w_1484_f()) {
            return "**:**";
        }
        int i = u_530_F.G_564_y((float)effect.J_1907_R() * durationFactor);
        return H_1468_N.n_1700_B(i);
    }

    public static boolean n_1700_B(r_4811_B entity) {
        return entity.J_1907_R(MobEffects.R_4764_Y) || entity.J_1907_R(MobEffects.A_4115_X);
    }

    public static int J_1907_R(r_4811_B entity) {
        int i = 0;
        int j = 0;
        if (entity.J_1907_R(MobEffects.R_4764_Y)) {
            i = entity.R_4764_Y(MobEffects.R_4764_Y).R_4764_Y();
        }
        if (entity.J_1907_R(MobEffects.A_4115_X)) {
            j = entity.R_4764_Y(MobEffects.A_4115_X).R_4764_Y();
        }
        return Math.max(i, j);
    }

    public static boolean R_4764_Y(r_4811_B entity) {
        return entity.J_1907_R(MobEffects.P_4830_p) || entity.J_1907_R(MobEffects.A_4115_X);
    }
}


