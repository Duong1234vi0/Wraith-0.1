/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import lightning.product.SerializationTags;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.r_109_r;
import lightning.product.t_5_h;

public abstract class n_3115_n {
    public static final n_3115_n n_1700_B = new n_3115_n(){

        @Override
        public boolean n_1700_B(t_5_h<?> type) {
            return true;
        }

        @Override
        public JsonElement n_1700_B() {
            return JsonNull.INSTANCE;
        }
    };
    private static final Joiner J_1907_R = Joiner.on((String)", ");

    public abstract boolean n_1700_B(t_5_h<?> var1);

    public abstract JsonElement n_1700_B();

    public static n_3115_n n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            String s = i_4431_W.n_1700_B(element, "type");
            if (s.startsWith("#")) {
                g_2336_b resourcelocation1 = new g_2336_b(s.substring(1));
                return new n_1700_B(SerializationTags.n_1700_B().G_564_y().J_1907_R(resourcelocation1));
            }
            g_2336_b resourcelocation = new g_2336_b(s);
            t_5_h<?> entitytype = V_3137_a.g_221_o.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown entity type '" + String.valueOf(resourcelocation) + "', valid types are: " + J_1907_R.join(V_3137_a.g_221_o.G_564_y())));
            return new J_1907_R(entitytype);
        }
        return n_1700_B;
    }

    public static n_3115_n J_1907_R(t_5_h<?> type) {
        return new J_1907_R(type);
    }

    public static n_3115_n n_1700_B(r_109_r<t_5_h<?>> tag) {
        return new n_1700_B(tag);
    }

    static class n_1700_B
    extends n_3115_n {
        private final r_109_r<t_5_h<?>> J_1907_R;

        public n_1700_B(r_109_r<t_5_h<?>> tag) {
            this.J_1907_R = tag;
        }

        @Override
        public boolean n_1700_B(t_5_h<?> type) {
            return this.J_1907_R.n_1700_B(type);
        }

        @Override
        public JsonElement n_1700_B() {
            return new JsonPrimitive("#" + String.valueOf(SerializationTags.n_1700_B().G_564_y().J_1907_R(this.J_1907_R)));
        }
    }

    static class J_1907_R
    extends n_3115_n {
        private final t_5_h<?> J_1907_R;

        public J_1907_R(t_5_h<?> type) {
            this.J_1907_R = type;
        }

        @Override
        public boolean n_1700_B(t_5_h<?> type) {
            return this.J_1907_R == type;
        }

        @Override
        public JsonElement n_1700_B() {
            return new JsonPrimitive(V_3137_a.g_221_o.J_1907_R(this.J_1907_R).toString());
        }
    }
}


