/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_1573_j;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_3504_M;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.j_4680_H;
import lightning.product.p_1183_T;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.q_817_e;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class v_1977_Z
extends X_3546_T {
    public q_366_O v_4262_N = new q_366_O("\u041c\u043e\u0434", "ReallyWorld", "ReallyWorld", "Grim", "Matrix", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "GrimTick", "Lony/Holy");
    public static int w_1484_f = 0;

    public v_1977_Z() {
        super("NoSlow", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(q_817_e event) {
        if (v_1977_Z.c_3005_b.Y_259_p == null || v_1977_Z.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (v_1977_Z.c_3005_b.Y_259_p.Y_601_j()) {
            switch ((String)this.v_4262_N.J_1907_R()) {
                case "ReallyWorld": {
                    this.J_1907_R(event);
                    break;
                }
                case "Grim": {
                    this.P_1922_E(event);
                    break;
                }
                case "Matrix": {
                    this.v_4262_N(event);
                    break;
                }
                case "Grim2": {
                    this.G_564_y(event);
                    break;
                }
                case "GrimTick": {
                    this.R_4764_Y(event);
                    break;
                }
                case "Lony/Holy": {
                    this.u_1723_Y(event);
                    break;
                }
                case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                    event.n_1700_B(true);
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (v_1977_Z.c_3005_b.Y_259_p == null || v_1977_Z.c_3005_b.Y_259_p.k_578_l()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("ReallyWorld")) {
            w_1484_f = v_1977_Z.c_3005_b.Y_259_p.Y_601_j() ? ++w_1484_f : 0;
        }
        if (this.v_4262_N.J_1907_R("Lony/Holy")) {
            this.h_1847_R();
        }
    }

    private void J_1907_R(q_817_e e) {
        int a = 2;
        if (w_1484_f >= a) {
            e.n_1700_B(true);
            w_1484_f = 0;
        }
    }

    private void R_4764_Y(q_817_e e) {
        if (v_1977_Z.c_3005_b.Y_259_p.t_2577_l % 2 == 0 && !v_1977_Z.c_3005_b.Y_259_p.q_2307_F()) {
            e.n_1700_B(true);
        }
    }

    private void G_564_y(q_817_e e) {
        if (v_1977_Z.c_3005_b.Y_259_p.Q_2552_b() == x_1688_C.J_1907_R) {
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y % 8 + 1));
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y % 7 + 2));
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y));
        } else if (v_1977_Z.c_3005_b.Y_259_p.g_1031_K() <= 3 || v_1977_Z.c_3005_b.Y_259_p.t_2577_l % 2 == 0) {
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
        }
        e.n_1700_B(true);
    }

    private void P_1922_E(q_817_e e) {
        boolean mainHandActive;
        boolean offHandActive = v_1977_Z.c_3005_b.Y_259_p.Y_601_j() && v_1977_Z.c_3005_b.Y_259_p.Q_2552_b() == x_1688_C.J_1907_R;
        boolean bl = mainHandActive = v_1977_Z.c_3005_b.Y_259_p.Y_601_j() && v_1977_Z.c_3005_b.Y_259_p.Q_2552_b() == x_1688_C.n_1700_B;
        if ((v_1977_Z.c_3005_b.Y_259_p.U_144_f() >= 25 || v_1977_Z.c_3005_b.Y_259_p.U_144_f() <= 4) && v_1977_Z.c_3005_b.Y_259_p.S_4035_N().J_1907_R() != q_4592_V.G_4948_k) {
            return;
        }
        if (v_1977_Z.c_3005_b.Y_259_p.Y_601_j() && !v_1977_Z.c_3005_b.Y_259_p.y_2772_m()) {
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y));
            if (offHandActive && !v_1977_Z.c_3005_b.Y_259_p.p_1458_L().n_1700_B(v_1977_Z.c_3005_b.Y_259_p.S_4035_N().J_1907_R())) {
                int old = v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(old + 1 > 8 ? old - 1 : old + 1));
                v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y));
                v_1977_Z.c_3005_b.Y_259_p.b_(false);
                e.n_1700_B(true);
            }
            if (mainHandActive && !v_1977_Z.c_3005_b.Y_259_p.p_1458_L().n_1700_B(v_1977_Z.c_3005_b.Y_259_p.A_2714_y().J_1907_R())) {
                v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.J_1907_R));
                if (v_1977_Z.c_3005_b.Y_259_p.S_4035_N().M_588_G().equals((Object)F_1573_j.n_1700_B)) {
                    e.n_1700_B(true);
                }
            }
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(v_1977_Z.c_3005_b.Y_259_p.l_1268_F.G_564_y));
        }
    }

    private void u_1723_Y(q_817_e e) {
        if (v_1977_Z.c_3005_b.Y_259_p.Q_2552_b() == x_1688_C.J_1907_R) {
            this.R_4764_Y(e);
            return;
        }
        if (v_1977_Z.c_3005_b.Y_259_p.g_1031_K() > 0) {
            e.n_1700_B(true);
        }
    }

    private void h_1847_R() {
        if (v_1977_Z.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if (v_1977_Z.c_3005_b.Y_259_p.Y_601_j() && v_1977_Z.c_3005_b.Y_259_p.g_1031_K() == 0) {
            v_1977_Z.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new j_4680_H(j_4680_H.n_1700_B.G_564_y, c_1514_x.ZERO, v_1977_Z.c_3005_b.Y_259_p.o_2767_H()));
        }
    }

    private void v_4262_N(q_817_e eventNoSlow) {
        boolean isFalling = (double)v_1977_Z.c_3005_b.Y_259_p.U_1241_n > 0.725;
        eventNoSlow.n_1700_B(true);
        if (v_1977_Z.c_3005_b.Y_259_p.M_1641_O() && !v_1977_Z.c_3005_b.Y_259_p.G_564_y.jump) {
            if (v_1977_Z.c_3005_b.Y_259_p.t_2577_l % 2 == 0) {
                boolean isNotStrafing = v_1977_Z.c_3005_b.Y_259_p.L_1362_X == 0.0f;
                float speedMultiplier = isNotStrafing ? 0.5f : 0.4f;
                v_1977_Z.c_3005_b.Y_259_p.I_4348_c().J_1907_R *= (double)speedMultiplier;
                v_1977_Z.c_3005_b.Y_259_p.I_4348_c().G_564_y *= (double)speedMultiplier;
            }
        } else if (isFalling) {
            boolean isVeryFastFalling = (double)v_1977_Z.c_3005_b.Y_259_p.U_1241_n > 1.4;
            float speedMultiplier = isVeryFastFalling ? 0.95f : 0.97f;
            v_1977_Z.c_3005_b.Y_259_p.I_4348_c().J_1907_R *= (double)speedMultiplier;
            v_1977_Z.c_3005_b.Y_259_p.I_4348_c().G_564_y *= (double)speedMultiplier;
        }
    }
}

