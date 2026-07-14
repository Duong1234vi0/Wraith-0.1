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
import lightning.product.V_3137_a;
import lightning.product.b_3278_X;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.RequirementsStrategy;
import lightning.product.u_3578_p;
import net.minecraft.data.C_2741_M;

public class v_4276_D {
    private final q_1613_l n_1700_B;
    private final b_3278_X J_1907_R;
    private final int R_4764_Y;
    private final A_2629_w.n_1700_B G_564_y = A_2629_w.n_1700_B.n_1700_B();
    private String P_1922_E;
    private final RecipeSerializer<?> u_1723_Y;

    public v_4276_D(RecipeSerializer<?> serializerIn, b_3278_X ingredientIn, q_1803_e resultProviderIn, int countIn) {
        this.u_1723_Y = serializerIn;
        this.n_1700_B = resultProviderIn.u_1723_Y();
        this.J_1907_R = ingredientIn;
        this.R_4764_Y = countIn;
    }

    public static v_4276_D n_1700_B(b_3278_X ingredientIn, q_1803_e resultIn) {
        return new v_4276_D(RecipeSerializer.Y_601_j, ingredientIn, resultIn, 1);
    }

    public static v_4276_D n_1700_B(b_3278_X ingredientIn, q_1803_e resultIn, int countIn) {
        return new v_4276_D(RecipeSerializer.Y_601_j, ingredientIn, resultIn, countIn);
    }

    public v_4276_D n_1700_B(String name, h_1723_G criterionIn) {
        this.G_564_y.n_1700_B(name, criterionIn);
        return this;
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, String save) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(this.n_1700_B);
        if (new g_2336_b(save).equals(resourcelocation)) {
            throw new IllegalStateException("Single Item Recipe " + save + " should remove its 'save' argument");
        }
        this.n_1700_B(consumerIn, new g_2336_b(save));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, g_2336_b id) {
        this.n_1700_B(id);
        this.G_564_y.n_1700_B(new g_2336_b("recipes/root")).n_1700_B("has_the_recipe", u_3578_p.n_1700_B(id)).n_1700_B(J_1008_m.n_1700_B.n_1700_B(id)).n_1700_B(RequirementsStrategy.J_1907_R);
        consumerIn.accept(new n_1700_B(id, this.u_1723_Y, this.P_1922_E == null ? "" : this.P_1922_E, this.J_1907_R, this.n_1700_B, this.R_4764_Y, this.G_564_y, new g_2336_b(id.R_4764_Y(), "recipes/" + this.n_1700_B.w_1457_N().J_1907_R() + "/" + id.J_1907_R())));
    }

    private void n_1700_B(g_2336_b id) {
        if (this.G_564_y.R_4764_Y().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(id));
        }
    }

    public static class n_1700_B
    implements C_2741_M {
        private final g_2336_b n_1700_B;
        private final String J_1907_R;
        private final b_3278_X R_4764_Y;
        private final q_1613_l G_564_y;
        private final int P_1922_E;
        private final A_2629_w.n_1700_B u_1723_Y;
        private final g_2336_b v_4262_N;
        private final RecipeSerializer<?> w_1484_f;

        public n_1700_B(g_2336_b idIn, RecipeSerializer<?> serializerIn, String groupIn, b_3278_X ingredientIn, q_1613_l resultIn, int countIn, A_2629_w.n_1700_B advancementBuilderIn, g_2336_b advancementIdIn) {
            this.n_1700_B = idIn;
            this.w_1484_f = serializerIn;
            this.J_1907_R = groupIn;
            this.R_4764_Y = ingredientIn;
            this.G_564_y = resultIn;
            this.P_1922_E = countIn;
            this.u_1723_Y = advancementBuilderIn;
            this.v_4262_N = advancementIdIn;
        }

        @Override
        public void n_1700_B(JsonObject json) {
            if (!this.J_1907_R.isEmpty()) {
                json.addProperty("group", this.J_1907_R);
            }
            json.add("ingredient", this.R_4764_Y.R_4764_Y());
            json.addProperty("result", V_3137_a.e_2887_G.J_1907_R(this.G_564_y).toString());
            json.addProperty("count", (Number)this.P_1922_E);
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
        }

        @Override
        public RecipeSerializer<?> n_1700_B() {
            return this.w_1484_f;
        }

        @Override
        @Nullable
        public JsonObject R_4764_Y() {
            return this.u_1723_Y.J_1907_R();
        }

        @Override
        @Nullable
        public g_2336_b G_564_y() {
            return this.v_4262_N;
        }
    }
}


