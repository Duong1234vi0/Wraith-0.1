/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.L_2225_p;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.n_1494_c;
import lightning.product.ClientBootstrap;
import lightning.product.r_4811_B;

public class E_4612_l {
    public static boolean n_1700_B(N_4263_v entity, MultiBooleanSetting settings, boolean considerNaked) {
        boolean isNaked;
        a_3913_L player;
        block6: {
            block5: {
                if (!(entity instanceof a_3913_L)) break block5;
                player = (a_3913_L)entity;
                if (entity != MinecraftAccess.c_3005_b.Y_259_p) break block6;
            }
            return false;
        }
        boolean isFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        boolean bl = isNaked = player.E_3343_g() == 0;
        if (isFriend) {
            return settings.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439");
        }
        if (considerNaked && isNaked) {
            return settings.J_1907_R("\u0413\u043e\u043b\u044b\u0445");
        }
        return settings.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
    }

    public static boolean n_1700_B(N_4263_v entity, MultiBooleanSetting settings) {
        return E_4612_l.n_1700_B(entity, settings, true);
    }

    public static boolean n_1700_B(r_4811_B entity, MultiBooleanSetting settings) {
        return entity instanceof L_2225_p && settings.J_1907_R("\u0416\u0438\u0442\u0435\u043b\u0435\u0439") != false;
    }

    public static boolean J_1907_R(r_4811_B entity, MultiBooleanSetting settings) {
        return entity instanceof Animal && settings.J_1907_R("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445") != false;
    }

    public static boolean R_4764_Y(r_4811_B entity, MultiBooleanSetting settings) {
        return entity instanceof Z_530_i && settings.J_1907_R("\u041c\u043e\u0431\u043e\u0432") != false;
    }

    public static boolean G_564_y(r_4811_B entity, MultiBooleanSetting settings) {
        return entity instanceof Z_530_i && !(entity instanceof Animal) && !(entity instanceof L_2225_p) && settings.J_1907_R("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432") != false;
    }

    public static boolean J_1907_R(N_4263_v entity, MultiBooleanSetting settings) {
        return entity == MinecraftAccess.c_3005_b.Y_259_p && settings.J_1907_R("\u0421\u0435\u0431\u044f") != false && !MinecraftAccess.c_3005_b.P_4830_p.P_4830_p().n_1700_B();
    }

    public static boolean R_4764_Y(N_4263_v entity, MultiBooleanSetting settings) {
        return entity instanceof n_1494_c && settings.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") != false;
    }

    public static boolean G_564_y(N_4263_v entity, MultiBooleanSetting settings) {
        if (entity instanceof r_4811_B) {
            r_4811_B livingEntity = (r_4811_B)entity;
            return E_4612_l.J_1907_R(entity, settings) || E_4612_l.n_1700_B(entity, settings, true) || E_4612_l.n_1700_B(livingEntity, settings) || E_4612_l.J_1907_R(livingEntity, settings) || E_4612_l.G_564_y(livingEntity, settings);
        }
        return E_4612_l.R_4764_Y(entity, settings);
    }
}



