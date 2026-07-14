/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import javax.annotation.Nullable;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.e_3591_l;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class B_4423_D
implements LootItemCondition {
    @Nullable
    private final Long n_1700_B;
    private final o_3393_s J_1907_R;

    private B_4423_D(@Nullable Long p_i225898_1_, o_3393_s p_i225898_2_) {
        this.n_1700_B = p_i225898_1_;
        this.J_1907_R = p_i225898_2_;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.M_182_A;
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        e_3591_l serverworld = p_test_1_.R_4764_Y();
        long i = serverworld.Z_976_R();
        if (this.n_1700_B != null) {
            i %= this.n_1700_B.longValue();
        }
        return this.J_1907_R.n_1700_B((int)i);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<B_4423_D> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, B_4423_D p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("period", (Number)p_230424_2_.n_1700_B);
            p_230424_1_.add("value", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public B_4423_D J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            Long olong = p_230423_1_.has("period") ? Long.valueOf(i_4431_W.s_956_w(p_230423_1_, "period")) : null;
            o_3393_s randomvaluerange = i_4431_W.n_1700_B(p_230423_1_, "value", p_230423_2_, o_3393_s.class);
            return new B_4423_D(olong, randomvaluerange);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


