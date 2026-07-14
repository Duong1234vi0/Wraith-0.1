/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lombok.Generated;

public class R_2822_N
extends I_1790_n<List<n_1700_B>> {
    public R_2822_N() {
        super("temp\\staff.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new ArrayList();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject config = new JsonObject();
        JsonArray arr = new JsonArray();
        for (n_1700_B entry : (List)this.P_1922_E) {
            arr.add((JsonElement)new JsonPrimitive(entry.n_1700_B()));
        }
        config.add("staff", (JsonElement)arr);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((List)this.P_1922_E).clear();
        JsonArray arr = jsonObject.getAsJsonArray("staff");
        if (arr != null) {
            for (JsonElement element : arr) {
                if (element.isJsonPrimitive()) {
                    ((List)this.P_1922_E).add(new n_1700_B(element.getAsString()));
                    continue;
                }
                if (!element.isJsonObject()) continue;
                JsonObject obj = element.getAsJsonObject();
                this.n_1700_B(obj, "name", nameEl -> ((List)this.P_1922_E).add(new n_1700_B(nameEl.getAsString())));
            }
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    @Override
    public void n_1700_B(String name) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        String n = name.trim();
        if (!this.R_4764_Y(n)) {
            ((List)this.P_1922_E).add(new n_1700_B(n));
            this.G_564_y();
        }
    }

    public void J_1907_R(String name) {
        if (name == null) {
            return;
        }
        boolean removed = ((List)this.P_1922_E).removeIf(e -> e.n_1700_B().equalsIgnoreCase(name.trim()));
        if (removed) {
            this.G_564_y();
        }
    }

    public void M_588_G() {
        if (!((List)this.P_1922_E).isEmpty()) {
            ((List)this.P_1922_E).clear();
            this.G_564_y();
        }
    }

    public boolean R_4764_Y(String name) {
        if (name == null) {
            return false;
        }
        return ((List)this.P_1922_E).stream().anyMatch(e -> e.n_1700_B().equalsIgnoreCase(name.trim()));
    }

    public List<n_1700_B> P_4830_p() {
        return new ArrayList<n_1700_B>((Collection)this.P_1922_E);
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;

        public n_1700_B(String name) {
            this.n_1700_B = name;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }
    }
}

