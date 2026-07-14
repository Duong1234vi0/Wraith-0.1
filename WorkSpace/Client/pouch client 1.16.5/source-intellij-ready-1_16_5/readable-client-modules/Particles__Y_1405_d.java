/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lightning.product.D_3318_r;
import lightning.product.E_3343_g;
import lightning.product.E_4925_L;
import lightning.product.E_688_b;
import lightning.product.F_2860_q;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.H_3699_T;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.b_2152_i;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.f_691_R;
import lightning.product.g_2336_b;
import lightning.product.h_1015_G;
import lightning.product.l_3747_P;
import lightning.product.l_697_B;
import lightning.product.m_2262_U;
import lightning.product.p_1977_n;
import lightning.product.q_3148_R;
import lightning.product.q_366_O;
import lightning.product.u_530_F;
import lightning.product.u_925_K;
import lightning.product.w_2989_N;
import lightning.product.w_3785_E;
import lightning.product.y_2603_k;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class Y_1405_d
extends X_3546_T {
    private static final String[] v_4262_N = new String[]{"\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438", "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438", "\u0414\u043e\u043b\u043b\u0430\u0440\u044b", "\u0422\u044b\u043a\u043e\u0432\u043a\u0438", "\u0411\u0443\u0431\u0435\u043d\u0446\u044b", "\u0421\u0438\u044f\u043d\u0438\u0435", "\u041a\u0443\u0431\u044b", "\u0420\u0430\u043d\u0434\u043e\u043c"};
    private static final String[] w_1484_f = new String[]{"\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438", "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438", "\u0414\u043e\u043b\u043b\u0430\u0440\u044b", "\u0422\u044b\u043a\u043e\u0432\u043a\u0438", "\u0411\u0443\u0431\u0435\u043d\u0446\u044b", "\u0421\u0438\u044f\u043d\u0438\u0435", "\u041a\u0443\u0431\u044b"};
    private static final String t_148_a = "\u041e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u0435";
    private static final String s_956_w = "\u041f\u0440\u0438\u0442\u044f\u0436\u0435\u043d\u0438\u0435";
    private static final String u_2550_I = "\u041e\u0440\u0431\u0438\u0442\u0430";
    private static final String M_588_G = "\u0412\u0438\u0445\u0430\u0440\u044c";
    private static final String P_4830_p = "\u041a\u0430\u0441\u0442\u043e\u043c";
    private final N_4463_r h_1847_R = new N_4463_r("\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0442\u044c \u043f\u0440\u0438", new p_1977_n("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438", true), new p_1977_n("\u0411\u0435\u0433\u0435", false), new p_1977_n("\u041a\u0440\u0438\u0442\u0435", false), new p_1977_n("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u043f\u0435\u0440\u043b\u0430", false), new p_1977_n("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430", false), new p_1977_n("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0441\u0442\u0440\u0435\u043b\u044b", false), new p_1977_n("\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430", false));
    private final q_366_O Q_4569_t = new q_366_O("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438"), v_4262_N);
    private final q_366_O M_182_A = new q_366_O("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u044f", "\u041e\u0431\u044b\u0447\u043d\u043e", () -> this.h_1847_R.J_1907_R("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438"), "\u041e\u0431\u044b\u0447\u043d\u043e", "\u0412\u043d\u0438\u0437");
    private final q_366_O t_1786_h = new q_366_O("\u0411\u0435\u0433", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u0411\u0435\u0433\u0435"), v_4262_N);
    private final q_366_O N_4405_n = new q_366_O("\u041a\u0440\u0438\u0442", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u041a\u0440\u0438\u0442\u0435"), v_4262_N);
    private final q_366_O w_1457_N = new q_366_O("\u041f\u0435\u0440\u043b", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u043f\u0435\u0440\u043b\u0430"), v_4262_N);
    private final q_366_O Y_601_j = new q_366_O("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430"), v_4262_N);
    private final q_366_O Y_259_p = new q_366_O("\u0421\u0442\u0440\u0435\u043b\u0430", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0441\u0442\u0440\u0435\u043b\u044b"), v_4262_N);
    private final q_366_O Q_2552_b = new q_366_O("\u0422\u043e\u0442\u0435\u043c", "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438", () -> this.h_1847_R.J_1907_R("\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430"), v_4262_N);
    private final q_366_O C_2741_M = new q_366_O("\u0410\u043d\u0438\u043c\u0430\u0446\u0438\u044f", "\u041e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u0435", "\u041e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u0435", "\u041f\u0440\u0438\u0442\u044f\u0436\u0435\u043d\u0438\u0435", "\u041e\u0440\u0431\u0438\u0442\u0430", "\u0412\u0438\u0445\u0430\u0440\u044c", "\u041a\u0430\u0441\u0442\u043e\u043c");
    private final I_686_h k_2293_S = new I_686_h("\u0421\u0438\u043b\u0430 \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438", 1.0f, 0.25f, 3.5f, 0.05f, () -> !this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h q_2307_F = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438", 1.0f, 0.25f, 3.0f, 0.05f, () -> !this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h Z_875_P = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c XZ", 1.0f, -3.0f, 3.0f, 0.05f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h t_4043_B = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c Y", 0.0f, -3.0f, 3.0f, 0.05f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h x_607_J = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c \u0433\u0440\u0430\u0432\u0438\u0442\u0430\u0446\u0438\u044f", 0.0f, -3.0f, 3.0f, 0.05f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h e_4240_b = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c \u0437\u0430\u0442\u0443\u0445\u0430\u043d\u0438\u0435", 0.985f, 0.9f, 1.0f, 0.001f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final p_1977_n n_3318_d = new p_1977_n("\u041a\u0430\u0441\u0442\u043e\u043c \u0441\u043f\u0430\u0432\u043d \u0441\u0432\u0435\u0440\u0445\u0443", true, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h d_2427_y = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c \u043f\u0430\u0434\u0435\u043d\u0438\u0435", 0.06f, 0.01f, 0.2f, 0.005f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final I_686_h z_1737_N = new I_686_h("\u041a\u0430\u0441\u0442\u043e\u043c \u0432\u044b\u0441\u043e\u0442\u0430 \u0441\u043f\u0430\u0432\u043d\u0430", 1.2f, 0.2f, 4.0f, 0.1f, () -> this.C_2741_M.J_1907_R(P_4830_p));
    private final p_1977_n v_4276_D = new p_1977_n("\u0413\u043b\u043e\u0443", true);
    private final I_686_h d_2461_k = new I_686_h("\u0421\u043b\u043e\u0451\u0432 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 1.0f, 0.0f, 3.0f, 1.0f, this.v_4276_D::t_148_a);
    private final p_1977_n G_624_v = new p_1977_n("\u041f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u044f \u0430\u043b\u044c\u0444\u044b", false);
    private final p_1977_n T_2506_i = new p_1977_n("\u0420\u0430\u0434\u0443\u0436\u043d\u044b\u0439", false);
    private final I_686_h q_4610_l = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0440\u0430\u0434\u0443\u0433\u0438", 0.4f, 0.05f, 2.0f, 0.05f, this.T_2506_i::t_148_a);
    private final I_686_h z_4693_k = new I_686_h("\u0420\u0430\u0437\u0431\u0440\u043e\u0441 \u0440\u0430\u0434\u0443\u0433\u0438", 0.5f, 0.0f, 1.0f, 0.05f, this.T_2506_i::t_148_a);
    private final I_686_h g_221_o = new I_686_h("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e", 1.0f, 0.1f, 3.0f, 0.1f);
    private final p_1977_n e_2887_G = new p_1977_n("\u0418\u0437\u043c\u0435\u043d\u044f\u0442\u044c \u0440\u0430\u0437\u043c\u0435\u0440", false);
    private final I_686_h B_1668_F = new I_686_h("\u0420\u0430\u0437\u043c\u0435\u0440", 1.0f, 0.5f, 3.0f, 0.1f, this.e_2887_G::t_148_a);
    private final ArrayList<n_1700_B> g_164_R = new ArrayList();
    private boolean X_933_l = false;
    private final Map<String, g_2336_b> Z_976_R = new HashMap<String, g_2336_b>();

    public Y_1405_d() {
        super("Particles", y_2603_k.R_4764_Y);
        this.n_1700_B(this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F, this.Z_875_P, this.t_4043_B, this.x_607_J, this.e_4240_b, this.n_3318_d, this.d_2427_y, this.z_1737_N, this.v_4276_D, this.d_2461_k, this.G_624_v, this.T_2506_i, this.q_4610_l, this.z_4693_k, this.g_221_o, this.e_2887_G, this.B_1668_F);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (Y_1405_d.c_3005_b.Y_601_j == null || Y_1405_d.c_3005_b.Y_259_p == null) {
            this.g_164_R.clear();
            return;
        }
        this.g_164_R.removeIf(particle -> particle.P_4830_p.J_1907_R(particle.u_2550_I));
        if (this.g_164_R.isEmpty()) {
            return;
        }
        w_3785_E rotation = new w_3785_E(Y_1405_d.c_3005_b.O_508_d().J_1907_R.u_1723_Y());
        rotation.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
        M_1336_P right3f = new M_1336_P(1.0f, 0.0f, 0.0f);
        M_1336_P up3f = new M_1336_P(0.0f, 1.0f, 0.0f);
        right3f.n_1700_B(rotation);
        up3f.n_1700_B(rotation);
        long now = System.currentTimeMillis();
        boolean rainbowOn = this.T_2506_i.t_148_a();
        float rSpeed = ((Float)this.q_4610_l.J_1907_R()).floatValue();
        float rSpread = ((Float)this.z_4693_k.J_1907_R()).floatValue();
        boolean pulse = this.G_624_v.t_148_a();
        boolean glowOn = this.v_4276_D.t_148_a();
        int glowCount = ((Float)this.d_2461_k.J_1907_R()).intValue();
        for (n_1700_B particle2 : this.g_164_R) {
            int baseRGB;
            particle2.n_1700_B();
            e_2866_D toCenter = particle2.n_1700_B.G_564_y(Y_1405_d.c_3005_b.O_508_d().J_1907_R.J_1907_R());
            double halfSize = particle2.v_4262_N * 0.5f;
            I_4817_s aabb = new I_4817_s(particle2.n_1700_B.J_1907_R - halfSize, particle2.n_1700_B.R_4764_Y - halfSize, particle2.n_1700_B.G_564_y - halfSize, particle2.n_1700_B.J_1907_R + halfSize, particle2.n_1700_B.R_4764_Y + halfSize, particle2.n_1700_B.G_564_y + halfSize);
            if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) continue;
            e_2866_D halfRight = new e_2866_D(right3f.n_1700_B(), right3f.J_1907_R(), right3f.R_4764_Y()).n_1700_B(halfSize);
            e_2866_D halfUp = new e_2866_D(up3f.n_1700_B(), up3f.J_1907_R(), up3f.R_4764_Y()).n_1700_B(halfSize);
            e_2866_D p0 = toCenter.G_564_y(halfRight).G_564_y(halfUp);
            e_2866_D p1 = toCenter.P_1922_E(halfRight).G_564_y(halfUp);
            e_2866_D p2 = toCenter.P_1922_E(halfRight).P_1922_E(halfUp);
            e_2866_D p3 = toCenter.G_564_y(halfRight).P_1922_E(halfUp);
            float effectiveAlpha = particle2.w_1484_f;
            if (pulse) {
                float pulseMul = (float)((Math.sin((double)(now - particle2.h_1847_R) / 200.0) + 1.0) / 2.0);
                effectiveAlpha = u_530_F.n_1700_B(effectiveAlpha * pulseMul, 0.0f, 1.0f);
            }
            if (rainbowOn) {
                float hue = (float)((double)(now % 100000L) / 1000.0 * (double)rSpeed + (double)(particle2.N_4405_n * rSpread));
                hue -= (float)Math.floor(hue);
                baseRGB = Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
            } else {
                baseRGB = particle2.u_1723_Y & 0xFFFFFF;
            }
            int color = baseRGB | (int)(u_530_F.n_1700_B(effectiveAlpha, 0.0f, 1.0f) * 255.0f) << 24;
            float p0x = (float)p0.J_1907_R;
            float p0y = (float)p0.R_4764_Y;
            float p0z = (float)p0.G_564_y;
            float p1x = (float)p1.J_1907_R;
            float p1y = (float)p1.R_4764_Y;
            float p1z = (float)p1.G_564_y;
            float p2x = (float)p2.J_1907_R;
            float p2y = (float)p2.R_4764_Y;
            float p2z = (float)p2.G_564_y;
            float p3x = (float)p3.J_1907_R;
            float p3y = (float)p3.R_4764_Y;
            float p3z = (float)p3.G_564_y;
            if ("\u041a\u0443\u0431\u044b".equals(particle2.M_588_G)) {
                F_489_x.J_1907_R();
                this.n_1700_B(particle2, color, toCenter);
                continue;
            }
            g_2336_b texture = this.R_4764_Y(particle2.M_588_G);
            if (glowOn && glowCount > 0) {
                float baseGlowScale = 1.6f;
                int baseGlowAlpha = 40;
                float cx = (p0x + p1x + p2x + p3x) * 0.25f;
                float cy = (p0y + p1y + p2y + p3y) * 0.25f;
                float cz = (p0z + p1z + p2z + p3z) * 0.25f;
                for (int i = 0; i < glowCount; ++i) {
                    float layerScale = baseGlowScale * (1.0f + (float)i * 0.35f);
                    int layerAlphaInt = Math.max(5, baseGlowAlpha - i * (baseGlowAlpha / Math.max(1, glowCount)));
                    int glowColor = H_2506_c.n_1700_B(color, (float)layerAlphaInt / 255.0f);
                    float gp0x = cx + (p0x - cx) * layerScale;
                    float gp0y = cy + (p0y - cy) * layerScale;
                    float gp0z = cz + (p0z - cz) * layerScale;
                    float gp1x = cx + (p1x - cx) * layerScale;
                    float gp1y = cy + (p1y - cy) * layerScale;
                    float gp1z = cz + (p1z - cz) * layerScale;
                    float gp2x = cx + (p2x - cx) * layerScale;
                    float gp2y = cy + (p2y - cy) * layerScale;
                    float gp2z = cz + (p2z - cz) * layerScale;
                    float gp3x = cx + (p3x - cx) * layerScale;
                    float gp3y = cy + (p3y - cy) * layerScale;
                    float gp3z = cz + (p3z - cz) * layerScale;
                    F_489_x.n_1700_B(texture, false, gp0x, gp0y, gp0z, gp1x, gp1y, gp1z, gp2x, gp2y, gp2z, gp3x, gp3y, gp3z, glowColor);
                }
            }
            F_489_x.n_1700_B(texture, glowOn, p0x, p0y, p0z, p1x, p1y, p1z, p2x, p2y, p2z, p3x, p3y, p3z, color);
        }
        F_489_x.J_1907_R();
    }

    private void n_1700_B(n_1700_B particle, int color, e_2866_D toCenter) {
        float half = particle.v_4262_N * 0.55f;
        e_2866_D[] offsets = new e_2866_D[]{new e_2866_D(-half, -half, -half), new e_2866_D(half, -half, -half), new e_2866_D(half, half, -half), new e_2866_D(-half, half, -half), new e_2866_D(-half, -half, half), new e_2866_D(half, -half, half), new e_2866_D(half, half, half), new e_2866_D(-half, half, half)};
        e_2866_D[] corners = new e_2866_D[8];
        for (int i = 0; i < offsets.length; ++i) {
            corners[i] = Y_1405_d.n_1700_B(offsets[i], particle.R_4764_Y).P_1922_E(toCenter);
        }
        Y_1405_d.n_1700_B(corners, color, 1.5f);
        Y_1405_d.J_1907_R(corners, H_2506_c.n_1700_B(color, 0.5f), 1.0f);
    }

    private static e_2866_D n_1700_B(e_2866_D point, e_2866_D rotation) {
        double cosY = Math.cos(rotation.R_4764_Y);
        double sinY = Math.sin(rotation.R_4764_Y);
        double x = point.J_1907_R * cosY - point.G_564_y * sinY;
        double z = point.J_1907_R * sinY + point.G_564_y * cosY;
        double cosX = Math.cos(rotation.J_1907_R);
        double sinX = Math.sin(rotation.J_1907_R);
        double y = point.R_4764_Y * cosX - z * sinX;
        z = point.R_4764_Y * sinX + z * cosX;
        double cosZ = Math.cos(rotation.G_564_y);
        double sinZ = Math.sin(rotation.G_564_y);
        double rx = x * cosZ - y * sinZ;
        double ry = x * sinZ + y * cosZ;
        return new e_2866_D(rx, ry, z);
    }

    private static void n_1700_B(e_2866_D[] c, int color, float width) {
        Y_1405_d.n_1700_B(c[0], c[1], color, width);
        Y_1405_d.n_1700_B(c[1], c[2], color, width);
        Y_1405_d.n_1700_B(c[2], c[3], color, width);
        Y_1405_d.n_1700_B(c[3], c[0], color, width);
        Y_1405_d.n_1700_B(c[4], c[5], color, width);
        Y_1405_d.n_1700_B(c[5], c[6], color, width);
        Y_1405_d.n_1700_B(c[6], c[7], color, width);
        Y_1405_d.n_1700_B(c[7], c[4], color, width);
        Y_1405_d.n_1700_B(c[0], c[4], color, width);
        Y_1405_d.n_1700_B(c[1], c[5], color, width);
        Y_1405_d.n_1700_B(c[2], c[6], color, width);
        Y_1405_d.n_1700_B(c[3], c[7], color, width);
    }

    private static void J_1907_R(e_2866_D[] c, int color, float width) {
        Y_1405_d.n_1700_B(c[0], c[6], color, width);
        Y_1405_d.n_1700_B(c[1], c[7], color, width);
        Y_1405_d.n_1700_B(c[2], c[4], color, width);
        Y_1405_d.n_1700_B(c[3], c[5], color, width);
    }

    private static void n_1700_B(e_2866_D a, e_2866_D b, int color, float width) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float bl = (float)(color & 0xFF) / 255.0f;
        float al = (float)(color >> 24 & 0xFF) / 255.0f;
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        c_4037_x.q_2307_F();
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)width);
        l_3747_P tess = l_3747_P.n_1700_B();
        D_3318_r buf = tess.R_4764_Y();
        buf.n_1700_B(1, E_688_b.Y_601_j);
        buf.pos(a.J_1907_R, a.R_4764_Y, a.G_564_y).n_1700_B(r, g, bl, al).endVertex();
        buf.pos(b.J_1907_R, b.R_4764_Y, b.G_564_y).n_1700_B(r, g, bl, al).endVertex();
        tess.J_1907_R();
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)2848);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        if (this.h_1847_R.J_1907_R("\u0411\u0435\u0433\u0435").booleanValue() && u_925_K.n_1700_B()) {
            double speed = Math.sqrt(Y_1405_d.c_3005_b.Y_259_p.T_69_K.J_1907_R * Y_1405_d.c_3005_b.Y_259_p.T_69_K.J_1907_R + Y_1405_d.c_3005_b.Y_259_p.T_69_K.G_564_y * Y_1405_d.c_3005_b.Y_259_p.T_69_K.G_564_y);
            e_2866_D direction = speed < 0.01 ? Y_1405_d.c_3005_b.Y_259_p.i_1610_l().n_1700_B(-1.0) : (Y_1405_d.c_3005_b.Y_259_p.k_578_l() ? Y_1405_d.c_3005_b.Y_259_p.T_69_K.G_564_y().n_1700_B(-1.0) : new e_2866_D(-Y_1405_d.c_3005_b.Y_259_p.T_69_K.J_1907_R / speed, 0.0, -Y_1405_d.c_3005_b.Y_259_p.T_69_K.G_564_y / speed));
            double distanceBehindBase = (Y_1405_d.c_3005_b.Y_259_p.k_578_l() ? 1.2 : 0.5) + (speed > 0.1 ? speed * 1.5 : 0.0);
            int toSpawn = (int)(6.0f * ((Float)this.g_221_o.J_1907_R()).floatValue());
            for (int i = 0; i < toSpawn; ++i) {
                double distanceBehind = distanceBehindBase + (double)F_747_P.G_564_y(-0.15f, 0.15f);
                double offsetX = F_747_P.G_564_y(-0.35f, 0.35f);
                double offsetZ = F_747_P.G_564_y(-0.35f, 0.35f);
                long life = (long)F_747_P.G_564_y(800.0f, 1500.0f);
                double posX = Y_1405_d.c_3005_b.Y_259_p.O_3598_v() + direction.J_1907_R * distanceBehind + offsetX;
                double posY = Y_1405_d.c_3005_b.Y_259_p.k_578_l() ? Y_1405_d.c_3005_b.Y_259_p.X_2960_b() + (double)Y_1405_d.c_3005_b.Y_259_p.v_165_F() / 2.0 + direction.R_4764_Y * distanceBehind + (double)F_747_P.G_564_y(-0.35f, 0.35f) : Y_1405_d.c_3005_b.Y_259_p.X_2960_b() + (double)F_747_P.G_564_y(0.2f, Y_1405_d.c_3005_b.Y_259_p.v_165_F() + 0.1f);
                double posZ = Y_1405_d.c_3005_b.Y_259_p.l_2647_k() + direction.G_564_y * distanceBehind + offsetZ;
                double halfSize = 0.5;
                I_4817_s aabb = new I_4817_s(posX - halfSize, posY - halfSize, posZ - halfSize, posX + halfSize, posY + halfSize, posZ + halfSize);
                if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) continue;
                float baseMx = F_747_P.G_564_y(-0.6f, 0.6f);
                float baseMy = F_747_P.G_564_y(-0.3f, 0.8f);
                float baseMz = F_747_P.G_564_y(-0.6f, 0.6f);
                e_2866_D velocity = direction.n_1700_B(0.15).P_1922_E(new e_2866_D(baseMx * 0.12f, baseMy * 0.12f, baseMz * 0.12f));
                this.n_1700_B(posX, posY, posZ, velocity, q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.25f, life, 1.2f, 3.0E-4f, Y_1405_d.G_564_y((String)this.t_1786_h.J_1907_R()), 0.65f);
            }
        }
        for (N_4263_v entity : Y_1405_d.c_3005_b.Y_601_j.J_1907_R()) {
            H_3699_T arrow;
            E_4925_L trident;
            w_2989_N pearl;
            if (this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u043f\u0435\u0440\u043b\u0430").booleanValue() && entity instanceof w_2989_N && !(pearl = (w_2989_N)entity).M_1641_O()) {
                this.n_1700_B(pearl.s_4990_V(), (String)this.w_1457_N.J_1907_R());
            }
            if (this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0442\u0440\u0435\u0437\u0443\u0431\u0446\u0430").booleanValue() && entity instanceof E_4925_L && this.n_1700_B(trident = (E_4925_L)entity)) {
                this.n_1700_B(trident.s_4990_V(), (String)this.Y_601_j.J_1907_R());
            }
            if (!this.h_1847_R.J_1907_R("\u041f\u0430\u0434\u0435\u043d\u0438\u0438 \u0441\u0442\u0440\u0435\u043b\u044b").booleanValue() || !(entity instanceof H_3699_T) || !this.n_1700_B(arrow = (H_3699_T)entity)) continue;
            this.n_1700_B(arrow.s_4990_V(), (String)this.Y_259_p.J_1907_R());
        }
    }

    private boolean n_1700_B(N_4263_v projectile) {
        if (projectile.M_1641_O() || projectile.I_4348_c().v_4262_N() <= 1.0E-4) {
            return false;
        }
        e_2866_D pos = projectile.s_4990_V();
        e_2866_D motion = projectile.I_4348_c().G_564_y().n_1700_B(0.5);
        c_1514_x currentPos = new c_1514_x(pos);
        c_1514_x frontPos = new c_1514_x(pos.P_1922_E(motion));
        return Y_1405_d.c_3005_b.Y_601_j.getBlockState(currentPos).v_4262_N() && Y_1405_d.c_3005_b.Y_601_j.getBlockState(frontPos).v_4262_N();
    }

    private void n_1700_B(e_2866_D position, String type) {
        double halfSize = 0.5;
        I_4817_s aabb = new I_4817_s(position.J_1907_R - halfSize, position.R_4764_Y - halfSize, position.G_564_y - halfSize, position.J_1907_R + halfSize, position.R_4764_Y + halfSize, position.G_564_y + halfSize);
        if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) {
            return;
        }
        int toSpawn = (int)(4.0f * ((Float)this.g_221_o.J_1907_R()).floatValue());
        for (int i = 0; i < toSpawn; ++i) {
            double distance = 0.0;
            double angle = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            double cosAngle = Math.cos(angle);
            double sinAngle = Math.sin(angle);
            double dx = cosAngle * distance;
            double dz = sinAngle * distance;
            double dy = F_747_P.G_564_y(0.1f, 0.35f);
            e_2866_D particlePos = new e_2866_D(position.J_1907_R + dx, position.R_4764_Y + dy, position.G_564_y + dz);
            long life = (long)F_747_P.G_564_y(2000.0f, 2500.0f);
            float speedMin = F_747_P.G_564_y(0.015f, 0.0375f);
            float speedMax = F_747_P.G_564_y(0.05f, 0.075f);
            double speedFinal = F_747_P.G_564_y(speedMin, speedMax);
            double speedFinalY = speedFinal * 0.4;
            double angleVel = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            double cosVel = Math.cos(angleVel);
            double sinVel = Math.sin(angleVel);
            double velX = cosVel * speedFinal;
            double velZ = sinVel * speedFinal;
            double velY = F_747_P.G_564_y((float)(-speedFinalY), (float)speedFinalY);
            this.n_1700_B(particlePos.J_1907_R, particlePos.R_4764_Y, particlePos.G_564_y, new e_2866_D(velX, velY, velZ), q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.3f, life, 2.0f, 5.0E-5f, Y_1405_d.G_564_y(type));
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (!this.h_1847_R.J_1907_R("\u0411\u0435\u0437\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0438").booleanValue()) {
            return;
        }
        e_2866_D base = new e_2866_D(Y_1405_d.c_3005_b.Y_259_p.O_3598_v(), Y_1405_d.c_3005_b.Y_259_p.X_2960_b() + (double)Y_1405_d.c_3005_b.Y_259_p.v_165_F() / 2.0, Y_1405_d.c_3005_b.Y_259_p.l_2647_k());
        int toSpawn = (int)(10.0f * ((Float)this.g_221_o.J_1907_R()).floatValue());
        this.g_164_R.ensureCapacity(this.g_164_R.size() + toSpawn);
        if (this.M_182_A.J_1907_R("\u0412\u043d\u0438\u0437")) {
            this.n_1700_B(base, toSpawn);
            return;
        }
        for (int i = 0; i < toSpawn; ++i) {
            double distance = F_747_P.G_564_y(7.0f, 40.0f);
            double angle = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            double height = F_747_P.G_564_y(-7.0f, 25.0f);
            e_2866_D offset = new e_2866_D(Math.cos(angle) * distance, height, Math.sin(angle) * distance);
            e_2866_D spawnPos = base.P_1922_E(offset);
            double halfSize = 0.5;
            I_4817_s aabb = new I_4817_s(spawnPos.J_1907_R - halfSize, spawnPos.R_4764_Y - halfSize, spawnPos.G_564_y - halfSize, spawnPos.J_1907_R + halfSize, spawnPos.R_4764_Y + halfSize, spawnPos.G_564_y + halfSize);
            if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) continue;
            e_2866_D originalPosition = base.P_1922_E(offset);
            long life = (long)F_747_P.G_564_y(1500.0f, 2000.0f);
            double speed = Math.random() < 0.8 ? (double)F_747_P.G_564_y(0.015f, 0.03f) : 0.125;
            double phi = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            float smooth = 3.0f;
            e_2866_D velocity = new e_2866_D(Math.cos(phi) * speed, F_747_P.G_564_y((float)(-speed * (double)0.1f), (float)(speed * (double)0.1f)), Math.sin(phi) * speed);
            this.n_1700_B(originalPosition.J_1907_R, originalPosition.R_4764_Y, originalPosition.G_564_y, velocity, q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.4f, life, smooth, 5.0E-6f, Y_1405_d.G_564_y((String)this.Q_4569_t.J_1907_R()));
        }
    }

    private void n_1700_B(e_2866_D base, int batchSize) {
        for (int i = 0; i < batchSize; ++i) {
            double distance = F_747_P.G_564_y(4.0f, 38.0f);
            double angle = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            double height = F_747_P.G_564_y(4.0f, 28.0f);
            e_2866_D offset = new e_2866_D(Math.cos(angle) * distance, height, Math.sin(angle) * distance);
            e_2866_D spawnPos = base.P_1922_E(offset);
            double halfSize = 0.5;
            I_4817_s aabb = new I_4817_s(spawnPos.J_1907_R - halfSize, spawnPos.R_4764_Y - halfSize, spawnPos.G_564_y - halfSize, spawnPos.J_1907_R + halfSize, spawnPos.R_4764_Y + halfSize, spawnPos.G_564_y + halfSize);
            if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) continue;
            long life = (long)F_747_P.G_564_y(2200.0f, 3200.0f);
            float smooth = 2.2f;
            double fall = F_747_P.G_564_y(0.06f, 0.14f);
            double drift = F_747_P.G_564_y(0.012f, 0.045f);
            double phi = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            e_2866_D velocity = new e_2866_D(Math.cos(phi) * drift, -fall, Math.sin(phi) * drift);
            this.n_1700_B(spawnPos.J_1907_R, spawnPos.R_4764_Y, spawnPos.G_564_y, velocity, q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.4f, life, smooth, 2.0E-5f, Y_1405_d.G_564_y((String)this.Q_4569_t.J_1907_R()));
        }
    }

    @Y_1740_V
    public void n_1700_B(l_697_B event) {
        if (!this.h_1847_R.J_1907_R("\u041a\u0440\u0438\u0442\u0435").booleanValue() || event.J_1907_R() == null) {
            return;
        }
        int toSpawn = (int)(50.0f * ((Float)this.g_221_o.J_1907_R()).floatValue());
        this.g_164_R.ensureCapacity(this.g_164_R.size() + toSpawn);
        for (int i = 0; i < toSpawn; ++i) {
            double targetX = event.J_1907_R().O_3598_v() + (double)F_747_P.G_564_y(-0.4f, 0.4f);
            double targetY = event.J_1907_R().X_2960_b() + (double)F_747_P.G_564_y(-0.4f, event.J_1907_R().v_165_F() + 0.4f);
            double targetZ = event.J_1907_R().l_2647_k() + (double)F_747_P.G_564_y(-0.4f, 0.4f);
            double halfSize = 0.5;
            I_4817_s aabb = new I_4817_s(targetX - halfSize, targetY - halfSize, targetZ - halfSize, targetX + halfSize, targetY + halfSize, targetZ + halfSize);
            if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) continue;
            float baseMx = F_747_P.G_564_y(-0.8f, 0.8f) * 2.0f;
            float baseMy = F_747_P.G_564_y(-0.4f, 1.3f);
            float baseMz = F_747_P.G_564_y(-0.8f, 0.8f) * 2.0f;
            float smooth = 0.75f;
            long life = (long)F_747_P.G_564_y(1000.0f, 1250.0f);
            e_2866_D velocity = new e_2866_D(baseMx * 0.07f, baseMy * 0.07f, baseMz * 0.07f);
            this.n_1700_B(targetX, targetY, targetZ, velocity, q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.25f, life, smooth, 5.0E-4f, Y_1405_d.G_564_y((String)this.N_4405_n.J_1907_R()));
        }
    }

    @Y_1740_V
    public void n_1700_B(f_691_R event) {
        this.X_933_l = event.J_1907_R() == Y_1405_d.c_3005_b.Y_259_p;
    }

    @Y_1740_V
    public void n_1700_B(F_2860_q event) {
        if (!this.h_1847_R.J_1907_R("\u0421\u043d\u043e\u0441\u0435 \u0442\u043e\u0442\u0435\u043c\u0430").booleanValue() || this.X_933_l) {
            return;
        }
        double x = event.J_1907_R();
        double y = event.R_4764_Y();
        double z = event.G_564_y();
        double halfSize = 0.5;
        I_4817_s aabb = new I_4817_s(x - halfSize, y - halfSize, z - halfSize, x + halfSize, y + halfSize, z + halfSize);
        if (!Y_1405_d.c_3005_b.u_1723_Y.v_4276_D().isBoundingBoxInFrustum(aabb)) {
            return;
        }
        int color = Math.random() < 0.7 ? -16711936 : -256;
        long life = (long)F_747_P.G_564_y(1500.0f, 2000.0f);
        e_2866_D adjustedVelocity = new e_2866_D(event.P_1922_E() * 0.06, event.u_1723_Y() * 0.06, event.v_4262_N() * 0.06);
        this.n_1700_B(x, y, z, adjustedVelocity, color, 0.3f, life, 0.6f, 2.0E-4, Y_1405_d.G_564_y((String)this.Q_2552_b.J_1907_R()));
        event.n_1700_B(true);
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        this.g_164_R.clear();
    }

    @Override
    public void J_1907_R() {
        this.g_164_R.clear();
        super.J_1907_R();
    }

    private g_2336_b R_4764_Y(String displayName) {
        return this.Z_976_R.computeIfAbsent(displayName, name -> new g_2336_b("Pouch/icons/world_render/" + Y_1405_d.P_1922_E(name)));
    }

    private static String G_564_y(String type) {
        if ("\u0420\u0430\u043d\u0434\u043e\u043c".equals(type)) {
            return w_1484_f[(int)(Math.random() * (double)w_1484_f.length)];
        }
        return type;
    }

    private static String P_1922_E(String displayName) {
        return switch (displayName) {
            case "\u0421\u0435\u0440\u0434\u0435\u0447\u043a\u0438" -> "heart.png";
            case "\u0414\u043e\u043b\u043b\u0430\u0440\u044b" -> "dollar.png";
            case "\u0421\u043d\u0435\u0436\u0438\u043d\u043a\u0438" -> "snowflake.png";
            case "\u0422\u044b\u043a\u043e\u0432\u043a\u0438" -> "pumpkin.png";
            case "\u0411\u0443\u0431\u0435\u043d\u0446\u044b" -> "glow.png";
            case "\u0417\u0432\u0435\u0437\u0434\u043e\u0447\u043a\u0438" -> "star.png";
            default -> "sparkle.png";
        };
    }

    private float n_1700_B(float baseSize) {
        return this.e_2887_G.t_148_a() != false ? baseSize * ((Float)this.B_1668_F.J_1907_R()).floatValue() : baseSize;
    }

    private J_1907_R n_1700_B(double baseGravity) {
        if (this.C_2741_M.J_1907_R(P_4830_p)) {
            return new J_1907_R(P_4830_p, 1.0f, 1.0f, ((Float)this.Z_875_P.J_1907_R()).floatValue(), ((Float)this.t_4043_B.J_1907_R()).floatValue(), ((Float)this.x_607_J.J_1907_R()).floatValue(), ((Float)this.e_4240_b.J_1907_R()).floatValue(), baseGravity);
        }
        return new J_1907_R((String)this.C_2741_M.J_1907_R(), ((Float)this.k_2293_S.J_1907_R()).floatValue(), ((Float)this.q_2307_F.J_1907_R()).floatValue(), 0.0f, 0.0f, 0.0f, 0.9999f, baseGravity);
    }

    private void n_1700_B(double x, double y, double z, e_2866_D velocity, int color, float size, long lifeTime, float smooth, double gravity, String particleType) {
        this.J_1907_R(x, y, z, velocity, color, size, lifeTime, smooth, gravity, particleType, 0.0f);
    }

    private void n_1700_B(double x, double y, double z, e_2866_D velocity, int color, float size, long lifeTime, float smooth, double gravity, String particleType, float bounceFactor) {
        this.J_1907_R(x, y, z, velocity, color, size, lifeTime, smooth, gravity, particleType, bounceFactor);
    }

    private void J_1907_R(double x, double y, double z, e_2866_D velocity, int color, float size, long lifeTime, float smooth, double gravity, String particleType, float bounceFactor) {
        e_2866_D safePos;
        size = this.n_1700_B(size);
        double spawnX = x;
        double spawnY = y;
        double spawnZ = z;
        e_2866_D spawnVelocity = velocity;
        if (this.C_2741_M.J_1907_R(P_4830_p) && this.n_3318_d.t_148_a().booleanValue()) {
            spawnY += (double)((Float)this.z_1737_N.J_1907_R()).floatValue();
            double horizScale = 0.15;
            spawnVelocity = new e_2866_D(velocity.J_1907_R * horizScale + (double)F_747_P.G_564_y(-0.003f, 0.003f), -((Float)this.d_2427_y.J_1907_R()).floatValue() + F_747_P.G_564_y(-0.01f, 0.005f), velocity.G_564_y * horizScale + (double)F_747_P.G_564_y(-0.003f, 0.003f));
        }
        if ((safePos = lightning.product.Y_1405_d$n_1700_B.n_1700_B(spawnX, spawnY, spawnZ, size)) != null) {
            this.g_164_R.add(new n_1700_B(safePos.J_1907_R, safePos.R_4764_Y, safePos.G_564_y, spawnVelocity, color, size, lifeTime, smooth, gravity, particleType, bounceFactor, this.n_1700_B(gravity)));
        }
    }

    public static class n_1700_B {
        e_2866_D n_1700_B;
        e_2866_D J_1907_R;
        e_2866_D R_4764_Y;
        e_2866_D G_564_y;
        final e_2866_D P_1922_E;
        int u_1723_Y;
        float v_4262_N;
        float w_1484_f = 1.0f;
        float t_148_a;
        float s_956_w;
        long u_2550_I;
        String M_588_G;
        final V_4557_X P_4830_p = new V_4557_X();
        final long h_1847_R;
        final J_1907_R Q_4569_t;
        final double M_182_A;
        final double t_1786_h;
        final float N_4405_n;
        private long w_1457_N;
        private final double Y_601_j;

        public n_1700_B(double x, double y, double z, e_2866_D velocity, int color, float size, long lifeTime, float smooth, double gravity, String particleType, float bounceFactor, J_1907_R motionProfile) {
            this.P_1922_E = this.n_1700_B = new e_2866_D(x, y, z);
            this.J_1907_R = velocity;
            this.u_1723_Y = color;
            this.v_4262_N = size;
            this.u_2550_I = lifeTime;
            this.M_588_G = particleType;
            this.h_1847_R = System.currentTimeMillis();
            this.P_4830_p.n_1700_B();
            this.w_1457_N = System.nanoTime();
            this.t_148_a = smooth;
            this.Y_601_j = gravity;
            this.s_956_w = bounceFactor;
            this.Q_4569_t = motionProfile;
            this.M_182_A = Math.toRadians(F_747_P.G_564_y(0.0f, 360.0f));
            this.t_1786_h = Math.random() < 0.5 ? -1.0 : 1.0;
            this.N_4405_n = (float)Math.random();
            this.R_4764_Y = e_2866_D.n_1700_B;
            this.G_564_y = new e_2866_D(F_747_P.G_564_y(-0.04f, 0.04f), F_747_P.G_564_y(-0.04f, 0.04f), F_747_P.G_564_y(-0.04f, 0.04f));
        }

        public void n_1700_B() {
            double newZ;
            double newY;
            long now = System.nanoTime();
            double delta = (double)(now - this.w_1457_N) / 1.0E9;
            this.w_1457_N = now;
            float progress = Math.min(1.0f, (float)this.P_4830_p.J_1907_R() / (float)this.u_2550_I);
            double factor = Y_1405_d.P_4830_p.equals(this.Q_4569_t.n_1700_B) ? 1.0 : Math.pow(1.0 - (double)progress, this.t_148_a);
            double scale = delta * 60.0 * (double)this.Q_4569_t.R_4764_Y;
            this.J_1907_R = this.n_1700_B(scale, progress);
            double vx = this.J_1907_R.J_1907_R;
            double vy = this.J_1907_R.R_4764_Y;
            double vz = this.J_1907_R.G_564_y;
            double px = this.n_1700_B.J_1907_R;
            double newX = px + vx * factor * scale;
            double py = this.n_1700_B.R_4764_Y;
            double pz = this.n_1700_B.G_564_y;
            if (lightning.product.Y_1405_d$n_1700_B.n_1700_B(newX, py, pz, this.v_4262_N) == null) {
                vx *= -0.8;
                newX = px;
            }
            if (lightning.product.Y_1405_d$n_1700_B.n_1700_B(newX, newY = py + vy * factor * scale, pz, this.v_4262_N) == null) {
                vy = this.s_956_w > 0.0f ? (vy *= -(1.5 + (double)this.s_956_w)) : (vy *= -1.5);
                newY = py;
            }
            if (lightning.product.Y_1405_d$n_1700_B.n_1700_B(newX, newY, newZ = pz + vz * factor * scale, this.v_4262_N) == null) {
                vz *= -0.8;
                newZ = pz;
            }
            this.n_1700_B = new e_2866_D(newX, newY, newZ);
            double effGravity = switch (this.Q_4569_t.n_1700_B) {
                case Y_1405_d.u_2550_I -> this.Q_4569_t.w_1484_f * 0.4;
                case Y_1405_d.M_588_G -> this.Q_4569_t.w_1484_f * 0.18;
                case Y_1405_d.s_956_w -> this.Q_4569_t.w_1484_f * 0.75;
                case Y_1405_d.P_4830_p -> Math.max(this.Q_4569_t.w_1484_f + (double)this.Q_4569_t.u_1723_Y * 2.5E-4, 8.0E-5);
                default -> this.Q_4569_t.w_1484_f;
            };
            double damping = Y_1405_d.P_4830_p.equals(this.Q_4569_t.n_1700_B) ? Math.max((double)this.Q_4569_t.v_4262_N, 0.9995) : 0.9999;
            this.J_1907_R = new e_2866_D(vx * damping, vy * damping - effGravity, vz * damping);
            this.R_4764_Y = this.R_4764_Y.P_1922_E(this.G_564_y.n_1700_B(scale));
            this.G_564_y = this.G_564_y.n_1700_B(0.98);
            this.w_1484_f = 1.0f - progress;
        }

        private e_2866_D n_1700_B(double scale, float progress) {
            e_2866_D nextVelocity = this.J_1907_R;
            e_2866_D radial = this.n_1700_B.G_564_y(this.P_1922_E);
            e_2866_D horizontal = new e_2866_D(radial.J_1907_R, 0.0, radial.G_564_y);
            switch (this.Q_4569_t.n_1700_B) {
                case "\u041e\u0442\u0442\u0430\u043b\u043a\u0438\u0432\u0430\u043d\u0438\u0435": {
                    e_2866_D outward = lightning.product.Y_1405_d$n_1700_B.n_1700_B(radial, this.J_1907_R, this.M_182_A);
                    nextVelocity = nextVelocity.P_1922_E(outward.n_1700_B(0.00105 * (double)this.Q_4569_t.J_1907_R * scale));
                    break;
                }
                case "\u041f\u0440\u0438\u0442\u044f\u0436\u0435\u043d\u0438\u0435": {
                    e_2866_D inward = lightning.product.Y_1405_d$n_1700_B.n_1700_B(this.P_1922_E.G_564_y(this.n_1700_B), this.J_1907_R.n_1700_B(-1.0), this.M_182_A + Math.PI);
                    nextVelocity = nextVelocity.P_1922_E(inward.n_1700_B(0.001 * (double)this.Q_4569_t.J_1907_R * scale));
                    break;
                }
                case "\u041e\u0440\u0431\u0438\u0442\u0430": {
                    e_2866_D orbitBasis = lightning.product.Y_1405_d$n_1700_B.n_1700_B(horizontal, this.J_1907_R, this.M_182_A);
                    e_2866_D tangent = new e_2866_D(-orbitBasis.G_564_y, 0.0, orbitBasis.J_1907_R).G_564_y().n_1700_B(this.t_1786_h);
                    double tangentForce = (0.00135 + Math.sin((double)progress * Math.PI + this.M_182_A) * 1.5E-4) * (double)this.Q_4569_t.J_1907_R * scale;
                    double centerPull = 5.5E-4 * (double)Math.max(0.5f, this.Q_4569_t.J_1907_R) * scale;
                    double lift = Math.sin((double)progress * Math.PI * 2.0 + this.M_182_A) * 6.5E-4 * (double)this.Q_4569_t.J_1907_R;
                    nextVelocity = nextVelocity.P_1922_E(tangent.n_1700_B(tangentForce));
                    nextVelocity = nextVelocity.P_1922_E(orbitBasis.n_1700_B(-centerPull));
                    nextVelocity = nextVelocity.J_1907_R(0.0, lift, 0.0);
                    break;
                }
                case "\u0412\u0438\u0445\u0430\u0440\u044c": {
                    e_2866_D whirlBasis = lightning.product.Y_1405_d$n_1700_B.n_1700_B(horizontal, this.J_1907_R, this.M_182_A);
                    e_2866_D tangent = new e_2866_D(-whirlBasis.G_564_y, 0.0, whirlBasis.J_1907_R).G_564_y().n_1700_B(this.t_1786_h);
                    double tangentForce = (0.00195 + Math.sin((double)progress * Math.PI * 2.0 + this.M_182_A) * 3.5E-4) * (double)this.Q_4569_t.J_1907_R * scale;
                    double centerPull = (0.00105 + horizontal.u_1723_Y() * 3.5E-4) * (double)this.Q_4569_t.J_1907_R * scale;
                    double lift = (0.00115 + Math.cos((double)progress * Math.PI * 3.0 + this.M_182_A) * 3.0E-4) * (double)this.Q_4569_t.J_1907_R;
                    nextVelocity = nextVelocity.P_1922_E(tangent.n_1700_B(tangentForce));
                    nextVelocity = nextVelocity.P_1922_E(whirlBasis.n_1700_B(-centerPull));
                    nextVelocity = nextVelocity.J_1907_R(0.0, lift, 0.0);
                    break;
                }
                case "\u041a\u0430\u0441\u0442\u043e\u043c": {
                    e_2866_D customBasis = lightning.product.Y_1405_d$n_1700_B.n_1700_B(horizontal, this.J_1907_R, this.M_182_A);
                    double horizontalForce = (double)this.Q_4569_t.G_564_y * 8.5E-4 * scale;
                    double verticalForce = (double)this.Q_4569_t.P_1922_E * 6.5E-4 * scale;
                    nextVelocity = nextVelocity.P_1922_E(customBasis.n_1700_B(horizontalForce));
                    nextVelocity = nextVelocity.J_1907_R(0.0, verticalForce, 0.0);
                    break;
                }
            }
            return nextVelocity;
        }

        private static e_2866_D n_1700_B(e_2866_D primary, e_2866_D secondary, double angle) {
            if (primary.v_4262_N() > 1.0E-6) {
                return primary.G_564_y();
            }
            if (secondary.v_4262_N() > 1.0E-6) {
                return secondary.G_564_y();
            }
            return new e_2866_D(Math.cos(angle), 0.0, Math.sin(angle));
        }

        static e_2866_D n_1700_B(double x, double y, double z, float size) {
            if (b_2152_i.c_3005_b.Y_601_j == null) {
                return null;
            }
            double half = (double)size * 0.5;
            int minX = u_530_F.R_4764_Y(x - half);
            int maxX = u_530_F.R_4764_Y(x + half);
            int minY = u_530_F.R_4764_Y(y - half);
            int maxY = u_530_F.R_4764_Y(y + half);
            int minZ = u_530_F.R_4764_Y(z - half);
            int maxZ = u_530_F.R_4764_Y(z + half);
            c_1514_x.n_1700_B pos = new c_1514_x.n_1700_B();
            for (int bx = minX; bx <= maxX; ++bx) {
                for (int by = minY; by <= maxY; ++by) {
                    for (int bz = minZ; bz <= maxZ; ++bz) {
                        K_4074_S state = b_2152_i.c_3005_b.Y_601_j.getBlockState(pos.n_1700_B(bx, by, bz));
                        if (state.v_4262_N()) continue;
                        return null;
                    }
                }
            }
            return new e_2866_D(x, y, z);
        }

        @Generated
        public e_2866_D J_1907_R() {
            return this.n_1700_B;
        }

        @Generated
        public e_2866_D R_4764_Y() {
            return this.J_1907_R;
        }

        @Generated
        public e_2866_D G_564_y() {
            return this.R_4764_Y;
        }

        @Generated
        public e_2866_D P_1922_E() {
            return this.G_564_y;
        }

        @Generated
        public e_2866_D u_1723_Y() {
            return this.P_1922_E;
        }

        @Generated
        public int v_4262_N() {
            return this.u_1723_Y;
        }

        @Generated
        public float w_1484_f() {
            return this.v_4262_N;
        }

        @Generated
        public float t_148_a() {
            return this.w_1484_f;
        }

        @Generated
        public float s_956_w() {
            return this.t_148_a;
        }

        @Generated
        public float u_2550_I() {
            return this.s_956_w;
        }

        @Generated
        public long M_588_G() {
            return this.u_2550_I;
        }

        @Generated
        public String P_4830_p() {
            return this.M_588_G;
        }

        @Generated
        public V_4557_X h_1847_R() {
            return this.P_4830_p;
        }

        @Generated
        public long Q_4569_t() {
            return this.h_1847_R;
        }

        @Generated
        public J_1907_R M_182_A() {
            return this.Q_4569_t;
        }

        @Generated
        public double t_1786_h() {
            return this.M_182_A;
        }

        @Generated
        public double N_4405_n() {
            return this.t_1786_h;
        }

        @Generated
        public float w_1457_N() {
            return this.N_4405_n;
        }

        @Generated
        public long Y_601_j() {
            return this.w_1457_N;
        }

        @Generated
        public double Y_259_p() {
            return this.Y_601_j;
        }
    }

    private static class J_1907_R {
        final String n_1700_B;
        final float J_1907_R;
        final float R_4764_Y;
        final float G_564_y;
        final float P_1922_E;
        final float u_1723_Y;
        final float v_4262_N;
        final double w_1484_f;

        J_1907_R(String mode, float forceMultiplier, float speedMultiplier, float customForceXZ, float customForceY, float customGravity, float customDamping, double baseGravity) {
            this.n_1700_B = mode;
            this.J_1907_R = forceMultiplier;
            this.R_4764_Y = speedMultiplier;
            this.G_564_y = customForceXZ;
            this.P_1922_E = customForceY;
            this.u_1723_Y = customGravity;
            this.v_4262_N = customDamping;
            this.w_1484_f = baseGravity;
        }
    }
}

