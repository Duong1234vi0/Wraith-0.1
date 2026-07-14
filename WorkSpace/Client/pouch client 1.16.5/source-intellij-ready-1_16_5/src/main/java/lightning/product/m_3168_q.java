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

public class m_3168_q
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("enchanted_item");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("levels"));
        return new n_1700_B(entityPredicate, itempredicate, minmaxbounds$intbound);
    }

    public void n_1700_B(B_4088_l player, Z_1993_T item, int levelsSpent) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(item, levelsSpent));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final w_4866_k n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, w_4866_k item, MinMaxBounds.G_564_y level) {
            super(n_1700_B, player);
            this.n_1700_B = item;
            this.J_1907_R = level;
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, w_4866_k.n_1700_B, MinMaxBounds.G_564_y.P_1922_E);
        }

        public boolean n_1700_B(Z_1993_T item, int levelsIn) {
            if (!this.n_1700_B.n_1700_B(item)) {
                return false;
            }
            return this.J_1907_R.R_4764_Y(levelsIn);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("item", this.n_1700_B.n_1700_B());
            jsonobject.add("levels", this.J_1907_R.G_564_y());
            return jsonobject;
        }
    }
}


