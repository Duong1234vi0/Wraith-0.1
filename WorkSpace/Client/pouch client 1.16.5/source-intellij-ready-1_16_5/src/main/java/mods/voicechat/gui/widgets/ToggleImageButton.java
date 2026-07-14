/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package mods.voicechat.gui.widgets;

import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import mods.voicechat.gui.widgets.ImageButton;

public class ToggleImageButton
extends ImageButton {
    @Nullable
    protected Supplier<Boolean> stateSupplier;

    public ToggleImageButton(int x, int y, g_2336_b texture, @Nullable Supplier<Boolean> stateSupplier, ImageButton.PressAction onPress, ImageButton.TooltipSupplier tooltipSupplier) {
        super(x, y, texture, onPress, tooltipSupplier);
        this.stateSupplier = stateSupplier;
    }

    @Override
    protected void renderImage(g_221_o matrices, int mouseX, int mouseY, float delta) {
        if (this.stateSupplier == null) {
            return;
        }
        this.mc.G_624_v().n_1700_B(this.texture);
        if (this.stateSupplier.get().booleanValue()) {
            ToggleImageButton.blit(matrices, this.x + 2, this.y + 2, 16.0f, 0.0f, 16, 16, 32, 32);
        } else {
            ToggleImageButton.blit(matrices, this.x + 2, this.y + 2, 0.0f, 0.0f, 16, 16, 32, 32);
        }
    }
}

