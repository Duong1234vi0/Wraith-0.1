/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_9848
 *  org.joml.Vector3d
 */
package code.alphadlc.utils.math.calc;

import code.alphadlc.utils.display.interfaces.QuickImports;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_9848;
import org.joml.Vector3d;

public final class Calculate
implements QuickImports {
    public static double PI2 = Math.PI * 2;

    public static boolean isHovered(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double computeGcd() {
        return Math.pow((Double)Calculate.mc.field_1690.method_42495().method_41753() * 0.6 + 0.2, 3.0) * 1.2;
    }

    public static float getTickDelta() {
        return mc.method_61966().method_60637(true);
    }

    public static int getRandom(int min, int max) {
        return (int)Calculate.getRandom((float)min, (float)max + 1.0f);
    }

    public static float getRandom(float min, float max) {
        return (float)Calculate.getRandom((double)min, (double)max);
    }

    public static double getRandom(double min, double max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            double d = min;
            min = max;
            max = d;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static void scale(class_4587 stack, float x, float y, float scale, Runnable data) {
        if (scale != 1.0f) {
            float scale2 = 0.5f + scale / 2.0f;
            stack.method_22903();
            stack.method_46416(x, y, 0.0f);
            stack.method_22905(scale2, scale2, 1.0f);
            stack.method_46416(-x, -y, 0.0f);
            Calculate.setAlpha(scale, data);
            stack.method_22909();
        } else {
            data.run();
        }
    }

    public static void scale(class_4587 stack, float x, float y, float scaleX, float scaleY, Runnable data) {
        float sumScale = scaleX * scaleY;
        if (sumScale != 1.0f) {
            stack.method_22903();
            stack.method_46416(x, y, 0.0f);
            stack.method_22905(scaleX, scaleY, 1.0f);
            stack.method_46416(-x, -y, 0.0f);
            Calculate.setAlpha(sumScale, data);
            stack.method_22909();
        } else {
            data.run();
        }
    }

    public static float textScrolling(float textWidth) {
        int speed = (int)(textWidth * 75.0f);
        return (float)class_3532.method_15350((double)((double)(System.currentTimeMillis() % (long)speed) * Math.PI / (double)speed), (double)0.0, (double)1.0) * textWidth;
    }

    public static void setAlpha(float alpha, Runnable data) {
        Calculate.setColor(1.0f, 1.0f, 1.0f, alpha, data);
    }

    public static void setColor(float red, float green, float blue, float alpha, Runnable data) {
        RenderSystem.setShaderColor((float)class_3532.method_15363((float)red, (float)0.0f, (float)1.0f), (float)class_3532.method_15363((float)green, (float)0.0f, (float)1.0f), (float)class_3532.method_15363((float)blue, (float)0.0f, (float)1.0f), (float)class_3532.method_15363((float)alpha, (float)0.0f, (float)1.0f));
        data.run();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static double round(double num, double increment) {
        double rounded = (double)Math.round(num / increment) * increment;
        return (double)Math.round(rounded * 100.0) / 100.0;
    }

    public static int floorNearestMulN(int x, int n) {
        return n * (int)Math.floor((double)x / (double)n);
    }

    public static int getRed(int hex) {
        return hex >> 16 & 0xFF;
    }

    public static int getGreen(int hex) {
        return hex >> 8 & 0xFF;
    }

    public static int getBlue(int hex) {
        return hex & 0xFF;
    }

    public static int getAlpha(int hex) {
        return hex >> 24 & 0xFF;
    }

    public static int applyOpacity(int color, float opacity) {
        return class_9848.method_61324((int)((int)((float)Calculate.getAlpha(color) * opacity / 255.0f)), (int)Calculate.getRed(color), (int)Calculate.getGreen(color), (int)Calculate.getBlue(color));
    }

    public static class_243 cosSin(int i, int size, double width) {
        int index = Math.min(i, size);
        float cos = (float)(Math.cos((double)index * PI2 / (double)size) * width);
        float sin = (float)(-Math.sin((double)index * PI2 / (double)size) * width);
        return new class_243((double)cos, 0.0, (double)sin);
    }

    public static double absSinAnimation(double input) {
        return Math.abs(1.0 + Math.sin(input)) / 2.0;
    }

    public static Vector3d interpolate(Vector3d prevPos, Vector3d pos) {
        return new Vector3d(Calculate.interpolate(prevPos.x, pos.x), Calculate.interpolate(prevPos.y, pos.y), Calculate.interpolate(prevPos.z, pos.z));
    }

    public static float interpolate(double oldValue, double newValue, double interpolationValue) {
        return (float)(oldValue + (newValue - oldValue) * interpolationValue);
    }

    public static float interpolate(float prev, float to, float value) {
        return prev + (to - prev) * value;
    }

    public static class_243 interpolate(class_243 prevPos, class_243 pos) {
        return new class_243(Calculate.interpolate(prevPos.field_1352, pos.field_1352), Calculate.interpolate(prevPos.field_1351, pos.field_1351), Calculate.interpolate(prevPos.field_1350, pos.field_1350));
    }

    public static class_243 interpolate(class_1297 entity) {
        if (entity == null) {
            return class_243.field_1353;
        }
        return new class_243(Calculate.interpolate(entity.field_6014, entity.method_23317()), Calculate.interpolate(entity.field_6036, entity.method_23318()), Calculate.interpolate(entity.field_5969, entity.method_23321()));
    }

    public static float interpolate(float prev, float orig) {
        return class_3532.method_16439((float)tickCounter.method_60637(false), (float)prev, (float)orig);
    }

    public static double interpolate(double prev, double orig) {
        return class_3532.method_16436((double)tickCounter.method_60637(false), (double)prev, (double)orig);
    }

    public static float interpolateSmooth(double smooth, float prev, float orig) {
        return (float)class_3532.method_16436((double)((double)tickCounter.method_60638() / smooth), (double)prev, (double)orig);
    }

    private Calculate() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
