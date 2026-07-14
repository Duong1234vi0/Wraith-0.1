/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_203_K
extends T_3951_H {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final o_3393_s R_4764_Y;

    private b_203_K(LootItemCondition[] conditionsIn, o_3393_s damageRangeIn) {
        super(conditionsIn);
        this.R_4764_Y = damageRangeIn;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.w_1484_f;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        if (stack.P_1922_E()) {
            float f = 1.0f - this.R_4764_Y.J_1907_R(context.n_1700_B());
            stack.J_1907_R(u_530_F.G_564_y(f * (float)stack.w_1484_f()));
        } else {
            J_1907_R.warn("Couldn't set damage of loot item {}", (Object)stack);
        }
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(o_3393_s p_215931_0_) {
        return b_203_K.n_1700_B((LootItemCondition[] p_215930_1_) -> new b_203_K((LootItemCondition[])p_215930_1_, p_215931_0_));
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<b_203_K> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, b_203_K p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("damage", p_230424_3_.serialize((Object)p_230424_2_.R_4764_Y));
        }

        public b_203_K J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            return new b_203_K(conditionsIn, i_4431_W.n_1700_B(object, "damage", deserializationContext, o_3393_s.class));
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


