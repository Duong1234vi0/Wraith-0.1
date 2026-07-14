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

public class r_4414_L
extends I_1790_n<List<n_1700_B>> {
    public r_4414_L() {
        super("temp\\macros.file");
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
            JsonObject obj = new JsonObject();
            obj.addProperty("name", entry.n_1700_B());
            obj.addProperty("key", (Number)entry.J_1907_R());
            obj.addProperty("cmd", entry.R_4764_Y());
            arr.add((JsonElement)obj);
        }
        config.add("macros", (JsonElement)arr);
        return config;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((List)this.P_1922_E).clear();
        JsonArray arr = jsonObject.getAsJsonArray("macros");
        if (arr != null) {
            for (JsonElement el : arr) {
                if (!el.isJsonObject()) continue;
                JsonObject o = el.getAsJsonObject();
                this.n_1700_B(o, "name", (JsonElement n) -> this.n_1700_B(o, "key", (JsonElement k) -> this.n_1700_B(o, "cmd", (JsonElement c) -> {
                    try {
                        ((List)this.P_1922_E).add(new n_1700_B(n.getAsString(), k.getAsInt(), c.getAsString()));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                })));
            }
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    public void n_1700_B(String name, int keyCode, String command) {
        if (name == null || command == null) {
            return;
        }
        this.n_1700_B(name);
        ((List)this.P_1922_E).add(new n_1700_B(name.trim(), keyCode, command));
        this.G_564_y();
    }

    @Override
    public void n_1700_B(String name) {
        if (name == null) {
            return;
        }
        boolean removed = ((List)this.P_1922_E).removeIf(m -> m.n_1700_B().equalsIgnoreCase(name.trim()));
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
        private final int J_1907_R;
        private final String R_4764_Y;

        public n_1700_B(String name, int keyCode, String command) {
            this.n_1700_B = name;
            this.J_1907_R = keyCode;
            this.R_4764_Y = command;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public int J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public String R_4764_Y() {
            return this.R_4764_Y;
        }
    }
}

