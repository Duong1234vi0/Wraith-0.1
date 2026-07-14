package sky.core.utils.render.font;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Locale;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;
import sky.core.utils.Wrapper;
import sky.core.utils.render.ColorUtil;
import sky.core.utils.render.ScissorUtil;
import sky.core.utils.render.shader.ShaderUtil;

public final class StyledFont extends AbstractFont implements Wrapper {
   private final float spacing;
   private static final int[] ATLAS_SIZES = new int[]{256, 512, 1024, 2048};
   private static final int[] CODES = new int[]{31, 256, 1024, 1106, 9728, 9984, 9632, 9728};
   private static final char[] CHARS;

   public float drawString(MatrixStack matrixStack, String text, double x, double y, int color) {
      return renderString(matrixStack, this, text, x, y, color);
   }

   public float drawText(MatrixStack matrixStack, ITextComponent component, double x, double y, int color) {
      return this.renderText(matrixStack, component, x, y, color);
   }

   public void drawOutlineString(MatrixStack stack, String text, double x, double y, int color, boolean left, boolean right, boolean up, boolean down, int outline_color) {
      if (left) {
         renderString(stack, this, text, x - 0.5, y, outline_color);
      }

      if (right) {
         renderString(stack, this, text, x + 0.5, y, outline_color);
      }

      if (up) {
         renderString(stack, this, text, x, y - 0.5, outline_color);
      }

      if (down) {
         renderString(stack, this, text, x, y + 0.5, outline_color);
      }

      this.drawString(stack, text, x, y, color);
   }

