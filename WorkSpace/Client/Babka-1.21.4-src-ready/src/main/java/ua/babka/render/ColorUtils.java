package ua.babka.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.util.math.MathHelper;

public class ColorUtils {
    public static final Color green = new Color(36, 218, 118);
    public static final Color yellow = new Color(255, 196, 67);
    public static final Color orange = new Color(255, 134, 0);
    public static final Color red = new Color(239, 72, 54);
    public static final Color Blues = new Color(125, 217, 250);

    public static int red(int c2) {
        return c2 >> 16 & 0xFF;
    }

    public static int green(int c2) {
        return c2 >> 8 & 0xFF;
    }

    public static float redf(int c2) {
        return (float)ColorUtils.red(c2) / 255.0f;
    }

    public static float greenf(int c2) {
        return (float)ColorUtils.green(c2) / 255.0f;
    }

    public static float bluef(int c2) {
        return (float)ColorUtils.blue(c2) / 255.0f;
    }

    public static float alphaf(int c2) {
        return (float)ColorUtils.alpha(c2) / 255.0f;
    }

    public static int getColor(int brightness, int alpha) {
        return ColorUtils.getColor(brightness, brightness, brightness, alpha);
    }

    public static int gradient(int color1, int color2, float amount) {
        amount = MathHelper.clamp((float)amount, (float)0.0f, (float)1.0f);
        int r2 = MathHelper.lerp((float)amount, (int)ColorUtils.red(color1), (int)ColorUtils.red(color2));
        int g2 = MathHelper.lerp((float)amount, (int)ColorUtils.green(color1), (int)ColorUtils.green(color2));
        int b2 = MathHelper.lerp((float)amount, (int)ColorUtils.blue(color1), (int)ColorUtils.blue(color2));
        int a2 = MathHelper.lerp((float)amount, (int)ColorUtils.alpha(color1), (int)ColorUtils.alpha(color2));
        return ColorUtils.rgba(r2, g2, b2, a2);
    }

    public static int toColor(String hexColor) {
        if (hexColor == null || hexColor.length() != 7 || !hexColor.startsWith("#")) {
            return -16777216;
        }
        try {
            int rgb = Integer.parseInt(hexColor.substring(1), 16);
            return 0xFF000000 | rgb;
        }
        catch (NumberFormatException e2) {
            return -16777216;
        }
    }

    public static int applyAlpha(int color, float alphaMul) {
        int a2 = color >> 24 & 0xFF;
        int na = (int)((float)a2 * Math.max(0.0f, Math.min(1.0f, alphaMul)));
        return color & 0xFFFFFF | na << 24;
    }

    public static int r(int color) {
        return color >> 16 & 0xFF;
    }

    public static int g(int color) {
        return color >> 8 & 0xFF;
    }

    public static int b(int color) {
        return color & 0xFF;
    }

    public static int a(int color) {
        return color >> 24 & 0xFF;
    }

