/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Path;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.v_3760_Q;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;

public class R_4764_Y
implements Y_259_p {
    private static final Gson J_1907_R = new GsonBuilder().setPrettyPrinting().create();
    private final Q_4569_t R_4764_Y;

    public R_4764_Y(Q_4569_t generatorIn) {
        this.R_4764_Y = generatorIn;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        JsonObject jsonobject = new JsonObject();
        for (T_2915_h block : V_3137_a.q_4610_l) {
            g_2336_b resourcelocation = V_3137_a.q_4610_l.J_1907_R(block);
            JsonObject jsonobject1 = new JsonObject();
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
            if (!statecontainer.G_564_y().isEmpty()) {
                JsonObject jsonobject2 = new JsonObject();
                for (v_3760_Q v_3760_Q2 : statecontainer.G_564_y()) {
                    JsonArray jsonarray = new JsonArray();
                    for (Comparable comparable : v_3760_Q2.n_1700_B()) {
                        jsonarray.add(j_3341_s.n_1700_B(v_3760_Q2, comparable));
                    }
                    jsonobject2.add(v_3760_Q2.P_1922_E(), (JsonElement)jsonarray);
                }
                jsonobject1.add("properties", (JsonElement)jsonobject2);
            }
            JsonArray jsonarray1 = new JsonArray();
            for (K_4074_S k_4074_S : statecontainer.n_1700_B()) {
                JsonObject jsonobject3 = new JsonObject();
                JsonObject jsonobject4 = new JsonObject();
                for (v_3760_Q<?> property1 : statecontainer.G_564_y()) {
                    jsonobject4.addProperty(property1.P_1922_E(), j_3341_s.n_1700_B(property1, k_4074_S.R_4764_Y(property1)));
                }
                if (jsonobject4.size() > 0) {
                    jsonobject3.add("properties", (JsonElement)jsonobject4);
                }
                jsonobject3.addProperty("id", (Number)T_2915_h.s_956_w(k_4074_S));
                if (k_4074_S == block.multiplayerClientSuggestionProvider()) {
                    jsonobject3.addProperty("default", Boolean.valueOf(true));
                }
                jsonarray1.add((JsonElement)jsonobject3);
            }
            jsonobject1.add("states", (JsonElement)jsonarray1);
            jsonobject.add(resourcelocation.toString(), (JsonElement)jsonobject1);
        }
        Path path = this.R_4764_Y.J_1907_R().resolve("reports/blocks.json");
        Y_259_p.n_1700_B(J_1907_R, cache, (JsonElement)jsonobject, path);
    }

    @Override
    public String n_1700_B() {
        return "Block List";
    }
}


