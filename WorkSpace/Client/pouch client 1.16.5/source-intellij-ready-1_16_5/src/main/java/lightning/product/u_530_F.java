/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.math.NumberUtils
 */
package lightning.product;

import java.util.Random;
import java.util.UUID;
import java.util.function.IntPredicate;
import lightning.product.j_3341_s;
import lightning.product.z_3539_x;
import net.optifine.util.MathUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class u_530_F {
    public static final float n_1700_B = u_530_F.R_4764_Y(2.0f);
    private static final int v_4262_N = 12;
    private static final int w_1484_f = 4095;
    private static final int t_148_a = 4096;
    private static final int s_956_w = 1024;
    public static final float J_1907_R = MathUtils.roundToFloat(Math.PI);
    public static final float R_4764_Y = MathUtils.roundToFloat(Math.PI * 2);
    public static final float G_564_y = MathUtils.roundToFloat(1.5707963267948966);
    private static final float u_2550_I = MathUtils.roundToFloat(651.8986469044033);
    public static final float P_1922_E = MathUtils.roundToFloat(Math.PI / 180);
    private static final float[] M_588_G = new float[4096];
    public static boolean u_1723_Y = false;
    private static final float[] P_4830_p = j_3341_s.n_1700_B(new float[65536], p_lambda$static$0_0_ -> {
        for (int i = 0; i < ((float[])p_lambda$static$0_0_).length; ++i) {
            p_lambda$static$0_0_[i] = (float)Math.sin((double)i * Math.PI * 2.0 / 65536.0);
        }
    });
    private static final Random h_1847_R = new Random();
    private static final int[] Q_4569_t = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
    private static final double M_182_A = Double.longBitsToDouble(4805340802404319232L);
    private static final double[] t_1786_h = new double[257];
    private static final double[] multiplayerClientSuggestionProvider = new double[257];

    public static float n_1700_B(float value) {
        return u_1723_Y ? M_588_G[(int)(value * u_2550_I) & 0xFFF] : P_4830_p[(int)(value * 10430.378f) & 0xFFFF];
    }

    public static float J_1907_R(float value) {
        return u_1723_Y ? M_588_G[(int)(value * u_2550_I + 1024.0f) & 0xFFF] : P_4830_p[(int)(value * 10430.378f + 16384.0f) & 0xFFFF];
    }

    public static float R_4764_Y(float value) {
        return (float)Math.sqrt(value);
    }

    public static float n_1700_B(double value) {
        return (float)Math.sqrt(value);
    }

    public static int G_564_y(float value) {
        int i = (int)value;
        return value < (float)i ? i - 1 : i;
    }

    public static int J_1907_R(double value) {
        return (int)(value + 1024.0) - 1024;
    }

    public static int R_4764_Y(double value) {
        int i = (int)value;
        return value < (double)i ? i - 1 : i;
    }

    public static long G_564_y(double value) {
        long i = (long)value;
        return value < (double)i ? i - 1L : i;
    }

    public static float P_1922_E(float value) {
        return Math.abs(value);
    }

    public static int n_1700_B(int value) {
        return Math.abs(value);
    }

    public static int u_1723_Y(float value) {
        int i = (int)value;
        return value > (float)i ? i + 1 : i;
    }

    public static int P_1922_E(double value) {
        int i = (int)value;
        return value > (double)i ? i + 1 : i;
    }

    public static int n_1700_B(int num, int min, int max) {
        if (num < min) {
            return min;
        }
        return num > max ? max : num;
    }

    public static long n_1700_B(long num, long min, long max) {
        if (num < min) {
            return min;
        }
        return num > max ? max : num;
    }

    public static float n_1700_B(float num, float min, float max) {
        if (num < min) {
            return min;
        }
        return num > max ? max : num;
    }

    public static double n_1700_B(double num, double min, double max) {
        if (num < min) {
            return min;
        }
        return num > max ? max : num;
    }

    public static double J_1907_R(double lowerBnd, double upperBnd, double slide) {
        if (slide < 0.0) {
            return lowerBnd;
        }
        return slide > 1.0 ? upperBnd : u_530_F.G_564_y(slide, lowerBnd, upperBnd);
    }

    public static double n_1700_B(double x, double y) {
        if (x < 0.0) {
            x = -x;
        }
        if (y < 0.0) {
            y = -y;
        }
        return x > y ? x : y;
    }

    public static int n_1700_B(int x, int y) {
        return Math.floorDiv(x, y);
    }

    public static int n_1700_B(Random random, int minimum, int maximum) {
        return minimum >= maximum ? minimum : random.nextInt(maximum - minimum + 1) + minimum;
    }

    public static float n_1700_B(Random random, float minimum, float maximum) {
        return minimum >= maximum ? minimum : random.nextFloat() * (maximum - minimum) + minimum;
    }

    public static double n_1700_B(Random random, double minimum, double maximum) {
        return minimum >= maximum ? minimum : random.nextDouble() * (maximum - minimum) + minimum;
    }

    public static double n_1700_B(long[] values) {
        long i = 0L;
        for (long j : values) {
            i += j;
        }
        return (double)i / (double)values.length;
    }

    public static boolean n_1700_B(float x, float y) {
        return Math.abs(y - x) < 1.0E-5f;
    }

    public static boolean J_1907_R(double x, double y) {
        return Math.abs(y - x) < (double)1.0E-5f;
    }

    public static int J_1907_R(int x, int y) {
        return Math.floorMod(x, y);
    }

    public static float J_1907_R(float numerator, float denominator) {
        return (numerator % denominator + denominator) % denominator;
    }

    public static double R_4764_Y(double numerator, double denominator) {
        return (numerator % denominator + denominator) % denominator;
    }

    public static int J_1907_R(int angle) {
        int i = angle % 360;
        if (i >= 180) {
            i -= 360;
        }
        if (i < -180) {
            i += 360;
        }
        return i;
    }

    public static float v_4262_N(float value) {
        float f = value % 360.0f;
        if (f >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static double u_1723_Y(double value) {
        double d0 = value % 360.0;
        if (d0 >= 180.0) {
            d0 -= 360.0;
        }
        if (d0 < -180.0) {
            d0 += 360.0;
        }
        return d0;
    }

    public static float R_4764_Y(float p_203302_0_, float p_203302_1_) {
        return u_530_F.v_4262_N(p_203302_1_ - p_203302_0_);
    }

    public static float G_564_y(float p_203301_0_, float p_203301_1_) {
        return u_530_F.P_1922_E(u_530_F.R_4764_Y(p_203301_0_, p_203301_1_));
    }

    public static float J_1907_R(float p_219800_0_, float p_219800_1_, float p_219800_2_) {
        float f = u_530_F.R_4764_Y(p_219800_0_, p_219800_1_);
        float f1 = u_530_F.n_1700_B(f, -p_219800_2_, p_219800_2_);
        return p_219800_1_ - f1;
    }

    public static float R_4764_Y(float p_203300_0_, float p_203300_1_, float p_203300_2_) {
        p_203300_2_ = u_530_F.P_1922_E(p_203300_2_);
        return p_203300_0_ < p_203300_1_ ? u_530_F.n_1700_B(p_203300_0_ + p_203300_2_, p_203300_0_, p_203300_1_) : u_530_F.n_1700_B(p_203300_0_ - p_203300_2_, p_203300_1_, p_203300_0_);
    }

    public static float G_564_y(float p_203303_0_, float p_203303_1_, float p_203303_2_) {
        float f = u_530_F.R_4764_Y(p_203303_0_, p_203303_1_);
        return u_530_F.R_4764_Y(p_203303_0_, p_203303_0_ + f, p_203303_2_);
    }

    public static int n_1700_B(String value, int defaultValue) {
        return NumberUtils.toInt((String)value, (int)defaultValue);
    }

    public static int R_4764_Y(int value) {
        int i = value - 1;
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    public static boolean G_564_y(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    public static int P_1922_E(int value) {
        value = u_530_F.G_564_y(value) ? value : u_530_F.R_4764_Y(value);
        return Q_4569_t[(int)((long)value * 125613361L >> 27) & 0x1F];
    }

    public static int u_1723_Y(int value) {
        return u_530_F.P_1922_E(value) - (u_530_F.G_564_y(value) ? 0 : 1);
    }

    public static int R_4764_Y(int number, int interval) {
        int i;
        if (interval == 0) {
            return 0;
        }
        if (number == 0) {
            return interval;
        }
        if (number < 0) {
            interval *= -1;
        }
        return (i = number % interval) == 0 ? number : number + interval - i;
    }

    public static int P_1922_E(float rIn, float gIn, float bIn) {
        return u_530_F.J_1907_R(u_530_F.G_564_y(rIn * 255.0f), u_530_F.G_564_y(gIn * 255.0f), u_530_F.G_564_y(bIn * 255.0f));
    }

    public static int J_1907_R(int rIn, int gIn, int bIn) {
        int i = (rIn << 8) + gIn;
        return (i << 8) + bIn;
    }

    public static float w_1484_f(float number) {
        return number - (float)u_530_F.G_564_y(number);
    }

    public static double v_4262_N(double number) {
        return number - (double)u_530_F.G_564_y(number);
    }

    public static long n_1700_B(z_3539_x pos) {
        return u_530_F.R_4764_Y(pos.getX(), pos.getY(), pos.getZ());
    }

    public static long R_4764_Y(int x, int y, int z) {
        long i = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
        i = i * i * 42317861L + i * 11L;
        return i >> 16;
    }

    public static UUID n_1700_B(Random rand) {
        long i = rand.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
        long j = rand.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
        return new UUID(i, j);
    }

    public static UUID n_1700_B() {
        return u_530_F.n_1700_B(h_1847_R);
    }

    public static double R_4764_Y(double p_233020_0_, double p_233020_2_, double p_233020_4_) {
        return (p_233020_0_ - p_233020_2_) / (p_233020_4_ - p_233020_2_);
    }

    public static double G_564_y(double p_181159_0_, double p_181159_2_) {
        boolean flag2;
        boolean flag1;
        boolean flag;
        double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;
        if (Double.isNaN(d0)) {
            return Double.NaN;
        }
        boolean bl = flag = p_181159_0_ < 0.0;
        if (flag) {
            p_181159_0_ = -p_181159_0_;
        }
        boolean bl2 = flag1 = p_181159_2_ < 0.0;
        if (flag1) {
            p_181159_2_ = -p_181159_2_;
        }
        boolean bl3 = flag2 = p_181159_0_ > p_181159_2_;
        if (flag2) {
            double d1 = p_181159_2_;
            p_181159_2_ = p_181159_0_;
            p_181159_0_ = d1;
        }
        double d9 = u_530_F.w_1484_f(d0);
        p_181159_2_ *= d9;
        double d2 = M_182_A + (p_181159_0_ *= d9);
        int i = (int)Double.doubleToRawLongBits(d2);
        double d3 = t_1786_h[i];
        double d4 = multiplayerClientSuggestionProvider[i];
        double d5 = d2 - M_182_A;
        double d6 = p_181159_0_ * d4 - p_181159_2_ * d5;
        double d7 = (6.0 + d6 * d6) * d6 * 0.16666666666666666;
        double d8 = d3 + d7;
        if (flag2) {
            d8 = 1.5707963267948966 - d8;
        }
        if (flag1) {
            d8 = Math.PI - d8;
        }
        if (flag) {
            d8 = -d8;
        }
        return d8;
    }

    public static float t_148_a(float number) {
        float f = 0.5f * number;
        int i = Float.floatToIntBits(number);
        i = 1597463007 - (i >> 1);
        number = Float.intBitsToFloat(i);
        return number * (1.5f - f * number * number);
    }

    public static double w_1484_f(double number) {
        double d0 = 0.5 * number;
        long i = Double.doubleToRawLongBits(number);
        i = 6910469410427058090L - (i >> 1);
        number = Double.longBitsToDouble(i);
        return number * (1.5 - d0 * number * number);
    }

    public static float s_956_w(float number) {
        int i = Float.floatToIntBits(number);
        i = 1419967116 - i / 3;
        float f = Float.intBitsToFloat(i);
        f = 0.6666667f * f + 1.0f / (3.0f * f * f * number);
        return 0.6666667f * f + 1.0f / (3.0f * f * f * number);
    }

    public static int u_1723_Y(float hue, float saturation, float value) {
        float f5;
        float f4;
        int i = (int)(hue * 6.0f) % 6;
        float f = hue * 6.0f - (float)i;
        float f1 = value * (1.0f - saturation);
        float f2 = value * (1.0f - f * saturation);
        float f3 = value * (1.0f - (1.0f - f) * saturation);
        float f6 = switch (i) {
            case 0 -> {
                f4 = value;
                f5 = f3;
                yield f1;
            }
            case 1 -> {
                f4 = f2;
                f5 = value;
                yield f1;
            }
            case 2 -> {
                f4 = f1;
                f5 = value;
                yield f3;
            }
            case 3 -> {
                f4 = f1;
                f5 = f2;
                yield value;
            }
            case 4 -> {
                f4 = f3;
                f5 = f1;
                yield value;
            }
            case 5 -> {
                f4 = value;
                f5 = f1;
                yield f2;
            }
            default -> throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        };
        int j = u_530_F.n_1700_B((int)(f4 * 255.0f), 0, 255);
        int k = u_530_F.n_1700_B((int)(f5 * 255.0f), 0, 255);
        int l = u_530_F.n_1700_B((int)(f6 * 255.0f), 0, 255);
        return j << 16 | k << 8 | l;
    }

    public static int v_4262_N(int p_188208_0_) {
        p_188208_0_ ^= p_188208_0_ >>> 16;
        p_188208_0_ *= -2048144789;
        p_188208_0_ ^= p_188208_0_ >>> 13;
        return (p_188208_0_ *= -1028477387) ^ p_188208_0_ >>> 16;
    }

    public static int n_1700_B(int min, int max, IntPredicate isTargetBeforeOrAt) {
        int i = max - min;
        while (i > 0) {
            int j = i / 2;
            int k = min + j;
            if (isTargetBeforeOrAt.test(k)) {
                i = j;
                continue;
            }
            min = k + 1;
            i -= j + 1;
        }
        return min;
    }

    public static float v_4262_N(float pct, float start, float end) {
        return start + pct * (end - start);
    }

    public static double G_564_y(double pct, double start, double end) {
        return start + pct * (end - start);
    }

    public static double n_1700_B(double p_219804_0_, double p_219804_2_, double p_219804_4_, double p_219804_6_, double p_219804_8_, double p_219804_10_) {
        return u_530_F.G_564_y(p_219804_2_, u_530_F.G_564_y(p_219804_0_, p_219804_4_, p_219804_6_), u_530_F.G_564_y(p_219804_0_, p_219804_8_, p_219804_10_));
    }

    public static double n_1700_B(double p_219807_0_, double p_219807_2_, double p_219807_4_, double p_219807_6_, double p_219807_8_, double p_219807_10_, double p_219807_12_, double p_219807_14_, double p_219807_16_, double p_219807_18_, double p_219807_20_) {
        return u_530_F.G_564_y(p_219807_4_, u_530_F.n_1700_B(p_219807_0_, p_219807_2_, p_219807_6_, p_219807_8_, p_219807_10_, p_219807_12_), u_530_F.n_1700_B(p_219807_0_, p_219807_2_, p_219807_14_, p_219807_16_, p_219807_18_, p_219807_20_));
    }

    public static double t_148_a(double p_219801_0_) {
        return p_219801_0_ * p_219801_0_ * p_219801_0_ * (p_219801_0_ * (p_219801_0_ * 6.0 - 15.0) + 10.0);
    }

    public static int s_956_w(double x) {
        if (x == 0.0) {
            return 0;
        }
        return x > 0.0 ? 1 : -1;
    }

    public static float w_1484_f(float p_219805_0_, float p_219805_1_, float p_219805_2_) {
        return p_219805_1_ + p_219805_0_ * u_530_F.v_4262_N(p_219805_2_ - p_219805_1_);
    }

    @Deprecated
    public static float t_148_a(float p_226167_0_, float p_226167_1_, float p_226167_2_) {
        float f;
        for (f = p_226167_1_ - p_226167_0_; f < -180.0f; f += 360.0f) {
        }
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return p_226167_0_ + p_226167_2_ * f;
    }

    @Deprecated
    public static float u_2550_I(double p_226168_0_) {
        while (p_226168_0_ >= 180.0) {
            p_226168_0_ -= 360.0;
        }
        while (p_226168_0_ < -180.0) {
            p_226168_0_ += 360.0;
        }
        return (float)p_226168_0_;
    }

    public static float P_1922_E(float p_233021_0_, float p_233021_1_) {
        return (Math.abs(p_233021_0_ % p_233021_1_ - p_233021_1_ * 0.5f) - p_233021_1_ * 0.25f) / (p_233021_1_ * 0.25f);
    }

    public static double M_588_G(double n) {
        return n * n;
    }

    public static float u_2550_I(float value) {
        return value * value;
    }

    static {
        for (int i = 0; i < 257; ++i) {
            double d0 = (double)i / 256.0;
            double d1 = Math.asin(d0);
            u_530_F.multiplayerClientSuggestionProvider[i] = Math.cos(d1);
            u_530_F.t_1786_h[i] = d1;
        }
        for (int j = 0; j < M_588_G.length; ++j) {
            u_530_F.M_588_G[j] = MathUtils.roundToFloat(Math.sin((double)j * Math.PI * 2.0 / 4096.0));
        }
    }
}


