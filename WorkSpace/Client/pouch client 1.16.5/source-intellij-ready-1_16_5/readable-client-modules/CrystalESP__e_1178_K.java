/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL13
 */
package lightning.product;

import java.awt.Color;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.V_3354_l;
import lightning.product.X_3546_T;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.Z_2049_e;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.h_2367_h;
import lightning.product.k_1366_K;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.s_4405_m;
import lightning.product.v_2826_q;
import lightning.product.y_2603_k;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL13;

public class e_1178_K
extends X_3546_T {
    private final q_366_O M_588_G = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Shader", "Flat", "Chams", "Shader");
    private final p_1977_n P_4830_p = new p_1977_n("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, () -> this.M_588_G.J_1907_R("Shader"));
    private final h_2367_h h_1847_R = new h_2367_h("\u0426\u0432\u0435\u0442 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", true, H_2506_c.n_1700_B(255, 0, 255, 255), () -> this.M_588_G.J_1907_R("Shader") && this.P_4830_p.t_148_a() != false);
    private final I_686_h Q_4569_t = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 6.0f, 1.0f, 12.0f, 1.0f, () -> this.M_588_G.J_1907_R("Shader") && this.P_4830_p.t_148_a() != false);
    private final p_1977_n M_182_A = new p_1977_n("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", false, () -> this.M_588_G.J_1907_R("Shader") && this.P_4830_p.t_148_a() != false);
    private final p_1977_n t_1786_h = new p_1977_n("3D \u0411\u043e\u043a\u0441", true);
    private final h_2367_h N_4405_n = new h_2367_h("\u0426\u0432\u0435\u0442 \u0431\u043e\u043a\u0441\u0430", true, H_2506_c.n_1700_B(255, 0, 255, 120), this.t_1786_h::t_148_a);
    private final I_686_h w_1457_N = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440 \u0431\u043e\u043a\u0441\u0430", 0.0f, -0.2f, 0.2f, 0.01f, this.t_1786_h::t_148_a);
    private final p_1977_n Y_601_j = new p_1977_n("\u0417\u0430\u043b\u0438\u0432\u043a\u0430", true, this.t_1786_h::t_148_a);
    private final p_1977_n Y_259_p = new p_1977_n("\u041e\u043a\u0440\u0430\u0448\u0438\u0432\u0430\u043d\u0438\u0435", true, () -> this.M_588_G.J_1907_R("Chams"));
    private final h_2367_h Q_2552_b = new h_2367_h("\u0426\u0432\u0435\u0442 \u043e\u043a\u0440\u0430\u0448\u0438\u0432\u0430\u043d\u0438\u044f", true, H_2506_c.n_1700_B(255, 0, 255, 255), () -> this.M_588_G.J_1907_R("Chams") || this.M_588_G.J_1907_R("Flat"));
    public k_1366_K v_4262_N;
    public k_1366_K w_1484_f;
    public k_1366_K t_148_a;
    public k_1366_K s_956_w;
    public k_1366_K u_2550_I;

    public e_1178_K() {
        super("CrystalESP", y_2603_k.R_4764_Y);
        this.n_1700_B(this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b);
    }

    public void h_1847_R() {
        this.v_4262_N = k_1366_K.J_1907_R(this.v_4262_N);
        this.w_1484_f = k_1366_K.J_1907_R(this.w_1484_f);
        this.t_148_a = k_1366_K.J_1907_R(this.t_148_a);
        this.s_956_w = k_1366_K.J_1907_R(this.s_956_w);
        this.u_2550_I = k_1366_K.J_1907_R(this.u_2550_I);
    }

    private boolean Q_4569_t() {
        for (V_3354_l crystal : e_1178_K.c_3005_b.Y_601_j.n_1700_B(V_3354_l.class, e_1178_K.c_3005_b.Y_259_p.i_601_W().grow(64.0))) {
            if (crystal == null || !crystal.H_3699_F() || !v_2826_q.n_1700_B(crystal)) continue;
            return true;
        }
        return false;
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        boolean useColorFill;
        if (e_1178_K.c_3005_b.Y_601_j == null || e_1178_K.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.Q_4569_t()) {
            return;
        }
        if (this.t_1786_h.t_148_a().booleanValue()) {
            this.n_1700_B(e.J_1907_R());
        }
        boolean useGlow = this.M_588_G.J_1907_R("Shader") && this.P_4830_p.t_148_a() != false;
        boolean bl = useColorFill = (this.M_588_G.J_1907_R("Chams") || this.M_588_G.J_1907_R("Flat")) && this.Y_259_p.t_148_a() != false;
        if (useGlow || useColorFill) {
            this.h_1847_R();
            this.v_4262_N.n_1700_B(true);
            this.J_1907_R(e.J_1907_R());
            this.v_4262_N.t_148_a();
            c_3005_b.G_564_y().J_1907_R(true);
            X_933_l.v_4262_N();
        }
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        boolean useFlatMode;
        if (e_1178_K.c_3005_b.Y_601_j == null || e_1178_K.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.Q_4569_t()) {
            return;
        }
        boolean useGlow = this.M_588_G.J_1907_R("Shader") && this.P_4830_p.t_148_a() != false;
        boolean useChamsMode = this.M_588_G.J_1907_R("Chams") && this.Y_259_p.t_148_a() != false;
        boolean bl = useFlatMode = this.M_588_G.J_1907_R("Flat") && this.Y_259_p.t_148_a() != false;
        if (!(useGlow || useChamsMode || useFlatMode)) {
            return;
        }
        if (this.v_4262_N == null) {
            return;
        }
        if (useGlow && this.w_1484_f != null && this.t_148_a != null) {
            this.t_1786_h();
            this.w_1484_f.n_1700_B(true);
            s_4405_m.P_4830_p.J_1907_R();
            this.n_1700_B(0.0f, 1.0f);
            TextureUtils.bindTexture(this.v_4262_N.v_4262_N);
            k_1366_K.R_4764_Y();
            this.n_1700_B(1.0f, 0.0f);
            TextureUtils.bindTexture(this.v_4262_N.v_4262_N);
            k_1366_K.R_4764_Y();
            s_4405_m.P_4830_p.R_4764_Y();
            this.w_1484_f.t_148_a();
            this.t_148_a.n_1700_B(true);
            s_4405_m.h_1847_R.J_1907_R();
            this.J_1907_R(1.0f, 0.0f);
            TextureUtils.bindTexture(this.w_1484_f.v_4262_N);
            k_1366_K.R_4764_Y();
            this.t_148_a.t_148_a();
            c_3005_b.G_564_y().J_1907_R(true);
            this.J_1907_R(0.0f, 1.0f);
            GL13.glActiveTexture((int)33984);
            TextureUtils.bindTexture(this.t_148_a.v_4262_N);
            k_1366_K.R_4764_Y();
            s_4405_m.h_1847_R.R_4764_Y();
            if (this.M_182_A.t_148_a().booleanValue()) {
                this.s_956_w.n_1700_B(true);
                s_4405_m.Q_4569_t.J_1907_R();
                this.M_182_A();
                TextureUtils.bindTexture(this.v_4262_N.v_4262_N);
                k_1366_K.R_4764_Y();
                s_4405_m.Q_4569_t.R_4764_Y();
                this.s_956_w.t_148_a();
                c_3005_b.G_564_y().J_1907_R(true);
                TextureUtils.bindTexture(this.s_956_w.v_4262_N);
                k_1366_K.R_4764_Y();
            }
            this.N_4405_n();
        }
        if (useChamsMode || useFlatMode) {
            this.t_1786_h();
            c_3005_b.G_564_y().J_1907_R(true);
            s_4405_m.w_1457_N.J_1907_R();
            this.P_1922_E(useFlatMode);
            GL13.glActiveTexture((int)33984);
            TextureUtils.bindTexture(this.v_4262_N.v_4262_N);
            k_1366_K.R_4764_Y();
            s_4405_m.w_1457_N.R_4764_Y();
            this.N_4405_n();
        }
    }

    private void n_1700_B(float partialTicks) {
        e_2866_D view = e_1178_K.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(-view.J_1907_R, -view.R_4764_Y, -view.G_564_y);
        for (V_3354_l crystal : e_1178_K.c_3005_b.Y_601_j.n_1700_B(V_3354_l.class, e_1178_K.c_3005_b.Y_259_p.i_601_W().grow(64.0))) {
            if (crystal == null || !crystal.H_3699_F() || !v_2826_q.n_1700_B(crystal)) continue;
            double interpX = crystal.q_1982_R + (crystal.O_3598_v() - crystal.q_1982_R) * (double)partialTicks;
            double interpY = crystal.w_1474_C + (crystal.X_2960_b() - crystal.w_1474_C) * (double)partialTicks;
            double interpZ = crystal.w_612_n + (crystal.l_2647_k() - crystal.w_612_n) * (double)partialTicks;
            I_4817_s originalBox = crystal.i_601_W().grow(((Float)this.w_1457_N.J_1907_R()).floatValue());
            I_4817_s box = originalBox.offset(interpX - crystal.O_3598_v(), interpY - crystal.X_2960_b(), interpZ - crystal.l_2647_k());
            F_489_x.n_1700_B(box, (Integer)this.N_4405_n.J_1907_R(), this.Y_601_j.t_148_a());
        }
        c_4037_x.d_2461_k();
    }

    private void J_1907_R(float ticks) {
        boolean prevShadow = c_3005_b.O_508_d().v_4262_N();
        c_3005_b.O_508_d().n_1700_B(false);
        for (V_3354_l crystal : e_1178_K.c_3005_b.Y_601_j.n_1700_B(V_3354_l.class, e_1178_K.c_3005_b.Y_259_p.i_601_W().grow(64.0))) {
            if (crystal == null || !crystal.H_3699_F() || !v_2826_q.n_1700_B(crystal)) continue;
            Z_2049_e<V_3354_l> renderer = c_3005_b.O_508_d().n_1700_B(crystal);
            boolean prevRenderName = renderer.G_564_y();
            renderer.J_1907_R(false);
            c_3005_b.O_508_d().n_1700_B(crystal, ticks, false);
            renderer.J_1907_R(prevRenderName);
        }
        c_3005_b.O_508_d().n_1700_B(prevShadow);
    }

    private void n_1700_B(float direction, float direction2) {
        Color color = new Color((Integer)this.h_1847_R.J_1907_R());
        s_4405_m.P_4830_p.n_1700_B("texture", new int[]{0});
        s_4405_m.P_4830_p.J_1907_R("radius", (((Float)this.Q_4569_t.J_1907_R()).floatValue() + 3.0f) / 1.5f);
        s_4405_m.P_4830_p.J_1907_R("texelSize", 1.0f / (float)c_3005_b.a_2085_x().u_2550_I(), 1.0f / (float)c_3005_b.a_2085_x().M_588_G());
        s_4405_m.P_4830_p.J_1907_R("direction", direction, direction2);
        s_4405_m.P_4830_p.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f);
    }

    private void J_1907_R(float direction, float direction2) {
        Color color = new Color((Integer)this.h_1847_R.J_1907_R());
        s_4405_m.h_1847_R.n_1700_B("textureIn", new int[]{0});
        s_4405_m.h_1847_R.J_1907_R("radius", ((Float)this.Q_4569_t.J_1907_R()).floatValue() + 3.0f);
        s_4405_m.h_1847_R.J_1907_R("texelSize", 1.0f / (float)c_3005_b.a_2085_x().u_2550_I(), 1.0f / (float)c_3005_b.a_2085_x().M_588_G());
        s_4405_m.h_1847_R.J_1907_R("direction", direction, direction2);
        s_4405_m.h_1847_R.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        s_4405_m.h_1847_R.J_1907_R("exposure", 1.0f);
        s_4405_m.h_1847_R.n_1700_B("avoidTexture", new int[]{0});
    }

    private void M_182_A() {
        Color color = new Color((Integer)this.h_1847_R.J_1907_R());
        s_4405_m.Q_4569_t.n_1700_B("texture", new int[]{0});
        s_4405_m.Q_4569_t.J_1907_R("texelSize", 1.0f / (float)c_3005_b.a_2085_x().u_2550_I(), 1.0f / (float)c_3005_b.a_2085_x().M_588_G());
        s_4405_m.Q_4569_t.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    private void P_1922_E(boolean flatMode) {
        Color color = new Color((Integer)this.Q_2552_b.J_1907_R());
        s_4405_m.w_1457_N.n_1700_B("textureIn", new int[]{0});
        s_4405_m.w_1457_N.J_1907_R("texelSize", 1.0f / (float)c_3005_b.a_2085_x().u_2550_I(), 1.0f / (float)c_3005_b.a_2085_x().M_588_G());
        s_4405_m.w_1457_N.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        float edgeFade = flatMode ? 2.0f : 5.0f;
        float intensity = flatMode ? 0.8f : 0.3f;
        s_4405_m.w_1457_N.J_1907_R("edgeFade", edgeFade);
        s_4405_m.w_1457_N.J_1907_R("intensity", intensity);
    }

    private void t_1786_h() {
        c_4037_x.v_4276_D();
        X_933_l.P_1922_E();
        X_933_l.n_1700_B(516, 0.0f);
        X_933_l.Q_4569_t();
        X_933_l.J_1907_R(770, 771, 1, 0);
        X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.w_1484_f(7425);
    }

    private void N_4405_n() {
        c_4037_x.w_1484_f(7424);
        X_933_l.h_1847_R();
        X_933_l.G_564_y();
        c_4037_x.d_2461_k();
    }
}