   public void drawSubString(MatrixStack matrixStack, String text, float x, float y, int color, float maxWidth) {
      RenderSystem.enableBlend();
      RenderSystem.blendFunc(770, 771);
      ShaderUtil.substring.attach();
      ShaderUtil.substring.setUniformf("inColor", (float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F);
      ShaderUtil.substring.setUniform("width", maxWidth);
      ShaderUtil.substring.setUniform("maxWidth", (x + maxWidth) * 2.0F);
      this.drawString(matrixStack, text, (double)x, (double)y, color);
      ShaderUtil.substring.detach();
      RenderSystem.disableBlend();
   }

   public void drawScrolledString(MatrixStack matrixStack, String text, float x, float y, float maxWidth, int color, boolean isHovered, TextScrollState scrollState) {
      float textWidth = this.getWidth(text);
      float fontHeight = this.getHeight();
      ScissorUtil.start((double)x, (double)(y - 1.0F), (double)maxWidth, (double)(fontHeight + 3.0F));
      long currentTime = System.currentTimeMillis();
      float speed = 40.0F;
      float loopWidth = textWidth + 10.0F;
      float scrollDuration = loopWidth / speed * 1000.0F;
      float pauseDuration = 600.0F;
      float totalCycle = scrollDuration + pauseDuration;
      float scrollOffset = 0.0F;
      boolean shouldScroll = textWidth > maxWidth;
      if (shouldScroll) {
         if (isHovered && !scrollState.isScrolling) {
            scrollState.scrollStartTime = currentTime;
            scrollState.isScrolling = true;
         }

         if (scrollState.isScrolling) {
            long elapsed = currentTime - scrollState.scrollStartTime;
            if ((float)elapsed < totalCycle) {
               if ((float)elapsed < scrollDuration) {
                  scrollOffset = (float)elapsed / 1000.0F * speed;
               } else {
                  scrollOffset = 0.0F;
               }
            } else {
               scrollState.isScrolling = false;
               scrollOffset = 0.0F;
            }
         }
      }

      this.drawString(matrixStack, text, (double)(x - scrollOffset), (double)y, color);
      if (shouldScroll && scrollOffset > 0.0F) {
         this.drawString(matrixStack, text, (double)(x - scrollOffset + textWidth + 10.0F), (double)y, color);
      }

      ScissorUtil.end();
   }

   private static float renderString(MatrixStack matrices, StyledFont font, String text, double x, double y, int color) {
      y -= 3.0;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float startPos = (float)Math.round(x * 2.0);
      float posX = startPos;
      float posY = (float)Math.round(y * 2.0);
      float[] rgb = ColorUtil.getColor(color);
      float red = rgb[0];
      float green = rgb[1];
      float blue = rgb[2];
      float alpha = rgb[3];
      matrices.push();
      matrices.scale(0.5F, 0.5F, 1.0F);
      Matrix4f matrix = matrices.getLast().getMatrix();
      font.beginFontBatch(matrix);
      int length = text.length();

      for(int i = 0; i < length; ++i) {
         char c0 = text.charAt(i);
         posX += font.renderGlyph(matrix, c0, posX, posY, red, green, blue, alpha);
      }

      matrices.pop();
      font.endFontBatch();
      return (posX - startPos) / 2.0F;
   }

   public float renderText(MatrixStack matrixStack, ITextComponent component, double x, double y, int defaultColor) {
      y -= 3.0;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float startPos = (float)x * 2.0F;
      float[] posX = new float[]{startPos};
      float posY = (float)y * 2.0F;
      matrixStack.push();
      matrixStack.scale(0.5F, 0.5F, 1.0F);
      Matrix4f matrix = matrixStack.getLast().getMatrix();
      this.beginFontBatch(matrix);
      IReorderingProcessor processor = component.func_241878_f();
      processor.accept((index, style, codePoint) -> {
         Color styleColor = style.getColor();
         int color = defaultColor;
         if (styleColor != null) {
            int rgb = styleColor.getColor();
            int alpha = defaultColor >> 24 & 255;
            color = alpha << 24 | rgb & 16777215;
         }

         float[] rgba = ColorUtil.getColor(color);
         String replacement = ReplaceSymbols.replaceCodePoint(codePoint);
         int replacementColor;
         if (replacement != null) {
            if (!replacement.isEmpty()) {
               for(int j = 0; j < replacement.length(); ++j) {
                  char chx = replacement.charAt(j);
                  replacementColor = ReplaceSymbols.getGradientColorForReplacement(codePoint, j, replacement.length(), rgba[3], color);
                  float[] replacementRgba = ColorUtil.getColor(replacementColor);
                  posX[0] += this.renderGlyph(matrix, chx, posX[0], posY, replacementRgba[0], replacementRgba[1], replacementRgba[2], replacementRgba[3]);
               }
            }

            return true;
         } else {
            char[] chars = Character.toChars(codePoint);
            char[] var13 = chars;
            replacementColor = chars.length;

            for(int var15 = 0; var15 < replacementColor; ++var15) {
               char ch = var13[var15];
               posX[0] += this.renderGlyph(matrix, ch, posX[0], posY, rgba[0], rgba[1], rgba[2], rgba[3]);
            }

            return true;
         }
      });
      matrixStack.pop();
      this.endFontBatch();
      return (posX[0] - startPos) / 2.0F;
   }

   public StyledFont(String fileName, int size, float spacing, boolean antialiasing) {
      Font font = AbstractFont.getFont(fileName, 0, size);
      this.fontName = font.getFontName(Locale.ENGLISH);
      this.spacing = spacing;
      this.antialiasing = antialiasing;
      int atlasSize = this.chooseAtlasSize(font);
      this.imgWidth = atlasSize;
      this.imgHeight = atlasSize;
      BufferedImage image = new BufferedImage(atlasSize, atlasSize, 2);
      Graphics2D graphics = this.setupGraphics(image, font);
      FontMetrics fontMetrics = graphics.getFontMetrics();
      this.fontHeight = (float)fontMetrics.getHeight() / 2.0F;
      int x = 0;
      int y = 0;
      int rowHeight = 0;
      int padding = true;
      char[] var14 = CHARS;
      int var15 = var14.length;

      for(int var16 = 0; var16 < var15; ++var16) {
         char character = var14[var16];
         Rectangle2D sizeRect = fontMetrics.getStringBounds(String.valueOf(character), graphics);
         int width = sizeRect.getBounds().width;
         int height = sizeRect.getBounds().height;
         int cellW = width + 4;
         int cellH = height + 4;
         if (x + cellW >= atlasSize) {
            x = 0;
            y += rowHeight;
            rowHeight = 0;
         }

         if (y + cellH < atlasSize) {
            AbstractFont.Glyph glyph = new AbstractFont.Glyph();
            glyph.width = width;
            glyph.height = height;
            glyph.x = x + 2;
            glyph.y = y + 2;
            graphics.drawString(String.valueOf(character), x + 2, y + 2 + fontMetrics.getAscent());
            this.glyphs.put(character, glyph);
            x += cellW;
            rowHeight = Math.max(rowHeight, cellH);
         }
      }

      graphics.dispose();
      if (RenderSystem.isOnRenderThread()) {
         this.setTexture(image);
      } else {
         RenderSystem.recordRenderCall(() -> {
            this.setTexture(image);
         });
      }

   }

   private int chooseAtlasSize(Font font) {
      int[] var2 = ATLAS_SIZES;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int atlasSize = var2[var4];
         if (this.canPackAllGlyphs(font, atlasSize)) {
            return atlasSize;
         }
      }

      return ATLAS_SIZES[ATLAS_SIZES.length - 1];
   }

