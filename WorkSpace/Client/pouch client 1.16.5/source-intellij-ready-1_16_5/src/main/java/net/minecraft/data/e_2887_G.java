/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lightning.product.v_3760_Q;

public final class e_2887_G {
    private static final e_2887_G n_1700_B = new e_2887_G((List<v_3760_Q.n_1700_B<?>>)ImmutableList.of());
    private static final Comparator<v_3760_Q.n_1700_B<?>> J_1907_R = Comparator.comparing(p_240192_0_ -> p_240192_0_.n_1700_B().P_1922_E());
    private final List<v_3760_Q.n_1700_B<?>> R_4764_Y;

    public e_2887_G n_1700_B(v_3760_Q.n_1700_B<?> p_240188_1_) {
        return new e_2887_G((List<v_3760_Q.n_1700_B<?>>)ImmutableList.builder().addAll(this.R_4764_Y).add(p_240188_1_).build());
    }

    public e_2887_G n_1700_B(e_2887_G p_240189_1_) {
        return new e_2887_G((List<v_3760_Q.n_1700_B<?>>)ImmutableList.builder().addAll(this.R_4764_Y).addAll(p_240189_1_.R_4764_Y).build());
    }

    private e_2887_G(List<v_3760_Q.n_1700_B<?>> p_i232541_1_) {
        this.R_4764_Y = p_i232541_1_;
    }

    public static e_2887_G n_1700_B() {
        return n_1700_B;
    }

    public static e_2887_G n_1700_B(v_3760_Q.n_1700_B<?> ... p_240190_0_) {
        return new e_2887_G((List<v_3760_Q.n_1700_B<?>>)ImmutableList.copyOf((Object[])p_240190_0_));
    }

    public boolean equals(Object p_equals_1_) {
        return this == p_equals_1_ || p_equals_1_ instanceof e_2887_G && this.R_4764_Y.equals(((e_2887_G)p_equals_1_).R_4764_Y);
    }

    public int hashCode() {
        return this.R_4764_Y.hashCode();
    }

    public String J_1907_R() {
        return this.R_4764_Y.stream().sorted(J_1907_R).map(v_3760_Q.n_1700_B::toString).collect(Collectors.joining(","));
    }

    public String toString() {
        return this.J_1907_R();
    }
}

