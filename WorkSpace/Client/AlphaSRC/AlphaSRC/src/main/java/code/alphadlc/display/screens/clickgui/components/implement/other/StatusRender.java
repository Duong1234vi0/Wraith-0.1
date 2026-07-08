/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 */
package code.alphadlc.display.screens.clickgui.components.implement.other;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.display.render.shape.ShapeProperties;
import code.alphadlc.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class StatusRender
extends AbstractComponent {
    private boolean state;
    private Runnable runnable;
    private final Animation alphaAnimation = new Decelerate().setMs(400).setValue(100.0);
    private final Animation stencilAnimation = new Decelerate().setMs(200).setValue(8.0);
    private final Animation sliderAnimation = new Decelerate().setMs(225).setValue(8.0);

    public StatusRender() {
        this.alphaAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        this.stencilAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        this.sliderAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        this.alphaAnimation.reset();
        this.stencilAnimation.reset();
        this.sliderAnimation.reset();
    }

    @Override
    public StatusRender position(float x, float y) {
        this.x = x - 8.0f;
        this.y = y;
        return this;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        class_4587 matrix = context.method_51448();
        this.alphaAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        this.stencilAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        this.sliderAnimation.setDirection(this.state ? Direction.FORWARDS : Direction.BACKWARDS);
        int stateColor = new Color(128, 128, 128, 255).getRGB();
        int opacity = this.alphaAnimation.getOutput().intValue();
        float sliderX = this.x + this.sliderAnimation.getOutput().floatValue();
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, 16.0, 8.0).round(4.0f).thickness(0.0f).softness(0.0f).outlineColor(new Color(128, 128, 128, 255).getRGB()).color(new Color(128, 128, 128, 40).getRGB()).build());
        rectangle.render(ShapeProperties.create(matrix, this.x, this.y, 16.0, 8.0).round(4.0f).thickness(0.0f).softness(0.0f).outlineColor(new Color(128, 128, 128, 255).getRGB()).color(Calculate.applyOpacity(stateColor, opacity)).build());
        rectangle.render(ShapeProperties.create(matrix, sliderX - 0.5f, this.y - 0.5f, 9.0, 9.0).round(4.5f).thickness(2.0f).softness(1.0f).outlineColor(new Color(155, 155, 165, 255).getRGB()).color(new Color(61, 67, 71, 255).getRGB(), new Color(71, 77, 81, 255).getRGB(), new Color(81, 87, 91, 255).getRGB(), new Color(91, 97, 101, 255).getRGB()).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (Calculate.isHovered(mouseX, mouseY, this.x, this.y, 16.0, 8.0) && button == 0) {
            this.state = !this.state;
            this.runnable.run();
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public StatusRender setState(boolean state) {
        this.state = state;
        return this;
    }

    public StatusRender setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }
}
