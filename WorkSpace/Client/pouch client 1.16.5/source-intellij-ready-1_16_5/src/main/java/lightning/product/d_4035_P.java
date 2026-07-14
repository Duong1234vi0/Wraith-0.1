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
import java.util.Random;
import lightning.product.A_2178_U;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.K_4096_w;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Y_4400_R;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;
import lightning.product.q_1704_m;

public class d_4035_P
extends T_3951_H {
    private final RandomIntGenerator J_1907_R;
    private final boolean R_4764_Y;

    private d_4035_P(LootItemCondition[] p_i51236_1_, RandomIntGenerator p_i51236_2_, boolean p_i51236_3_) {
        super(p_i51236_1_);
        this.J_1907_R = p_i51236_2_;
        this.R_4764_Y = p_i51236_3_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.R_4764_Y;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Random random = context.n_1700_B();
        return K_4096_w.n_1700_B(random, stack, this.J_1907_R.n_1700_B(random), this.R_4764_Y);
    }

    public static n_1700_B n_1700_B(RandomIntGenerator p_215895_0_) {
        return new n_1700_B(p_215895_0_);
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private final RandomIntGenerator n_1700_B;
        private boolean J_1907_R;

        public n_1700_B(RandomIntGenerator p_i51494_1_) {
            this.n_1700_B = p_i51494_1_;
        }

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B v_4262_N() {
            this.J_1907_R = true;
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new d_4035_P(this.R_4764_Y(), this.n_1700_B, this.J_1907_R);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<d_4035_P> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, d_4035_P p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.add("levels", Y_4400_R.n_1700_B(p_230424_2_.J_1907_R, p_230424_3_));
            p_230424_1_.addProperty("treasure", Boolean.valueOf(p_230424_2_.R_4764_Y));
        }

        public d_4035_P J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            RandomIntGenerator irandomrange = Y_4400_R.n_1700_B(object.get("levels"), deserializationContext);
            boolean flag = i_4431_W.n_1700_B(object, "treasure", false);
            return new d_4035_P(conditionsIn, irandomrange, flag);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


