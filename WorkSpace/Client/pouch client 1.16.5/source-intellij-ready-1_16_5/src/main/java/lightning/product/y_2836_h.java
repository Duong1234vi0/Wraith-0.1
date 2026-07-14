/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.i_4431_W;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.MinMaxBounds;

public class y_2836_h {
    public static final y_2836_h n_1700_B = new y_2836_h(Collections.emptyMap());
    private final Map<g_422_i, n_1700_B> J_1907_R;

    public y_2836_h(Map<g_422_i, n_1700_B> effects) {
        this.J_1907_R = effects;
    }

    public static y_2836_h n_1700_B() {
        return new y_2836_h(Maps.newLinkedHashMap());
    }

    public y_2836_h n_1700_B(g_422_i effect) {
        this.J_1907_R.put(effect, new n_1700_B());
        return this;
    }

    public boolean n_1700_B(N_4263_v entityIn) {
        if (this == n_1700_B) {
            return true;
        }
        return entityIn instanceof r_4811_B ? this.n_1700_B(((r_4811_B)entityIn).b_3528_u()) : false;
    }

    public boolean n_1700_B(r_4811_B entityIn) {
        return this == n_1700_B ? true : this.n_1700_B(entityIn.b_3528_u());
    }

    public boolean n_1700_B(Map<g_422_i, k_2610_C> potions) {
        if (this == n_1700_B) {
            return true;
        }
        for (Map.Entry<g_422_i, n_1700_B> entry : this.J_1907_R.entrySet()) {
            k_2610_C effectinstance = potions.get(entry.getKey());
            if (entry.getValue().n_1700_B(effectinstance)) continue;
            return false;
        }
        return true;
    }

    public static y_2836_h n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "effects");
            LinkedHashMap map = Maps.newLinkedHashMap();
            for (Map.Entry entry : jsonobject.entrySet()) {
                g_2336_b resourcelocation = new g_2336_b((String)entry.getKey());
                g_422_i effect = V_3137_a.T_2506_i.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown effect '" + String.valueOf(resourcelocation) + "'"));
                n_1700_B mobeffectspredicate$instancepredicate = lightning.product.y_2836_h$n_1700_B.n_1700_B(i_4431_W.w_1484_f((JsonElement)entry.getValue(), (String)entry.getKey()));
                map.put(effect, mobeffectspredicate$instancepredicate);
            }
            return new y_2836_h(map);
        }
        return n_1700_B;
    }

    public JsonElement J_1907_R() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        for (Map.Entry<g_422_i, n_1700_B> entry : this.J_1907_R.entrySet()) {
            jsonobject.add(V_3137_a.T_2506_i.J_1907_R(entry.getKey()).toString(), entry.getValue().n_1700_B());
        }
        return jsonobject;
    }

    public static class n_1700_B {
        private final MinMaxBounds.G_564_y n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;
        @Nullable
        private final Boolean R_4764_Y;
        @Nullable
        private final Boolean G_564_y;

        public n_1700_B(MinMaxBounds.G_564_y amplifier, MinMaxBounds.G_564_y duration, @Nullable Boolean ambient, @Nullable Boolean visible) {
            this.n_1700_B = amplifier;
            this.J_1907_R = duration;
            this.R_4764_Y = ambient;
            this.G_564_y = visible;
        }

        public n_1700_B() {
            this(MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, null, null);
        }

        public boolean n_1700_B(@Nullable k_2610_C effect) {
            if (effect == null) {
                return false;
            }
            if (!this.n_1700_B.R_4764_Y(effect.R_4764_Y())) {
                return false;
            }
            if (!this.J_1907_R.R_4764_Y(effect.J_1907_R())) {
                return false;
            }
            if (this.R_4764_Y != null && this.R_4764_Y.booleanValue() != effect.G_564_y()) {
                return false;
            }
            return this.G_564_y == null || this.G_564_y.booleanValue() == effect.P_1922_E();
        }

        public JsonElement n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("amplifier", this.n_1700_B.G_564_y());
            jsonobject.add("duration", this.J_1907_R.G_564_y());
            jsonobject.addProperty("ambient", this.R_4764_Y);
            jsonobject.addProperty("visible", this.G_564_y);
            return jsonobject;
        }

        public static n_1700_B n_1700_B(JsonObject object) {
            MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(object.get("amplifier"));
            MinMaxBounds.G_564_y minmaxbounds$intbound1 = MinMaxBounds.G_564_y.n_1700_B(object.get("duration"));
            Boolean obool = object.has("ambient") ? Boolean.valueOf(i_4431_W.w_1484_f(object, "ambient")) : null;
            Boolean obool1 = object.has("visible") ? Boolean.valueOf(i_4431_W.w_1484_f(object, "visible")) : null;
            return new n_1700_B(minmaxbounds$intbound, minmaxbounds$intbound1, obool, obool1);
        }
    }
}


