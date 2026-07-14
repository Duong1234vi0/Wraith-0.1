/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.F_747_P;
import lightning.product.Setting;
import lightning.product.u_530_F;

public class NumberSetting
extends Setting<Float> {
    public float G_564_y;
    public float P_1922_E;
    public float u_1723_Y;
    public float v_4262_N;
    private final Supplier<Float> w_1484_f;
    private Supplier<Float> t_148_a;
    private Function<Float, String> s_956_w;

    private NumberSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, Supplier<Float> minSupplier, Supplier<Float> maxSupplier) {
        super(name, Float.valueOf(defaultVal));
        this.G_564_y = min;
        this.P_1922_E = max;
        this.v_4262_N = defaultVal;
        this.u_1723_Y = increment;
        if (visible != null) {
            this.n_1700_B(visible);
        }
        this.t_148_a = minSupplier;
        this.w_1484_f = maxSupplier;
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment) {
        this(name, defaultVal, min, max, increment, null, null, null);
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible) {
        this(name, defaultVal, min, max, increment, visible, null, null);
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment, Supplier<Float> maxSupplier) {
        this(name, defaultVal, min, max, increment, null, null, maxSupplier);
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, Supplier<Float> maxSupplier) {
        this(name, defaultVal, min, max, increment, visible, null, maxSupplier);
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment, NumberSetting maxValue) {
        this(name, defaultVal, min, max, increment, null, null, maxValue::J_1907_R);
        maxValue.n_1700_B(this);
    }

    public NumberSetting(String name, float defaultVal, float min, float max, float increment, BooleanSupplier visible, NumberSetting maxValue) {
        this(name, defaultVal, min, max, increment, visible, null, maxValue::J_1907_R);
        maxValue.n_1700_B(this);
    }

    @Override
    public void setValue(Float value) {
        float effMin = this.t_148_a != null ? this.t_148_a.get().floatValue() : this.G_564_y;
        float effMax = this.w_1484_f != null ? this.w_1484_f.get().floatValue() : this.P_1922_E;
        float rounded = F_747_P.R_4764_Y(value.floatValue(), this.u_1723_Y);
        float clamped = u_530_F.n_1700_B(rounded, effMin, effMax);
        if (Math.abs(((Float)this.getValue()).floatValue() - clamped) < 1.0E-6f) {
            return;
        }
        super.setValue(Float.valueOf(clamped));
    }

    public NumberSetting minFrom(NumberSetting minValue) {
        this.t_148_a = minValue::J_1907_R;
        return this;
    }

    public NumberSetting formatWith(Function<Float, String> formatter) {
        this.s_956_w = formatter;
        return this;
    }

    public String getFormattedValue() {
        if (this.s_956_w != null) {
            return this.s_956_w.apply((Float)this.getValue());
        }
        return String.valueOf(this.getValue());
    }
}

