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

public class z_1063_O
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435", 16.0f, 1.0f, 20.0f, 0.5f);
    private final p_1977_n w_1484_f = new p_1977_n("\u041d\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0441 AttackAura", false);
    private final p_1977_n t_148_a = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", false);
    private boolean s_956_w = false;
    private x_1688_C u_2550_I = null;

    public z_1063_O() {
        super("AutoSoup", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        r_3979_X aura;
        X_3546_T attackAura;
        if (z_1063_O.c_3005_b.Y_259_p == null || z_1063_O.c_3005_b.Y_601_j == null) {
            this.h_1847_R();
            return;
        }
        if (this.t_148_a.t_148_a().booleanValue() && (z_1063_O.c_3005_b.Y_1740_V == null || !(z_1063_O.c_3005_b.Y_1740_V instanceof Q_1939_l))) {
            this.h_1847_R();
            return;
        }
        if (z_1063_O.c_3005_b.Y_1740_V != null && !(z_1063_O.c_3005_b.Y_1740_V instanceof Q_1939_l)) {
            this.h_1847_R();
            return;
        }
        if (this.w_1484_f.t_148_a().booleanValue() && (attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class)) != null && attackAura.w_1484_f() && (aura = (r_3979_X)attackAura).h_1847_R() != null) {
            this.h_1847_R();
            return;
        }
        float currentHealth = z_1063_O.c_3005_b.Y_259_p.g_46_E() + z_1063_O.c_3005_b.Y_259_p.U_3823_u();
        if (this.s_956_w) {
            if (currentHealth >= 20.0f) {
                this.h_1847_R();
            } else {
                if (z_1063_O.c_3005_b.Y_259_p.Y_601_j()) {
                    return;
                }
                this.h_1847_R();
            }
        }
        if (currentHealth <= ((Float)this.v_4262_N.J_1907_R()).floatValue()) {
            Z_1993_T offHandStack = z_1063_O.c_3005_b.Y_259_p.S_4035_N();
            Z_1993_T mainHandStack = z_1063_O.c_3005_b.Y_259_p.A_2714_y();
            boolean offHandSoup = this.n_1700_B(offHandStack);
            boolean mainHandSoup = this.n_1700_B(mainHandStack);
            if (offHandSoup || mainHandSoup) {
                x_1688_C handToUse = offHandSoup ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
                this.n_1700_B(handToUse);
                return;
            }
            for (int i = 0; i < 9; ++i) {
                Z_1993_T stack = z_1063_O.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!this.n_1700_B(stack)) continue;
                z_1063_O.c_3005_b.Y_259_p.l_1268_F.G_564_y = i;
                this.n_1700_B(x_1688_C.n_1700_B);
                return;
            }
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == q_4592_V.b_2152_i || stack.J_1907_R() == q_4592_V.N_3347_G || stack.J_1907_R() == q_4592_V.S_3570_g;
    }

    private void n_1700_B(x_1688_C hand) {
        if (this.s_956_w && this.u_2550_I == hand) {
            if (!z_1063_O.c_3005_b.Y_259_p.Y_601_j()) {
                z_1063_O.c_3005_b.w_1457_N.processRightClick(z_1063_O.c_3005_b.Y_259_p, z_1063_O.c_3005_b.Y_601_j, hand);
                z_1063_O.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
            }
            return;
        }
        if (this.s_956_w) {
            this.h_1847_R();
        }
        this.s_956_w = true;
        this.u_2550_I = hand;
        z_1063_O.c_3005_b.w_1457_N.processRightClick(z_1063_O.c_3005_b.Y_259_p, z_1063_O.c_3005_b.Y_601_j, hand);
        z_1063_O.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
    }

    private void h_1847_R() {
        if (this.s_956_w) {
            if (z_1063_O.c_3005_b.Y_259_p != null && z_1063_O.c_3005_b.Y_259_p.Y_601_j()) {
                z_1063_O.c_3005_b.w_1457_N.onStoppedUsingItem(z_1063_O.c_3005_b.Y_259_p);
            }
            z_1063_O.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
            this.s_956_w = false;
            this.u_2550_I = null;
        }
    }

    @Override
    public void J_1907_R() {
        this.h_1847_R();
        super.J_1907_R();
    }
}

