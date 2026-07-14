/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.H_1440_Y;
import lightning.product.K_1583_J;
import lightning.product.Container;
import lightning.product.StonecutterRecipe;
import lightning.product.Q_4863_g;
import lightning.product.S_3924_b;
import lightning.product.V_3137_a;
import lightning.product.Y_3349_u;
import lightning.product.b_4507_u;
import lightning.product.f_993_z;
import lightning.product.g_2336_b;
import lightning.product.Recipe;

public interface RecipeType<T extends Recipe<?>> {
    public static final RecipeType<Q_4863_g> n_1700_B = RecipeType.n_1700_B("crafting");
    public static final RecipeType<Y_3349_u> J_1907_R = RecipeType.n_1700_B("smelting");
    public static final RecipeType<H_1440_Y> R_4764_Y = RecipeType.n_1700_B("blasting");
    public static final RecipeType<f_993_z> G_564_y = RecipeType.n_1700_B("smoking");
    public static final RecipeType<S_3924_b> P_1922_E = RecipeType.n_1700_B("campfire_cooking");
    public static final RecipeType<StonecutterRecipe> u_1723_Y = RecipeType.n_1700_B("stonecutting");
    public static final RecipeType<K_1583_J> v_4262_N = RecipeType.n_1700_B("smithing");

    public static <T extends Recipe<?>> RecipeType<T> n_1700_B(final String key) {
        return V_3137_a.n_1700_B(V_3137_a.D_4792_h, new g_2336_b(key), new RecipeType<T>(){

            public String toString() {
                return key;
            }
        });
    }

    default public <C extends Container> Optional<T> n_1700_B(Recipe<C> recipe, b_4507_u worldIn, C inv) {
        return recipe.n_1700_B(inv, worldIn) ? Optional.of(recipe) : Optional.empty();
    }
}


