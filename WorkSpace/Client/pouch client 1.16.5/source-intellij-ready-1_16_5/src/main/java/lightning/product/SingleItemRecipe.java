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
import lightning.product.NonNullList;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.b_3278_X;
import lightning.product.g_2336_b;
import lightning.product.RecipeType;
import lightning.product.Recipe;
import lightning.product.i_4431_W;
import lightning.product.RecipeSerializer;

public abstract class SingleItemRecipe
implements Recipe<Container> {
    protected final b_3278_X n_1700_B;
    protected final Z_1993_T J_1907_R;
    private final RecipeType<?> P_1922_E;
    private final RecipeSerializer<?> u_1723_Y;
    protected final g_2336_b R_4764_Y;
    protected final String G_564_y;

    public SingleItemRecipe(RecipeType<?> type, RecipeSerializer<?> serializer, g_2336_b id, String group, b_3278_X ingredient, Z_1993_T result) {
        this.P_1922_E = type;
        this.u_1723_Y = serializer;
        this.R_4764_Y = id;
        this.G_564_y = group;
        this.n_1700_B = ingredient;
        this.J_1907_R = result;
    }

    @Override
    public RecipeType<?> v_4262_N() {
        return this.P_1922_E;
    }

    @Override
    public RecipeSerializer<?> E_() {
        return this.u_1723_Y;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.R_4764_Y;
    }

    @Override
    public String G_564_y() {
        return this.G_564_y;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return this.J_1907_R;
    }

    @Override
    public NonNullList<b_3278_X> n_1700_B() {
        NonNullList<b_3278_X> nonnulllist = NonNullList.n_1700_B();
        nonnulllist.add(this.n_1700_B);
        return nonnulllist;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return true;
    }

    @Override
    public Z_1993_T n_1700_B(Container inv) {
        return this.J_1907_R.t_148_a();
    }

    public static class lightning.product.SingleItemRecipe$n_1700_B<T extends SingleItemRecipe>
    implements RecipeSerializer<T> {
        final n_1700_B<T> Q_2552_b;

        protected lightning.product.SingleItemRecipe$n_1700_B(n_1700_B<T> factory) {
            this.Q_2552_b = factory;
        }

        public T n_1700_B(g_2336_b recipeId, JsonObject json) {
            String s = i_4431_W.n_1700_B(json, "group", "");
            b_3278_X ingredient = i_4431_W.R_4764_Y(json, "ingredient") ? b_3278_X.n_1700_B((JsonElement)i_4431_W.P_4830_p(json, "ingredient")) : b_3278_X.n_1700_B((JsonElement)i_4431_W.M_588_G(json, "ingredient"));
            String s1 = i_4431_W.u_1723_Y(json, "result");
            int i = i_4431_W.u_2550_I(json, "count");
            Z_1993_T itemstack = new Z_1993_T(V_3137_a.e_2887_G.n_1700_B(new g_2336_b(s1)), i);
            return this.Q_2552_b.create(recipeId, s, ingredient, itemstack);
        }

        public T n_1700_B(g_2336_b recipeId, b_2585_i buffer) {
            String s = buffer.P_1922_E(Short.MAX_VALUE);
            b_3278_X ingredient = b_3278_X.J_1907_R(buffer);
            Z_1993_T itemstack = buffer.u_2550_I();
            return this.Q_2552_b.create(recipeId, s, ingredient, itemstack);
        }

        @Override
        public void n_1700_B(b_2585_i buffer, T recipe) {
            buffer.n_1700_B(((SingleItemRecipe)recipe).G_564_y);
            ((SingleItemRecipe)recipe).n_1700_B.n_1700_B(buffer);
            buffer.n_1700_B(((SingleItemRecipe)recipe).J_1907_R);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, b_2585_i b_2585_i2) {
            return this.n_1700_B(g_2336_b2, b_2585_i2);
        }

        @Override
        public /* synthetic */ Recipe J_1907_R(g_2336_b g_2336_b2, JsonObject jsonObject) {
            return this.n_1700_B(g_2336_b2, jsonObject);
        }

        static interface n_1700_B<T extends SingleItemRecipe> {
            public T create(g_2336_b var1, String var2, b_3278_X var3, Z_1993_T var4);
        }
    }
}


