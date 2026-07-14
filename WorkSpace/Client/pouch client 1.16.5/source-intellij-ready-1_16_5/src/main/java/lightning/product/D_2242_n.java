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
import java.util.function.Consumer;
import lightning.product.LootPoolEntry;
import lightning.product.LootItemCondition;
import lightning.product.g_1866_m;
import lightning.product.ComposableEntryContainer;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.u_1373_N;

public abstract class D_2242_n
extends u_1373_N {
    protected final u_1373_N[] G_564_y;
    private final ComposableEntryContainer P_1922_E;

    protected D_2242_n(u_1373_N[] entries, LootItemCondition[] conditions) {
        super(conditions);
        this.G_564_y = entries;
        this.P_1922_E = this.n_1700_B(entries);
    }

    @Override
    public void n_1700_B(g_1866_m p_225579_1_) {
        super.n_1700_B(p_225579_1_);
        if (this.G_564_y.length == 0) {
            p_225579_1_.n_1700_B("Empty children list");
        }
        for (int i = 0; i < this.G_564_y.length; ++i) {
            this.G_564_y[i].n_1700_B(p_225579_1_.J_1907_R(".entry[" + i + "]"));
        }
    }

    protected abstract ComposableEntryContainer n_1700_B(ComposableEntryContainer[] var1);

    @Override
    public final boolean expand(q_1704_m p_expand_1_, Consumer<LootPoolEntry> p_expand_2_) {
        return !this.n_1700_B(p_expand_1_) ? false : this.P_1922_E.expand(p_expand_1_, p_expand_2_);
    }

    public static <T extends D_2242_n> u_1373_N.J_1907_R<T> n_1700_B(final n_1700_B<T> factory) {
        return new u_1373_N.J_1907_R<T>(){

            @Override
            public void n_1700_B(JsonObject object, T context, JsonSerializationContext conditions) {
                object.add("children", conditions.serialize((Object)((D_2242_n)context).G_564_y));
            }

            public final T J_1907_R(JsonObject object, JsonDeserializationContext context, LootItemCondition[] conditions) {
                u_1373_N[] alootentry = i_4431_W.n_1700_B(object, "children", context, u_1373_N[].class);
                return factory.create(alootentry, conditions);
            }

            @Override
            public /* synthetic */ u_1373_N n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
                return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
            }
        };
    }

    @FunctionalInterface
    public static interface n_1700_B<T extends D_2242_n> {
        public T create(u_1373_N[] var1, LootItemCondition[] var2);
    }
}


