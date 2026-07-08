/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.group.GroupWindow;
import code.alphadlc.features.module.setting.implement.GroupSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;

public class GroupComponent
extends AbstractSettingComponent {
    private final GroupSetting setting;

    public GroupComponent(GroupSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 18.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(context.method_51448(), this.setting.getName(), this.x + 6.0f, this.y + 10.0f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + this.width - 24.0f, this.y + 5.0f, 7.0, 7.0).round(2.0f).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 40)).build());
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + this.width - 13.0f, this.y + 5.0f, 7.0, 7.0).round(2.0f).color(this.setting.isValue() ? ColorAssist.getClientColor() : ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 35)).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            return false;
        }
        if (button == 0) {
            if (Calculate.isHovered(mouseX, mouseY, this.x + this.width - 24.0f, this.y + 5.0f, 7.0, 7.0)) {
                this.openGroupWindow((int)mouseX, (int)mouseY);
            } else {
                this.setting.setValue(!this.setting.isValue());
            }
            return true;
        }
        if (button == 1) {
            this.openGroupWindow((int)mouseX, (int)mouseY);
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }

    private void openGroupWindow(int mouseX, int mouseY) {
        AbstractWindow existing = null;
        for (AbstractWindow win : windowManager.getWindows()) {
            GroupWindow groupWindow;
            if (!(win instanceof GroupWindow) || (groupWindow = (GroupWindow)win).getSetting() != this.setting) continue;
            existing = win;
            break;
        }
        if (existing != null) {
            existing.startCloseAnimation();
            return;
        }
        windowManager.add(new GroupWindow(this.setting).position(mouseX + 4, mouseY).size(137.0f, 23.0f).draggable(false));
    }
}
