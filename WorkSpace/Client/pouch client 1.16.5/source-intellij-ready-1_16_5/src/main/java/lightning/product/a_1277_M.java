/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Locale;
import java.util.Set;
import lightning.product.A_2178_U;
import lightning.product.LootContextParams;
import lightning.product.B_4977_Y;
import lightning.product.F_2904_S;
import lightning.product.F_3620_e;
import lightning.product.StructureFeature;
import lightning.product.G_3165_y;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.J_2020_G;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.Z_1993_T;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class a_1277_M
extends T_3951_H {
    private static final Logger G_564_y = LogManager.getLogger();
    public static final StructureFeature<?> J_1907_R = StructureFeature.M_182_A;
    public static final J_2020_G.n_1700_B R_4764_Y = J_2020_G.n_1700_B.t_148_a;
    private final StructureFeature<?> P_1922_E;
    private final J_2020_G.n_1700_B u_1723_Y;
    private final byte v_4262_N;
    private final int w_1484_f;
    private final boolean t_148_a;

    private a_1277_M(LootItemCondition[] p_i232169_1_, StructureFeature<?> p_i232169_2_, J_2020_G.n_1700_B p_i232169_3_, byte p_i232169_4_, int p_i232169_5_, boolean p_i232169_6_) {
        super(p_i232169_1_);
        this.P_1922_E = p_i232169_2_;
        this.u_1723_Y = p_i232169_3_;
        this.v_4262_N = p_i232169_4_;
        this.w_1484_f = p_i232169_5_;
        this.t_148_a = p_i232169_6_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.u_2550_I;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(LootContextParams.u_1723_Y);
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        e_3591_l serverworld;
        c_1514_x blockpos;
        if (stack.J_1907_R() != Items.S_1431_H) {
            return stack;
        }
        e_2866_D vector3d = context.J_1907_R(LootContextParams.u_1723_Y);
        if (vector3d != null && (blockpos = (serverworld = context.R_4764_Y()).n_1700_B(this.P_1922_E, new c_1514_x(vector3d), this.w_1484_f, this.t_148_a)) != null) {
            Z_1993_T itemstack = G_3165_y.n_1700_B(serverworld, blockpos.getX(), blockpos.getZ(), this.v_4262_N, true, true);
            G_3165_y.n_1700_B(serverworld, itemstack);
            F_3620_e.n_1700_B(itemstack, blockpos, "+", this.u_1723_Y);
            itemstack.n_1700_B(new F_2904_S("filled_map." + this.P_1922_E.v_4262_N().toLowerCase(Locale.ROOT)));
            return itemstack;
        }
        return stack;
    }

    public static n_1700_B R_4764_Y() {
        return new n_1700_B();
    }

    public static class n_1700_B
    extends T_3951_H.n_1700_B<n_1700_B> {
        private StructureFeature<?> n_1700_B = J_1907_R;
        private J_2020_G.n_1700_B J_1907_R = R_4764_Y;
        private byte R_4764_Y = (byte)2;
        private int G_564_y = 50;
        private boolean P_1922_E = true;

        protected n_1700_B P_1922_E() {
            return this;
        }

        public n_1700_B n_1700_B(StructureFeature<?> p_237427_1_) {
            this.n_1700_B = p_237427_1_;
            return this;
        }

        public n_1700_B n_1700_B(J_2020_G.n_1700_B p_216064_1_) {
            this.J_1907_R = p_216064_1_;
            return this;
        }

        public n_1700_B n_1700_B(byte p_216062_1_) {
            this.R_4764_Y = p_216062_1_;
            return this;
        }

        public n_1700_B n_1700_B(boolean p_216063_1_) {
            this.P_1922_E = p_216063_1_;
            return this;
        }

        @Override
        public A_2178_U u_1723_Y() {
            return new a_1277_M(this.R_4764_Y(), this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }

        @Override
        protected /* synthetic */ T_3951_H.n_1700_B J_1907_R() {
            return this.P_1922_E();
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<a_1277_M> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, a_1277_M p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            if (!p_230424_2_.P_1922_E.equals(J_1907_R)) {
                p_230424_1_.add("destination", p_230424_3_.serialize((Object)p_230424_2_.P_1922_E.v_4262_N()));
            }
            if (p_230424_2_.u_1723_Y != R_4764_Y) {
                p_230424_1_.add("decoration", p_230424_3_.serialize((Object)p_230424_2_.u_1723_Y.toString().toLowerCase(Locale.ROOT)));
            }
            if (p_230424_2_.v_4262_N != 2) {
                p_230424_1_.addProperty("zoom", (Number)p_230424_2_.v_4262_N);
            }
            if (p_230424_2_.w_1484_f != 50) {
                p_230424_1_.addProperty("search_radius", (Number)p_230424_2_.w_1484_f);
            }
            if (!p_230424_2_.t_148_a) {
                p_230424_1_.addProperty("skip_existing_chunks", Boolean.valueOf(p_230424_2_.t_148_a));
            }
        }

        public a_1277_M J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            StructureFeature<?> structure = lightning.product.a_1277_M$J_1907_R.n_1700_B(object);
            String s = object.has("decoration") ? i_4431_W.u_1723_Y(object, "decoration") : "mansion";
            J_2020_G.n_1700_B mapdecoration$type = R_4764_Y;
            try {
                mapdecoration$type = J_2020_G.n_1700_B.valueOf(s.toUpperCase(Locale.ROOT));
            }
            catch (IllegalArgumentException illegalargumentexception) {
                G_564_y.error("Error while parsing loot table decoration entry. Found {}. Defaulting to " + String.valueOf((Object)R_4764_Y), (Object)s);
            }
            byte b0 = i_4431_W.n_1700_B(object, "zoom", (byte)2);
            int i = i_4431_W.n_1700_B(object, "search_radius", 50);
            boolean flag = i_4431_W.n_1700_B(object, "skip_existing_chunks", true);
            return new a_1277_M(conditionsIn, structure, mapdecoration$type, b0, i, flag);
        }

        private static StructureFeature<?> n_1700_B(JsonObject p_237428_0_) {
            String s;
            StructureFeature structure;
            if (p_237428_0_.has("destination") && (structure = (StructureFeature)StructureFeature.n_1700_B.get((Object)(s = i_4431_W.u_1723_Y(p_237428_0_, "destination")).toLowerCase(Locale.ROOT))) != null) {
                return structure;
            }
            return J_1907_R;
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


