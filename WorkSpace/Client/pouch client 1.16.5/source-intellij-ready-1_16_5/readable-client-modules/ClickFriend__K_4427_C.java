/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.i_4434_b;
import lightning.product.o_148_s;
import lightning.product.q_3206_W;
import lightning.product.v_1900_v;
import lightning.product.y_2603_k;

public class K_4427_C
extends X_3546_T {
    private final q_3206_W v_4262_N = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0432\u0437\u0430\u0438\u043c\u043e\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044f");

    public K_4427_C() {
        super("ClickFriend", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        N_4263_v n_4263_v;
        if (e.n_1700_B() == ((Integer)this.v_4262_N.J_1907_R()).intValue() && e.J_1907_R() && (n_4263_v = K_4427_C.c_3005_b.q_2307_F) instanceof a_3913_L) {
            a_3913_L entity = (a_3913_L)n_4263_v;
            String entityName = entity.O_1309_Q().getString();
            if (o_148_s.Y_601_j().v_4262_N().R_4764_Y(entityName)) {
                o_148_s.Y_601_j().v_4262_N().J_1907_R(entityName);
                v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.Q_2552_b) + entityName + " \u0423\u0434\u0430\u043b\u0435\u043d \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u0434\u0440\u0443\u0437\u0435\u0439!", new Object[0]);
            } else {
                o_148_s.Y_601_j().v_4262_N().n_1700_B(entityName);
                v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.Q_2552_b) + entityName + " \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439!", new Object[0]);
            }
        }
    }
}

