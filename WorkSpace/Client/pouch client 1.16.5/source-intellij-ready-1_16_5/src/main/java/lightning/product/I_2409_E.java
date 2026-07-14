/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.HashSet;
import java.util.Map;
import lightning.product.T_335_n;
import lightning.product.b_164_E;
import lightning.product.b_236_t;
import lightning.product.i_4431_W;

public class I_2409_E
implements T_335_n<b_236_t> {
    public b_236_t n_1700_B(JsonObject json) {
        HashSet set = Sets.newHashSet();
        for (Map.Entry entry : json.entrySet()) {
            String s = (String)entry.getKey();
            if (s.length() > 16) {
                throw new JsonParseException("Invalid language->'" + s + "': language code must not be more than 16 characters long");
            }
            JsonObject jsonobject = i_4431_W.w_1484_f((JsonElement)entry.getValue(), "language");
            String s1 = i_4431_W.u_1723_Y(jsonobject, "region");
            String s2 = i_4431_W.u_1723_Y(jsonobject, "name");
            boolean flag = i_4431_W.n_1700_B(jsonobject, "bidirectional", false);
            if (s1.isEmpty()) {
                throw new JsonParseException("Invalid language->'" + s + "'->region: empty value");
            }
            if (s2.isEmpty()) {
                throw new JsonParseException("Invalid language->'" + s + "'->name: empty value");
            }
            if (set.add(new b_164_E(s, s1, s2, flag))) continue;
            throw new JsonParseException("Duplicate language->'" + s + "' defined");
        }
        return new b_236_t(set);
    }

    @Override
    public String n_1700_B() {
        return "language";
    }

    @Override
    public /* synthetic */ Object J_1907_R(JsonObject jsonObject) {
        return this.n_1700_B(jsonObject);
    }
}

