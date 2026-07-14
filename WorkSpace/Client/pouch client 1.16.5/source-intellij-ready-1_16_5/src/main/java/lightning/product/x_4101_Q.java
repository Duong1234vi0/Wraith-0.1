/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Streams
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;

public class x_4101_Q {
    @Nullable
    private final List<g_2336_b> n_1700_B;

    private x_4101_Q(@Nullable List<g_2336_b> textures) {
        this.n_1700_B = textures;
    }

    @Nullable
    public List<g_2336_b> n_1700_B() {
        return this.n_1700_B;
    }

    public static x_4101_Q n_1700_B(JsonObject json) {
        JsonArray jsonarray = i_4431_W.n_1700_B(json, "textures", (JsonArray)null);
        List list = jsonarray != null ? (List)Streams.stream((Iterable)jsonarray).map(element -> i_4431_W.n_1700_B(element, "texture")).map(g_2336_b::new).collect(ImmutableList.toImmutableList()) : null;
        return new x_4101_Q(list);
    }
}

