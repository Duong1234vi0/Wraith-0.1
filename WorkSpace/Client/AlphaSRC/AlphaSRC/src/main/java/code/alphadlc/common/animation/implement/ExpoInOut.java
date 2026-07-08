/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.common.animation.implement;

import code.alphadlc.common.animation.Animation;

public class ExpoInOut
extends Animation {
    @Override
    public double calculation(double value) {
        double x = value / (double)this.ms;
        if (x == 0.0) {
            return 0.0;
        }
        if (x == 1.0) {
            return 1.0;
        }
        return x < 0.5 ? Math.pow(2.0, 20.0 * x - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * x + 10.0)) / 2.0;
    }
}
