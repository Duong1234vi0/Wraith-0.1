/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;

public class f_2392_k<T> {
    private static final Map<String, f_2392_k<?>> n_1700_B = Collections.synchronizedMap(Maps.newIdentityHashMap());
    private final g_2336_b J_1907_R;
    private final g_2336_b R_4764_Y;

    public static <T> f_2392_k<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> parent, g_2336_b location) {
        return f_2392_k.n_1700_B(parent.R_4764_Y, location);
    }

    public static <T> f_2392_k<V_3137_a<T>> n_1700_B(g_2336_b location) {
        return f_2392_k.n_1700_B(V_3137_a.J_1907_R, location);
    }

    private static <T> f_2392_k<T> n_1700_B(g_2336_b parent, g_2336_b location) {
        String s = (String.valueOf(parent) + ":" + String.valueOf(location)).intern();
        return n_1700_B.computeIfAbsent(s, concatKey -> new f_2392_k(parent, location));
    }

    private f_2392_k(g_2336_b parent, g_2336_b location) {
        this.J_1907_R = parent;
        this.R_4764_Y = location;
    }

    public String toString() {
        return "ResourceKey[" + String.valueOf(this.J_1907_R) + " / " + String.valueOf(this.R_4764_Y) + "]";
    }

    public boolean n_1700_B(f_2392_k<? extends V_3137_a<?>> key) {
        return this.J_1907_R.equals(key.n_1700_B());
    }

    public g_2336_b n_1700_B() {
        return this.R_4764_Y;
    }

    public static <T> Function<g_2336_b, f_2392_k<T>> J_1907_R(f_2392_k<? extends V_3137_a<T>> parent) {
        return location -> f_2392_k.n_1700_B(parent, location);
    }
}

