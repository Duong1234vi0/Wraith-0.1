/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;
import lightning.product.x_3974_Q;

public class q_608_V
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("construct_beacon");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("level"));
        return new n_1700_B(entityPredicate, minmaxbounds$intbound);
    }

    public void n_1700_B(B_4088_l player, x_3974_Q beacon) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(beacon));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final MinMaxBounds.G_564_y n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, MinMaxBounds.G_564_y level) {
            super(n_1700_B, player);
            this.n_1700_B = level;
        }

        public static n_1700_B n_1700_B(MinMaxBounds.G_564_y level) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, level);
        }

        public boolean n_1700_B(x_3974_Q beacon) {
            return this.n_1700_B.R_4764_Y(beacon.w_1484_f());
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("level", this.n_1700_B.G_564_y());
            return jsonobject;
        }
    }
}


