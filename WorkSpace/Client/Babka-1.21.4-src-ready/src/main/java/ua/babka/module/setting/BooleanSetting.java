package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.Setting;
import ua.babka.util.animation.Animation;
import ua.babka.util.animation.Easing;

public class BooleanSetting
extends Setting {
    private final Animation animation = new Animation(Easing.QUINTIC_OUT, 400L);
    private final Animation clickAnimation = new Animation(Easing.QUINTIC_OUT, 400L);
    private boolean clicked;
    private boolean value;
    private int key = -1;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.value = defaultValue;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String getValueAsString() {
        return Boolean.toString(this.value);
    }

    @Override
    public void setValueFromString(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public void toggle() {
        this.value = !this.value;
    }

    @Override
    public BooleanSetting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public Animation getClickAnimation() {
        return this.clickAnimation;
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public int getKey() {
        return this.key;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }
}

