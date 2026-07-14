/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lightning.product.L_2946_U;
import lightning.product.S_50_d;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class E_1879_e<E extends r_4811_B>
extends L_2946_U<E> {
    public E_1879_e(List<Pair<Behavior<? super E>, Integer>> p_i50354_1_) {
        this((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), p_i50354_1_);
    }

    public E_1879_e(Map<MemoryModuleType<?>, S_50_d> p_i51502_1_, List<Pair<Behavior<? super E>, Integer>> p_i51502_2_) {
        super(p_i51502_1_, (Set<MemoryModuleType<?>>)ImmutableSet.of(), L_2946_U.n_1700_B.J_1907_R, L_2946_U.J_1907_R.n_1700_B, p_i51502_2_);
    }
}


