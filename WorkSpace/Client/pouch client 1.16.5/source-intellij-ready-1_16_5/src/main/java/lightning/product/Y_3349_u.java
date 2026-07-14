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

public class Y_3349_u
extends AbstractCookingRecipe {
    public Y_3349_u(g_2336_b idIn, String groupIn, b_3278_X ingredientIn, Z_1993_T resultIn, float experienceIn, int cookTimeIn) {
        super(RecipeType.J_1907_R, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.P_925_e);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.M_182_A;
    }
}


