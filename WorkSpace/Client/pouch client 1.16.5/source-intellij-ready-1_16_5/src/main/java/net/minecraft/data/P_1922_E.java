/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.data.G_564_y;

public class P_1922_E
implements Supplier<JsonElement> {
    private final Map<G_564_y<?>, G_564_y.n_1700_B> n_1700_B = Maps.newLinkedHashMap();

    public <T> P_1922_E n_1700_B(G_564_y<T> info, T value) {
        G_564_y.n_1700_B blockmodeinfo = this.n_1700_B.put(info, info.n_1700_B(value));
        if (blockmodeinfo != null) {
            throw new IllegalStateException("Replacing value of " + String.valueOf(blockmodeinfo) + " with " + String.valueOf(value));
        }
        return this;
    }

    public static P_1922_E n_1700_B() {
        return new P_1922_E();
    }

    public static P_1922_E n_1700_B(P_1922_E definition1, P_1922_E definition2) {
        P_1922_E blockmodeldefinition = new P_1922_E();
        blockmodeldefinition.n_1700_B.putAll(definition1.n_1700_B);
        blockmodeldefinition.n_1700_B.putAll(definition2.n_1700_B);
        return blockmodeldefinition;
    }

    public JsonElement J_1907_R() {
        JsonObject jsonobject = new JsonObject();
        this.n_1700_B.values().forEach(field -> field.n_1700_B(jsonobject));
        return jsonobject;
    }

    public static JsonElement n_1700_B(List<P_1922_E> definitions) {
        if (definitions.size() == 1) {
            return definitions.get(0).J_1907_R();
        }
        JsonArray jsonarray = new JsonArray();
        definitions.forEach(definition -> jsonarray.add(definition.J_1907_R()));
        return jsonarray;
    }

    @Override
    public /* synthetic */ Object get() {
        return this.J_1907_R();
    }
}

