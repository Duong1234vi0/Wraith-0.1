/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.function.Supplier;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import net.minecraft.data.P_1922_E;
import net.minecraft.data.Q_2552_b;
import net.minecraft.data.k_2293_S;

public class multiplayerClientSuggestionProvider
implements Q_2552_b {
    private final T_2915_h n_1700_B;
    private final List<J_1907_R> J_1907_R = Lists.newArrayList();

    private multiplayerClientSuggestionProvider(T_2915_h p_i232524_1_) {
        this.n_1700_B = p_i232524_1_;
    }

    @Override
    public T_2915_h n_1700_B() {
        return this.n_1700_B;
    }

    public static multiplayerClientSuggestionProvider n_1700_B(T_2915_h p_240106_0_) {
        return new multiplayerClientSuggestionProvider(p_240106_0_);
    }

    public multiplayerClientSuggestionProvider n_1700_B(List<P_1922_E> p_240112_1_) {
        this.J_1907_R.add(new J_1907_R(p_240112_1_));
        return this;
    }

    public multiplayerClientSuggestionProvider n_1700_B(P_1922_E p_240111_1_) {
        return this.n_1700_B((List<P_1922_E>)ImmutableList.of((Object)p_240111_1_));
    }

    public multiplayerClientSuggestionProvider n_1700_B(k_2293_S p_240109_1_, List<P_1922_E> p_240109_2_) {
        this.J_1907_R.add(new n_1700_B(p_240109_1_, p_240109_2_));
        return this;
    }

    public multiplayerClientSuggestionProvider n_1700_B(k_2293_S p_240110_1_, P_1922_E ... p_240110_2_) {
        return this.n_1700_B(p_240110_1_, (List<P_1922_E>)ImmutableList.copyOf((Object[])p_240110_2_));
    }

    public multiplayerClientSuggestionProvider n_1700_B(k_2293_S p_240108_1_, P_1922_E p_240108_2_) {
        return this.n_1700_B(p_240108_1_, (List<P_1922_E>)ImmutableList.of((Object)p_240108_2_));
    }

    public JsonElement J_1907_R() {
        Y_1835_y<T_2915_h, K_4074_S> statecontainer = this.n_1700_B.t_1786_h();
        this.J_1907_R.forEach(p_240107_1_ -> p_240107_1_.n_1700_B(statecontainer));
        JsonArray jsonarray = new JsonArray();
        this.J_1907_R.stream().map(J_1907_R::n_1700_B).forEach(arg_0 -> ((JsonArray)jsonarray).add(arg_0));
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("multipart", (JsonElement)jsonarray);
        return jsonobject;
    }

    @Override
    public /* synthetic */ Object get() {
        return this.J_1907_R();
    }

    static class J_1907_R
    implements Supplier<JsonElement> {
        private final List<P_1922_E> n_1700_B;

        private J_1907_R(List<P_1922_E> p_i232527_1_) {
            this.n_1700_B = p_i232527_1_;
        }

        public void n_1700_B(Y_1835_y<?, ?> p_230525_1_) {
        }

        public void n_1700_B(JsonObject p_230526_1_) {
        }

        public JsonElement n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            this.n_1700_B(jsonobject);
            jsonobject.add("apply", P_1922_E.n_1700_B(this.n_1700_B));
            return jsonobject;
        }

        @Override
        public /* synthetic */ Object get() {
            return this.n_1700_B();
        }
    }

    static class n_1700_B
    extends J_1907_R {
        private final k_2293_S n_1700_B;

        private n_1700_B(k_2293_S p_i232525_1_, List<P_1922_E> p_i232525_2_) {
            super(p_i232525_2_);
            this.n_1700_B = p_i232525_1_;
        }

        @Override
        public void n_1700_B(Y_1835_y<?, ?> p_230525_1_) {
            this.n_1700_B.n_1700_B(p_230525_1_);
        }

        @Override
        public void n_1700_B(JsonObject p_230526_1_) {
            p_230526_1_.add("when", (JsonElement)this.n_1700_B.get());
        }
    }
}


