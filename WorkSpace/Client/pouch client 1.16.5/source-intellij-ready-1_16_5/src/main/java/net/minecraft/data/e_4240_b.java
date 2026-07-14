/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Path;
import lightning.product.DefaultedRegistry;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import net.minecraft.data.M_182_A;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.Y_259_p;

public class e_4240_b
implements Y_259_p {
    private static final Gson J_1907_R = new GsonBuilder().setPrettyPrinting().create();
    private final Q_4569_t R_4764_Y;

    public e_4240_b(Q_4569_t generator) {
        this.R_4764_Y = generator;
    }

    @Override
    public void n_1700_B(M_182_A cache) throws IOException {
        JsonObject jsonobject = new JsonObject();
        V_3137_a.G_564_y.G_564_y().forEach(registryId -> jsonobject.add(registryId.toString(), e_4240_b.n_1700_B(V_3137_a.G_564_y.n_1700_B((g_2336_b)registryId))));
        Path path = this.R_4764_Y.J_1907_R().resolve("reports/registries.json");
        Y_259_p.n_1700_B(J_1907_R, cache, (JsonElement)jsonobject, path);
    }

    private static <T> JsonElement n_1700_B(V_3137_a<T> registry) {
        JsonObject jsonobject = new JsonObject();
        if (registry instanceof DefaultedRegistry) {
            g_2336_b resourcelocation = ((DefaultedRegistry)registry).n_1700_B();
            jsonobject.addProperty("default", resourcelocation.toString());
        }
        int j = V_3137_a.G_564_y.n_1700_B(registry);
        jsonobject.addProperty("protocol_id", (Number)j);
        JsonObject jsonobject1 = new JsonObject();
        for (g_2336_b resourcelocation1 : registry.G_564_y()) {
            T t = registry.n_1700_B(resourcelocation1);
            int i = registry.n_1700_B(t);
            JsonObject jsonobject2 = new JsonObject();
            jsonobject2.addProperty("protocol_id", (Number)i);
            jsonobject1.add(resourcelocation1.toString(), (JsonElement)jsonobject2);
        }
        jsonobject.add("entries", (JsonElement)jsonobject1);
        return jsonobject;
    }

    @Override
    public String n_1700_B() {
        return "Registry Dump";
    }
}


