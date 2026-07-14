/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MobEffects;
import lightning.product.V_3137_a;
import lightning.product.k_2610_C;
import lightning.product.y_528_b;

public class Potions {
    public static final y_528_b n_1700_B = Potions.n_1700_B("empty", new y_528_b(new k_2610_C[0]));
    public static final y_528_b J_1907_R = Potions.n_1700_B("water", new y_528_b(new k_2610_C[0]));
    public static final y_528_b R_4764_Y = Potions.n_1700_B("mundane", new y_528_b(new k_2610_C[0]));
    public static final y_528_b G_564_y = Potions.n_1700_B("thick", new y_528_b(new k_2610_C[0]));
    public static final y_528_b P_1922_E = Potions.n_1700_B("awkward", new y_528_b(new k_2610_C[0]));
    public static final y_528_b u_1723_Y = Potions.n_1700_B("night_vision", new y_528_b(new k_2610_C(MobEffects.M_182_A, 3600)));
    public static final y_528_b v_4262_N = Potions.n_1700_B("long_night_vision", new y_528_b("night_vision", new k_2610_C(MobEffects.M_182_A, 9600)));
    public static final y_528_b w_1484_f = Potions.n_1700_B("invisibility", new y_528_b(new k_2610_C(MobEffects.h_1847_R, 3600)));
    public static final y_528_b t_148_a = Potions.n_1700_B("long_invisibility", new y_528_b("invisibility", new k_2610_C(MobEffects.h_1847_R, 9600)));
    public static final y_528_b s_956_w = Potions.n_1700_B("leaping", new y_528_b(new k_2610_C(MobEffects.w_1484_f, 3600)));
    public static final y_528_b u_2550_I = Potions.n_1700_B("long_leaping", new y_528_b("leaping", new k_2610_C(MobEffects.w_1484_f, 9600)));
    public static final y_528_b M_588_G = Potions.n_1700_B("strong_leaping", new y_528_b("leaping", new k_2610_C(MobEffects.w_1484_f, 1800, 1)));
    public static final y_528_b P_4830_p = Potions.n_1700_B("fire_resistance", new y_528_b(new k_2610_C(MobEffects.M_588_G, 3600)));
    public static final y_528_b h_1847_R = Potions.n_1700_B("long_fire_resistance", new y_528_b("fire_resistance", new k_2610_C(MobEffects.M_588_G, 9600)));
    public static final y_528_b Q_4569_t = Potions.n_1700_B("swiftness", new y_528_b(new k_2610_C(MobEffects.n_1700_B, 3600)));
    public static final y_528_b M_182_A = Potions.n_1700_B("long_swiftness", new y_528_b("swiftness", new k_2610_C(MobEffects.n_1700_B, 9600)));
    public static final y_528_b t_1786_h = Potions.n_1700_B("strong_swiftness", new y_528_b("swiftness", new k_2610_C(MobEffects.n_1700_B, 1800, 1)));
    public static final y_528_b multiplayerClientSuggestionProvider = Potions.n_1700_B("slowness", new y_528_b(new k_2610_C(MobEffects.J_1907_R, 1800)));
    public static final y_528_b w_1457_N = Potions.n_1700_B("long_slowness", new y_528_b("slowness", new k_2610_C(MobEffects.J_1907_R, 4800)));
    public static final y_528_b Y_601_j = Potions.n_1700_B("strong_slowness", new y_528_b("slowness", new k_2610_C(MobEffects.J_1907_R, 400, 3)));
    public static final y_528_b Y_259_p = Potions.n_1700_B("turtle_master", new y_528_b("turtle_master", new k_2610_C(MobEffects.J_1907_R, 400, 3), new k_2610_C(MobEffects.u_2550_I, 400, 2)));
    public static final y_528_b Q_2552_b = Potions.n_1700_B("long_turtle_master", new y_528_b("turtle_master", new k_2610_C(MobEffects.J_1907_R, 800, 3), new k_2610_C(MobEffects.u_2550_I, 800, 2)));
    public static final y_528_b C_2741_M = Potions.n_1700_B("strong_turtle_master", new y_528_b("turtle_master", new k_2610_C(MobEffects.J_1907_R, 400, 5), new k_2610_C(MobEffects.u_2550_I, 400, 3)));
    public static final y_528_b k_2293_S = Potions.n_1700_B("water_breathing", new y_528_b(new k_2610_C(MobEffects.P_4830_p, 3600)));
    public static final y_528_b q_2307_F = Potions.n_1700_B("long_water_breathing", new y_528_b("water_breathing", new k_2610_C(MobEffects.P_4830_p, 9600)));
    public static final y_528_b Z_875_P = Potions.n_1700_B("healing", new y_528_b(new k_2610_C(MobEffects.u_1723_Y, 1)));
    public static final y_528_b c_3005_b = Potions.n_1700_B("strong_healing", new y_528_b("healing", new k_2610_C(MobEffects.u_1723_Y, 1, 1)));
    public static final y_528_b H_2857_Y = Potions.n_1700_B("harming", new y_528_b(new k_2610_C(MobEffects.v_4262_N, 1)));
    public static final y_528_b A_4115_X = Potions.n_1700_B("strong_harming", new y_528_b("harming", new k_2610_C(MobEffects.v_4262_N, 1, 1)));
    public static final y_528_b Y_1740_V = Potions.n_1700_B("poison", new y_528_b(new k_2610_C(MobEffects.w_1457_N, 900)));
    public static final y_528_b t_4043_B = Potions.n_1700_B("long_poison", new y_528_b("poison", new k_2610_C(MobEffects.w_1457_N, 1800)));
    public static final y_528_b x_607_J = Potions.n_1700_B("strong_poison", new y_528_b("poison", new k_2610_C(MobEffects.w_1457_N, 432, 1)));
    public static final y_528_b e_4240_b = Potions.n_1700_B("regeneration", new y_528_b(new k_2610_C(MobEffects.s_956_w, 900)));
    public static final y_528_b n_3318_d = Potions.n_1700_B("long_regeneration", new y_528_b("regeneration", new k_2610_C(MobEffects.s_956_w, 1800)));
    public static final y_528_b d_2427_y = Potions.n_1700_B("strong_regeneration", new y_528_b("regeneration", new k_2610_C(MobEffects.s_956_w, 450, 1)));
    public static final y_528_b z_1737_N = Potions.n_1700_B("strength", new y_528_b(new k_2610_C(MobEffects.P_1922_E, 3600)));
    public static final y_528_b v_4276_D = Potions.n_1700_B("long_strength", new y_528_b("strength", new k_2610_C(MobEffects.P_1922_E, 9600)));
    public static final y_528_b d_2461_k = Potions.n_1700_B("strong_strength", new y_528_b("strength", new k_2610_C(MobEffects.P_1922_E, 1800, 1)));
    public static final y_528_b G_624_v = Potions.n_1700_B("weakness", new y_528_b(new k_2610_C(MobEffects.multiplayerClientSuggestionProvider, 1800)));
    public static final y_528_b T_2506_i = Potions.n_1700_B("long_weakness", new y_528_b("weakness", new k_2610_C(MobEffects.multiplayerClientSuggestionProvider, 4800)));
    public static final y_528_b q_4610_l = Potions.n_1700_B("luck", new y_528_b("luck", new k_2610_C(MobEffects.Z_875_P, 6000)));
    public static final y_528_b z_4693_k = Potions.n_1700_B("slow_falling", new y_528_b(new k_2610_C(MobEffects.H_2857_Y, 1800)));
    public static final y_528_b g_221_o = Potions.n_1700_B("long_slow_falling", new y_528_b("slow_falling", new k_2610_C(MobEffects.H_2857_Y, 4800)));

    private static y_528_b n_1700_B(String key, y_528_b potionIn) {
        return V_3137_a.n_1700_B(V_3137_a.B_1668_F, key, potionIn);
    }
}


