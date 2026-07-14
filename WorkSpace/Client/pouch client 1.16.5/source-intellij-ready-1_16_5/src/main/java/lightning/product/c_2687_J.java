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
import lightning.product.A_2178_U;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.d_614_w;
import lightning.product.e_2748_L;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.LootPoolEntries;

public class c_2687_J
extends e_2748_L {
    private final g_2336_b v_4262_N;

    private c_2687_J(g_2336_b tableIn, int weightIn, int qualityIn, LootItemCondition[] conditionsIn, A_2178_U[] functionsIn) {
        super(weightIn, qualityIn, conditionsIn, functionsIn);
        this.v_4262_N = tableIn;
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.R_4764_Y;
    }

    @Override
    public void n_1700_B(Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
        p_4985_U loottable = context.n_1700_B(this.v_4262_N);
        loottable.n_1700_B(context, stackConsumer);
    }

    @Override
    public void n_1700_B(g_1866_m p_225579_1_) {
        if (p_225579_1_.n_1700_B(this.v_4262_N)) {
            p_225579_1_.n_1700_B("Table " + String.valueOf(this.v_4262_N) + " is recursively called");
        } else {
            super.n_1700_B(p_225579_1_);
            p_4985_U loottable = p_225579_1_.R_4764_Y(this.v_4262_N);
            if (loottable == null) {
                p_225579_1_.n_1700_B("Unknown loot table called " + String.valueOf(this.v_4262_N));
            } else {
                loottable.n_1700_B(p_225579_1_.n_1700_B("->{" + String.valueOf(this.v_4262_N) + "}", this.v_4262_N));
            }
        }
    }

    public static e_2748_L.n_1700_B<?> n_1700_B(g_2336_b tableIn) {
        return c_2687_J.n_1700_B((int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) -> new c_2687_J(tableIn, weight, quality, conditions, functions));
    }

    public static class n_1700_B
    extends e_2748_L.P_1922_E<c_2687_J> {
        @Override
        public void n_1700_B(JsonObject object, c_2687_J context, JsonSerializationContext conditions) {
            super.n_1700_B(object, context, conditions);
            object.addProperty("name", context.v_4262_N.toString());
        }

        protected c_2687_J n_1700_B(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "name"));
            return new c_2687_J(resourcelocation, weight, quality, conditions, functions);
        }

        @Override
        protected /* synthetic */ e_2748_L J_1907_R(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, int n, int n2, LootItemCondition[] q_2342_RArray, A_2178_U[] a_2178_UArray) {
            return this.n_1700_B(jsonObject, jsonDeserializationContext, n, n2, q_2342_RArray, a_2178_UArray);
        }
    }
}


