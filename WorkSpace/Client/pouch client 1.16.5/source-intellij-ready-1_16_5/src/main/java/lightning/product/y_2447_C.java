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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import lightning.product.I_1790_n;
import lombok.Generated;

public class y_2447_C
extends I_1790_n<Map<String, List<n_1700_B>>> {
    public y_2447_C() {
        super("temp\\waypoints.file");
    }

    @Override
    protected void t_148_a() {
        this.P_1922_E = new HashMap();
    }

    @Override
    protected JsonObject s_956_w() {
        JsonObject root = new JsonObject();
        for (Map.Entry byServer : ((Map)this.P_1922_E).entrySet()) {
            String server = (String)byServer.getKey();
            JsonArray arr = new JsonArray();
            for (n_1700_B wp : (List)byServer.getValue()) {
                JsonObject o = new JsonObject();
                o.addProperty("name", wp.n_1700_B());
                o.addProperty("x", (Number)wp.J_1907_R());
                o.addProperty("y", (Number)wp.R_4764_Y());
                o.addProperty("z", (Number)wp.G_564_y());
                arr.add((JsonElement)o);
            }
            root.add(server, (JsonElement)arr);
        }
        return root;
    }

    @Override
    protected void n_1700_B(JsonObject jsonObject) {
        ((Map)this.P_1922_E).clear();
        for (Map.Entry entry : jsonObject.entrySet()) {
            String server = (String)entry.getKey();
            JsonElement element = (JsonElement)entry.getValue();
            if (!element.isJsonArray()) continue;
            JsonArray arr = element.getAsJsonArray();
            ArrayList list = new ArrayList();
            for (JsonElement el : arr) {
                if (!el.isJsonObject()) continue;
                JsonObject o = el.getAsJsonObject();
                this.n_1700_B(o, "name", (JsonElement n) -> this.n_1700_B(o, "x", (JsonElement x) -> this.n_1700_B(o, "y", (JsonElement y) -> this.n_1700_B(o, "z", (JsonElement z) -> {
                    try {
                        list.add(new n_1700_B(n.getAsString(), x.getAsDouble(), y.getAsDouble(), z.getAsDouble()));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }))));
            }
            ((Map)this.P_1922_E).put(server, list);
        }
    }

    @Override
    protected void n_1700_B(Exception exception) {
        if (this.P_1922_E == null) {
            this.t_148_a();
        }
    }

    public List<n_1700_B> n_1700_B(String serverKey) {
        return new ArrayList<n_1700_B>(((Map)this.P_1922_E).getOrDefault(serverKey, Collections.emptyList()));
    }

    public void n_1700_B(String serverKey, String name, double x, double y, double z) {
        if (serverKey == null || name == null) {
            return;
        }
        String normalized = name.trim();
        if (normalized.isEmpty()) {
            return;
        }
        List list = ((Map)this.P_1922_E).computeIfAbsent(serverKey, k -> new ArrayList());
        list.removeIf(w -> w.n_1700_B().equalsIgnoreCase(normalized));
        list.add(new n_1700_B(normalized, x, y, z));
        this.G_564_y();
    }

    public boolean n_1700_B(String serverKey, String name) {
        if (serverKey == null || name == null) {
            return false;
        }
        List list = (List)((Map)this.P_1922_E).get(serverKey);
        if (list == null) {
            return false;
        }
        boolean removed = list.removeIf(w -> w.n_1700_B().equalsIgnoreCase(name.trim()));
        if (removed) {
            this.G_564_y();
        }
        return removed;
    }

    public void J_1907_R(String serverKey) {
        if (serverKey == null) {
            return;
        }
        List list = (List)((Map)this.P_1922_E).get(serverKey);
        if (list != null && !list.isEmpty()) {
            list.clear();
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
        private final double J_1907_R;
        private final double R_4764_Y;
        private final double G_564_y;

        public n_1700_B(String name, double x, double y, double z) {
            this.n_1700_B = name;
            this.J_1907_R = x;
            this.R_4764_Y = y;
            this.G_564_y = z;
        }

        @Generated
        public String n_1700_B() {
            return this.n_1700_B;
        }

        @Generated
        public double J_1907_R() {
            return this.J_1907_R;
        }

        @Generated
        public double R_4764_Y() {
            return this.R_4764_Y;
        }

        @Generated
        public double G_564_y() {
            return this.G_564_y;
        }
    }
}

