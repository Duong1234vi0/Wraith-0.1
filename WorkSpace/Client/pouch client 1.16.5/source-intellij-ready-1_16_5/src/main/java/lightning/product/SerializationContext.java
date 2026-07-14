/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lightning.product.LootItemCondition;
import lightning.product.Deserializers;

public class SerializationContext {
    public static final SerializationContext n_1700_B = new SerializationContext();
    private final Gson J_1907_R = Deserializers.n_1700_B().create();

    public final JsonElement n_1700_B(LootItemCondition[] p_235681_1_) {
        return this.J_1907_R.toJsonTree((Object)p_235681_1_);
    }
}


