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
import java.util.Random;
import java.util.Set;
import lightning.product.LootContextParams;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.LootItemCondition;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class g_1926_q
implements LootItemCondition {
    private static final g_1926_q n_1700_B = new g_1926_q();

    private g_1926_q() {
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.u_2550_I;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.s_956_w);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        Float f = p_test_1_.J_1907_R(LootContextParams.s_956_w);
        if (f != null) {
            Random random = p_test_1_.n_1700_B();
            float f1 = 1.0f / f.floatValue();
            return random.nextFloat() <= f1;
        }
        return true;
    }

    public static LootItemCondition.n_1700_B R_4764_Y() {
        return () -> n_1700_B;
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<g_1926_q> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, g_1926_q p_230424_2_, JsonSerializationContext p_230424_3_) {
        }

        public g_1926_q J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            return n_1700_B;
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


