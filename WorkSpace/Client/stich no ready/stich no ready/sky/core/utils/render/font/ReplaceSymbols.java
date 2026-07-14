package sky.core.utils.render.font;

import java.util.HashMap;
import java.util.Map;
import sky.core.utils.render.ColorUtil;

public class ReplaceSymbols {
   private static final Map REPLACEMENTS = new HashMap();
   private static final Map RANK_COLORS = new HashMap();
   private static final int[] RANKS = new int[]{42240, 42244, 42248, 42258, 42262, 42272, 42276, 42280, 42336, 42290, 42294, 42308, 42326, 42312, 42304, 42322, 42249, 42259, 42263, 42273, 42277, 42281, 42291, 42295, 42241, 42245};

   public static String replaceCodePoint(int codePoint) {
      return (String)REPLACEMENTS.get(codePoint);
   }

   public static int getGradientColorForReplacement(int codePoint, int charIndex, int totalChars, float alpha, int currentColor) {
      boolean isRank = false;
      int[] var6 = RANKS;
      int endColor = var6.length;

      int interpolatedColor;
      for(int var8 = 0; var8 < endColor; ++var8) {
         interpolatedColor = var6[var8];
         if (interpolatedColor == codePoint) {
            isRank = true;
            break;
         }
      }

      if (isRank) {
         Integer baseColor = (Integer)RANK_COLORS.get(codePoint);
         endColor = ColorUtil.darken(baseColor, 0.8F);
         float ratio = (float)charIndex / (float)(totalChars - 1);
         interpolatedColor = ColorUtil.interpolate(endColor, baseColor, ratio);
         return ColorUtil.applyOpacity(interpolatedColor, alpha);
      } else {
         return ColorUtil.applyOpacity(currentColor, alpha);
      }
   }

   static {
      REPLACEMENTS.put(9889, "");
      REPLACEMENTS.put(9733, "");
      REPLACEMENTS.put(42240, "PLAYER");
      REPLACEMENTS.put(42244, "HERO");
      REPLACEMENTS.put(42248, "TITAN");
      REPLACEMENTS.put(42258, "AVENGER");
      REPLACEMENTS.put(42262, "OVERLORD");
      REPLACEMENTS.put(42272, "MAGISTER");
      REPLACEMENTS.put(42276, "IMPERATOR");
      REPLACEMENTS.put(42280, "DRAGON");
      REPLACEMENTS.put(42336, "D.HELPER");
      REPLACEMENTS.put(42290, "BULL");
      REPLACEMENTS.put(42294, "TIGER");
      REPLACEMENTS.put(42308, "DRACULA");
      REPLACEMENTS.put(42326, "BUNNY");
      REPLACEMENTS.put(42312, "COBRA");
      REPLACEMENTS.put(42304, "HYDRA");
      REPLACEMENTS.put(42322, "RABBIT");
      REPLACEMENTS.put(42249, "HELPER");
      REPLACEMENTS.put(42259, "ML.MODER");
      REPLACEMENTS.put(42263, "MODER");
      REPLACEMENTS.put(42273, "MODER+");
      REPLACEMENTS.put(42277, "ST.MODER");
      REPLACEMENTS.put(42281, "GL.MODER");
      REPLACEMENTS.put(42291, "ML.ADMIN");
      REPLACEMENTS.put(42295, "ADMIN");
      REPLACEMENTS.put(42241, "MEDIA");
      REPLACEMENTS.put(42245, "YT");
      REPLACEMENTS.put(42305, "GOD");
      REPLACEMENTS.put(7424, "A");
      REPLACEMENTS.put(665, "B");
      REPLACEMENTS.put(7428, "C");
      REPLACEMENTS.put(7429, "D");
      REPLACEMENTS.put(7431, "E");
      REPLACEMENTS.put(42800, "F");
      REPLACEMENTS.put(610, "G");
      REPLACEMENTS.put(668, "H");
      REPLACEMENTS.put(618, "I");
      REPLACEMENTS.put(7434, "J");
      REPLACEMENTS.put(7435, "K");
      REPLACEMENTS.put(671, "L");
      REPLACEMENTS.put(7437, "M");
      REPLACEMENTS.put(628, "N");
      REPLACEMENTS.put(7439, "O");
      REPLACEMENTS.put(7448, "P");
      REPLACEMENTS.put(491, "Q");
      REPLACEMENTS.put(640, "R");
      REPLACEMENTS.put(7451, "T");
      REPLACEMENTS.put(7452, "U");
      REPLACEMENTS.put(42801, "S");
      REPLACEMENTS.put(7456, "V");
      REPLACEMENTS.put(7457, "W");
      REPLACEMENTS.put(7521, "X");
      REPLACEMENTS.put(655, "Y");
      REPLACEMENTS.put(7458, "Z");
      RANK_COLORS.put(42240, ColorUtil.getColor(120, 120, 120));
      RANK_COLORS.put(42244, ColorUtil.getColor(100, 113, 251));
      RANK_COLORS.put(42248, ColorUtil.getColor(214, 200, 42));
      RANK_COLORS.put(42258, ColorUtil.getColor(101, 189, 56));
      RANK_COLORS.put(42262, ColorUtil.getColor(85, 255, 255));
      RANK_COLORS.put(42272, ColorUtil.getColor(202, 130, 60));
      RANK_COLORS.put(42276, ColorUtil.getColor(202, 60, 60));
      RANK_COLORS.put(42280, ColorUtil.getColor(245, 51, 238));
      RANK_COLORS.put(42336, ColorUtil.getColor(214, 200, 42));
      RANK_COLORS.put(42290, ColorUtil.getColor(121, 81, 202));
      RANK_COLORS.put(42294, ColorUtil.getColor(202, 130, 60));
      RANK_COLORS.put(42308, ColorUtil.getColor(202, 60, 60));
      RANK_COLORS.put(42326, ColorUtil.getColor(68, 65, 66));
      RANK_COLORS.put(42312, ColorUtil.getColor(127, 214, 86));
      RANK_COLORS.put(42304, ColorUtil.getColor(92, 120, 7));
      RANK_COLORS.put(42322, ColorUtil.getColor(120, 120, 120));
      RANK_COLORS.put(42249, ColorUtil.getColor(214, 200, 42));
      RANK_COLORS.put(42259, ColorUtil.getColor(100, 113, 251));
      RANK_COLORS.put(42263, ColorUtil.getColor(100, 113, 251));
      RANK_COLORS.put(42273, ColorUtil.getColor(121, 81, 202));
      RANK_COLORS.put(42277, ColorUtil.getColor(100, 113, 251));
      RANK_COLORS.put(42281, ColorUtil.getColor(121, 81, 202));
      RANK_COLORS.put(42291, ColorUtil.getColor(64, 151, 214));
      RANK_COLORS.put(42295, ColorUtil.getColor(202, 60, 60));
      RANK_COLORS.put(42241, ColorUtil.getColor(121, 81, 202));
      RANK_COLORS.put(42245, ColorUtil.getColor(255, 255, 255));
      RANK_COLORS.put(42305, ColorUtil.getColor(214, 200, 42));
   }
}
