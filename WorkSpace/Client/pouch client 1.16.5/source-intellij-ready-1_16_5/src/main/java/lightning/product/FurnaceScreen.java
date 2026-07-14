/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_4924_i;
import lightning.product.SmeltingRecipeBookComponent;
import lightning.product.W_3491_f;
import lightning.product.g_2336_b;
import lightning.product.g_4614_N;
import lightning.product.x_282_a;

public class FurnaceScreen
extends J_4924_i<g_4614_N> {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/container/furnace.png");

    public FurnaceScreen(g_4614_N container, W_3491_f playerInventory, x_282_a title) {
        super(container, new SmeltingRecipeBookComponent(), playerInventory, title, J_1907_R);
    }
}


