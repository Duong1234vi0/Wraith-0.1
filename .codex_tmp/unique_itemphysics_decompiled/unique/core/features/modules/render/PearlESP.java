package unique.core.features.modules.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1684;
import net.minecraft.class_1802;
import net.minecraft.class_243;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4587;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import unique.core.features.modules.Module;
import unique.core.utility.render.Render2DEngine;
import unique.core.utility.render.Render3DEngine;

public class PearlESP extends Module {
   private static final Color BACKGROUND_COLOR = new Color(10, 10, 10, 180);
   private final Map<class_1684, Float> appearAnimations = new HashMap<>();
   private final Map<class_1684, Long> lastSeen = new HashMap<>();
   private final Map<class_1684, Float> scaleAnimations = new HashMap<>();
   private final Map<class_1684, class_243> landingPositions = new HashMap<>();
   private final Map<class_1684, Long> landingTimes = new HashMap<>();

   public PearlESP() {
      super("PearlESP", Module.Category.RENDER);
   }

   @EventHandler
   @Override
   public void onRender2D(class_332 context) {
      if (!fullNullCheck()) {
         long currentTime = System.currentTimeMillis();
         HashSet<class_1684> toRemove = new HashSet<>();
         this.lastSeen.forEach((pearlx, time) -> {
            if (currentTime - time > 5000L || !pearlx.method_5805()) {
               toRemove.add(pearlx);
            }
         });

         for (class_1684 pearl : toRemove) {
            this.lastSeen.remove(pearl);
            this.appearAnimations.remove(pearl);
            this.scaleAnimations.remove(pearl);
            this.landingPositions.remove(pearl);
            this.landingTimes.remove(pearl);
         }

         for (class_1297 entity : mc.field_1687.method_18112()) {
            if (entity instanceof class_1684 pearl) {
               if (!this.landingPositions.containsKey(pearl)) {
                  PearlESP.PearlSimulationResult result = this.simulatePearlTrajectory(pearl);
                  if (result != null) {
                     this.landingPositions.put(pearl, result.landingPos);
                     this.landingTimes.put(pearl, System.currentTimeMillis() + result.flightTimeInTicks * 50L);
                  }
               }

               this.lastSeen.put(pearl, currentTime);
               this.appearAnimations.putIfAbsent(pearl, 0.0F);
               this.scaleAnimations.putIfAbsent(pearl, 0.0F);
               float appear = class_3532.method_16439(0.1F, this.appearAnimations.get(pearl), 1.0F);
               float scale = class_3532.method_16439(0.15F, this.scaleAnimations.get(pearl), 1.0F);
               this.appearAnimations.put(pearl, appear);
               this.scaleAnimations.put(pearl, scale);
               class_243 interp = new class_243(
                  class_3532.method_16436(Render3DEngine.getTickDelta(), pearl.field_6014, pearl.method_23317()),
                  class_3532.method_16436(Render3DEngine.getTickDelta(), pearl.field_6036, pearl.method_23318()),
                  class_3532.method_16436(Render3DEngine.getTickDelta(), pearl.field_5969, pearl.method_23321())
               );
               class_243 screen = Render3DEngine.worldSpaceToScreenSpace(interp.method_1031(0.0, 0.5, 0.0));
               if (screen.field_1350 > 0.0 && screen.field_1350 < 1.0) {
                  this.renderPearlTag(context, screen.field_1352, screen.field_1351, scale, appear);
               }

               if (this.landingPositions.containsKey(pearl) && this.landingTimes.containsKey(pearl)) {
                  class_243 lPos = this.landingPositions.get(pearl);
                  class_243 landingScreen = Render3DEngine.worldSpaceToScreenSpace(new class_243(lPos.field_1352, lPos.field_1351 + 0.2, lPos.field_1350));
                  long remain = this.landingTimes.get(pearl) - System.currentTimeMillis();
                  if (landingScreen.field_1350 > 0.0 && landingScreen.field_1350 < 1.0 && remain > 0L) {
                     this.renderLandingMarker(context, landingScreen.field_1352, landingScreen.field_1351, appear, remain);
                  }
               }
            }
         }
      }
   }

