/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2226_Q;
import lightning.product.D_4024_W;
import lightning.product.I_686_h;
import lightning.product.J_2751_l;
import lightning.product.Q_2753_H;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.h_3859_C;
import lightning.product.i_4434_b;
import lightning.product.k_1836_E;
import lightning.product.q_3115_L;
import lightning.product.q_3206_W;
import lightning.product.t_3138_Z;
import lightning.product.v_1900_v;
import lightning.product.x_612_B;
import lightning.product.y_2603_k;

public class r_3963_q
extends X_3546_T {
    private final I_686_h w_1484_f = new I_686_h("\u0413\u0440\u0438\u0444", 1.0f, 1.0f, 54.0f, 1.0f);
    private final q_3206_W t_148_a = new q_3206_W("\u041a\u043b\u0430\u0432\u0438\u0448\u0430 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u044f");
    private final V_4557_X s_956_w = new V_4557_X();
    private boolean u_2550_I = false;
    boolean v_4262_N = false;

    public r_3963_q() {
        super("AutoContract", y_2603_k.P_1922_E);
        this.n_1700_B(this.w_1484_f, this.t_148_a);
    }

    @Override
    public void n_1700_B() {
        if (x_612_B.J_1907_R != null) {
            h_3859_C.n_1700_B(((Float)this.w_1484_f.J_1907_R()).intValue());
            h_3859_C.n_1700_B();
        }
        super.n_1700_B();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        Object message;
        k_1836_E packet;
        t_3138_Z<?> t_3138_Z2;
        if (q_3115_L.n_1700_B()) {
            v_1900_v.n_1700_B("\u0412\u044b \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0435\u0441\u044c \u0432 PVP-\u0440\u0435\u0436\u0438\u043c\u0435!", new Object[0]);
            this.R_4764_Y();
        }
        if (x_612_B.J_1907_R == null) {
            v_1900_v.n_1700_B("\u0423\u043a\u0430\u0436\u0438\u0442\u0435 \u0446\u0435\u043b\u044c! .autocontract <name> | .act <name>", new Object[0]);
            this.R_4764_Y();
        }
        if (e.G_564_y() instanceof J_2751_l) {
            this.v_4262_N = true;
        }
        if ((t_3138_Z2 = e.G_564_y()) instanceof k_1836_E) {
            packet = (k_1836_E)t_3138_Z2;
            message = D_4024_W.n_1700_B(packet.J_1907_R().getString());
            if (((String)message).contains("\u0412\u0430\u0448\u0430 \u0446\u0435\u043b\u044c") && ((String)message).contains(x_612_B.J_1907_R)) {
                v_1900_v.n_1700_B("\u041d\u0430\u0439\u0434\u0435\u043d \u043d\u0443\u0436\u043d\u044b\u0439 \u0438\u0433\u0440\u043e\u043a", new Object[0]);
                this.R_4764_Y();
            }
            if (((String)message).contains("\u0412\u0430\u0448\u0430 \u0446\u0435\u043b\u044c") || ((String)message).contains("\u041d\u0435 \u0441\u043f\u0430\u043c\u044c!") || ((String)message).contains("\u041d\u0435 \u043f\u043e\u043b\u0443\u0447\u0438\u043b\u043e\u0441\u044c")) {
                this.v_4262_N = false;
                this.u_2550_I = true;
                this.s_956_w.n_1700_B();
            }
        }
        if ((message = e.G_564_y()) instanceof k_1836_E && (((String)(message = D_4024_W.n_1700_B((packet = (k_1836_E)message).J_1907_R().getString()))).contains("\u041a \u0441\u043e\u0436\u0430\u043b\u0435\u043d\u0438\u044e \u0441\u0435\u0440\u0432\u0435\u0440 \u043f\u0435\u0440\u0435\u043f\u043e\u043b\u043d\u0435\u043d") || ((String)message).contains("\u041f\u043e\u0434\u043e\u0436\u0434\u0438\u0442\u0435 20 \u0441\u0435\u043a\u0443\u043d\u0434!") || ((String)message).contains("\u0431\u043e\u043b\u044c\u0448\u043e\u0439 \u043f\u043e\u0442\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432"))) {
            this.u_2550_I = true;
            this.s_956_w.n_1700_B();
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.u_2550_I && this.s_956_w.J_1907_R(600L)) {
            h_3859_C.n_1700_B(((Float)this.w_1484_f.J_1907_R()).intValue());
            h_3859_C.n_1700_B();
            this.u_2550_I = false;
        }
        boolean check = true;
        for (A_2226_Q info : c_3005_b.k_2293_S().P_1922_E()) {
            if (!info.n_1700_B().getName().equals(x_612_B.J_1907_R)) continue;
            check = false;
            break;
        }
        if (this.v_4262_N && !check) {
            r_3963_q.c_3005_b.Y_259_p.n_1700_B("/contract get");
            this.v_4262_N = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (((Integer)this.t_148_a.J_1907_R()).intValue() == e.n_1700_B() && e.J_1907_R()) {
            this.R_4764_Y();
        }
    }
}

