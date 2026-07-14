/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_3887_a;
import lightning.product.MenuType;
import lightning.product.Container;
import lightning.product.W_3491_f;
import lightning.product.RecipeType;
import lightning.product.k_902_f;
import lightning.product.ContainerData;

public class B_512_t
extends k_902_f {
    public B_512_t(int id, W_3491_f playerInventory) {
        super(MenuType.Q_2552_b, RecipeType.G_564_y, I_3887_a.G_564_y, id, playerInventory);
    }

    public B_512_t(int id, W_3491_f playerInventory, Container inventory, ContainerData p_i50062_4_) {
        super(MenuType.Q_2552_b, RecipeType.G_564_y, I_3887_a.G_564_y, id, playerInventory, inventory, p_i50062_4_);
    }
}


