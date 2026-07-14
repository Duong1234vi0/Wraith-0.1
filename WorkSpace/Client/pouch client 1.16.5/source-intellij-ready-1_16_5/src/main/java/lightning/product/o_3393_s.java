/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;
import lightning.product.u_530_F;

public class o_3393_s
implements RandomIntGenerator {
    private final float G_564_y;
    private final float P_1922_E;

    public o_3393_s(float minIn, float maxIn) {
        this.G_564_y = minIn;
        this.P_1922_E = maxIn;
    }

    public o_3393_s(float value) {
        this.G_564_y = value;
        this.P_1922_E = value;
    }

    public static o_3393_s n_1700_B(float minIn, float maxIn) {
        return new o_3393_s(minIn, maxIn);
    }

    public float J_1907_R() {
        return this.G_564_y;
    }

    public float R_4764_Y() {
        return this.P_1922_E;
    }

    @Override
    public int n_1700_B(Random rand) {
        return u_530_F.n_1700_B(rand, u_530_F.G_564_y(this.G_564_y), u_530_F.G_564_y(this.P_1922_E));
    }

    public float J_1907_R(Random rand) {
        return u_530_F.n_1700_B(rand, this.G_564_y, this.P_1922_E);
    }

    public boolean n_1700_B(int value) {
        return (float)value <= this.P_1922_E && (float)value >= this.G_564_y;
    }

    @Override
    public g_2336_b n_1700_B() {
        return J_1907_R;
    }

    public static class n_1700_B
    implements JsonDeserializer<o_3393_s>,
    JsonSerializer<o_3393_s> {
        public o_3393_s n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            if (i_4431_W.J_1907_R(p_deserialize_1_)) {
                return new o_3393_s(i_4431_W.G_564_y(p_deserialize_1_, "value"));
            }
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "value");
            float f = i_4431_W.t_148_a(jsonobject, "min");
            float f1 = i_4431_W.t_148_a(jsonobject, "max");
            return new o_3393_s(f, f1);
        }

        public JsonElement n_1700_B(o_3393_s p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            if (p_serialize_1_.G_564_y == p_serialize_1_.P_1922_E) {
                return new JsonPrimitive((Number)Float.valueOf(p_serialize_1_.G_564_y));
            }
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("min", (Number)Float.valueOf(p_serialize_1_.G_564_y));
            jsonobject.addProperty("max", (Number)Float.valueOf(p_serialize_1_.P_1922_E));
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((o_3393_s)object, type, jsonSerializationContext);
        }
    }
}


