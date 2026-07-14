/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import lightning.product.Setting;
import lightning.product.BooleanSetting;

public class MultiBooleanSetting
extends Setting<List<BooleanSetting>> {
    public MultiBooleanSetting(String name, BooleanSetting ... settings) {
        super(name, Arrays.asList(settings));
        for (BooleanSetting setting : (List)this.getValue()) {
            setting.G_564_y = setting.t_148_a();
        }
    }

    public MultiBooleanSetting(String name, Supplier<Boolean> visible, BooleanSetting ... settings) {
        super(name, Arrays.asList(settings));
        for (BooleanSetting setting : (List)this.getValue()) {
            setting.G_564_y = setting.t_148_a();
        }
        this.n_1700_B(visible);
    }

    public Boolean isOptionEnabled(String settingName) {
        return ((List)this.getValue()).stream().filter(s -> s.getName().equalsIgnoreCase(settingName)).findFirst().map(Setting::getValue).orElse(null);
    }

    public BooleanSetting getOption(int index) {
        List settings = (List)this.getValue();
        if (index >= 0 && index < settings.size()) {
            return (BooleanSetting)settings.get(index);
        }
        throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + settings.size());
    }
}

