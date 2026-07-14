/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AbstractButton;
import lightning.product.g_221_o;
import lightning.product.x_282_a;

public class Button
extends AbstractButton {
    public static final J_1907_R field_238486_s_ = (button, matrixStack, mouseX, mouseY) -> {};
    protected final n_1700_B onPress;
    protected final J_1907_R onTooltip;

    public Button(int x, int y, int width, int height, x_282_a title, n_1700_B pressedAction) {
        this(x, y, width, height, title, pressedAction, field_238486_s_);
    }

    public Button(int x, int y, int width, int height, x_282_a title, n_1700_B pressedAction, J_1907_R onTooltip) {
        super(x, y, width, height, title);
        this.onPress = pressedAction;
        this.onTooltip = onTooltip;
    }

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }

    @Override
    public void renderToolTip(g_221_o matrixStack, int mouseX, int mouseY) {
        this.onTooltip.onTooltip(this, matrixStack, mouseX, mouseY);
    }

    public static interface J_1907_R {
        public void onTooltip(Button var1, g_221_o var2, int var3, int var4);
    }

    public static interface n_1700_B {
        public void onPress(Button var1);
    }
}


