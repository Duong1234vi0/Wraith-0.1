/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.I_3887_a;
import lightning.product.RecipeBookSettings;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.RecipeBookMenu;

public class U_1907_s {
    protected final Set<g_2336_b> n_1700_B = Sets.newHashSet();
    protected final Set<g_2336_b> J_1907_R = Sets.newHashSet();
    private final RecipeBookSettings R_4764_Y = new RecipeBookSettings();

    public void n_1700_B(U_1907_s that) {
        this.n_1700_B.clear();
        this.J_1907_R.clear();
        this.R_4764_Y.n_1700_B(that.R_4764_Y);
        this.n_1700_B.addAll(that.n_1700_B);
        this.J_1907_R.addAll(that.J_1907_R);
    }

    public void n_1700_B(Recipe<?> recipe) {
        if (!recipe.t_148_a()) {
            this.n_1700_B(recipe.u_1723_Y());
        }
    }

    protected void n_1700_B(g_2336_b resourceLocation) {
        this.n_1700_B.add(resourceLocation);
    }

    public boolean J_1907_R(@Nullable Recipe<?> recipe) {
        return recipe == null ? false : this.n_1700_B.contains(recipe.u_1723_Y());
    }

    public boolean J_1907_R(g_2336_b id) {
        return this.n_1700_B.contains(id);
    }

    public void R_4764_Y(Recipe<?> recipe) {
        this.R_4764_Y(recipe.u_1723_Y());
    }

    protected void R_4764_Y(g_2336_b resourceLocation) {
        this.n_1700_B.remove(resourceLocation);
        this.J_1907_R.remove(resourceLocation);
    }

    public boolean G_564_y(Recipe<?> recipe) {
        return this.J_1907_R.contains(recipe.u_1723_Y());
    }

    public void P_1922_E(Recipe<?> recipe) {
        this.J_1907_R.remove(recipe.u_1723_Y());
    }

    public void u_1723_Y(Recipe<?> recipe) {
        this.G_564_y(recipe.u_1723_Y());
    }

    protected void G_564_y(g_2336_b resourceLocation) {
        this.J_1907_R.add(resourceLocation);
    }

    public boolean n_1700_B(I_3887_a p_242142_1_) {
        return this.R_4764_Y.n_1700_B(p_242142_1_);
    }

    public void n_1700_B(I_3887_a p_242143_1_, boolean p_242143_2_) {
        this.R_4764_Y.n_1700_B(p_242143_1_, p_242143_2_);
    }

    public boolean n_1700_B(RecipeBookMenu<?> p_242141_1_) {
        return this.J_1907_R(p_242141_1_.t_148_a());
    }

    public boolean J_1907_R(I_3887_a p_242145_1_) {
        return this.R_4764_Y.J_1907_R(p_242145_1_);
    }

    public void J_1907_R(I_3887_a p_242146_1_, boolean p_242146_2_) {
        this.R_4764_Y.J_1907_R(p_242146_1_, p_242146_2_);
    }

    public void n_1700_B(RecipeBookSettings p_242140_1_) {
        this.R_4764_Y.n_1700_B(p_242140_1_);
    }

    public RecipeBookSettings J_1907_R() {
        return this.R_4764_Y.n_1700_B();
    }

    public void n_1700_B(I_3887_a p_242144_1_, boolean p_242144_2_, boolean p_242144_3_) {
        this.R_4764_Y.n_1700_B(p_242144_1_, p_242144_2_);
        this.R_4764_Y.J_1907_R(p_242144_1_, p_242144_3_);
    }
}


