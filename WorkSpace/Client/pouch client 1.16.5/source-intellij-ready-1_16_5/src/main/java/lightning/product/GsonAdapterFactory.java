/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Pair;
import java.lang.reflect.Type;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.o_3236_c;

public class GsonAdapterFactory {
    public static <E, T extends o_3236_c<E>> J_1907_R<E, T> n_1700_B(V_3137_a<T> registry, String id, String name, Function<E, T> typeFunction) {
        return new J_1907_R<E, T>(registry, id, name, typeFunction);
    }

    public static class J_1907_R<E, T extends o_3236_c<E>> {
        private final V_3137_a<T> n_1700_B;
        private final String J_1907_R;
        private final String R_4764_Y;
        private final Function<E, T> G_564_y;
        @Nullable
        private Pair<T, n_1700_B<? extends E>> P_1922_E;

        private J_1907_R(V_3137_a<T> registry, String id, String name, Function<E, T> typeFunction) {
            this.n_1700_B = registry;
            this.J_1907_R = id;
            this.R_4764_Y = name;
            this.G_564_y = typeFunction;
        }

        public Object n_1700_B() {
            return new R_4764_Y<E, T>(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }

    static class R_4764_Y<E, T extends o_3236_c<E>>
    implements JsonDeserializer<E>,
    JsonSerializer<E> {
        private final V_3137_a<T> n_1700_B;
        private final String J_1907_R;
        private final String R_4764_Y;
        private final Function<E, T> G_564_y;
        @Nullable
        private final Pair<T, n_1700_B<? extends E>> P_1922_E;

        private R_4764_Y(V_3137_a<T> registry, String id, String name, Function<E, T> typeFunction, @Nullable Pair<T, n_1700_B<? extends E>> typeSerializer) {
            this.n_1700_B = registry;
            this.J_1907_R = id;
            this.R_4764_Y = name;
            this.G_564_y = typeFunction;
            this.P_1922_E = typeSerializer;
        }

        public E deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            if (p_deserialize_1_.isJsonObject()) {
                JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, this.J_1907_R);
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, this.R_4764_Y));
                o_3236_c t = (o_3236_c)this.n_1700_B.n_1700_B(resourcelocation);
                if (t == null) {
                    throw new JsonSyntaxException("Unknown type '" + String.valueOf(resourcelocation) + "'");
                }
                return (E)t.n_1700_B().n_1700_B(jsonobject, p_deserialize_3_);
            }
            if (this.P_1922_E == null) {
                throw new UnsupportedOperationException("Object " + String.valueOf(p_deserialize_1_) + " can't be deserialized");
            }
            return (E)((n_1700_B)this.P_1922_E.getSecond()).n_1700_B(p_deserialize_1_, p_deserialize_3_);
        }

        public JsonElement serialize(E p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            o_3236_c t = (o_3236_c)this.G_564_y.apply(p_serialize_1_);
            if (this.P_1922_E != null && this.P_1922_E.getFirst() == t) {
                return ((n_1700_B)this.P_1922_E.getSecond()).n_1700_B(p_serialize_1_, p_serialize_3_);
            }
            if (t == null) {
                throw new JsonSyntaxException("Unknown type: " + String.valueOf(p_serialize_1_));
            }
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty(this.R_4764_Y, this.n_1700_B.J_1907_R(t).toString());
            t.n_1700_B().n_1700_B(jsonobject, p_serialize_1_, p_serialize_3_);
            return jsonobject;
        }
    }

    public static interface n_1700_B<T> {
        public JsonElement n_1700_B(T var1, JsonSerializationContext var2);

        public T n_1700_B(JsonElement var1, JsonDeserializationContext var2);
    }
}


