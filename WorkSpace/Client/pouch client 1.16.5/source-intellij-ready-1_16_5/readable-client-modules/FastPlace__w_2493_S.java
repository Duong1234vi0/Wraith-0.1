/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;

public class w_2493_S
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0431\u0435\u0437 \u043f\u0432\u043f \u0440\u0435\u0436\u0438\u043c\u0430", false);
    private final I_686_h w_1484_f = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 0.0f, 0.0f, 4.0f, 1.0f);
    private final p_1977_n t_148_a = new p_1977_n("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430", false);
    private final p_1977_n s_956_w = new p_1977_n("\u0411\u043b\u043e\u043a\u043e\u0432", false);
    private final p_1977_n u_2550_I = new p_1977_n("\u042f\u0438\u0446", false);
    private final p_1977_n M_588_G = new p_1977_n("\u0421\u043d\u0435\u0436\u043a\u043e\u0432", false);
    private final p_1977_n P_4830_p = new p_1977_n("\u042d\u043d\u0434\u0435\u0440-\u043f\u0435\u0440\u043b\u043e\u0432", false);
    private final p_1977_n h_1847_R = new p_1977_n("\u0412\u0437\u0440\u044b\u0432\u043d\u044b\u0445 \u0437\u0435\u043b\u0438\u0439", false);
    private final p_1977_n Q_4569_t = new p_1977_n("\u041e\u0441\u0435\u0434\u0430\u044e\u0449\u0438\u0445 \u0437\u0435\u043b\u0438\u0439", false);

    public w_2493_S() {
        super("FastPlace", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.s_956_w, this.t_148_a, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (w_2493_S.c_3005_b.Y_259_p == null || w_2493_S.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.t_148_a().booleanValue() && q_3115_L.n_1700_B()) {
            return;
        }
        Z_1993_T mainHand = w_2493_S.c_3005_b.Y_259_p.A_2714_y();
        Z_1993_T offHand = w_2493_S.c_3005_b.Y_259_p.S_4035_N();
        boolean shouldApplyFastPlace = false;
        if (this.s_956_w.t_148_a().booleanValue() && (this.n_1700_B(mainHand) || this.n_1700_B(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.u_2550_I.t_148_a().booleanValue() && (this.J_1907_R(mainHand) || this.J_1907_R(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.M_588_G.t_148_a().booleanValue() && (this.R_4764_Y(mainHand) || this.R_4764_Y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.P_4830_p.t_148_a().booleanValue() && (this.G_564_y(mainHand) || this.G_564_y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.t_148_a.t_148_a().booleanValue() && (this.P_1922_E(mainHand) || this.P_1922_E(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.h_1847_R.t_148_a().booleanValue() && (this.u_1723_Y(mainHand) || this.u_1723_Y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.Q_4569_t.t_148_a().booleanValue() && (this.v_4262_N(mainHand) || this.v_4262_N(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (shouldApplyFastPlace) {
            w_2493_S.c_3005_b.c_3005_b = ((Float)this.w_1484_f.J_1907_R()).intValue();
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("BlockItem");
    }

    private boolean J_1907_R(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.s_4405_m;
    }

    private boolean R_4764_Y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.i_770_g;
    }

    private boolean G_564_y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.v_2746_S;
    }

    private boolean P_1922_E(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.s_3084_y;
    }

    private boolean u_1723_Y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("SplashPotionItem");
    }

    private boolean v_4262_N(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("LingeringPotionItem");
    }
}

