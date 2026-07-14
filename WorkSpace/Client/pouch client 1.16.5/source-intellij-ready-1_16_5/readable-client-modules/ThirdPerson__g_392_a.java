/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import lightning.product.F_1446_q;
import lightning.product.I_686_h;
import lightning.product.L_1733_J;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.d_2169_p;
import lightning.product.d_4412_Z;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.l_1268_F;
import lightning.product.o_148_s;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.r_4790_y;
import lightning.product.t_1920_R;
import lightning.product.y_2603_k;
import org.joml.Vector2f;

public class g_392_a
extends X_3546_T {
    private q_366_O w_1484_f = new q_366_O("\u0420\u0435\u0436\u0438\u043c \u043e\u0431\u0437\u043e\u0440\u0430", "\u0421\u0437\u0430\u0434\u0438", "\u0421\u043f\u0435\u0440\u0435\u0434\u0438", "\u0421\u0437\u0430\u0434\u0438");
    private I_686_h t_148_a = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 4.0f, 1.0f, 10.0f, 0.1f);
    public q_3206_W v_4262_N = new q_3206_W("\u0411\u0438\u043d\u0434");
    private t_1920_R s_956_w = t_1920_R.n_1700_B;
    private boolean u_2550_I;
    private boolean M_588_G;
    private Vector2f P_4830_p = new Vector2f(0.0f, 0.0f);

    public g_392_a() {
        super("ThirdPerson", y_2603_k.R_4764_Y);
        this.n_1700_B(this.w_1484_f, this.v_4262_N, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (o_148_s.Y_601_j().J_1907_R().n_1700_B(d_4412_Z.class).w_1484_f()) {
            return;
        }
        if (e.n_1700_B() == ((Integer)this.v_4262_N.J_1907_R()).intValue()) {
            this.M_588_G = e.J_1907_R();
        }
    }

    @Y_1740_V
    public void n_1700_B(L_1733_J e) {
        if (this.u_2550_I) {
            e.n_1700_B(((Float)this.t_148_a.J_1907_R()).floatValue());
        }
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (!this.u_2550_I) {
            this.s_956_w = g_392_a.c_3005_b.P_4830_p.P_4830_p();
            this.P_4830_p.x = d_2169_p.J_1907_R();
            this.P_4830_p.y = d_2169_p.R_4764_Y();
        }
        if (this.M_588_G && g_392_a.c_3005_b.Y_1740_V == null) {
            r_4790_y.n_1700_B(new F_1446_q(g_392_a.c_3005_b.Y_259_p.p_178_J, g_392_a.c_3005_b.Y_259_p.f_4016_n), 360.0f, 1, 1);
            if (this.w_1484_f.J_1907_R("\u0421\u0437\u0430\u0434\u0438")) {
                g_392_a.c_3005_b.P_4830_p.n_1700_B(t_1920_R.J_1907_R);
            } else {
                g_392_a.c_3005_b.P_4830_p.n_1700_B(t_1920_R.R_4764_Y);
            }
            this.u_2550_I = true;
        } else if (this.u_2550_I) {
            d_2169_p.n_1700_B(this.P_4830_p.x);
            d_2169_p.J_1907_R(this.P_4830_p.y);
            g_392_a.c_3005_b.P_4830_p.n_1700_B(this.s_956_w);
            this.u_2550_I = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(l_1268_F e) {
        if (this.M_588_G) {
            e.n_1700_B(true);
        }
    }
}

