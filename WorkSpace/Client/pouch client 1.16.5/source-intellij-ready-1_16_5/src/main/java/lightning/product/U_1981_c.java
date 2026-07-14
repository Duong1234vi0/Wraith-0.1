/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lightning.product.CraftingContainer;
import lightning.product.M_996_h;
import lightning.product.NonNullList;
import lightning.product.Q_4863_g;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.RecipeSerializer;
import lightning.product.r_4432_i;

public class U_1981_c
implements Q_4863_g {
    private final g_2336_b n_1700_B;
    private final String J_1907_R;
    private final Z_1993_T R_4764_Y;
    private final NonNullList<b_3278_X> G_564_y;

    public U_1981_c(g_2336_b idIn, String groupIn, Z_1993_T recipeOutputIn, NonNullList<b_3278_X> recipeItemsIn) {
        this.n_1700_B = idIn;
        this.J_1907_R = groupIn;
        this.R_4764_Y = recipeOutputIn;
        this.G_564_y = recipeItemsIn;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.n_1700_B;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.J_1907_R;
    }

    @Override
    public String G_564_y() {
        return this.J_1907_R;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return this.R_4764_Y;
    }

    @Override
    public NonNullList<b_3278_X> n_1700_B() {
        return this.G_564_y;
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        r_4432_i recipeitemhelper = new r_4432_i();
        int i = 0;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack = inv.s_956_w(j);
            if (itemstack.n_1700_B()) continue;
            ++i;
            recipeitemhelper.n_1700_B(itemstack, 1);
        }
        return i == this.G_564_y.size() && recipeitemhelper.n_1700_B(this, null);
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        return this.R_4764_Y.t_148_a();
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= this.G_564_y.size();
    }

    public static class n_1700_B
    implements RecipeSerializer<U_1981_c> {
        public U_1981_c n_1700_B(g_2336_b recipeId, JsonObject json) {
            String s = i_4431_W.n_1700_B(json, "group", "");
            NonNullList<b_3278_X> nonnulllist = lightning.product.U_1981_c$n_1700_B.n_1700_B(i_4431_W.P_4830_p(json, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            }
            if (nonnulllist.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            }
            Z_1993_T itemstack = M_996_h.n_1700_B(i_4431_W.M_588_G(json, "result"));
            return new U_1981_c(recipeId, s, itemstack, nonnulllist);
        }

        private static NonNullList<b_3278_X> n_1700_B(JsonArray ingredientArray) {
            NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B();
            for (int i = 0; i < ingredientArray.size(); ++i) {
                b_3278_X ingredient = b_3278_X.n_1700_B(ingredientArray.get(i));
                if (ingredient.G_564_y()) continue;
                nonnulllist.add(ingredient);
            }
            return nonnulllist;
        }

        public U_1981_c n_1700_B(g_2336_b recipeId, b_2585_i buffer) {
            String s = buffer.P_1922_E(Short.MAX_VALUE);
            int i = buffer.u_1723_Y();
            NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B(i, b_3278_X.n_1700_B);
            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, b_3278_X.J_1907_R(buffer));
            }
            Z_1993_T itemstack = buffer.u_2550_I();
            return new U_1981_c(recipeId, s, itemstack, nonnulllist);
        }

        @Override
        public void n_1700_B(b_2585_i buffer, U_1981_c recipe) {
            buffer.n_1700_B(recipe.J_1907_R);
            buffer.G_564_y(recipe.G_564_y.size());
            for (b_3278_X ingredient : recipe.G_564_y) {
                ingredient.n_1700_B(buffer);
            }
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


