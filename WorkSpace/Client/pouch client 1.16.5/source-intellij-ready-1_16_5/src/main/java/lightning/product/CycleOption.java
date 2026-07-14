/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.x_282_a;

public class CycleOption
extends M_2935_g {
    protected BiConsumer<V_4423_d, Integer> setter;
    protected BiFunction<V_4423_d, CycleOption, x_282_a> getter;

    public CycleOption(String translationKeyIn, BiConsumer<V_4423_d, Integer> setterIn, BiFunction<V_4423_d, CycleOption, x_282_a> getterIn) {
        super(translationKeyIn);
        this.setter = setterIn;
        this.getter = getterIn;
    }

    public void setValueIndex(V_4423_d options, int valueIn) {
        this.setter.accept(options, valueIn);
        options.J_1907_R();
    }

    @Override
    public V_2511_L createWidget(V_4423_d options, int xIn, int yIn, int widthIn) {
        return new Y_4729_x(xIn, yIn, widthIn, 20, this, this.getName(options), p_lambda$createWidget$0_2_ -> {
            this.setValueIndex(options, 1);
            p_lambda$createWidget$0_2_.setMessage(this.getName(options));
        });
    }

    public x_282_a getName(V_4423_d settings) {
        return this.getter.apply(settings, this);
    }
}


