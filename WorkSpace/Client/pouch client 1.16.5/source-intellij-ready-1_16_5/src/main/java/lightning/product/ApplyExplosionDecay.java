/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Random;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.q_1704_m;

public class ApplyExplosionDecay
extends T_3951_H {
    private ApplyExplosionDecay(LootItemCondition[] p_i51244_1_) {
        super(p_i51244_1_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.multiplayerClientSuggestionProvider;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Float f = context.J_1907_R(LootContextParams.s_956_w);
        if (f != null) {
            Random random = context.n_1700_B();
            float f1 = 1.0f / f.floatValue();
            int i = stack.t_4043_B();
            int j = 0;
            for (int k = 0; k < i; ++k) {
                if (!(random.nextFloat() <= f1)) continue;
                ++j;
            }
            stack.P_1922_E(j);
        }
        return stack;
    }

    public static T_3951_H.n_1700_B<?> R_4764_Y() {
        return ApplyExplosionDecay.n_1700_B(ApplyExplosionDecay::new);
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<ApplyExplosionDecay> {
        public ApplyExplosionDecay J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            return new ApplyExplosionDecay(conditionsIn);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


