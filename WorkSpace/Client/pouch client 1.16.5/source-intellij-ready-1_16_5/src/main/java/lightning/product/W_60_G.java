/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.function.Consumer;
import lightning.product.A_2178_U;
import lightning.product.LootPoolEntry;
import lightning.product.LootItemCondition;
import lightning.product.SerializationTags;
import lightning.product.Z_1993_T;
import lightning.product.d_614_w;
import lightning.product.e_2748_L;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.r_109_r;
import lightning.product.LootPoolEntries;

public class W_60_G
extends e_2748_L {
    private final r_109_r<q_1613_l> v_4262_N;
    private final boolean w_1484_f;

    private W_60_G(r_109_r<q_1613_l> tag, boolean expand, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
        super(weight, quality, conditions, functions);
        this.v_4262_N = tag;
        this.w_1484_f = expand;
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.P_1922_E;
    }

    @Override
    public void n_1700_B(Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
        this.v_4262_N.n_1700_B().forEach(item -> stackConsumer.accept(new Z_1993_T((q_1803_e)item)));
    }

    private boolean n_1700_B(q_1704_m context, Consumer<LootPoolEntry> generatorConsumer) {
        if (!this.n_1700_B(context)) {
            return false;
        }
        for (final q_1613_l item : this.v_4262_N.n_1700_B()) {
            generatorConsumer.accept(new e_2748_L.R_4764_Y(this){

                @Override
                public void n_1700_B(Consumer<Z_1993_T> p_216188_1_, q_1704_m p_216188_2_) {
                    p_216188_1_.accept(new Z_1993_T(item));
                }
            });
        }
        return true;
    }

    @Override
    public boolean expand(q_1704_m p_expand_1_, Consumer<LootPoolEntry> p_expand_2_) {
        return this.w_1484_f ? this.n_1700_B(p_expand_1_, p_expand_2_) : super.expand(p_expand_1_, p_expand_2_);
    }

    public static e_2748_L.n_1700_B<?> n_1700_B(r_109_r<q_1613_l> tag) {
        return W_60_G.n_1700_B((int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) -> new W_60_G(tag, true, weight, quality, conditions, functions));
    }

    public static class n_1700_B
    extends e_2748_L.P_1922_E<W_60_G> {
        @Override
        public void n_1700_B(JsonObject object, W_60_G context, JsonSerializationContext conditions) {
            super.n_1700_B(object, context, conditions);
            object.addProperty("name", SerializationTags.n_1700_B().J_1907_R().J_1907_R(context.v_4262_N).toString());
            object.addProperty("expand", Boolean.valueOf(context.w_1484_f));
        }

        protected W_60_G n_1700_B(JsonObject object, JsonDeserializationContext context, int weight, int quality, LootItemCondition[] conditions, A_2178_U[] functions) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "name"));
            r_109_r<q_1613_l> itag = SerializationTags.n_1700_B().J_1907_R().n_1700_B(resourcelocation);
            if (itag == null) {
                throw new JsonParseException("Can't find tag: " + String.valueOf(resourcelocation));
            }
            boolean flag = i_4431_W.w_1484_f(object, "expand");
            return new W_60_G(itag, flag, weight, quality, conditions, functions);
        }

        @Override
        protected /* synthetic */ e_2748_L J_1907_R(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, int n, int n2, LootItemCondition[] q_2342_RArray, A_2178_U[] a_2178_UArray) {
            return this.n_1700_B(jsonObject, jsonDeserializationContext, n, n2, q_2342_RArray, a_2178_UArray);
        }
    }
}


