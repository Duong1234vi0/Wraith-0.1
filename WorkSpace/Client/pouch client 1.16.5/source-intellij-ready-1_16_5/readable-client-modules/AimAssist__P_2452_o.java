/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.StreamSupport;
import lightning.product.D_686_b;
import lightning.product.E_2264_m;
import lightning.product.E_4612_l;
import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.r_3979_X;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_1403_d;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;

public class P_2452_o
extends X_3546_T {
    private final N_4463_r v_4262_N = new N_4463_r("\u041a\u043e\u0433\u043e \u043d\u0430\u0432\u043e\u0434\u0438\u0442\u044c", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", false), new p_1977_n("\u0413\u043e\u043b\u044b\u0445", true), new p_1977_n("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new p_1977_n("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new p_1977_n("\u041c\u043e\u0431\u043e\u0432", false));
    private final I_686_h w_1484_f = new I_686_h("\u0412\u0440\u0435\u043c\u044f \u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0438", 2.7f, 0.0f, 8.0f, 0.1f);
    private final I_686_h t_148_a = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043d\u0430\u0432\u043e\u0434\u043a\u0438", 4.0f, 2.0f, 8.0f, 0.1f);
    private final I_686_h s_956_w = new I_686_h("\u0421\u0438\u043b\u0430 \u043d\u0430\u0432\u043e\u0434\u043a\u0438", 1.25f, 0.1f, 2.0f, 0.01f);
    private final I_686_h u_2550_I = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0440\u0438\u0446\u0435\u043b\u0438\u0432\u0430\u043d\u0438\u044f", 10.0f, 0.1f, 20.0f, 0.1f);
    private final I_686_h M_588_G = new I_686_h("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u0438", 8.5f, 0.0f, 10.0f, 0.1f);
    private final I_686_h P_4830_p = new I_686_h("FOV", 70.0f, 15.0f, 180.0f, 1.0f);
    private final p_1977_n h_1847_R = new p_1977_n("\u0411\u043e\u043b\u0435\u0435 \"\u0447\u0435\u043b\u043e\u0432\u0435\u0447\u0435\u0441\u043a\u0438\u0439\"", false);
    private final p_1977_n Q_4569_t = new p_1977_n("\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u0443\u044e \u043d\u0430\u0432\u043e\u0434\u043a\u0443", true);
    private final I_686_h M_182_A = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d.", 0.23f, 0.01f, 1.0f, 0.01f, this.Q_4569_t::t_148_a);
    private final p_1977_n t_1786_h = new p_1977_n("\u0428\u0443\u043c \u0432\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0439 \u043e\u0441\u0438 \u043f\u0440\u0438 \u0433\u043e\u0440\u0438\u0437\u043e\u043d\u0442\u0430\u043b\u044c\u043d\u043e\u043c \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438", false, this.Q_4569_t::t_148_a);
    private final I_686_h N_4405_n = new I_686_h("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0448\u0443\u043c\u0430", 0.13f, 0.0f, 0.5f, 0.01f, this.t_1786_h::t_148_a);
    private final p_1977_n w_1457_N = new p_1977_n("\u0413\u0435\u043d\u0435\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \"\u0437\u0430\u0442\u0443\u043f\"", false);
    private final p_1977_n Y_601_j = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043e\u0440\u0443\u0436\u0438\u0438", true);
    private final p_1977_n Y_259_p = new p_1977_n("\u041d\u0435 \u0434\u0432\u0438\u0433\u0430\u0442\u044c \u043f\u0440\u0438 \u043f\u0440\u043e\u0442\u0438\u0432\u043d\u0438\u043a\u0435", true);
    private final p_1977_n Q_2552_b = new p_1977_n("\u041e\u0442 \u0432\u0432\u043e\u0434\u0430", false);
    private final I_686_h C_2741_M = new I_686_h("\u041f\u0440\u0435\u0434\u0443\u0433\u0430\u0434\u044b\u0432\u0430\u043d\u0438\u0435 \u043f\u043e\u0437\u0438\u0446\u0438\u0438", 0.0f, 0.0f, 1.5f, 0.05f);
    private final I_686_h k_2293_S = new I_686_h("\u041f\u0440\u0435\u0434\u0438\u043a\u0442 \u043e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u044f", 0.0f, 0.0f, 1.5f, 0.05f);
    private final p_1977_n q_2307_F = new p_1977_n("\u0421\u0438\u0441\u0442\u0435\u043c\u0430 \u043c\u0443\u043b\u044c\u0442\u0438\u043f\u043e\u0438\u043d\u0442", false);
    private final p_1977_n Z_875_P = new p_1977_n("\u0414\u043e\u0432\u043e\u0434\u0438\u0442\u044c \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438 \u043c\u044b\u0448\u0438", true);
    private final p_1977_n t_4043_B = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0432\u0438\u0434\u0438\u043c\u044b\u0445", true);
    private final p_1977_n x_607_J = new p_1977_n("\u0412\u044b\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u0441 AttackAura", true);
    private final Random e_4240_b = new Random();
    private r_4811_B n_3318_d;
    private long d_2427_y;
    private int z_1737_N;

    public P_2452_o() {
        super("AimAssist", y_2603_k.n_1700_B);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Z_875_P, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.t_4043_B, this.x_607_J);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        String rotationMode;
        boolean auraFov90;
        boolean allowWithAuraFov90Raycast;
        r_3979_X aura;
        if (P_2452_o.c_3005_b.Y_259_p == null || P_2452_o.c_3005_b.Y_601_j == null) {
            return;
        }
        if (P_2452_o.c_3005_b.Y_1740_V != null) {
            return;
        }
        if (!P_2452_o.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
            return;
        }
        if (this.x_607_J.t_148_a().booleanValue() && (aura = o_148_s.Y_601_j().J_1907_R().J_1907_R()) != null && aura.w_1484_f() && aura.v_4262_N != null && !(allowWithAuraFov90Raycast = (auraFov90 = (rotationMode = aura.Q_4569_t() != null ? (String)aura.Q_4569_t().J_1907_R() : "") != null && rotationMode.toLowerCase().contains("fov90")))) {
            return;
        }
        if (this.Y_601_j.t_148_a().booleanValue() && !(P_2452_o.c_3005_b.Y_259_p.A_2714_y().J_1907_R() instanceof u_1403_d)) {
            return;
        }
        if (this.Q_2552_b.t_148_a().booleanValue() && !P_2452_o.c_3005_b.P_4830_p.D_60_a.G_564_y()) {
            return;
        }
        r_4811_B target = this.h_1847_R();
        if (target == null) {
            return;
        }
        if (this.w_1457_N.t_148_a().booleanValue()) {
            if (this.z_1737_N > 0) {
                --this.z_1737_N;
                return;
            }
            if (this.e_4240_b.nextFloat() < 0.045f) {
                this.z_1737_N = 1 + this.e_4240_b.nextInt(2);
                return;
            }
        }
        float[] needed = this.J_1907_R(target);
        float yawDiff = u_530_F.v_4262_N(needed[0] - P_2452_o.c_3005_b.Y_259_p.p_178_J);
        float pitchDiff = u_530_F.v_4262_N(needed[1] - P_2452_o.c_3005_b.Y_259_p.f_4016_n);
        if (Math.abs(yawDiff) > ((Float)this.P_4830_p.J_1907_R()).floatValue() * 0.5f) {
            return;
        }
        if (this.Y_259_p.t_148_a().booleanValue() && !this.Z_875_P.t_148_a().booleanValue() && Math.abs(yawDiff) < 0.35f && Math.abs(pitchDiff) < 0.3f) {
            return;
        }
        float smoothLinear = u_530_F.n_1700_B((10.0f - ((Float)this.M_588_G.J_1907_R()).floatValue()) / 10.0f, 0.03f, 1.0f);
        float assistStrength = ((Float)this.s_956_w.J_1907_R()).floatValue();
        float yawCap = ((Float)this.u_2550_I.J_1907_R()).floatValue();
        float yawStep = u_530_F.n_1700_B(yawDiff * assistStrength * smoothLinear, -yawCap, yawCap);
        if (this.h_1847_R.t_148_a().booleanValue()) {
            yawStep += (this.e_4240_b.nextFloat() - 0.5f) * 0.22f;
        }
        float pitchStep = 0.0f;
        if (this.Q_4569_t.t_148_a().booleanValue()) {
            float pitchCap = ((Float)this.M_182_A.J_1907_R()).floatValue();
            pitchStep = u_530_F.n_1700_B(pitchDiff * assistStrength * smoothLinear, -pitchCap, pitchCap);
            if (this.t_1786_h.t_148_a().booleanValue() && Math.abs(yawStep) > 0.01f) {
                pitchStep += (this.e_4240_b.nextFloat() - 0.5f) * ((Float)this.N_4405_n.J_1907_R()).floatValue();
            }
            if (this.h_1847_R.t_148_a().booleanValue()) {
                pitchStep += (this.e_4240_b.nextFloat() - 0.5f) * 0.08f;
            }
        }
        float targetYaw = P_2452_o.c_3005_b.Y_259_p.p_178_J + yawStep;
        float targetPitch = u_530_F.n_1700_B(P_2452_o.c_3005_b.Y_259_p.f_4016_n + pitchStep, -89.0f, 89.0f);
        float patchedYaw = r_4790_y.n_1700_B(P_2452_o.c_3005_b.Y_259_p.p_178_J, targetYaw);
        float patchedPitch = r_4790_y.n_1700_B(P_2452_o.c_3005_b.Y_259_p.f_4016_n, targetPitch);
        P_2452_o.c_3005_b.Y_259_p.p_178_J = patchedYaw;
        P_2452_o.c_3005_b.Y_259_p.f_3449_S = patchedYaw;
        P_2452_o.c_3005_b.Y_259_p.C_1162_e = patchedYaw;
        P_2452_o.c_3005_b.Y_259_p.f_4016_n = patchedPitch;
    }

    private r_4811_B h_1847_R() {
        r_4811_B found;
        long now = System.currentTimeMillis();
        if (this.n_3318_d != null && now <= this.d_2427_y && this.n_1700_B((N_4263_v)this.n_3318_d)) {
            return this.n_3318_d;
        }
        this.n_3318_d = found = this.Q_4569_t();
        this.d_2427_y = found != null ? now + (long)(((Float)this.w_1484_f.J_1907_R()).floatValue() * 1000.0f) : 0L;
        return found;
    }

    private r_4811_B Q_4569_t() {
        return StreamSupport.stream(P_2452_o.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).filter(this::n_1700_B).map(ent -> (r_4811_B)ent).min(Comparator.comparingDouble(this::n_1700_B).thenComparingDouble(ent -> P_2452_o.c_3005_b.Y_259_p.G_564_y((N_4263_v)ent))).orElse(null);
    }

    private boolean n_1700_B(N_4263_v entity) {
        if (!(entity instanceof r_4811_B)) {
            return false;
        }
        r_4811_B living = (r_4811_B)entity;
        if (entity == P_2452_o.c_3005_b.Y_259_p || !entity.H_3699_F() || entity instanceof D_686_b) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            E_2264_m antiBot = (E_2264_m)o_148_s.Y_601_j().J_1907_R().n_1700_B(E_2264_m.class);
            if (antiBot != null && antiBot.w_1484_f() && player.g_4106_L) {
                return false;
            }
        }
        if (this.t_4043_B.t_148_a().booleanValue() && !P_2452_o.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (P_2452_o.c_3005_b.Y_259_p.R_4764_Y(entity) > ((Float)this.t_148_a.J_1907_R()).floatValue()) {
            return false;
        }
        if (this.n_1700_B(living) > (double)(((Float)this.P_4830_p.J_1907_R()).floatValue() * 0.5f)) {
            return false;
        }
        return E_4612_l.n_1700_B(living, this.v_4262_N, true) || E_4612_l.n_1700_B(living, this.v_4262_N) || E_4612_l.J_1907_R(living, this.v_4262_N) || E_4612_l.R_4764_Y(living, this.v_4262_N);
    }

    private double n_1700_B(r_4811_B target) {
        float[] needed = this.J_1907_R(target);
        return Math.abs(u_530_F.v_4262_N(needed[0] - P_2452_o.c_3005_b.Y_259_p.p_178_J));
    }

    private float[] J_1907_R(r_4811_B target) {
        e_2866_D predict = this.R_4764_Y(target);
        double dx = predict.J_1907_R - P_2452_o.c_3005_b.Y_259_p.O_3598_v();
        double dz = predict.G_564_y - P_2452_o.c_3005_b.Y_259_p.l_2647_k();
        double targetBodyY = target.X_2960_b() + (double)target.v_165_F() * 0.62;
        if (target.q_2307_F()) {
            targetBodyY -= 0.08;
        }
        double dy = targetBodyY - P_2452_o.c_3005_b.Y_259_p.X_2048_Y();
        double dist = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, dist)));
        if (this.q_2307_F.t_148_a().booleanValue()) {
            double altY = target.X_2960_b() + (double)target.v_165_F() * 0.45;
            double altDy = altY - P_2452_o.c_3005_b.Y_259_p.X_2048_Y();
            float altPitch = (float)(-Math.toDegrees(Math.atan2(altDy, dist)));
            pitch = (pitch + altPitch) * 0.5f;
        }
        return new float[]{yaw, pitch};
    }

    private e_2866_D R_4764_Y(r_4811_B target) {
        double pred = ((Float)this.C_2741_M.J_1907_R()).floatValue();
        double kb = ((Float)this.k_2293_S.J_1907_R()).floatValue();
        double motionX = target.I_4348_c().J_1907_R * (0.5 + pred);
        double motionZ = target.I_4348_c().G_564_y * (0.5 + pred);
        if (kb > 0.0) {
            motionX += (target.O_3598_v() - target.r_715_M) * kb;
            motionZ += (target.l_2647_k() - target.i_1637_u) * kb;
        }
        return new e_2866_D(target.O_3598_v() + motionX, target.X_2960_b(), target.l_2647_k() + motionZ);
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.n_3318_d = null;
        this.d_2427_y = 0L;
        this.z_1737_N = 0;
    }
}

