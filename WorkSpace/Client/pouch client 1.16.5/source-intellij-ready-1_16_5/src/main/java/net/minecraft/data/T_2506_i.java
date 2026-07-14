/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import javax.annotation.Nullable;

public final class T_2506_i {
    public static final T_2506_i n_1700_B = T_2506_i.n_1700_B("all");
    public static final T_2506_i J_1907_R = T_2506_i.n_1700_B("texture", n_1700_B);
    public static final T_2506_i R_4764_Y = T_2506_i.n_1700_B("particle", J_1907_R);
    public static final T_2506_i G_564_y = T_2506_i.n_1700_B("end", n_1700_B);
    public static final T_2506_i P_1922_E = T_2506_i.n_1700_B("bottom", G_564_y);
    public static final T_2506_i u_1723_Y = T_2506_i.n_1700_B("top", G_564_y);
    public static final T_2506_i v_4262_N = T_2506_i.n_1700_B("front", n_1700_B);
    public static final T_2506_i w_1484_f = T_2506_i.n_1700_B("back", n_1700_B);
    public static final T_2506_i t_148_a = T_2506_i.n_1700_B("side", n_1700_B);
    public static final T_2506_i s_956_w = T_2506_i.n_1700_B("north", t_148_a);
    public static final T_2506_i u_2550_I = T_2506_i.n_1700_B("south", t_148_a);
    public static final T_2506_i M_588_G = T_2506_i.n_1700_B("east", t_148_a);
    public static final T_2506_i P_4830_p = T_2506_i.n_1700_B("west", t_148_a);
    public static final T_2506_i h_1847_R = T_2506_i.n_1700_B("up");
    public static final T_2506_i Q_4569_t = T_2506_i.n_1700_B("down");
    public static final T_2506_i M_182_A = T_2506_i.n_1700_B("cross");
    public static final T_2506_i t_1786_h = T_2506_i.n_1700_B("plant");
    public static final T_2506_i multiplayerClientSuggestionProvider = T_2506_i.n_1700_B("wall", n_1700_B);
    public static final T_2506_i w_1457_N = T_2506_i.n_1700_B("rail");
    public static final T_2506_i Y_601_j = T_2506_i.n_1700_B("wool");
    public static final T_2506_i Y_259_p = T_2506_i.n_1700_B("pattern");
    public static final T_2506_i Q_2552_b = T_2506_i.n_1700_B("pane");
    public static final T_2506_i C_2741_M = T_2506_i.n_1700_B("edge");
    public static final T_2506_i k_2293_S = T_2506_i.n_1700_B("fan");
    public static final T_2506_i q_2307_F = T_2506_i.n_1700_B("stem");
    public static final T_2506_i Z_875_P = T_2506_i.n_1700_B("upperstem");
    public static final T_2506_i c_3005_b = T_2506_i.n_1700_B("crop");
    public static final T_2506_i H_2857_Y = T_2506_i.n_1700_B("dirt");
    public static final T_2506_i A_4115_X = T_2506_i.n_1700_B("fire");
    public static final T_2506_i Y_1740_V = T_2506_i.n_1700_B("lantern");
    public static final T_2506_i t_4043_B = T_2506_i.n_1700_B("platform");
    public static final T_2506_i x_607_J = T_2506_i.n_1700_B("unsticky");
    public static final T_2506_i e_4240_b = T_2506_i.n_1700_B("torch");
    public static final T_2506_i n_3318_d = T_2506_i.n_1700_B("layer0");
    public static final T_2506_i d_2427_y = T_2506_i.n_1700_B("lit_log");
    private final String z_1737_N;
    @Nullable
    private final T_2506_i v_4276_D;

    private static T_2506_i n_1700_B(String name) {
        return new T_2506_i(name, null);
    }

    private static T_2506_i n_1700_B(String name, T_2506_i textureAlias) {
        return new T_2506_i(name, textureAlias);
    }

    private T_2506_i(String name, @Nullable T_2506_i textureAlias) {
        this.z_1737_N = name;
        this.v_4276_D = textureAlias;
    }

    public String n_1700_B() {
        return this.z_1737_N;
    }

    @Nullable
    public T_2506_i J_1907_R() {
        return this.v_4276_D;
    }

    public String toString() {
        return "#" + this.z_1737_N;
    }
}


