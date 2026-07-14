package sky.core.utils.render.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.math.vector.Vector2f;
import sky.core.utils.Wrapper;
import sky.core.utils.misc.OtherUtil;

public class CustomFramebuffer extends Framebuffer implements Wrapper {
   private boolean linear;

   public CustomFramebuffer(boolean useDepth) {
      super(1, 1, useDepth, Minecraft.IS_RUNNING_ON_MAC);
   }

   public CustomFramebuffer(int width, int height, boolean useDepth) {
      super(width, height, useDepth, Minecraft.IS_RUNNING_ON_MAC);
   }

   private static void resizeFramebuffer(CustomFramebuffer framebuffer) {
      if (needsNewFramebuffer(framebuffer)) {
         framebuffer.createBuffers(Math.max(mc.getMainWindow().getFramebufferWidth(), 1), Math.max(mc.getMainWindow().getFramebufferHeight(), 1), Minecraft.IS_RUNNING_ON_MAC);
      }

   }

   public CustomFramebuffer setLinear() {
      this.linear = true;
      return this;
   }

   public void setFramebufferFilter(int framebufferFilterIn) {
      super.setFramebufferFilter(this.linear ? 9729 : framebufferFilterIn);
   }

   public void setup(boolean clear) {
      resizeFramebuffer(this);
      if (clear) {
         this.framebufferClear(Minecraft.IS_RUNNING_ON_MAC);
      }

      this.bindFramebuffer(false);
   }

   public void setup() {
      this.setup(true);
   }

   public static void drawQuads(double x, double y, double width, double height) {
      BUFFER.begin(7, DefaultVertexFormats.POSITION_TEX);
      BUFFER.pos(x, y, 0.0).tex(0.0F, 1.0F).endVertex();
      BUFFER.pos(x, y + height, 0.0).tex(0.0F, 0.0F).endVertex();
      BUFFER.pos(x + width, y + height, 0.0).tex(1.0F, 0.0F).endVertex();
      BUFFER.pos(x + width, y, 0.0).tex(1.0F, 1.0F).endVertex();
      TESSELLATOR.draw();
   }

   public static void drawQuads() {
      Vector2f window = OtherUtil.getMouse(mc.getMainWindow().getScaledWidth(), mc.getMainWindow().getScaledHeight());
      double width = (double)window.getX();
      double height = (double)window.getY();
      drawQuads(0.0, 0.0, width, height);
   }

   public void draw() {
      this.bindFramebufferTexture();
      drawQuads();
   }

   public static boolean needsNewFramebuffer(CustomFramebuffer framebuffer) {
      return framebuffer == null || framebuffer.framebufferWidth != mw.getFramebufferWidth() || framebuffer.framebufferHeight != mw.getFramebufferHeight();
   }

   public static CustomFramebuffer createFramebuffer(CustomFramebuffer framebuffer) {
      if (needsNewFramebuffer(framebuffer)) {
         if (framebuffer != null) {
            framebuffer.deleteFramebuffer();
         }

         return new CustomFramebuffer(mw.getFramebufferWidth(), mw.getFramebufferHeight(), true);
      } else {
         return framebuffer;
      }
   }
}
