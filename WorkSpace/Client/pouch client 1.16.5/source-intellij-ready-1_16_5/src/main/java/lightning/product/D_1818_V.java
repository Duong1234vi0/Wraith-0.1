/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSyntaxException
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lightning.product.A_2178_U;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.SuspiciousStewItem;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.i_4431_W;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.Items;

public class D_1818_V
extends T_3951_H {
    private final Map<g_422_i, o_3393_s> J_1907_R;

    private D_1818_V(LootItemCondition[] p_i51215_1_, Map<g_422_i, o_3393_s> p_i51215_2_) {
        super(p_i51215_1_);
        this.J_1907_R = ImmutableMap.copyOf(p_i51215_2_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.M_588_G;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        if (stack.J_1907_R() == Items.Q_2342_H && !this.J_1907_R.isEmpty()) {
            Random random = context.n_1700_B();
            int i = random.nextInt(this.J_1907_R.size());
            Map.Entry entry = (Map.Entry)Iterables.get(this.J_1907_R.entrySet(), (int)i);
            g_422_i effect = (g_422_i)entry.getKey();
            int j = ((o_3393_s)entry.getValue()).n_1700_B(random);
            if (!effect.n_1700_B()) {
                j *= 20;
            }
            SuspiciousStewItem.n_1700_B(stack, effect, j);
            return stack;
        }
        return stack;
    }

    public static n_1700_B R_4764_Y() {
        return new n_1700_B();
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final Map<g_422_i, o_3393_s> n_1700_B = Maps.newHashMap();

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B n_1700_B(g_422_i p_216077_1_, o_3393_s p_216077_2_) {
            this.n_1700_B.put(p_216077_1_, p_216077_2_);
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new D_1818_V(this.R_4764_Y(), this.n_1700_B);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<D_1818_V> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, D_1818_V p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            if (!p_230424_2_.J_1907_R.isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                for (g_422_i effect : p_230424_2_.J_1907_R.keySet()) {
                    JsonObject jsonobject = new JsonObject();
                    g_2336_b resourcelocation = V_3137_a.T_2506_i.J_1907_R(effect);
                    if (resourcelocation == null) {
                        throw new IllegalArgumentException("Don't know how to serialize mob effect " + String.valueOf(effect));
                    }
                    jsonobject.add("type", (JsonElement)new JsonPrimitive(resourcelocation.toString()));
                    jsonobject.add("duration", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R.get(effect)));
                    jsonarray.add((JsonElement)jsonobject);
                }
                p_230424_1_.add("effects", (JsonElement)jsonarray);
            }
        }

        public D_1818_V J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            HashMap map = Maps.newHashMap();
            if (object.has("effects")) {
                for (JsonElement jsonelement : i_4431_W.P_4830_p(object, "effects")) {
                    String s = i_4431_W.u_1723_Y(jsonelement.getAsJsonObject(), "type");
                    g_422_i effect = V_3137_a.T_2506_i.J_1907_R(new g_2336_b(s)).orElseThrow(() -> new JsonSyntaxException("Unknown mob effect '" + s + "'"));
                    o_3393_s randomvaluerange = i_4431_W.n_1700_B(jsonelement.getAsJsonObject(), "duration", deserializationContext, o_3393_s.class);
                    map.put(effect, randomvaluerange);
                }
            }
            return new D_1818_V(conditionsIn, map);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


