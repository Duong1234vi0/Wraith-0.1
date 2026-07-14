/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.B_4088_l;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;

public class V_1824_v
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("used_ender_eye");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        MinMaxBounds.n_1700_B minmaxbounds$floatbound = MinMaxBounds.n_1700_B.n_1700_B(json.get("distance"));
        return new n_1700_B(entityPredicate, minmaxbounds$floatbound);
    }

    public void n_1700_B(B_4088_l player, c_1514_x pos) {
        double d0 = player.O_3598_v() - (double)pos.getX();
        double d1 = player.l_2647_k() - (double)pos.getZ();
        double d2 = d0 * d0 + d1 * d1;
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(d2));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final MinMaxBounds.n_1700_B n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, MinMaxBounds.n_1700_B distance) {
            super(n_1700_B, player);
            this.n_1700_B = distance;
        }

        public boolean n_1700_B(double distanceSq) {
            return this.n_1700_B.n_1700_B(distanceSq);
        }
    }
}


