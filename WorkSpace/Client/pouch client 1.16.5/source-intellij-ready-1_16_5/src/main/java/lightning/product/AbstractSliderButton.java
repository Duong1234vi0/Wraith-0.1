/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.V_2511_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.k_4218_M;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public abstract class AbstractSliderButton
extends V_2511_L {
    protected double sliderValue;

    public AbstractSliderButton(int x, int y, int width, int height, x_282_a message, double defaultValue) {
        super(x, y, width, height, message);
        this.sliderValue = defaultValue;
    }

    @Override
    protected int getYImage(boolean isHovered) {
        return 0;
    }

    @Override
    protected MutableComponent getNarrationMessage() {
        return new F_2904_S("gui.narrate.slider", this.getMessage());
    }

    @Override
    protected void renderBg(g_221_o matrixStack, MinecraftClient minecraft, int mouseX, int mouseY) {
        minecraft.G_624_v().n_1700_B(WIDGETS_LOCATION);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        int i = (this.isHovered() ? 2 : 1) * 20;
        this.blit(matrixStack, this.x + (int)(this.sliderValue * (double)(this.width - 8)), this.y, 0, 46 + i, 4, 20);
        this.blit(matrixStack, this.x + (int)(this.sliderValue * (double)(this.width - 8)) + 4, this.y, 196, 46 + i, 4, 20);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.changeSliderValue(mouseX);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean flag;
        boolean bl = flag = keyCode == 263;
        if (flag || keyCode == 262) {
            float f = flag ? -1.0f : 1.0f;
            this.setSliderValue(this.sliderValue + (double)(f / (float)(this.width - 8)));
        }
        return false;
    }

    private void changeSliderValue(double mouseX) {
        this.setSliderValue((mouseX - (double)(this.x + 4)) / (double)(this.width - 8));
    }

    private void setSliderValue(double value) {
        double d0 = this.sliderValue;
        this.sliderValue = u_530_F.n_1700_B(value, 0.0, 1.0);
        if (d0 != this.sliderValue) {
            this.func_230972_a_();
        }
        this.func_230979_b_();
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        this.changeSliderValue(mouseX);
        super.onDrag(mouseX, mouseY, dragX, dragY);
    }

    @Override
    public void playDownSound(k_4218_M handler) {
    }

    @Override
    public void onRelease(double mouseX, double mouseY) {
        super.playDownSound(MinecraftClient.A_4115_X().Z_976_R());
    }

    protected abstract void func_230979_b_();

    protected abstract void func_230972_a_();
}



