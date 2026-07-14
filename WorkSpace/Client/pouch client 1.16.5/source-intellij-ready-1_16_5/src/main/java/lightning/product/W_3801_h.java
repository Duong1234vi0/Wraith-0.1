/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Ambience;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.q_3148_R;
import lightning.product.s_4405_m;
import lightning.product.u_530_F;
import net.optifine.shaders.Shaders;

public final class W_3801_h
implements MinecraftAccess {
    private static final float n_1700_B = 100.0f;
    private static final int J_1907_R = 28;
    private static final int R_4764_Y = 56;

    public static void n_1700_B(g_221_o matrixStackIn, float partialTicks, boolean optifineShaders) {
        s_4405_m shader = s_4405_m.d_2427_y;
        if (Ambience.t_1786_h.J_1907_R("Balatro")) {
            shader = s_4405_m.z_1737_N;
        } else if (Ambience.t_1786_h.J_1907_R("\u041b\u0435\u0442\u043e")) {
            shader = s_4405_m.v_4276_D;
        } else if (Ambience.t_1786_h.J_1907_R("\u0421\u0430\u043a\u0443\u0440\u0430")) {
            shader = s_4405_m.d_2461_k;
        } else if (Ambience.t_1786_h.J_1907_R("Aurora")) {
            shader = s_4405_m.q_4610_l;
        } else if (Ambience.t_1786_h.J_1907_R("\u042d\u0444\u0438\u0440")) {
            shader = s_4405_m.G_624_v;
        } else if (Ambience.t_1786_h.J_1907_R("\u041c\u0435\u0442\u0435\u043b\u044c")) {
            shader = s_4405_m.T_2506_i;
        }
        if (!shader.n_1700_B() || W_3801_h.c_3005_b.Y_601_j == null) {
            return;
        }
        float tickDelta = c_3005_b.RealmsClientConfig();
        float time = ((float)W_3801_h.c_3005_b.Y_601_j.X_933_l() + tickDelta) * ((Float)Ambience.Y_601_j.J_1907_R()).floatValue();
        shader.J_1907_R();
        int theme = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        shader.n_1700_B("u_Color", (float)H_2506_c.n_1700_B(theme) / 255.0f, (float)H_2506_c.J_1907_R(theme) / 255.0f, (float)H_2506_c.R_4764_Y(theme) / 255.0f, 1.0f);
        if (Ambience.t_1786_h.J_1907_R("\u041b\u0435\u0442\u043e")) {
            shader.J_1907_R("u_Night", Ambience.M_182_A() ? 1.0f : 0.0f);
        }
        shader.n_1700_B("u_Scale", ((Float)Ambience.w_1457_N.J_1907_R()).floatValue());
        float timeScale = 0.08f;
        if (Ambience.t_1786_h.J_1907_R("\u041b\u0435\u0442\u043e")) {
            timeScale = 0.105f;
        } else if (Ambience.t_1786_h.J_1907_R("\u0421\u0430\u043a\u0443\u0440\u0430")) {
            timeScale = 0.09f;
        } else if (Ambience.t_1786_h.J_1907_R("Aurora")) {
            timeScale = 0.072f;
        } else if (Ambience.t_1786_h.J_1907_R("\u042d\u0444\u0438\u0440")) {
            timeScale = 0.055f;
        } else if (Ambience.t_1786_h.J_1907_R("\u041c\u0435\u0442\u0435\u043b\u044c")) {
            timeScale = 0.09f;
        }
        shader.J_1907_R("u_Time", time * timeScale);
        c_4037_x.q_2307_F();
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Y_601_j);
        W_3801_h.n_1700_B(buffer, matrix4f, 100.0f, 28, 56);
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        shader.R_4764_Y();
        c_4037_x.k_2293_S();
        c_4037_x.C_2741_M();
        if (optifineShaders) {
            Shaders.disableFog();
        }
    }

    private static void n_1700_B(D_3318_r buf, D_1098_v mat, float radius, int stacks, int slices) {
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float)(Math.PI * (double)i / (double)stacks);
            float phi1 = (float)(Math.PI * (double)(i + 1) / (double)stacks);
            for (int j = 0; j < slices; ++j) {
                float theta0 = (float)(Math.PI * 2 * (double)j / (double)slices);
                float theta1 = (float)(Math.PI * 2 * (double)(j + 1) / (double)slices);
                W_3801_h.n_1700_B(buf, mat, radius, theta0, phi0);
                W_3801_h.n_1700_B(buf, mat, radius, theta1, phi0);
                W_3801_h.n_1700_B(buf, mat, radius, theta1, phi1);
                W_3801_h.n_1700_B(buf, mat, radius, theta0, phi1);
            }
        }
    }

    private static void n_1700_B(D_3318_r buffer, D_1098_v m, float r, float theta, float phi) {
        float sinPhi = u_530_F.n_1700_B(phi);
        float x = r * u_530_F.J_1907_R(theta) * sinPhi;
        float y = r * u_530_F.J_1907_R(phi);
        float z = r * u_530_F.n_1700_B(theta) * sinPhi;
        M_1336_P worldDir = new M_1336_P(x, y, z);
        worldDir.G_564_y();
        int cr = u_530_F.n_1700_B((int)(worldDir.n_1700_B() * 127.5f + 127.5f), 0, 255);
        int cg = u_530_F.n_1700_B((int)(worldDir.J_1907_R() * 127.5f + 127.5f), 0, 255);
        int cb = u_530_F.n_1700_B((int)(worldDir.R_4764_Y() * 127.5f + 127.5f), 0, 255);
        buffer.n_1700_B(m, x, y, z).color(cr, cg, cb, 255).endVertex();
    }

    private W_3801_h() {
    }
}



