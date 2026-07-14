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
import lightning.product.N_4263_v;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;

public class S_3844_E
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("target_hit");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("signal_strength"));
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "projectile", conditionsParser);
        return new n_1700_B(entityPredicate, minmaxbounds$intbound, entitypredicate$andpredicate);
    }

    public void n_1700_B(B_4088_l player, N_4263_v projectile, e_2866_D vector, int signalStrength) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, projectile);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(lootcontext, vector, signalStrength));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final MinMaxBounds.G_564_y n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, MinMaxBounds.G_564_y signalStrength, b_1430_k.n_1700_B projectile) {
            super(n_1700_B, player);
            this.n_1700_B = signalStrength;
            this.J_1907_R = projectile;
        }

        public static n_1700_B n_1700_B(MinMaxBounds.G_564_y signalStrength, b_1430_k.n_1700_B projectile) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, signalStrength, projectile);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("signal_strength", this.n_1700_B.G_564_y());
            jsonobject.add("projectile", this.J_1907_R.n_1700_B(conditions));
            return jsonobject;
        }

        public boolean n_1700_B(q_1704_m context, e_2866_D vector, int signalStrength) {
            if (!this.n_1700_B.R_4764_Y(signalStrength)) {
                return false;
            }
            return this.J_1907_R.n_1700_B(context);
        }
    }
}


