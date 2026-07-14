/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSyntaxException
 *  com.google.gson.reflect.TypeToken
 *  com.google.gson.stream.JsonReader
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.annotation.Nullable;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import org.apache.commons.lang3.StringUtils;

public class i_4431_W {
    private static final Gson n_1700_B = new GsonBuilder().create();

    public static boolean n_1700_B(JsonObject json, String memberName) {
        return !i_4431_W.G_564_y(json, memberName) ? false : json.getAsJsonPrimitive(memberName).isString();
    }

    public static boolean n_1700_B(JsonElement json) {
        return !json.isJsonPrimitive() ? false : json.getAsJsonPrimitive().isString();
    }

    public static boolean J_1907_R(JsonElement json) {
        return !json.isJsonPrimitive() ? false : json.getAsJsonPrimitive().isNumber();
    }

    public static boolean J_1907_R(JsonObject json, String memberName) {
        return !i_4431_W.G_564_y(json, memberName) ? false : json.getAsJsonPrimitive(memberName).isBoolean();
    }

    public static boolean R_4764_Y(JsonObject json, String memberName) {
        return !i_4431_W.P_1922_E(json, memberName) ? false : json.get(memberName).isJsonArray();
    }

    public static boolean G_564_y(JsonObject json, String memberName) {
        return !i_4431_W.P_1922_E(json, memberName) ? false : json.get(memberName).isJsonPrimitive();
    }

    public static boolean P_1922_E(JsonObject json, String memberName) {
        if (json == null) {
            return false;
        }
        return json.get(memberName) != null;
    }

    public static String n_1700_B(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return json.getAsString();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a string, was " + i_4431_W.R_4764_Y(json));
    }

