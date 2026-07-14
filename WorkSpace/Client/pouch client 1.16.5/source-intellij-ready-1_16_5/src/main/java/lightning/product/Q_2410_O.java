/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import lightning.product.V_4824_J;
import lightning.product.c_1514_x;

public class Q_2410_O<T> {
    private static long G_564_y;
    private final T P_1922_E;
    public final c_1514_x n_1700_B;
    public final long J_1907_R;
    public final V_4824_J R_4764_Y;
    private final long u_1723_Y = G_564_y++;

    public Q_2410_O(c_1514_x positionIn, T p_i48977_2_) {
        this(positionIn, p_i48977_2_, 0L, V_4824_J.G_564_y);
    }

    public Q_2410_O(c_1514_x positionIn, T p_i48978_2_, long scheduledTimeIn, V_4824_J priorityIn) {
        this.n_1700_B = positionIn.toImmutable();
        this.P_1922_E = p_i48978_2_;
        this.J_1907_R = scheduledTimeIn;
        this.R_4764_Y = priorityIn;
    }

    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof Q_2410_O)) {
            return false;
        }
        Q_2410_O nextticklistentry = (Q_2410_O)p_equals_1_;
        return this.n_1700_B.equals(nextticklistentry.n_1700_B) && this.P_1922_E == nextticklistentry.P_1922_E;
    }

    public int hashCode() {
        return this.n_1700_B.hashCode();
    }

    public static <T> Comparator<Q_2410_O<T>> n_1700_B() {
        return Comparator.comparingLong(p_226710_0_ -> p_226710_0_.J_1907_R).thenComparing(p_226709_0_ -> p_226709_0_.R_4764_Y).thenComparingLong(p_226708_0_ -> p_226708_0_.u_1723_Y);
    }

    public String toString() {
        return String.valueOf(this.P_1922_E) + ": " + String.valueOf(this.n_1700_B) + ", " + this.J_1907_R + ", " + String.valueOf((Object)this.R_4764_Y) + ", " + this.u_1723_Y;
    }

    public T J_1907_R() {
        return this.P_1922_E;
    }
}

