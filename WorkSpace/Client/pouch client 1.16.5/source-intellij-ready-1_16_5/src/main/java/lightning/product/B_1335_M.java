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
import lightning.product.DamageSourcePredicate;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.P_1965_C;
import lightning.product.U_3554_Q;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;

public class B_1335_M
extends SimpleCriterionTrigger<n_1700_B> {
    private final g_2336_b n_1700_B;

    public B_1335_M(g_2336_b id) {
        this.n_1700_B = id;
    }

    @Override
    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        return new n_1700_B(this.n_1700_B, entityPredicate, b_1430_k.n_1700_B.n_1700_B(json, "entity", conditionsParser), DamageSourcePredicate.n_1700_B(json.get("killing_blow")));
    }

    public void n_1700_B(B_4088_l player, N_4263_v entity, P_11_z source) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, entity);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player, lootcontext, source));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B n_1700_B;
        private final DamageSourcePredicate J_1907_R;

        public n_1700_B(g_2336_b criterion, b_1430_k.n_1700_B player, b_1430_k.n_1700_B entity, DamageSourcePredicate killingBlow) {
            super(criterion, player);
            this.n_1700_B = entity;
            this.J_1907_R = killingBlow;
        }

        public static n_1700_B n_1700_B(b_1430_k.J_1907_R builder) {
            return new n_1700_B(U_3554_Q.J_1907_R.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B(builder.J_1907_R()), DamageSourcePredicate.n_1700_B);
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(U_3554_Q.J_1907_R.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, DamageSourcePredicate.n_1700_B);
        }

        public static n_1700_B n_1700_B(b_1430_k.J_1907_R entityBuilder, DamageSourcePredicate.n_1700_B sourceBuilder) {
            return new n_1700_B(U_3554_Q.J_1907_R.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B(entityBuilder.J_1907_R()), sourceBuilder.J_1907_R());
        }

        public static n_1700_B G_564_y() {
            return new n_1700_B(U_3554_Q.R_4764_Y.n_1700_B, b_1430_k.n_1700_B.n_1700_B, b_1430_k.n_1700_B.n_1700_B, DamageSourcePredicate.n_1700_B);
        }

        public boolean n_1700_B(B_4088_l player, q_1704_m context, P_11_z source) {
            return !this.J_1907_R.n_1700_B(player, source) ? false : this.n_1700_B.n_1700_B(context);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("entity", this.n_1700_B.n_1700_B(conditions));
            jsonobject.add("killing_blow", this.J_1907_R.n_1700_B());
            return jsonobject;
        }
    }
}


