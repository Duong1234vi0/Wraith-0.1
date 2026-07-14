/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;

public class GenericDirtMessageScreen
extends k_2603_m {
    public GenericDirtMessageScreen(x_282_a p_i51114_1_) {
        super(p_i51114_1_);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        GenericDirtMessageScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 70, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


