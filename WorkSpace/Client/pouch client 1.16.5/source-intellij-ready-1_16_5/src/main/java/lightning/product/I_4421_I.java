/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.N_4263_v;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;

public class I_4421_I
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("channeled_lightning");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        b_1430_k.n_1700_B[] aentitypredicate$andpredicate = b_1430_k.n_1700_B.J_1907_R(json, "victims", conditionsParser);
        return new n_1700_B(entityPredicate, aentitypredicate$andpredicate);
    }

    @Override
    public void n_1700_B(B_4088_l player, Collection<? extends N_4263_v> entityTriggered) {
        List list = entityTriggered.stream().map(entity -> b_1430_k.J_1907_R(player, entity)).collect(Collectors.toList());
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(list));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B[] n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, b_1430_k.n_1700_B[] victims) {
            super(n_1700_B, player);
            this.n_1700_B = victims;
        }

        public static n_1700_B n_1700_B(b_1430_k ... victims) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, (b_1430_k.n_1700_B[])Stream.of(victims).map(b_1430_k.n_1700_B::n_1700_B).toArray(b_1430_k.n_1700_B[]::new));
        }

        public boolean n_1700_B(Collection<? extends q_1704_m> victims) {
            for (b_1430_k.n_1700_B entitypredicate$andpredicate : this.n_1700_B) {
                boolean flag = false;
                for (q_1704_m q_1704_m2 : victims) {
                    if (!entitypredicate$andpredicate.n_1700_B(q_1704_m2)) continue;
                    flag = true;
                    break;
                }
                if (flag) continue;
                return false;
            }
            return true;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("victims", b_1430_k.n_1700_B.n_1700_B(this.n_1700_B, conditions));
            return jsonobject;
        }
    }
}


