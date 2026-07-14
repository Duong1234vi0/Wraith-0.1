/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import com.google.gson.JsonElement;
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
import lightning.product.RequirementsStrategy;
import lightning.product.u_3578_p;
import net.minecraft.data.C_2741_M;

public class d_2461_k {
    private final b_3278_X n_1700_B;
    private final b_3278_X J_1907_R;
    private final q_1613_l R_4764_Y;
    private final A_2629_w.n_1700_B G_564_y = A_2629_w.n_1700_B.n_1700_B();
    private final RecipeSerializer<?> P_1922_E;

    public d_2461_k(RecipeSerializer<?> serializer, b_3278_X base, b_3278_X addition, q_1613_l output) {
        this.P_1922_E = serializer;
        this.n_1700_B = base;
        this.J_1907_R = addition;
        this.R_4764_Y = output;
    }

    public static d_2461_k n_1700_B(b_3278_X base, b_3278_X addition, q_1613_l output) {
        return new d_2461_k(RecipeSerializer.Y_259_p, base, addition, output);
    }

    public d_2461_k n_1700_B(String name, h_1723_G criterion) {
        this.G_564_y.n_1700_B(name, criterion);
        return this;
    }

    public void n_1700_B(Consumer<C_2741_M> consumer, String id) {
        this.n_1700_B(consumer, new g_2336_b(id));
    }

    public void n_1700_B(Consumer<C_2741_M> recipe, g_2336_b id) {
        this.n_1700_B(id);
        this.G_564_y.n_1700_B(new g_2336_b("recipes/root")).n_1700_B("has_the_recipe", u_3578_p.n_1700_B(id)).n_1700_B(J_1008_m.n_1700_B.n_1700_B(id)).n_1700_B(RequirementsStrategy.J_1907_R);
        recipe.accept(new n_1700_B(id, this.P_1922_E, this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, new g_2336_b(id.R_4764_Y(), "recipes/" + this.R_4764_Y.w_1457_N().J_1907_R() + "/" + id.J_1907_R())));
    }

    private void n_1700_B(g_2336_b id) {
        if (this.G_564_y.R_4764_Y().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(id));
        }
    }

    public static class n_1700_B
    implements C_2741_M {
        private final g_2336_b n_1700_B;
        private final b_3278_X J_1907_R;
        private final b_3278_X R_4764_Y;
        private final q_1613_l G_564_y;
        private final A_2629_w.n_1700_B P_1922_E;
        private final g_2336_b u_1723_Y;
        private final RecipeSerializer<?> v_4262_N;

        public n_1700_B(g_2336_b id, RecipeSerializer<?> serializer, b_3278_X base, b_3278_X addition, q_1613_l output, A_2629_w.n_1700_B advancementBuilder, g_2336_b advancementId) {
            this.n_1700_B = id;
            this.v_4262_N = serializer;
            this.J_1907_R = base;
            this.R_4764_Y = addition;
            this.G_564_y = output;
            this.P_1922_E = advancementBuilder;
            this.u_1723_Y = advancementId;
        }

        @Override
        public void n_1700_B(JsonObject json) {
            json.add("base", this.J_1907_R.R_4764_Y());
            json.add("addition", this.R_4764_Y.R_4764_Y());
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.G_564_y).toString());
            json.add("result", (JsonElement)jsonobject);
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
        }

        @Override
        public RecipeSerializer<?> n_1700_B() {
            return this.v_4262_N;
        }

        @Override
        @Nullable
        public JsonObject R_4764_Y() {
            return this.P_1922_E.J_1907_R();
        }

        @Override
        @Nullable
        public g_2336_b G_564_y() {
            return this.u_1723_Y;
        }
    }
}


