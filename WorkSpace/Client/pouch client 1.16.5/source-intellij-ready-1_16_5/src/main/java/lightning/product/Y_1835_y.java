/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSortedMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Decoder
 *  com.mojang.serialization.Encoder
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.MapDecoder
 *  com.mojang.serialization.MapEncoder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapDecoder;
import com.mojang.serialization.MapEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.P_1008_U;
import lightning.product.v_3760_Q;

public class Y_1835_y<O, S extends P_1008_U<O, S>> {
    private static final Pattern n_1700_B = Pattern.compile("^[a-z0-9_]+$");
    private final O J_1907_R;
    private final ImmutableSortedMap<String, v_3760_Q<?>> R_4764_Y;
    private final ImmutableList<S> G_564_y;

    protected Y_1835_y(Function<O, S> p_i231877_1_, O p_i231877_2_, J_1907_R<O, S> p_i231877_3_, Map<String, v_3760_Q<?>> p_i231877_4_) {
        this.J_1907_R = p_i231877_2_;
        this.R_4764_Y = ImmutableSortedMap.copyOf(p_i231877_4_);
        Supplier<P_1008_U> supplier = () -> (P_1008_U)p_i231877_1_.apply(p_i231877_2_);
        MapCodec<P_1008_U> mapcodec = MapCodec.of((MapEncoder)Encoder.empty(), (MapDecoder)Decoder.unit(supplier));
        for (Map.Entry entry : this.R_4764_Y.entrySet()) {
            mapcodec = Y_1835_y.n_1700_B(mapcodec, supplier, (String)entry.getKey(), (v_3760_Q)entry.getValue());
        }
        MapCodec<P_1008_U> mapcodec1 = mapcodec;
        LinkedHashMap map = Maps.newLinkedHashMap();
        ArrayList list = Lists.newArrayList();
        Stream<List<List<Object>>> stream = Stream.of(Collections.emptyList());
        for (v_3760_Q property : this.R_4764_Y.values()) {
            stream = stream.flatMap(p_200999_1_ -> property.n_1700_B().stream().map(p_200998_2_ -> {
                ArrayList list1 = Lists.newArrayList((Iterable)p_200999_1_);
                list1.add(Pair.of((Object)property, (Object)p_200998_2_));
                return list1;
            }));
        }
        stream.forEach(p_201000_5_ -> {
            ImmutableMap immutablemap = (ImmutableMap)p_201000_5_.stream().collect(ImmutableMap.toImmutableMap(Pair::getFirst, Pair::getSecond));
            P_1008_U s1 = (P_1008_U)p_i231877_3_.create(p_i231877_2_, immutablemap, mapcodec1);
            map.put(immutablemap, s1);
            list.add(s1);
        });
        for (P_1008_U s : list) {
            s.n_1700_B(map);
        }
        this.G_564_y = ImmutableList.copyOf((Collection)list);
    }

    private static <S extends P_1008_U<?, S>, T extends Comparable<T>> MapCodec<S> n_1700_B(MapCodec<S> p_241487_0_, Supplier<S> p_241487_1_, String p_241487_2_, v_3760_Q<T> p_241487_3_) {
        return Codec.mapPair(p_241487_0_, (MapCodec)p_241487_3_.G_564_y().fieldOf(p_241487_2_).setPartial(() -> p_241487_3_.n_1700_B((P_1008_U)p_241487_1_.get()))).xmap(p_241485_1_ -> (P_1008_U)((P_1008_U)p_241485_1_.getFirst()).n_1700_B(p_241487_3_, ((v_3760_Q.n_1700_B)p_241485_1_.getSecond()).J_1907_R()), p_241484_1_ -> Pair.of((Object)p_241484_1_, p_241487_3_.n_1700_B((P_1008_U<?, ?>)p_241484_1_)));
    }

    public ImmutableList<S> n_1700_B() {
        return this.G_564_y;
    }

    public S J_1907_R() {
        return (S)((P_1008_U)this.G_564_y.get(0));
    }

    public O R_4764_Y() {
        return this.J_1907_R;
    }

    public Collection<v_3760_Q<?>> G_564_y() {
        return this.R_4764_Y.values();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("block", this.J_1907_R).add("properties", this.R_4764_Y.values().stream().map(v_3760_Q::P_1922_E).collect(Collectors.toList())).toString();
    }

    @Nullable
    public v_3760_Q<?> n_1700_B(String propertyName) {
        return (v_3760_Q)this.R_4764_Y.get((Object)propertyName);
    }

    public static interface J_1907_R<O, S> {
        public S create(O var1, ImmutableMap<v_3760_Q<?>, Comparable<?>> var2, MapCodec<S> var3);
    }

    public static class n_1700_B<O, S extends P_1008_U<O, S>> {
        private final O n_1700_B;
        private final Map<String, v_3760_Q<?>> J_1907_R = Maps.newHashMap();

        public n_1700_B(O object) {
            this.n_1700_B = object;
        }

        public n_1700_B<O, S> n_1700_B(v_3760_Q<?> ... propertiesIn) {
            for (v_3760_Q<?> property : propertiesIn) {
                this.n_1700_B((v_3760_Q<T>)property);
                this.J_1907_R.put(property.P_1922_E(), property);
            }
            return this;
        }

        private <T extends Comparable<T>> void n_1700_B(v_3760_Q<T> property) {
            String s = property.P_1922_E();
            if (!n_1700_B.matcher(s).matches()) {
                throw new IllegalArgumentException(String.valueOf(this.n_1700_B) + " has invalidly named property: " + s);
            }
            Collection<T> collection = property.n_1700_B();
            if (collection.size() <= 1) {
                throw new IllegalArgumentException(String.valueOf(this.n_1700_B) + " attempted use property " + s + " with <= 1 possible values");
            }
            for (Comparable t : collection) {
                String s1 = property.n_1700_B(t);
                if (n_1700_B.matcher(s1).matches()) continue;
                throw new IllegalArgumentException(String.valueOf(this.n_1700_B) + " has property: " + s + " with invalidly named value: " + s1);
            }
            if (this.J_1907_R.containsKey(s)) {
                throw new IllegalArgumentException(String.valueOf(this.n_1700_B) + " has duplicate property: " + s);
            }
        }

        public Y_1835_y<O, S> n_1700_B(Function<O, S> p_235882_1_, J_1907_R<O, S> p_235882_2_) {
            return new Y_1835_y<O, S>(p_235882_1_, this.n_1700_B, p_235882_2_, this.J_1907_R);
        }
    }
}

