/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.r_4318_c;

public class i_4685_W
extends T_3951_H {
    private final U_2912_j J_1907_R;

    private i_4685_W(LootItemCondition[] conditionsIn, U_2912_j tagIn) {
        super(conditionsIn);
        this.J_1907_R = tagIn;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.P_1922_E;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        stack.M_182_A().n_1700_B(this.J_1907_R);
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(U_2912_j p_215952_0_) {
        return i_4685_W.n_1700_B((LootItemCondition[] p_215951_1_) -> new i_4685_W((LootItemCondition[])p_215951_1_, p_215952_0_));
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<i_4685_W> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, i_4685_W p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("tag", p_230424_2_.J_1907_R.toString());
        }

        public i_4685_W J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            try {
                U_2912_j compoundnbt = r_4318_c.n_1700_B(i_4431_W.u_1723_Y(object, "tag"));
                return new i_4685_W(conditionsIn, compoundnbt);
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                throw new JsonSyntaxException(commandsyntaxexception.getMessage());
            }
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


