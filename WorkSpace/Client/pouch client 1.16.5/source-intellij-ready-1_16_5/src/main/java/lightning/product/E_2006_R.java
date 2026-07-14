/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import lightning.product.LootContextParams;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.LootItemCondition;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class E_2006_R
implements LootItemCondition {
    private final K_1310_v n_1700_B;
    private final float[] J_1907_R;

    private E_2006_R(K_1310_v enchantment, float[] chances) {
        this.n_1700_B = enchantment;
        this.J_1907_R = chances;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.s_956_w;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.t_148_a);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        Z_1993_T itemstack = p_test_1_.J_1907_R(LootContextParams.t_148_a);
        int i = itemstack != null ? K_4096_w.n_1700_B(this.n_1700_B, itemstack) : 0;
        float f = this.J_1907_R[Math.min(i, this.J_1907_R.length - 1)];
        return p_test_1_.n_1700_B().nextFloat() < f;
    }

    public static LootItemCondition.n_1700_B n_1700_B(K_1310_v enchantmentIn, float ... chancesIn) {
        return () -> new E_2006_R(enchantmentIn, chancesIn);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<E_2006_R> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, E_2006_R p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("enchantment", V_3137_a.z_4693_k.J_1907_R(p_230424_2_.n_1700_B).toString());
            p_230424_1_.add("chances", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public E_2006_R J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(p_230423_1_, "enchantment"));
            K_1310_v enchantment = V_3137_a.z_4693_k.J_1907_R(resourcelocation).orElseThrow(() -> new JsonParseException("Invalid enchantment id: " + String.valueOf(resourcelocation)));
            float[] afloat = i_4431_W.n_1700_B(p_230423_1_, "chances", p_230423_2_, float[].class);
            return new E_2006_R(enchantment, afloat);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


