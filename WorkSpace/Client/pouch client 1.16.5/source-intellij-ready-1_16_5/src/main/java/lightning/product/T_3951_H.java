/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import lightning.product.A_2178_U;
import lightning.product.A_3895_D;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;
import org.apache.commons.lang3.ArrayUtils;

public abstract class T_3951_H
implements A_2178_U {
    protected final LootItemCondition[] n_1700_B;
    private final Predicate<q_1704_m> J_1907_R;

    protected T_3951_H(LootItemCondition[] conditionsIn) {
        this.n_1700_B = conditionsIn;
        this.J_1907_R = LootItemConditions.n_1700_B(conditionsIn);
    }

    public final Z_1993_T n_1700_B(Z_1993_T p_apply_1_, q_1704_m p_apply_2_) {
        return this.J_1907_R.test(p_apply_2_) ? this.J_1907_R(p_apply_1_, p_apply_2_) : p_apply_1_;
    }

    protected abstract Z_1993_T J_1907_R(Z_1993_T var1, q_1704_m var2);

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        A_2178_U.super.n_1700_B(p_225580_1_);
        for (int i = 0; i < this.n_1700_B.length; ++i) {
            this.n_1700_B[i].n_1700_B(p_225580_1_.J_1907_R(".conditions[" + i + "]"));
        }
    }

    protected static n_1700_B<?> n_1700_B(Function<LootItemCondition[], A_2178_U> p_215860_0_) {
        return new R_4764_Y(p_215860_0_);
    }

    @Override
    public /* synthetic */ Object apply(Object object, Object object2) {
        return this.n_1700_B((Z_1993_T)object, (q_1704_m)object2);
    }

    static final class R_4764_Y
    extends n_1700_B<R_4764_Y> {
        private final Function<LootItemCondition[], A_2178_U> n_1700_B;

        public R_4764_Y(Function<LootItemCondition[], A_2178_U> p_i50229_1_) {
            this.n_1700_B = p_i50229_1_;
        }

        protected R_4764_Y P_1922_E() {
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return this.n_1700_B.apply(this.R_4764_Y());
        }

        @Override
        protected /* synthetic */ n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static abstract class J_1907_R<T extends T_3951_H>
    implements Serializer<T> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, T p_230424_2_, JsonSerializationContext p_230424_3_) {
            if (!ArrayUtils.isEmpty((Object[])((T_3951_H)p_230424_2_).n_1700_B)) {
                p_230424_1_.add("conditions", p_230424_3_.serialize((Object)((T_3951_H)p_230424_2_).n_1700_B));
            }
        }

        public final T J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            LootItemCondition[] ailootcondition = i_4431_W.n_1700_B(p_230423_1_, "conditions", new LootItemCondition[0], p_230423_2_, LootItemCondition[].class);
            return this.n_1700_B(p_230423_1_, p_230423_2_, ailootcondition);
        }

        public abstract T n_1700_B(JsonObject var1, JsonDeserializationContext var2, LootItemCondition[] var3);

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }

    public static abstract class n_1700_B<T extends n_1700_B<T>>
    implements A_2178_U.n_1700_B,
    A_3895_D<T> {
        private final List<LootItemCondition> n_1700_B = Lists.newArrayList();

        public T J_1907_R(LootItemCondition.n_1700_B conditionBuilder) {
            this.n_1700_B.add(conditionBuilder.build());
            return this.J_1907_R();
        }

        public final T n_1700_B() {
            return this.J_1907_R();
        }

        protected abstract T J_1907_R();

        protected LootItemCondition[] R_4764_Y() {
            return this.n_1700_B.toArray(new LootItemCondition[0]);
        }

        @Override
        public /* synthetic */ Object G_564_y() {
            return this.n_1700_B();
        }

        @Override
        public /* synthetic */ Object n_1700_B(LootItemCondition.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }
    }
}


