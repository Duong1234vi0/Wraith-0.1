/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  lombok.Generated
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lombok.Generated;

public class z_3000_g
extends I_1790_n<List<n_1700_B>> {
    public z_3000_g() {
        super("temp\\blockesp.file");
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
            JsonObject o = new JsonObject();
            o.addProperty("block", entry.n_1700_B());
            o.addProperty("color", (Number)entry.J_1907_R());
            arr.add((JsonElement)o);
        }
        config.add("blockesp", (JsonElement)arr);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((List)this.P_1922_E).clear();
        JsonArray arr = jsonObject.getAsJsonArray("blockesp");
        if (arr != null) {
            for (JsonElement el : arr) {
                if (!el.isJsonObject()) continue;
                JsonObject o = el.getAsJsonObject();
                this.n_1700_B(o, "block", b -> this.n_1700_B(o, "color", c -> {
                    try {
                        ((List)this.P_1922_E).add(new n_1700_B(b.getAsString(), c.getAsInt()));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }));
            }
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    public List<n_1700_B> M_588_G() {
        return new ArrayList<n_1700_B>((Collection)this.P_1922_E);
    }

    public void n_1700_B(String block, int color) {
        if (block == null) {
            return;
        }
        String normalized = block.trim().toLowerCase();
        ((List)this.P_1922_E).removeIf(b -> b.n_1700_B().equalsIgnoreCase(normalized));
        ((List)this.P_1922_E).add(new n_1700_B(normalized, color));
        this.G_564_y();
    }

    public boolean n_1700_B(String block) {
        if (block == null) {
            return false;
        }
        boolean removed = ((List)this.P_1922_E).removeIf(b -> b.n_1700_B().equalsIgnoreCase(block.trim()));
        if (removed) {
            this.G_564_y();
        }
        return removed;
    }

    public void P_4830_p() {
        if (!((List)this.P_1922_E).isEmpty()) {
            ((List)this.P_1922_E).clear();
            this.G_564_y();
        }
    }

    private void n_1700_B(JsonObject object, String key, Consumer<JsonElement> consumer) {
        JsonElement element = object.get(key);
        if (element != null && !element.isJsonNull()) {
            consumer.accept(element);
        }
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final int J_1907_R;

        public n_1700_B(String block, int color) {
            this.n_1700_B = block;
            this.J_1907_R = color;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public int J_1907_R() {
            return this.J_1907_R;
        }
    }
}

