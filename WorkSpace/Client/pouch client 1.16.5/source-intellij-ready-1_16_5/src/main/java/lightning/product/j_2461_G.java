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
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1803_e;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class j_2461_G
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("shot_crossbow");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        return new n_1700_B(entityPredicate, itempredicate);
    }

    public void n_1700_B(B_4088_l shooter, Z_1993_T stack) {
        this.n_1700_B(shooter, (T instance) -> instance.n_1700_B(stack));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final w_4866_k n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, w_4866_k itemPredicate) {
            super(n_1700_B, player);
            this.n_1700_B = itemPredicate;
        }

        public static n_1700_B n_1700_B(q_1803_e itemProvider) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, w_4866_k.n_1700_B.n_1700_B().n_1700_B(itemProvider).J_1907_R());
        }

        public boolean n_1700_B(Z_1993_T stack) {
            return this.n_1700_B.n_1700_B(stack);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("item", this.n_1700_B.n_1700_B());
            return jsonobject;
        }
    }
}


