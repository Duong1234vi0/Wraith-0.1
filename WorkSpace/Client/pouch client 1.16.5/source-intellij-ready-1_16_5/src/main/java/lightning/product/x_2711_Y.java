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
import lightning.product.P_1965_C;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.o_3456_E;
import lightning.product.DeserializationContext;

public class x_2711_Y
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("nether_travel");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        B_368_w locationpredicate = B_368_w.n_1700_B(json.get("entered"));
        B_368_w locationpredicate1 = B_368_w.n_1700_B(json.get("exited"));
        o_3456_E distancepredicate = o_3456_E.n_1700_B(json.get("distance"));
        return new n_1700_B(entityPredicate, locationpredicate, locationpredicate1, distancepredicate);
    }

    public void n_1700_B(B_4088_l player, e_2866_D enteredNetherPosition) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player.c_3005_b(), enteredNetherPosition, player.O_3598_v(), player.X_2960_b(), player.l_2647_k()));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final B_368_w n_1700_B;
        private final B_368_w J_1907_R;
        private final o_3456_E R_4764_Y;

        public n_1700_B(b_1430_k.n_1700_B player, B_368_w entered, B_368_w exited, o_3456_E distance) {
            super(n_1700_B, player);
            this.n_1700_B = entered;
            this.J_1907_R = exited;
            this.R_4764_Y = distance;
        }

        public static n_1700_B n_1700_B(o_3456_E distance) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, B_368_w.n_1700_B, B_368_w.n_1700_B, distance);
        }

        public boolean n_1700_B(e_3591_l world, e_2866_D enteredNetherPosition, double x, double y, double z) {
            if (!this.n_1700_B.n_1700_B(world, enteredNetherPosition.J_1907_R, enteredNetherPosition.R_4764_Y, enteredNetherPosition.G_564_y)) {
                return false;
            }
            if (!this.J_1907_R.n_1700_B(world, x, y, z)) {
                return false;
            }
            return this.R_4764_Y.n_1700_B(enteredNetherPosition.J_1907_R, enteredNetherPosition.R_4764_Y, enteredNetherPosition.G_564_y, x, y, z);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("entered", this.n_1700_B.n_1700_B());
            jsonobject.add("exited", this.J_1907_R.n_1700_B());
            jsonobject.add("distance", this.R_4764_Y.n_1700_B());
            return jsonobject;
        }
    }
}


