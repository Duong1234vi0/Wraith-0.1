/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.J_1008_m;
import lightning.product.AbstractCookingRecipe;
import lightning.product.V_3137_a;
import lightning.product.b_3278_X;
import lightning.product.e_2674_c;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.RequirementsStrategy;
import lightning.product.u_3578_p;
import net.minecraft.data.C_2741_M;

public class P_4830_p {
    private final q_1613_l n_1700_B;
    private final b_3278_X J_1907_R;
    private final float R_4764_Y;
    private final int G_564_y;
    private final A_2629_w.n_1700_B P_1922_E = A_2629_w.n_1700_B.n_1700_B();
    private String u_1723_Y;
    private final e_2674_c<?> v_4262_N;

    private P_4830_p(q_1803_e resultIn, b_3278_X ingredientIn, float experienceIn, int cookingTimeIn, e_2674_c<?> serializer) {
        this.n_1700_B = resultIn.u_1723_Y();
        this.J_1907_R = ingredientIn;
        this.R_4764_Y = experienceIn;
        this.G_564_y = cookingTimeIn;
        this.v_4262_N = serializer;
    }

    public static P_4830_p n_1700_B(b_3278_X ingredientIn, q_1803_e resultIn, float experienceIn, int cookingTimeIn, e_2674_c<?> serializer) {
        return new P_4830_p(resultIn, ingredientIn, experienceIn, cookingTimeIn, serializer);
    }

    public static P_4830_p n_1700_B(b_3278_X ingredientIn, q_1803_e resultIn, float experienceIn, int cookingTimeIn) {
        return P_4830_p.n_1700_B(ingredientIn, resultIn, experienceIn, cookingTimeIn, RecipeSerializer.t_1786_h);
    }

    public static P_4830_p J_1907_R(b_3278_X ingredientIn, q_1803_e resultIn, float experienceIn, int cookingTimeIn) {
        return P_4830_p.n_1700_B(ingredientIn, resultIn, experienceIn, cookingTimeIn, RecipeSerializer.M_182_A);
    }

    public P_4830_p n_1700_B(String name, h_1723_G criterionIn) {
        this.P_1922_E.n_1700_B(name, criterionIn);
        return this;
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn) {
        this.n_1700_B(consumerIn, V_3137_a.e_2887_G.J_1907_R(this.n_1700_B));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, String save) {
        g_2336_b resourcelocation1 = new g_2336_b(save);
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(this.n_1700_B);
        if (resourcelocation1.equals(resourcelocation)) {
            throw new IllegalStateException("Recipe " + String.valueOf(resourcelocation1) + " should remove its 'save' argument");
        }
        this.n_1700_B(consumerIn, resourcelocation1);
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, g_2336_b id) {
        this.n_1700_B(id);
        this.P_1922_E.n_1700_B(new g_2336_b("recipes/root")).n_1700_B("has_the_recipe", u_3578_p.n_1700_B(id)).n_1700_B(J_1008_m.n_1700_B.n_1700_B(id)).n_1700_B(RequirementsStrategy.J_1907_R);
        consumerIn.accept(new n_1700_B(id, this.u_1723_Y == null ? "" : this.u_1723_Y, this.J_1907_R, this.n_1700_B, this.R_4764_Y, this.G_564_y, this.P_1922_E, new g_2336_b(id.R_4764_Y(), "recipes/" + this.n_1700_B.w_1457_N().J_1907_R() + "/" + id.J_1907_R()), this.v_4262_N));
    }

    private void n_1700_B(g_2336_b id) {
        if (this.P_1922_E.R_4764_Y().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(id));
        }
    }

    public static class n_1700_B
    implements C_2741_M {
        private final g_2336_b n_1700_B;
        private final String J_1907_R;
        private final b_3278_X R_4764_Y;
        private final q_1613_l G_564_y;
        private final float P_1922_E;
        private final int u_1723_Y;
        private final A_2629_w.n_1700_B v_4262_N;
        private final g_2336_b w_1484_f;
        private final RecipeSerializer<? extends AbstractCookingRecipe> t_148_a;

        public n_1700_B(g_2336_b idIn, String groupIn, b_3278_X ingredientIn, q_1613_l resultIn, float experienceIn, int cookingTimeIn, A_2629_w.n_1700_B advancementBuilderIn, g_2336_b advancementIdIn, RecipeSerializer<? extends AbstractCookingRecipe> serializerIn) {
            this.n_1700_B = idIn;
            this.J_1907_R = groupIn;
            this.R_4764_Y = ingredientIn;
            this.G_564_y = resultIn;
            this.P_1922_E = experienceIn;
            this.u_1723_Y = cookingTimeIn;
            this.v_4262_N = advancementBuilderIn;
            this.w_1484_f = advancementIdIn;
            this.t_148_a = serializerIn;
        }

        @Override
        public void n_1700_B(JsonObject json) {
            if (!this.J_1907_R.isEmpty()) {
                json.addProperty("group", this.J_1907_R);
            }
            json.add("ingredient", this.R_4764_Y.R_4764_Y());
            json.addProperty("result", V_3137_a.e_2887_G.J_1907_R(this.G_564_y).toString());
            json.addProperty("experience", (Number)Float.valueOf(this.P_1922_E));
            json.addProperty("cookingtime", (Number)this.u_1723_Y);
        }

        @Override
        public RecipeSerializer<?> n_1700_B() {
            return this.t_148_a;
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
        }

        @Override
        @Nullable
        public JsonObject R_4764_Y() {
            return this.v_4262_N.J_1907_R();
        }

        @Override
        @Nullable
        public g_2336_b G_564_y() {
            return this.w_1484_f;
        }
    }
}


