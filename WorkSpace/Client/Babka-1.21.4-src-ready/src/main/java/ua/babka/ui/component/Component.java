package ua.babka.ui.component;

import ua.babka.ui.component.IComponent;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public abstract class Component
implements IComponent {
    public float x;
    public float y;
    public float width;
    public float height;
    private final Animation alphaAnim = new Animation(Easing.BACK_OUT, 550L);
    private final Animation alphaAnimSetting = new Animation(Easing.CUBIC_OUT, 280L);
    private final Animation alphaAnimBack = new Animation(Easing.CUBIC_OUT, 280L);

    public boolean isVisible() {
        return true;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Animation getAlphaAnim() {
        return this.alphaAnim;
    }

    public Animation getAlphaAnimSetting() {
        return this.alphaAnimSetting;
    }

    public Animation getAlphaAnimBack() {
        return this.alphaAnimBack;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

