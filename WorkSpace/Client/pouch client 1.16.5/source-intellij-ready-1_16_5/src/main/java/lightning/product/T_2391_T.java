/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class T_2391_T
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("bee_nest_destroyed");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        T_2915_h block = T_2391_T.n_1700_B(json);
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(json.get("num_bees_inside"));
        return new n_1700_B(entityPredicate, block, itempredicate, minmaxbounds$intbound);
    }

    @Nullable
    private static T_2915_h n_1700_B(JsonObject json) {
        if (json.has("block")) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "block"));
            return V_3137_a.q_4610_l.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + String.valueOf(resourcelocation) + "'"));
        }
        return null;
    }

    public void n_1700_B(B_4088_l player, T_2915_h block, Z_1993_T stack, int beesContained) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(block, stack, beesContained));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        @Nullable
        private final T_2915_h n_1700_B;
        private final w_4866_k J_1907_R;
        private final MinMaxBounds.G_564_y R_4764_Y;

        public n_1700_B(b_1430_k.n_1700_B entityCondition, @Nullable T_2915_h block, w_4866_k itemCondition, MinMaxBounds.G_564_y beesContained) {
            super(n_1700_B, entityCondition);
            this.n_1700_B = block;
            this.J_1907_R = itemCondition;
            this.R_4764_Y = beesContained;
        }

        public static n_1700_B n_1700_B(T_2915_h block, w_4866_k.n_1700_B itemConditionBuilder, MinMaxBounds.G_564_y beesContained) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, block, itemConditionBuilder.J_1907_R(), beesContained);
        }

        public boolean n_1700_B(T_2915_h block, Z_1993_T stack, int beesContained) {
            if (this.n_1700_B != null && block != this.n_1700_B) {
                return false;
            }
            return !this.J_1907_R.n_1700_B(stack) ? false : this.R_4764_Y.R_4764_Y(beesContained);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            if (this.n_1700_B != null) {
                jsonobject.addProperty("block", V_3137_a.q_4610_l.J_1907_R(this.n_1700_B).toString());
            }
            jsonobject.add("item", this.J_1907_R.n_1700_B());
            jsonobject.add("num_bees_inside", this.R_4764_Y.G_564_y());
            return jsonobject;
        }
    }
}


