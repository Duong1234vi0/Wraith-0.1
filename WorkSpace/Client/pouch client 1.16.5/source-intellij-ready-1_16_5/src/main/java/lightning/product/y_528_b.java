/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.k_2610_C;

public class y_528_b {
    private final String n_1700_B;
    private final ImmutableList<k_2610_C> J_1907_R;

    public static y_528_b n_1700_B(String name) {
        return V_3137_a.B_1668_F.n_1700_B(g_2336_b.J_1907_R(name));
    }

    public y_528_b(k_2610_C ... effectsIn) {
        this((String)null, effectsIn);
    }

    public y_528_b(@Nullable String baseNameIn, k_2610_C ... effectsIn) {
        this.n_1700_B = baseNameIn;
        this.J_1907_R = ImmutableList.copyOf((Object[])effectsIn);
    }

    public String J_1907_R(String prefix) {
        return prefix + (this.n_1700_B == null ? V_3137_a.B_1668_F.J_1907_R(this).J_1907_R() : this.n_1700_B);
    }

    public List<k_2610_C> n_1700_B() {
        return this.J_1907_R;
    }

    public boolean J_1907_R() {
        if (!this.J_1907_R.isEmpty()) {
            for (k_2610_C effectinstance : this.J_1907_R) {
                if (!effectinstance.n_1700_B().n_1700_B()) continue;
                return true;
            }
        }
        return false;
    }
}

