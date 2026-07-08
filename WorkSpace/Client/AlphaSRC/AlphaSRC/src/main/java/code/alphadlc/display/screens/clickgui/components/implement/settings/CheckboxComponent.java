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
import code.alphadlc.features.module.setting.implement.BooleanSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import net.minecraft.class_332;
import net.minecraft.class_3532;

public class CheckboxComponent
extends AbstractSettingComponent {
    private final BooleanSetting setting;
    private float hoverAnimation;

    public CheckboxComponent(BooleanSetting setting) {
        super(setting);
        this.setting = setting;
        this.height = 21.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        this.hoverAnimation = class_3532.method_16439((float)0.2f, (float)this.hoverAnimation, (float)(this.isHover(mouseX, mouseY) ? 1.0f : 0.0f));
        int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(255.0f * (0.72f + 0.28f * (this.setting.isValue() ? 1.0f : 0.0f) + 0.18f * this.hoverAnimation)));
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        ToggleSwitchRenderer.drawLabel(labelFont, context.method_51448(), this.setting.getName(), this.x + 10.0f, this.y, this.height, textColor);
        float toggleX = this.x + this.width - 22.0f - 9.0f;
        float toggleY = ToggleSwitchRenderer.alignedControlY(labelFont, this.setting.getName(), this.y, this.height, 10.0f);
        ToggleSwitchRenderer.renderDefault(context, toggleX, toggleY, this.setting.isValue(), this.setting);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        float toggleX = this.x + this.width - 22.0f - 9.0f;
        float toggleY = ToggleSwitchRenderer.alignedControlY(labelFont, this.setting.getName(), this.y, this.height, 10.0f);
        if (button == 0 && ToggleSwitchRenderer.isDefaultHovered(mouseX, mouseY, toggleX, toggleY)) {
            this.setting.setValue(!this.setting.isValue());
            return true;
        }
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            this.setting.setValue(!this.setting.isValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
