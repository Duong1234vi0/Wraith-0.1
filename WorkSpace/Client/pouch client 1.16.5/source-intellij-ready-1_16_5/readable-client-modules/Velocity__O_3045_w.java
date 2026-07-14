/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.Q_2753_H;
import lightning.product.W_2770_z;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.d_4412_Z;
import lightning.product.e_2866_D;
import lightning.product.g_805_K;
import lightning.product.h_1015_G;
import lightning.product.m_4093_W;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.t_3138_Z;
import lightning.product.u_530_F;
import lightning.product.v_887_r;
import lightning.product.y_2603_k;

public class O_3045_w
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u041c\u043e\u0434", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041b\u0435\u0433\u0438\u0442", "Reverse", "Tick", "Grim");
    private final p_1977_n w_1484_f = new p_1977_n("\u041f\u0440\u044b\u0433\u0430\u0442\u044c", true, () -> this.v_4262_N.J_1907_R("\u041b\u0435\u0433\u0438\u0442"));
    private final I_686_h t_148_a = new I_686_h("\u0421\u0438\u043b\u0430 \u0440\u0435\u0432\u0435\u0440\u0441\u0430", 0.5f, 0.1f, 1.0f, 0.1f, () -> this.v_4262_N.J_1907_R("Reverse"));
    private final p_1977_n s_956_w = new p_1977_n("\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0440\u0435\u0432\u0435\u0440\u0441", false, () -> this.v_4262_N.J_1907_R("Reverse"));
    private final I_686_h u_2550_I = new I_686_h("\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u0442\u0438\u043a\u043e\u0432", 2.0f, 1.0f, 5.0f, 1.0f, () -> this.v_4262_N.J_1907_R("Tick"));
    private final p_1977_n M_588_G = new p_1977_n("\u041e\u0442\u043c\u0435\u043d\u044f\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439", true, () -> this.v_4262_N.J_1907_R("Tick"));
    private final p_1977_n P_4830_p = new p_1977_n("\u041f\u0440\u044b\u0436\u043e\u043a", true, () -> this.v_4262_N.J_1907_R("Grim"));
    private final p_1977_n h_1847_R = new p_1977_n("\u0421\u0442\u0440\u0435\u0439\u0444", true, () -> this.v_4262_N.J_1907_R("Grim"));
    private final I_686_h Q_4569_t = new I_686_h("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c", 6.0f, 1.0f, 12.0f, 1.0f, () -> this.v_4262_N.J_1907_R("Grim"));
    private e_2866_D M_182_A = e_2866_D.n_1700_B;
    private boolean t_1786_h = false;
    private final W_2770_z N_4405_n = new W_2770_z();
    private int w_1457_N = 0;

    public O_3045_w() {
        super("Velocity", y_2603_k.n_1700_B);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t);
    }

    @Override
    public void J_1907_R() {
        this.M_182_A = e_2866_D.n_1700_B;
        this.t_1786_h = false;
        this.w_1457_N = 0;
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (O_3045_w.c_3005_b.Y_259_p == null || !e.J_1907_R()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Grim")) {
            g_805_K explosion;
            e_2866_D expVel;
            m_4093_W p;
            t_3138_Z<?> t_3138_Z2 = e.G_564_y();
            if (t_3138_Z2 instanceof m_4093_W && (p = (m_4093_W)t_3138_Z2).J_1907_R() == O_3045_w.c_3005_b.Y_259_p.j_276_v()) {
                this.M_182_A = new e_2866_D((double)p.R_4764_Y() / 8000.0, (double)p.G_564_y() / 8000.0, (double)p.P_1922_E() / 8000.0);
                this.t_1786_h = true;
                this.w_1457_N = 0;
            }
            if ((t_3138_Z2 = e.G_564_y()) instanceof g_805_K && (expVel = new e_2866_D((explosion = (g_805_K)t_3138_Z2).J_1907_R(), explosion.R_4764_Y(), explosion.G_564_y())).v_4262_N() > 0.0) {
                this.M_182_A = expVel;
                this.t_1786_h = true;
                this.w_1457_N = 0;
            }
            return;
        }
        t_3138_Z<?> expVel = e.G_564_y();
        if (!(expVel instanceof m_4093_W)) {
            return;
        }
        m_4093_W p = (m_4093_W)expVel;
        if (p.J_1907_R() != O_3045_w.c_3005_b.Y_259_p.j_276_v()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("\u041e\u0431\u044b\u0447\u043d\u044b\u0439")) {
            e.n_1700_B(true);
        }
        if (this.v_4262_N.J_1907_R("\u041b\u0435\u0433\u0438\u0442")) {
            this.N_4405_n.n_1700_B(700L);
            if (this.N_4405_n.R_4764_Y()) {
                this.M_182_A = new e_2866_D(p.R_4764_Y(), p.G_564_y(), p.P_1922_E());
            }
            this.N_4405_n.n_1700_B(e);
        }
        if (this.v_4262_N.J_1907_R("Reverse")) {
            e.n_1700_B(true);
            double motionX = (double)p.R_4764_Y() / 8000.0;
            double motionY = (double)p.G_564_y() / 8000.0;
            double motionZ = (double)p.P_1922_E() / 8000.0;
            float strength = ((Float)this.t_148_a.J_1907_R()).floatValue();
            O_3045_w.c_3005_b.Y_259_p.s_956_w(-motionX * (double)strength, this.s_956_w.t_148_a() != false ? -motionY * (double)strength : motionY * 0.5, -motionZ * (double)strength);
        }
        if (this.v_4262_N.J_1907_R("Tick")) {
            ++this.w_1457_N;
            int interval = ((Float)this.u_2550_I.J_1907_R()).intValue();
            if (this.w_1457_N % interval == 0) {
                e.n_1700_B(true);
            } else {
                double motionX = (double)p.R_4764_Y() / 8000.0;
                double motionY = (double)p.G_564_y() / 8000.0;
                double motionZ = (double)p.P_1922_E() / 8000.0;
                O_3045_w.c_3005_b.Y_259_p.s_956_w(motionX * 0.3, this.M_588_G.t_148_a() != false ? 0.0 : motionY * 0.5, motionZ * 0.3);
                e.n_1700_B(true);
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(v_887_r e) {
        if (this.v_4262_N.J_1907_R("\u041b\u0435\u0433\u0438\u0442")) {
            this.N_4405_n.n_1700_B(e);
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!this.t_1786_h || O_3045_w.c_3005_b.Y_259_p == null || !this.v_4262_N.J_1907_R("Grim")) {
            return;
        }
        ++this.w_1457_N;
        if (this.w_1457_N > ((Float)this.Q_4569_t.J_1907_R()).intValue()) {
            this.t_1786_h = false;
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        if (this.v_4262_N.J_1907_R("Grim")) {
            if (!this.t_1786_h || O_3045_w.c_3005_b.Y_259_p == null) {
                return;
            }
            if (O_3045_w.c_3005_b.Y_259_p.H_3699_F <= 0 && this.w_1457_N > 2) {
                this.t_1786_h = false;
                return;
            }
            double hLenSq = this.M_182_A.J_1907_R * this.M_182_A.J_1907_R + this.M_182_A.G_564_y * this.M_182_A.G_564_y;
            if (hLenSq < 0.001) {
                return;
            }
            if (this.h_1847_R.t_148_a().booleanValue()) {
                double relativeAngle = this.h_1847_R();
                float forward = 0.0f;
                float strafe = 0.0f;
                if (relativeAngle > -45.0 && relativeAngle < 45.0) {
                    forward = 1.0f;
                } else if (relativeAngle >= 45.0 && relativeAngle <= 135.0) {
                    strafe = -1.0f;
                } else if (relativeAngle <= -45.0 && relativeAngle >= -135.0) {
                    strafe = 1.0f;
                } else {
                    forward = -1.0f;
                }
                e.n_1700_B(forward);
                e.J_1907_R(strafe);
            }
            if (this.P_4830_p.t_148_a().booleanValue() && O_3045_w.c_3005_b.Y_259_p.M_1641_O()) {
                e.P_1922_E(true);
            }
            return;
        }
        if (!this.v_4262_N.J_1907_R("\u041b\u0435\u0433\u0438\u0442")) {
            return;
        }
        if (o_148_s.Y_601_j().J_1907_R().n_1700_B(d_4412_Z.class).w_1484_f()) {
            this.M_182_A = e_2866_D.n_1700_B;
            return;
        }
        if (O_3045_w.c_3005_b.Y_259_p.H_3699_F > 0 && this.M_182_A.v_4262_N() > 0.0) {
            double relativeAngle = this.h_1847_R();
            float forward = 0.0f;
            float strafe = 0.0f;
            if (relativeAngle > -45.0 && relativeAngle < 45.0) {
                forward = 1.0f;
            } else if (!(relativeAngle > 135.0) && !(relativeAngle < -135.0)) {
                if (relativeAngle >= 45.0 && relativeAngle <= 135.0) {
                    strafe = -1.0f;
                } else if (relativeAngle <= -45.0 && relativeAngle >= -135.0) {
                    strafe = 1.0f;
                }
            } else {
                forward = -1.0f;
            }
            e.n_1700_B(forward);
            e.J_1907_R(strafe);
            e.P_1922_E(this.w_1484_f.t_148_a() != false && O_3045_w.c_3005_b.Y_259_p.M_1641_O());
        } else {
            this.M_182_A = e_2866_D.n_1700_B;
        }
    }

    private double h_1847_R() {
        double yaw = O_3045_w.c_3005_b.Y_259_p.p_178_J;
        double dx = -this.M_182_A.J_1907_R;
        double dz = -this.M_182_A.G_564_y;
        double attackerYaw = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;
        return u_530_F.u_1723_Y(attackerYaw - yaw);
    }
}

