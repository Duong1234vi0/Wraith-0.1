/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.J_1008_m;
import lightning.product.M_712_N;
import lightning.product.T_4001_f;
import lightning.product.U_2871_b;
import lightning.product.W_4813_f;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.c_973_a;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.i_4431_W;
import lightning.product.q_1803_e;
import lightning.product.RequirementsStrategy;
import lightning.product.DeserializationContext;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import org.apache.commons.lang3.ArrayUtils;

public class A_2629_w {
    private final A_2629_w n_1700_B;
    private final M_712_N J_1907_R;
    private final J_1008_m R_4764_Y;
    private final g_2336_b G_564_y;
    private final Map<String, T_4001_f> P_1922_E;
    private final String[][] u_1723_Y;
    private final Set<A_2629_w> v_4262_N = Sets.newLinkedHashSet();
    private final x_282_a w_1484_f;

    public A_2629_w(g_2336_b id, @Nullable A_2629_w parentIn, @Nullable M_712_N displayIn, J_1008_m rewardsIn, Map<String, T_4001_f> criteriaIn, String[][] requirementsIn) {
        this.G_564_y = id;
        this.J_1907_R = displayIn;
        this.P_1922_E = ImmutableMap.copyOf(criteriaIn);
        this.n_1700_B = parentIn;
        this.R_4764_Y = rewardsIn;
        this.u_1723_Y = requirementsIn;
        if (parentIn != null) {
            parentIn.n_1700_B(this);
        }
        if (displayIn == null) {
            this.w_1484_f = new U_2871_b(id.toString());
        } else {
            x_282_a itextcomponent = displayIn.n_1700_B();
            D_4024_W textformatting = displayIn.P_1922_E().R_4764_Y();
            MutableComponent itextcomponent1 = ComponentUtils.n_1700_B(itextcomponent.P_1922_E(), Z_1567_W.n_1700_B.n_1700_B(textformatting)).n_1700_B("\n").n_1700_B(displayIn.J_1907_R());
            MutableComponent itextcomponent2 = itextcomponent.P_1922_E().n_1700_B(style -> style.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, itextcomponent1)));
            this.w_1484_f = ComponentUtils.n_1700_B(itextcomponent2).n_1700_B(textformatting);
        }
    }

    public n_1700_B n_1700_B() {
        return new n_1700_B(this.n_1700_B == null ? null : this.n_1700_B.w_1484_f(), this.J_1907_R, this.R_4764_Y, this.P_1922_E, this.u_1723_Y);
    }

    @Nullable
    public A_2629_w J_1907_R() {
        return this.n_1700_B;
    }

    @Nullable
    public M_712_N R_4764_Y() {
        return this.J_1907_R;
    }

    public J_1008_m G_564_y() {
        return this.R_4764_Y;
    }

    public String toString() {
        return "SimpleAdvancement{id=" + String.valueOf(this.w_1484_f()) + ", parent=" + String.valueOf(this.n_1700_B == null ? "null" : this.n_1700_B.w_1484_f()) + ", display=" + String.valueOf(this.J_1907_R) + ", rewards=" + String.valueOf(this.R_4764_Y) + ", criteria=" + String.valueOf(this.P_1922_E) + ", requirements=" + Arrays.deepToString((Object[])this.u_1723_Y) + "}";
    }

    public Iterable<A_2629_w> P_1922_E() {
        return this.v_4262_N;
    }

    public Map<String, T_4001_f> u_1723_Y() {
        return this.P_1922_E;
    }

    public int v_4262_N() {
        return this.u_1723_Y.length;
    }

    public void n_1700_B(A_2629_w advancementIn) {
        this.v_4262_N.add(advancementIn);
    }

    public g_2336_b w_1484_f() {
        return this.G_564_y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof A_2629_w)) {
            return false;
        }
        A_2629_w advancement = (A_2629_w)p_equals_1_;
        return this.G_564_y.equals(advancement.G_564_y);
    }

    public int hashCode() {
        return this.G_564_y.hashCode();
    }

    public String[][] t_148_a() {
        return this.u_1723_Y;
    }

    public x_282_a s_956_w() {
        return this.w_1484_f;
    }

    public static class n_1700_B {
        private g_2336_b n_1700_B;
        private A_2629_w J_1907_R;
        private M_712_N R_4764_Y;
        private J_1008_m G_564_y = J_1008_m.n_1700_B;
        private Map<String, T_4001_f> P_1922_E = Maps.newLinkedHashMap();
        private String[][] u_1723_Y;
        private RequirementsStrategy v_4262_N = RequirementsStrategy.n_1700_B;

        private n_1700_B(@Nullable g_2336_b parentIdIn, @Nullable M_712_N displayIn, J_1008_m rewardsIn, Map<String, T_4001_f> criteriaIn, String[][] requirementsIn) {
            this.n_1700_B = parentIdIn;
            this.R_4764_Y = displayIn;
            this.G_564_y = rewardsIn;
            this.P_1922_E = criteriaIn;
            this.u_1723_Y = requirementsIn;
        }

        private n_1700_B() {
        }

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(A_2629_w parentIn) {
            this.J_1907_R = parentIn;
            return this;
        }

        public n_1700_B n_1700_B(g_2336_b parentIdIn) {
            this.n_1700_B = parentIdIn;
            return this;
        }

        public n_1700_B n_1700_B(Z_1993_T stack, x_282_a title, x_282_a description, @Nullable g_2336_b background, W_4813_f frame, boolean showToast, boolean announceToChat, boolean hidden) {
            return this.n_1700_B(new M_712_N(stack, title, description, background, frame, showToast, announceToChat, hidden));
        }

        public n_1700_B n_1700_B(q_1803_e itemIn, x_282_a title, x_282_a description, @Nullable g_2336_b background, W_4813_f frame, boolean showToast, boolean announceToChat, boolean hidden) {
            return this.n_1700_B(new M_712_N(new Z_1993_T(itemIn.u_1723_Y()), title, description, background, frame, showToast, announceToChat, hidden));
        }

        public n_1700_B n_1700_B(M_712_N displayIn) {
            this.R_4764_Y = displayIn;
            return this;
        }

        public n_1700_B n_1700_B(J_1008_m.n_1700_B rewardsBuilder) {
            return this.n_1700_B(rewardsBuilder.n_1700_B());
        }

        public n_1700_B n_1700_B(J_1008_m rewards) {
            this.G_564_y = rewards;
            return this;
        }

        public n_1700_B n_1700_B(String key, h_1723_G criterionIn) {
            return this.n_1700_B(key, new T_4001_f(criterionIn));
        }

        public n_1700_B n_1700_B(String key, T_4001_f criterionIn) {
            if (this.P_1922_E.containsKey(key)) {
                throw new IllegalArgumentException("Duplicate criterion " + key);
            }
            this.P_1922_E.put(key, criterionIn);
            return this;
        }

        public n_1700_B n_1700_B(RequirementsStrategy strategy) {
            this.v_4262_N = strategy;
            return this;
        }

        public boolean n_1700_B(Function<g_2336_b, A_2629_w> lookup) {
            if (this.n_1700_B == null) {
                return true;
            }
            if (this.J_1907_R == null) {
                this.J_1907_R = lookup.apply(this.n_1700_B);
            }
            return this.J_1907_R != null;
        }

        public A_2629_w J_1907_R(g_2336_b id) {
            if (!this.n_1700_B((g_2336_b parentID) -> null)) {
                throw new IllegalStateException("Tried to build incomplete advancement!");
            }
            if (this.u_1723_Y == null) {
                this.u_1723_Y = this.v_4262_N.createRequirements(this.P_1922_E.keySet());
            }
            return new A_2629_w(id, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
        }

        public A_2629_w n_1700_B(Consumer<A_2629_w> consumer, String id) {
            A_2629_w advancement = this.J_1907_R(new g_2336_b(id));
            consumer.accept(advancement);
            return advancement;
        }

        public JsonObject J_1907_R() {
            if (this.u_1723_Y == null) {
                this.u_1723_Y = this.v_4262_N.createRequirements(this.P_1922_E.keySet());
            }
            JsonObject jsonobject = new JsonObject();
            if (this.J_1907_R != null) {
                jsonobject.addProperty("parent", this.J_1907_R.w_1484_f().toString());
            } else if (this.n_1700_B != null) {
                jsonobject.addProperty("parent", this.n_1700_B.toString());
            }
            if (this.R_4764_Y != null) {
                jsonobject.add("display", this.R_4764_Y.u_2550_I());
            }
            jsonobject.add("rewards", this.G_564_y.n_1700_B());
            JsonObject jsonobject1 = new JsonObject();
            for (Map.Entry<String, T_4001_f> entry : this.P_1922_E.entrySet()) {
                jsonobject1.add(entry.getKey(), entry.getValue().J_1907_R());
            }
            jsonobject.add("criteria", (JsonElement)jsonobject1);
            JsonArray jsonarray1 = new JsonArray();
            for (String[] astring : this.u_1723_Y) {
                JsonArray jsonarray = new JsonArray();
                for (String s : astring) {
                    jsonarray.add(s);
                }
                jsonarray1.add((JsonElement)jsonarray);
            }
            jsonobject.add("requirements", (JsonElement)jsonarray1);
            return jsonobject;
        }

        public void n_1700_B(b_2585_i buf) {
            if (this.n_1700_B == null) {
                buf.writeBoolean(false);
            } else {
                buf.writeBoolean(true);
                buf.n_1700_B(this.n_1700_B);
            }
            if (this.R_4764_Y == null) {
                buf.writeBoolean(false);
            } else {
                buf.writeBoolean(true);
                this.R_4764_Y.n_1700_B(buf);
            }
            T_4001_f.n_1700_B(this.P_1922_E, buf);
            buf.G_564_y(this.u_1723_Y.length);
            for (String[] astring : this.u_1723_Y) {
                buf.G_564_y(astring.length);
                for (String s : astring) {
                    buf.n_1700_B(s);
                }
            }
        }

        public String toString() {
            return "Task Advancement{parentId=" + String.valueOf(this.n_1700_B) + ", display=" + String.valueOf(this.R_4764_Y) + ", rewards=" + String.valueOf(this.G_564_y) + ", criteria=" + String.valueOf(this.P_1922_E) + ", requirements=" + Arrays.deepToString((Object[])this.u_1723_Y) + "}";
        }

        public static n_1700_B n_1700_B(JsonObject json, DeserializationContext conditionParser) {
            g_2336_b resourcelocation = json.has("parent") ? new g_2336_b(i_4431_W.u_1723_Y(json, "parent")) : null;
            M_712_N displayinfo = json.has("display") ? M_712_N.n_1700_B(i_4431_W.M_588_G(json, "display")) : null;
            J_1008_m advancementrewards = json.has("rewards") ? J_1008_m.n_1700_B(i_4431_W.M_588_G(json, "rewards")) : J_1008_m.n_1700_B;
            Map<String, T_4001_f> map = T_4001_f.J_1907_R(i_4431_W.M_588_G(json, "criteria"), conditionParser);
            if (map.isEmpty()) {
                throw new JsonSyntaxException("Advancement criteria cannot be empty");
            }
            JsonArray jsonarray = i_4431_W.n_1700_B(json, "requirements", new JsonArray());
            String[][] astring = new String[jsonarray.size()][];
            for (int i = 0; i < jsonarray.size(); ++i) {
                JsonArray jsonarray1 = i_4431_W.t_148_a(jsonarray.get(i), "requirements[" + i + "]");
                astring[i] = new String[jsonarray1.size()];
                for (int j = 0; j < jsonarray1.size(); ++j) {
                    astring[i][j] = i_4431_W.n_1700_B(jsonarray1.get(j), "requirements[" + i + "][" + j + "]");
                }
            }
            if (astring.length == 0) {
                astring = new String[map.size()][];
                int k = 0;
                for (String s2 : map.keySet()) {
                    astring[k++] = new String[]{s2};
                }
            }
            for (String[] astring1 : astring) {
                if (astring1.length == 0 && map.isEmpty()) {
                    throw new JsonSyntaxException("Requirement entry cannot be empty");
                }
                String[] stringArray = astring1;
                int n = stringArray.length;
                for (int i = 0; i < n; ++i) {
                    String s = stringArray[i];
                    if (map.containsKey(s)) continue;
                    throw new JsonSyntaxException("Unknown required criterion '" + s + "'");
                }
            }
            for (String s1 : map.keySet()) {
                boolean flag = false;
                for (Object[] objectArray : astring) {
                    if (!ArrayUtils.contains((Object[])objectArray, (Object)s1)) continue;
                    flag = true;
                    break;
                }
                if (flag) continue;
                throw new JsonSyntaxException("Criterion '" + s1 + "' isn't a requirement for completion. This isn't supported behaviour, all criteria must be required.");
            }
            return new n_1700_B(resourcelocation, displayinfo, advancementrewards, map, astring);
        }

        public static n_1700_B J_1907_R(b_2585_i buf) {
            g_2336_b resourcelocation = buf.readBoolean() ? buf.P_4830_p() : null;
            M_712_N displayinfo = buf.readBoolean() ? M_712_N.J_1907_R(buf) : null;
            Map<String, T_4001_f> map = T_4001_f.R_4764_Y(buf);
            String[][] astring = new String[buf.u_1723_Y()][];
            for (int i = 0; i < astring.length; ++i) {
                astring[i] = new String[buf.u_1723_Y()];
                for (int j = 0; j < astring[i].length; ++j) {
                    astring[i][j] = buf.P_1922_E(Short.MAX_VALUE);
                }
            }
            return new n_1700_B(resourcelocation, displayinfo, J_1008_m.n_1700_B, map, astring);
        }

        public Map<String, T_4001_f> R_4764_Y() {
            return this.P_1922_E;
        }
    }
}


