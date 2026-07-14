/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_2511_L;
import lightning.product.MinecraftClient;
import lightning.product.x_282_a;

public abstract class AbstractButton
extends V_2511_L {
    public AbstractButton(int x, int y, int width, int height, x_282_a title) {
        super(x, y, width, height, title);
    }

    public abstract void onPress();

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.onPress();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.active && this.visible) {
            if (keyCode != 257 && keyCode != 32 && keyCode != 335) {
                return false;
            }
            this.playDownSound(MinecraftClient.A_4115_X().Z_976_R());
            this.onPress();
            return true;
        }
        return false;
    }
}



