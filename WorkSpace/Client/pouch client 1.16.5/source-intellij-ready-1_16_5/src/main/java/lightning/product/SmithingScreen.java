/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.ItemCombinerScreen;
import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.SmithingMenu;
import lightning.product.x_282_a;

public class SmithingScreen
extends ItemCombinerScreen<SmithingMenu> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/smithing.png");

    public SmithingScreen(SmithingMenu container, W_3491_f playerInventory, x_282_a title) {
        super(container, playerInventory, title, n_1700_B);
        this.u_2550_I = 60;
        this.M_588_G = 18;
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, int x, int y) {
        c_4037_x.Y_259_p();
        super.n_1700_B(matrixStack, x, y);
    }
}


