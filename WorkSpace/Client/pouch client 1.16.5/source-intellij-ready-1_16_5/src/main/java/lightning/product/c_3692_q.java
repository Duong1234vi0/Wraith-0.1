/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  it.unimi.dsi.fastutil.ints.IntListIterator
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.Container;
import lightning.product.CraftingMenu;
import lightning.product.W_1158_a;
import lightning.product.W_3491_f;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Recipe;
import lightning.product.PlaceRecipe;
import lightning.product.r_4432_i;
import lightning.product.y_6_Q;
import lightning.product.RecipeBookMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_3692_q<C extends Container>
implements PlaceRecipe<Integer> {
    protected static final Logger n_1700_B = LogManager.getLogger();
    protected final r_4432_i J_1907_R = new r_4432_i();
    protected W_3491_f R_4764_Y;
    protected RecipeBookMenu<C> G_564_y;

    public c_3692_q(RecipeBookMenu<C> recipeBookContainer) {
        this.G_564_y = recipeBookContainer;
    }

    public void n_1700_B(B_4088_l player, @Nullable Recipe<C> recipe, boolean placeAll) {
        if (recipe != null && player.d_2427_y().J_1907_R(recipe)) {
            this.R_4764_Y = player.l_1268_F;
            if (this.J_1907_R() || player.G_624_v()) {
                this.J_1907_R.n_1700_B();
                player.l_1268_F.n_1700_B(this.J_1907_R);
                this.G_564_y.n_1700_B(this.J_1907_R);
                if (this.J_1907_R.n_1700_B(recipe, null)) {
                    this.n_1700_B(recipe, placeAll);
                } else {
                    this.n_1700_B();
                    player.n_1700_B.n_1700_B(new W_1158_a(player.H_1873_g.u_1723_Y, recipe));
                }
                player.l_1268_F.J_1907_R();
            }
        }
    }

    protected void n_1700_B() {
        for (int i = 0; i < this.G_564_y.R_4764_Y() * this.G_564_y.G_564_y() + 1; ++i) {
            if (i == this.G_564_y.J_1907_R() && (this.G_564_y instanceof CraftingMenu || this.G_564_y instanceof y_6_Q)) continue;
            this.n_1700_B(i);
        }
        this.G_564_y.n_1700_B();
    }

    protected void n_1700_B(int slotIn) {
        Z_1993_T itemstack = this.G_564_y.n_1700_B(slotIn).n_1700_B();
        if (!itemstack.n_1700_B()) {
            while (itemstack.t_4043_B() > 0) {
                int i = this.R_4764_Y.G_564_y(itemstack);
                if (i == -1) {
                    i = this.R_4764_Y.P_1922_E();
                }
                Z_1993_T itemstack1 = itemstack.t_148_a();
                itemstack1.P_1922_E(1);
                if (!this.R_4764_Y.n_1700_B(i, itemstack1)) {
                    n_1700_B.error("Can't find any space for item in the inventory");
                }
                this.G_564_y.n_1700_B(slotIn).n_1700_B(1);
            }
        }
    }

    protected void n_1700_B(Recipe<C> recipe, boolean placeAll) {
        int j1;
        IntArrayList intlist;
        boolean flag = this.G_564_y.n_1700_B(recipe);
        int i = this.J_1907_R.J_1907_R(recipe, null);
        if (flag) {
            for (int j = 0; j < this.G_564_y.G_564_y() * this.G_564_y.R_4764_Y() + 1; ++j) {
                Z_1993_T itemstack;
                if (j == this.G_564_y.J_1907_R() || (itemstack = this.G_564_y.n_1700_B(j).n_1700_B()).n_1700_B() || Math.min(i, itemstack.R_4764_Y()) >= itemstack.t_4043_B() + 1) continue;
                return;
            }
        }
        if (this.J_1907_R.n_1700_B(recipe, (IntList)(intlist = new IntArrayList()), j1 = this.n_1700_B(placeAll, i, flag))) {
            int k = j1;
            IntListIterator intListIterator = intlist.iterator();
            while (intListIterator.hasNext()) {
                int l = (Integer)intListIterator.next();
                int i1 = r_4432_i.n_1700_B(l).R_4764_Y();
                if (i1 >= k) continue;
                k = i1;
            }
            if (this.J_1907_R.n_1700_B(recipe, (IntList)intlist, k)) {
                this.n_1700_B();
                this.n_1700_B(this.G_564_y.R_4764_Y(), this.G_564_y.G_564_y(), this.G_564_y.J_1907_R(), recipe, intlist.iterator(), k);
            }
        }
    }

    @Override
    public void n_1700_B(Iterator<Integer> ingredients, int slotIn, int maxAmount, int y, int x) {
        Slot slot = this.G_564_y.n_1700_B(slotIn);
        Z_1993_T itemstack = r_4432_i.n_1700_B(ingredients.next());
        if (!itemstack.n_1700_B()) {
            for (int i = 0; i < maxAmount; ++i) {
                this.n_1700_B(slot, itemstack);
            }
        }
    }

    protected int n_1700_B(boolean placeAll, int maxPossible, boolean recipeMatches) {
        int i = 1;
        if (placeAll) {
            i = maxPossible;
        } else if (recipeMatches) {
            i = 64;
            for (int j = 0; j < this.G_564_y.R_4764_Y() * this.G_564_y.G_564_y() + 1; ++j) {
                Z_1993_T itemstack;
                if (j == this.G_564_y.J_1907_R() || (itemstack = this.G_564_y.n_1700_B(j).n_1700_B()).n_1700_B() || i <= itemstack.t_4043_B()) continue;
                i = itemstack.t_4043_B();
            }
            if (i < 64) {
                ++i;
            }
        }
        return i;
    }

    protected void n_1700_B(Slot slotToFill, Z_1993_T ingredientIn) {
        Z_1993_T itemstack;
        int i = this.R_4764_Y.R_4764_Y(ingredientIn);
        if (i != -1 && !(itemstack = this.R_4764_Y.s_956_w(i).t_148_a()).n_1700_B()) {
            if (itemstack.t_4043_B() > 1) {
                this.R_4764_Y.n_1700_B(i, 1);
            } else {
                this.R_4764_Y.u_2550_I(i);
            }
            itemstack.P_1922_E(1);
            if (slotToFill.n_1700_B().n_1700_B()) {
                slotToFill.J_1907_R(itemstack);
            } else {
                slotToFill.n_1700_B().u_1723_Y(1);
            }
        }
    }

    private boolean J_1907_R() {
        ArrayList list = Lists.newArrayList();
        int i = this.R_4764_Y();
        for (int j = 0; j < this.G_564_y.R_4764_Y() * this.G_564_y.G_564_y() + 1; ++j) {
            Z_1993_T itemstack;
            if (j == this.G_564_y.J_1907_R() || (itemstack = this.G_564_y.n_1700_B(j).n_1700_B().t_148_a()).n_1700_B()) continue;
            int k = this.R_4764_Y.G_564_y(itemstack);
            if (k == -1 && list.size() <= i) {
                for (Z_1993_T itemstack1 : list) {
                    if (!itemstack1.n_1700_B(itemstack) || itemstack1.t_4043_B() == itemstack1.R_4764_Y() || itemstack1.t_4043_B() + itemstack.t_4043_B() > itemstack1.R_4764_Y()) continue;
                    itemstack1.u_1723_Y(itemstack.t_4043_B());
                    itemstack.P_1922_E(0);
                    break;
                }
                if (itemstack.n_1700_B()) continue;
                if (list.size() >= i) {
                    return false;
                }
                list.add(itemstack);
                continue;
            }
            if (k != -1) continue;
            return false;
        }
        return true;
    }

    private int R_4764_Y() {
        int i = 0;
        for (Z_1993_T itemstack : this.R_4764_Y.n_1700_B) {
            if (!itemstack.n_1700_B()) continue;
            ++i;
        }
        return i;
    }
}


