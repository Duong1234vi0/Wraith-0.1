/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NonNullList;
import lightning.product.R_1120_N;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.ContainerListener;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;
import lightning.product.z_3427_G;

public class ItemCombinerScreen<T extends R_1120_N>
extends z_3427_G<T>
implements ContainerListener {
    private g_2336_b n_1700_B;

    public ItemCombinerScreen(T container, W_3491_f playerInventory, x_282_a title, g_2336_b guiTexture) {
        super(container, playerInventory, title);
        this.n_1700_B = guiTexture;
    }

    protected void n_1700_B() {
    }

    @Override
    protected void init() {
        super.init();
        this.n_1700_B();
        ((R_1120_N)this.Q_4569_t).n_1700_B(this);
    }

    @Override
    public void onClose() {
        super.onClose();
        ((R_1120_N)this.Q_4569_t).J_1907_R(this);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        c_4037_x.Y_259_p();
        this.n_1700_B(matrixStack, mouseX, mouseY, partialTicks);
        this.J_1907_R(matrixStack, mouseX, mouseY);
    }

    protected void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
    }

    @Override
    protected void n_1700_B(g_221_o matrixStack, float partialTicks, int x, int y) {
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(this.n_1700_B);
        int i = (this.width - this.t_148_a) / 2;
        int j = (this.height - this.s_956_w) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.t_148_a, this.s_956_w);
        this.blit(matrixStack, i + 59, j + 20, 0, this.s_956_w + (((R_1120_N)this.Q_4569_t).n_1700_B(0).J_1907_R() ? 0 : 16), 110, 16);
        if ((((R_1120_N)this.Q_4569_t).n_1700_B(0).J_1907_R() || ((R_1120_N)this.Q_4569_t).n_1700_B(1).J_1907_R()) && !((R_1120_N)this.Q_4569_t).n_1700_B(2).J_1907_R()) {
            this.blit(matrixStack, i + 99, j + 45, this.t_148_a, 0, 28, 21);
        }
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, NonNullList<Z_1993_T> itemsList) {
        this.n_1700_B(containerToSend, 0, containerToSend.n_1700_B(0).n_1700_B());
    }

    @Override
    public void n_1700_B(a_2900_S containerIn, int varToUpdate, int newValue) {
    }

    @Override
    public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
    }
}


