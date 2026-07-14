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
import lightning.product.DamageSourcePredicate;
import lightning.product.P_11_z;
import lightning.product.LootItemCondition;
import lightning.product.e_2866_D;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class H_1285_S
implements LootItemCondition {
    private final DamageSourcePredicate n_1700_B;

    private H_1285_S(DamageSourcePredicate p_i51205_1_) {
        this.n_1700_B = p_i51205_1_;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.M_588_G;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.u_1723_Y, LootContextParams.R_4764_Y);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        P_11_z damagesource = p_test_1_.J_1907_R(LootContextParams.R_4764_Y);
        e_2866_D vector3d = p_test_1_.J_1907_R(LootContextParams.u_1723_Y);
        return vector3d != null && damagesource != null && this.n_1700_B.n_1700_B(p_test_1_.R_4764_Y(), vector3d, damagesource);
    }

    public static LootItemCondition.n_1700_B n_1700_B(DamageSourcePredicate.n_1700_B p_215966_0_) {
        return () -> new H_1285_S(p_215966_0_.J_1907_R());
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<H_1285_S> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, H_1285_S p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("predicate", p_230424_2_.n_1700_B.n_1700_B());
        }

        public H_1285_S J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            DamageSourcePredicate damagesourcepredicate = DamageSourcePredicate.n_1700_B(p_230423_1_.get("predicate"));
            return new H_1285_S(damagesourcepredicate);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


