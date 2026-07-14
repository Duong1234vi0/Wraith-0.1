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
import java.util.Set;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.LootItemCondition;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class W_1923_h
implements LootItemCondition {
    private final LootItemCondition n_1700_B;

    private W_1923_h(LootItemCondition term) {
        this.n_1700_B = term;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.n_1700_B;
    }

    public final boolean n_1700_B(q_1704_m p_test_1_) {
        return !this.n_1700_B.test(p_test_1_);
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return this.n_1700_B.n_1700_B();
    }

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        LootItemCondition.super.n_1700_B(p_225580_1_);
        this.n_1700_B.n_1700_B(p_225580_1_);
    }

    public static LootItemCondition.n_1700_B n_1700_B(LootItemCondition.n_1700_B p_215979_0_) {
        W_1923_h inverted = new W_1923_h(p_215979_0_.build());
        return () -> inverted;
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<W_1923_h> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, W_1923_h p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("term", p_230424_3_.serialize((Object)p_230424_2_.n_1700_B));
        }

        public W_1923_h J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            LootItemCondition ilootcondition = i_4431_W.n_1700_B(p_230423_1_, "term", p_230423_2_, LootItemCondition.class);
            return new W_1923_h(ilootcondition);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


