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
import java.lang.reflect.Type;
import java.util.Objects;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.TextColor;
import lightning.product.c_973_a;
import lightning.product.g_2336_b;
import lightning.product.i_2909_p;
import lightning.product.i_4431_W;
import lightning.product.s_3109_F;

public class Z_1567_W {
    public static final Z_1567_W n_1700_B = new Z_1567_W(null, null, null, null, null, null, null, null, null, null);
    public static final g_2336_b J_1907_R = new g_2336_b("minecraft", "default");
    @Nullable
    private final TextColor R_4764_Y;
    @Nullable
    private final Boolean G_564_y;
    @Nullable
    private final Boolean P_1922_E;
    @Nullable
    private final Boolean u_1723_Y;
    @Nullable
    private final Boolean v_4262_N;
    @Nullable
    private final Boolean w_1484_f;
    @Nullable
    private final i_2909_p t_148_a;
    @Nullable
    private final c_973_a s_956_w;
    @Nullable
    private final String u_2550_I;
    @Nullable
    private final g_2336_b M_588_G;

    private Z_1567_W(@Nullable TextColor color, @Nullable Boolean bold, @Nullable Boolean italic, @Nullable Boolean underlined, @Nullable Boolean strikethrough, @Nullable Boolean obfuscated, @Nullable i_2909_p clickEvent, @Nullable c_973_a hoverEvent, @Nullable String insertion, @Nullable g_2336_b fontId) {
        this.R_4764_Y = color;
        this.G_564_y = bold;
        this.P_1922_E = italic;
        this.u_1723_Y = underlined;
        this.v_4262_N = strikethrough;
        this.w_1484_f = obfuscated;
        this.t_148_a = clickEvent;
        this.s_956_w = hoverEvent;
        this.u_2550_I = insertion;
        this.M_588_G = fontId;
    }

    @Nullable
    public TextColor n_1700_B() {
        return this.R_4764_Y;
    }

    public boolean J_1907_R() {
        return this.G_564_y == Boolean.TRUE;
    }

    public boolean R_4764_Y() {
        return this.P_1922_E == Boolean.TRUE;
    }

    public boolean G_564_y() {
        return this.v_4262_N == Boolean.TRUE;
    }

    public boolean P_1922_E() {
        return this.u_1723_Y == Boolean.TRUE;
    }

    public boolean u_1723_Y() {
        return this.w_1484_f == Boolean.TRUE;
    }

    public boolean v_4262_N() {
        return this == n_1700_B;
    }

    @Nullable
    public i_2909_p w_1484_f() {
        return this.t_148_a;
    }

    @Nullable
    public c_973_a t_148_a() {
        return this.s_956_w;
    }

    @Nullable
    public String s_956_w() {
        return this.u_2550_I;
    }

    public g_2336_b u_2550_I() {
        return this.M_588_G != null ? this.M_588_G : J_1907_R;
    }

