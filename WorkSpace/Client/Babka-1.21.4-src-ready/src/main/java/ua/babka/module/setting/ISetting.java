package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.Setting;

public interface ISetting {
    public Setting setVisible(Supplier<Boolean> var1);
}

