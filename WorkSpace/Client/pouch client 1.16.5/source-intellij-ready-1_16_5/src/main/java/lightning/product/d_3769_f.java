/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.AgableMob;
import lightning.product.SerializationContext;
import lightning.product.Animal;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;

public class d_3769_f
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("bred_animals");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "parent", conditionsParser);
        b_1430_k.n_1700_B entitypredicate$andpredicate1 = b_1430_k.n_1700_B.n_1700_B(json, "partner", conditionsParser);
        b_1430_k.n_1700_B entitypredicate$andpredicate2 = b_1430_k.n_1700_B.n_1700_B(json, "child", conditionsParser);
        return new n_1700_B(entityPredicate, entitypredicate$andpredicate, entitypredicate$andpredicate1, entitypredicate$andpredicate2);
    }

    public void n_1700_B(B_4088_l player, Animal parent1, Animal parent2, @Nullable AgableMob child) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, parent1);
        q_1704_m lootcontext1 = b_1430_k.J_1907_R(player, parent2);
        q_1704_m lootcontext2 = child != null ? b_1430_k.J_1907_R(player, child) : null;
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(lootcontext, lootcontext1, lootcontext2));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;
        private final b_1430_k.n_1700_B R_4764_Y;

        public n_1700_B(b_1430_k.n_1700_B player, b_1430_k.n_1700_B parent, b_1430_k.n_1700_B partner, b_1430_k.n_1700_B child) {
            super(n_1700_B, player);
            this.n_1700_B = parent;
            this.J_1907_R = partner;
            this.R_4764_Y = child;
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B);
        }

        public static n_1700_B n_1700_B(b_1430_k.J_1907_R builder) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B(builder.J_1907_R()));
        }

        public static n_1700_B n_1700_B(b_1430_k parent, b_1430_k partner, b_1430_k child) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B(parent), b_1430_k.n_1700_B.n_1700_B(partner), b_1430_k.n_1700_B.n_1700_B(child));
        }

        public boolean n_1700_B(q_1704_m parentContext, q_1704_m partnerContext, @Nullable q_1704_m childContext) {
            if (this.R_4764_Y == b_1430_k.n_1700_B.n_1700_B || childContext != null && this.R_4764_Y.n_1700_B(childContext)) {
                return this.n_1700_B.n_1700_B(parentContext) && this.J_1907_R.n_1700_B(partnerContext) || this.n_1700_B.n_1700_B(partnerContext) && this.J_1907_R.n_1700_B(parentContext);
            }
            return false;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("parent", this.n_1700_B.n_1700_B(conditions));
            jsonobject.add("partner", this.J_1907_R.n_1700_B(conditions));
            jsonobject.add("child", this.R_4764_Y.n_1700_B(conditions));
            return jsonobject;
        }
    }
}


