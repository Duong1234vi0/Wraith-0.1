/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.E_4612_l;
import lightning.product.E_4925_L;
import lightning.product.F_1446_q;
import lightning.product.F_489_x;
import lightning.product.I_3875_v;
import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.O_1678_k;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1630_j;
import lightning.product.a_1344_X;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.d_2169_p;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_384_L;
import lightning.product.i_2572_h;
import lightning.product.i_4434_b;
import lightning.product.o_148_s;
import lightning.product.p_1183_T;
import lightning.product.p_1977_n;
import lightning.product.p_863_D;
import lightning.product.q_1613_l;
import lightning.product.q_3206_W;
import lightning.product.q_4592_V;
import lightning.product.r_4790_y;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class Q_2558_o
extends X_3546_T {
    private final N_4463_r v_4262_N = new N_4463_r("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", new p_1977_n("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", false), new p_1977_n("\u041b\u0443\u043a", false), new p_1977_n("\u0410\u0440\u0431\u0430\u043b\u0435\u0442", false));
    private final I_686_h w_1484_f = new I_686_h("\u0421\u0438\u043b\u0430 \u0432\u044b\u0441\u0442\u0440\u0435\u043b\u0430", 3.0f, 1.0f, 20.0f, 0.5f, () -> this.v_4262_N.J_1907_R("\u041b\u0443\u043a"));
    private final p_1977_n t_148_a = new p_1977_n("\u0417\u0432\u0443\u043a \u043f\u0440\u0438 \u043f\u043e\u043f\u0430\u0434\u0430\u043d\u0438\u0438", false);
    private final q_3206_W s_956_w = new q_3206_W("\u041a\u043d\u043e\u043f\u043a\u0430 \u0431\u0440\u043e\u0441\u043a\u0430 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", () -> this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446"));
    private final I_686_h u_2550_I = new I_686_h("\u0412\u0440\u0435\u043c\u044f \u0437\u0430\u0440\u044f\u0434\u043a\u0438 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", 10.0f, 10.0f, 20.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446"));
    private final p_1977_n M_588_G = new p_1977_n("\u0410\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442", false);
    private final I_686_h P_4830_p = new I_686_h("\u0414\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0430\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442\u0430", 50.0f, 10.0f, 100.0f, 1.0f, () -> this.M_588_G.t_148_a());
    private final I_686_h h_1847_R = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 FOV", 60.0f, 10.0f, 180.0f, 1.0f, () -> this.M_588_G.t_148_a());
    private final p_1977_n Q_4569_t = new p_1977_n("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u044b\u0432\u0430\u0442\u044c FOV", false, () -> this.M_588_G.t_148_a());
    private final p_1977_n M_182_A = new p_1977_n("\u041f\u0440\u0435\u0434\u0438\u043a\u0442 \u0446\u0435\u043b\u0438", false, () -> this.M_588_G.t_148_a());
    private final p_1977_n t_1786_h = new p_1977_n("\u0422\u0430\u0440\u0433\u0435\u0442\u0438\u0442\u044c \u0431\u043b\u0438\u0436\u0430\u0439\u0448\u0443\u044e \u0446\u0435\u043b\u044c", false, () -> this.M_588_G.t_148_a());
    private final p_1977_n N_4405_n = new p_1977_n("\u041d\u0435 \u0441\u0442\u0440\u0435\u043b\u044f\u0442\u044c \u0435\u0441\u043b\u0438 \u0442\u0430\u0440\u0433\u0435\u0442 \u0437\u0430 \u0441\u0442\u0435\u043d\u043e\u0439", false, () -> this.M_588_G.t_148_a());
    private final N_4463_r w_1457_N = new N_4463_r("\u0426\u0435\u043b\u0438 \u0430\u0438\u043c-\u0430\u0441\u0441\u0438\u0441\u0442\u0430", () -> this.M_588_G.t_148_a(), new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", false), new p_1977_n("\u041c\u043e\u0431\u043e\u0432", false), new p_1977_n("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new p_1977_n("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false));
    private final Set<Integer> Y_601_j = new HashSet<Integer>();
    private final Set<Integer> Y_259_p = new HashSet<Integer>();
    private final V_4557_X Q_2552_b = new V_4557_X();
    private boolean C_2741_M;
    private int k_2293_S = -1;
    private int q_2307_F = -1;
    private int Z_875_P = 0;
    private int t_4043_B = 0;

    public Q_2558_o() {
        super("ItemRelease", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && ((Integer)this.s_956_w.J_1907_R()).intValue() == e.n_1700_B() && e.J_1907_R() && !Q_2558_o.c_3005_b.Y_259_p.p_1458_L().n_1700_B(q_4592_V.P_2605_j)) {
            int slot = u_1934_K.n_1700_B(q_4592_V.P_2605_j);
            if (slot != -1 && this.t_4043_B == 0) {
                this.C_2741_M = true;
                this.k_2293_S = slot;
                this.q_2307_F = Q_2558_o.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            }
        } else if (this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && ((Integer)this.s_956_w.J_1907_R()).intValue() == e.n_1700_B() && !e.J_1907_R() && this.t_4043_B == 2) {
            Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
            this.t_4043_B = 3;
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        r_4811_B target;
        if (this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && this.C_2741_M) {
            switch (this.t_4043_B) {
                case 0: {
                    if (this.k_2293_S < 9) {
                        Q_2558_o.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.k_2293_S));
                        Q_2558_o.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    } else {
                        Q_2558_o.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(this.k_2293_S));
                    }
                    this.t_4043_B = 1;
                    break;
                }
                case 1: {
                    if (this.k_2293_S < 9) {
                        Q_2558_o.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.k_2293_S;
                        Q_2558_o.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    }
                    Q_2558_o.c_3005_b.w_1457_N.processRightClick(Q_2558_o.c_3005_b.Y_259_p, Q_2558_o.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                    this.Z_875_P = 0;
                    this.t_4043_B = 2;
                    break;
                }
                case 2: {
                    ++this.Z_875_P;
                    if (this.Z_875_P < ((Float)this.u_2550_I.J_1907_R()).intValue()) break;
                    if (Q_2558_o.c_3005_b.Y_259_p.Y_601_j()) {
                        Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
                    }
                    this.t_4043_B = 3;
                    break;
                }
                case 3: {
                    if (this.k_2293_S < 9) {
                        Q_2558_o.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.q_2307_F));
                        Q_2558_o.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.q_2307_F;
                        Q_2558_o.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    } else {
                        Q_2558_o.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(this.k_2293_S));
                    }
                    this.N_4405_n();
                }
            }
        }
        if (this.v_4262_N.J_1907_R("\u041b\u0443\u043a").booleanValue() && Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof O_1678_k && Q_2558_o.c_3005_b.Y_259_p.Y_601_j() && (float)Q_2558_o.c_3005_b.Y_259_p.g_1031_K() >= ((Float)this.w_1484_f.J_1907_R()).floatValue()) {
            if (this.M_182_A()) {
                return;
            }
            Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
        }
        if (this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue() && this.t_4043_B == 0 && Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof I_3875_v && Q_2558_o.c_3005_b.Y_259_p.Y_601_j() && Q_2558_o.c_3005_b.Y_259_p.g_1031_K() >= 10) {
            if (this.M_182_A()) {
                return;
            }
            Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
        }
        if (this.v_4262_N.J_1907_R("\u0410\u0440\u0431\u0430\u043b\u0435\u0442").booleanValue() && Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j && Q_2558_o.c_3005_b.Y_259_p.Y_601_j() && Q_2558_o.c_3005_b.Y_259_p.g_1031_K() >= Z_1630_j.v_4262_N(Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y())) {
            if (this.M_182_A()) {
                return;
            }
            Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
        }
        if (this.t_148_a.t_148_a().booleanValue() && Q_2558_o.c_3005_b.Y_601_j != null) {
            this.h_1847_R();
        }
        if (this.M_588_G.t_148_a().booleanValue() && Q_2558_o.c_3005_b.Y_259_p != null && Q_2558_o.c_3005_b.Y_601_j != null && (Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof O_1678_k || Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof I_3875_v || Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j) && Q_2558_o.c_3005_b.Y_259_p.Y_601_j() && (target = this.t_1786_h()) != null) {
            this.R_4764_Y(target);
        }
    }

    private void h_1847_R() {
        if (Q_2558_o.c_3005_b.Y_259_p == null || Q_2558_o.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v entity : Q_2558_o.c_3005_b.Y_601_j.J_1907_R()) {
            E_4925_L trident;
            N_4263_v shooter;
            if (entity instanceof h_384_L) {
                h_384_L arrow = (h_384_L)entity;
                shooter = arrow.Y_601_j();
                if (shooter != Q_2558_o.c_3005_b.Y_259_p || this.Y_601_j.contains(arrow.j_276_v())) continue;
                this.Y_601_j.add(arrow.j_276_v());
                continue;
            }
            if (!(entity instanceof E_4925_L) || (shooter = (trident = (E_4925_L)entity).Y_601_j()) != Q_2558_o.c_3005_b.Y_259_p || this.Y_601_j.contains(trident.j_276_v())) continue;
            this.Y_601_j.add(trident.j_276_v());
        }
        this.Y_601_j.removeIf(projectileId -> {
            N_4263_v projectile = Q_2558_o.c_3005_b.Y_601_j.J_1907_R((int)projectileId);
            if (projectile == null || !projectile.H_3699_F()) {
                return true;
            }
            for (N_4263_v entity : Q_2558_o.c_3005_b.Y_601_j.J_1907_R()) {
                double speed;
                if (!(entity instanceof r_4811_B)) continue;
                r_4811_B living = (r_4811_B)entity;
                if (entity == Q_2558_o.c_3005_b.Y_259_p || this.Y_259_p.contains(projectileId) || !projectile.i_601_W().intersects(entity.i_601_W()) || !((speed = projectile.I_4348_c().v_4262_N()) < 0.01) && living.H_3699_F <= 0) continue;
                this.Q_4569_t();
                this.Y_259_p.add((Integer)projectileId);
                return true;
            }
            double speed = projectile.I_4348_c().v_4262_N();
            return speed < 0.001;
        });
        this.Y_259_p.removeIf(id -> Q_2558_o.c_3005_b.Y_601_j.J_1907_R((int)id) == null);
    }

    private void Q_4569_t() {
        if (this.Q_2552_b.J_1907_R(100L)) {
            p_863_D.n_1700_B("toggle");
            this.Q_2552_b.n_1700_B();
        }
    }

    private boolean M_182_A() {
        if (!this.M_588_G.t_148_a().booleanValue() || !this.N_4405_n.t_148_a().booleanValue() || Q_2558_o.c_3005_b.Y_259_p == null) {
            return false;
        }
        r_4811_B target = this.t_1786_h();
        return target != null && !Q_2558_o.c_3005_b.Y_259_p.c_3005_b(target);
    }

    private r_4811_B t_1786_h() {
        if (Q_2558_o.c_3005_b.Y_259_p == null || Q_2558_o.c_3005_b.Y_601_j == null) {
            return null;
        }
        try {
            List entities = StreamSupport.stream(Q_2558_o.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).collect(Collectors.toList());
            Comparator<r_4811_B> comparator = this.t_1786_h.t_148_a() != false ? Comparator.comparingDouble(entity -> entity.R_4764_Y(Q_2558_o.c_3005_b.Y_259_p)) : Comparator.comparingDouble(this::n_1700_B);
            return entities.stream().filter(e -> e instanceof r_4811_B).map(e -> (r_4811_B)e).filter(this::J_1907_R).sorted(comparator).findFirst().orElse(null);
        }
        catch (Exception e2) {
            return null;
        }
    }

    private double n_1700_B(r_4811_B entity) {
        e_2866_D eyePos = Q_2558_o.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D toTarget = entity.i_601_W().getCenter().G_564_y(eyePos).G_564_y();
        float realYaw = (d_2169_p.n_1700_B() ? d_2169_p.J_1907_R() : Q_2558_o.c_3005_b.Y_259_p.p_178_J) * (float)Math.PI / 180.0f;
        float realPitch = (d_2169_p.n_1700_B() ? d_2169_p.R_4764_Y() : Q_2558_o.c_3005_b.Y_259_p.f_4016_n) * (float)Math.PI / 180.0f;
        e_2866_D lookVec = new e_2866_D(-Math.sin(realYaw) * Math.cos(realPitch), -Math.sin(realPitch), Math.cos(realYaw) * Math.cos(realPitch)).G_564_y();
        double dot = toTarget.J_1907_R(lookVec);
        return Math.acos(u_530_F.n_1700_B(dot, -1.0, 1.0));
    }

    private boolean J_1907_R(r_4811_B entity) {
        if (entity == null || !entity.H_3699_F() || entity == Q_2558_o.c_3005_b.Y_259_p) {
            return false;
        }
        double distance = a_1344_X.J_1907_R(entity).u_1723_Y();
        if (distance > (double)((Float)this.P_4830_p.J_1907_R()).floatValue()) {
            return false;
        }
        e_2866_D eyePos = Q_2558_o.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = entity.i_601_W().getCenter();
        e_2866_D toTarget = targetPos.G_564_y(eyePos).G_564_y();
        float realYaw = (d_2169_p.n_1700_B() ? d_2169_p.J_1907_R() : Q_2558_o.c_3005_b.Y_259_p.p_178_J) * (float)Math.PI / 180.0f;
        float realPitch = (d_2169_p.n_1700_B() ? d_2169_p.R_4764_Y() : Q_2558_o.c_3005_b.Y_259_p.f_4016_n) * (float)Math.PI / 180.0f;
        e_2866_D lookVec = new e_2866_D(-Math.sin(realYaw) * Math.cos(realPitch), -Math.sin(realPitch), Math.cos(realYaw) * Math.cos(realPitch)).G_564_y();
        double dot = toTarget.J_1907_R(lookVec);
        double angle = Math.toDegrees(Math.acos(u_530_F.n_1700_B(dot, -1.0, 1.0)));
        if (angle > (double)((Float)this.h_1847_R.J_1907_R()).floatValue() / 2.0) {
            return false;
        }
        if (this.N_4405_n.t_148_a().booleanValue() && !Q_2558_o.c_3005_b.Y_259_p.c_3005_b(entity)) {
            return false;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            if (!this.w_1457_N.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue() && o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName())) {
                return false;
            }
            return E_4612_l.n_1700_B(entity, this.w_1457_N, true);
        }
        return E_4612_l.R_4764_Y(entity, this.w_1457_N) || E_4612_l.J_1907_R(entity, this.w_1457_N) || E_4612_l.n_1700_B(entity, this.w_1457_N);
    }

    private void R_4764_Y(r_4811_B target) {
        if (target == null || Q_2558_o.c_3005_b.Y_259_p == null) {
            return;
        }
        float gravity = 0.05f;
        q_1613_l heldItem = Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R();
        float arrowSpeed = heldItem instanceof Z_1630_j ? 3.15f : (heldItem instanceof I_3875_v ? 2.5f : 3.0f);
        e_2866_D eyePos = Q_2558_o.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D targetPos = target.i_601_W().getCenter();
        if (this.M_182_A.t_148_a().booleanValue()) {
            e_2866_D velocity = new e_2866_D(target.O_3598_v() - target.r_715_M, target.X_2960_b() - target.A_1038_p, target.l_2647_k() - target.i_1637_u);
            double distance = eyePos.u_1723_Y(targetPos);
            int flightTicks = this.n_1700_B(distance, arrowSpeed);
            e_2866_D predicted = targetPos.P_1922_E(velocity.n_1700_B((double)flightTicks));
            distance = eyePos.u_1723_Y(predicted);
            flightTicks = this.n_1700_B(distance, arrowSpeed);
            targetPos = targetPos.P_1922_E(velocity.n_1700_B((double)flightTicks));
        }
        e_2866_D diff = targetPos.G_564_y(eyePos);
        double hDist = Math.sqrt(diff.J_1907_R * diff.J_1907_R + diff.G_564_y * diff.G_564_y);
        float yaw = (float)(Math.toDegrees(Math.atan2(diff.G_564_y, diff.J_1907_R)) - 90.0);
        float pitch = this.n_1700_B(hDist, diff.R_4764_Y, arrowSpeed, gravity);
        float turnSpeed = ThreadLocalRandom.current().nextFloat(275.0f, 444.0f);
        r_4790_y.n_1700_B(new F_1446_q(yaw, pitch), turnSpeed, 1, 6);
    }

    private int n_1700_B(double distance, float arrowSpeed) {
        double speed = arrowSpeed;
        double traveled = 0.0;
        for (int tick = 1; tick <= 100; ++tick) {
            traveled += speed;
            speed *= 0.99;
            if (!(traveled >= distance)) continue;
            return tick;
        }
        return 100;
    }

    private float n_1700_B(double hDist, double vDist, float arrowSpeed, float gravity) {
        if (hDist < 0.5) {
            return (float)(-Math.toDegrees(Math.atan2(vDist, hDist)));
        }
        double adjustedVDist = vDist;
        float pitch = (float)(-Math.toDegrees(Math.atan2(adjustedVDist, hDist)));
        for (int iteration = 0; iteration < 4; ++iteration) {
            double error;
            double pitchRad = Math.toRadians(pitch);
            double hSpeed = (double)arrowSpeed * Math.cos(pitchRad);
            double vSpeed = (double)(-arrowSpeed) * Math.sin(pitchRad);
            double hTraveled = 0.0;
            double vTraveled = 0.0;
            for (int tick = 0; tick < 200; ++tick) {
                vTraveled += vSpeed;
                hSpeed *= 0.99;
                vSpeed *= 0.99;
                vSpeed -= (double)gravity;
                if ((hTraveled += hSpeed) >= hDist) break;
            }
            if (Math.abs(error = vDist - vTraveled) < 0.05) break;
            pitch = (float)(-Math.toDegrees(Math.atan2(adjustedVDist += error, hDist)));
        }
        return u_530_F.n_1700_B(pitch, -90.0f, 90.0f);
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        if (!this.Q_4569_t.t_148_a().booleanValue() || !this.M_588_G.t_148_a().booleanValue() || Q_2558_o.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!(Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof O_1678_k || Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof I_3875_v || Q_2558_o.c_3005_b.Y_259_p.l_1268_F.R_4764_Y().J_1907_R() instanceof Z_1630_j)) {
            return;
        }
        float centerX = (float)c_3005_b.a_2085_x().Q_4569_t() / 2.0f;
        float centerY = (float)c_3005_b.a_2085_x().M_182_A() / 2.0f;
        double fov = Q_2558_o.c_3005_b.s_956_w.n_1700_B(Q_2558_o.c_3005_b.O_508_d().J_1907_R, c_3005_b.P_2565_J(), true);
        float screenHeight = c_3005_b.a_2085_x().M_182_A();
        float halfHeight = screenHeight / 2.0f;
        float fovRadiusPixels = (float)((double)halfHeight * Math.tan(Math.toRadians((double)((Float)this.h_1847_R.J_1907_R()).floatValue() / 2.0)) / Math.tan(Math.toRadians(fov / 2.0)));
        int color = -1;
        F_489_x.n_1700_B(centerX, centerY, fovRadiusPixels, color, 2.5f);
    }

    private void N_4405_n() {
        this.C_2741_M = false;
        this.k_2293_S = -1;
        this.q_2307_F = -1;
        this.Z_875_P = 0;
        this.t_4043_B = 0;
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        if (this.t_4043_B == 2 && Q_2558_o.c_3005_b.Y_259_p != null && Q_2558_o.c_3005_b.Y_259_p.Y_601_j()) {
            Q_2558_o.c_3005_b.w_1457_N.onStoppedUsingItem(Q_2558_o.c_3005_b.Y_259_p);
        }
        this.N_4405_n();
    }
}

