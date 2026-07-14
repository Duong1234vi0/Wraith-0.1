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
import lightning.product.H_3357_D;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;

public class O_215_U
extends T_3951_H {
    private final H_3357_D J_1907_R;

    private O_215_U(LootItemCondition[] p_i51232_1_, H_3357_D p_i51232_2_) {
        super(p_i51232_1_);
        this.J_1907_R = p_i51232_2_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.Q_4569_t;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        int i = this.J_1907_R.applyAsInt(stack.t_4043_B());
        stack.P_1922_E(i);
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(H_3357_D p_215911_0_) {
        return O_215_U.n_1700_B((LootItemCondition[] p_215912_1_) -> new O_215_U((LootItemCondition[])p_215912_1_, p_215911_0_));
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<O_215_U> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, O_215_U p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("limit", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public O_215_U J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            H_3357_D intclamper = i_4431_W.n_1700_B(object, "limit", deserializationContext, H_3357_D.class);
            return new O_215_U(conditionsIn, intclamper);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


