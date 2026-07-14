package sky.core.utils.render.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;

public class KawaseBlur {
   public static KawaseBlur blur = new KawaseBlur();
   public final CustomFramebuffer BLURRED = (new CustomFramebuffer(false)).setLinear();
   public final CustomFramebuffer ADDITIONAL = (new CustomFramebuffer(false)).setLinear();

   public void updateBlur(float offset, int steps) {
      Minecraft mc = Minecraft.getInstance();
      Framebuffer mcFramebuffer = mc.getFramebuffer();
      this.ADDITIONAL.setup();
      mcFramebuffer.bindFramebufferTexture();
      ShaderUtil.light_kawase_down.attach();
      ShaderUtil.light_kawase_down.setUniform("offset", offset);
      ShaderUtil.light_kawase_down.setUniformf("resolution", 1.0F / (float)mc.getMainWindow().getWidth(), 1.0F / (float)mc.getMainWindow().getHeight());
      CustomFramebuffer.drawQuads();
      CustomFramebuffer[] buffers = new CustomFramebuffer[]{this.ADDITIONAL, this.BLURRED};

      int i;
      int step;
      for(i = 1; i < steps; ++i) {
         step = i % 2;
         buffers[step].setup();
         buffers[(step + 1) % 2].draw();
      }

      ShaderUtil.light_kawase_down.detach();
      ShaderUtil.light_kawase_up.attach();
      ShaderUtil.light_kawase_up.setUniform("offset", offset);
      ShaderUtil.light_kawase_up.setUniformf("resolution", 1.0F / (float)mc.getMainWindow().getWidth(), 1.0F / (float)mc.getMainWindow().getHeight());

      for(i = 0; i < steps; ++i) {
         step = i % 2;
         buffers[(step + 1) % 2].setup();
         buffers[step].draw();
      }

      ShaderUtil.light_kawase_up.detach();
      mcFramebuffer.bindFramebuffer(false);
   }
}
