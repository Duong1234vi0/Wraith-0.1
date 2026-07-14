/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import lightning.product.LootContextParams;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;
import lightning.product.w_4866_k;

public class y_3142_C
implements LootItemCondition {
    private final w_4866_k n_1700_B;

    public y_3142_C(w_4866_k predicate) {
        this.n_1700_B = predicate;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.t_148_a;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.t_148_a);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        Z_1993_T itemstack = p_test_1_.J_1907_R(LootContextParams.t_148_a);
        return itemstack != null && this.n_1700_B.n_1700_B(itemstack);
    }

    public static LootItemCondition.n_1700_B n_1700_B(w_4866_k.n_1700_B p_216012_0_) {
        return () -> new y_3142_C(p_216012_0_.J_1907_R());
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<y_3142_C> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, y_3142_C p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("predicate", p_230424_2_.n_1700_B.n_1700_B());
        }

        public y_3142_C J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            w_4866_k itempredicate = w_4866_k.n_1700_B(p_230423_1_.get("predicate"));
            return new y_3142_C(itempredicate);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


