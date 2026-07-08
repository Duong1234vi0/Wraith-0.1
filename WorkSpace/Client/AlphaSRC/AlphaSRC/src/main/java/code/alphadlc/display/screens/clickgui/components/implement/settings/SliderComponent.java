/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  org.lwjgl.glfw.GLFW
 */
package code.alphadlc.display.screens.clickgui.components.implement.settings;

import code.alphadlc.common.animation.inovated.EasingList;
import code.alphadlc.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import code.alphadlc.features.module.setting.implement.SliderSettings;
import code.alphadlc.utils.animation.util.Animation;
import code.alphadlc.utils.animation.util.Easings;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.util.Locale;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import org.lwjgl.glfw.GLFW;

public class SliderComponent
extends AbstractSettingComponent {
    private final SliderSettings setting;
    private final Animation valueAnimation = new Animation();
    private final Animation movingAnimation = new Animation();
    private boolean drag;
    private float hoverAnimation;
    private static SliderComponent current;

    public SliderComponent(SliderSettings setting) {
        super(setting);
        this.setting = setting;
        this.height = setting.isBarOnly() ? 22.0f : 29.0f;
        this.valueAnimation.set(setting.getValue());
        this.movingAnimation.set(0.0);
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        float sliderX = this.x + 9.0f;
        float sliderY = this.y + 1.0f;
        float sliderW = this.width - 18.0f;
        float leftPadding = 10.0f;
        if (GLFW.glfwGetMouseButton((long)class_310.method_1551().method_22683().method_4490(), (int)0) != 1) {
            this.drag = false;
        }
        if (this.drag) {
            this.valueAnimation.set(this.setting.getValue());
        } else {
            this.valueAnimation.run(this.setting.getValue(), 0.25, Easings.CUBIC_OUT, true);
            this.valueAnimation.update();
        }
        this.hoverAnimation = class_3532.method_16439((float)0.22f, (float)this.hoverAnimation, (float)(this.isHover(mouseX, mouseY) ? 1.0f : 0.0f));
        float currentAnimated = (float)this.valueAnimation.getValue();
        float currentPercent = this.clampPercent(currentAnimated);
        float knobCenterX = sliderX + sliderW * currentPercent;
        rectangle.render(ShapeProperties.create(context.method_51448(), sliderX, sliderY + this.height - 12.25f, sliderW, 2.5).round(1.25f).color(ColorAssist.applyOpacity(ThemeManager.offModuleColor.getColor(), ThemeManager.isLiquidGlassTheme() ? 58 : 88)).build());
        rectangle.render(ShapeProperties.create(context.method_51448(), sliderX, sliderY + this.height - 12.25f, sliderW * currentPercent, 2.5).round(1.25f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
        if (!this.setting.isBarOnly()) {
            this.movingAnimation.run(this.drag ? 1.0 : 0.0, 0.2, EasingList.CIRC_BOTH, true);
            this.movingAnimation.update();
            float moving = (float)this.movingAnimation.getValue();
            if (ThemeManager.isLiquidGlassTheme()) {
                rectangle.render(ShapeProperties.create(context.method_51448(), knobCenterX - 4.5f - 3.0f * moving, sliderY + this.height - 15.0f - 1.5f * moving, 9.0f + 6.0f * moving, 6.0f + 4.0f * moving).round((6.0f + 4.0f * moving) / 2.0f).color(ColorAssist.applyOpacity(-16777216, (int)(75.0f + 50.0f * moving))).build());
                rectangle.render(ShapeProperties.create(context.method_51448(), knobCenterX - 4.5f - 3.0f * moving, sliderY + this.height - 15.0f - 1.5f * moving, 9.0f + 6.0f * moving, 6.0f + 4.0f * moving).round((6.0f + 4.0f * moving) / 2.0f).color(ColorAssist.applyOpacity(-1, (int)(255.0f * (1.0f - moving)))).build());
            } else {
                float knobW = 8.0f + moving * 2.0f;
                float knobH = 5.0f + moving * 1.5f;
                float knobCenterY = sliderY + this.height - 11.0f;
                rectangle.render(ShapeProperties.create(context.method_51448(), knobCenterX - knobW / 2.0f, knobCenterY - knobH / 2.0f + 0.5f, knobW, knobH).round(knobH / 2.0f).color(ColorAssist.applyOpacity(-16777216, 64)).build());
                rectangle.render(ShapeProperties.create(context.method_51448(), knobCenterX - knobW / 2.0f, knobCenterY - knobH / 2.0f, knobW, knobH).round(knobH / 2.0f).color(ThemeManager.textColor.getColor()).build());
            }
        }
        Fonts.getSize(14, Fonts.Type.ROCKSTAR_MEDIUM).drawString(context.method_51448(), this.setting.getName(), this.x + leftPadding, sliderY + 11.0f - 4.0f, ColorAssist.lerp(this.hoverAnimation, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 191), ThemeManager.textColor.getColor()));
        String value = this.formatValue(currentAnimated);
        float valueWidth = Fonts.getSize(11, Fonts.Type.ROCKSTAR_SEMIBOLD).getStringWidth(value);
        Fonts.getSize(11, Fonts.Type.ROCKSTAR_SEMIBOLD).drawString(context.method_51448(), value, sliderX + sliderW - valueWidth, sliderY + 11.0f - 3.5f, ColorAssist.lerp(this.hoverAnimation, ColorAssist.applyOpacity(ThemeManager.textColor.getColor(), 191), ThemeManager.textColor.getColor()));
        if (this.drag) {
            float xValue = this.getSliderValue(sliderX, sliderW, mouseX);
            this.setting.setValue(xValue);
            current = this;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height)) {
            this.drag = true;
            current = this;
            this.updateValue(mouseX);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.drag) {
            this.updateValue(mouseX);
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.drag = false;
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if ((keyCode == 262 || keyCode == 263) && current == this) {
            float step = this.setting.getStep() <= 0.0f ? 0.01f : this.setting.getStep();
            float delta = step * 0.7f * (keyCode == 262 ? 1.0f : -1.0f);
            this.setting.setValue(this.clampAndSnap(this.setting.getValue() + delta));
            return true;
        }
        return false;
    }

    @Override
    public boolean isHover(double mouseX, double mouseY) {
        return Calculate.isHovered(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }

    private void updateValue(double mouseX) {
        float sliderX = this.x + 9.0f;
        float sliderW = this.width - 18.0f;
        this.setting.setValue(this.getSliderValue(sliderX, sliderW, mouseX));
    }

    private float getSliderValue(float sliderX, float sliderW, double mouseX) {
        float percent = class_3532.method_15363((float)((float)((mouseX - (double)sliderX) / (double)Math.max(1.0f, sliderW))), (float)0.0f, (float)1.0f);
        float raw = this.setting.getMin() + (this.setting.getMax() - this.setting.getMin()) * percent;
        return this.clampAndSnap(raw);
    }

    private float clampAndSnap(float value) {
        float step = this.setting.getStep() <= 0.0f ? 0.01f : this.setting.getStep();
        float snapped = (float)Math.round(value / step) * step;
        return class_3532.method_15363((float)snapped, (float)this.setting.getMin(), (float)this.setting.getMax());
    }

    private float clampPercent(float value) {
        float range = Math.max(1.0E-4f, this.setting.getMax() - this.setting.getMin());
        return class_3532.method_15363((float)((value - this.setting.getMin()) / range), (float)0.0f, (float)1.0f);
    }

    private String formatValue(float value) {
        if (this.setting.isInteger()) {
            return String.valueOf((int)value);
        }
        float step = this.setting.getStep() <= 0.0f ? 0.01f : this.setting.getStep();
        int precision = 0;
        String stepText = String.valueOf(step);
        int dot = stepText.indexOf(46);
        if (dot >= 0) {
            precision = stepText.length() - dot - 1;
        }
        String format = "%." + Math.max(1, precision) + "f";
        return String.format(Locale.US, format, Float.valueOf(value)).replaceAll("0+$", "").replaceAll("\\.$", "");
    }
}
