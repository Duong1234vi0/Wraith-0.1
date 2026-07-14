package ua.babka.combat;

import ua.babka.util.IMinecraft;

public final class GCDFixer
implements IMinecraft {
    public static float getFixRotate(float rot) {
        return GCDFixer.getDeltaMouse(rot) * GCDFixer.getGCDValue();
    }

    public static float getGCDValue() {
        return (float)((double)GCDFixer.getGCD() * 0.15);
    }

    public static float getGCD() {
        double var11 = (Double)GCDFixer.mc.options.getMouseSensitivity().getValue() / (double)0.15f / 8.0;
        double var9 = Math.cbrt(var11);
        float f1 = (float)((var9 - (double)0.2f) / (double)0.6f * 0.6 + 0.2);
        return f1 * f1 * f1 * 8.0f;
    }

    public static float getDeltaMouse(float delta) {
        return Math.round(delta / GCDFixer.getGCDValue());
    }

    private GCDFixer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

