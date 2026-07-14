/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_1604_A;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.L_1875_m;
import lightning.product.SerializationTags;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.h_2396_v;
import lightning.product.i_4431_W;
import lightning.product.EnchantedBookItem;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.r_109_r;
import lightning.product.MinMaxBounds;
import lightning.product.y_528_b;

public class w_4866_k {
    public static final w_4866_k n_1700_B = new w_4866_k();
    @Nullable
    private final r_109_r<q_1613_l> J_1907_R;
    @Nullable
    private final q_1613_l R_4764_Y;
    private final MinMaxBounds.G_564_y G_564_y;
    private final MinMaxBounds.G_564_y P_1922_E;
    private final A_1604_A[] u_1723_Y;
    private final A_1604_A[] v_4262_N;
    @Nullable
    private final y_528_b w_1484_f;
    private final h_2396_v t_148_a;

    public w_4866_k() {
        this.J_1907_R = null;
        this.R_4764_Y = null;
        this.w_1484_f = null;
        this.G_564_y = MinMaxBounds.G_564_y.P_1922_E;
        this.P_1922_E = MinMaxBounds.G_564_y.P_1922_E;
        this.u_1723_Y = A_1604_A.J_1907_R;
        this.v_4262_N = A_1604_A.J_1907_R;
        this.t_148_a = h_2396_v.n_1700_B;
    }

    public w_4866_k(@Nullable r_109_r<q_1613_l> tag, @Nullable q_1613_l item, MinMaxBounds.G_564_y count, MinMaxBounds.G_564_y durability, A_1604_A[] enchantments, A_1604_A[] bookEnchantments, @Nullable y_528_b potion, h_2396_v nbt) {
        this.J_1907_R = tag;
        this.R_4764_Y = item;
        this.G_564_y = count;
        this.P_1922_E = durability;
        this.u_1723_Y = enchantments;
        this.v_4262_N = bookEnchantments;
        this.w_1484_f = potion;
        this.t_148_a = nbt;
    }

    public boolean n_1700_B(Z_1993_T item) {
        if (this == n_1700_B) {
            return true;
        }
        if (this.J_1907_R != null && !this.J_1907_R.n_1700_B(item.J_1907_R())) {
            return false;
        }
        if (this.R_4764_Y != null && item.J_1907_R() != this.R_4764_Y) {
            return false;
        }
        if (!this.G_564_y.R_4764_Y(item.t_4043_B())) {
            return false;
        }
        if (!this.P_1922_E.R_4764_Y() && !item.P_1922_E()) {
            return false;
        }
        if (!this.P_1922_E.R_4764_Y(item.w_1484_f() - item.v_4262_N())) {
            return false;
        }
        if (!this.t_148_a.n_1700_B(item)) {
            return false;
        }
        if (this.u_1723_Y.length > 0) {
            Map<K_1310_v, Integer> map = K_4096_w.n_1700_B(item.t_1786_h());
            for (A_1604_A enchantmentpredicate : this.u_1723_Y) {
                if (enchantmentpredicate.n_1700_B(map)) continue;
                return false;
            }
        }
        if (this.v_4262_N.length > 0) {
            Map<K_1310_v, Integer> map1 = K_4096_w.n_1700_B(EnchantedBookItem.G_564_y(item));
            for (A_1604_A enchantmentpredicate1 : this.v_4262_N) {
                if (enchantmentpredicate1.n_1700_B(map1)) continue;
                return false;
            }
        }
        y_528_b potion = L_1875_m.G_564_y(item);
        return this.w_1484_f == null || this.w_1484_f == potion;
    }

