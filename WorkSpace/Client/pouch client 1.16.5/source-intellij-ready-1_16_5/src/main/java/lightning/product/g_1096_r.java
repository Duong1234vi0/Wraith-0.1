/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.B_368_w;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.K_4074_S;
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class g_1096_r
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("item_used_on_block");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        B_368_w locationpredicate = B_368_w.n_1700_B(json.get("location"));
        w_4866_k itempredicate = w_4866_k.n_1700_B(json.get("item"));
        return new n_1700_B(entityPredicate, locationpredicate, itempredicate);
    }

    public void n_1700_B(B_4088_l player, c_1514_x pos, Z_1993_T stack) {
        K_4074_S blockstate = player.c_3005_b().getBlockState(pos);
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(blockstate, player.c_3005_b(), pos, stack));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final B_368_w n_1700_B;
        private final w_4866_k J_1907_R;

        public n_1700_B(b_1430_k.n_1700_B player, B_368_w location, w_4866_k stack) {
            super(n_1700_B, player);
            this.n_1700_B = location;
            this.J_1907_R = stack;
        }

        public static n_1700_B n_1700_B(B_368_w.n_1700_B locationBuilder, w_4866_k.n_1700_B stackBuilder) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, locationBuilder.J_1907_R(), stackBuilder.J_1907_R());
        }

        public boolean n_1700_B(K_4074_S state, e_3591_l world, c_1514_x pos, Z_1993_T stack) {
            return !this.n_1700_B.n_1700_B(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5) ? false : this.J_1907_R.n_1700_B(stack);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("location", this.n_1700_B.n_1700_B());
            jsonobject.add("item", this.J_1907_R.n_1700_B());
            return jsonobject;
        }
    }
}


