/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.HashSet;
import java.util.Set;
import lightning.product.A_2178_U;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.K_4074_S;
import lightning.product.LootItemCondition;
import lightning.product.T_2915_h;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.v_3760_Q;

public class T_328_T
extends T_3951_H {
    private final T_2915_h J_1907_R;
    private final Set<v_3760_Q<?>> R_4764_Y;

    private T_328_T(LootItemCondition[] p_i225890_1_, T_2915_h p_i225890_2_, Set<v_3760_Q<?>> p_i225890_3_) {
        super(p_i225890_1_);
        this.J_1907_R = p_i225890_2_;
        this.R_4764_Y = p_i225890_3_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.Q_2552_b;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.v_4262_N);
    }

    @Override
    protected Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        K_4074_S blockstate = context.J_1907_R(LootContextParams.v_4262_N);
        if (blockstate != null) {
            U_2912_j compoundnbt1;
            U_2912_j compoundnbt = stack.M_182_A();
            if (compoundnbt.R_4764_Y("BlockStateTag", 10)) {
                compoundnbt1 = compoundnbt.M_182_A("BlockStateTag");
            } else {
                compoundnbt1 = new U_2912_j();
                compoundnbt.n_1700_B("BlockStateTag", compoundnbt1);
            }
            this.R_4764_Y.stream().filter(blockstate::J_1907_R).forEach(p_227548_2_ -> compoundnbt1.n_1700_B(p_227548_2_.P_1922_E(), T_328_T.n_1700_B(blockstate, p_227548_2_)));
        }
        return stack;
    }

    public static n_1700_B n_1700_B(T_2915_h p_227545_0_) {
        return new n_1700_B(p_227545_0_);
    }

    private static <T extends Comparable<T>> String n_1700_B(K_4074_S p_227546_0_, v_3760_Q<T> p_227546_1_) {
        T t = p_227546_0_.R_4764_Y(p_227546_1_);
        return p_227546_1_.n_1700_B(t);
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final T_2915_h n_1700_B;
        private final Set<v_3760_Q<?>> J_1907_R = Sets.newHashSet();

        private n_1700_B(T_2915_h p_i225892_1_) {
            this.n_1700_B = p_i225892_1_;
        }

        public n_1700_B n_1700_B(v_3760_Q<?> p_227552_1_) {
            if (!this.n_1700_B.t_1786_h().G_564_y().contains(p_227552_1_)) {
                throw new IllegalStateException("Property " + String.valueOf(p_227552_1_) + " is not present on block " + String.valueOf(this.n_1700_B));
            }
            this.J_1907_R.add(p_227552_1_);
            return this;
        }

        protected n_1700_B P_1922_E() {
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new T_328_T(this.R_4764_Y(), this.n_1700_B, this.J_1907_R);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<T_328_T> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, T_328_T p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("block", V_3137_a.q_4610_l.J_1907_R(p_230424_2_.J_1907_R).toString());
            JsonArray jsonarray = new JsonArray();
            p_230424_2_.R_4764_Y.forEach(p_227553_1_ -> jsonarray.add(p_227553_1_.P_1922_E()));
            p_230424_1_.add("properties", (JsonElement)jsonarray);
        }

        public T_328_T J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "block"));
            T_2915_h block = V_3137_a.q_4610_l.J_1907_R(resourcelocation).orElseThrow(() -> new IllegalArgumentException("Can't find block " + String.valueOf(resourcelocation)));
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = block.t_1786_h();
            HashSet set = Sets.newHashSet();
            JsonArray jsonarray = i_4431_W.n_1700_B(object, "properties", (JsonArray)null);
            if (jsonarray != null) {
                jsonarray.forEach(p_227554_2_ -> set.add(statecontainer.n_1700_B(i_4431_W.n_1700_B(p_227554_2_, "property"))));
            }
            return new T_328_T(conditionsIn, block, set);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


