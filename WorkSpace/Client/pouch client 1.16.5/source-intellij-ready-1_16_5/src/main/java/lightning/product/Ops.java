/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Set;
import lightning.product.ValueObject;

public class Ops
extends ValueObject {
    public Set<String> n_1700_B = Sets.newHashSet();

    public static Ops n_1700_B(String p_230754_0_) {
        Ops ops = new Ops();
        JsonParser jsonparser = new JsonParser();
        try {
            JsonElement jsonelement = jsonparser.parse(p_230754_0_);
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            JsonElement jsonelement1 = jsonobject.get("ops");
            if (jsonelement1.isJsonArray()) {
                for (JsonElement jsonelement2 : jsonelement1.getAsJsonArray()) {
                    ops.n_1700_B.add(jsonelement2.getAsString());
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return ops;
    }
}


