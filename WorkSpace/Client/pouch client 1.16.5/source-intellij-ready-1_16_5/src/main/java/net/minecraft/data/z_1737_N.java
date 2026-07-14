/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
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
import lightning.product.r_109_r;
import lightning.product.RequirementsStrategy;
import lightning.product.u_3578_p;
import net.minecraft.data.C_2741_M;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class z_1737_N {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final q_1613_l J_1907_R;
    private final int R_4764_Y;
    private final List<b_3278_X> G_564_y = Lists.newArrayList();
    private final A_2629_w.n_1700_B P_1922_E = A_2629_w.n_1700_B.n_1700_B();
    private String u_1723_Y;

    public z_1737_N(q_1803_e resultIn, int countIn) {
        this.J_1907_R = resultIn.u_1723_Y();
        this.R_4764_Y = countIn;
    }

    public static z_1737_N n_1700_B(q_1803_e resultIn) {
        return new z_1737_N(resultIn, 1);
    }

    public static z_1737_N n_1700_B(q_1803_e resultIn, int countIn) {
        return new z_1737_N(resultIn, countIn);
    }

    public z_1737_N n_1700_B(r_109_r<q_1613_l> tagIn) {
        return this.n_1700_B(b_3278_X.n_1700_B(tagIn));
    }

    public z_1737_N J_1907_R(q_1803_e itemIn) {
        return this.J_1907_R(itemIn, 1);
    }

    public z_1737_N J_1907_R(q_1803_e itemIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.n_1700_B(b_3278_X.n_1700_B(itemIn));
        }
        return this;
    }

    public z_1737_N n_1700_B(b_3278_X ingredientIn) {
        return this.n_1700_B(ingredientIn, 1);
    }

    public z_1737_N n_1700_B(b_3278_X ingredientIn, int quantity) {
        for (int i = 0; i < quantity; ++i) {
            this.G_564_y.add(ingredientIn);
        }
        return this;
    }

    public z_1737_N n_1700_B(String name, h_1723_G criterionIn) {
        this.P_1922_E.n_1700_B(name, criterionIn);
        return this;
    }

    public z_1737_N n_1700_B(String groupIn) {
        this.u_1723_Y = groupIn;
        return this;
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn) {
        this.n_1700_B(consumerIn, V_3137_a.e_2887_G.J_1907_R(this.J_1907_R));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, String save) {
        g_2336_b resourcelocation = V_3137_a.e_2887_G.J_1907_R(this.J_1907_R);
        if (new g_2336_b(save).equals(resourcelocation)) {
            throw new IllegalStateException("Shapeless Recipe " + save + " should remove its 'save' argument");
        }
        this.n_1700_B(consumerIn, new g_2336_b(save));
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, g_2336_b id) {
        this.n_1700_B(id);
        this.P_1922_E.n_1700_B(new g_2336_b("recipes/root")).n_1700_B("has_the_recipe", u_3578_p.n_1700_B(id)).n_1700_B(J_1008_m.n_1700_B.n_1700_B(id)).n_1700_B(RequirementsStrategy.J_1907_R);
        consumerIn.accept(new n_1700_B(id, this.J_1907_R, this.R_4764_Y, this.u_1723_Y == null ? "" : this.u_1723_Y, this.G_564_y, this.P_1922_E, new g_2336_b(id.R_4764_Y(), "recipes/" + this.J_1907_R.w_1457_N().J_1907_R() + "/" + id.J_1907_R())));
    }

    private void n_1700_B(g_2336_b id) {
        if (this.P_1922_E.R_4764_Y().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(id));
        }
    }

    public static class n_1700_B
    implements C_2741_M {
        private final g_2336_b n_1700_B;
        private final q_1613_l J_1907_R;
        private final int R_4764_Y;
        private final String G_564_y;
        private final List<b_3278_X> P_1922_E;
        private final A_2629_w.n_1700_B u_1723_Y;
        private final g_2336_b v_4262_N;

        public n_1700_B(g_2336_b idIn, q_1613_l resultIn, int countIn, String groupIn, List<b_3278_X> ingredientsIn, A_2629_w.n_1700_B advancementBuilderIn, g_2336_b advancementIdIn) {
            this.n_1700_B = idIn;
            this.J_1907_R = resultIn;
            this.R_4764_Y = countIn;
            this.G_564_y = groupIn;
            this.P_1922_E = ingredientsIn;
            this.u_1723_Y = advancementBuilderIn;
            this.v_4262_N = advancementIdIn;
        }

        @Override
        public void n_1700_B(JsonObject json) {
            if (!this.G_564_y.isEmpty()) {
                json.addProperty("group", this.G_564_y);
            }
            JsonArray jsonarray = new JsonArray();
            for (b_3278_X ingredient : this.P_1922_E) {
                jsonarray.add(ingredient.R_4764_Y());
            }
            json.add("ingredients", (JsonElement)jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.J_1907_R).toString());
            if (this.R_4764_Y > 1) {
                jsonobject.addProperty("count", (Number)this.R_4764_Y);
            }
            json.add("result", (JsonElement)jsonobject);
        }

        @Override
        public RecipeSerializer<?> n_1700_B() {
            return RecipeSerializer.J_1907_R;
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
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


