package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.Setting;

public class ColorSetting
extends Setting {
    private int value;

    public ColorSetting(String name, Integer defaultValue) {
        super(name);
        this.value = defaultValue;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getValueAsString() {
        return Integer.toString(this.value);
    }

    @Override
    public void setValueFromString(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public ColorSetting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }
}

