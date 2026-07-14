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
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.Nameable;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;

public class d_150_Y
extends T_3951_H {
    private final J_1907_R J_1907_R;

    private d_150_Y(LootItemCondition[] conditionsIn, J_1907_R sourceIn) {
        super(conditionsIn);
        this.J_1907_R = sourceIn;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.P_4830_p;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(this.J_1907_R.u_1723_Y);
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Nameable inameable;
        Object object = context.J_1907_R(this.J_1907_R.u_1723_Y);
        if (object instanceof Nameable && (inameable = (Nameable)object).t_3452_g()) {
            stack.n_1700_B(inameable.c_());
        }
        return stack;
    }

    public static T_3951_H.n_1700_B<?> n_1700_B(J_1907_R sourceIn) {
        return d_150_Y.n_1700_B((LootItemCondition[] p_215891_1_) -> new d_150_Y((LootItemCondition[])p_215891_1_, sourceIn));
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R("this", LootContextParams.n_1700_B);
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R("killer", LootContextParams.G_564_y);
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R("killer_player", LootContextParams.J_1907_R);
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R("block_entity", LootContextParams.w_1484_f);
        public final String P_1922_E;
        public final I_2011_f<?> u_1723_Y;
        private static final /* synthetic */ J_1907_R[] v_4262_N;

        public static J_1907_R[] values() {
            return (J_1907_R[])v_4262_N.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(String nameIn, I_2011_f<?> parameterIn) {
            this.P_1922_E = nameIn;
            this.u_1723_Y = parameterIn;
        }

        public static J_1907_R n_1700_B(String nameIn) {
            for (J_1907_R copyname$source : lightning.product.d_150_Y$J_1907_R.values()) {
                if (!copyname$source.P_1922_E.equals(nameIn)) continue;
                return copyname$source;
            }
            throw new IllegalArgumentException("Invalid name source " + nameIn);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.d_150_Y$J_1907_R.n_1700_B();
        }
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<d_150_Y> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, d_150_Y p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("source", p_230424_2_.J_1907_R.P_1922_E);
        }

        public d_150_Y J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            J_1907_R copyname$source = lightning.product.d_150_Y$J_1907_R.n_1700_B(i_4431_W.u_1723_Y(object, "source"));
            return new d_150_Y(conditionsIn, copyname$source);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


