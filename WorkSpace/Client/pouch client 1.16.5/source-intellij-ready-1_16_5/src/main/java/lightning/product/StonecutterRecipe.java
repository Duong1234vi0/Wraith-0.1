/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SingleItemRecipe;
import lightning.product.Container;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.RecipeSerializer;

public class StonecutterRecipe
extends SingleItemRecipe {
    public StonecutterRecipe(g_2336_b id, String group, b_3278_X ingredient, Z_1993_T result) {
        super(RecipeType.u_1723_Y, RecipeSerializer.Y_601_j, id, group, ingredient, result);
    }

    @Override
    public boolean n_1700_B(Container inv, b_4507_u worldIn) {
        return this.n_1700_B.n_1700_B(inv.s_956_w(0));
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.f_4705_f);
    }
}


