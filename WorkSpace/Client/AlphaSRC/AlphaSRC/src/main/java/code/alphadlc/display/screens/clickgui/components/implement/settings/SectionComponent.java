/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.SettingComponentAdder;
import code.alphadlc.features.module.setting.implement.SectionSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class SectionComponent
extends AbstractSettingComponent {
    private static final float HEADER_HEIGHT = 19.0f;
    private final SectionSetting setting;
    private final List<AbstractSettingComponent> children = new ArrayList<AbstractSettingComponent>();
    private boolean expanded;

    public SectionComponent(SectionSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 19.0f;
        new SettingComponentAdder().addSettingComponent(setting.getSubSettings(), this.children, setting.getBoundModule());
    }

    private float childrenHeight() {
        float total = 0.0f;
        for (AbstractSettingComponent child : this.children) {
            if (!child.getSetting().isVisible()) continue;
            total += child.height + 1.0f;
        }
        return total;
    }

    private void layoutChildren() {
        float cursorY = this.y + 19.0f;
        for (AbstractSettingComponent child : this.children) {
            if (!child.getSetting().isVisible()) continue;
            child.x = this.x + 8.0f;
            child.y = cursorY;
            child.width = this.width - 10.0f;
            cursorY += child.height + 1.0f;
        }
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        float f = this.height = this.expanded ? 19.0f + this.childrenHeight() : 19.0f;
        if (this.expanded) {
            rectangle.render(ShapeProperties.create(context.method_51448(), this.x + 2.0f, this.y + 19.0f - 1.0f, 1.2f, this.childrenHeight() - 2.0f).round(1.0f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), this.setting.isOn() ? 160 : 40)).build());
        }
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(context.method_51448(), this.setting.getName(), this.x + 6.0f, this.y + 10.0f, ThemeManager.textColor.getColor());
        String arrow = this.expanded ? "j" : "i";
        Fonts.getSize(13, Fonts.Type.ALPHADLC_ICONS).drawString(context.method_51448(), arrow, this.x + this.width - 26.0f, this.y + 4.5f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 160));
        rectangle.render(ShapeProperties.create(context.method_51448(), this.x + this.width - 14.0f, this.y + 6.0f, 8.0, 8.0).round(2.0f).color(this.setting.isOn() ? ColorAssist.getClientColor() : ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 35)).build());
        if (this.expanded) {
            this.layoutChildren();
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible()) continue;
                child.render(context, mouseX, mouseY, delta);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.expanded) {
            this.layoutChildren();
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible() || !Calculate.isHovered(mouseX, mouseY, child.x, child.y, child.width, child.height) || !child.mouseClicked(mouseX, mouseY, button)) continue;
                return true;
            }
        }
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, 19.0)) {
            if (button == 0) {
                if (Calculate.isHovered(mouseX, mouseY, this.x + this.width - 16.0f, this.y + 4.0f, 12.0, 12.0)) {
                    this.setting.toggle();
                } else {
                    this.expanded = !this.expanded;
                }
                return true;
            }
            if (button == 1) {
                this.expanded = !this.expanded;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.layoutChildren();
        for (AbstractSettingComponent child : this.children) {
            child.mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.expanded) {
            this.layoutChildren();
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible() || !child.mouseDragged(mouseX, mouseY, button, deltaX, deltaY)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (this.expanded) {
            this.layoutChildren();
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible() || !child.mouseScrolled(mouseX, mouseY, amount)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.expanded) {
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible() || !child.keyPressed(keyCode, scanCode, modifiers)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.expanded) {
            for (AbstractSettingComponent child : this.children) {
                if (!child.getSetting().isVisible() || !child.charTyped(chr, modifiers)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        for (AbstractSettingComponent child : this.children) {
            child.tick();
        }
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
