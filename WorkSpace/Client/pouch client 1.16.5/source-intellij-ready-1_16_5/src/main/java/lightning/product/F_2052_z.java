/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.l_3747_P;

public class F_2052_z {
    public static void n_1700_B(g_221_o ms, float x, float y, float z, float size, int color, boolean glow) {
        D_1098_v matrix = ms.R_4764_Y().n_1700_B();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        float h = size * 1.5f;
        float w = size * 0.4f;
        c_4037_x.e_4240_b();
        c_4037_x.Y_601_j();
        c_4037_x.q_2307_F();
        c_4037_x.J_1907_R(false);
        if (glow) {
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        } else {
            c_4037_x.s_2632_s();
        }
        buffer.n_1700_B(6, E_688_b.Y_601_j);
        buffer.n_1700_B(matrix, x, y + h, z).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z + w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x - w, y, z + w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x - w, y, z - w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z - w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z + w).n_1700_B(r, g, b, a).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
        buffer.n_1700_B(6, E_688_b.Y_601_j);
        buffer.n_1700_B(matrix, x, y - h, z).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z - w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x - w, y, z - w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x - w, y, z + w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z + w).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(matrix, x + w, y, z - w).n_1700_B(r, g, b, a).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
        c_4037_x.J_1907_R(true);
        c_4037_x.k_2293_S();
        c_4037_x.x_607_J();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
    }
}

