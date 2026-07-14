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
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.DeserializationContext;

public class u_3578_p
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("recipe_unlocked");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "recipe"));
        return new n_1700_B(entityPredicate, resourcelocation);
    }

    @Override
    public void n_1700_B(B_4088_l player, Recipe<?> recipe) {
        ((SimpleCriterionTrigger)this).n_1700_B(player, (T instance) -> instance.n_1700_B(recipe));
    }

    public static n_1700_B n_1700_B(g_2336_b recipeID) {
        return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, recipeID);
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final g_2336_b n_1700_B;

        public n_1700_B(b_1430_k.n_1700_B player, g_2336_b recipeID) {
            super(n_1700_B, player);
            this.n_1700_B = recipeID;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            jsonobject.addProperty("recipe", this.n_1700_B.toString());
            return jsonobject;
        }

        public boolean n_1700_B(Recipe<?> recipe) {
            return this.n_1700_B.equals(recipe.u_1723_Y());
        }
    }
}


