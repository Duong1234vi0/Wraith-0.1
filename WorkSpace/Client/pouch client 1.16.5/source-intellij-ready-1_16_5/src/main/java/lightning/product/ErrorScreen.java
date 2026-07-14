/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class ErrorScreen
extends k_2603_m {
    private final x_282_a n_1700_B;

    public ErrorScreen(x_282_a p_i232277_1_, x_282_a p_i232277_2_) {
        super(p_i232277_1_);
        this.n_1700_B = p_i232277_2_;
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new Button(this.width / 2 - 100, 140, 200, 20, CommonComponents.G_564_y, p_213034_1_ -> this.minecraft.n_1700_B((k_2603_m)null)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        ErrorScreen.fillGradient(matrixStack, 0, 0, this.width, this.height, -12574688, -11530224);
        ErrorScreen.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 90, 0xFFFFFF);
        ErrorScreen.drawCenteredString(matrixStack, this.font, this.n_1700_B, this.width / 2, 110, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}


