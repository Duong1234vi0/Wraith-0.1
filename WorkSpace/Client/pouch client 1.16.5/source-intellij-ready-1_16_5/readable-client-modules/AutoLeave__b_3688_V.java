/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.D_3612_q;
import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.q_366_O;
import lightning.product.x_2635_q;
import lightning.product.y_2603_k;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;

public class b_3688_V
extends X_3546_T {
    private final N_4463_r v_4262_N = new N_4463_r("\u041b\u0438\u0432\u0430\u0442\u044c \u0435\u0441\u043b\u0438", new p_1977_n("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", true), new p_1977_n("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a", true), new p_1977_n("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435", true));
    private final q_366_O w_1484_f = new q_366_O("\u0422\u0438\u043f \u0443\u0445\u043e\u0434\u0430", "\u0421\u043f\u0430\u0432\u043d", () -> this.v_4262_N.J_1907_R("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.v_4262_N.J_1907_R("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.v_4262_N.J_1907_R("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false, "\u0421\u043f\u0430\u0432\u043d", "\u0414\u043e\u043c\u043e\u0439", "\u0425\u0430\u0431");
    private final p_1977_n t_148_a = new p_1977_n("\u041b\u0438\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0432 \u043a\u043e\u043d\u0446\u0435 \u043f\u0432\u043f", false, () -> this.v_4262_N.J_1907_R("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.v_4262_N.J_1907_R("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.v_4262_N.J_1907_R("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false);
    private final I_686_h s_956_w = new I_686_h("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435", 15.0f, 1.0f, 20.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f"));
    private final I_686_h u_2550_I = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441", 10.0f, 1.0f, 50.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a"));
    private final p_1977_n M_588_G = new p_1977_n("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0431\u0430\u0440\u0438\u0442\u043e\u043d", true, () -> this.v_4262_N.J_1907_R("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") != false || this.v_4262_N.J_1907_R("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a") != false || this.v_4262_N.J_1907_R("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435") != false);

    public b_3688_V() {
        super("AutoLeave", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        List<D_3612_q> vanishedStaff;
        if (b_3688_V.c_3005_b.Y_259_p == null || b_3688_V.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("\u041c\u0430\u043b\u043e \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f").booleanValue() && b_3688_V.c_3005_b.Y_259_p.g_46_E() <= ((Float)this.s_956_w.J_1907_R()).floatValue()) {
            this.h_1847_R();
            return;
        }
        if (this.v_4262_N.J_1907_R("\u0420\u044f\u0434\u043e\u043c \u0438\u0433\u0440\u043e\u043a").booleanValue()) {
            List<X_4340_E> players = b_3688_V.c_3005_b.Y_601_j.N_4405_n();
            for (a_3913_L a_3913_L2 : players) {
                if (a_3913_L2 == b_3688_V.c_3005_b.Y_259_p || o_148_s.Y_601_j().v_4262_N().R_4764_Y(a_3913_L2.y_4642_Y().getName()) || !(b_3688_V.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2) <= ((Float)this.u_2550_I.J_1907_R()).floatValue())) continue;
                this.h_1847_R();
                return;
            }
        }
        if (this.v_4262_N.J_1907_R("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440 \u0432 \u0432\u0430\u043d\u0438\u0448\u0435").booleanValue() && !(vanishedStaff = x_2635_q.J_1907_R()).isEmpty()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        if (this.t_148_a.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        if (this.M_588_G.t_148_a().booleanValue()) {
            String prefix = (String)BaritoneAPI.getSettings().prefix.value;
            if (BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing()) {
                b_3688_V.c_3005_b.Y_259_p.n_1700_B(prefix + "stop");
            }
        }
        switch ((String)this.w_1484_f.J_1907_R()) {
            case "\u0421\u043f\u0430\u0432\u043d": {
                b_3688_V.c_3005_b.Y_259_p.n_1700_B("/spawn");
                break;
            }
            case "\u0414\u043e\u043c\u043e\u0439": {
                b_3688_V.c_3005_b.Y_259_p.n_1700_B("/home");
                break;
            }
            case "\u0425\u0430\u0431": {
                b_3688_V.c_3005_b.Y_259_p.n_1700_B("/hub");
            }
        }
        this.R_4764_Y();
    }
}

