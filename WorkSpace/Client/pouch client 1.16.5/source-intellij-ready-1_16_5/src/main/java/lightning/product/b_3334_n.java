/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lightning.product.B_368_w;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.U_3554_Q;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.b_1430_k;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;

public class b_3334_n
extends SimpleCriterionTrigger<n_1700_B> {
    private final g_2336_b n_1700_B;

    public b_3334_n(g_2336_b id) {
        this.n_1700_B = id;
    }

    @Override
    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        JsonObject jsonobject = i_4431_W.n_1700_B(json, "location", json);
        B_368_w locationpredicate = B_368_w.n_1700_B((JsonElement)jsonobject);
        return new n_1700_B(this.n_1700_B, entityPredicate, locationpredicate);
    }

    public void n_1700_B(B_4088_l player) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(player.c_3005_b(), player.O_3598_v(), player.X_2960_b(), player.l_2647_k()));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final B_368_w n_1700_B;

        public n_1700_B(g_2336_b id, b_1430_k.n_1700_B player, B_368_w location) {
            super(id, player);
            this.n_1700_B = location;
        }

        public static n_1700_B n_1700_B(B_368_w location) {
            return new n_1700_B(U_3554_Q.M_182_A.n_1700_B, b_1430_k.n_1700_B.n_1700_B, location);
        }

        public static n_1700_B J_1907_R() {
            return new n_1700_B(U_3554_Q.t_1786_h.n_1700_B, b_1430_k.n_1700_B.n_1700_B, B_368_w.n_1700_B);
        }

        public static n_1700_B G_564_y() {
            return new n_1700_B(U_3554_Q.n_3318_d.n_1700_B, b_1430_k.n_1700_B.n_1700_B, B_368_w.n_1700_B);
        }

        public boolean n_1700_B(e_3591_l world, double x, double y, double z) {
            return this.n_1700_B.n_1700_B(world, x, y, z);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.add("location", this.n_1700_B.n_1700_B());
            return jsonobject;
        }
    }
}


