/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_4463_r;
import lightning.product.P_328_a;
import lightning.product.R_552_s;
import lightning.product.X_3546_T;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.Z_2491_A;
import lightning.product.a_3913_L;
import lightning.product.b_1213_w;
import lightning.product.b_2162_C;
import lightning.product.c_4037_x;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.h_4311_S;
import lightning.product.j_8_l;
import lightning.product.n_1658_l;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3148_R;
import lightning.product.q_366_O;
import lightning.product.s_4405_m;
import lightning.product.t_1920_R;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;
import lightning.product.y_4642_Y;
import org.lwjgl.opengl.GL11;

public class v_3429_e
extends X_3546_T {
    private static v_3429_e v_4262_N;
    private boolean w_1484_f;
    private final N_4463_r t_148_a = new N_4463_r("\u0420\u0430\u0437\u0434\u0435\u043b", new p_1977_n("\u0413\u043e\u043b\u043e\u0432\u0430", false), new p_1977_n("\u0421\u043f\u0438\u043d\u0430", true), new p_1977_n("\u041d\u043e\u0433\u0438", false));
    private final q_366_O s_956_w = new q_366_O("\u0421\u043f\u0438\u043d\u0430", "\u041a\u0440\u044b\u043b\u044c\u044f", this::t_1786_h, "\u041a\u0440\u044b\u043b\u044c\u044f", "\u041a\u0430\u0442\u0430\u043d\u0430");
    private final q_366_O u_2550_I = new q_366_O("\u0413\u043e\u043b\u043e\u0432\u0430", "\u041a\u0438\u0442\u0430\u0439\u0441\u043a\u0430\u044f \u0448\u043b\u044f\u043f\u0430", this::M_182_A, "\u041a\u0438\u0442\u0430\u0439\u0441\u043a\u0430\u044f \u0448\u043b\u044f\u043f\u0430", "\u041a\u043e\u0440\u043e\u043d\u0430");
    private final I_686_h M_588_G = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440 (\u0433\u043e\u043b\u043e\u0432\u0430)", 1.0f, 0.6f, 1.6f, 0.05f, this::M_182_A);
    private final q_366_O P_4830_p = new q_366_O("\u041e\u0431\u0443\u0432\u044c", "\u041a\u043b\u043e\u0443\u043d\u0441\u043a\u0438\u0435", this::N_4405_n, "\u041a\u043b\u043e\u0443\u043d\u0441\u043a\u0438\u0435", "\u041f\u043e\u043b\u043e\u0441\u0430\u0442\u044b\u0435", "\u0421\u0430\u043f\u043e\u0433\u0438", "\u041f\u0443\u0437\u044b\u0440\u0438");
    private final I_686_h h_1847_R = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440 (\u043d\u043e\u0433\u0438)", 1.0f, 0.6f, 1.8f, 0.05f, this::N_4405_n);
    private final p_1977_n Q_4569_t = new p_1977_n("\u041f\u043e\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435 (\u043d\u043e\u0433\u0438)", true, this::N_4405_n);
    private final q_366_O M_182_A = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435", "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435");
    private final q_366_O t_1786_h = new q_366_O("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430 (\u043a\u0440\u044b\u043b\u044c\u044f)", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u0428\u0435\u0439\u0434\u0435\u0440");
    private final q_366_O N_4405_n = new q_366_O("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430 (\u043a\u0430\u0442\u0430\u043d\u0430)", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u0428\u0435\u0439\u0434\u0435\u0440");
    private final q_366_O w_1457_N = new q_366_O("\u0428\u0435\u0439\u0434\u0435\u0440", "\u0418\u0437 Ambience", () -> this.w_1457_N() && this.t_1786_h.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440") || this.Y_601_j() && this.N_4405_n.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440"), "\u0418\u0437 Ambience", "Plasma", "Balatro");
    private final I_686_h Y_601_j = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440", 1.0f, 0.5f, 2.0f, 0.05f);
    private final q_366_O Y_259_p = new q_366_O("\u0426\u0432\u0435\u0442", "Interface", "Interface", "\u0421\u0432\u043e\u0439");
    private final h_2367_h Q_2552_b = new h_2367_h("\u0421\u0432\u043e\u0439 \u0446\u0432\u0435\u0442", true, H_2506_c.n_1700_B(255, 255, 255, 200));
    private final p_1977_n C_2741_M = new p_1977_n("\u041d\u0430 \u0434\u0440\u0443\u0437\u044c\u044f\u0445", true);
    private final p_1977_n k_2293_S = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043e\u0442 3-\u0433\u043e \u043b\u0438\u0446\u0430", false);
    private final p_1977_n q_2307_F = new p_1977_n("\u041f\u043e\u0432\u0435\u0440\u0445 \u0447\u0430\u043c\u0441\u043e\u0432 (F5 \u0441\u0437\u0430\u0434\u0438)", true);
    private final p_1977_n Z_875_P = new p_1977_n("\u0421\u043a\u0440\u044b\u0442\u044c \u043f\u043b\u0430\u0449", true);
    private final I_686_h t_4043_B = new I_686_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", 1.0f, 0.0f, 2.0f, 0.1f);
    private float x_607_J = 0.0f;
    private float e_4240_b = 0.0f;
    private static final float[][] n_3318_d;
    private static final float[][] d_2427_y;
    private static final float[][] z_1737_N;
    private static final int[] v_4276_D;
    private static final int[] d_2461_k;
    private static final int[] G_624_v;
    private static final float T_2506_i;
    private static final g_2336_b q_4610_l;

    public static v_3429_e h_1847_R() {
        return v_4262_N;
    }

    public v_3429_e() {
        super("Cosmetics", y_2603_k.R_4764_Y);
        v_4262_N = this;
        this.M_182_A.n_1700_B(this::w_1457_N);
        this.t_1786_h.n_1700_B(this::w_1457_N);
        this.N_4405_n.n_1700_B(this::Y_601_j);
        this.t_4043_B.n_1700_B(() -> this.w_1457_N() && !this.t_1786_h.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440"));
        this.Q_2552_b.n_1700_B(() -> "\u0421\u0432\u043e\u0439".equals(this.Y_259_p.J_1907_R()));
        this.n_1700_B(this.t_148_a, this.u_2550_I, this.M_588_G, this.s_956_w, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.t_4043_B, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P);
    }

    public boolean n_1700_B(a_3913_L player) {
        if (y_4642_Y.R_4764_Y() && player == v_3429_e.c_3005_b.Y_259_p) {
            return true;
        }
        if (!this.w_1484_f() || !this.Z_875_P.t_148_a().booleanValue()) {
            return false;
        }
        boolean isLocal = player == v_3429_e.c_3005_b.Y_259_p;
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        return isLocal || isFriend && this.C_2741_M.t_148_a() != false;
    }

    @Y_1740_V
    public void n_1700_B(P_328_a e) {
        b_2162_C emotionsPreview = b_2162_C.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            return;
        }
        R_552_s<?> r_552_s = e.J_1907_R();
        if (r_552_s instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)((Object)r_552_s);
            r_552_s = e.G_564_y();
            if (r_552_s instanceof n_1658_l) {
                n_1658_l biped = (n_1658_l)r_552_s;
                boolean isLocal = player == v_3429_e.c_3005_b.Y_259_p;
                boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
                if (!isLocal && !isFriend) {
                    return;
                }
                if (!isLocal && !this.C_2741_M.t_148_a().booleanValue()) {
                    return;
                }
                if (isLocal && this.k_2293_S.t_148_a().booleanValue() && v_3429_e.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
                    return;
                }
                if (this.C_2741_M()) {
                    this.J_1907_R(player, e.R_4764_Y(), biped);
                }
                if (this.k_2293_S()) {
                    this.n_1700_B(player, e.R_4764_Y(), biped);
                }
                if (!this.Q_4569_t() && this.Y_259_p()) {
                    this.R_4764_Y(player);
                    this.G_564_y(player, e.R_4764_Y(), biped);
                } else if (!this.Q_4569_t() && this.Q_2552_b()) {
                    this.R_4764_Y(e.R_4764_Y(), biped);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(float partialTicks) {
        if (!this.w_1484_f() || v_3429_e.c_3005_b.Y_601_j == null || v_3429_e.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.Q_4569_t()) {
            return;
        }
        b_2162_C emotionsPreview = b_2162_C.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            return;
        }
        this.w_1484_f = true;
        try {
            c_4037_x.v_4276_D();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.e_4240_b();
            c_4037_x.q_2307_F();
            c_4037_x.t_1786_h();
            c_4037_x.J_1907_R(false);
            c_4037_x.u_2550_I();
            GL11.glShadeModel((int)7425);
            for (X_4340_E player : v_3429_e.c_3005_b.Y_601_j.N_4405_n()) {
                if (!this.J_1907_R(player)) continue;
                g_221_o ms = new g_221_o();
                double dx = u_530_F.G_564_y((double)partialTicks, player.r_715_M, player.O_3598_v()) - c_3005_b.O_508_d().renderPosX();
                double dy = u_530_F.G_564_y((double)partialTicks, player.A_1038_p, player.X_2960_b()) - c_3005_b.O_508_d().renderPosY();
                double dz = u_530_F.G_564_y((double)partialTicks, player.i_1637_u, player.l_2647_k()) - c_3005_b.O_508_d().renderPosZ();
                ms.n_1700_B();
                ms.n_1700_B(dx, dy, dz);
                ms.n_1700_B();
                h_4311_S pr = (h_4311_S)c_3005_b.O_508_d().n_1700_B(player);
                float yaw = u_530_F.v_4262_N(partialTicks, player.j_276_v, player.p_178_J);
                pr.n_1700_B(player, yaw, partialTicks, ms);
                n_1658_l biped = (n_1658_l)pr.n_1700_B();
                if (this.Y_259_p()) {
                    this.R_4764_Y(player);
                    this.G_564_y(player, ms, biped);
                } else if (this.Q_2552_b()) {
                    this.R_4764_Y(ms, biped);
                }
                ms.J_1907_R();
                ms.J_1907_R();
            }
            c_4037_x.M_588_G();
            c_4037_x.J_1907_R(true);
            c_4037_x.N_4405_n();
            c_4037_x.x_607_J();
            c_4037_x.Y_259_p();
            c_4037_x.d_2461_k();
        }
        finally {
            this.w_1484_f = false;
        }
    }

    private boolean Q_4569_t() {
        if (!this.q_2307_F.t_148_a().booleanValue()) {
            return false;
        }
        if (!this.t_1786_h()) {
            return false;
        }
        if (c_3005_b == null || v_3429_e.c_3005_b.P_4830_p == null) {
            return false;
        }
        return v_3429_e.c_3005_b.P_4830_p.P_4830_p() == t_1920_R.J_1907_R;
    }

    private boolean J_1907_R(a_3913_L player) {
        boolean isLocal = player == v_3429_e.c_3005_b.Y_259_p;
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        if (!isLocal && !isFriend) {
            return false;
        }
        if (!isLocal && !this.C_2741_M.t_148_a().booleanValue()) {
            return false;
        }
        return !isLocal || this.k_2293_S.t_148_a() == false || !v_3429_e.c_3005_b.P_4830_p.P_4830_p().n_1700_B();
    }

    private boolean M_182_A() {
        return Boolean.TRUE.equals(this.t_148_a.J_1907_R("\u0413\u043e\u043b\u043e\u0432\u0430"));
    }

    private boolean t_1786_h() {
        return Boolean.TRUE.equals(this.t_148_a.J_1907_R("\u0421\u043f\u0438\u043d\u0430"));
    }

    private boolean N_4405_n() {
        return Boolean.TRUE.equals(this.t_148_a.J_1907_R("\u041d\u043e\u0433\u0438"));
    }

    private boolean w_1457_N() {
        return this.t_1786_h() && "\u041a\u0440\u044b\u043b\u044c\u044f".equals(this.s_956_w.J_1907_R());
    }

    private boolean Y_601_j() {
        return this.t_1786_h() && "\u041a\u0430\u0442\u0430\u043d\u0430".equals(this.s_956_w.J_1907_R());
    }

    private boolean Y_259_p() {
        return this.w_1457_N();
    }

    private boolean Q_2552_b() {
        return this.Y_601_j();
    }

    private boolean C_2741_M() {
        return this.M_182_A();
    }

    private boolean k_2293_S() {
        return this.N_4405_n();
    }

    private void n_1700_B(a_3913_L player, g_221_o matrixStack, n_1658_l<?> biped) {
        float wiggle = this.Q_4569_t.t_148_a() != false ? u_530_F.n_1700_B((float)(System.currentTimeMillis() % 4000L) / 4000.0f * u_530_F.R_4764_Y) * 0.04f : 0.0f;
        this.n_1700_B(matrixStack, biped.u_1723_Y, false, wiggle);
        this.n_1700_B(matrixStack, biped.v_4262_N, true, -wiggle);
    }

    private void n_1700_B(g_221_o ms, e_4189_z leg, boolean mirror, float wiggle) {
        ms.n_1700_B();
        leg.n_1700_B(ms);
        ms.n_1700_B(0.0, (double)0.72f, 0.0);
        if (wiggle != 0.0f) {
            ms.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(wiggle * 14.0f));
        }
        float s = ((Float)this.h_1847_R.J_1907_R()).floatValue();
        ms.n_1700_B(s, s, s);
        if (mirror) {
            ms.n_1700_B(-1.0f, 1.0f, 1.0f);
        }
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        if (!this.w_1484_f) {
            c_4037_x.N_4405_n();
        }
        c_4037_x.w_1484_f(7425);
        D_1098_v m = ms.R_4764_Y().n_1700_B();
        if (!this.n_1700_B(7, E_688_b.Y_601_j)) {
            c_4037_x.k_2293_S();
            c_4037_x.x_607_J();
            c_4037_x.w_1484_f(7424);
            ms.J_1907_R();
            return;
        }
        switch ((String)this.P_4830_p.J_1907_R()) {
            case "\u041f\u043e\u043b\u043e\u0441\u0430\u0442\u044b\u0435": {
                this.J_1907_R(m);
                break;
            }
            case "\u0421\u0430\u043f\u043e\u0433\u0438": {
                this.R_4764_Y(m);
                break;
            }
            case "\u041f\u0443\u0437\u044b\u0440\u0438": {
                this.G_564_y(m);
                break;
            }
            default: {
                this.n_1700_B(m);
            }
        }
        Y_1740_V.J_1907_R();
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
        ms.J_1907_R();
    }

    private void n_1700_B(D_1098_v m) {
        float[] red = H_2506_c.P_1922_E(H_2506_c.n_1700_B(220, 30, 30, 255));
        float[] redDk = H_2506_c.P_1922_E(H_2506_c.n_1700_B(160, 20, 20, 255));
        float[] yellow = H_2506_c.P_1922_E(H_2506_c.n_1700_B(245, 220, 50, 255));
        float[] white = H_2506_c.P_1922_E(H_2506_c.n_1700_B(245, 245, 245, 255));
        float[] black = H_2506_c.P_1922_E(H_2506_c.n_1700_B(25, 25, 25, 255));
        this.n_1700_B(m, -0.13f, -0.06f, 0.13f, 0.13f, 0.02f, 0.24f, redDk[0], redDk[1], redDk[2], 1.0f);
        this.n_1700_B(m, -0.15f, -0.06f, -0.13f, 0.15f, 0.02f, 0.13f, red[0], red[1], red[2], 1.0f);
        this.n_1700_B(m, -0.18f, -0.1f, -0.46f, 0.18f, 0.02f, -0.1f, red[0], red[1], red[2], 1.0f);
        this.n_1700_B(m, -0.13f, -0.13f, -0.54f, 0.13f, -0.02f, -0.42f, yellow[0], yellow[1], yellow[2], 1.0f);
        this.n_1700_B(m, -0.12f, -0.18f, -0.1f, 0.12f, -0.1f, 0.1f, white[0], white[1], white[2], 1.0f);
        this.n_1700_B(m, -0.16f, 0.02f, -0.42f, 0.16f, 0.06f, 0.22f, black[0], black[1], black[2], 1.0f);
    }

    private void J_1907_R(D_1098_v m) {
        float[] red = H_2506_c.P_1922_E(H_2506_c.n_1700_B(225, 35, 35, 255));
        float[] white = H_2506_c.P_1922_E(H_2506_c.n_1700_B(245, 245, 245, 255));
        float[] black = H_2506_c.P_1922_E(H_2506_c.n_1700_B(25, 25, 25, 255));
        float[] blue = H_2506_c.P_1922_E(H_2506_c.n_1700_B(50, 100, 220, 255));
        this.n_1700_B(m, -0.13f, -0.06f, 0.13f, 0.13f, 0.02f, 0.22f, red[0], red[1], red[2], 1.0f);
        this.n_1700_B(m, -0.15f, -0.06f, -0.13f, 0.15f, 0.02f, 0.13f, red[0], red[1], red[2], 1.0f);
        float toeStart = -0.13f;
        float toeEnd = -0.5f;
        int segs = 6;
        float segLen = (toeStart - toeEnd) / (float)segs;
        for (int i = 0; i < segs; ++i) {
            float z0 = toeStart - (float)(i + 1) * segLen;
            float z1 = toeStart - (float)i * segLen;
            float[] c = (i & 1) == 0 ? white : red;
            float width = 0.17f + (float)i * 0.004f;
            float top = -0.1f - (float)i * 0.005f;
            this.n_1700_B(m, -width, top, z0, width, 0.02f, z1, c[0], c[1], c[2], 1.0f);
        }
        this.n_1700_B(m, -0.1f, -0.16f, -0.56f, 0.1f, -0.04f, -0.46f, blue[0], blue[1], blue[2], 1.0f);
        this.n_1700_B(m, -0.13f, -0.18f, -0.1f, 0.13f, -0.1f, 0.1f, white[0], white[1], white[2], 1.0f);
        this.n_1700_B(m, -0.16f, 0.02f, -0.46f, 0.16f, 0.06f, 0.2f, black[0], black[1], black[2], 1.0f);
    }

    private void R_4764_Y(D_1098_v m) {
        float[] leather = H_2506_c.P_1922_E(H_2506_c.n_1700_B(70, 40, 25, 255));
        float[] leatherDk = H_2506_c.P_1922_E(H_2506_c.n_1700_B(40, 22, 12, 255));
        float[] buckle = H_2506_c.P_1922_E(H_2506_c.n_1700_B(230, 200, 60, 255));
        float[] sole = H_2506_c.P_1922_E(H_2506_c.n_1700_B(20, 20, 20, 255));
        this.n_1700_B(m, -0.14f, -0.06f, 0.14f, 0.14f, 0.02f, 0.2f, leather[0], leather[1], leather[2], 1.0f);
        this.n_1700_B(m, -0.16f, -0.07f, -0.16f, 0.16f, 0.02f, 0.14f, leather[0], leather[1], leather[2], 1.0f);
        this.n_1700_B(m, -0.16f, -0.09f, -0.28f, 0.16f, 0.0f, -0.12f, leather[0], leather[1], leather[2], 1.0f);
        this.n_1700_B(m, -0.18f, 0.02f, -0.28f, 0.18f, 0.07f, 0.22f, sole[0], sole[1], sole[2], 1.0f);
        this.n_1700_B(m, -0.14f, -0.48f, -0.1f, 0.14f, -0.07f, 0.12f, leather[0], leather[1], leather[2], 1.0f);
        this.n_1700_B(m, -0.15f, -0.52f, -0.11f, 0.15f, -0.44f, 0.13f, leatherDk[0], leatherDk[1], leatherDk[2], 1.0f);
        this.n_1700_B(m, -0.17f, -0.2f, -0.05f, 0.17f, -0.14f, 0.05f, buckle[0], buckle[1], buckle[2], 1.0f);
    }

    private void G_564_y(D_1098_v m) {
        float[] cyan = H_2506_c.P_1922_E(H_2506_c.n_1700_B(80, 200, 230, 255));
        float[] pink = H_2506_c.P_1922_E(H_2506_c.n_1700_B(245, 110, 180, 255));
        float[] yellow = H_2506_c.P_1922_E(H_2506_c.n_1700_B(245, 220, 50, 255));
        float[] white = H_2506_c.P_1922_E(H_2506_c.n_1700_B(250, 250, 250, 255));
        this.n_1700_B(m, -0.16f, -0.13f, 0.06f, 0.16f, 0.02f, 0.26f, pink[0], pink[1], pink[2], 1.0f);
        this.n_1700_B(m, -0.22f, -0.17f, -0.18f, 0.22f, 0.02f, 0.1f, cyan[0], cyan[1], cyan[2], 1.0f);
        this.n_1700_B(m, -0.16f, -0.14f, -0.32f, 0.16f, 0.02f, -0.16f, pink[0], pink[1], pink[2], 1.0f);
        this.n_1700_B(m, -0.06f, -0.19f, -0.06f, 0.06f, -0.16f, 0.02f, yellow[0], yellow[1], yellow[2], 1.0f);
        this.n_1700_B(m, -0.04f, -0.17f, -0.14f, 0.04f, -0.15f, -0.08f, yellow[0], yellow[1], yellow[2], 1.0f);
        this.n_1700_B(m, -0.13f, -0.22f, -0.08f, 0.13f, -0.16f, 0.08f, white[0], white[1], white[2], 1.0f);
    }

    private void J_1907_R(a_3913_L player, g_221_o matrixStack, n_1658_l<?> biped) {
        if ("\u041a\u043e\u0440\u043e\u043d\u0430".equals(this.u_2550_I.J_1907_R())) {
            this.n_1700_B(matrixStack, biped);
        } else {
            this.R_4764_Y(player, matrixStack, biped);
        }
    }

    private void R_4764_Y(a_3913_L player, g_221_o matrixStack, n_1658_l<?> biped) {
        int outlineColor;
        int baseColor;
        float z;
        float x;
        int angle;
        float iPi;
        int i;
        float radius = 0.42f * ((Float)this.M_588_G.J_1907_R()).floatValue();
        float rimY = player.l_1268_F.J_1907_R.get(3).n_1700_B() ? -0.42f : -0.49f;
        float apexY = rimY - 0.2f * ((Float)this.M_588_G.J_1907_R()).floatValue();
        matrixStack.n_1700_B();
        biped.n_1700_B.n_1700_B(matrixStack);
        c_4037_x.Y_601_j();
        c_4037_x.N_4405_n();
        c_4037_x.q_2307_F();
        c_4037_x.e_4240_b();
        c_4037_x.s_2632_s();
        c_4037_x.w_1484_f(7425);
        c_4037_x.G_564_y(2.0f);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        D_1098_v m = matrixStack.R_4764_Y().n_1700_B();
        s_4405_m headShader = this.q_2307_F();
        if (!this.n_1700_B(5, E_688_b.Y_601_j)) {
            return;
        }
        for (i = 0; i <= 180; ++i) {
            iPi = (float)i * T_2506_i;
            angle = i * 8;
            x = u_530_F.n_1700_B(iPi) * radius;
            z = u_530_F.J_1907_R(iPi) * radius;
            baseColor = this.c_3005_b();
            outlineColor = H_2506_c.J_1907_R(3, angle, baseColor, H_2506_c.J_1907_R(baseColor, 0.5f));
            int coneColor = H_2506_c.n_1700_B(outlineColor, (float)H_2506_c.G_564_y(outlineColor) / 255.0f * 0.5f);
            A_4115_X.n_1700_B(m, x, rimY, z).n_1700_B(coneColor).endVertex();
            A_4115_X.n_1700_B(m, 0.0f, apexY, 0.0f).n_1700_B(baseColor).endVertex();
        }
        Y_1740_V.J_1907_R();
        c_4037_x.J_1907_R(false);
        if (!this.n_1700_B(2, E_688_b.Y_601_j)) {
            return;
        }
        for (i = 0; i <= 180; ++i) {
            iPi = (float)i * T_2506_i;
            angle = i * 8;
            x = u_530_F.n_1700_B(iPi) * radius;
            z = u_530_F.J_1907_R(iPi) * radius;
            baseColor = this.c_3005_b();
            outlineColor = H_2506_c.J_1907_R(3, angle, baseColor, H_2506_c.J_1907_R(baseColor, 0.5f));
            A_4115_X.n_1700_B(m, x, rimY, z).n_1700_B(outlineColor).endVertex();
        }
        Y_1740_V.J_1907_R();
        c_4037_x.J_1907_R(true);
        GL11.glDisable((int)2848);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
        if (headShader != null) {
            headShader.R_4764_Y();
        }
        matrixStack.J_1907_R();
    }

    private void n_1700_B(g_221_o matrixStack, n_1658_l<?> biped) {
        float a1;
        float a0;
        int i;
        float radius = 0.28f * ((Float)this.M_588_G.J_1907_R()).floatValue();
        float y = -0.46f;
        float peakH = 0.12f * ((Float)this.M_588_G.J_1907_R()).floatValue();
        int segments = 8;
        int baseColor = this.c_3005_b();
        int dark = H_2506_c.J_1907_R(baseColor, 0.75f);
        matrixStack.n_1700_B();
        biped.n_1700_B.n_1700_B(matrixStack);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.N_4405_n();
        c_4037_x.q_2307_F();
        c_4037_x.e_4240_b();
        c_4037_x.w_1484_f(7425);
        s_4405_m headShader = this.q_2307_F();
        D_1098_v m = matrixStack.R_4764_Y().n_1700_B();
        if (!this.n_1700_B(7, E_688_b.Y_601_j)) {
            return;
        }
        for (i = 0; i < segments; ++i) {
            a0 = u_530_F.R_4764_Y * (float)i / (float)segments;
            a1 = u_530_F.R_4764_Y * (float)(i + 1) / (float)segments;
            float x0o = u_530_F.J_1907_R(a0) * radius;
            float z0o = u_530_F.n_1700_B(a0) * radius;
            float x1o = u_530_F.J_1907_R(a1) * radius;
            float z1o = u_530_F.n_1700_B(a1) * radius;
            float x0i = u_530_F.J_1907_R(a0) * (radius * 0.72f);
            float z0i = u_530_F.n_1700_B(a0) * (radius * 0.72f);
            float x1i = u_530_F.J_1907_R(a1) * (radius * 0.72f);
            float z1i = u_530_F.n_1700_B(a1) * (radius * 0.72f);
            A_4115_X.n_1700_B(m, x0o, y, z0o).n_1700_B(baseColor).endVertex();
            A_4115_X.n_1700_B(m, x1o, y, z1o).n_1700_B(baseColor).endVertex();
            A_4115_X.n_1700_B(m, x1i, y, z1i).n_1700_B(dark).endVertex();
            A_4115_X.n_1700_B(m, x0i, y, z0i).n_1700_B(dark).endVertex();
        }
        Y_1740_V.J_1907_R();
        if (!this.n_1700_B(4, E_688_b.Y_601_j)) {
            return;
        }
        for (i = 0; i < segments; ++i) {
            a0 = u_530_F.R_4764_Y * (float)i / (float)segments;
            a1 = u_530_F.R_4764_Y * (float)(i + 1) / (float)segments;
            float am = (a0 + a1) * 0.5f;
            float x0 = u_530_F.J_1907_R(a0) * radius;
            float z0 = u_530_F.n_1700_B(a0) * radius;
            float x1 = u_530_F.J_1907_R(a1) * radius;
            float z1 = u_530_F.n_1700_B(a1) * radius;
            float xp = u_530_F.J_1907_R(am) * (radius * 0.92f);
            float zp = u_530_F.n_1700_B(am) * (radius * 0.92f);
            A_4115_X.n_1700_B(m, x0, y, z0).n_1700_B(dark).endVertex();
            A_4115_X.n_1700_B(m, x1, y, z1).n_1700_B(dark).endVertex();
            A_4115_X.n_1700_B(m, xp, y - peakH, zp).n_1700_B(baseColor).endVertex();
        }
        Y_1740_V.J_1907_R();
        c_4037_x.w_1484_f(7424);
        c_4037_x.x_607_J();
        c_4037_x.k_2293_S();
        if (headShader != null) {
            headShader.R_4764_Y();
        }
        matrixStack.J_1907_R();
    }

    private s_4405_m q_2307_F() {
        if (!this.M_182_A() || !this.Z_875_P() || v_3429_e.c_3005_b.Y_601_j == null) {
            return null;
        }
        s_4405_m shader = this.A_4115_X();
        if (shader == null || !shader.n_1700_B()) {
            return null;
        }
        float pt = c_3005_b.P_2565_J();
        float skyTime = ((float)v_3429_e.c_3005_b.Y_601_j.X_933_l() + pt) * ((Float)j_8_l.Y_601_j.J_1907_R()).floatValue();
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        float[] bc = H_2506_c.P_1922_E(baseColor);
        shader.J_1907_R();
        shader.n_1700_B("u_Color", bc[0], bc[1], bc[2], 1.0f);
        shader.J_1907_R("u_Alpha", 0.9f);
        shader.n_1700_B("u_Scale", ((Float)j_8_l.w_1457_N.J_1907_R()).floatValue());
        shader.J_1907_R("u_Time", skyTime * 0.08f);
        return shader;
    }

    private boolean Z_875_P() {
        return this.w_1457_N() && this.t_1786_h.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440") || this.Y_601_j() && this.N_4405_n.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440");
    }

    private int c_3005_b() {
        return "Interface".equals(this.Y_259_p.J_1907_R()) ? q_3148_R.n_1700_B(K_1200_E.J_1907_R) : (Integer)this.Q_2552_b.J_1907_R();
    }

    private void R_4764_Y(a_3913_L player) {
        this.e_4240_b = this.x_607_J;
        float target = player.k_578_l() ? 1.0f : 0.0f;
        this.x_607_J += (target - this.x_607_J) * 0.12f;
    }

    private void J_1907_R(g_221_o ms, n_1658_l<?> biped) {
        biped.R_4764_Y.n_1700_B(ms);
        ms.n_1700_B((double)-0.3f, 0.0, (double)0.18f);
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(2.0f));
        ms.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(2.0f));
        ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-38.0f));
        float sc = ((Float)this.Y_601_j.J_1907_R()).floatValue();
        ms.n_1700_B(sc, sc, sc);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        if (!this.w_1484_f) {
            c_4037_x.N_4405_n();
        } else {
            c_4037_x.t_1786_h();
            c_4037_x.J_1907_R(false);
        }
        c_4037_x.w_1484_f(7425);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
    }

    private void H_2857_Y() {
        GL11.glDisable((int)2848);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
    }

    private void n_1700_B(D_1098_v m, float x0, float y0, float z0, float x1, float y1, float z1, float r, float g, float b, float a) {
        float rS = r * 0.85f;
        float gS = g * 0.85f;
        float bS = b * 0.85f;
        float rD = r * 0.7f;
        float gD = g * 0.7f;
        float bD = b * 0.7f;
        A_4115_X.n_1700_B(m, x0, y0, z0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z0).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z1).n_1700_B(rS, gS, bS, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y0, z1).n_1700_B(rS, gS, bS, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z1).n_1700_B(rS, gS, bS, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z1).n_1700_B(rS, gS, bS, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y0, z1).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y0, z0).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z0).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z1).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z0).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z1).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z1).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z0).n_1700_B(rD, gD, bD, a).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z0).n_1700_B(r, g, b, a * 0.95f).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z0).n_1700_B(r, g, b, a * 0.95f).endVertex();
        A_4115_X.n_1700_B(m, x1, y1, z1).n_1700_B(r, g, b, a * 0.95f).endVertex();
        A_4115_X.n_1700_B(m, x0, y1, z1).n_1700_B(r, g, b, a * 0.95f).endVertex();
        A_4115_X.n_1700_B(m, x0, y0, z1).n_1700_B(rS, gS, bS, a * 0.9f).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z1).n_1700_B(rS, gS, bS, a * 0.9f).endVertex();
        A_4115_X.n_1700_B(m, x1, y0, z0).n_1700_B(rS, gS, bS, a * 0.9f).endVertex();
        A_4115_X.n_1700_B(m, x0, y0, z0).n_1700_B(rS, gS, bS, a * 0.9f).endVertex();
    }

    private void R_4764_Y(g_221_o ms, n_1658_l<?> biped) {
        ms.n_1700_B();
        this.J_1907_R(ms, biped);
        D_1098_v mat = ms.R_4764_Y().n_1700_B();
        int baseColor = this.c_3005_b();
        int bladeArgb = H_2506_c.J_1907_R(baseColor, 60);
        float[] blade = H_2506_c.P_1922_E(bladeArgb);
        float[] tsuba = H_2506_c.P_1922_E(H_2506_c.J_1907_R(baseColor, 0.6f));
        float[] wrap1 = H_2506_c.P_1922_E(H_2506_c.J_1907_R(baseColor, 1.3f));
        float[] wrap2 = H_2506_c.P_1922_E(H_2506_c.J_1907_R(baseColor, 1.7f));
        float[] kashira = H_2506_c.P_1922_E(H_2506_c.J_1907_R(baseColor, 2.0f));
        float bw = 0.018f;
        float bd = 0.008f;
        boolean shaderKatana = this.N_4405_n.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440");
        s_4405_m shader = null;
        if (shaderKatana && v_3429_e.c_3005_b.Y_601_j != null) {
            shader = this.A_4115_X();
            if (shader != null && shader.n_1700_B()) {
                float pt = c_3005_b.P_2565_J();
                float skyTime = ((float)v_3429_e.c_3005_b.Y_601_j.X_933_l() + pt) * ((Float)j_8_l.Y_601_j.J_1907_R()).floatValue();
                shader.J_1907_R();
                shader.n_1700_B("u_Scale", ((Float)j_8_l.w_1457_N.J_1907_R()).floatValue());
                shader.J_1907_R("u_Time", skyTime * 0.08f);
            } else {
                shaderKatana = false;
                shader = null;
            }
        }
        if (shaderKatana && shader != null) {
            this.n_1700_B(shader, bladeArgb, 0.97f);
            this.n_1700_B(mat, -bw, 0.06f, -bd, bw, 0.74f, bd);
            ms.n_1700_B();
            ms.n_1700_B(0.0, (double)0.74f, 0.0);
            D_1098_v tipMat = ms.R_4764_Y().n_1700_B();
            this.n_1700_B(shader, bladeArgb, 0.97f);
            this.n_1700_B(tipMat, -bw, 0.0f, -bd, bw, 0.0f, -bd, 0.0f, 0.06f, 0.0f);
            this.n_1700_B(shader, bladeArgb, 0.92f);
            this.n_1700_B(tipMat, bw, 0.0f, bd, -bw, 0.0f, bd, 0.0f, 0.06f, 0.0f);
            this.n_1700_B(shader, bladeArgb, 0.85f);
            this.n_1700_B(tipMat, -bw, 0.0f, bd, -bw, 0.0f, -bd, 0.0f, 0.06f, 0.0f);
            this.n_1700_B(shader, bladeArgb, 0.85f);
            this.n_1700_B(tipMat, bw, 0.0f, -bd, bw, 0.0f, bd, 0.0f, 0.06f, 0.0f);
            ms.J_1907_R();
            tw = 0.04f;
            float td = 0.016f;
            float th = 0.018f;
            this.n_1700_B(shader, tsuba[0], tsuba[1], tsuba[2], 0.95f * tsuba[3]);
            this.n_1700_B(mat, -tw, 0.06f - th * 0.5f, -td, tw, 0.06f + th * 0.5f, td);
            float hw = 0.016f;
            float hd = 0.01f;
            float hLen = 0.22f;
            int wrapCount = 8;
            float segH = hLen / (float)wrapCount;
            for (int i = 0; i < wrapCount; ++i) {
                float y0 = 0.06f - (float)(i + 1) * segH;
                float y1 = 0.06f - (float)i * segH;
                float[] c = (i & 1) == 0 ? wrap1 : wrap2;
                this.n_1700_B(shader, c[0], c[1], c[2], c[3]);
                this.n_1700_B(mat, -hw, y0, -hd, hw, y1, hd);
            }
            float kw = 0.02f;
            float kd = 0.012f;
            float ky0 = 0.06f - hLen - 0.015f;
            float ky1 = 0.06f - hLen;
            this.n_1700_B(shader, kashira[0], kashira[1], kashira[2], 0.95f * kashira[3]);
            this.n_1700_B(mat, -kw, ky0, -kd, kw, ky1, kd);
            shader.R_4764_Y();
        } else {
            if (!this.n_1700_B(7, E_688_b.Y_601_j)) {
                return;
            }
            this.n_1700_B(mat, -bw, 0.06f, -bd, bw, 0.74f, bd, blade[0], blade[1], blade[2], 0.97f);
            ms.n_1700_B();
            ms.n_1700_B(0.0, (double)0.74f, 0.0);
            D_1098_v tipMat = ms.R_4764_Y().n_1700_B();
            A_4115_X.n_1700_B(tipMat, -bw, 0.0f, -bd).n_1700_B(blade[0], blade[1], blade[2], 0.97f).endVertex();
            A_4115_X.n_1700_B(tipMat, bw, 0.0f, -bd).n_1700_B(blade[0], blade[1], blade[2], 0.97f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.97f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.97f).endVertex();
            A_4115_X.n_1700_B(tipMat, bw, 0.0f, bd).n_1700_B(blade[0], blade[1], blade[2], 0.92f).endVertex();
            A_4115_X.n_1700_B(tipMat, -bw, 0.0f, bd).n_1700_B(blade[0], blade[1], blade[2], 0.92f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.92f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.92f).endVertex();
            A_4115_X.n_1700_B(tipMat, -bw, 0.0f, bd).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, -bw, 0.0f, -bd).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, bw, 0.0f, -bd).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, bw, 0.0f, bd).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            A_4115_X.n_1700_B(tipMat, 0.0f, 0.06f, 0.0f).n_1700_B(blade[0], blade[1], blade[2], 0.85f).endVertex();
            ms.J_1907_R();
            tw = 0.04f;
            float td = 0.016f;
            float th = 0.018f;
            this.n_1700_B(mat, -tw, 0.06f - th * 0.5f, -td, tw, 0.06f + th * 0.5f, td, tsuba[0], tsuba[1], tsuba[2], 0.95f);
            float hw = 0.016f;
            float hd = 0.01f;
            float hLen = 0.22f;
            int wrapCount = 8;
            float segH = hLen / (float)wrapCount;
            for (int i = 0; i < wrapCount; ++i) {
                float y0 = 0.06f - (float)(i + 1) * segH;
                float y1 = 0.06f - (float)i * segH;
                float[] c = (i & 1) == 0 ? wrap1 : wrap2;
                this.n_1700_B(mat, -hw, y0, -hd, hw, y1, hd, c[0], c[1], c[2], c[3]);
            }
            float kw = 0.02f;
            float kd = 0.012f;
            float ky0 = 0.06f - hLen - 0.015f;
            float ky1 = 0.06f - hLen;
            this.n_1700_B(mat, -kw, ky0, -kd, kw, ky1, kd, kashira[0], kashira[1], kashira[2], 0.95f);
            Y_1740_V.J_1907_R();
        }
        this.H_2857_Y();
        ms.J_1907_R();
    }

    private void n_1700_B(s_4405_m shader, int argb, float alphaMul) {
        float r = (float)H_2506_c.n_1700_B(argb) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(argb) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(argb) / 255.0f;
        float a = (float)H_2506_c.G_564_y(argb) / 255.0f;
        shader.n_1700_B("u_Color", r, g, b, 1.0f);
        shader.J_1907_R("u_Alpha", alphaMul * a);
    }

    private void n_1700_B(s_4405_m shader, float r, float g, float b, float a) {
        shader.n_1700_B("u_Color", r, g, b, 1.0f);
        shader.J_1907_R("u_Alpha", a);
    }

    private void n_1700_B(D_1098_v matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        Z_2491_A c = new Z_2491_A((minX + maxX) * 0.5f, (minY + maxY) * 0.5f, (minZ + maxZ) * 0.5f, 1.0f);
        c.n_1700_B(matrix);
        float cx = c.n_1700_B();
        float cy = c.J_1907_R();
        float cz = c.R_4764_Y();
        if (!this.n_1700_B(7, E_688_b.Y_601_j)) {
            return;
        }
        this.J_1907_R(matrix, minX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, minX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, minZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, maxY, maxZ, cx, cy, cz);
        this.J_1907_R(matrix, maxX, minY, maxZ, cx, cy, cz);
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(D_1098_v matrix, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2) {
        Z_2491_A c = new Z_2491_A((x0 + x1 + x2) / 3.0f, (y0 + y1 + y2) / 3.0f, (z0 + z1 + z2) / 3.0f, 1.0f);
        c.n_1700_B(matrix);
        float cx = c.n_1700_B();
        float cy = c.J_1907_R();
        float cz = c.R_4764_Y();
        if (!this.n_1700_B(4, E_688_b.Y_601_j)) {
            return;
        }
        this.J_1907_R(matrix, x0, y0, z0, cx, cy, cz);
        this.J_1907_R(matrix, x1, y1, z1, cx, cy, cz);
        this.J_1907_R(matrix, x2, y2, z2, cx, cy, cz);
        Y_1740_V.J_1907_R();
    }

    private void J_1907_R(D_1098_v matrix, float x, float y, float z, float cx, float cy, float cz) {
        Z_2491_A v = new Z_2491_A(x, y, z, 1.0f);
        v.n_1700_B(matrix);
        float vx = v.n_1700_B();
        float vy = v.J_1907_R();
        float vz = v.R_4764_Y();
        float dx = vx - cx;
        float dy = vy - cy;
        float dz = vz - cz;
        float len = u_530_F.R_4764_Y(dx * dx + dy * dy + dz * dz);
        if (len > 1.0E-5f) {
            dx /= len;
            dy /= len;
            dz /= len;
        } else {
            dx = 0.0f;
            dy = 1.0f;
            dz = 0.0f;
        }
        float cr = u_530_F.n_1700_B(dx * 0.5f + 0.5f, 0.0f, 1.0f);
        float cg = u_530_F.n_1700_B(dy * 0.5f + 0.5f, 0.0f, 1.0f);
        float cb = u_530_F.n_1700_B(dz * 0.5f + 0.5f, 0.0f, 1.0f);
        A_4115_X.pos(vx, vy, vz).n_1700_B(cr, cg, cb, 1.0f).endVertex();
    }

    private void G_564_y(a_3913_L player, g_221_o ms, n_1658_l<?> biped) {
        ms.n_1700_B();
        biped.R_4764_Y.n_1700_B(ms);
        ms.n_1700_B(0.0, 0.25, 0.35);
        float sc = ((Float)this.Y_601_j.J_1907_R()).floatValue() * 0.0625f;
        ms.n_1700_B(sc * 16.0f, sc * 16.0f, sc * 16.0f);
        float time = (float)(System.currentTimeMillis() % 10000L) / 1000.0f;
        float breathe = 1.0f + u_530_F.n_1700_B(time * 1.8f) * 0.02f;
        ms.n_1700_B(breathe, breathe, breathe);
        float spread = u_530_F.v_4262_N(c_3005_b.P_2565_J(), this.e_4240_b, this.x_607_J);
        float flapY = 0.0f;
        if (player.k_578_l()) {
            flapY = u_530_F.n_1700_B(time * 4.0f) * 0.06f;
        }
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.q_2307_F();
        if (!this.w_1484_f) {
            c_4037_x.N_4405_n();
        } else {
            c_4037_x.t_1786_h();
            c_4037_x.J_1907_R(false);
        }
        c_4037_x.w_1484_f(7425);
        c_4037_x.G_564_y(1.5f);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        float[][] wingShape = this.Y_1740_V();
        int[] featherIdx = this.t_4043_B();
        int baseColor = this.c_3005_b();
        int edgeColor = this.J_1907_R(baseColor);
        int glowColor = this.R_4764_Y(baseColor);
        float baseAlpha = this.x_607_J();
        float glow = ((Float)this.t_4043_B.J_1907_R()).floatValue();
        boolean shaderWings = this.t_1786_h.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440");
        s_4405_m shader = null;
        if (shaderWings && v_3429_e.c_3005_b.Y_601_j != null) {
            shader = this.A_4115_X();
            if (shader != null && shader.n_1700_B()) {
                float pt = c_3005_b.P_2565_J();
                float skyTime = ((float)v_3429_e.c_3005_b.Y_601_j.X_933_l() + pt) * ((Float)j_8_l.Y_601_j.J_1907_R()).floatValue();
                float[] bc = H_2506_c.P_1922_E(baseColor);
                float uAlpha = baseAlpha * bc[3];
                shader.J_1907_R();
                shader.n_1700_B("u_Color", bc[0], bc[1], bc[2], 1.0f);
                shader.n_1700_B("u_Scale", ((Float)j_8_l.w_1457_N.J_1907_R()).floatValue());
                shader.J_1907_R("u_Time", skyTime * 0.08f);
                shader.J_1907_R("u_Alpha", uAlpha);
            } else {
                shaderWings = false;
                shader = null;
            }
        }
        if (glow > 0.0f && !shaderWings) {
            float[][] glowLayers;
            c_4037_x.J_1907_R(false);
            c_4037_x.J_1907_R(770, 1);
            float pulse = 1.0f + u_530_F.n_1700_B(time * 3.0f) * 0.15f;
            for (float[] layer : glowLayers = new float[][]{{1.15f, 0.12f}, {1.3f, 0.06f}, {1.5f, 0.025f}}) {
                float layerScale = layer[0];
                float layerAlpha = layer[1] * glow * pulse;
                ms.n_1700_B();
                ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(spread, 15.0f, 35.0f)));
                if (flapY != 0.0f) {
                    ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(flapY * 30.0f));
                }
                ms.n_1700_B(layerScale, layerScale, layerScale);
                this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, glowColor, layerAlpha, false);
                ms.J_1907_R();
                ms.n_1700_B();
                ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(spread, -15.0f, -35.0f)));
                if (flapY != 0.0f) {
                    ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-flapY * 30.0f));
                }
                ms.n_1700_B(layerScale, layerScale, layerScale);
                this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, glowColor, layerAlpha, true);
                ms.J_1907_R();
            }
            c_4037_x.J_1907_R(true);
            c_4037_x.s_2632_s();
        }
        ms.n_1700_B();
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(spread, 15.0f, 35.0f)));
        if (flapY != 0.0f) {
            ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(flapY * 30.0f));
        }
        if (shaderWings && shader != null) {
            this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, false);
        } else {
            this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, baseColor, edgeColor, baseAlpha, false);
        }
        this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, featherIdx, glowColor, baseAlpha, false);
        this.J_1907_R(ms.R_4764_Y().n_1700_B(), wingShape, glowColor, baseAlpha * 0.6f, false);
        ms.J_1907_R();
        ms.n_1700_B();
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(u_530_F.v_4262_N(spread, -15.0f, -35.0f)));
        if (flapY != 0.0f) {
            ms.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-flapY * 30.0f));
        }
        if (shaderWings && shader != null) {
            this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, true);
        } else {
            this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, baseColor, edgeColor, baseAlpha, true);
        }
        this.n_1700_B(ms.R_4764_Y().n_1700_B(), wingShape, featherIdx, glowColor, baseAlpha, true);
        this.J_1907_R(ms.R_4764_Y().n_1700_B(), wingShape, glowColor, baseAlpha * 0.6f, true);
        ms.J_1907_R();
        if (shaderWings && shader != null) {
            shader.R_4764_Y();
        }
        GL11.glDisable((int)2848);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.w_1484_f(7424);
        ms.J_1907_R();
    }

    private void n_1700_B(D_1098_v mat, float[][] wing, int glowColor, float alpha, boolean mirror) {
        float m = mirror ? -1.0f : 1.0f;
        float[] gc = H_2506_c.P_1922_E(glowColor);
        if (!this.n_1700_B(6, E_688_b.Y_601_j)) {
            return;
        }
        A_4115_X.n_1700_B(mat, 0.0f, 0.0f, 0.0f).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.8f).endVertex();
        for (float[] p : wing) {
            A_4115_X.n_1700_B(mat, p[0] * m, p[1], p[2]).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.1f).endVertex();
        }
        Y_1740_V.J_1907_R();
    }

    private s_4405_m A_4115_X() {
        boolean wantBalatro = this.w_1457_N.J_1907_R("Plasma") ? false : (this.w_1457_N.J_1907_R("Balatro") ? true : j_8_l.t_1786_h != null && j_8_l.t_1786_h.J_1907_R("Balatro"));
        return wantBalatro ? s_4405_m.g_221_o : s_4405_m.z_4693_k;
    }

    private void n_1700_B(D_1098_v mat, float[][] wing, boolean mirror) {
        float m = mirror ? -1.0f : 1.0f;
        Z_2491_A c = new Z_2491_A(0.0f, 0.0f, 0.0f, 1.0f);
        c.n_1700_B(mat);
        float cx = c.n_1700_B();
        float cy = c.J_1907_R();
        float cz = c.R_4764_Y();
        if (!this.n_1700_B(6, E_688_b.Y_601_j)) {
            return;
        }
        this.J_1907_R(mat, 0.0f, 0.0f, 0.0f, cx, cy, cz);
        for (float[] p : wing) {
            this.J_1907_R(mat, p[0] * m, p[1], p[2], cx, cy, cz);
        }
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(D_1098_v mat, float[][] wing, int baseColor, int edgeColor, float alpha, boolean mirror) {
        float m = mirror ? -1.0f : 1.0f;
        float[] bc = H_2506_c.P_1922_E(baseColor);
        float[] ec = H_2506_c.P_1922_E(edgeColor);
        if (!this.n_1700_B(6, E_688_b.Y_601_j)) {
            return;
        }
        A_4115_X.n_1700_B(mat, 0.0f, 0.0f, 0.0f).n_1700_B(bc[0], bc[1], bc[2], alpha * bc[3]).endVertex();
        for (float[] p : wing) {
            float dist = (float)Math.sqrt(p[0] * p[0] + p[1] * p[1] + p[2] * p[2]);
            float t = Math.min(dist / 0.7f, 1.0f);
            float r = u_530_F.v_4262_N(t, bc[0], ec[0]);
            float g = u_530_F.v_4262_N(t, bc[1], ec[1]);
            float b = u_530_F.v_4262_N(t, bc[2], ec[2]);
            float a = alpha * u_530_F.v_4262_N(t, bc[3], ec[3] * 0.6f);
            A_4115_X.n_1700_B(mat, p[0] * m, p[1], p[2]).n_1700_B(r, g, b, a).endVertex();
        }
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(D_1098_v mat, float[][] wing, int[] indices, int glowColor, float alpha, boolean mirror) {
        float m = mirror ? -1.0f : 1.0f;
        float[] gc = H_2506_c.P_1922_E(glowColor);
        if (!this.n_1700_B(1, E_688_b.Y_601_j)) {
            return;
        }
        for (int idx : indices) {
            if (idx >= wing.length) continue;
            float[] p = wing[idx];
            A_4115_X.n_1700_B(mat, 0.0f, 0.0f, 0.0f).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.8f).endVertex();
            A_4115_X.n_1700_B(mat, p[0] * m, p[1], p[2]).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.15f).endVertex();
        }
        Y_1740_V.J_1907_R();
    }

    private void J_1907_R(D_1098_v mat, float[][] wing, int glowColor, float alpha, boolean mirror) {
        float m = mirror ? -1.0f : 1.0f;
        float[] gc = H_2506_c.P_1922_E(glowColor);
        c_4037_x.G_564_y(2.5f);
        if (!this.n_1700_B(3, E_688_b.Y_601_j)) {
            return;
        }
        A_4115_X.n_1700_B(mat, 0.0f, 0.0f, 0.0f).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.5f).endVertex();
        for (float[] p : wing) {
            A_4115_X.n_1700_B(mat, p[0] * m, p[1], p[2]).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.3f).endVertex();
        }
        A_4115_X.n_1700_B(mat, 0.0f, 0.0f, 0.0f).n_1700_B(gc[0], gc[1], gc[2], alpha * 0.5f).endVertex();
        Y_1740_V.J_1907_R();
        c_4037_x.G_564_y(1.5f);
    }

    private boolean n_1700_B(int glMode, b_1213_w format) {
        if (A_4115_X.s_956_w()) {
            try {
                Y_1740_V.J_1907_R();
            }
            catch (Throwable ignored) {
                A_4115_X.w_1484_f();
            }
            if (A_4115_X.s_956_w()) {
                A_4115_X.w_1484_f();
            }
        }
        A_4115_X.n_1700_B(glMode, format);
        return true;
    }

    private float[][] Y_1740_V() {
        return switch ((String)this.M_182_A.J_1907_R()) {
            case "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435" -> d_2427_y;
            case "\u0424\u0430\u043d\u0442\u043e\u043c\u043d\u044b\u0435" -> z_1737_N;
            default -> n_3318_d;
        };
    }

    private int[] t_4043_B() {
        return switch ((String)this.M_182_A.J_1907_R()) {
            case "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435" -> d_2461_k;
            case "\u0424\u0430\u043d\u0442\u043e\u043c\u043d\u044b\u0435" -> G_624_v;
            default -> v_4276_D;
        };
    }

    private int J_1907_R(int base) {
        return switch ((String)this.M_182_A.J_1907_R()) {
            case "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435" -> H_2506_c.n_1700_B(H_2506_c.J_1907_R(base, 0.3f), 0.9f);
            case "\u0424\u0430\u043d\u0442\u043e\u043c\u043d\u044b\u0435" -> H_2506_c.n_1700_B(H_2506_c.J_1907_R(base, 1.4f), 0.5f);
            default -> H_2506_c.n_1700_B(H_2506_c.J_1907_R(base, 1.2f), 0.8f);
        };
    }

    private int R_4764_Y(int base) {
        return switch ((String)this.M_182_A.J_1907_R()) {
            case "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435" -> H_2506_c.J_1907_R(base, 1.5f);
            case "\u0424\u0430\u043d\u0442\u043e\u043c\u043d\u044b\u0435" -> H_2506_c.J_1907_R(base, 1.6f);
            default -> H_2506_c.J_1907_R(base, 1.3f);
        };
    }

    private float x_607_J() {
        return switch ((String)this.M_182_A.J_1907_R()) {
            case "\u0424\u0430\u043d\u0442\u043e\u043c\u043d\u044b\u0435" -> 0.4f;
            case "\u0414\u0435\u043c\u043e\u043d\u0438\u0447\u0435\u0441\u043a\u0438\u0435" -> 0.85f;
            default -> 0.75f;
        };
    }

    static {
        n_3318_d = new float[][]{{0.06f, 0.32f, -0.01f}, {0.18f, 0.4f, -0.03f}, {0.34f, 0.38f, -0.06f}, {0.5f, 0.28f, -0.09f}, {0.62f, 0.14f, -0.11f}, {0.68f, -0.02f, -0.12f}, {0.6f, -0.16f, -0.1f}, {0.46f, -0.26f, -0.07f}, {0.3f, -0.3f, -0.04f}, {0.16f, -0.24f, -0.02f}, {0.06f, -0.14f, 0.0f}};
        d_2427_y = new float[][]{{0.06f, 0.38f, -0.01f}, {0.28f, 0.52f, -0.04f}, {0.22f, 0.36f, -0.06f}, {0.48f, 0.44f, -0.09f}, {0.38f, 0.26f, -0.11f}, {0.66f, 0.3f, -0.13f}, {0.56f, 0.1f, -0.14f}, {0.74f, 0.02f, -0.15f}, {0.58f, -0.12f, -0.12f}, {0.4f, -0.22f, -0.08f}, {0.22f, -0.24f, -0.04f}, {0.08f, -0.14f, -0.01f}};
        z_1737_N = new float[][]{{0.08f, 0.28f, -0.02f}, {0.22f, 0.36f, -0.04f}, {0.4f, 0.32f, -0.08f}, {0.56f, 0.22f, -0.11f}, {0.7f, 0.08f, -0.14f}, {0.74f, -0.06f, -0.15f}, {0.66f, -0.18f, -0.13f}, {0.5f, -0.28f, -0.1f}, {0.34f, -0.32f, -0.06f}, {0.18f, -0.28f, -0.03f}, {0.06f, -0.16f, -0.01f}};
        v_4276_D = new int[]{1, 3, 5, 7, 9};
        d_2461_k = new int[]{1, 3, 5, 7};
        G_624_v = new int[]{2, 4, 6, 8};
        T_2506_i = u_530_F.R_4764_Y / 90.0f;
        q_4610_l = new g_2336_b("Pouch/icons/world_render/glow.png");
    }
}

