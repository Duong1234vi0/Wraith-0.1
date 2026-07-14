/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.W_1247_f;
import lightning.product.i_4431_W;

public class FishingHookPredicate {
    public static final FishingHookPredicate n_1700_B = new FishingHookPredicate(false);
    private boolean J_1907_R;

    private FishingHookPredicate(boolean p_i231586_1_) {
        this.J_1907_R = p_i231586_1_;
    }

    public static FishingHookPredicate n_1700_B(boolean p_234640_0_) {
        return new FishingHookPredicate(p_234640_0_);
    }

    public static FishingHookPredicate n_1700_B(@Nullable JsonElement p_234639_0_) {
        if (p_234639_0_ != null && !p_234639_0_.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_234639_0_, "fishing_hook");
            JsonElement jsonelement = jsonobject.get("in_open_water");
            return jsonelement != null ? new FishingHookPredicate(i_4431_W.R_4764_Y(jsonelement, "in_open_water")) : n_1700_B;
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("in_open_water", (JsonElement)new JsonPrimitive(Boolean.valueOf(this.J_1907_R)));
        return jsonobject;
    }

    public boolean n_1700_B(N_4263_v p_234638_1_) {
        if (this == n_1700_B) {
            return true;
        }
        if (!(p_234638_1_ instanceof W_1247_f)) {
            return false;
        }
        W_1247_f fishingbobberentity = (W_1247_f)p_234638_1_;
        return this.J_1907_R == fishingbobberentity.P_1922_E();
    }
}


