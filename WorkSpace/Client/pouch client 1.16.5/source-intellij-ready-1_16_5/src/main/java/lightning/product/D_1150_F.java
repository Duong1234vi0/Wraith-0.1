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
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;

public class D_1150_F
extends T_3951_H {
    private final g_2336_b J_1907_R;
    private final long R_4764_Y;

    private D_1150_F(LootItemCondition[] p_i51224_1_, g_2336_b p_i51224_2_, long p_i51224_3_) {
        super(p_i51224_1_);
        this.J_1907_R = p_i51224_2_;
        this.R_4764_Y = p_i51224_3_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.t_1786_h;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        if (stack.n_1700_B()) {
            return stack;
        }
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("LootTable", this.J_1907_R.toString());
        if (this.R_4764_Y != 0L) {
            compoundnbt.n_1700_B("LootTableSeed", this.R_4764_Y);
        }
        stack.M_182_A().n_1700_B("BlockEntityTag", compoundnbt);
        return stack;
    }

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        if (p_225580_1_.n_1700_B(this.J_1907_R)) {
            p_225580_1_.n_1700_B("Table " + String.valueOf(this.J_1907_R) + " is recursively called");
        } else {
            super.n_1700_B(p_225580_1_);
            p_4985_U loottable = p_225580_1_.R_4764_Y(this.J_1907_R);
            if (loottable == null) {
                p_225580_1_.n_1700_B("Unknown loot table called " + String.valueOf(this.J_1907_R));
            } else {
                loottable.n_1700_B(p_225580_1_.n_1700_B("->{" + String.valueOf(this.J_1907_R) + "}", this.J_1907_R));
            }
        }
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<D_1150_F> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, D_1150_F p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("name", p_230424_2_.J_1907_R.toString());
            if (p_230424_2_.R_4764_Y != 0L) {
                p_230424_1_.addProperty("seed", (Number)p_230424_2_.R_4764_Y);
            }
        }

        public D_1150_F J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "name"));
            long i = i_4431_W.n_1700_B(object, "seed", 0L);
            return new D_1150_F(conditionsIn, resourcelocation, i);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


