/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_4431_W;
import lightning.product.MinMaxBounds;

public class LightPredicate {
    public static final LightPredicate n_1700_B = new LightPredicate(MinMaxBounds.G_564_y.P_1922_E);
    private final MinMaxBounds.G_564_y J_1907_R;

    private LightPredicate(MinMaxBounds.G_564_y bounds) {
        this.J_1907_R = bounds;
    }

    public boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        if (this == n_1700_B) {
            return true;
        }
        if (!world.multiplayerClientSuggestionProvider(pos)) {
            return false;
        }
        return this.J_1907_R.R_4764_Y(world.u_2550_I(pos));
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("light", this.J_1907_R.G_564_y());
        return jsonobject;
    }

    public static LightPredicate n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "light");
            MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("light"));
            return new LightPredicate(minmaxbounds$intbound);
        }
        return n_1700_B;
    }
}


