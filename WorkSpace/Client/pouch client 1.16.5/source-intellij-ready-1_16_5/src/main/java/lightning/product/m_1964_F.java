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
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;

public class m_1964_F
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("player_generates_container_loot");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    protected n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "loot_table"));
        return new n_1700_B(entityPredicate, resourcelocation);
    }

    public void n_1700_B(B_4088_l player, g_2336_b generatedLoot) {
        this.n_1700_B(player, (T instance) -> instance.J_1907_R(generatedLoot));
    }

    @Override
    protected /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final g_2336_b n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, g_2336_b generatedLoot) {
            super(n_1700_B, player);
            this.n_1700_B = generatedLoot;
        }

        public static n_1700_B n_1700_B(g_2336_b generatedLoot) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, generatedLoot);
        }

        public boolean J_1907_R(g_2336_b generatedLoot) {
            return this.n_1700_B.equals(generatedLoot);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.addProperty("loot_table", this.n_1700_B.toString());
            return jsonobject;
        }
    }
}


