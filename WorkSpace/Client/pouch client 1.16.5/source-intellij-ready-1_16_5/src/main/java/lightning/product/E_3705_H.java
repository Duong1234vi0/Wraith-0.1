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
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Y_4400_R;
import lightning.product.Z_1993_T;
import lightning.product.RandomIntGenerator;
import lightning.product.q_1704_m;

public class E_3705_H
extends T_3951_H {
    private final RandomIntGenerator J_1907_R;

    private E_3705_H(LootItemCondition[] p_i51222_1_, RandomIntGenerator p_i51222_2_) {
        super(p_i51222_1_);
        this.J_1907_R = p_i51222_2_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.J_1907_R;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        stack.P_1922_E(this.J_1907_R.n_1700_B(context.n_1700_B()));
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(RandomIntGenerator p_215932_0_) {
        return E_3705_H.n_1700_B((LootItemCondition[] p_215934_1_) -> new E_3705_H((LootItemCondition[])p_215934_1_, p_215932_0_));
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<E_3705_H> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, E_3705_H p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("count", Y_4400_R.n_1700_B(p_230424_2_.J_1907_R, p_230424_3_));
        }

        public E_3705_H J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            RandomIntGenerator irandomrange = Y_4400_R.n_1700_B(object.get("count"), deserializationContext);
            return new E_3705_H(conditionsIn, irandomrange);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


