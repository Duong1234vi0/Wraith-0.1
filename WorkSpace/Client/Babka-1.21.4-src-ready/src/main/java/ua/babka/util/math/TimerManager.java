package ua.babka.util.math;

import java.lang.reflect.Field;
import net.minecraft.client.render.RenderTickCounter;
import ua.babka.util.IMinecraft;

public final class TimerManager
implements IMinecraft {
    private static final float DEFAULT_TPS = 20.0f;
    private static Field cachedField = null;

    private static Field findField() {
        if (cachedField != null) {
            return cachedField;
        }
        RenderTickCounter counter = mc.getRenderTickCounter();
        if (counter == null) {
            return null;
        }
        for (Class clazz = counter.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() != Float.TYPE) continue;
                try {
                    field.setAccessible(true);
                    float val = field.getFloat(counter);
                    if (!(Math.abs(val - 20.0f) < 5.0f)) continue;
                    cachedField = field;
                    return field;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        return null;
    }

    public static void setTimer(float multiplier) {
        RenderTickCounter counter = mc.getRenderTickCounter();
        if (counter == null) {
            return;
        }
        Field field = TimerManager.findField();
        if (field == null) {
            return;
        }
        try {
            field.setFloat(counter, 20.0f * multiplier);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static float getTimer() {
        RenderTickCounter counter = mc.getRenderTickCounter();
        if (counter == null) {
            return 1.0f;
        }
        Field field = TimerManager.findField();
        if (field == null) {
            return 1.0f;
        }
        try {
            return field.getFloat(counter) / 20.0f;
        }
        catch (Exception exception) {
            return 1.0f;
        }
    }

    public static void reset() {
        TimerManager.setTimer(1.0f);
    }
}

