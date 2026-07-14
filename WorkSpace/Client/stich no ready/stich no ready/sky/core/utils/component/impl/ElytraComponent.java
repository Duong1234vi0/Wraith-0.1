package sky.core.utils.component.impl;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.opengl.GL11;
import sky.core.SkyCore;
import sky.core.events.EventAttack;
import sky.core.events.EventRender3D;
import sky.core.handlers.impl.Rotation;
import sky.core.modules.impl.combat.AttackAura;
import sky.core.modules.impl.movement.ElytraTarget;
import sky.core.modules.impl.movement.FreeCamera;
import sky.core.ui.gui.themes.ThemeEditor;
import sky.core.ui.gui.themes.ThemeSettings;
import sky.core.utils.component.RotationAccess;
import sky.core.utils.math.TimeUtil;
import sky.core.utils.render.ColorUtil;

public class ElytraComponent extends RotationAccess {
   public static boolean defensiveActive = false;
   public static Vector3d leaveVec;
   public static Vector3d lastVec;
   public static boolean predictCondition;
   private static int movementTicks;
   private static boolean prePredictCondition;
   private static final TimeUtil tickStopWatch;
   public static boolean blockPos;
   public static Vector3d pos;
   public static double value2;
   public static Vector3d vector;

   public static void renderPredictCube(EventRender3D event, ElytraTarget elytraTarget) {
      Minecraft var10000 = mc;
      if (Minecraft.player != null && mc.world != null) {
         if (elytraTarget != null && elytraTarget.isEnabled()) {
            if ((Boolean)elytraTarget.getTarget().get()) {
               if ((Boolean)elytraTarget.getPredictCube().get()) {
                  if (elytraTarget.supportsPredictCube()) {
                     var10000 = mc;
                     if (Minecraft.player.isElytraFlying()) {
                        AttackAura aura = SkyCore.getInstance().getModuleManager().getAttackAura();
                        if (aura != null) {
                           LivingEntity auraTarget = AttackAura.getTarget();
                           if (auraTarget != null) {
                              if (auraTarget.isElytraFlying()) {
                                 if (pos != null && !pos.equals(Vector3d.ZERO)) {
                                    Vector3d cam = mc.getRenderManager().info.getProjectedView();
                                    double x = pos.x - cam.x;
                                    double y = pos.y - cam.y;
                                    double z = pos.z - cam.z;
                                    double r = 0.35;
                                    AxisAlignedBB bb = new AxisAlignedBB(x - r, y - r, z - r, x + r, y + r, z + r);
                                    int baseColor = (Boolean)elytraTarget.getPredictFromTheme().get() ? ThemeEditor.getColor(ThemeSettings.MODULE_VISUAL) : ColorUtil.rgb(255, 255, 255);
                                    int fillColor = ColorUtil.setAlpha(baseColor, ((Float)elytraTarget.getPredictFillAlpha().get()).intValue());
                                    int lineColor = ColorUtil.setAlpha(baseColor, 255);
                                    String boxMode = (String)elytraTarget.getPredictBoxMode().get();
                                    if (boxMode.equals("Пунктир")) {
                                       drawPredictFill(bb, fillColor);
                                       drawPredictDashedOutline(bb, lineColor);
                                    } else if (boxMode.equals("Диагонали")) {
                                       drawPredictFill(bb, fillColor);
                                       drawPredictSolidOutline(bb, lineColor);
                                       drawPredictBodyDiagonals(bb, lineColor);
                                    } else {
                                       drawPredictFill(bb, fillColor);
                                       drawPredictSolidOutline(bb, lineColor);
                                    }

                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static void drawPredictFill(AxisAlignedBB bb, int color) {
      if ((color >> 24 & 255) > 0) {
         setupPredictRender();
         Tessellator tessellator = Tessellator.getInstance();
         BufferBuilder buffer = tessellator.getBuffer();
         buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
         float a = (float)(color >> 24 & 255) / 255.0F;
         float r = (float)(color >> 16 & 255) / 255.0F;
         float g = (float)(color >> 8 & 255) / 255.0F;
         float b = (float)(color & 255) / 255.0F;
         addPredictBoxFaces(buffer, bb, r, g, b, a);
         tessellator.draw();
         cleanupPredictRender();
      }
   }

   private static void drawPredictSolidOutline(AxisAlignedBB bb, int color) {
      setupPredictRender();
      GL11.glEnable(2848);
      GL11.glLineWidth(1.5F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      addPredictBoxEdges(buffer, bb, r, g, b, a);
      tessellator.draw();
      GL11.glDisable(2848);
      cleanupPredictRender();
   }

   private static void drawPredictDashedOutline(AxisAlignedBB bb, int color) {
      setupPredictRender();
      GL11.glEnable(2848);
      GL11.glLineWidth(1.5F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      float dash = 0.12F;
      float gap = 0.1F;
      double[][] edges = new double[][]{{bb.minX, bb.minY, bb.minZ, bb.maxX, bb.minY, bb.minZ}, {bb.maxX, bb.minY, bb.minZ, bb.maxX, bb.minY, bb.maxZ}, {bb.maxX, bb.minY, bb.maxZ, bb.minX, bb.minY, bb.maxZ}, {bb.minX, bb.minY, bb.maxZ, bb.minX, bb.minY, bb.minZ}, {bb.minX, bb.maxY, bb.minZ, bb.maxX, bb.maxY, bb.minZ}, {bb.maxX, bb.maxY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ}, {bb.maxX, bb.maxY, bb.maxZ, bb.minX, bb.maxY, bb.maxZ}, {bb.minX, bb.maxY, bb.maxZ, bb.minX, bb.maxY, bb.minZ}, {bb.minX, bb.minY, bb.minZ, bb.minX, bb.maxY, bb.minZ}, {bb.maxX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.minZ}, {bb.maxX, bb.minY, bb.maxZ, bb.maxX, bb.maxY, bb.maxZ}, {bb.minX, bb.minY, bb.maxZ, bb.minX, bb.maxY, bb.maxZ}};
      double[][] var11 = edges;
      int var12 = edges.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         double[] edge = var11[var13];
         drawPredictDashedLine(buffer, edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], dash, gap, r, g, b, a);
      }

      tessellator.draw();
      GL11.glDisable(2848);
      cleanupPredictRender();
   }

   private static void drawPredictBodyDiagonals(AxisAlignedBB bb, int color) {
      setupPredictRender();
      GL11.glEnable(2848);
      GL11.glLineWidth(1.2F);
      Tessellator tessellator = Tessellator.getInstance();
      BufferBuilder buffer = tessellator.getBuffer();
      buffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
      float a = (float)(color >> 24 & 255) / 255.0F;
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      double[][] diagonals = new double[][]{{bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ}, {bb.maxX, bb.minY, bb.minZ, bb.minX, bb.maxY, bb.maxZ}, {bb.minX, bb.minY, bb.maxZ, bb.maxX, bb.maxY, bb.minZ}, {bb.maxX, bb.minY, bb.maxZ, bb.minX, bb.maxY, bb.minZ}};
      double[][] var9 = diagonals;
      int var10 = diagonals.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         double[] d = var9[var11];
         buffer.pos(d[0], d[1], d[2]).color(r, g, b, a).endVertex();
         buffer.pos(d[3], d[4], d[5]).color(r, g, b, a).endVertex();
      }

      tessellator.draw();
      GL11.glDisable(2848);
      cleanupPredictRender();
   }

   private static void drawPredictDashedLine(BufferBuilder buffer, double x1, double y1, double z1, double x2, double y2, double z2, float dashLen, float gapLen, float r, float g, float b, float a) {
      double dx = x2 - x1;
      double dy = y2 - y1;
      double dz = z2 - z1;
      double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
      if (!(len < 0.001)) {
         double nx = dx / len;
         double ny = dy / len;
         double nz = dz / len;
         double pos = 0.0;

         for(boolean draw = true; pos < len; draw = !draw) {
            double end = Math.min(pos + (double)(draw ? dashLen : gapLen), len);
            if (draw) {
               buffer.pos(x1 + nx * pos, y1 + ny * pos, z1 + nz * pos).color(r, g, b, a).endVertex();
               buffer.pos(x1 + nx * end, y1 + ny * end, z1 + nz * end).color(r, g, b, a).endVertex();
            }

            pos = end;
         }

      }
   }

   private static void addPredictBoxFaces(BufferBuilder buffer, AxisAlignedBB bb, float r, float g, float b, float a) {
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
   }

   private static void addPredictBoxEdges(BufferBuilder buffer, AxisAlignedBB bb, float r, float g, float b, float a) {
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.minZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.maxX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.minY, bb.maxZ).color(r, g, b, a).endVertex();
      buffer.pos(bb.minX, bb.maxY, bb.maxZ).color(r, g, b, a).endVertex();
   }

   private static void setupPredictRender() {
      RenderSystem.disableTexture();
      RenderSystem.enableBlend();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.disableCull();
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
   }

   private static void cleanupPredictRender() {
      RenderSystem.enableCull();
      RenderSystem.depthMask(true);
      RenderSystem.enableDepthTest();
      RenderSystem.enableTexture();
   }

   public static void processTargetLogic(ElytraTarget elytraTarget) {
      float distanceValue = (Float)elytraTarget.distance.get();
      float forward = distanceValue < 4.0F ? 0.0F : distanceValue - 3.0F;
      Vector3d eyePos = target.getEyePosition(1.0F);
      Vector3d resol = target.getForward();
      pos = eyePos.add(resol.normalize().scale((double)distanceValue));
      Vector3d var6 = eyePos.add(resol.normalize().scale((double)forward));
      updateDistances(var6);
   }

   public static void setVector(Vector3d vector1) {
      vector = vector1;
   }

   public ElytraComponent() {
      super("ElytraComponent");
   }

   public static void updateDistances(Vector3d var1) {
      Minecraft var10001 = mc;
      float var2 = (float)var1.distanceTo(Minecraft.player.getEyePosition(1.0F));
      Minecraft var10000 = mc;
      float var3 = Minecraft.player.getDistance(target);
      value2 = blockPos ? (double)var2 : (double)var3;
   }

   public static void smartPredict() {
      if (tickStopWatch.isReached(50L)) {
         if (target != null) {
            float x = (float)(target.getPosX() - target.prevPosX);
            float y = (float)(target.getPosY() - target.prevPosY);
            float z = (float)(target.getPosZ() - target.prevPosZ);
            float dist = (float)Math.sqrt((double)(x * x + y * y + z * z));
            float finalSpeed = (float)((double)dist * 20.0);
            if (finalSpeed > 20.0F) {
               movementTicks = 0;
               prePredictCondition = false;
               predictCondition = true;
            } else {
               ++movementTicks;
               if (predictCondition) {
                  prePredictCondition = true;
               }

               if (movementTicks >= 3) {
                  predictCondition = false;
                  movementTicks = 0;
                  prePredictCondition = false;
               } else {
                  predictCondition = prePredictCondition;
               }
            }
         }

         tickStopWatch.reset();
      }

   }

   public static void updateRotation(Vector2f current) {
      Vector3d vec3d = vector;
      float rawYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d.z, vec3d.x)) - 90.0);
      float rawPitch = (float)MathHelper.wrapDegrees(Math.toDegrees(-Math.atan2(vec3d.y, Math.hypot(vec3d.x, vec3d.z))));
      float currentYaw = current.x;
      float currentPitch = current.y;
      float yawDelta = MathHelper.wrapDegrees(rawYaw - currentYaw);
      float pitchDelta = MathHelper.wrapDegrees(rawPitch - currentPitch);
      if (Math.abs(yawDelta) > 180.0F) {
         Minecraft var10000 = mc;
         if (Minecraft.player.isSwimming()) {
            yawDelta -= Math.signum(yawDelta) * 360.0F;
         }
      }

      float finalDeltaYaw = MathHelper.clamp(yawDelta, -180.0F, 180.0F);
      float finalDeltaPitch = MathHelper.clamp(pitchDelta, -90.0F, 90.0F);
      float newYaw = currentYaw + finalDeltaYaw;
      float newPitch = currentPitch + finalDeltaPitch;
      float finalNewPitch = MathHelper.clamp(newPitch, -89.9F, 89.9F);
      float speedYaw = 150.0F;
      float speedPitch = 150.0F;
      if (blockPos) {
         speedYaw = 9999.0F;
         speedPitch = 9999.0F;
      }

      RotationComponent.update(new Rotation(newYaw, finalNewPitch), speedYaw, speedPitch, 360.0F, 360.0F, 0, 1, false);
   }

   public static void attack(EventAttack e) {
   }

   public static Vector3d getVector3d(LivingEntity me, LivingEntity to) {
      ElytraTarget elytraTarget = SkyCore.getInstance().getModuleManager().getElytraTarget();
      FreeCamera freeCamera = (FreeCamera)SkyCore.getInstance().getModuleManager().getModule(FreeCamera.class);
      if (me != null && to != null && elytraTarget != null && elytraTarget.isEnabled()) {
         boolean reallyWorldMode;
         boolean testMode;
         boolean pedikMode;
         Minecraft var10000;
         boolean var21;
         label87: {
            reallyWorldMode = elytraTarget.predictMode.is("SDfsdf") || elytraTarget.predictMode.is("Ddsfsdf");
            testMode = elytraTarget.predictMode.is("ReallyWorld");
            pedikMode = elytraTarget.isPedikPredict();
            if ((Boolean)elytraTarget.target.get()) {
               var10000 = mc;
               if (Minecraft.player.isElytraFlying() && to.isElytraFlying() && (freeCamera == null || !freeCamera.isEnabled())) {
                  var21 = true;
                  break label87;
               }
            }

            var21 = false;
         }

         boolean baseChase = var21;
         Vector3d fenal;
         boolean useChasePredictPath;
         double wHalf;
         Vector3d targetPos;
         Vector3d motionOffset;
         Vector3d add;
         Vector3d playerPos;
         if (baseChase && reallyWorldMode) {
            useChasePredictPath = true;
            wHalf = (double)(Float)elytraTarget.distance.get();
            add = new Vector3d(0.0, MathHelper.clamp(to.getPosY() - (double)to.getHeight(), 0.0, (double)(to.getHeight() / 2.0F)), 0.0);
            targetPos = to.getPositionVec().add(add);
            playerPos = me.getEyePosition(mc.getRenderPartialTicks());
            motionOffset = to.getMotion().mul(wHalf, wHalf, wHalf);
            fenal = targetPos.subtract(playerPos).add(motionOffset);
         } else {
            Vector3d pedik;
            if (baseChase && testMode) {
               useChasePredictPath = true;
               pedik = pos.add(to.getForward().normalize().scale(2.0));
               fenal = new Vector3d(pedik.getX() - me.getPosX(), pedik.getY() - me.getPosY(), pedik.getZ() - me.getPosZ());
            } else if (baseChase && pedikMode) {
               useChasePredictPath = true;
               wHalf = (double)(Float)elytraTarget.distance.get();
               add = new Vector3d(0.0, MathHelper.clamp(to.getPosY() - (double)to.getHeight(), 0.0, (double)(to.getHeight() / 2.0F)), 0.0);
               targetPos = to.getPositionVec().add(add);
               playerPos = me.getEyePosition(mc.getRenderPartialTicks());
               motionOffset = to.getMotion().mul(wHalf, wHalf, wHalf);
               fenal = targetPos.subtract(playerPos).add(motionOffset);
            } else if (baseChase && predictCondition) {
               useChasePredictPath = true;
               pedik = pos.add(to.getForward().normalize().scale(2.0));
               fenal = new Vector3d(pedik.getX() - me.getPosX(), pedik.getY() - me.getPosY(), pedik.getZ() - me.getPosZ());
            } else {
               useChasePredictPath = false;
               wHalf = (double)(to.getWidth() / 2.0F);
               double var22 = to.getPosYEye() - to.getPosY();
               float var10002 = to.getHeight();
               Minecraft var10003 = mc;
               double yExpand = MathHelper.clamp(var22, 0.0, (double)(var10002 - (Minecraft.player.isElytraFlying() ? 0.0F : 0.5F)));
               var10000 = mc;
               double xExpand = MathHelper.clamp(Minecraft.player.getPosX() - to.getPosX(), -wHalf, wHalf);
               var10000 = mc;
               double zExpand = MathHelper.clamp(Minecraft.player.getPosZ() - to.getPosZ(), -wHalf, wHalf);
               double var23 = to.getPosX();
               var10003 = mc;
               var23 = var23 - Minecraft.player.getPosX() + xExpand;
               double var24 = to.getPosY();
               Minecraft var10004 = mc;
               var24 = var24 - Minecraft.player.getPosYEye() + yExpand;
               double var25 = to.getPosZ();
               Minecraft var10005 = mc;
               fenal = new Vector3d(var23, var24, var25 - Minecraft.player.getPosZ() + zExpand);
            }
         }

         blockPos = useChasePredictPath;
         return fenal;
      } else {
         return Vector3d.ZERO;
      }
   }

   public static void updateDefensiveState(LivingEntity target) {
      defensiveActive = false;
   }

   public static void resetState() {
      defensiveActive = false;
      leaveVec = Vector3d.ZERO;
      lastVec = Vector3d.ZERO;
   }

   static {
      leaveVec = Vector3d.ZERO;
      lastVec = Vector3d.ZERO;
      predictCondition = false;
      movementTicks = 0;
      prePredictCondition = false;
      tickStopWatch = new TimeUtil();
      pos = Vector3d.ZERO;
      value2 = 0.0;
      vector = Vector3d.ZERO;
   }
}
