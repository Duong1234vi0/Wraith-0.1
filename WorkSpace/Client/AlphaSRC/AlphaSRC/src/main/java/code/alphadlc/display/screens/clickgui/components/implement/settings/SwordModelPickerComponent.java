/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.display.screens.clickgui.components.implement.module.ModuleDescriptions;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.implement.SelectSetting;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class SwordModelPickerComponent
extends AbstractSettingComponent {
    private static final float ROW_HEIGHT = 16.0f;
    private static final float ROW_GAP = 2.0f;
    private static final float DESC_HEIGHT = 18.0f;
    private static final float LABEL_HEIGHT = 19.0f;
    private static final float PAD_X = 9.0f;
    private final SelectSetting selectSetting;
    private final Map<String, Float> hoverAnimations = new HashMap<String, Float>();

    public SwordModelPickerComponent(SelectSetting setting) {
        super(setting);
        this.selectSetting = setting;
    }

    @Override
    public void prepareLayout(float width) {
        this.width = width;
        List<String> options = this.selectSetting.getList();
        int count = options == null ? 0 : options.size();
        this.height = 43.0f + (float)count * 18.0f + 4.0f;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrices = context.method_51448();
        List<String> options = this.selectSetting.getList();
        if (options == null || options.isEmpty()) {
            return;
        }
        String selected = this.selectSetting.getSelected();
        if (selected == null || selected.isEmpty()) {
            selected = options.getFirst();
        }
        float contentX = this.x + 9.0f;
        float contentW = this.width - 18.0f;
        float cursorY = this.y;
        String description = ModuleDescriptions.tryGetDescription(this.getModule());
        if (description != null && !ModuleDescriptions.isMissingDescription(description)) {
            Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawString(matrices, description, contentX, cursorY + 4.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 185));
            cursorY += 18.0f;
        }
        int selectedIndex = Math.max(0, options.indexOf(selected)) + 1;
        String label = this.selectSetting.getName() + "  " + selectedIndex + "/" + options.size();
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(matrices, label, contentX, cursorY, 19.0f, ThemeManager.textColor.getColor());
        rectangle.render(ShapeProperties.create(matrices, contentX, cursorY += 23.0f, contentW, 2.0).round(1.0f).color(ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 18)).build());
        cursorY += 6.0f;
        for (String option : options) {
            boolean active = option.equals(selected);
            boolean hovered = Calculate.isHovered(mouseX, mouseY, contentX, cursorY, contentW, 16.0);
            float hoverAnim = Calculate.interpolateSmooth(14.0, this.hoverAnimations.getOrDefault(option, Float.valueOf(0.0f)).floatValue(), hovered ? 1.0f : 0.0f);
            this.hoverAnimations.put(option, Float.valueOf(hoverAnim));
            float activeAnim = active ? 1.0f : 0.0f;
            int baseBg = ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), (int)(55.0f + 30.0f * hoverAnim));
            int activeBg = ColorAssist.applyOpacity(ColorAssist.getClientColor(), (int)(70.0f + 50.0f * activeAnim));
            int chipColor = ColorAssist.lerp(activeAnim, baseBg, activeBg);
            rectangle.render(ShapeProperties.create(matrices, contentX, cursorY, contentW, 16.0).round(4.0f).color(chipColor).build());
            int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(170.0f + 40.0f * hoverAnim + 85.0f * activeAnim));
            Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(matrices, option, contentX + 8.0f, cursorY, 16.0f, textColor);
            if (activeAnim > 0.01f) {
                BackgroundIcons.draw(context, BackgroundIcons.SETTINGS, contentX + contentW - 14.0f, cursorY + 3.0f, 10.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(255.0f * activeAnim)));
            }
            cursorY += 18.0f;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        List<String> options = this.selectSetting.getList();
        if (options == null || options.isEmpty()) {
            return false;
        }
        float contentX = this.x + 9.0f;
        float contentW = this.width - 18.0f;
        float cursorY = this.y;
        String description = ModuleDescriptions.tryGetDescription(this.getModule());
        if (description != null && !ModuleDescriptions.isMissingDescription(description)) {
            cursorY += 18.0f;
        }
        cursorY += 29.0f;
        for (String option : options) {
            if (Calculate.isHovered(mouseX, mouseY, contentX, cursorY, contentW, 16.0)) {
                if (this.selectSetting.getList().contains(option)) {
                    this.selectSetting.selected(option);
                }
                return true;
            }
            cursorY += 18.0f;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }
}
