/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.ColorPresetButton;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.display.render.font.Fonts;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class ColorPresetComponent
extends AbstractComponent {
    private final List<ColorPresetButton> colorPresetButtonList = new ArrayList<ColorPresetButton>();
    private final ColorSetting setting;
    private float windowHeight;
    private float windowWidth;

    public ColorPresetComponent(ColorSetting setting) {
        this.setting = setting;
        for (int preset : setting.getPresets()) {
            this.colorPresetButtonList.add(new ColorPresetButton(setting, preset));
        }
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        if (!this.colorPresetButtonList.isEmpty()) {
            Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(context.method_51448(), "\u0413\u043e\u0442\u043e\u0432\u044b\u0435 \u0442\u0441\u0432\u0435\u0442\u0430", this.x + 6.0f, this.y + 95.0f, -1);
        }
        int xOffset = 0;
        int yOffset = 0;
        int colorIndex = 0;
        int size = 13;
        for (ColorPresetButton button : this.colorPresetButtonList) {
            button.x = this.x + 6.0f + (float)xOffset;
            button.y = this.y + 103.0f + (float)yOffset;
            button.render(context, mouseX, mouseY, delta);
            xOffset += size;
            if (++colorIndex < 11) continue;
            colorIndex = 0;
            xOffset = 0;
            yOffset += size - 1;
        }
        this.windowHeight = this.colorPresetButtonList.isEmpty() ? 132.0f : (float)(166 + yOffset) - (float)yOffset / 2.0f;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.colorPresetButtonList.forEach(colorPresetButton -> colorPresetButton.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public List<ColorPresetButton> getColorPresetButtonList() {
        return this.colorPresetButtonList;
    }

    public ColorSetting getSetting() {
        return this.setting;
    }

    public float getWindowHeight() {
        return this.windowHeight;
    }

    public float getWindowWidth() {
        return this.windowWidth;
    }
}
