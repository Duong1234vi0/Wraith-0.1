/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_9779
 */
package code.alphadlc.utils.client.timer;

import code.alphadlc.utils.client.timer.IRenderTickCounterDynamic;
import net.minecraft.class_310;
import net.minecraft.class_9779;

public class TimerManager {
    private static float timerSpeed = 1.0f;

    public static void setTimer(float speed) {
        timerSpeed = Math.max(0.1f, Math.min(speed, 10.0f));
        class_310 mc = class_310.method_1551();
        if (mc == null) {
            return;
        }
        class_9779 tickCounter = mc.method_61966();
        if (tickCounter instanceof IRenderTickCounterDynamic) {
            ((IRenderTickCounterDynamic)tickCounter).setSpeedMultiplier(speed);
        }
    }

    public static void reset() {
        TimerManager.setTimer(1.0f);
    }

    public static float getTimerSpeed() {
        return timerSpeed;
    }
}
