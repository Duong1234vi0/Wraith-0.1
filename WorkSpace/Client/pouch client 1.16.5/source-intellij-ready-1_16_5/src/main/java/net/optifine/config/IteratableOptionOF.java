/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.CycleOption;
import lightning.product.M_2935_g;
import lightning.product.V_4423_d;
import lightning.product.x_282_a;

public class IteratableOptionOF
extends CycleOption {
    public IteratableOptionOF(String nameIn) {
        super(nameIn, null, null);
        this.setter = this::nextOptionValue;
        this.getter = this::getOptionText;
    }

    public void nextOptionValue(V_4423_d gameSettings, int increment) {
        gameSettings.n_1700_B((M_2935_g)this, increment);
    }

    public x_282_a getOptionText(V_4423_d gameSettings, CycleOption option) {
        return gameSettings.J_1907_R(option);
    }
}


