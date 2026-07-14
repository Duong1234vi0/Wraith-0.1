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
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class a_307_A
implements LootItemCondition {
    @Nullable
    private final Boolean n_1700_B;
    @Nullable
    private final Boolean J_1907_R;

    private a_307_A(@Nullable Boolean raining, @Nullable Boolean thundering) {
        this.n_1700_B = raining;
        this.J_1907_R = thundering;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.h_1847_R;
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        e_3591_l serverworld = p_test_1_.R_4764_Y();
        if (this.n_1700_B != null && this.n_1700_B.booleanValue() != serverworld.c_4037_x()) {
            return false;
        }
        return this.J_1907_R == null || this.J_1907_R.booleanValue() == serverworld.N_2525_X();
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<a_307_A> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, a_307_A p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("raining", p_230424_2_.n_1700_B);
            p_230424_1_.addProperty("thundering", p_230424_2_.J_1907_R);
        }

        public a_307_A J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            Boolean obool = p_230423_1_.has("raining") ? Boolean.valueOf(i_4431_W.w_1484_f(p_230423_1_, "raining")) : null;
            Boolean obool1 = p_230423_1_.has("thundering") ? Boolean.valueOf(i_4431_W.w_1484_f(p_230423_1_, "thundering")) : null;
            return new a_307_A(obool, obool1);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


