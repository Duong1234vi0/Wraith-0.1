/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import lightning.product.x_353_w;
import lightning.product.z_3427_G;

public class ShulkerBoxScreen
extends z_3427_G<x_353_w> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/shulker_box.png");

    public ShulkerBoxScreen(x_353_w screenContainer, W_3491_f inv, x_282_a titleIn) {
        super(screenContainer, inv, titleIn);
        ++this.s_956_w;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
    }
}