    public static w_4866_k n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "item");
            MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("count"));
            MinMaxBounds.G_564_y minmaxbounds$intbound1 = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("durability"));
            if (jsonobject.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            }
            h_2396_v nbtpredicate = h_2396_v.n_1700_B(jsonobject.get("nbt"));
            q_1613_l item = null;
            if (jsonobject.has("item")) {
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "item"));
                item = V_3137_a.e_2887_G.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown item id '" + String.valueOf(resourcelocation) + "'"));
            }
            r_109_r<q_1613_l> itag = null;
            if (jsonobject.has("tag")) {
                g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "tag"));
                itag = SerializationTags.n_1700_B().J_1907_R().n_1700_B(resourcelocation1);
                if (itag == null) {
                    throw new JsonSyntaxException("Unknown item tag '" + String.valueOf(resourcelocation1) + "'");
                }
            }
            y_528_b potion = null;
            if (jsonobject.has("potion")) {
                g_2336_b resourcelocation2 = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "potion"));
                potion = V_3137_a.B_1668_F.J_1907_R(resourcelocation2).orElseThrow(() -> new JsonSyntaxException("Unknown potion '" + String.valueOf(resourcelocation2) + "'"));
            }
            A_1604_A[] aenchantmentpredicate1 = A_1604_A.J_1907_R(jsonobject.get("enchantments"));
            A_1604_A[] aenchantmentpredicate = A_1604_A.J_1907_R(jsonobject.get("stored_enchantments"));
            return new w_4866_k(itag, item, minmaxbounds$intbound, minmaxbounds$intbound1, aenchantmentpredicate1, aenchantmentpredicate, potion, nbtpredicate);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (this.R_4764_Y != null) {
            jsonobject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.R_4764_Y).toString());
        }
        if (this.J_1907_R != null) {
            jsonobject.addProperty("tag", SerializationTags.n_1700_B().J_1907_R().J_1907_R(this.J_1907_R).toString());
        }
        jsonobject.add("count", this.G_564_y.G_564_y());
        jsonobject.add("durability", this.P_1922_E.G_564_y());
        jsonobject.add("nbt", this.t_148_a.n_1700_B());
        if (this.u_1723_Y.length > 0) {
            JsonArray jsonarray = new JsonArray();
            for (A_1604_A enchantmentpredicate : this.u_1723_Y) {
                jsonarray.add(enchantmentpredicate.n_1700_B());
            }
            jsonobject.add("enchantments", (JsonElement)jsonarray);
        }
        if (this.v_4262_N.length > 0) {
            JsonArray jsonarray1 = new JsonArray();
            for (A_1604_A enchantmentpredicate1 : this.v_4262_N) {
                jsonarray1.add(enchantmentpredicate1.n_1700_B());
            }
            jsonobject.add("stored_enchantments", (JsonElement)jsonarray1);
        }
        if (this.w_1484_f != null) {
            jsonobject.addProperty("potion", V_3137_a.B_1668_F.J_1907_R(this.w_1484_f).toString());
        }
        return jsonobject;
    }

    public static w_4866_k[] J_1907_R(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonArray jsonarray = i_4431_W.t_148_a(element, "items");
            w_4866_k[] aitempredicate = new w_4866_k[jsonarray.size()];
            for (int i = 0; i < aitempredicate.length; ++i) {
                aitempredicate[i] = w_4866_k.n_1700_B(jsonarray.get(i));
            }
            return aitempredicate;
        }
        return new w_4866_k[0];
    }

    public static class n_1700_B {
        private final List<A_1604_A> n_1700_B = Lists.newArrayList();
        private final List<A_1604_A> J_1907_R = Lists.newArrayList();
        @Nullable
        private q_1613_l R_4764_Y;
        @Nullable
        private r_109_r<q_1613_l> G_564_y;
        private MinMaxBounds.G_564_y P_1922_E = MinMaxBounds.G_564_y.P_1922_E;
        private MinMaxBounds.G_564_y u_1723_Y = MinMaxBounds.G_564_y.P_1922_E;
        @Nullable
        private y_528_b v_4262_N;
        private h_2396_v w_1484_f = h_2396_v.n_1700_B;

        private n_1700_B() {
        }

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(q_1803_e provider) {
            this.R_4764_Y = provider.u_1723_Y();
            return this;
        }

        public n_1700_B n_1700_B(r_109_r<q_1613_l> tag) {
            this.G_564_y = tag;
            return this;
        }

        public n_1700_B n_1700_B(U_2912_j nbt) {
            this.w_1484_f = new h_2396_v(nbt);
            return this;
        }

        public n_1700_B n_1700_B(A_1604_A enchantmentCondition) {
            this.n_1700_B.add(enchantmentCondition);
            return this;
        }

        public w_4866_k J_1907_R() {
            return new w_4866_k(this.G_564_y, this.R_4764_Y, this.P_1922_E, this.u_1723_Y, this.n_1700_B.toArray(A_1604_A.J_1907_R), this.J_1907_R.toArray(A_1604_A.J_1907_R), this.v_4262_N, this.w_1484_f);
        }
    }
}


