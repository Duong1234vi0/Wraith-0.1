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
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.i_4431_W;
import lightning.product.s_3109_F;
import org.apache.commons.lang3.StringUtils;

public class g_2336_b
implements Comparable<g_2336_b> {
    public static final Codec<g_2336_b> n_1700_B = Codec.STRING.comapFlatMap(g_2336_b::n_1700_B, g_2336_b::toString).stable();
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.id.invalid"));
    protected final String J_1907_R;
    protected final String R_4764_Y;

    protected g_2336_b(String[] resourceParts) {
        this.J_1907_R = StringUtils.isEmpty((CharSequence)resourceParts[0]) ? "minecraft" : resourceParts[0];
        this.R_4764_Y = resourceParts[1];
        if (this.R_4764_Y.equals("DUMMY")) {
            if (!g_2336_b.P_1922_E(this.J_1907_R)) {
                throw new s_3109_F("Non [a-z0-9_.-] character in namespace of location: " + this.J_1907_R + ":" + this.R_4764_Y);
            }
            if (!g_2336_b.G_564_y(this.R_4764_Y)) {
                throw new s_3109_F("Non [a-z0-9/._-] character in path of location: " + this.J_1907_R + ":" + this.R_4764_Y);
            }
        }
    }

    public g_2336_b(String resourceName) {
        this(g_2336_b.J_1907_R(resourceName, ':'));
    }

    public g_2336_b(String namespaceIn, String pathIn) {
        this(new String[]{namespaceIn, pathIn});
    }

    public static g_2336_b n_1700_B(String resourceName, char splitOn) {
        return new g_2336_b(g_2336_b.J_1907_R(resourceName, splitOn));
    }

    @Nullable
    public static g_2336_b J_1907_R(String string) {
        try {
            return new g_2336_b(string);
        }
        catch (s_3109_F resourcelocationexception) {
            return null;
        }
    }

    protected static String[] J_1907_R(String resourceName, char splitOn) {
        String[] astring = new String[]{"minecraft", resourceName};
        int i = resourceName.indexOf(splitOn);
        if (i >= 0) {
            astring[1] = resourceName.substring(i + 1, resourceName.length());
            if (i >= 1) {
                astring[0] = resourceName.substring(0, i);
            }
        }
        return astring;
    }

    private static DataResult<g_2336_b> n_1700_B(String encoded) {
        try {
            return DataResult.success((Object)new g_2336_b(encoded));
        }
        catch (s_3109_F resourcelocationexception) {
            return DataResult.error((String)("Not a valid resource location: " + encoded + " " + resourcelocationexception.getMessage()));
        }
    }

    public String J_1907_R() {
        return this.R_4764_Y;
    }

    public String R_4764_Y() {
        return this.J_1907_R;
    }

    public String toString() {
        return this.J_1907_R + ":" + this.R_4764_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof g_2336_b)) {
            return false;
        }
        g_2336_b resourcelocation = (g_2336_b)p_equals_1_;
        return this.J_1907_R.equals(resourcelocation.J_1907_R) && this.R_4764_Y.equals(resourcelocation.R_4764_Y);
    }

    public int hashCode() {
        return 31 * this.J_1907_R.hashCode() + this.R_4764_Y.hashCode();
    }

    public int n_1700_B(g_2336_b p_compareTo_1_) {
        int i = this.R_4764_Y.compareTo(p_compareTo_1_.R_4764_Y);
        if (i == 0) {
            i = this.J_1907_R.compareTo(p_compareTo_1_.J_1907_R);
        }
        return i;
    }

    public static g_2336_b n_1700_B(StringReader reader) throws CommandSyntaxException {
        int i = reader.getCursor();
        while (reader.canRead() && g_2336_b.n_1700_B(reader.peek())) {
            reader.skip();
        }
        String s = reader.getString().substring(i, reader.getCursor());
        try {
            return new g_2336_b(s);
        }
        catch (s_3109_F resourcelocationexception) {
            reader.setCursor(i);
            throw G_564_y.createWithContext((ImmutableStringReader)reader);
        }
    }

    public static boolean n_1700_B(char charIn) {
        return charIn >= '0' && charIn <= '9' || charIn >= 'a' && charIn <= 'z' || charIn == '_' || charIn == ':' || charIn == '/' || charIn == '.' || charIn == '-';
    }

    private static boolean G_564_y(String pathIn) {
        for (int i = 0; i < pathIn.length(); ++i) {
            if (g_2336_b.J_1907_R(pathIn.charAt(i))) continue;
            return false;
        }
        return true;
    }

    private static boolean P_1922_E(String namespaceIn) {
        for (int i = 0; i < namespaceIn.length(); ++i) {
            if (g_2336_b.R_4764_Y(namespaceIn.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean J_1907_R(char charValue) {
        return charValue == '_' || charValue == '-' || charValue >= 'a' && charValue <= 'z' || charValue >= '0' && charValue <= '9' || charValue == '/' || charValue == '.';
    }

    private static boolean R_4764_Y(char charValue) {
        return charValue == '_' || charValue == '-' || charValue >= 'a' && charValue <= 'z' || charValue >= '0' && charValue <= '9' || charValue == '.';
    }

    public static boolean R_4764_Y(String resourceName) {
        String[] astring = g_2336_b.J_1907_R(resourceName, ':');
        return g_2336_b.P_1922_E(StringUtils.isEmpty((CharSequence)astring[0]) ? "minecraft" : astring[0]) && g_2336_b.G_564_y(astring[1]);
    }

    public int J_1907_R(g_2336_b p_compareNamespaced_1_) {
        int i = this.J_1907_R.compareTo(p_compareNamespaced_1_.J_1907_R);
        return i != 0 ? i : this.R_4764_Y.compareTo(p_compareNamespaced_1_.R_4764_Y);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((g_2336_b)object);
    }

    public static class n_1700_B
    implements JsonDeserializer<g_2336_b>,
    JsonSerializer<g_2336_b> {
        public g_2336_b n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            return new g_2336_b(i_4431_W.n_1700_B(p_deserialize_1_, "location"));
        }

        public JsonElement n_1700_B(g_2336_b p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            return new JsonPrimitive(p_serialize_1_.toString());
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((g_2336_b)object, type, jsonSerializationContext);
        }
    }
}

