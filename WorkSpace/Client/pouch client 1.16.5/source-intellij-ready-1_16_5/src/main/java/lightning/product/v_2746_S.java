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
import lightning.product.P_11_z;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.r_1970_q;
import lightning.product.DeserializationContext;

public class v_2746_S
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("player_hurt_entity");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        r_1970_q damagepredicate = r_1970_q.n_1700_B(json.get("damage"));
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "entity", conditionsParser);
        return new n_1700_B(entityPredicate, damagepredicate, entitypredicate$andpredicate);
    }

    public void n_1700_B(B_4088_l player, N_4263_v entityIn, P_11_z source, float amountDealt, float amountTaken, boolean blocked) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, entityIn);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player, lootcontext, source, amountDealt, amountTaken, blocked));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final r_1970_q n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, r_1970_q damage, b_1430_k.n_1700_B entity) {
            super(n_1700_B, player);
            this.n_1700_B = damage;
            this.J_1907_R = entity;
        }

        public static n_1700_B n_1700_B(r_1970_q.n_1700_B builder) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, builder.J_1907_R(), b_1430_k.n_1700_B.n_1700_B);
        }

        public boolean n_1700_B(B_4088_l player, q_1704_m context, P_11_z damage, float dealt, float taken, boolean blocked) {
            if (!this.n_1700_B.n_1700_B(player, damage, dealt, taken, blocked)) {
                return false;
            }
            return this.J_1907_R.n_1700_B(context);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("damage", this.n_1700_B.n_1700_B());
            jsonobject.add("entity", this.J_1907_R.n_1700_B(conditions));
            return jsonobject;
        }
    }
}


