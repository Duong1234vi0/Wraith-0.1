/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_3137_a;

public class Motive {
    public static final Motive n_1700_B = Motive.n_1700_B("kebab", 16, 16);
    public static final Motive J_1907_R = Motive.n_1700_B("aztec", 16, 16);
    public static final Motive R_4764_Y = Motive.n_1700_B("alban", 16, 16);
    public static final Motive G_564_y = Motive.n_1700_B("aztec2", 16, 16);
    public static final Motive P_1922_E = Motive.n_1700_B("bomb", 16, 16);
    public static final Motive u_1723_Y = Motive.n_1700_B("plant", 16, 16);
    public static final Motive v_4262_N = Motive.n_1700_B("wasteland", 16, 16);
    public static final Motive w_1484_f = Motive.n_1700_B("pool", 32, 16);
    public static final Motive t_148_a = Motive.n_1700_B("courbet", 32, 16);
    public static final Motive s_956_w = Motive.n_1700_B("sea", 32, 16);
    public static final Motive u_2550_I = Motive.n_1700_B("sunset", 32, 16);
    public static final Motive M_588_G = Motive.n_1700_B("creebet", 32, 16);
    public static final Motive P_4830_p = Motive.n_1700_B("wanderer", 16, 32);
    public static final Motive h_1847_R = Motive.n_1700_B("graham", 16, 32);
    public static final Motive Q_4569_t = Motive.n_1700_B("match", 32, 32);
    public static final Motive M_182_A = Motive.n_1700_B("bust", 32, 32);
    public static final Motive t_1786_h = Motive.n_1700_B("stage", 32, 32);
    public static final Motive multiplayerClientSuggestionProvider = Motive.n_1700_B("void", 32, 32);
    public static final Motive w_1457_N = Motive.n_1700_B("skull_and_roses", 32, 32);
    public static final Motive Y_601_j = Motive.n_1700_B("wither", 32, 32);
    public static final Motive Y_259_p = Motive.n_1700_B("fighters", 64, 32);
    public static final Motive Q_2552_b = Motive.n_1700_B("pointer", 64, 64);
    public static final Motive C_2741_M = Motive.n_1700_B("pigscene", 64, 64);
    public static final Motive k_2293_S = Motive.n_1700_B("burning_skull", 64, 64);
    public static final Motive q_2307_F = Motive.n_1700_B("skeleton", 64, 48);
    public static final Motive Z_875_P = Motive.n_1700_B("donkey_kong", 64, 48);
    private final int c_3005_b;
    private final int H_2857_Y;

    private static Motive n_1700_B(String key, int width, int height) {
        return V_3137_a.n_1700_B(V_3137_a.Z_976_R, key, new Motive(width, height));
    }

    public Motive(int width, int height) {
        this.c_3005_b = width;
        this.H_2857_Y = height;
    }

    public int n_1700_B() {
        return this.c_3005_b;
    }

    public int J_1907_R() {
        return this.H_2857_Y;
    }
}


