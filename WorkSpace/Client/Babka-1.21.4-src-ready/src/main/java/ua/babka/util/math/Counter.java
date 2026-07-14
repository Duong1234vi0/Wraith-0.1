package ua.babka.util.math;

import net.minecraft.util.math.MathHelper;
import ua.babka.util.IMinecraft;

public final class Counter
implements IMinecraft {
    private static int currentFPS;

    public static void updateFPS() {
        int prevFPS = mc.getCurrentFps();
        currentFPS = MathHelper.lerp((float)0.5f, (int)prevFPS, (int)currentFPS);
    }

    private Counter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static int getCurrentFPS() {
        return currentFPS;
    }
}

