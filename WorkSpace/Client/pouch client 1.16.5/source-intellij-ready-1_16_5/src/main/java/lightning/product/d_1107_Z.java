/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import lightning.product.A_2178_U;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.r_4811_B;

public class d_1107_Z
extends T_3951_H {
    private final o_3393_s J_1907_R;
    private final int R_4764_Y;

    private d_1107_Z(LootItemCondition[] conditions, o_3393_s countIn, int limitIn) {
        super(conditions);
        this.J_1907_R = countIn;
        this.R_4764_Y = limitIn;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.v_4262_N;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.G_564_y);
    }

    private boolean R_4764_Y() {
        return this.R_4764_Y > 0;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        N_4263_v entity = context.J_1907_R(LootContextParams.G_564_y);
        if (entity instanceof r_4811_B) {
            int i = K_4096_w.v_4262_N((r_4811_B)entity);
            if (i == 0) {
                return stack;
            }
            float f = (float)i * this.J_1907_R.J_1907_R(context.n_1700_B());
            stack.u_1723_Y(Math.round(f));
            if (this.R_4764_Y() && stack.t_4043_B() > this.R_4764_Y) {
                stack.P_1922_E(this.R_4764_Y);
            }
        }
        return stack;
    }

    public static n_1700_B n_1700_B(o_3393_s range) {
        return new n_1700_B(range);
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final o_3393_s n_1700_B;
        private int J_1907_R = 0;

        public n_1700_B(o_3393_s p_i50932_1_) {
            this.n_1700_B = p_i50932_1_;
        }

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B n_1700_B(int p_216072_1_) {
            this.J_1907_R = p_216072_1_;
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new d_1107_Z(this.R_4764_Y(), this.n_1700_B, this.J_1907_R);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<d_1107_Z> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, d_1107_Z p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("count", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
            if (p_230424_2_.R_4764_Y()) {
                p_230424_1_.add("limit", p_230424_3_.serialize((Object)p_230424_2_.R_4764_Y));
            }
        }

        public d_1107_Z J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            int i = i_4431_W.n_1700_B(object, "limit", 0);
            return new d_1107_Z(conditionsIn, i_4431_W.n_1700_B(object, "count", deserializationContext, o_3393_s.class), i);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


