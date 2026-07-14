/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_3457_f;
import lightning.product.N_4263_v;
import lightning.product.V_772_m;
import lightning.product.X_3546_T;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.p_1977_n;
import lightning.product.v_1900_v;
import lightning.product.y_2603_k;

public class K_4793_q
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e", true);

    public K_4793_q() {
        super("LeaveTracker", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(I_3457_f event) {
        if (K_4793_q.c_3005_b.Y_259_p == null || K_4793_q.c_3005_b.Y_601_j == null) {
            return;
        }
        N_4263_v n_4263_v = event.J_1907_R();
        if (n_4263_v instanceof X_4340_E) {
            X_4340_E clientPlayer = (X_4340_E)n_4263_v;
            if (clientPlayer instanceof V_772_m) {
                return;
            }
            if (this.v_4262_N.t_148_a().booleanValue() && K_4793_q.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)clientPlayer) < 100.0f) {
                return;
            }
            String name = clientPlayer.c_4037_x();
            if (name == null || name.contains("CIT-")) {
                return;
            }
            String x = String.format("%.1f", clientPlayer.O_3598_v());
            String y = String.format("%.1f", clientPlayer.X_2960_b());
            String z = String.format("%.1f", clientPlayer.l_2647_k());
            v_1900_v.n_1700_B("\u0418\u0433\u0440\u043e\u043a %s \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043b\u0441\u044f \u043d\u0430 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b x: %s y: %s z: %s", name, x, y, z);
        }
    }
}

