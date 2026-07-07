import java.awt.*; import java.awt.font.*; import java.awt.geom.*; import java.io.*;
public class FM2 {
  static void info(String path) throws Exception {
    Font f = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(43f);
    FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
    Rectangle2D capH = f.createGlyphVector(frc, "H").getVisualBounds();
    System.out.printf("%-22s capHeight(H)=%.2f  ratio(H/43)=%.3f%n", new File(path).getName(), capH.getHeight(), capH.getHeight()/43.0);
  }
  public static void main(String[] a) throws Exception {
    info("src-theme/src/assets/font/inter/Inter-Regular.ttf");
    info("src/main/resources/resources/liquidbounce/font/montserrat.ttf");
    info("src/main/resources/resources/liquidbounce/font/pixel.ttf");
  }
}
