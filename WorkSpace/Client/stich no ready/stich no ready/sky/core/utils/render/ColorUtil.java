package sky.core.utils.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.util.math.MathHelper;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.math.MathUtil;

public class ColorUtil {
   public static void setManalColor(int color) {
      setAlphaManalColor(color, (float)(color >> 24 & 255) / 255.0F);
   }

   public static void setAlphaManalColor(int color, float alpha) {
      float red = (float)(color >> 16 & 255) / 255.0F;
      float green = (float)(color >> 8 & 255) / 255.0F;
      float blue = (float)(color & 255) / 255.0F;
      RenderSystem.color4f(red, green, blue, alpha);
   }

   public static int applyOpacity(int color, float alpha) {
      int alphaValue = Math.min(255, Math.max(0, (int)(alpha * 255.0F)));
      return getColor(red(color), green(color), blue(color), alphaValue);
   }

   public static int applyOpacity(int color, int alpha) {
      return getColor(red(color), green(color), blue(color), alpha);
   }

   public static float[] getColor(int color) {
      return new float[]{(float)red(color) / 255.0F, (float)green(color) / 255.0F, (float)blue(color) / 255.0F, alphaf(color)};
   }

   public static Color random() {
      return new Color(Color.HSBtoRGB((float)Math.random(), (float)(0.75 + Math.random() / 4.0), (float)(0.75 + Math.random() / 4.0)));
   }

   public static void glHexColor(int hex, int alpha) {
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      RenderSystem.color4f(red, green, blue, (float)alpha / 255.0F);
   }

   public static int getColorWithDarkness(int color, float darkness) {
      float[] rgb = getRGBAf(color);
      return rgba((int)(rgb[0] * 255.0F / darkness), (int)(rgb[1] * 255.0F / darkness), (int)(rgb[2] * 255.0F / darkness), (int)(rgb[3] * 255.0F));
   }

   public static int rgb(int r, int g, int b) {
      return -16777216 | r << 16 | g << 8 | b;
   }

   public static int gradient2(int speed, int index, int... colors) {
      if (colors != null && colors.length != 0) {
         if (colors.length == 1) {
            return colors[0];
         } else {
            if (speed <= 0) {
               speed = 1;
            }

            int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
            angle = (angle > 180 ? 360 - angle : angle) + 180;
            float angleNormalized = (float)angle / 360.0F * (float)colors.length;
            int colorIndex = (int)angleNormalized;
            if (colorIndex >= colors.length) {
               colorIndex = colors.length - 1;
            }

            if (colorIndex < 0) {
               colorIndex = 0;
            }

            int color1 = colors[colorIndex];
            int color2 = colors[colorIndex == colors.length - 1 ? 0 : colorIndex + 1];
            float progress = angleNormalized - (float)colorIndex;
            progress = MathHelper.clamp(progress, 0.0F, 1.0F);
            return interpolate(color1, color2, progress);
         }
      } else {
         return -1;
      }
   }

   public static int getColorWithAlpha(int color, float alpha) {
      float[] rgb = getRGBAf(color);
      return rgba((int)(rgb[0] * 255.0F), (int)(rgb[1] * 255.0F), (int)(rgb[2] * 255.0F), (int)alpha);
   }

   public int getColorWithAlpha(int color, double alpha) {
      float[] rgb = getRGBAf(color);
      return rgba((int)(rgb[0] * 255.0F), (int)(rgb[1] * 255.0F), (int)(rgb[2] * 255.0F), (int)alpha);
   }

