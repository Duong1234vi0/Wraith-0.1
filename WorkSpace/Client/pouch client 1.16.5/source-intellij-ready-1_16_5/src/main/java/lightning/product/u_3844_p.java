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
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class u_3844_p
implements LootItemCondition {
    private static final u_3844_p n_1700_B = new u_3844_p();

    private u_3844_p() {
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.u_1723_Y;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.J_1907_R);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        return p_test_1_.n_1700_B(LootContextParams.J_1907_R);
    }

    public static LootItemCondition.n_1700_B R_4764_Y() {
        return () -> n_1700_B;
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<u_3844_p> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, u_3844_p p_230424_2_, JsonSerializationContext p_230424_3_) {
        }

        public u_3844_p J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            return n_1700_B;
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


