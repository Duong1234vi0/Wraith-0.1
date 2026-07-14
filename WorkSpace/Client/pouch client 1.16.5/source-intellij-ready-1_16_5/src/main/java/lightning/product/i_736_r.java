/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Streams
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import lightning.product.B_4977_Y;
import lightning.product.D_408_h;
import lightning.product.StringTag;
import lightning.product.I_2011_f;
import lightning.product.LootItemFunctions;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.i_4431_W;
import lightning.product.q_1704_m;
import lightning.product.q_2896_o;
import lightning.product.x_282_a;

public class i_736_r
extends T_3951_H {
    private final boolean J_1907_R;
    private final List<x_282_a> R_4764_Y;
    @Nullable
    private final q_1704_m.J_1907_R G_564_y;

    public i_736_r(LootItemCondition[] p_i51220_1_, boolean replace, List<x_282_a> lore, @Nullable q_1704_m.J_1907_R p_i51220_4_) {
        super(p_i51220_1_);
        this.J_1907_R = replace;
        this.R_4764_Y = ImmutableList.copyOf(lore);
        this.G_564_y = p_i51220_4_;
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.w_1457_N;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return this.G_564_y != null ? ImmutableSet.of(this.G_564_y.n_1700_B()) : ImmutableSet.of();
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        q_2896_o listnbt = this.n_1700_B(stack, !this.R_4764_Y.isEmpty());
        if (listnbt != null) {
            if (this.J_1907_R) {
                listnbt.clear();
            }
            UnaryOperator<x_282_a> unaryoperator = D_408_h.n_1700_B(context, this.G_564_y);
            this.R_4764_Y.stream().map(unaryoperator).map(x_282_a.n_1700_B::n_1700_B).map(StringTag::n_1700_B).forEach(listnbt::add);
        }
        return stack;
    }

    @Nullable
    private q_2896_o n_1700_B(Z_1993_T p_215942_1_, boolean p_215942_2_) {
        U_2912_j compoundnbt1;
        U_2912_j compoundnbt;
        if (p_215942_1_.h_1847_R()) {
            compoundnbt = p_215942_1_.Q_4569_t();
        } else {
            if (!p_215942_2_) {
                return null;
            }
            compoundnbt = new U_2912_j();
            p_215942_1_.R_4764_Y(compoundnbt);
        }
        if (compoundnbt.R_4764_Y("display", 10)) {
            compoundnbt1 = compoundnbt.M_182_A("display");
        } else {
            if (!p_215942_2_) {
                return null;
            }
            compoundnbt1 = new U_2912_j();
            compoundnbt.n_1700_B("display", compoundnbt1);
        }
        if (compoundnbt1.R_4764_Y("Lore", 9)) {
            return compoundnbt1.G_564_y("Lore", 8);
        }
        if (p_215942_2_) {
            q_2896_o listnbt = new q_2896_o();
            compoundnbt1.n_1700_B("Lore", listnbt);
            return listnbt;
        }
        return null;
    }

    public static class n_1700_B
    extends T_3951_H.J_1907_R<i_736_r> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, i_736_r p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            p_230424_1_.addProperty("replace", Boolean.valueOf(p_230424_2_.J_1907_R));
            JsonArray jsonarray = new JsonArray();
            for (x_282_a itextcomponent : p_230424_2_.R_4764_Y) {
                jsonarray.add(x_282_a.n_1700_B.J_1907_R(itextcomponent));
            }
            p_230424_1_.add("lore", (JsonElement)jsonarray);
            if (p_230424_2_.G_564_y != null) {
                p_230424_1_.add("entity", p_230424_3_.serialize((Object)p_230424_2_.G_564_y));
            }
        }

        public i_736_r J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            boolean flag = i_4431_W.n_1700_B(object, "replace", false);
            List list = (List)Streams.stream((Iterable)i_4431_W.P_4830_p(object, "lore")).map(x_282_a.n_1700_B::n_1700_B).collect(ImmutableList.toImmutableList());
            q_1704_m.J_1907_R lootcontext$entitytarget = i_4431_W.n_1700_B(object, "entity", null, deserializationContext, q_1704_m.J_1907_R.class);
            return new i_736_r(conditionsIn, flag, list, lootcontext$entitytarget);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


