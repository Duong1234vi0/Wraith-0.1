/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AbstractCookingRecipe;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_3278_X;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.RecipeSerializer;

public class H_1440_Y
extends AbstractCookingRecipe {
    public H_1440_Y(g_2336_b id, String group, b_3278_X ingredient, Z_1993_T result, float experience, int cookTime) {
        super(RecipeType.R_4764_Y, id, group, ingredient, result, experience, cookTime);
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.F_2052_z);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.t_1786_h;
    }
}


