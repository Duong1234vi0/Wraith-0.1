/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;

public final class S_2110_L
implements RandomIntGenerator {
    private final int G_564_y;

    public S_2110_L(int value) {
        this.G_564_y = value;
    }

    @Override
    public int n_1700_B(Random rand) {
        return this.G_564_y;
    }

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public static S_2110_L n_1700_B(int constant) {
        return new S_2110_L(constant);
    }

    public static class n_1700_B
    implements JsonDeserializer<S_2110_L>,
    JsonSerializer<S_2110_L> {
        public S_2110_L n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            return new S_2110_L(i_4431_W.u_1723_Y(p_deserialize_1_, "value"));
        }

        public JsonElement n_1700_B(S_2110_L p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            return new JsonPrimitive((Number)p_serialize_1_.G_564_y);
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((S_2110_L)object, type, jsonSerializationContext);
        }
    }
}


