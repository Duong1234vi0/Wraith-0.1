/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;

public class SettingComponent
extends AbstractComponent {
    private Runnable runnable;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        Fonts.getSize(15, Fonts.Type.GUIICONS).drawString(context.method_51448(), "B", this.x - 5.0f, this.y + 6.0f, new Color(128, 128, 128, 255).getRGB());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x - 5.0f, this.y + 6.0f, 7.0, 7.0) && button == 0) {
            this.runnable.run();
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public SettingComponent setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }
}
