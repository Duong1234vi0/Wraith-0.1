/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.N_4463_r;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.W_2756_H;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_178_J;
import lightning.product.a_2432_k;
import lightning.product.a_3913_L;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.i_1894_C;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.k_790_Q;
import lightning.product.m_3147_m;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.r_4811_B;
import lightning.product.t_2932_z;
import lightning.product.u_1403_d;
import lightning.product.u_1934_K;
import lightning.product.y_2603_k;
import lightning.product.y_2898_w;

public class F_3666_k
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", "\u041a\u043e\u043b\u0435\u0441\u043e");
    private final q_3206_W w_1484_f = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", () -> this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));
    private final q_3206_W t_148_a = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u043a\u043e\u043b\u0435\u0441\u0430", () -> this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e"));
    private final q_3206_W s_956_w = new q_3206_W("\u041a\u043e\u043b\u0435\u0441\u043e \u043d\u0430 \u0433\u043e\u043b\u043e\u0432\u0443", () -> this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e"));
    private final q_366_O u_2550_I = new q_366_O("\u041f\u0435\u0440\u0432\u044b\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0422\u043e\u0442\u0435\u043c", () -> this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"), "\u0429\u0438\u0442", "\u0422\u043e\u0442\u0435\u043c", "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u042f\u0431\u043b\u043e\u043a\u043e", "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430");
    private final q_366_O M_588_G = new q_366_O("\u0412\u0442\u043e\u0440\u043e\u0439 \u043f\u0440\u0435\u0434\u043c\u0435\u0442", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430", () -> this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"), "\u0429\u0438\u0442", "\u0422\u043e\u0442\u0435\u043c", "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", "\u042f\u0431\u043b\u043e\u043a\u043e", "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430", "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430");
    private final p_1977_n P_4830_p = new p_1977_n("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043e\u0431\u044b\u0447\u043d\u044b\u0435 \u0442\u043e\u0442\u0435\u043c\u044b", true, () -> this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b"));
    private final q_3206_W h_1847_R = new q_3206_W("\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u043a\u043d\u043e\u043f\u043a\u0430");
    private final N_4463_r Q_4569_t = new N_4463_r("\u0411\u0440\u0430\u0442\u044c \u0448\u0430\u0440", new p_1977_n("\u0412\u0441\u0435\u0433\u0434\u0430", false), new p_1977_n("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430", true), new p_1977_n("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442", true), new p_1977_n("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435", true));
    private final p_1977_n M_182_A = new p_1977_n("\u0423\u0431\u0438\u0440\u0430\u0442\u044c \u0431\u0435\u0437 \u0442\u0430\u0440\u0433\u0435\u0442\u0430", true, () -> this.Q_4569_t.J_1907_R("\u0412\u0441\u0435\u0433\u0434\u0430"));
    private final p_1977_n t_1786_h = new p_1977_n("\u0421\u0432\u0430\u043f\u0430\u0442\u044c \u043f\u0435\u0440\u0435\u0434 \u0443\u0434\u0430\u0440\u043e\u043c", false);
    private boolean N_4405_n = false;
    private boolean w_1457_N = false;
    private int Y_601_j = 0;
    private int Y_259_p = -1;
    private int Q_2552_b = 0;
    private int C_2741_M = 0;
    private int k_2293_S = -1;
    private int q_2307_F = 0;
    private final V_4557_X Z_875_P = new V_4557_X();
    private boolean t_4043_B = false;
    private int x_607_J = -1;
    private boolean e_4240_b = false;
    private int n_3318_d = -1;
    private boolean d_2427_y = false;
    private boolean z_1737_N = false;
    private int v_4276_D = -1;
    private final V_4557_X d_2461_k = new V_4557_X();
    private boolean G_624_v = false;
    private boolean T_2506_i = false;
    private boolean q_4610_l = false;

    public F_3666_k() {
        super("AutoSwap", y_2603_k.n_1700_B);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h);
    }

    private boolean w_1457_N() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean Y_601_j() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.Q_4569_t();
    }

    private boolean Y_259_p() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.h_1847_R();
    }

    private boolean Q_2552_b() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    private void J_1907_R(int playerInvSlot) {
        this.n_1700_B(() -> {
            u_1934_K.J_1907_R(playerInvSlot);
            F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void n_1700_B(Runnable action) {
        boolean prev;
        a_2432_k g = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        boolean bl = prev = g != null && g.s_956_w;
        if (g != null && g.w_1484_f()) {
            g.s_956_w = true;
        }
        try {
            action.run();
        }
        finally {
            if (g != null) {
                g.s_956_w = prev;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        t_2932_z headHandler;
        m_3147_m handler;
        int invFrom;
        boolean noTargetSwapBack;
        boolean cerberInOffhand;
        if (F_3666_k.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.Q_2552_b > 0) {
            --this.Q_2552_b;
        }
        if (this.q_2307_F > 0) {
            --this.q_2307_F;
        }
        if (F_3666_k.c_3005_b.Y_259_p.H_3699_F > 0) {
            this.d_2461_k.n_1700_B();
        }
        if (!(cerberInOffhand = this.n_3318_d())) {
            this.z_1737_N = false;
        }
        r_4811_B target = o_148_s.Y_601_j().J_1907_R().n_1700_B != null ? o_148_s.Y_601_j().J_1907_R().n_1700_B.h_1847_R() : null;
        boolean bl = noTargetSwapBack = target == null && (this.Q_4569_t.J_1907_R("\u0412\u0441\u0435\u0433\u0434\u0430") == false || this.M_182_A.t_148_a() != false);
        if (!this.t_1786_h.t_148_a().booleanValue()) {
            if (this.z_1737_N && this.v_4276_D != -1 && (noTargetSwapBack || target != null && !this.J_1907_R(target))) {
                this.P_1922_E(this.v_4276_D);
                this.v_4276_D = -1;
                this.z_1737_N = false;
                return;
            }
            if (this.d_2427_y && target != null && this.J_1907_R(target) && !this.z_1737_N && this.d_2427_y()) {
                this.t_4043_B();
                this.z_1737_N = true;
            }
        }
        if (this.t_1786_h.t_148_a().booleanValue()) {
            if (this.T_2506_i) {
                if (!this.n_3318_d()) {
                    int slot = this.x_607_J();
                    if (slot >= 0) {
                        this.v_4276_D = slot;
                        this.P_1922_E(slot);
                    }
                } else {
                    this.G_624_v = true;
                    this.T_2506_i = false;
                }
            } else if (this.q_4610_l) {
                if (this.n_3318_d() && this.v_4276_D >= 0) {
                    this.P_1922_E(this.v_4276_D);
                } else {
                    this.q_4610_l = false;
                    this.v_4276_D = -1;
                    this.G_624_v = false;
                }
            }
        }
        if (this.t_4043_B && this.x_607_J != -1 && this.Z_875_P.J_1907_R(100L)) {
            invFrom = u_1934_K.n_1700_B(this.x_607_J);
            c_3005_b.n_1700_B(new Q_1939_l(F_3666_k.c_3005_b.Y_259_p));
            this.n_1700_B(() -> F_3666_k.c_3005_b.w_1457_N.windowClick(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, F_3666_k.c_3005_b.Y_259_p));
            F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
            this.t_4043_B = false;
            this.x_607_J = -1;
            this.N_4405_n = false;
        }
        if (this.e_4240_b && this.n_3318_d != -1 && this.Z_875_P.J_1907_R(100L)) {
            c_3005_b.n_1700_B(new Q_1939_l(F_3666_k.c_3005_b.Y_259_p));
            this.J_1907_R(this.n_3318_d);
            c_3005_b.n_1700_B((k_2603_m)null);
            this.e_4240_b = false;
            this.n_3318_d = -1;
            this.w_1457_N = false;
        }
        if (this.t_4043_B || this.e_4240_b) {
            return;
        }
        if (this.Y_601_j == 1 && this.Y_259_p != -1 && this.Q_2552_b == 0) {
            invFrom = u_1934_K.n_1700_B(this.Y_259_p);
            this.n_1700_B(() -> {
                F_3666_k.c_3005_b.w_1457_N.windowClick(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, F_3666_k.c_3005_b.Y_259_p);
                F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            });
            this.Y_601_j = 0;
            this.Y_259_p = -1;
            this.N_4405_n = false;
            return;
        }
        if (this.C_2741_M == 1 && this.k_2293_S != -1 && this.q_2307_F == 0) {
            this.J_1907_R(this.k_2293_S);
            this.C_2741_M = 0;
            this.k_2293_S = -1;
            this.w_1457_N = false;
            return;
        }
        if (this.Y_601_j > 0 || this.C_2741_M > 0) {
            return;
        }
        if (this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b")) {
            if (this.N_4405_n) {
                this.k_2293_S();
            }
        } else if (this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e") && (handler = this.A_4115_X()) != null) {
            int selected = handler.G_564_y();
            if (selected >= 0) {
                boolean offhandDiff;
                Z_1993_T configured = handler.J_1907_R(selected);
                boolean missing = configured.n_1700_B();
                boolean bl2 = offhandDiff = !missing && !this.n_1700_B(F_3666_k.c_3005_b.Y_259_p.S_4035_N(), configured);
                if (!handler.R_4764_Y() && (missing || offhandDiff)) {
                    handler.P_1922_E(-1);
                }
            }
            if (handler.J_1907_R() && this.N_4405_n) {
                this.q_2307_F();
            }
        }
        if ((this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e")) && (headHandler = this.Y_1740_V()) != null) {
            int headSel = headHandler.R_4764_Y();
            if (headSel >= 0) {
                boolean headDiff;
                Z_1993_T headCfg = headHandler.J_1907_R(headSel);
                boolean headMissing = headCfg.n_1700_B();
                Z_1993_T onHead = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
                boolean bl3 = headDiff = !headMissing && !this.n_1700_B(onHead, headCfg);
                if (!headHandler.J_1907_R() && (headMissing || headDiff)) {
                    headHandler.G_564_y(-1);
                }
            }
            if (headHandler.n_1700_B() && this.w_1457_N) {
                this.Z_875_P();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.Q_2552_b > 0 || this.q_2307_F > 0) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.P_1922_E(false);
            e.u_1723_Y(false);
        }
        if (this.t_4043_B || this.e_4240_b) {
            if (!this.Z_875_P.J_1907_R(190L)) {
                e.n_1700_B(0.0f);
                e.J_1907_R(0.0f);
                e.u_1723_Y(false);
            }
            if (!this.Z_875_P.J_1907_R(350L)) {
                e.P_1922_E(false);
            }
        }
    }

    private q_1613_l R_4764_Y(String mode) {
        return switch (mode) {
            case "\u0422\u043e\u0442\u0435\u043c" -> q_4592_V.N_81_X;
            case "\u0429\u0438\u0442" -> q_4592_V.G_4948_k;
            case "\u0424\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a" -> q_4592_V.B_1305_J;
            case "\u042f\u0431\u043b\u043e\u043a\u043e" -> q_4592_V.p_863_D;
            case "\u0428\u0430\u0440, C\u0444\u0435\u0440\u0430" -> q_4592_V.C_3560_B;
            case "\u041b\u044e\u0431\u0430\u044f \u0435\u0434\u0430" -> null;
            default -> q_4592_V.n_1700_B;
        };
    }

    private int n_1700_B(q_1613_l item) {
        if (item == q_4592_V.N_81_X && this.P_4830_p.t_148_a().booleanValue()) {
            return u_1934_K.P_1922_E();
        }
        if (item == null) {
            return u_1934_K.G_564_y();
        }
        return u_1934_K.u_1723_Y(item);
    }

    private int C_2741_M() {
        Z_1993_T offhandItemStack = F_3666_k.c_3005_b.Y_259_p.S_4035_N();
        q_1613_l currentOffhandItem = offhandItemStack.J_1907_R();
        q_1613_l first = this.R_4764_Y((String)this.u_2550_I.J_1907_R());
        q_1613_l second = this.R_4764_Y((String)this.M_588_G.J_1907_R());
        if (currentOffhandItem instanceof k_790_Q) {
            int firstSlot = this.n_1700_B(first);
            if (firstSlot >= 0) {
                return firstSlot;
            }
            return this.n_1700_B(second);
        }
        if (first != null && currentOffhandItem == first || first == null && currentOffhandItem.Y_259_p()) {
            return this.n_1700_B(second);
        }
        if (second != null && currentOffhandItem == second || second == null && currentOffhandItem.Y_259_p()) {
            return this.n_1700_B(first);
        }
        int firstSlot = this.n_1700_B(first);
        return firstSlot >= 0 ? firstSlot : this.n_1700_B(second);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        Z_1993_T stack;
        k_2603_m screen;
        if (F_3666_k.c_3005_b.Y_1740_V != null && !(F_3666_k.c_3005_b.Y_1740_V instanceof i_1894_C) && !(F_3666_k.c_3005_b.Y_1740_V instanceof W_2756_H)) {
            return;
        }
        int pressed = e.n_1700_B();
        if (this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") && pressed == (Integer)this.w_1484_f.J_1907_R() && e.J_1907_R()) {
            this.N_4405_n = true;
        }
        if (this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e") && pressed == (Integer)this.t_148_a.J_1907_R()) {
            if (e.J_1907_R()) {
                this.N_4405_n = true;
                if (!(F_3666_k.c_3005_b.Y_1740_V instanceof i_1894_C)) {
                    c_3005_b.n_1700_B(new i_1894_C());
                }
            } else {
                k_2603_m k_2603_m2 = F_3666_k.c_3005_b.Y_1740_V;
                if (k_2603_m2 instanceof i_1894_C) {
                    m_3147_m handler;
                    screen = (i_1894_C)k_2603_m2;
                    int hovered = ((i_1894_C)screen).n_1700_B();
                    if (hovered >= 0 && (handler = this.A_4115_X()) != null && !(stack = handler.J_1907_R(hovered)).n_1700_B()) {
                        handler.P_1922_E(hovered);
                    }
                    c_3005_b.n_1700_B((k_2603_m)null);
                }
            }
        }
        if ((this.v_4262_N.J_1907_R("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b") || this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e")) && pressed == (Integer)this.s_956_w.J_1907_R()) {
            if (e.J_1907_R()) {
                this.w_1457_N = true;
                if (!(F_3666_k.c_3005_b.Y_1740_V instanceof W_2756_H)) {
                    c_3005_b.n_1700_B(new W_2756_H());
                }
            } else {
                k_2603_m hovered = F_3666_k.c_3005_b.Y_1740_V;
                if (hovered instanceof W_2756_H) {
                    t_2932_z headHandler;
                    screen = (W_2756_H)hovered;
                    int hovered2 = ((W_2756_H)screen).n_1700_B();
                    if (hovered2 >= 0 && (headHandler = this.Y_1740_V()) != null && !(stack = headHandler.J_1907_R(hovered2)).n_1700_B()) {
                        headHandler.G_564_y(hovered2);
                    }
                    c_3005_b.n_1700_B((k_2603_m)null);
                }
            }
        }
        if (!e.J_1907_R() && (Integer)this.h_1847_R.J_1907_R() == pressed) {
            boolean bl = this.d_2427_y = !this.d_2427_y;
            if (this.d_2427_y) {
                Z_1993_T sphere = this.e_4240_b();
                String sphereName = sphere.n_1700_B() ? "\u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d" : sphere.N_4405_n().getString();
                U_3758_B.n_1700_B("J", "\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u0432\u043a\u043b\u044e\u0447\u0435\u043d: " + sphereName, H_2506_c.n_1700_B(100, 255, 100));
            } else {
                U_3758_B.n_1700_B("K", "\u0410\u0432\u0442\u043e-\u0448\u0430\u0440 \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d", H_2506_c.n_1700_B(255, 80, 80));
            }
        }
    }

    private void k_2293_S() {
        int targetSlot = this.C_2741_M();
        if (targetSlot < 0) {
            this.N_4405_n = false;
            return;
        }
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.t_4043_B = true;
            this.x_607_J = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.Q_2552_b = 2;
            this.Y_601_j = 1;
            this.Y_259_p = targetSlot;
        } else {
            if (this.Y_601_j()) {
                a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.N_4405_n();
            }
            int invFrom = u_1934_K.n_1700_B(targetSlot);
            this.n_1700_B(() -> {
                F_3666_k.c_3005_b.w_1457_N.windowClick(0, invFrom, 40, a_408_T.R_4764_Y, F_3666_k.c_3005_b.Y_259_p);
                F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            });
            this.N_4405_n = false;
        }
    }

    private void q_2307_F() {
        Z_1993_T target = this.H_2857_Y();
        Z_1993_T off = F_3666_k.c_3005_b.Y_259_p.S_4035_N();
        if (target.n_1700_B() || this.n_1700_B(off, target)) {
            return;
        }
        int targetSlot = this.n_1700_B(target);
        if (targetSlot < 0) {
            this.N_4405_n = false;
            return;
        }
        this.G_564_y(targetSlot);
    }

    private void Z_875_P() {
        Z_1993_T target = this.c_3005_b();
        Z_1993_T onHead = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
        if (target.n_1700_B() || this.n_1700_B(onHead, target)) {
            return;
        }
        int targetSlot = this.J_1907_R(target);
        if (targetSlot < 0) {
            this.w_1457_N = false;
            return;
        }
        this.R_4764_Y(targetSlot);
    }

    private Z_1993_T c_3005_b() {
        t_2932_z handler = this.Y_1740_V();
        if (handler == null || F_3666_k.c_3005_b.Y_259_p == null) {
            return Z_1993_T.J_1907_R;
        }
        int index = handler.R_4764_Y();
        if (index < 0) {
            return Z_1993_T.J_1907_R;
        }
        return handler.J_1907_R(index);
    }

    private void R_4764_Y(int targetSlot) {
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.e_4240_b = true;
            this.n_3318_d = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.q_2307_F = 2;
            this.C_2741_M = 1;
            this.k_2293_S = targetSlot;
        } else {
            if (this.Y_601_j()) {
                a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.N_4405_n();
            }
            this.J_1907_R(targetSlot);
            this.w_1457_N = false;
        }
    }

    private Z_1993_T H_2857_Y() {
        m_3147_m handler = this.A_4115_X();
        if (handler == null || F_3666_k.c_3005_b.Y_259_p == null) {
            return Z_1993_T.J_1907_R;
        }
        int index = handler.G_564_y();
        if (index < 0) {
            return Z_1993_T.J_1907_R;
        }
        return handler.J_1907_R(index);
    }

    private m_3147_m A_4115_X() {
        return o_148_s.Y_601_j().t_1786_h().J_1907_R();
    }

    private t_2932_z Y_1740_V() {
        return o_148_s.Y_601_j().t_1786_h().R_4764_Y();
    }

    private int n_1700_B(Z_1993_T template) {
        if (template == null || template.n_1700_B()) {
            return -1;
        }
        if (this.v_4262_N.J_1907_R("\u041a\u043e\u043b\u0435\u0441\u043e")) {
            for (int i = 0; i < F_3666_k.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
                Z_1993_T stack = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!this.n_1700_B(stack, template)) continue;
                return i;
            }
            return -1;
        }
        for (int i = 0; i < F_3666_k.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T stack = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!this.n_1700_B(stack, template)) continue;
            return i;
        }
        return this.n_1700_B(template.J_1907_R());
    }

    private boolean n_1700_B(Z_1993_T a, Z_1993_T b) {
        if (a == null || b == null || a.n_1700_B() || b.n_1700_B()) {
            return false;
        }
        if (!Z_1993_T.J_1907_R(a, b)) {
            return false;
        }
        return a.N_4405_n().getString().equals(b.N_4405_n().getString());
    }

    private void G_564_y(int targetSlot) {
        if (this.w_1457_N()) {
            this.Z_875_P.n_1700_B();
            this.t_4043_B = true;
            this.x_607_J = targetSlot;
            return;
        }
        if (this.Y_259_p() || this.Q_2552_b()) {
            this.Q_2552_b = 2;
            this.Y_601_j = 1;
            this.Y_259_p = targetSlot;
        } else {
            if (this.Y_601_j()) {
                a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
                guiWalk.N_4405_n();
            }
            int invFrom = u_1934_K.n_1700_B(targetSlot);
            this.n_1700_B(() -> {
                F_3666_k.c_3005_b.w_1457_N.windowClick(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, invFrom, 40, a_408_T.R_4764_Y, F_3666_k.c_3005_b.Y_259_p);
                F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.o_1800_r.u_1723_Y));
            });
            this.N_4405_n = false;
        }
    }

    private int J_1907_R(Z_1993_T template) {
        if (template == null || template.n_1700_B()) {
            return -1;
        }
        for (int i = 0; i < F_3666_k.c_3005_b.Y_259_p.l_1268_F.Y_259_p(); ++i) {
            Z_1993_T stack;
            if (i == 39 || !this.n_1700_B(stack = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(i), template)) continue;
            return i;
        }
        Z_1993_T onHead = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(39);
        if (this.n_1700_B(onHead, template)) {
            return 39;
        }
        return this.n_1700_B(template.J_1907_R());
    }

    private boolean J_1907_R(r_4811_B target) {
        if (!this.d_2427_y) {
            return false;
        }
        if (this.Q_4569_t.J_1907_R("\u0412\u0441\u0435\u0433\u0434\u0430").booleanValue()) {
            return true;
        }
        if (F_3666_k.c_3005_b.Y_259_p.H_3699_F > 0 || target == null) {
            return false;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430").booleanValue() && !(target.A_2714_y().J_1907_R() instanceof u_1403_d)) {
            return true;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442").booleanValue() && this.R_4764_Y(target) && !F_3666_k.c_3005_b.Y_259_p.a_2180_A()) {
            return true;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435").booleanValue() && target instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)target;
            Z_1993_T chestplate = player.l_1268_F.J_1907_R.get(2);
            if (chestplate.J_1907_R() == q_4592_V.B_1548_Z) {
                return true;
            }
        }
        return false;
    }

    private void t_4043_B() {
        if (F_3666_k.c_3005_b.Y_1740_V != null) {
            return;
        }
        int cerberSlot = this.x_607_J();
        if (cerberSlot < 0) {
            return;
        }
        this.v_4276_D = cerberSlot;
        this.P_1922_E(cerberSlot);
    }

    private int x_607_J() {
        Z_1993_T selectedSphere = this.e_4240_b();
        if (selectedSphere.n_1700_B()) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = F_3666_k.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!this.n_1700_B(stack, selectedSphere)) continue;
            return i;
        }
        return -1;
    }

    private Z_1993_T e_4240_b() {
        m_3147_m handler = this.A_4115_X();
        if (handler == null) {
            return Z_1993_T.J_1907_R;
        }
        return handler.n_1700_B();
    }

    private boolean n_3318_d() {
        Z_1993_T selectedSphere = this.e_4240_b();
        if (selectedSphere.n_1700_B()) {
            return false;
        }
        return this.n_1700_B(F_3666_k.c_3005_b.Y_259_p.S_4035_N(), selectedSphere);
    }

    private void P_1922_E(int slot) {
        int invFrom = u_1934_K.n_1700_B(slot);
        this.n_1700_B(() -> {
            F_3666_k.c_3005_b.w_1457_N.windowClick(0, invFrom, 40, a_408_T.R_4764_Y, F_3666_k.c_3005_b.Y_259_p);
            F_3666_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(F_3666_k.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
        });
    }

    private boolean d_2427_y() {
        y_2898_w autoTotem = (y_2898_w)o_148_s.Y_601_j().J_1907_R().n_1700_B(y_2898_w.class);
        if (autoTotem == null || !autoTotem.w_1484_f()) {
            return true;
        }
        float currentHealth = F_3666_k.c_3005_b.Y_259_p.g_46_E() + F_3666_k.c_3005_b.Y_259_p.U_3823_u();
        return currentHealth > 4.0f;
    }

    private boolean R_4764_Y(r_4811_B target) {
        double dz;
        if (F_3666_k.c_3005_b.Y_259_p == null) {
            return false;
        }
        double targetVelocity = Math.sqrt(target.I_4348_c().J_1907_R * target.I_4348_c().J_1907_R + target.I_4348_c().G_564_y * target.I_4348_c().G_564_y);
        if (targetVelocity < 0.1) {
            return false;
        }
        double dx = target.O_3598_v() - F_3666_k.c_3005_b.Y_259_p.O_3598_v();
        double distance = Math.sqrt(dx * dx + (dz = target.l_2647_k() - F_3666_k.c_3005_b.Y_259_p.l_2647_k()) * dz);
        if (distance < 0.1) {
            return false;
        }
        double directionX = dx / distance;
        double velocityX = target.I_4348_c().J_1907_R;
        double directionZ = dz / distance;
        double velocityZ = target.I_4348_c().G_564_y;
        double dotProduct = directionX * velocityX + directionZ * velocityZ;
        return dotProduct > 0.15;
    }

    public boolean h_1847_R() {
        return this.d_2427_y;
    }

    public boolean Q_4569_t() {
        return this.z_1737_N;
    }

    public boolean n_1700_B(r_4811_B target) {
        return this.d_2427_y && this.t_1786_h.t_148_a() != false && target != null && this.d_2427_y() && !this.G_624_v;
    }

    public boolean M_182_A() {
        return this.G_624_v && this.n_3318_d();
    }

    public void t_1786_h() {
        if (!this.t_1786_h.t_148_a().booleanValue() || !this.d_2427_y) {
            return;
        }
        this.T_2506_i = true;
        this.q_4610_l = false;
    }

    public void N_4405_n() {
        if (!this.t_1786_h.t_148_a().booleanValue() || !this.d_2427_y) {
            return;
        }
        if (!this.G_624_v) {
            return;
        }
        this.q_4610_l = true;
        this.T_2506_i = false;
    }

    private boolean G_564_y(r_4811_B target) {
        if (!this.d_2427_y) {
            return false;
        }
        if (this.Q_4569_t.J_1907_R("\u0412\u0441\u0435\u0433\u0434\u0430").booleanValue()) {
            return true;
        }
        if (target == null) {
            return false;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u0431\u0435\u0437 \u043c\u0435\u0447\u0430").booleanValue() && !(target.A_2714_y().J_1907_R() instanceof u_1403_d)) {
            return true;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u043b\u0438\u0432\u0430\u0435\u0442").booleanValue() && this.R_4764_Y(target) && !F_3666_k.c_3005_b.Y_259_p.a_2180_A()) {
            return true;
        }
        if (this.Q_4569_t.J_1907_R("\u041a\u043e\u0433\u0434\u0430 \u0432 \u044d\u043b\u0438\u0442\u0440\u0435").booleanValue() && target instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)target;
            Z_1993_T chestplate = player.l_1268_F.J_1907_R.get(2);
            if (chestplate.J_1907_R() == q_4592_V.B_1548_Z) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void J_1907_R() {
        this.N_4405_n = false;
        this.w_1457_N = false;
        this.Y_601_j = 0;
        this.Y_259_p = -1;
        this.Q_2552_b = 0;
        this.C_2741_M = 0;
        this.k_2293_S = -1;
        this.q_2307_F = 0;
        this.t_4043_B = false;
        this.x_607_J = -1;
        this.e_4240_b = false;
        this.n_3318_d = -1;
        this.d_2427_y = false;
        this.z_1737_N = false;
        this.v_4276_D = -1;
        this.G_624_v = false;
        this.T_2506_i = false;
        this.q_4610_l = false;
        super.J_1907_R();
    }
}

