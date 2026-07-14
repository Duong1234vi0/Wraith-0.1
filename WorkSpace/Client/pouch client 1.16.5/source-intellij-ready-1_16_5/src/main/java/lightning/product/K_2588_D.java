/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class K_2588_D<U> {
    protected final List<n_1700_B<U>> n_1700_B;
    private final Random J_1907_R = new Random();

    public K_2588_D() {
        this(Lists.newArrayList());
    }

    private K_2588_D(List<n_1700_B<U>> p_i231541_1_) {
        this.n_1700_B = Lists.newArrayList(p_i231541_1_);
    }

    public static <U> Codec<K_2588_D<U>> n_1700_B(Codec<U> p_234002_0_) {
        return lightning.product.K_2588_D$n_1700_B.n_1700_B(p_234002_0_).listOf().xmap(K_2588_D::new, p_234001_0_ -> p_234001_0_.n_1700_B);
    }

    public K_2588_D<U> n_1700_B(U p_226313_1_, int p_226313_2_) {
        this.n_1700_B.add(new n_1700_B<U>(p_226313_1_, p_226313_2_));
        return this;
    }

    public K_2588_D<U> n_1700_B() {
        return this.n_1700_B(this.J_1907_R);
    }

    public K_2588_D<U> n_1700_B(Random p_226314_1_) {
        this.n_1700_B.forEach(p_234004_1_ -> p_234004_1_.n_1700_B(p_226314_1_.nextFloat()));
        this.n_1700_B.sort(Comparator.comparingDouble(p_234003_0_ -> p_234003_0_.J_1907_R()));
        return this;
    }

    public boolean J_1907_R() {
        return this.n_1700_B.isEmpty();
    }

    public Stream<U> R_4764_Y() {
        return this.n_1700_B.stream().map(n_1700_B::n_1700_B);
    }

    public U J_1907_R(Random p_226318_1_) {
        return this.n_1700_B(p_226318_1_).R_4764_Y().findFirst().orElseThrow(RuntimeException::new);
    }

    public String toString() {
        return "WeightedList[" + String.valueOf(this.n_1700_B) + "]";
    }

    public static class n_1700_B<T> {
        private final T n_1700_B;
        private final int J_1907_R;
        private double R_4764_Y;

        private n_1700_B(T p_i231542_1_, int p_i231542_2_) {
            this.J_1907_R = p_i231542_2_;
            this.n_1700_B = p_i231542_1_;
        }

        private double J_1907_R() {
            return this.R_4764_Y;
        }

        private void n_1700_B(float p_220648_1_) {
            this.R_4764_Y = -Math.pow(p_220648_1_, 1.0f / (float)this.J_1907_R);
        }

        public T n_1700_B() {
            return this.n_1700_B;
        }

        public String toString() {
            return this.J_1907_R + ":" + String.valueOf(this.n_1700_B);
        }

        public static <E> Codec<n_1700_B<E>> n_1700_B(final Codec<E> p_234008_0_) {
            return new Codec<n_1700_B<E>>(){

                public <T> DataResult<Pair<n_1700_B<E>, T>> decode(DynamicOps<T> p_decode_1_, T p_decode_2_) {
                    Dynamic dynamic = new Dynamic(p_decode_1_, p_decode_2_);
                    return dynamic.get("data").flatMap(arg_0 -> ((Codec)p_234008_0_).parse(arg_0)).map(p_234012_1_ -> new n_1700_B<Object>(p_234012_1_, dynamic.get("weight").asInt(1))).map(p_234013_1_ -> Pair.of((Object)p_234013_1_, (Object)p_decode_1_.empty()));
                }

                public <T> DataResult<T> n_1700_B(n_1700_B<E> p_encode_1_, DynamicOps<T> p_encode_2_, T p_encode_3_) {
                    return p_encode_2_.mapBuilder().add("weight", p_encode_2_.createInt(p_encode_1_.J_1907_R)).add("data", p_234008_0_.encodeStart(p_encode_2_, p_encode_1_.n_1700_B)).build(p_encode_3_);
                }

                public /* synthetic */ DataResult encode(Object object, DynamicOps dynamicOps, Object object2) {
                    return this.n_1700_B((n_1700_B)object, dynamicOps, object2);
                }
            };
        }
    }
}

