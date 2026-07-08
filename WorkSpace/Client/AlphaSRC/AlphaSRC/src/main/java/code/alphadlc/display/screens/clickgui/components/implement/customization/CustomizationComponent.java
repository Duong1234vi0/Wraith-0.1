/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.customization;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.display.screens.clickgui.components.implement.window.AbstractWindow;
import code.alphadlc.display.screens.clickgui.components.implement.window.implement.settings.color.ColorWindow;
import code.alphadlc.features.module.setting.implement.ColorSetting;
import code.alphadlc.utils.customization.CustomizationManager;
import code.alphadlc.utils.display.render.font.FontRenderer;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.render.shape.implement.Rectangle;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class CustomizationComponent
extends AbstractComponent {
    private static final float ROW_HEIGHT = 28.0f;
    private static final float ROW_GAP = 4.0f;
    private static final float SWATCH_SIZE = 16.0f;
    private static final float LABEL_SWATCH_GAP = 10.0f;
    private static final float HORIZONTAL_PADDING = 10.0f;
    private final Rectangle rectangle = new Rectangle();
    private final List<ColorSetting> settings = CustomizationManager.getCustomizationSettings();

    public CustomizationComponent() {
        this.height = this.calculateHeight();
    }

    private int calculateHeight() {
        return (int)(20.0f + (float)this.settings.size() * 32.0f);
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        float settingY = this.y + 4.0f;
        Fonts.getSize(13, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrix, "Colors", this.x + 10.0f, settingY + 2.0f, Calculate.applyOpacity(ThemeManager.textColor.getColor(), 170.0f));
        settingY += 22.0f;
        for (ColorSetting setting : this.settings) {
            this.renderColorSetting(context, matrix, setting, this.x, settingY, this.width, 28.0f, mouseX, mouseY);
            settingY += 32.0f;
        }
    }

    private void renderColorSetting(class_332 context, class_4587 matrix, ColorSetting setting, float rowX, float rowY, float rowWidth, float rowHeight, int mouseX, int mouseY) {
        boolean hovered = Calculate.isHovered(mouseX, mouseY, rowX, rowY, rowWidth, rowHeight);
        this.rectangle.render(ShapeProperties.create(matrix, rowX, rowY, rowWidth, rowHeight).round(6.0f).color(Calculate.applyOpacity(ThemeManager.BackgroundGui.getColor(), hovered ? 110.0f : 75.0f)).build());
        String displayName = this.getDisplayName(setting);
        float textX = rowX + 10.0f;
        FontRenderer labelFont = Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM);
        float textY = labelFont.getCenteredY(rowY, rowHeight, displayName);
        labelFont.drawString(matrix, displayName, textX, textY, ThemeManager.textColor.getColor());
        float swatchX = textX + labelFont.getStringWidth(displayName) + 10.0f;
        float swatchY = rowY + (rowHeight - 16.0f) / 2.0f;
        this.rectangle.render(ShapeProperties.create(matrix, swatchX, swatchY, 16.0, 16.0).round(4.0f).color(Calculate.applyOpacity(-16777216, 40.0f)).build());
        this.rectangle.render(ShapeProperties.create(matrix, swatchX + 1.0f, swatchY + 1.0f, 14.0, 14.0).round(3.5f).color(setting.getColor()).build());
    }

    private String getDisplayName(ColorSetting setting) {
        if (setting == ThemeManager.primaryColor) {
            return "Primary";
        }
        if (setting == ThemeManager.secondaryColor) {
            return "Secondary";
        }
        if (setting == ThemeManager.BackgroundGui) {
            return "GUI";
        }
        if (setting == ThemeManager.offModuleColor) {
            return "Modules";
        }
        if (setting == ThemeManager.BackgroundSettings) {
            return "Settings";
        }
        if (setting == ThemeManager.textColor) {
            return "Text";
        }
        return setting.getName();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            return false;
        }
        float settingY = this.y + 26.0f;
        for (ColorSetting setting : this.settings) {
            if (Calculate.isHovered(mouseX, mouseY, this.x, settingY, this.width, 28.0) && button == 0) {
                AbstractWindow existingWindow = null;
                for (AbstractWindow window : windowManager.getWindows()) {
                    ColorWindow colorWindow;
                    if (!(window instanceof ColorWindow) || (colorWindow = (ColorWindow)window).getSetting() != setting) continue;
                    existingWindow = window;
                    break;
                }
                if (existingWindow != null) {
                    windowManager.delete(existingWindow);
                } else {
                    AbstractWindow colorWindow = new ColorWindow(setting).position((int)(mouseX - 110.0), (int)(mouseY - 20.0)).size(100.0f, 155.0f).draggable(true);
                    windowManager.add(colorWindow);
                }
                return true;
            }
            settingY += 32.0f;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
