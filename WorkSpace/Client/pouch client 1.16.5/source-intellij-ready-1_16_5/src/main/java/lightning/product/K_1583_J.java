/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lightning.product.Container;
import lightning.product.M_996_h;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_2585_i;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.RecipeSerializer;

public class K_1583_J
implements Recipe<Container> {
    private final b_3278_X n_1700_B;
    private final b_3278_X J_1907_R;
    private final Z_1993_T R_4764_Y;
    private final g_2336_b G_564_y;

    public K_1583_J(g_2336_b recipeId, b_3278_X base, b_3278_X addition, Z_1993_T result) {
        this.G_564_y = recipeId;
        this.n_1700_B = base;
        this.J_1907_R = addition;
        this.R_4764_Y = result;
    }

    @Override
    public boolean n_1700_B(Container inv, b_4507_u worldIn) {
        return this.n_1700_B.n_1700_B(inv.s_956_w(0)) && this.J_1907_R.n_1700_B(inv.s_956_w(1));
    }

    @Override
    public Z_1993_T n_1700_B(Container inv) {
        Z_1993_T itemstack = this.R_4764_Y.t_148_a();
        U_2912_j compoundnbt = inv.s_956_w(0).Q_4569_t();
        if (compoundnbt != null) {
            itemstack.R_4764_Y(compoundnbt.v_4262_N());
        }
        return itemstack;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return this.R_4764_Y;
    }

    public boolean n_1700_B(Z_1993_T addition) {
        return this.J_1907_R.n_1700_B(addition);
    }

    @Override
    public Z_1993_T w_1484_f() {
        return new Z_1993_T(a_3742_W.i_4833_u);
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.G_564_y;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.Y_259_p;
    }

    @Override
    public RecipeType<?> v_4262_N() {
        return RecipeType.v_4262_N;
    }

    public static class n_1700_B
    implements RecipeSerializer<K_1583_J> {
        public K_1583_J n_1700_B(g_2336_b recipeId, JsonObject json) {
            b_3278_X ingredient = b_3278_X.n_1700_B((JsonElement)i_4431_W.M_588_G(json, "base"));
            b_3278_X ingredient1 = b_3278_X.n_1700_B((JsonElement)i_4431_W.M_588_G(json, "addition"));
            Z_1993_T itemstack = M_996_h.n_1700_B(i_4431_W.M_588_G(json, "result"));
            return new K_1583_J(recipeId, ingredient, ingredient1, itemstack);
        }

        public K_1583_J n_1700_B(g_2336_b recipeId, b_2585_i buffer) {
            b_3278_X ingredient = b_3278_X.J_1907_R(buffer);
            b_3278_X ingredient1 = b_3278_X.J_1907_R(buffer);
            Z_1993_T itemstack = buffer.u_2550_I();
            return new K_1583_J(recipeId, ingredient, ingredient1, itemstack);
        }

        @Override
        public void n_1700_B(b_2585_i buffer, K_1583_J recipe) {
            recipe.n_1700_B.n_1700_B(buffer);
            recipe.J_1907_R.n_1700_B(buffer);
            buffer.n_1700_B(recipe.R_4764_Y);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, b_2585_i b_2585_i2) {
            return this.n_1700_B(g_2336_b2, b_2585_i2);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, JsonObject jsonObject) {
            return this.n_1700_B(g_2336_b2, jsonObject);
        }
    }
}


