/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.B_3068_A;
import lightning.product.B_4088_l;
import lightning.product.C_3304_p;
import lightning.product.I_14_v;
import lightning.product.ServerRecipeBook;
import lightning.product.N_4263_v;
import lightning.product.S_4998_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.k_2895_h;
import lightning.product.ServerAdvancementManager;
import lightning.product.o_98_P;
import lightning.product.q_3277_O;
import lightning.product.MinMaxBounds;

public class A_2487_t {
    public static final A_2487_t n_1700_B = new R_4764_Y().n_1700_B();
    private final MinMaxBounds.G_564_y J_1907_R;
    private final I_14_v R_4764_Y;
    private final Map<o_98_P<?>, MinMaxBounds.G_564_y> G_564_y;
    private final Object2BooleanMap<g_2336_b> P_1922_E;
    private final Map<g_2336_b, G_564_y> u_1723_Y;

    private static G_564_y J_1907_R(JsonElement element) {
        if (element.isJsonPrimitive()) {
            boolean flag = element.getAsBoolean();
            return new n_1700_B(flag);
        }
        Object2BooleanOpenHashMap object2booleanmap = new Object2BooleanOpenHashMap();
        JsonObject jsonobject = i_4431_W.w_1484_f(element, "criterion data");
        jsonobject.entrySet().forEach(arg_0 -> A_2487_t.n_1700_B((Object2BooleanMap)object2booleanmap, arg_0));
        return new J_1907_R((Object2BooleanMap<String>)object2booleanmap);
    }

    private A_2487_t(MinMaxBounds.G_564_y level, I_14_v gamemode, Map<o_98_P<?>, MinMaxBounds.G_564_y> stats, Object2BooleanMap<g_2336_b> recipes, Map<g_2336_b, G_564_y> advancements) {
        this.J_1907_R = level;
        this.R_4764_Y = gamemode;
        this.G_564_y = stats;
        this.P_1922_E = recipes;
        this.u_1723_Y = advancements;
    }

    public boolean n_1700_B(N_4263_v player) {
        if (this == n_1700_B) {
            return true;
        }
        if (!(player instanceof B_4088_l)) {
            return false;
        }
        B_4088_l serverplayerentity = (B_4088_l)player;
        if (!this.J_1907_R.R_4764_Y(serverplayerentity.v_165_F)) {
            return false;
        }
        if (this.R_4764_Y != I_14_v.n_1700_B && this.R_4764_Y != serverplayerentity.R_4764_Y.J_1907_R()) {
            return false;
        }
        k_2895_h statisticsmanager = serverplayerentity.n_3318_d();
        for (Map.Entry<o_98_P<?>, MinMaxBounds.G_564_y> entry : this.G_564_y.entrySet()) {
            int i = statisticsmanager.n_1700_B(entry.getKey());
            if (entry.getValue().R_4764_Y(i)) continue;
            return false;
        }
        ServerRecipeBook recipebook = serverplayerentity.d_2427_y();
        for (Object2BooleanMap.Entry entry2 : this.P_1922_E.object2BooleanEntrySet()) {
            if (recipebook.J_1907_R((g_2336_b)entry2.getKey()) == entry2.getBooleanValue()) continue;
            return false;
        }
        if (!this.u_1723_Y.isEmpty()) {
            S_4998_h s_4998_h = serverplayerentity.g_164_R();
            ServerAdvancementManager advancementmanager = serverplayerentity.f_1574_f().RealmsWorldOptions();
            for (Map.Entry<g_2336_b, G_564_y> entry1 : this.u_1723_Y.entrySet()) {
                A_2629_w advancement = advancementmanager.n_1700_B(entry1.getKey());
                if (advancement != null && entry1.getValue().test(s_4998_h.J_1907_R(advancement))) continue;
                return false;
            }
        }
        return true;
    }

