package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.Setting;

public class StringSetting
extends Setting {
    private String value;

    public StringSetting(String name, String defaultValue) {
        super(name);
        this.value = defaultValue;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValueAsString() {
        return this.value;
    }

    @Override
    public void setValueFromString(String value) {
        this.value = value;
    }

    @Override
    public Setting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }
}

