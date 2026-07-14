/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.datafixers.util.Pair
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;
import lightning.product.T_2915_h;
import lightning.product.j_3341_s;
import lightning.product.v_3760_Q;
import net.minecraft.data.P_1922_E;
import net.minecraft.data.Q_2552_b;
import net.minecraft.data.e_2887_G;
import net.minecraft.data.s_956_w;

public class w_1457_N
implements Q_2552_b {
    private final T_2915_h n_1700_B;
    private final List<P_1922_E> J_1907_R;
    private final Set<v_3760_Q<?>> R_4764_Y = Sets.newHashSet();
    private final List<s_956_w> G_564_y = Lists.newArrayList();

    private w_1457_N(T_2915_h p_i232529_1_, List<P_1922_E> p_i232529_2_) {
        this.n_1700_B = p_i232529_1_;
        this.J_1907_R = p_i232529_2_;
    }

    public w_1457_N n_1700_B(s_956_w p_240125_1_) {
        p_240125_1_.J_1907_R().forEach(p_240122_1_ -> {
            if (this.n_1700_B.t_1786_h().n_1700_B(p_240122_1_.P_1922_E()) != p_240122_1_) {
                throw new IllegalStateException("Property " + String.valueOf(p_240122_1_) + " is not defined for block " + String.valueOf(this.n_1700_B));
            }
            if (!this.R_4764_Y.add((v_3760_Q<?>)p_240122_1_)) {
                throw new IllegalStateException("Values of property " + String.valueOf(p_240122_1_) + " already defined for block " + String.valueOf(this.n_1700_B));
            }
        });
        this.G_564_y.add(p_240125_1_);
        return this;
    }

    public JsonElement J_1907_R() {
        Stream<Object> stream = Stream.of(Pair.of((Object)e_2887_G.n_1700_B(), this.J_1907_R));
        for (s_956_w blockstatevariantbuilder : this.G_564_y) {
            Map<e_2887_G, List<P_1922_E>> map = blockstatevariantbuilder.n_1700_B();
            stream = stream.flatMap(p_240130_1_ -> map.entrySet().stream().map(p_240124_1_ -> {
                e_2887_G variantpropertybuilder = ((e_2887_G)p_240130_1_.getFirst()).n_1700_B((e_2887_G)p_240124_1_.getKey());
                List<P_1922_E> list = w_1457_N.n_1700_B((List)p_240130_1_.getSecond(), (List)p_240124_1_.getValue());
                return Pair.of((Object)variantpropertybuilder, list);
            }));
        }
        TreeMap map1 = new TreeMap();
        stream.forEach(p_240129_1_ -> {
            JsonElement jsonelement = map1.put(((e_2887_G)p_240129_1_.getFirst()).J_1907_R(), P_1922_E.n_1700_B((List)p_240129_1_.getSecond()));
        });
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("variants", (JsonElement)j_3341_s.n_1700_B(new JsonObject(), (T p_240128_1_) -> map1.forEach((arg_0, arg_1) -> ((JsonObject)p_240128_1_).add(arg_0, arg_1))));
        return jsonobject;
    }

    private static List<P_1922_E> n_1700_B(List<P_1922_E> p_240127_0_, List<P_1922_E> p_240127_1_) {
        ImmutableList.Builder builder = ImmutableList.builder();
        p_240127_0_.forEach(p_240126_2_ -> p_240127_1_.forEach(p_240123_2_ -> builder.add((Object)P_1922_E.n_1700_B(p_240126_2_, p_240123_2_))));
        return builder.build();
    }

    @Override
    public T_2915_h n_1700_B() {
        return this.n_1700_B;
    }

    public static w_1457_N n_1700_B(T_2915_h p_240119_0_) {
        return new w_1457_N(p_240119_0_, (List<P_1922_E>)ImmutableList.of((Object)P_1922_E.n_1700_B()));
    }

    public static w_1457_N n_1700_B(T_2915_h p_240120_0_, P_1922_E p_240120_1_) {
        return new w_1457_N(p_240120_0_, (List<P_1922_E>)ImmutableList.of((Object)p_240120_1_));
    }

    public static w_1457_N n_1700_B(T_2915_h p_240121_0_, P_1922_E ... p_240121_1_) {
        return new w_1457_N(p_240121_0_, (List<P_1922_E>)ImmutableList.copyOf((Object[])p_240121_1_));
    }

    @Override
    public /* synthetic */ Object get() {
        return this.J_1907_R();
    }
}

