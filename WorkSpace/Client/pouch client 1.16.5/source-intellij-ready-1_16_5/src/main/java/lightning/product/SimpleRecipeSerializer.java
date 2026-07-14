/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import java.util.function.Function;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.RecipeSerializer;

public class SimpleRecipeSerializer<T extends Recipe<?>>
implements RecipeSerializer<T> {
    private final Function<g_2336_b, T> Q_2552_b;

    public SimpleRecipeSerializer(Function<g_2336_b, T> p_i50024_1_) {
        this.Q_2552_b = p_i50024_1_;
    }

    @Override
    public T J_1907_R(g_2336_b recipeId, JsonObject json) {
        return (T)((Recipe)this.Q_2552_b.apply(recipeId));
    }

    @Override
    public T J_1907_R(g_2336_b recipeId, b_2585_i buffer) {
        return (T)((Recipe)this.Q_2552_b.apply(recipeId));
    }

    @Override
    public void n_1700_B(b_2585_i buffer, T recipe) {
    }
}


