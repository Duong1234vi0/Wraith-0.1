/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.V_3137_a;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;

public class z_936_s
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("changed_dimension");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        f_2392_k<b_4507_u> registrykey = json.has("from") ? f_2392_k.n_1700_B(V_3137_a.z_1737_N, new g_2336_b(i_4431_W.u_1723_Y(json, "from"))) : null;
        f_2392_k<b_4507_u> registrykey1 = json.has("to") ? f_2392_k.n_1700_B(V_3137_a.z_1737_N, new g_2336_b(i_4431_W.u_1723_Y(json, "to"))) : null;
        return new n_1700_B(entityPredicate, registrykey, registrykey1);
    }

    public void n_1700_B(B_4088_l player, f_2392_k<b_4507_u> fromWorld, f_2392_k<b_4507_u> toWorld) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(fromWorld, toWorld));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        @Nullable
        private final f_2392_k<b_4507_u> n_1700_B;
        @Nullable
        private final f_2392_k<b_4507_u> J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B entityPredicate, @Nullable f_2392_k<b_4507_u> fromWorld, @Nullable f_2392_k<b_4507_u> toWorld) {
            super(n_1700_B, entityPredicate);
            this.n_1700_B = fromWorld;
            this.J_1907_R = toWorld;
        }

        public static n_1700_B n_1700_B(f_2392_k<b_4507_u> toWorld) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, null, toWorld);
        }

        public boolean n_1700_B(f_2392_k<b_4507_u> fromWorld, f_2392_k<b_4507_u> toWorld) {
            if (this.n_1700_B != null && this.n_1700_B != fromWorld) {
                return false;
            }
            return this.J_1907_R == null || this.J_1907_R == toWorld;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            if (this.n_1700_B != null) {
                jsonobject.addProperty("from", this.n_1700_B.n_1700_B().toString());
            }
            if (this.J_1907_R != null) {
                jsonobject.addProperty("to", this.J_1907_R.n_1700_B().toString());
            }
            return jsonobject;
        }
    }
}


