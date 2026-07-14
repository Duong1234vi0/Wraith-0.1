package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventRender2D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.SliderSetting;
import sky.core.utils.render.shader.CustomFramebuffer;
import sky.core.utils.render.shader.ShaderUtil;

public class Saturation extends Module {
   private final SliderSetting saturation = new SliderSetting("Насыщенность", 1.0F, 0.0F, 2.0F, 0.05F);
   private CustomFramebuffer cacheFramebuffer;

   public Saturation() {
      super("Saturation", "Изменяет насыщенность цветов на экране", Category.Visuals);
      this.addSettings(new Setting[]{this.saturation});
   }

   @EventTarget
   public void onRender(EventRender2D.Send event) {
      if (mc.world != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            RenderSystem.bindRenderThread();
            if (!RenderSystem.isOnRenderThread()) {
               return;
            }

            float saturationValue = (Float)this.saturation.get();
            if (Math.abs(saturationValue - 1.0F) < 0.001F) {
               return;
            }

            Framebuffer mainFramebuffer = mc.getFramebuffer();
            this.cacheFramebuffer = CustomFramebuffer.createFramebuffer(this.cacheFramebuffer);
            RenderSystem.pushMatrix();
            GlStateManager.disableDepthTest();
            GlStateManager.enableTexture();
            GlStateManager.disableBlend();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.cacheFramebuffer.setup(true);
            mainFramebuffer.bindFramebufferTexture();
            GL11.glBindTexture(3553, mainFramebuffer.framebufferTexture);
            CustomFramebuffer.drawQuads();
            mainFramebuffer.bindFramebuffer(true);
            ShaderUtil.saturation.useProgram();
            GL11.glBindTexture(3553, this.cacheFramebuffer.framebufferTexture);
            ShaderUtil.saturation.setUniform("texture", 0);
            ShaderUtil.saturation.setUniformf("saturation", saturationValue);
            CustomFramebuffer.drawQuads();
            ShaderUtil.saturation.unloadProgram();
            GlStateManager.enableBlend();
            GlStateManager.enableDepthTest();
            RenderSystem.popMatrix();
            return;
         }
      }

   }

   public void onDisable() {
      if (this.cacheFramebuffer != null) {
         this.cacheFramebuffer.deleteFramebuffer();
         this.cacheFramebuffer = null;
      }

      super.onDisable();
   }
}
