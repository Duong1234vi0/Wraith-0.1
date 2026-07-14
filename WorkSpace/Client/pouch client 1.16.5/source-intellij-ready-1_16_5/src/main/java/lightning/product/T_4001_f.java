/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.SerializationContext;
import lightning.product.CriterionTrigger;
import lightning.product.U_3554_Q;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;

public class T_4001_f {
    private final h_1723_G n_1700_B;

    public T_4001_f(h_1723_G criterionInstance) {
        this.n_1700_B = criterionInstance;
    }

    public T_4001_f() {
        this.n_1700_B = null;
    }

    public void n_1700_B(b_2585_i buffer) {
    }

    public static T_4001_f n_1700_B(JsonObject json, DeserializationContext conditionParser) {
        g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "trigger"));
        CriterionTrigger icriteriontrigger = U_3554_Q.n_1700_B(resourcelocation);
        if (icriteriontrigger == null) {
            throw new JsonSyntaxException("Invalid criterion trigger: " + String.valueOf(resourcelocation));
        }
        Object icriterioninstance = icriteriontrigger.n_1700_B(i_4431_W.n_1700_B(json, "conditions", new JsonObject()), conditionParser);
        return new T_4001_f((h_1723_G)icriterioninstance);
    }

    public static T_4001_f J_1907_R(b_2585_i buffer) {
        return new T_4001_f();
    }

    public static Map<String, T_4001_f> J_1907_R(JsonObject json, DeserializationContext conditionParser) {
        HashMap map = Maps.newHashMap();
        for (Map.Entry entry : json.entrySet()) {
            map.put((String)entry.getKey(), T_4001_f.n_1700_B(i_4431_W.w_1484_f((JsonElement)entry.getValue(), "criterion"), conditionParser));
        }
        return map;
    }

    public static Map<String, T_4001_f> R_4764_Y(b_2585_i bus) {
        HashMap map = Maps.newHashMap();
        int i = bus.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            map.put(bus.P_1922_E(Short.MAX_VALUE), T_4001_f.J_1907_R(bus));
        }
        return map;
    }

    public static void n_1700_B(Map<String, T_4001_f> criteria, b_2585_i buf) {
        buf.G_564_y(criteria.size());
        for (Map.Entry<String, T_4001_f> entry : criteria.entrySet()) {
            buf.n_1700_B(entry.getKey());
            entry.getValue().n_1700_B(buf);
        }
    }

    @Nullable
    public h_1723_G n_1700_B() {
        return this.n_1700_B;
    }

    public JsonElement J_1907_R() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("trigger", this.n_1700_B.n_1700_B().toString());
        JsonObject jsonobject1 = this.n_1700_B.n_1700_B(SerializationContext.n_1700_B);
        if (jsonobject1.size() != 0) {
            jsonobject.add("conditions", (JsonElement)jsonobject1);
        }
        return jsonobject;
    }
}


