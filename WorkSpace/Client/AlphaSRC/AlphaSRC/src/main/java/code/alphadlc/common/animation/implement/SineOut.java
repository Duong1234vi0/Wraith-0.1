/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.common.animation.implement;

import code.alphadlc.common.animation.Animation;

public class SineOut
extends Animation {
    @Override
    public double calculation(double value) {
        double x = value / (double)this.ms;
        return Math.sin(x * Math.PI / 2.0);
    }
}
