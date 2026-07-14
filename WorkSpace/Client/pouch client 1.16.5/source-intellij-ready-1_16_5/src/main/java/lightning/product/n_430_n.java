/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class n_430_n
implements LootItemCondition {
    private final float n_1700_B;

    private n_430_n(float chanceIn) {
        this.n_1700_B = chanceIn;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.R_4764_Y;
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        return p_test_1_.n_1700_B().nextFloat() < this.n_1700_B;
    }

    public static LootItemCondition.n_1700_B n_1700_B(float chanceIn) {
        return () -> new n_430_n(chanceIn);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<n_430_n> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, n_430_n p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("chance", (Number)Float.valueOf(p_230424_2_.n_1700_B));
        }

        public n_430_n J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            return new n_430_n(i_4431_W.t_148_a(p_230423_1_, "chance"));
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


