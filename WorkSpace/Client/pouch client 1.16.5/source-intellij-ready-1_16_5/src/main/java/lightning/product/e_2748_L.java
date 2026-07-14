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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import lightning.product.A_2178_U;
import lightning.product.LootItemFunctions;
import lightning.product.LootPoolEntry;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.Z_3128_E;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.u_1373_N;
import lightning.product.u_530_F;
import org.apache.commons.lang3.ArrayUtils;

public abstract class e_2748_L
extends u_1373_N {
    protected final int G_564_y;
    protected final int P_1922_E;
    protected final A_2178_U[] u_1723_Y;
    private final BiFunction<Z_1993_T, q_1704_m, Z_1993_T> v_4262_N;
    private final LootPoolEntry w_1484_f = new R_4764_Y(){

        @Override
        public void n_1700_B(Consumer<Z_1993_T> p_216188_1_, q_1704_m p_216188_2_) {
            e_2748_L.this.n_1700_B(A_2178_U.n_1700_B(e_2748_L.this.v_4262_N, p_216188_1_, p_216188_2_), p_216188_2_);
        }
    };

    protected e_2748_L(int weightIn, int qualityIn, LootItemCondition[] conditionsIn, A_2178_U[] functionsIn) {
        super(conditionsIn);
        this.G_564_y = weightIn;
        this.P_1922_E = qualityIn;
        this.u_1723_Y = functionsIn;
        this.v_4262_N = LootItemFunctions.n_1700_B(functionsIn);
    }

    @Override
    public void n_1700_B(g_1866_m p_225579_1_) {
        super.n_1700_B(p_225579_1_);
        for (int i = 0; i < this.u_1723_Y.length; ++i) {
            this.u_1723_Y[i].n_1700_B(p_225579_1_.J_1907_R(".functions[" + i + "]"));
        }
    }

    protected abstract void n_1700_B(Consumer<Z_1993_T> var1, q_1704_m var2);

    @Override
    public boolean expand(q_1704_m p_expand_1_, Consumer<LootPoolEntry> p_expand_2_) {
        if (this.n_1700_B(p_expand_1_)) {
            p_expand_2_.accept(this.w_1484_f);
            return true;
        }
        return false;
    }

    public static n_1700_B<?> n_1700_B(G_564_y entryBuilderIn) {
        return new J_1907_R(entryBuilderIn);
    }

    static class J_1907_R
    extends n_1700_B<J_1907_R> {
        private final G_564_y R_4764_Y;

        public J_1907_R(G_564_y builder) {
            this.R_4764_Y = builder;
        }

        protected J_1907_R v_4262_N() {
            return this;
        }

        @Override
        public u_1373_N J_1907_R() {
            return this.R_4764_Y.build(this.n_1700_B, this.J_1907_R, this.u_1723_Y(), this.n_1700_B());
        }

        @Override
        protected /* synthetic */ u_1373_N.n_1700_B R_4764_Y() {
            return this.v_4262_N();
        }
    }

    @FunctionalInterface
    public static interface G_564_y {
        public e_2748_L build(int var1, int var2, LootItemCondition[] var3, A_2178_U[] var4);
    }

    public static abstract class P_1922_E<T extends e_2748_L>
    extends u_1373_N.J_1907_R<T> {
        @Override
        public void n_1700_B(JsonObject object, T context, JsonSerializationContext conditions) {
            if (((e_2748_L)context).G_564_y != 1) {
                object.addProperty("weight", (Number)((e_2748_L)context).G_564_y);
            }
            if (((e_2748_L)context).P_1922_E != 0) {
                object.addProperty("quality", (Number)((e_2748_L)context).P_1922_E);
            }
            if (!ArrayUtils.isEmpty((Object[])((e_2748_L)context).u_1723_Y)) {
                object.add("functions", conditions.serialize((Object)((e_2748_L)context).u_1723_Y));
            }
        }

        public final T J_1907_R(JsonObject object, JsonDeserializationContext context, LootItemCondition[] conditions) {
            int i = i_4431_W.n_1700_B(object, "weight", 1);
            int j = i_4431_W.n_1700_B(object, "quality", 0);
            A_2178_U[] ailootfunction = i_4431_W.n_1700_B(object, "functions", new A_2178_U[0], context, A_2178_U[].class);
            return this.J_1907_R(object, context, i, j, conditions, ailootfunction);
        }

        protected abstract T J_1907_R(JsonObject var1, JsonDeserializationContext var2, int var3, int var4, LootItemCondition[] var5, A_2178_U[] var6);

        @Override
        public /* synthetic */ u_1373_N n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }

    public abstract class R_4764_Y
    implements LootPoolEntry {
        protected R_4764_Y() {
        }

        @Override
        public int n_1700_B(float luck) {
            return Math.max(u_530_F.G_564_y((float)e_2748_L.this.G_564_y + (float)e_2748_L.this.P_1922_E * luck), 0);
        }
    }

    public static abstract class n_1700_B<T extends n_1700_B<T>>
    extends u_1373_N.n_1700_B<T>
    implements Z_3128_E<T> {
        protected int n_1700_B = 1;
        protected int J_1907_R = 0;
        private final List<A_2178_U> R_4764_Y = Lists.newArrayList();

        public T J_1907_R(A_2178_U.n_1700_B functionBuilder) {
            this.R_4764_Y.add(functionBuilder.u_1723_Y());
            return (T)((n_1700_B)this.R_4764_Y());
        }

        protected A_2178_U[] n_1700_B() {
            return this.R_4764_Y.toArray(new A_2178_U[0]);
        }

        public T n_1700_B(int weightIn) {
            this.n_1700_B = weightIn;
            return (T)((n_1700_B)this.R_4764_Y());
        }

        public T J_1907_R(int qualityIn) {
            this.J_1907_R = qualityIn;
            return (T)((n_1700_B)this.R_4764_Y());
        }

        @Override
        public /* synthetic */ Object n_1700_B(A_2178_U.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }
    }
}


