package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import sky.core.events.EventRender3D;
import sky.core.events.EventUpdate;
import sky.core.handlers.richidog.RichiBrain;
import sky.core.handlers.richidog.RichiModel;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.ModeSetting;

public class RichiDog extends Module {
   private final ModeSetting variant = new ModeSetting("Вид", "Такса", new String[]{"Такса", "DJ"});
   private final RichiModel model = new RichiModel(RenderType::getEntityCutoutNoCull);
   private final RichiBrain brain = new RichiBrain();

   public RichiDog() {
      super("Pets", "Питомец-собака рядом с игроком", Category.Visuals);
      this.addSettings(new Setting[]{this.variant});
   }

   public void onEnable() {
      this.brain.reset();
      super.onEnable();
   }

   public void onDisable() {
      this.brain.reset();
      super.onDisable();
   }

   @EventTarget
   private void onUpdate(EventUpdate event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null) {
         Minecraft var10001 = mc;
         this.brain.setEntity(Minecraft.player);
      }

      this.brain.onUpdate(event);
   }

   @EventTarget
   private void onRender(EventRender3D event) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         MatrixStack ms = new MatrixStack();
         float partialTicks = event.getPartialTicks();
         Vector3d cam = mc.getRenderManager().info.getProjectedView();
         ms.push();
         ms.translate(this.brain.getPos().x - cam.x, this.brain.getPos().y - cam.y, this.brain.getPos().z - cam.z);
         Minecraft var10001 = mc;
         this.model.setRotationAngles((float)Minecraft.player.ticksExisted + partialTicks, this.brain);
         ResourceLocation tex = this.variant.is("DJ") ? RichiModel.TEXTURE_DJ : RichiModel.TEXTURE_TAKSA;
         IRenderTypeBuffer.Impl buffers = mc.getRenderTypeBuffers().getBufferSource();
         int packedLight = WorldRenderer.getCombinedLight(mc.world, new BlockPos(this.brain.getPos()));
         this.model.render(ms, buffers, packedLight, OverlayTexture.NO_OVERLAY, tex, this.brain);
         buffers.finish(RenderType.getEntityTranslucent(tex));
         ms.pop();
      }
   }
}