    public Z_1567_W n_1700_B(@Nullable TextColor color) {
        return new Z_1567_W(color, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(@Nullable D_4024_W formatting) {
        return this.n_1700_B(formatting != null ? TextColor.n_1700_B(formatting) : null);
    }

    public Z_1567_W n_1700_B(@Nullable Boolean bold) {
        return new Z_1567_W(this.R_4764_Y, bold, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W J_1907_R(@Nullable Boolean italic) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, italic, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W R_4764_Y(@Nullable Boolean p_244282_1_) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, this.P_1922_E, p_244282_1_, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(@Nullable i_2909_p clickEvent) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, clickEvent, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(@Nullable c_973_a hoverEvent) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, hoverEvent, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(@Nullable String insertion) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, insertion, this.M_588_G);
    }

    public Z_1567_W n_1700_B(@Nullable g_2336_b fontId) {
        return new Z_1567_W(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, fontId);
    }

    public Z_1567_W J_1907_R(D_4024_W formatting) {
        TextColor color = this.R_4764_Y;
        Boolean obool = this.G_564_y;
        Boolean obool1 = this.P_1922_E;
        Boolean obool2 = this.v_4262_N;
        Boolean obool3 = this.u_1723_Y;
        Boolean obool4 = this.w_1484_f;
        switch (formatting) {
            case t_1786_h: {
                obool4 = true;
                break;
            }
            case multiplayerClientSuggestionProvider: {
                obool = true;
                break;
            }
            case w_1457_N: {
                obool2 = true;
                break;
            }
            case Y_601_j: {
                obool3 = true;
                break;
            }
            case Y_259_p: {
                obool1 = true;
                break;
            }
            case Q_2552_b: {
                return n_1700_B;
            }
            default: {
                color = TextColor.n_1700_B(formatting);
            }
        }
        return new Z_1567_W(color, obool, obool1, obool3, obool2, obool4, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W R_4764_Y(D_4024_W formatting) {
        TextColor color = this.R_4764_Y;
        Boolean obool = this.G_564_y;
        Boolean obool1 = this.P_1922_E;
        Boolean obool2 = this.v_4262_N;
        Boolean obool3 = this.u_1723_Y;
        Boolean obool4 = this.w_1484_f;
        switch (formatting) {
            case t_1786_h: {
                obool4 = true;
                break;
            }
            case multiplayerClientSuggestionProvider: {
                obool = true;
                break;
            }
            case w_1457_N: {
                obool2 = true;
                break;
            }
            case Y_601_j: {
                obool3 = true;
                break;
            }
            case Y_259_p: {
                obool1 = true;
                break;
            }
            case Q_2552_b: {
                return n_1700_B;
            }
            default: {
                obool4 = false;
                obool = false;
                obool2 = false;
                obool3 = false;
                obool1 = false;
                color = TextColor.n_1700_B(formatting);
            }
        }
        return new Z_1567_W(color, obool, obool1, obool3, obool2, obool4, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(D_4024_W ... formatings) {
        TextColor color = this.R_4764_Y;
        Boolean obool = this.G_564_y;
        Boolean obool1 = this.P_1922_E;
        Boolean obool2 = this.v_4262_N;
        Boolean obool3 = this.u_1723_Y;
        Boolean obool4 = this.w_1484_f;
        block8: for (D_4024_W textformatting : formatings) {
            switch (textformatting) {
                case t_1786_h: {
                    obool4 = true;
                    continue block8;
                }
                case multiplayerClientSuggestionProvider: {
                    obool = true;
                    continue block8;
                }
                case w_1457_N: {
                    obool2 = true;
                    continue block8;
                }
                case Y_601_j: {
                    obool3 = true;
                    continue block8;
                }
                case Y_259_p: {
                    obool1 = true;
                    continue block8;
                }
                case Q_2552_b: {
                    return n_1700_B;
                }
                default: {
                    color = TextColor.n_1700_B(textformatting);
                }
            }
        }
        return new Z_1567_W(color, obool, obool1, obool3, obool2, obool4, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    public Z_1567_W n_1700_B(Z_1567_W style) {
        if (this == n_1700_B) {
            return style;
        }
        return style == n_1700_B ? this : new Z_1567_W(this.R_4764_Y != null ? this.R_4764_Y : style.R_4764_Y, this.G_564_y != null ? this.G_564_y : style.G_564_y, this.P_1922_E != null ? this.P_1922_E : style.P_1922_E, this.u_1723_Y != null ? this.u_1723_Y : style.u_1723_Y, this.v_4262_N != null ? this.v_4262_N : style.v_4262_N, this.w_1484_f != null ? this.w_1484_f : style.w_1484_f, this.t_148_a != null ? this.t_148_a : style.t_148_a, this.s_956_w != null ? this.s_956_w : style.s_956_w, this.u_2550_I != null ? this.u_2550_I : style.u_2550_I, this.M_588_G != null ? this.M_588_G : style.M_588_G);
    }

    public String toString() {
        return "Style{ color=" + String.valueOf(this.R_4764_Y) + ", bold=" + this.G_564_y + ", italic=" + this.P_1922_E + ", underlined=" + this.u_1723_Y + ", strikethrough=" + this.v_4262_N + ", obfuscated=" + this.w_1484_f + ", clickEvent=" + String.valueOf(this.w_1484_f()) + ", hoverEvent=" + String.valueOf(this.t_148_a()) + ", insertion=" + this.s_956_w() + ", font=" + String.valueOf(this.u_2550_I()) + "}";
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof Z_1567_W)) {
            return false;
        }
        Z_1567_W style = (Z_1567_W)p_equals_1_;
        return this.J_1907_R() == style.J_1907_R() && Objects.equals(this.n_1700_B(), style.n_1700_B()) && this.R_4764_Y() == style.R_4764_Y() && this.u_1723_Y() == style.u_1723_Y() && this.G_564_y() == style.G_564_y() && this.P_1922_E() == style.P_1922_E() && Objects.equals(this.w_1484_f(), style.w_1484_f()) && Objects.equals(this.t_148_a(), style.t_148_a()) && Objects.equals(this.s_956_w(), style.s_956_w()) && Objects.equals(this.u_2550_I(), style.u_2550_I());
    }

    public int hashCode() {
        return Objects.hash(this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    public static class n_1700_B
    implements JsonDeserializer<Z_1567_W>,
    JsonSerializer<Z_1567_W> {
        @Nullable
        public Z_1567_W n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            if (p_deserialize_1_.isJsonObject()) {
                JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
                if (jsonobject == null) {
                    return null;
                }
                Boolean obool = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject, "bold");
                Boolean obool1 = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject, "italic");
                Boolean obool2 = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject, "underlined");
                Boolean obool3 = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject, "strikethrough");
                Boolean obool4 = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject, "obfuscated");
                TextColor color = lightning.product.Z_1567_W$n_1700_B.P_1922_E(jsonobject);
                String s = lightning.product.Z_1567_W$n_1700_B.G_564_y(jsonobject);
                i_2909_p clickevent = lightning.product.Z_1567_W$n_1700_B.R_4764_Y(jsonobject);
                c_973_a hoverevent = lightning.product.Z_1567_W$n_1700_B.J_1907_R(jsonobject);
                g_2336_b resourcelocation = lightning.product.Z_1567_W$n_1700_B.n_1700_B(jsonobject);
                return new Z_1567_W(color, obool, obool1, obool2, obool3, obool4, clickevent, hoverevent, s, resourcelocation);
            }
            return null;
        }

        @Nullable
        private static g_2336_b n_1700_B(JsonObject json) {
            if (json.has("font")) {
                String s = i_4431_W.u_1723_Y(json, "font");
                try {
                    return new g_2336_b(s);
                }
                catch (s_3109_F resourcelocationexception) {
                    throw new JsonSyntaxException("Invalid font name: " + s);
                }
            }
            return null;
        }

        @Nullable
        private static c_973_a J_1907_R(JsonObject json) {
            JsonObject jsonobject;
            c_973_a hoverevent;
            if (json.has("hoverEvent") && (hoverevent = c_973_a.n_1700_B(jsonobject = i_4431_W.M_588_G(json, "hoverEvent"))) != null && hoverevent.n_1700_B().n_1700_B()) {
                return hoverevent;
            }
            return null;
        }

        @Nullable
        private static i_2909_p R_4764_Y(JsonObject json) {
            if (json.has("clickEvent")) {
                JsonObject jsonobject = i_4431_W.M_588_G(json, "clickEvent");
                String s = i_4431_W.n_1700_B(jsonobject, "action", (String)null);
                i_2909_p.n_1700_B clickevent$action = s == null ? null : i_2909_p.n_1700_B.n_1700_B(s);
                String s1 = i_4431_W.n_1700_B(jsonobject, "value", (String)null);
                if (clickevent$action != null && s1 != null && clickevent$action.n_1700_B()) {
                    return new i_2909_p(clickevent$action, s1);
                }
            }
            return null;
        }

        @Nullable
        private static String G_564_y(JsonObject json) {
            return i_4431_W.n_1700_B(json, "insertion", (String)null);
        }

        @Nullable
        private static TextColor P_1922_E(JsonObject json) {
            if (json.has("color")) {
                String s = i_4431_W.u_1723_Y(json, "color");
                return TextColor.n_1700_B(s);
            }
            return null;
        }

        @Nullable
        private static Boolean n_1700_B(JsonObject json, String memberName) {
            return json.has(memberName) ? Boolean.valueOf(json.get(memberName).getAsBoolean()) : null;
        }

        @Nullable
        public JsonElement n_1700_B(Z_1567_W p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            if (p_serialize_1_.v_4262_N()) {
                return null;
            }
            JsonObject jsonobject = new JsonObject();
            if (p_serialize_1_.G_564_y != null) {
                jsonobject.addProperty("bold", p_serialize_1_.G_564_y);
            }
            if (p_serialize_1_.P_1922_E != null) {
                jsonobject.addProperty("italic", p_serialize_1_.P_1922_E);
            }
            if (p_serialize_1_.u_1723_Y != null) {
                jsonobject.addProperty("underlined", p_serialize_1_.u_1723_Y);
            }
            if (p_serialize_1_.v_4262_N != null) {
                jsonobject.addProperty("strikethrough", p_serialize_1_.v_4262_N);
            }
            if (p_serialize_1_.w_1484_f != null) {
                jsonobject.addProperty("obfuscated", p_serialize_1_.w_1484_f);
            }
            if (p_serialize_1_.R_4764_Y != null) {
                jsonobject.addProperty("color", p_serialize_1_.R_4764_Y.J_1907_R());
            }
            if (p_serialize_1_.u_2550_I != null) {
                jsonobject.add("insertion", p_serialize_3_.serialize((Object)p_serialize_1_.u_2550_I));
            }
            if (p_serialize_1_.t_148_a != null) {
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.addProperty("action", p_serialize_1_.t_148_a.n_1700_B().J_1907_R());
                jsonobject1.addProperty("value", p_serialize_1_.t_148_a.J_1907_R());
                jsonobject.add("clickEvent", (JsonElement)jsonobject1);
            }
            if (p_serialize_1_.s_956_w != null) {
                jsonobject.add("hoverEvent", (JsonElement)p_serialize_1_.s_956_w.J_1907_R());
            }
            if (p_serialize_1_.M_588_G != null) {
                jsonobject.addProperty("font", p_serialize_1_.M_588_G.toString());
            }
            return jsonobject;
        }

        @Nullable
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        @Nullable
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((Z_1567_W)object, type, jsonSerializationContext);
        }
    }
}


