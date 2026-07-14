/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;

public class m_4962_f
extends T_3951_H {
    private static final Map<g_2336_b, R_4764_Y> J_1907_R = Maps.newHashMap();
    private final K_1310_v R_4764_Y;
    private final J_1907_R G_564_y;

    private m_4962_f(LootItemCondition[] conditionsIn, K_1310_v enchantmentIn, J_1907_R p_i51246_3_) {
        super(conditionsIn);
        this.R_4764_Y = enchantmentIn;
        this.G_564_y = p_i51246_3_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.M_182_A;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.t_148_a);
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Z_1993_T itemstack = context.J_1907_R(LootContextParams.t_148_a);
        if (itemstack != null) {
            int i = K_4096_w.n_1700_B(this.R_4764_Y, itemstack);
            int j = this.G_564_y.n_1700_B(context.n_1700_B(), stack.t_4043_B(), i);
            stack.P_1922_E(j);
        }
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(K_1310_v p_215870_0_, float p_215870_1_, int p_215870_2_) {
        return m_4962_f.n_1700_B((LootItemCondition[] p_215864_3_) -> new m_4962_f((LootItemCondition[])p_215864_3_, p_215870_0_, new n_1700_B(p_215870_2_, p_215870_1_)));
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(K_1310_v p_215869_0_) {
        return m_4962_f.n_1700_B((LootItemCondition[] p_215866_1_) -> new m_4962_f((LootItemCondition[])p_215866_1_, p_215869_0_, new G_564_y()));
    }

    public static T_3951_H.n_1700_B<?> J_1907_R(K_1310_v p_215871_0_) {
        return m_4962_f.n_1700_B((LootItemCondition[] p_215872_1_) -> new m_4962_f((LootItemCondition[])p_215872_1_, p_215871_0_, new u_1723_Y(1)));
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(K_1310_v p_215865_0_, int p_215865_1_) {
        return m_4962_f.n_1700_B((LootItemCondition[] p_215868_2_) -> new m_4962_f((LootItemCondition[])p_215868_2_, p_215865_0_, new u_1723_Y(p_215865_1_)));
    }

    static {
        J_1907_R.put(lightning.product.m_4962_f$n_1700_B.n_1700_B, n_1700_B::n_1700_B);
        J_1907_R.put(lightning.product.m_4962_f$G_564_y.n_1700_B, G_564_y::n_1700_B);
        J_1907_R.put(u_1723_Y.n_1700_B, u_1723_Y::n_1700_B);
    }

    static interface J_1907_R {
        public int n_1700_B(Random var1, int var2, int var3);

        public void n_1700_B(JsonObject var1, JsonSerializationContext var2);

        public g_2336_b n_1700_B();
    }

    static final class u_1723_Y
    implements J_1907_R {
        public static final g_2336_b n_1700_B = new g_2336_b("uniform_bonus_count");
        private final int J_1907_R;

        public u_1723_Y(int bonusMultiplier) {
            this.J_1907_R = bonusMultiplier;
        }

        @Override
        public int n_1700_B(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
            return p_216204_2_ + p_216204_1_.nextInt(this.J_1907_R * p_216204_3_ + 1);
        }

        @Override
        public void n_1700_B(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
            p_216202_1_.addProperty("bonusMultiplier", (Number)this.J_1907_R);
        }

        public static J_1907_R n_1700_B(JsonObject p_216207_0_, JsonDeserializationContext p_216207_1_) {
            int i = i_4431_W.u_2550_I(p_216207_0_, "bonusMultiplier");
            return new u_1723_Y(i);
        }

        @Override
        public g_2336_b n_1700_B() {
            return n_1700_B;
        }
    }

    static final class G_564_y
    implements J_1907_R {
        public static final g_2336_b n_1700_B = new g_2336_b("ore_drops");

        private G_564_y() {
        }

        @Override
        public int n_1700_B(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
            if (p_216204_3_ > 0) {
                int i = p_216204_1_.nextInt(p_216204_3_ + 2) - 1;
                if (i < 0) {
                    i = 0;
                }
                return p_216204_2_ * (i + 1);
            }
            return p_216204_2_;
        }

        @Override
        public void n_1700_B(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
        }

        public static J_1907_R n_1700_B(JsonObject p_216205_0_, JsonDeserializationContext p_216205_1_) {
            return new G_564_y();
        }

        @Override
        public g_2336_b n_1700_B() {
            return n_1700_B;
        }
    }

    static final class n_1700_B
    implements J_1907_R {
        public static final g_2336_b n_1700_B = new g_2336_b("binomial_with_bonus_count");
        private final int J_1907_R;
        private final float R_4764_Y;

        public n_1700_B(int extra, float probability) {
            this.J_1907_R = extra;
            this.R_4764_Y = probability;
        }

        @Override
        public int n_1700_B(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
            for (int i = 0; i < p_216204_3_ + this.J_1907_R; ++i) {
                if (!(p_216204_1_.nextFloat() < this.R_4764_Y)) continue;
                ++p_216204_2_;
            }
            return p_216204_2_;
        }

        @Override
        public void n_1700_B(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
            p_216202_1_.addProperty("extra", (Number)this.J_1907_R);
            p_216202_1_.addProperty("probability", (Number)Float.valueOf(this.R_4764_Y));
        }

        public static J_1907_R n_1700_B(JsonObject p_216210_0_, JsonDeserializationContext p_216210_1_) {
            int i = i_4431_W.u_2550_I(p_216210_0_, "extra");
            float f = i_4431_W.t_148_a(p_216210_0_, "probability");
            return new n_1700_B(i, f);
        }

        @Override
        public g_2336_b n_1700_B() {
            return n_1700_B;
        }
    }

    static interface R_4764_Y {
        public J_1907_R deserialize(JsonObject var1, JsonDeserializationContext var2);
    }

    public static class P_1922_E
    extends T_3951_H.J_1907_R<m_4962_f> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, m_4962_f p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("enchantment", V_3137_a.z_4693_k.J_1907_R(p_230424_2_.R_4764_Y).toString());
            p_230424_1_.addProperty("formula", p_230424_2_.G_564_y.n_1700_B().toString());
            JsonObject jsonobject = new JsonObject();
            p_230424_2_.G_564_y.n_1700_B(jsonobject, p_230424_3_);
            if (jsonobject.size() > 0) {
                p_230424_1_.add("parameters", (JsonElement)jsonobject);
            }
        }

        public m_4962_f J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "enchantment"));
            K_1310_v enchantment = V_3137_a.z_4693_k.J_1907_R(resourcelocation).orElseThrow(() -> new JsonParseException("Invalid enchantment id: " + String.valueOf(resourcelocation)));
            g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(object, "formula"));
            R_4764_Y applybonus$iformuladeserializer = J_1907_R.get(resourcelocation1);
            if (applybonus$iformuladeserializer == null) {
                throw new JsonParseException("Invalid formula id: " + String.valueOf(resourcelocation1));
            }
            J_1907_R applybonus$iformula = object.has("parameters") ? applybonus$iformuladeserializer.deserialize(i_4431_W.M_588_G(object, "parameters"), deserializationContext) : applybonus$iformuladeserializer.deserialize(new JsonObject(), deserializationContext);
            return new m_4962_f(conditionsIn, enchantment, applybonus$iformula);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


