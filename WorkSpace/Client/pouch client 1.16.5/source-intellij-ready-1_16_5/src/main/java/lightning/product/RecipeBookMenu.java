/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.I_3887_a;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.a_2900_S;
import lightning.product.c_3692_q;
import lightning.product.Recipe;
import lightning.product.r_4432_i;

public abstract class RecipeBookMenu<C extends Container>
extends a_2900_S {
    public RecipeBookMenu(MenuType<?> type, int id) {
        super(type, id);
    }

    public void n_1700_B(boolean p_217056_1_, Recipe<?> p_217056_2_, B_4088_l player) {
        new c_3692_q(this).n_1700_B(player, p_217056_2_, p_217056_1_);
    }

    public abstract void n_1700_B(r_4432_i var1);

    public abstract void n_1700_B();

    public abstract boolean n_1700_B(Recipe<? super C> var1);

    public abstract int J_1907_R();

    public abstract int R_4764_Y();

    public abstract int G_564_y();

    public abstract int P_1922_E();

    public abstract I_3887_a t_148_a();
}


