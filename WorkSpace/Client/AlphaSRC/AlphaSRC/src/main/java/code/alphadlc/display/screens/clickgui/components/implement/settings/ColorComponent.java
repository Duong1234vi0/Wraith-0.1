/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.other.ToggleSwitchRenderer;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.ColorWindow;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import net.minecraft.class_3532;

public class ColorComponent
extends AbstractSettingComponent {
    private final ColorSetting setting;
    private float hoverAnimation;

    public ColorComponent(ColorSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 21.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        this.hoverAnimation = class_3532.method_16439((float)0.2f, (float)this.hoverAnimation, (float)(this.isHover(mouseX, mouseY) ? 1.0f : 0.0f));
        float leftPadding = 10.0f;
        float checkSize = 8.0f;
        int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(255.0f * (0.75f + 0.25f * this.hoverAnimation)));
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(context.method_51448(), this.setting.getName(), this.x + leftPadding, ToggleSwitchRenderer.labelDrawY(Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM), this.setting.getName(), this.y, this.height), textColor);
        float outlineX = this.x + this.width - leftPadding - checkSize;
        float outlineY = this.y + (this.height - checkSize) / 2.0f;
        rectangle.render(ShapeProperties.create(context.method_51448(), outlineX, outlineY, checkSize, checkSize).round(4.5f).color(ColorAssist.getOutline()).build());
        rectangle.render(ShapeProperties.create(context.method_51448(), outlineX + 1.0f, outlineY + 1.0f, 6.0, 6.0).round(4.5f).color(this.setting.getColor()).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && this.isHover(mouseX, mouseY)) {
            this.openColorWindow((int)mouseX, (int)mouseY);
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }

    private void openColorWindow(int mouseX, int mouseY) {
        AbstractWindow existing = null;
        for (AbstractWindow win : windowManager.getWindows()) {
            ColorWindow colorWindow;
            if (!(win instanceof ColorWindow) || (colorWindow = (ColorWindow)win).getSetting() != this.setting) continue;
            existing = win;
            break;
        }
        if (existing != null) {
            existing.startCloseAnimation();
            return;
        }
        windowManager.add(new ColorWindow(this.setting).position(mouseX - 90, mouseY - 10).size(100.0f, 155.0f).draggable(true));
    }
}