   @EventHandler
   @Override
   public void onRender3D(class_4587 matrices) {
      if (!fullNullCheck()) {
         for (class_1297 entity : mc.field_1687.method_18112()) {
            if (entity instanceof class_1684 pearl) {
               this.renderPearlTrajectory(pearl);
            }
         }
      }
   }

   private void renderPearlTrajectory(class_1684 pearl) {
      class_243 velocity = pearl.method_18798();
      class_243 lastPos = pearl.method_19538();

      for (int i = 0; i < 150; i++) {
         class_243 nextPos = lastPos.method_1019(velocity);
         class_3965 hit = mc.field_1687.method_17742(new class_3959(lastPos, nextPos, class_3960.field_17558, class_242.field_1348, mc.field_1724));
         Render3DEngine.drawLine(lastPos, nextPos, new Color(0, 255, 255, 200));
         if (hit.method_17783() == class_240.field_1332) {
            break;
         }

         velocity = velocity.method_1021(0.99).method_1031(0.0, -0.03, 0.0);
         lastPos = nextPos;
         if (nextPos.field_1351 < -64.0) {
            break;
         }
      }
   }

   private void renderPearlTag(class_332 context, double x, double y, float scale, float alpha) {
      float size = 26.0F * scale * alpha;
      float drawX = (float)x - size / 2.0F;
      float drawY = (float)y - size / 2.0F;
      Render2DEngine.drawRect(
         context.method_51448(), drawX, drawY, size, size, 4.0F, alpha, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR
      );
      context.method_51448().method_22903();
      context.method_51448().method_46416(drawX + size / 2.0F - 8.0F, drawY + size / 2.0F - 8.0F, 0.0F);
      context.method_51448().method_22905(0.9F, 0.9F, 1.0F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
      context.method_51427(class_1802.field_8634.method_7854(), 0, 0);
      context.method_51448().method_22909();
   }

   private void renderLandingMarker(class_332 context, double x, double y, float alpha, long timeMs) {
      String text = String.format("%.1fs", (float)timeMs / 1000.0F);
      float textWidth = mc.field_1772.method_1727(text);
      float width = textWidth + 26.0F;
      float height = 16.0F;
      float drawX = (float)x - width / 2.0F;
      float drawY = (float)y - height / 2.0F;
      Render2DEngine.drawRect(
         context.method_51448(), drawX, drawY, width, height, 4.0F, alpha, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR
      );
      context.method_51448().method_22903();
      context.method_51448().method_46416(drawX + 3.0F, drawY + 1.0F, 0.0F);
      context.method_51448().method_22905(0.75F, 0.75F, 1.0F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
      context.method_51427(class_1802.field_8634.method_7854(), 0, 0);
      context.method_51448().method_22909();
      int color = Render2DEngine.injectAlpha(Color.WHITE, (int)(alpha * 255.0F)).getRGB();
      context.method_51433(mc.field_1772, text, (int)(drawX + 18.0F), (int)(drawY + 4.0F), color, false);
   }

   private PearlESP.PearlSimulationResult simulatePearlTrajectory(class_1684 pearl) {
      double x = pearl.method_23317();
      double y = pearl.method_23318();
      double z = pearl.method_23321();
      double mx = pearl.method_18798().field_1352;
      double my = pearl.method_18798().field_1351;
      double mz = pearl.method_18798().field_1350;

      for (int tick = 0; tick < 300; tick++) {
         class_243 lastPos = new class_243(x, y, z);
         x += mx;
         y += my;
         z += mz;
         mx *= 0.99;
         my *= 0.99;
         mz *= 0.99;
         my -= 0.03;
         class_243 pos = new class_243(x, y, z);
         class_3965 hit = mc.field_1687.method_17742(new class_3959(lastPos, pos, class_3960.field_17558, class_242.field_1348, mc.field_1724));
         if (hit.method_17783() == class_240.field_1332) {
            return new PearlESP.PearlSimulationResult(hit.method_17784(), tick);
         }

         if (y < -64.0) {
            break;
         }
      }

      return null;
   }

   private static class PearlSimulationResult {
      public final class_243 landingPos;
      public final long flightTimeInTicks;

      public PearlSimulationResult(class_243 landingPos, long flightTimeInTicks) {
         this.landingPos = landingPos;
         this.flightTimeInTicks = flightTimeInTicks;
      }
   }
}
