/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lightning.product.U_1907_s;
import lightning.product.Z_1993_T;
import lightning.product.Recipe;
import lightning.product.r_4432_i;

public class RecipeCollection {
    private final List<Recipe<?>> n_1700_B;
    private final boolean J_1907_R;
    private final Set<Recipe<?>> R_4764_Y = Sets.newHashSet();
    private final Set<Recipe<?>> G_564_y = Sets.newHashSet();
    private final Set<Recipe<?>> P_1922_E = Sets.newHashSet();

    public RecipeCollection(List<Recipe<?>> p_i242062_1_) {
        this.n_1700_B = ImmutableList.copyOf(p_i242062_1_);
        this.J_1907_R = p_i242062_1_.size() <= 1 ? true : RecipeCollection.n_1700_B(p_i242062_1_);
    }

    private static boolean n_1700_B(List<Recipe<?>> p_243413_0_) {
        int i = p_243413_0_.size();
        Z_1993_T itemstack = p_243413_0_.get(0).R_4764_Y();
        for (int j = 1; j < i; ++j) {
            Z_1993_T itemstack1 = p_243413_0_.get(j).R_4764_Y();
            if (Z_1993_T.R_4764_Y(itemstack, itemstack1) && Z_1993_T.n_1700_B(itemstack, itemstack1)) continue;
            return false;
        }
        return true;
    }

    public boolean n_1700_B() {
        return !this.P_1922_E.isEmpty();
    }

    public void n_1700_B(U_1907_s book) {
        for (Recipe<?> irecipe : this.n_1700_B) {
            if (!book.J_1907_R(irecipe)) continue;
            this.P_1922_E.add(irecipe);
        }
    }

    public void n_1700_B(r_4432_i handler, int width, int height, U_1907_s book) {
        for (Recipe<?> irecipe : this.n_1700_B) {
            boolean flag;
            boolean bl = flag = irecipe.n_1700_B(width, height) && book.J_1907_R(irecipe);
            if (flag) {
                this.G_564_y.add(irecipe);
            } else {
                this.G_564_y.remove(irecipe);
            }
            if (flag && handler.n_1700_B(irecipe, null)) {
                this.R_4764_Y.add(irecipe);
                continue;
            }
            this.R_4764_Y.remove(irecipe);
        }
    }

    public boolean n_1700_B(Recipe<?> recipe) {
        return this.R_4764_Y.contains(recipe);
    }

    public boolean J_1907_R() {
        return !this.R_4764_Y.isEmpty();
    }

    public boolean R_4764_Y() {
        return !this.G_564_y.isEmpty();
    }

    public List<Recipe<?>> G_564_y() {
        return this.n_1700_B;
    }

    public List<Recipe<?>> n_1700_B(boolean onlyCraftable) {
        ArrayList list = Lists.newArrayList();
        Set<Recipe<?>> set = onlyCraftable ? this.R_4764_Y : this.G_564_y;
        for (Recipe<?> irecipe : this.n_1700_B) {
            if (!set.contains(irecipe)) continue;
            list.add(irecipe);
        }
        return list;
    }

    public List<Recipe<?>> J_1907_R(boolean onlyCraftable) {
        ArrayList list = Lists.newArrayList();
        for (Recipe<?> irecipe : this.n_1700_B) {
            if (!this.G_564_y.contains(irecipe) || this.R_4764_Y.contains(irecipe) != onlyCraftable) continue;
            list.add(irecipe);
        }
        return list;
    }

    public boolean P_1922_E() {
        return this.J_1907_R;
    }
}


