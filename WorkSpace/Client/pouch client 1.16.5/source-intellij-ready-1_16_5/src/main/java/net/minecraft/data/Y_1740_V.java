/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Streams
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.T_2915_h;
import lightning.product.g_2336_b;
import net.minecraft.data.A_4115_X;
import net.minecraft.data.H_2857_Y;
import net.minecraft.data.T_2506_i;

public class Y_1740_V {
    private final Optional<g_2336_b> n_1700_B;
    private final Set<T_2506_i> J_1907_R;
    private Optional<String> R_4764_Y;

    public Y_1740_V(Optional<g_2336_b> p_i232546_1_, Optional<String> p_i232546_2_, T_2506_i ... p_i232546_3_) {
        this.n_1700_B = p_i232546_1_;
        this.R_4764_Y = p_i232546_2_;
        this.J_1907_R = ImmutableSet.copyOf((Object[])p_i232546_3_);
    }

    public g_2336_b n_1700_B(T_2915_h p_240228_1_, H_2857_Y p_240228_2_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240228_3_) {
        return this.n_1700_B(A_4115_X.n_1700_B(p_240228_1_, this.R_4764_Y.orElse("")), p_240228_2_, p_240228_3_);
    }

    public g_2336_b n_1700_B(T_2915_h p_240229_1_, String p_240229_2_, H_2857_Y p_240229_3_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240229_4_) {
        return this.n_1700_B(A_4115_X.n_1700_B(p_240229_1_, p_240229_2_ + this.R_4764_Y.orElse("")), p_240229_3_, p_240229_4_);
    }

    public g_2336_b J_1907_R(T_2915_h p_240235_1_, String p_240235_2_, H_2857_Y p_240235_3_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240235_4_) {
        return this.n_1700_B(A_4115_X.n_1700_B(p_240235_1_, p_240235_2_), p_240235_3_, p_240235_4_);
    }

    public g_2336_b n_1700_B(g_2336_b p_240234_1_, H_2857_Y p_240234_2_, BiConsumer<g_2336_b, Supplier<JsonElement>> p_240234_3_) {
        Map<T_2506_i, g_2336_b> map = this.n_1700_B(p_240234_2_);
        p_240234_3_.accept(p_240234_1_, () -> {
            JsonObject jsonobject = new JsonObject();
            this.n_1700_B.ifPresent(p_240231_1_ -> jsonobject.addProperty("parent", p_240231_1_.toString()));
            if (!map.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                map.forEach((p_240230_1_, p_240230_2_) -> jsonobject1.addProperty(p_240230_1_.n_1700_B(), p_240230_2_.toString()));
                jsonobject.add("textures", (JsonElement)jsonobject1);
            }
            return jsonobject;
        });
        return p_240234_1_;
    }

    private Map<T_2506_i, g_2336_b> n_1700_B(H_2857_Y p_240232_1_) {
        return (Map)Streams.concat((Stream[])new Stream[]{this.J_1907_R.stream(), p_240232_1_.n_1700_B()}).collect(ImmutableMap.toImmutableMap(Function.identity(), p_240232_1_::n_1700_B));
    }
}

