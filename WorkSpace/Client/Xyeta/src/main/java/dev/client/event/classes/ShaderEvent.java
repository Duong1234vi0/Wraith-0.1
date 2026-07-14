package dev.client.event.classes;

import dev.client.event.CancellableEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.Framebuffer;

@Environment(EnvType.CLIENT)
public class ShaderEvent extends CancellableEvent {
   private final Framebuffer beforeHandFbo;
   private final Framebuffer withHandFbo;

   public ShaderEvent(Framebuffer beforeHandFbo, Framebuffer withHandFbo) {
      this.beforeHandFbo = beforeHandFbo;
      this.withHandFbo = withHandFbo;
   }

   public Framebuffer getBeforeHandFbo() {
      return this.beforeHandFbo;
   }

   public Framebuffer getWithHandFbo() {
      return this.withHandFbo;
   }
}
