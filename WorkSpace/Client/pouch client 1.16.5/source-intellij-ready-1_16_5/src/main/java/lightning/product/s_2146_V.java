/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.authlib.GameProfile;
import java.util.Set;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.i_4431_W;
import lightning.product.n_3832_I;
import lightning.product.q_1704_m;
import lightning.product.Items;

public class s_2146_V
extends T_3951_H {
    private final q_1704_m.J_1907_R J_1907_R;

    public s_2146_V(LootItemCondition[] p_i51234_1_, q_1704_m.J_1907_R p_i51234_2_) {
        super(p_i51234_1_);
        this.J_1907_R = p_i51234_2_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.Y_601_j;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(this.J_1907_R.n_1700_B());
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        N_4263_v entity;
        if (stack.J_1907_R() == Items.C_3560_B && (entity = context.J_1907_R(this.J_1907_R.n_1700_B())) instanceof a_3913_L) {
            GameProfile gameprofile = ((a_3913_L)entity).y_4642_Y();
            stack.M_182_A().n_1700_B("SkullOwner", n_3832_I.n_1700_B(new U_2912_j(), gameprofile));
        }
        return stack;
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<s_2146_V> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, s_2146_V p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("entity", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public s_2146_V J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            q_1704_m.J_1907_R lootcontext$entitytarget = i_4431_W.n_1700_B(object, "entity", deserializationContext, q_1704_m.J_1907_R.class);
            return new s_2146_V(conditionsIn, lootcontext$entitytarget);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


