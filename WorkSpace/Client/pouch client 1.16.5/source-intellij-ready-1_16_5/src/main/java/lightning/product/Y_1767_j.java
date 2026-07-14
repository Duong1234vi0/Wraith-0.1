/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.B_4977_Y;
import lightning.product.LootItemFunctions;
import lightning.product.Attribute;
import lightning.product.LootItemCondition;
import lightning.product.T_3951_H;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.j_3341_s;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;

public class Y_1767_j
extends T_3951_H {
    private final List<n_1700_B> J_1907_R;

    private Y_1767_j(LootItemCondition[] p_i51228_1_, List<n_1700_B> p_i51228_2_) {
        super(p_i51228_1_);
        this.J_1907_R = ImmutableList.copyOf(p_i51228_2_);
    }

    @Override
    public B_4977_Y J_1907_R() {
        return LootItemFunctions.t_148_a;
    }

    @Override
    public Z_1993_T J_1907_R(Z_1993_T stack, q_1704_m context) {
        Random random = context.n_1700_B();
        for (n_1700_B setattributes$modifier : this.J_1907_R) {
            UUID uuid = setattributes$modifier.P_1922_E;
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
            e_1174_E equipmentslottype = j_3341_s.n_1700_B(setattributes$modifier.u_1723_Y, random);
            stack.n_1700_B(setattributes$modifier.J_1907_R, new U_1880_G(uuid, setattributes$modifier.n_1700_B, (double)setattributes$modifier.G_564_y.J_1907_R(random), setattributes$modifier.R_4764_Y), equipmentslottype);
        }
        return stack;
    }

    static class n_1700_B {
        private final String n_1700_B;
        private final Attribute J_1907_R;
        private final U_1880_G.n_1700_B R_4764_Y;
        private final o_3393_s G_564_y;
        @Nullable
        private final UUID P_1922_E;
        private final e_1174_E[] u_1723_Y;

        private n_1700_B(String p_i232172_1_, Attribute p_i232172_2_, U_1880_G.n_1700_B p_i232172_3_, o_3393_s p_i232172_4_, e_1174_E[] p_i232172_5_, @Nullable UUID p_i232172_6_) {
            this.n_1700_B = p_i232172_1_;
            this.J_1907_R = p_i232172_2_;
            this.R_4764_Y = p_i232172_3_;
            this.G_564_y = p_i232172_4_;
            this.P_1922_E = p_i232172_6_;
            this.u_1723_Y = p_i232172_5_;
        }

        public JsonObject n_1700_B(JsonSerializationContext context) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("name", this.n_1700_B);
            jsonobject.addProperty("attribute", V_3137_a.l_1233_K.J_1907_R(this.J_1907_R).toString());
            jsonobject.addProperty("operation", lightning.product.Y_1767_j$n_1700_B.n_1700_B(this.R_4764_Y));
            jsonobject.add("amount", context.serialize((Object)this.G_564_y));
            if (this.P_1922_E != null) {
                jsonobject.addProperty("id", this.P_1922_E.toString());
            }
            if (this.u_1723_Y.length == 1) {
                jsonobject.addProperty("slot", this.u_1723_Y[0].G_564_y());
            } else {
                JsonArray jsonarray = new JsonArray();
                for (e_1174_E equipmentslottype : this.u_1723_Y) {
                    jsonarray.add((JsonElement)new JsonPrimitive(equipmentslottype.G_564_y()));
                }
                jsonobject.add("slot", (JsonElement)jsonarray);
            }
            return jsonobject;
        }

        public static n_1700_B n_1700_B(JsonObject jsonObj, JsonDeserializationContext context) {
            e_1174_E[] aequipmentslottype;
            String s = i_4431_W.u_1723_Y(jsonObj, "name");
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonObj, "attribute"));
            Attribute attribute = V_3137_a.l_1233_K.n_1700_B(resourcelocation);
            if (attribute == null) {
                throw new JsonSyntaxException("Unknown attribute: " + String.valueOf(resourcelocation));
            }
            U_1880_G.n_1700_B attributemodifier$operation = lightning.product.Y_1767_j$n_1700_B.n_1700_B(i_4431_W.u_1723_Y(jsonObj, "operation"));
            o_3393_s randomvaluerange = i_4431_W.n_1700_B(jsonObj, "amount", context, o_3393_s.class);
            UUID uuid = null;
            if (i_4431_W.n_1700_B(jsonObj, "slot")) {
                aequipmentslottype = new e_1174_E[]{e_1174_E.n_1700_B(i_4431_W.u_1723_Y(jsonObj, "slot"))};
            } else {
                if (!i_4431_W.R_4764_Y(jsonObj, "slot")) {
                    throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
                }
                JsonArray jsonarray = i_4431_W.P_4830_p(jsonObj, "slot");
                aequipmentslottype = new e_1174_E[jsonarray.size()];
                int i = 0;
                for (JsonElement jsonelement : jsonarray) {
                    aequipmentslottype[i++] = e_1174_E.n_1700_B(i_4431_W.n_1700_B(jsonelement, "slot"));
                }
                if (aequipmentslottype.length == 0) {
                    throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
                }
            }
            if (jsonObj.has("id")) {
                String s1 = i_4431_W.u_1723_Y(jsonObj, "id");
                try {
                    uuid = UUID.fromString(s1);
                }
                catch (IllegalArgumentException illegalargumentexception) {
                    throw new JsonSyntaxException("Invalid attribute modifier id '" + s1 + "' (must be UUID format, with dashes)");
                }
            }
            return new n_1700_B(s, attribute, attributemodifier$operation, randomvaluerange, aequipmentslottype, uuid);
        }

        private static String n_1700_B(U_1880_G.n_1700_B p_216244_0_) {
            switch (p_216244_0_) {
                case n_1700_B: {
                    return "addition";
                }
                case J_1907_R: {
                    return "multiply_base";
                }
                case R_4764_Y: {
                    return "multiply_total";
                }
            }
            throw new IllegalArgumentException("Unknown operation " + String.valueOf((Object)p_216244_0_));
        }

        private static U_1880_G.n_1700_B n_1700_B(String p_216246_0_) {
            int b0 = -1;
            switch (p_216246_0_.hashCode()) {
                case -1226589444: {
                    if (!p_216246_0_.equals("addition")) break;
                    b0 = 0;
                    break;
                }
                case -78229492: {
                    if (!p_216246_0_.equals("multiply_base")) break;
                    b0 = 1;
                    break;
                }
                case 1886894441: {
                    if (!p_216246_0_.equals("multiply_total")) break;
                    b0 = 2;
                }
            }
            switch (b0) {
                case 0: {
                    return U_1880_G.n_1700_B.n_1700_B;
                }
                case 1: {
                    return U_1880_G.n_1700_B.J_1907_R;
                }
                case 2: {
                    return U_1880_G.n_1700_B.R_4764_Y;
                }
            }
            throw new JsonSyntaxException("Unknown attribute modifier operation " + p_216246_0_);
        }
    }

    public static class J_1907_R
    extends T_3951_H.J_1907_R<Y_1767_j> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, Y_1767_j p_230424_2_, JsonSerializationContext p_230424_3_) {
            super.n_1700_B(p_230424_1_, p_230424_2_, p_230424_3_);
            JsonArray jsonarray = new JsonArray();
            for (n_1700_B setattributes$modifier : p_230424_2_.J_1907_R) {
                jsonarray.add((JsonElement)setattributes$modifier.n_1700_B(p_230424_3_));
            }
            p_230424_1_.add("modifiers", (JsonElement)jsonarray);
        }

        public Y_1767_j J_1907_R(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            JsonArray jsonarray = i_4431_W.P_4830_p(object, "modifiers");
            ArrayList list = Lists.newArrayListWithExpectedSize((int)jsonarray.size());
            for (JsonElement jsonelement : jsonarray) {
                list.add(lightning.product.Y_1767_j$n_1700_B.n_1700_B(i_4431_W.w_1484_f(jsonelement, "modifier"), deserializationContext));
            }
            if (list.isEmpty()) {
                throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
            }
            return new Y_1767_j(conditionsIn, list);
        }

        @Override
        public /* synthetic */ T_3951_H n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootItemCondition[] q_2342_RArray) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext, q_2342_RArray);
        }
    }
}


