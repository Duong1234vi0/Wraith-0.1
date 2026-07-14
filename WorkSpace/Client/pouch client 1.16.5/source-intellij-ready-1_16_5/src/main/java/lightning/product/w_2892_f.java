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
import lightning.product.B_368_w;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.K_4074_S;
import lightning.product.P_1965_C;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.r_2687_x;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class w_2892_f
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("placed_block");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        T_2915_h block = w_2892_f.n_1700_B(json);
        r_2687_x statepropertiespredicate = r_2687_x.n_1700_B(json.get("state"));
        if (block != null) {
            statepropertiespredicate.n_1700_B(block.t_1786_h(), (String property) -> {
                throw new JsonSyntaxException("Block " + String.valueOf(block) + " has no property " + property + ":");
            });
        }
        B_368_w locationpredicate = B_368_w.n_1700_B(json.get("location"));
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        return new n_1700_B(entityPredicate, block, statepropertiespredicate, locationpredicate, itempredicate);
    }

    @Nullable
    private static T_2915_h n_1700_B(JsonObject object) {
        if (object.has("block")) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(object, "block"));
            return V_3137_a.q_4610_l.J_1907_R(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + String.valueOf(resourcelocation) + "'"));
        }
        return null;
    }

    public void n_1700_B(B_4088_l player, c_1514_x pos, Z_1993_T item) {
        K_4074_S blockstate = player.c_3005_b().getBlockState(pos);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(blockstate, pos, player.c_3005_b(), item));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final T_2915_h n_1700_B;
        private final r_2687_x J_1907_R;
        private final B_368_w R_4764_Y;
        private final w_4866_k G_564_y;

        public n_1700_B(b_1430_k.n_1700_B player, @Nullable T_2915_h block, r_2687_x properties, B_368_w location, w_4866_k item) {
            super(n_1700_B, player);
            this.n_1700_B = block;
            this.J_1907_R = properties;
            this.R_4764_Y = location;
            this.G_564_y = item;
        }

        public static n_1700_B n_1700_B(T_2915_h block) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, block, r_2687_x.n_1700_B, B_368_w.n_1700_B, w_4866_k.n_1700_B);
        }

        public boolean n_1700_B(K_4074_S state, c_1514_x pos, e_3591_l world, Z_1993_T item) {
            if (this.n_1700_B != null && !state.n_1700_B(this.n_1700_B)) {
                return false;
            }
            if (!this.J_1907_R.n_1700_B(state)) {
                return false;
            }
            if (!this.R_4764_Y.n_1700_B(world, pos.getX(), pos.getY(), pos.getZ())) {
                return false;
            }
            return this.G_564_y.n_1700_B(item);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            if (this.n_1700_B != null) {
                jsonobject.addProperty("block", V_3137_a.q_4610_l.J_1907_R(this.n_1700_B).toString());
            }
            jsonobject.add("state", this.J_1907_R.n_1700_B());
            jsonobject.add("location", this.R_4764_Y.n_1700_B());
            jsonobject.add("item", this.G_564_y.n_1700_B());
            return jsonobject;
        }
    }
}


