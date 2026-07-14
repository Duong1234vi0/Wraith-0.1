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
import lightning.product.g_4621_i;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class z_1100_b
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("villager_trade");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "villager", conditionsParser);
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        return new n_1700_B(entityPredicate, entitypredicate$andpredicate, itempredicate);
    }

    public void n_1700_B(B_4088_l player, g_4621_i villager, Z_1993_T stack) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, villager);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(lootcontext, stack));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B n_1700_B;
        private final w_4866_k J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, b_1430_k.n_1700_B villager, w_4866_k stack) {
            super(n_1700_B, player);
            this.n_1700_B = villager;
            this.J_1907_R = stack;
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, w_4866_k.n_1700_B);
        }

        public boolean n_1700_B(q_1704_m context, Z_1993_T stack) {
            if (!this.n_1700_B.n_1700_B(context)) {
                return false;
            }
            return this.J_1907_R.n_1700_B(stack);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("item", this.J_1907_R.n_1700_B());
            jsonobject.add("villager", this.n_1700_B.n_1700_B(conditions));
            return jsonobject;
        }
    }
}


