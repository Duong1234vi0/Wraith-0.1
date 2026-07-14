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
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.LootPoolEntries;

public class v_2678_c
extends e_2748_L {
    private final g_2336_b v_4262_N;

    private v_2678_c(g_2336_b p_i51260_1_, int p_i51260_2_, int p_i51260_3_, LootItemCondition[] p_i51260_4_, A_2178_U[] p_i51260_5_) {
        super(p_i51260_2_, p_i51260_3_, p_i51260_4_, p_i51260_5_);
        this.v_4262_N = p_i51260_1_;
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.G_564_y;
    }

    @Override
    public void n_1700_B(Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
        context.n_1700_B(this.v_4262_N, stackConsumer);
    }

    public static e_2748_L.n_1700_B<?> n_1700_B(g_2336_b p_216162_0_) {
        return v_2678_c.n_1700_B((int p_216164_1_, int p_216164_2_, LootItemCondition[] p_216164_3_, A_2178_U[] p_216164_4_) -> new v_2678_c(p_216162_0_, p_216164_1_, p_216164_2_, p_216164_3_, p_216164_4_));
    }

    public static class n_1700_B
    extends e_2748_L.P_1922_E<v_2678_c> {
        @Override
        public void n_1700_B(JsonObject object, v_2678_c context, JsonSerializationContext conditions) {
            super.n_1700_B(object, context, conditions);
            object.addProperty("name", context.v_4262_N.toString());
        }

        protected v_2678_c n_1700_B(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "name"));
            return new v_2678_c(resourcelocation, weight, quality, conditions, functions);
        }

        @Override
        protected /* synthetic */ e_2748_L J_1907_R(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, int n, int n2, LootItemCondition[] q_2342_RArray, A_2178_U[] a_2178_UArray) {
            return this.n_1700_B(jsonObject, jsonDeserializationContext, n, n2, q_2342_RArray, a_2178_UArray);
        }
    }
}