    public static String u_1723_Y(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.n_1700_B(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a string");
    }

    public static String n_1700_B(JsonObject json, String memberName, String fallback) {
        return json.has(memberName) ? i_4431_W.n_1700_B(json.get(memberName), memberName) : fallback;
    }

    public static q_1613_l J_1907_R(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            String s = json.getAsString();
            return V_3137_a.e_2887_G.J_1907_R(new g_2336_b(s)).orElseThrow(() -> new JsonSyntaxException("Expected " + memberName + " to be an item, was unknown string '" + s + "'"));
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be an item, was " + i_4431_W.R_4764_Y(json));
    }

    public static q_1613_l v_4262_N(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.J_1907_R(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find an item");
    }

    public static boolean R_4764_Y(JsonElement json, String memberName) {
        if (json.isJsonPrimitive()) {
            return json.getAsBoolean();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Boolean, was " + i_4431_W.R_4764_Y(json));
    }

    public static boolean w_1484_f(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.R_4764_Y(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Boolean");
    }

    public static boolean n_1700_B(JsonObject json, String memberName, boolean fallback) {
        return json.has(memberName) ? i_4431_W.R_4764_Y(json.get(memberName), memberName) : fallback;
    }

    public static float G_564_y(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsFloat();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Float, was " + i_4431_W.R_4764_Y(json));
    }

    public static float t_148_a(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.G_564_y(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Float");
    }

    public static float n_1700_B(JsonObject json, String memberName, float fallback) {
        return json.has(memberName) ? i_4431_W.G_564_y(json.get(memberName), memberName) : fallback;
    }

    public static long P_1922_E(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsLong();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Long, was " + i_4431_W.R_4764_Y(json));
    }

    public static long s_956_w(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.P_1922_E(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Long");
    }

    public static long n_1700_B(JsonObject json, String memberName, long fallback) {
        return json.has(memberName) ? i_4431_W.P_1922_E(json.get(memberName), memberName) : fallback;
    }

    public static int u_1723_Y(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsInt();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Int, was " + i_4431_W.R_4764_Y(json));
    }

    public static int u_2550_I(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.u_1723_Y(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a Int");
    }

    public static int n_1700_B(JsonObject json, String memberName, int fallback) {
        return json.has(memberName) ? i_4431_W.u_1723_Y(json.get(memberName), memberName) : fallback;
    }

    public static byte v_4262_N(JsonElement json, String memberName) {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
            return json.getAsByte();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a Byte, was " + i_4431_W.R_4764_Y(json));
    }

    public static byte n_1700_B(JsonObject json, String memberName, byte fallback) {
        return json.has(memberName) ? i_4431_W.v_4262_N(json.get(memberName), memberName) : fallback;
    }

    public static JsonObject w_1484_f(JsonElement json, String memberName) {
        if (json.isJsonObject()) {
            return json.getAsJsonObject();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a JsonObject, was " + i_4431_W.R_4764_Y(json));
    }

    public static JsonObject M_588_G(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.w_1484_f(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a JsonObject");
    }

    public static JsonObject n_1700_B(JsonObject json, String memberName, JsonObject fallback) {
        return json.has(memberName) ? i_4431_W.w_1484_f(json.get(memberName), memberName) : fallback;
    }

    public static JsonArray t_148_a(JsonElement json, String memberName) {
        if (json.isJsonArray()) {
            return json.getAsJsonArray();
        }
        throw new JsonSyntaxException("Expected " + memberName + " to be a JsonArray, was " + i_4431_W.R_4764_Y(json));
    }

    public static JsonArray P_4830_p(JsonObject json, String memberName) {
        if (json.has(memberName)) {
            return i_4431_W.t_148_a(json.get(memberName), memberName);
        }
        throw new JsonSyntaxException("Missing " + memberName + ", expected to find a JsonArray");
    }

    @Nullable
    public static JsonArray n_1700_B(JsonObject json, String memberName, @Nullable JsonArray fallback) {
        return json.has(memberName) ? i_4431_W.t_148_a(json.get(memberName), memberName) : fallback;
    }

    public static <T> T n_1700_B(@Nullable JsonElement json, String memberName, JsonDeserializationContext context, Class<? extends T> adapter) {
        if (json != null) {
            return (T)context.deserialize(json, adapter);
        }
        throw new JsonSyntaxException("Missing " + memberName);
    }

    public static <T> T n_1700_B(JsonObject json, String memberName, JsonDeserializationContext context, Class<? extends T> adapter) {
        if (json.has(memberName)) {
            return i_4431_W.n_1700_B(json.get(memberName), memberName, context, adapter);
        }
        throw new JsonSyntaxException("Missing " + memberName);
    }

    public static <T> T n_1700_B(JsonObject json, String memberName, T fallback, JsonDeserializationContext context, Class<? extends T> adapter) {
        return json.has(memberName) ? i_4431_W.n_1700_B(json.get(memberName), memberName, context, adapter) : fallback;
    }

    public static String R_4764_Y(JsonElement json) {
        String s = StringUtils.abbreviateMiddle((String)String.valueOf(json), (String)"...", (int)10);
        if (json == null) {
            return "null (missing)";
        }
        if (json.isJsonNull()) {
            return "null (json)";
        }
        if (json.isJsonArray()) {
            return "an array (" + s + ")";
        }
        if (json.isJsonObject()) {
            return "an object (" + s + ")";
        }
        if (json.isJsonPrimitive()) {
            JsonPrimitive jsonprimitive = json.getAsJsonPrimitive();
            if (jsonprimitive.isNumber()) {
                return "a number (" + s + ")";
            }
            if (jsonprimitive.isBoolean()) {
                return "a boolean (" + s + ")";
            }
        }
        return s;
    }

    @Nullable
    public static <T> T n_1700_B(Gson gsonIn, Reader readerIn, Class<T> adapter, boolean lenient) {
        try {
            JsonReader jsonreader = new JsonReader(readerIn);
            jsonreader.setLenient(lenient);
            return (T)gsonIn.getAdapter(adapter).read(jsonreader);
        }
        catch (IOException ioexception) {
            throw new JsonParseException((Throwable)ioexception);
        }
    }

    @Nullable
    public static <T> T n_1700_B(Gson gson, Reader reader, TypeToken<T> type, boolean lenient) {
        try {
            JsonReader jsonreader = new JsonReader(reader);
            jsonreader.setLenient(lenient);
            return (T)gson.getAdapter(type).read(jsonreader);
        }
        catch (IOException ioexception) {
            throw new JsonParseException((Throwable)ioexception);
        }
    }

    @Nullable
    public static <T> T n_1700_B(Gson gson, String string, TypeToken<T> type, boolean lenient) {
        return i_4431_W.n_1700_B(gson, (Reader)new StringReader(string), type, lenient);
    }

    @Nullable
    public static <T> T n_1700_B(Gson gsonIn, String json, Class<T> adapter, boolean lenient) {
        return i_4431_W.n_1700_B(gsonIn, (Reader)new StringReader(json), adapter, lenient);
    }

    @Nullable
    public static <T> T n_1700_B(Gson gson, Reader reader, TypeToken<T> type) {
        return i_4431_W.n_1700_B(gson, reader, type, false);
    }

    @Nullable
    public static <T> T n_1700_B(Gson gson, String string, TypeToken<T> type) {
        return i_4431_W.n_1700_B(gson, string, type, false);
    }

    @Nullable
    public static <T> T n_1700_B(Gson gson, Reader reader, Class<T> jsonClass) {
        return i_4431_W.n_1700_B(gson, reader, jsonClass, false);
    }

    @Nullable
    public static <T> T n_1700_B(Gson gsonIn, String json, Class<T> adapter) {
        return i_4431_W.n_1700_B(gsonIn, json, adapter, false);
    }

    public static JsonObject n_1700_B(String json, boolean lenient) {
        return i_4431_W.n_1700_B(new StringReader(json), lenient);
    }

    public static JsonObject n_1700_B(Reader reader, boolean lenient) {
        return i_4431_W.n_1700_B(n_1700_B, reader, JsonObject.class, lenient);
    }

    public static JsonObject n_1700_B(String json) {
        return i_4431_W.n_1700_B(json, false);
    }

    public static JsonObject n_1700_B(Reader reader) {
        return i_4431_W.n_1700_B(reader, false);
    }
}

