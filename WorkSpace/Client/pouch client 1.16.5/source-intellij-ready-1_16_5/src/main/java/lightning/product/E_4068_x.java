/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.Collection;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.N_4263_v;
import lightning.product.P_1965_C;
import lightning.product.W_1247_f;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.n_1494_c;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class E_4068_x
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("fishing_rod_hooked");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("rod"));
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(json, "entity", conditionsParser);
        w_4866_k itempredicate1 = w_4866_k.n_1700_B(json.get("item"));
        return new n_1700_B(entityPredicate, itempredicate, entitypredicate$andpredicate, itempredicate1);
    }

    public void n_1700_B(B_4088_l player, Z_1993_T rod, W_1247_f entity, Collection<Z_1993_T> items) {
        q_1704_m lootcontext = b_1430_k.J_1907_R(player, entity.w_1484_f() != null ? entity.w_1484_f() : entity);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(rod, lootcontext, items));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final w_4866_k n_1700_B;
        private final b_1430_k.n_1700_B J_1907_R;
        private final w_4866_k R_4764_Y;

        public n_1700_B(b_1430_k.n_1700_B player, w_4866_k rod, b_1430_k.n_1700_B entity, w_4866_k item) {
            super(n_1700_B, player);
            this.n_1700_B = rod;
            this.J_1907_R = entity;
            this.R_4764_Y = item;
        }

        public static n_1700_B n_1700_B(w_4866_k rod, b_1430_k bobber, w_4866_k item) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, rod, b_1430_k.n_1700_B.n_1700_B(bobber), item);
        }

        public boolean n_1700_B(Z_1993_T rod, q_1704_m context, Collection<Z_1993_T> items) {
            if (!this.n_1700_B.n_1700_B(rod)) {
                return false;
            }
            if (!this.J_1907_R.n_1700_B(context)) {
                return false;
            }
            if (this.R_4764_Y != w_4866_k.n_1700_B) {
                n_1494_c itementity;
                boolean flag = false;
                N_4263_v entity = context.J_1907_R(LootContextParams.n_1700_B);
                if (entity instanceof n_1494_c && this.R_4764_Y.n_1700_B((itementity = (n_1494_c)entity).P_1922_E())) {
                    flag = true;
                }
                for (Z_1993_T itemstack : items) {
                    if (!this.R_4764_Y.n_1700_B(itemstack)) continue;
                    flag = true;
                    break;
                }
                if (!flag) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("rod", this.n_1700_B.n_1700_B());
            jsonobject.add("entity", this.J_1907_R.n_1700_B(conditions));
            jsonobject.add("item", this.R_4764_Y.n_1700_B());
            return jsonobject;
        }
    }
}


