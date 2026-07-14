/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.I_2176_d;
import lightning.product.LootItemCondition;
import lightning.product.g_2336_b;
import lightning.product.p_4985_U;
import lightning.product.q_4394_S;

public class g_1866_m {
    private final Multimap<String, String> n_1700_B;
    private final Supplier<String> J_1907_R;
    private final I_2176_d R_4764_Y;
    private final Function<g_2336_b, LootItemCondition> G_564_y;
    private final Set<g_2336_b> P_1922_E;
    private final Function<g_2336_b, p_4985_U> u_1723_Y;
    private final Set<g_2336_b> v_4262_N;
    private String w_1484_f;

    public g_1866_m(I_2176_d p_i225889_1_, Function<g_2336_b, LootItemCondition> p_i225889_2_, Function<g_2336_b, p_4985_U> p_i225889_3_) {
        this((Multimap<String, String>)HashMultimap.create(), () -> "", p_i225889_1_, p_i225889_2_, (Set<g_2336_b>)ImmutableSet.of(), p_i225889_3_, (Set<g_2336_b>)ImmutableSet.of());
    }

    public g_1866_m(Multimap<String, String> p_i225888_1_, Supplier<String> p_i225888_2_, I_2176_d p_i225888_3_, Function<g_2336_b, LootItemCondition> p_i225888_4_, Set<g_2336_b> p_i225888_5_, Function<g_2336_b, p_4985_U> p_i225888_6_, Set<g_2336_b> p_i225888_7_) {
        this.n_1700_B = p_i225888_1_;
        this.J_1907_R = p_i225888_2_;
        this.R_4764_Y = p_i225888_3_;
        this.G_564_y = p_i225888_4_;
        this.P_1922_E = p_i225888_5_;
        this.u_1723_Y = p_i225888_6_;
        this.v_4262_N = p_i225888_7_;
    }

    private String J_1907_R() {
        if (this.w_1484_f == null) {
            this.w_1484_f = this.J_1907_R.get();
        }
        return this.w_1484_f;
    }

    public void n_1700_B(String p_227530_1_) {
        this.n_1700_B.put((Object)this.J_1907_R(), (Object)p_227530_1_);
    }

    public g_1866_m J_1907_R(String p_227534_1_) {
        return new g_1866_m(this.n_1700_B, () -> this.J_1907_R() + p_227534_1_, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N);
    }

    public g_1866_m n_1700_B(String p_227531_1_, g_2336_b p_227531_2_) {
        ImmutableSet immutableset = ImmutableSet.builder().addAll(this.v_4262_N).add((Object)p_227531_2_).build();
        return new g_1866_m(this.n_1700_B, () -> this.J_1907_R() + p_227531_1_, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, (Set<g_2336_b>)immutableset);
    }

    public g_1866_m J_1907_R(String p_227535_1_, g_2336_b p_227535_2_) {
        ImmutableSet immutableset = ImmutableSet.builder().addAll(this.P_1922_E).add((Object)p_227535_2_).build();
        return new g_1866_m(this.n_1700_B, () -> this.J_1907_R() + p_227535_1_, this.R_4764_Y, this.G_564_y, (Set<g_2336_b>)immutableset, this.u_1723_Y, this.v_4262_N);
    }

    public boolean n_1700_B(g_2336_b p_227532_1_) {
        return this.v_4262_N.contains(p_227532_1_);
    }

    public boolean J_1907_R(g_2336_b p_227536_1_) {
        return this.P_1922_E.contains(p_227536_1_);
    }

    public Multimap<String, String> n_1700_B() {
        return ImmutableMultimap.copyOf(this.n_1700_B);
    }

    public void n_1700_B(q_4394_S p_227528_1_) {
        this.R_4764_Y.n_1700_B(this, p_227528_1_);
    }

    @Nullable
    public p_4985_U R_4764_Y(g_2336_b p_227539_1_) {
        return this.u_1723_Y.apply(p_227539_1_);
    }

    @Nullable
    public LootItemCondition G_564_y(g_2336_b p_227541_1_) {
        return this.G_564_y.apply(p_227541_1_);
    }

    public g_1866_m n_1700_B(I_2176_d p_227529_1_) {
        return new g_1866_m(this.n_1700_B, this.J_1907_R, p_227529_1_, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N);
    }
}


