/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Streams
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Streams;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.MultiVariant;
import lightning.product.Y_1835_y;
import lightning.product.KeyValueCondition;
import lightning.product.i_4431_W;
import lightning.product.Condition;
import lightning.product.o_1429_S;
import lightning.product.w_1847_p;

public class Selector {
    private final Condition n_1700_B;
    private final MultiVariant J_1907_R;

    public Selector(Condition conditionIn, MultiVariant variantListIn) {
        if (conditionIn == null) {
            throw new IllegalArgumentException("Missing condition for selector");
        }
        if (variantListIn == null) {
            throw new IllegalArgumentException("Missing variant for selector");
        }
        this.n_1700_B = conditionIn;
        this.J_1907_R = variantListIn;
    }

    public MultiVariant n_1700_B() {
        return this.J_1907_R;
    }

    public Predicate<K_4074_S> n_1700_B(Y_1835_y<T_2915_h, K_4074_S> state) {
        return this.n_1700_B.getPredicate(state);
    }

    public boolean equals(Object p_equals_1_) {
        return this == p_equals_1_;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public static class n_1700_B
    implements JsonDeserializer<Selector> {
        public Selector n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            return new Selector(this.J_1907_R(jsonobject), (MultiVariant)p_deserialize_3_.deserialize(jsonobject.get("apply"), MultiVariant.class));
        }

        private Condition J_1907_R(JsonObject json) {
            return json.has("when") ? lightning.product.Selector$n_1700_B.n_1700_B(i_4431_W.M_588_G(json, "when")) : Condition.n_1700_B;
        }

        @VisibleForTesting
        static Condition n_1700_B(JsonObject json) {
            Set set = json.entrySet();
            if (set.isEmpty()) {
                throw new JsonParseException("No elements found in selector");
            }
            if (set.size() == 1) {
                if (json.has("OR")) {
                    List list1 = Streams.stream((Iterable)i_4431_W.P_4830_p(json, "OR")).map(json1 -> lightning.product.Selector$n_1700_B.n_1700_B(json1.getAsJsonObject())).collect(Collectors.toList());
                    return new o_1429_S(list1);
                }
                if (json.has("AND")) {
                    List list = Streams.stream((Iterable)i_4431_W.P_4830_p(json, "AND")).map(json1 -> lightning.product.Selector$n_1700_B.n_1700_B(json1.getAsJsonObject())).collect(Collectors.toList());
                    return new w_1847_p(list);
                }
                return lightning.product.Selector$n_1700_B.n_1700_B((Map.Entry)set.iterator().next());
            }
            return new w_1847_p(set.stream().map(n_1700_B::n_1700_B).collect(Collectors.toList()));
        }

        private static Condition n_1700_B(Map.Entry<String, JsonElement> entry) {
            return new KeyValueCondition(entry.getKey(), entry.getValue().getAsString());
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }
    }
}


