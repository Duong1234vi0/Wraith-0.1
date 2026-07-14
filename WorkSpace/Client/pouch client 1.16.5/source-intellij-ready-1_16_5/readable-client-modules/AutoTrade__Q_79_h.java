/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.K_3710_b;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Y_2498_n;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.g_904_S;
import lightning.product.h_1015_G;
import lightning.product.i_793_K;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.w_4942_z;
import lightning.product.y_2603_k;

public class Q_79_h
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 500.0f, 10.0f);
    private final I_686_h w_1484_f = new I_686_h("\u041d\u043e\u043c\u0435\u0440 \u0442\u0440\u0435\u0439\u0434\u0430", 1.0f, 1.0f, 10.0f, 1.0f);
    private final q_366_O t_148_a = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439", "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439", "\u0412\u0441\u0435", "\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435");
    private final p_1977_n s_956_w = new p_1977_n("\u0410\u0432\u0442\u043e-\u0432\u044b\u0431\u043e\u0440 \u0442\u0440\u0435\u0439\u0434\u0430", true);
    private final p_1977_n u_2550_I = new p_1977_n("\u041f\u0440\u043e\u043f\u0443\u0441\u043a\u0430\u0442\u044c \u0438\u0441\u0447\u0435\u0440\u043f\u0430\u043d\u043d\u044b\u0435", true);
    private final p_1977_n M_588_G = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u0438\u044f", false, () -> this.t_148_a.J_1907_R("\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435"));
    private final p_1977_n P_4830_p = new p_1977_n("\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u043a\u043e\u0433\u0434\u0430 \u0433\u043e\u0442\u043e\u0432\u043e", false);
    private final V_4557_X h_1847_R = new V_4557_X();
    private int Q_4569_t = 0;
    private boolean M_182_A = true;

    public Q_79_h() {
        super("AutoTrade", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p);
    }

    @Override
    public void n_1700_B() {
        this.Q_4569_t = 0;
        this.M_182_A = true;
        super.n_1700_B();
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (Q_79_h.c_3005_b.Y_259_p == null || Q_79_h.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!(Q_79_h.c_3005_b.Y_1740_V instanceof g_904_S)) {
            this.Q_4569_t = 0;
            this.M_182_A = true;
            return;
        }
        if (!(Q_79_h.c_3005_b.Y_259_p.H_1873_g instanceof K_3710_b)) {
            return;
        }
        K_3710_b container = (K_3710_b)Q_79_h.c_3005_b.Y_259_p.H_1873_g;
        i_793_K offers = container.P_1922_E();
        if (offers == null || offers.isEmpty()) {
            return;
        }
        if (!this.h_1847_R.J_1907_R(((Float)this.v_4262_N.J_1907_R()).longValue())) {
            return;
        }
        switch ((String)this.t_148_a.J_1907_R()) {
            case "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439": {
                this.n_1700_B(container, offers);
                break;
            }
            case "\u0412\u0441\u0435": {
                this.J_1907_R(container, offers);
                break;
            }
            case "\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435": {
                this.R_4764_Y(container, offers);
            }
        }
    }

    private void n_1700_B(K_3710_b container, i_793_K offers) {
        int index = ((Float)this.w_1484_f.J_1907_R()).intValue() - 1;
        if (index >= offers.size()) {
            return;
        }
        w_4942_z offer = (w_4942_z)offers.get(index);
        if (this.u_2550_I.t_148_a().booleanValue() && offer.M_182_A()) {
            return;
        }
        if (!this.J_1907_R(offer)) {
            return;
        }
        if (this.s_956_w.t_148_a().booleanValue() && this.M_182_A) {
            this.n_1700_B(container, index);
            this.M_182_A = false;
            this.h_1847_R.n_1700_B();
            return;
        }
        this.n_1700_B(container);
        this.h_1847_R.n_1700_B();
    }

    private void J_1907_R(K_3710_b container, i_793_K offers) {
        if (this.Q_4569_t >= offers.size()) {
            if (this.P_4830_p.t_148_a().booleanValue()) {
                Q_79_h.c_3005_b.Y_259_p.P_1922_E();
            }
            this.Q_4569_t = 0;
            return;
        }
        w_4942_z offer = (w_4942_z)offers.get(this.Q_4569_t);
        if (this.u_2550_I.t_148_a().booleanValue() && offer.M_182_A()) {
            ++this.Q_4569_t;
            this.M_182_A = true;
            return;
        }
        if (!this.J_1907_R(offer)) {
            ++this.Q_4569_t;
            this.M_182_A = true;
            return;
        }
        if (this.s_956_w.t_148_a().booleanValue() && this.M_182_A) {
            this.n_1700_B(container, this.Q_4569_t);
            this.M_182_A = false;
            this.h_1847_R.n_1700_B();
            return;
        }
        this.n_1700_B(container);
        this.h_1847_R.n_1700_B();
        if (offer.M_182_A()) {
            ++this.Q_4569_t;
            this.M_182_A = true;
        }
    }

    private void R_4764_Y(K_3710_b container, i_793_K offers) {
        for (int i = 0; i < offers.size(); ++i) {
            w_4942_z offer = (w_4942_z)offers.get(i);
            if (this.u_2550_I.t_148_a().booleanValue() && offer.M_182_A() || !this.J_1907_R(offer) || !this.n_1700_B(offer)) continue;
            if (this.s_956_w.t_148_a().booleanValue() && (this.M_182_A || this.Q_4569_t != i)) {
                this.n_1700_B(container, i);
                this.Q_4569_t = i;
                this.M_182_A = false;
                this.h_1847_R.n_1700_B();
                return;
            }
            this.n_1700_B(container);
            this.h_1847_R.n_1700_B();
            return;
        }
        if (this.P_4830_p.t_148_a().booleanValue()) {
            Q_79_h.c_3005_b.Y_259_p.P_1922_E();
        }
    }

    private boolean n_1700_B(w_4942_z offer) {
        q_1613_l input;
        Z_1993_T result = offer.G_564_y();
        if (this.M_588_G.t_148_a().booleanValue()) {
            return result.k_2293_S() || result.J_1907_R() == q_4592_V.M_4472_P;
        }
        q_1613_l resultItem = result.J_1907_R();
        if (result.k_2293_S()) {
            return true;
        }
        if (resultItem == q_4592_V.M_4472_P) {
            return true;
        }
        if (resultItem == q_4592_V.N_2592_G) {
            return true;
        }
        if (resultItem == q_4592_V.M_2029_A) {
            return true;
        }
        if (resultItem == q_4592_V.C_1577_A) {
            return true;
        }
        if (resultItem == q_4592_V.f_508_U) {
            return true;
        }
        if (resultItem == q_4592_V.q_4361_M) {
            return true;
        }
        if (resultItem == q_4592_V.A_1603_w) {
            return true;
        }
        if (resultItem == q_4592_V.V_4557_X) {
            return true;
        }
        if (resultItem == q_4592_V.v_2746_S) {
            return true;
        }
        if (resultItem == q_4592_V.K_3681_o) {
            return true;
        }
        if (resultItem == q_4592_V.Z_361_l) {
            return true;
        }
        return resultItem == q_4592_V.Y_2905_A && ((input = offer.n_1700_B().J_1907_R()) == q_4592_V.l_3370_o || input == q_4592_V.m_1964_F || input == q_4592_V.j_755_i || input == q_4592_V.V_3441_j || input == q_4592_V.T_797_O || input == q_4592_V.D_1621_L);
    }

    private boolean J_1907_R(w_4942_z offer) {
        int needed2;
        int count2;
        int needed1;
        Z_1993_T cost1 = offer.n_1700_B();
        Z_1993_T cost2 = offer.R_4764_Y();
        int count1 = this.n_1700_B(cost1.J_1907_R());
        if (count1 < (needed1 = cost1.t_4043_B())) {
            return false;
        }
        return cost2.n_1700_B() || (count2 = this.n_1700_B(cost2.J_1907_R())) >= (needed2 = cost2.t_4043_B());
    }

    private int n_1700_B(q_1613_l item) {
        int count = 0;
        for (Z_1993_T stack : Q_79_h.c_3005_b.Y_259_p.l_1268_F.n_1700_B) {
            if (stack.J_1907_R() != item) continue;
            count += stack.t_4043_B();
        }
        return count;
    }

    private void n_1700_B(K_3710_b container, int index) {
        container.G_564_y(index);
        container.v_4262_N(index);
    }

    private void n_1700_B(K_3710_b container) {
        Y_2498_n resultSlot = container.n_1700_B(2);
        if (resultSlot.J_1907_R()) {
            Q_79_h.c_3005_b.w_1457_N.windowClick(container.u_1723_Y, 2, 0, a_408_T.J_1907_R, Q_79_h.c_3005_b.Y_259_p);
        }
    }

    @Override
    public void J_1907_R() {
        this.Q_4569_t = 0;
        this.M_182_A = true;
        super.J_1907_R();
    }
}

