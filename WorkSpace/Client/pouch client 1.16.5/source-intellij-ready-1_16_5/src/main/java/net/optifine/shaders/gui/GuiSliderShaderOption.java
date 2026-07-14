/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.gui;

import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.optifine.shaders.gui.GuiShaderOptions;

public class GuiSliderShaderOption
extends GuiButtonShaderOption {
    private float sliderValue = 1.0f;
    public boolean dragging;
    private ShaderOption shaderOption = null;

    public GuiSliderShaderOption(int buttonId, int x, int y, int w, int h, ShaderOption shaderOption, String text) {
        super(buttonId, x, y, w, h, shaderOption, text);
        this.shaderOption = shaderOption;
        this.sliderValue = shaderOption.getIndexNormalized();
        this.setMessage(GuiShaderOptions.getButtonText(shaderOption, this.width));
    }

    @Override
    protected int getYImage(boolean p_getYImage_1_) {
        return 0;
    }

    @Override
    protected void renderBg(g_221_o matrixStackIn, MinecraftClient mc, int mouseX, int mouseY) {
        if (this.visible) {
            if (this.dragging && !k_2603_m.hasShiftDown()) {
                this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                this.sliderValue = u_530_F.n_1700_B(this.sliderValue, 0.0f, 1.0f);
                this.shaderOption.setIndexNormalized(this.sliderValue);
                this.sliderValue = this.shaderOption.getIndexNormalized();
                this.setMessage(GuiShaderOptions.getButtonText(this.shaderOption, this.width));
            }
            mc.G_624_v().n_1700_B(WIDGETS_LOCATION);
            X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int i = (this.isHovered() ? 2 : 1) * 20;
            this.blit(matrixStackIn, this.x + (int)(this.sliderValue * (float)(this.width - 8)), this.y, 0, 46 + i, 4, 20);
            this.blit(matrixStackIn, this.x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.y, 196, 46 + i, 4, 20);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            this.sliderValue = (float)(mouseX - (double)(this.x + 4)) / (float)(this.width - 8);
            this.sliderValue = u_530_F.n_1700_B(this.sliderValue, 0.0f, 1.0f);
            this.shaderOption.setIndexNormalized(this.sliderValue);
            this.setMessage(GuiShaderOptions.getButtonText(this.shaderOption, this.width));
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
        return true;
    }

    @Override
    public void valueChanged() {
        this.sliderValue = this.shaderOption.getIndexNormalized();
    }

    @Override
    public boolean isSwitchable() {
        return false;
    }
}


