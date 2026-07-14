/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.K_1310_v;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.MinMaxBounds;

public class A_1604_A {
    public static final A_1604_A n_1700_B = new A_1604_A();
    public static final A_1604_A[] J_1907_R = new A_1604_A[0];
    private final K_1310_v R_4764_Y;
    private final MinMaxBounds.G_564_y G_564_y;

    public A_1604_A() {
        this.R_4764_Y = null;
        this.G_564_y = MinMaxBounds.G_564_y.P_1922_E;
    }

    public A_1604_A(@Nullable K_1310_v enchantment, MinMaxBounds.G_564_y levels) {
        this.R_4764_Y = enchantment;
        this.G_564_y = levels;
    }

    public boolean n_1700_B(Map<K_1310_v, Integer> enchantmentsIn) {
        if (this.R_4764_Y != null) {
            if (!enchantmentsIn.containsKey(this.R_4764_Y)) {
                return false;
            }
            int i = enchantmentsIn.get(this.R_4764_Y);
            if (this.G_564_y != null && !this.G_564_y.R_4764_Y(i)) {
                return false;
            }
        } else if (this.G_564_y != null) {
            for (Integer integer : enchantmentsIn.values()) {
                if (!this.G_564_y.R_4764_Y(integer)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (this.R_4764_Y != null) {
            jsonobject.addProperty("enchantment", V_3137_a.z_4693_k.J_1907_R(this.R_4764_Y).toString());
        }
        jsonobject.add("levels", this.G_564_y.G_564_y());
        return jsonobject;
    }

    public static A_1604_A n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "enchantment");
            K_1310_v enchantment = null;
            if (jsonobject.has("enchantment")) {
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "enchantment"));
                enchantment = V_3137_a.z_4693_k.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown enchantment '" + String.valueOf(resourcelocation) + "'"));
            }
            MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("levels"));
            return new A_1604_A(enchantment, minmaxbounds$intbound);
        }
        return n_1700_B;
    }

    public static A_1604_A[] J_1907_R(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonArray jsonarray = i_4431_W.t_148_a(element, "enchantments");
            A_1604_A[] aenchantmentpredicate = new A_1604_A[jsonarray.size()];
            for (int i = 0; i < aenchantmentpredicate.length; ++i) {
                aenchantmentpredicate[i] = A_1604_A.n_1700_B(jsonarray.get(i));
            }
            return aenchantmentpredicate;
        }
        return J_1907_R;
    }
}