   private boolean canPackAllGlyphs(Font font, int atlasSize) {
      BufferedImage image = new BufferedImage(1, 1, 2);
      Graphics2D graphics = image.createGraphics();
      graphics.setFont(font);
      if (this.antialiasing) {
         graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      }

      FontMetrics fontMetrics = graphics.getFontMetrics();
      int x = 0;
      int y = 0;
      int rowHeight = 0;
      int padding = true;
      char[] var10 = CHARS;
      int var11 = var10.length;

      for(int var12 = 0; var12 < var11; ++var12) {
         char character = var10[var12];
         Rectangle2D sizeRect = fontMetrics.getStringBounds(String.valueOf(character), graphics);
         int width = sizeRect.getBounds().width;
         int height = sizeRect.getBounds().height;
         int cellW = width + 4;
         int cellH = height + 4;
         if (x + cellW >= atlasSize) {
            x = 0;
            y += rowHeight;
            rowHeight = 0;
         }

         if (y + cellH >= atlasSize) {
            graphics.dispose();
            return false;
         }

         x += cellW;
         rowHeight = Math.max(rowHeight, cellH);
      }

      graphics.dispose();
      return true;
   }

   public float getSpacing() {
      return this.spacing;
   }

   public float getWidth(String text) {
      if (text != null && !text.isEmpty()) {
         float width = 0.0F;
         int i = 0;

         while(true) {
            String replacement;
            label33:
            do {
               while(i < text.length()) {
                  int codePoint = text.codePointAt(i);
                  i += Character.charCount(codePoint);
                  replacement = ReplaceSymbols.replaceCodePoint(codePoint);
                  if (replacement != null) {
                     continue label33;
                  }

                  char[] chars = Character.toChars(codePoint);
                  char[] var7 = chars;
                  int var8 = chars.length;

                  for(int var9 = 0; var9 < var8; ++var9) {
                     char ch = var7[var9];
                     width += this.getWidth(ch) + this.getSpacing();
                  }
               }

               return (float)Math.round(width - this.getSpacing()) / 2.0F;
            } while(replacement.isEmpty());

            for(int j = 0; j < replacement.length(); ++j) {
               char ch = replacement.charAt(j);
               width += this.getWidth(ch) + this.getSpacing();
            }
         }
      } else {
         return 0.0F;
      }
   }

   public float getHeight() {
      return (float)Math.round(this.getFontHeight()) / 2.0F;
   }

   static {
      int len = CODES[1] - CODES[0] + (CODES[3] - CODES[2]) + (CODES[5] - CODES[4]) + (CODES[7] - CODES[6]);
      CHARS = new char[len];
      int idx = 0;

      int i;
      for(i = CODES[0]; i < CODES[1]; ++i) {
         CHARS[idx++] = (char)i;
      }

      for(i = CODES[2]; i < CODES[3]; ++i) {
         CHARS[idx++] = (char)i;
      }

      for(i = CODES[4]; i < CODES[5]; ++i) {
         CHARS[idx++] = (char)i;
      }

      for(i = CODES[6]; i < CODES[7]; ++i) {
         CHARS[idx++] = (char)i;
      }

   }

   public static class TextScrollState {
      public boolean isScrolling = false;
      public long scrollStartTime = 0L;
   }
}
