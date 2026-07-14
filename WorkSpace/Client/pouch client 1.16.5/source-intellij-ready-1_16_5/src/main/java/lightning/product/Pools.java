/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.function.Function;
import lightning.product.V_3137_a;
import lightning.product.BuiltinRegistries;
import lightning.product.V_4775_U;
import lightning.product.X_2241_P;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.StructurePoolElement;
import lightning.product.BastionPieces;
import lightning.product.VillagePools;

public class Pools {
    public static final f_2392_k<X_2241_P> n_1700_B = f_2392_k.n_1700_B(V_3137_a.V_1446_Y, new g_2336_b("empty"));
    private static final X_2241_P J_1907_R = Pools.n_1700_B(new X_2241_P(n_1700_B.n_1700_B(), n_1700_B.n_1700_B(), (List<Pair<Function<X_2241_P.n_1700_B, ? extends StructurePoolElement>, Integer>>)ImmutableList.of(), X_2241_P.n_1700_B.J_1907_R));

    public static X_2241_P n_1700_B(X_2241_P p_244094_0_) {
        return BuiltinRegistries.n_1700_B(BuiltinRegistries.w_1484_f, p_244094_0_.J_1907_R(), p_244094_0_);
    }

    public static X_2241_P n_1700_B() {
        BastionPieces.n_1700_B();
        V_4775_U.n_1700_B();
        VillagePools.n_1700_B();
        return J_1907_R;
    }

    static {
        Pools.n_1700_B();
    }
}


