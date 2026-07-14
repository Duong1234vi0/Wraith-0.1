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
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.o_3456_E;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;

public class K_4518_s
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("levitation");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        o_3456_E distancepredicate = o_3456_E.n_1700_B(json.get("distance"));
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("duration"));
        return new n_1700_B(entityPredicate, distancepredicate, minmaxbounds$intbound);
    }

    public void n_1700_B(B_4088_l player, e_2866_D startPos, int duration) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player, startPos, duration));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final o_3456_E n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, o_3456_E distance, MinMaxBounds.G_564_y duration) {
            super(n_1700_B, player);
            this.n_1700_B = distance;
            this.J_1907_R = duration;
        }

        public static n_1700_B n_1700_B(o_3456_E distance) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, distance, MinMaxBounds.G_564_y.P_1922_E);
        }

        public boolean n_1700_B(B_4088_l player, e_2866_D startPos, int durationIn) {
            if (!this.n_1700_B.n_1700_B(startPos.J_1907_R, startPos.R_4764_Y, startPos.G_564_y, player.O_3598_v(), player.X_2960_b(), player.l_2647_k())) {
                return false;
            }
            return this.J_1907_R.R_4764_Y(durationIn);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("distance", this.n_1700_B.n_1700_B());
            jsonobject.add("duration", this.J_1907_R.G_564_y());
            return jsonobject;
        }
    }
}


