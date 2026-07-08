/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;

public class ColorEditorComponent
extends AbstractComponent {
    private final ColorSetting setting;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        int displayValue = (int)(this.setting.getAlpha() * 100.0f);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (Calculate.isHovered(mouseX, mouseY, this.x + 122.0f, this.y + 90.5f, 22.0, 14.0)) {
            this.setting.setAlpha(class_3532.method_15363((float)((float)((double)this.setting.getAlpha() - amount * 2.0 / 100.0)), (float)0.0f, (float)1.0f));
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public ColorEditorComponent(ColorSetting setting) {
        this.setting = setting;
    }
}
