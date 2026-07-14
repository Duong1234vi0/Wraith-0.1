/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.X_585_L;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.q_3277_O;
import lightning.product.t_5_h;

public class Stats {
    public static final q_3277_O<T_2915_h> n_1700_B = Stats.n_1700_B("mined", V_3137_a.q_4610_l);
    public static final q_3277_O<q_1613_l> J_1907_R = Stats.n_1700_B("crafted", V_3137_a.e_2887_G);
    public static final q_3277_O<q_1613_l> R_4764_Y = Stats.n_1700_B("used", V_3137_a.e_2887_G);
    public static final q_3277_O<q_1613_l> G_564_y = Stats.n_1700_B("broken", V_3137_a.e_2887_G);
    public static final q_3277_O<q_1613_l> P_1922_E = Stats.n_1700_B("picked_up", V_3137_a.e_2887_G);
    public static final q_3277_O<q_1613_l> u_1723_Y = Stats.n_1700_B("dropped", V_3137_a.e_2887_G);
    public static final q_3277_O<t_5_h<?>> v_4262_N = Stats.n_1700_B("killed", V_3137_a.g_221_o);
    public static final q_3277_O<t_5_h<?>> w_1484_f = Stats.n_1700_B("killed_by", V_3137_a.g_221_o);
    public static final q_3277_O<g_2336_b> t_148_a = Stats.n_1700_B("custom", V_3137_a.H_1990_U);
    public static final g_2336_b s_956_w = Stats.n_1700_B("leave_game", X_585_L.J_1907_R);
    public static final g_2336_b u_2550_I = Stats.n_1700_B("play_one_minute", X_585_L.P_1922_E);
    public static final g_2336_b M_588_G = Stats.n_1700_B("time_since_death", X_585_L.P_1922_E);
    public static final g_2336_b P_4830_p = Stats.n_1700_B("time_since_rest", X_585_L.P_1922_E);
    public static final g_2336_b h_1847_R = Stats.n_1700_B("sneak_time", X_585_L.P_1922_E);
    public static final g_2336_b Q_4569_t = Stats.n_1700_B("walk_one_cm", X_585_L.G_564_y);
    public static final g_2336_b M_182_A = Stats.n_1700_B("crouch_one_cm", X_585_L.G_564_y);
    public static final g_2336_b t_1786_h = Stats.n_1700_B("sprint_one_cm", X_585_L.G_564_y);
    public static final g_2336_b multiplayerClientSuggestionProvider = Stats.n_1700_B("walk_on_water_one_cm", X_585_L.G_564_y);
    public static final g_2336_b w_1457_N = Stats.n_1700_B("fall_one_cm", X_585_L.G_564_y);
    public static final g_2336_b Y_601_j = Stats.n_1700_B("climb_one_cm", X_585_L.G_564_y);
    public static final g_2336_b Y_259_p = Stats.n_1700_B("fly_one_cm", X_585_L.G_564_y);
    public static final g_2336_b Q_2552_b = Stats.n_1700_B("walk_under_water_one_cm", X_585_L.G_564_y);
    public static final g_2336_b C_2741_M = Stats.n_1700_B("minecart_one_cm", X_585_L.G_564_y);
    public static final g_2336_b k_2293_S = Stats.n_1700_B("boat_one_cm", X_585_L.G_564_y);
    public static final g_2336_b q_2307_F = Stats.n_1700_B("pig_one_cm", X_585_L.G_564_y);
    public static final g_2336_b Z_875_P = Stats.n_1700_B("horse_one_cm", X_585_L.G_564_y);
    public static final g_2336_b c_3005_b = Stats.n_1700_B("aviate_one_cm", X_585_L.G_564_y);
    public static final g_2336_b H_2857_Y = Stats.n_1700_B("swim_one_cm", X_585_L.G_564_y);
    public static final g_2336_b A_4115_X = Stats.n_1700_B("strider_one_cm", X_585_L.G_564_y);
    public static final g_2336_b Y_1740_V = Stats.n_1700_B("jump", X_585_L.J_1907_R);
    public static final g_2336_b t_4043_B = Stats.n_1700_B("drop", X_585_L.J_1907_R);
    public static final g_2336_b x_607_J = Stats.n_1700_B("damage_dealt", X_585_L.R_4764_Y);
    public static final g_2336_b e_4240_b = Stats.n_1700_B("damage_dealt_absorbed", X_585_L.R_4764_Y);
    public static final g_2336_b n_3318_d = Stats.n_1700_B("damage_dealt_resisted", X_585_L.R_4764_Y);
    public static final g_2336_b d_2427_y = Stats.n_1700_B("damage_taken", X_585_L.R_4764_Y);
    public static final g_2336_b z_1737_N = Stats.n_1700_B("damage_blocked_by_shield", X_585_L.R_4764_Y);
    public static final g_2336_b v_4276_D = Stats.n_1700_B("damage_absorbed", X_585_L.R_4764_Y);
    public static final g_2336_b d_2461_k = Stats.n_1700_B("damage_resisted", X_585_L.R_4764_Y);
    public static final g_2336_b G_624_v = Stats.n_1700_B("deaths", X_585_L.J_1907_R);
    public static final g_2336_b T_2506_i = Stats.n_1700_B("mob_kills", X_585_L.J_1907_R);
    public static final g_2336_b q_4610_l = Stats.n_1700_B("animals_bred", X_585_L.J_1907_R);
    public static final g_2336_b z_4693_k = Stats.n_1700_B("player_kills", X_585_L.J_1907_R);
    public static final g_2336_b g_221_o = Stats.n_1700_B("fish_caught", X_585_L.J_1907_R);
    public static final g_2336_b e_2887_G = Stats.n_1700_B("talked_to_villager", X_585_L.J_1907_R);
    public static final g_2336_b B_1668_F = Stats.n_1700_B("traded_with_villager", X_585_L.J_1907_R);
    public static final g_2336_b g_164_R = Stats.n_1700_B("eat_cake_slice", X_585_L.J_1907_R);
    public static final g_2336_b X_933_l = Stats.n_1700_B("fill_cauldron", X_585_L.J_1907_R);
    public static final g_2336_b Z_976_R = Stats.n_1700_B("use_cauldron", X_585_L.J_1907_R);
    public static final g_2336_b H_1990_U = Stats.n_1700_B("clean_armor", X_585_L.J_1907_R);
    public static final g_2336_b N_2525_X = Stats.n_1700_B("clean_banner", X_585_L.J_1907_R);
    public static final g_2336_b c_4037_x = Stats.n_1700_B("clean_shulker_box", X_585_L.J_1907_R);
    public static final g_2336_b g_2268_R = Stats.n_1700_B("interact_with_brewingstand", X_585_L.J_1907_R);
    public static final g_2336_b T_3594_S = Stats.n_1700_B("interact_with_beacon", X_585_L.J_1907_R);
    public static final g_2336_b D_4792_h = Stats.n_1700_B("inspect_dropper", X_585_L.J_1907_R);
    public static final g_2336_b s_2632_s = Stats.n_1700_B("inspect_hopper", X_585_L.J_1907_R);
    public static final g_2336_b l_1233_K = Stats.n_1700_B("inspect_dispenser", X_585_L.J_1907_R);
    public static final g_2336_b z_1333_t = Stats.n_1700_B("play_noteblock", X_585_L.J_1907_R);
    public static final g_2336_b O_508_d = Stats.n_1700_B("tune_noteblock", X_585_L.J_1907_R);
    public static final g_2336_b r_715_M = Stats.n_1700_B("pot_flower", X_585_L.J_1907_R);
    public static final g_2336_b A_1038_p = Stats.n_1700_B("trigger_trapped_chest", X_585_L.J_1907_R);
    public static final g_2336_b i_1637_u = Stats.n_1700_B("open_enderchest", X_585_L.J_1907_R);
    public static final g_2336_b Ping = Stats.n_1700_B("enchant_item", X_585_L.J_1907_R);
    public static final g_2336_b p_178_J = Stats.n_1700_B("play_record", X_585_L.J_1907_R);
    public static final g_2336_b RealmsClientConfig = Stats.n_1700_B("interact_with_furnace", X_585_L.J_1907_R);
    public static final g_2336_b f_4016_n = Stats.n_1700_B("interact_with_crafting_table", X_585_L.J_1907_R);
    public static final g_2336_b j_276_v = Stats.n_1700_B("open_chest", X_585_L.J_1907_R);
    public static final g_2336_b UploadStatus = Stats.n_1700_B("sleep_in_bed", X_585_L.J_1907_R);
    public static final g_2336_b e_1992_r = Stats.n_1700_B("open_shulker_box", X_585_L.J_1907_R);
    public static final g_2336_b D_60_a = Stats.n_1700_B("open_barrel", X_585_L.J_1907_R);
    public static final g_2336_b k_3961_g = Stats.n_1700_B("interact_with_blast_furnace", X_585_L.J_1907_R);
    public static final g_2336_b Ops = Stats.n_1700_B("interact_with_smoker", X_585_L.J_1907_R);
    public static final g_2336_b h_4320_q = Stats.n_1700_B("interact_with_lectern", X_585_L.J_1907_R);
    public static final g_2336_b t_4219_U = Stats.n_1700_B("interact_with_campfire", X_585_L.J_1907_R);
    public static final g_2336_b V_1446_Y = Stats.n_1700_B("interact_with_cartography_table", X_585_L.J_1907_R);
    public static final g_2336_b PlayerInfo = Stats.n_1700_B("interact_with_loom", X_585_L.J_1907_R);
    public static final g_2336_b V_1225_t = Stats.n_1700_B("interact_with_stonecutter", X_585_L.J_1907_R);
    public static final g_2336_b U_1241_n = Stats.n_1700_B("bell_ring", X_585_L.J_1907_R);
    public static final g_2336_b q_1982_R = Stats.n_1700_B("raid_trigger", X_585_L.J_1907_R);
    public static final g_2336_b dtoRealmsServerAddress = Stats.n_1700_B("raid_win", X_585_L.J_1907_R);
    public static final g_2336_b w_612_n = Stats.n_1700_B("interact_with_anvil", X_585_L.J_1907_R);
    public static final g_2336_b RealmsServerPing = Stats.n_1700_B("interact_with_grindstone", X_585_L.J_1907_R);
    public static final g_2336_b j_1564_a = Stats.n_1700_B("target_hit", X_585_L.J_1907_R);
    public static final g_2336_b M_1641_O = Stats.n_1700_B("interact_with_smithing_table", X_585_L.J_1907_R);

    private static g_2336_b n_1700_B(String key, X_585_L formatter) {
        g_2336_b resourcelocation = new g_2336_b(key);
        V_3137_a.n_1700_B(V_3137_a.H_1990_U, key, resourcelocation);
        t_148_a.n_1700_B(resourcelocation, formatter);
        return resourcelocation;
    }

    private static <T> q_3277_O<T> n_1700_B(String key, V_3137_a<T> registry) {
        return V_3137_a.n_1700_B(V_3137_a.z_1333_t, key, new q_3277_O<T>(registry));
    }
}


