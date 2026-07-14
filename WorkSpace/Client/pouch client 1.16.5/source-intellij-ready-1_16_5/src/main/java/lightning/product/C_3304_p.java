/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.B_3068_A;
import lightning.product.T_4001_f;
import lightning.product.b_2585_i;
import lightning.product.i_4431_W;

public class C_3304_p
implements Comparable<C_3304_p> {
    private final Map<String, B_3068_A> n_1700_B = Maps.newHashMap();
    private String[][] J_1907_R = new String[0][];

    public void n_1700_B(Map<String, T_4001_f> criteriaIn, String[][] requirements) {
        Set<String> set = criteriaIn.keySet();
        this.n_1700_B.entrySet().removeIf(criteriaEntry -> !set.contains(criteriaEntry.getKey()));
        for (String s : set) {
            if (this.n_1700_B.containsKey(s)) continue;
            this.n_1700_B.put(s, new B_3068_A());
        }
        this.J_1907_R = requirements;
    }

    public boolean n_1700_B() {
        if (this.J_1907_R.length == 0) {
            return false;
        }
        for (String[] astring : this.J_1907_R) {
            boolean flag = false;
            for (String s : astring) {
                B_3068_A criterionprogress = this.R_4764_Y(s);
                if (criterionprogress == null || !criterionprogress.n_1700_B()) continue;
                flag = true;
                break;
            }
            if (flag) continue;
            return false;
        }
        return true;
    }

    public boolean J_1907_R() {
        for (B_3068_A criterionprogress : this.n_1700_B.values()) {
            if (!criterionprogress.n_1700_B()) continue;
            return true;
        }
        return false;
    }

    public boolean n_1700_B(String criterionIn) {
        B_3068_A criterionprogress = this.n_1700_B.get(criterionIn);
        if (criterionprogress != null && !criterionprogress.n_1700_B()) {
            criterionprogress.J_1907_R();
            return true;
        }
        return false;
    }

    public boolean J_1907_R(String criterionIn) {
        B_3068_A criterionprogress = this.n_1700_B.get(criterionIn);
        if (criterionprogress != null && criterionprogress.n_1700_B()) {
            criterionprogress.R_4764_Y();
            return true;
        }
        return false;
    }

    public String toString() {
        return "AdvancementProgress{criteria=" + String.valueOf(this.n_1700_B) + ", requirements=" + Arrays.deepToString((Object[])this.J_1907_R) + "}";
    }

    public void n_1700_B(b_2585_i buffer) {
        buffer.G_564_y(this.n_1700_B.size());
        for (Map.Entry<String, B_3068_A> entry : this.n_1700_B.entrySet()) {
            buffer.n_1700_B(entry.getKey());
            entry.getValue().n_1700_B(buffer);
        }
    }

    public static C_3304_p J_1907_R(b_2585_i buffer) {
        C_3304_p advancementprogress = new C_3304_p();
        int i = buffer.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            advancementprogress.n_1700_B.put(buffer.P_1922_E(Short.MAX_VALUE), B_3068_A.J_1907_R(buffer));
        }
        return advancementprogress;
    }

    @Nullable
    public B_3068_A R_4764_Y(String criterionIn) {
        return this.n_1700_B.get(criterionIn);
    }

    public float R_4764_Y() {
        if (this.n_1700_B.isEmpty()) {
            return 0.0f;
        }
        float f = this.J_1907_R.length;
        float f1 = this.w_1484_f();
        return f1 / f;
    }

    @Nullable
    public String G_564_y() {
        if (this.n_1700_B.isEmpty()) {
            return null;
        }
        int i = this.J_1907_R.length;
        if (i <= 1) {
            return null;
        }
        int j = this.w_1484_f();
        return j + "/" + i;
    }

    private int w_1484_f() {
        int i = 0;
        for (String[] astring : this.J_1907_R) {
            boolean flag = false;
            for (String s : astring) {
                B_3068_A criterionprogress = this.R_4764_Y(s);
                if (criterionprogress == null || !criterionprogress.n_1700_B()) continue;
                flag = true;
                break;
            }
            if (!flag) continue;
            ++i;
        }
        return i;
    }

    public Iterable<String> P_1922_E() {
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<String, B_3068_A> entry : this.n_1700_B.entrySet()) {
            if (entry.getValue().n_1700_B()) continue;
            list.add(entry.getKey());
        }
        return list;
    }

    public Iterable<String> u_1723_Y() {
        ArrayList list = Lists.newArrayList();
        for (Map.Entry<String, B_3068_A> entry : this.n_1700_B.entrySet()) {
            if (!entry.getValue().n_1700_B()) continue;
            list.add(entry.getKey());
        }
        return list;
    }

    @Nullable
    public Date v_4262_N() {
        Date date = null;
        for (B_3068_A criterionprogress : this.n_1700_B.values()) {
            if (!criterionprogress.n_1700_B() || date != null && !criterionprogress.G_564_y().before(date)) continue;
            date = criterionprogress.G_564_y();
        }
        return date;
    }

    public int n_1700_B(C_3304_p p_compareTo_1_) {
        Date date = this.v_4262_N();
        Date date1 = p_compareTo_1_.v_4262_N();
        if (date == null && date1 != null) {
            return 1;
        }
        if (date != null && date1 == null) {
            return -1;
        }
        return date == null && date1 == null ? 0 : date.compareTo(date1);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((C_3304_p)object);
    }

    public static class n_1700_B
    implements JsonDeserializer<C_3304_p>,
    JsonSerializer<C_3304_p> {
        public JsonElement n_1700_B(C_3304_p p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            JsonObject jsonobject1 = new JsonObject();
            for (Map.Entry<String, B_3068_A> entry : p_serialize_1_.n_1700_B.entrySet()) {
                B_3068_A criterionprogress = entry.getValue();
                if (!criterionprogress.n_1700_B()) continue;
                jsonobject1.add(entry.getKey(), criterionprogress.P_1922_E());
            }
            if (!jsonobject1.entrySet().isEmpty()) {
                jsonobject.add("criteria", (JsonElement)jsonobject1);
            }
            jsonobject.addProperty("done", Boolean.valueOf(p_serialize_1_.n_1700_B()));
            return jsonobject;
        }

        public C_3304_p n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "advancement");
            JsonObject jsonobject1 = i_4431_W.n_1700_B(jsonobject, "criteria", new JsonObject());
            C_3304_p advancementprogress = new C_3304_p();
            for (Map.Entry entry : jsonobject1.entrySet()) {
                String s = (String)entry.getKey();
                advancementprogress.n_1700_B.put(s, B_3068_A.n_1700_B(i_4431_W.n_1700_B((JsonElement)entry.getValue(), s)));
            }
            return advancementprogress;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((C_3304_p)object, type, jsonSerializationContext);
        }
    }
}

