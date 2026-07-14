/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.data;

import java.util.Optional;
import java.util.stream.IntStream;
import lightning.product.g_2336_b;
import net.minecraft.data.T_2506_i;
import net.minecraft.data.Y_1740_V;

public class G_624_v {
    public static final Y_1740_V n_1700_B = net.minecraft.data.G_624_v.n_1700_B("cube", net.minecraft.data.T_2506_i.R_4764_Y, net.minecraft.data.T_2506_i.s_956_w, net.minecraft.data.T_2506_i.u_2550_I, net.minecraft.data.T_2506_i.M_588_G, net.minecraft.data.T_2506_i.P_4830_p, net.minecraft.data.T_2506_i.h_1847_R, net.minecraft.data.T_2506_i.Q_4569_t);
    public static final Y_1740_V J_1907_R = net.minecraft.data.G_624_v.n_1700_B("cube_directional", net.minecraft.data.T_2506_i.R_4764_Y, net.minecraft.data.T_2506_i.s_956_w, net.minecraft.data.T_2506_i.u_2550_I, net.minecraft.data.T_2506_i.M_588_G, net.minecraft.data.T_2506_i.P_4830_p, net.minecraft.data.T_2506_i.h_1847_R, net.minecraft.data.T_2506_i.Q_4569_t);
    public static final Y_1740_V R_4764_Y = net.minecraft.data.G_624_v.n_1700_B("cube_all", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V G_564_y = net.minecraft.data.G_624_v.n_1700_B("cube_mirrored_all", "_mirrored", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V P_1922_E = net.minecraft.data.G_624_v.n_1700_B("cube_column", net.minecraft.data.T_2506_i.G_564_y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V u_1723_Y = net.minecraft.data.G_624_v.n_1700_B("cube_column_horizontal", "_horizontal", net.minecraft.data.T_2506_i.G_564_y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V v_4262_N = net.minecraft.data.G_624_v.n_1700_B("cube_top", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V w_1484_f = net.minecraft.data.G_624_v.n_1700_B("cube_bottom_top", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V t_148_a = net.minecraft.data.G_624_v.n_1700_B("orientable", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.v_4262_N, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V s_956_w = net.minecraft.data.G_624_v.n_1700_B("orientable_with_bottom", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.t_148_a, net.minecraft.data.T_2506_i.v_4262_N);
    public static final Y_1740_V u_2550_I = net.minecraft.data.G_624_v.n_1700_B("orientable_vertical", "_vertical", net.minecraft.data.T_2506_i.v_4262_N, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V M_588_G = net.minecraft.data.G_624_v.n_1700_B("button", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V P_4830_p = net.minecraft.data.G_624_v.n_1700_B("button_pressed", "_pressed", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V h_1847_R = net.minecraft.data.G_624_v.n_1700_B("button_inventory", "_inventory", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V Q_4569_t = net.minecraft.data.G_624_v.n_1700_B("door_bottom", "_bottom", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E);
    public static final Y_1740_V M_182_A = net.minecraft.data.G_624_v.n_1700_B("door_bottom_rh", "_bottom_hinge", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E);
    public static final Y_1740_V t_1786_h = net.minecraft.data.G_624_v.n_1700_B("door_top", "_top", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E);
    public static final Y_1740_V multiplayerClientSuggestionProvider = net.minecraft.data.G_624_v.n_1700_B("door_top_rh", "_top_hinge", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.P_1922_E);
    public static final Y_1740_V w_1457_N = net.minecraft.data.G_624_v.n_1700_B("fence_post", "_post", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V Y_601_j = net.minecraft.data.G_624_v.n_1700_B("fence_side", "_side", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V Y_259_p = net.minecraft.data.G_624_v.n_1700_B("fence_inventory", "_inventory", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V Q_2552_b = net.minecraft.data.G_624_v.n_1700_B("template_wall_post", "_post", net.minecraft.data.T_2506_i.multiplayerClientSuggestionProvider);
    public static final Y_1740_V C_2741_M = net.minecraft.data.G_624_v.n_1700_B("template_wall_side", "_side", net.minecraft.data.T_2506_i.multiplayerClientSuggestionProvider);
    public static final Y_1740_V k_2293_S = net.minecraft.data.G_624_v.n_1700_B("template_wall_side_tall", "_side_tall", net.minecraft.data.T_2506_i.multiplayerClientSuggestionProvider);
    public static final Y_1740_V q_2307_F = net.minecraft.data.G_624_v.n_1700_B("wall_inventory", "_inventory", net.minecraft.data.T_2506_i.multiplayerClientSuggestionProvider);
    public static final Y_1740_V Z_875_P = net.minecraft.data.G_624_v.n_1700_B("template_fence_gate", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V c_3005_b = net.minecraft.data.G_624_v.n_1700_B("template_fence_gate_open", "_open", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V H_2857_Y = net.minecraft.data.G_624_v.n_1700_B("template_fence_gate_wall", "_wall", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V A_4115_X = net.minecraft.data.G_624_v.n_1700_B("template_fence_gate_wall_open", "_wall_open", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V Y_1740_V = net.minecraft.data.G_624_v.n_1700_B("pressure_plate_up", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V t_4043_B = net.minecraft.data.G_624_v.n_1700_B("pressure_plate_down", "_down", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V x_607_J = net.minecraft.data.G_624_v.n_1700_B(net.minecraft.data.T_2506_i.R_4764_Y);
    public static final Y_1740_V e_4240_b = net.minecraft.data.G_624_v.n_1700_B("slab", net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V n_3318_d = net.minecraft.data.G_624_v.n_1700_B("slab_top", "_top", net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V d_2427_y = net.minecraft.data.G_624_v.n_1700_B("leaves", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V z_1737_N = net.minecraft.data.G_624_v.n_1700_B("stairs", net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V v_4276_D = net.minecraft.data.G_624_v.n_1700_B("inner_stairs", "_inner", net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V d_2461_k = net.minecraft.data.G_624_v.n_1700_B("outer_stairs", "_outer", net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V G_624_v = net.minecraft.data.G_624_v.n_1700_B("template_trapdoor_top", "_top", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V T_2506_i = net.minecraft.data.G_624_v.n_1700_B("template_trapdoor_bottom", "_bottom", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V q_4610_l = net.minecraft.data.G_624_v.n_1700_B("template_trapdoor_open", "_open", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V z_4693_k = net.minecraft.data.G_624_v.n_1700_B("template_orientable_trapdoor_top", "_top", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V g_221_o = net.minecraft.data.G_624_v.n_1700_B("template_orientable_trapdoor_bottom", "_bottom", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V e_2887_G = net.minecraft.data.G_624_v.n_1700_B("template_orientable_trapdoor_open", "_open", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V B_1668_F = net.minecraft.data.G_624_v.n_1700_B("cross", net.minecraft.data.T_2506_i.M_182_A);
    public static final Y_1740_V g_164_R = net.minecraft.data.G_624_v.n_1700_B("tinted_cross", net.minecraft.data.T_2506_i.M_182_A);
    public static final Y_1740_V X_933_l = net.minecraft.data.G_624_v.n_1700_B("flower_pot_cross", net.minecraft.data.T_2506_i.t_1786_h);
    public static final Y_1740_V Z_976_R = net.minecraft.data.G_624_v.n_1700_B("tinted_flower_pot_cross", net.minecraft.data.T_2506_i.t_1786_h);
    public static final Y_1740_V H_1990_U = net.minecraft.data.G_624_v.n_1700_B("rail_flat", net.minecraft.data.T_2506_i.w_1457_N);
    public static final Y_1740_V N_2525_X = net.minecraft.data.G_624_v.n_1700_B("rail_curved", "_corner", net.minecraft.data.T_2506_i.w_1457_N);
    public static final Y_1740_V c_4037_x = net.minecraft.data.G_624_v.n_1700_B("template_rail_raised_ne", "_raised_ne", net.minecraft.data.T_2506_i.w_1457_N);
    public static final Y_1740_V g_2268_R = net.minecraft.data.G_624_v.n_1700_B("template_rail_raised_sw", "_raised_sw", net.minecraft.data.T_2506_i.w_1457_N);
    public static final Y_1740_V T_3594_S = net.minecraft.data.G_624_v.n_1700_B("carpet", net.minecraft.data.T_2506_i.Y_601_j);
    public static final Y_1740_V D_4792_h = net.minecraft.data.G_624_v.n_1700_B("coral_fan", net.minecraft.data.T_2506_i.k_2293_S);
    public static final Y_1740_V s_2632_s = net.minecraft.data.G_624_v.n_1700_B("coral_wall_fan", net.minecraft.data.T_2506_i.k_2293_S);
    public static final Y_1740_V l_1233_K = net.minecraft.data.G_624_v.n_1700_B("template_glazed_terracotta", net.minecraft.data.T_2506_i.Y_259_p);
    public static final Y_1740_V z_1333_t = net.minecraft.data.G_624_v.n_1700_B("template_chorus_flower", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V O_508_d = net.minecraft.data.G_624_v.n_1700_B("template_daylight_detector", net.minecraft.data.T_2506_i.u_1723_Y, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V r_715_M = net.minecraft.data.G_624_v.n_1700_B("template_glass_pane_noside", "_noside", net.minecraft.data.T_2506_i.Q_2552_b);
    public static final Y_1740_V A_1038_p = net.minecraft.data.G_624_v.n_1700_B("template_glass_pane_noside_alt", "_noside_alt", net.minecraft.data.T_2506_i.Q_2552_b);
    public static final Y_1740_V i_1637_u = net.minecraft.data.G_624_v.n_1700_B("template_glass_pane_post", "_post", net.minecraft.data.T_2506_i.Q_2552_b, net.minecraft.data.T_2506_i.C_2741_M);
    public static final Y_1740_V Ping = net.minecraft.data.G_624_v.n_1700_B("template_glass_pane_side", "_side", net.minecraft.data.T_2506_i.Q_2552_b, net.minecraft.data.T_2506_i.C_2741_M);
    public static final Y_1740_V p_178_J = net.minecraft.data.G_624_v.n_1700_B("template_glass_pane_side_alt", "_side_alt", net.minecraft.data.T_2506_i.Q_2552_b, net.minecraft.data.T_2506_i.C_2741_M);
    public static final Y_1740_V RealmsClientConfig = net.minecraft.data.G_624_v.n_1700_B("template_command_block", net.minecraft.data.T_2506_i.v_4262_N, net.minecraft.data.T_2506_i.w_1484_f, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V f_4016_n = net.minecraft.data.G_624_v.n_1700_B("template_anvil", net.minecraft.data.T_2506_i.u_1723_Y);
    public static final Y_1740_V[] j_276_v = (Y_1740_V[])IntStream.range(0, 8).mapToObj(growthStage -> net.minecraft.data.G_624_v.n_1700_B("stem_growth" + growthStage, "_stage" + growthStage, net.minecraft.data.T_2506_i.q_2307_F)).toArray(Y_1740_V[]::new);
    public static final Y_1740_V UploadStatus = net.minecraft.data.G_624_v.n_1700_B("stem_fruit", net.minecraft.data.T_2506_i.q_2307_F, net.minecraft.data.T_2506_i.Z_875_P);
    public static final Y_1740_V e_1992_r = net.minecraft.data.G_624_v.n_1700_B("crop", net.minecraft.data.T_2506_i.c_3005_b);
    public static final Y_1740_V D_60_a = net.minecraft.data.G_624_v.n_1700_B("template_farmland", net.minecraft.data.T_2506_i.H_2857_Y, net.minecraft.data.T_2506_i.u_1723_Y);
    public static final Y_1740_V k_3961_g = net.minecraft.data.G_624_v.n_1700_B("template_fire_floor", net.minecraft.data.T_2506_i.A_4115_X);
    public static final Y_1740_V Ops = net.minecraft.data.G_624_v.n_1700_B("template_fire_side", net.minecraft.data.T_2506_i.A_4115_X);
    public static final Y_1740_V h_4320_q = net.minecraft.data.G_624_v.n_1700_B("template_fire_side_alt", net.minecraft.data.T_2506_i.A_4115_X);
    public static final Y_1740_V t_4219_U = net.minecraft.data.G_624_v.n_1700_B("template_fire_up", net.minecraft.data.T_2506_i.A_4115_X);
    public static final Y_1740_V V_1446_Y = net.minecraft.data.G_624_v.n_1700_B("template_fire_up_alt", net.minecraft.data.T_2506_i.A_4115_X);
    public static final Y_1740_V PlayerInfo = net.minecraft.data.G_624_v.n_1700_B("template_campfire", net.minecraft.data.T_2506_i.A_4115_X, net.minecraft.data.T_2506_i.d_2427_y);
    public static final Y_1740_V V_1225_t = net.minecraft.data.G_624_v.n_1700_B("template_lantern", net.minecraft.data.T_2506_i.Y_1740_V);
    public static final Y_1740_V U_1241_n = net.minecraft.data.G_624_v.n_1700_B("template_hanging_lantern", "_hanging", net.minecraft.data.T_2506_i.Y_1740_V);
    public static final Y_1740_V q_1982_R = net.minecraft.data.G_624_v.n_1700_B("template_torch", net.minecraft.data.T_2506_i.e_4240_b);
    public static final Y_1740_V dtoRealmsServerAddress = net.minecraft.data.G_624_v.n_1700_B("template_torch_wall", net.minecraft.data.T_2506_i.e_4240_b);
    public static final Y_1740_V w_612_n = net.minecraft.data.G_624_v.n_1700_B("template_piston", net.minecraft.data.T_2506_i.t_4043_B, net.minecraft.data.T_2506_i.P_1922_E, net.minecraft.data.T_2506_i.t_148_a);
    public static final Y_1740_V RealmsServerPing = net.minecraft.data.G_624_v.n_1700_B("template_piston_head", net.minecraft.data.T_2506_i.t_4043_B, net.minecraft.data.T_2506_i.t_148_a, net.minecraft.data.T_2506_i.x_607_J);
    public static final Y_1740_V j_1564_a = net.minecraft.data.G_624_v.n_1700_B("template_piston_head_short", net.minecraft.data.T_2506_i.t_4043_B, net.minecraft.data.T_2506_i.t_148_a, net.minecraft.data.T_2506_i.x_607_J);
    public static final Y_1740_V M_1641_O = net.minecraft.data.G_624_v.n_1700_B("template_seagrass", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V RealmsWorldOptions = net.minecraft.data.G_624_v.n_1700_B("template_turtle_egg", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V RealmsWorldResetDto = net.minecraft.data.G_624_v.n_1700_B("template_two_turtle_eggs", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V RegionPingResult = net.minecraft.data.G_624_v.n_1700_B("template_three_turtle_eggs", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V H_1083_k = net.minecraft.data.G_624_v.n_1700_B("template_four_turtle_eggs", net.minecraft.data.T_2506_i.n_1700_B);
    public static final Y_1740_V R_3908_n = net.minecraft.data.G_624_v.n_1700_B("template_single_face", net.minecraft.data.T_2506_i.J_1907_R);
    public static final Y_1740_V ValueObject = net.minecraft.data.G_624_v.J_1907_R("generated", net.minecraft.data.T_2506_i.n_3318_d);
    public static final Y_1740_V F_1410_V = net.minecraft.data.G_624_v.J_1907_R("handheld", net.minecraft.data.T_2506_i.n_3318_d);
    public static final Y_1740_V S_4022_R = net.minecraft.data.G_624_v.J_1907_R("handheld_rod", net.minecraft.data.T_2506_i.n_3318_d);
    public static final Y_1740_V l_4537_E = net.minecraft.data.G_624_v.J_1907_R("template_shulker_box", net.minecraft.data.T_2506_i.R_4764_Y);
    public static final Y_1740_V F_2624_D = net.minecraft.data.G_624_v.J_1907_R("template_bed", net.minecraft.data.T_2506_i.R_4764_Y);
    public static final Y_1740_V RealmsDefaultUncaughtExceptionHandler = net.minecraft.data.G_624_v.J_1907_R("template_banner", new T_2506_i[0]);
    public static final Y_1740_V y_1700_S = net.minecraft.data.G_624_v.J_1907_R("template_skull", new T_2506_i[0]);

    private static Y_1740_V n_1700_B(T_2506_i ... textureAliases) {
        return new Y_1740_V(Optional.empty(), Optional.empty(), textureAliases);
    }

    private static Y_1740_V n_1700_B(String name, T_2506_i ... textureAliases) {
        return new Y_1740_V(Optional.of(new g_2336_b("minecraft", "block/" + name)), Optional.empty(), textureAliases);
    }

    private static Y_1740_V J_1907_R(String name, T_2506_i ... textureAliases) {
        return new Y_1740_V(Optional.of(new g_2336_b("minecraft", "item/" + name)), Optional.empty(), textureAliases);
    }

    private static Y_1740_V n_1700_B(String name, String append, T_2506_i ... textureAliases) {
        return new Y_1740_V(Optional.of(new g_2336_b("minecraft", "block/" + name)), Optional.of(append), textureAliases);
    }
}


