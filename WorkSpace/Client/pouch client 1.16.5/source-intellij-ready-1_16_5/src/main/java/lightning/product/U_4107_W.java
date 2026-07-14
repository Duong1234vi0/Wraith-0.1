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
import lightning.product.P_11_z;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.r_1970_q;
import lightning.product.DeserializationContext;

public class U_4107_W
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("entity_hurt_player");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        r_1970_q damagepredicate = r_1970_q.n_1700_B(json.get("damage"));
        return new n_1700_B(entityPredicate, damagepredicate);
    }

    public void n_1700_B(B_4088_l player, P_11_z source, float amountDealt, float amountTaken, boolean wasBlocked) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player, source, amountDealt, amountTaken, wasBlocked));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final r_1970_q n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, r_1970_q damageCondition) {
            super(n_1700_B, player);
            this.n_1700_B = damageCondition;
        }

        public static n_1700_B n_1700_B(r_1970_q.n_1700_B damageConditionBuilder) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, damageConditionBuilder.J_1907_R());
        }

        public boolean n_1700_B(B_4088_l player, P_11_z source, float amountDealt, float amountTaken, boolean wasBlocked) {
            return this.n_1700_B.n_1700_B(player, source, amountDealt, amountTaken, wasBlocked);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("damage", this.n_1700_B.n_1700_B());
            return jsonobject;
        }
    }
}


