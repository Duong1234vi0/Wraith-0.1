package ua.babka.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.util.Window;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ua.babka.util.IMinecraft;

public final class MathUtil
implements IMinecraft {
    public double PI2 = Math.PI * 2;
    private static final int TABLE_SIZE = 65536;
    private static final double TWO_PI = Math.PI * 2;
    private static final double[] TRIG_TABLE = new double[65536];

    public static double sin(double radians) {
        int index = (int)(radians * 10430.378350470453) & 0xFFFF;
        return TRIG_TABLE[index];
    }

    public static double cos(double radians) {
        int index = (int)(radians * 10430.378350470453 + 16384.0) & 0xFFFF;
        return TRIG_TABLE[index];
    }

    public static double random(double min, double max) {
        return ThreadLocalRandom.current().nextDouble() * (max - min) + min;
    }

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static double calc(double value) {
        Window rs = mc.getWindow();
        return value * rs.getScaleFactor() / 2.0;
    }

    public static double getBps(Entity player) {
        double dx = player.getX() - player.prevX;
        double dy = player.getY() - player.prevY;
        double dz = player.getZ() - player.prevZ;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        return 20.0 * distance;
    }

    public static float random(float min, float max) {
        return (float)(Math.random() * (double)(max - min) + (double)min);
    }

    public static double getDistanceSq(double x2, double y2, double z2) {
        double d0 = MathUtil.mc.player.getX() - x2;
        double d1 = MathUtil.mc.player.getY() - y2;
        double d2 = MathUtil.mc.player.getZ() - z2;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public static double getDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double d0 = x1 - x2;
        double d1 = y1 - y2;
        double d2 = z1 - z2;
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public static double getSqrDistance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double d0 = x1 - x2;
        double d1 = y1 - y2;
        double d2 = z1 - z2;
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public static float round(float value) {
        BigDecimal bd2 = new BigDecimal(value);
        bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
        return bd2.floatValue();
    }

    public static double getDistanceSq(Entity ent) {
        return MathUtil.getDistanceSq(ent.getX(), ent.getY(), ent.getZ());
    }

    public static double roundToDecimal(double n2, int point) {
        if (point == 0) {
            return Math.floor(n2);
        }
        double factor = Math.pow(10.0, point);
        return (double)Math.round(n2 * factor) / factor;
    }

    public static double angle(Vec3d vec3d, Vec3d other) {
        double lengthSq = vec3d.length() * other.length();
        if (lengthSq < 1.0E-4) {
            return 0.0;
        }
        double dot = vec3d.dotProduct(other);
        double arg = dot / lengthSq;
        if (arg > 1.0) {
            return 0.0;
        }
        if (arg < -1.0) {
            return 180.0;
        }
        return Math.acos(arg) * 180.0 / Math.PI;
    }

    public static Vec3d fromTo(Vec3d from, double x2, double y2, double z2) {
        return MathUtil.fromTo(from.x, from.y, from.z, x2, y2, z2);
    }

    public static float lerp(float f2, float st, float en) {
        return st + f2 * (en - st);
    }

    public static Vec3d fromTo(double x2, double y2, double z2, double x22, double y22, double z22) {
        return new Vec3d(x22 - x2, y22 - y2, z22 - z2);
    }

    public static float rad(float angle) {
        return (float)((double)angle * Math.PI / 180.0);
    }

    public static int clamp(int num, int min, int max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float clamp(float num, float min, float max) {
        return num < min ? min : Math.min(num, max);
    }

    public static double clamp(double num, double min, double max) {
        return num < min ? min : Math.min(num, max);
    }

    public static float sin(float value) {
        return MathHelper.sin((float)value);
    }

    public static float cos(float value) {
        return MathHelper.cos((float)value);
    }

    public static float wrapDegrees(float value) {
        return MathHelper.wrapDegrees((float)value);
    }

    public static double wrapDegrees(double value) {
        return MathHelper.wrapDegrees((double)value);
    }

    public static double square(double input) {
        return input * input;
    }

    public static double round(double value, int places) {
        BigDecimal bd2 = BigDecimal.valueOf(value);
        bd2 = bd2.setScale(places, RoundingMode.HALF_UP);
        return bd2.doubleValue();
    }

    public static float wrap(float angle) {
        float wrappedAngle = angle % 360.0f;
        if (wrappedAngle >= 180.0f) {
            wrappedAngle -= 360.0f;
        }
        if (wrappedAngle < -180.0f) {
            wrappedAngle += 360.0f;
        }
        return wrappedAngle;
    }

    public static Vec3d direction(float yaw) {
        return new Vec3d(Math.cos(MathUtil.degToRad(yaw + 90.0f)), 0.0, Math.sin(MathUtil.degToRad(yaw + 90.0f)));
    }

    public static float round(float value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd2 = BigDecimal.valueOf(value);
        bd2 = bd2.setScale(places, RoundingMode.FLOOR);
        return bd2.floatValue();
    }

    public static float round2(double value) {
        BigDecimal bd2 = new BigDecimal(value);
        bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
        return bd2.floatValue();
    }

    public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map, boolean descending) {
        LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        if (descending) {
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        } else {
            list.sort(Map.Entry.comparingByValue());
        }
        LinkedHashMap result = new LinkedHashMap();
        for (Map.Entry entry : list) {
            result.put(entry.getKey(), (Comparable)entry.getValue());
        }
        return result;
    }

    public static double degToRad(double deg) {
        return deg * 0.01745329238474369;
    }

    static {
        for (int i2 = 0; i2 < 65536; ++i2) {
            MathUtil.TRIG_TABLE[i2] = Math.sin((double)i2 * (Math.PI * 2) / 65536.0);
        }
    }
}

