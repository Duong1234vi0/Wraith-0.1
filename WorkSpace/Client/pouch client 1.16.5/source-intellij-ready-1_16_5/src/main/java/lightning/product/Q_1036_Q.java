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
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class Q_1036_Q
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("thrown_item_picked_up_by_entity");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    protected n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "entity", conditionsParser);
        return new n_1700_B(entityPredicate, itempredicate, entitypredicate$andpredicate);
    }

    public void n_1700_B(B_4088_l player, Z_1993_T stack, N_4263_v entity) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, entity);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player, stack, lootcontext));
    }

    @Override
    protected /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final w_4866_k n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, w_4866_k stack, b_1430_k.n_1700_B entity) {
            super(n_1700_B, player);
            this.n_1700_B = stack;
            this.J_1907_R = entity;
        }

        public static n_1700_B n_1700_B(b_1430_k.n_1700_B player, w_4866_k.n_1700_B stack, b_1430_k.n_1700_B entity) {
            return new n_1700_B(player, stack.J_1907_R(), entity);
        }

        public boolean n_1700_B(B_4088_l player, Z_1993_T stack, q_1704_m context) {
            if (!this.n_1700_B.n_1700_B(stack)) {
                return false;
            }
            return this.J_1907_R.n_1700_B(context);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("item", this.n_1700_B.n_1700_B());
            jsonobject.add("entity", this.J_1907_R.n_1700_B(conditions));
            return jsonobject;
        }
    }
}


