package ua.babka.module.setting;

import java.util.Arrays;
import java.util.function.Supplier;
import ua.babka.module.setting.Setting;
import ua.babka.theme.Theme;

public class ThemeSetting
extends Setting {
    private final Theme[] themes;
    private Theme current;

    public ThemeSetting(Theme defaultValue, Theme ... themes) {
        super("Гавно");
        this.setValue(defaultValue);
        this.themes = themes;
    }

    public void setValue(Theme theme) {
        if (this.current == null) {
            this.current = theme;
        }
        if (this.current != theme) {
            theme.startAnimation(this.current.color1, this.current.color2);
            this.current = theme;
        }
    }

    public Theme getValue() {
        return this.current;
    }

    @Override
    public String getValueAsString() {
        return this.getValue().name;
    }

    @Override
    public void setValueFromString(String value) {
        Arrays.stream(this.themes).filter(theme -> theme.name.equals(value)).forEach(this::setValue);
    }

    @Override
    public ThemeSetting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }

    public Theme[] getThemes() {
        return this.themes;
    }

    public Theme getCurrent() {
        return this.current;
    }
}

