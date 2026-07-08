/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.implement.ButtonSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;

public class SButtonComponent
extends AbstractSettingComponent {
    private final ButtonSetting setting;

    public SButtonComponent(ButtonSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 20.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        boolean hovered = Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 4.0f, this.y + 2.0f, Math.max(1.0f, this.width - 8.0f), 16.0).round(4.0f).color(ColorAssist.applyOpacity(ThemeManager.BackgroundGui.getColor(), hovered ? 110 : 85)).build());
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_SEMIBOLD).drawCenteredString(context.method_51448(), this.setting.getName(), this.x + this.width / 2.0f, this.y + 10.0f, ThemeManager.textColor.getColor());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            if (this.setting.getRunnable() != null) {
                this.setting.getRunnable().run();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
