/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.Y_1835_y;
import lightning.product.v_3760_Q;

public interface k_2293_S
extends Supplier<JsonElement> {
    public void n_1700_B(Y_1835_y<?, ?> var1);

    public static J_1907_R n_1700_B() {
        return new J_1907_R();
    }

    public static k_2293_S n_1700_B(k_2293_S ... p_240090_0_) {
        return new R_4764_Y(n_1700_B.J_1907_R, Arrays.asList(p_240090_0_));
    }

    public static class J_1907_R
    implements k_2293_S {
        private final Map<v_3760_Q<?>, String> n_1700_B = Maps.newHashMap();

        private static <T extends Comparable<T>> String n_1700_B(v_3760_Q<T> p_240101_0_, Stream<T> p_240101_1_) {
            return p_240101_1_.map(p_240101_0_::n_1700_B).collect(Collectors.joining("|"));
        }

        private static <T extends Comparable<T>> String J_1907_R(v_3760_Q<T> p_240103_0_, T p_240103_1_, T[] p_240103_2_) {
            return J_1907_R.n_1700_B(p_240103_0_, Stream.concat(Stream.of(p_240103_1_), Stream.of(p_240103_2_)));
        }

        private <T extends Comparable<T>> void n_1700_B(v_3760_Q<T> p_240100_1_, String p_240100_2_) {
            String s = this.n_1700_B.put(p_240100_1_, p_240100_2_);
            if (s != null) {
                throw new IllegalStateException("Tried to replace " + String.valueOf(p_240100_1_) + " value from " + s + " to " + p_240100_2_);
            }
        }

        public final <T extends Comparable<T>> J_1907_R n_1700_B(v_3760_Q<T> p_240098_1_, T p_240098_2_) {
            this.n_1700_B(p_240098_1_, p_240098_1_.n_1700_B(p_240098_2_));
            return this;
        }

        @SafeVarargs
        public final <T extends Comparable<T>> J_1907_R n_1700_B(v_3760_Q<T> p_240099_1_, T p_240099_2_, T ... p_240099_3_) {
            this.n_1700_B(p_240099_1_, J_1907_R.J_1907_R(p_240099_1_, p_240099_2_, p_240099_3_));
            return this;
        }

        public JsonElement J_1907_R() {
            JsonObject jsonobject = new JsonObject();
            this.n_1700_B.forEach((p_240102_1_, p_240102_2_) -> jsonobject.addProperty(p_240102_1_.P_1922_E(), p_240102_2_));
            return jsonobject;
        }

        @Override
        public void n_1700_B(Y_1835_y<?, ?> p_230523_1_) {
            List list = this.n_1700_B.keySet().stream().filter(p_240097_1_ -> p_230523_1_.n_1700_B(p_240097_1_.P_1922_E()) != p_240097_1_).collect(Collectors.toList());
            if (!list.isEmpty()) {
                throw new IllegalStateException("Properties " + String.valueOf(list) + " are missing from " + String.valueOf(p_230523_1_));
            }
        }

        @Override
        public /* synthetic */ Object get() {
            return this.J_1907_R();
        }
    }

    public static class R_4764_Y
    implements k_2293_S {
        private final n_1700_B n_1700_B;
        private final List<k_2293_S> J_1907_R;

        private R_4764_Y(n_1700_B p_i232521_1_, List<k_2293_S> p_i232521_2_) {
            this.n_1700_B = p_i232521_1_;
            this.J_1907_R = p_i232521_2_;
        }

        @Override
        public void n_1700_B(Y_1835_y<?, ?> p_230523_1_) {
            this.J_1907_R.forEach(p_240093_1_ -> p_240093_1_.n_1700_B(p_230523_1_));
        }

        public JsonElement J_1907_R() {
            JsonArray jsonarray = new JsonArray();
            this.J_1907_R.stream().map(Supplier::get).forEach(arg_0 -> ((JsonArray)jsonarray).add(arg_0));
            JsonObject jsonobject = new JsonObject();
            jsonobject.add(this.n_1700_B.R_4764_Y, (JsonElement)jsonarray);
            return jsonobject;
        }

        @Override
        public /* synthetic */ Object get() {
            return this.J_1907_R();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("AND");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("OR");
        private final String R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i232523_3_) {
            this.R_4764_Y = p_i232523_3_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = net.minecraft.data.k_2293_S$n_1700_B.n_1700_B();
        }
    }
}

