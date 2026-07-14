/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.B_4088_l;
import lightning.product.F_4355_q;
import lightning.product.SerializationContext;
import lightning.product.L_2225_p;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;

public class K_4866_h
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("cured_zombie_villager");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "zombie", conditionsParser);
        b_1430_k.n_1700_B entitypredicate$andpredicate1 = b_1430_k.n_1700_B.n_1700_B(json, "villager", conditionsParser);
        return new n_1700_B(entityPredicate, entitypredicate$andpredicate, entitypredicate$andpredicate1);
    }

    public void n_1700_B(B_4088_l player, F_4355_q zombie, L_2225_p villager) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, zombie);
        q_1704_m lootcontext1 = b_1430_k.J_1907_R(player, villager);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(lootcontext, lootcontext1));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, b_1430_k.n_1700_B zombie, b_1430_k.n_1700_B villager) {
            super(n_1700_B, player);
            this.n_1700_B = zombie;
            this.J_1907_R = villager;
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B);
        }

        public boolean n_1700_B(q_1704_m zombie, q_1704_m villager) {
            if (!this.n_1700_B.n_1700_B(zombie)) {
                return false;
            }
            return this.J_1907_R.n_1700_B(villager);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("zombie", this.n_1700_B.n_1700_B(conditions));
            jsonobject.add("villager", this.J_1907_R.n_1700_B(conditions));
            return jsonobject;
        }
    }
}


