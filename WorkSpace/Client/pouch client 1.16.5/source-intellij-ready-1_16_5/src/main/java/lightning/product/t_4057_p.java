/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.N_4263_v;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.q_1704_m;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;

public class t_4057_p
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("killed_by_crossbow");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        b_1430_k.n_1700_B[] aentitypredicate$andpredicate = b_1430_k.n_1700_B.J_1907_R(json, "victims", conditionsParser);
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("unique_entity_types"));
        return new n_1700_B(entityPredicate, aentitypredicate$andpredicate, minmaxbounds$intbound);
    }

    @Override
    public void n_1700_B(B_4088_l player, Collection<N_4263_v> entities) {
        ArrayList list = Lists.newArrayList();
        HashSet set = Sets.newHashSet();
        for (N_4263_v entity : entities) {
            set.add(entity.f_4016_n());
            list.add(b_1430_k.J_1907_R(player, entity));
        }
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(list, set.size()));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final b_1430_k.n_1700_B[] n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, b_1430_k.n_1700_B[] entities, MinMaxBounds.G_564_y bounds) {
            super(n_1700_B, player);
            this.n_1700_B = entities;
            this.J_1907_R = bounds;
        }

        public static n_1700_B n_1700_B(b_1430_k.J_1907_R ... builders) {
            b_1430_k.n_1700_B[] aentitypredicate$andpredicate = new b_1430_k.n_1700_B[builders.length];
            for (int i = 0; i < builders.length; ++i) {
                b_1430_k.J_1907_R entitypredicate$builder = builders[i];
                aentitypredicate$andpredicate[i] = b_1430_k.n_1700_B.n_1700_B(entitypredicate$builder.J_1907_R());
            }
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, aentitypredicate$andpredicate, MinMaxBounds.G_564_y.P_1922_E);
        }

        public static n_1700_B n_1700_B(MinMaxBounds.G_564_y bounds) {
            b_1430_k.n_1700_B[] aentitypredicate$andpredicate = new b_1430_k.n_1700_B[]{};
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, aentitypredicate$andpredicate, bounds);
        }

        public boolean n_1700_B(Collection<q_1704_m> contexts, int bounds) {
            if (this.n_1700_B.length > 0) {
                ArrayList list = Lists.newArrayList(contexts);
                for (b_1430_k.n_1700_B entitypredicate$andpredicate : this.n_1700_B) {
                    boolean flag = false;
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        q_1704_m lootcontext = (q_1704_m)iterator.next();
                        if (!entitypredicate$andpredicate.n_1700_B(lootcontext)) continue;
                        iterator.remove();
                        flag = true;
                        break;
                    }
                    if (flag) continue;
                    return false;
                }
            }
            return this.J_1907_R.R_4764_Y(bounds);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("victims", b_1430_k.n_1700_B.n_1700_B(this.n_1700_B, conditions));
            jsonobject.add("unique_entity_types", this.J_1907_R.G_564_y());
            return jsonobject;
        }
    }
}


