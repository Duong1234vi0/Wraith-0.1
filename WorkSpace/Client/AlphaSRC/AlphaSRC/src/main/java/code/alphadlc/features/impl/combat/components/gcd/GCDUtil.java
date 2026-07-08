/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.combat.components.gcd;

import code.alphadlc.utils.display.interfaces.QuickImports;

public class GCDUtil
implements QuickImports {
    public static float getFixedRotation(float rot) {
        return GCDUtil.getDeltaMouse(rot) * GCDUtil.getGCDValue();
    }

    public static float getGCDValue() {
        return (float)((double)GCDUtil.getGCD() * 0.15);
    }

    public static float getGCD() {
        double sens = 0.5;
        if (GCDUtil.mc.field_1690 != null) {
            sens = (Double)GCDUtil.mc.field_1690.method_42495().method_41753();
        }
        double f = sens * (double)0.6f + (double)0.2f;
        return (float)(f * f * f * 8.0);
    }

    public static float getDeltaMouse(float delta) {
        return Math.round(delta / GCDUtil.getGCDValue());
    }
}
