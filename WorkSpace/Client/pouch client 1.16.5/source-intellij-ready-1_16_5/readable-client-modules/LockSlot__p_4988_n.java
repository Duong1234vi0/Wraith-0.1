/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_2848_I;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.q_4592_V;
import lightning.product.v_1900_v;
import lightning.product.y_2603_k;

public class p_4988_n
extends X_3546_T {
    private final p_1977_n w_1484_f = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043f\u0432\u043f", true);
    public final N_4463_r v_4262_N = new N_4463_r("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043b\u043e\u0442\u044b", new p_1977_n("1", false), new p_1977_n("2", false), new p_1977_n("3", false), new p_1977_n("4", false), new p_1977_n("5", false), new p_1977_n("6", false), new p_1977_n("7", false), new p_1977_n("8", false), new p_1977_n("9", false));

    public p_4988_n() {
        super("LockSlot", y_2603_k.G_564_y);
        this.n_1700_B(this.w_1484_f, this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(h_2848_I e) {
        if (p_4988_n.c_3005_b.Y_259_p == null || p_4988_n.c_3005_b.Y_259_p.l_1268_F == null || p_4988_n.c_3005_b.Y_259_p.A_2714_y().J_1907_R() == q_4592_V.n_1700_B) {
            return;
        }
        if (this.w_1484_f.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        int currentSlot = e.J_1907_R();
        p_1977_n setting = this.v_4262_N.n_1700_B(currentSlot);
        if (setting.t_148_a().booleanValue()) {
            e.n_1700_B(true);
            v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0412\u044b\u0431\u0440\u043e\u0441 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0438\u0437 \u0441\u043b\u043e\u0442\u0430 " + (currentSlot + 1) + " \u0431\u044b\u043b \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d", new Object[0]);
        }
    }
}