    public static int hexToRgb(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Invalid HEX format: " + hex);
        }
        int r2 = Integer.parseInt(hex.substring(0, 2), 16);
        int g2 = Integer.parseInt(hex.substring(2, 4), 16);
        int b2 = Integer.parseInt(hex.substring(4, 6), 16);
        return ColorUtils.rgb(r2, g2, b2);
    }

    public static int getThemeColor() {
        return -16776961;
    }

    public static int getThemeColor(int index) {
        return -16776961;
    }

    public static int getThemeStaticColor() {
        return -16776961;
    }

    public static int rainbow(int speed, int index, float saturation, float brightness, float opacity) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        float hue = (float)angle / 360.0f;
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return ColorUtils.getColor(ColorUtils.red(color), ColorUtils.green(color), ColorUtils.blue(color), Math.max(0, Math.min(255, (int)(opacity * 255.0f))));
    }

    public static int interpolate(int color1, int color2, double amount) {
        amount = MathHelper.clamp((double)amount, (double)0.0, (double)1.0);
        int r2 = (int)MathHelper.lerp((double)amount, (double)ColorUtils.red(color1), (double)ColorUtils.red(color2));
        int g2 = (int)MathHelper.lerp((double)amount, (double)ColorUtils.green(color1), (double)ColorUtils.green(color2));
        int b2 = (int)MathHelper.lerp((double)amount, (double)ColorUtils.blue(color1), (double)ColorUtils.blue(color2));
        int a2 = (int)MathHelper.lerp((double)amount, (double)ColorUtils.alpha(color1), (double)ColorUtils.alpha(color2));
        return ColorUtils.rgba(r2, g2, b2, a2);
    }

    public static int[] genGradientForText(int color1, int color2, int length) {
        int[] gradient = new int[length];
        for (int i2 = 0; i2 < length; ++i2) {
            double pc = (double)i2 / (double)(length - 1);
            gradient[i2] = ColorUtils.interpolate(color1, color2, pc);
        }
        return gradient;
    }

    public static int blue(int c2) {
        return c2 & 0xFF;
    }

    public static int overCol(int c1, int c2, float pc01) {
        return ColorUtils.getColor((float)ColorUtils.red(c1) * (1.0f - pc01) + (float)ColorUtils.red(c2) * pc01, (float)ColorUtils.green(c1) * (1.0f - pc01) + (float)ColorUtils.green(c2) * pc01, (float)ColorUtils.blue(c1) * (1.0f - pc01) + (float)ColorUtils.blue(c2) * pc01, (float)ColorUtils.alpha(c1) * (1.0f - pc01) + (float)ColorUtils.alpha(c2) * pc01);
    }

    public static int darken(int color, float factor) {
        float[] rgb = ColorUtils.getColorT(color);
        float[] hsb = Color.RGBtoHSB((int)(rgb[0] * 255.0f), (int)(rgb[1] * 255.0f), (int)(rgb[2] * 255.0f), null);
        hsb[2] = hsb[2] * factor;
        hsb[2] = Math.max(0.0f, Math.min(1.0f, hsb[2]));
        int darkenedRGB = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        return ColorUtils.applyOpacity(darkenedRGB, (int)(rgb[3] * 255.0f));
    }

    public static int multDark(int c2, float brpc) {
        return ColorUtils.getColor((float)ColorUtils.red(c2) * brpc, (float)ColorUtils.green(c2) * brpc, (float)ColorUtils.blue(c2) * brpc, (float)ColorUtils.alpha(c2));
    }

    public static int overCol(int c1, int c2) {
        return ColorUtils.overCol(c1, c2, 0.5f);
    }

    public static int alpha(int c2) {
        return c2 >> 24 & 0xFF;
    }

    public static int multAlpha(int c2, float apc) {
        return ColorUtils.getColor((float)ColorUtils.red(c2), (float)ColorUtils.green(c2), (float)ColorUtils.blue(c2), (float)ColorUtils.alpha(c2) * apc);
    }

    public static int replAlpha(int color, int alpha) {
        alpha = Math.max(0, Math.min(255, alpha));
        return alpha << 24 | color & 0xFFFFFF;
    }

    public static Color random() {
        return new Color(Color.HSBtoRGB((float)Math.random(), (float)(0.75 + Math.random() / 4.0), (float)(0.75 + Math.random() / 4.0)));
    }

    public static int getColor(float r2, float g2, float b2, float a2) {
        return new Color((int)r2, (int)g2, (int)b2, (int)a2).getRGB();
    }

    public static float[] getRGBAf(int c2) {
        return new float[]{ColorUtils.redf(c2), ColorUtils.greenf(c2), ColorUtils.bluef(c2), ColorUtils.alphaf(c2)};
    }

    public static float[] getRGBAf1(int c2) {
        return new float[]{(float)ColorUtils.red(c2) / 255.0f, (float)ColorUtils.green(c2) / 255.0f, (float)ColorUtils.blue(c2) / 255.0f, (float)ColorUtils.alpha(c2) / 255.0f};
    }

    public static Color interpolateTwoColors(int speed, int index, Color start, Color end, boolean trueColor) {
        int angle = 0;
        angle = speed == 0 ? index % 360 : (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle >= 180 ? 360 - angle : angle) * 2;
        return trueColor ? ColorUtils.interpolateColorHue(start, end, (float)angle / 360.0f) : ColorUtils.interpolateColorC(start, end, (float)angle / 360.0f);
    }

    public static Color interpolateTwoColors(int speed, int index, Color start, Color end) {
        return ColorUtils.interpolateTwoColors(speed, index, start, end, false);
    }

    public static Color astolfo(float yDist, float yTotal, float saturation, float speedt) {
        float hue;
        float speed = 1800.0f;
        for (hue = (float)(System.currentTimeMillis() % (long)((int)speed)) + (yTotal - yDist) * speedt; hue > speed; hue -= speed) {
        }
        if ((hue /= speed) > 1.0f) {
            hue = 1.0f - (hue - 1.0f);
        }
        return Color.getHSBColor(hue += 1.0f, saturation, 1.0f);
    }

    public static void setColor(Color color, float alpha) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        RenderSystem.setShaderColor((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static int rgb(int r2, int g2, int b2) {
        return 0xFF000000 | r2 << 16 | g2 << 8 | b2;
    }

    public static int getColor(int red, int green, int blue) {
        return ColorUtils.getColor(red, green, blue, 255);
    }

    public static int getColor(int red, int green, int blue, int alpha) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public static int getColor(Color color) {
        return ColorUtils.getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static int getColor(int brightness) {
        return ColorUtils.getColor(brightness, brightness, brightness, 255);
    }

    public static Color getColorObject(int color) {
        return new Color(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF);
    }

    public static float[] getColorT(int color) {
        return new float[]{(float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f, (float)(color >> 24 & 0xFF) / 255.0f};
    }

    public static int rgba(int r2, int g2, int b2, int a2) {
        return a2 << 24 | r2 << 16 | g2 << 8 | b2;
    }

    public static int applyOpacity(int color, int alpha) {
        return ColorUtils.getColor(ColorUtils.red(color), ColorUtils.green(color), ColorUtils.blue(color), alpha);
    }

    public static int applyOpacity(int color, float opacity) {
        return ColorUtils.getColor(ColorUtils.red(color), ColorUtils.green(color), ColorUtils.blue(color), (int)((float)ColorUtils.alpha(color) * opacity));
    }

    public static int setAlphaColor(int color, int alpha) {
        return color & 0xFFFFFF | alpha << 24;
    }

    public static Color interpolateColorC(Color color1, Color color2, double amount) {
        amount = Math.max(0.0, Math.min(1.0, amount));
        return new Color((int)MathHelper.lerp((double)amount, (double)color1.getRed(), (double)color2.getRed()), (int)MathHelper.lerp((double)amount, (double)color1.getGreen(), (double)color2.getGreen()), (int)MathHelper.lerp((double)amount, (double)color1.getBlue(), (double)color2.getBlue()), (int)MathHelper.lerp((double)amount, (double)color1.getAlpha(), (double)color2.getAlpha()));
    }

    public static Color interpolateColorHue(Color color1, Color color2, double amount) {
        amount = Math.max(0.0, Math.min(1.0, amount));
        float[] hsb1 = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] hsb2 = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        float hue = (float)((double)hsb1[0] + (double)(hsb2[0] - hsb1[0]) * amount);
        float sat = (float)((double)hsb1[1] + (double)(hsb2[1] - hsb1[1]) * amount);
        float bri = (float)((double)hsb1[2] + (double)(hsb2[2] - hsb1[2]) * amount);
        return new Color(Color.HSBtoRGB(hue, sat, bri));
    }
}

