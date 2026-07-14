/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.L_2735_S;
import lightning.product.N_3408_T;
import lightning.product.N_4463_r;
import lightning.product.O_2864_C;
import lightning.product.O_3045_w;
import lightning.product.Q_2753_H;
import lightning.product.S_2856_u;
import lightning.product.W_2398_E;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_2432_k;
import lightning.product.b_1205_t;
import lightning.product.c_902_l;
import lightning.product.e_2866_D;
import lightning.product.m_4093_W;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.t_3138_Z;
import lightning.product.v_1900_v;
import lightning.product.v_3961_f;
import lightning.product.y_2603_k;

public class S_2721_L
extends X_3546_T {
    private static final double v_4262_N = 0.5;
    private final p_1977_n w_1484_f = new p_1977_n("\u041b\u043e\u0433 \u0432 \u0447\u0430\u0442", true);
    private final N_4463_r t_148_a = new N_4463_r("\u041e\u0442\u043a\u0430\u0442 \u0434\u043b\u044f", new p_1977_n("Flight", true), new p_1977_n("Speed", true), new p_1977_n("Phase", true), new p_1977_n("Timer", true), new p_1977_n("GuiMove", true), new p_1977_n("Blink", true), new p_1977_n("HighJump", true));
    private final N_4463_r s_956_w = new N_4463_r("\u0412\u0435\u043b\u043e\u0441\u0438\u0442\u0438 \u0441\u0435\u0439\u0444", new p_1977_n("NoVelocity", true));

    public S_2721_L() {
        super("FlagDetector", y_2603_k.P_1922_E);
        this.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (!e.J_1907_R() || S_2721_L.c_3005_b.Y_259_p == null) {
            return;
        }
        t_3138_Z<?> packet = e.G_564_y();
        String name = packet.getClass().getSimpleName();
        if (packet instanceof b_1205_t) {
            b_1205_t p = (b_1205_t)packet;
            double targetX = p.w_1484_f().contains((Object)b_1205_t.n_1700_B.n_1700_B) ? S_2721_L.c_3005_b.Y_259_p.O_3598_v() + p.J_1907_R() : p.J_1907_R();
            double targetY = p.w_1484_f().contains((Object)b_1205_t.n_1700_B.J_1907_R) ? S_2721_L.c_3005_b.Y_259_p.X_2960_b() + p.R_4764_Y() : p.R_4764_Y();
            double targetZ = p.w_1484_f().contains((Object)b_1205_t.n_1700_B.R_4764_Y) ? S_2721_L.c_3005_b.Y_259_p.l_2647_k() + p.G_564_y() : p.G_564_y();
            e_2866_D current = S_2721_L.c_3005_b.Y_259_p.s_4990_V();
            double distance = current.u_1723_Y(new e_2866_D(targetX, targetY, targetZ));
            if (distance >= 0.5) {
                this.n_1700_B(name, "\u041e\u0442\u043a\u0430\u0442 \u043f\u043e\u0437\u0438\u0446\u0438\u0438: " + String.format("%.1f \u0431\u043b\u043e\u043a\u043e\u0432", distance));
                this.h_1847_R();
            }
            return;
        }
        if (packet instanceof m_4093_W) {
            double vz;
            double vy;
            m_4093_W p = (m_4093_W)packet;
            if (p.J_1907_R() != S_2721_L.c_3005_b.Y_259_p.j_276_v()) {
                return;
            }
            double vx = (double)p.R_4764_Y() / 8000.0;
            double len = Math.sqrt(vx * vx + (vy = (double)p.G_564_y() / 8000.0) * vy + (vz = (double)p.P_1922_E() / 8000.0) * vz);
            if (len < 0.01 && S_2721_L.c_3005_b.Y_259_p.I_4348_c().u_1723_Y() > 0.05) {
                this.n_1700_B(name, "\u041e\u0431\u043d\u0443\u043b\u0435\u043d\u0438\u0435 \u0441\u043a\u043e\u0440\u043e\u0441\u0442\u0438");
                this.Q_4569_t();
            }
        }
    }

    private void h_1847_R() {
        c_902_l mgr = o_148_s.Y_601_j().J_1907_R();
        boolean prev = X_3546_T.P_4830_p();
        X_3546_T.G_564_y(true);
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("Flight"))) {
            this.n_1700_B(mgr.n_1700_B(N_3408_T.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("Speed"))) {
            this.n_1700_B(mgr.n_1700_B(v_3961_f.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("Phase"))) {
            this.n_1700_B(mgr.n_1700_B(O_2864_C.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("Timer"))) {
            this.n_1700_B(mgr.n_1700_B(L_2735_S.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("GuiMove"))) {
            this.n_1700_B(mgr.n_1700_B(a_2432_k.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("Blink"))) {
            this.n_1700_B(mgr.n_1700_B(S_2856_u.class));
        }
        if (Boolean.TRUE.equals(this.t_148_a.J_1907_R("HighJump"))) {
            this.n_1700_B(mgr.n_1700_B(W_2398_E.class));
        }
        X_3546_T.G_564_y(prev);
    }

    private void Q_4569_t() {
        if (!Boolean.TRUE.equals(this.s_956_w.J_1907_R("NoVelocity"))) {
            return;
        }
        X_3546_T m = o_148_s.Y_601_j().J_1907_R().n_1700_B(O_3045_w.class);
        if (m == null) {
            return;
        }
        boolean prev = X_3546_T.P_4830_p();
        X_3546_T.G_564_y(true);
        this.n_1700_B(m);
        X_3546_T.G_564_y(prev);
    }

    private void n_1700_B(X_3546_T m) {
        if (m != null && m.w_1484_f()) {
            m.n_1700_B(false);
        }
    }

    private void n_1700_B(String packetName, String reason) {
        if (!this.w_1484_f.t_148_a().booleanValue()) {
            return;
        }
        v_1900_v.n_1700_B("\u00a7c[Flag] \u00a77\u041f\u0430\u043a\u0435\u0442: \u00a7f" + packetName + " \u00a77\u2014 " + reason, new Object[0]);
    }
}

