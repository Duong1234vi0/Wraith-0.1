/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.N_3268_u;
import lightning.product.Q_2753_H;
import lightning.product.V_674_I;
import lightning.product.X_1313_W;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.i_2572_h;
import lightning.product.k_1836_E;
import lightning.product.m_2262_U;
import lightning.product.o_148_s;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.q_4293_E;
import lightning.product.q_4592_V;
import lightning.product.t_3138_Z;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.u_925_K;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class O_2864_C
extends X_3546_T {
    private final List<t_3138_Z<?>> v_4262_N = new ArrayList();
    private final q_366_O w_1484_f = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Vanilla", "Vanilla", "Pearl");
    private final I_686_h t_148_a = new I_686_h("\u041a\u043e\u043b-\u0432\u043e \u043f\u0430\u043a\u0435\u0442\u043e\u0432", 2.0f, 1.0f, 15.0f, 1.0f, () -> this.w_1484_f.J_1907_R("Vanilla"));
    private final p_1977_n s_956_w = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", false, () -> this.w_1484_f.J_1907_R("Pearl"));
    private final p_1977_n u_2550_I = new p_1977_n("\u0410\u0432\u0442\u043e\u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435", false, () -> this.w_1484_f.J_1907_R("Pearl"));
    private final I_686_h M_588_G = new I_686_h("\u0422\u0430\u0439\u043c\u0430\u0443\u0442 \u043f\u043e\u0441\u043b\u0435 \u0436\u0435\u043c\u0447\u0443\u0433\u0430", 0.0f, 0.0f, 60.0f, 1.0f, () -> this.w_1484_f.J_1907_R("Pearl"));
    private final I_686_h P_4830_p = new I_686_h("Pitch", 80.0f, 0.0f, 90.0f, 1.0f, () -> this.w_1484_f.J_1907_R("Pearl"));
    private final I_686_h h_1847_R = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.06f, 0.01f, 0.2f, 0.01f, () -> this.w_1484_f.J_1907_R("Test"));
    private final I_686_h Q_4569_t = new I_686_h("\u041c\u0430\u043a\u0441. \u0442\u0438\u043a\u043e\u0432", 15.0f, 5.0f, 40.0f, 1.0f, () -> this.w_1484_f.J_1907_R("Test"));
    private boolean M_182_A;
    private boolean t_1786_h;
    private int N_4405_n;
    private int w_1457_N;
    private float[] Y_601_j;
    private boolean Y_259_p;
    private e_2866_D Q_2552_b;
    private int C_2741_M;
    private final CopyOnWriteArrayList<t_3138_Z<?>> k_2293_S = new CopyOnWriteArrayList();
    private boolean q_2307_F;
    private boolean Z_875_P;
    private long t_4043_B;

    public O_2864_C() {
        super("Phase", y_2603_k.J_1907_R);
        this.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (this.w_1484_f.J_1907_R("Vanilla")) {
            u_925_K.n_1700_B((double)0.001f);
        }
        if (this.w_1484_f.J_1907_R("Pearl") && this.Y_601_j != null) {
            e.n_1700_B(this.Y_601_j[0]);
            e.J_1907_R(this.Y_601_j[1]);
            O_2864_C.c_3005_b.Y_259_p.f_3449_S = this.Y_601_j[0];
            O_2864_C.c_3005_b.Y_259_p.C_1162_e = this.Y_601_j[0];
            this.Y_601_j = null;
        }
        if (this.w_1484_f.J_1907_R("Test") && this.Y_259_p) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(X_1313_W e) {
        if (O_2864_C.c_3005_b.Y_259_p == null || O_2864_C.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if (this.w_1484_f.J_1907_R("Vanilla")) {
            int playerBlockY = O_2864_C.c_3005_b.Y_259_p.b_2312_j().getY();
            if (e.J_1907_R().getY() >= playerBlockY || O_2864_C.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                e.n_1700_B(true);
            }
        }
        if (this.w_1484_f.J_1907_R("Pearl") && (this.t_1786_h() || this.w_1457_N > 0)) {
            c_1514_x playerPos = new c_1514_x(u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.O_3598_v()), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.X_2960_b()), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.l_2647_k()));
            if (!e.J_1907_R().equals(playerPos.down()) || O_2864_C.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                e.n_1700_B(true);
            }
        }
        if (this.w_1484_f.J_1907_R("Test") && this.Y_259_p) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H eventPacket) {
        t_3138_Z<?> t_3138_Z2;
        if (O_2864_C.c_3005_b.Y_259_p == null || O_2864_C.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if ((this.w_1484_f.J_1907_R("LonyGrief") || this.Z_875_P) && eventPacket.J_1907_R() && (t_3138_Z2 = eventPacket.G_564_y()) instanceof k_1836_E) {
            k_1836_E chatPacket = (k_1836_E)t_3138_Z2;
            String text = chatPacket.J_1907_R().getString();
            if (text.contains("\u0423\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d \u0440\u0435\u0436\u0438\u043c \u0438\u0433\u0440\u044b")) {
                eventPacket.n_1700_B(true);
                if (this.Z_875_P) {
                    this.Z_875_P = false;
                    super.J_1907_R();
                }
            }
            if (this.Z_875_P && System.currentTimeMillis() - this.t_4043_B > 3000L) {
                this.Z_875_P = false;
                super.J_1907_R();
            }
            return;
        }
        if (!eventPacket.R_4764_Y()) {
            return;
        }
        if (this.w_1484_f.J_1907_R("Test")) {
            if (this.Y_259_p) {
                if (eventPacket.G_564_y() instanceof N_3268_u) {
                    eventPacket.n_1700_B(true);
                } else if (eventPacket.G_564_y() instanceof V_674_I) {
                    this.k_2293_S.add(eventPacket.G_564_y());
                    eventPacket.n_1700_B(true);
                }
            }
            return;
        }
        if (eventPacket.G_564_y() instanceof N_3268_u) {
            this.v_4262_N.add(eventPacket.G_564_y());
            eventPacket.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G eventPacket) {
        if (O_2864_C.c_3005_b.Y_259_p == null || O_2864_C.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.N_4405_n > 0) {
            --this.N_4405_n;
        }
        if (this.w_1457_N > 0) {
            --this.w_1457_N;
        }
        if (this.w_1484_f.J_1907_R("Test")) {
            this.h_1847_R();
            return;
        }
        if (this.w_1484_f.J_1907_R("Vanilla")) {
            if (o_148_s.Y_601_j().J_1907_R().n_1700_B(O_2864_C.class).w_1484_f() && O_2864_C.c_3005_b.Y_259_p.G_564_y(J_588_u.n_1700_B)) {
                u_925_K.n_1700_B((double)0.001f);
            }
            long totalStates = O_2864_C.c_3005_b.Y_601_j.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.i_601_W().shrink(0.001)).count();
            long solidStates = O_2864_C.c_3005_b.Y_601_j.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.i_601_W().shrink(0.001)).filter(q_4293_E.n_1700_B::M_588_G).count();
            if (!this.M_182_A && solidStates > 0L && solidStates < totalStates) {
                int i = 0;
                while ((float)i < ((Float)this.t_148_a.J_1907_R()).floatValue()) {
                    O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(O_2864_C.c_3005_b.Y_259_p.O_3598_v(), O_2864_C.c_3005_b.Y_259_p.X_2960_b(), O_2864_C.c_3005_b.Y_259_p.l_2647_k(), O_2864_C.c_3005_b.Y_259_p.p_178_J, O_2864_C.c_3005_b.Y_259_p.f_4016_n, O_2864_C.c_3005_b.Y_259_p.M_1641_O()));
                    ++i;
                }
                this.M_182_A = true;
                return;
            }
            if (this.M_182_A && O_2864_C.c_3005_b.Y_601_j.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.i_601_W().shrink(0.001)).noneMatch(q_4293_E.n_1700_B::M_588_G)) {
                this.t_1786_h = true;
                this.R_4764_Y();
            }
        }
        if (this.w_1484_f.J_1907_R("Pearl") && (O_2864_C.c_3005_b.Y_259_p.M_1641_O() || !this.s_956_w.t_148_a().booleanValue()) && O_2864_C.c_3005_b.Y_259_p.D_60_a && !this.N_4405_n() && this.N_4405_n <= 0 && O_2864_C.c_3005_b.Y_259_p.t_2577_l > 60) {
            double yaw = Math.toRadians(O_2864_C.c_3005_b.Y_259_p.p_178_J);
            double dirX = -Math.sin(yaw) * 0.5;
            double dirZ = Math.cos(yaw) * 0.5;
            c_1514_x block = new c_1514_x(u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.O_3598_v() + dirX), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.X_2960_b()), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.l_2647_k() + dirZ));
            if (O_2864_C.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                return;
            }
            float[] angle = this.n_1700_B(block);
            int epSlot = this.M_182_A();
            if (epSlot != -1) {
                O_2864_C.c_3005_b.Y_259_p.p_178_J = angle[0];
                O_2864_C.c_3005_b.Y_259_p.f_4016_n = ((Float)this.P_4830_p.J_1907_R()).floatValue();
                this.Y_601_j = new float[]{angle[0], ((Float)this.P_4830_p.J_1907_R()).floatValue()};
                int prevItem = O_2864_C.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (epSlot < 9) {
                    O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(epSlot));
                    O_2864_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = epSlot;
                    O_2864_C.c_3005_b.w_1457_N.syncCurrentPlayItem();
                } else {
                    O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(epSlot));
                }
                O_2864_C.c_3005_b.w_1457_N.processRightClick(O_2864_C.c_3005_b.Y_259_p, O_2864_C.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                O_2864_C.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                if (epSlot < 9) {
                    O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(prevItem));
                    O_2864_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = prevItem;
                    O_2864_C.c_3005_b.w_1457_N.syncCurrentPlayItem();
                } else {
                    O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(epSlot));
                }
                if (this.u_2550_I.t_148_a().booleanValue()) {
                    this.R_4764_Y();
                }
                this.N_4405_n = 20;
                this.w_1457_N = ((Float)this.M_588_G.J_1907_R()).intValue();
            }
        }
    }

    @Override
    public void J_1907_R() {
        if (this.w_1484_f.J_1907_R("LonyGrief")) {
            if (O_2864_C.c_3005_b.Y_259_p != null) {
                O_2864_C.c_3005_b.Y_259_p.n_1700_B("/gms");
            }
            this.Z_875_P = true;
            this.t_4043_B = System.currentTimeMillis();
            return;
        }
        if (this.w_1484_f.J_1907_R("Test")) {
            if (this.Y_259_p) {
                this.Q_4569_t();
            }
            this.k_2293_S.clear();
            super.J_1907_R();
            return;
        }
        if (!this.t_1786_h && this.M_182_A) {
            O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(O_2864_C.c_3005_b.Y_259_p.O_3598_v() - 5000.0, O_2864_C.c_3005_b.Y_259_p.X_2960_b(), O_2864_C.c_3005_b.Y_259_p.l_2647_k() - 5000.0, O_2864_C.c_3005_b.Y_259_p.p_178_J, O_2864_C.c_3005_b.Y_259_p.f_4016_n, false));
            O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(O_2864_C.c_3005_b.Y_259_p.O_3598_v() + 5000.0, O_2864_C.c_3005_b.Y_259_p.X_2960_b(), O_2864_C.c_3005_b.Y_259_p.l_2647_k() + 5000.0, O_2864_C.c_3005_b.Y_259_p.p_178_J, O_2864_C.c_3005_b.Y_259_p.f_4016_n, false));
            O_2864_C.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(O_2864_C.c_3005_b.Y_259_p.O_3598_v(), O_2864_C.c_3005_b.Y_259_p.X_2960_b(), O_2864_C.c_3005_b.Y_259_p.l_2647_k(), O_2864_C.c_3005_b.Y_259_p.p_178_J, O_2864_C.c_3005_b.Y_259_p.f_4016_n, O_2864_C.c_3005_b.Y_259_p.M_1641_O()));
        }
        if (O_2864_C.c_3005_b.Y_259_p != null && O_2864_C.c_3005_b.Y_259_p.n_1700_B != null && !this.v_4262_N.isEmpty()) {
            ArrayList toSend = new ArrayList(this.v_4262_N);
            for (t_3138_Z t_3138_Z2 : toSend) {
                O_2864_C.c_3005_b.Y_259_p.n_1700_B.J_1907_R(t_3138_Z2);
            }
            this.v_4262_N.removeAll(toSend);
        }
        super.J_1907_R();
    }

    @Override
    public void n_1700_B() {
        this.v_4262_N.clear();
        this.M_182_A = false;
        this.t_1786_h = false;
        this.N_4405_n = 0;
        this.w_1457_N = 0;
        this.Y_601_j = null;
        this.Y_259_p = false;
        this.Q_2552_b = null;
        this.C_2741_M = 0;
        this.k_2293_S.clear();
        this.Z_875_P = false;
        if (this.w_1484_f.J_1907_R("LonyGrief") && O_2864_C.c_3005_b.Y_259_p != null) {
            O_2864_C.c_3005_b.Y_259_p.n_1700_B("/gmsp");
        }
        super.n_1700_B();
    }

    private void h_1847_R() {
        boolean onSolid;
        if (O_2864_C.c_3005_b.Y_259_p == null || O_2864_C.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.Y_259_p) {
            if (O_2864_C.c_3005_b.Y_259_p.D_60_a && u_925_K.n_1700_B() && O_2864_C.c_3005_b.Y_259_p.M_1641_O()) {
                this.Y_259_p = true;
                this.Q_2552_b = O_2864_C.c_3005_b.Y_259_p.s_4990_V();
                this.C_2741_M = 0;
                this.k_2293_S.clear();
                this.q_2307_F = O_2864_C.c_3005_b.Y_259_p.o_2341_D();
                O_2864_C.c_3005_b.Y_259_p.b_(false);
                O_2864_C.c_3005_b.P_4830_p.P_2565_J.n_1700_B(false);
            }
            return;
        }
        ++this.C_2741_M;
        O_2864_C.c_3005_b.Y_259_p.b_(false);
        O_2864_C.c_3005_b.P_4830_p.P_2565_J.n_1700_B(false);
        double yaw = Math.toRadians(O_2864_C.c_3005_b.Y_259_p.p_178_J);
        double moveX = -Math.sin(yaw) * (double)((Float)this.h_1847_R.J_1907_R()).floatValue();
        double moveZ = Math.cos(yaw) * (double)((Float)this.h_1847_R.J_1907_R()).floatValue();
        O_2864_C.c_3005_b.Y_259_p.j_1564_a = true;
        O_2864_C.c_3005_b.Y_259_p.h_1847_R(moveX, 0.0, moveZ);
        O_2864_C.c_3005_b.Y_259_p.u_1723_Y(false);
        boolean clearOfBlocks = O_2864_C.c_3005_b.Y_601_j.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.i_601_W().shrink(0.01)).noneMatch(q_4293_E.n_1700_B::M_588_G);
        boolean bl = onSolid = !O_2864_C.c_3005_b.Y_601_j.u_1723_Y(new c_1514_x(O_2864_C.c_3005_b.Y_259_p.O_3598_v(), O_2864_C.c_3005_b.Y_259_p.X_2960_b() - 0.1, O_2864_C.c_3005_b.Y_259_p.l_2647_k()));
        if (clearOfBlocks && onSolid || this.C_2741_M >= ((Float)this.Q_4569_t.J_1907_R()).intValue()) {
            this.Q_4569_t();
        }
    }

    private void Q_4569_t() {
        if (O_2864_C.c_3005_b.Y_259_p == null || O_2864_C.c_3005_b.Y_259_p.n_1700_B == null || this.Q_2552_b == null) {
            this.Y_259_p = false;
            this.k_2293_S.clear();
            return;
        }
        double endX = O_2864_C.c_3005_b.Y_259_p.O_3598_v();
        double endY = O_2864_C.c_3005_b.Y_259_p.X_2960_b();
        double endZ = O_2864_C.c_3005_b.Y_259_p.l_2647_k();
        for (t_3138_Z<?> pkt : this.k_2293_S) {
            O_2864_C.c_3005_b.Y_259_p.n_1700_B.J_1907_R(pkt);
        }
        this.k_2293_S.clear();
        O_2864_C.c_3005_b.Y_259_p.n_1700_B.J_1907_R(new N_3268_u.n_1700_B(endX, endY, endZ, true));
        O_2864_C.c_3005_b.Y_259_p.j_1564_a = false;
        O_2864_C.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
        O_2864_C.c_3005_b.Y_259_p.b_(this.q_2307_F);
        this.Y_259_p = false;
        this.Q_2552_b = null;
        this.C_2741_M = 0;
    }

    private int M_182_A() {
        if (O_2864_C.c_3005_b.Y_259_p.A_2714_y().J_1907_R() == q_4592_V.v_2746_S) {
            return O_2864_C.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        }
        return u_1934_K.n_1700_B(q_4592_V.v_2746_S);
    }

    private boolean t_1786_h() {
        return this.N_4405_n != 0;
    }

    private boolean N_4405_n() {
        c_1514_x pos = new c_1514_x(u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.O_3598_v()), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.X_2960_b()), u_530_F.R_4764_Y(O_2864_C.c_3005_b.Y_259_p.l_2647_k()));
        return !O_2864_C.c_3005_b.Y_601_j.u_1723_Y(pos);
    }

    private float[] n_1700_B(c_1514_x block) {
        e_2866_D eyePos = O_2864_C.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = new e_2866_D((double)block.getX() + 0.5, (double)block.getY() + 0.5, (double)block.getZ() + 0.5);
        e_2866_D direction = targetPos.G_564_y(eyePos);
        double distance = u_530_F.R_4764_Y((float)(direction.J_1907_R * direction.J_1907_R + direction.G_564_y * direction.G_564_y));
        float yaw = (float)(u_530_F.G_564_y(direction.G_564_y, direction.J_1907_R) * 57.29577951308232 - 90.0);
        float pitch = (float)(-u_530_F.G_564_y(direction.R_4764_Y, distance) * 57.29577951308232);
        return new float[]{yaw, pitch};
    }
}

