/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import lightning.product.I_2212_R;
import lightning.product.V_4423_d;
import lightning.product.x_282_a;

public class LogaritmicProgressOption
extends I_2212_R {
    public LogaritmicProgressOption(String translationKey, double minValueIn, double maxValueIn, float stepSizeIn, Function<V_4423_d, Double> getterIn, BiConsumer<V_4423_d, Double> setterIn, BiFunction<V_4423_d, I_2212_R, x_282_a> getterDisplayString) {
        super(translationKey, minValueIn, maxValueIn, stepSizeIn, getterIn, setterIn, getterDisplayString);
    }

    @Override
    public double normalizeValue(double value) {
        return Math.log(value / this.minValue) / Math.log(this.maxValue / this.minValue);
    }

    @Override
    public double denormalizeValue(double value) {
        return this.minValue * Math.pow(Math.E, Math.log(this.maxValue / this.minValue) * value);
    }
}


