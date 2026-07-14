/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_2506_c;
import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.P_4526_H;
import lightning.product.Q_1939_l;
import lightning.product.R_2515_i;
import lightning.product.T_3952_j;
import lightning.product.U_3758_B;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.a_178_J;
import lightning.product.a_2432_k;
import lightning.product.a_408_T;
import lightning.product.b_3706_V;
import lightning.product.e_1174_E;
import lightning.product.g_4727_e;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.k_2603_m;
import lightning.product.o_148_s;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_3206_W;
import lightning.product.q_4592_V;
import lightning.product.v_1900_v;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class E_3014_r
extends X_3546_T {
    private final q_3206_W v_4262_N = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0441\u0432\u0430\u043f\u0430");
    private final q_3206_W w_1484_f = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0444\u0435\u0439\u0435\u0440\u0430");
    private final p_1977_n t_148_a = new p_1977_n("\u0424\u0435\u0439\u0435\u0432\u0435\u0440\u043a\u0438 \u0442\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", false, () -> (Integer)this.w_1484_f.J_1907_R() != -1);
    private final I_686_h s_956_w = new I_686_h("\u0421\u043b\u043e\u0442 \u0445\u043e\u0442\u0431\u0430\u0440\u0430 (1-9)", 1.0f, 1.0f, 9.0f, 1.0f, () -> this.t_148_a.t_148_a());
    private final p_1977_n u_2550_I = new p_1977_n("\u041e\u0431\u0445\u043e\u0434 \u0444\u0435\u0439\u0435\u0440\u0430 HW", false, () -> (Integer)this.w_1484_f.J_1907_R() != -1);
    private final I_686_h M_588_G = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0441\u0432\u0430\u043f\u0430", 1.0f, 0.0f, 9.0f, 1.0f);
    private final p_1977_n P_4830_p = new p_1977_n("\u0421\u043d\u0438\u043c\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u044b \u043f\u0440\u0438 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u0438", false, () -> (Integer)this.v_4262_N.J_1907_R() != -1);
    private final p_1977_n h_1847_R = new p_1977_n("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0432\u0437\u043b\u0435\u0442\u0430\u0442\u044c", false);
    private final p_1977_n Q_4569_t = new p_1977_n("\u0424\u0435\u0439\u0435\u0432\u0435\u0440\u043a \u043f\u0440\u0438 \u0432\u0437\u043b\u0435\u0442\u0435", false);
    private final p_1977_n M_182_A = new p_1977_n("\u0412\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f", true);
    private boolean t_1786_h = false;
    private boolean N_4405_n = false;
    private int w_1457_N = 0;
    private int Y_601_j = 0;
    private final V_4557_X Y_259_p = new V_4557_X();
    private boolean Q_2552_b = false;
    private boolean C_2741_M = false;
    private Boolean k_2293_S = null;
    private int q_2307_F = 0;
    private int Z_875_P = -1;

    public E_3014_r() {
        super("ElytraHelper", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A);
    }

    private boolean t_1786_h() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.w_1484_f() && guiWalk.v_4262_N.J_1907_R("Funtime");
    }

    private boolean N_4405_n() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.Q_4569_t();
    }

    private boolean w_1457_N() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.h_1847_R();
    }

    private boolean Y_601_j() {
        a_2432_k guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
        return guiWalk != null && guiWalk.M_182_A();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        a_2432_k guiWalk;
        if (this.Y_601_j > 0) {
            --this.Y_601_j;
        }
        if (this.w_1457_N > 0) {
            --this.w_1457_N;
        }
        if (this.q_2307_F > 0) {
            --this.q_2307_F;
            if (this.q_2307_F == 0 && this.Z_875_P >= 0 && E_3014_r.c_3005_b.Y_259_p != null) {
                E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Z_875_P));
                E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Z_875_P;
                this.Z_875_P = -1;
            }
        }
        if (this.Q_2552_b && this.Y_259_p.J_1907_R(100L) && this.w_1457_N == 0) {
            c_3005_b.n_1700_B(new Q_1939_l(E_3014_r.c_3005_b.Y_259_p));
            this.Y_259_p();
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            c_3005_b.n_1700_B((k_2603_m)null);
            this.Q_2552_b = false;
            this.t_1786_h = false;
            this.k_2293_S = null;
            this.w_1457_N = ((Float)this.M_588_G.J_1907_R()).intValue();
        }
        if (this.C_2741_M && this.Y_601_j == 0 && this.w_1457_N == 0) {
            boolean shouldBypassGuiMove;
            guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
            boolean bl = shouldBypassGuiMove = guiWalk != null && (guiWalk.h_1847_R() || guiWalk.M_182_A());
            if (shouldBypassGuiMove) {
                guiWalk.s_956_w = true;
            }
            try {
                this.Y_259_p();
                E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
            }
            finally {
                if (shouldBypassGuiMove) {
                    guiWalk.s_956_w = false;
                }
            }
            this.C_2741_M = false;
            this.t_1786_h = false;
            this.k_2293_S = null;
            this.w_1457_N = ((Float)this.M_588_G.J_1907_R()).intValue();
        }
        if (this.Q_2552_b || this.C_2741_M) {
            this.N_4405_n = E_3014_r.c_3005_b.Y_259_p.M_1641_O();
            return;
        }
        if (this.t_1786_h && this.w_1457_N == 0) {
            if (this.t_1786_h()) {
                this.Y_259_p.n_1700_B();
                this.Q_2552_b = true;
                this.Y_601_j = 2;
            } else if (this.w_1457_N() || this.Y_601_j()) {
                this.Y_601_j = 2;
                this.C_2741_M = true;
            } else {
                if (this.N_4405_n()) {
                    guiWalk = o_148_s.Y_601_j().J_1907_R().P_4830_p;
                    guiWalk.N_4405_n();
                }
                this.Y_259_p();
                E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new P_4526_H(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y));
                this.t_1786_h = false;
                this.k_2293_S = null;
                this.w_1457_N = ((Float)this.M_588_G.J_1907_R()).intValue();
            }
        }
        if (this.P_4830_p.t_148_a().booleanValue() && !this.N_4405_n && E_3014_r.c_3005_b.Y_259_p.M_1641_O() && E_3014_r.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z && E_3014_r.M_182_A() != -1 && E_3014_r.c_3005_b.Y_259_p.k_578_l() && this.w_1457_N == 0) {
            this.t_1786_h = true;
        }
        b_3706_V elytraJump = (b_3706_V)o_148_s.Y_601_j().J_1907_R().n_1700_B(b_3706_V.class);
        if (!(!this.h_1847_R.t_148_a().booleanValue() || elytraJump != null && elytraJump.w_1484_f() || E_3014_r.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() != q_4592_V.B_1548_Z || E_3014_r.c_3005_b.Y_259_p.k_578_l() || E_3014_r.c_3005_b.Y_259_p.a_2180_A() || E_3014_r.c_3005_b.Y_259_p.J_1907_R(J_588_u.q_2307_F))) {
            if (E_3014_r.c_3005_b.Y_259_p.M_1641_O()) {
                if (!E_3014_r.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
                    E_3014_r.c_3005_b.Y_259_p.e_837_t();
                }
            } else if (g_4727_e.G_564_y(E_3014_r.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E)) && !E_3014_r.c_3005_b.Y_259_p.C_415_h.J_1907_R) {
                E_3014_r.c_3005_b.Y_259_p.y_2447_C();
                E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new T_3952_j(E_3014_r.c_3005_b.Y_259_p, T_3952_j.n_1700_B.t_148_a));
                if (this.Q_4569_t.t_148_a().booleanValue() && !this.Q_2552_b()) {
                    E_3014_r.J_1907_R(q_4592_V.B_1305_J);
                }
            }
        }
        this.N_4405_n = E_3014_r.c_3005_b.Y_259_p.M_1641_O();
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        boolean hasChestplate;
        if (e.J_1907_R()) {
            return;
        }
        boolean hasElytra = E_3014_r.n_1700_B(q_4592_V.B_1548_Z) != -1;
        boolean bl = hasChestplate = E_3014_r.M_182_A() != -1;
        if (e.n_1700_B() == ((Integer)this.v_4262_N.J_1907_R()).intValue() && (hasElytra || hasChestplate) && this.w_1457_N == 0) {
            this.t_1786_h = true;
        }
        if (e.n_1700_B() == ((Integer)this.w_1484_f.J_1907_R()).intValue() && E_3014_r.c_3005_b.Y_259_p.k_578_l()) {
            if (this.Q_2552_b()) {
                return;
            }
            if (this.u_2550_I.t_148_a().booleanValue() && this.k_2293_S()) {
                return;
            }
            if (this.t_148_a.t_148_a().booleanValue()) {
                int slot = E_3014_r.n_1700_B(q_4592_V.B_1305_J);
                if (slot >= 0 && slot < 9) {
                    E_3014_r.J_1907_R(q_4592_V.B_1305_J);
                } else if (slot >= 9) {
                    int targetHotbar = Math.max(1, Math.min(9, ((Float)this.s_956_w.J_1907_R()).intValue())) - 1;
                    int invFrom = slot < 9 ? slot + 36 : slot;
                    try {
                        E_3014_r.c_3005_b.w_1457_N.windowClick(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, invFrom, targetHotbar, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
                        E_3014_r.J_1907_R(q_4592_V.B_1305_J);
                    }
                    catch (Exception ex) {
                        v_1900_v.n_1700_B("\u00a7c\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u043f\u0435\u0440\u0435\u043c\u0435\u0441\u0442\u0438\u0442\u044c \u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a \u0432 \u0445\u043e\u0442\u0431\u0430\u0440: " + ex.getMessage(), new Object[0]);
                    }
                } else {
                    v_1900_v.n_1700_B("\u00a7c\u0424\u0435\u0439\u0432\u0435\u0440\u043a\u0438 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
                }
            } else {
                E_3014_r.J_1907_R(q_4592_V.B_1305_J);
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(a_178_J e) {
        if (this.Y_601_j > 0) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.u_1723_Y(false);
            e.P_1922_E(false);
        }
        if (this.Q_2552_b && !this.Y_259_p.J_1907_R(190L)) {
            e.n_1700_B(0.0f);
            e.J_1907_R(0.0f);
            e.u_1723_Y(false);
            e.P_1922_E(false);
        }
    }

    private void Y_259_p() {
        this.n_1700_B(this.k_2293_S);
    }

    private void n_1700_B(Boolean forceToChestplate) {
        boolean swapToChestplate = forceToChestplate != null ? forceToChestplate : E_3014_r.c_3005_b.Y_259_p.J_1907_R(e_1174_E.P_1922_E).J_1907_R() == q_4592_V.B_1548_Z;
        int targetSlot = swapToChestplate ? E_3014_r.M_182_A() : E_3014_r.n_1700_B(q_4592_V.B_1548_Z);
        if (targetSlot == -1) {
            return;
        }
        E_3014_r.n_1700_B(targetSlot, 6);
        if (this.M_182_A.t_148_a().booleanValue()) {
            if (swapToChestplate) {
                v_1900_v.n_1700_B("\u00a7a\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", new Object[0]);
                U_3758_B.n_1700_B("J", "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u043d\u0430\u0433\u0440\u0443\u0434\u043d\u0438\u043a", H_2506_c.n_1700_B(100, 255, 100));
            } else {
                v_1900_v.n_1700_B("\u00a7a\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443", new Object[0]);
                U_3758_B.n_1700_B("J", "\u0421\u0432\u0430\u043f\u043d\u0443\u043b \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0443", H_2506_c.n_1700_B(100, 200, 255));
            }
        }
    }

    private boolean Q_2552_b() {
        return E_3014_r.c_3005_b.Y_259_p != null && E_3014_r.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.B_1305_J);
    }

    public void h_1847_R() {
        this.P_1922_E(true);
    }

    public void Q_4569_t() {
        this.P_1922_E(false);
    }

    private void P_1922_E(boolean toChestplate) {
        if (E_3014_r.c_3005_b.Y_259_p == null || E_3014_r.c_3005_b.w_1457_N == null) {
            return;
        }
        this.k_2293_S = toChestplate;
        if (!this.w_1484_f()) {
            this.n_1700_B(this.k_2293_S);
            this.k_2293_S = null;
            return;
        }
        this.t_1786_h = true;
    }

    public static int M_182_A() {
        if (E_3014_r.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < E_3014_r.c_3005_b.Y_259_p.l_1268_F.n_1700_B.size(); ++i) {
            Z_1993_T itemStack = E_3014_r.c_3005_b.Y_259_p.l_1268_F.n_1700_B.get(i);
            if (itemStack.n_1700_B() || !(itemStack.J_1907_R() instanceof R_2515_i) || ((R_2515_i)itemStack.J_1907_R()).R_4764_Y() != e_1174_E.P_1922_E) continue;
            return i;
        }
        return -1;
    }

    public static void n_1700_B(int fromSlot, int armorSlot) {
        if (E_3014_r.c_3005_b.Y_259_p == null || E_3014_r.c_3005_b.w_1457_N == null) {
            return;
        }
        int invFrom = fromSlot < 9 ? fromSlot + 36 : fromSlot;
        E_3014_r.c_3005_b.w_1457_N.windowClick(0, invFrom, E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
        E_3014_r.c_3005_b.w_1457_N.windowClick(0, armorSlot, E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
        E_3014_r.c_3005_b.w_1457_N.windowClick(0, invFrom, E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
    }

    public static int n_1700_B(q_1613_l item) {
        if (c_3005_b == null || E_3014_r.c_3005_b.Y_259_p == null) {
            return -1;
        }
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = E_3014_r.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B() || stack.J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }

    public static void J_1907_R(q_1613_l item) {
        boolean isInHotbar;
        if (E_3014_r.c_3005_b.Y_259_p == null || E_3014_r.c_3005_b.w_1457_N == null) {
            return;
        }
        int slot = E_3014_r.n_1700_B(item);
        if (slot == -1) {
            return;
        }
        int currentSlot = E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        boolean bl = isInHotbar = slot < 9;
        if (isInHotbar && slot == currentSlot) {
            E_3014_r.C_2741_M();
        } else if (isInHotbar) {
            E_3014_r.R_4764_Y(slot, currentSlot);
        } else {
            int hotbarSlot = currentSlot % 8 + 1;
            E_3014_r.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
            E_3014_r.R_4764_Y(hotbarSlot, currentSlot);
            E_3014_r.c_3005_b.w_1457_N.windowClick(0, slot, hotbarSlot, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
        }
    }

    private static void C_2741_M() {
        if (E_3014_r.c_3005_b.Y_259_p == null) {
            return;
        }
        if (E_3014_r.c_3005_b.Y_259_p.Y_601_j() && !E_3014_r.c_3005_b.Y_259_p.I_1790_n()) {
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
        } else {
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
        }
    }

    private static void R_4764_Y(int targetSlot, int currentSlot) {
        if (E_3014_r.c_3005_b.Y_259_p == null) {
            return;
        }
        if (E_3014_r.c_3005_b.Y_259_p.Y_601_j() && !E_3014_r.c_3005_b.Y_259_p.I_1790_n()) {
            E_3014_r.J_1907_R(targetSlot, 45);
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
            E_3014_r.J_1907_R(targetSlot, 45);
        } else {
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(targetSlot));
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(currentSlot));
        }
    }

    private boolean k_2293_S() {
        if (E_3014_r.c_3005_b.Y_259_p == null) {
            return false;
        }
        int fireworkSlot = E_3014_r.n_1700_B(q_4592_V.B_1305_J);
        if (fireworkSlot < 0 || fireworkSlot >= 9) {
            return false;
        }
        int currentSlot = E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (currentSlot != fireworkSlot) {
            if (this.q_2307_F <= 0) {
                this.Z_875_P = currentSlot;
            }
            E_3014_r.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(fireworkSlot));
            E_3014_r.c_3005_b.Y_259_p.l_1268_F.G_564_y = fireworkSlot;
            this.q_2307_F = 10;
        }
        E_3014_r.C_2741_M();
        return true;
    }

    public static void J_1907_R(int from, int to) {
        if (from == to) {
            return;
        }
        from = from < 9 ? from + 36 : from;
        E_3014_r.c_3005_b.w_1457_N.windowClick(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
        E_3014_r.c_3005_b.w_1457_N.windowClick(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, to, 0, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
        E_3014_r.c_3005_b.w_1457_N.windowClick(E_3014_r.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, from, 0, a_408_T.R_4764_Y, E_3014_r.c_3005_b.Y_259_p);
    }
}

