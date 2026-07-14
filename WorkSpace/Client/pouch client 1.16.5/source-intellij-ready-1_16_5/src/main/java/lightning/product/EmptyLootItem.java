/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.function.Consumer;
import lightning.product.A_2178_U;
import lightning.product.LootItemCondition;
import lightning.product.Z_1993_T;
import lightning.product.d_614_w;
import lightning.product.e_2748_L;
import lightning.product.q_1704_m;
import lightning.product.LootPoolEntries;

public class EmptyLootItem
extends e_2748_L {
    private EmptyLootItem(int p_i51258_1_, int p_i51258_2_, LootItemCondition[] p_i51258_3_, A_2178_U[] p_i51258_4_) {
        super(p_i51258_1_, p_i51258_2_, p_i51258_3_, p_i51258_4_);
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.n_1700_B;
    }

    @Override
    public void n_1700_B(Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
    }

    public static e_2748_L.n_1700_B<?> J_1907_R() {
        return EmptyLootItem.n_1700_B(EmptyLootItem::new);
    }

    public static class n_1700_B
    extends e_2748_L.P_1922_E<EmptyLootItem> {
        public EmptyLootItem n_1700_B(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
            return new EmptyLootItem(weight, quality, conditions, functions);
        }

        @Override
        public /* synthetic */ e_2748_L J_1907_R(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, int n, int n2, LootItemCondition[] q_2342_RArray, A_2178_U[] a_2178_UArray) {
            return this.n_1700_B(jsonObject, jsonDeserializationContext, n, n2, q_2342_RArray, a_2178_UArray);
        }
    }
}


