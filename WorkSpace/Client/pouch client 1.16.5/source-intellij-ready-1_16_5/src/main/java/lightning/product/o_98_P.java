/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.M_1462_J;
import lightning.product.V_3137_a;
import lightning.product.X_585_L;
import lightning.product.g_2336_b;
import lightning.product.q_3277_O;

public class o_98_P<T>
extends M_1462_J {
    private final X_585_L Q_4569_t;
    private final T M_182_A;
    private final q_3277_O<T> t_1786_h;

    protected o_98_P(q_3277_O<T> typeIn, T valueIn, X_585_L formatterIn) {
        super(o_98_P.n_1700_B(typeIn, valueIn));
        this.t_1786_h = typeIn;
        this.Q_4569_t = formatterIn;
        this.M_182_A = valueIn;
    }

    public static <T> String n_1700_B(q_3277_O<T> typeIn, T valueIn) {
        return o_98_P.n_1700_B(V_3137_a.z_1333_t.J_1907_R(typeIn)) + ":" + o_98_P.n_1700_B(typeIn.n_1700_B().J_1907_R(valueIn));
    }

    private static <T> String n_1700_B(@Nullable g_2336_b id) {
        return id.toString().replace(':', '.');
    }

    public q_3277_O<T> G_564_y() {
        return this.t_1786_h;
    }

    public T P_1922_E() {
        return this.M_182_A;
    }

    public String n_1700_B(int number) {
        return this.Q_4569_t.format(number);
    }

    public boolean equals(Object p_equals_1_) {
        return this == p_equals_1_ || p_equals_1_ instanceof o_98_P && Objects.equals(this.n_1700_B(), ((o_98_P)p_equals_1_).n_1700_B());
    }

    public int hashCode() {
        return this.n_1700_B().hashCode();
    }

    public String toString() {
        return "Stat{name=" + this.n_1700_B() + ", formatter=" + String.valueOf(this.Q_4569_t) + "}";
    }
}

