/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.V_3137_a;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;
import lightning.product.y_528_b;

public class n_4560_z
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("brewed_potion");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        y_528_b potion = null;
        if (json.has("potion")) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "potion"));
            potion = V_3137_a.B_1668_F.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown potion '" + String.valueOf(resourcelocation) + "'"));
        }
        return new n_1700_B(entityPredicate, potion);
    }

    public void n_1700_B(B_4088_l player, y_528_b potionIn) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(potionIn));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final y_528_b n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, @Nullable y_528_b potion) {
            super(n_1700_B, player);
            this.n_1700_B = potion;
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, (y_528_b)null);
        }

        public boolean n_1700_B(y_528_b potion) {
            return this.n_1700_B == null || this.n_1700_B == potion;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            if (this.n_1700_B != null) {
                jsonobject.addProperty("potion", V_3137_a.B_1668_F.J_1907_R(this.n_1700_B).toString());
            }
            return jsonobject;
        }
    }
}


