/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.widgets;

import javax.annotation.Nullable;
import lightning.product.AbstractButton;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;

public class ImageButton
extends AbstractButton {
    protected MinecraftClient mc = MinecraftClient.A_4115_X();
    protected g_2336_b texture;
    @Nullable
    protected PressAction onPress;
    protected TooltipSupplier tooltipSupplier;

    public ImageButton(int x, int y, g_2336_b texture, @Nullable PressAction onPress, TooltipSupplier tooltipSupplier) {
        super(x, y, 20, 20, new U_2871_b(""));
        this.texture = texture;
        this.onPress = onPress;
        this.tooltipSupplier = tooltipSupplier;
    }

    @Override
    public void onPress() {
        if (this.onPress != null) {
            this.onPress.onPress(this);
        }
    }

    protected void renderImage(g_221_o matrices, int mouseX, int mouseY, float delta) {
        this.mc.G_624_v().n_1700_B(this.texture);
        ImageButton.blit(matrices, this.x + 2, this.y + 2, 0.0f, 0.0f, 16, 16, 16, 16);
    }

    protected boolean shouldRenderTooltip() {
        return this.isHovered;
    }

    @Override
    public void renderButton(g_221_o matrices, int mouseX, int mouseY, float delta) {
        super.renderButton(matrices, mouseX, mouseY, delta);
        this.renderImage(matrices, mouseX, mouseY, delta);
        if (this.shouldRenderTooltip()) {
            this.renderToolTip(matrices, mouseX, mouseY);
        }
    }

    @Override
    public void renderToolTip(g_221_o matrices, int mouseX, int mouseY) {
        this.tooltipSupplier.onTooltip(this, matrices, mouseX, mouseY);
    }

    public static interface PressAction {
        public void onPress(ImageButton var1);
    }

    public static interface TooltipSupplier {
        public void onTooltip(ImageButton var1, g_221_o var2, int var3, int var4);
    }
}



