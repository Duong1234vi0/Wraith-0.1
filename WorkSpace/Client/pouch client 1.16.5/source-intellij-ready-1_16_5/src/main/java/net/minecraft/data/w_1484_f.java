/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.function.Supplier;
import lightning.product.g_2336_b;

public class w_1484_f
implements Supplier<JsonElement> {
    private final g_2336_b n_1700_B;

    public w_1484_f(g_2336_b p_i232545_1_) {
        this.n_1700_B = p_i232545_1_;
    }

    public JsonElement n_1700_B() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("parent", this.n_1700_B.toString());
        return jsonobject;
    }

    @Override
    public /* synthetic */ Object get() {
        return this.n_1700_B();
    }
}

