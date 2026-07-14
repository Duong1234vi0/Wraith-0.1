/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.m_1679_b;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.r_4811_B;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;

public class P_1216_m
extends X_3546_T {
    private static final double w_1484_f = 1.0E-4;
    private final q_366_O t_148_a = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Collision", "Collision", "Default", "Legit");
    private final p_1977_n s_956_w = new p_1977_n("\u0410\u0432\u0442\u043e \u041f\u0440\u044b\u0436\u043e\u043a", true, () -> this.t_148_a.J_1907_R("Default"));
    private final I_686_h u_2550_I = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 2.4f, 0.1f, 6.0f, 0.1f, () -> this.t_148_a.J_1907_R("Default"));
    private final I_686_h M_588_G = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.23f, 0.1f, 1.0f, 0.01f, () -> this.t_148_a.J_1907_R("Default"));
    private final p_1977_n P_4830_p = new p_1977_n("\u0411\u0443\u0441\u0442 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435", false, () -> this.t_148_a.J_1907_R("Default"));
    private final I_686_h h_1847_R = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", 0.5f, 0.1f, 1.5f, 0.1f, () -> this.t_148_a.J_1907_R("Default") && this.P_4830_p.t_148_a() != false);
    private final I_686_h Q_4569_t = new I_686_h("\u0421\u0438\u043b\u0430", 0.03f, 0.01f, 0.1f, 0.01f, () -> this.t_148_a.J_1907_R("Holyworld"));
    private final I_686_h M_182_A = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", 0.4f, 0.1f, 1.0f, 0.1f, () -> this.t_148_a.J_1907_R("Holyworld"));
    private final I_686_h t_1786_h = new I_686_h("\u041f\u0440\u0435\u0434\u0438\u043a\u0442", 5.0f, 1.0f, 10.0f, 1.0f, () -> this.t_148_a.J_1907_R("Holyworld"));
    private final I_686_h N_4405_n = new I_686_h("\u0421\u0438\u043b\u0430", 0.03f, 0.01f, 0.1f, 0.01f, () -> this.t_148_a.J_1907_R("Holyworld New"));
    private final I_686_h w_1457_N = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u0443\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u044f", 0.4f, 0.1f, 1.0f, 0.1f, () -> this.t_148_a.J_1907_R("Holyworld New"));
    private final I_686_h Y_601_j = new I_686_h("\u041f\u0440\u0435\u0434\u0438\u043a\u0442", 5.0f, 1.0f, 10.0f, 1.0f, () -> this.t_148_a.J_1907_R("Holyworld New"));
    private final p_1977_n Y_259_p = new p_1977_n("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u043f\u0440\u044b\u0433\u0430\u0442\u044c", true, () -> this.t_148_a.J_1907_R("Legit"));
    private final p_1977_n Q_2552_b = new p_1977_n("\u0412\u0436\u0438\u043c\u0430\u0442\u044c\u0441\u044f \u0432 \u0446\u0435\u043b\u044c", true, () -> this.t_148_a.J_1907_R("Legit"));
    private final I_686_h C_2741_M = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0441\u0442\u0440\u0435\u0439\u0444\u0430", 2.5f, 0.5f, 5.0f, 0.1f, () -> this.t_148_a.J_1907_R("Legit"));
    public static boolean v_4262_N;
    private boolean k_2293_S = true;

    public P_1216_m() {
        super("TargetStrafe", y_2603_k.n_1700_B);
        this.n_1700_B(this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M);
    }

    private boolean h_1847_R() {
        for (int j = (int)P_1216_m.c_3005_b.Y_259_p.X_2960_b(); j > 0; --j) {
            c_1514_x pos = new c_1514_x(P_1216_m.c_3005_b.Y_259_p.O_3598_v(), (double)j, P_1216_m.c_3005_b.Y_259_p.l_2647_k());
            if (P_1216_m.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() instanceof m_1679_b) continue;
            return false;
        }
        return true;
    }

    public boolean n_1700_B(double x, double z) {
        if (P_1216_m.c_3005_b.Y_259_p.D_60_a || P_1216_m.c_3005_b.P_4830_p.r_715_M.G_564_y() || P_1216_m.c_3005_b.P_4830_p.i_1637_u.G_564_y()) {
            return true;
        }
        for (int j = (int)(P_1216_m.c_3005_b.Y_259_p.X_2960_b() + 4.0); j >= 0; --j) {
            c_1514_x blockPos = new c_1514_x(x, (double)j, z);
            if (P_1216_m.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R().equals(a_3742_W.H_2857_Y) || P_1216_m.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R().equals(a_3742_W.x_612_B)) {
                return true;
            }
            if (P_1216_m.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R() == a_3742_W.y_1700_S) {
                return true;
            }
            if (this.h_1847_R()) {
                return true;
            }
            if (P_1216_m.c_3005_b.Y_601_j.u_1723_Y(blockPos)) continue;
            return false;
        }
        return true;
    }

    private float n_1700_B(float targetX, float targetZ) {
        double dx = (double)targetX - P_1216_m.c_3005_b.Y_259_p.O_3598_v();
        double dz = (double)targetZ - P_1216_m.c_3005_b.Y_259_p.l_2647_k();
        return (float)(Math.atan2(dz, dx) * 180.0 / Math.PI - 90.0);
    }

    private float n_1700_B(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (this.t_148_a.J_1907_R("Collision")) {
            this.t_1786_h();
        } else if (this.t_148_a.J_1907_R("Default")) {
            this.N_4405_n();
        } else if (this.t_148_a.J_1907_R("Holyworld New")) {
            this.w_1457_N();
        } else if (this.t_148_a.J_1907_R("Legit")) {
            this.Q_4569_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J e) {
        boolean pressing;
        r_4811_B target;
        if (!this.t_148_a.J_1907_R("Legit")) {
            return;
        }
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.H_3699_F()) {
            return;
        }
        double distance = P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > 6.0) {
            return;
        }
        double dx = target.O_3598_v() - P_1216_m.c_3005_b.Y_259_p.O_3598_v();
        double dz = target.l_2647_k() - P_1216_m.c_3005_b.Y_259_p.l_2647_k();
        float toTargetYaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        boolean bl = pressing = e.n_1700_B() != 0.0f || e.J_1907_R() != 0.0f;
        if (this.Q_2552_b.t_148_a().booleanValue() && distance > (double)((Float)this.C_2741_M.J_1907_R()).floatValue()) {
            e.n_1700_B(1.0f);
            e.J_1907_R(0.0f);
            e.n_1700_B(true);
            u_925_K.n_1700_B(e, toTargetYaw);
        } else if (pressing) {
            float perpOffset = this.k_2293_S ? 90.0f : -90.0f;
            float strafeYaw = toTargetYaw + perpOffset;
            u_925_K.n_1700_B(e, strafeYaw);
        }
    }

    private void Q_4569_t() {
        double nextZ;
        double nextX;
        r_4811_B target;
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.H_3699_F()) {
            v_4262_N = false;
            return;
        }
        double distance = P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > 6.0) {
            v_4262_N = false;
            return;
        }
        v_4262_N = true;
        if (P_1216_m.c_3005_b.Y_259_p.D_60_a) {
            boolean bl = this.k_2293_S = !this.k_2293_S;
        }
        if (this.n_1700_B(nextX = P_1216_m.c_3005_b.Y_259_p.O_3598_v() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R * 3.0, nextZ = P_1216_m.c_3005_b.Y_259_p.l_2647_k() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y * 3.0)) {
            boolean bl = this.k_2293_S = !this.k_2293_S;
        }
        if (this.Y_259_p.t_148_a().booleanValue() && distance <= (double)((Float)this.C_2741_M.J_1907_R()).floatValue() + 1.0) {
            if (P_1216_m.c_3005_b.Y_259_p.M_1641_O()) {
                P_1216_m.c_3005_b.P_4830_p.T_69_K.n_1700_B(true);
            } else if (!P_1216_m.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
                P_1216_m.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            }
        }
    }

    private void M_182_A() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null) {
            return;
        }
        e_2866_D targetPos = target.s_4990_V();
        if (((Float)this.t_1786_h.J_1907_R()).floatValue() > 0.0f) {
            e_2866_D predictedMotion = target.I_4348_c();
            targetPos = targetPos.P_1922_E(predictedMotion.n_1700_B((double)((Float)this.t_1786_h.J_1907_R()).floatValue() * 0.05));
        }
        e_2866_D playerPos = P_1216_m.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        double distance = P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)(((Float)this.M_182_A.J_1907_R()).floatValue() + 1.0f)) {
            return;
        }
        e_2866_D motionPos = new e_2866_D(P_1216_m.c_3005_b.Y_259_p.O_3598_v() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R, P_1216_m.c_3005_b.Y_259_p.X_2960_b() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, P_1216_m.c_3005_b.Y_259_p.l_2647_k() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = P_1216_m.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float strength = ((Float)this.Q_4569_t.J_1907_R()).floatValue();
        if (distance < (double)((Float)this.M_182_A.J_1907_R()).floatValue()) {
            float accelerationFactor = 1.0f + (float)((double)((Float)this.M_182_A.J_1907_R()).floatValue() - distance) / ((Float)this.M_182_A.J_1907_R()).floatValue();
            strength *= accelerationFactor;
        }
        if (target.D_60_a) {
            strength *= 1.2f;
        }
        float ground = strength;
        float falling = strength;
        float jump = strength;
        double speed = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (P_1216_m.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        P_1216_m.c_3005_b.Y_259_p.h_1847_R(P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }

    private void t_1786_h() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target) > 2.75f) {
            return;
        }
        e_2866_D targetMotion = target.I_4348_c();
        double targetHorizontalSpeedSq = targetMotion.J_1907_R * targetMotion.J_1907_R + targetMotion.G_564_y * targetMotion.G_564_y;
        if (targetHorizontalSpeedSq <= 1.0E-4) {
            return;
        }
        e_2866_D playerPos = P_1216_m.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D targetPos = target.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        e_2866_D motionPos = new e_2866_D(P_1216_m.c_3005_b.Y_259_p.O_3598_v() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R, P_1216_m.c_3005_b.Y_259_p.X_2960_b() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, P_1216_m.c_3005_b.Y_259_p.l_2647_k() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = P_1216_m.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float ground = 0.05f;
        float falling = 0.05f;
        float jump = 0.05f;
        float gradus = (float)System.currentTimeMillis() / 100.0f;
        float centrifugal = 0.0f;
        float deviationX = (float)Math.cos(Math.toDegrees(gradus)) * centrifugal;
        float deviationZ = (float)Math.sin(Math.toDegrees(gradus)) * centrifugal;
        direction = direction.J_1907_R(deviationX, 0.0, deviationZ);
        double speed = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (P_1216_m.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        P_1216_m.c_3005_b.Y_259_p.h_1847_R(P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }

    private void N_4405_n() {
        float targetZ;
        r_4811_B target;
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null || !target.H_3699_F()) {
            v_4262_N = false;
            return;
        }
        if (this.s_956_w.t_148_a().booleanValue() && P_1216_m.c_3005_b.Y_259_p.M_1641_O()) {
            P_1216_m.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
            P_1216_m.c_3005_b.Y_259_p.e_837_t();
            return;
        }
        float maxDistance = 6.0f;
        double distance = P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)maxDistance) {
            v_4262_N = false;
            return;
        }
        P_1216_m.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        float speed = ((Float)this.M_588_G.J_1907_R()).floatValue();
        if (this.P_4830_p.t_148_a().booleanValue() && P_1216_m.c_3005_b.Y_259_p.H_3699_F > 0 && P_1216_m.c_3005_b.Y_259_p.H_3699_F()) {
            speed += ((Float)this.h_1847_R.J_1907_R()).floatValue();
        }
        float distRatio = this.n_1700_B((float)distance / maxDistance, 0.01f, 1.0f);
        double angle = Math.atan2(P_1216_m.c_3005_b.Y_259_p.l_2647_k() - target.l_2647_k(), P_1216_m.c_3005_b.Y_259_p.O_3598_v() - target.O_3598_v());
        float angleStep = this.n_1700_B(speed / distRatio, 0.01f, 1.0f);
        float targetX = (float)(target.O_3598_v() + (double)((Float)this.u_2550_I.J_1907_R()).floatValue() * Math.cos(angle += this.k_2293_S ? (double)angleStep : (double)(-angleStep)));
        if (this.n_1700_B((double)targetX, (double)(targetZ = (float)(target.l_2647_k() + (double)((Float)this.u_2550_I.J_1907_R()).floatValue() * Math.sin(angle))))) {
            this.k_2293_S = !this.k_2293_S;
            targetX = (float)(target.O_3598_v() + (double)((Float)this.u_2550_I.J_1907_R()).floatValue() * Math.cos(angle += (double)(2.0f * (this.k_2293_S ? angleStep : -angleStep))));
            targetZ = (float)(target.l_2647_k() + (double)((Float)this.u_2550_I.J_1907_R()).floatValue() * Math.sin(angle));
        }
        v_4262_N = true;
        float yaw = this.n_1700_B(targetX, targetZ);
        double motionX = (double)speed * -Math.sin(Math.toRadians(yaw));
        double motionZ = (double)speed * Math.cos(Math.toRadians(yaw));
        if (Double.isNaN(motionX) || Double.isNaN(motionZ)) {
            return;
        }
        P_1216_m.c_3005_b.Y_259_p.h_1847_R(motionX, P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, motionZ);
    }

    private void w_1457_N() {
        r_4811_B target;
        if (!u_925_K.n_1700_B()) {
            return;
        }
        r_3979_X attackAura = o_148_s.Y_601_j().J_1907_R().n_1700_B;
        r_4811_B r_4811_B2 = target = attackAura != null && attackAura.w_1484_f() ? attackAura.h_1847_R() : null;
        if (target == null) {
            return;
        }
        e_2866_D targetPos = target.s_4990_V();
        if (((Float)this.Y_601_j.J_1907_R()).floatValue() > 0.0f) {
            e_2866_D predictedMotion = target.I_4348_c();
            targetPos = targetPos.P_1922_E(predictedMotion.n_1700_B((double)((Float)this.Y_601_j.J_1907_R()).floatValue() * 0.05));
        }
        e_2866_D playerPos = P_1216_m.c_3005_b.Y_259_p.s_4990_V();
        e_2866_D direction = targetPos.G_564_y(playerPos).G_564_y();
        double distance = P_1216_m.c_3005_b.Y_259_p.R_4764_Y(target);
        if (distance > (double)(((Float)this.w_1457_N.J_1907_R()).floatValue() + 1.0f)) {
            return;
        }
        e_2866_D motionPos = new e_2866_D(P_1216_m.c_3005_b.Y_259_p.O_3598_v() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R, P_1216_m.c_3005_b.Y_259_p.X_2960_b() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y, P_1216_m.c_3005_b.Y_259_p.l_2647_k() + P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        c_1514_x motionBlockPos = new c_1514_x(motionPos);
        float p = P_1216_m.c_3005_b.Y_601_j.getBlockState(motionBlockPos).J_1907_R().h_1847_R();
        float f = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.91f;
        float f2 = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? p : 0.99f;
        double motionY = P_1216_m.c_3005_b.Y_259_p.I_4348_c().R_4764_Y;
        float strength = ((Float)this.N_4405_n.J_1907_R()).floatValue() * 0.3f;
        if (distance < (double)((Float)this.w_1457_N.J_1907_R()).floatValue()) {
            float accelerationFactor = 1.0f + (float)((double)((Float)this.w_1457_N.J_1907_R()).floatValue() - distance) / ((Float)this.w_1457_N.J_1907_R()).floatValue() * 0.5f;
            strength *= accelerationFactor;
        }
        if (target.D_60_a) {
            strength *= 0.9f;
        }
        float ground = strength;
        float falling = strength * 0.8f;
        float jump = strength * 0.9f;
        double speed = P_1216_m.c_3005_b.Y_259_p.M_1641_O() ? (double)ground : (P_1216_m.c_3005_b.Y_259_p.U_1241_n > 0.0f ? (double)falling : (double)jump);
        double newX = direction.J_1907_R * speed * (double)f2 / (double)f;
        double newZ = direction.G_564_y * speed * (double)f2 / (double)f;
        P_1216_m.c_3005_b.Y_259_p.h_1847_R(P_1216_m.c_3005_b.Y_259_p.I_4348_c().J_1907_R + newX, motionY, P_1216_m.c_3005_b.Y_259_p.I_4348_c().G_564_y + newZ);
    }
}