   public static float[] getRGBAf(int color) {
      return new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F};
   }

   public static int rgba(int r, int g, int b, int a) {
      return a << 24 | r << 16 | g << 8 | b;
   }

   public static float[] rgba(int color) {
      return new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F};
   }

   public static int gradientModule(int index) {
      return gradient2(10, index, ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), darken(ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL), 0.5F));
   }

   public static int gradientInterface(int index) {
      return gradient2(10, index, ThemeEditor.getColor(ThemeSettings.LOGO), darken(ThemeEditor.getColor(ThemeSettings.LOGO), 0.3F));
   }

   public static int interpolate(int start, int end, float value) {
      float[] startColor = getColor(start);
      float[] endColor = getColor(end);
      return getColor((int)MathUtil.interpolate(startColor[0] * 255.0F, endColor[0] * 255.0F, value), (int)MathUtil.interpolate(startColor[1] * 255.0F, endColor[1] * 255.0F, value), (int)MathUtil.interpolate(startColor[2] * 255.0F, endColor[2] * 255.0F, value), (int)MathUtil.interpolate(startColor[3] * 255.0F, endColor[3] * 255.0F, value));
   }

   public static int darken(int color, float factor) {
      float[] rgb = getColor(color);
      float[] hsb = Color.RGBtoHSB((int)(rgb[0] * 255.0F), (int)(rgb[1] * 255.0F), (int)(rgb[2] * 255.0F), (float[])null);
      hsb[2] *= factor;
      hsb[2] = Math.max(0.0F, Math.min(1.0F, hsb[2]));
      int darkenedRGB = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
      return applyOpacity(darkenedRGB, (int)(rgb[3] * 255.0F));
   }

   public static int interpolateSuccessColor(int percentage) {
      int red = Color.GREEN.getRGB();
      int green = Color.RED.getRGB();
      float factor = MathHelper.clamp((float)percentage / 100.0F, 0.0F, 1.0F);
      return interpolate(red, green, factor);
   }

   public static int setAlpha(int color, int alpha) {
      return MathHelper.clamp(alpha, 0, 255) << 24 | color & 16777215;
   }

   public static int hex(String hex) {
      String s = hex.trim();
      if (s.startsWith("#")) {
         s = s.substring(1);
      }

      if (s.startsWith("0x") || s.startsWith("0X")) {
         s = s.substring(2);
      }

      int r;
      int g;
      int b;
      int a;
      if (s.length() == 3 || s.length() == 4) {
         r = s.charAt(0);
         g = s.charAt(1);
         b = s.charAt(2);
         a = s.length() == 4 ? s.charAt(3) : 70;
         s = "" + r + r + g + g + b + b + a + a;
      }

      if (s.length() == 6) {
         r = Integer.parseInt(s.substring(0, 2), 16);
         g = Integer.parseInt(s.substring(2, 4), 16);
         b = Integer.parseInt(s.substring(4, 6), 16);
         return getColor(r, g, b, 255);
      } else if (s.length() == 8) {
         r = Integer.parseInt(s.substring(0, 2), 16);
         g = Integer.parseInt(s.substring(2, 4), 16);
         b = Integer.parseInt(s.substring(4, 6), 16);
         a = Integer.parseInt(s.substring(6, 8), 16);
         return getColor(r, g, b, a);
      } else {
         throw new IllegalArgumentException("Unsupported hex format: " + hex);
      }
   }

   public static int multAlpha(int color, float percent01) {
      return getColor(red(color), green(color), blue(color), alphaf(color) * percent01);
   }

   public static int getColor(int red, int green, int blue, float alpha) {
      return getColor(red, green, blue, (int)(alpha * 255.0F));
   }

   public static int getColor(int r, int g, int b) {
      return getColor(r, g, b, 255);
   }

   public static int getColor(int red, int green, int blue, int alpha) {
      return computeColor(red, green, blue, alpha);
   }

   private static int computeColor(int red, int green, int blue, int alpha) {
      return MathHelper.clamp(alpha, 0, 255) << 24 | MathHelper.clamp(red, 0, 255) << 16 | MathHelper.clamp(green, 0, 255) << 8 | MathHelper.clamp(blue, 0, 255);
   }

   public static int red(int c) {
      return c >> 16 & 255;
   }

   public static int green(int c) {
      return c >> 8 & 255;
   }

   public static int blue(int c) {
      return c & 255;
   }

   public static float alphaf(int c) {
      return (float)alpha(c) / 255.0F;
   }

   public static int alpha(int c) {
      return c >> 24 & 255;
   }

   public static int fade(int speed, int index, int first, int second) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      angle = angle >= 180 ? 360 - angle : angle;
      return overCol(first, second, (float)angle / 180.0F);
   }

   public static int overCol(int color1, int color2, float percent01) {
      float percent = MathHelper.clamp(percent01, 0.0F, 1.0F);
      return getColor(calc(red(color1), red(color2), (double)percent), calc(green(color1), green(color2), (double)percent), calc(blue(color1), blue(color2), (double)percent), calc(alpha(color1), alpha(color2), (double)percent));
   }

   public static int calc(int input, int target, double step) {
      return (int)((double)input + step * (double)(target - input));
   }

   public static class IntColor {
      public static float[] rgb(int color) {
         return new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F};
      }

      public static int rgba(int r, int g, int b, int a) {
         return a << 24 | r << 16 | g << 8 | b;
      }

      public static int getRed(int hex) {
         return hex >> 16 & 255;
      }

      public static int getGreen(int hex) {
         return hex >> 8 & 255;
      }

      public static int getBlue(int hex) {
         return hex & 255;
      }

      public static int getAlpha(int hex) {
         return hex >> 24 & 255;
      }
   }
}
