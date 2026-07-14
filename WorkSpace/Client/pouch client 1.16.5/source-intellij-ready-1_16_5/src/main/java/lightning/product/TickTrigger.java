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
import lightning.product.g_2336_b;
import lightning.product.DeserializationContext;

public class TickTrigger
extends SimpleCriterionTrigger<n_1700_B> {
    public static final g_2336_b n_1700_B = new g_2336_b("tick");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        return new n_1700_B(entityPredicate);
    }

    public void n_1700_B(B_4088_l player) {
        this.n_1700_B(player, (T instance) -> true);
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        public n_1700_B(b_1430_k.n_1700_B player) {
            super(n_1700_B, player);
        }
    }
}


