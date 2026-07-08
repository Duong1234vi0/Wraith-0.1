/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.common.animation.implement;

import code.alphadlc.common.animation.Animation;

public class ExpoOut
extends Animation {
    @Override
    public double calculation(double value) {
        double x = value / (double)this.ms;
        return x == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * x);
    }
}
