/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntListIterator
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import lightning.product.Container;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.c_3692_q;
import lightning.product.Recipe;
import lightning.product.r_4432_i;
import lightning.product.RecipeBookMenu;

public class ServerPlaceSmeltingRecipe<C extends Container>
extends c_3692_q<C> {
    private boolean P_1922_E;

    public ServerPlaceSmeltingRecipe(RecipeBookMenu<C> recipeBookContainer) {
        super(recipeBookContainer);
    }

    @Override
    protected void n_1700_B(Recipe<C> recipe, boolean placeAll) {
        Z_1993_T itemstack;
        this.P_1922_E = this.G_564_y.n_1700_B(recipe);
        int i = this.J_1907_R.J_1907_R(recipe, null);
        if (this.P_1922_E && ((itemstack = this.G_564_y.n_1700_B(0).n_1700_B()).n_1700_B() || i <= itemstack.t_4043_B())) {
            return;
        }
        IntArrayList intlist = new IntArrayList();
        int j = this.n_1700_B(placeAll, i, this.P_1922_E);
        if (this.J_1907_R.n_1700_B(recipe, (IntList)intlist, j)) {
            if (!this.P_1922_E) {
                this.n_1700_B(this.G_564_y.J_1907_R());
                this.n_1700_B(0);
            }
            this.n_1700_B(j, (IntList)intlist);
        }
    }

    @Override
    protected void n_1700_B() {
        this.n_1700_B(this.G_564_y.J_1907_R());
        super.n_1700_B();
    }

    protected void n_1700_B(int p_201516_1_, IntList p_201516_2_) {
        IntListIterator iterator = p_201516_2_.iterator();
        Slot slot = this.G_564_y.n_1700_B(0);
        Z_1993_T itemstack = r_4432_i.n_1700_B((Integer)iterator.next());
        if (!itemstack.n_1700_B()) {
            int i = Math.min(itemstack.R_4764_Y(), p_201516_1_);
            if (this.P_1922_E) {
                i -= slot.n_1700_B().t_4043_B();
            }
            for (int j = 0; j < i; ++j) {
                this.n_1700_B(slot, itemstack);
            }
        }
    }
}


