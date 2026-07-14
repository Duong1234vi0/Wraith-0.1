/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL13
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 */
package lightning.product;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.P_4249_L;
import lightning.product.X_2048_Y;
import lightning.product.Module;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.h_2367_h;
import lightning.product.l_3747_P;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.s_4405_m;
import lightning.product.ModuleCategory;
import lightning.product.y_4842_Z;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class f_2247_K
extends Module {
    private final ModeSetting v_4262_N = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u0417\u0430\u043b\u0438\u0432\u043a\u0430", "\u0417\u0435\u0440\u043a\u0430\u043b\u043e", "\u0417\u0430\u043b\u0438\u0432\u043a\u0430");
    private final ModeSetting w_1484_f = new ModeSetting("\u0426\u0432\u0435\u0442 \u0437\u0435\u0440\u043a\u0430\u043b\u0430", "\u0421\u0432\u043e\u0439", () -> this.v_4262_N.J_1907_R("\u0417\u0435\u0440\u043a\u0430\u043b\u043e"), "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    private final h_2367_h t_148_a = new h_2367_h("\u0426\u0432\u0435\u0442", false, H_2506_c.n_1700_B("#8A98FFFF"), () -> this.v_4262_N.J_1907_R("\u0417\u0435\u0440\u043a\u0430\u043b\u043e") && this.w_1484_f.J_1907_R("\u0421\u0432\u043e\u0439"));
    private final NumberSetting s_956_w = new NumberSetting("\u0421\u043c\u0435\u0448\u0438\u0432\u0430\u043d\u0438\u0435", 0.0f, 0.0f, 0.5f, 0.01f, () -> this.v_4262_N.J_1907_R("\u0417\u0435\u0440\u043a\u0430\u043b\u043e"));
    private final BooleanSetting u_2550_I = new BooleanSetting("\u0420\u0430\u0434\u0443\u0436\u043d\u0430\u044f", false, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430"));
    private final NumberSetting M_588_G = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0440\u0430\u0434\u0443\u0433\u0438", 0.4f, 0.0f, 2.0f, 0.05f, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") && this.u_2550_I.t_148_a() != false);
    private final NumberSetting P_4830_p = new NumberSetting("\u041c\u0430\u0441\u0448\u0442\u0430\u0431 \u0440\u0430\u0434\u0443\u0433\u0438", 1.0f, 0.2f, 3.0f, 0.1f, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") && this.u_2550_I.t_148_a() != false);
    private final ModeSetting h_1847_R = new ModeSetting("\u0426\u0432\u0435\u0442 \u0437\u0430\u043b\u0438\u0432\u043a\u0438", "\u0421\u0432\u043e\u0439", () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") && this.u_2550_I.t_148_a() == false, "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    private final h_2367_h Q_4569_t = new h_2367_h("\u0426\u0432\u0435\u0442 \u0437\u0430\u043b\u0438\u0432\u043a\u0438 (\u0441\u0432\u043e\u0439)", false, H_2506_c.n_1700_B("#FF4444FF"), () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") && this.u_2550_I.t_148_a() == false && this.h_1847_R.J_1907_R("\u0421\u0432\u043e\u0439"));
    private final NumberSetting M_182_A = new NumberSetting("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u0437\u0430\u043b\u0438\u0432\u043a\u0438", 0.8f, 0.0f, 1.0f, 0.05f, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430"));
    private final BooleanSetting t_1786_h = new BooleanSetting("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u0442\u0435\u043d\u0438", true, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430"));
    private final NumberSetting multiplayerClientSuggestionProvider = new NumberSetting("\u0421\u0438\u043b\u0430 \u0442\u0435\u043d\u0435\u0439", 0.3f, 0.0f, 1.0f, 0.05f, () -> this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430") && this.t_1786_h.t_148_a() != false);
    private final BooleanSetting w_1457_N = new BooleanSetting("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", false);
    private final BooleanSetting Y_601_j = new BooleanSetting("\u0413\u043b\u043e\u0432", true);
    private final NumberSetting Y_259_p = new NumberSetting("\u0420\u0430\u0437\u043c\u044b\u0442\u0438\u0435", 4.0f, 1.0f, 6.0f, 1.0f, () -> this.Y_601_j.t_148_a());
    private final BooleanSetting Q_2552_b = new BooleanSetting("\u0412\u043d\u0435\u0448\u043d\u0438\u0439 \u0433\u043b\u043e\u0432", true, () -> this.Y_601_j.t_148_a());
    private final NumberSetting C_2741_M = new NumberSetting("\u042f\u0440\u043a\u043e\u0441\u0442\u044c", 2.0f, 0.5f, 5.0f, 0.1f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false);
    private final BooleanSetting k_2293_S = new BooleanSetting("\u0410\u0432\u0442\u043e \u0446\u0432\u0435\u0442", false, () -> this.Y_601_j.t_148_a() != false || this.w_1457_N.t_148_a() != false);
    private final NumberSetting q_2307_F = new NumberSetting("\u041d\u0430\u0441\u044b\u0449\u0435\u043d\u043d\u043e\u0441\u0442\u044c", 1.4f, 0.5f, 3.0f, 0.1f, () -> (this.Y_601_j.t_148_a() != false || this.w_1457_N.t_148_a() != false) && this.k_2293_S.t_148_a() != false);
    private final ModeSetting Z_875_P = new ModeSetting("\u0426\u0432\u0435\u0442 \u044d\u0444\u0444\u0435\u043a\u0442\u0430", "\u0421\u0432\u043e\u0439", () -> (this.Y_601_j.t_148_a() != false || this.w_1457_N.t_148_a() != false) && this.k_2293_S.t_148_a() == false, "\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441", "\u0421\u0432\u043e\u0439");
    private final h_2367_h t_4043_B = new h_2367_h("\u0426\u0432\u0435\u0442 \u0433\u043b\u043e\u0432\u0430 1", false, H_2506_c.n_1700_B("#8A98FFFF"), () -> this.Y_601_j.t_148_a() != false && this.k_2293_S.t_148_a() == false && this.Z_875_P.J_1907_R("\u0421\u0432\u043e\u0439"));
    private final h_2367_h x_607_J = new h_2367_h("\u0426\u0432\u0435\u0442 \u0433\u043b\u043e\u0432\u0430 2", false, H_2506_c.n_1700_B("#FF6BACFF"), () -> this.Y_601_j.t_148_a() != false && this.k_2293_S.t_148_a() == false && this.Z_875_P.J_1907_R("\u0421\u0432\u043e\u0439"));
    private final BooleanSetting e_4240_b = new BooleanSetting("\u0428\u043b\u0435\u0439\u0444", true, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false);
    private final NumberSetting n_3318_d = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0437\u0430\u0442\u0443\u0445\u0430\u043d\u0438\u044f", 0.012f, 0.002f, 0.2f, 0.002f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting d_2427_y = new NumberSetting("\u041f\u043e\u0434\u044a\u0451\u043c", 0.2f, 0.0f, 1.5f, 0.05f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting z_1737_N = new NumberSetting("\u041a\u0430\u0447\u0430\u043d\u0438\u0435", 0.05f, 0.0f, 0.2f, 0.005f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting v_4276_D = new NumberSetting("\u0422\u0443\u0440\u0431\u0443\u043b\u0435\u043d\u0442\u043d\u043e\u0441\u0442\u044c", 0.0f, 0.0f, 0.6f, 0.01f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting d_2461_k = new NumberSetting("\u041c\u0435\u0440\u0446\u0430\u043d\u0438\u0435", 0.0f, 0.0f, 0.2f, 0.01f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final BooleanSetting G_624_v = new BooleanSetting("\u0421\u0434\u0443\u0432 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435", true, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting T_2506_i = new NumberSetting("\u0421\u0438\u043b\u0430 \u0441\u0434\u0443\u0432\u0430", 4.0f, 1.0f, 10.0f, 0.5f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false && this.G_624_v.t_148_a() != false);
    private final BooleanSetting q_4610_l = new BooleanSetting("\u0428\u043b\u0435\u0439\u0444 \u043c\u043e\u0434\u0435\u043b\u0438", true, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false);
    private final NumberSetting z_4693_k = new NumberSetting("\u041f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u044c \u043c\u043e\u0434\u0435\u043b\u0438", 0.6f, 0.1f, 1.0f, 0.05f, () -> this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false && this.e_4240_b.t_148_a() != false && this.q_4610_l.t_148_a() != false);
    private final NumberSetting g_221_o = new NumberSetting("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043e\u0431\u0432\u043e\u0434\u043a\u0438", 1.0f, 0.5f, 3.0f, 0.5f, this.w_1457_N::t_148_a);
    private final h_2367_h e_2887_G = new h_2367_h("\u0426\u0432\u0435\u0442 \u043e\u0431\u0432\u043e\u0434\u043a\u0438", false, H_2506_c.n_1700_B("#8A98FFFF"), () -> this.w_1457_N.t_148_a() != false && this.Z_875_P.J_1907_R("\u0421\u0432\u043e\u0439"));
    private P_4249_L B_1668_F;
    private P_4249_L g_164_R;
    private P_4249_L X_933_l;
    private final List<P_4249_L> Z_976_R = new ArrayList<P_4249_L>();
    private int H_1990_U = -1;
    private int N_2525_X = -1;
    private int c_4037_x = -1;
    private int g_2268_R = -1;
    private int T_3594_S = -1;
    private int D_4792_h = -1;
    private int s_2632_s = -1;
    private int l_1233_K = -1;
    private long z_1333_t = 0L;
    private float O_508_d = 0.016666668f;
    private long r_715_M = -10000L;
    private boolean A_1038_p = false;
    private final float[] i_1637_u = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    private int Ping = 0;
    private static final boolean p_178_J = MinecraftClient.n_1700_B;
    private final Map<Long, Integer> RealmsClientConfig = new HashMap<Long, Integer>();
    private static final int f_4016_n = 36006;

    private int h_1847_R() {
        return q_3148_R.n_1700_B(K_1200_E.J_1907_R);
    }

    public f_2247_K() {
        super("Hands", "\u041d\u0430\u043a\u043b\u0430\u0434\u044b\u0432\u0430\u0435\u0442 \u044d\u0444\u0444\u0435\u043a\u0442 \u043d\u0430 \u0432\u0430\u0448\u0438 \u0440\u0443\u043a\u0438", ModuleCategory.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.multiplayerClientSuggestionProvider, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B, this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, this.z_1737_N, this.v_4276_D, this.d_2461_k, this.G_624_v, this.T_2506_i, this.q_4610_l, this.z_4693_k, this.w_1457_N, this.g_221_o, this.e_2887_G);
    }

    private P_4249_L n_1700_B(P_4249_L buf, boolean depth) {
        return this.n_1700_B(buf, depth, 1);
    }

    private P_4249_L n_1700_B(P_4249_L buf, boolean depth, int divisor) {
        int fw = c_3005_b.RealmsServerPing().u_2550_I();
        int fh = c_3005_b.RealmsServerPing().M_588_G();
        int w = Math.max(2, fw / divisor);
        int h = Math.max(2, fh / divisor);
        if (buf == null) {
            buf = new P_4249_L(w, h, depth, p_178_J);
            buf.n_1700_B(9729);
            buf.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f);
            buf.R_4764_Y(p_178_J);
        } else if (buf.R_4764_Y != w || buf.G_564_y != h) {
            buf.n_1700_B(w, h, p_178_J);
            buf.n_1700_B(9729);
            buf.R_4764_Y(p_178_J);
        }
        return buf;
    }

    private int n_1700_B(int p, String n) {
        long key = (long)p << 32 | (long)n.hashCode() & 0xFFFFFFFFL;
        Integer cached = this.RealmsClientConfig.get(key);
        if (cached != null) {
            return cached;
        }
        int l = GL20.glGetUniformLocation((int)p, (CharSequence)n);
        this.RealmsClientConfig.put(key, l);
        return l;
    }

    private void n_1700_B(int p, String n, int v) {
        int l = this.n_1700_B(p, n);
        if (l >= 0) {
            GL20.glUniform1i((int)l, (int)v);
        }
    }

    private void n_1700_B(int p, String n, float v) {
        int l = this.n_1700_B(p, n);
        if (l >= 0) {
            GL20.glUniform1f((int)l, (float)v);
        }
    }

    private void n_1700_B(int p, String n, float a, float b) {
        int l = this.n_1700_B(p, n);
        if (l >= 0) {
            GL20.glUniform2f((int)l, (float)a, (float)b);
        }
    }

    private void n_1700_B(int p, String n, float a, float b, float c) {
        int l = this.n_1700_B(p, n);
        if (l >= 0) {
            GL20.glUniform3f((int)l, (float)a, (float)b, (float)c);
        }
    }

    private void n_1700_B(int unit, int tex) {
        lightning.product.X_933_l.t_1786_h(unit);
        lightning.product.X_933_l.w_1457_N(tex);
    }

    @Y_1740_V
    private void n_1700_B(X_2048_Y.J_1907_R e) {
        if (this.g_2268_R == -1) {
            this.C_2741_M();
        }
        if (this.Y_601_j.t_148_a().booleanValue() || this.w_1457_N.t_148_a().booleanValue()) {
            this.B_1668_F = this.n_1700_B(this.B_1668_F, true);
            this.B_1668_F.R_4764_Y(p_178_J);
            this.B_1668_F.J_1907_R(true);
        }
        if (this.v_4262_N.J_1907_R("\u0417\u0435\u0440\u043a\u0430\u043b\u043e")) {
            if (y_4842_Z.n_1700_B.J_1907_R == null) {
                return;
            }
            s_4405_m.M_182_A.J_1907_R();
            GL13.glActiveTexture((int)33989);
            GL11.glBindTexture((int)3553, (int)y_4842_Z.n_1700_B.J_1907_R.v_4262_N);
            GL13.glActiveTexture((int)33984);
            s_4405_m.M_182_A.n_1700_B("originalTexture", new int[]{0});
            s_4405_m.M_182_A.n_1700_B("blurredTexture", new int[]{5});
            int mirrorColor = this.w_1484_f.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.t_148_a.J_1907_R()).intValue();
            float[] c = H_2506_c.P_1922_E(mirrorColor);
            s_4405_m.M_182_A.J_1907_R("multiplier", c[0], c[1], c[2], c[3]);
            s_4405_m.M_182_A.J_1907_R("resolution", y_4842_Z.n_1700_B.J_1907_R.R_4764_Y, y_4842_Z.n_1700_B.J_1907_R.G_564_y);
            s_4405_m.M_182_A.J_1907_R("mixFactor", ((Float)this.s_956_w.J_1907_R()).floatValue());
        } else if (this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430")) {
            GL20.glUseProgram((int)this.g_2268_R);
            int fillClr = this.h_1847_R.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.Q_4569_t.J_1907_R()).intValue();
            float[] c = H_2506_c.P_1922_E(fillClr);
            this.n_1700_B(this.g_2268_R, "fillColor", c[0], c[1], c[2]);
            this.n_1700_B(this.g_2268_R, "fillAlpha", ((Float)this.M_182_A.J_1907_R()).floatValue());
            this.n_1700_B(this.g_2268_R, "keepShading", this.t_1786_h.t_148_a() != false ? 1 : 0);
            this.n_1700_B(this.g_2268_R, "shadingStrength", ((Float)this.multiplayerClientSuggestionProvider.J_1907_R()).floatValue());
            this.n_1700_B(this.g_2268_R, "originalTexture", 0);
            this.n_1700_B(this.g_2268_R, "rainbow", this.u_2550_I.t_148_a() != false ? 1 : 0);
            this.n_1700_B(this.g_2268_R, "rainbowTime", (float)(System.currentTimeMillis() % 100000L) / 1000.0f);
            this.n_1700_B(this.g_2268_R, "rainbowSpeed", ((Float)this.M_588_G.J_1907_R()).floatValue());
            this.n_1700_B(this.g_2268_R, "rainbowScale", ((Float)this.P_4830_p.J_1907_R()).floatValue());
            this.n_1700_B(this.g_2268_R, "screenH", (float)c_3005_b.RealmsServerPing().M_588_G());
        }
    }

    @Y_1740_V
    private void n_1700_B(X_2048_Y.n_1700_B e) {
        if (this.v_4262_N.J_1907_R("\u0417\u0435\u0440\u043a\u0430\u043b\u043e")) {
            s_4405_m.M_182_A.R_4764_Y();
        } else {
            GL20.glUseProgram((int)0);
        }
        if ((this.Y_601_j.t_148_a().booleanValue() || this.w_1457_N.t_148_a().booleanValue()) && this.B_1668_F != null) {
            c_3005_b.G_564_y().J_1907_R(true);
            this.Q_4569_t();
        }
    }

    private void Q_4569_t() {
        boolean bloomDone;
        if (this.H_1990_U == -1) {
            this.C_2741_M();
        }
        this.Y_259_p();
        this.l_1233_K = -1;
        boolean bl = bloomDone = this.Y_601_j.t_148_a() != false && this.Q_2552_b.t_148_a() != false;
        if (bloomDone) {
            this.w_1457_N();
        } else if (this.w_1457_N.t_148_a().booleanValue() && this.k_2293_S.t_148_a().booleanValue()) {
            this.multiplayerClientSuggestionProvider();
        }
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.B_1668_F.v_4262_N();
        this.Y_601_j();
        if (this.w_1457_N.t_148_a().booleanValue()) {
            this.M_182_A();
        }
        this.Q_2552_b();
    }

    private void M_182_A() {
        boolean auto;
        boolean rainbow = this.u_2550_I.t_148_a() != false && this.v_4262_N.J_1907_R("\u0417\u0430\u043b\u0438\u0432\u043a\u0430");
        boolean bl = auto = this.k_2293_S.t_148_a() != false && this.l_1233_K != -1;
        int colorMode = rainbow ? 1 : (auto ? 2 : 0);
        int outlineClr = this.Z_875_P.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.e_2887_G.J_1907_R()).intValue();
        Color c = new Color(outlineClr, true);
        float a = (float)c.getAlpha() / 255.0f;
        GL20.glUseProgram((int)this.s_2632_s);
        this.n_1700_B(this.s_2632_s, "inTex", 0);
        this.n_1700_B(this.s_2632_s, "bloomTex", 1);
        this.n_1700_B(this.s_2632_s, "colorMode", colorMode);
        this.n_1700_B(this.s_2632_s, "width", ((Float)this.g_221_o.J_1907_R()).floatValue());
        this.n_1700_B(this.s_2632_s, "texelSize", 1.0f / (float)c_3005_b.RealmsServerPing().u_2550_I(), 1.0f / (float)c_3005_b.RealmsServerPing().M_588_G());
        this.n_1700_B(this.s_2632_s, "alpha", a);
        this.n_1700_B(this.s_2632_s, "rainbowTime", (float)(System.currentTimeMillis() % 100000L) / 1000.0f);
        this.n_1700_B(this.s_2632_s, "rainbowSpeed", ((Float)this.M_588_G.J_1907_R()).floatValue());
        this.n_1700_B(this.s_2632_s, "rainbowScale", ((Float)this.P_4830_p.J_1907_R()).floatValue());
        this.n_1700_B(this.s_2632_s, "screenH", (float)c_3005_b.RealmsServerPing().M_588_G());
        this.n_1700_B(this.s_2632_s, "saturation", ((Float)this.q_2307_F.J_1907_R()).floatValue());
        this.n_1700_B(this.s_2632_s, "solidColor", (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f);
        if (auto) {
            this.n_1700_B(33985, this.l_1233_K);
        }
        this.n_1700_B(33984, this.B_1668_F.v_4262_N);
        this.Y_601_j();
        if (auto) {
            this.n_1700_B(33985, 0);
        }
        GL20.glUseProgram((int)0);
    }

    private void t_1786_h() {
        if (this.Z_976_R.isEmpty()) {
            return;
        }
        if (this.Ping++ % 6 != 0) {
            return;
        }
        P_4249_L small = this.Z_976_R.get(this.Z_976_R.size() - 1);
        int w = small.R_4764_Y;
        int h = small.G_564_y;
        int prev = GL11.glGetInteger((int)36006);
        small.J_1907_R(false);
        ByteBuffer buf = BufferUtils.createByteBuffer((int)(w * h * 4));
        GL11.glReadPixels((int)0, (int)0, (int)w, (int)h, (int)6408, (int)5121, (ByteBuffer)buf);
        GL30.glBindFramebuffer((int)36160, (int)prev);
        float sumR = 0.0f;
        float sumG = 0.0f;
        float sumB = 0.0f;
        float sumA = 0.0f;
        for (int i = 0; i < w * h; ++i) {
            int o = i * 4;
            float pr = (float)(buf.get(o) & 0xFF) / 255.0f;
            float pg = (float)(buf.get(o + 1) & 0xFF) / 255.0f;
            float pb = (float)(buf.get(o + 2) & 0xFF) / 255.0f;
            float pa = (float)(buf.get(o + 3) & 0xFF) / 255.0f;
            sumR += pr;
            sumG += pg;
            sumB += pb;
            sumA += pa;
        }
        if (sumA < 0.001f) {
            return;
        }
        float r = sumR / sumA;
        float g = sumG / sumA;
        float b = sumB / sumA;
        float m = Math.max(r, Math.max(g, b));
        if (m > 0.001f) {
            r /= m;
            g /= m;
            b /= m;
        }
        float sat = ((Float)this.q_2307_F.J_1907_R()).floatValue();
        float l = 0.299f * r + 0.587f * g + 0.114f * b;
        r = f_2247_K.n_1700_B(l + (r - l) * sat);
        g = f_2247_K.n_1700_B(l + (g - l) * sat);
        b = f_2247_K.n_1700_B(l + (b - l) * sat);
        this.i_1637_u[0] = r;
        this.i_1637_u[1] = g;
        this.i_1637_u[2] = b;
        this.i_1637_u[3] = 1.0f;
    }

    private static float n_1700_B(float v) {
        return v < 0.0f ? 0.0f : Math.min(v, 1.0f);
    }

    private void multiplayerClientSuggestionProvider() {
        P_4249_L b;
        int i;
        int iter = 3;
        this.J_1907_R(iter);
        int cur = this.B_1668_F.v_4262_N;
        GL20.glUseProgram((int)this.H_1990_U);
        this.n_1700_B(this.H_1990_U, "inTexture", 0);
        for (i = 0; i < iter; ++i) {
            b = this.Z_976_R.get(i);
            b.R_4764_Y(p_178_J);
            b.J_1907_R(true);
            this.n_1700_B(this.H_1990_U, "uSize", b.R_4764_Y, b.G_564_y);
            this.n_1700_B(this.H_1990_U, "uOffset", 1.0f + (float)i, 1.0f + (float)i);
            this.n_1700_B(this.H_1990_U, "uHalfPixel", 0.5f / (float)b.R_4764_Y, 0.5f / (float)b.G_564_y);
            this.n_1700_B(33984, cur);
            this.Y_601_j();
            cur = b.v_4262_N;
        }
        GL20.glUseProgram((int)this.N_2525_X);
        this.n_1700_B(this.N_2525_X, "inTexture", 0);
        for (i = iter - 1; i >= 1; --i) {
            b = this.Z_976_R.get(i - 1);
            b.J_1907_R(true);
            this.n_1700_B(this.N_2525_X, "uSize", b.R_4764_Y, b.G_564_y);
            this.n_1700_B(this.N_2525_X, "uOffset", 1.0f + (float)i, 1.0f + (float)i);
            this.n_1700_B(this.N_2525_X, "uHalfPixel", 0.5f / (float)b.R_4764_Y, 0.5f / (float)b.G_564_y);
            this.n_1700_B(this.N_2525_X, "color", 1.0f, 1.0f, 1.0f);
            this.n_1700_B(33984, cur);
            this.Y_601_j();
            cur = b.v_4262_N;
        }
        GL20.glUseProgram((int)0);
        this.l_1233_K = cur;
        c_3005_b.G_564_y().J_1907_R(true);
    }

    private void w_1457_N() {
        P_4249_L b;
        int i;
        int iter = ((Float)this.Y_259_p.J_1907_R()).intValue();
        int cur = this.B_1668_F.v_4262_N;
        this.J_1907_R(iter);
        GL20.glUseProgram((int)this.H_1990_U);
        this.n_1700_B(this.H_1990_U, "inTexture", 0);
        for (i = 0; i < iter; ++i) {
            b = this.Z_976_R.get(i);
            b.R_4764_Y(p_178_J);
            b.J_1907_R(true);
            this.n_1700_B(this.H_1990_U, "uSize", b.R_4764_Y, b.G_564_y);
            this.n_1700_B(this.H_1990_U, "uOffset", 1.0f + (float)i, 1.0f + (float)i);
            this.n_1700_B(this.H_1990_U, "uHalfPixel", 0.5f / (float)b.R_4764_Y, 0.5f / (float)b.G_564_y);
            this.n_1700_B(33984, cur);
            this.Y_601_j();
            cur = b.v_4262_N;
        }
        GL20.glUseProgram((int)this.N_2525_X);
        this.n_1700_B(this.N_2525_X, "inTexture", 0);
        for (i = iter - 1; i >= 1; --i) {
            b = this.Z_976_R.get(i - 1);
            b.J_1907_R(true);
            this.n_1700_B(this.N_2525_X, "uSize", b.R_4764_Y, b.G_564_y);
            this.n_1700_B(this.N_2525_X, "uOffset", 1.0f + (float)i, 1.0f + (float)i);
            this.n_1700_B(this.N_2525_X, "uHalfPixel", 0.5f / (float)b.R_4764_Y, 0.5f / (float)b.G_564_y);
            this.n_1700_B(this.N_2525_X, "color", 1.0f, 1.0f, 1.0f);
            this.n_1700_B(33984, cur);
            this.Y_601_j();
            cur = b.v_4262_N;
        }
        GL20.glUseProgram((int)0);
        this.l_1233_K = cur;
        int glow1 = this.Z_875_P.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.t_4043_B.J_1907_R()).intValue();
        int glow2 = this.Z_875_P.J_1907_R("\u0418\u043d\u0442\u0435\u0440\u0444\u0435\u0439\u0441") ? this.h_1847_R() : ((Integer)this.x_607_J.J_1907_R()).intValue();
        float[] c1 = H_2506_c.P_1922_E(glow1);
        float[] c2 = H_2506_c.P_1922_E(glow2);
        if (this.e_4240_b.t_148_a().booleanValue()) {
            this.n_1700_B(cur, c1, c2);
        } else {
            c_3005_b.G_564_y().J_1907_R(true);
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.J_1907_R(770, 1);
            GL20.glUseProgram((int)this.c_4037_x);
            this.n_1700_B(this.c_4037_x, "bloomTexture", 0);
            this.n_1700_B(this.c_4037_x, "maskTexture", 1);
            this.n_1700_B(this.c_4037_x, "glowColor1", c1[0], c1[1], c1[2]);
            this.n_1700_B(this.c_4037_x, "glowColor2", c2[0], c2[1], c2[2]);
            this.n_1700_B(this.c_4037_x, "exposure", ((Float)this.C_2741_M.J_1907_R()).floatValue());
            this.n_1700_B(this.c_4037_x, "autoColor", this.k_2293_S.t_148_a() != false ? 1 : 0);
            this.n_1700_B(this.c_4037_x, "saturation", ((Float)this.q_2307_F.J_1907_R()).floatValue());
            this.n_1700_B(33985, this.B_1668_F.v_4262_N);
            this.n_1700_B(33984, cur);
            this.Y_601_j();
            GL20.glUseProgram((int)0);
            this.n_1700_B(33985, 0);
            this.n_1700_B(33984, 0);
            lightning.product.c_4037_x.s_2632_s();
        }
    }

    private void n_1700_B(int bloomTex, float[] c1, float[] c2) {
        boolean swinging;
        this.g_164_R = this.n_1700_B(this.g_164_R, false, 2);
        this.X_933_l = this.n_1700_B(this.X_933_l, false, 2);
        long now = System.currentTimeMillis();
        float rawDt = this.z_1333_t > 0L ? Math.min(0.05f, (float)(now - this.z_1333_t) / 1000.0f) : 0.016666668f;
        this.z_1333_t = now;
        float dt = this.O_508_d = this.O_508_d * 0.7f + rawDt * 0.3f;
        float t = (float)(now % 100000L) / 1000.0f;
        float swayFreq = 5.0265484f;
        float upDelta = ((Float)this.d_2427_y.J_1907_R()).floatValue() * dt;
        float amp = ((Float)this.z_1737_N.J_1907_R()).floatValue();
        float swayDelta = (float)(Math.sin(t * swayFreq) - Math.sin((t - dt) * swayFreq)) * amp;
        boolean bl = swinging = f_2247_K.c_3005_b.Y_259_p != null && f_2247_K.c_3005_b.Y_259_p.RealmsCreateRealmScreen;
        if (this.G_624_v.t_148_a().booleanValue() && swinging && !this.A_1038_p) {
            this.r_715_M = now;
        }
        this.A_1038_p = swinging;
        float burstAge = (float)(now - this.r_715_M) / 1000.0f;
        float burst = Math.max(0.0f, 1.0f - burstAge / 0.35f);
        float fadeAdd = burst * ((Float)this.T_2506_i.J_1907_R()).floatValue() * 0.025f;
        this.X_933_l.J_1907_R(true);
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
        lightning.product.c_4037_x.Y_259_p();
        GL20.glUseProgram((int)this.T_3594_S);
        this.n_1700_B(this.T_3594_S, "inTex", 0);
        this.n_1700_B(this.T_3594_S, "offset", swayDelta, upDelta);
        this.n_1700_B(this.T_3594_S, "fade", ((Float)this.n_3318_d.J_1907_R()).floatValue() + fadeAdd);
        this.n_1700_B(this.T_3594_S, "t", t);
        this.n_1700_B(this.T_3594_S, "dt", dt);
        this.n_1700_B(this.T_3594_S, "turb", ((Float)this.v_4276_D.J_1907_R()).floatValue());
        this.n_1700_B(this.T_3594_S, "flickAmp", ((Float)this.d_2461_k.J_1907_R()).floatValue());
        this.n_1700_B(this.T_3594_S, "texSize", this.g_164_R.R_4764_Y, this.g_164_R.G_564_y);
        this.n_1700_B(33984, this.g_164_R.v_4262_N);
        this.Y_601_j();
        GL20.glUseProgram((int)0);
        this.n_1700_B(33984, 0);
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.J_1907_R(1, 771);
        GL20.glUseProgram((int)this.D_4792_h);
        this.n_1700_B(this.D_4792_h, "bloomTexture", 0);
        this.n_1700_B(this.D_4792_h, "glowColor1", c1[0], c1[1], c1[2]);
        this.n_1700_B(this.D_4792_h, "glowColor2", c2[0], c2[1], c2[2]);
        this.n_1700_B(this.D_4792_h, "exposure", ((Float)this.C_2741_M.J_1907_R()).floatValue());
        this.n_1700_B(this.D_4792_h, "autoColor", this.k_2293_S.t_148_a() != false ? 1 : 0);
        this.n_1700_B(this.D_4792_h, "saturation", ((Float)this.q_2307_F.J_1907_R()).floatValue());
        this.n_1700_B(33984, bloomTex);
        this.Y_601_j();
        GL20.glUseProgram((int)0);
        this.n_1700_B(33984, 0);
        if (this.q_4610_l.t_148_a().booleanValue()) {
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.J_1907_R(770, 771);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, ((Float)this.z_4693_k.J_1907_R()).floatValue());
            this.B_1668_F.v_4262_N();
            this.Y_601_j();
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        }
        c_3005_b.G_564_y().J_1907_R(true);
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.J_1907_R(1, 771);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.X_933_l.v_4262_N();
        this.Y_601_j();
        lightning.product.c_4037_x.s_2632_s();
        P_4249_L tmp = this.g_164_R;
        this.g_164_R = this.X_933_l;
        this.X_933_l = tmp;
    }

    private void J_1907_R(int n) {
        int i;
        int fw = c_3005_b.RealmsServerPing().u_2550_I();
        int fh = c_3005_b.RealmsServerPing().M_588_G();
        if (this.Z_976_R.size() < n) {
            this.Z_976_R.forEach(P_4249_L::P_1922_E);
            this.Z_976_R.clear();
            for (i = 0; i < n; ++i) {
                P_4249_L f = new P_4249_L(Math.max(2, fw >> i + 1), Math.max(2, fh >> i + 1), false, p_178_J);
                f.n_1700_B(9729);
                f.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f);
                this.Z_976_R.add(f);
            }
        }
        for (i = 0; i < n; ++i) {
            int w = Math.max(2, fw >> i + 1);
            int h = Math.max(2, fh >> i + 1);
            P_4249_L b = this.Z_976_R.get(i);
            if (b.R_4764_Y == w && b.G_564_y == h) continue;
            b.n_1700_B(w, h, p_178_J);
            b.n_1700_B(9729);
        }
    }

    private void Y_601_j() {
        D_3318_r b = l_3747_P.n_1700_B().R_4764_Y();
        float w = c_3005_b.RealmsServerPing().Q_4569_t();
        float h = c_3005_b.RealmsServerPing().M_182_A();
        b.n_1700_B(7, E_688_b.Q_2552_b);
        b.pos(0.0, h, 0.0).tex(0.0f, 0.0f).endVertex();
        b.pos(w, h, 0.0).tex(1.0f, 0.0f).endVertex();
        b.pos(w, 0.0, 0.0).tex(1.0f, 1.0f).endVertex();
        b.pos(0.0, 0.0, 0.0).tex(0.0f, 1.0f).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
    }

    private void Y_259_p() {
        lightning.product.c_4037_x.u_2550_I(5889);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.z_1737_N();
        lightning.product.c_4037_x.n_1700_B(0.0, c_3005_b.RealmsServerPing().Q_4569_t(), c_3005_b.RealmsServerPing().M_182_A(), 0.0, 1000.0, 3000.0);
        lightning.product.c_4037_x.u_2550_I(5888);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.z_1737_N();
        lightning.product.c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.u_2550_I();
    }

    private void Q_2552_b() {
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        lightning.product.c_4037_x.v_4262_N(0);
        lightning.product.c_4037_x.u_2550_I(5889);
        lightning.product.c_4037_x.d_2461_k();
        lightning.product.c_4037_x.u_2550_I(5888);
        lightning.product.c_4037_x.d_2461_k();
    }

    private void C_2741_M() {
        String V = "#version 120\nvoid main(){gl_TexCoord[0]=gl_MultiTexCoord0;gl_Position=gl_ModelViewProjectionMatrix*gl_Vertex;}";
        this.H_1990_U = this.n_1700_B(V, "#version 120\nuniform sampler2D inTexture;uniform vec2 uOffset,uHalfPixel,uSize;void main(){vec2 u=gl_TexCoord[0].xy,h=uHalfPixel*uOffset;gl_FragColor=(texture2D(inTexture,u)*4.0+texture2D(inTexture,u-h)+texture2D(inTexture,u+h)+texture2D(inTexture,u+vec2(h.x,-h.y))+texture2D(inTexture,u-vec2(h.x,-h.y)))/8.0;}");
        this.N_2525_X = this.n_1700_B(V, "#version 120\nuniform sampler2D inTexture;uniform vec2 uOffset,uHalfPixel,uSize;uniform vec3 color;void main(){vec2 u=gl_TexCoord[0].xy,h=uHalfPixel*uOffset;vec4 s=texture2D(inTexture,u+vec2(-h.x*2.,0.))+texture2D(inTexture,u+vec2(-h.x,h.y))*2.+texture2D(inTexture,u+vec2(0.,h.y*2.))+texture2D(inTexture,u+vec2(h.x,h.y))*2.+texture2D(inTexture,u+vec2(h.x*2.,0.))+texture2D(inTexture,u+vec2(h.x,-h.y))*2.+texture2D(inTexture,u+vec2(0.,-h.y*2.))+texture2D(inTexture,u+vec2(-h.x,-h.y))*2.;vec4 r=s/12.;gl_FragColor=vec4(r.rgb*color,r.a);}");
        this.c_4037_x = this.n_1700_B(V, "#version 120\nuniform sampler2D bloomTexture, maskTexture;\nuniform vec3 glowColor1, glowColor2;\nuniform float exposure;\nuniform int autoColor;\nuniform float saturation;\nvoid main(){\n  vec2 u = gl_TexCoord[0].xy;\n  vec4 b = texture2D(bloomTexture, u);\n  float i = b.a * (1.0 - texture2D(maskTexture, u).a) * exposure;\n  vec3 col;\n  if (autoColor == 1) {\n    vec3 c = b.rgb / max(b.a, 0.001);\n    float m = max(c.r, max(c.g, c.b));\n    if (m > 0.001) c /= m;\n    float l = dot(c, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), c, saturation), 0.0, 1.0);\n  } else {\n    col = mix(glowColor1, glowColor2, u.y);\n  }\n  gl_FragColor = vec4(col, i);\n}");
        this.s_2632_s = this.n_1700_B(V, "#version 120\nuniform sampler2D inTex;\nuniform sampler2D bloomTex;\nuniform vec2 texelSize;\nuniform float width, alpha, rainbowTime, rainbowSpeed, rainbowScale, screenH, saturation;\nuniform int colorMode;\nuniform vec3 solidColor;\nvec3 hue2rgb(float h){\n  vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0/3.0, 1.0/3.0)) * 6.0 - 3.0);\n  return clamp(p - 1.0, 0.0, 1.0);\n}\nvoid main(){\n  vec2 uv = gl_TexCoord[0].xy;\n  vec4 c = texture2D(inTex, uv);\n  if (c.a > 0.01) discard;\n  float maxA = 0.0;\n  for (int x = -2; x <= 2; x++) {\n    for (int y = -2; y <= 2; y++) {\n      if (x == 0 && y == 0) continue;\n      vec2 d = vec2(float(x), float(y)) * texelSize * width;\n      maxA = max(maxA, texture2D(inTex, uv + d).a);\n    }\n  }\n  if (maxA < 0.01) discard;\n  vec3 col;\n  if (colorMode == 1) {\n    float yN = gl_FragCoord.y / max(screenH, 1.0);\n    float h = fract(yN * rainbowScale + rainbowTime * rainbowSpeed);\n    col = hue2rgb(h);\n  } else if (colorMode == 2) {\n    vec4 b = texture2D(bloomTex, uv);\n    vec3 cb = b.rgb / max(b.a, 0.001);\n    float m = max(cb.r, max(cb.g, cb.b));\n    if (m > 0.001) cb /= m;\n    float l = dot(cb, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), cb, saturation), 0.0, 1.0);\n  } else {\n    col = solidColor;\n  }\n  gl_FragColor = vec4(col, maxA * alpha);\n}");
        this.g_2268_R = this.n_1700_B(V, "#version 120\nuniform sampler2D originalTexture;\nuniform vec3 fillColor;\nuniform float fillAlpha, shadingStrength;\nuniform int keepShading;\nuniform int rainbow;\nuniform float rainbowTime, rainbowSpeed, rainbowScale, screenH;\nvec3 hue2rgb(float h){\n  vec3 p = abs(fract(vec3(h) + vec3(0.0, 2.0/3.0, 1.0/3.0)) * 6.0 - 3.0);\n  return clamp(p - 1.0, 0.0, 1.0);\n}\nvoid main(){\n  vec4 s = texture2D(originalTexture, gl_TexCoord[0].xy);\n  if (s.a < 0.01) discard;\n  vec3 f;\n  if (rainbow == 1) {\n    float yN = gl_FragCoord.y / max(screenH, 1.0);\n    float h = fract(yN * rainbowScale + rainbowTime * rainbowSpeed);\n    f = hue2rgb(h);\n  } else {\n    f = fillColor;\n  }\n  if (keepShading == 1) f *= mix(1.0, dot(s.rgb, vec3(0.299, 0.587, 0.114)), shadingStrength);\n  gl_FragColor = vec4(mix(s.rgb, f, fillAlpha), s.a);\n}");
        this.T_3594_S = this.n_1700_B(V, "#version 120\nuniform sampler2D inTex;\nuniform vec2 offset;\nuniform vec2 texSize;\nuniform float fade;\nuniform float t;\nuniform float dt;\nuniform float turb;\nuniform float flickAmp;\nfloat wob(float y, float tt){\n  return sin(y*9.0 + tt*4.3)*0.6\n       + sin(y*17.0 - tt*2.1)*0.3\n       + sin(y*4.0 + tt*1.3)*0.4\n       + sin(y*28.0 + tt*5.7)*0.15;\n}\nvec4 blurSample(vec2 p){\n  vec2 px = 1.0 / max(texSize, vec2(1.0));\n  vec4 c = texture2D(inTex, p) * 0.36;\n  c += texture2D(inTex, p + vec2( px.x, 0.0)) * 0.16;\n  c += texture2D(inTex, p + vec2(-px.x, 0.0)) * 0.16;\n  c += texture2D(inTex, p + vec2(0.0,  px.y)) * 0.16;\n  c += texture2D(inTex, p + vec2(0.0, -px.y)) * 0.16;\n  return c;\n}\nvoid main(){\n  vec2 uv = gl_TexCoord[0].xy;\n  float dxTurb = (wob(uv.y, t) - wob(uv.y, t - dt)) * turb;\n  float yFactor = 0.7 + uv.y * 0.8;\n  vec2 disp = vec2(offset.x + dxTurb, offset.y * yFactor);\n  vec4 c = blurSample(uv - disp);\n  float flick = 1.0 - flickAmp + flickAmp * (0.5 + 0.5*sin(t*14.0) + 0.25*sin(t*9.3));\n  float oldA = c.a;\n  float newA = max(0.0, oldA * (0.985 * flick) - fade);\n  float scale = oldA > 0.001 ? newA / oldA : 0.0;\n  gl_FragColor = vec4(c.rgb * scale, newA);\n}");
        this.D_4792_h = this.n_1700_B(V, "#version 120\nuniform sampler2D bloomTexture;\nuniform vec3 glowColor1;\nuniform vec3 glowColor2;\nuniform float exposure;\nuniform int autoColor;\nuniform float saturation;\nvoid main(){\n  vec2 u = gl_TexCoord[0].xy;\n  vec4 b = texture2D(bloomTexture, u);\n  float a = clamp(b.a * exposure, 0.0, 1.0);\n  vec3 col;\n  if (autoColor == 1) {\n    vec3 c = b.rgb / max(b.a, 0.001);\n    float m = max(c.r, max(c.g, c.b));\n    if (m > 0.001) c /= m;\n    float l = dot(c, vec3(0.299, 0.587, 0.114));\n    col = clamp(mix(vec3(l), c, saturation), 0.0, 1.0);\n  } else {\n    col = mix(glowColor1, glowColor2, u.y);\n  }\n  gl_FragColor = vec4(col * a, a);\n}");
    }

    private int n_1700_B(String vs, String fs) {
        int v = GL20.glCreateShader((int)35633);
        int f = GL20.glCreateShader((int)35632);
        int p = GL20.glCreateProgram();
        GL20.glShaderSource((int)v, (CharSequence)vs);
        GL20.glCompileShader((int)v);
        if (GL20.glGetShaderi((int)v, (int)35713) == 0) {
            System.err.println("Vertex Shader Error: " + GL20.glGetShaderInfoLog((int)v, (int)1024));
        }
        GL20.glShaderSource((int)f, (CharSequence)fs);
        GL20.glCompileShader((int)f);
        if (GL20.glGetShaderi((int)f, (int)35713) == 0) {
            System.err.println("Fragment Shader Error: " + GL20.glGetShaderInfoLog((int)f, (int)1024));
        }
        GL20.glAttachShader((int)p, (int)v);
        GL20.glAttachShader((int)p, (int)f);
        GL20.glLinkProgram((int)p);
        return p;
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        if (this.B_1668_F != null) {
            this.B_1668_F.P_1922_E();
            this.B_1668_F = null;
        }
        if (this.g_164_R != null) {
            this.g_164_R.P_1922_E();
            this.g_164_R = null;
        }
        if (this.X_933_l != null) {
            this.X_933_l.P_1922_E();
            this.X_933_l = null;
        }
        this.Z_976_R.forEach(P_4249_L::P_1922_E);
        this.Z_976_R.clear();
        this.z_1333_t = 0L;
        if (this.H_1990_U != -1) {
            GL20.glDeleteProgram((int)this.H_1990_U);
            GL20.glDeleteProgram((int)this.N_2525_X);
            GL20.glDeleteProgram((int)this.c_4037_x);
            GL20.glDeleteProgram((int)this.g_2268_R);
            GL20.glDeleteProgram((int)this.T_3594_S);
            GL20.glDeleteProgram((int)this.D_4792_h);
            if (this.s_2632_s != -1) {
                GL20.glDeleteProgram((int)this.s_2632_s);
            }
            this.s_2632_s = -1;
            this.H_1990_U = -1;
            this.N_2525_X = -1;
            this.c_4037_x = -1;
            this.g_2268_R = -1;
            this.T_3594_S = -1;
            this.D_4792_h = -1;
            this.RealmsClientConfig.clear();
        }
    }
}



