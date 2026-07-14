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
import lightning.product.Pools;
import lightning.product.J_4354_U;
import lightning.product.Q_2369_t;
import lightning.product.X_2241_P;
import lightning.product.ProcessorLists;
import lightning.product.g_1497_f;
import lightning.product.g_2336_b;
import lightning.product.g_4949_C;
import lightning.product.StructurePoolElement;
import lightning.product.p_3067_u;

public class BastionPieces {
    public static final X_2241_P n_1700_B = Pools.n_1700_B(new X_2241_P(new g_2336_b("bastion/starts"), new g_2336_b("empty"), (List<Pair<Function<X_2241_P.n_1700_B, ? extends StructurePoolElement>, Integer>>)ImmutableList.of((Object)Pair.of(StructurePoolElement.J_1907_R("bastion/units/air_base", ProcessorLists.k_2293_S), (Object)1), (Object)Pair.of(StructurePoolElement.J_1907_R("bastion/hoglin_stable/air_base", ProcessorLists.k_2293_S), (Object)1), (Object)Pair.of(StructurePoolElement.J_1907_R("bastion/treasure/big_air_full", ProcessorLists.k_2293_S), (Object)1), (Object)Pair.of(StructurePoolElement.J_1907_R("bastion/bridge/starting_pieces/entrance_base", ProcessorLists.k_2293_S), (Object)1)), X_2241_P.n_1700_B.J_1907_R));

    public static void n_1700_B() {
        g_1497_f.n_1700_B();
        Q_2369_t.n_1700_B();
        p_3067_u.n_1700_B();
        J_4354_U.n_1700_B();
        g_4949_C.n_1700_B();
    }
}


