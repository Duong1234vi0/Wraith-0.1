/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1613_l;
import lightning.product.r_4318_c;
import lightning.product.t_5_h;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_973_a {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final n_1700_B<?> J_1907_R;
    private final Object R_4764_Y;

    public <T> c_973_a(n_1700_B<T> action, T value) {
        this.J_1907_R = action;
        this.R_4764_Y = value;
    }

    public n_1700_B<?> n_1700_B() {
        return this.J_1907_R;
    }

    @Nullable
    public <T> T n_1700_B(n_1700_B<T> actionType) {
        return this.J_1907_R == actionType ? (T)actionType.J_1907_R(this.R_4764_Y) : null;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            c_973_a hoverevent = (c_973_a)p_equals_1_;
            return this.J_1907_R == hoverevent.J_1907_R && Objects.equals(this.R_4764_Y, hoverevent.R_4764_Y);
        }
        return false;
    }

    public String toString() {
        return "HoverEvent{action=" + String.valueOf(this.J_1907_R) + ", value='" + String.valueOf(this.R_4764_Y) + "'}";
    }

    public int hashCode() {
        int i = this.J_1907_R.hashCode();
        return 31 * i + (this.R_4764_Y != null ? this.R_4764_Y.hashCode() : 0);
    }

    @Nullable
    public static c_973_a n_1700_B(JsonObject json) {
        String s = i_4431_W.n_1700_B(json, "action", (String)null);
        if (s == null) {
            return null;
        }
        n_1700_B action = lightning.product.c_973_a$n_1700_B.n_1700_B(s);
        if (action == null) {
            return null;
        }
        JsonElement jsonelement = json.get("contents");
        if (jsonelement != null) {
            return action.n_1700_B(jsonelement);
        }
        MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(json.get("value"));
        return itextcomponent != null ? action.n_1700_B(itextcomponent) : null;
    }

    public JsonObject J_1907_R() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("action", this.J_1907_R.J_1907_R());
        jsonobject.add("contents", this.J_1907_R.n_1700_B(this.R_4764_Y));
        return jsonobject;
    }

    public static class n_1700_B<T> {
        public static final n_1700_B<x_282_a> n_1700_B = new n_1700_B<x_282_a>("show_text", true, x_282_a.n_1700_B::n_1700_B, x_282_a.n_1700_B::J_1907_R, Function.identity());
        public static final n_1700_B<R_4764_Y> J_1907_R = new n_1700_B<R_4764_Y>("show_item", true, element -> lightning.product.c_973_a$R_4764_Y.n_1700_B(element), hover -> hover.J_1907_R(), component -> lightning.product.c_973_a$R_4764_Y.n_1700_B(component));
        public static final n_1700_B<J_1907_R> R_4764_Y = new n_1700_B<J_1907_R>("show_entity", true, J_1907_R::n_1700_B, J_1907_R::n_1700_B, J_1907_R::n_1700_B);
        private static final Map<String, n_1700_B> G_564_y = (Map)Stream.of(n_1700_B, J_1907_R, R_4764_Y).collect(ImmutableMap.toImmutableMap(n_1700_B::J_1907_R, action -> action));
        private final String P_1922_E;
        private final boolean u_1723_Y;
        private final Function<JsonElement, T> v_4262_N;
        private final Function<T, JsonElement> w_1484_f;
        private final Function<x_282_a, T> t_148_a;

        public n_1700_B(String canonicalName, boolean allowedInChat, Function<JsonElement, T> deserializeFromJSON, Function<T, JsonElement> serializeToJSON, Function<x_282_a, T> deserializeFromTextComponent) {
            this.P_1922_E = canonicalName;
            this.u_1723_Y = allowedInChat;
            this.v_4262_N = deserializeFromJSON;
            this.w_1484_f = serializeToJSON;
            this.t_148_a = deserializeFromTextComponent;
        }

        public boolean n_1700_B() {
            return this.u_1723_Y;
        }

        public String J_1907_R() {
            return this.P_1922_E;
        }

        @Nullable
        public static n_1700_B n_1700_B(String canonicalNameIn) {
            return G_564_y.get(canonicalNameIn);
        }

        private T J_1907_R(Object parameter) {
            return (T)parameter;
        }

        @Nullable
        public c_973_a n_1700_B(JsonElement element) {
            T t = this.v_4262_N.apply(element);
            return t == null ? null : new c_973_a(this, t);
        }

        @Nullable
        public c_973_a n_1700_B(x_282_a component) {
            T t = this.t_148_a.apply(component);
            return t == null ? null : new c_973_a(this, t);
        }

        public JsonElement n_1700_B(Object parameter) {
            return this.w_1484_f.apply(this.J_1907_R(parameter));
        }

        public String toString() {
            return "<action " + this.P_1922_E + ">";
        }
    }

    public static class R_4764_Y {
        private final q_1613_l n_1700_B;
        private final int J_1907_R;
        @Nullable
        private final U_2912_j R_4764_Y;
        @Nullable
        private Z_1993_T G_564_y;

        R_4764_Y(q_1613_l item, int count, @Nullable U_2912_j tag) {
            this.n_1700_B = item;
            this.J_1907_R = count;
            this.R_4764_Y = tag;
        }

        public R_4764_Y(Z_1993_T stack) {
            this(stack.J_1907_R(), stack.t_4043_B(), stack.Q_4569_t() != null ? stack.Q_4569_t().v_4262_N() : null);
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                R_4764_Y hoverevent$itemhover = (R_4764_Y)p_equals_1_;
                return this.J_1907_R == hoverevent$itemhover.J_1907_R && this.n_1700_B.equals(hoverevent$itemhover.n_1700_B) && Objects.equals(this.R_4764_Y, hoverevent$itemhover.R_4764_Y);
            }
            return false;
        }

        public int hashCode() {
            int i = this.n_1700_B.hashCode();
            i = 31 * i + this.J_1907_R;
            return 31 * i + (this.R_4764_Y != null ? this.R_4764_Y.hashCode() : 0);
        }

        public Z_1993_T n_1700_B() {
            if (this.G_564_y == null) {
                this.G_564_y = new Z_1993_T(this.n_1700_B, this.J_1907_R);
                if (this.R_4764_Y != null) {
                    this.G_564_y.R_4764_Y(this.R_4764_Y);
                }
            }
            return this.G_564_y;
        }

        private static R_4764_Y n_1700_B(JsonElement element) {
            if (element.isJsonPrimitive()) {
                return new R_4764_Y(V_3137_a.e_2887_G.n_1700_B(new g_2336_b(element.getAsString())), 1, null);
            }
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "item");
            q_1613_l item = V_3137_a.e_2887_G.n_1700_B(new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "id")));
            int i = i_4431_W.n_1700_B(jsonobject, "count", 1);
            if (jsonobject.has("tag")) {
                String s = i_4431_W.u_1723_Y(jsonobject, "tag");
                try {
                    U_2912_j compoundnbt = r_4318_c.n_1700_B(s);
                    return new R_4764_Y(item, i, compoundnbt);
                }
                catch (CommandSyntaxException commandsyntaxexception) {
                    n_1700_B.warn("Failed to parse tag: {}", (Object)s, (Object)commandsyntaxexception);
                }
            }
            return new R_4764_Y(item, i, null);
        }

        @Nullable
        private static R_4764_Y n_1700_B(x_282_a component) {
            try {
                U_2912_j compoundnbt = r_4318_c.n_1700_B(component.getString());
                return new R_4764_Y(Z_1993_T.n_1700_B(compoundnbt));
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                n_1700_B.warn("Failed to parse item tag: {}", (Object)component, (Object)commandsyntaxexception);
                return null;
            }
        }

        private JsonElement J_1907_R() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("id", V_3137_a.e_2887_G.J_1907_R(this.n_1700_B).toString());
            if (this.J_1907_R != 1) {
                jsonobject.addProperty("count", (Number)this.J_1907_R);
            }
            if (this.R_4764_Y != null) {
                jsonobject.addProperty("tag", this.R_4764_Y.toString());
            }
            return jsonobject;
        }
    }

    public static class J_1907_R {
        public final t_5_h<?> n_1700_B;
        public final UUID J_1907_R;
        @Nullable
        public final x_282_a R_4764_Y;
        @Nullable
        private List<x_282_a> G_564_y;

        public J_1907_R(t_5_h<?> type, UUID id, @Nullable x_282_a name) {
            this.n_1700_B = type;
            this.J_1907_R = id;
            this.R_4764_Y = name;
        }

        @Nullable
        public static J_1907_R n_1700_B(JsonElement element) {
            if (!element.isJsonObject()) {
                return null;
            }
            JsonObject jsonobject = element.getAsJsonObject();
            t_5_h<?> entitytype = V_3137_a.g_221_o.n_1700_B(new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "type")));
            UUID uuid = UUID.fromString(i_4431_W.u_1723_Y(jsonobject, "id"));
            MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(jsonobject.get("name"));
            return new J_1907_R(entitytype, uuid, itextcomponent);
        }

        @Nullable
        public static J_1907_R n_1700_B(x_282_a component) {
            try {
                U_2912_j compoundnbt = r_4318_c.n_1700_B(component.getString());
                MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(compoundnbt.M_588_G("name"));
                t_5_h<?> entitytype = V_3137_a.g_221_o.n_1700_B(new g_2336_b(compoundnbt.M_588_G("type")));
                UUID uuid = UUID.fromString(compoundnbt.M_588_G("id"));
                return new J_1907_R(entitytype, uuid, itextcomponent);
            }
            catch (JsonSyntaxException | CommandSyntaxException jsonsyntaxexception) {
                return null;
            }
        }

        public JsonElement n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("type", V_3137_a.g_221_o.J_1907_R(this.n_1700_B).toString());
            jsonobject.addProperty("id", this.J_1907_R.toString());
            if (this.R_4764_Y != null) {
                jsonobject.add("name", x_282_a.n_1700_B.J_1907_R(this.R_4764_Y));
            }
            return jsonobject;
        }

        public List<x_282_a> J_1907_R() {
            if (this.G_564_y == null) {
                this.G_564_y = Lists.newArrayList();
                if (this.R_4764_Y != null) {
                    this.G_564_y.add(this.R_4764_Y);
                }
                this.G_564_y.add(new F_2904_S("gui.entity_tooltip.type", this.n_1700_B.v_4262_N()));
                this.G_564_y.add(new U_2871_b(this.J_1907_R.toString()));
            }
            return this.G_564_y;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                J_1907_R hoverevent$entityhover = (J_1907_R)p_equals_1_;
                return this.n_1700_B.equals(hoverevent$entityhover.n_1700_B) && this.J_1907_R.equals(hoverevent$entityhover.J_1907_R) && Objects.equals(this.R_4764_Y, hoverevent$entityhover.R_4764_Y);
            }
            return false;
        }

        public int hashCode() {
            int i = this.n_1700_B.hashCode();
            i = 31 * i + this.J_1907_R.hashCode();
            return 31 * i + (this.R_4764_Y != null ? this.R_4764_Y.hashCode() : 0);
        }
    }
}


