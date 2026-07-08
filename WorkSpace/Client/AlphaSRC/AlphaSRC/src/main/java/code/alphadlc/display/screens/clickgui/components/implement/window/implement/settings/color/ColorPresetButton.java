/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_332;

public class ColorPresetButton
extends AbstractComponent {
    private final ColorSetting setting;
    private final int color;

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x, this.y, 8.0, 8.0).round(2.0f).color(this.color).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, 8.0, 8.0) && button == 0) {
            this.setting.setColor(this.color);
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public ColorPresetButton(ColorSetting setting, int color) {
        this.setting = setting;
        this.color = color;
    }
}
