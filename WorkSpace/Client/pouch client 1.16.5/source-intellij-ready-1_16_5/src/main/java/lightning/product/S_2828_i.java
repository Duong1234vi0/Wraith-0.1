/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import lightning.product.I_1790_n;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;

public class S_2828_i
extends I_1790_n<List<String>> {
    public S_2828_i() {
        super("temp\\nuker.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new ArrayList();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        JsonArray arr = new JsonArray();
        for (String blockName : (List)this.P_1922_E) {
            arr.add(blockName);
        }
        config.add("nuker_blocks", (JsonElement)arr);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((List)this.P_1922_E).clear();
        JsonArray arr = jsonObject.getAsJsonArray("nuker_blocks");
        if (arr != null) {
            for (JsonElement el : arr) {
                if (!el.isJsonPrimitive()) continue;
                ((List)this.P_1922_E).add(el.getAsString());
            }
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    public List<T_2915_h> M_588_G() {
        ArrayList<T_2915_h> blocks = new ArrayList<T_2915_h>();
        for (String blockName : (List)this.P_1922_E) {
            try {
                g_2336_b location = new g_2336_b(blockName);
                T_2915_h block = V_3137_a.q_4610_l.J_1907_R(location).orElse(null);
                if (block == null) continue;
                blocks.add(block);
            }
            catch (Exception exception) {}
        }
        return blocks;
    }

    public boolean n_1700_B(g_2336_b resourceLocation) {
        if (resourceLocation == null) {
            return false;
        }
        String blockName = resourceLocation.toString();
        T_2915_h block = V_3137_a.q_4610_l.J_1907_R(resourceLocation).orElse(null);
        if (block == null || ((List)this.P_1922_E).contains(blockName)) {
            return false;
        }
        ((List)this.P_1922_E).add(blockName);
        this.G_564_y();
        return true;
    }

    public boolean J_1907_R(g_2336_b resourceLocation) {
        if (resourceLocation == null) {
            return false;
        }
        String blockName = resourceLocation.toString();
        boolean removed = ((List)this.P_1922_E).remove(blockName);
        if (removed) {
            this.G_564_y();
        }
        return removed;
    }

    public boolean n_1700_B(T_2915_h block) {
        if (block == null) {
            return false;
        }
        g_2336_b location = V_3137_a.q_4610_l.J_1907_R(block);
        return ((List)this.P_1922_E).contains(location.toString());
    }

    public void P_4830_p() {
        if (!((List)this.P_1922_E).isEmpty()) {
            ((List)this.P_1922_E).clear();
            this.G_564_y();
        }
    }

    public boolean h_1847_R() {
        return ((List)this.P_1922_E).isEmpty();
    }

    public int Q_4569_t() {
        return ((List)this.P_1922_E).size();
    }
}

