/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_3532
 */
package code.alphadlc.features.impl.combat.components.interpolation;

import code.alphadlc.utils.display.interfaces.QuickImports;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_3532;

public class HumanAim
implements QuickImports {
    private static int lastTargetId = -1;
    private static long reactionStart = 0L;
    private static long reactionDelay = 0L;
    private static long rampDuration = 160L;
    private static float speedJitterValue = 1.0f;

    public static float reactionFactor(class_1297 target) {
        long elapsed;
        int id = target == null ? -1 : target.method_5628();
        long now = System.currentTimeMillis();
        if (id != lastTargetId) {
            lastTargetId = id;
            reactionStart = now;
            reactionDelay = ThreadLocalRandom.current().nextLong(90L, 230L);
            rampDuration = ThreadLocalRandom.current().nextLong(120L, 220L);
        }
        if ((elapsed = now - reactionStart) <= reactionDelay) {
            return 0.0f;
        }
        float t = (float)(elapsed - reactionDelay) / (float)rampDuration;
        if (t >= 1.0f) {
            return 1.0f;
        }
        return (float)(1.0 - Math.pow(1.0 - (double)t, 3.0));
    }

    public static void reset() {
        lastTargetId = -1;
        reactionStart = 0L;
        reactionDelay = 0L;
    }

    public static float noiseYaw() {
        double t = (double)System.nanoTime() / 1.0E9;
        return (float)(Math.sin(t * 2.3) * 0.16 + Math.sin(t * 5.7 + 1.1) * 0.05);
    }

    public static float noisePitch() {
        double t = (double)System.nanoTime() / 1.0E9;
        return (float)(Math.sin(t * 1.9 + 0.7) * 0.09 + Math.sin(t * 4.3 + 2.2) * 0.03);
    }

    public static float bezierEase(float t) {
        float c = class_3532.method_15363((float)t, (float)0.0f, (float)1.0f);
        return c * c * (3.0f - 2.0f * c);
    }

    public static float fovFactor(float diffYaw, float diffPitch) {
        float dist = (float)Math.hypot(diffYaw, diffPitch);
        if (dist <= 30.0f) {
            return 1.0f;
        }
        if (dist >= 120.0f) {
            return 0.55f;
        }
        return 1.0f - (dist - 30.0f) / 90.0f * 0.45f;
    }

    public static float speedJitter() {
        float target = (float)(1.0 + ThreadLocalRandom.current().nextGaussian() * 0.08);
        target = class_3532.method_15363((float)target, (float)0.85f, (float)1.15f);
        speedJitterValue += (target - speedJitterValue) * 0.08f;
        return speedJitterValue;
    }
}
