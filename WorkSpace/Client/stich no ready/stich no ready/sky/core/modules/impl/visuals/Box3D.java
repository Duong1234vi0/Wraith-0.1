package sky.core.modules.impl.visuals;

import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.StopWatch;
import sky.core.utils.render.ColorUtil;

public class Box3D extends Module {
   private final BooleanSetting pulse = new BooleanSetting("Пульсация", false);
   private final StopWatch stopWatch = new StopWatch();
   private float alpha = 50.0F;
   private boolean decrease = true;
   private final BufferBuilder buffer = Tessellator.getInstance().getBuffer();
   private final Tessellator tessellator = Tessellator.getInstance();

   public Box3D() {
      super("3DBox", "Отображает контуры игроков", Category.Visuals);
      this.addSettings(new Setting[]{this.pulse});
   }

   @EventTarget
   private void onRender(EventRender3D event) {
      if (mc.world != null) {
         Minecraft var10000 = mc;
         if (Minecraft.player != null) {
            Vector3d cam = mc.getRenderManager().info.getProjectedView();
            RenderSystem.pushMatrix();
            RenderSystem.translated(-cam.x, -cam.y, -cam.z);
            Iterator var3 = mc.world.getAllEntities().iterator();

            while(var3.hasNext()) {
               Entity entity = (Entity)var3.next();
               if (!this.shouldSkipPlayer(entity)) {
                  this.renderPlayerBox((LivingEntity)entity, mc.getRenderPartialTicks());
               }
            }

            RenderSystem.popMatrix();
            return;
         }
      }

   }

   private boolean shouldSkipPlayer(Entity entity) {
      boolean var10000;
      if (entity != null && entity.isAlive() && entity instanceof PlayerEntity) {
         Minecraft var10001 = mc;
         if (entity != Minecraft.player || !mc.gameSettings.getPointOfView().firstPerson()) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   private void renderPlayerBox(LivingEntity player, float tickDelta) {
      double x = MathHelper.lerp((double)tickDelta, player.lastTickPosX, player.getPosX());
      double y = MathHelper.lerp((double)tickDelta, player.lastTickPosY, player.getPosY());
      double z = MathHelper.lerp((double)tickDelta, player.lastTickPosZ, player.getPosZ());
      AxisAlignedBB bb = player.getBoundingBox().offset(-player.getPosX() + x, -player.getPosY() + y, -player.getPosZ() + z);
      float hurtPercent = (float)Math.sin((double)player.hurtTime * Math.PI / 10.0);
      boolean friend = this.isFriend(player);
      int baseColor = friend ? ColorUtil.getColor(0, 255, 0) : ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL);
      int hurtColor = ColorUtil.getColor(255, 0, 0);
      int color = this.overCol(baseColor, hurtColor, hurtPercent);
      if ((Boolean)this.pulse.get()) {
         if (this.stopWatch.finished(25.0)) {
            if (this.decrease) {
               this.alpha -= 2.0F;
               if (this.alpha <= 30.0F) {
                  this.alpha = 30.0F;
                  this.decrease = false;
               }
            } else {
               this.alpha += 2.0F;
               if (this.alpha >= 50.0F) {
                  this.alpha = 60.0F;
                  this.decrease = true;
               }
            }

            this.stopWatch.reset();
         }
      } else {
         this.alpha = 40.0F;
      }

      int fillColor = ColorUtil.setAlpha(color, (int)this.alpha);
      int outlineColor = ColorUtil.setAlpha(color, 255);
      this.drawFill(bb, fillColor);
      this.drawOutline(bb, outlineColor, 1.0F);
   }

   private void drawFill(AxisAlignedBB bb, int color) {
      this.setupRender();
      RenderSystem.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableAlphaTest();
      RenderSystem.shadeModel(7425);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      this.buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      this.addBoxVertices(bb, r, g, b, a);
      this.tessellator.draw();
      RenderSystem.enableAlphaTest();
      RenderSystem.shadeModel(7424);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      this.cleanupRender();
   }

   private void drawOutline(AxisAlignedBB bb, int color, float width) {
      this.setupRender();
      RenderSystem.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableAlphaTest();
      RenderSystem.shadeModel(7425);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glLineWidth(width);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      this.buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      this.addBoxOutlineVertices(bb, r, g, b, a);
      this.tessellator.draw();
      GL11.glDisable(2848);
      RenderSystem.enableAlphaTest();
      RenderSystem.shadeModel(7424);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
      this.cleanupRender();
   }

   private void addBoxVertices(AxisAlignedBB bb, float r, float g, float b, float a) {
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
   }

   private void addBoxOutlineVertices(AxisAlignedBB bb, float r, float g, float b, float a) {
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      this.buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
   }

   private void setupRender() {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableTexture();
      RenderSystem.disableLighting();
      RenderSystem.disableCull();
   }

   private void cleanupRender() {
      RenderSystem.enableCull();
      RenderSystem.enableTexture();
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
   }

   private boolean isFriend(Entity entity) {
      return entity instanceof PlayerEntity && SkyCore.getInstance().getFriendManager().isFriend(entity.getName().getString());
   }

   public int overCol(int color1, int color2, float percent) {
      percent = MathHelper.clamp(percent, 0.0F, 1.0F);
      return ColorUtil.getColor((int)MathHelper.lerp(percent, (float)ColorUtil.red(color1), (float)ColorUtil.red(color2)), (int)MathHelper.lerp(percent, (float)ColorUtil.green(color1), (float)ColorUtil.green(color2)), (int)MathHelper.lerp(percent, (float)ColorUtil.blue(color1), (float)ColorUtil.blue(color2)), ColorUtil.alpha(color1));
   }
}
