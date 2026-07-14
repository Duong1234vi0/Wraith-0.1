/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.K_4096_w;
import lightning.product.O_4882_g;
import lightning.product.Q_2753_H;
import lightning.product.V_4286_F;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.X_3744_n;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.t_3138_Z;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class l_2912_c
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0431\u0440\u0430\u0442\u044c \u0443\u0434\u043e\u0447\u043a\u0443", true);
    private final I_686_h w_1484_f = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u043e\u0434\u0441\u0435\u0447\u043a\u0438 (\u043c\u0441)", 600.0f, 100.0f, 2000.0f, 50.0f);
    private final I_686_h t_148_a = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u0435\u0440\u0435\u0437\u0430\u0431\u0440\u043e\u0441\u0430 (\u043c\u0441)", 300.0f, 100.0f, 1000.0f, 50.0f);
    private final p_1977_n s_956_w = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c", true);
    private final I_686_h u_2550_I = new I_686_h("\u041c\u0438\u043d. \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c (%)", 10.0f, 1.0f, 50.0f, 1.0f, () -> this.s_956_w.t_148_a());
    private final p_1977_n M_588_G = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", true);
    private final p_1977_n P_4830_p = new p_1977_n("\u041e\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0442\u044c \u043f\u0440\u0438 \u043f\u043e\u043b\u043d\u043e\u043c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", false, () -> this.M_588_G.t_148_a());
    private final p_1977_n h_1847_R = new p_1977_n("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u043d\u0430\u043b\u0438\u0447\u0438\u0435 \u0431\u043e\u0431\u0431\u0435\u0440\u0430", true);
    private final p_1977_n Q_4569_t = new p_1977_n("\u0410\u0432\u0442\u043e \u0441\u043c\u0435\u043d\u0430 \u0443\u0434\u043e\u0447\u043a\u0438 \u043f\u0440\u0438 \u043f\u043e\u043b\u043e\u043c\u043a\u0435", true);
    private final p_1977_n M_182_A = new p_1977_n("\u0410\u0432\u0442\u043e \u0437\u0430\u0431\u0440\u043e\u0441 \u0432 \u0432\u043e\u0434\u0443", true);
    private final I_686_h t_1786_h = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0430\u0432\u0442\u043e \u0437\u0430\u0431\u0440\u043e\u0441\u0430 (\u043c\u0441)", 500.0f, 100.0f, 2000.0f, 50.0f, () -> this.M_182_A.t_148_a());
    private boolean N_4405_n = false;
    private boolean w_1457_N = false;
    private int Y_601_j = -1;
    private final V_4557_X Y_259_p = new V_4557_X();
    private final V_4557_X Q_2552_b = new V_4557_X();

    public l_2912_c() {
        super("AutoFish", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h);
    }

    @Override
    public void J_1907_R() {
        this.N_4405_n = false;
        this.w_1457_N = false;
        this.Y_601_j = -1;
        this.Y_259_p.n_1700_B();
        this.Q_2552_b.n_1700_B();
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        boolean hasBobber;
        Z_1993_T rodStack;
        if (l_2912_c.c_3005_b.Y_259_p == null || l_2912_c.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.M_588_G.t_148_a().booleanValue() && this.M_182_A() && this.P_4830_p.t_148_a().booleanValue()) {
            return;
        }
        if (this.v_4262_N.t_148_a().booleanValue() && this.Y_601_j == -1) {
            this.Q_4569_t();
        }
        if (this.Y_601_j != -1 && this.s_956_w.t_148_a().booleanValue() && (rodStack = l_2912_c.c_3005_b.Y_259_p.l_1268_F.s_956_w(this.Y_601_j)).J_1907_R() instanceof O_4882_g && this.n_1700_B(rodStack)) {
            if (this.Q_4569_t.t_148_a().booleanValue()) {
                this.Y_601_j = -1;
                this.Q_4569_t();
            } else {
                return;
            }
        }
        if (this.Y_601_j != -1 && l_2912_c.c_3005_b.Y_259_p.l_1268_F.G_564_y != this.Y_601_j) {
            l_2912_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Y_601_j));
            l_2912_c.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Y_601_j;
        }
        boolean bl = hasBobber = l_2912_c.c_3005_b.Y_259_p.X_2960_b != null && !l_2912_c.c_3005_b.Y_259_p.X_2960_b.t_4219_U;
        if (this.h_1847_R.t_148_a().booleanValue() && this.w_1457_N && hasBobber) {
            return;
        }
        if (this.M_182_A.t_148_a().booleanValue() && this.Y_601_j != -1 && !hasBobber && !this.N_4405_n && !this.w_1457_N) {
            if (this.Q_2552_b.n_1700_B((double)((Float)this.t_1786_h.J_1907_R()).intValue())) {
                this.h_1847_R();
                this.Q_2552_b.n_1700_B();
            }
        } else if (hasBobber || this.N_4405_n || this.w_1457_N) {
            this.Q_2552_b.n_1700_B();
        }
        if (this.N_4405_n && this.Y_259_p.n_1700_B((double)((Float)this.w_1484_f.J_1907_R()).intValue())) {
            if (hasBobber) {
                this.h_1847_R();
                this.N_4405_n = false;
                this.w_1457_N = true;
                this.Y_259_p.n_1700_B();
            } else {
                this.N_4405_n = false;
                this.w_1457_N = true;
                this.Y_259_p.n_1700_B();
            }
        }
        if (this.w_1457_N && this.Y_259_p.n_1700_B((double)((Float)this.t_148_a.J_1907_R()).intValue())) {
            if (!hasBobber) {
                this.h_1847_R();
                this.w_1457_N = false;
                this.Y_259_p.n_1700_B();
            } else {
                this.Y_259_p.n_1700_B();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        X_3744_n packet;
        if (l_2912_c.c_3005_b.Y_259_p == null || l_2912_c.c_3005_b.Y_601_j == null) {
            return;
        }
        t_3138_Z<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof X_3744_n && (packet = (X_3744_n)t_3138_Z2).J_1907_R() == V_4286_F.I_3637_j) {
            this.N_4405_n = true;
            this.Y_259_p.n_1700_B();
        }
    }

    private void h_1847_R() {
        if (this.Y_601_j != -1 && l_2912_c.c_3005_b.Y_259_p.l_1268_F.s_956_w(this.Y_601_j).J_1907_R() instanceof O_4882_g) {
            if (l_2912_c.c_3005_b.Y_259_p.l_1268_F.G_564_y != this.Y_601_j) {
                l_2912_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Y_601_j));
                l_2912_c.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Y_601_j;
            }
            l_2912_c.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            l_2912_c.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        }
    }

    private void Q_4569_t() {
        int bestRodSlot = -1;
        int maxEnchantments = -1;
        for (int i = 0; i < 9; ++i) {
            int enchantmentCount;
            Z_1993_T stack = l_2912_c.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!(stack.J_1907_R() instanceof O_4882_g) || this.s_956_w.t_148_a().booleanValue() && this.n_1700_B(stack) || (enchantmentCount = K_4096_w.n_1700_B(stack).size()) <= maxEnchantments) continue;
            maxEnchantments = enchantmentCount;
            bestRodSlot = i;
        }
        if (bestRodSlot != -1) {
            this.Y_601_j = bestRodSlot;
        }
    }

    private boolean n_1700_B(Z_1993_T rodStack) {
        int currentDamage;
        if (!rodStack.P_1922_E()) {
            return false;
        }
        int maxDamage = rodStack.w_1484_f();
        float durabilityPercent = (float)(maxDamage - (currentDamage = rodStack.v_4262_N())) / (float)maxDamage * 100.0f;
        return durabilityPercent < ((Float)this.u_2550_I.J_1907_R()).floatValue();
    }

    private boolean M_182_A() {
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = l_2912_c.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!stack.n_1700_B()) continue;
            return false;
        }
        return true;
    }
}

