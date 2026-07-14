/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import lightning.product.i_4431_W;
import lightning.product.x_282_a;

public class ServerStatus {
    private x_282_a n_1700_B;
    private n_1700_B J_1907_R;
    private R_4764_Y R_4764_Y;
    private String G_564_y;

    public x_282_a n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(x_282_a descriptionIn) {
        this.n_1700_B = descriptionIn;
    }

    public n_1700_B J_1907_R() {
        return this.J_1907_R;
    }

    public void n_1700_B(n_1700_B playersIn) {
        this.J_1907_R = playersIn;
    }

    public R_4764_Y R_4764_Y() {
        return this.R_4764_Y;
    }

    public void n_1700_B(R_4764_Y versionIn) {
        this.R_4764_Y = versionIn;
    }

    public void n_1700_B(String faviconBlob) {
        this.G_564_y = faviconBlob;
    }

    public String G_564_y() {
        return this.G_564_y;
    }

    public static class lightning.product.ServerStatus$n_1700_B {
        private final int n_1700_B;
        private final int J_1907_R;
        private GameProfile[] R_4764_Y;

        public lightning.product.ServerStatus$n_1700_B(int maxOnlinePlayers, int onlinePlayers) {
            this.n_1700_B = maxOnlinePlayers;
            this.J_1907_R = onlinePlayers;
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        public GameProfile[] R_4764_Y() {
            return this.R_4764_Y;
        }

        public void n_1700_B(GameProfile[] playersIn) {
            this.R_4764_Y = playersIn;
        }

        public static class n_1700_B
        implements JsonDeserializer<lightning.product.ServerStatus$n_1700_B>,
        JsonSerializer<lightning.product.ServerStatus$n_1700_B> {
            public lightning.product.ServerStatus$n_1700_B n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
                JsonArray jsonarray;
                JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "players");
                lightning.product.ServerStatus$n_1700_B serverstatusresponse$players = new lightning.product.ServerStatus$n_1700_B(i_4431_W.u_2550_I(jsonobject, "max"), i_4431_W.u_2550_I(jsonobject, "online"));
                if (i_4431_W.R_4764_Y(jsonobject, "sample") && (jsonarray = i_4431_W.P_4830_p(jsonobject, "sample")).size() > 0) {
                    GameProfile[] agameprofile = new GameProfile[jsonarray.size()];
                    for (int i = 0; i < agameprofile.length; ++i) {
                        JsonObject jsonobject1 = i_4431_W.w_1484_f(jsonarray.get(i), "player[" + i + "]");
                        String s = i_4431_W.u_1723_Y(jsonobject1, "id");
                        agameprofile[i] = new GameProfile(UUID.fromString(s), i_4431_W.u_1723_Y(jsonobject1, "name"));
                    }
                    serverstatusresponse$players.n_1700_B(agameprofile);
                }
                return serverstatusresponse$players;
            }

            public JsonElement n_1700_B(lightning.product.ServerStatus$n_1700_B p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("max", (Number)p_serialize_1_.n_1700_B());
                jsonobject.addProperty("online", (Number)p_serialize_1_.J_1907_R());
                if (p_serialize_1_.R_4764_Y() != null && p_serialize_1_.R_4764_Y().length > 0) {
                    JsonArray jsonarray = new JsonArray();
                    for (int i = 0; i < p_serialize_1_.R_4764_Y().length; ++i) {
                        JsonObject jsonobject1 = new JsonObject();
                        UUID uuid = p_serialize_1_.R_4764_Y()[i].getId();
                        jsonobject1.addProperty("id", uuid == null ? "" : uuid.toString());
                        jsonobject1.addProperty("name", p_serialize_1_.R_4764_Y()[i].getName());
                        jsonarray.add((JsonElement)jsonobject1);
                    }
                    jsonobject.add("sample", (JsonElement)jsonarray);
                }
                return jsonobject;
            }

            public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
            }

            public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
                return this.n_1700_B((lightning.product.ServerStatus$n_1700_B)object, type, jsonSerializationContext);
            }
        }
    }

    public static class R_4764_Y {
        private final String n_1700_B;
        private final int J_1907_R;

        public R_4764_Y(String nameIn, int protocolIn) {
            this.n_1700_B = nameIn;
            this.J_1907_R = protocolIn;
        }

        public String n_1700_B() {
            return this.n_1700_B;
        }

        public int J_1907_R() {
            return this.J_1907_R;
        }

        public static class n_1700_B
        implements JsonDeserializer<R_4764_Y>,
        JsonSerializer<R_4764_Y> {
            public R_4764_Y n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
                JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "version");
                return new R_4764_Y(i_4431_W.u_1723_Y(jsonobject, "name"), i_4431_W.u_2550_I(jsonobject, "protocol"));
            }

            public JsonElement n_1700_B(R_4764_Y p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("name", p_serialize_1_.n_1700_B());
                jsonobject.addProperty("protocol", (Number)p_serialize_1_.J_1907_R());
                return jsonobject;
            }

            public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
            }

            public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
                return this.n_1700_B((R_4764_Y)object, type, jsonSerializationContext);
            }
        }
    }

    public static class J_1907_R
    implements JsonDeserializer<ServerStatus>,
    JsonSerializer<ServerStatus> {
        public ServerStatus n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "status");
            ServerStatus serverstatusresponse = new ServerStatus();
            if (jsonobject.has("description")) {
                serverstatusresponse.n_1700_B((x_282_a)p_deserialize_3_.deserialize(jsonobject.get("description"), x_282_a.class));
            }
            if (jsonobject.has("players")) {
                serverstatusresponse.n_1700_B((n_1700_B)p_deserialize_3_.deserialize(jsonobject.get("players"), n_1700_B.class));
            }
            if (jsonobject.has("version")) {
                serverstatusresponse.n_1700_B((R_4764_Y)p_deserialize_3_.deserialize(jsonobject.get("version"), R_4764_Y.class));
            }
            if (jsonobject.has("favicon")) {
                serverstatusresponse.n_1700_B(i_4431_W.u_1723_Y(jsonobject, "favicon"));
            }
            return serverstatusresponse;
        }

        public JsonElement n_1700_B(ServerStatus p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            if (p_serialize_1_.n_1700_B() != null) {
                jsonobject.add("description", p_serialize_3_.serialize((Object)p_serialize_1_.n_1700_B()));
            }
            if (p_serialize_1_.J_1907_R() != null) {
                jsonobject.add("players", p_serialize_3_.serialize((Object)p_serialize_1_.J_1907_R()));
            }
            if (p_serialize_1_.R_4764_Y() != null) {
                jsonobject.add("version", p_serialize_3_.serialize((Object)p_serialize_1_.R_4764_Y()));
            }
            if (p_serialize_1_.G_564_y() != null) {
                jsonobject.addProperty("favicon", p_serialize_1_.G_564_y());
            }
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((ServerStatus)object, type, jsonSerializationContext);
        }
    }
}


