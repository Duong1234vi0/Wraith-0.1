/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.x_282_a;

public final class R_2450_T
extends Enum<R_2450_T> {
    public static final /* enum */ R_2450_T n_1700_B = new R_2450_T(0, "peaceful");
    public static final /* enum */ R_2450_T J_1907_R = new R_2450_T(1, "easy");
    public static final /* enum */ R_2450_T R_4764_Y = new R_2450_T(2, "normal");
    public static final /* enum */ R_2450_T G_564_y = new R_2450_T(3, "hard");
    private static final R_2450_T[] P_1922_E;
    private final int u_1723_Y;
    private final String v_4262_N;
    private static final /* synthetic */ R_2450_T[] w_1484_f;

    public static R_2450_T[] values() {
        return (R_2450_T[])w_1484_f.clone();
    }

    public static R_2450_T valueOf(String name) {
        return Enum.valueOf(R_2450_T.class, name);
    }

    private R_2450_T(int difficultyIdIn, String difficultyResourceKeyIn) {
        this.u_1723_Y = difficultyIdIn;
        this.v_4262_N = difficultyResourceKeyIn;
    }

    public int n_1700_B() {
        return this.u_1723_Y;
    }

    public x_282_a J_1907_R() {
        return new F_2904_S("options.difficulty." + this.v_4262_N);
    }

    public static R_2450_T n_1700_B(int id) {
        return P_1922_E[id % P_1922_E.length];
    }

    @Nullable
    public static R_2450_T n_1700_B(String nameIn) {
        for (R_2450_T difficulty : R_2450_T.values()) {
            if (!difficulty.v_4262_N.equals(nameIn)) continue;
            return difficulty;
        }
        return null;
    }

    public String R_4764_Y() {
        return this.v_4262_N;
    }

    public R_2450_T G_564_y() {
        return P_1922_E[(this.u_1723_Y + 1) % P_1922_E.length];
    }

    private static /* synthetic */ R_2450_T[] P_1922_E() {
        return new R_2450_T[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
    }

    static {
        w_1484_f = R_2450_T.P_1922_E();
        P_1922_E = (R_2450_T[])Arrays.stream(R_2450_T.values()).sorted(Comparator.comparingInt(R_2450_T::n_1700_B)).toArray(R_2450_T[]::new);
    }
}

