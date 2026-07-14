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

public class S_3924_b
extends AbstractCookingRecipe {
    public S_3924_b(g_2336_b p_i50030_1_, String p_i50030_2_, b_3278_X p_i50030_3_, Z_1993_T p_i50030_4_, float p_i50030_5_, int p_i50030_6_) {
        super(RecipeType.P_1922_E, p_i50030_1_, p_i50030_2_, p_i50030_3_, p_i50030_4_, p_i50030_5_, p_i50030_6_);
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.k_1366_K);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.w_1457_N;
    }
}


