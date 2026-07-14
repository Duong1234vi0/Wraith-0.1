/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.Y_4729_x;
import lightning.product.MinecraftClient;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class BooleanOption
extends M_2935_g {
    private final Predicate<V_4423_d> n_1700_B;
    private final BiConsumer<V_4423_d, Boolean> J_1907_R;
    @Nullable
    private final x_282_a R_4764_Y;

    public BooleanOption(String translationKeyIn, Predicate<V_4423_d> getter, BiConsumer<V_4423_d, Boolean> setter) {
        this(translationKeyIn, null, getter, setter);
    }

    public BooleanOption(String p_i242130_1_, @Nullable x_282_a p_i242130_2_, Predicate<V_4423_d> p_i242130_3_, BiConsumer<V_4423_d, Boolean> p_i242130_4_) {
        super(p_i242130_1_);
        this.n_1700_B = p_i242130_3_;
        this.J_1907_R = p_i242130_4_;
        this.R_4764_Y = p_i242130_2_;
    }

    public void n_1700_B(V_4423_d options, String valueIn) {
        this.n_1700_B(options, "true".equals(valueIn));
    }

    public void n_1700_B(V_4423_d options) {
        this.n_1700_B(options, !this.J_1907_R(options));
        options.J_1907_R();
    }

    private void n_1700_B(V_4423_d options, boolean valueIn) {
        this.J_1907_R.accept(options, valueIn);
    }

    public boolean J_1907_R(V_4423_d options) {
        return this.n_1700_B.test(options);
    }

    @Override
    public V_2511_L createWidget(V_4423_d options, int xIn, int yIn, int widthIn) {
        if (this.R_4764_Y != null) {
            this.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(this.R_4764_Y, 200));
        }
        return new Y_4729_x(xIn, yIn, widthIn, 20, this, this.R_4764_Y(options), p_216745_2_ -> {
            this.n_1700_B(options);
            p_216745_2_.setMessage(this.R_4764_Y(options));
        });
    }

    public x_282_a R_4764_Y(V_4423_d p_238152_1_) {
        return CommonComponents.n_1700_B(this.getBaseMessageTranslation(), this.J_1907_R(p_238152_1_));
    }
}



