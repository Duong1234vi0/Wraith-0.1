/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class Y_2805_J
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("item_durability_changed");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("durability"));
        MinMaxBounds.G_564_y minmaxbounds$intbound1 = MinMaxBounds.G_564_y.n_1700_B(json.get("delta"));
        return new n_1700_B(entityPredicate, itempredicate, minmaxbounds$intbound, minmaxbounds$intbound1);
    }

    public void n_1700_B(B_4088_l player, Z_1993_T itemIn, int newDurability) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(itemIn, newDurability));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final w_4866_k n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;
        private final MinMaxBounds.G_564_y R_4764_Y;

        public n_1700_B(b_1430_k.n_1700_B player, w_4866_k item, MinMaxBounds.G_564_y durability, MinMaxBounds.G_564_y delta) {
            super(n_1700_B, player);
            this.n_1700_B = item;
            this.J_1907_R = durability;
            this.R_4764_Y = delta;
        }

        public static n_1700_B n_1700_B(b_1430_k.n_1700_B player, w_4866_k item, MinMaxBounds.G_564_y durability) {
            return new n_1700_B(player, item, durability, MinMaxBounds.G_564_y.P_1922_E);
        }

        public boolean n_1700_B(Z_1993_T item, int durability) {
            if (!this.n_1700_B.n_1700_B(item)) {
                return false;
            }
            if (!this.J_1907_R.R_4764_Y(item.w_1484_f() - durability)) {
                return false;
            }
            return this.R_4764_Y.R_4764_Y(item.v_4262_N() - durability);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("item", this.n_1700_B.n_1700_B());
            jsonobject.add("durability", this.J_1907_R.G_564_y());
            jsonobject.add("delta", this.R_4764_Y.G_564_y());
            return jsonobject;
        }
    }
}


