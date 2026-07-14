/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.V_3137_a;
import lightning.product.X_585_L;
import lightning.product.o_98_P;
import lightning.product.x_282_a;

public class q_3277_O<T>
implements Iterable<o_98_P<T>> {
    private final V_3137_a<T> n_1700_B;
    private final Map<T, o_98_P<T>> J_1907_R = new IdentityHashMap<T, o_98_P<T>>();
    @Nullable
    private x_282_a R_4764_Y;

    public q_3277_O(V_3137_a<T> registry) {
        this.n_1700_B = registry;
    }

    public boolean n_1700_B(T stat) {
        return this.J_1907_R.containsKey(stat);
    }

    public o_98_P<T> n_1700_B(T p_199077_1_, X_585_L formatter) {
        return this.J_1907_R.computeIfAbsent(p_199077_1_, p_199075_2_ -> new o_98_P<Object>(this, p_199075_2_, formatter));
    }

    public V_3137_a<T> n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public Iterator<o_98_P<T>> iterator() {
        return this.J_1907_R.values().iterator();
    }

    public o_98_P<T> J_1907_R(T stat) {
        return this.n_1700_B(stat, X_585_L.J_1907_R);
    }

    public String J_1907_R() {
        return "stat_type." + V_3137_a.z_1333_t.J_1907_R(this).toString().replace(':', '.');
    }

    public x_282_a R_4764_Y() {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = new F_2904_S(this.J_1907_R());
        }
        return this.R_4764_Y;
    }
}

