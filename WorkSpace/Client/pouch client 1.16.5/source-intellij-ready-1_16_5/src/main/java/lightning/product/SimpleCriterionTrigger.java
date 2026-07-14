/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import lightning.product.B_4088_l;
import lightning.product.P_1965_C;
import lightning.product.CriterionTrigger;
import lightning.product.S_4998_h;
import lightning.product.b_1430_k;
import lightning.product.h_1723_G;
import lightning.product.q_1704_m;
import lightning.product.DeserializationContext;

public abstract class SimpleCriterionTrigger<T extends P_1965_C>
implements CriterionTrigger<T> {
    private final Map<S_4998_h, Set<CriterionTrigger.n_1700_B<T>>> n_1700_B = Maps.newIdentityHashMap();

    @Override
    public final void n_1700_B(S_4998_h playerAdvancementsIn, CriterionTrigger.n_1700_B<T> listener) {
        this.n_1700_B.computeIfAbsent(playerAdvancementsIn, playerAdvancements -> Sets.newHashSet()).add(listener);
    }

    @Override
    public final void J_1907_R(S_4998_h playerAdvancementsIn, CriterionTrigger.n_1700_B<T> listener) {
        Set<CriterionTrigger.n_1700_B<T>> set = this.n_1700_B.get(playerAdvancementsIn);
        if (set != null) {
            set.remove(listener);
            if (set.isEmpty()) {
                this.n_1700_B.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public final void n_1700_B(S_4998_h playerAdvancementsIn) {
        this.n_1700_B.remove(playerAdvancementsIn);
    }

    protected abstract T n_1700_B(JsonObject var1, b_1430_k.n_1700_B var2, DeserializationContext var3);

    public final T J_1907_R(JsonObject object, DeserializationContext conditions) {
        b_1430_k.n_1700_B entitypredicate$andpredicate = b_1430_k.n_1700_B.n_1700_B(object, "player", conditions);
        return this.n_1700_B(object, entitypredicate$andpredicate, conditions);
    }

    protected void n_1700_B(B_4088_l serverPlayer, Predicate<T> testTrigger) {
        S_4998_h playeradvancements = serverPlayer.g_164_R();
        Set<CriterionTrigger.n_1700_B<T>> set = this.n_1700_B.get(playeradvancements);
        if (set != null && !set.isEmpty()) {
            q_1704_m lootcontext = b_1430_k.J_1907_R(serverPlayer, serverPlayer);
            List list = null;
            for (CriterionTrigger.n_1700_B<T> listener : set) {
                P_1965_C t = (P_1965_C)listener.n_1700_B();
                if (!t.R_4764_Y().n_1700_B(lootcontext) || !testTrigger.test(t)) continue;
                if (list == null) {
                    list = Lists.newArrayList();
                }
                list.add(listener);
            }
            if (list != null) {
                for (CriterionTrigger.n_1700_B<Object> listener1 : list) {
                    listener1.n_1700_B(playeradvancements);
                }
            }
        }
    }

    @Override
    public /* synthetic */ h_1723_G n_1700_B(JsonObject jsonObject, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, u_4771_O2);
    }
}


