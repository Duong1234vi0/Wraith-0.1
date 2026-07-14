/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Map;
import lightning.product.J_22_h;
import lightning.product.S_2110_L;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;
import lightning.product.o_3393_s;

public class Y_4400_R {
    private static final Map<g_2336_b, Class<? extends RandomIntGenerator>> n_1700_B = Maps.newHashMap();

    public static RandomIntGenerator n_1700_B(JsonElement json, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return (RandomIntGenerator)context.deserialize(json, S_2110_L.class);
        }
        JsonObject jsonobject = json.getAsJsonObject();
        String s = i_4431_W.n_1700_B(jsonobject, "type", RandomIntGenerator.J_1907_R.toString());
        Class<? extends RandomIntGenerator> oclass = n_1700_B.get(new g_2336_b(s));
        if (oclass == null) {
            throw new JsonParseException("Unknown generator: " + s);
        }
        return (RandomIntGenerator)context.deserialize((JsonElement)jsonobject, oclass);
    }

    public static JsonElement n_1700_B(RandomIntGenerator randomRange, JsonSerializationContext context) {
        JsonElement jsonelement = context.serialize((Object)randomRange);
        if (jsonelement.isJsonObject()) {
            jsonelement.getAsJsonObject().addProperty("type", randomRange.n_1700_B().toString());
        }
        return jsonelement;
    }

    static {
        n_1700_B.put(RandomIntGenerator.J_1907_R, o_3393_s.class);
        n_1700_B.put(RandomIntGenerator.R_4764_Y, J_22_h.class);
        n_1700_B.put(RandomIntGenerator.n_1700_B, S_2110_L.class);
    }
}


