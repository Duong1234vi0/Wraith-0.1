/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_4082_i;
import lightning.product.W_3491_f;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class GrindstoneScreen
extends z_3427_G<F_4082_i> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/grindstone.png");

    public GrindstoneScreen(F_4082_i container, W_3491_f playerInventory, x_282_a textComponent) {
        super(container, playerInventory, textComponent);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.n_1700_B(matrixStack, partialTicks, mouseX, mouseY);
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
        if ((((F_4082_i)this.Q_4569_t).n_1700_B(0).J_1907_R() || ((F_4082_i)this.Q_4569_t).n_1700_B(1).J_1907_R()) && !((F_4082_i)this.Q_4569_t).n_1700_B(2).J_1907_R()) {
            this.blit(matrixStack, i + 92, j + 31, this.t_148_a, 0, 28, 21);
        }
    }
}


