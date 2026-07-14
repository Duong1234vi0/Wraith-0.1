package ua.babka.module.setting;

import java.util.function.Supplier;
import ua.babka.module.setting.Setting;
import ua.babka.module.setting.ValueUnit;

public class SliderSetting
extends Setting {
    private double value;
    private ValueUnit unit;
    private final double min;
    private final double max;
    private final double step;

    public SliderSetting(String name, ValueUnit unit, double defaultValue, double min, double max, double step) {
        super(name);
        this.value = defaultValue;
        this.unit = unit;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public SliderSetting(String name, double defaultValue, double min, double max, double step) {
        super(name);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public String getFormattedValue() {
        return this.unit.format(this.value);
    }

    public int getIntValue() {
        return (int)this.value;
    }

    public float getFloatValue() {
        return (float)this.value;
    }

    public void setValue(double value) {
        this.value = Math.max(this.min, Math.min(this.max, value));
    }

    public void increment() {
        this.setValue(this.value + this.step);
    }

    public void decrement() {
        this.setValue(this.value - this.step);
    }

    @Override
    public String getValueAsString() {
        return Double.toString(this.value);
    }

    @Override
    public void setValueFromString(String value) {
        try {
            this.setValue(Double.parseDouble(value));
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
    }

    @Override
    public SliderSetting setVisible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }

    public double getValue() {
        return this.value;
    }

    public ValueUnit getUnit() {
        return this.unit;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getStep() {
        return this.step;
    }

    public void setUnit(ValueUnit unit) {
        this.unit = unit;
    }
}

