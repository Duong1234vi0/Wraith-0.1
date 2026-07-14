/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSyntaxException
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Set;
import lightning.product.LootContextParams;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.K_4074_S;
import lightning.product.LootItemCondition;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.r_2687_x;
import lightning.product.LootItemConditions;

public class w_2512_g
implements LootItemCondition {
    private final T_2915_h n_1700_B;
    private final r_2687_x J_1907_R;

    private w_2512_g(T_2915_h p_i225896_1_, r_2687_x p_i225896_2_) {
        this.n_1700_B = p_i225896_1_;
        this.J_1907_R = p_i225896_2_;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.w_1484_f;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.v_4262_N);
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        K_4074_S blockstate = p_test_1_.J_1907_R(LootContextParams.v_4262_N);
        return blockstate != null && this.n_1700_B == blockstate.J_1907_R() && this.J_1907_R.n_1700_B(blockstate);
    }

    public static n_1700_B n_1700_B(T_2915_h blockIn) {
        return new n_1700_B(blockIn);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements LootItemCondition.n_1700_B {
        private final T_2915_h n_1700_B;
        private r_2687_x J_1907_R = r_2687_x.n_1700_B;

        public n_1700_B(T_2915_h blockIn) {
            this.n_1700_B = blockIn;
        }

        public n_1700_B n_1700_B(r_2687_x.n_1700_B p_227567_1_) {
            this.J_1907_R = p_227567_1_.J_1907_R();
            return this;
        }

        @Override
        public LootItemCondition build() {
            return new w_2512_g(this.n_1700_B, this.J_1907_R);
        }
    }

    public static class J_1907_R
    implements Serializer<w_2512_g> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, w_2512_g p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("block", V_3137_a.q_4610_l.J_1907_R(p_230424_2_.n_1700_B).toString());
            p_230424_1_.add("properties", p_230424_2_.J_1907_R.n_1700_B());
        }

        public w_2512_g J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(p_230423_1_, "block"));
            T_2915_h block = V_3137_a.q_4610_l.J_1907_R(resourcelocation).orElseThrow(() -> new IllegalArgumentException("Can't find block " + String.valueOf(resourcelocation)));
            r_2687_x statepropertiespredicate = r_2687_x.n_1700_B(p_230423_1_.get("properties"));
            statepropertiespredicate.n_1700_B(block.t_1786_h(), (String p_227568_1_) -> {
                throw new JsonSyntaxException("Block " + String.valueOf(block) + " has no property " + p_227568_1_);
            });
            return new w_2512_g(block, statepropertiespredicate);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


