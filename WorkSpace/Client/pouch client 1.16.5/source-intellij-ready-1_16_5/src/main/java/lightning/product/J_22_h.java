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
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;

public final class J_22_h
implements RandomIntGenerator {
    private final int G_564_y;
    private final float P_1922_E;

    public J_22_h(int n, float p) {
        this.G_564_y = n;
        this.P_1922_E = p;
    }

    @Override
    public int n_1700_B(Random rand) {
        int i = 0;
        for (int j = 0; j < this.G_564_y; ++j) {
            if (!(rand.nextFloat() < this.P_1922_E)) continue;
            ++i;
        }
        return i;
    }

    public static J_22_h n_1700_B(int nIn, float pIn) {
        return new J_22_h(nIn, pIn);
    }

    @Override
    public g_2336_b n_1700_B() {
        return R_4764_Y;
    }

    public static class n_1700_B
    implements JsonDeserializer<J_22_h>,
    JsonSerializer<J_22_h> {
        public J_22_h n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "value");
            int i = i_4431_W.u_2550_I(jsonobject, "n");
            float f = i_4431_W.t_148_a(jsonobject, "p");
            return new J_22_h(i, f);
        }

        public JsonElement n_1700_B(J_22_h p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("n", (Number)p_serialize_1_.G_564_y);
            jsonobject.addProperty("p", (Number)Float.valueOf(p_serialize_1_.P_1922_E));
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((J_22_h)object, type, jsonSerializationContext);
        }
    }
}


