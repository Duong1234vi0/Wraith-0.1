/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_3822_q;
import lightning.product.w_2223_C;

public class l_3370_o {
    public static final String n_1700_B = "/assets/minecraft/Pouch/fonts/";
    public static volatile Z_3822_q[] J_1907_R = new Z_3822_q[35];
    public static volatile Z_3822_q[] R_4764_Y = new Z_3822_q[32];
    public static volatile Z_3822_q[] G_564_y = new Z_3822_q[38];
    public static volatile Z_3822_q[] P_1922_E = new Z_3822_q[38];
    public static volatile Z_3822_q[] u_1723_Y = new Z_3822_q[60];
    public static volatile Z_3822_q[] v_4262_N = new Z_3822_q[42];
    public static volatile Z_3822_q[] w_1484_f = new Z_3822_q[80];
    public static volatile Z_3822_q[] t_148_a = new Z_3822_q[60];

    @w_2223_C
    public static void n_1700_B() {
        l_3370_o.n_1700_B(J_1907_R, "sf_medium.ttf", 0.0f);
        l_3370_o.n_1700_B(P_1922_E, "sf_bold.ttf", -1.0f);
        l_3370_o.n_1700_B(R_4764_Y, "sf_regular.ttf", -1.01f);
        l_3370_o.n_1700_B(G_564_y, "sf_semibold.ttf", 0.0f);
        l_3370_o.n_1700_B(u_1723_Y, "icons.ttf", 0.0f);
        l_3370_o.n_1700_B(v_4262_N, "waypoint_icons.ttf", 0.0f);
        l_3370_o.n_1700_B(w_1484_f, "iconz.ttf", 0.0f);
        l_3370_o.n_1700_B(t_148_a, "pouch.ttf", 0.0f);
    }

    private static void n_1700_B(Z_3822_q[] target, String fontFileName, float spacing) {
        for (int i = 1; i < target.length; ++i) {
            target[i] = new Z_3822_q(fontFileName, i, spacing, true);
        }
    }
}

