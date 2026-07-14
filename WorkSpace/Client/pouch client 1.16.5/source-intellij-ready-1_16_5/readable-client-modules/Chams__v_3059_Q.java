/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import lightning.product.D_1098_v;
import lightning.product.E_688_b;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.K_2034_Y;
import lightning.product.L_2225_p;
import lightning.product.M_1336_P;
import lightning.product.N_1021_F;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.X_4340_E;
import lightning.product.Z_2491_A;
import lightning.product.a_3913_L;
import lightning.product.b_2152_i;
import lightning.product.b_4074_q;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_4311_S;
import lightning.product.j_3959_P;
import lightning.product.j_8_l;
import lightning.product.n_1658_l;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3148_R;
import lightning.product.q_366_O;
import lightning.product.r_4811_B;
import lightning.product.s_4405_m;
import lightning.product.t_1920_R;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;
import org.lwjgl.opengl.GL11;

public class v_3059_Q
extends X_3546_T
implements b_2152_i {
    private static v_3059_Q M_588_G;
    private final N_4463_r P_4830_p = new N_4463_r("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432", false), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", true), new p_1977_n("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new p_1977_n("\u0421\u0435\u0431\u044f", false), new p_1977_n("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false));
    public final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0431\u0430", "\u0417\u0430\u043b\u0438\u0432\u043a\u0430", "\u041a\u043e\u043d\u0442\u0443\u0440", "\u041e\u0431\u0430");
    public final q_366_O w_1484_f = new q_366_O("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u041e\u0431\u044b\u0447\u043d\u0430\u044f", "\u0428\u0435\u0439\u0434\u0435\u0440", "Toon Ink");
    private final q_366_O h_1847_R = new q_366_O("Shader", "From Ambience", () -> this.w_1484_f.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440"), "From Ambience", "Plasma", "Balatro");
    private final I_686_h Q_4569_t = new I_686_h("Toon Steps", 3.0f, 2.0f, 5.0f, 1.0f, () -> this.w_1484_f.J_1907_R("Toon Ink"));
    private final I_686_h M_182_A = new I_686_h("Toon Shadow", 0.62f, 0.35f, 0.9f, 0.01f, () -> this.w_1484_f.J_1907_R("Toon Ink"));
    private final h_2367_h t_1786_h = new h_2367_h("Ink Color", true, new Color(20, 20, 24, 255).getRGB(), () -> this.w_1484_f.J_1907_R("Toon Ink"));
    private final I_686_h N_4405_n = new I_686_h("Ink Width", 2.2f, 1.0f, 4.0f, 0.1f, () -> this.w_1484_f.J_1907_R("Toon Ink"));
    public final q_366_O t_148_a = new q_366_O("\u0426\u0432\u0435\u0442", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u0421\u0432\u043e\u0439", "\u0420\u0430\u0434\u0443\u0433\u0430");
    public final h_2367_h s_956_w = new h_2367_h("\u0426\u0432\u0435\u0442 \u0437\u0430\u043b\u0438\u0432\u043a\u0438", true, new Color(100, 180, 255, 80).getRGB(), () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430"));
    public final h_2367_h u_2550_I = new h_2367_h("\u0426\u0432\u0435\u0442 \u043a\u043e\u043d\u0442\u0443\u0440\u0430", true, new Color(150, 220, 255, 255).getRGB(), () -> this.v_4262_N.J_1907_R("\u041a\u043e\u043d\u0442\u0443\u0440") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430"));
    private final I_686_h w_1457_N = new I_686_h("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439", 1.5f, 0.5f, 5.0f, 0.1f, () -> this.v_4262_N.J_1907_R("\u041a\u043e\u043d\u0442\u0443\u0440") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430"));
    private final p_1977_n Y_601_j = new p_1977_n("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true);
    private final I_686_h Y_259_p = new I_686_h("\u0421\u0438\u043b\u0430 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 2.0f, 1.0f, 5.0f, 0.1f, this.Y_601_j::t_148_a);
    private final I_686_h Q_2552_b = new I_686_h("\u0421\u043b\u043e\u0438 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 3.0f, 1.0f, 6.0f, 1.0f, this.Y_601_j::t_148_a);
    private final p_1977_n C_2741_M = new p_1977_n("\u041f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u044f", false);
    private final I_686_h k_2293_S = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u0438", 2.0f, 0.5f, 5.0f, 0.1f, this.C_2741_M::t_148_a);
    private final p_1977_n q_2307_F = new p_1977_n("\u042d\u0444\u0444\u0435\u043a\u0442 \u043f\u0440\u0438 \u0443\u0440\u043e\u043d\u0435", true);
    private final h_2367_h Z_875_P = new h_2367_h("\u0426\u0432\u0435\u0442 \u0443\u0440\u043e\u043d\u0430", false, new Color(255, 50, 50, 255).getRGB(), this.q_2307_F::t_148_a);
    private final p_1977_n t_4043_B = new p_1977_n("\u0421\u0432\u043e\u0439 \u0446\u0432\u0435\u0442 \u0434\u043b\u044f \u0434\u0440\u0443\u0437\u0435\u0439", false);
    private final h_2367_h x_607_J = new h_2367_h("\u0417\u0430\u043b\u0438\u0432\u043a\u0430 \u0434\u0440\u0443\u0437\u0435\u0439", true, new Color(85, 255, 85, 60).getRGB(), this.t_4043_B::t_148_a);
    private final h_2367_h e_4240_b = new h_2367_h("\u041a\u043e\u043d\u0442\u0443\u0440 \u0434\u0440\u0443\u0437\u0435\u0439", true, new Color(100, 255, 100, 255).getRGB(), this.t_4043_B::t_148_a);
    private final p_1977_n n_3318_d = new p_1977_n("\u0421\u043a\u0440\u044b\u0442\u044c \u043e\u0440\u0438\u0433\u0438\u043d\u0430\u043b", false);
    private final p_1977_n d_2427_y = new p_1977_n("\u0421\u043a\u0440\u044b\u0442\u044c \u0438\u0433\u0440\u043e\u043a\u043e\u0432", true, this.n_3318_d::t_148_a);
    private final p_1977_n z_1737_N = new p_1977_n("\u0421\u043a\u0440\u044b\u0442\u044c \u0441\u0435\u0431\u044f", false, this.n_3318_d::t_148_a);
    private long v_4276_D = System.currentTimeMillis();

    public static v_3059_Q h_1847_R() {
        return M_588_G;
    }

    public v_3059_Q() {
        super("Chams", y_2603_k.R_4764_Y);
        M_588_G = this;
        this.n_1700_B(this.P_4830_p, this.v_4262_N, this.w_1484_f, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.t_148_a, this.s_956_w, this.u_2550_I, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B, this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, this.z_1737_N);
    }

    public boolean n_1700_B(N_4263_v entity) {
        if (!this.w_1484_f() || !this.n_3318_d.t_148_a().booleanValue()) {
            return false;
        }
        if (entity == v_3059_Q.c_3005_b.Y_259_p) {
            return this.z_1737_N.t_148_a() != false && this.P_4830_p.J_1907_R("\u0421\u0435\u0431\u044f") != false && v_3059_Q.c_3005_b.P_4830_p.P_4830_p() != t_1920_R.n_1700_B;
        }
        if (entity instanceof a_3913_L) {
            return this.d_2427_y.t_148_a() != false && (this.P_4830_p.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u043e\u0432") != false || this.P_4830_p.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439") != false);
        }
        return false;
    }

    public void n_1700_B(float partialTicks) {
        if (!this.w_1484_f() || v_3059_Q.c_3005_b.Y_601_j == null || v_3059_Q.c_3005_b.Y_259_p == null) {
            return;
        }
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.e_4240_b();
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.q_2307_F();
        c_4037_x.u_2550_I();
        c_4037_x.h_1847_R();
        GL11.glShadeModel((int)7425);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.Y_601_j.t_148_a().booleanValue()) {
            c_4037_x.J_1907_R(770, 1, 1, 0);
        } else {
            c_4037_x.s_2632_s();
        }
        for (a_3913_L a_3913_L2 : v_3059_Q.c_3005_b.Y_601_j.N_4405_n()) {
            if (!this.n_1700_B(a_3913_L2)) continue;
            this.n_1700_B(a_3913_L2, partialTicks);
        }
        if (this.P_4830_p.J_1907_R("\u0421\u0435\u0431\u044f").booleanValue() && v_3059_Q.c_3005_b.Y_259_p != null && v_3059_Q.c_3005_b.Y_259_p.H_3699_F() && v_3059_Q.c_3005_b.P_4830_p.P_4830_p() != t_1920_R.n_1700_B && !b_4074_q.v_4262_N) {
            this.n_1700_B(v_3059_Q.c_3005_b.Y_259_p, partialTicks);
        }
        GL11.glShadeModel((int)7425);
        c_4037_x.h_1847_R();
        c_4037_x.M_588_G();
        c_4037_x.s_2632_s();
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.N_4405_n();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private boolean n_1700_B(a_3913_L player) {
        if (player == null || !player.H_3699_F() || player.t_2577_l < 3) {
            return false;
        }
        if (player == v_3059_Q.c_3005_b.Y_259_p) {
            return false;
        }
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        if (isFriend && !this.P_4830_p.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue()) {
            return false;
        }
        return isFriend || this.P_4830_p.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u043e\u0432") != false;
    }

    private void n_1700_B(a_3913_L player, float partialTicks) {
        float swingProgress;
        if (!(player instanceof X_4340_E)) {
            return;
        }
        X_4340_E clientPlayer = (X_4340_E)player;
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        boolean isDamaged = this.q_2307_F.t_148_a() != false && player.H_3699_F > 0;
        Color fill = this.n_1700_B(isFriend, isDamaged);
        Color outline = this.J_1907_R(isFriend, isDamaged);
        fill = this.J_1907_R(fill);
        outline = this.J_1907_R(outline);
        if (this.w_1484_f.J_1907_R("Toon Ink")) {
            fill = this.n_1700_B(fill);
            Color ink = new Color((Integer)this.t_1786_h.J_1907_R(), true);
            outline = new Color(ink.getRed(), ink.getGreen(), ink.getBlue(), outline.getAlpha());
        }
        h_4311_S renderer = (h_4311_S)c_3005_b.O_508_d().n_1700_B(clientPlayer);
        K_2034_Y model = (K_2034_Y)renderer.n_1700_B();
        float bodyYaw = u_530_F.w_1484_f(partialTicks, player.D_4361_a, player.C_1162_e);
        float limbSwingAmount = u_530_F.v_4262_N(partialTicks, player.A_3959_N, player.G_424_k);
        float limbSwing = player.i_1610_l - player.G_424_k * (1.0f - partialTicks);
        float age = (float)player.t_2577_l + partialTicks;
        float netHeadYaw = u_530_F.w_1484_f(partialTicks, player.n_2977_f, player.f_3449_S) - bodyYaw;
        float headPitch = u_530_F.v_4262_N(partialTicks, player.y_2356_n, player.f_4016_n);
        model.h_1847_R = swingProgress = player.Y_601_j(partialTicks);
        model.Q_4569_t = player.y_2772_m();
        model.M_182_A = player.d_();
        model.s_956_w = player.Z_875_P();
        model.u_2550_I = player.u_1723_Y(partialTicks);
        model.t_148_a = n_1658_l.n_1700_B.n_1700_B;
        model.w_1484_f = n_1658_l.n_1700_B.n_1700_B;
        if (!player.A_2714_y().n_1700_B()) {
            model.t_148_a = n_1658_l.n_1700_B.J_1907_R;
        }
        if (!player.S_4035_N().n_1700_B()) {
            model.w_1484_f = n_1658_l.n_1700_B.J_1907_R;
        }
        if (player.Y_601_j()) {
            if (player.Q_2552_b() == x_1688_C.n_1700_B) {
                model.t_148_a = n_1658_l.n_1700_B.R_4764_Y;
            } else {
                model.w_1484_f = n_1658_l.n_1700_B.R_4764_Y;
            }
        }
        model.n_1700_B(clientPlayer, limbSwing, limbSwingAmount, partialTicks);
        model.n_1700_B(clientPlayer, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
        boolean shaderFill = this.w_1484_f.J_1907_R("\u0428\u0435\u0439\u0434\u0435\u0440") && (this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430"));
        s_4405_m shader = null;
        if (shaderFill) {
            shader = this.Q_4569_t();
            if (shader != null && shader.n_1700_B() && v_3059_Q.c_3005_b.Y_601_j != null) {
                float time = ((float)v_3059_Q.c_3005_b.Y_601_j.X_933_l() + partialTicks) * ((Float)j_8_l.Y_601_j.J_1907_R()).floatValue();
                float a = (float)fill.getAlpha() / 255.0f;
                shader.J_1907_R();
                shader.n_1700_B("u_Color", (float)fill.getRed() / 255.0f, (float)fill.getGreen() / 255.0f, (float)fill.getBlue() / 255.0f, 1.0f);
                shader.n_1700_B("u_Scale", ((Float)j_8_l.w_1457_N.J_1907_R()).floatValue());
                shader.J_1907_R("u_Time", time * 0.08f);
                shader.J_1907_R("u_Alpha", a);
            } else {
                shaderFill = false;
                shader = null;
            }
        }
        if (this.Y_601_j.t_148_a().booleanValue()) {
            int layers = ((Float)this.Q_2552_b.J_1907_R()).intValue();
            float intensity = ((Float)this.Y_259_p.J_1907_R()).floatValue();
            for (int i = layers; i >= 0; --i) {
                float expand = (float)i * 0.5f * intensity;
                float alphaMultiplier = i == 0 ? 1.0f : 1.0f / (float)(i + 1) * 0.7f;
                Color layerFill = new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), (int)((float)fill.getAlpha() * alphaMultiplier));
                Color layerOutline = new Color(outline.getRed(), outline.getGreen(), outline.getBlue(), (int)((float)outline.getAlpha() * alphaMultiplier));
                g_221_o matrixStack = new g_221_o();
                this.n_1700_B(matrixStack, player, clientPlayer, partialTicks, renderer);
                this.n_1700_B(matrixStack, model.n_1700_B, -4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, expand, layerFill, layerOutline, shaderFill);
                this.n_1700_B(matrixStack, model.R_4764_Y, -4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, expand, layerFill, layerOutline, shaderFill);
                this.n_1700_B(matrixStack, model.G_564_y, -3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, layerFill, layerOutline, shaderFill);
                this.n_1700_B(matrixStack, model.P_1922_E, -1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, layerFill, layerOutline, shaderFill);
                this.n_1700_B(matrixStack, model.u_1723_Y, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, layerFill, layerOutline, shaderFill);
                this.n_1700_B(matrixStack, model.v_4262_N, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, expand, layerFill, layerOutline, shaderFill);
            }
        } else {
            g_221_o matrixStack = new g_221_o();
            this.n_1700_B(matrixStack, player, clientPlayer, partialTicks, renderer);
            this.n_1700_B(matrixStack, model.n_1700_B, -4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, fill, outline, shaderFill);
            this.n_1700_B(matrixStack, model.R_4764_Y, -4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, fill, outline, shaderFill);
            this.n_1700_B(matrixStack, model.G_564_y, -3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, fill, outline, shaderFill);
            this.n_1700_B(matrixStack, model.P_1922_E, -1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, fill, outline, shaderFill);
            this.n_1700_B(matrixStack, model.u_1723_Y, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, fill, outline, shaderFill);
            this.n_1700_B(matrixStack, model.v_4262_N, -2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, fill, outline, shaderFill);
        }
        if (shader != null && shaderFill) {
            shader.R_4764_Y();
        }
    }

    private s_4405_m Q_4569_t() {
        boolean wantBalatro = this.h_1847_R.J_1907_R("Plasma") ? false : (this.h_1847_R.J_1907_R("Balatro") ? true : j_8_l.t_1786_h != null && j_8_l.t_1786_h.J_1907_R("Balatro"));
        return wantBalatro ? s_4405_m.g_221_o : s_4405_m.z_4693_k;
    }

    private void n_1700_B(g_221_o baseStack, e_4189_z part, float offX, float offY, float offZ, float width, float height, float depth, Color fill, Color outline, boolean shaderFill) {
        this.n_1700_B(baseStack, part, offX, offY, offZ, width, height, depth, 0.0f, fill, outline, shaderFill);
    }

    private void n_1700_B(g_221_o baseStack, e_4189_z part, float offX, float offY, float offZ, float width, float height, float depth, float expand, Color fill, Color outline, boolean shaderFill) {
        baseStack.n_1700_B();
        part.n_1700_B(baseStack);
        D_1098_v matrix = baseStack.R_4764_Y().n_1700_B();
        float scale = 0.0625f;
        float expandScale = expand * scale;
        float minX = offX * scale - expandScale;
        float minY = offY * scale - expandScale;
        float minZ = offZ * scale - expandScale;
        float maxX = (offX + width) * scale + expandScale;
        float maxY = (offY + height) * scale + expandScale;
        float maxZ = (offZ + depth) * scale + expandScale;
        float fr = (float)fill.getRed() / 255.0f;
        float fg = (float)fill.getGreen() / 255.0f;
        float fb = (float)fill.getBlue() / 255.0f;
        float fa = (float)fill.getAlpha() / 255.0f;
        float or = (float)outline.getRed() / 255.0f;
        float og = (float)outline.getGreen() / 255.0f;
        float ob = (float)outline.getBlue() / 255.0f;
        float oa = (float)outline.getAlpha() / 255.0f;
        if (this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430")) {
            if (shaderFill) {
                this.n_1700_B(matrix, minX, minY, minZ, maxX, maxY, maxZ);
            } else {
                A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
                this.n_1700_B(matrix, minX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, minX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, minZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, maxY, maxZ, fr, fg, fb, fa);
                this.n_1700_B(matrix, maxX, minY, maxZ, fr, fg, fb, fa);
                Y_1740_V.J_1907_R();
            }
        }
        if (this.v_4262_N.J_1907_R("\u041a\u043e\u043d\u0442\u0443\u0440") || this.v_4262_N.J_1907_R("\u041e\u0431\u0430")) {
            GL11.glEnable((int)2848);
            if (this.w_1484_f.J_1907_R("Toon Ink")) {
                GL11.glDisable((int)2848);
            } else {
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
            }
            c_4037_x.G_564_y((this.w_1484_f.J_1907_R("Toon Ink") ? (Float)this.N_4405_n.J_1907_R() : (Float)this.w_1457_N.J_1907_R()).floatValue());
            A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
            this.n_1700_B(matrix, minX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, minZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, maxX, maxY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, minY, maxZ, or, og, ob, oa);
            this.n_1700_B(matrix, minX, maxY, maxZ, or, og, ob, oa);
            Y_1740_V.J_1907_R();
            if (!this.w_1484_f.J_1907_R("Toon Ink")) {
                GL11.glDisable((int)2848);
            }
        }
        baseStack.J_1907_R();
    }

    private void n_1700_B(D_1098_v matrix, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        Z_2491_A c = new Z_2491_A((minX + maxX) * 0.5f, (minY + maxY) * 0.5f, (minZ + maxZ) * 0.5f, 1.0f);
        c.n_1700_B(matrix);
        float cx = c.n_1700_B();
        float cy = c.J_1907_R();
        float cz = c.R_4764_Y();
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
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

    private void n_1700_B(D_1098_v matrix, float x, float y, float z, float r, float g, float b, float a) {
        Z_2491_A vec = new Z_2491_A(x, y, z, 1.0f);
        vec.n_1700_B(matrix);
        A_4115_X.pos(vec.n_1700_B(), vec.J_1907_R(), vec.R_4764_Y()).n_1700_B(r, g, b, a).endVertex();
    }

    private void n_1700_B(g_221_o matrixStack, a_3913_L player, X_4340_E clientPlayer, float partialTicks, h_4311_S renderer) {
        e_2866_D pos = F_747_P.n_1700_B((N_4263_v)player, partialTicks);
        double renderX = pos.J_1907_R - c_3005_b.O_508_d().renderPosX();
        double renderY = pos.R_4764_Y - c_3005_b.O_508_d().renderPosY();
        double renderZ = pos.G_564_y - c_3005_b.O_508_d().renderPosZ();
        matrixStack.n_1700_B(renderX, renderY, renderZ);
        float bodyYaw = u_530_F.w_1484_f(partialTicks, player.D_4361_a, player.C_1162_e);
        float swimAnim = player.u_1723_Y(partialTicks);
        if (player.k_578_l()) {
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - bodyYaw));
            float elytraTicks = (float)player.h_3859_C() + partialTicks;
            float elytraProgress = u_530_F.n_1700_B(elytraTicks * elytraTicks / 100.0f, 0.0f, 1.0f);
            if (!player.B_3040_x()) {
                matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(elytraProgress * (-90.0f - player.f_4016_n)));
            }
            e_2866_D lookVec = player.t_148_a(partialTicks);
            e_2866_D motionVec = player.I_4348_c();
            double motionHorizontal = motionVec.J_1907_R * motionVec.J_1907_R + motionVec.G_564_y * motionVec.G_564_y;
            double lookHorizontal = lookVec.J_1907_R * lookVec.J_1907_R + lookVec.G_564_y * lookVec.G_564_y;
            if (motionHorizontal > 0.0 && lookHorizontal > 0.0) {
                double dot = (motionVec.J_1907_R * lookVec.J_1907_R + motionVec.G_564_y * lookVec.G_564_y) / Math.sqrt(motionHorizontal * lookHorizontal);
                double cross = motionVec.J_1907_R * lookVec.G_564_y - motionVec.G_564_y * lookVec.J_1907_R;
                matrixStack.n_1700_B(M_1336_P.G_564_y.J_1907_R((float)(Math.signum(cross) * Math.acos(dot))));
            }
        } else if (swimAnim > 0.0f) {
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - bodyYaw));
            float swimAngle = player.a_2180_A() ? -90.0f - player.f_4016_n : -90.0f;
            float lerpAngle = u_530_F.v_4262_N(swimAnim, 0.0f, swimAngle);
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(lerpAngle));
            if (player.x_612_B()) {
                matrixStack.n_1700_B(0.0, -1.0, 0.3);
            }
        } else {
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - bodyYaw));
        }
        if (player.Z_875_P()) {
            matrixStack.n_1700_B(0.0, -0.11, 0.0);
        }
        matrixStack.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixStack.n_1700_B(0.9375f, 0.9375f, 0.9375f);
        matrixStack.n_1700_B(0.0, -1.501, 0.0);
    }

    private Color n_1700_B(boolean isFriend, boolean isDamaged) {
        if (isDamaged) {
            Color dmg = new Color((Integer)this.Z_875_P.J_1907_R(), true);
            return new Color(dmg.getRed(), dmg.getGreen(), dmg.getBlue(), 80);
        }
        if (isFriend && this.t_4043_B.t_148_a().booleanValue()) {
            return new Color((Integer)this.x_607_J.J_1907_R(), true);
        }
        if (this.t_148_a.J_1907_R("\u0420\u0430\u0434\u0443\u0433\u0430")) {
            float hue = (float)(System.currentTimeMillis() % 3000L) / 3000.0f;
            Color rainbow = Color.getHSBColor(hue, 0.7f, 1.0f);
            return new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), 60);
        }
        if (this.t_148_a.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
            int c = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            return new Color(H_2506_c.n_1700_B(c), H_2506_c.J_1907_R(c), H_2506_c.R_4764_Y(c), 60);
        }
        return new Color((Integer)this.s_956_w.J_1907_R(), true);
    }

    private Color J_1907_R(boolean isFriend, boolean isDamaged) {
        if (isDamaged) {
            return new Color((Integer)this.Z_875_P.J_1907_R(), true);
        }
        if (isFriend && this.t_4043_B.t_148_a().booleanValue()) {
            return new Color((Integer)this.e_4240_b.J_1907_R(), true);
        }
        if (this.t_148_a.J_1907_R("\u0420\u0430\u0434\u0443\u0433\u0430")) {
            float hue = (float)(System.currentTimeMillis() % 3000L) / 3000.0f;
            return Color.getHSBColor(hue, 0.8f, 1.0f);
        }
        if (this.t_148_a.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
            int c = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            return new Color(H_2506_c.n_1700_B(c), H_2506_c.J_1907_R(c), H_2506_c.R_4764_Y(c), 255);
        }
        return new Color((Integer)this.u_2550_I.J_1907_R(), true);
    }

    private Color n_1700_B(Color color) {
        int steps = Math.max(2, ((Float)this.Q_4569_t.J_1907_R()).intValue());
        float shadow = u_530_F.n_1700_B(((Float)this.M_182_A.J_1907_R()).floatValue(), 0.2f, 1.0f);
        float step = 255.0f / (float)(steps - 1);
        int r = u_530_F.n_1700_B((int)((float)Math.round((float)color.getRed() * shadow / step) * step), 0, 255);
        int g = u_530_F.n_1700_B((int)((float)Math.round((float)color.getGreen() * shadow / step) * step), 0, 255);
        int b = u_530_F.n_1700_B((int)((float)Math.round((float)color.getBlue() * shadow / step) * step), 0, 255);
        return new Color(r, g, b, color.getAlpha());
    }

    private Color J_1907_R(Color color) {
        if (!this.C_2741_M.t_148_a().booleanValue()) {
            return color;
        }
        float time = (float)(System.currentTimeMillis() - this.v_4276_D) / 1000.0f;
        float pulseValue = (float)((Math.sin((double)(time * ((Float)this.k_2293_S.J_1907_R()).floatValue()) * Math.PI) + 1.0) / 2.0);
        float alpha = 0.3f + 0.7f * pulseValue;
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * alpha));
    }

    public boolean J_1907_R(N_4263_v entity) {
        if (entity == null || !entity.H_3699_F()) {
            return false;
        }
        if (!(entity instanceof r_4811_B)) {
            return false;
        }
        if (entity == v_3059_Q.c_3005_b.Y_259_p) {
            if (!this.P_4830_p.J_1907_R("\u0421\u0435\u0431\u044f").booleanValue()) {
                return false;
            }
            return v_3059_Q.c_3005_b.P_4830_p.P_4830_p() != t_1920_R.n_1700_B;
        }
        if (entity instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)entity;
            if (o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName())) {
                return this.P_4830_p.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439");
            }
            return this.P_4830_p.J_1907_R("\u0418\u0433\u0440\u043e\u043a\u043e\u0432");
        }
        if (entity instanceof j_3959_P) {
            return this.P_4830_p.J_1907_R("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432");
        }
        if (entity instanceof N_1021_F) {
            return this.P_4830_p.J_1907_R("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445");
        }
        if (entity instanceof L_2225_p) {
            return this.P_4830_p.J_1907_R("\u0416\u0438\u0442\u0435\u043b\u0435\u0439");
        }
        return false;
    }
}

