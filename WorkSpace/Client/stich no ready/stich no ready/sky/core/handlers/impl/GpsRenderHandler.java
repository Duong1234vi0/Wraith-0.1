package sky.core.handlers.impl;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import sky.core.cmd.impl.GpsCommand;
import sky.core.events.EventRender2D;
import sky.core.utils.Wrapper;
import sky.core.utils.render.RenderUtil;
import sky.core.utils.render.font.Fonts;

public class GpsRenderHandler implements Wrapper {
   @EventTarget
   public void render(EventRender2D eventRender2D) {
      if (GpsCommand.gpsEnabled) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            double diffX = GpsCommand.x - mc.getRenderManager().info.getProjectedView().getX();
            double diffZ = GpsCommand.z - mc.getRenderManager().info.getProjectedView().getZ();
            var10000 = mc;
            float yaw = Minecraft.player.rotationYaw;
            double cos = (double)MathHelper.cos((float)((double)yaw * 0.017453292519943295));
            double sin = (double)MathHelper.sin((float)((double)yaw * 0.017453292519943295));
            double rotY = -(diffZ * cos - diffX * sin);
            double rotX = -(diffX * cos + diffZ * sin);
            float angle = (float)(Math.atan2(rotY, rotX) * 180.0 / Math.PI);
            Minecraft var10001 = mc;
            double var26 = Math.pow(GpsCommand.x - Minecraft.player.getPosX(), 2.0);
            Minecraft var10002 = mc;
            double dst = Math.sqrt(var26 + Math.pow(GpsCommand.z - Minecraft.player.getPosZ(), 2.0));
            float radius = 55.0F;
            float centerX = (float)mc.getMainWindow().getScaledWidth() / 2.0F;
            float centerY = (float)mc.getMainWindow().getScaledHeight() / 2.0F;
            float drawY = centerY - radius - 20.0F;
            float arrowSize = 52.0F;
            RenderSystem.pushMatrix();
            RenderSystem.translated((double)centerX, (double)drawY, 0.0);
            RenderSystem.rotatef(angle, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawImage2D(new ResourceLocation("SkyCore/icons/world_render/arrow_gps.png"), -arrowSize / 2.0F, -arrowSize / 2.0F, arrowSize, arrowSize, -1);
            RenderSystem.popMatrix();
            String text = (int)dst + "m";
            float textWidth = Fonts.sfregular[13].getWidth(text);
            Fonts.sfregular[13].drawString(eventRender2D.getStack(), text, (double)(centerX - textWidth / 2.0F + 1.0F), (double)(drawY - 15.0F), -1);
            return;
         }
      }

   }
}
