/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_332
 */
package code.alphadlc.display.screens.clickgui.components.implement.window;

import code.alphadlc.common.animation.Animation;
import code.alphadlc.common.animation.Direction;
import code.alphadlc.common.animation.implement.Decelerate;
import code.alphadlc.display.screens.clickgui.components.AbstractComponent;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_332;

public abstract class AbstractWindow
extends AbstractComponent {
    protected boolean dragging;
    private boolean draggable;
    protected int dragX;
    protected int dragY;
    private final Animation scaleAnimation = new Decelerate().setValue(1.0).setMs(100);

    public AbstractWindow() {
        this.scaleAnimation.setDirection(Direction.FORWARDS);
    }

    public AbstractWindow draggable(boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    @Override
    public AbstractWindow size(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public AbstractWindow position(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0 && this.draggable) {
            this.dragging = true;
            this.dragX = (int)((double)this.x - mouseX);
            this.dragY = (int)((double)this.y - mouseY);
            return true;
        }
        return false;
    }

    @Override
    public void render(class_332 context, int mouseX, int mouseY, float delta) {
        if (this.dragging && this.draggable) {
            this.x = mouseX + this.dragX;
            this.y = mouseY + this.dragY;
        }
        float scale = this.scaleAnimation.getOutput().floatValue();
        Calculate.scale(context.method_51448(), this.x + this.width / 2.0f, this.y + this.height / 2.0f, scale, () -> this.drawWindow(context, mouseX, mouseY, delta));
    }

    protected abstract void drawWindow(class_332 var1, int var2, int var3, float var4);

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
        return true;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
    }

    public void startCloseAnimation() {
        this.scaleAnimation.setDirection(Direction.BACKWARDS);
    }

    public boolean isCloseAnimationFinished() {
        return this.scaleAnimation.isFinished(Direction.BACKWARDS);
    }

    public Animation getScaleAnimation() {
        return this.scaleAnimation;
    }
}
