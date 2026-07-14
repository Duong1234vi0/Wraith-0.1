/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.Attributes;
import lightning.product.AttackDamageMobEffect;
import lightning.product.R_2450_T;
import lightning.product.AbsoptionMobEffect;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;
import lightning.product.InstantenousMobEffect;
import lightning.product.e_3591_l;
import lightning.product.g_422_i;
import lightning.product.HealthBoostMobEffect;
import lightning.product.j_956_y;
import lightning.product.r_4811_B;

public class MobEffects {
    public static final g_422_i n_1700_B = MobEffects.n_1700_B(1, "speed", new g_422_i(j_956_y.n_1700_B, 8171462).n_1700_B(Attributes.G_564_y, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2f, U_1880_G.n_1700_B.R_4764_Y));
    public static final g_422_i J_1907_R = MobEffects.n_1700_B(2, "slowness", new g_422_i(j_956_y.J_1907_R, 5926017).n_1700_B(Attributes.G_564_y, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15f, U_1880_G.n_1700_B.R_4764_Y));
    public static final g_422_i R_4764_Y = MobEffects.n_1700_B(3, "haste", new g_422_i(j_956_y.n_1700_B, 14270531).n_1700_B(Attributes.w_1484_f, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.1f, U_1880_G.n_1700_B.R_4764_Y));
    public static final g_422_i G_564_y = MobEffects.n_1700_B(4, "mining_fatigue", new g_422_i(j_956_y.J_1907_R, 4866583).n_1700_B(Attributes.w_1484_f, "55FCED67-E92A-486E-9800-B47F202C4386", -0.1f, U_1880_G.n_1700_B.R_4764_Y));
    public static final g_422_i P_1922_E = MobEffects.n_1700_B(5, "strength", new AttackDamageMobEffect(j_956_y.n_1700_B, 9643043, 3.0).n_1700_B(Attributes.u_1723_Y, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0, U_1880_G.n_1700_B.n_1700_B));
    public static final g_422_i u_1723_Y = MobEffects.n_1700_B(6, "instant_health", new InstantenousMobEffect(j_956_y.n_1700_B, 16262179));
    public static final g_422_i v_4262_N = MobEffects.n_1700_B(7, "instant_damage", new InstantenousMobEffect(j_956_y.J_1907_R, 4393481));
    public static final g_422_i w_1484_f = MobEffects.n_1700_B(8, "jump_boost", new g_422_i(j_956_y.n_1700_B, 2293580));
    public static final g_422_i t_148_a = MobEffects.n_1700_B(9, "nausea", new g_422_i(j_956_y.J_1907_R, 5578058));
    public static final g_422_i s_956_w = MobEffects.n_1700_B(10, "regeneration", new g_422_i(j_956_y.n_1700_B, 13458603));
    public static final g_422_i u_2550_I = MobEffects.n_1700_B(11, "resistance", new g_422_i(j_956_y.n_1700_B, 10044730));
    public static final g_422_i M_588_G = MobEffects.n_1700_B(12, "fire_resistance", new g_422_i(j_956_y.n_1700_B, 14981690));
    public static final g_422_i P_4830_p = MobEffects.n_1700_B(13, "water_breathing", new g_422_i(j_956_y.n_1700_B, 3035801));
    public static final g_422_i h_1847_R = MobEffects.n_1700_B(14, "invisibility", new g_422_i(j_956_y.n_1700_B, 8356754));
    public static final g_422_i Q_4569_t = MobEffects.n_1700_B(15, "blindness", new g_422_i(j_956_y.J_1907_R, 2039587));
    public static final g_422_i M_182_A = MobEffects.n_1700_B(16, "night_vision", new g_422_i(j_956_y.n_1700_B, 0x1F1FA1));
    public static final g_422_i t_1786_h = MobEffects.n_1700_B(17, "hunger", new g_422_i(j_956_y.J_1907_R, 5797459));
    public static final g_422_i multiplayerClientSuggestionProvider = MobEffects.n_1700_B(18, "weakness", new AttackDamageMobEffect(j_956_y.J_1907_R, 0x484D48, -4.0).n_1700_B(Attributes.u_1723_Y, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0, U_1880_G.n_1700_B.n_1700_B));
    public static final g_422_i w_1457_N = MobEffects.n_1700_B(19, "poison", new g_422_i(j_956_y.J_1907_R, 5149489));
    public static final g_422_i Y_601_j = MobEffects.n_1700_B(20, "wither", new g_422_i(j_956_y.J_1907_R, 3484199));
    public static final g_422_i Y_259_p = MobEffects.n_1700_B(21, "health_boost", new HealthBoostMobEffect(j_956_y.n_1700_B, 16284963).n_1700_B(Attributes.n_1700_B, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, U_1880_G.n_1700_B.n_1700_B));
    public static final g_422_i Q_2552_b = MobEffects.n_1700_B(22, "absorption", new AbsoptionMobEffect(j_956_y.n_1700_B, 0x2552A5));
    public static final g_422_i C_2741_M = MobEffects.n_1700_B(23, "saturation", new InstantenousMobEffect(j_956_y.n_1700_B, 16262179));
    public static final g_422_i k_2293_S = MobEffects.n_1700_B(24, "glowing", new g_422_i(j_956_y.R_4764_Y, 9740385));
    public static final g_422_i q_2307_F = MobEffects.n_1700_B(25, "levitation", new g_422_i(j_956_y.J_1907_R, 0xCEFFFF));
    public static final g_422_i Z_875_P = MobEffects.n_1700_B(26, "luck", new g_422_i(j_956_y.n_1700_B, 0x339900).n_1700_B(Attributes.u_2550_I, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0, U_1880_G.n_1700_B.n_1700_B));
    public static final g_422_i c_3005_b = MobEffects.n_1700_B(27, "unluck", new g_422_i(j_956_y.J_1907_R, 12624973).n_1700_B(Attributes.u_2550_I, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0, U_1880_G.n_1700_B.n_1700_B));
    public static final g_422_i H_2857_Y = MobEffects.n_1700_B(28, "slow_falling", new g_422_i(j_956_y.n_1700_B, 16773073));
    public static final g_422_i A_4115_X = MobEffects.n_1700_B(29, "conduit_power", new g_422_i(j_956_y.n_1700_B, 1950417));
    public static final g_422_i Y_1740_V = MobEffects.n_1700_B(30, "dolphins_grace", new g_422_i(j_956_y.n_1700_B, 8954814));
    public static final g_422_i t_4043_B = MobEffects.n_1700_B(31, "bad_omen", new g_422_i(j_956_y.R_4764_Y, 745784){

        @Override
        public boolean n_1700_B(int duration, int amplifier) {
            return true;
        }

        @Override
        public void n_1700_B(r_4811_B entityLivingBaseIn, int amplifier) {
            if (entityLivingBaseIn instanceof B_4088_l && !entityLivingBaseIn.d_2461_k()) {
                B_4088_l serverplayerentity = (B_4088_l)entityLivingBaseIn;
                e_3591_l serverworld = serverplayerentity.c_3005_b();
                if (serverworld.x_607_J() == R_2450_T.n_1700_B) {
                    return;
                }
                if (serverworld.q_2307_F(entityLivingBaseIn.b_2312_j())) {
                    serverworld.RealmsClientConfig().n_1700_B(serverplayerentity);
                }
            }
        }
    });
    public static final g_422_i x_607_J = MobEffects.n_1700_B(32, "hero_of_the_village", new g_422_i(j_956_y.n_1700_B, 0x44FF44));

    private static g_422_i n_1700_B(int id, String key, g_422_i effectIn) {
        return V_3137_a.n_1700_B(V_3137_a.T_2506_i, id, key, effectIn);
    }
}


