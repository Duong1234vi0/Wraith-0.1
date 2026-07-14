/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.gui;

import lightning.product.X_933_l;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.gui.GuiButtonOF;

public class GuiButtonDownloadShaders
extends GuiButtonOF {
    public GuiButtonDownloadShaders(int buttonID, int xPos, int yPos) {
        super(buttonID, xPos, yPos, 22, 20, "");
    }

    @Override
    public void render(g_221_o matrixStackIn, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            super.render(matrixStackIn, mouseX, mouseY, partialTicks);
            g_2336_b resourcelocation = new g_2336_b("optifine/textures/icons.png");
            Config.getTextureManager().n_1700_B(resourcelocation);
            X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.blit(matrixStackIn, this.x + 3, this.y + 2, 0, 0, 16, 16);
        }
    }
}

