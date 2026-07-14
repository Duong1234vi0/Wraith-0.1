package ua.babka.render;

import java.awt.Color;
import net.minecraft.util.math.MathHelper;
import ua.babka.theme.Theme;
import ua.babka.theme.ThemeManager;
import ua.babka.util.math.MathUtil;

public final class ColorProvider {
    private static boolean isRainbowName() {
        String name = ThemeManager.getInstance().getCurrentTheme().name;
        return "АЛЛАХ".equals(name) || "Радужный".equals(name);
    }

    private static int rainbowAccent() {
        if (ColorProvider.isRainbowName()) {
            float hue = (float)(System.currentTimeMillis() % 3600L) / 3600.0f;
            return Color.HSBtoRGB(hue, 0.85f, 1.0f);
        }
        return -1;
    }

    public static int red(int c2) {
        return c2 >> 16 & 0xFF;
    }

    public static int green(int c2) {
        return c2 >> 8 & 0xFF;
    }

    public static int blue(int c2) {
        return c2 & 0xFF;
    }

    public static int alpha(int c2) {
        return c2 >> 24 & 0xFF;
    }

    public static int getThemeColor() {
        int c2;
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        Theme theme = ThemeManager.getInstance().getCurrentTheme();
        int c1 = theme.getColorFirst();
        if (ColorProvider.isTwoColorTheme(c1, c2 = theme.getColorSecond())) {
            float t2 = (float)(Math.sin((double)System.currentTimeMillis() / 1000.0 * Math.PI) * 0.5 + 0.5);
            return ColorProvider.interpolateColor(c1, c2, t2);
        }
        return c1;
    }

    public static int getThemeColorTwo() {
        int c2;
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        Theme theme = ThemeManager.getInstance().getCurrentTheme();
        int c1 = theme.getColorFirst();
        if (ColorProvider.isTwoColorTheme(c1, c2 = theme.getColorSecond())) {
            float t2 = (float)(Math.sin((double)System.currentTimeMillis() / 1000.0 * Math.PI + 1.5707963267948966) * 0.5 + 0.5);
            return ColorProvider.interpolateColor(c1, c2, t2);
        }
        return c2;
    }

    private static boolean isTwoColorTheme(int c1, int c2) {
        int avg1 = (ColorProvider.red(c1) + ColorProvider.green(c1) + ColorProvider.blue(c1)) / 3;
        int avg2 = (ColorProvider.red(c2) + ColorProvider.green(c2) + ColorProvider.blue(c2)) / 3;
        return avg1 > 80 && avg2 > 80;
    }

    public static int getColorMain() {
        return ThemeManager.getInstance().getCurrentTheme().colorMain;
    }

