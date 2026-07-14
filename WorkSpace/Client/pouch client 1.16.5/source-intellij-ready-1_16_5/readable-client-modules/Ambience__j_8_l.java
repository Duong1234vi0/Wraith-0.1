/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.time.LocalTime;
import lightning.product.I_686_h;
import lightning.product.O_922_L;
import lightning.product.Q_2753_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.k_596_g;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_2262_k;
import lightning.product.q_366_O;
import lightning.product.s_4405_m;
import lightning.product.u_796_y;
import lightning.product.y_2603_k;

public class j_8_l
extends X_3546_T {
    public static q_366_O v_4262_N = new q_366_O("\u0412\u0440\u0435\u043c\u044f", "\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c", "\u0420\u0430\u0441\u0441\u0432\u0435\u0442", "\u0423\u0442\u0440\u043e", "\u0414\u0435\u043d\u044c", "\u0412\u0435\u0447\u0435\u0440", "\u0417\u0430\u0445\u043e\u0434 \u0441\u043e\u043b\u043d\u0446\u0430", "\u041d\u043e\u0447\u044c", "\u0412\u0440\u0435\u043c\u044f \u0438\u0437 \u0440\u0435\u0430\u043b\u044c\u043d\u043e\u0439 \u0436\u0438\u0437\u043d\u0438", "\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c");
    public static q_366_O w_1484_f = new q_366_O("\u0422\u0443\u043c\u0430\u043d", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "Adaptive", "\u041e\u0447\u0438\u0441\u0442\u0438\u0442\u044c", "\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c");
    public static q_366_O t_148_a = new q_366_O("\u0426\u0432\u0435\u0442", "\u0421\u0432\u043e\u0439", "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    public static h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0442\u0443\u043c\u0430\u043d\u0430", false, -1, () -> w_1484_f.J_1907_R("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c") && t_148_a.J_1907_R("\u0421\u0432\u043e\u0439"));
    public static I_686_h u_2550_I = new I_686_h("\u041a\u043e\u043d\u0435\u0446 \u0442\u0443\u043c\u0430\u043d\u0430", 1.0f, 0.1f, 1.5f, 0.1f, () -> w_1484_f.J_1907_R("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c"));
    public static I_686_h M_588_G = new I_686_h("\u041d\u0430\u0447\u0430\u043b\u043e \u0442\u0443\u043c\u0430\u043d\u0430", 0.5f, 0.1f, 1.5f, 0.1f, () -> w_1484_f.J_1907_R("\u041f\u0435\u0440\u0435\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0438\u0442\u044c"), u_2550_I);
    public static I_686_h P_4830_p = new I_686_h("Adaptive: \u0420\u0430\u0434\u0438\u0443\u0441 \u0442\u0443\u043c\u0430\u043d\u0430", 42.0f, 8.0f, 140.0f, 1.0f, () -> w_1484_f.J_1907_R("Adaptive"));
    public static I_686_h h_1847_R = new I_686_h("Adaptive: \u0421\u0438\u043b\u0430 \u0442\u0443\u043c\u0430\u043d\u0430", 0.85f, 0.1f, 1.5f, 0.05f, () -> w_1484_f.J_1907_R("Adaptive"));
    public static p_1977_n Q_4569_t = new p_1977_n("Adaptive: \u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0434\u043e\u0436\u0434\u044c", false, () -> w_1484_f.J_1907_R("Adaptive"));
    public static I_686_h M_182_A = new I_686_h("Adaptive: \u041c\u0438\u043d \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u0432 \u0434\u043e\u0436\u0434\u044c", 0.1f, 0.0f, 1.0f, 0.05f, () -> w_1484_f.J_1907_R("Adaptive") && Q_4569_t.t_148_a() != false);
    public static q_366_O t_1786_h = new q_366_O("\u041d\u0435\u0431\u043e", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041a\u043e\u0441\u043c\u043e\u0441", "\u041f\u043b\u0430\u0437\u043c\u0430", "Balatro", "\u041b\u0435\u0442\u043e", "\u0421\u0430\u043a\u0443\u0440\u0430", "Aurora", "\u042d\u0444\u0438\u0440");
    public static q_366_O N_4405_n = new q_366_O("\u041b\u0435\u0442\u043e: \u0432\u0438\u0434", "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", j_8_l::Q_4569_t, "\u041e\u0431\u044b\u0447\u043d\u043e\u0435", "\u041d\u043e\u0447\u043d\u043e\u0435");
    public static I_686_h w_1457_N = new I_686_h("\u0428\u0435\u0439\u0434.\u043d\u0435\u0431\u043e: \u043c\u0430\u0441\u0448\u0442\u0430\u0431", 1.0f, 0.2f, 3.0f, 0.05f, j_8_l::t_1786_h);
    public static I_686_h Y_601_j = new I_686_h("\u0428\u0435\u0439\u0434.\u043d\u0435\u0431\u043e: \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 1.0f, 0.0f, 3.0f, 0.05f, j_8_l::t_1786_h);
    private final p_1977_n Y_259_p = new p_1977_n("\u0418\u0437\u043c\u0435\u043d\u044f\u0442\u044c \u043d\u0430\u0441\u044b\u0449\u0435\u043d\u043d\u043e\u0441\u0442\u044c \u0438\u0433\u0440\u044b", true);
    private final I_686_h Q_2552_b = new I_686_h("\u041d\u0430\u0441\u044b\u0449\u0435\u043d\u043d\u043e\u0441\u0442\u044c", 1.0f, 0.0f, 2.0f, 0.1f, this.Y_259_p::t_148_a);

    public j_8_l() {
        super("Ambience", y_2603_k.R_4764_Y);
        this.n_1700_B(v_4262_N, t_1786_h, N_4405_n, w_1457_N, Y_601_j, this.Y_259_p, this.Q_2552_b, w_1484_f, t_148_a, s_956_w, M_588_G, u_2550_I, P_4830_p, h_1847_R, Q_4569_t, M_182_A);
    }

    public static boolean h_1847_R() {
        if (j_8_l.c_3005_b.Y_601_j == null || o_148_s.Y_601_j() == null) {
            return false;
        }
        j_8_l m = o_148_s.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && t_1786_h.J_1907_R("\u041a\u043e\u0441\u043c\u043e\u0441");
    }

    public static boolean Q_4569_t() {
        return t_1786_h.J_1907_R("\u041b\u0435\u0442\u043e");
    }

    public static boolean M_182_A() {
        return j_8_l.Q_4569_t() && N_4405_n.J_1907_R("\u041d\u043e\u0447\u043d\u043e\u0435");
    }

    public static boolean t_1786_h() {
        if (j_8_l.c_3005_b.Y_601_j == null || o_148_s.Y_601_j() == null) {
            return false;
        }
        j_8_l m = o_148_s.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && (t_1786_h.J_1907_R("\u041f\u043b\u0430\u0437\u043c\u0430") || t_1786_h.J_1907_R("Balatro") || t_1786_h.J_1907_R("\u041b\u0435\u0442\u043e") || t_1786_h.J_1907_R("\u0421\u0430\u043a\u0443\u0440\u0430") || t_1786_h.J_1907_R("Aurora") || t_1786_h.J_1907_R("\u042d\u0444\u0438\u0440") || t_1786_h.J_1907_R("\u041c\u0435\u0442\u0435\u043b\u044c"));
    }

    public static boolean N_4405_n() {
        if (j_8_l.c_3005_b.Y_601_j == null || o_148_s.Y_601_j() == null) {
            return false;
        }
        j_8_l m = o_148_s.Y_601_j().J_1907_R().v_4262_N;
        return m != null && m.w_1484_f() && !t_1786_h.J_1907_R("\u041e\u0431\u044b\u0447\u043d\u043e\u0435");
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (e.G_564_y() instanceof q_2262_k && !v_4262_N.J_1907_R("\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c")) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!((String)v_4262_N.J_1907_R()).equals("\u041d\u0435 \u043c\u0435\u043d\u044f\u0442\u044c")) {
            long time;
            if (((String)v_4262_N.J_1907_R()).equals("\u0412\u0440\u0435\u043c\u044f \u0438\u0437 \u0440\u0435\u0430\u043b\u044c\u043d\u043e\u0439 \u0436\u0438\u0437\u043d\u0438")) {
                time = this.Y_601_j();
            } else {
                time = switch ((String)v_4262_N.J_1907_R()) {
                    case "\u0420\u0430\u0441\u0441\u0432\u0435\u0442" -> 23000L;
                    case "\u0423\u0442\u0440\u043e" -> 1000L;
                    case "\u0414\u0435\u043d\u044c" -> 6000L;
                    case "\u0412\u0435\u0447\u0435\u0440" -> 12000L;
                    case "\u0417\u0430\u0445\u043e\u0434 \u0441\u043e\u043b\u043d\u0446\u0430" -> 13000L;
                    case "\u041d\u043e\u0447\u044c" -> 18000L;
                    default -> j_8_l.c_3005_b.Y_601_j.Z_976_R();
                };
            }
            j_8_l.c_3005_b.Y_601_j.J_1907_R(time);
        }
    }

    private boolean w_1457_N() {
        if (j_8_l.c_3005_b.Y_1740_V instanceof k_596_g || j_8_l.c_3005_b.Y_1740_V instanceof O_922_L) {
            return false;
        }
        return j_8_l.c_3005_b.Y_601_j != null;
    }

    @Y_1740_V
    public void n_1700_B(u_796_y e) {
        if (!this.Y_259_p.t_148_a().booleanValue()) {
            return;
        }
        if (!this.w_1457_N()) {
            return;
        }
        if (e.n_1700_B == u_796_y.n_1700_B.n_1700_B) {
            s_4405_m.N_4405_n.J_1907_R();
            s_4405_m.N_4405_n.n_1700_B("texture", new int[]{0});
            s_4405_m.N_4405_n.n_1700_B("saturation", ((Float)this.Q_2552_b.J_1907_R()).floatValue());
        }
        if (e.n_1700_B == u_796_y.n_1700_B.J_1907_R) {
            s_4405_m.N_4405_n.R_4764_Y();
        }
    }

    private long Y_601_j() {
        int seconds;
        int minutes;
        LocalTime now = LocalTime.now();
        int hours = now.getHour();
        int totalSeconds = hours * 3600 + (minutes = now.getMinute()) * 60 + (seconds = now.getSecond());
        int offsetSeconds = (totalSeconds - 21600) % 86400;
        if (offsetSeconds < 0) {
            offsetSeconds += 86400;
        }
        return (long)((double)offsetSeconds / 86400.0 * 24000.0);
    }
}

