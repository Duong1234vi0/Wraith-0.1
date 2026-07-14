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
import java.util.function.Predicate;
import lightning.product.A_3895_D;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.d_1292_N;
import lightning.product.d_614_w;
import lightning.product.g_1866_m;
import lightning.product.ComposableEntryContainer;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;
import org.apache.commons.lang3.ArrayUtils;

public abstract class u_1373_N
implements ComposableEntryContainer {
    protected final LootItemCondition[] R_4764_Y;
    private final Predicate<q_1704_m> G_564_y;

    protected u_1373_N(LootItemCondition[] p_i51254_1_) {
        this.R_4764_Y = p_i51254_1_;
        this.G_564_y = LootItemConditions.n_1700_B(p_i51254_1_);
    }

    public void n_1700_B(g_1866_m p_225579_1_) {
        for (int i = 0; i < this.R_4764_Y.length; ++i) {
            this.R_4764_Y[i].n_1700_B(p_225579_1_.J_1907_R(".condition[" + i + "]"));
        }
    }

    protected final boolean n_1700_B(q_1704_m p_216141_1_) {
        return this.G_564_y.test(p_216141_1_);
    }

    public abstract d_614_w n_1700_B();

    public static abstract class J_1907_R<T extends u_1373_N>
    implements Serializer<T> {
        public final void J_1907_R(JsonObject p_230424_1_, T p_230424_2_, JsonSerializationContext p_230424_3_) {
            if (!ArrayUtils.isEmpty((Object[])((u_1373_N)p_230424_2_).R_4764_Y)) {
                p_230424_1_.add("conditions", p_230424_3_.serialize((Object)((u_1373_N)p_230424_2_).R_4764_Y));
            }
            this.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
        }

        public final T J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            LootItemCondition[] ailootcondition = i_4431_W.n_1700_B(p_230423_1_, "conditions", new LootItemCondition[0], p_230423_2_, LootItemCondition[].class);
            return this.n_1700_B(p_230423_1_, p_230423_2_, ailootcondition);
        }

        @Override
        public abstract void n_1700_B(JsonObject var1, T var2, JsonSerializationContext var3);

        public abstract T n_1700_B(JsonObject var1, JsonDeserializationContext var2, LootItemCondition[] var3);

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }

        @Override
        public /* synthetic */ void n_1700_B(JsonObject jsonObject, Object object, JsonSerializationContext jsonSerializationContext) {
            this.J_1907_R(jsonObject, (u_1373_N)object, jsonSerializationContext);
        }
    }

    public static abstract class n_1700_B<T extends n_1700_B<T>>
    implements A_3895_D<T> {
        private final List<LootItemCondition> n_1700_B = Lists.newArrayList();

        protected abstract T R_4764_Y();

        public T J_1907_R(LootItemCondition.n_1700_B conditionBuilder) {
            this.n_1700_B.add(conditionBuilder.build());
            return this.R_4764_Y();
        }

        public final T P_1922_E() {
            return this.R_4764_Y();
        }

        protected LootItemCondition[] u_1723_Y() {
            return this.n_1700_B.toArray(new LootItemCondition[0]);
        }

        public d_1292_N.n_1700_B n_1700_B(n_1700_B<?> p_216080_1_) {
            return new d_1292_N.n_1700_B(this, p_216080_1_);
        }

        public abstract u_1373_N J_1907_R();

        @Override
        public /* synthetic */ Object G_564_y() {
            return this.P_1922_E();
        }

        @Override
        public /* synthetic */ Object n_1700_B(LootItemCondition.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }
    }
}


