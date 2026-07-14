/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.Q_1939_l;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_4592_V;
import lightning.product.r_3979_X;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class I_3289_C
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041d\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0441 AttackAura", false);
    private final I_686_h w_1484_f = new I_686_h("\u041a\u043e\u043b-\u0432\u043e \u0433\u043e\u043b\u043e\u0434\u0430", 18.0f, 1.0f, 20.0f, 0.5f);
    private final p_1977_n t_148_a = new p_1977_n("\u0415\u0441\u0442\u044c \u0437\u043e\u043b\u043e\u0442\u044b\u0435 \u044f\u0431\u043b\u043e\u043a\u0438", true);
    private final I_686_h s_956_w = new I_686_h("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u0434\u043b\u044f \u0413\u0410", 14.0f, 1.0f, 20.0f, 0.5f, this.t_148_a::t_148_a);
    private final p_1977_n u_2550_I = new p_1977_n("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0413\u0410", true, this.t_148_a::t_148_a);
    private boolean M_588_G = false;
    private boolean P_4830_p = false;
    private x_1688_C h_1847_R = null;

    public I_3289_C() {
        super("AutoEat", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        r_3979_X aura;
        X_3546_T attackAura;
        if (I_3289_C.c_3005_b.Y_259_p == null || I_3289_C.c_3005_b.Y_601_j == null) {
            this.h_1847_R();
            return;
        }
        if (I_3289_C.c_3005_b.Y_1740_V != null && !(I_3289_C.c_3005_b.Y_1740_V instanceof Q_1939_l)) {
            this.h_1847_R();
            return;
        }
        if (this.v_4262_N.t_148_a().booleanValue() && (attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class)) != null && attackAura.w_1484_f() && (aura = (r_3979_X)attackAura).h_1847_R() != null) {
            this.h_1847_R();
            return;
        }
        Z_1993_T offHandStack = I_3289_C.c_3005_b.Y_259_p.S_4035_N();
        Z_1993_T mainHandStack = I_3289_C.c_3005_b.Y_259_p.A_2714_y();
        boolean offHandGApple = this.n_1700_B(offHandStack);
        boolean mainHandGApple = this.n_1700_B(mainHandStack);
        boolean hasGApple = offHandGApple || mainHandGApple;
        boolean offHandFood = this.J_1907_R(offHandStack) && !offHandGApple;
        boolean mainHandFood = this.J_1907_R(mainHandStack) && !mainHandGApple;
        boolean hasFood = offHandFood || mainHandFood;
        float currentHealth = I_3289_C.c_3005_b.Y_259_p.g_46_E() + I_3289_C.c_3005_b.Y_259_p.U_3823_u();
        int currentHunger = I_3289_C.c_3005_b.Y_259_p.P_2295_B().n_1700_B();
        if (this.P_4830_p) {
            if (currentHealth > ((Float)this.s_956_w.J_1907_R()).floatValue()) {
                this.h_1847_R();
            } else {
                if (I_3289_C.c_3005_b.Y_259_p.Y_601_j()) {
                    return;
                }
                this.h_1847_R();
            }
        }
        if (this.t_148_a.t_148_a().booleanValue() && hasGApple && currentHealth <= ((Float)this.s_956_w.J_1907_R()).floatValue()) {
            x_1688_C handToUse = offHandGApple ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
            this.n_1700_B(handToUse, true);
            return;
        }
        if (hasFood && currentHunger <= ((Float)this.w_1484_f.J_1907_R()).intValue()) {
            if (this.u_2550_I.t_148_a().booleanValue() && this.t_148_a.t_148_a().booleanValue() && hasGApple && currentHealth <= ((Float)this.s_956_w.J_1907_R()).floatValue() + 4.0f) {
                return;
            }
            x_1688_C handToUse = offHandFood ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
            this.n_1700_B(handToUse, false);
            return;
        }
        if (this.M_588_G && !this.P_4830_p && I_3289_C.c_3005_b.Y_259_p.Y_601_j()) {
            if (currentHunger >= 20) {
                this.h_1847_R();
            }
            return;
        }
        if (this.M_588_G) {
            this.h_1847_R();
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.p_863_D || stack.J_1907_R() == q_4592_V.E_4612_l;
    }

    private boolean J_1907_R(Z_1993_T stack) {
        return !stack.n_1700_B() && stack.J_1907_R().Y_259_p();
    }

    private void n_1700_B(x_1688_C hand, boolean isGApple) {
        if (this.M_588_G && this.h_1847_R == hand && this.P_4830_p == isGApple) {
            if (!I_3289_C.c_3005_b.Y_259_p.Y_601_j()) {
                I_3289_C.c_3005_b.w_1457_N.processRightClick(I_3289_C.c_3005_b.Y_259_p, I_3289_C.c_3005_b.Y_601_j, hand);
                I_3289_C.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
            }
            return;
        }
        if (this.M_588_G) {
            this.h_1847_R();
        }
        this.M_588_G = true;
        this.P_4830_p = isGApple;
        this.h_1847_R = hand;
        I_3289_C.c_3005_b.w_1457_N.processRightClick(I_3289_C.c_3005_b.Y_259_p, I_3289_C.c_3005_b.Y_601_j, hand);
        I_3289_C.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
    }

    private void h_1847_R() {
        if (this.M_588_G) {
            if (I_3289_C.c_3005_b.Y_259_p != null && I_3289_C.c_3005_b.Y_259_p.Y_601_j()) {
                I_3289_C.c_3005_b.w_1457_N.onStoppedUsingItem(I_3289_C.c_3005_b.Y_259_p);
            }
            I_3289_C.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
            this.M_588_G = false;
            this.P_4830_p = false;
            this.h_1847_R = null;
        }
    }

    @Override
    public void J_1907_R() {
        this.h_1847_R();
        super.J_1907_R();
    }
}

