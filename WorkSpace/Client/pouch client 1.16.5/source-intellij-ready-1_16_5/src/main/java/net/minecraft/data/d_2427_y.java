/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.J_1008_m;
import lightning.product.V_3137_a;
import lightning.product.b_3278_X;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.r_109_r;
import lightning.product.RequirementsStrategy;
import lightning.product.u_3578_p;
import net.minecraft.data.C_2741_M;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class d_2427_y {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final q_1613_l J_1907_R;
    private final int R_4764_Y;
    private final List<String> G_564_y = Lists.newArrayList();
    private final Map<Character, b_3278_X> P_1922_E = Maps.newLinkedHashMap();
    private final A_2629_w.n_1700_B u_1723_Y = A_2629_w.n_1700_B.n_1700_B();
    private String v_4262_N;

    public d_2427_y(q_1803_e resultIn, int countIn) {
        this.J_1907_R = resultIn.u_1723_Y();
        this.R_4764_Y = countIn;
    }

    public static d_2427_y n_1700_B(q_1803_e resultIn) {
        return d_2427_y.n_1700_B(resultIn, 1);
    }

    public static d_2427_y n_1700_B(q_1803_e resultIn, int countIn) {
        return new d_2427_y(resultIn, countIn);
    }

    public d_2427_y n_1700_B(Character symbol, r_109_r<q_1613_l> tagIn) {
        return this.n_1700_B(symbol, b_3278_X.n_1700_B(tagIn));
    }

    public d_2427_y n_1700_B(Character symbol, q_1803_e itemIn) {
        return this.n_1700_B(symbol, b_3278_X.n_1700_B(itemIn));
    }

    public d_2427_y n_1700_B(Character symbol, b_3278_X ingredientIn) {
        if (this.P_1922_E.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        }
        if (symbol.charValue() == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        }
        this.P_1922_E.put(symbol, ingredientIn);
        return this;
    }

    public d_2427_y n_1700_B(String patternIn) {
        if (!this.G_564_y.isEmpty() && patternIn.length() != this.G_564_y.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        }
        this.G_564_y.add(patternIn);
        return this;
    }

    public d_2427_y n_1700_B(String name, h_1723_G criterionIn) {
        this.u_1723_Y.n_1700_B(name, criterionIn);
        return this;
    }

    public d_2427_y J_1907_R(String groupIn) {
        this.v_4262_N = groupIn;
        return this;
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn) {
        this.n_1700_B(consumerIn, V_3137_a.e_2887_G.J_1907_R(this.J_1907_R));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, String save) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(this.J_1907_R);
        if (new g_2336_b(save).equals(resourcelocation)) {
            throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
        }
        this.n_1700_B(consumerIn, new g_2336_b(save));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, g_2336_b id) {
        this.n_1700_B(id);
        this.u_1723_Y.n_1700_B(new g_2336_b("recipes/root")).n_1700_B("has_the_recipe", u_3578_p.n_1700_B(id)).n_1700_B(J_1008_m.n_1700_B.n_1700_B(id)).n_1700_B(RequirementsStrategy.J_1907_R);
        consumerIn.accept(new n_1700_B(this, id, this.J_1907_R, this.R_4764_Y, this.v_4262_N == null ? "" : this.v_4262_N, this.G_564_y, this.P_1922_E, this.u_1723_Y, new g_2336_b(id.R_4764_Y(), "recipes/" + this.J_1907_R.w_1457_N().J_1907_R() + "/" + id.J_1907_R())));
    }

    private void n_1700_B(g_2336_b id) {
        if (this.G_564_y.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + String.valueOf(id) + "!");
        }
        HashSet set = Sets.newHashSet(this.P_1922_E.keySet());
        set.remove(Character.valueOf(' '));
        for (String s : this.G_564_y) {
            for (int i = 0; i < s.length(); ++i) {
                char c0 = s.charAt(i);
                if (!this.P_1922_E.containsKey(Character.valueOf(c0)) && c0 != ' ') {
                    throw new IllegalStateException("Pattern in recipe " + String.valueOf(id) + " uses undefined symbol '" + c0 + "'");
                }
                set.remove(Character.valueOf(c0));
            }
        }
        if (!set.isEmpty()) {
            throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + String.valueOf(id));
        }
        if (this.G_564_y.size() == 1 && this.G_564_y.get(0).length() == 1) {
            throw new IllegalStateException("Shaped recipe " + String.valueOf(id) + " only takes in a single item - should it be a shapeless recipe instead?");
        }
        if (this.u_1723_Y.R_4764_Y().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(id));
        }
    }

    class n_1700_B
    implements C_2741_M {
        private final g_2336_b n_1700_B;
        private final q_1613_l J_1907_R;
        private final int R_4764_Y;
        private final String G_564_y;
        private final List<String> P_1922_E;
        private final Map<Character, b_3278_X> u_1723_Y;
        private final A_2629_w.n_1700_B v_4262_N;
        private final g_2336_b w_1484_f;

        public n_1700_B(d_2427_y this$0, g_2336_b idIn, q_1613_l resultIn, int countIn, String groupIn, List<String> patternIn, Map<Character, b_3278_X> keyIn, A_2629_w.n_1700_B advancementBuilderIn, g_2336_b advancementIdIn) {
            this.n_1700_B = idIn;
            this.J_1907_R = resultIn;
            this.R_4764_Y = countIn;
            this.G_564_y = groupIn;
            this.P_1922_E = patternIn;
            this.u_1723_Y = keyIn;
            this.v_4262_N = advancementBuilderIn;
            this.w_1484_f = advancementIdIn;
        }

        @Override
        public void n_1700_B(JsonObject json) {
            if (!this.G_564_y.isEmpty()) {
                json.addProperty("group", this.G_564_y);
            }
            JsonArray jsonarray = new JsonArray();
            for (String string : this.P_1922_E) {
                jsonarray.add(string);
            }
            json.add("pattern", (JsonElement)jsonarray);
            JsonObject jsonobject = new JsonObject();
            for (Map.Entry<Character, b_3278_X> entry : this.u_1723_Y.entrySet()) {
                jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().R_4764_Y());
            }
            json.add("key", (JsonElement)jsonobject);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.J_1907_R).toString());
            if (this.R_4764_Y > 1) {
                jsonObject.addProperty("count", (Number)this.R_4764_Y);
            }
            json.add("result", (JsonElement)jsonObject);
        }

        @Override
        public RecipeSerializer<?> n_1700_B() {
            return RecipeSerializer.n_1700_B;
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
        }

        @Override
        @Nullable
        public JsonObject R_4764_Y() {
            return this.v_4262_N.J_1907_R();
        }

        @Override
        @Nullable
        public g_2336_b G_564_y() {
            return this.w_1484_f;
        }
    }
}


