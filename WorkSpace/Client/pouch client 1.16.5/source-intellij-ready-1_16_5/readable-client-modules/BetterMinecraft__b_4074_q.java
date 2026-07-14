/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lightning.product.F_1573_j;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.H_274_C;
import lightning.product.I_686_h;
import lightning.product.J_4125_o;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.Q_2753_H;
import lightning.product.W_4328_U;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_2812_M;
import lightning.product.Z_4720_K;
import lightning.product.a_3913_L;
import lightning.product.b_2152_i;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_2739_B;
import lightning.product.i_4482_j;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.q_3401_q;
import lightning.product.q_366_O;
import lightning.product.r_3979_X;
import lightning.product.r_4811_B;
import lightning.product.t_3138_Z;
import lightning.product.v_2826_q;
import lightning.product.y_2603_k;
import lightning.product.z_2025_Z;
import lightning.product.z_4547_I;
import lombok.Generated;
import org.joml.Vector2f;

public class b_4074_q
extends X_3546_T
implements b_2152_i {
    private final Map<r_4811_B, n_1700_B> w_1484_f = new ConcurrentHashMap<r_4811_B, n_1700_B>();
    private final N_4463_r t_148_a = new N_4463_r("\u0427\u0430\u0442 \u0423\u0442\u0438\u043b\u0438\u0442\u044b", new p_1977_n("\u0423\u043b\u0443\u0447\u0448\u0435\u043d\u043d\u044b\u0439 \u0432\u044b\u0432\u043e\u0434", false), new p_1977_n("\u0423\u0431\u0440\u0430\u0442\u044c \u0441\u043f\u0430\u043c", false), new p_1977_n("\u0418\u0441\u0442\u043e\u0440\u0438\u044f \u0447\u0430\u0442\u0430", false));
    private final p_1977_n s_956_w = new p_1977_n("UwU \u0427\u0430\u0442", false);
    private final Random u_2550_I = new Random();
    private static final String[] M_588_G = new String[]{"UwU", "OwO", "owo", "uwu", "Owo", "uWu", "x3", "xd", "xD", "o3o"};
    private static final String[] P_4830_p = new String[]{"nyaa", "nuzzles", "blushes", "hehe", "rawr", "wags tail", "murr", "glomps", "uwu sowwy", "pwease", "OwO whats this", "boops", "haii", "kawaii desu", "purrs", "nya", "senpai noticed me", "wiggles"};
    private boolean h_1847_R = false;
    private final N_4463_r Q_4569_t = new N_4463_r("\u041e\u043f\u0442\u0438\u043c\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u0442\u044c", new p_1977_n("\u0427\u0430\u0441\u0442\u0438\u0446\u044b \u0431\u043b\u043e\u043a\u043e\u0432", false), new p_1977_n("\u0411\u0443\u0444\u0435\u0440 \u0442\u0430\u0431\u0430", true));
    private final p_1977_n M_182_A = new p_1977_n("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u043a\u0430\u043c\u0435\u0440\u044b", false);
    private final N_4463_r t_1786_h = new N_4463_r("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u0432\u044b\u0441\u043e\u0442\u044b", () -> this.M_182_A.t_148_a(), new p_1977_n("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", false), new p_1977_n("\u041d\u0430 \u0437\u0435\u043c\u043b\u0435", false));
    private final I_686_h N_4405_n = new I_686_h("\u0412\u044b\u0441\u043e\u0442\u0430 \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a() != false && this.t_1786_h.J_1907_R("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435") != null && this.t_1786_h.J_1907_R("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435") != false);
    private final I_686_h w_1457_N = new I_686_h("\u0412\u044b\u0441\u043e\u0442\u0430 \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a() != false && this.t_1786_h.J_1907_R("\u041d\u0430 \u0437\u0435\u043c\u043b\u0435") != null && this.t_1786_h.J_1907_R("\u041d\u0430 \u0437\u0435\u043c\u043b\u0435") != false);
    private final I_686_h Y_601_j = new I_686_h("\u0428\u0438\u0440\u0438\u043d\u0430 X \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a());
    private final I_686_h Y_259_p = new I_686_h("\u0428\u0438\u0440\u0438\u043d\u0430 Z \u043d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a());
    private final I_686_h Q_2552_b = new I_686_h("\u0428\u0438\u0440\u0438\u043d\u0430 X \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a());
    private final I_686_h C_2741_M = new I_686_h("\u0428\u0438\u0440\u0438\u043d\u0430 Z \u043d\u0430 \u0437\u0435\u043c\u043b\u0435", 0.0f, -2.0f, 2.0f, 0.1f, () -> this.M_182_A.t_148_a());
    private final I_686_h k_2293_S = new I_686_h("\u0423\u0440\u043e\u0432\u0435\u043d\u044c \u0437\u0443\u043c\u0430", 4.0f, 1.0f, 10.0f, 0.1f);
    private final p_1977_n q_2307_F = new p_1977_n("\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0432\u044b\u0445\u043e\u0434\u0430 \u0432 \u0431\u043e\u044e", true);
    private final p_1977_n Z_875_P = new p_1977_n("\u041a\u043d\u043e\u043f\u043a\u0430 Reconnect", true);
    private final I_686_h t_4043_B = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0440\u0435\u043a\u043e\u043d\u043d\u0435\u043a\u0442\u0430", 3.0f, 0.0f, 10.0f, 0.5f, () -> this.Z_875_P.t_148_a());
    private final p_1977_n x_607_J = new p_1977_n("3D \u0417\u0432\u0443\u043a", false);
    private final I_686_h e_4240_b = new I_686_h("\u0423\u0441\u0438\u043b\u0435\u043d\u0438\u0435 \u044d\u043c\u0431\u0438\u0435\u043d\u0442\u0430", 2.5f, 1.0f, 5.0f, 0.1f, () -> this.x_607_J.t_148_a());
    private final I_686_h n_3318_d = new I_686_h("\u0423\u0441\u0438\u043b\u0435\u043d\u0438\u0435 \u0448\u0430\u0433\u043e\u0432", 1.5f, 1.0f, 5.0f, 0.1f, () -> this.x_607_J.t_148_a());
    private final I_686_h d_2427_y = new I_686_h("\u0423\u0441\u0438\u043b\u0435\u043d\u0438\u0435 \u043c\u043e\u0431\u043e\u0432", 2.0f, 1.0f, 5.0f, 0.1f, () -> this.x_607_J.t_148_a());
    private final p_1977_n z_1737_N = new p_1977_n("BetterF3", false);
    private final p_1977_n v_4276_D = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0447\u0442\u043e \u043a\u0443\u0448\u0430\u0435\u0442/\u043f\u044c\u0435\u0442", false);
    private final p_1977_n d_2461_k = new p_1977_n("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u043a\u043e\u0433\u0434\u0430 \u043c\u0430\u043b\u043e \u0445\u043f", false);
    private final I_686_h G_624_v = new I_686_h("\u041f\u043e\u0440\u043e\u0433 \u0445\u043f", 6.0f, 1.0f, 20.0f, 0.5f, () -> this.d_2461_k.t_148_a());
    private final I_686_h T_2506_i = new I_686_h("\u0418\u043d\u0442\u0435\u043d\u0441\u0438\u0432\u043d\u043e\u0441\u0442\u044c", 0.5f, 0.1f, 1.0f, 0.05f, () -> this.d_2461_k.t_148_a());
    private final N_4463_r q_4610_l = new N_4463_r("\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u0438", new p_1977_n("\u0421\u043f\u0438\u0441\u043e\u043a \u0438\u0433\u0440\u043e\u043a\u043e\u0432", false), new p_1977_n("\u0418\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", false), new p_1977_n("\u041f\u0440\u0438\u0431\u043b\u0438\u0436\u0435\u043d\u0438\u0435 \u043a\u0430\u043c\u0435\u0440\u044b", false), new p_1977_n("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432", false), new p_1977_n("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u043f\u0435\u0440\u0441\u043f\u0435\u043a\u0442\u0438\u0432\u044b", false));
    private final q_366_O z_4693_k = new q_366_O("\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u044f \u0447\u0430\u043d\u043a\u043e\u0432", "Quart", () -> this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432") != null && this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432") != false, "Quart", "Circ", "Sine", "Cubic");
    private final I_686_h g_221_o = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 6.0f, 2.0f, 10.0f, 1.0f, () -> this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432") != null && this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432") != false);
    private final p_1977_n e_2887_G = new p_1977_n("\u041f\u043e\u0434\u043c\u0435\u043d\u044f\u0442\u044c \u0440\u043e\u0442\u0430\u0446\u0438\u044e", false);
    private final q_366_O B_1668_F = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "360 \u043f\u043e\u0441\u043b\u0435 \u0443\u0434\u0430\u0440\u0430", () -> this.e_2887_G.t_148_a(), "360 \u043f\u043e\u0441\u043b\u0435 \u0443\u0434\u0430\u0440\u0430", "\u0412\u0435\u0440\u0442\u043e\u043b\u0451\u0442", "\u041a\u0430\u0447\u0435\u043b\u0438", "\u0414\u0451\u0440\u0433\u0430\u043d\u044b\u0439", "\u041d\u0430\u0437\u0430\u0434", "\u042d\u043f\u0438\u043b\u0435\u043f\u0441\u0438\u044f", "\u0414\u0435\u043c\u043e\u043d", "\u0417\u043c\u0435\u0439\u043a\u0430");
    private final I_686_h g_164_R = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 30.0f, 5.0f, 100.0f, 1.0f, () -> this.e_2887_G.t_148_a() != false && !((String)this.B_1668_F.J_1907_R()).equals("\u041d\u0430\u0437\u0430\u0434"));
    private final I_686_h X_933_l = new I_686_h("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c 360", 200.0f, 50.0f, 500.0f, 10.0f, () -> this.e_2887_G.t_148_a() != false && ((String)this.B_1668_F.J_1907_R()).equals("360 \u043f\u043e\u0441\u043b\u0435 \u0443\u0434\u0430\u0440\u0430"));
    private final I_686_h Z_976_R = new I_686_h("\u0410\u043c\u043f\u043b\u0438\u0442\u0443\u0434\u0430", 90.0f, 30.0f, 180.0f, 5.0f, () -> this.e_2887_G.t_148_a() != false && (((String)this.B_1668_F.J_1907_R()).equals("\u041a\u0430\u0447\u0435\u043b\u0438") || ((String)this.B_1668_F.J_1907_R()).equals("\u0414\u0451\u0440\u0433\u0430\u043d\u044b\u0439") || ((String)this.B_1668_F.J_1907_R()).equals("\u0417\u043c\u0435\u0439\u043a\u0430")));
    private final p_1977_n H_1990_U = new p_1977_n("\u0424\u0435\u0439\u043a Pitch", false, () -> this.e_2887_G.t_148_a());
    private final I_686_h N_2525_X = new I_686_h("Pitch \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435", 80.0f, -90.0f, 90.0f, 5.0f, () -> this.e_2887_G.t_148_a() != false && this.H_1990_U.t_148_a() != false);
    private final p_1977_n c_4037_x = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 F5", true, () -> this.e_2887_G.t_148_a());
    public static boolean v_4262_N = false;
    private long g_2268_R = 0L;
    private boolean T_3594_S = false;
    private float D_4792_h = 0.0f;
    private float s_2632_s = 0.0f;
    private float l_1233_K = 0.0f;
    private float z_1333_t = 0.0f;
    private long O_508_d = 0L;
    private float r_715_M = 0.0f;
    private float A_1038_p = 0.0f;
    private final WeakHashMap<z_4547_I.n_1700_B, AtomicLong> i_1637_u = new WeakHashMap();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void h_1847_R() {
        WeakHashMap<z_4547_I.n_1700_B, AtomicLong> weakHashMap = this.i_1637_u;
        synchronized (weakHashMap) {
            this.i_1637_u.size();
        }
    }

    public b_4074_q() {
        super("BetterMinecraft", y_2603_k.P_1922_E);
        this.n_1700_B(this.t_148_a, this.s_956_w, this.Q_4569_t, this.e_2887_G, this.B_1668_F, this.g_164_R, this.X_933_l, this.Z_976_R, this.H_1990_U, this.N_2525_X, this.c_4037_x, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B, this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, this.z_1737_N, this.v_4276_D, this.d_2461_k, this.G_624_v, this.T_2506_i, this.q_4610_l, this.z_4693_k, this.g_221_o);
    }

    public boolean Q_4569_t() {
        return this.w_1484_f() && this.z_1737_N.t_148_a() != false;
    }

    public boolean M_182_A() {
        return this.w_1484_f() && Boolean.TRUE.equals(this.Q_4569_t.J_1907_R("\u0411\u0443\u0444\u0435\u0440 \u0442\u0430\u0431\u0430"));
    }

    public float t_1786_h() {
        float threshold;
        if (!this.w_1484_f() || !this.d_2461_k.t_148_a().booleanValue() || b_4074_q.c_3005_b.Y_259_p == null) {
            return 0.0f;
        }
        float currentHp = b_4074_q.c_3005_b.Y_259_p.g_46_E();
        if (currentHp >= (threshold = ((Float)this.G_624_v.J_1907_R()).floatValue())) {
            return 0.0f;
        }
        float intensity = 1.0f - currentHp / threshold;
        return intensity * ((Float)this.T_2506_i.J_1907_R()).floatValue();
    }

    public boolean N_4405_n() {
        return this.w_1484_f() && this.d_2461_k.t_148_a() != false;
    }

    private double n_1700_B(double t) {
        String modeName = (String)this.z_4693_k.J_1907_R();
        H_274_C easing = "Circ".equalsIgnoreCase(modeName) ? i_4482_j.C_2741_M : ("Sine".equalsIgnoreCase(modeName) ? i_4482_j.Y_601_j : ("Cubic".equalsIgnoreCase(modeName) ? i_4482_j.u_2550_I : i_4482_j.h_1847_R));
        return easing.ease(t);
    }

    @Y_1740_V
    public void n_1700_B(q_3401_q event) {
        boolean isGround;
        if (!this.w_1484_f() || b_4074_q.c_3005_b.Y_259_p == null || !this.M_182_A.t_148_a().booleanValue()) {
            return;
        }
        if (b_4074_q.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
            return;
        }
        Boolean elytraEnabled = this.t_1786_h.J_1907_R("\u041d\u0430 \u044d\u043b\u0438\u0442\u0440\u0435");
        Boolean groundEnabled = this.t_1786_h.J_1907_R("\u041d\u0430 \u0437\u0435\u043c\u043b\u0435");
        if (elytraEnabled == null && groundEnabled == null && ((Float)this.Y_601_j.J_1907_R()).floatValue() == 0.0f && ((Float)this.Y_259_p.J_1907_R()).floatValue() == 0.0f && ((Float)this.Q_2552_b.J_1907_R()).floatValue() == 0.0f && ((Float)this.C_2741_M.J_1907_R()).floatValue() == 0.0f) {
            return;
        }
        e_2866_D position = event.J_1907_R();
        double heightOffset = 0.0;
        double widthOffsetX = 0.0;
        double widthOffsetZ = 0.0;
        boolean isElytra = b_4074_q.c_3005_b.Y_259_p.k_578_l();
        boolean bl = isGround = !isElytra;
        if (isElytra) {
            if (elytraEnabled != null && elytraEnabled.booleanValue()) {
                heightOffset = ((Float)this.N_4405_n.J_1907_R()).floatValue();
            }
            widthOffsetX = ((Float)this.Y_601_j.J_1907_R()).floatValue();
            widthOffsetZ = ((Float)this.Y_259_p.J_1907_R()).floatValue();
        } else if (isGround) {
            if (groundEnabled != null && groundEnabled.booleanValue()) {
                heightOffset = ((Float)this.w_1457_N.J_1907_R()).floatValue();
            }
            widthOffsetX = ((Float)this.Q_2552_b.J_1907_R()).floatValue();
            widthOffsetZ = ((Float)this.C_2741_M.J_1907_R()).floatValue();
        }
        if (heightOffset != 0.0 || widthOffsetX != 0.0 || widthOffsetZ != 0.0) {
            event.n_1700_B(new e_2866_D(position.J_1907_R + widthOffsetX, position.R_4764_Y + heightOffset, position.G_564_y + widthOffsetZ));
        }
    }

    public float w_1457_N() {
        if (!this.w_1484_f()) {
            return 4.0f;
        }
        return ((Float)this.k_2293_S.J_1907_R()).floatValue();
    }

    public boolean Y_601_j() {
        if (!this.w_1484_f() || !this.q_2307_F.t_148_a().booleanValue()) {
            return false;
        }
        if (b_4074_q.c_3005_b.Y_259_p == null || b_4074_q.c_3005_b.Y_601_j == null) {
            return false;
        }
        return q_3115_L.n_1700_B();
    }

    @Y_1740_V
    private void n_1700_B(Z_2812_M e) {
        if (!this.w_1484_f()) {
            return;
        }
        if (!Boolean.TRUE.equals(this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432"))) {
            return;
        }
        if (b_4074_q.c_3005_b.Y_259_p != null && b_4074_q.c_3005_b.Y_601_j != null && !this.i_1637_u.containsKey(e.J_1907_R())) {
            this.i_1637_u.put(e.J_1907_R(), new AtomicLong(-1L));
        }
    }

    @Y_1740_V
    private void n_1700_B(J_4125_o e) {
        if (!this.w_1484_f()) {
            return;
        }
        if (!Boolean.TRUE.equals(this.q_4610_l.J_1907_R("\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 \u0447\u0430\u043d\u043a\u043e\u0432"))) {
            return;
        }
        if (this.i_1637_u.containsKey(e.J_1907_R())) {
            double durationMs;
            long timeDifference;
            AtomicLong timeAlive = this.i_1637_u.get(e.J_1907_R());
            long timeClone = timeAlive.get();
            if (timeClone == -1L) {
                timeClone = System.currentTimeMillis();
                timeAlive.set(timeClone);
            }
            if ((double)(timeDifference = System.currentTimeMillis() - timeClone) <= (durationMs = (double)(((Float)this.g_221_o.J_1907_R()).floatValue() * 100.0f))) {
                double chunkY = e.J_1907_R().P_1922_E().getY();
                double t = (double)timeDifference / durationMs;
                double offsetY = chunkY * this.n_1700_B(t);
                lightning.product.c_4037_x.J_1907_R(0.0, -chunkY + offsetY, 0.0);
            }
        }
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        if (!(this.w_1484_f() && this.s_956_w.t_148_a().booleanValue() && e.R_4764_Y())) {
            return;
        }
        if (this.h_1847_R) {
            return;
        }
        t_3138_Z<?> t_3138_Z2 = e.G_564_y();
        if (t_3138_Z2 instanceof W_4328_U) {
            W_4328_U chatPacket = (W_4328_U)t_3138_Z2;
            String message = chatPacket.J_1907_R();
            if (message.startsWith("/")) {
                return;
            }
            String uwuMessage = this.R_4764_Y(message);
            e.n_1700_B(true);
            this.h_1847_R = true;
            b_4074_q.c_3005_b.Y_259_p.n_1700_B(uwuMessage);
            this.h_1847_R = false;
        }
    }

    @Y_1740_V
    private void n_1700_B(b_3528_u.J_1907_R e) {
        if (!this.w_1484_f() || !this.v_4276_D.t_148_a().booleanValue() || b_4074_q.c_3005_b.Y_259_p == null || b_4074_q.c_3005_b.Y_601_j == null || b_4074_q.c_3005_b.P_4830_p.r_3651_U) {
            return;
        }
        this.w_1484_f.entrySet().removeIf(entry -> {
            r_4811_B ent = (r_4811_B)entry.getKey();
            if (ent == null || !ent.H_3699_F() || !ent.Y_601_j()) {
                return true;
            }
            Z_1993_T stack = ent.B_2580_P();
            if (stack.n_1700_B()) {
                return true;
            }
            F_1573_j a = stack.M_588_G();
            return a != F_1573_j.J_1907_R && a != F_1573_j.R_4764_Y;
        });
        r_4811_B target = this.c_3005_b();
        if (target == null || !target.H_3699_F() || !target.Y_601_j()) {
            return;
        }
        Z_1993_T activeStack = target.B_2580_P();
        if (activeStack.n_1700_B()) {
            return;
        }
        F_1573_j action = activeStack.M_588_G();
        if (action != F_1573_j.J_1907_R && action != F_1573_j.R_4764_Y) {
            return;
        }
        int useCount = target.U_144_f();
        int maxDuration = activeStack.u_2550_I();
        if (maxDuration <= 0) {
            return;
        }
        n_1700_B prev = this.w_1484_f.get(target);
        if (prev == null || !Z_1993_T.R_4764_Y(prev.n_1700_B, activeStack)) {
            prev = new n_1700_B(activeStack.t_148_a(), maxDuration, System.currentTimeMillis());
            this.w_1484_f.put(target, prev);
        }
        float remainingByCount = (float)useCount / (float)maxDuration;
        float elapsedTicks = (float)(System.currentTimeMillis() - prev.R_4764_Y) / 50.0f;
        float remainingByTime = 1.0f - elapsedTicks / (float)prev.J_1907_R;
        float remaining = Math.max(0.0f, Math.min(1.0f, Math.min(remainingByCount, remainingByTime)));
        e_2866_D targetPos = F_747_P.n_1700_B((N_4263_v)target, e.R_4764_Y());
        Vector2f screenPos = v_2826_q.n_1700_B(targetPos.J_1907_R, targetPos.R_4764_Y + (double)target.v_165_F() * 0.5, targetPos.G_564_y);
        if (screenPos == null || screenPos.x == Float.MAX_VALUE || screenPos.y == Float.MAX_VALUE) {
            return;
        }
        float centerX = screenPos.x;
        float centerY = screenPos.y;
        float bgSize = 22.0f;
        float circleRadius = 10.0f;
        float itemScale = 0.95f;
        int accent = action == F_1573_j.R_4764_Y ? H_2506_c.n_1700_B(80, 170, 255, 255) : H_2506_c.n_1700_B(110, 220, 120, 255);
        F_489_x.n_1700_B(centerX - bgSize / 2.0f, centerY - bgSize / 2.0f, bgSize, bgSize, 9.0f, H_2506_c.n_1700_B(6, 6, 6, 210));
        F_489_x.n_1700_B(centerX, centerY, circleRadius, H_2506_c.n_1700_B(20, 20, 20, 255), 2.2f);
        F_489_x.n_1700_B(e.J_1907_R(), centerX, centerY, circleRadius - 2.2f, circleRadius, -90.0, -90.0 + 360.0 * (double)remaining, accent);
        F_489_x.n_1700_B(activeStack, centerX - 7.6f, centerY - 7.6f, itemScale, false);
    }

    private r_4811_B c_3005_b() {
        r_3979_X aura = (r_3979_X)o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class);
        if (aura != null && aura.w_1484_f() && aura.v_4262_N != null) {
            return aura.v_4262_N;
        }
        a_3913_L nearest = null;
        double bestDist = 1028.0;
        for (a_3913_L a_3913_L2 : b_4074_q.c_3005_b.Y_601_j.N_4405_n()) {
            double d;
            F_1573_j action;
            Z_1993_T stack;
            if (a_3913_L2 == null || a_3913_L2 == b_4074_q.c_3005_b.Y_259_p || !a_3913_L2.H_3699_F() || a_3913_L2.d_2461_k() || !a_3913_L2.Y_601_j() || (stack = a_3913_L2.B_2580_P()).n_1700_B() || (action = stack.M_588_G()) != F_1573_j.J_1907_R && action != F_1573_j.R_4764_Y || !((d = b_4074_q.c_3005_b.Y_259_p.G_564_y((N_4263_v)a_3913_L2)) < bestDist)) continue;
            bestDist = d;
            nearest = a_3913_L2;
        }
        return nearest;
    }

    @Y_1740_V
    private void n_1700_B(Z_4720_K e) {
        if (!this.w_1484_f() || !this.x_607_J.t_148_a().booleanValue()) {
            return;
        }
        String name = e.J_1907_R().u_1723_Y().toString();
        if (name.contains("ambient") || name.contains("cave")) {
            e.n_1700_B(e.R_4764_Y() * ((Float)this.e_4240_b.J_1907_R()).floatValue());
        } else if (name.contains("step") || name.contains("footstep")) {
            e.n_1700_B(e.R_4764_Y() * ((Float)this.n_3318_d.J_1907_R()).floatValue());
        } else if (name.contains("entity.zombie") || name.contains("entity.skeleton") || name.contains("entity.creeper") || name.contains("entity.spider") || name.contains("entity.enderman") || name.contains("entity.witch") || name.contains("entity.phantom") || name.contains("entity.drowned") || name.contains("entity.husk") || name.contains("entity.stray") || name.contains("entity.wither") || name.contains("entity.blaze") || name.contains("entity.ghast") || name.contains("entity.piglin") || name.contains("entity.hoglin") || name.contains("entity.ravager")) {
            e.n_1700_B(e.R_4764_Y() * ((Float)this.d_2427_y.J_1907_R()).floatValue());
        }
    }

    @Y_1740_V
    private void n_1700_B(h_2739_B e) {
        if (!this.w_1484_f() || !this.e_2887_G.t_148_a().booleanValue() || b_4074_q.c_3005_b.Y_259_p == null) {
            return;
        }
        String mode = (String)this.B_1668_F.J_1907_R();
        if (mode.equals("360 \u043f\u043e\u0441\u043b\u0435 \u0443\u0434\u0430\u0440\u0430")) {
            this.g_2268_R = System.currentTimeMillis();
            this.T_3594_S = true;
            this.D_4792_h = b_4074_q.c_3005_b.Y_259_p.p_178_J;
        }
    }

    @Y_1740_V
    private void n_1700_B(z_2025_Z e) {
        v_4262_N = false;
        if (!this.w_1484_f() || !this.e_2887_G.t_148_a().booleanValue() || b_4074_q.c_3005_b.Y_259_p == null) {
            return;
        }
        if (e.n_1700_B() != b_4074_q.c_3005_b.Y_259_p) {
            return;
        }
        if (!this.H_2857_Y()) {
            return;
        }
        if (this.c_4037_x.t_148_a().booleanValue() && b_4074_q.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
            return;
        }
        v_4262_N = true;
        float[] fakeRotations = this.A_4115_X();
        float fakeYaw = fakeRotations[0];
        float fakePitchVal = fakeRotations[1];
        e.J_1907_R(fakeYaw);
        e.R_4764_Y(fakeYaw);
        e.G_564_y(fakeYaw);
        e.P_1922_E(fakeYaw);
        if (this.H_1990_U.t_148_a().booleanValue()) {
            e.u_1723_Y(fakePitchVal);
            e.v_4262_N(fakePitchVal);
            e.w_1484_f(fakePitchVal);
            e.t_148_a(fakePitchVal);
        }
    }

    private boolean H_2857_Y() {
        if (o_148_s.Y_601_j() == null) {
            return false;
        }
        r_3979_X aura = (r_3979_X)o_148_s.Y_601_j().J_1907_R().n_1700_B(r_3979_X.class);
        if (aura == null || !aura.w_1484_f()) {
            return false;
        }
        return aura.v_4262_N != null;
    }

    private float[] A_4115_X() {
        String mode = (String)this.B_1668_F.J_1907_R();
        float baseYaw = b_4074_q.c_3005_b.Y_259_p.p_178_J;
        float basePitch = this.H_1990_U.t_148_a() != false ? ((Float)this.N_2525_X.J_1907_R()).floatValue() : b_4074_q.c_3005_b.Y_259_p.f_4016_n;
        switch (mode) {
            case "360 \u043f\u043e\u0441\u043b\u0435 \u0443\u0434\u0430\u0440\u0430": {
                return new float[]{this.n_1700_B(baseYaw), basePitch};
            }
            case "\u0412\u0435\u0440\u0442\u043e\u043b\u0451\u0442": {
                return this.n_1700_B(baseYaw, basePitch);
            }
            case "\u041a\u0430\u0447\u0435\u043b\u0438": {
                return this.J_1907_R(baseYaw, basePitch);
            }
            case "\u0414\u0451\u0440\u0433\u0430\u043d\u044b\u0439": {
                return this.R_4764_Y(baseYaw, basePitch);
            }
            case "\u041d\u0430\u0437\u0430\u0434": {
                return new float[]{baseYaw + 180.0f, basePitch};
            }
            case "\u042d\u043f\u0438\u043b\u0435\u043f\u0441\u0438\u044f": {
                return this.G_564_y(baseYaw, basePitch);
            }
            case "\u0414\u0435\u043c\u043e\u043d": {
                return this.P_1922_E(baseYaw, basePitch);
            }
            case "\u0417\u043c\u0435\u0439\u043a\u0430": {
                return this.u_1723_Y(baseYaw, basePitch);
            }
        }
        return new float[]{baseYaw, basePitch};
    }

    private float n_1700_B(float baseYaw) {
        float duration;
        if (!this.T_3594_S) {
            return baseYaw;
        }
        long timeSinceAttack = System.currentTimeMillis() - this.g_2268_R;
        if ((float)timeSinceAttack >= (duration = ((Float)this.X_933_l.J_1907_R()).floatValue())) {
            this.T_3594_S = false;
            return baseYaw;
        }
        float progress = (float)timeSinceAttack / duration;
        float easedProgress = (float)i_4482_j.h_1847_R.ease(progress);
        float spinOffset = easedProgress * 360.0f * (((Float)this.g_164_R.J_1907_R()).floatValue() / 30.0f);
        return this.D_4792_h + spinOffset;
    }

    private float[] n_1700_B(float baseYaw, float basePitch) {
        this.s_2632_s += ((Float)this.g_164_R.J_1907_R()).floatValue() * 0.5f;
        if (this.s_2632_s >= 360.0f) {
            this.s_2632_s -= 360.0f;
        }
        return new float[]{this.s_2632_s, basePitch};
    }

    private float[] J_1907_R(float baseYaw, float basePitch) {
        this.l_1233_K += ((Float)this.g_164_R.J_1907_R()).floatValue() * 0.05f;
        float swingOffset = (float)Math.sin(this.l_1233_K) * ((Float)this.Z_976_R.J_1907_R()).floatValue();
        return new float[]{baseYaw + swingOffset, basePitch};
    }

    private float[] R_4764_Y(float baseYaw, float basePitch) {
        float interval;
        long now = System.currentTimeMillis();
        if ((float)(now - this.O_508_d) > (interval = 150.0f - ((Float)this.g_164_R.J_1907_R()).floatValue())) {
            this.z_1333_t = (float)((Math.random() - 0.5) * 2.0 * (double)((Float)this.Z_976_R.J_1907_R()).floatValue());
            this.O_508_d = now;
        }
        return new float[]{baseYaw + this.z_1333_t, basePitch};
    }

    private float[] G_564_y(float baseYaw, float basePitch) {
        float yawShake = (float)((Math.random() - 0.5) * (double)((Float)this.g_164_R.J_1907_R()).floatValue() * 4.0);
        float pitchShake = (float)((Math.random() - 0.5) * (double)((Float)this.g_164_R.J_1907_R()).floatValue() * 2.0);
        float newPitch = this.H_1990_U.t_148_a() != false ? ((Float)this.N_2525_X.J_1907_R()).floatValue() + pitchShake : basePitch + pitchShake;
        newPitch = Math.max(-90.0f, Math.min(90.0f, newPitch));
        return new float[]{baseYaw + yawShake, newPitch};
    }

    private float[] P_1922_E(float baseYaw, float basePitch) {
        this.r_715_M += ((Float)this.g_164_R.J_1907_R()).floatValue() * 0.1f;
        float yawOffset = (float)Math.sin(this.r_715_M) * 180.0f;
        float pitchOffset = (float)Math.sin(this.r_715_M * 0.7f) * 60.0f;
        float newPitch = this.H_1990_U.t_148_a() != false ? ((Float)this.N_2525_X.J_1907_R()).floatValue() + pitchOffset : (pitchOffset += (float)(Math.cos(this.r_715_M * 5.0f) * 20.0));
        newPitch = Math.max(-90.0f, Math.min(90.0f, newPitch));
        return new float[]{baseYaw + (yawOffset += (float)(Math.sin(this.r_715_M * 7.0f) * 30.0)), newPitch};
    }

    private float[] u_1723_Y(float baseYaw, float basePitch) {
        this.A_1038_p += ((Float)this.g_164_R.J_1907_R()).floatValue() * 0.08f;
        float yawOffset = (float)(Math.sin(this.A_1038_p) * (double)((Float)this.Z_976_R.J_1907_R()).floatValue() * 0.5);
        float pitchOffset = (float)(Math.cos(this.A_1038_p * 1.3f) * 25.0);
        float newPitch = this.H_1990_U.t_148_a() != false ? ((Float)this.N_2525_X.J_1907_R()).floatValue() + pitchOffset : basePitch + pitchOffset;
        newPitch = Math.max(-90.0f, Math.min(90.0f, newPitch));
        return new float[]{baseYaw + (yawOffset += (float)(Math.sin(this.A_1038_p * 2.5f) * (double)((Float)this.Z_976_R.J_1907_R()).floatValue() * (double)0.3f)), newPitch};
    }

    private String R_4764_Y(String message) {
        StringBuilder result = new StringBuilder();
        String lower = message.toLowerCase();
        for (int i = 0; i < message.length(); ++i) {
            char c = message.charAt(i);
            char lc = lower.charAt(i);
            if (lc == 'r' || lc == 'l') {
                result.append(Character.isUpperCase(c) ? (char)'W' : 'w');
                continue;
            }
            if (lc == 't' && i + 1 < message.length() && lower.charAt(i + 1) == 'h') {
                result.append(Character.isUpperCase(c) ? (char)'D' : 'd');
                ++i;
                continue;
            }
            if (lc == 'o' && i + 2 < message.length() && lower.charAt(i + 1) == 'v' && lower.charAt(i + 2) == 'e') {
                result.append(Character.isUpperCase(c) ? "UV" : "uv");
                i += 2;
                continue;
            }
            if (lc >= 'a' && lc <= 'z' && (i == 0 || message.charAt(i - 1) == ' ') && this.u_2550_I.nextInt(5) == 0) {
                result.append(c).append(c);
                continue;
            }
            result.append(c);
        }
        result.append(' ').append(M_588_G[this.u_2550_I.nextInt(M_588_G.length)]);
        result.append(' ').append(P_4830_p[this.u_2550_I.nextInt(P_4830_p.length)]);
        if (this.u_2550_I.nextInt(3) == 0) {
            result.append(' ').append(P_4830_p[this.u_2550_I.nextInt(P_4830_p.length)]);
        }
        return result.toString();
    }

    @Generated
    public N_4463_r Y_259_p() {
        return this.t_148_a;
    }

    @Generated
    public N_4463_r Q_2552_b() {
        return this.Q_4569_t;
    }

    @Generated
    public N_4463_r C_2741_M() {
        return this.t_1786_h;
    }

    @Generated
    public p_1977_n k_2293_S() {
        return this.Z_875_P;
    }

    @Generated
    public I_686_h q_2307_F() {
        return this.t_4043_B;
    }

    @Generated
    public N_4463_r Z_875_P() {
        return this.q_4610_l;
    }

    private static class n_1700_B {
        final Z_1993_T n_1700_B;
        final int J_1907_R;
        final long R_4764_Y;

        n_1700_B(Z_1993_T stack, int maxDuration, long startTime) {
            this.n_1700_B = stack;
            this.J_1907_R = maxDuration;
            this.R_4764_Y = startTime;
        }
    }
}

