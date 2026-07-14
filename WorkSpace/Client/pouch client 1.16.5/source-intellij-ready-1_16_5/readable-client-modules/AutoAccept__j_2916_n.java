/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.Q_2753_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.k_1320_C;
import lightning.product.k_1836_E;
import lightning.product.n_473_l;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.t_3138_Z;
import lightning.product.y_2603_k;

public class j_2916_n
extends X_3546_T {
    private static String[] v_4262_N = new String[]{"\u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", "has requested teleport", "\u043f\u0440\u043e\u0441\u0438\u0442 \u043a \u0432\u0430\u043c \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", "\u0437\u0430\u043f\u0440\u0430\u0448\u0438\u0432\u0430\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043a \u0432\u0430\u043c"};
    private final p_1977_n w_1484_f = new p_1977_n("\u041f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u044c \u0437\u0430\u043f\u0440\u043e\u0441\u044b \u0442\u043e\u043b\u044c\u043a\u043e \u043e\u0442 \u0434\u0440\u0443\u0437\u0435\u0439", true);

    public j_2916_n() {
        super("AutoAccept", y_2603_k.P_1922_E);
        this.n_1700_B(this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        t_3138_Z<?> t_3138_Z2;
        if (j_2916_n.c_3005_b.Y_259_p == null || j_2916_n.c_3005_b.Y_601_j == null || q_3115_L.n_1700_B() || !((t_3138_Z2 = event.G_564_y()) instanceof k_1836_E)) {
            return;
        }
        k_1836_E chatPacket = (k_1836_E)t_3138_Z2;
        String message = chatPacket.J_1907_R().getString().toLowerCase();
        if (Arrays.stream(v_4262_N).anyMatch(message::contains)) {
            if (this.w_1484_f.t_148_a().booleanValue() && !this.R_4764_Y(message)) {
                return;
            }
            j_2916_n.c_3005_b.Y_259_p.n_1700_B("/tpaccept");
        }
    }

    private boolean R_4764_Y(String message) {
        k_1320_C nameProtect = (k_1320_C)o_148_s.Y_601_j().J_1907_R().n_1700_B(k_1320_C.class);
        if (nameProtect.w_1484_f() && k_1320_C.v_4262_N.t_148_a().booleanValue() && message.contains("protected")) {
            return true;
        }
        for (n_473_l.n_1700_B friend : o_148_s.Y_601_j().v_4262_N().P_4830_p()) {
            String friendName = friend.n_1700_B();
            if (!message.contains(friendName.toLowerCase())) continue;
            return true;
        }
        return false;
    }
}

