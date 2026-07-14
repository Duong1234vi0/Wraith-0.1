/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSyntaxException
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lightning.product.A_2178_U;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.K_1310_v;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.T_4041_i;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.EnchantedBookItem;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b_4643_Y
extends T_3951_H {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final List<K_1310_v> R_4764_Y;

    private b_4643_Y(LootItemCondition[] p_i51238_1_, Collection<K_1310_v> p_i51238_2_) {
        super(p_i51238_1_);
        this.R_4764_Y = ImmutableList.copyOf(p_i51238_2_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.G_564_y;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        K_1310_v enchantment;
        Random random = context.n_1700_B();
        if (this.R_4764_Y.isEmpty()) {
            boolean flag = stack.J_1907_R() == Items.K_4237_u;
            List list = V_3137_a.z_4693_k.u_1723_Y().filter(K_1310_v::t_148_a).filter(p_237421_2_ -> flag || p_237421_2_.n_1700_B(stack)).collect(Collectors.toList());
            if (list.isEmpty()) {
                J_1907_R.warn("Couldn't find a compatible enchantment for {}", (Object)stack);
                return stack;
            }
            enchantment = (K_1310_v)list.get(random.nextInt(list.size()));
        } else {
            enchantment = this.R_4764_Y.get(random.nextInt(this.R_4764_Y.size()));
        }
        return b_4643_Y.n_1700_B(stack, enchantment, random);
    }

    private static Z_1993_T n_1700_B(Z_1993_T p_237420_0_, K_1310_v p_237420_1_, Random p_237420_2_) {
        int i = u_530_F.n_1700_B(p_237420_2_, p_237420_1_.P_1922_E(), p_237420_1_.n_1700_B());
        if (p_237420_0_.J_1907_R() == Items.K_4237_u) {
            p_237420_0_ = new Z_1993_T(Items.M_4472_P);
            EnchantedBookItem.n_1700_B(p_237420_0_, new T_4041_i(p_237420_1_, i));
        } else {
            p_237420_0_.n_1700_B(p_237420_1_, i);
        }
        return p_237420_0_;
    }

    public static T_3951_H.n_1700_B<?> R_4764_Y() {
        return b_4643_Y.n_1700_B(p_237422_0_ -> new b_4643_Y((LootItemCondition[])p_237422_0_, (Collection<K_1310_v>)ImmutableList.of()));
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<b_4643_Y> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, b_4643_Y p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            if (!p_230424_2_.R_4764_Y.isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                for (K_1310_v enchantment : p_230424_2_.R_4764_Y) {
                    g_2336_b resourcelocation = V_3137_a.z_4693_k.J_1907_R(enchantment);
                    if (resourcelocation == null) {
                        throw new IllegalArgumentException("Don't know how to serialize enchantment " + String.valueOf(enchantment));
                    }
                    jsonarray.add((JsonElement)new JsonPrimitive(resourcelocation.toString()));
                }
                p_230424_1_.add("enchantments", (JsonElement)jsonarray);
            }
        }

        public b_4643_Y J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            ArrayList list = Lists.newArrayList();
            if (object.has("enchantments")) {
                for (JsonElement jsonelement : i_4431_W.P_4830_p(object, "enchantments")) {
                    String s = i_4431_W.n_1700_B(jsonelement, "enchantment");
                    K_1310_v enchantment = V_3137_a.z_4693_k.J_1907_R(new g_2336_b(s)).orElseThrow(() -> new JsonSyntaxException("Unknown enchantment '" + s + "'"));
                    list.add(enchantment);
                }
            }
            return new b_4643_Y(conditionsIn, list);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final Set<K_1310_v> n_1700_B = Sets.newHashSet();

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B n_1700_B(K_1310_v p_237424_1_) {
            this.n_1700_B.add(p_237424_1_);
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new b_4643_Y(this.R_4764_Y(), this.n_1700_B);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }
}


