/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.google.gson.TypeAdapterFactory
 *  com.google.gson.stream.JsonReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.mojang.brigadier.Message;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.K_2271_Q;
import lightning.product.L_3144_D;
import lightning.product.N_1112_I;
import lightning.product.U_2871_b;
import lightning.product.Y_901_G;
import lightning.product.Z_1567_W;
import lightning.product.FormattedCharSequence;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import lightning.product.NameProtect;
import lightning.product.q_3584_e;
import lightning.product.s_4082_G;

public interface x_282_a
extends Message,
FormattedText {
    public Z_1567_W n_1700_B();

    public String J_1907_R();

    @Override
    default public String getString() {
        return FormattedText.super.getString();
    }

    default public String n_1700_B(int maxLen) {
        StringBuilder stringbuilder = new StringBuilder();
        this.n_1700_B(string -> {
            int i = maxLen - stringbuilder.length();
            if (i <= 0) {
                return n_1700_B;
            }
            stringbuilder.append(string.length() <= i ? string : string.substring(0, i));
            return Optional.empty();
        });
        return stringbuilder.toString();
    }

    public List<x_282_a> R_4764_Y();

    public MutableComponent G_564_y();

    public MutableComponent P_1922_E();

    public FormattedCharSequence u_1723_Y();

    @Override
    default public <T> Optional<T> n_1700_B(FormattedText.n_1700_B<T> acceptor, Z_1567_W styleIn) {
        Z_1567_W style = this.n_1700_B().n_1700_B(styleIn);
        Optional<T> optional = this.J_1907_R(acceptor, style);
        if (optional.isPresent()) {
            return optional;
        }
        for (x_282_a itextcomponent : this.R_4764_Y()) {
            Optional<T> optional1 = itextcomponent.n_1700_B(acceptor, style);
            if (!optional1.isPresent()) continue;
            return optional1;
        }
        return Optional.empty();
    }

    @Override
    default public <T> Optional<T> n_1700_B(FormattedText.J_1907_R<T> acceptor) {
        Optional<T> optional = this.J_1907_R(acceptor);
        if (optional.isPresent()) {
            return optional;
        }
        for (x_282_a itextcomponent : this.R_4764_Y()) {
            Optional<T> optional1 = itextcomponent.n_1700_B(acceptor);
            if (!optional1.isPresent()) continue;
            return optional1;
        }
        return Optional.empty();
    }

    default public <T> Optional<T> J_1907_R(FormattedText.n_1700_B<T> acceptor, Z_1567_W style) {
        NameProtect np = NameProtect.h_1847_R();
        if (np != null && np.w_1484_f()) {
            return acceptor.accept(style, NameProtect.G_564_y(this.J_1907_R()));
        }
        return acceptor.accept(style, this.J_1907_R());
    }

    default public <T> Optional<T> J_1907_R(FormattedText.J_1907_R<T> acceptor) {
        NameProtect np = NameProtect.h_1847_R();
        if (np != null && np.w_1484_f()) {
            return acceptor.accept(NameProtect.G_564_y(this.J_1907_R()));
        }
        return acceptor.accept(this.J_1907_R());
    }

    public static x_282_a J_1907_R(@Nullable String p_244388_0_) {
        return p_244388_0_ != null ? new U_2871_b(p_244388_0_) : U_2871_b.R_4764_Y;
    }

    public static class n_1700_B
    implements JsonDeserializer<MutableComponent>,
    JsonSerializer<x_282_a> {
        private static final Gson n_1700_B = j_3341_s.n_1700_B(() -> {
            GsonBuilder gsonbuilder = new GsonBuilder();
            gsonbuilder.disableHtmlEscaping();
            gsonbuilder.registerTypeHierarchyAdapter(x_282_a.class, (Object)new n_1700_B());
            gsonbuilder.registerTypeHierarchyAdapter(Z_1567_W.class, (Object)new Z_1567_W.n_1700_B());
            gsonbuilder.registerTypeAdapterFactory((TypeAdapterFactory)new N_1112_I());
            return gsonbuilder.create();
        });
        private static final Field J_1907_R = j_3341_s.n_1700_B(() -> {
            try {
                new JsonReader((Reader)new StringReader(""));
                Field field = JsonReader.class.getDeclaredField("pos");
                field.setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException nosuchfieldexception) {
                throw new IllegalStateException("Couldn't get field 'pos' for JsonReader", nosuchfieldexception);
            }
        });
        private static final Field R_4764_Y = j_3341_s.n_1700_B(() -> {
            try {
                new JsonReader((Reader)new StringReader(""));
                Field field = JsonReader.class.getDeclaredField("lineStart");
                field.setAccessible(true);
                return field;
            }
            catch (NoSuchFieldException nosuchfieldexception) {
                throw new IllegalStateException("Couldn't get field 'lineStart' for JsonReader", nosuchfieldexception);
            }
        });

        public MutableComponent n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            L_3144_D iformattabletextcomponent;
            if (p_deserialize_1_.isJsonPrimitive()) {
                return new U_2871_b(p_deserialize_1_.getAsString());
            }
            if (!p_deserialize_1_.isJsonObject()) {
                if (p_deserialize_1_.isJsonArray()) {
                    JsonArray jsonarray1 = p_deserialize_1_.getAsJsonArray();
                    MutableComponent iformattabletextcomponent1 = null;
                    for (JsonElement jsonelement : jsonarray1) {
                        MutableComponent iformattabletextcomponent2 = this.n_1700_B(jsonelement, jsonelement.getClass(), p_deserialize_3_);
                        if (iformattabletextcomponent1 == null) {
                            iformattabletextcomponent1 = iformattabletextcomponent2;
                            continue;
                        }
                        iformattabletextcomponent1.n_1700_B(iformattabletextcomponent2);
                    }
                    return iformattabletextcomponent1;
                }
                throw new JsonParseException("Don't know how to turn " + String.valueOf(p_deserialize_1_) + " into a Component");
            }
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            if (jsonobject.has("text")) {
                iformattabletextcomponent = new U_2871_b(i_4431_W.u_1723_Y(jsonobject, "text"));
            } else if (jsonobject.has("translate")) {
                String s = i_4431_W.u_1723_Y(jsonobject, "translate");
                if (jsonobject.has("with")) {
                    JsonArray jsonarray = i_4431_W.P_4830_p(jsonobject, "with");
                    Object[] aobject = new Object[jsonarray.size()];
                    for (int i = 0; i < aobject.length; ++i) {
                        U_2871_b stringtextcomponent;
                        aobject[i] = this.n_1700_B(jsonarray.get(i), p_deserialize_2_, p_deserialize_3_);
                        if (!(aobject[i] instanceof U_2871_b) || !(stringtextcomponent = (U_2871_b)aobject[i]).n_1700_B().v_4262_N() || !stringtextcomponent.R_4764_Y().isEmpty()) continue;
                        aobject[i] = stringtextcomponent.v_4262_N();
                    }
                    iformattabletextcomponent = new F_2904_S(s, aobject);
                } else {
                    iformattabletextcomponent = new F_2904_S(s);
                }
            } else if (jsonobject.has("score")) {
                JsonObject jsonobject1 = i_4431_W.M_588_G(jsonobject, "score");
                if (!jsonobject1.has("name") || !jsonobject1.has("objective")) {
                    throw new JsonParseException("A score component needs a least a name and an objective");
                }
                iformattabletextcomponent = new K_2271_Q(i_4431_W.u_1723_Y(jsonobject1, "name"), i_4431_W.u_1723_Y(jsonobject1, "objective"));
            } else if (jsonobject.has("selector")) {
                iformattabletextcomponent = new Y_901_G(i_4431_W.u_1723_Y(jsonobject, "selector"));
            } else if (jsonobject.has("keybind")) {
                iformattabletextcomponent = new s_4082_G(i_4431_W.u_1723_Y(jsonobject, "keybind"));
            } else {
                if (!jsonobject.has("nbt")) {
                    throw new JsonParseException("Don't know how to turn " + String.valueOf(p_deserialize_1_) + " into a Component");
                }
                String s1 = i_4431_W.u_1723_Y(jsonobject, "nbt");
                boolean flag = i_4431_W.n_1700_B(jsonobject, "interpret", false);
                if (jsonobject.has("block")) {
                    iformattabletextcomponent = new q_3584_e.n_1700_B(s1, flag, i_4431_W.u_1723_Y(jsonobject, "block"));
                } else if (jsonobject.has("entity")) {
                    iformattabletextcomponent = new q_3584_e.J_1907_R(s1, flag, i_4431_W.u_1723_Y(jsonobject, "entity"));
                } else {
                    if (!jsonobject.has("storage")) {
                        throw new JsonParseException("Don't know how to turn " + String.valueOf(p_deserialize_1_) + " into a Component");
                    }
                    iformattabletextcomponent = new q_3584_e.R_4764_Y(s1, flag, new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "storage")));
                }
            }
            if (jsonobject.has("extra")) {
                JsonArray jsonarray2 = i_4431_W.P_4830_p(jsonobject, "extra");
                if (jsonarray2.size() <= 0) {
                    throw new JsonParseException("Unexpected empty array of components");
                }
                for (int j = 0; j < jsonarray2.size(); ++j) {
                    iformattabletextcomponent.n_1700_B(this.n_1700_B(jsonarray2.get(j), p_deserialize_2_, p_deserialize_3_));
                }
            }
            iformattabletextcomponent.n_1700_B((Z_1567_W)p_deserialize_3_.deserialize(p_deserialize_1_, Z_1567_W.class));
            return iformattabletextcomponent;
        }

        private void n_1700_B(Z_1567_W style, JsonObject object, JsonSerializationContext ctx) {
            JsonElement jsonelement = ctx.serialize((Object)style);
            if (jsonelement.isJsonObject()) {
                JsonObject jsonobject = (JsonObject)jsonelement;
                for (Map.Entry entry : jsonobject.entrySet()) {
                    object.add((String)entry.getKey(), (JsonElement)entry.getValue());
                }
            }
        }

        public JsonElement n_1700_B(x_282_a p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            if (!p_serialize_1_.n_1700_B().v_4262_N()) {
                this.n_1700_B(p_serialize_1_.n_1700_B(), jsonobject, p_serialize_3_);
            }
            if (!p_serialize_1_.R_4764_Y().isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                for (x_282_a itextcomponent : p_serialize_1_.R_4764_Y()) {
                    jsonarray.add(this.n_1700_B(itextcomponent, itextcomponent.getClass(), p_serialize_3_));
                }
                jsonobject.add("extra", (JsonElement)jsonarray);
            }
            if (p_serialize_1_ instanceof U_2871_b) {
                jsonobject.addProperty("text", ((U_2871_b)p_serialize_1_).v_4262_N());
            } else if (p_serialize_1_ instanceof F_2904_S) {
                F_2904_S translationtextcomponent = (F_2904_S)p_serialize_1_;
                jsonobject.addProperty("translate", translationtextcomponent.w_1484_f());
                if (translationtextcomponent.s_956_w() != null && translationtextcomponent.s_956_w().length > 0) {
                    JsonArray jsonarray1 = new JsonArray();
                    for (Object object : translationtextcomponent.s_956_w()) {
                        if (object instanceof x_282_a) {
                            jsonarray1.add(this.n_1700_B((x_282_a)object, object.getClass(), p_serialize_3_));
                            continue;
                        }
                        jsonarray1.add((JsonElement)new JsonPrimitive(String.valueOf(object)));
                    }
                    jsonobject.add("with", (JsonElement)jsonarray1);
                }
            } else if (p_serialize_1_ instanceof K_2271_Q) {
                K_2271_Q scoretextcomponent = (K_2271_Q)p_serialize_1_;
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.addProperty("name", scoretextcomponent.v_4262_N());
                jsonobject1.addProperty("objective", scoretextcomponent.w_1484_f());
                jsonobject.add("score", (JsonElement)jsonobject1);
            } else if (p_serialize_1_ instanceof Y_901_G) {
                Y_901_G selectortextcomponent = (Y_901_G)p_serialize_1_;
                jsonobject.addProperty("selector", selectortextcomponent.v_4262_N());
            } else if (p_serialize_1_ instanceof s_4082_G) {
                s_4082_G keybindtextcomponent = (s_4082_G)p_serialize_1_;
                jsonobject.addProperty("keybind", keybindtextcomponent.w_1484_f());
            } else {
                if (!(p_serialize_1_ instanceof q_3584_e)) {
                    throw new IllegalArgumentException("Don't know how to serialize " + String.valueOf(p_serialize_1_) + " as a Component");
                }
                q_3584_e nbttextcomponent = (q_3584_e)p_serialize_1_;
                jsonobject.addProperty("nbt", nbttextcomponent.v_4262_N());
                jsonobject.addProperty("interpret", Boolean.valueOf(nbttextcomponent.w_1484_f()));
                if (p_serialize_1_ instanceof q_3584_e.n_1700_B) {
                    q_3584_e.n_1700_B nbttextcomponent$block = (q_3584_e.n_1700_B)p_serialize_1_;
                    jsonobject.addProperty("block", nbttextcomponent$block.s_956_w());
                } else if (p_serialize_1_ instanceof q_3584_e.J_1907_R) {
                    q_3584_e.J_1907_R nbttextcomponent$entity = (q_3584_e.J_1907_R)p_serialize_1_;
                    jsonobject.addProperty("entity", nbttextcomponent$entity.s_956_w());
                } else {
                    if (!(p_serialize_1_ instanceof q_3584_e.R_4764_Y)) {
                        throw new IllegalArgumentException("Don't know how to serialize " + String.valueOf(p_serialize_1_) + " as a Component");
                    }
                    q_3584_e.R_4764_Y nbttextcomponent$storage = (q_3584_e.R_4764_Y)p_serialize_1_;
                    jsonobject.addProperty("storage", nbttextcomponent$storage.s_956_w().toString());
                }
            }
            return jsonobject;
        }

        public static String n_1700_B(x_282_a component) {
            return n_1700_B.toJson((Object)component);
        }

        public static JsonElement J_1907_R(x_282_a component) {
            return n_1700_B.toJsonTree((Object)component);
        }

        @Nullable
        public static MutableComponent n_1700_B(String p_240643_0_) {
            return i_4431_W.n_1700_B(n_1700_B, p_240643_0_, MutableComponent.class, false);
        }

        @Nullable
        public static MutableComponent n_1700_B(JsonElement json) {
            return (MutableComponent)n_1700_B.fromJson(json, MutableComponent.class);
        }

        @Nullable
        public static MutableComponent J_1907_R(String p_240644_0_) {
            return i_4431_W.n_1700_B(n_1700_B, p_240644_0_, MutableComponent.class, true);
        }

        public static MutableComponent n_1700_B(com.mojang.brigadier.StringReader reader) {
            try {
                JsonReader jsonreader = new JsonReader((Reader)new StringReader(reader.getRemaining()));
                jsonreader.setLenient(false);
                MutableComponent iformattabletextcomponent = (MutableComponent)n_1700_B.getAdapter(MutableComponent.class).read(jsonreader);
                reader.setCursor(reader.getCursor() + lightning.product.x_282_a$n_1700_B.n_1700_B(jsonreader));
                return iformattabletextcomponent;
            }
            catch (IOException | StackOverflowError ioexception) {
                throw new JsonParseException(ioexception);
            }
        }

        private static int n_1700_B(JsonReader reader) {
            try {
                return J_1907_R.getInt(reader) - R_4764_Y.getInt(reader) + 1;
            }
            catch (IllegalAccessException illegalaccessexception) {
                throw new IllegalStateException("Couldn't read position of JsonReader", illegalaccessexception);
            }
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((x_282_a)object, type, jsonSerializationContext);
        }
    }
}



