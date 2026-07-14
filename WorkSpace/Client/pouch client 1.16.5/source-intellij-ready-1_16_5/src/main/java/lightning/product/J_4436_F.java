/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Arrays;
import java.util.List;
import lightning.product.A_2178_U;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.NonNullList;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.ContainerHelper;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.u_1373_N;

public class J_4436_F
extends T_3951_H {
    private final List<u_1373_N> J_1907_R;

    private J_4436_F(LootItemCondition[] p_i51226_1_, List<u_1373_N> p_i51226_2_) {
        super(p_i51226_1_);
        this.J_1907_R = ImmutableList.copyOf(p_i51226_2_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.h_1847_R;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        if (stack.n_1700_B()) {
            return stack;
        }
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B();
        this.J_1907_R.forEach(p_215921_2_ -> p_215921_2_.expand(context, p_215922_2_ -> p_215922_2_.n_1700_B(p_4985_U.n_1700_B(nonnulllist::add), context)));
        U_2912_j compoundnbt = new U_2912_j();
        ContainerHelper.n_1700_B(compoundnbt, nonnulllist);
        U_2912_j compoundnbt1 = stack.M_182_A();
        compoundnbt1.n_1700_B("BlockEntityTag", compoundnbt.n_1700_B(compoundnbt1.M_182_A("BlockEntityTag")));
        return stack;
    }

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        super.n_1700_B(p_225580_1_);
        for (int i = 0; i < this.J_1907_R.size(); ++i) {
            this.J_1907_R.get(i).n_1700_B(p_225580_1_.J_1907_R(".entry[" + i + "]"));
        }
    }

    public static n_1700_B R_4764_Y() {
        return new n_1700_B();
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final List<u_1373_N> n_1700_B = Lists.newArrayList();

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B n_1700_B(u_1373_N.n_1700_B<?> lootEntryBuilder) {
            this.n_1700_B.add(lootEntryBuilder.J_1907_R());
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new J_4436_F(this.R_4764_Y(), this.n_1700_B);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<J_4436_F> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, J_4436_F p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("entries", p_230424_3_.serialize(p_230424_2_.J_1907_R));
        }

        public J_4436_F J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            u_1373_N[] alootentry = i_4431_W.n_1700_B(object, "entries", deserializationContext, u_1373_N[].class);
            return new J_4436_F(conditionsIn, Arrays.asList(alootentry));
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


