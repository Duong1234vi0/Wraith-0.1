/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lightning.product.AbstractCookingRecipe;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.b_3278_X;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.RecipeSerializer;

public class e_2674_c<T extends AbstractCookingRecipe>
implements RecipeSerializer<T> {
    private final int Q_2552_b;
    private final n_1700_B<T> C_2741_M;

    public e_2674_c(n_1700_B<T> factory, int cookingTime) {
        this.Q_2552_b = cookingTime;
        this.C_2741_M = factory;
    }

    public T n_1700_B(g_2336_b recipeId, JsonObject json) {
        String s = i_4431_W.n_1700_B(json, "group", "");
        JsonArray jsonelement = i_4431_W.R_4764_Y(json, "ingredient") ? i_4431_W.P_4830_p(json, "ingredient") : i_4431_W.M_588_G(json, "ingredient");
        b_3278_X ingredient = b_3278_X.n_1700_B((JsonElement)jsonelement);
        String s1 = i_4431_W.u_1723_Y(json, "result");
        g_2336_b resourcelocation = new g_2336_b(s1);
        Z_1993_T itemstack = new Z_1993_T(V_3137_a.e_2887_G.J_1907_R(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        float f = i_4431_W.n_1700_B(json, "experience", 0.0f);
        int i = i_4431_W.n_1700_B(json, "cookingtime", this.Q_2552_b);
        return this.C_2741_M.create(recipeId, s, ingredient, itemstack, f, i);
    }

    public T n_1700_B(g_2336_b recipeId, b_2585_i buffer) {
        String s = buffer.P_1922_E(Short.MAX_VALUE);
        b_3278_X ingredient = b_3278_X.J_1907_R(buffer);
        Z_1993_T itemstack = buffer.u_2550_I();
        float f = buffer.readFloat();
        int i = buffer.u_1723_Y();
        return this.C_2741_M.create(recipeId, s, ingredient, itemstack, f, i);
    }

    @Override
    public void n_1700_B(b_2585_i buffer, T recipe) {
        buffer.n_1700_B(((AbstractCookingRecipe)recipe).R_4764_Y);
        ((AbstractCookingRecipe)recipe).G_564_y.n_1700_B(buffer);
        buffer.n_1700_B(((AbstractCookingRecipe)recipe).P_1922_E);
        buffer.writeFloat(((AbstractCookingRecipe)recipe).u_1723_Y);
        buffer.G_564_y(((AbstractCookingRecipe)recipe).v_4262_N);
    }

    @Override
    public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, b_2585_i b_2585_i2) {
        return this.n_1700_B(g_2336_b2, b_2585_i2);
    }

    @Override
    public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, JsonObject jsonObject) {
        return this.n_1700_B(g_2336_b2, jsonObject);
    }

    static interface n_1700_B<T extends AbstractCookingRecipe> {
        public T create(g_2336_b var1, String var2, b_3278_X var3, Z_1993_T var4, float var5, int var6);
    }
}


