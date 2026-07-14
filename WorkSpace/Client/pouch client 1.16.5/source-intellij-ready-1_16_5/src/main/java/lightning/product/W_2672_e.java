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
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.b_1430_k;
import lightning.product.e_2866_D;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class W_2672_e
implements LootItemCondition {
    private final b_1430_k n_1700_B;
    private final q_1704_m.J_1907_R J_1907_R;

    private W_2672_e(b_1430_k predicateIn, q_1704_m.J_1907_R targetIn) {
        this.n_1700_B = predicateIn;
        this.J_1907_R = targetIn;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.P_1922_E;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.u_1723_Y, this.J_1907_R.n_1700_B());
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        N_4263_v entity = p_test_1_.J_1907_R(this.J_1907_R.n_1700_B());
        e_2866_D vector3d = p_test_1_.J_1907_R(LootContextParams.u_1723_Y);
        return this.n_1700_B.n_1700_B(p_test_1_.R_4764_Y(), vector3d, entity);
    }

    public static LootItemCondition.n_1700_B n_1700_B(q_1704_m.J_1907_R targetIn) {
        return W_2672_e.n_1700_B(targetIn, b_1430_k.J_1907_R.n_1700_B());
    }

    public static LootItemCondition.n_1700_B n_1700_B(q_1704_m.J_1907_R targetIn, b_1430_k.J_1907_R predicateBuilderIn) {
        return () -> new W_2672_e(predicateBuilderIn.J_1907_R(), targetIn);
    }

    public static LootItemCondition.n_1700_B n_1700_B(q_1704_m.J_1907_R p_237477_0_, b_1430_k p_237477_1_) {
        return () -> new W_2672_e(p_237477_1_, p_237477_0_);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<W_2672_e> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, W_2672_e p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("predicate", p_230424_2_.n_1700_B.n_1700_B());
            p_230424_1_.add("entity", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public W_2672_e J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            b_1430_k entitypredicate = b_1430_k.n_1700_B(p_230423_1_.get("predicate"));
            return new W_2672_e(entitypredicate, i_4431_W.n_1700_B(p_230423_1_, "entity", p_230423_2_, q_1704_m.J_1907_R.class));
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


