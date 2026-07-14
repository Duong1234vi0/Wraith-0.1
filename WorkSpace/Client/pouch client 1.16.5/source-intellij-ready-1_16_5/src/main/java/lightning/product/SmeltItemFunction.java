/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Optional;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.N_1216_z;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Y_3349_u;
import lightning.product.Z_1993_T;
import lightning.product.RecipeType;
import lightning.product.q_1704_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmeltItemFunction
extends T_3951_H {
    private static final Logger J_1907_R = LogManager.getLogger();

    private SmeltItemFunction(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.u_1723_Y;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Z_1993_T itemstack;
        if (stack.n_1700_B()) {
            return stack;
        }
        Optional<Y_3349_u> optional = context.R_4764_Y().s_956_w().n_1700_B(RecipeType.J_1907_R, new N_1216_z(stack), context.R_4764_Y());
        if (optional.isPresent() && !(itemstack = optional.get().R_4764_Y()).n_1700_B()) {
            Z_1993_T itemstack1 = itemstack.t_148_a();
            itemstack1.P_1922_E(stack.t_4043_B());
            return itemstack1;
        }
        J_1907_R.warn("Couldn't smelt {} because there is no smelting recipe", (Object)stack);
        return stack;
    }

    public static T_3951_H.n_1700_B<?> R_4764_Y() {
        return SmeltItemFunction.n_1700_B(SmeltItemFunction::new);
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<SmeltItemFunction> {
        public SmeltItemFunction J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            return new SmeltItemFunction(conditionsIn);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


