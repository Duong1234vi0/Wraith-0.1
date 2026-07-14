/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FormattedText;
import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import lightning.product.x_940_l;
import lightning.product.z_3427_G;

public class DispenserScreen
extends z_3427_G<x_940_l> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/dispenser.png");

    public DispenserScreen(x_940_l container, W_3491_f playerInventory, x_282_a textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    protected void init() {
        super.init();
        this.u_2550_I = (this.t_148_a - this.font.n_1700_B((FormattedText)this.title)) / 2;
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


