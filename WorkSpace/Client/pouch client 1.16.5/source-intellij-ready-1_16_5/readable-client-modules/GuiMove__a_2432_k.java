/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lightning.product.D_590_W;
import lightning.product.P_3201_s;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.Q_2753_H;
import lightning.product.Q_4113_P;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.h_4412_P;
import lightning.product.k_2603_m;
import lightning.product.m_1621_v;
import lightning.product.q_3115_L;
import lightning.product.q_366_O;
import lightning.product.t_2037_T;
import lightning.product.t_3138_Z;
import lightning.product.u_925_K;
import lightning.product.v_4727_z;
import lightning.product.y_1945_D;
import lightning.product.y_2603_k;
import lightning.product.z_3427_G;

public class a_2432_k
extends X_3546_T {
    public q_366_O v_4262_N = new q_366_O("\u041e\u0431\u0445\u043e\u0434", "Vanilla", "Vanilla", "Reallyworld", "Funtime", "Holyworld", "SpookyTime", "LonyGrief");
    private final List<t_3138_Z<?>> u_2550_I = new ArrayList();
    private int M_588_G = 0;
    public boolean w_1484_f = false;
    private final V_4557_X P_4830_p = new V_4557_X();
    private int h_1847_R = 0;
    private boolean Q_4569_t = false;
    private int M_182_A = 0;
    private boolean t_1786_h = false;
    private boolean N_4405_n = false;
    public int t_148_a = 0;
    public boolean s_956_w = false;
    private final Queue<P_3201_s> w_1457_N = new LinkedList<P_3201_s>();
    private long Y_601_j;

    public a_2432_k() {
        super("GuiMove", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N);
    }

    public boolean h_1847_R() {
        return this.w_1484_f() && this.v_4262_N.J_1907_R("Holyworld");
    }

    public boolean Q_4569_t() {
        return this.w_1484_f() && this.v_4262_N.J_1907_R("SpookyTime");
    }

    public boolean M_182_A() {
        return this.w_1484_f() && this.v_4262_N.J_1907_R("LonyGrief");
    }

    public boolean t_1786_h() {
        return !this.w_1484_f() || this.t_148_a > 0;
    }

    private boolean w_1457_N() {
        return this.v_4262_N.J_1907_R("Reallyworld") || this.v_4262_N.J_1907_R("Funtime") || this.v_4262_N.J_1907_R("Holyworld") || this.v_4262_N.J_1907_R("SpookyTime") || this.v_4262_N.J_1907_R("LonyGrief");
    }

    private boolean Y_601_j() {
        return a_2432_k.c_3005_b.Y_259_p.G_564_y.moveForward != 0.0f || a_2432_k.c_3005_b.Y_259_p.G_564_y.moveStrafe != 0.0f || this.t_1786_h;
    }

    private int n_1700_B(a_408_T clickType) {
        if (this.v_4262_N.J_1907_R("SpookyTime")) {
            return clickType.equals((Object)a_408_T.n_1700_B) ? 2 : (q_3115_L.u_1723_Y() ? 6 : 3);
        }
        return clickType.equals((Object)a_408_T.n_1700_B) ? 1 : (this.M_182_A > 1 ? 2 : 3);
    }

    private void J_1907_R(a_408_T clickType) {
        if (this.w_1484_f() && this.v_4262_N.J_1907_R("SpookyTime")) {
            this.M_182_A = this.n_1700_B(clickType == null ? a_408_T.n_1700_B : clickType) + 1;
        }
    }

    public void N_4405_n() {
        if (this.w_1484_f() && this.v_4262_N.J_1907_R("SpookyTime")) {
            this.M_182_A = 8;
        }
    }

    private boolean Y_259_p() {
        if (!(a_2432_k.c_3005_b.Y_1740_V instanceof z_3427_G)) {
            return false;
        }
        if (!this.Y_601_j()) {
            return false;
        }
        return !a_2432_k.c_3005_b.Y_259_p.l_1268_F.s_956_w().n_1700_B();
    }

    private void Q_2552_b() {
        if (this.w_1457_N.isEmpty()) {
            return;
        }
        while (!this.w_1457_N.isEmpty()) {
            P_3201_s p = this.w_1457_N.poll();
            if (p == null) continue;
            a_2432_k.c_3005_b.Y_259_p.n_1700_B.J_1907_R(p);
        }
        this.t_148_a = 0;
    }

    private boolean n_1700_B(P_3201_s packetIn) {
        return !this.w_1457_N.contains(packetIn) && this.w_1457_N.add(packetIn);
    }

    @Y_1740_V
    public void n_1700_B(m_1621_v event) {
        if (!this.w_1457_N()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("SpookyTime")) {
            return;
        }
        if (this.v_4262_N.J_1907_R("LonyGrief")) {
            return;
        }
        if (this.s_956_w) {
            return;
        }
        if (a_2432_k.c_3005_b.Y_1740_V instanceof Q_1939_l && u_925_K.n_1700_B()) {
            this.u_2550_I.add(new P_3201_s(event.J_1907_R(), event.R_4764_Y(), event.G_564_y(), event.P_1922_E(), event.u_1723_Y(), event.v_4262_N()));
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(y_1945_D event) {
        if (!this.w_1457_N()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("SpookyTime")) {
            return;
        }
        if (this.v_4262_N.J_1907_R("LonyGrief")) {
            if (a_2432_k.c_3005_b.Y_1740_V instanceof Q_1939_l) {
                this.w_1484_f = true;
                this.P_4830_p.n_1700_B();
                event.n_1700_B(true);
            }
            return;
        }
        if (a_2432_k.c_3005_b.Y_1740_V instanceof Q_1939_l && u_925_K.n_1700_B()) {
            if (!this.u_2550_I.isEmpty()) {
                this.w_1484_f = true;
                if (this.v_4262_N.J_1907_R("Funtime")) {
                    this.P_4830_p.n_1700_B();
                } else if (this.v_4262_N.J_1907_R("Holyworld")) {
                    this.h_1847_R = 2;
                    this.Q_4569_t = true;
                } else {
                    this.M_588_G = 3;
                }
            }
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        if (this.v_4262_N.J_1907_R("LonyGrief")) {
            t_3138_Z<?> t_3138_Z2;
            if (!event.J_1907_R() && (t_3138_Z2 = event.G_564_y()) instanceof P_3201_s) {
                P_3201_s click = (P_3201_s)t_3138_Z2;
                if (a_2432_k.c_3005_b.Y_1740_V instanceof Q_1939_l && !this.s_956_w) {
                    this.u_2550_I.add(click);
                    event.n_1700_B(true);
                }
            }
            return;
        }
        if (!this.v_4262_N.J_1907_R("SpookyTime")) {
            return;
        }
        if (event.J_1907_R()) {
            if (event.G_564_y() instanceof v_4727_z) {
                event.n_1700_B(true);
            }
        } else {
            t_3138_Z<?> t_3138_Z3 = event.G_564_y();
            if (t_3138_Z3 instanceof P_3201_s) {
                P_3201_s toSend = (P_3201_s)t_3138_Z3;
                this.Y_601_j = System.currentTimeMillis();
                if (!this.s_956_w && this.Y_601_j() && toSend.R_4764_Y() != -1) {
                    this.J_1907_R(toSend.v_4262_N());
                    if (!this.t_1786_h && this.n_1700_B(toSend)) {
                        this.t_148_a = 0;
                        event.n_1700_B(true);
                    }
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (this.v_4262_N.J_1907_R("Reallyworld") && this.M_588_G > 0) {
            event.n_1700_B(0.0f);
            event.J_1907_R(0.0f);
            event.P_1922_E(false);
            event.u_1723_Y(false);
        }
        if (this.v_4262_N.J_1907_R("Funtime") && !this.P_4830_p.J_1907_R(190L)) {
            event.n_1700_B(0.0f);
            event.J_1907_R(0.0f);
            event.P_1922_E(false);
            event.u_1723_Y(false);
        }
        if (this.v_4262_N.J_1907_R("Holyworld") && this.h_1847_R > 0 && !this.P_4830_p.J_1907_R(222L)) {
            event.n_1700_B(0.0f);
            event.J_1907_R(0.0f);
            event.P_1922_E(false);
            event.u_1723_Y(false);
        }
        if (this.v_4262_N.J_1907_R("SpookyTime")) {
            if (System.currentTimeMillis() - this.Y_601_j <= 170L) {
                event.n_1700_B(0.0f);
                event.J_1907_R(0.0f);
                event.P_1922_E(false);
                event.u_1723_Y(false);
            } else if (this.t_1786_h) {
                event.n_1700_B(0.0f);
                event.J_1907_R(0.0f);
                event.P_1922_E(false);
                event.u_1723_Y(false);
            }
        }
        if (this.v_4262_N.J_1907_R("LonyGrief") && this.w_1484_f && !this.P_4830_p.J_1907_R(50L)) {
            event.n_1700_B(0.0f);
            event.J_1907_R(0.0f);
            event.P_1922_E(false);
            event.u_1723_Y(false);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (this.M_588_G > 0) {
            --this.M_588_G;
        }
        if (this.h_1847_R > 0) {
            --this.h_1847_R;
        }
        if (this.v_4262_N.J_1907_R("SpookyTime")) {
            if (this.N_4405_n && this.t_1786_h && this.M_182_A > 0) {
                this.Q_2552_b();
            }
            this.N_4405_n = this.t_1786_h;
            if (this.Y_259_p()) {
                this.J_1907_R((a_408_T)null);
            } else if (this.M_182_A > 0) {
                --this.M_182_A;
            }
            this.t_1786_h = this.M_182_A > 0;
            ++this.t_148_a;
            if (this.t_1786_h) {
                this.t_148_a = 0;
            }
        }
        D_590_W[] keys = new D_590_W[]{a_2432_k.c_3005_b.P_4830_p.O_508_d, a_2432_k.c_3005_b.P_4830_p.A_1038_p, a_2432_k.c_3005_b.P_4830_p.r_715_M, a_2432_k.c_3005_b.P_4830_p.i_1637_u, a_2432_k.c_3005_b.P_4830_p.T_69_K};
        if (this.v_4262_N.J_1907_R("Reallyworld") && this.M_588_G > 0) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Funtime") && !this.P_4830_p.J_1907_R(100L)) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Holyworld") && this.h_1847_R > 0 && !this.P_4830_p.J_1907_R(125L)) {
            return;
        }
        if (this.v_4262_N.J_1907_R("SpookyTime") && this.t_1786_h) {
            return;
        }
        if (this.v_4262_N.J_1907_R("LonyGrief") && this.w_1484_f && !this.P_4830_p.J_1907_R(50L)) {
            return;
        }
        if (a_2432_k.c_3005_b.Y_1740_V instanceof h_4412_P || a_2432_k.c_3005_b.Y_1740_V instanceof t_2037_T) {
            return;
        }
        for (D_590_W keyBinding : keys) {
            boolean isKeyPressed = Q_4113_P.n_1700_B(c_3005_b.a_2085_x().t_148_a(), keyBinding.getKey().J_1907_R());
            keyBinding.n_1700_B(isKeyPressed);
        }
        if (this.w_1484_f && this.v_4262_N.J_1907_R("Reallyworld")) {
            for (t_3138_Z t_3138_Z2 : this.u_2550_I) {
                a_2432_k.c_3005_b.Y_259_p.n_1700_B.J_1907_R(t_3138_Z2);
            }
            a_2432_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(a_2432_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            this.u_2550_I.clear();
            this.w_1484_f = false;
        }
        if (this.w_1484_f && this.v_4262_N.J_1907_R("Funtime")) {
            c_3005_b.n_1700_B(new Q_1939_l(a_2432_k.c_3005_b.Y_259_p));
            for (t_3138_Z t_3138_Z3 : this.u_2550_I) {
                a_2432_k.c_3005_b.Y_259_p.n_1700_B.J_1907_R(t_3138_Z3);
            }
            this.u_2550_I.clear();
            this.w_1484_f = false;
            a_2432_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(a_2432_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
        }
        if (this.Q_4569_t && this.v_4262_N.J_1907_R("Holyworld") && this.h_1847_R == 0) {
            for (t_3138_Z t_3138_Z4 : this.u_2550_I) {
                a_2432_k.c_3005_b.Y_259_p.n_1700_B.J_1907_R(t_3138_Z4);
            }
            a_2432_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(a_2432_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            this.u_2550_I.clear();
            this.Q_4569_t = false;
            this.w_1484_f = false;
        }
        if (this.w_1484_f && this.v_4262_N.J_1907_R("LonyGrief") && this.P_4830_p.J_1907_R(50L)) {
            for (t_3138_Z t_3138_Z5 : this.u_2550_I) {
                a_2432_k.c_3005_b.Y_259_p.n_1700_B.J_1907_R(t_3138_Z5);
            }
            a_2432_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(a_2432_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            this.u_2550_I.clear();
            this.w_1484_f = false;
            if (a_2432_k.c_3005_b.Y_1740_V != null) {
                c_3005_b.n_1700_B((k_2603_m)null);
            }
        }
    }

    @Override
    public void J_1907_R() {
        this.Q_2552_b();
        this.t_148_a = 1;
        this.u_2550_I.clear();
        this.M_588_G = 0;
        this.h_1847_R = 0;
        this.M_182_A = 0;
        this.t_1786_h = false;
        this.w_1484_f = false;
        this.Q_4569_t = false;
        this.s_956_w = false;
        this.w_1457_N.clear();
        super.J_1907_R();
    }

    @Override
    public void n_1700_B() {
        this.Q_2552_b();
        this.w_1457_N.clear();
        this.t_148_a = 1;
        super.n_1700_B();
    }
}

