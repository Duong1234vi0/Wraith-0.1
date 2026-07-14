/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4498_h;
import lightning.product.ChestMenu;
import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class ContainerScreen
extends z_3427_G<ChestMenu>
implements N_4498_h<ChestMenu> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/generic_54.png");
    private final int J_1907_R;

    public ContainerScreen(ChestMenu container, W_3491_f playerInventory, x_282_a title) {
        super(container, playerInventory, title);
        this.passEvents = false;
        int i = 222;
        int j = 114;
        this.J_1907_R = container.J_1907_R();
        this.s_956_w = 114 + this.J_1907_R * 18;
        this.h_1847_R = this.s_956_w - 94;
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
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.J_1907_R * 18 + 17);
        this.blit(matrixStack, i, j + this.J_1907_R * 18 + 17, 0, 126, this.t_148_a, 96);
    }
}