    public static A_2487_t n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "player");
            MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("level"));
            String s = i_4431_W.n_1700_B(jsonobject, "gamemode", "");
            I_14_v gametype = I_14_v.n_1700_B(s, I_14_v.n_1700_B);
            HashMap map = Maps.newHashMap();
            JsonArray jsonarray = i_4431_W.n_1700_B(jsonobject, "stats", (JsonArray)null);
            if (jsonarray != null) {
                for (JsonElement jsonelement : jsonarray) {
                    JsonObject jsonobject1 = i_4431_W.w_1484_f(jsonelement, "stats entry");
                    g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject1, "type"));
                    q_3277_O<?> stattype = V_3137_a.z_1333_t.n_1700_B(resourcelocation);
                    if (stattype == null) {
                        throw new JsonParseException("Invalid stat type: " + String.valueOf(resourcelocation));
                    }
                    g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(jsonobject1, "stat"));
                    o_98_P<?> stat = A_2487_t.n_1700_B(stattype, resourcelocation1);
                    MinMaxBounds.G_564_y minmaxbounds$intbound1 = MinMaxBounds.G_564_y.n_1700_B(jsonobject1.get("value"));
                    map.put(stat, minmaxbounds$intbound1);
                }
            }
            Object2BooleanOpenHashMap object2booleanmap = new Object2BooleanOpenHashMap();
            JsonObject jsonobject2 = i_4431_W.n_1700_B(jsonobject, "recipes", new JsonObject());
            for (Map.Entry entry : jsonobject2.entrySet()) {
                g_2336_b resourcelocation2 = new g_2336_b((String)entry.getKey());
                boolean flag = i_4431_W.R_4764_Y((JsonElement)entry.getValue(), "recipe present");
                object2booleanmap.put((Object)resourcelocation2, flag);
            }
            HashMap map1 = Maps.newHashMap();
            JsonObject jsonobject3 = i_4431_W.n_1700_B(jsonobject, "advancements", new JsonObject());
            for (Map.Entry entry1 : jsonobject3.entrySet()) {
                g_2336_b resourcelocation3 = new g_2336_b((String)entry1.getKey());
                G_564_y playerpredicate$iadvancementpredicate = A_2487_t.J_1907_R((JsonElement)entry1.getValue());
                map1.put(resourcelocation3, playerpredicate$iadvancementpredicate);
            }
            return new A_2487_t(minmaxbounds$intbound, gametype, map, (Object2BooleanMap<g_2336_b>)object2booleanmap, map1);
        }
        return n_1700_B;
    }

    private static <T> o_98_P<T> n_1700_B(q_3277_O<T> type, g_2336_b identifier) {
        V_3137_a<T> registry = type.n_1700_B();
        T t = registry.n_1700_B(identifier);
        if (t == null) {
            throw new JsonParseException("Unknown object " + String.valueOf(identifier) + " for stat type " + String.valueOf(V_3137_a.z_1333_t.J_1907_R(type)));
        }
        return type.J_1907_R(t);
    }

    private static <T> g_2336_b n_1700_B(o_98_P<T> stat) {
        return stat.G_564_y().n_1700_B().J_1907_R(stat.P_1922_E());
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("level", this.J_1907_R.G_564_y());
        if (this.R_4764_Y != I_14_v.n_1700_B) {
            jsonobject.addProperty("gamemode", this.R_4764_Y.J_1907_R());
        }
        if (!this.G_564_y.isEmpty()) {
            JsonArray jsonarray = new JsonArray();
            this.G_564_y.forEach((stat, value) -> {
                JsonObject jsonobject3 = new JsonObject();
                jsonobject3.addProperty("type", V_3137_a.z_1333_t.J_1907_R(stat.G_564_y()).toString());
                jsonobject3.addProperty("stat", A_2487_t.n_1700_B(stat).toString());
                jsonobject3.add("value", value.G_564_y());
                jsonarray.add((JsonElement)jsonobject3);
            });
            jsonobject.add("stats", (JsonElement)jsonarray);
        }
        if (!this.P_1922_E.isEmpty()) {
            JsonObject jsonobject1 = new JsonObject();
            this.P_1922_E.forEach((recipeID, unlocked) -> jsonobject1.addProperty(recipeID.toString(), unlocked));
            jsonobject.add("recipes", (JsonElement)jsonobject1);
        }
        if (!this.u_1723_Y.isEmpty()) {
            JsonObject jsonobject2 = new JsonObject();
            this.u_1723_Y.forEach((advancementID, playerAdvancements) -> jsonobject2.add(advancementID.toString(), playerAdvancements.n_1700_B()));
            jsonobject.add("advancements", (JsonElement)jsonobject2);
        }
        return jsonobject;
    }

    private static /* synthetic */ void n_1700_B(Object2BooleanMap object2booleanmap, Map.Entry criterionEntry) {
        boolean flag1 = i_4431_W.R_4764_Y((JsonElement)criterionEntry.getValue(), "criterion test");
        object2booleanmap.put((Object)((String)criterionEntry.getKey()), flag1);
    }

    static class n_1700_B
    implements G_564_y {
        private final boolean n_1700_B;

        public n_1700_B(boolean completion) {
            this.n_1700_B = completion;
        }

        @Override
        public JsonElement n_1700_B() {
            return new JsonPrimitive(Boolean.valueOf(this.n_1700_B));
        }

        public boolean n_1700_B(C_3304_p p_test_1_) {
            return p_test_1_.n_1700_B() == this.n_1700_B;
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((C_3304_p)object);
        }
    }

    static class J_1907_R
    implements G_564_y {
        private final Object2BooleanMap<String> n_1700_B;

        public J_1907_R(Object2BooleanMap<String> completion) {
            this.n_1700_B = completion;
        }

        @Override
        public JsonElement n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            this.n_1700_B.forEach((arg_0, arg_1) -> ((JsonObject)jsonobject).addProperty(arg_0, arg_1));
            return jsonobject;
        }

        public boolean n_1700_B(C_3304_p p_test_1_) {
            for (Object2BooleanMap.Entry entry : this.n_1700_B.object2BooleanEntrySet()) {
                B_3068_A criterionprogress = p_test_1_.R_4764_Y((String)entry.getKey());
                if (criterionprogress != null && criterionprogress.n_1700_B() == entry.getBooleanValue()) continue;
                return false;
            }
            return true;
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((C_3304_p)object);
        }
    }

    static interface G_564_y
    extends Predicate<C_3304_p> {
        public JsonElement n_1700_B();
    }

    public static class R_4764_Y {
        private MinMaxBounds.G_564_y n_1700_B = MinMaxBounds.G_564_y.P_1922_E;
        private I_14_v J_1907_R = I_14_v.n_1700_B;
        private final Map<o_98_P<?>, MinMaxBounds.G_564_y> R_4764_Y = Maps.newHashMap();
        private final Object2BooleanMap<g_2336_b> G_564_y = new Object2BooleanOpenHashMap();
        private final Map<g_2336_b, G_564_y> P_1922_E = Maps.newHashMap();

        public A_2487_t n_1700_B() {
            return new A_2487_t(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }
}


