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

public class f_993_z
extends AbstractCookingRecipe {
    public f_993_z(g_2336_b id, String group, b_3278_X ingredient, Z_1993_T result, float experience, int cookTime) {
        super(RecipeType.G_564_y, id, group, ingredient, result, experience, cookTime);
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.H_2506_c);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.multiplayerClientSuggestionProvider;
    }
}


