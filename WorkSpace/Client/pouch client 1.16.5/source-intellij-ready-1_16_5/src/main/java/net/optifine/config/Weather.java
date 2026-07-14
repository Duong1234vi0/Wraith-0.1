/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.b_4507_u;

public enum Weather {
    CLEAR,
    RAIN,
    THUNDER;


    public static Weather getWeather(b_4507_u world, float partialTicks) {
        float f = world.u_1723_Y(partialTicks);
        if (f > 0.5f) {
            return THUNDER;
        }
        float f1 = world.w_1484_f(partialTicks);
        return f1 > 0.5f ? RAIN : CLEAR;
    }
}

