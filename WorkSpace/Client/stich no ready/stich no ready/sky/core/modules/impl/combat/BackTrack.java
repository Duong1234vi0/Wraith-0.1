package sky.core.modules.impl.combat;

import com.adl.nativeprotect.Native;
import com.darkmagician6.eventapi.eventapinew.EventTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.events.EventMotion;
import sky.core.events.EventRender3D;
import sky.core.modules.Category;
import sky.core.modules.Module;
import sky.core.modules.api.constructors.Setting;
import sky.core.modules.api.constructors.impl.BooleanSetting;
import sky.core.modules.api.constructors.impl.SliderSetting;

public class BackTrack extends Module {
   private final SliderSetting time = new SliderSetting("Время (мс)", 600.0F, 100.0F, 1000.0F, 50.0F);
   public final BooleanSetting render = new BooleanSetting("Отображать", true);
   private final Map backtrackPositions = new HashMap();

   public BackTrack() {
      super("BackTrack", "Задерживает хитбокс игроков для увеличения дальности удара", Category.Combat);
      this.addSettings(new Setting[]{this.time, this.render});
   }

   @EventTarget
   @Native
   public void gandon(EventMotion eventMotion) {
      this.handleMotion();
   }

   @EventTarget
   @Native
   public void render(EventRender3D eventRender3D) {
      this.handleRender(eventRender3D);
   }

   @Native
   private void handleMotion() {
      long maxDelay = ((Float)this.time.get()).longValue();
      Iterator var3 = mc.world.getPlayers().iterator();

      while(var3.hasNext()) {
         PlayerEntity player = (PlayerEntity)var3.next();
         Minecraft var10001 = mc;
         if (player != Minecraft.player) {
            List positions = (List)this.backtrackPositions.computeIfAbsent(player, (k) -> {
               return new ArrayList();
            });
            positions.add(new Position(new Vector3d(player.getPosX(), player.getPosY(), player.getPosZ()), System.currentTimeMillis()));
            positions.removeIf((pos) -> {
               return System.currentTimeMillis() - pos.getTime() > maxDelay;
            });
         }
      }

   }

   @Native
   public Position getOldestPosition(PlayerEntity player) {
      if (!this.isEnabled()) {
         return null;
      } else {
         List positions = (List)this.backtrackPositions.get(player);
         return positions != null && !positions.isEmpty() ? (Position)positions.get(0) : null;
      }
   }

   @Native
   public AxisAlignedBB getBackTrackBox(PlayerEntity player, Position backtrackPos) {
      if (backtrackPos == null) {
         return null;
      } else {
         float width = player.getWidth();
         float height = player.getHeight();
         Vector3d pos = backtrackPos.getPos();
         return new AxisAlignedBB(pos.x - (double)width / 2.0, pos.y, pos.z - (double)width / 2.0, pos.x + (double)width / 2.0, pos.y + (double)height, pos.z + (double)width / 2.0);
      }
   }

   @Native
   private void handleRender(EventRender3D e) {
      if ((Boolean)this.render.get()) {
         EntityRendererManager renderManager = mc.getRenderManager();
         if (renderManager != null && renderManager.info != null) {
            long maxDelay = ((Float)this.time.get()).longValue();
            GL11.glPushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.depthMask(false);
            GlStateManager.disableTexture();
            GL11.glShadeModel(7425);
            GlStateManager.disableCull();
            GlStateManager.enableDepthTest();
            GlStateManager.blendFunc(770, 772);
            Iterator var5 = mc.world.getPlayers().iterator();

            while(true) {
               PlayerEntity player;
               do {
                  if (!var5.hasNext()) {
                     GlStateManager.blendFunc(770, 771);
                     GL11.glShadeModel(7424);
                     GlStateManager.disableBlend();
                     GlStateManager.enableTexture();
                     GlStateManager.depthMask(true);
                     GlStateManager.enableCull();
                     GL11.glPopMatrix();
                     return;
                  }

                  player = (PlayerEntity)var5.next();
                  Minecraft var10001 = mc;
               } while(player == Minecraft.player);

               List positions = (List)this.backtrackPositions.getOrDefault(player, new ArrayList());
               Iterator var8 = positions.iterator();

               while(var8.hasNext()) {
                  Position position = (Position)var8.next();

                  try {
                     Vector3d pos = position.getPos();
                     Vector3d cameraPos = renderManager.info.getProjectedView();
                     double x = pos.x - cameraPos.x;
                     double y = pos.y - cameraPos.y;
                     double z = pos.z - cameraPos.z;
                     float timePassed = (float)(System.currentTimeMillis() - position.getTime());
                     float alpha = 1.0F - timePassed / (float)maxDelay;
                     alpha = Math.max(0.0F, Math.min(alpha, 0.5F));
                     GL11.glPushMatrix();
                     GL11.glEnable(3042);
                     GL11.glLineWidth(2.0F);
                     GL11.glDisable(3553);
                     GL11.glDisable(2929);
                     float width = player.getWidth();
                     float height = player.getHeight();
                     AxisAlignedBB box = new AxisAlignedBB(x - (double)width / 2.0, y, z - (double)width / 2.0, x + (double)width / 2.0, y + (double)height, z + (double)width / 2.0);
                     int color = (new Color(1.0F, 1.0F, 1.0F, alpha)).getRGB();
                     drawBox(box, color);
                     GL11.glLineWidth(1.0F);
                     GL11.glEnable(3553);
                     GL11.glEnable(2929);
                     GL11.glDisable(3042);
                     GL11.glPopMatrix();
                  } catch (Exception var24) {
                  }
               }
            }
         }
      }
   }

   public static void drawBox(AxisAlignedBB bb, int color) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glLineWidth(1.0F);
      float[] rgb = BackTrack.IntColor.rgb(color);
      GlStateManager.color4f(rgb[0], rgb[1], rgb[2], rgb[3]);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder vertexbuffer = tessellator.getBuffer();
      vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(bb.minX, bb.minY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.minY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.minY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.minY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.minY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      tessellator.draw();
      vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(bb.minX, bb.maxY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.maxY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.maxY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.maxY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      tessellator.draw();
      vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
      vertexbuffer.pos(bb.minX, bb.minY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.maxY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.minY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.maxY, bb.minZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.minY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.minY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      vertexbuffer.pos(bb.minX, bb.maxY, bb.maxZ).color(rgb[0], rgb[1], rgb[2], rgb[3]).endVertex();
      tessellator.draw();
      GlStateManager.color4f(rgb[0], rgb[1], rgb[2], rgb[3]);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glPopMatrix();
   }

   public void onEnable() {
      this.backtrackPositions.clear();
   }

   public void onDisable() {
      this.backtrackPositions.clear();
   }

   static class Position {
      private final Vector3d pos;
      private final long time;

      public Position(Vector3d pos, long time) {
         this.pos = pos;
         this.time = time;
      }

      public Vector3d getPos() {
         return this.pos;
      }

      public long getTime() {
         return this.time;
      }
   }

   public static class IntColor {
      public static float[] rgb(int color) {
         return new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F, (float)(color >> 24 & 255) / 255.0F};
      }

      public static int getRed(int hex) {
         return hex >> 16 & 255;
      }

      public static int getGreen(int hex) {
         return hex >> 8 & 255;
      }

      public static int getBlue(int hex) {
         return hex & 255;
      }

      public static int getAlpha(int hex) {
         return hex >> 24 & 255;
      }
   }
}
