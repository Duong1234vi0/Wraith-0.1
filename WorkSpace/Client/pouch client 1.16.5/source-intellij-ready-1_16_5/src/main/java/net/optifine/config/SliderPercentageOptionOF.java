/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import lightning.product.I_2212_R;
import lightning.product.M_2935_g;
import lightning.product.V_4423_d;
import lightning.product.x_282_a;

public class SliderPercentageOptionOF
extends I_2212_R {
    public SliderPercentageOptionOF(String name) {
        this(name, 0.0, 1.0, 0.0f);
    }

    public SliderPercentageOptionOF(String name, double valueMin, double valueMax, float step) {
        super(name, valueMin, valueMax, step, (Function<V_4423_d, Double>)null, (BiConsumer<V_4423_d, Double>)null, (BiFunction<V_4423_d, I_2212_R, x_282_a>)null);
        this.getter = this::getOptionValue;
        this.setter = this::setOptionValue;
        this.getDisplayStringFunc = this::getOptionText;
    }

    public SliderPercentageOptionOF(String name, double valueMin, double valueMax, double[] stepValues) {
        super(name, valueMin, valueMax, stepValues, (Function<V_4423_d, Double>)null, (BiConsumer<V_4423_d, Double>)null, (BiFunction<V_4423_d, I_2212_R, x_282_a>)null);
        this.getter = this::getOptionValue;
        this.setter = this::setOptionValue;
        this.getDisplayStringFunc = this::getOptionText;
    }

    private double getOptionValue(V_4423_d gameSettings) {
        return gameSettings.n_1700_B(this);
    }

    private void setOptionValue(V_4423_d gameSettings, double value) {
        gameSettings.n_1700_B((M_2935_g)this, value);
    }

    private x_282_a getOptionText(V_4423_d gameSettings, I_2212_R sliderPercentageOption) {
        return gameSettings.J_1907_R(sliderPercentageOption);
    }
}

