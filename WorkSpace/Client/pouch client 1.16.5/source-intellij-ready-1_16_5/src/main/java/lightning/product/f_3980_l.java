/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.function.Predicate;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class f_3980_l
implements LootItemCondition {
    private final LootItemCondition[] n_1700_B;
    private final Predicate<q_1704_m> J_1907_R;

    private f_3980_l(LootItemCondition[] conditionsIn) {
        this.n_1700_B = conditionsIn;
        this.J_1907_R = LootItemConditions.J_1907_R(conditionsIn);
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.J_1907_R;
    }

    public final boolean n_1700_B(q_1704_m p_test_1_) {
        return this.J_1907_R.test(p_test_1_);
    }

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        LootItemCondition.super.n_1700_B(p_225580_1_);
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i].n_1700_B(p_225580_1_.J_1907_R(".term[" + i + "]"));
        }
    }

    public static n_1700_B n_1700_B(LootItemCondition.n_1700_B ... buildersIn) {
        return new n_1700_B(buildersIn);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements LootItemCondition.n_1700_B {
        private final List<LootItemCondition> n_1700_B = Lists.newArrayList();

        public n_1700_B(LootItemCondition.n_1700_B ... buildersIn) {
            for (LootItemCondition.n_1700_B ilootcondition$ibuilder : buildersIn) {
                this.n_1700_B.add(ilootcondition$ibuilder.build());
            }
        }

        @Override
        public n_1700_B n_1700_B(LootItemCondition.n_1700_B builderIn) {
            this.n_1700_B.add(builderIn.build());
            return this;
        }

        @Override
        public LootItemCondition build() {
            return new f_3980_l(this.n_1700_B.toArray(new LootItemCondition[0]));
        }
    }

    public static class J_1907_R
    implements Serializer<f_3980_l> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, f_3980_l p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("terms", p_230424_3_.serialize((Object)p_230424_2_.n_1700_B));
        }

        public f_3980_l J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            LootItemCondition[] ailootcondition = i_4431_W.n_1700_B(p_230423_1_, "terms", p_230423_2_, LootItemCondition[].class);
            return new f_3980_l(ailootcondition);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