    public static int getColorVisualModules() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorVisualModules;
    }

    public static int getColorText() {
        return ThemeManager.getInstance().getCurrentTheme().colorText;
    }

    public static int getColorInactiveText() {
        return ThemeManager.getInstance().getCurrentTheme().colorInactiveText;
    }

    public static int getColorHeaderBg() {
        return ThemeManager.getInstance().getCurrentTheme().colorHeaderBg;
    }

    public static int getColorHeaderText() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorHeaderText;
    }

    public static int getColorSlider() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorSlider;
    }

    public static int getColorSliderCircle() {
        return ThemeManager.getInstance().getCurrentTheme().colorSliderCircle;
    }

    public static int getColorSliderWindow() {
        return ThemeManager.getInstance().getCurrentTheme().colorSliderWindow;
    }

    public static int getColorIndicator() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorIndicator;
    }

    public static int getColorInactiveIndicator() {
        return ThemeManager.getInstance().getCurrentTheme().colorInactiveIndicator;
    }

    public static int getColorButton() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorButton;
    }

    public static int getColorInactiveButton() {
        return ThemeManager.getInstance().getCurrentTheme().colorInactiveButton;
    }

    public static int getColorSeparator() {
        return ThemeManager.getInstance().getCurrentTheme().colorSeparator;
    }

    public static int getColorField() {
        return ThemeManager.getInstance().getCurrentTheme().colorField;
    }

    public static int getColorInactiveField() {
        return ThemeManager.getInstance().getCurrentTheme().colorInactiveField;
    }

    public static int getColorTooltipText() {
        return ThemeManager.getInstance().getCurrentTheme().colorTooltipText;
    }

    public static int getColorWindowBg() {
        return ThemeManager.getInstance().getCurrentTheme().colorWindowBg;
    }

    public static int getColorIcons() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorIcons;
    }

    public static int getColorClient() {
        int r2 = ColorProvider.rainbowAccent();
        if (r2 != -1) {
            return r2;
        }
        return ThemeManager.getInstance().getCurrentTheme().colorClient;
    }

    public static int[] getOrbitalRect(int c1, int c2, double speed, int alpha) {
        int[] colors = new int[4];
        double time = (double)System.currentTimeMillis() / speed;
        for (int i2 = 0; i2 < 4; ++i2) {
            double phase = (double)i2 * 1.5707963267948966;
            int color = ColorProvider.interpolateColor(c1, c2, (float)(Math.sin(time + phase) * 0.5 + 0.5));
            colors[i2] = ColorProvider.setAlpha(color, alpha);
        }
        return colors;
    }

    public static int gradient(int speed, int index, int ... colors) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle > 180 ? 360 - angle : angle) + 180;
        int colorIndex = (int)((float)angle / 360.0f * (float)colors.length);
        if (colorIndex == colors.length) {
            --colorIndex;
        }
        int color1 = colors[colorIndex];
        int color2 = colors[colorIndex == colors.length - 1 ? 0 : colorIndex + 1];
        return ColorProvider.interpolateColor(color1, color2, (float)angle / 360.0f * (float)colors.length - (float)colorIndex);
    }

    public static int interpolateColor(int from, int to, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        int red1 = ColorProvider.red(to);
        int green1 = ColorProvider.green(to);
        int blue1 = ColorProvider.blue(to);
        int alpha1 = ColorProvider.alpha(to);
        int red2 = ColorProvider.red(from);
        int green2 = ColorProvider.green(from);
        int blue2 = ColorProvider.blue(from);
        int alpha2 = ColorProvider.alpha(from);
        int interpolatedRed = ColorProvider.interpolateInt(red1, red2, amount);
        int interpolatedGreen = ColorProvider.interpolateInt(green1, green2, amount);
        int interpolatedBlue = ColorProvider.interpolateInt(blue1, blue2, amount);
        int interpolatedAlpha = ColorProvider.interpolateInt(alpha1, alpha2, amount);
        return interpolatedAlpha << 24 | interpolatedRed << 16 | interpolatedGreen << 8 | interpolatedBlue;
    }

    private static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
        return ColorProvider.interpolate(oldValue, newValue, (float)interpolationValue);
    }

    public static int interpolate(int start, int end, float value) {
        float[] startColor = ColorProvider.rgba(start);
        float[] endColor = ColorProvider.rgba(end);
        return ColorProvider.rgba((int)MathUtil.interpolate(startColor[0] * 255.0f, endColor[0] * 255.0f, value), (int)MathUtil.interpolate(startColor[1] * 255.0f, endColor[1] * 255.0f, value), (int)MathUtil.interpolate(startColor[2] * 255.0f, endColor[2] * 255.0f, value), (int)MathUtil.interpolate(startColor[3] * 255.0f, endColor[3] * 255.0f, value));
    }

    public static float[] rgba(int color) {
        return new float[]{(float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f, (float)(color >> 24 & 0xFF) / 255.0f};
    }

    public static int rgba(int r2, int g2, int b2, double a2) {
        return (int)a2 << 24 | r2 << 16 | g2 << 8 | b2;
    }

    public static int rgba(int r2, int g2, int b2, float a2) {
        return (int)a2 << 24 | r2 << 16 | g2 << 8 | b2;
    }

    public static int rgb(int r2, int g2, int b2) {
        return 0xFF000000 | r2 << 16 | g2 << 8 | b2;
    }

    public static int setAlpha(int color, int alpha) {
        return MathHelper.clamp((int)alpha, (int)0, (int)255) << 24 | color & 0xFFFFFF;
    }

    public static int setAlpha(int color, double alpha) {
        return MathHelper.clamp((int)((int)alpha), (int)0, (int)255) << 24 | color & 0xFFFFFF;
    }

    public static int brighter(int color, float factor) {
        int r2 = Math.min(255, (int)((float)ColorProvider.red(color) * factor));
        int g2 = Math.min(255, (int)((float)ColorProvider.green(color) * factor));
        int b2 = Math.min(255, (int)((float)ColorProvider.blue(color) * factor));
        int a2 = ColorProvider.alpha(color);
        return ColorProvider.rgba(r2, g2, b2, a2);
    }

    public static int pack(int red, int green, int blue, int alpha) {
        return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF) << 0;
    }

    public static int[] unpack(int color) {
        return new int[]{color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, color >> 24 & 0xFF};
    }

    public static float[] normalize(Color color) {
        return new float[]{(float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f};
    }

    public static float[] normalize(int color) {
        int[] components = ColorProvider.unpack(color);
        return new float[]{(float)components[0] / 255.0f, (float)components[1] / 255.0f, (float)components[2] / 255.0f, (float)components[3] / 255.0f};
    }
}

