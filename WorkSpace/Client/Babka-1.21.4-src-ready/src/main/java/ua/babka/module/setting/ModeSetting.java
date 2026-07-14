package ua.babka.module.setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import ua.babka.module.setting.Setting;

public class ModeSetting
extends Setting {
    private String value;
    private final List<String> modes = new ArrayList<String>();

    public ModeSetting(String name, String defaultValue, String ... modes) {
        super(name);
        this.value = defaultValue;
        this.modes.addAll(Arrays.asList(modes));
    }

    public void setModes(List<String> newModes) {
        this.modes.clear();
        this.modes.addAll(newModes);
        if (!this.modes.contains(this.value)) {
            this.value = this.modes.isEmpty() ? "" : this.modes.get(0);
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        if (this.modes.contains(value)) {
            this.value = value;
        }
    }

    public boolean is(String equalsIgnoreCase) {
        return this.value.equalsIgnoreCase(equalsIgnoreCase);
    }

    public List<String> getModes() {
        return this.modes;
    }

    public int getIndex() {
        return this.modes.indexOf(this.value);
    }

    public void setIndex(int index) {
        if (index >= 0 && index < this.modes.size()) {
            this.value = this.modes.get(index);
        }
    }

    public void cycle() {
        int nextIndex = (this.getIndex() + 1) % this.modes.size();
        this.setValue(this.modes.get(nextIndex));
    }

    @Override
    public String getValueAsString() {
        return this.value;
    }

    @Override
    public void setValueFromString(String value) {
        if (this.modes.contains(value)) {
            this.value = value;
        }
    }

    @Override
    public ModeSetting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }
}

