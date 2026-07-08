/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.AlphaDLC;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.BackgroundIcons;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.display.scissor.ClipBounds;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public final class SettingDropdownRenderer
implements QuickImports {
    private static final class_2960 SETTINGS_ICON = BackgroundIcons.SETTINGS;
    private static final float ICON_SIZE = 10.0f;
    private static final float OPEN_ANIM_SMOOTH = 4.0f;
    private static final float CLOSE_ANIM_SMOOTH = 9.0f;
    private static final float PANEL_HOVER_SMOOTH = 8.0f;
    private static final float OPTION_HOVER_SMOOTH = 14.0f;
    public static final float LABEL_HEIGHT = 19.0f;
    public static final float PANEL_HEIGHT = 18.0f;
    public static final float OPTION_HEIGHT = 16.0f;
    public static final float OPTION_GAP = 2.0f;
    private static final float CONTENT_PAD_X = 9.0f;
    private boolean open;
    private float openAnimation;
    private float panelHoverAnimation;
    private final Map<String, Float> optionHoverAnimations = new HashMap<String, Float>();

    public boolean isOpen() {
        return this.open;
    }

    public void close() {
        this.open = false;
    }

    public float getOpenAnimation() {
        return this.openAnimation;
    }

    public float computeHeight(int optionCount) {
        float collapsed = 40.0f;
        float expanded = (float)optionCount * 18.0f;
        return collapsed + expanded * this.openAnimation;
    }

    public void render(class_332 context, float x, float y, float width, String label, String panelText, List<String> options, Predicate<String> isSelected, int mouseX, int mouseY) {
        float contentX = x + 9.0f;
        float contentY = y + 1.0f;
        float contentW = width - 18.0f;
        float panelY = contentY + 19.0f + 1.0f;
        boolean panelHovered = Calculate.isHovered(mouseX, mouseY, contentX, panelY, contentW, 18.0);
        this.updateAnimations(panelHovered);
        int headerColor = ColorAssist.lerp(this.panelHoverAnimation, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 191), ThemeManager.textColor.getColor());
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(context.method_51448(), label, x + 10.0f, contentY, 19.0f, headerColor);
        int panelBg = ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), (int)(55.0f + 35.0f * this.panelHoverAnimation + 25.0f * this.openAnimation));
        rectangle.render(ShapeProperties.create(context.method_51448(), contentX, panelY, contentW, 18.0).round(5.0f).color(panelBg).build());
        if (this.openAnimation > 0.04f) {
            rectangle.render(ShapeProperties.create(context.method_51448(), contentX + 1.0f, panelY + 1.0f, contentW - 2.0f, 16.0).round(4.0f).color(ColorAssist.applyOpacity(ColorAssist.getClientColor(), (int)(18.0f * this.openAnimation))).build());
        }
        Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(context.method_51448(), panelText, contentX + 8.0f, panelY, 18.0f, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(170.0f + 85.0f * this.panelHoverAnimation)));
        float iconX = contentX + contentW - 10.0f - 4.0f;
        float iconY = panelY + 4.0f;
        int iconColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)(140.0f + 115.0f * this.openAnimation));
        BackgroundIcons.draw(context, SETTINGS_ICON, iconX, iconY, 10.0f, iconColor);
        if (this.openAnimation <= 0.01f || options.isEmpty()) {
            return;
        }
        float optionsY = panelY + 18.0f + 3.0f;
        float step = 18.0f;
        float fullOptionsHeight = (float)options.size() * step - 2.0f;
        float visibleOptionsHeight = fullOptionsHeight * this.openAnimation;
        float fade = this.openAnimation * this.openAnimation;
        ClipBounds clip = AlphaDLC.getInstance().getScissorManager().getCurrentBounds();
        if (clip != null && visibleOptionsHeight > 0.01f) {
            float spaceBelow = clip.bottom() - optionsY;
            visibleOptionsHeight = Math.min(visibleOptionsHeight, Math.max(0.0f, spaceBelow));
            float maxRight = clip.right();
            if (contentX + contentW > maxRight) {
                contentW = Math.max(0.0f, maxRight - contentX);
            }
        }
        class_4587 matrices = context.method_51448();
        if (visibleOptionsHeight > 0.01f && contentW > 0.5f) {
            AlphaDLC.getInstance().getScissorManager().push(matrices.method_23760().method_23761(), contentX, optionsY, contentW, visibleOptionsHeight);
        }
        float offset = 0.0f;
        for (String value : options) {
            float collapsedOffset = offset * this.openAnimation;
            if (collapsedOffset > visibleOptionsHeight + 0.5f) break;
            float optionY = optionsY + collapsedOffset;
            boolean hovered = this.open && Calculate.isHovered(mouseX, mouseY, contentX, optionY, contentW, 16.0);
            boolean active = isSelected.test(value);
            float hoverAnim = Calculate.interpolateSmooth(14.0, this.optionHoverAnimations.getOrDefault(value, Float.valueOf(0.0f)).floatValue(), hovered ? 1.0f : 0.0f);
            this.optionHoverAnimations.put(value, Float.valueOf(hoverAnim));
            float activeAnim = active ? 1.0f : 0.0f;
            int baseBg = ColorAssist.applyOpacity(ThemeManager.BackgroundSettings.getColor(), (int)((38.0f + 30.0f * hoverAnim) * fade));
            int activeBg = ColorAssist.applyOpacity(ColorAssist.getClientColor(), (int)((40.0f + 45.0f * activeAnim) * fade));
            int chipColor = ColorAssist.lerp(activeAnim, baseBg, activeBg);
            rectangle.render(ShapeProperties.create(matrices, contentX, optionY, contentW, 16.0).round(4.0f).color(chipColor).build());
            int textColor = ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), (int)((150.0f + 35.0f * hoverAnim + 70.0f * activeAnim) * fade));
            Fonts.getSize(12, Fonts.Type.ROCKSTAR_MEDIUM).drawVerticallyCentered(matrices, value, contentX + 8.0f, optionY, 16.0f, textColor);
            if (activeAnim > 0.01f) {
                Fonts.getSize(11, Fonts.Type.ALPHADLC_ICONS).drawVerticallyCentered(matrices, "k", contentX + contentW - 14.0f, optionY, 16.0f, ColorAssist.applyOpacity(ColorAssist.getClientColor(), (int)(255.0f * activeAnim * fade)));
            }
            offset += step;
        }
        if (visibleOptionsHeight > 0.01f && contentW > 0.5f) {
            AlphaDLC.getInstance().getScissorManager().pop();
        }
    }

    public boolean handleClick(double mouseX, double mouseY, int button, float x, float y, float width, List<String> options, Function<String, Boolean> onOptionClick) {
        if (button != 0) {
            return false;
        }
        float contentX = x + 9.0f;
        float contentY = y + 1.0f;
        float panelY = contentY + 19.0f + 1.0f;
        float contentW = width - 18.0f;
        if (Calculate.isHovered(mouseX, mouseY, contentX, panelY, contentW, 18.0)) {
            this.open = !this.open;
            return true;
        }
        if (!this.open || this.openAnimation < 0.22f) {
            return false;
        }
        float optionsY = panelY + 18.0f + 3.0f;
        float step = 18.0f;
        float fullOptionsHeight = (float)options.size() * step - 2.0f;
        float visibleOptionsHeight = fullOptionsHeight * this.openAnimation;
        float offset = 0.0f;
        for (String value : options) {
            float optionY = optionsY + offset * this.openAnimation;
            if (Calculate.isHovered(mouseX, mouseY, contentX, optionY, contentW, 16.0)) {
                return Boolean.TRUE.equals(onOptionClick.apply(value));
            }
            offset += step;
        }
        return false;
    }

    private void updateAnimations(boolean panelHovered) {
        float openTarget = this.open ? 1.0f : 0.0f;
        float smooth = this.open ? 4.0f : 9.0f;
        this.openAnimation = Calculate.interpolateSmooth(smooth, this.openAnimation, openTarget);
        this.panelHoverAnimation = Calculate.interpolateSmooth(8.0, this.panelHoverAnimation, panelHovered ? 1.0f : 0.0f);
        if (!this.open && this.openAnimation < 0.002f) {
            this.openAnimation = 0.0f;
            this.optionHoverAnimations.clear();
        }
    }
}
