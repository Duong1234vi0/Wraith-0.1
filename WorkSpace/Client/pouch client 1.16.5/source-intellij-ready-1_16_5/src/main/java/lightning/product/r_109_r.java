/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lightning.product.E_2561_m;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.SetTag;

public interface r_109_r<T> {
    public static <T> Codec<r_109_r<T>> n_1700_B(Supplier<E_2561_m<T>> collectionSupplier) {
        return g_2336_b.n_1700_B.flatXmap(tagId -> Optional.ofNullable(((E_2561_m)collectionSupplier.get()).n_1700_B((g_2336_b)tagId)).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown tag: " + String.valueOf(tagId)))), tag -> Optional.ofNullable(((E_2561_m)collectionSupplier.get()).n_1700_B(tag)).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown tag: " + String.valueOf(tag)))));
    }

    public boolean n_1700_B(T var1);

    public List<T> n_1700_B();

    default public T n_1700_B(Random random) {
        List<T> list = this.n_1700_B();
        return list.get(random.nextInt(list.size()));
    }

    public static <T> r_109_r<T> n_1700_B(Set<T> elements) {
        return SetTag.J_1907_R(elements);
    }

    public static class w_1484_f
    implements R_4764_Y {
        private final g_2336_b n_1700_B;

        public w_1484_f(g_2336_b resourceLocationIn) {
            this.n_1700_B = resourceLocationIn;
        }

        @Override
        public <T> boolean n_1700_B(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction, Consumer<T> elementConsumer) {
            r_109_r<T> itag = resourceTagFunction.apply(this.n_1700_B);
            if (itag == null) {
                return false;
            }
            itag.n_1700_B().forEach(elementConsumer);
            return true;
        }

        @Override
        public void n_1700_B(JsonArray jsonArray) {
            jsonArray.add("#" + String.valueOf(this.n_1700_B));
        }

        public String toString() {
            return "#" + String.valueOf(this.n_1700_B);
        }
    }

    public static class v_4262_N {
        private final R_4764_Y n_1700_B;
        private final String J_1907_R;

        private v_4262_N(R_4764_Y entry, String identifier) {
            this.n_1700_B = entry;
            this.J_1907_R = identifier;
        }

        public R_4764_Y n_1700_B() {
            return this.n_1700_B;
        }

        public String toString() {
            return this.n_1700_B.toString() + " (from " + this.J_1907_R + ")";
        }
    }

    public static class u_1723_Y
    implements R_4764_Y {
        private final g_2336_b n_1700_B;

        public u_1723_Y(g_2336_b id) {
            this.n_1700_B = id;
        }

        @Override
        public <T> boolean n_1700_B(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction, Consumer<T> elementConsumer) {
            r_109_r<T> itag = resourceTagFunction.apply(this.n_1700_B);
            if (itag != null) {
                itag.n_1700_B().forEach(elementConsumer);
            }
            return true;
        }

        @Override
        public void n_1700_B(JsonArray jsonArray) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("id", "#" + String.valueOf(this.n_1700_B));
            jsonobject.addProperty("required", Boolean.valueOf(false));
            jsonArray.add((JsonElement)jsonobject);
        }

        public String toString() {
            return "#" + String.valueOf(this.n_1700_B) + "?";
        }
    }

    public static class P_1922_E
    implements R_4764_Y {
        private final g_2336_b n_1700_B;

        public P_1922_E(g_2336_b id) {
            this.n_1700_B = id;
        }

        @Override
        public <T> boolean n_1700_B(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction, Consumer<T> elementConsumer) {
            T t = resourceElementFunction.apply(this.n_1700_B);
            if (t != null) {
                elementConsumer.accept(t);
            }
            return true;
        }

        @Override
        public void n_1700_B(JsonArray jsonArray) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("id", this.n_1700_B.toString());
            jsonobject.addProperty("required", Boolean.valueOf(false));
            jsonArray.add((JsonElement)jsonobject);
        }

        public String toString() {
            return this.n_1700_B.toString() + "?";
        }
    }

    public static class G_564_y
    implements R_4764_Y {
        private final g_2336_b n_1700_B;

        public G_564_y(g_2336_b identifier) {
            this.n_1700_B = identifier;
        }

        @Override
        public <T> boolean n_1700_B(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction, Consumer<T> elementConsumer) {
            T t = resourceElementFunction.apply(this.n_1700_B);
            if (t == null) {
                return false;
            }
            elementConsumer.accept(t);
            return true;
        }

        @Override
        public void n_1700_B(JsonArray jsonArray) {
            jsonArray.add(this.n_1700_B.toString());
        }

        public String toString() {
            return this.n_1700_B.toString();
        }
    }

    public static interface R_4764_Y {
        public <T> boolean n_1700_B(Function<g_2336_b, r_109_r<T>> var1, Function<g_2336_b, T> var2, Consumer<T> var3);

        public void n_1700_B(JsonArray var1);
    }

    public static interface J_1907_R<T>
    extends r_109_r<T> {
        public g_2336_b J_1907_R();
    }

    public static class n_1700_B {
        private final List<v_4262_N> n_1700_B = Lists.newArrayList();

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(v_4262_N proxyTag) {
            this.n_1700_B.add(proxyTag);
            return this;
        }

        public n_1700_B n_1700_B(R_4764_Y tagEntry, String identifier) {
            return this.n_1700_B(new v_4262_N(tagEntry, identifier));
        }

        public n_1700_B n_1700_B(g_2336_b registryName, String identifier) {
            return this.n_1700_B(new G_564_y(registryName), identifier);
        }

        public n_1700_B J_1907_R(g_2336_b tag, String identifier) {
            return this.n_1700_B(new w_1484_f(tag), identifier);
        }

        public <T> Optional<r_109_r<T>> n_1700_B(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction) {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            for (v_4262_N itag$proxy : this.n_1700_B) {
                if (itag$proxy.n_1700_B().n_1700_B(resourceTagFunction, resourceElementFunction, arg_0 -> ((ImmutableSet.Builder)builder).add(arg_0))) continue;
                return Optional.empty();
            }
            return Optional.of(r_109_r.n_1700_B(builder.build()));
        }

        public Stream<v_4262_N> J_1907_R() {
            return this.n_1700_B.stream();
        }

        public <T> Stream<v_4262_N> J_1907_R(Function<g_2336_b, r_109_r<T>> resourceTagFunction, Function<g_2336_b, T> resourceElementFunction) {
            return this.J_1907_R().filter(tagProxy -> !tagProxy.n_1700_B().n_1700_B(resourceTagFunction, resourceElementFunction, tagType -> {}));
        }

        public n_1700_B n_1700_B(JsonObject json, String identifier) {
            JsonArray jsonarray = i_4431_W.P_4830_p(json, "values");
            ArrayList list = Lists.newArrayList();
            for (JsonElement jsonelement : jsonarray) {
                list.add(lightning.product.r_109_r$n_1700_B.n_1700_B(jsonelement));
            }
            if (i_4431_W.n_1700_B(json, "replace", false)) {
                this.n_1700_B.clear();
            }
            list.forEach(tagEntry -> this.n_1700_B.add(new v_4262_N((R_4764_Y)tagEntry, identifier)));
            return this;
        }

        private static R_4764_Y n_1700_B(JsonElement json) {
            boolean flag;
            String s;
            if (json.isJsonObject()) {
                JsonObject jsonobject = json.getAsJsonObject();
                s = i_4431_W.u_1723_Y(jsonobject, "id");
                flag = i_4431_W.n_1700_B(jsonobject, "required", true);
            } else {
                s = i_4431_W.n_1700_B(json, "id");
                flag = true;
            }
            if (s.startsWith("#")) {
                g_2336_b resourcelocation1 = new g_2336_b(s.substring(1));
                return flag ? new w_1484_f(resourcelocation1) : new u_1723_Y(resourcelocation1);
            }
            g_2336_b resourcelocation = new g_2336_b(s);
            return flag ? new G_564_y(resourcelocation) : new P_1922_E(resourcelocation);
        }

        public JsonObject R_4764_Y() {
            JsonObject jsonobject = new JsonObject();
            JsonArray jsonarray = new JsonArray();
            for (v_4262_N itag$proxy : this.n_1700_B) {
                itag$proxy.n_1700_B().n_1700_B(jsonarray);
            }
            jsonobject.addProperty("replace", Boolean.valueOf(false));
            jsonobject.add("values", (JsonElement)jsonarray);
            return jsonobject;
        }
    }
}


