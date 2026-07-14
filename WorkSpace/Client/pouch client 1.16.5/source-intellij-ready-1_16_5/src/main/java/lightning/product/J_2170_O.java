/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.j_3341_s;
import lightning.product.o_1290_k;

public final class J_2170_O
extends Enum<J_2170_O> {
    public static final /* enum */ J_2170_O n_1700_B = new J_2170_O(0, 1, 2);
    public static final /* enum */ J_2170_O J_1907_R = new J_2170_O(1, 0, 2);
    public static final /* enum */ J_2170_O R_4764_Y = new J_2170_O(0, 2, 1);
    public static final /* enum */ J_2170_O G_564_y = new J_2170_O(1, 2, 0);
    public static final /* enum */ J_2170_O P_1922_E = new J_2170_O(2, 0, 1);
    public static final /* enum */ J_2170_O u_1723_Y = new J_2170_O(2, 1, 0);
    private final int[] v_4262_N;
    private final o_1290_k w_1484_f;
    private static final J_2170_O[][] t_148_a;
    private static final /* synthetic */ J_2170_O[] s_956_w;

    public static J_2170_O[] values() {
        return (J_2170_O[])s_956_w.clone();
    }

    public static J_2170_O valueOf(String name) {
        return Enum.valueOf(J_2170_O.class, name);
    }

    private J_2170_O(int p_i232416_3_, int p_i232416_4_, int p_i232416_5_) {
        this.v_4262_N = new int[]{p_i232416_3_, p_i232416_4_, p_i232416_5_};
        this.w_1484_f = new o_1290_k();
        this.w_1484_f.n_1700_B(0, this.n_1700_B(0), 1.0f);
        this.w_1484_f.n_1700_B(1, this.n_1700_B(1), 1.0f);
        this.w_1484_f.n_1700_B(2, this.n_1700_B(2), 1.0f);
    }

    public J_2170_O n_1700_B(J_2170_O p_239188_1_) {
        return t_148_a[this.ordinal()][p_239188_1_.ordinal()];
    }

    public int n_1700_B(int p_239187_1_) {
        return this.v_4262_N[p_239187_1_];
    }

    public o_1290_k n_1700_B() {
        return this.w_1484_f;
    }

    private static /* synthetic */ J_2170_O[] J_1907_R() {
        return new J_2170_O[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
    }

    static {
        s_956_w = J_2170_O.J_1907_R();
        t_148_a = j_3341_s.n_1700_B(new J_2170_O[J_2170_O.values().length][J_2170_O.values().length], (T p_239190_0_) -> {
            for (J_2170_O triplepermutation : J_2170_O.values()) {
                for (J_2170_O triplepermutation1 : J_2170_O.values()) {
                    J_2170_O triplepermutation2;
                    int[] aint = new int[3];
                    for (int i = 0; i < 3; ++i) {
                        aint[i] = triplepermutation.v_4262_N[triplepermutation1.v_4262_N[i]];
                    }
                    p_239190_0_[triplepermutation.ordinal()][triplepermutation1.ordinal()] = triplepermutation2 = Arrays.stream(J_2170_O.values()).filter(p_239189_1_ -> Arrays.equals(p_239189_1_.v_4262_N, aint)).findFirst().get();
                }
            }
        });
    }
}

