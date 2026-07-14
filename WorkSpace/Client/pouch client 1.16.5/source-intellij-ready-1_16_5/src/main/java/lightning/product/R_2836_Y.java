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
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.d_614_w;
import lightning.product.e_2748_L;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.LootPoolEntries;

public class R_2836_Y
extends e_2748_L {
    private final q_1613_l v_4262_N;

    private R_2836_Y(q_1613_l itemIn, int weightIn, int qualityIn, LootItemCondition[] conditionsIn, A_2178_U[] functionsIn) {
        super(weightIn, qualityIn, conditionsIn, functionsIn);
        this.v_4262_N = itemIn;
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.J_1907_R;
    }

    @Override
    public void n_1700_B(Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
        stackConsumer.accept(new Z_1993_T(this.v_4262_N));
    }

    public static e_2748_L.n_1700_B<?> n_1700_B(q_1803_e itemIn) {
        return R_2836_Y.n_1700_B((int p_216169_1_, int p_216169_2_, LootItemCondition[] p_216169_3_, A_2178_U[] p_216169_4_) -> new R_2836_Y(itemIn.u_1723_Y(), p_216169_1_, p_216169_2_, p_216169_3_, p_216169_4_));
    }

    public static class n_1700_B
    extends e_2748_L.P_1922_E<R_2836_Y> {
        @Override
        public void n_1700_B(JsonObject object, R_2836_Y context, JsonSerializationContext conditions) {
            super.n_1700_B(object, context, conditions);
            g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(context.v_4262_N);
            if (resourcelocation == null) {
                throw new IllegalArgumentException("Can't serialize unknown item " + String.valueOf(context.v_4262_N));
            }
            object.addProperty("name", resourcelocation.toString());
        }

        protected R_2836_Y n_1700_B(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
            q_1613_l item = i_4431_W.v_4262_N(object, "name");
            return new R_2836_Y(item, weight, quality, conditions, functions);
        }

        @Override
        protected /* synthetic */ e_2748_L J_1907_R(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, int n, int n2, LootItemCondition[] q_2342_RArray, A_2178_U[] a_2178_UArray) {
            return this.n_1700_B(jsonObject, jsonDeserializationContext, n, n2, q_2342_RArray, a_2178_UArray);
        }
    }
}


