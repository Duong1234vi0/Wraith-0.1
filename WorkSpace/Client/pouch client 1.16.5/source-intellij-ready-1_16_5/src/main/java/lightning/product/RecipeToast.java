/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.D_1624_i;
import lightning.product.F_2904_S;
import lightning.product.Toast;
import lightning.product.Z_1993_T;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.Recipe;
import lightning.product.x_282_a;

public class RecipeToast
implements Toast {
    private static final x_282_a R_4764_Y = new F_2904_S("recipe.toast.title");
    private static final x_282_a G_564_y = new F_2904_S("recipe.toast.description");
    private final List<Recipe<?>> P_1922_E = Lists.newArrayList();
    private long u_1723_Y;
    private boolean v_4262_N;

    public RecipeToast(Recipe<?> recipeIn) {
        this.P_1922_E.add(recipeIn);
    }

    @Override
    public Toast.n_1700_B func_230444_a_(g_221_o p_230444_1_, D_1624_i p_230444_2_, long p_230444_3_) {
        if (this.v_4262_N) {
            this.u_1723_Y = p_230444_3_;
            this.v_4262_N = false;
        }
        if (this.P_1922_E.isEmpty()) {
            return Toast.n_1700_B.J_1907_R;
        }
        p_230444_2_.J_1907_R().G_624_v().n_1700_B(n_1700_B);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f);
        p_230444_2_.blit(p_230444_1_, 0, 0, 0, 32, this.J_1907_R(), this.R_4764_Y());
        p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, R_4764_Y, 30.0f, 7.0f, -11534256);
        p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, G_564_y, 30.0f, 18.0f, -16777216);
        Recipe<?> irecipe = this.P_1922_E.get((int)(p_230444_3_ / Math.max(1L, 5000L / (long)this.P_1922_E.size()) % (long)this.P_1922_E.size()));
        Z_1993_T itemstack = irecipe.w_1484_f();
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(0.6f, 0.6f, 1.0f);
        p_230444_2_.J_1907_R().r_715_M().R_4764_Y(itemstack, 3, 3);
        c_4037_x.d_2461_k();
        p_230444_2_.J_1907_R().r_715_M().R_4764_Y(irecipe.R_4764_Y(), 8, 8);
        return p_230444_3_ - this.u_1723_Y >= 5000L ? Toast.n_1700_B.J_1907_R : Toast.n_1700_B.n_1700_B;
    }

    private void n_1700_B(Recipe<?> recipeIn) {
        this.P_1922_E.add(recipeIn);
        this.v_4262_N = true;
    }

    public static void n_1700_B(D_1624_i toastGui, Recipe<?> recipeIn) {
        RecipeToast recipetoast = toastGui.n_1700_B(RecipeToast.class, J_1907_R);
        if (recipetoast == null) {
            toastGui.n_1700_B(new RecipeToast(recipeIn));
        } else {
            recipetoast.n_1700_B(recipeIn);
        }
    }
}


