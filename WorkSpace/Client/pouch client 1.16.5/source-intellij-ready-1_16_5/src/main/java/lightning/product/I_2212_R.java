/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.o_3730_L;
import lightning.product.u_530_F;
import lightning.product.x_282_a;
import net.optifine.Config;

public class I_2212_R
extends M_2935_g {
    protected final float stepSize;
    protected final double minValue;
    protected double maxValue;
    protected Function<V_4423_d, Double> getter;
    protected BiConsumer<V_4423_d, Double> setter;
    protected BiFunction<V_4423_d, I_2212_R, x_282_a> getDisplayStringFunc;
    protected double[] stepValues;

    public I_2212_R(String translationKey, double minValueIn, double maxValueIn, float stepSizeIn, Function<V_4423_d, Double> getter, BiConsumer<V_4423_d, Double> setter, BiFunction<V_4423_d, I_2212_R, x_282_a> getDisplayString) {
        super(translationKey);
        this.minValue = minValueIn;
        this.maxValue = maxValueIn;
        this.stepSize = stepSizeIn;
        this.getter = getter;
        this.setter = setter;
        this.getDisplayStringFunc = getDisplayString;
    }

    public I_2212_R(String p_i242103_1_, double p_i242103_2_, double p_i242103_4_, double[] p_i242103_6_, Function<V_4423_d, Double> p_i242103_7_, BiConsumer<V_4423_d, Double> p_i242103_8_, BiFunction<V_4423_d, I_2212_R, x_282_a> p_i242103_9_) {
        super(p_i242103_1_);
        this.minValue = p_i242103_2_;
        this.maxValue = p_i242103_4_;
        this.stepSize = 0.0f;
        this.getter = p_i242103_7_;
        this.setter = p_i242103_8_;
        this.getDisplayStringFunc = p_i242103_9_;
        this.stepValues = p_i242103_6_;
        if (p_i242103_6_ != null) {
            p_i242103_6_ = (double[])p_i242103_6_.clone();
            Arrays.sort(p_i242103_6_);
        }
    }

    @Override
    public V_2511_L createWidget(V_4423_d options, int xIn, int yIn, int widthIn) {
        return new o_3730_L(options, xIn, yIn, widthIn, 20, this);
    }

    public double normalizeValue(double value) {
        return u_530_F.n_1700_B((this.snapToStepClamp(value) - this.minValue) / (this.maxValue - this.minValue), 0.0, 1.0);
    }

    public double denormalizeValue(double value) {
        return this.snapToStepClamp(u_530_F.G_564_y(u_530_F.n_1700_B(value, 0.0, 1.0), this.minValue, this.maxValue));
    }

    private double snapToStepClamp(double valueIn) {
        if (this.stepSize > 0.0f) {
            valueIn = this.stepSize * (float)Math.round(valueIn / (double)this.stepSize);
        }
        if (this.stepValues != null) {
            for (int i = 0; i < this.stepValues.length; ++i) {
                double d1;
                double d0 = i <= 0 ? -1.7976931348623157E308 : (this.stepValues[i - 1] + this.stepValues[i]) / 2.0;
                double d = d1 = i >= this.stepValues.length - 1 ? Double.MAX_VALUE : (this.stepValues[i] + this.stepValues[i + 1]) / 2.0;
                if (!Config.between(valueIn, d0, d1)) continue;
                valueIn = this.stepValues[i];
                break;
            }
        }
        return u_530_F.n_1700_B(valueIn, this.minValue, this.maxValue);
    }

    public double getMinValue() {
        return this.minValue;
    }

    public double getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(float valueIn) {
        this.maxValue = valueIn;
    }

    public void set(V_4423_d options, double valueIn) {
        this.setter.accept(options, valueIn);
    }

    public double get(V_4423_d options) {
        return this.getter.apply(options);
    }

    public x_282_a func_238334_c_(V_4423_d p_238334_1_) {
        return this.getDisplayStringFunc.apply(p_238334_1_, this);
    }
}

