/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.v_3760_Q;
import net.minecraft.data.e_2887_G;

public abstract class s_956_w {
    private final Map<e_2887_G, List<net.minecraft.data.P_1922_E>> n_1700_B = Maps.newHashMap();

    protected void n_1700_B(e_2887_G p_240140_1_, List<net.minecraft.data.P_1922_E> p_240140_2_) {
        List<net.minecraft.data.P_1922_E> list = this.n_1700_B.put(p_240140_1_, p_240140_2_);
        if (list != null) {
            throw new IllegalStateException("Value " + String.valueOf(p_240140_1_) + " is already defined");
        }
    }

    Map<e_2887_G, List<net.minecraft.data.P_1922_E>> n_1700_B() {
        this.R_4764_Y();
        return ImmutableMap.copyOf(this.n_1700_B);
    }

    private void R_4764_Y() {
        List<v_3760_Q<?>> list = this.J_1907_R();
        Stream<e_2887_G> stream = Stream.of(e_2887_G.n_1700_B());
        for (v_3760_Q<?> property : list) {
            stream = stream.flatMap(p_240138_1_ -> property.R_4764_Y().map(p_240138_1_::n_1700_B));
        }
        List list1 = stream.filter(p_240139_1_ -> !this.n_1700_B.containsKey(p_240139_1_)).collect(Collectors.toList());
        if (!list1.isEmpty()) {
            throw new IllegalStateException("Missing definition for properties: " + String.valueOf(list1));
        }
    }

    abstract List<v_3760_Q<?>> J_1907_R();

