/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3331_Z;
import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.t_2650_P;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;

public class f_24_h
extends X_3546_T {
    public q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Legit", "Legit", "Motion", "Grim", "PolarBack");
    private final I_686_h w_1484_f = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.5f, 0.1f, 2.0f, 0.05f, () -> this.v_4262_N.J_1907_R("Motion"));
    private final p_1977_n t_148_a = new p_1977_n("\u0410\u0432\u0442\u043e \u043f\u0440\u043e\u0431\u0435\u043b/\u0448\u0438\u0444\u0442", false, () -> this.v_4262_N.J_1907_R("Legit"));
    private final p_1977_n s_956_w = new p_1977_n("\u041d\u0435 \u0432\u0441\u043f\u043b\u044b\u0432\u0430\u0442\u044c", false, () -> this.t_148_a.t_148_a());
    private int u_2550_I = 0;
    private boolean M_588_G = false;

    public f_24_h() {
        super("WaterSpeed", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Override
    public void J_1907_R() {
        this.u_2550_I = 0;
        if (f_24_h.c_3005_b.Y_259_p != null) {
            f_24_h.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            f_24_h.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
        }
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (f_24_h.c_3005_b.Y_259_p == null || f_24_h.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("PolarBack")) {
            this.Y_601_j();
            return;
        }
        this.M_182_A();
        if (this.v_4262_N.J_1907_R("Legit") && this.t_148_a.t_148_a().booleanValue()) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        boolean nearSurface;
        if (!f_24_h.c_3005_b.Y_259_p.a_2180_A()) {
            if (this.M_588_G) {
                f_24_h.c_3005_b.P_4830_p.T_69_K.n_1700_B(f_24_h.c_3005_b.P_4830_p.T_69_K.G_564_y());
                f_24_h.c_3005_b.P_4830_p.p_178_J.n_1700_B(f_24_h.c_3005_b.P_4830_p.p_178_J.G_564_y());
                this.M_588_G = false;
            }
            this.u_2550_I = 0;
            return;
        }
        this.M_588_G = true;
        ++this.u_2550_I;
        boolean bl = nearSurface = this.s_956_w.t_148_a() != false && this.Q_4569_t();
        if (nearSurface) {
            f_24_h.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            f_24_h.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
            return;
        }
        int cycle = this.u_2550_I % 10;
        if ((float)cycle < 5.5f) {
            f_24_h.c_3005_b.P_4830_p.T_69_K.n_1700_B(true);
            f_24_h.c_3005_b.P_4830_p.p_178_J.n_1700_B(false);
        } else {
            f_24_h.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            f_24_h.c_3005_b.P_4830_p.p_178_J.n_1700_B(true);
        }
    }

    private boolean Q_4569_t() {
        c_1514_x above = f_24_h.c_3005_b.Y_259_p.b_2312_j().up();
        return f_24_h.c_3005_b.Y_601_j.getBlockState(above).v_4262_N();
    }

    @Override
    public void n_1700_B() {
        this.u_2550_I = 0;
        super.n_1700_B();
    }

    private void M_182_A() {
        if (!f_24_h.c_3005_b.Y_259_p.a_2180_A()) {
            return;
        }
        if (!f_24_h.c_3005_b.Y_259_p.C_1269_X() && !this.v_4262_N.J_1907_R("Grim")) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Legit")) {
            this.t_1786_h();
        } else if (this.v_4262_N.J_1907_R("Motion")) {
            this.N_4405_n();
        } else if (this.v_4262_N.J_1907_R("Grim")) {
            this.w_1457_N();
        }
    }

    private void t_1786_h() {
        if (f_24_h.c_3005_b.Y_259_p.t_2577_l % (15 + f_24_h.c_3005_b.Y_259_p.j_276_v() % 11) != 0) {
            return;
        }
        double motionX = f_24_h.c_3005_b.Y_259_p.I_4348_c().J_1907_R;
        double motionY = f_24_h.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        double motionZ = f_24_h.c_3005_b.Y_259_p.I_4348_c().G_564_y;
        double horizontalSpeed = Math.sqrt(motionX * motionX + motionZ * motionZ);
        double multiplier = 1.01 + Math.random() * 0.015;
        double maxSpeed = 0.209;
        if (horizontalSpeed > 0.01) {
            if (horizontalSpeed * multiplier > maxSpeed) {
                double scale = maxSpeed / horizontalSpeed;
                f_24_h.c_3005_b.Y_259_p.s_956_w(motionX * scale, motionY, motionZ * scale);
            } else {
                f_24_h.c_3005_b.Y_259_p.s_956_w(motionX * multiplier, motionY, motionZ * multiplier);
            }
        }
    }

    private void N_4405_n() {
        float yaw = (float)Math.toRadians(f_24_h.c_3005_b.Y_259_p.p_178_J);
        double forward = f_24_h.c_3005_b.Y_259_p.G_564_y.moveForward;
        double strafe = f_24_h.c_3005_b.Y_259_p.G_564_y.moveStrafe;
        if (forward == 0.0 && strafe == 0.0) {
            return;
        }
        double speed = ((Float)this.w_1484_f.J_1907_R()).floatValue();
        double motionX = -Math.sin(yaw) * speed;
        double motionZ = Math.cos(yaw) * speed;
        if (forward < 0.0) {
            motionX = -motionX;
            motionZ = -motionZ;
        }
        f_24_h.c_3005_b.Y_259_p.h_1847_R(motionX, f_24_h.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, motionZ);
    }

    private void w_1457_N() {
        double waterLevel;
        if (!f_24_h.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
            return;
        }
        if (!f_24_h.c_3005_b.Y_259_p.a_2180_A()) {
            return;
        }
        c_1514_x playerPos = new c_1514_x(f_24_h.c_3005_b.Y_259_p.O_3598_v(), f_24_h.c_3005_b.Y_259_p.X_2960_b(), f_24_h.c_3005_b.Y_259_p.l_2647_k());
        try {
            waterLevel = f_24_h.c_3005_b.Y_259_p.O_508_d.getFluidState(playerPos).n_1700_B((F_3331_Z)f_24_h.c_3005_b.Y_259_p.O_508_d, playerPos);
        }
        catch (NoSuchMethodError e) {
            waterLevel = f_24_h.c_3005_b.Y_259_p.O_508_d.getBlockState(playerPos).R_4764_Y() == t_2650_P.s_956_w ? (double)playerPos.x + 1.0 : (double)playerPos.y;
        }
        double playerEyeY = f_24_h.c_3005_b.Y_259_p.X_2960_b() + (double)f_24_h.c_3005_b.Y_259_p.X_1313_W();
        if (playerEyeY >= waterLevel - 0.2 && playerEyeY <= waterLevel + 0.2) {
            f_24_h.c_3005_b.Y_259_p.h_1847_R(f_24_h.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.2, f_24_h.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            double horizontal = Math.sqrt(f_24_h.c_3005_b.Y_259_p.I_4348_c().J_1907_R * f_24_h.c_3005_b.Y_259_p.I_4348_c().J_1907_R + f_24_h.c_3005_b.Y_259_p.I_4348_c().G_564_y * f_24_h.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            u_925_K.n_1700_B(horizontal * 5.0);
        }
    }

    private void Y_601_j() {
        if (f_24_h.c_3005_b.Y_259_p.C_1269_X() || f_24_h.c_3005_b.Y_259_p.k_578_l() || f_24_h.c_3005_b.Y_259_p.q_2307_F()) {
            return;
        }
        double height = f_24_h.c_3005_b.Y_259_p.i_601_W().maxY - f_24_h.c_3005_b.Y_259_p.i_601_W().minY;
        if (height >= 1.5) {
            return;
        }
        float motion = f_24_h.c_3005_b.Y_259_p.J_1907_R(J_588_u.n_1700_B) ? 0.32f : 0.3f;
        u_925_K.n_1700_B((double)motion);
    }
}

