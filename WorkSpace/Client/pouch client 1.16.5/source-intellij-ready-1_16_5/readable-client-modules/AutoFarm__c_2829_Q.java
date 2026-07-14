/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.D_3030_E;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_103_K;
import lightning.product.F_1464_b;
import lightning.product.F_4355_q;
import lightning.product.G_3416_z;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.K_4074_S;
import lightning.product.L_1875_m;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.P_225_f;
import lightning.product.Q_112_C;
import lightning.product.R_1400_H;
import lightning.product.R_3414_E;
import lightning.product.S_3848_S;
import lightning.product.T_2915_h;
import lightning.product.U_2871_b;
import lightning.product.V_4557_X;
import lightning.product.V_4888_A;
import lightning.product.X_3546_T;
import lightning.product.X_4570_n;
import lightning.product.Y_1060_L;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_408_T;
import lightning.product.b_257_Y;
import lightning.product.b_3485_j;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.d_3786_K;
import lightning.product.e_2866_D;
import lightning.product.g_88_D;
import lightning.product.h_1015_G;
import lightning.product.i_2154_H;
import lightning.product.j_4680_H;
import lightning.product.k_290_v;
import lightning.product.l_3747_P;
import lightning.product.l_4140_i;
import lightning.product.o_148_s;
import lightning.product.o_3622_W;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.r_3979_X;
import lightning.product.t_5_h;
import lightning.product.t_693_s;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;
import lightning.product.y_528_b;
import lightning.product.z_1210_S;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalNear;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalRunAway;
import org.lwjgl.opengl.GL11;

