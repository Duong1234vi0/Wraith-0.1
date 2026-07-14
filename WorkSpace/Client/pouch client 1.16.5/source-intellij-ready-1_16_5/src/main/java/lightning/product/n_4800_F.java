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
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.r_4811_B;
import lightning.product.LootItemConditions;

public class n_4800_F
implements LootItemCondition {
    private final float n_1700_B;
    private final float J_1907_R;

    private n_4800_F(float chanceIn, float lootingMultiplierIn) {
        this.n_1700_B = chanceIn;
        this.J_1907_R = lootingMultiplierIn;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.G_564_y;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.G_564_y);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        N_4263_v entity = p_test_1_.J_1907_R(LootContextParams.G_564_y);
        int i = 0;
        if (entity instanceof r_4811_B) {
            i = K_4096_w.v_4262_N((r_4811_B)entity);
        }
        return p_test_1_.n_1700_B().nextFloat() < this.n_1700_B + (float)i * this.J_1907_R;
    }

    public static LootItemCondition.n_1700_B n_1700_B(float chanceIn, float lootingMultiplierIn) {
        return () -> new n_4800_F(chanceIn, lootingMultiplierIn);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<n_4800_F> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, n_4800_F p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("chance", (Number)Float.valueOf(p_230424_2_.n_1700_B));
            p_230424_1_.addProperty("looting_multiplier", (Number)Float.valueOf(p_230424_2_.J_1907_R));
        }

        public n_4800_F J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            return new n_4800_F(i_4431_W.t_148_a(p_230423_1_, "chance"), i_4431_W.t_148_a(p_230423_1_, "looting_multiplier"));
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


