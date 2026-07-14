/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collections;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.Recipe;

public interface L_2125_Q {
    public void n_1700_B(@Nullable Recipe<?> var1);

    @Nullable
    public Recipe<?> R_4764_Y();

    default public void n_1700_B(a_3913_L player) {
        Recipe<?> irecipe = this.R_4764_Y();
        if (irecipe != null && !irecipe.t_148_a()) {
            player.J_1907_R(Collections.singleton(irecipe));
            this.n_1700_B((Recipe)null);
        }
    }

    default public boolean n_1700_B(b_4507_u worldIn, B_4088_l player, Recipe<?> recipe) {
        if (!recipe.t_148_a() && worldIn.H_1990_U().J_1907_R(A_2352_Z.Y_259_p) && !player.d_2427_y().J_1907_R(recipe)) {
            return false;
        }
        this.n_1700_B(recipe);
        return true;
    }
}