    public static <T1 extends Comparable<T1>> G_564_y<T1> n_1700_B(v_3760_Q<T1> p_240133_0_) {
        return new G_564_y<T1>(p_240133_0_);
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> u_1723_Y<T1, T2> n_1700_B(v_3760_Q<T1> p_240134_0_, v_3760_Q<T2> p_240134_1_) {
        return new u_1723_Y<T1, T2>(p_240134_0_, p_240134_1_);
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> P_1922_E<T1, T2, T3> n_1700_B(v_3760_Q<T1> p_240135_0_, v_3760_Q<T2> p_240135_1_, v_3760_Q<T3> p_240135_2_) {
        return new P_1922_E<T1, T2, T3>(p_240135_0_, p_240135_1_, p_240135_2_);
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>> J_1907_R<T1, T2, T3, T4> n_1700_B(v_3760_Q<T1> p_240136_0_, v_3760_Q<T2> p_240136_1_, v_3760_Q<T3> p_240136_2_, v_3760_Q<T4> p_240136_3_) {
        return new J_1907_R<T1, T2, T3, T4>(p_240136_0_, p_240136_1_, p_240136_2_, p_240136_3_);
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>> n_1700_B<T1, T2, T3, T4, T5> n_1700_B(v_3760_Q<T1> p_240137_0_, v_3760_Q<T2> p_240137_1_, v_3760_Q<T3> p_240137_2_, v_3760_Q<T4> p_240137_3_, v_3760_Q<T5> p_240137_4_) {
        return new n_1700_B<T1, T2, T3, T4, T5>(p_240137_0_, p_240137_1_, p_240137_2_, p_240137_3_, p_240137_4_);
    }

    public static class G_564_y<T1 extends Comparable<T1>>
    extends s_956_w {
        private final v_3760_Q<T1> n_1700_B;

        private G_564_y(v_3760_Q<T1> p_i232530_1_) {
            this.n_1700_B = p_i232530_1_;
        }

        @Override
        public List<v_3760_Q<?>> J_1907_R() {
            return ImmutableList.of(this.n_1700_B);
        }

        public G_564_y<T1> n_1700_B(T1 p_240144_1_, List<net.minecraft.data.P_1922_E> p_240144_2_) {
            e_2887_G variantpropertybuilder = e_2887_G.n_1700_B(this.n_1700_B.J_1907_R(p_240144_1_));
            this.n_1700_B(variantpropertybuilder, p_240144_2_);
            return this;
        }

        public G_564_y<T1> n_1700_B(T1 p_240143_1_, net.minecraft.data.P_1922_E p_240143_2_) {
            return this.n_1700_B(p_240143_1_, Collections.singletonList(p_240143_2_));
        }

        public s_956_w n_1700_B(Function<T1, net.minecraft.data.P_1922_E> p_240145_1_) {
            this.n_1700_B.n_1700_B().forEach(p_240146_2_ -> this.n_1700_B(p_240146_2_, (net.minecraft.data.P_1922_E)p_240145_1_.apply(p_240146_2_)));
            return this;
        }
    }

    public static class u_1723_Y<T1 extends Comparable<T1>, T2 extends Comparable<T2>>
    extends s_956_w {
        private final v_3760_Q<T1> n_1700_B;
        private final v_3760_Q<T2> J_1907_R;

        private u_1723_Y(v_3760_Q<T1> p_i232532_1_, v_3760_Q<T2> p_i232532_2_) {
            this.n_1700_B = p_i232532_1_;
            this.J_1907_R = p_i232532_2_;
        }

        @Override
        public List<v_3760_Q<?>> J_1907_R() {
            return ImmutableList.of(this.n_1700_B, this.J_1907_R);
        }

        public u_1723_Y<T1, T2> n_1700_B(T1 p_240150_1_, T2 p_240150_2_, List<net.minecraft.data.P_1922_E> p_240150_3_) {
            e_2887_G variantpropertybuilder = e_2887_G.n_1700_B(this.n_1700_B.J_1907_R(p_240150_1_), this.J_1907_R.J_1907_R(p_240150_2_));
            this.n_1700_B(variantpropertybuilder, p_240150_3_);
            return this;
        }

        public u_1723_Y<T1, T2> n_1700_B(T1 p_240149_1_, T2 p_240149_2_, net.minecraft.data.P_1922_E p_240149_3_) {
            return this.n_1700_B(p_240149_1_, p_240149_2_, Collections.singletonList(p_240149_3_));
        }

        public s_956_w n_1700_B(BiFunction<T1, T2, net.minecraft.data.P_1922_E> p_240152_1_) {
            this.n_1700_B.n_1700_B().forEach(p_240156_2_ -> this.J_1907_R.n_1700_B().forEach(p_240154_3_ -> this.n_1700_B(p_240156_2_, p_240154_3_, (net.minecraft.data.P_1922_E)p_240152_1_.apply(p_240156_2_, p_240154_3_))));
            return this;
        }

        public s_956_w J_1907_R(BiFunction<T1, T2, List<net.minecraft.data.P_1922_E>> p_240155_1_) {
            this.n_1700_B.n_1700_B().forEach(p_240153_2_ -> this.J_1907_R.n_1700_B().forEach(p_240151_3_ -> this.n_1700_B(p_240153_2_, p_240151_3_, (List)p_240155_1_.apply(p_240153_2_, p_240151_3_))));
            return this;
        }
    }

    public static class P_1922_E<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>>
    extends s_956_w {
        private final v_3760_Q<T1> n_1700_B;
        private final v_3760_Q<T2> J_1907_R;
        private final v_3760_Q<T3> R_4764_Y;

        private P_1922_E(v_3760_Q<T1> p_i232534_1_, v_3760_Q<T2> p_i232534_2_, v_3760_Q<T3> p_i232534_3_) {
            this.n_1700_B = p_i232534_1_;
            this.J_1907_R = p_i232534_2_;
            this.R_4764_Y = p_i232534_3_;
        }

        @Override
        public List<v_3760_Q<?>> J_1907_R() {
            return ImmutableList.of(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
        }

        public P_1922_E<T1, T2, T3> n_1700_B(T1 p_240162_1_, T2 p_240162_2_, T3 p_240162_3_, List<net.minecraft.data.P_1922_E> p_240162_4_) {
            e_2887_G variantpropertybuilder = e_2887_G.n_1700_B(this.n_1700_B.J_1907_R(p_240162_1_), this.J_1907_R.J_1907_R(p_240162_2_), this.R_4764_Y.J_1907_R(p_240162_3_));
            this.n_1700_B(variantpropertybuilder, p_240162_4_);
            return this;
        }

        public P_1922_E<T1, T2, T3> n_1700_B(T1 p_240161_1_, T2 p_240161_2_, T3 p_240161_3_, net.minecraft.data.P_1922_E p_240161_4_) {
            return this.n_1700_B(p_240161_1_, p_240161_2_, p_240161_3_, Collections.singletonList(p_240161_4_));
        }

        public s_956_w n_1700_B(R_4764_Y<T1, T2, T3, net.minecraft.data.P_1922_E> p_240160_1_) {
            this.n_1700_B.n_1700_B().forEach(p_240163_2_ -> this.J_1907_R.n_1700_B().forEach(p_240164_3_ -> this.R_4764_Y.n_1700_B().forEach(p_240165_4_ -> this.n_1700_B(p_240163_2_, p_240164_3_, p_240165_4_, (net.minecraft.data.P_1922_E)p_240160_1_.apply(p_240163_2_, p_240164_3_, p_240165_4_)))));
            return this;
        }
    }

    public static class J_1907_R<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>>
    extends s_956_w {
        private final v_3760_Q<T1> n_1700_B;
        private final v_3760_Q<T2> J_1907_R;
        private final v_3760_Q<T3> R_4764_Y;
        private final v_3760_Q<T4> G_564_y;

        private J_1907_R(v_3760_Q<T1> p_i232536_1_, v_3760_Q<T2> p_i232536_2_, v_3760_Q<T3> p_i232536_3_, v_3760_Q<T4> p_i232536_4_) {
            this.n_1700_B = p_i232536_1_;
            this.J_1907_R = p_i232536_2_;
            this.R_4764_Y = p_i232536_3_;
            this.G_564_y = p_i232536_4_;
        }

        @Override
        public List<v_3760_Q<?>> J_1907_R() {
            return ImmutableList.of(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y);
        }

        public J_1907_R<T1, T2, T3, T4> n_1700_B(T1 p_240171_1_, T2 p_240171_2_, T3 p_240171_3_, T4 p_240171_4_, List<net.minecraft.data.P_1922_E> p_240171_5_) {
            e_2887_G variantpropertybuilder = e_2887_G.n_1700_B(this.n_1700_B.J_1907_R(p_240171_1_), this.J_1907_R.J_1907_R(p_240171_2_), this.R_4764_Y.J_1907_R(p_240171_3_), this.G_564_y.J_1907_R(p_240171_4_));
            this.n_1700_B(variantpropertybuilder, p_240171_5_);
            return this;
        }

        public J_1907_R<T1, T2, T3, T4> n_1700_B(T1 p_240170_1_, T2 p_240170_2_, T3 p_240170_3_, T4 p_240170_4_, net.minecraft.data.P_1922_E p_240170_5_) {
            return this.n_1700_B(p_240170_1_, p_240170_2_, p_240170_3_, p_240170_4_, Collections.singletonList(p_240170_5_));
        }
    }

    public static class n_1700_B<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>>
    extends s_956_w {
        private final v_3760_Q<T1> n_1700_B;
        private final v_3760_Q<T2> J_1907_R;
        private final v_3760_Q<T3> R_4764_Y;
        private final v_3760_Q<T4> G_564_y;
        private final v_3760_Q<T5> P_1922_E;

        private n_1700_B(v_3760_Q<T1> p_i232538_1_, v_3760_Q<T2> p_i232538_2_, v_3760_Q<T3> p_i232538_3_, v_3760_Q<T4> p_i232538_4_, v_3760_Q<T5> p_i232538_5_) {
            this.n_1700_B = p_i232538_1_;
            this.J_1907_R = p_i232538_2_;
            this.R_4764_Y = p_i232538_3_;
            this.G_564_y = p_i232538_4_;
            this.P_1922_E = p_i232538_5_;
        }

        @Override
        public List<v_3760_Q<?>> J_1907_R() {
            return ImmutableList.of(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }

        public n_1700_B<T1, T2, T3, T4, T5> n_1700_B(T1 p_240178_1_, T2 p_240178_2_, T3 p_240178_3_, T4 p_240178_4_, T5 p_240178_5_, List<net.minecraft.data.P_1922_E> p_240178_6_) {
            e_2887_G variantpropertybuilder = e_2887_G.n_1700_B(this.n_1700_B.J_1907_R(p_240178_1_), this.J_1907_R.J_1907_R(p_240178_2_), this.R_4764_Y.J_1907_R(p_240178_3_), this.G_564_y.J_1907_R(p_240178_4_), this.P_1922_E.J_1907_R(p_240178_5_));
            this.n_1700_B(variantpropertybuilder, p_240178_6_);
            return this;
        }

        public n_1700_B<T1, T2, T3, T4, T5> n_1700_B(T1 p_240177_1_, T2 p_240177_2_, T3 p_240177_3_, T4 p_240177_4_, T5 p_240177_5_, net.minecraft.data.P_1922_E p_240177_6_) {
            return this.n_1700_B(p_240177_1_, p_240177_2_, p_240177_3_, p_240177_4_, p_240177_5_, Collections.singletonList(p_240177_6_));
        }
    }

    @FunctionalInterface
    public static interface R_4764_Y<P1, P2, P3, R> {
        public R apply(P1 var1, P2 var2, P3 var3);
    }
}

