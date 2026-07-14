package sky.core.utils.render.font;

import com.mojang.blaze3d.platform.GlStateManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import sky.core.utils.Wrapper;

public abstract class AbstractFont implements Wrapper {
   protected final Map glyphs = new HashMap();
   protected int texId;
   protected int imgWidth;
   protected int imgHeight;
   protected float fontHeight;
   protected String fontName;
   protected boolean antialiasing;
   private boolean fontBatchActive = false;
   private Matrix4f batchMatrix = null;

   public abstract float getSpacing();

   protected final void setTexture(BufferedImage img) {
      int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), (int[])null, 0, img.getWidth());
      ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);

      try {
         int[] var4 = pixels;
         int var5 = pixels.length;
         int var6 = 0;

         while(true) {
            if (var6 >= var5) {
               buffer.flip();
               break;
            }

            int pixel = var4[var6];
            buffer.put((byte)(pixel >> 16 & 255));
            buffer.put((byte)(pixel >> 8 & 255));
            buffer.put((byte)(pixel & 255));
            buffer.put((byte)(pixel >> 24 & 255));
            ++var6;
         }
      } catch (ReadOnlyBufferException | BufferOverflowException var8) {
         this.texId = -1;
         return;
      }

      int textureID = GlStateManager.genTexture();
      GlStateManager.bindTexture(textureID);
      GlStateManager.texParameter(3553, 10241, 9728);
      GlStateManager.texParameter(3553, 10240, 9728);
      GL30.glTexImage2D(3553, 0, 32856, img.getWidth(), img.getHeight(), 0, 6408, 5121, buffer);
      GlStateManager.bindTexture(0);
      this.texId = textureID;
   }

   public final void bindTex() {
      GlStateManager.bindTexture(this.texId);
   }

   public final void unbindTex() {
      GlStateManager.bindTexture(0);
   }

   public static Font getFont(String fileName, int style, int size) {
      String path = "/assets/minecraft/SkyCore/fonts/".concat(fileName);
      Font font = null;

      try {
         font = Font.createFont(0, (InputStream)Objects.requireNonNull(Fonts.class.getResourceAsStream(path))).deriveFont(style, (float)size);
      } catch (IOException | FontFormatException var6) {
         var6.printStackTrace();
      }

      return font;
   }

   public final Graphics2D setupGraphics(BufferedImage img, Font font) {
      Graphics2D graphics = img.createGraphics();
      graphics.setFont(font);
      graphics.setColor(new Color(255, 255, 255, 0));
      graphics.fillRect(0, 0, this.imgWidth, this.imgHeight);
      graphics.setColor(Color.WHITE);
      if (this.antialiasing) {
         graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      }

      return graphics;
   }

   public float renderGlyph(Matrix4f matrix, char c, float x, float y, float red, float green, float blue, float alpha) {
      Glyph glyph = (Glyph)this.glyphs.get(c);
      if (glyph == null) {
         return 0.0F;
      } else {
         float pageX = (float)glyph.x / (float)this.imgWidth;
         float pageY = (float)glyph.y / (float)this.imgHeight;
         float pageWidth = (float)glyph.width / (float)this.imgWidth;
         float pageHeight = (float)glyph.height / (float)this.imgHeight;
         float width = (float)glyph.width;
         float height = (float)glyph.height;
         Matrix4f usedMatrix = this.fontBatchActive && this.batchMatrix != null ? this.batchMatrix : matrix;
         if (!this.fontBatchActive) {
            BUFFER.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
         }

         BUFFER.pos(usedMatrix, x, y + height, 0.0F).color(red, green, blue, alpha).tex(pageX, pageY + pageHeight).endVertex();
         BUFFER.pos(usedMatrix, x + width, y + height, 0.0F).color(red, green, blue, alpha).tex(pageX + pageWidth, pageY + pageHeight).endVertex();
         BUFFER.pos(usedMatrix, x + width, y, 0.0F).color(red, green, blue, alpha).tex(pageX + pageWidth, pageY).endVertex();
         BUFFER.pos(usedMatrix, x, y, 0.0F).color(red, green, blue, alpha).tex(pageX, pageY).endVertex();
         if (!this.fontBatchActive) {
            TESSELLATOR.draw();
         }

         return width + this.getSpacing();
      }
   }

   public void beginFontBatch(Matrix4f matrix) {
      if (this.fontBatchActive) {
         this.endFontBatch();
      }

      this.fontBatchActive = true;
      this.batchMatrix = matrix;
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      this.bindTex();
      BUFFER.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
   }

   public void endFontBatch() {
      if (this.fontBatchActive) {
         TESSELLATOR.draw();
         this.unbindTex();
         GlStateManager.disableBlend();
         this.fontBatchActive = false;
         this.batchMatrix = null;
      }
   }

   public float getWidth(char ch) {
      Glyph glyph = (Glyph)this.glyphs.get(ch);
      return glyph != null ? (float)glyph.width : 0.0F;
   }

   public Map getGlyphs() {
      return this.glyphs;
   }

   public int getTexId() {
      return this.texId;
   }

   public int getImgWidth() {
      return this.imgWidth;
   }

   public int getImgHeight() {
      return this.imgHeight;
   }

   public float getFontHeight() {
      return this.fontHeight;
   }

   public String getFontName() {
      return this.fontName;
   }

   public boolean isAntialiasing() {
      return this.antialiasing;
   }

   public boolean isFontBatchActive() {
      return this.fontBatchActive;
   }

   public Matrix4f getBatchMatrix() {
      return this.batchMatrix;
   }

   public static class Glyph {
      public int x;
      public int y;
      public int width;
      public int height;
   }
}
