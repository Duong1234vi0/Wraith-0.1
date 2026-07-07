import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.io.*;
public class FM {
  static void info(String path) throws Exception {
    Font f = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(43f);
    FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
    LineMetrics lm = f.getLineMetrics("Hg", frc);
    Rectangle2D capH = f.createGlyphVector(frc, "H").getVisualBounds();
    Rectangle2D xH = f.createGlyphVector(frc, "x").getVisualBounds();
    double wAdv = f.createGlyphVector(frc, "Inventory").getLogicalBounds().getWidth();
    System.out.printf("%-14s name=%-20s lineH=%.1f asc=%.1f capH(H)=%.1f xH(x)=%.1f adv(Inventory)=%.1f%n",
      new File(path).getName(), f.getName(), lm.getHeight(), lm.getAscent(), capH.getHeight(), xH.getHeight(), wAdv);
  }
  public static void main(String[] a) throws Exception {
    info("src/main/resources/resources/liquidbounce/font/montserrat.ttf");
    info("src/main/resources/resources/liquidbounce/font/pixel.ttf");
  }
}
