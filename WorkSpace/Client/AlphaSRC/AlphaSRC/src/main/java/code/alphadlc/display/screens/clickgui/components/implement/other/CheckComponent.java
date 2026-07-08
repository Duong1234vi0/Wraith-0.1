/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.animation.AnimationHelper;
import code.alphadlc.utils.client.sound.SoundManager;
import code.alphadlc.utils.display.color.ColorAssist;
import code.alphadlc.utils.display.render.font.Fonts;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import code.alphadlc.utils.theme.ThemeManager;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class CheckComponent
extends AbstractComponent {
    private boolean state;
    private Runnable runnable;
    private boolean moduleEnabled = true;

    @Override
    public CheckComponent position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        String toggleAnimationKey = "checkbox_toggle_" + System.identityHashCode(this);
        float scale = AnimationHelper.getAnimationValue(toggleAnimationKey, 1.0f);
        if (this.state) {
            if (this.moduleEnabled) {
                rectangle.render(ShapeProperties.create(matrix, this.x, this.y, 7.0f * scale, 7.0f * scale).round(1.5f).color(ColorAssist.getClientColor(), ColorAssist.getClientColor2(), ColorAssist.getClientColor(), ColorAssist.getClientColor2()).build());
            } else {
                rectangle.render(ShapeProperties.create(matrix, this.x, this.y, 7.0f * scale, 7.0f * scale).round(1.5f).color(new Color(57, 57, 60, 255).getRGB()).build());
            }
        } else {
            rectangle.render(ShapeProperties.create(matrix, this.x, this.y, 7.0f * scale, 7.0f * scale).round(1.5f).softness(1.0f).thickness(2.0f).outlineColor(new Color(57, 57, 60, 255).getRGB()).color(new Color(32, 32, 35, 40).getRGB()).build());
        }
        if (this.state && this.moduleEnabled) {
            Fonts.getSize(13, Fonts.Type.ALPHADLC_ICONS).drawString(matrix, "k", this.x + 0.2f, this.y + 2.9f, ThemeManager.textColor.getColor());
        } else if (this.state && !this.moduleEnabled) {
            Fonts.getSize(13, Fonts.Type.ALPHADLC_ICONS).drawString(matrix, "k", this.x + 0.2f, this.y + 2.9f, new Color(137, 137, 140, 255).getRGB());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, 6.0, 6.0) && button == 0) {
            String toggleAnimationKey = "checkbox_toggle_" + System.identityHashCode(this);
            AnimationHelper.startAnimation(toggleAnimationKey, 1.0f, 1.2f, 100L, AnimationHelper.EasingType.EASE_OUT);
            SoundManager.playSound(SoundManager.SETTING_INTERACT);
            this.runnable.run();
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public CheckComponent setState(boolean state) {
        this.state = state;
        return this;
    }

    public CheckComponent setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public CheckComponent setModuleEnabled(boolean moduleEnabled) {
        this.moduleEnabled = moduleEnabled;
        return this;
    }
}
