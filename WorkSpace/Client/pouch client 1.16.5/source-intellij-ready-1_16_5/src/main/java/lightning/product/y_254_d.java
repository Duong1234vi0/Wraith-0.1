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
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.q_3148_R;
import lightning.product.s_4405_m;
import lightning.product.u_530_F;
import net.optifine.shaders.Shaders;

public final class y_254_d
implements MinecraftAccess {
    private static final float n_1700_B = 100.0f;
    private static final int J_1907_R = 28;
    private static final int R_4764_Y = 56;

    public static void n_1700_B(g_221_o matrixStackIn, float partialTicks, boolean optifineShaders) {
        if (!s_4405_m.n_3318_d.n_1700_B() || y_254_d.c_3005_b.Y_601_j == null) {
            return;
        }
        s_4405_m.n_3318_d.J_1907_R();
        float time = (float)y_254_d.c_3005_b.Y_601_j.X_933_l() + partialTicks;
        s_4405_m.n_3318_d.J_1907_R("uTime", time * 0.08f);
        float[] theme = H_2506_c.P_1922_E(q_3148_R.n_1700_B(K_1200_E.J_1907_R));
        s_4405_m.n_3318_d.J_1907_R("uThemeColor", theme[0], theme[1], theme[2]);
        c_4037_x.q_2307_F();
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        y_254_d.n_1700_B(bufferbuilder, matrix4f, 100.0f, 28, 56);
        bufferbuilder.u_1723_Y();
        o_2840_r.n_1700_B(bufferbuilder);
        s_4405_m.n_3318_d.R_4764_Y();
        c_4037_x.k_2293_S();
        c_4037_x.C_2741_M();
        if (optifineShaders) {
            Shaders.disableFog();
        }
    }

    private static void n_1700_B(D_3318_r buffer, D_1098_v mat, float radius, int stacks, int slices) {
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float)(Math.PI * (double)i / (double)stacks);
            float phi1 = (float)(Math.PI * (double)(i + 1) / (double)stacks);
            for (int j = 0; j < slices; ++j) {
                float theta0 = (float)(Math.PI * 2 * (double)j / (double)slices);
                float theta1 = (float)(Math.PI * 2 * (double)(j + 1) / (double)slices);
                y_254_d.n_1700_B(buffer, mat, radius, theta0, phi0);
                y_254_d.n_1700_B(buffer, mat, radius, theta1, phi0);
                y_254_d.n_1700_B(buffer, mat, radius, theta1, phi1);
                y_254_d.n_1700_B(buffer, mat, radius, theta0, phi1);
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

    private y_254_d() {
    }
}