public class c_2829_Q
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0422\u0438\u043f", "Plants", "Plants", "Mobs", "\u0417\u0435\u043b\u044c\u044f");
    private final I_686_h w_1484_f = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430", 8.0f, 3.0f, 16.0f, 1.0f, () -> this.v_4262_N.J_1907_R("Plants"));
    private final q_366_O t_148_a = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Baritone", () -> this.v_4262_N.J_1907_R("Plants"), "Baritone", "\u0412\u0440\u0443\u0447\u043d\u0443\u044e");
    private final I_686_h s_956_w = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f", 32.0f, 16.0f, 64.0f, 4.0f, () -> this.v_4262_N.J_1907_R("Plants") && this.t_148_a.J_1907_R("Baritone"));
    private final p_1977_n u_2550_I = new p_1977_n("\u0410\u0432\u0442\u043e\u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435", true, () -> this.v_4262_N.J_1907_R("Plants") && this.t_148_a.J_1907_R("Baritone"));
    private final I_686_h M_588_G = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f (\u0441\u0435\u043a)", 3.0f, 1.0f, 10.0f, 0.5f, () -> this.v_4262_N.J_1907_R("Plants") && this.u_2550_I.t_148_a() != false);
    private final N_4463_r P_4830_p = new N_4463_r("\u0421\u043e\u0431\u0438\u0440\u0430\u0442\u044c", () -> this.v_4262_N.J_1907_R("Plants"), new p_1977_n("\u0422\u044b\u043a\u0432\u044b", true), new p_1977_n("\u0410\u0440\u0431\u0443\u0437\u044b", true), new p_1977_n("\u041c\u043e\u0440\u043a\u043e\u0432\u044c", true), new p_1977_n("\u041a\u0430\u0440\u0442\u043e\u0444\u0435\u043b\u044c", true), new p_1977_n("\u0421\u0432\u0435\u043a\u043b\u0443", true), new p_1977_n("\u041f\u0448\u0435\u043d\u0438\u0446\u0443", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0434\u0443\u0431\u0430", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0431\u0435\u0440\u0435\u0437\u044b", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0435\u043b\u0438", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0434\u0436\u0443\u043d\u0433\u043b\u0435\u0439", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0430\u043a\u0430\u0446\u0438\u0438", true), new p_1977_n("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0442\u0435\u043c\u043d\u043e\u0433\u043e \u0434\u0443\u0431\u0430", true), new p_1977_n("\u041f\u043e\u0431\u0435\u0433\u0438 \u0431\u0430\u043c\u0431\u0443\u043a\u0430", true));
    private final p_1977_n h_1847_R = new p_1977_n("\u0410\u0432\u0442\u043e \u0438\u043d\u0441\u0442\u0440\u0443\u043c\u0435\u043d\u0442", true, () -> this.v_4262_N.J_1907_R("Plants"));
    private final I_686_h Q_4569_t = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043b\u043e\u043c\u0430\u043d\u0438\u044f (\u043c\u0441)", 200.0f, 50.0f, 1000.0f, 50.0f, () -> this.v_4262_N.J_1907_R("Plants"));
    private final I_686_h M_182_A = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u0435\u0440\u0435\u043c\u0435\u0449\u0435\u043d\u0438\u044f (\u043c\u0441)", 500.0f, 100.0f, 2000.0f, 100.0f, () -> this.v_4262_N.J_1907_R("Plants"));
    private final p_1977_n t_1786_h = new p_1977_n("\u041f\u043e\u0432\u043e\u0440\u0430\u0447\u0438\u0432\u0430\u0442\u044c\u0441\u044f \u043a \u0431\u043b\u043e\u043a\u0443", true, () -> this.v_4262_N.J_1907_R("Plants"));
    private final p_1977_n N_4405_n = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", true, () -> this.v_4262_N.J_1907_R("Plants"));
    private final p_1977_n w_1457_N = new p_1977_n("\u041e\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0442\u044c \u043f\u0440\u0438 \u043f\u043e\u043b\u043d\u043e\u043c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", true, () -> this.v_4262_N.J_1907_R("Plants") && this.N_4405_n.t_148_a() != false);
    private final p_1977_n Y_601_j = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0434\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u0431\u043b\u043e\u043a\u0438", true, () -> this.v_4262_N.J_1907_R("Plants"));
    private final p_1977_n Y_259_p = new p_1977_n("\u0410\u0432\u0442\u043e\u043f\u043e\u0441\u0430\u0434\u043a\u0430", true, () -> this.v_4262_N.J_1907_R("Plants"));
    private final I_686_h Q_2552_b = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u043e\u0441\u0430\u0434\u043a\u0438 (\u043c\u0441)", 300.0f, 100.0f, 1000.0f, 50.0f, () -> this.v_4262_N.J_1907_R("Plants") && this.Y_259_p.t_148_a() != false);
    private final N_4463_r C_2741_M = new N_4463_r("\u041c\u043e\u0431\u044b", () -> this.v_4262_N.J_1907_R("Mobs"), new p_1977_n("\u041a\u0440\u0438\u043f\u0435\u0440\u044b", true), new p_1977_n("\u0421\u043a\u0435\u043b\u0435\u0442\u044b", true), new p_1977_n("\u0412\u0438\u0437\u0435\u0440 \u0441\u043a\u0435\u043b\u0435\u0442\u044b", false), new p_1977_n("\u0421\u0442\u0440\u044d\u0438", false), new p_1977_n("\u0417\u043e\u043c\u0431\u0438", true), new p_1977_n("\u0423\u0442\u043e\u043f\u043b\u0435\u043d\u043d\u0438\u043a\u0438", false), new p_1977_n("\u0425\u0430\u0441\u043a\u0438", false), new p_1977_n("\u0417\u043e\u043c\u0431\u0438-\u0436\u0438\u0442\u0435\u043b\u0438", false), new p_1977_n("\u041f\u0430\u0443\u043a\u0438", true), new p_1977_n("\u041f\u0435\u0449\u0435\u0440\u043d\u044b\u0435 \u043f\u0430\u0443\u043a\u0438", false));
    private final I_686_h k_2293_S = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430", 32.0f, 16.0f, 64.0f, 4.0f, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final I_686_h q_2307_F = new I_686_h("\u041c\u0430\u043a\u0441. \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u0433\u043e\u043d\u0438", 64.0f, 32.0f, 128.0f, 8.0f, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final I_686_h Z_875_P = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043f\u043e\u0434\u0445\u043e\u0434\u0430", 4.0f, 2.0f, 8.0f, 0.5f, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final I_686_h t_4043_B = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u043a\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f (\u0441\u0435\u043a)", 2.0f, 0.5f, 5.0f, 0.5f, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final p_1977_n x_607_J = new p_1977_n("\u0418\u0437\u0431\u0435\u0433\u0430\u0442\u044c \u0432\u0437\u0440\u044b\u0432\u0430 \u043a\u0440\u0438\u043f\u0435\u0440\u043e\u0432", true, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final I_686_h e_4240_b = new I_686_h("\u0411\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u0430\u044f \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 5.0f, 3.0f, 8.0f, 0.5f, () -> this.v_4262_N.J_1907_R("Mobs") && this.x_607_J.t_148_a() != false);
    private final p_1977_n n_3318_d = new p_1977_n("\u0416\u0434\u0430\u0442\u044c \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u0443", true, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final p_1977_n d_2427_y = new p_1977_n("\u0410\u0432\u0442\u043e\u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435", true, () -> this.v_4262_N.J_1907_R("Mobs"));
    private final q_366_O z_1737_N = new q_366_O("\u0417\u0435\u043b\u044c\u0435", "\u0421\u0438\u043b\u0430", () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"), "\u0421\u0438\u043b\u0430", "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", "\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f", "\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435", "\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c", "\u041f\u043e\u0434\u0432\u043e\u0434\u043d\u043e\u0435 \u0434\u044b\u0445\u0430\u043d\u0438\u0435", "\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c", "\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u043e\u0435 \u043f\u0430\u0434\u0435\u043d\u0438\u0435", "\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435", "\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c", "\u0423\u0440\u043e\u043d", "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043c\u0430\u0441\u0442\u0435\u0440");
    private final q_366_O v_4276_D = new q_366_O("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u0435", "\u0423\u0441\u0438\u043b\u0438\u0442\u044c", () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"), "\u0423\u0441\u0438\u043b\u0438\u0442\u044c", "\u041f\u0440\u043e\u0434\u043b\u0438\u0442\u044c", "\u041d\u0435 \u0443\u043b\u0443\u0447\u0448\u0430\u0442\u044c");
    private final I_686_h d_2461_k = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u043f\u043e\u0438\u0441\u043a\u0430 (\u0432\u0430\u0440\u043a\u0430)", 16.0f, 5.0f, 32.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"));
    private final I_686_h G_624_v = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0439 (\u0432\u0430\u0440\u043a\u0430)", 200.0f, 50.0f, 500.0f, 10.0f, () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"));
    private final p_1977_n T_2506_i = new p_1977_n("\u0421\u043a\u043b\u0430\u0434\u044b\u0432\u0430\u0442\u044c \u0432 \u0441\u0443\u043d\u0434\u0443\u043a", true, () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"));
    private final p_1977_n q_4610_l = new p_1977_n("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0441\u0442\u0430\u0442\u0443\u0441 \u0432\u0430\u0440\u043a\u0438", true, () -> this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f"));
    private final p_1977_n z_4693_k = new p_1977_n("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u043e\u0442\u043b\u0430\u0434\u043a\u0443", true);
    private final List<c_1514_x> g_221_o = new CopyOnWriteArrayList<c_1514_x>();
    private c_1514_x e_2887_G = null;
    private N_4263_v B_1668_F = null;
    private IBaritone g_164_R;
    private final V_4557_X X_933_l = new V_4557_X();
    private final V_4557_X Z_976_R = new V_4557_X();
    private final V_4557_X H_1990_U = new V_4557_X();
    private final V_4557_X N_2525_X = new V_4557_X();
    private final V_4557_X c_4037_x = new V_4557_X();
    private n_1700_B g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
    private int T_3594_S = -1;
    private c_1514_x D_4792_h = null;
    private c_1514_x s_2632_s = null;
    private final V_4557_X l_1233_K = new V_4557_X();
    private final V_4557_X z_1333_t = new V_4557_X();
    private c_1514_x O_508_d;
    private c_1514_x r_715_M;
    private final Map<c_1514_x, Long> A_1038_p = new HashMap<c_1514_x, Long>();
    private final List<c_1514_x> i_1637_u = new ArrayList<c_1514_x>();
    private final List<c_1514_x> T_69_K = new ArrayList<c_1514_x>();
    private final List<c_1514_x> p_178_J = new ArrayList<c_1514_x>();
    private int P_2565_J = 0;
    private int f_4016_n = 0;
    private boolean j_276_v = false;
    private static final long y_2356_n = 20000L;

    public c_2829_Q() {
        super("AutoFarm", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.t_148_a, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B, this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, this.z_1737_N, this.v_4276_D, this.d_2461_k, this.G_624_v, this.T_2506_i, this.q_4610_l, this.z_4693_k);
    }

    @Override
    public void n_1700_B() {
        if (c_2829_Q.c_3005_b.Y_259_p != null) {
            this.g_164_R = BaritoneAPI.getProvider().getBaritoneForPlayer(c_2829_Q.c_3005_b.Y_259_p);
        }
        this.g_2268_R = this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f") ? lightning.product.c_2829_Q$n_1700_B.v_4262_N : lightning.product.c_2829_Q$n_1700_B.n_1700_B;
        this.g_221_o.clear();
        this.e_2887_G = null;
        this.B_1668_F = null;
        this.D_4792_h = null;
        this.s_2632_s = null;
        this.c_4037_x.n_1700_B();
        this.O_508_d = null;
        this.r_715_M = null;
        this.A_1038_p.clear();
        this.i_1637_u.clear();
        this.T_69_K.clear();
        this.p_178_J.clear();
        this.P_2565_J = 0;
        this.f_4016_n = 0;
        this.j_276_v = false;
        if (this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f")) {
            this.G_564_y("\u0417\u0430\u043f\u0443\u0449\u0435\u043d, \u0441\u043a\u0430\u043d\u0438\u0440\u0443\u044e...");
        }
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        if (this.g_164_R != null) {
            this.g_164_R.getPathingBehavior().cancelEverything();
        }
        if (c_2829_Q.c_3005_b.Y_259_p != null && this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f")) {
            c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
        }
        this.g_221_o.clear();
        this.e_2887_G = null;
        this.B_1668_F = null;
        this.s_2632_s = null;
        this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
        this.O_508_d = null;
        this.r_715_M = null;
        this.A_1038_p.clear();
        this.i_1637_u.clear();
        this.T_69_K.clear();
        this.p_178_J.clear();
        this.j_276_v = false;
        if (this.T_3594_S != -1) {
            c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.T_3594_S;
            this.T_3594_S = -1;
        }
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (c_2829_Q.c_3005_b.Y_259_p == null || c_2829_Q.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Plants")) {
            this.h_1847_R();
        } else if (this.v_4262_N.J_1907_R("Mobs")) {
            this.Q_4569_t();
        } else if (this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f")) {
            this.Y_1740_V();
        }
    }

    private void h_1847_R() {
        if (this.g_2268_R.name().startsWith("BREW_")) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
        }
        if (this.N_4405_n.t_148_a().booleanValue() && this.A_4115_X() && this.w_1457_N.t_148_a().booleanValue()) {
            return;
        }
        switch (this.g_2268_R.ordinal()) {
            case 0: {
                this.Y_259_p();
                if (!this.g_221_o.isEmpty()) {
                    if (this.z_4693_k.t_148_a().booleanValue()) {
                        this.R_4764_Y("\u041d\u0430\u0439\u0434\u0435\u043d\u043e " + this.g_221_o.size() + " \u0446\u0435\u043b\u0435\u0439");
                    }
                    this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
                    this.k_2293_S();
                    break;
                }
                if (!this.t_148_a.J_1907_R("Baritone") || !this.u_2550_I.t_148_a().booleanValue()) break;
                if (this.z_4693_k.t_148_a().booleanValue()) {
                    this.R_4764_Y("\u0426\u0435\u043b\u0438 \u0432 \u0440\u0430\u0434\u0438\u0443\u0441\u0435 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b, \u043d\u0430\u0447\u0438\u043d\u0430\u044e \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435...");
                }
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.J_1907_R;
                break;
            }
            case 1: {
                if (this.z_4693_k.t_148_a().booleanValue()) {
                    this.R_4764_Y("\u0418\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435... \u0418\u0449\u0443 \u043d\u043e\u0432\u044b\u0435 \u0444\u0435\u0440\u043c\u044b");
                }
                this.C_2741_M();
                break;
            }
            case 2: {
                if (this.e_2887_G == null) {
                    this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
                    break;
                }
                if (this.t_148_a.J_1907_R("Baritone")) {
                    this.q_2307_F();
                    break;
                }
                this.Z_875_P();
                break;
            }
            case 3: {
                if (this.e_2887_G == null || !this.n_1700_B(this.e_2887_G)) {
                    this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
                    this.e_2887_G = null;
                    break;
                }
                this.c_3005_b();
            }
        }
    }

    private void Q_4569_t() {
        if (this.g_164_R == null) {
            return;
        }
        if (this.g_2268_R.name().startsWith("BREW_")) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
        }
        switch (this.g_2268_R.ordinal()) {
            case 0: {
                this.M_182_A();
                break;
            }
            case 2: {
                this.t_1786_h();
                break;
            }
            case 4: {
                this.N_4405_n();
                break;
            }
            case 1: {
                this.Y_601_j();
                break;
            }
            case 5: {
                this.w_1457_N();
            }
        }
    }

    private void M_182_A() {
        if (!this.H_1990_U.n_1700_B((double)((long)(((Float)this.t_4043_B.J_1907_R()).floatValue() * 1000.0f)))) {
            return;
        }
        List<N_4263_v> mobs = this.H_2857_Y();
        if (!mobs.isEmpty()) {
            this.B_1668_F = mobs.get(0);
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
            this.N_2525_X.n_1700_B();
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041d\u0430\u0439\u0434\u0435\u043d \u043c\u043e\u0431: " + this.J_1907_R(this.B_1668_F) + " \u043d\u0430 \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u0438 " + String.format("%.1f", Float.valueOf(c_2829_Q.c_3005_b.Y_259_p.R_4764_Y(this.B_1668_F))));
            }
        } else if (this.d_2427_y.t_148_a().booleanValue()) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.J_1907_R;
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041c\u043e\u0431\u044b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b, \u043d\u0430\u0447\u0438\u043d\u0430\u044e \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0435");
            }
        }
        this.H_1990_U.n_1700_B();
    }

    private void t_1786_h() {
        boolean shouldUpdatePath;
        b_3485_j creeper;
        if (this.B_1668_F == null || !this.B_1668_F.H_3699_F()) {
            this.B_1668_F = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            this.g_164_R.getPathingBehavior().cancelEverything();
            this.s_2632_s = null;
            return;
        }
        double distance = c_2829_Q.c_3005_b.Y_259_p.R_4764_Y(this.B_1668_F);
        if (distance > (double)((Float)this.q_2307_F.J_1907_R()).floatValue()) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u0426\u0435\u043b\u044c \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u0430\u043b\u0435\u043a\u043e (" + (int)distance + " \u0431\u043b\u043e\u043a\u043e\u0432), \u0438\u0449\u0443 \u0434\u0440\u0443\u0433\u0443\u044e");
            }
            this.g_164_R.getPathingBehavior().cancelEverything();
            this.B_1668_F = null;
            this.s_2632_s = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            return;
        }
        if (this.B_1668_F instanceof b_3485_j && this.x_607_J.t_148_a().booleanValue() && this.n_1700_B(creeper = (b_3485_j)this.B_1668_F) && distance < (double)((Float)this.e_4240_b.J_1907_R()).floatValue()) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041a\u0440\u0438\u043f\u0435\u0440 \u043d\u0430\u0447\u0430\u043b \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c\u0441\u044f! \u0423\u0431\u0435\u0433\u0430\u044e!");
            }
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.u_1723_Y;
            this.s_2632_s = null;
            return;
        }
        if (distance <= (double)((Float)this.Z_875_P.J_1907_R()).floatValue()) {
            this.g_164_R.getPathingBehavior().cancelEverything();
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.P_1922_E;
            this.s_2632_s = null;
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041f\u043e\u0434\u043e\u0448\u0435\u043b \u043a " + this.J_1907_R(this.B_1668_F) + ", \u0443\u0431\u0438\u0432\u0430\u0439");
            }
            return;
        }
        c_1514_x targetPos = this.B_1668_F.b_2312_j();
        boolean targetMoved = this.s_2632_s == null || !this.s_2632_s.equals(targetPos);
        boolean bl = shouldUpdatePath = !this.g_164_R.getPathingBehavior().isPathing() || targetMoved && this.c_4037_x.n_1700_B(500.0);
        if (shouldUpdatePath) {
            GoalNear goal = new GoalNear(targetPos, (int)((Float)this.Z_875_P.J_1907_R()).floatValue());
            this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
            this.s_2632_s = targetPos;
            this.c_4037_x.n_1700_B();
            if (this.z_4693_k.t_148_a().booleanValue() && targetMoved && distance > 10.0) {
                this.R_4764_Y("\u041f\u0440\u0435\u0441\u043b\u0435\u0434\u0443\u044e " + this.J_1907_R(this.B_1668_F) + " (" + (int)distance + " \u0431\u043b\u043e\u043a\u043e\u0432)");
            }
        }
        if (this.N_2525_X.n_1700_B(6000.0)) {
            c_1514_x currentPos = c_2829_Q.c_3005_b.Y_259_p.b_2312_j();
            if (this.D_4792_h != null && this.D_4792_h.equals(currentPos)) {
                if (this.z_4693_k.t_148_a().booleanValue()) {
                    this.R_4764_Y("\u0417\u0430\u0441\u0442\u0440\u044f\u043b, \u0438\u0449\u0443 \u0434\u0440\u0443\u0433\u0443\u044e \u0446\u0435\u043b\u044c");
                }
                this.g_164_R.getPathingBehavior().cancelEverything();
                this.B_1668_F = null;
                this.s_2632_s = null;
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            }
            this.D_4792_h = currentPos;
            this.N_2525_X.n_1700_B();
        }
    }

    private void N_4405_n() {
        r_3979_X attackAura;
        b_3485_j creeper;
        if (this.B_1668_F == null || !this.B_1668_F.H_3699_F()) {
            this.B_1668_F = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u0426\u0435\u043b\u044c \u0443\u0431\u0438\u0442\u0430, \u0438\u0449\u0443 \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0443\u044e");
            }
            return;
        }
        double distance = c_2829_Q.c_3005_b.Y_259_p.R_4764_Y(this.B_1668_F);
        if (this.B_1668_F instanceof b_3485_j && this.x_607_J.t_148_a().booleanValue() && this.n_1700_B(creeper = (b_3485_j)this.B_1668_F) && distance < (double)((Float)this.e_4240_b.J_1907_R()).floatValue()) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041a\u0440\u0438\u043f\u0435\u0440 \u043d\u0430\u0447\u0430\u043b \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c\u0441\u044f! \u0423\u0431\u0435\u0433\u0430\u044e!");
            }
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.u_1723_Y;
            return;
        }
        if (distance > (double)(((Float)this.Z_875_P.J_1907_R()).floatValue() + 2.0f)) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
            this.s_2632_s = null;
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u0426\u0435\u043b\u044c \u043e\u0442\u043e\u0448\u043b\u0430, \u0434\u043e\u0433\u043e\u043d\u044f\u044e...");
            }
            return;
        }
        if (this.n_3318_d.t_148_a().booleanValue() && (attackAura = (r_3979_X)o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class)) != null && attackAura.w_1484_f()) {
            return;
        }
    }

    private void w_1457_N() {
        if (this.B_1668_F == null || !this.B_1668_F.H_3699_F()) {
            this.B_1668_F = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            this.g_164_R.getPathingBehavior().cancelEverything();
            return;
        }
        if (!(this.B_1668_F instanceof b_3485_j)) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            this.g_164_R.getPathingBehavior().cancelEverything();
            return;
        }
        b_3485_j creeper = (b_3485_j)this.B_1668_F;
        double distance = c_2829_Q.c_3005_b.Y_259_p.R_4764_Y(creeper);
        if (!this.n_1700_B(creeper)) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041a\u0440\u0438\u043f\u0435\u0440 \u043f\u0435\u0440\u0435\u0441\u0442\u0430\u043b \u0432\u0437\u0440\u044b\u0432\u0430\u0442\u044c\u0441\u044f, \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0430\u044e\u0441\u044c");
            }
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            this.B_1668_F = null;
            this.g_164_R.getPathingBehavior().cancelEverything();
            return;
        }
        if (distance >= (double)((Float)this.e_4240_b.J_1907_R()).floatValue()) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u0414\u043e\u0441\u0442\u0438\u0433 \u0431\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u043e\u0433\u043e \u0440\u0430\u0441\u0441\u0442\u043e\u044f\u043d\u0438\u044f \u043e\u0442 \u043a\u0440\u0438\u043f\u0435\u0440\u0430");
            }
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            this.B_1668_F = null;
            this.g_164_R.getPathingBehavior().cancelEverything();
            return;
        }
        c_1514_x creeperPos = creeper.b_2312_j();
        if (!this.g_164_R.getPathingBehavior().isPathing() || this.c_4037_x.n_1700_B(500.0)) {
            GoalRunAway goal = new GoalRunAway((double)((Float)this.e_4240_b.J_1907_R()).floatValue() + 2.0, creeperPos);
            this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
            this.c_4037_x.n_1700_B();
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u0423\u0431\u0435\u0433\u0430\u044e \u043e\u0442 \u043a\u0440\u0438\u043f\u0435\u0440\u0430! \u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f: " + String.format("%.1f", distance) + " \u0431\u043b\u043e\u043a\u043e\u0432");
            }
        }
    }

    private void Y_601_j() {
        if (this.H_1990_U.n_1700_B((double)((long)(((Float)this.t_4043_B.J_1907_R()).floatValue() * 1000.0f)))) {
            List<N_4263_v> mobs = this.H_2857_Y();
            if (!mobs.isEmpty()) {
                this.B_1668_F = mobs.get(0);
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
                this.g_164_R.getPathingBehavior().cancelEverything();
                this.N_2525_X.n_1700_B();
                this.H_1990_U.n_1700_B();
                if (this.z_4693_k.t_148_a().booleanValue()) {
                    this.R_4764_Y("\u041d\u0430\u0448\u0435\u043b \u043c\u043e\u0431\u0430 \u043f\u0440\u0438 \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u0438: " + this.J_1907_R(this.B_1668_F));
                }
                return;
            }
            this.H_1990_U.n_1700_B();
        }
        if (!this.g_164_R.getPathingBehavior().isPathing()) {
            c_1514_x randomTarget = c_2829_Q.c_3005_b.Y_259_p.b_2312_j().add((int)(Math.random() * 40.0 - 20.0), 0, (int)(Math.random() * 40.0 - 20.0));
            GoalNear goal = new GoalNear(randomTarget, 3);
            this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
        }
    }

    private void Y_259_p() {
        this.g_221_o.clear();
        c_1514_x playerPos = c_2829_Q.c_3005_b.Y_259_p.b_2312_j();
        int range = (int)((Float)this.w_1484_f.J_1907_R()).floatValue();
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    c_1514_x pos2 = playerPos.add(x, y, z);
                    if (!this.n_1700_B(pos2) || this.Y_601_j.t_148_a().booleanValue() && !this.J_1907_R(pos2)) continue;
                    this.g_221_o.add(pos2);
                }
            }
        }
        this.g_221_o.sort(Comparator.comparingDouble(pos -> c_2829_Q.c_3005_b.Y_259_p.b_2312_j().distanceSq((z_3539_x)pos)));
    }

    private void Q_2552_b() {
        this.g_221_o.clear();
        c_1514_x playerPos = c_2829_Q.c_3005_b.Y_259_p.b_2312_j();
        int exploreRange = (int)((Float)this.s_956_w.J_1907_R()).floatValue();
        for (int x = -exploreRange; x <= exploreRange; x += 4) {
            for (int y = -8; y <= 8; ++y) {
                for (int z = -exploreRange; z <= exploreRange; z += 4) {
                    c_1514_x pos2 = playerPos.add(x, y, z);
                    if (!this.n_1700_B(pos2)) continue;
                    this.g_221_o.add(pos2);
                    if (this.g_221_o.size() < 5) continue;
                    this.g_221_o.sort(Comparator.comparingDouble(p -> c_2829_Q.c_3005_b.Y_259_p.b_2312_j().distanceSq((z_3539_x)p)));
                    return;
                }
            }
        }
        this.g_221_o.sort(Comparator.comparingDouble(pos -> c_2829_Q.c_3005_b.Y_259_p.b_2312_j().distanceSq((z_3539_x)pos)));
    }

    private void C_2741_M() {
        if (this.g_164_R == null) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            return;
        }
        this.Q_2552_b();
        if (!this.g_221_o.isEmpty()) {
            if (this.z_4693_k.t_148_a().booleanValue()) {
                this.R_4764_Y("\u041d\u0430\u0439\u0434\u0435\u043d\u043e " + this.g_221_o.size() + " \u0434\u0430\u043b\u044c\u043d\u0438\u0445 \u0446\u0435\u043b\u0435\u0439!");
            }
            this.k_2293_S();
            if (this.e_2887_G != null) {
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
            }
        } else {
            long exploreDelayMs;
            if (!this.g_164_R.getPathingBehavior().isPathing()) {
                c_1514_x randomTarget = c_2829_Q.c_3005_b.Y_259_p.b_2312_j().add((int)(Math.random() * 32.0 - 16.0), 0, (int)(Math.random() * 32.0 - 16.0));
                GoalBlock goal = new GoalBlock(randomTarget);
                this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
            }
            if (this.Z_976_R.n_1700_B((double)(exploreDelayMs = (long)(((Float)this.M_588_G.J_1907_R()).floatValue() * 1000.0f)))) {
                this.Y_259_p();
                if (!this.g_221_o.isEmpty()) {
                    this.g_164_R.getPathingBehavior().cancelEverything();
                    this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
                    if (this.z_4693_k.t_148_a().booleanValue()) {
                        this.R_4764_Y("\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u044b \u0446\u0435\u043b\u0438 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0438\u0441\u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f!");
                    }
                }
                this.Z_976_R.n_1700_B();
            }
        }
    }

    private boolean n_1700_B(c_1514_x pos) {
        K_4074_S blockState = c_2829_Q.c_3005_b.Y_601_j.getBlockState(pos);
        T_2915_h block = blockState.J_1907_R();
        if (this.P_4830_p.J_1907_R("\u0422\u044b\u043a\u0432\u044b").booleanValue() && block == a_3742_W.A_3244_K) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0410\u0440\u0431\u0443\u0437\u044b").booleanValue() && block == a_3742_W.E_3343_g) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0434\u0443\u0431\u0430").booleanValue() && block == a_3742_W.Y_601_j) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0431\u0435\u0440\u0435\u0437\u044b").booleanValue() && block == a_3742_W.Q_2552_b) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0435\u043b\u0438").booleanValue() && block == a_3742_W.Y_259_p) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0434\u0436\u0443\u043d\u0433\u043b\u0435\u0439").booleanValue() && block == a_3742_W.C_2741_M) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0430\u043a\u0430\u0446\u0438\u0438").booleanValue() && block == a_3742_W.k_2293_S) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0430\u0436\u0435\u043d\u0446\u044b \u0442\u0435\u043c\u043d\u043e\u0433\u043e \u0434\u0443\u0431\u0430").booleanValue() && block == a_3742_W.q_2307_F) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u041f\u043e\u0431\u0435\u0433\u0438 \u0431\u0430\u043c\u0431\u0443\u043a\u0430").booleanValue() && block == a_3742_W.m_3828_C) {
            return true;
        }
        if (this.P_4830_p.J_1907_R("\u041c\u043e\u0440\u043a\u043e\u0432\u044c").booleanValue() && block == a_3742_W.P_2295_B) {
            return this.n_1700_B(blockState, F_103_K.h_1847_R);
        }
        if (this.P_4830_p.J_1907_R("\u041a\u0430\u0440\u0442\u043e\u0444\u0435\u043b\u044c").booleanValue() && block == a_3742_W.U_1697_c) {
            return this.n_1700_B(blockState, F_103_K.h_1847_R);
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0432\u0435\u043a\u043b\u0443").booleanValue() && block == a_3742_W.d_4412_Z) {
            return this.n_1700_B(blockState, X_4570_n.P_4830_p);
        }
        if (this.P_4830_p.J_1907_R("\u041f\u0448\u0435\u043d\u0438\u0446\u0443").booleanValue() && block == a_3742_W.l_4088_R) {
            return this.n_1700_B(blockState, F_103_K.h_1847_R);
        }
        return false;
    }

    private boolean n_1700_B(K_4074_S blockState, g_88_D ageProperty) {
        int maxAge;
        if (!blockState.J_1907_R(ageProperty)) {
            return false;
        }
        int age = blockState.R_4764_Y(ageProperty);
        return age == (maxAge = ageProperty.n_1700_B().stream().mapToInt(Integer::intValue).max().orElse(7));
    }

    private boolean J_1907_R(c_1514_x pos) {
        double distance = c_2829_Q.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(e_2866_D.n_1700_B(pos));
        return distance <= (double)c_2829_Q.c_3005_b.w_1457_N.getBlockReachDistance();
    }

    private void k_2293_S() {
        this.g_221_o.removeIf(pos -> !this.n_1700_B((c_1514_x)pos));
        if (this.g_221_o.isEmpty()) {
            this.e_2887_G = null;
            this.g_2268_R = this.t_148_a.J_1907_R("Baritone") && this.u_2550_I.t_148_a() != false ? lightning.product.c_2829_Q$n_1700_B.J_1907_R : lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            return;
        }
        this.e_2887_G = this.g_221_o.get(0);
        this.g_221_o.remove(0);
    }

    private void q_2307_F() {
        if (this.e_2887_G == null) {
            return;
        }
        if (this.g_164_R != null) {
            double distance = c_2829_Q.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(e_2866_D.n_1700_B(this.e_2887_G));
            if (distance <= (double)c_2829_Q.c_3005_b.w_1457_N.getBlockReachDistance()) {
                if (this.z_4693_k.t_148_a().booleanValue()) {
                    this.R_4764_Y("\u041d\u0430\u0447\u0438\u043d\u0430\u044e \u0441\u0431\u043e\u0440 \u0431\u043b\u043e\u043a\u0430 \u043d\u0430 " + String.valueOf(this.e_2887_G));
                }
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.G_564_y;
                this.g_164_R.getPathingBehavior().cancelEverything();
            } else {
                if (!this.g_164_R.getPathingBehavior().isPathing()) {
                    Goal goal;
                    if (distance > 10.0) {
                        goal = new GoalNear(this.e_2887_G, 3);
                        if (this.z_4693_k.t_148_a().booleanValue()) {
                            this.R_4764_Y("\u0418\u0434\u0443 \u043a \u0434\u0430\u043b\u044c\u043d\u0435\u0439 \u0446\u0435\u043b\u0438: " + (int)distance + " \u0431\u043b\u043e\u043a\u043e\u0432");
                        }
                    } else {
                        goal = new GoalBlock(this.e_2887_G);
                        if (this.z_4693_k.t_148_a().booleanValue()) {
                            this.R_4764_Y("\u0418\u0434\u0443 \u043a \u0431\u043b\u0438\u0436\u043d\u0435\u0439 \u0446\u0435\u043b\u0438: " + (int)distance + " \u0431\u043b\u043e\u043a\u043e\u0432");
                        }
                    }
                    this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
                }
                if (this.Z_976_R.n_1700_B(5000.0)) {
                    if (this.g_164_R.getPathingBehavior().isPathing()) {
                        this.g_164_R.getPathingBehavior().cancelEverything();
                        this.k_2293_S();
                        if (this.e_2887_G == null) {
                            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.n_1700_B;
                        }
                    }
                    this.Z_976_R.n_1700_B();
                }
            }
        }
    }

    private void Z_875_P() {
        if (this.e_2887_G == null) {
            return;
        }
        double distance = c_2829_Q.c_3005_b.Y_259_p.s_4990_V().u_1723_Y(e_2866_D.n_1700_B(this.e_2887_G));
        if (distance <= (double)c_2829_Q.c_3005_b.w_1457_N.getBlockReachDistance()) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.G_564_y;
        } else if (this.Z_976_R.n_1700_B((double)((Float)this.M_182_A.J_1907_R()).intValue())) {
            this.k_2293_S();
            if (this.e_2887_G != null) {
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.R_4764_Y;
            }
            this.Z_976_R.n_1700_B();
        }
    }

    private void c_3005_b() {
        if (this.e_2887_G == null || !this.n_1700_B(this.e_2887_G)) {
            this.k_2293_S();
            this.g_2268_R = this.e_2887_G != null ? lightning.product.c_2829_Q$n_1700_B.R_4764_Y : lightning.product.c_2829_Q$n_1700_B.n_1700_B;
            return;
        }
        if (!this.X_933_l.n_1700_B((double)((Float)this.Q_4569_t.J_1907_R()).intValue())) {
            return;
        }
        if (this.t_1786_h.t_148_a().booleanValue()) {
            e_2866_D blockVec = e_2866_D.n_1700_B(this.e_2887_G);
            e_2866_D playerVec = c_2829_Q.c_3005_b.Y_259_p.s_4990_V().J_1907_R(0.0, c_2829_Q.c_3005_b.Y_259_p.X_1313_W(), 0.0);
            e_2866_D diff = blockVec.G_564_y(playerVec);
            float yaw = (float)Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0f;
            float pitch = (float)(-Math.toDegrees(Math.atan2(diff.R_4764_Y, Math.sqrt(diff.J_1907_R * diff.J_1907_R + diff.G_564_y * diff.G_564_y))));
            c_2829_Q.c_3005_b.Y_259_p.p_178_J = yaw;
            c_2829_Q.c_3005_b.Y_259_p.f_4016_n = pitch;
        }
        if (this.h_1847_R.t_148_a().booleanValue()) {
            this.T_3594_S = c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            int bestTool = u_1934_K.n_1700_B(c_2829_Q.c_3005_b.Y_601_j.getBlockState(this.e_2887_G));
            if (bestTool != -1) {
                c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = bestTool;
            }
        }
        b_257_Y direction = b_257_Y.J_1907_R;
        c_2829_Q.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.n_1700_B, this.e_2887_G, direction));
        c_2829_Q.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.R_4764_Y, this.e_2887_G, direction));
        c_2829_Q.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        T_2915_h harvestedBlock = c_2829_Q.c_3005_b.Y_601_j.getBlockState(this.e_2887_G).J_1907_R();
        if (this.h_1847_R.t_148_a().booleanValue() && this.T_3594_S != -1) {
            c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.T_3594_S;
            this.T_3594_S = -1;
        }
        if (this.Y_259_p.t_148_a().booleanValue()) {
            new Thread(() -> {
                try {
                    Thread.sleep(((Float)this.Q_2552_b.J_1907_R()).intValue());
                    if (c_2829_Q.c_3005_b.Y_259_p != null && c_2829_Q.c_3005_b.Y_601_j != null) {
                        this.n_1700_B(this.e_2887_G, harvestedBlock);
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }).start();
        }
        this.X_933_l.n_1700_B();
        this.k_2293_S();
        this.g_2268_R = this.e_2887_G != null ? lightning.product.c_2829_Q$n_1700_B.R_4764_Y : lightning.product.c_2829_Q$n_1700_B.n_1700_B;
    }

    private List<N_4263_v> H_2857_Y() {
        List allEntities;
        double radius = ((Float)this.k_2293_S.J_1907_R()).floatValue();
        try {
            allEntities = StreamSupport.stream(c_2829_Q.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).collect(Collectors.toList());
        }
        catch (Exception e2) {
            return new ArrayList<N_4263_v>();
        }
        return allEntities.stream().filter(this::n_1700_B).filter(N_4263_v::H_3699_F).filter(e -> (double)c_2829_Q.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)e) <= radius).sorted(Comparator.comparingDouble(e -> c_2829_Q.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)e))).collect(Collectors.toList());
    }

    private boolean n_1700_B(N_4263_v entity) {
        if (this.C_2741_M.J_1907_R("\u041a\u0440\u0438\u043f\u0435\u0440\u044b").booleanValue() && entity instanceof b_3485_j) {
            b_3485_j creeper = (b_3485_j)entity;
            return this.x_607_J.t_148_a() == false || !this.n_1700_B(creeper);
        }
        if (this.C_2741_M.J_1907_R("\u0421\u043a\u0435\u043b\u0435\u0442\u044b").booleanValue() && entity instanceof k_290_v) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0412\u0438\u0437\u0435\u0440 \u0441\u043a\u0435\u043b\u0435\u0442\u044b").booleanValue() && entity instanceof z_1210_S) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0421\u0442\u0440\u044d\u0438").booleanValue() && entity instanceof o_3622_W) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0417\u043e\u043c\u0431\u0438").booleanValue() && entity.getClass() == F_4355_q.class) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0423\u0442\u043e\u043f\u043b\u0435\u043d\u043d\u0438\u043a\u0438").booleanValue() && entity instanceof S_3848_S) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0425\u0430\u0441\u043a\u0438").booleanValue() && entity instanceof d_3786_K) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u0417\u043e\u043c\u0431\u0438-\u0436\u0438\u0442\u0435\u043b\u0438").booleanValue() && entity instanceof l_4140_i) {
            return true;
        }
        if (this.C_2741_M.J_1907_R("\u041f\u0430\u0443\u043a\u0438").booleanValue() && entity.getClass() == Q_112_C.class) {
            return true;
        }
        return this.C_2741_M.J_1907_R("\u041f\u0435\u0449\u0435\u0440\u043d\u044b\u0435 \u043f\u0430\u0443\u043a\u0438") != false && entity instanceof V_4888_A;
    }

    private boolean n_1700_B(b_3485_j creeper) {
        return creeper.V_1176_p() || creeper.y_4642_Y() > 0;
    }

    private String J_1907_R(N_4263_v entity) {
        if (entity instanceof b_3485_j) {
            return "\u041a\u0440\u0438\u043f\u0435\u0440";
        }
        if (entity instanceof z_1210_S) {
            return "\u0412\u0438\u0437\u0435\u0440 \u0441\u043a\u0435\u043b\u0435\u0442";
        }
        if (entity instanceof o_3622_W) {
            return "\u0421\u0442\u0440\u044d\u0439";
        }
        if (entity instanceof k_290_v) {
            return "\u0421\u043a\u0435\u043b\u0435\u0442";
        }
        if (entity instanceof S_3848_S) {
            return "\u0423\u0442\u043e\u043f\u043b\u0435\u043d\u043d\u0438\u043a";
        }
        if (entity instanceof d_3786_K) {
            return "\u0425\u0430\u0441\u043a";
        }
        if (entity instanceof l_4140_i) {
            return "\u0417\u043e\u043c\u0431\u0438-\u0436\u0438\u0442\u0435\u043b\u044c";
        }
        if (entity instanceof F_4355_q) {
            return "\u0417\u043e\u043c\u0431\u0438";
        }
        if (entity instanceof V_4888_A) {
            return "\u041f\u0435\u0449\u0435\u0440\u043d\u044b\u0439 \u043f\u0430\u0443\u043a";
        }
        if (entity instanceof Q_112_C) {
            return "\u041f\u0430\u0443\u043a";
        }
        return t_5_h.n_1700_B(entity.f_4016_n()).toString();
    }

    private boolean A_4115_X() {
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = c_2829_Q.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!stack.n_1700_B()) continue;
            return false;
        }
        return true;
    }

    private Z_1993_T n_1700_B(T_2915_h block) {
        if (block == a_3742_W.P_2295_B) {
            return new Z_1993_T(q_4592_V.T_2055_d);
        }
        if (block == a_3742_W.U_1697_c) {
            return new Z_1993_T(q_4592_V.l_683_e);
        }
        if (block == a_3742_W.d_4412_Z) {
            return new Z_1993_T(q_4592_V.d_4460_l);
        }
        if (block == a_3742_W.l_4088_R) {
            return new Z_1993_T(q_4592_V.G_4691_Q);
        }
        return Z_1993_T.J_1907_R;
    }

    private int n_1700_B(Z_1993_T seedsNeeded) {
        if (seedsNeeded.n_1700_B()) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = c_2829_Q.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != seedsNeeded.J_1907_R()) continue;
            return i;
        }
        return -1;
    }

    private void n_1700_B(c_1514_x pos, T_2915_h harvestedBlock) {
        if (!this.Y_259_p.t_148_a().booleanValue()) {
            return;
        }
        K_4074_S belowState = c_2829_Q.c_3005_b.Y_601_j.getBlockState(pos.down());
        if (belowState.J_1907_R() != a_3742_W.Z_735_d) {
            return;
        }
        Z_1993_T seedsNeeded = this.n_1700_B(harvestedBlock);
        if (seedsNeeded.n_1700_B()) {
            return;
        }
        int seedsSlot = this.n_1700_B(seedsNeeded);
        if (seedsSlot == -1) {
            return;
        }
        int prevSlot = c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (seedsSlot < 9) {
            c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = seedsSlot;
        } else {
            c_2829_Q.c_3005_b.w_1457_N.windowClick(0, seedsSlot, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
            for (int i = 0; i < 9; ++i) {
                if (!c_2829_Q.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B()) continue;
                c_2829_Q.c_3005_b.w_1457_N.windowClick(0, i, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
                c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = i;
                break;
            }
        }
        G_3416_z rayTrace = new G_3416_z(e_2866_D.n_1700_B(pos.down()), b_257_Y.J_1907_R, pos.down(), false);
        c_2829_Q.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new F_1464_b(x_1688_C.n_1700_B, rayTrace));
        c_2829_Q.c_3005_b.Y_259_p.l_1268_F.G_564_y = prevSlot;
    }

    private void R_4764_Y(String message) {
        if (this.z_4693_k.t_148_a().booleanValue() && c_2829_Q.c_3005_b.Y_259_p != null) {
            c_2829_Q.c_3005_b.Y_259_p.n_1700_B((x_282_a)new U_2871_b("\u00a77[\u00a76AutoFarm\u00a77] \u00a7f" + message), false);
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (!this.v_4262_N.J_1907_R("\u0417\u0435\u043b\u044c\u044f")) {
            return;
        }
        if (c_2829_Q.c_3005_b.Y_259_p == null || c_2829_Q.c_3005_b.Y_601_j == null) {
            return;
        }
        double rx = c_2829_Q.c_3005_b.O_508_d().J_1907_R.J_1907_R().J_1907_R;
        double ry = c_2829_Q.c_3005_b.O_508_d().J_1907_R.J_1907_R().R_4764_Y;
        double rz = c_2829_Q.c_3005_b.O_508_d().J_1907_R.J_1907_R().G_564_y;
        for (c_1514_x pos : this.T_69_K) {
            boolean brewing = this.A_1038_p.containsKey(pos);
            boolean ready = this.i_1637_u.contains(pos);
            int color = ready ? -16711936 : (brewing ? -256 : -65536);
            I_4817_s box = new I_4817_s(pos).offset(-rx, -ry, -rz);
            this.n_1700_B(box, color);
        }
        if (this.O_508_d != null) {
            I_4817_s box = new I_4817_s(this.O_508_d).offset(-rx, -ry, -rz);
            this.n_1700_B(box, -1);
        }
    }

    private void n_1700_B(I_4817_s box, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r buffer = tessellator.R_4764_Y();
        float x0 = (float)box.minX;
        float x1 = (float)box.maxX;
        float y0 = (float)box.minY;
        float y1 = (float)box.maxY;
        float z0 = (float)box.minZ;
        float z1 = (float)box.maxZ;
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.e_4240_b();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.G_564_y(2.0f);
        GL11.glEnable((int)2848);
        buffer.n_1700_B(1, E_688_b.Y_601_j);
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z0).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x1, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y0, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        buffer.pos(x0, y1, z1).n_1700_B(r, g, b, 1.0f).endVertex();
        tessellator.J_1907_R();
        GL11.glDisable((int)2848);
        lightning.product.c_4037_x.N_4405_n();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.d_2461_k();
    }

    private void Y_1740_V() {
        if (this.g_164_R == null) {
            return;
        }
        switch (this.g_2268_R.ordinal()) {
            case 6: {
                this.t_4043_B();
                break;
            }
            case 7: {
                this.n_3318_d();
                break;
            }
            case 8: {
                this.d_2427_y();
                break;
            }
            case 9: {
                this.z_1737_N();
                break;
            }
            case 10: {
                this.d_2461_k();
                break;
            }
            case 11: {
                this.G_624_v();
                break;
            }
            default: {
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            }
        }
    }

    private void t_4043_B() {
        if (!this.l_1233_K.J_1907_R(((Float)this.G_624_v.J_1907_R()).longValue())) {
            return;
        }
        if (!this.j_276_v) {
            this.x_607_J();
            this.j_276_v = true;
            if (this.T_69_K.isEmpty()) {
                this.G_564_y("\u00a7c\u0421\u0442\u043e\u0439\u043a\u0438 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b!");
                return;
            }
            this.G_564_y("\u041d\u0430\u0439\u0434\u0435\u043d\u043e " + this.T_69_K.size() + " \u0441\u0442\u043e\u0435\u043a");
            this.l_1233_K.n_1700_B();
            return;
        }
        this.e_4240_b();
        if (!this.i_1637_u.isEmpty()) {
            this.O_508_d = this.i_1637_u.remove(0);
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.w_1484_f;
            this.G_564_y("\u0417\u0430\u0431\u0438\u0440\u0430\u044e \u0437\u0435\u043b\u044c\u044f (" + (this.i_1637_u.size() + 1) + " \u043e\u0441\u0442\u0430\u043b\u043e\u0441\u044c)");
            this.R_4764_Y(this.O_508_d);
            this.l_1233_K.n_1700_B();
            return;
        }
        if (this.P_2565_J < this.T_69_K.size()) {
            c_1514_x pos = this.T_69_K.get(this.P_2565_J);
            if (!this.A_1038_p.containsKey(pos)) {
                this.O_508_d = pos;
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.w_1484_f;
                this.G_564_y("\u0421\u0442\u043e\u0439\u043a\u0430 " + (this.P_2565_J + 1) + "/" + this.T_69_K.size());
                this.R_4764_Y(pos);
            }
            ++this.P_2565_J;
        } else if (!this.A_1038_p.isEmpty()) {
            long minRemaining = Long.MAX_VALUE;
            long now = System.currentTimeMillis();
            for (Long startTime : this.A_1038_p.values()) {
                long remaining = 20000L - (now - startTime);
                if (remaining >= minRemaining) continue;
                minRemaining = remaining;
            }
            this.G_564_y("\u041e\u0436\u0438\u0434\u0430\u043d\u0438\u0435... " + minRemaining / 1000L + " \u0441\u0435\u043a (" + this.A_1038_p.size() + " \u0441\u0442\u043e\u0435\u043a)");
        } else {
            this.P_2565_J = 0;
            this.G_564_y("\u0426\u0438\u043a\u043b \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d, \u043d\u0430\u0447\u0438\u043d\u0430\u044e \u0437\u0430\u043d\u043e\u0432\u043e");
        }
        this.l_1233_K.n_1700_B();
    }

    private void x_607_J() {
        c_1514_x pos;
        this.T_69_K.clear();
        int range = ((Float)this.d_2461_k.J_1907_R()).intValue();
        c_1514_x playerPos = c_2829_Q.c_3005_b.Y_259_p.b_2312_j();
        ArrayList<c_1514_x> found = new ArrayList<c_1514_x>();
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    pos = playerPos.add(x, y, z);
                    if (c_2829_Q.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() != a_3742_W.e_837_t) continue;
                    found.add(pos);
                }
            }
        }
        if (found.isEmpty()) {
            return;
        }
        c_1514_x furthest = found.stream().max(Comparator.comparingDouble(p -> p.distanceSq(playerPos))).orElse(null);
        ArrayList remaining = new ArrayList(found);
        c_1514_x current = furthest;
        while (!remaining.isEmpty()) {
            pos = current;
            remaining.remove(pos);
            this.T_69_K.add(pos);
            if (remaining.isEmpty()) continue;
            current = remaining.stream().min(Comparator.comparingDouble(p -> p.distanceSq(pos))).orElse(null);
        }
    }

    private void e_4240_b() {
        long now = System.currentTimeMillis();
        ArrayList<c_1514_x> toMove = new ArrayList<c_1514_x>();
        for (Map.Entry<c_1514_x, Long> entry : this.A_1038_p.entrySet()) {
            if (now - entry.getValue() < 20000L) continue;
            toMove.add(entry.getKey());
        }
        toMove.sort((a, b) -> {
            Long timeA = this.A_1038_p.get(a);
            Long timeB = this.A_1038_p.get(b);
            return Long.compare(timeA, timeB);
        });
        for (c_1514_x pos : toMove) {
            this.A_1038_p.remove(pos);
            this.i_1637_u.add(pos);
        }
    }

    private void n_3318_d() {
        if (this.O_508_d == null) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            return;
        }
        double distance = c_2829_Q.c_3005_b.Y_259_p.v_4262_N((double)this.O_508_d.getX() + 0.5, this.O_508_d.getY(), (double)this.O_508_d.getZ() + 0.5);
        if (distance <= 16.0) {
            this.g_164_R.getPathingBehavior().cancelEverything();
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.t_148_a;
            this.l_1233_K.n_1700_B();
            this.G_564_y("\u041e\u0442\u043a\u0440\u044b\u0432\u0430\u044e \u0432\u0430\u0440\u043e\u0447\u043d\u0443\u044e \u0441\u0442\u043e\u0439\u043a\u0443");
        } else if (!this.g_164_R.getPathingBehavior().isPathing() && this.z_1333_t.J_1907_R(1000L)) {
            this.R_4764_Y(this.O_508_d);
            this.z_1333_t.n_1700_B();
        }
    }

    private void d_2427_y() {
        if (c_2829_Q.c_3005_b.Y_1740_V instanceof R_1400_H) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.s_956_w;
            return;
        }
        if (!this.l_1233_K.J_1907_R(((Float)this.G_624_v.J_1907_R()).longValue())) {
            return;
        }
        if (this.O_508_d != null) {
            e_2866_D vec = new e_2866_D((double)this.O_508_d.getX() + 0.5, (double)this.O_508_d.getY() + 0.5, (double)this.O_508_d.getZ() + 0.5);
            G_3416_z hit = new G_3416_z(vec, b_257_Y.J_1907_R, this.O_508_d, false);
            c_2829_Q.c_3005_b.w_1457_N.func_217292_a(c_2829_Q.c_3005_b.Y_259_p, c_2829_Q.c_3005_b.Y_601_j, x_1688_C.n_1700_B, hit);
            this.l_1233_K.n_1700_B();
        }
    }

    private void z_1737_N() {
        a_2900_S container = c_2829_Q.c_3005_b.Y_259_p.H_1873_g;
        if (!(container instanceof Y_1060_L)) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.t_148_a;
            return;
        }
        if (!this.l_1233_K.J_1907_R(((Float)this.G_624_v.J_1907_R()).longValue())) {
            return;
        }
        Y_1060_L brew = (Y_1060_L)container;
        int brewTime = brew.J_1907_R();
        int fuel = brew.n_1700_B();
        if (this.n_1700_B(brew)) {
            this.J_1907_R(brew);
            this.f_4016_n += 3;
            this.G_564_y("\u0417\u0430\u0431\u0440\u0430\u043b \u0437\u0435\u043b\u044c\u044f! \u0412\u0441\u0435\u0433\u043e: " + this.f_4016_n);
            this.A_1038_p.remove(this.O_508_d);
            this.l_1233_K.n_1700_B();
            this.v_4276_D();
            return;
        }
        if (brewTime > 0) {
            if (!this.A_1038_p.containsKey(this.O_508_d)) {
                this.A_1038_p.put(this.O_508_d, System.currentTimeMillis());
            }
            this.G_564_y("\u0412\u0430\u0440\u0438\u0442\u0441\u044f, \u0438\u0434\u0443 \u043a \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0435\u0439...");
            c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
            this.O_508_d = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            this.l_1233_K.n_1700_B();
            return;
        }
        if (brew.n_1700_B(4).n_1700_B().n_1700_B() && fuel == 0) {
            int blazeSlot = this.n_1700_B(q_4592_V.C_3528_u);
            if (blazeSlot == -1) {
                this.G_564_y("\u00a7c\u041d\u0443\u0436\u0435\u043d \u043e\u0433\u043d\u0435\u043d\u043d\u044b\u0439 \u043f\u043e\u0440\u043e\u0448\u043e\u043a!");
                this.v_4276_D();
                return;
            }
            this.n_1700_B(blazeSlot, 4);
            this.G_564_y("\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u044e \u0442\u043e\u043f\u043b\u0438\u0432\u043e");
            this.l_1233_K.n_1700_B();
            return;
        }
        for (int i = 0; i < 3; ++i) {
            Z_1993_T slotStack = brew.n_1700_B(i).n_1700_B();
            if (!slotStack.n_1700_B()) continue;
            int waterBottle = this.R_4764_Y(brew);
            if (waterBottle == -1) {
                this.v_4276_D();
                return;
            }
            this.G_564_y("\u0412\u043e\u0434\u0430 \u2192 \u0441\u043b\u043e\u0442 " + i);
            c_2829_Q.c_3005_b.w_1457_N.windowClick(brew.u_1723_Y, waterBottle, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
            c_2829_Q.c_3005_b.w_1457_N.windowClick(brew.u_1723_Y, i, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
            this.l_1233_K.n_1700_B();
            return;
        }
        if (brew.n_1700_B(3).n_1700_B().n_1700_B()) {
            String selected = (String)this.z_1737_N.J_1907_R();
            if (selected.equals("\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c") && this.n_1700_B(brew, R_3414_E.J_1907_R)) {
                this.n_1700_B(brew, q_4592_V.Y_3066_B, "\u043f\u0430\u0443\u0447\u0438\u0439 \u0433\u043b\u0430\u0437");
                return;
            }
            if (this.n_1700_B(brew, R_3414_E.J_1907_R)) {
                this.n_1700_B(brew, q_4592_V.g_1096_r, "\u043d\u0430\u0440\u043e\u0441\u0442");
                return;
            }
            if (this.n_1700_B(brew, R_3414_E.P_1922_E)) {
                q_1613_l ingredient = this.z_4693_k();
                if (ingredient != null) {
                    this.n_1700_B(brew, ingredient, "\u0438\u043d\u0433\u0440\u0435\u0434\u0438\u0435\u043d\u0442");
                } else {
                    this.G_564_y("\u00a7c\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u044b\u0439 \u0440\u0435\u0446\u0435\u043f\u0442!");
                    this.v_4276_D();
                }
                return;
            }
            if (selected.equals("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c") && this.n_1700_B(brew, R_3414_E.u_1723_Y)) {
                this.n_1700_B(brew, q_4592_V.Y_3066_B, "\u043f\u0440\u0435\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u0435 \u0432 \u043d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c");
                return;
            }
            if (selected.equals("\u0423\u0440\u043e\u043d") && this.n_1700_B(brew, R_3414_E.Z_875_P)) {
                this.n_1700_B(brew, q_4592_V.Y_3066_B, "\u043f\u0440\u0435\u0432\u0440\u0430\u0449\u0435\u043d\u0438\u0435 \u0432 \u0443\u0440\u043e\u043d");
                return;
            }
            y_528_b basePotion = this.g_221_o();
            if (basePotion != null && this.n_1700_B(brew, basePotion)) {
                String upgrade = (String)this.v_4276_D.J_1907_R();
                if (upgrade.equals("\u0423\u0441\u0438\u043b\u0438\u0442\u044c") && this.e_2887_G()) {
                    this.n_1700_B(brew, q_4592_V.f_4840_J, "\u0443\u0441\u0438\u043b\u0435\u043d\u0438\u0435");
                    return;
                }
                if (upgrade.equals("\u041f\u0440\u043e\u0434\u043b\u0438\u0442\u044c") && this.B_1668_F()) {
                    this.n_1700_B(brew, q_4592_V.v_570_f, "\u043f\u0440\u043e\u0434\u043b\u0435\u043d\u0438\u0435");
                    return;
                }
                this.J_1907_R(brew);
                this.f_4016_n += 3;
                this.G_564_y("\u0417\u0430\u0431\u0440\u0430\u043b \u0437\u0435\u043b\u044c\u044f! \u0412\u0441\u0435\u0433\u043e: " + this.f_4016_n);
                this.A_1038_p.remove(this.O_508_d);
                this.v_4276_D();
                return;
            }
        }
    }

    private void n_1700_B(Y_1060_L brew, q_1613_l ingredient, String msg) {
        int slot = this.n_1700_B(ingredient);
        if (slot == -1) {
            this.G_564_y("\u00a7c\u041d\u0443\u0436\u0435\u043d: " + msg);
            this.v_4276_D();
            return;
        }
        this.n_1700_B(slot, 3);
        this.G_564_y("\u0414\u043e\u0431\u0430\u0432\u0438\u043b " + msg + " \u2192 \u0438\u0434\u0443 \u0434\u0430\u043b\u044c\u0448\u0435");
        this.A_1038_p.put(this.O_508_d, System.currentTimeMillis());
        c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
        this.O_508_d = null;
        this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
        this.l_1233_K.n_1700_B();
    }

    private boolean n_1700_B(Y_1060_L brew) {
        boolean hasAnyPotion = false;
        for (int i = 0; i < 3; ++i) {
            if (brew.n_1700_B(i).n_1700_B().J_1907_R() != q_4592_V.j_2461_G) continue;
            hasAnyPotion = true;
            break;
        }
        if (!hasAnyPotion) {
            return false;
        }
        y_528_b target = this.g_164_R();
        return target != null && this.n_1700_B(brew, target);
    }

    private void v_4276_D() {
        c_1514_x chestPos;
        c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
        this.O_508_d = null;
        if (!this.i_1637_u.isEmpty()) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            this.l_1233_K.n_1700_B();
            return;
        }
        if (this.T_2506_i.t_148_a().booleanValue() && this.X_933_l() && this.A_1038_p.isEmpty() && (chestPos = this.q_4610_l()) != null) {
            this.r_715_M = chestPos;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.u_2550_I;
            this.G_564_y("\u0412\u0441\u0451 \u0441\u043e\u0431\u0440\u0430\u043b \u2192 \u0432 \u0441\u0443\u043d\u0434\u0443\u043a");
            this.R_4764_Y(chestPos);
            return;
        }
        this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
        this.l_1233_K.n_1700_B();
    }

    private void d_2461_k() {
        if (this.r_715_M == null) {
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            return;
        }
        double distance = c_2829_Q.c_3005_b.Y_259_p.v_4262_N((double)this.r_715_M.getX() + 0.5, this.r_715_M.getY(), (double)this.r_715_M.getZ() + 0.5);
        if (distance <= 16.0) {
            this.g_164_R.getPathingBehavior().cancelEverything();
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.M_588_G;
            this.l_1233_K.n_1700_B();
            this.T_2506_i();
        } else if (!this.g_164_R.getPathingBehavior().isPathing() && this.z_1333_t.J_1907_R(1000L)) {
            this.R_4764_Y(this.r_715_M);
            this.z_1333_t.n_1700_B();
        }
    }

    private void G_624_v() {
        if (!(c_2829_Q.c_3005_b.Y_1740_V instanceof D_3030_E)) {
            if (this.l_1233_K.J_1907_R(((Float)this.G_624_v.J_1907_R()).longValue())) {
                this.T_2506_i();
                this.l_1233_K.n_1700_B();
            }
            return;
        }
        if (!this.l_1233_K.J_1907_R(((Float)this.G_624_v.J_1907_R()).longValue())) {
            return;
        }
        a_2900_S container = c_2829_Q.c_3005_b.Y_259_p.H_1873_g;
        if (!(container instanceof P_225_f)) {
            return;
        }
        P_225_f chest = (P_225_f)container;
        int chestSlots = chest.P_1922_E.size() - 36;
        boolean chestFull = true;
        for (int i = 0; i < chestSlots; ++i) {
            if (!chest.n_1700_B(i).n_1700_B().n_1700_B()) continue;
            chestFull = false;
            break;
        }
        if (chestFull) {
            this.G_564_y("\u0421\u0443\u043d\u0434\u0443\u043a \u043f\u043e\u043b\u043d\u044b\u0439, \u0438\u0449\u0443 \u0434\u0440\u0443\u0433\u043e\u0439...");
            this.p_178_J.add(this.r_715_M);
            c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
            c_1514_x nextChest = this.q_4610_l();
            if (nextChest != null) {
                this.r_715_M = nextChest;
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.u_2550_I;
                this.R_4764_Y(nextChest);
            } else {
                this.G_564_y("\u00a7c\u0412\u0441\u0435 \u0441\u0443\u043d\u0434\u0443\u043a\u0438 \u043f\u043e\u043b\u043d\u044b\u0435!");
                this.r_715_M = null;
                this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            }
            this.l_1233_K.n_1700_B();
            return;
        }
        boolean deposited = false;
        for (int i = chest.P_1922_E.size() - 36; i < chest.P_1922_E.size(); ++i) {
            Z_1993_T stack = chest.n_1700_B(i).n_1700_B();
            if (!this.J_1907_R(stack)) continue;
            this.J_1907_R(i);
            deposited = true;
            this.l_1233_K.n_1700_B();
            return;
        }
        if (!deposited) {
            c_2829_Q.c_3005_b.Y_259_p.P_1922_E();
            this.r_715_M = null;
            this.g_2268_R = lightning.product.c_2829_Q$n_1700_B.v_4262_N;
            this.G_564_y("\u0417\u0435\u043b\u044c\u044f \u0441\u043b\u043e\u0436\u0435\u043d\u044b");
            this.l_1233_K.n_1700_B();
        }
    }

    private void T_2506_i() {
        if (this.r_715_M != null) {
            e_2866_D vec = new e_2866_D((double)this.r_715_M.getX() + 0.5, (double)this.r_715_M.getY() + 0.5, (double)this.r_715_M.getZ() + 0.5);
            G_3416_z hit = new G_3416_z(vec, b_257_Y.J_1907_R, this.r_715_M, false);
            c_2829_Q.c_3005_b.w_1457_N.func_217292_a(c_2829_Q.c_3005_b.Y_259_p, c_2829_Q.c_3005_b.Y_601_j, x_1688_C.n_1700_B, hit);
        }
    }

    private void R_4764_Y(c_1514_x pos) {
        if (this.g_164_R != null) {
            GoalNear goal = new GoalNear(pos, 3);
            this.g_164_R.getCustomGoalProcess().setGoalAndPath(goal);
        }
    }

    private c_1514_x q_4610_l() {
        int range = ((Float)this.d_2461_k.J_1907_R()).intValue();
        c_1514_x playerPos = c_2829_Q.c_3005_b.Y_259_p.b_2312_j();
        ArrayList<c_1514_x> chests = new ArrayList<c_1514_x>();
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    c_1514_x pos = playerPos.add(x, y, z);
                    i_2154_H te = c_2829_Q.c_3005_b.Y_601_j.getTileEntity(pos);
                    if (!(te instanceof t_693_s) || this.p_178_J.contains(pos)) continue;
                    chests.add(pos);
                }
            }
        }
        return chests.stream().min(Comparator.comparingDouble(p -> p.distanceSq(playerPos))).orElse(null);
    }

    private q_1613_l z_4693_k() {
        return switch ((String)this.z_1737_N.J_1907_R()) {
            case "\u0421\u0438\u043b\u0430" -> q_4592_V.C_3528_u;
            case "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c" -> q_4592_V.o_3456_E;
            case "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c" -> q_4592_V.S_3844_E;
            case "\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f" -> q_4592_V.b_3334_n;
            case "\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435", "\u0423\u0440\u043e\u043d" -> q_4592_V.e_1503_j;
            case "\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435", "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c" -> q_4592_V.q_128_P;
            case "\u041f\u043e\u0434\u0432\u043e\u0434\u043d\u043e\u0435 \u0434\u044b\u0445\u0430\u043d\u0438\u0435" -> q_4592_V.U_1258_d;
            case "\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c" -> q_4592_V.i_783_N;
            case "\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u043e\u0435 \u043f\u0430\u0434\u0435\u043d\u0438\u0435" -> q_4592_V.T_2619_F;
            case "\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435" -> q_4592_V.r_2687_x;
            case "\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c" -> q_4592_V.Y_3066_B;
            case "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043f\u0430\u043d\u0446\u0438\u0440\u044c" -> q_4592_V.S_315_z;
            default -> null;
        };
    }

    private y_528_b g_221_o() {
        return switch ((String)this.z_1737_N.J_1907_R()) {
            case "\u0421\u0438\u043b\u0430" -> R_3414_E.z_1737_N;
            case "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c" -> R_3414_E.Q_4569_t;
            case "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c" -> R_3414_E.P_4830_p;
            case "\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f" -> R_3414_E.e_4240_b;
            case "\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435" -> R_3414_E.Z_875_P;
            case "\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435" -> R_3414_E.u_1723_Y;
            case "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c" -> R_3414_E.w_1484_f;
            case "\u041f\u043e\u0434\u0432\u043e\u0434\u043d\u043e\u0435 \u0434\u044b\u0445\u0430\u043d\u0438\u0435" -> R_3414_E.k_2293_S;
            case "\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c" -> R_3414_E.s_956_w;
            case "\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u043e\u0435 \u043f\u0430\u0434\u0435\u043d\u0438\u0435" -> R_3414_E.z_4693_k;
            case "\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435" -> R_3414_E.Y_1740_V;
            case "\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c" -> R_3414_E.G_624_v;
            case "\u0423\u0440\u043e\u043d" -> R_3414_E.H_2857_Y;
            case "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043f\u0430\u043d\u0446\u0438\u0440\u044c" -> R_3414_E.Y_259_p;
            default -> null;
        };
    }

    private boolean e_2887_G() {
        String p = (String)this.z_1737_N.J_1907_R();
        return p.equals("\u0421\u0438\u043b\u0430") || p.equals("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c") || p.equals("\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f") || p.equals("\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435") || p.equals("\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c") || p.equals("\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435") || p.equals("\u0423\u0440\u043e\u043d") || p.equals("\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043f\u0430\u043d\u0446\u0438\u0440\u044c");
    }

    private boolean B_1668_F() {
        String p = (String)this.z_1737_N.J_1907_R();
        return p.equals("\u0421\u0438\u043b\u0430") || p.equals("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c") || p.equals("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c") || p.equals("\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f") || p.equals("\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435") || p.equals("\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c") || p.equals("\u041f\u043e\u0434\u0432\u043e\u0434\u043d\u043e\u0435 \u0434\u044b\u0445\u0430\u043d\u0438\u0435") || p.equals("\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c") || p.equals("\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u043e\u0435 \u043f\u0430\u0434\u0435\u043d\u0438\u0435") || p.equals("\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435") || p.equals("\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c") || p.equals("\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043f\u0430\u043d\u0446\u0438\u0440\u044c");
    }

    private y_528_b g_164_R() {
        y_528_b base = this.g_221_o();
        if (base == null) {
            return null;
        }
        String upgrade = (String)this.v_4276_D.J_1907_R();
        if (upgrade.equals("\u0423\u0441\u0438\u043b\u0438\u0442\u044c") && this.e_2887_G()) {
            return switch ((String)this.z_1737_N.J_1907_R()) {
                case "\u0421\u0438\u043b\u0430" -> R_3414_E.d_2461_k;
                case "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c" -> R_3414_E.t_1786_h;
                case "\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f" -> R_3414_E.d_2427_y;
                case "\u0418\u0441\u0446\u0435\u043b\u0435\u043d\u0438\u0435" -> R_3414_E.c_3005_b;
                case "\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c" -> R_3414_E.M_588_G;
                case "\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435" -> R_3414_E.x_607_J;
                case "\u0423\u0440\u043e\u043d" -> R_3414_E.A_4115_X;
                case "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043f\u0430\u043d\u0446\u0438\u0440\u044c" -> R_3414_E.C_2741_M;
                default -> base;
            };
        }
        if (upgrade.equals("\u041f\u0440\u043e\u0434\u043b\u0438\u0442\u044c") && this.B_1668_F()) {
            return switch ((String)this.z_1737_N.J_1907_R()) {
                case "\u0421\u0438\u043b\u0430" -> R_3414_E.v_4276_D;
                case "\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c" -> R_3414_E.M_182_A;
                case "\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c" -> R_3414_E.h_1847_R;
                case "\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f" -> R_3414_E.n_3318_d;
                case "\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435" -> R_3414_E.v_4262_N;
                case "\u041d\u0435\u0432\u0438\u0434\u0438\u043c\u043e\u0441\u0442\u044c" -> R_3414_E.t_148_a;
                case "\u041f\u043e\u0434\u0432\u043e\u0434\u043d\u043e\u0435 \u0434\u044b\u0445\u0430\u043d\u0438\u0435" -> R_3414_E.q_2307_F;
                case "\u041f\u0440\u044b\u0433\u0443\u0447\u0435\u0441\u0442\u044c" -> R_3414_E.u_2550_I;
                case "\u041c\u0435\u0434\u043b\u0435\u043d\u043d\u043e\u0435 \u043f\u0430\u0434\u0435\u043d\u0438\u0435" -> R_3414_E.g_221_o;
                case "\u041e\u0442\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435" -> R_3414_E.t_4043_B;
                case "\u0421\u043b\u0430\u0431\u043e\u0441\u0442\u044c" -> R_3414_E.T_2506_i;
                case "\u0427\u0435\u0440\u0435\u043f\u0430\u0448\u0438\u0439 \u043c\u0430\u0441\u0442\u0435\u0440" -> R_3414_E.Q_2552_b;
                default -> base;
            };
        }
        return base;
    }

    private void J_1907_R(Y_1060_L brew) {
        for (int i = 0; i < 3; ++i) {
            if (brew.n_1700_B(i).n_1700_B().n_1700_B()) continue;
            this.J_1907_R(i);
        }
    }

    private void n_1700_B(int from, int to) {
        c_2829_Q.c_3005_b.w_1457_N.windowClick(c_2829_Q.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
        c_2829_Q.c_3005_b.w_1457_N.windowClick(c_2829_Q.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, to, 1, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
        c_2829_Q.c_3005_b.w_1457_N.windowClick(c_2829_Q.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.n_1700_B, c_2829_Q.c_3005_b.Y_259_p);
    }

    private void J_1907_R(int slot) {
        c_2829_Q.c_3005_b.w_1457_N.windowClick(c_2829_Q.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.J_1907_R, c_2829_Q.c_3005_b.Y_259_p);
    }

    private int n_1700_B(q_1613_l item) {
        int playerStart;
        a_2900_S container = c_2829_Q.c_3005_b.Y_259_p.H_1873_g;
        int totalSlots = container.P_1922_E.size();
        for (int i = playerStart = totalSlots - 36; i < totalSlots; ++i) {
            if (container.n_1700_B(i).n_1700_B().J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    private boolean n_1700_B(Y_1060_L brew, y_528_b potionType) {
        for (int i = 0; i < 3; ++i) {
            y_528_b p;
            Z_1993_T stack = brew.n_1700_B(i).n_1700_B();
            if (stack.J_1907_R() != q_4592_V.j_2461_G || (p = L_1875_m.G_564_y(stack)) == potionType) continue;
            return false;
        }
        return true;
    }

    private int R_4764_Y(Y_1060_L brew) {
        int playerStart;
        int totalSlots = brew.P_1922_E.size();
        for (int i = playerStart = totalSlots - 36; i < totalSlots; ++i) {
            y_528_b p;
            Z_1993_T stack = brew.n_1700_B(i).n_1700_B();
            if (stack.J_1907_R() != q_4592_V.j_2461_G || (p = L_1875_m.G_564_y(stack)) != R_3414_E.J_1907_R) continue;
            return i;
        }
        int waterCount = 0;
        int glassCount = 0;
        for (int i = playerStart; i < totalSlots; ++i) {
            Z_1993_T stack = brew.n_1700_B(i).n_1700_B();
            if (stack.J_1907_R() == q_4592_V.j_2461_G) {
                ++waterCount;
            }
            if (stack.J_1907_R() != q_4592_V.Y_3588_g) continue;
            ++glassCount;
        }
        if (glassCount > 0) {
            this.G_564_y("\u00a7e\u041f\u0443\u0441\u0442\u044b\u0435 \u043a\u043e\u043b\u0431\u044b: " + glassCount + ", \u043d\u0443\u0436\u043d\u044b \u0441 \u0432\u043e\u0434\u043e\u0439!");
        } else if (waterCount == 0) {
            this.G_564_y("\u00a7c\u0411\u0443\u0442\u044b\u043b\u043e\u043a \u0432\u043e\u0434\u044b: 0, \u0441\u043b\u043e\u0442\u044b: " + playerStart + "-" + (totalSlots - 1));
        }
        return -1;
    }

    private boolean X_933_l() {
        for (int i = 0; i < c_2829_Q.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            if (!this.J_1907_R(c_2829_Q.c_3005_b.Y_259_p.l_1268_F.s_956_w(i))) continue;
            return true;
        }
        return false;
    }

    private boolean J_1907_R(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        if (item == q_4592_V.j_2461_G) {
            y_528_b p = L_1875_m.G_564_y(stack);
            return p != R_3414_E.J_1907_R;
        }
        return item == q_4592_V.g_2492_v || item == q_4592_V.K_2911_x;
    }

    private void G_564_y(String message) {
        if (this.q_4610_l.t_148_a().booleanValue()) {
            v_1900_v.n_1700_B("\u00a77[\u00a7bAutoBrew\u00a77] \u00a7f" + message, new Object[0]);
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B();
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B();
        public static final /* enum */ n_1700_B s_956_w = new n_1700_B();
        public static final /* enum */ n_1700_B u_2550_I = new n_1700_B();
        public static final /* enum */ n_1700_B M_588_G = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_4830_p;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_4830_p.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G};
        }

        static {
            P_4830_p = lightning.product.c_2829_Q$n_1700_B.n_1700_B();
        }
    }
}

