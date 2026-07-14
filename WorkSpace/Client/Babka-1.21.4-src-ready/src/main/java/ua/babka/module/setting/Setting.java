package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.ISetting;

public abstract class Setting
implements ISetting {
    private final String name;
    public Supplier<Boolean> visible = () -> true;

    public Setting(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getValueAsString();

    public abstract void setValueFromString(String var1);
}

